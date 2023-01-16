package com.example.demo.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="news")
@Data
public class NewsModel {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(name="pet")
    @NotBlank
    private String pet;

    @Column(name="mail")
    @NotBlank
    @Email
    private String mail;

}
