package com.example.kkm.user.auth.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.FieldError;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAuthResponseError {

    private String field;
    private String message;

    public static UserAuthResponseError of(FieldError error) {
        return UserAuthResponseError.builder()
                                    .field(error.getField())
                                    .message(error.getDefaultMessage())
                                    .build();
    }
}
