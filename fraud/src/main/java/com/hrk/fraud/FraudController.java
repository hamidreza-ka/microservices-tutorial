package com.hrk.fraud;

import com.hrk.clients.fraud.FraudCheckResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/v1/fraud-check")
public record FraudController(FraudCheckService service) {

    @GetMapping(path = "{customerId}")
    public FraudCheckResponse isFraudster(@PathVariable Integer customerId){
        log.info("fraud check request for customer {}", customerId);
        return new FraudCheckResponse(service.isFraudulentCustomer(customerId));
    }
}
