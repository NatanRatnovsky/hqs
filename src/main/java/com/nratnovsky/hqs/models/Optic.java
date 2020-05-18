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
@Table(name = "optic")
public class Optic {
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

    @Column(length = 10)
    private String storingId;

    @OneToMany(mappedBy = "optic")
    private Set<IssueOfOptic> issueOfOptics;
}
