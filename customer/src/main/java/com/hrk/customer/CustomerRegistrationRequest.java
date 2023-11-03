package com.hrk.customer;

public record CustomerRegistrationRequest(
        String firstName,
        String lastName,
        String email
) {
}
