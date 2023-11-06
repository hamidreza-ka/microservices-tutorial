package com.hrk.notification;

import com.hrk.clients.notification.NotificationRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/v1/notification")
public record NotificationController(NotificationService service) {

    @PostMapping
    public void sendNotification(@RequestBody NotificationRequest notificationRequest){
        log.info("new notification sent {}", notificationRequest);
        service.send(notificationRequest);
    }
}
