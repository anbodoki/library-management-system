package com.lms.atom.notofication.service;

import com.lms.atom.notofication.storage.NotificationHelper;
import com.lms.atom.notofication.storage.NotificationRepository;
import com.lms.atom.notofication.storage.NotificationStorage;
import com.lms.atom.notofication.storage.model.Notification;
import com.lms.client.client.service.ClientService;
import com.lms.common.dto.atom.notification.NotificationDTO;
import com.lms.common.dto.client.ClientDTO;
import com.lms.common.dto.response.ListResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class NotificationServiceImpl implements NotificationService {

    private final NotificationStorage storage;
    private final NotificationRepository repository;
    private final ClientService clientService;

    @Autowired
    public NotificationServiceImpl(NotificationStorage storage, NotificationRepository repository, ClientService clientService) {
        this.storage = storage;
        this.repository = repository;
        this.clientService = clientService;
    }

    @Override
    public void create(String message, Long clientId, Long resourceId) {
        NotificationDTO result = new NotificationDTO();
        result.setCreationDate(new Date());
        result.setMessage(message);
        result.setClientId(clientId);
        result.setResourceId(resourceId);
        result.setSeen(false);
        result.setRead(false);
        repository.save(NotificationHelper.toEntity(result));
    }

    @Override
    public ListResult<NotificationDTO> getNotificationsForClient(int limit, int offset) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ClientDTO client = clientService.getByEmail(authentication.getName());
        ListResult<Notification> materialTypes = storage.getNotificationsForClient(client.getId(), limit, offset);
        ListResult<NotificationDTO> result = materialTypes.copy(NotificationDTO.class);
        result.setResultList(NotificationHelper.fromEntities(materialTypes.getResultList()));
        return result;
    }

    @Override
    public void markAsSeen() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ClientDTO client = clientService.getByEmail(authentication.getName());
        storage.markAsSeen(client.getId());
    }

    @Override
    public void markAsRead(Long notificationId) {
        Notification notification = repository.getOne(notificationId);
        notification.setRead(true);
        repository.save(notification);
    }

    @Override
    public NotificationDTO getNotificationForResource(Long resourceId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ClientDTO client = clientService.getByEmail(authentication.getName());
        return NotificationHelper.fromEntity(storage.getNotificationForResource(client.getId(), resourceId));
    }
}
