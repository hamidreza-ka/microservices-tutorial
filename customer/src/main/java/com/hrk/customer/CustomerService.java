package com.hrk.customer;

import com.hrk.clients.fraud.FraudCheckResponse;
import com.hrk.clients.fraud.FraudClient;
import com.hrk.clients.notification.NotificationClient;
import com.hrk.clients.notification.NotificationRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public record CustomerService(CustomerRepository repository, FraudClient fraudClient, NotificationClient notificationClient) {
    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();
        // todo: check if email valid
        // todo: check if email not taken
        repository.saveAndFlush(customer);

        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());

        if (fraudCheckResponse.isFraudster())
            throw new IllegalStateException("fraudster");

        notificationClient.sendNotification(
                new NotificationRequest(
                        customer.getId(),
                        customer.getEmail(),
                        String.format("Hi %s welcome to HRK Academy...", customer.getFirstName())
                )
        );
    }
}
