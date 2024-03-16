package com.example.kkm.post.controller;

import com.example.kkm.post.domain.dto.PostDTO;
import com.example.kkm.post.domain.model.PostForm;
import com.example.kkm.post.domain.model.PostResponseError;
import com.example.kkm.post.service.PostService;
import com.example.kkm.user.auth.exception.UserNotFoundException;
import jakarta.validation.Valid;
import java.util.ArrayList;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
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
     * @return 결과
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

        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

        }
    }

    /**
     * 유효성 처리
     * @param errors 유효성 관련 에러
     * @return 에러
     */
    private ResponseEntity<?> sendPostResponseErrors(Errors errors) {
        var postResponseErrors = new ArrayList<PostResponseError>();

        errors.getAllErrors().forEach(error -> {
            postResponseErrors.add(PostResponseError.of((FieldError) error));
        });

        return new ResponseEntity<>(postResponseErrors, HttpStatus.BAD_REQUEST);
    }
}
