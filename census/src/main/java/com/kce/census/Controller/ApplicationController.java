package com.kce.census.Controller;

import com.kce.census.Entity.Application;
import com.kce.census.Service.ApplicationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
@CrossOrigin("*")
public class ApplicationController {

    private final ApplicationService service;

    public ApplicationController(ApplicationService service) {
        this.service = service;
    }

    @GetMapping
    public List<Application> getAll() {
        return service.getAll();
    }

    @PutMapping("/{id}/approve")
    public Application approve(@PathVariable String id) {
        return service.updateStatus(id, "Verified");
    }

    @PutMapping("/{id}/reject")
    public Application reject(@PathVariable String id) {
        return service.updateStatus(id, "Rejected");
    }
}
