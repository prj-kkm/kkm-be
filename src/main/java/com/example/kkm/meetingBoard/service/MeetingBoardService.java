package com.example.kkm.meetingBoard.service;

import com.example.kkm.meetingBoard.entity.MeetingBoard;
import com.example.kkm.meetingBoard.entity.MeetingMember;
import com.example.kkm.meetingBoard.model.MeetingBoardModel;
import com.example.kkm.meetingBoard.repository.MeetingBoardRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class MeetingBoardService {
    @Autowired
    private MeetingBoardRepository meetingBoardRepository;

    //생성
    public MeetingBoard createMeetingBoard( MeetingBoard meetingBoard){
        return meetingBoardRepository.save(meetingBoard);
    }
    //조회
    public List<MeetingBoard> getAllMeetingBoard(){
        return meetingBoardRepository.findAll();
    }
    public Optional<MeetingBoard> getMeetingBoardById(Long id){
        return meetingBoardRepository.findById(id);
    }
    //수정
    public MeetingBoardModel updateMeetingBoard(Long id, MeetingBoard meetingBoard){

    }


}
