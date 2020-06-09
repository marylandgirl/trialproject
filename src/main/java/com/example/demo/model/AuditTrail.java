package com.example.demo.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class AuditTrail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @DateTimeFormat(iso=DateTimeFormat.ISO.TIME, pattern = "yyyy-MM-dd" )
    private LocalDate dateAccessed;

    private int stage;                   //created=1,edited=2,approved=3,rejected=4,archived=5

}
