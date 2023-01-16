package com.example.demo.services;

import com.example.demo.models.*;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface IUserService  extends UserDetailsService {
    List<UserModel> Index();
    List<UserModel> GetAllByFirstName(String firstName);
    List<UserModel> GetAllByLastName (String lastName);
    UserModel GetByEmail(String email);
    UserModel GetByAddress(String address);
    UserModel GetByPassword(String password);
    UserModel GetByPhoneHQL(String phone);
    UserWithProductsModel GetUserAndProducts(String email);
    UserModel CreateUser(UserModel user);
    Optional<UserModel> GetUser(Integer id);
    UserModel EditUser(UserModel user);
     void DeleteUser(Integer id);

}
