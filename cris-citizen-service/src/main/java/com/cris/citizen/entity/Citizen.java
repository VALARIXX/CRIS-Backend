package com.cris.citizen.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "citizens")
public class Citizen {

    @Id
    private String id;

    @Indexed(unique = true)
    private String citizenId;

    @Indexed
    private String fullName;

    private LocalDate dateOfBirth;
    private String gender;

    private String state;
    private String district;
    private String address;

    private String education;
    private String occupation;
    private String mobile;

    private String householdId;
    private String relationshipToHead;

    private String residencyStatus;

    private String fatherName;
    private String motherName;
    private String maritalStatus;
    private String bloodGroup;
    private String nationality;

    @Indexed(unique = true)
    private String aadharNumber;

    private String photoUrl;

    private Instant createdAt;
}
