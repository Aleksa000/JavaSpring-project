package com.example.demo.services;

import com.example.demo.models.BookModel;
import com.example.demo.models.NewsModel;
import com.example.demo.models.UserModel;
import com.example.demo.repositories.IUNewsRepository;
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
public class NewsService implements IUNewsService{

    private final IUNewsRepository userNewsRepository;
    private final IUserRepository userRepository;
    private final ModelMapper mapper;

    public NewsService (IUserRepository userRepository, IUNewsRepository userNewsRepository, ModelMapper mapper){
        this.userNewsRepository=userNewsRepository;
        this.userRepository=userRepository;
        this.mapper = mapper;

    }

    @Override
    public NewsModel GetByPet(String pet) {
        return userNewsRepository.findByPet(pet);
    }
    @Override
    public NewsModel GetByMail(String mail) {
        return userNewsRepository.findByMail(mail);
    }

    @Override
    public NewsModel CreateNews(NewsModel news) {
        return userNewsRepository.save(news);
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
