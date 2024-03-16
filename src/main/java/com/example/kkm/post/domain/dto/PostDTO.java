package com.example.kkm.post.domain.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {

    private Long post_id;
    private Long user_id;

    private String title;               // 제목
    private String contents;            // 내용
    private String category;            // 주제

    private Double latitude;            // 위도
    private Double longitude;           // 경도
    private int likeCount;              // 좋아요 수

    private LocalDateTime createdAt;    // 작성일
    private LocalDateTime updatedAt;    // 수정일

}
