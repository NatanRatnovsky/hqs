package com.nratnovsky.hqs.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    @OneToMany(mappedBy = "company")
    private Set<Job> jobNames;

    @OneToMany(mappedBy = "company")
    private Set<Solder> solders;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "unit_id")
    private Unit unit;

    public Company(String name) {
        this.name = name;
    }

    public Company(String name, Unit unit) {
        this.name = name;
        this.unit = unit;
    }
}
