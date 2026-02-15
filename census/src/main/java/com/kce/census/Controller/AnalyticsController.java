package com.kce.census.Controller;

import com.kce.census.Service.AnalyticsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/analytics")
@CrossOrigin("*")
public class AnalyticsController {

    private final AnalyticsService service;

    public AnalyticsController(AnalyticsService service) {
        this.service = service;
    }

    @GetMapping("/gender")
    public Map<String, Long> genderStats() {
        return service.genderStats();
    }

    @GetMapping("/state")
    public Map<String, Long> stateStats() {
        return service.stateStats();
    }
}
