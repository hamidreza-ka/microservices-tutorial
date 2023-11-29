package com.hrk.apigw.security;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("fake")
public class ApiKeyAuthorizationCheckerFakeImpl implements ApiKeyAuthorizationChecker {

    private final static Map<String, List<String>> apiKeys = Map.of("secretKey", List.of("customer"));

    @Override
    public boolean isAuthorized(
            String apiKey,
            String applicationName
    ) {
        return apiKeys.getOrDefault(apiKey, List.of())
                .stream()
                .anyMatch(applications -> applications.contains(applicationName));
    }
}
