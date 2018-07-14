package com.lms.atom.gateway;

import com.lms.atom.book.service.ResourceService;
import com.lms.atom.borrow.service.ResourceBorrowService;
import com.lms.atom.messages.Messages;
import com.lms.client.client.service.ClientService;
import com.lms.common.dto.atom.resource.ResourceBorrowDTO;
import com.lms.common.dto.atom.resource.ResourceCopyDTO;
import com.lms.common.dto.client.ClientDTO;
import com.lms.gateway.DeviceMessageHandlerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class DeviceMessageHandlerFactoryImpl extends DeviceMessageHandlerFactory {

    private final ResourceBorrowService resourceBorrowService;
    private final ResourceService resourceService;
    private final ClientService clientService;

    @Autowired
    public DeviceMessageHandlerFactoryImpl(ResourceBorrowService resourceBorrowService, ResourceService resourceService, ClientService clientService) {
        this.resourceBorrowService = resourceBorrowService;
        this.resourceService = resourceService;
        this.clientService = clientService;
    }

    @Override
    public String processSubmit(String bookId, String clientId, Date date) {
        ResourceBorrowDTO resourceBorrow = resourceBorrowService.get(bookId, clientId);
        if (resourceBorrow == null) {
            ResourceBorrowDTO result = new ResourceBorrowDTO();
            ClientDTO clientForCard = clientService.getClientForCard(clientId);
            result.setClientId(clientForCard.getId());
            result.setResourceCopy(resourceService.getResourceCopyByIdentifier(bookId));
            result.setBorrowTime(new Date());
            result.setScheduledReturnTime(date);
            resourceBorrowService.updateResourceBorrow(result);
            return Messages.get("successfullyBorrowed");
        } else {
            resourceBorrow.setReturnTime(new Date());
            resourceBorrowService.updateResourceBorrow(resourceBorrow);
            return Messages.get("successfullyReturned");
        }
    }

    @Override
    public String processCheckBook(String bookIdentifier) {
        ResourceCopyDTO resourceCopyByIdentifier = resourceService.getResourceCopyByIdentifier(bookIdentifier);
        if (resourceCopyByIdentifier == null) {
            return Messages.get("bookNotExists");
        }
        return resourceCopyByIdentifier.getResource().getName() + " by " + resourceCopyByIdentifier.getResource().getAuthor();
    }

    @Override
    public String processCheckClient(String clientCardId) {
        ClientDTO clientForCard = clientService.getClientForCard(clientCardId);
        if (clientForCard == null) {
            return Messages.get("clientNotExists");
        }
        return clientForCard.getFirstName() + " " + clientForCard.getLastName();
    }
}