package com.kce.census.Service;

import com.kce.census.Entity.Citizen;
import com.kce.census.Repository.CitizenRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AnalyticsService {

    private final CitizenRepository citizenRepository;

    public AnalyticsService(CitizenRepository citizenRepository) {
        this.citizenRepository = citizenRepository;
    }

    public Map<String, Long> genderStats() {
        List<Citizen> allCitizens = citizenRepository.findAll();
        return allCitizens.stream()
                .collect(Collectors.groupingBy(
                        c -> c.getGender() != null ? c.getGender() : "Unknown",
                        Collectors.counting()));
    }

    public Map<String, Long> stateStats() {
        List<Citizen> allCitizens = citizenRepository.findAll();
        return allCitizens.stream()
                .collect(Collectors.groupingBy(
                        c -> c.getState() != null ? c.getState() : "Unknown",
                        Collectors.counting()));
    }
}
