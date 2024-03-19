package com.example.kkm.meetingBoard.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class MeetingBoardInput {

    private String title;
    private String contents;
    @JsonFormat(shape = Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime meetingDate;

}
