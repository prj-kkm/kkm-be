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
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class MeetingMember {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //의문점 : 미팅멤버 테이블에 id가 꼭 필요로하는가? 필요로 한다.

    @ManyToOne
    @JoinColumn(name = "meeting_board_id")
    private MeetingBoard meetingBoard;

    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;

    @Column
    private LocalDateTime joinDate;
}
