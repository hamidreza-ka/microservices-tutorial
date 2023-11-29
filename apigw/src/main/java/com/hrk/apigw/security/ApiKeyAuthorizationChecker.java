package com.hrk.apigw.security;

public interface ApiKeyAuthorizationChecker {
    boolean isAuthorized(String apiKey, String applicationName);
}
