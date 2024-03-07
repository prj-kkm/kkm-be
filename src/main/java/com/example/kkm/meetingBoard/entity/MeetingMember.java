package com.example.kkm.meetingBoard.entity;

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
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class MeetingMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long meetingMemberId;
    //의문점 : 미팅멤버 테이블에 id가 꼭 필요로하는가? 필요로 한다.

    @JoinColumn
    @ManyToOne
    private MeetingBoard meetingId;

    @JoinColumn
    @ManyToOne
    private User userId;

    @Column
    private LocalDateTime joinDate;
}
