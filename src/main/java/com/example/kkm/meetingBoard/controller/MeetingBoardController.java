package com.example.kkm.meetingBoard.controller;

import com.example.kkm.meetingBoard.entity.MeetingBoard;
import com.example.kkm.meetingBoard.model.MeetingBoardInput;
import com.example.kkm.meetingBoard.model.MeetingBoardModel;
import com.example.kkm.meetingBoard.repository.MeetingBoardRepository;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/meetingBoard")
public class MeetingBoardController {
   /* @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
    */

    private final MeetingBoardRepository meetingBoardRepository;

    public MeetingBoardController(MeetingBoardRepository meetingBoardRepository) {
        this.meetingBoardRepository = meetingBoardRepository;
    }

    @PostMapping("/createMeeting")
    public void createMeeting(@RequestBody MeetingBoardInput meetingBoardInput){
        MeetingBoard meetingBoard= MeetingBoard.builder()
            .meetingName(meetingBoardInput.getTitle())
            .meetingOverview(meetingBoardInput.getContents())
            .meetingDate(meetingBoardInput.getMeetingDate())
            .regDate(LocalDateTime.now())
            .build();
        //MeetingBoardRepository.save();
    }


}
