package com.cris.citizen.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "households")
public class Household {

    @Id
    private String id;

    private String headCitizenId;
    private String address;

    private Map<String,String> relations;

}
