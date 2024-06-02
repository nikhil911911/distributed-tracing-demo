package com.nikhil.grandchild_service.controller;

import com.nikhil.grandchild_service.service.GcService;
import io.micrometer.observation.annotation.Observed;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class GrandChildController {
    @Autowired
    private GcService gcService;

    @GetMapping("/grandchild")
    @Observed(name = "user.name",
            contextualName = "Grandchild-->service",
            lowCardinalityKeyValues = {"userType", "userType2"})
    public String getGrandChild() {
        log.info("grandChild was called");
        return gcService.getGrandChild();
    }
}
