package com.nikhil.parent_service.controller;

import io.micrometer.observation.annotation.Observed;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
public class ParentController {

    final RestTemplate restTemplate;
    public ParentController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("parent")
    @Observed(name = "user.name",
            contextualName = "Parent-->Child",
            lowCardinalityKeyValues = {"userType", "userType2"})
    public String sayHi(){
        log.info("parent was called");
        log.info("say hi to grandchild");
        ResponseEntity<String> response=restTemplate.exchange("http://localhost:6060/child-service/child", HttpMethod.GET, null, String.class);
        String responseString=response.getBody();
        return "grandChildSaid "+responseString;
    }
}
