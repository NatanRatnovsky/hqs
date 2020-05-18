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
@Table(name = "company_gear")
public class Gear {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false)
    private int quantity;

    @Column
    private String description;

    @Column(unique = true)
    private long serial;

    @OneToMany(mappedBy = "gear")
    private Set<IssueOfGear> issueOfGears;
}
