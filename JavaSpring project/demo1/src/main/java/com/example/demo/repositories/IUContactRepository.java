package com.example.demo.repositories;



import com.example.demo.models.ContactModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IUContactRepository extends JpaRepository<ContactModel, Integer> {

    @Query(nativeQuery = true,value = "SELECT * FROM contacts WHERE subject LIKE (%:subject%)")
    ContactModel findBySubject(@Param("subject")String subject);
    ContactModel findByMessage(String message);
}
