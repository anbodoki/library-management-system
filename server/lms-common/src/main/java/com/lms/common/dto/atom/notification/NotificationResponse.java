package com.lms.common.dto.atom.notification;

import com.lms.common.dto.response.ListResult;

import java.io.Serializable;

public class NotificationResponse implements Serializable {

    private ListResult<NotificationDTO> notifications;
    private Long count;

    public ListResult<NotificationDTO> getNotifications() {
        return notifications;
    }

    public void setNotifications(ListResult<NotificationDTO> notifications) {
        this.notifications = notifications;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
