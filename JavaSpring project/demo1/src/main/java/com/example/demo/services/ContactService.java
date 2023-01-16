package com.example.demo.services;

import com.example.demo.models.ContactModel;
import com.example.demo.models.UserModel;
import com.example.demo.repositories.IUCommentRepository;
import com.example.demo.repositories.IUContactRepository;
import com.example.demo.repositories.IUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service()
public class ContactService implements IUContactService{

    private final IUContactRepository userContactRepository;
    private final IUserRepository userRepository;
    private final ModelMapper mapper;

    public ContactService (IUserRepository userRepository,IUContactRepository userContactRepository,ModelMapper mapper){
        this.userContactRepository=userContactRepository;
        this.userRepository=userRepository;
        this.mapper = mapper;

    }
    @Override
    public ContactModel GetBySubject(String subject) {
        return userContactRepository.findBySubject(subject);
    }

    @Override
    public ContactModel GetByMessage(String message) {
        return userContactRepository.findByMessage(message);
    }

    @Override
    public ContactModel CreateContact(ContactModel contact) {
        return userContactRepository.save(contact);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel user = mapper.map(userRepository.findByEmail(username), UserModel.class);
        if (user == null){
            throw new UsernameNotFoundException("User not found!");
        }

        List authorities = Arrays.asList(new SimpleGrantedAuthority("user"));

        return new User(user.getEmail(), user.getPassword(), authorities);
    }
}
