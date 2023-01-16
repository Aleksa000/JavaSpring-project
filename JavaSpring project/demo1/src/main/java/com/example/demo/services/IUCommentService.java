package com.example.demo.services;
import com.example.demo.models.*;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUCommentService  extends UserDetailsService {
    CommentModel GetByName(String name);
    CommentModel CreateComment(CommentModel comment);
}
