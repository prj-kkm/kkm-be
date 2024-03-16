package com.example.kkm.post.domain.model;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class PostUpdateForm {

    @NotBlank(message = "제목을 입력해 주세요.")
    private String title;

    @NotBlank(message = "게시글 내용을 입력해 주세요.")
    private String contents;

    @Nullable
    private Double latitude;    // 위도

    @Nullable
    private Double longitude;   // 경도

}
