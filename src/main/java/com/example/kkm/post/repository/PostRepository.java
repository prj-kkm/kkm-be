package com.example.kkm.post.repository;

import com.example.kkm.post.domain.entity.Post;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}
