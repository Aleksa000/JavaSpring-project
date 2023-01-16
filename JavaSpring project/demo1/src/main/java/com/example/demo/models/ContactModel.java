package com.example.demo.models;
import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="contacts")
@Data
public class ContactModel {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(name="subject")
    @NotBlank
    private String subject;

    @Column(name="message")
    @NotBlank
    private String message;
}
