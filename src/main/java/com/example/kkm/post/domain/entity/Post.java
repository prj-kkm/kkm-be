package com.example.kkm.post.domain.entity;

import com.example.kkm.post.domain.dto.PostDTO;
import com.example.kkm.post.domain.model.PostForm;
import com.example.kkm.user.domain.entity.User;
import jakarta.annotation.Nullable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @NotNull
    private String title;       // 제목

    @NotNull
    private String contents;    // 내용

    @NotNull
    private String category;    // 주제

    private Double latitude;    // 위도
    private Double longitude;   // 경도
    private int likeCount;      // 좋아요 수

    private LocalDateTime createdAt;    // 작성일
    private LocalDateTime updatedAt;    // 수정일

    public Post(User user, PostForm postForm) {
        this.user = user;

        this.title = postForm.getTitle();
        this.contents = postForm.getContents();
        this.category = postForm.getCategory();

        this.latitude = postForm.getLatitude();
        this.longitude = postForm.getLongitude();
        this.likeCount = 0;

        this.createdAt = LocalDateTime.now();
    }

    public PostDTO toPostDTO() {
        return PostDTO.builder()
                    .post_id(this.id)
                    .user_id(this.user.getId())
                    .title(this.title)
                    .contents(this.contents)
                    .category(this.category)
                    .latitude(this.latitude)
                    .longitude(this.longitude)
                    .likeCount(this.likeCount)
                    .createdAt(this.createdAt)
                    .updatedAt(this.updatedAt)
                    .build();
    }
}
