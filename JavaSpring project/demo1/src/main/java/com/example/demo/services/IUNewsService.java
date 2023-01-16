package com.example.demo.services;

import com.example.demo.models.BookModel;
import com.example.demo.models.NewsModel;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUNewsService extends UserDetailsService {
    NewsModel GetByPet(String pet);
    NewsModel GetByMail(String mail);
    NewsModel CreateNews(NewsModel news);

}
