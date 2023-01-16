package com.example.demo.repositories;


import com.example.demo.models.NewsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface IUNewsRepository extends JpaRepository<NewsModel, Integer> {
    @Query(nativeQuery = true,value = "SELECT * FROM news WHERE pet LIKE (%:pet%)")
    NewsModel findByPet(@Param("pet")String pet);
    NewsModel findByMail(String mail);
}
