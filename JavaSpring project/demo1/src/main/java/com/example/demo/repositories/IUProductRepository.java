package com.example.demo.repositories;
import com.example.demo.models.UserWithProductsModel;
import org.springframework.data.jpa.repository.JpaRepository;
public interface IUProductRepository extends JpaRepository<UserWithProductsModel, Integer> {
    UserWithProductsModel findByEmail(String email);
}
