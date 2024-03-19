package com.example.kkm.post.domain.model;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class PostListRequestFrom {

    @NotNull(message = "페이지 번호를 입력해주세요.")
    private int pageNumber;

    @NotNull(message = "조회할 게시글 개수를 입력해주세요.")
    private int postCount;

}
