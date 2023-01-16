package com.example.demo.repositories;

import com.example.demo.entity.CommentEntity;
import com.example.demo.models.CommentModel;
import com.example.demo.models.UserWithProductsModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUCommentRepository extends JpaRepository<CommentEntity, Integer> {
    CommentEntity findByName(String name);
}
