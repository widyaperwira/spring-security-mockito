package com.paymo.login.model;

import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

/**
 * Created by Asus on 11/15/2018.
 */
@Data
@Builder
@Entity
@Table(name="user_profile")
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_profile_id")
    private int id;

    @Column(name = "email")
    @Email(message = "*email not valid")
    @NotEmpty(message = "*email cannot empty")
    private String email;

    @Column(name = "password")
    @Length(min = 6, message = "*password minimal 6 characters")
    @NotEmpty(message = "*password cannot empty")
    private String password;

    @Column(name = "name")
    @NotEmpty(message = "*name cannot empty")
    private String name;

    @Column(name = "lastName")
    @NotEmpty(message = "*last name cannot empty")
    private String lastName;

    @Column(name = "active")
    private int active;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_profile_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

}
