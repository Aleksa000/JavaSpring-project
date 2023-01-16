package com.example.demo.models;
import lombok.Data;
import javax.persistence.*;
import java.util.List;
@Entity
@Table(name="users")
@Data
public class UserWithProductsModel {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="password")
    private String password;

    @Column(name="address")
    private String address;

    @Column(name="phone")
    private String phone;

    @Column(name="email")
    private String email;

    @OneToMany(mappedBy = "userId")
    private List<ProductModel> products;
}
