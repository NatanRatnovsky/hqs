package com.nratnovsky.hqs.models;

import javax.persistence.*;
import java.util.Set;

@Entity
public class SolderArmory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne
    @MapsId
    private Solder solder;

    @OneToMany(mappedBy = "solderArmory")
    private Set<IssueOfWeapon> issueOfWeapons;

    @OneToMany(mappedBy = "solderArmory")
    private Set<IssueOfOptic> issueOfOptics;

    @OneToMany(mappedBy = "solderArmory")
    private Set<IssueOfAccessories> issueOfAccessories;

    @OneToMany(mappedBy = "solderArmory")
    private Set<IssueOfGear> issueOfGears;
}
