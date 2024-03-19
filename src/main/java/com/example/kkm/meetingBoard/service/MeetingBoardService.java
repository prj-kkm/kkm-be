package com.example.kkm.meetingBoard.service;

import com.example.kkm.meetingBoard.entity.MeetingBoard;
import com.example.kkm.meetingBoard.model.MeetingBoardInput;
import com.example.kkm.meetingBoard.model.MeetingModel;


public interface MeetingBoardService {

//crud

    MeetingBoard createMeeting(MeetingBoardInput meetingBoardInput);


    MeetingBoard updateMeeting(long meetingId, MeetingBoardInput input);

    MeetingBoard getMeetingBoardById(long meetingId);

    void deleteMeetingBoard(long meetingId);


}
