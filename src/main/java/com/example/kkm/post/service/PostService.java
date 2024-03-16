package com.example.kkm.post.service;

import com.example.kkm.post.domain.dto.PostDTO;
import com.example.kkm.post.domain.entity.Post;
import com.example.kkm.post.domain.model.PostForm;
import com.example.kkm.post.repository.PostRepository;
import com.example.kkm.user.auth.exception.UserNotFoundException;
import com.example.kkm.user.auth.repository.UserRepository;
import com.example.kkm.user.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;

    /**
     * 새 게시글 등록
     * @param postForm 등록할 게시글 정보
     * @return 등록된 게시글
     */
    public PostDTO createNewPost(PostForm postForm) {

        User user = userRepository.findById(postForm.getUser_id())
            .orElseThrow(() -> new UserNotFoundException("유저를 찾을 수 없습니다."));

        Post post = new Post(user, postForm);
        postRepository.save(post);

        return post.toPostDTO();
    }
}
