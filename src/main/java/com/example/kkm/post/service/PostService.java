package com.example.kkm.post.service;

import com.example.kkm.post.domain.dto.PostDTO;
import com.example.kkm.post.domain.entity.Post;
import com.example.kkm.post.domain.model.PostForm;
import com.example.kkm.post.domain.model.PostListRequestFrom;
import com.example.kkm.post.domain.model.PostUpdateForm;
import com.example.kkm.post.exception.PostNotFoundException;
import com.example.kkm.post.repository.PostRepository;
import com.example.kkm.user.auth.exception.UserNotFoundException;
import com.example.kkm.user.auth.repository.UserRepository;
import com.example.kkm.user.domain.entity.User;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
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

    /**
     * 게시글 불러오기
     * @param post_id 게시글 ID
     * @return 조회된 게시글
     */
    public PostDTO getPostDto(Long post_id) {

        Post post = postRepository.findById(post_id)
            .orElseThrow(() -> new PostNotFoundException("해당 게시글을 찾을 수 없습니다."));

        return post.toPostDTO();
    }

    /**
     * 게시글 수정하기
     * @param post_id 수정할 게시글 ID
     * @param postUpdateForm 수정할 내용
     * @return 수정된 게시글 정보
     */
    public PostDTO updatePost(Long post_id, PostUpdateForm postUpdateForm) {

        Post post = postRepository.findById(post_id)
            .orElseThrow(() -> new PostNotFoundException("해당 게시글을 찾을 수 없습니다."));

        post.update(postUpdateForm);    // 게시글 수정
        postRepository.save(post);

        return post.toPostDTO();
    }

    /**
     * 게시글 삭제하기
     * @param post_id 삭제할 게시글 ID
     */
    public void deletePost(Long post_id) {

        Post post = postRepository.findById(post_id)
            .orElseThrow(() -> new PostNotFoundException("해당 게시글을 찾을 수 없습니다."));

        postRepository.delete(post);
    }

    public List<PostDTO> getRecentPosts(PostListRequestFrom postListRequestFrom) {
        Page<Post> posts = postRepository.findAll(
            PageRequest.of(postListRequestFrom.getPageNumber(),
                postListRequestFrom.getPostCount(),
                Direction.DESC, "createdAt")
        );

        List<PostDTO> results = new ArrayList<>();
        posts.stream().forEach(
            post -> {
                results.add(post.toPostDTO());
            }
        );

        return results;
    }
}
