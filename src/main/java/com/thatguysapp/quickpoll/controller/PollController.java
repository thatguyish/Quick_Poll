package com.thatguysapp.quickpoll.controller;

import com.thatguysapp.quickpoll.domain.Poll;
import com.thatguysapp.quickpoll.exception.ResourceNotFoundException;
import com.thatguysapp.quickpoll.repository.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.inject.Inject;
import java.net.URI;

@RestController
public class PollController {

    @Autowired
    private PollRepository pollRepository;

    protected void verifyPoll(Long pollId) throws ResourceNotFoundException {
        pollRepository.findById(pollId)
                .orElseThrow(()->new ResourceNotFoundException("Poll with id" + pollId + "not found"));
    }

    @RequestMapping(value="/polls",method= RequestMethod.GET)
    public ResponseEntity<Iterable<Poll>> getAllPolls() {
        Iterable<Poll> polls = pollRepository.findAll();
        return new ResponseEntity(pollRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value="/polls", method= RequestMethod.POST)
    public ResponseEntity<?> createPoll(@RequestBody Poll poll){
        pollRepository.save(poll);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newPollUri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(poll.getId())
                .toUri();
        responseHeaders.setLocation(newPollUri);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value="/polls/{pollId}", method= RequestMethod.GET)
    public ResponseEntity<?> getPoll(@PathVariable Long pollId) {
        verifyPoll(pollId);
        Poll poll = pollRepository.findById(pollId).get();
        if (poll == null){
            throw new ResourceNotFoundException("Poll with id" + pollId + "not found");

        }
        return new ResponseEntity<>(poll, HttpStatus.OK);
    }

    @RequestMapping(value="/polls/{pollId}",method=RequestMethod.PUT)
    public ResponseEntity<?> updatePoll(@PathVariable Long pollId, @RequestBody Poll poll){
        verifyPoll(pollId);
        pollRepository.save(poll);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/polls/{pollId}")
    public ResponseEntity<?> deletePoll(@PathVariable Long pollId) {
        verifyPoll(pollId);
        pollRepository.deleteById(pollId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
