package com.example.demo.models;

import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.validation.constraints.*;

@Data
public class UserModel {

    private Integer id;
    private String firstName;
    private String lastName;
    private String password;
    private String address;
    private String phone;
    @Email
    @NotBlank
    private String email;

}
