package com.kce.census.Service;

import com.kce.census.Entity.Officer;
import com.kce.census.Repository.OfficerRepository;
import com.kce.census.Util.PasswordUtil;
import org.springframework.stereotype.Service;

@Service
public class OfficerService {
    private final OfficerRepository repo;
    public OfficerService(OfficerRepository repo) {
        this.repo = repo;
    }
    public Officer getProfile(String id){
        return repo.findById(id).orElseThrow();
    }
    public  Officer updateProfile(String email,Officer date){
        Officer officer = getProfile(email);
        officer.setName(date.getName());
        officer.setEmail(date.getEmail());
        return repo.save(officer);
    }
    public String changePassword(String email,String oldPassword,String newPassword){
        Officer officer = getProfile(email);
        if(!PasswordUtil.matches(oldPassword,officer.getPassword()))
            return "Passwords don't match";
        officer.setPassword(PasswordUtil.encode(newPassword));
        repo.save(officer);
        return "password changed";
    }

}

