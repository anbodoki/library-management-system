package com.lms.atom.borrow.service;

import com.lms.atom.book.service.ResourceService;
import com.lms.atom.borrow.BorrowConcurrencyHelper;
import com.lms.atom.borrow.storage.ResourceBorrowHelper;
import com.lms.atom.borrow.storage.ResourceBorrowRepository;
import com.lms.atom.borrow.storage.ResourceBorrowStorage;
import com.lms.atom.borrow.storage.model.ResourceBorrow;
import com.lms.atom.notofication.mail.EmailService;
import com.lms.client.client.service.ClientService;
import com.lms.common.dto.atom.SendMailRequest;
import com.lms.common.dto.atom.resource.ResourceBorrowDTO;
import com.lms.common.dto.atom.resource.ResourceDTO;
import com.lms.common.dto.client.ClientDTO;
import com.lms.common.dto.response.ListResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ResourceBorrowServiceImpl implements ResourceBorrowService {

    private final ResourceBorrowRepository repository;
    private final ResourceBorrowStorage storage;
    private final ResourceService resourceService;
    private final EmailService emailService;
    private final ClientService clientService;

    @Autowired
    public ResourceBorrowServiceImpl(ResourceBorrowRepository repository, ResourceBorrowStorage storage, ResourceService resourceService, EmailService emailService, ClientService clientService) {
        this.repository = repository;
        this.storage = storage;
        this.resourceService = resourceService;
        this.emailService = emailService;
        this.clientService = clientService;
    }

    @Override
    public ResourceBorrowDTO updateResourceBorrow(ResourceBorrowDTO borrow) throws Exception {
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
            resourceService.justSave(resourceById);
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

    @Override
    public List<ResourceBorrowDTO> getLateResourceBorrows() {
        List<ResourceBorrowDTO> resourceBorrows = ResourceBorrowHelper.fromEntities(storage.getLateResourceBorrows());
        for (ResourceBorrowDTO resourceBorrow : resourceBorrows) {
            resourceBorrow.setCritical(true);
            repository.save(ResourceBorrowHelper.toEntity(resourceBorrow));
        }
        return resourceBorrows;
    }

    @Override
    public List<ResourceBorrowDTO> getTwoDayLeftResourceBorrows() {
        return ResourceBorrowHelper.fromEntities(storage.getTwoDayLeftResourceBorrows());
    }

    @Override
    public ListResult<ResourceBorrowDTO> find(String query, int limit, int offset) {
        ListResult<ResourceBorrow> borrows = storage.find(query, limit, offset);
        ListResult<ResourceBorrowDTO> result = borrows.copy(ResourceBorrowDTO.class);
        result.setResultList(ResourceBorrowHelper.fromEntities(borrows.getResultList()));
        return result;
    }

    @Override
    public ListResult<ResourceBorrowDTO> find(String identifier, String isbn, Long clientId,
                                              Date fromBorrowTime, Date toBorrowTime,
                                              Date fromReturnTime, Date toReturnTime,
                                              Date fromScheduledTime, Date toScheduledTime,
                                              Boolean critical, Boolean notReturned, int limit, int offset) {
        ListResult<ResourceBorrow> borrows = storage.find(identifier, isbn, clientId,
                fromBorrowTime, toBorrowTime,
                fromReturnTime, toReturnTime,
                fromScheduledTime, toScheduledTime,
                critical, notReturned, limit, offset);
        ListResult<ResourceBorrowDTO> result = borrows.copy(ResourceBorrowDTO.class);
        result.setResultList(ResourceBorrowHelper.fromEntities(borrows.getResultList()));
        return result;
    }

    @Override
    public void sendMail(SendMailRequest request) throws Exception {
        ClientDTO byId = clientService.getById(request.getClientId());
        if (byId != null) {
            emailService.sendMail(byId.getEmail(), request.getSubject(), request.getMessage());
        }
    }

    @Override
    public long getBorrowedResourcesCount() {
        return repository.countByReturnTimeIsNull();
    }

    @Override
    public long getCriticalCount() {
        return repository.countByCriticalIsTrue();
    }
}
