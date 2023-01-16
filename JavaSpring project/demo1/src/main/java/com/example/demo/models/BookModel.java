package com.example.demo.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="books")
@Data
public class BookModel {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(name="date")
    @NotBlank
    private String date;

    @Column(name="time")
    @NotBlank
    private String time;

    @Column(name="service")
    @NotBlank
    private String service;
}
