package com.example.kkm.meetingBoard.service;

import com.example.kkm.meetingBoard.entity.MeetingBoard;
import com.example.kkm.meetingBoard.model.MeetingModel;


public interface MeetingBoardService {

//crud

    MeetingBoard createMeeting(MeetingBoard meetingBoard);

    MeetingBoard updateMeeting(long meetingId, MeetingBoard meetingBoard);

    MeetingBoard getMeetingBoardById(long meetingId);

    void deleteMeetingBoard(long meetingId);


}
