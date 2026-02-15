package com.kce.census.Controller;

import com.kce.census.Entity.Officer;
import com.kce.census.Service.OfficerService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/officer")
@CrossOrigin("*")
public class OfficerController {

    private final OfficerService service;

    public OfficerController(OfficerService service) {
        this.service = service;
    }

    @GetMapping("/{email}")
    public Officer profile(@PathVariable String email) {
        return service.getProfile(email);
    }

    @PutMapping("/{email}")
    public Officer update(@PathVariable String email,
                          @RequestBody Officer officer) {
        return service.updateProfile(email, officer);
    }

    @PutMapping("/{email}/password")
    public String changePassword(
            @PathVariable String email,
            @RequestBody Map<String, String> req) {

        return service.changePassword(
                email,
                req.get("currentPassword"),
                req.get("newPassword"));
    }
}
