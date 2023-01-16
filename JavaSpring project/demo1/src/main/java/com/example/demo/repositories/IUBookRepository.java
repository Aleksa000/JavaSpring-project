package com.example.demo.repositories;


import com.example.demo.entity.UserEntity;
import com.example.demo.models.BookModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IUBookRepository extends JpaRepository<BookModel, Integer> {
    BookModel findByDate(String date);
    BookModel findByTime(String time);

    @Query(nativeQuery = true,value = "SELECT * FROM books WHERE service LIKE (%:service%)")
    List<BookModel> findAllByService(@Param("service")String service);
}
