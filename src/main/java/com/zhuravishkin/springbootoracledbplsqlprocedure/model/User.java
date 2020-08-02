package com.zhuravishkin.springbootoracledbplsqlprocedure.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "supernatural")
@Getter
@Setter
public class User {
    @Id
    private Long id;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = "email")
    private String email;
}
