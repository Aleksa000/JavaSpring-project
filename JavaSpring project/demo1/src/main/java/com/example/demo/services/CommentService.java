package com.example.demo.services;

import com.example.demo.entity.CommentEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.models.CommentModel;
import com.example.demo.models.UserModel;
import com.example.demo.repositories.*;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service()
public class CommentService implements IUCommentService{

    private final IUCommentRepository userCommentRepository;
    private final IUserRepository userRepository;

    private final ModelMapper mapper;

    public CommentService(IUserRepository userRepository,IUCommentRepository userCommentRepository,ModelMapper mapper){
        this.userCommentRepository=userCommentRepository;
        this.userRepository=userRepository;
        this.mapper = mapper;

    }

    @Override
    public CommentModel GetByName(String name) {
        return mapper.map(userCommentRepository.findByName(name), CommentModel.class);
    }

    @Override
    public CommentModel CreateComment(CommentModel comment) {
        var createdComment = userCommentRepository.save(mapper.map(comment, CommentEntity.class));
        return mapper.map(createdComment, CommentModel.class);
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

