package com.nratnovsky.hqs.models;

import javax.persistence.*;
import java.util.Set;

@Entity
public class SolderHR {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne
    @MapsId
    private Solder solder;

    @ManyToMany
    private Set<DriverLicense> driverLicenses;
}
