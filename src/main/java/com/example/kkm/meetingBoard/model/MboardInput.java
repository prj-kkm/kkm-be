package com.example.kkm.meetingBoard.model;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class MboardInput {

    private String title;
    private String contents;
    private LocalDateTime meetingDate;

}
