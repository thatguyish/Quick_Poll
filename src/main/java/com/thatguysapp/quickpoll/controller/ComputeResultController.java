package com.thatguysapp.quickpoll.controller;

import com.thatguysapp.quickpoll.domain.Vote;
import com.thatguysapp.quickpoll.dto.OptionCount;
import com.thatguysapp.quickpoll.dto.VoteResult;
import com.thatguysapp.quickpoll.repository.VoteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ComputeResultController {

    @Inject
    private VoteRepository voteRepository;

    @RequestMapping(value="/computeresult", method = RequestMethod.GET)
    public ResponseEntity<?> computeResult(@RequestParam Long pollId) {
        VoteResult voteResult = new VoteResult();
        Iterable<Vote> allVotes = voteRepository.findByPollId(pollId);

        int totalVotes = 0;
        Map<Long, OptionCount> tempMap = new HashMap<>();
        for (Vote vote : allVotes) {
            totalVotes++;
            OptionCount optionCount = tempMap.get(vote.getOption().getId());
            if (optionCount == null) {
                optionCount = new OptionCount();
                optionCount.setOptionId(vote.getOption().getId());
                tempMap.put(vote.getOption().getId(), optionCount);
            }
            optionCount.setCount(optionCount.getCount() + 1);
        }
        voteResult.setTotalVotes(totalVotes);


        return new ResponseEntity<VoteResult>(voteResult, HttpStatus.OK);
    }

}
