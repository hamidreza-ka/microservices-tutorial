package com.hrk.notification;

import com.hrk.clients.notification.NotificationRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public record NotificationService(NotificationRepository repository) {

    public void send(NotificationRequest notificationRequest) {
        repository.save(
                Notification.builder()
                        .toCustomerId(notificationRequest.toCustomerId())
                        .toCustomerEmail(notificationRequest.toCustomerEmail())
                        .message(notificationRequest.message())
                        .sender("HRK")
                        .sentAt(LocalDateTime.now())
                        .build()
        );
    }
}
