package com.example.kkm.meetingBoard.entity;

import com.example.kkm.user.domain.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MeetingBoard {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    private String meetingName;

    @Column
    private String meetingOverview;

    @Column
    private int members;
    @Column
    private LocalDateTime meetingDate;
    @Column
    private LocalDateTime regDate;
    @Column
    private LocalDateTime updateDate;
    @Column
    private boolean deleted;

    //조회수 관련 추가

    @Column
    private Long viewCount = 0L;

    public void incrementViewCount() {
        this.viewCount += 1;
    }


}
