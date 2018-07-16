package com.lms.atom.borrow.service;

import com.lms.atom.book.service.ResourceService;
import com.lms.atom.borrow.BorrowConcurrencyHelper;
import com.lms.atom.borrow.storage.ResourceBorrowHelper;
import com.lms.atom.borrow.storage.ResourceBorrowRepository;
import com.lms.atom.borrow.storage.ResourceBorrowStorage;
import com.lms.atom.borrow.storage.model.ResourceBorrow;
import com.lms.atom.exception.AtomException;
import com.lms.common.dto.atom.resource.ResourceBorrowDTO;
import com.lms.common.dto.atom.resource.ResourceDTO;
import com.lms.common.dto.response.ListResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ResourceBorrowServiceImpl implements ResourceBorrowService {

    private final ResourceBorrowRepository repository;
    private final ResourceBorrowStorage storage;
    private final ResourceService resourceService;

    @Autowired
    public ResourceBorrowServiceImpl(ResourceBorrowRepository repository, ResourceBorrowStorage storage, ResourceService resourceService) {
        this.repository = repository;
        this.storage = storage;
        this.resourceService = resourceService;
    }

    @Override
    public ResourceBorrowDTO updateResourceBorrow(ResourceBorrowDTO borrow) throws AtomException {
        ResourceBorrowDTO resourceBorrowDTO = ResourceBorrowHelper.fromEntity(repository.save(ResourceBorrowHelper.toEntity(borrow)));
        ResourceDTO resourceById = resourceService.getResourceById(borrow.getResourceCopy().getResource().getId());
        try {
            BorrowConcurrencyHelper.lock(resourceById.getId());
            if (borrow.getReturnTime() == null) {
                resourceById.setQuantity(resourceById.getQuantity() - 1);
                resourceById.setRentedQuantity(resourceById.getRentedQuantity() + 1);
            } else {
                resourceById.setQuantity(resourceById.getQuantity() + 1);
                resourceById.setRentedQuantity(resourceById.getRentedQuantity() - 1);
            }
            resourceService.update(resourceById);
            return resourceBorrowDTO;
        } finally {
            BorrowConcurrencyHelper.unlock(resourceById.getId());
        }
    }

    @Override
    public ListResult<ResourceBorrowDTO> getClientResourceBorrow(Long clientId, boolean current, int limit, int offset) {
        ListResult<ResourceBorrow> borrows = storage.getClientResourceBorrow(clientId, current, limit, offset);
        ListResult<ResourceBorrowDTO> result = borrows.copy(ResourceBorrowDTO.class);
        result.setResultList(ResourceBorrowHelper.fromEntities(borrows.getResultList()));
        return result;
    }

    @Override
    public ListResult<ResourceBorrowDTO> getResourceCopyHistory(String identifier, int limit, int offset) {
        ListResult<ResourceBorrow> borrows = storage.getResourceCopyHistory(identifier, limit, offset);
        ListResult<ResourceBorrowDTO> result = borrows.copy(ResourceBorrowDTO.class);
        result.setResultList(ResourceBorrowHelper.fromEntities(borrows.getResultList()));
        return result;
    }

    @Override
    public ResourceBorrowDTO get(String bookId, Long clientId) throws Exception {
        return ResourceBorrowHelper.fromEntity(storage.get(bookId, clientId));
    }
}
