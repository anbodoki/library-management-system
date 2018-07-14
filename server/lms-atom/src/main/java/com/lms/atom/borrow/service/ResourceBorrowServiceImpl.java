package com.lms.atom.borrow.service;

import com.lms.atom.borrow.storage.ResourceBorrowHelper;
import com.lms.atom.borrow.storage.ResourceBorrowRepository;
import com.lms.atom.borrow.storage.ResourceBorrowStorage;
import com.lms.atom.borrow.storage.model.ResourceBorrow;
import com.lms.common.dto.atom.resource.ResourceBorrowDTO;
import com.lms.common.dto.response.ListResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ResourceBorrowServiceImpl implements ResourceBorrowService {

    private final ResourceBorrowRepository repository;
    private final ResourceBorrowStorage storage;

    @Autowired
    public ResourceBorrowServiceImpl(ResourceBorrowRepository repository, ResourceBorrowStorage storage) {
        this.repository = repository;
        this.storage = storage;
    }

    @Override
    public ResourceBorrowDTO updateResourceBorrow(ResourceBorrowDTO borrow) {
        return ResourceBorrowHelper.fromEntity(repository.save(ResourceBorrowHelper.toEntity(borrow)));
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
    public ResourceBorrowDTO get(String bookId, String clientId) {
        return ResourceBorrowHelper.fromEntity(storage.get(bookId, clientId));
    }
}
