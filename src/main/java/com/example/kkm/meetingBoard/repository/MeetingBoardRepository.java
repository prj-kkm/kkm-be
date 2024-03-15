package com.example.kkm.meetingBoard.repository;

import com.example.kkm.meetingBoard.entity.MeetingBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeetingBoardRepository extends JpaRepository<MeetingBoard,Long> {

}
