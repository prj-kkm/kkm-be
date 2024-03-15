package com.example.kkm.meetingBoard.model;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MeetingModel {

    private Long id;     //모임 id
    private Long userId;    //유저 id
    private String meetingName; //모임명
    private String meetingOverview; // 모임 소개
    private int members; // 모임 인원
    private LocalDateTime meetingDate; //모임 날짜

    //이부분은 erd에서도 수정 필요(글 작성,수정,삭제)
    private LocalDateTime regDate; //글 작성 시각
    private LocalDateTime updateDate; //글 수정시각
    private boolean deleted; //글 삭제여부
}
