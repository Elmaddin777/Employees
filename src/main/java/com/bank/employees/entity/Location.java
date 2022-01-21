package com.bank.employees.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "Location")
@Table(name = "location")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Location {
    @JsonIgnore
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long Id;

    @Column(name = "branch", nullable = false, unique = true, columnDefinition = "TEXT")
    private String branch;

    @Column(name = "city", nullable = false, columnDefinition = "TEXT")
    private String city;

    @JsonIgnore
    @OneToMany(targetEntity = Department.class, mappedBy = "location", cascade=CascadeType.ALL)
    private Set<Department> departments = new HashSet<Department>();
}
