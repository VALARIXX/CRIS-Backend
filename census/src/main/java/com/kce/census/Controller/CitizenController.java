package com.kce.census.Controller;

import com.kce.census.Entity.Citizen;
import com.kce.census.Service.CitizenService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/citizens")
@CrossOrigin
public class CitizenController {
    private final CitizenService citizenService;
    public CitizenController(CitizenService citizenService) {
        this.citizenService = citizenService;
    }
    @PostMapping("/register")
    public Citizen create(@RequestBody Citizen citizen) {
        return citizenService.register( citizen );
    }
    @GetMapping("/search")
    public List<Citizen> search(@RequestParam String q) {
        return citizenService.search(q);
    }
    @GetMapping
    public List<Citizen> getAll() {
        return citizenService.getAll();
    }

}
