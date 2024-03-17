package com.example.kkm.post.controller;

import com.example.kkm.post.domain.dto.PostDTO;
import com.example.kkm.post.domain.model.PostForm;
import com.example.kkm.post.domain.model.PostListRequestFrom;
import com.example.kkm.post.domain.model.PostResponseError;
import com.example.kkm.post.domain.model.PostUpdateForm;
import com.example.kkm.post.exception.PostNotFoundException;
import com.example.kkm.post.service.PostService;
import com.example.kkm.user.auth.exception.UserNotFoundException;
import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    /**
     * 새 게시글 작성을 위한 API
     * @param postForm 새 게시글 작성 정보
     * @param errors
     * @return 새로 작성된 게시글
     */
    @PostMapping("/api/post")
    public ResponseEntity<?> createNewPost(@RequestBody @Valid PostForm postForm, Errors errors) {
        if (errors.hasErrors()) {
            return sendPostResponseErrors(errors);
        }

        try {
            PostDTO postDTO = postService.createNewPost(postForm);
            return new ResponseEntity<>(postDTO, HttpStatus.CREATED);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * 게시글 조회를 위한 API
     * @param post_id 게시글 ID
     * @return 조회된 게시글
     */
    @GetMapping("/api/post/{post_id}")
    public ResponseEntity<?> getPost(@PathVariable Long post_id) {
        try {
            return new ResponseEntity<>(postService.getPostDto(post_id), HttpStatus.OK);
        } catch (PostNotFoundException exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/api/post/recent")
    public ResponseEntity<?> getRecentPosts(@RequestBody PostListRequestFrom postListRequestFrom, Errors errors) {
        if (errors.hasErrors()) {
            return sendPostResponseErrors(errors);
        }

        try {
            List<PostDTO> recentPosts = postService.getRecentPosts(postListRequestFrom);
            return new ResponseEntity<>(recentPosts, HttpStatus.OK);
        } catch (RuntimeException exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * 게시글 수정을 위한 API
     * @param post_id 게시글 ID
     * @param postUpdateForm 게시글 수정 내용
     * @param errors
     * @return 수정된 게시글 정보
     */
    @PatchMapping("/api/post/{post_id}")
    public ResponseEntity<?> updatePost(@PathVariable Long post_id,
        @RequestBody @Valid PostUpdateForm postUpdateForm,
        Errors errors) {
        if (errors.hasErrors()) {
            return sendPostResponseErrors(errors);
        }

        try {
            PostDTO postDTO = postService.updatePost(post_id, postUpdateForm);
            return new ResponseEntity<>(postDTO, HttpStatus.OK);
        } catch (PostNotFoundException exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * 게시글 삭제를 위한 API
     * @param post_id 게시글 ID
     * @return 삭제 결과
     */
    @DeleteMapping("/api/post/{post_id}")
    public ResponseEntity<?> deletePost(@PathVariable Long post_id) {
        try {
            postService.deletePost(post_id);
            return new ResponseEntity<>("게시글이 정상적으로 삭제됐습니다.", HttpStatus.OK);
        } catch (PostNotFoundException exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * 유효성 처리
     * @param errors 유효성 관련 에러
     * @return 에러
     */
    private ResponseEntity<?> sendPostResponseErrors(Errors errors) {
        var postResponseErrors = new ArrayList<PostResponseError>();

        errors.getAllErrors().forEach(error ->
            postResponseErrors.add(PostResponseError.of((FieldError) error))
        );

        return new ResponseEntity<>(postResponseErrors, HttpStatus.BAD_REQUEST);
    }
}
