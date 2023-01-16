package com.example.demo.entity;

import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
@Entity
@Table(name="users")
@Data
public class UserEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(name="first_name")
    @Pattern(regexp = "[A-Z][a-z]*")
    private String firstName;

    @Column(name="last_name")
    @Pattern(regexp = "[A-Z][a-z]*")
    private String lastName;

    @Column(name="password")
    @Size(min = 10)
    private String password;

    @Column(name="address")
    @Pattern(regexp = "[A-Z][a-z]* [A-Z][a-z]* [1-9][0-9]")
    private String address;

    @Column(name="phone")
    @Size(max=10)
    private String phone;

    @Column(name="email")
    @NotBlank
    @Pattern(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,}$")
    private String email;
//cuvaju se kao zapis u bazi podataka
}
