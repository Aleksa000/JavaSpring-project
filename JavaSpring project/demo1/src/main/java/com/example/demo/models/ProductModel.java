package com.example.demo.models;
import lombok.Data;
import javax.persistence.*;
@Entity
@Table(name="products")
@Data
public class ProductModel {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(name="name")
    private String name;

    @Column(name="user_id")
    private String userId;

    public void setAuthorId(Integer id) {
    }
}
