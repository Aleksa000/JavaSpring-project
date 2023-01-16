package com.example.demo.services;

import com.example.demo.models.BookModel;
import com.example.demo.models.UserModel;
import com.example.demo.repositories.IUBookRepository;
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
public class BookService implements IUBookService{

    private final IUBookRepository userBookRepository;
    private final IUserRepository userRepository;
    private final ModelMapper mapper;

    public BookService (IUserRepository userRepository,IUBookRepository userBookRepository,ModelMapper mapper){
        this.userBookRepository=userBookRepository;
        this.userRepository=userRepository;
        this.mapper = mapper;

    }

    @Override
    public BookModel GetByDate(String date) {
        return userBookRepository.findByDate(date);
    }
    @Override
    public BookModel GetByTime(String time) {
        return userBookRepository.findByTime(time);
    }
    @Override
    public List<BookModel> GetAllByService(String service) {
        return userBookRepository.findAllByService(service);
    }
    @Override
    public BookModel CreateBook(BookModel book) {
        return userBookRepository.save(book);
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
