package com.lms.atom.gateway;

import com.lms.atom.book.service.ResourceService;
import com.lms.atom.borrow.service.ResourceBorrowService;
import com.lms.atom.exception.AtomException;
import com.lms.atom.messages.Messages;
import com.lms.client.client.service.ClientService;
import com.lms.common.dto.atom.resource.ResourceBorrowDTO;
import com.lms.common.dto.atom.resource.ResourceCopyDTO;
import com.lms.common.dto.client.ClientDTO;
import com.lms.gateway.DeviceMessageHandlerFactory;
import com.lms.gateway.ProtocolConfig;
import com.lms.gateway.exception.GatewayException;
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
    public String processSubmit(String bookId, String clientId, Date date) throws Exception {
        ClientDTO clientForCard = clientService.getClientForCard(clientId);
        ResourceBorrowDTO resourceBorrow = resourceBorrowService.get(bookId, null);
        if (resourceBorrow == null) {
            return createBorrow(clientForCard, bookId, date);
        } else {
            if (resourceBorrow.getReturnTime() == null) {
                if (!resourceBorrow.getClientId().equals(clientForCard.getId())) {
                    throw new GatewayException(Messages.get("cannotReturnBook"));
                }
                resourceBorrow.setReturnTime(new Date());
                resourceBorrowService.updateResourceBorrow(resourceBorrow);
                return Messages.get("successfullyReturned");
            } else {
                return createBorrow(clientForCard, bookId, date);
            }
        }
    }

    private String createBorrow(ClientDTO clientForCard, String bookId, Date date) throws Exception {
        ResourceBorrowDTO result = new ResourceBorrowDTO();
        result.setClientId(clientForCard.getId());
        result.setResourceCopy(resourceService.getResourceCopyByIdentifier(bookId));
        result.setBorrowTime(new Date());
        result.setScheduledReturnTime(date);
        resourceBorrowService.updateResourceBorrow(result);
        return Messages.get("successfullyBorrowed");
    }

    @Override
    public String processCheckBook(String bookIdentifier) throws Exception {
        ResourceCopyDTO resourceCopyByIdentifier = resourceService.getResourceCopyByIdentifier(bookIdentifier);
        if (resourceCopyByIdentifier == null) {
            throw new GatewayException(Messages.get("bookNotExists"));
        }
        ResourceBorrowDTO resourceBorrow = resourceBorrowService.get(bookIdentifier, null);
        if (resourceBorrow == null) {
            return resourceCopyByIdentifier.getResource().getName()
                    + " by "
                    + resourceCopyByIdentifier.getResource().getAuthor() + ProtocolConfig.MSG_DATA_DELIMITER + "b";
        } else {
            return resourceCopyByIdentifier.getResource().getName()
                    + " by "
                    + resourceCopyByIdentifier.getResource().getAuthor() + ProtocolConfig.MSG_DATA_DELIMITER + "r";
        }
    }

    @Override
    public String processCheckClient(String clientCardId) throws Exception {
        ClientDTO clientForCard = clientService.getClientForCard(clientCardId);
        if (clientForCard == null) {
            throw new GatewayException(Messages.get("clientNotExists"));
        }
        return clientForCard.getFirstName() + " " + clientForCard.getLastName();
    }
}