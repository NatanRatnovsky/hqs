package com.nratnovsky.hqs.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String jobName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id")
    private Company company;

    public Job(String job, Company company) {
        this.jobName = job;
        this.company = company;
    }
}
