package com.example.kkm.meetingBoard.repository;

import com.example.kkm.meetingBoard.entity.MeetingBoard;
import com.example.kkm.meetingBoard.entity.MeetingBoardHits;
import com.example.kkm.user.domain.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeetingBoardHitsRepository extends JpaRepository<MeetingBoardHits, Long> {

}
