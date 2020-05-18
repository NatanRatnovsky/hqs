package com.nratnovsky.hqs.models;

import com.nratnovsky.hqs.models.enums.LogType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class SystemLogger {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private Date date;

    private String userName;

    @Enumerated
    private LogType logType;

    @Column(length = 510)
    private String message;

    public SystemLogger(Date date, String userName,  LogType logType,String message) {
        this.date = date;
        this.userName = userName;
        this.logType = logType;
        this.message = message;
    }
}
