package com.example.kkm.post.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.FieldError;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostResponseError {

    private String field;
    private String message;

    public static PostResponseError of(FieldError error) {
        return PostResponseError.builder()
                                .field(error.getField())
                                .message(error.getDefaultMessage())
                                .build();
    }

}
