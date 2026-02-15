package com.kce.census.Service;

import com.kce.census.Entity.Citizen;
import com.kce.census.Repository.CitizenRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CitizenService {
    private final CitizenRepository repo;
    public CitizenService(CitizenRepository repo) {
        this.repo = repo;
    }
    public Citizen register(Citizen citizen){
        return repo.save(citizen);
    }
    public List<Citizen> search(String keyword){
        return repo.findByFirstNameContainingIgnoreCase(keyword);
    }
    public List<Citizen> getAll(){
        return repo.findAll();
    }
}
