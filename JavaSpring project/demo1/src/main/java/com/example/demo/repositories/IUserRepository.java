package com.example.demo.repositories;
import com.example.demo.entity.UserEntity;
import com.example.demo.models.CommentModel;
import com.example.demo.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface IUserRepository extends JpaRepository<UserEntity, Integer> {
    UserEntity findByEmail(String email);
    UserEntity findByPassword(String password);
    UserEntity findByAddress(String address);
    @Query("SELECT users FROM UserEntity users WHERE users.phone =:phone")
    UserEntity findByPhoneHQL (@Param("phone") String phone);
    @Query(nativeQuery = true,value = "SELECT * FROM users WHERE first_name LIKE (%:firstName%)")
    List<UserEntity> findAllByFirstName(@Param("firstName")String firstName);

    @Query(nativeQuery = true,value = "SELECT * FROM users WHERE last_name LIKE (%:lastName%)")
    List<UserEntity> findAllByLastName(@Param("lastName")String lastName);

}
