package com.example.kkm.post.domain.model;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class PostForm {

    @NotNull(message = "작성자를 입력해 주세요.")
    private Long user_id;

    @NotBlank(message = "제목을 입력해 주세요.")
    private String title;

    @NotBlank(message = "게시글 내용을 입력해 주세요.")
    private String contents;

    @NotBlank(message = "태그를 선택해 주세요.")
    private String category;

    @Nullable
    private Double latitude;    // 위도

    @Nullable
    private Double longitude;   // 경도

}
