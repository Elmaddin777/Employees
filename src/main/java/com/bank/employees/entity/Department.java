package com.bank.employees.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Department")
@Table(name = "Department")
@Builder
public class Department {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long Id;

    @JsonIgnore
    @Column(unique = true)
    private Long location_id;

    @Column(name = "dept_name", nullable = false, columnDefinition = "TEXT")
    private String deptName;

    @JsonIgnore
    @OneToMany(mappedBy = "department")
    private Set<Employee> employees = new HashSet<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Location location;
}
