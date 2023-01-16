package com.example.demo.services;
import com.example.demo.models.*;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUBookService extends UserDetailsService {
    BookModel GetByDate(String date);
    BookModel GetByTime(String time);
    BookModel CreateBook(BookModel book);
    List<BookModel> GetAllByService (String lastName);

}
