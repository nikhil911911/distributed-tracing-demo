package com.nikhil.child_service.controller;

import io.micrometer.observation.annotation.Observed;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
public class ChildController {
    final RestTemplate restTemplate;
    public ChildController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/child")
    @Observed(name = "user.name",
            contextualName = "Child-->Grandchild",
            lowCardinalityKeyValues = {"userType", "userType2"})
    public String child(){
        log.info("child was called");
        log.info("calling grandchild now");
        ResponseEntity<String> response=restTemplate.exchange("http://localhost:5050/grandchild-service/grandchild", HttpMethod.GET, null, String.class);
        return response.getBody();
    }

}
