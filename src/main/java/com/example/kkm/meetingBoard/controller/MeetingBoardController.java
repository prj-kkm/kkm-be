package com.example.kkm.meetingBoard.controller;

import com.example.kkm.meetingBoard.entity.MeetingBoard;
import com.example.kkm.meetingBoard.model.MeetingBoardInput;
import com.example.kkm.meetingBoard.repository.MeetingBoardRepository;
import com.example.kkm.meetingBoard.service.MeetingBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/meetingBoard")
public class MeetingBoardController {
   /* @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
    */


    private final MeetingBoardRepository meetingBoardRepository;

    private final MeetingBoardService meetingBoardService;

    @PostMapping("/create/post")
    public ResponseEntity<MeetingBoard> createMeetingBoard(@RequestBody MeetingBoardInput meetingBoardInput) {
        return new ResponseEntity<>(meetingBoardService.createMeeting(meetingBoardInput),
            HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<MeetingBoard> getMeetingBoardById(
        @PathVariable(value = "id") Long meetingBoardId) {
        return new ResponseEntity<>(meetingBoardService.getMeetingBoardById(meetingBoardId),
            HttpStatus.OK);
    }
    /*
    게시글 리스트 조회 기능

    public List<MeetingBoard> getAllMeetingBoard(){
        return meetingBoardRepository.findAll();
    }


     */
    @PutMapping("/update/{meetingId}")
    public ResponseEntity<MeetingBoard> updateMeetingBoard(
        @PathVariable(value = "meetingId") Long meetingId,
        @Validated @RequestBody MeetingBoardInput meetingBoardInput) {
        MeetingBoard updatedMeetingBaord = meetingBoardService.updateMeeting(meetingId,
            meetingBoardInput);
        return ResponseEntity.ok(updatedMeetingBaord);
    }

    @DeleteMapping("/delete/{meetingId}")
    public ResponseEntity<?> deleteMeetingBaord(@PathVariable Long meetingId) {
        meetingBoardService.deleteMeetingBoard(meetingId);
        return ResponseEntity.ok().build();
    }

}
