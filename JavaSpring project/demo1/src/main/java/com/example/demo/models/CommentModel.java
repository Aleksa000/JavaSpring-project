package com.example.demo.models;
import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;


@Data
public class CommentModel {

    private Integer id;
    private String name;
}
