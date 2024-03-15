package com.example.kkm.meetingBoard.service;

import com.example.kkm.meetingBoard.entity.MeetingBoard;
import com.example.kkm.meetingBoard.exception.MeetingBoardException;
import com.example.kkm.meetingBoard.repository.MeetingBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MeetingBoardServiceImpl implements MeetingBoardService {

    @Autowired
    private final MeetingBoardRepository meetingBoardRepository;

    public MeetingBoardServiceImpl(MeetingBoardRepository meetingBoardRepository) {
        this.meetingBoardRepository = meetingBoardRepository;
    }


    @Override
    public MeetingBoard createMeeting(MeetingBoard meetingBoard) {
        return meetingBoardRepository.save(meetingBoard);
    }

    @Override
    public MeetingBoard updateMeeting(long meetingId, MeetingBoard meetingBoardDetails) {
        //게시글 찾기
        MeetingBoard existingMeetingBoard = meetingBoardRepository.findById(meetingId)
            .orElseThrow(()-> new MeetingBoardException(meetingId+"의 게시글이 존재하지 않습니다."));

        //삭제된 게시글은 업데이트 하지 않음
        if(existingMeetingBoard.isDeleted()){
            throw new IllegalStateException("삭제된 모임게시글은 수정할 수 없습니다.");
        }
        MeetingBoard meetingBoard = getMeetingBoardById(meetingId);
        meetingBoard.setMeetingName(meetingBoardDetails.getMeetingName());
        meetingBoard.setMeetingOverview(meetingBoardDetails.getMeetingOverview());
        meetingBoard.setMeetingDate(meetingBoardDetails.getMeetingDate());

        return meetingBoardRepository.save(meetingBoard);
    }

    @Override
    public MeetingBoard getMeetingBoardById(long meetingId) {
        return meetingBoardRepository.findById(meetingId)
            .orElseThrow(() -> new MeetingBoardException("모임 게시글이 없습니다."));
    }

    @Override
    public void deleteMeetingBoard(long meetingId) {
        MeetingBoard meetingBoard = getMeetingBoardById(meetingId);
        meetingBoard.setDeleted(true);
        meetingBoardRepository.save(meetingBoard);
    }
}
