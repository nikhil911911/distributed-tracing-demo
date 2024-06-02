package com.nikhil.grandchild_service.service;

import org.springframework.stereotype.Service;

@Service
public class GcService {
    public String getGrandChild() {
        return "hi from grandchild";
    }
}
