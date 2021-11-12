package com.thatguysapp.quickpoll.repository;

import com.thatguysapp.quickpoll.domain.Poll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PollRepository extends JpaRepository<Poll, Long> {
}
