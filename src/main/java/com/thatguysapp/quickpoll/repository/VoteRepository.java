package com.thatguysapp.quickpoll.repository;

import com.thatguysapp.quickpoll.domain.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends CrudRepository<Vote, Long> {

    @Query(value="select v.* from POption o, Vote v where o.POLL_ID = ?1 and v.OPTION_ID = o.OPTION_ID",nativeQuery = true)
    public Iterable<Vote> findByPollId(Long pollId);

}
