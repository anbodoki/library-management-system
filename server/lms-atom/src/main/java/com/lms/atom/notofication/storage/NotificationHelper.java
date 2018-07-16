package com.lms.atom.notofication.storage;

import com.lms.atom.notofication.storage.model.Notification;
import com.lms.common.dto.atom.notification.NotificationDTO;

import java.util.ArrayList;
import java.util.List;

public class NotificationHelper {

    public static Notification toEntity(NotificationDTO notification) {
        if (notification == null) {
            return null;
        }
        Notification result = new Notification();
        result.setId(notification.getId());
        result.setMessage(notification.getMessage());
        result.setClientId(notification.getClientId());
        result.setResourceId(notification.getResourceId());
        result.setCreationDate(notification.getCreationDate());
        result.setSeen(notification.getSeen());
        result.setRead(notification.getRead());
        return result;
    }

    public static NotificationDTO fromEntity(Notification notification) {
        if (notification == null) {
            return null;
        }
        NotificationDTO result = new NotificationDTO();
        result.setId(notification.getId());
        result.setMessage(notification.getMessage());
        result.setClientId(notification.getClientId());
        result.setResourceId(notification.getResourceId());
        result.setCreationDate(notification.getCreationDate());
        result.setSeen(notification.getSeen());
        result.setRead(notification.getRead());
        return result;
    }

    public static List<Notification> toEntities(List<NotificationDTO> notifications) {
        if (notifications == null) {
            return null;
        }
        List<Notification> result = new ArrayList<>();
        for (NotificationDTO notification : notifications) {
            result.add(toEntity(notification));
        }
        return result;
    }

    public static List<NotificationDTO> fromEntities(List<Notification> notifications) {
        if (notifications == null) {
            return null;
        }
        List<NotificationDTO> result = new ArrayList<>();
        for (Notification notification : notifications) {
            result.add(fromEntity(notification));
        }
        return result;
    }
}
