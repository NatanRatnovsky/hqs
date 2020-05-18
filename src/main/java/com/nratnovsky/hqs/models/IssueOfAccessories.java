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
public class IssueOfAccessories {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "solder_armory_id")
    private SolderArmory solderArmory;

    @ManyToOne
    @JoinColumn(name = "accessories_id")
    private Accessories accessories;

    private Date registeredAt;

    private Date returnedAt;

}
