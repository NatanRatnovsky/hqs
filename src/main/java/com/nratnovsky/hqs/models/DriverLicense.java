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
public class DriverLicense {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @Column(insertable = true)
    private String name;

    @ManyToMany
    private Set<SolderHR> solderSet;
}
