package com.kce.census.Service;

import com.kce.census.Entity.Application;
import com.kce.census.Repository.ApplicationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationService {
    private final ApplicationRepository repo;

    public ApplicationService(ApplicationRepository repo) {
        this.repo = repo;
    }

    public List<Application> getAll() {
        return repo.findAll();
    }

    public Application updateStatus(String id, String status) {
        Application app = repo.findById(id).orElseThrow();
        app.setStatus(status);
        return repo.save(app);
    }
}