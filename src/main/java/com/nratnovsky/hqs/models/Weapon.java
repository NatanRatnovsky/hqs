package com.nratnovsky.hqs.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "weapon")
public class Weapon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, unique = true)
    private long serial;

    @Column
    private boolean personal;

    @Column
    private boolean condition;

    @Column
    private boolean stored;

    @Column
    private String storingId;

    @Column
    private Date storedDate;

    @Column
    private boolean checkSerial;

    @OneToMany(mappedBy = "weapon")
    private Set<IssueOfWeapon> issueOfWeapons;

}