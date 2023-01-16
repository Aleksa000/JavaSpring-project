package com.example.demo.services;
import com.example.demo.models.*;
import com.example.demo.models.ContactModel;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUContactService extends UserDetailsService {

    ContactModel GetBySubject(String subject);
    ContactModel GetByMessage(String message);
    ContactModel CreateContact(ContactModel contact);
}
