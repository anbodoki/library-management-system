package com.lms.atom.notofication.job;

import com.lms.atom.borrow.service.ResourceBorrowService;
import com.lms.atom.notofication.mail.EmailService;
import com.lms.atom.notofication.service.NotificationService;
import com.lms.client.client.service.ClientService;
import com.lms.client.exception.ClientException;
import com.lms.common.dto.atom.resource.ResourceBorrowDTO;
import com.lms.common.dto.client.ClientDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
@EnableScheduling
public class LateResourcesTracker {

    private Logger log = LoggerFactory.getLogger(LateResourcesTracker.class);

    private final ResourceBorrowService resourceBorrowService;
    private final NotificationService notificationService;
    private final EmailService emailService;
    private final ClientService clientService;

    @Autowired
    public LateResourcesTracker(ResourceBorrowService resourceBorrowService, NotificationService notificationService, EmailService emailService, ClientService clientService) {
        this.resourceBorrowService = resourceBorrowService;
        this.notificationService = notificationService;
        this.emailService = emailService;
        this.clientService = clientService;
    }

    @PostConstruct
    public void start() {
        log.info("Starting Resource due date penalties job");
        late();
        twoDaysLeft();
    }

    @Scheduled(cron = "0 0 8 * * *")
    public void startTaskDueDatePenalties() throws Exception {
        log.info("Starting Resource due date penalties job");
        late();
        twoDaysLeft();
    }

    @Transactional
    public void late() {
        List<ResourceBorrowDTO> resourceBorrows = resourceBorrowService.getLateResourceBorrows();
        for (ResourceBorrowDTO resourceBorrow : resourceBorrows) {
            notificationService.create("Please return "
                            + resourceBorrow.getResourceCopy().getResource().getName()
                            + " by " + resourceBorrow.getResourceCopy().getResource().getAuthor()
                            + " in library, book identifier is "
                            + resourceBorrow.getResourceCopy().getIdentifier(),
                    resourceBorrow.getClientId(),
                    resourceBorrow.getResourceCopy().getResource().getId());
            ClientDTO client;
            try {
                client = clientService.getById(resourceBorrow.getClientId());
            } catch (ClientException e) {
                continue;
            }
            try {
                emailService.sendMail(client.getEmail(), "Late", "Please return "
                        + resourceBorrow.getResourceCopy().getResource().getName()
                        + " by " + resourceBorrow.getResourceCopy().getResource().getAuthor()
                        + " in library, book identifier is "
                        + resourceBorrow.getResourceCopy().getIdentifier());
            } catch (Exception ignored) {
            }
        }
    }

    @Transactional
    public void twoDaysLeft() {
        List<ResourceBorrowDTO> resourceBorrows = resourceBorrowService.getTwoDayLeftResourceBorrows();
        for (ResourceBorrowDTO resourceBorrow : resourceBorrows) {
            notificationService.create("Two days until returning "
                            + resourceBorrow.getResourceCopy().getResource().getName()
                            + " by " + resourceBorrow.getResourceCopy().getResource().getAuthor()
                            + " in library, book identifier is "
                            + resourceBorrow.getResourceCopy().getIdentifier(),
                    resourceBorrow.getClientId(),
                    resourceBorrow.getResourceCopy().getResource().getId());
            ClientDTO client;
            try {
                client = clientService.getById(resourceBorrow.getClientId());
            } catch (ClientException e) {
                continue;
            }
            try {
                emailService.sendMail(client.getEmail(), "Warning", "Two days until returning "
                        + resourceBorrow.getResourceCopy().getResource().getName()
                        + " by " + resourceBorrow.getResourceCopy().getResource().getAuthor()
                        + " in library, book identifier is "
                        + resourceBorrow.getResourceCopy().getIdentifier());
            } catch (Exception ignored) {
            }
        }
    }
}
