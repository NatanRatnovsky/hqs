package com.nratnovsky.hqs.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class IssueOfOptic {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "solder_armory_id")
    private SolderArmory solderArmory;

    @ManyToOne
    @JoinColumn(name = "optic_id")
    private Optic optic;

    private LocalDateTime registeredAt;

    private LocalDateTime returnedAt;
}
