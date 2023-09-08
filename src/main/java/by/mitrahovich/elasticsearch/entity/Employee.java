package by.mitrahovich.elasticsearch.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@JsonInclude(JsonInclude.Include.ALWAYS)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Employee {
    private String id;
    private String name;
    private String dob;
    private Address address;
    private String email;
    private List<String> skills;
    private int experience;
    private double rating;
    private String description;
    private boolean verified;
    private int salary;
}


