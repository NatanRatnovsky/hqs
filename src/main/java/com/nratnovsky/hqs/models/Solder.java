package com.nratnovsky.hqs.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Solder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique = true, length = 12)
    private String solderId;

    @Column(nullable = false, length = 30)
    private String firstName;

    @Column(nullable = false, length = 30)
    private String lastName;

    @Column
    private String phone;


    @OneToOne(mappedBy = "solder", cascade = CascadeType.ALL)
    private SolderArmory solderArmory;

    @OneToOne(mappedBy = "solder", cascade = CascadeType.ALL)
    private SolderHR solderHR;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id")
    private Company company;

    public Solder(String solderId, String firstName, String lastName, String phone, Company company) {
        this.solderId = solderId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.company = company;
    }
}
