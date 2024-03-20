package com.example.kkm.meetingBoard.service;

import com.example.kkm.meetingBoard.entity.MeetingBoard;
import com.example.kkm.meetingBoard.entity.MeetingBoardHits;
import com.example.kkm.meetingBoard.exception.MeetingBoardException;
import com.example.kkm.meetingBoard.model.MeetingBoardInput;
import com.example.kkm.meetingBoard.repository.MeetingBoardHitsRepository;
import com.example.kkm.meetingBoard.repository.MeetingBoardRepository;
import com.example.kkm.user.auth.repository.UserRepository;
import com.example.kkm.user.domain.entity.User;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MeetingBoardServiceImpl implements MeetingBoardService {

    @Autowired
    private final MeetingBoardRepository meetingBoardRepository;

    public MeetingBoardServiceImpl(MeetingBoardRepository meetingBoardRepository) {
        this.meetingBoardRepository = meetingBoardRepository;
    }

    @Autowired
    private UserRepository userRepository;

    @Override
    public MeetingBoard createMeeting(MeetingBoardInput meetingBoardInput) {
        MeetingBoard meeting = MeetingBoard.builder()
            .meetingName(meetingBoardInput.getTitle())
            .meetingOverview(meetingBoardInput.getContents())
            .meetingDate(meetingBoardInput.getMeetingDate())
            .regDate(LocalDateTime.now())
            .updateDate(LocalDateTime.now())
            .build();

        return meetingBoardRepository.save(meeting);

    }

    @Override
    public MeetingBoard updateMeeting(long meetingId, MeetingBoardInput input) {
        //게시글 찾기
        MeetingBoard existingMeetingBoard = meetingBoardRepository.findById(meetingId)
            .orElseThrow(()-> new MeetingBoardException(meetingId+"의 게시글이 존재하지 않습니다."));

        //삭제된 게시글은 업데이트 하지 않음
        if(existingMeetingBoard.isDeleted()){
            throw new IllegalStateException("삭제된 모임게시글은 수정할 수 없습니다.");
        }
        existingMeetingBoard.setMeetingName(input.getTitle());
        existingMeetingBoard.setMeetingOverview(input.getContents());
        existingMeetingBoard.setMeetingDate(input.getMeetingDate());

        return meetingBoardRepository.save(existingMeetingBoard);
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


    @Override
    @Transactional
    public MeetingBoard incrementViewCountAndGetMeetingBoard(Long meetingId) {
        MeetingBoard meetingBoard = meetingBoardRepository.findById(meetingId)
            .orElseThrow(()-> new IllegalArgumentException("게시글이 존재하지 않습니다"));
        meetingBoard.incrementViewCount(); //조회수 증가 메소드 호출
        //save 호출 없이 변경상항 데이터베이스 반영
        return meetingBoard;
    }


}
