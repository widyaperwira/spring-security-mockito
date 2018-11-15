package com.paymo.login.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

/**
 * Created by Asus on 11/15/2018.
 */
@Data
@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    private int id;

    @Column(name = "role")
    @NotEmpty(message = "*role name cannot empty")
    private String role;

}
