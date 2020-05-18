package com.nratnovsky.hqs.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class IssueOfGear {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "solder_armoory_id")
    private SolderArmory solderArmory;

    @ManyToOne
    @JoinColumn(name = "gear_id")
    private Gear gear;

    private Date registeredAt;

    private Date returnedAt;

    }
