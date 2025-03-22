package com.embarkx.challengeapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/challenges")
public class ChallengeController
{

    @Autowired
    private ChallengeService challengeService;

    @GetMapping
    public ResponseEntity<List<Challenge>> getChallenge() {
        return new ResponseEntity<>(challengeService.getChallenge(), HttpStatus.OK);
    }

    @GetMapping("/{month}")
    public ResponseEntity<Challenge> getChallengeByMonth(@PathVariable String month) {
        Challenge challenge = challengeService.getChallengeByMonth(month);

        if (Objects.nonNull(challenge))
        {
            return new ResponseEntity<>(challenge, HttpStatus.OK);
        } else
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<String> addChallenges(@RequestBody Challenge challenge) {
        Boolean isCreated = challengeService.addChallenges(challenge);
        if (isCreated)
        {
            return new ResponseEntity<>("Challenge successfully created", HttpStatus.OK);
        } else
        {
            return new ResponseEntity<>("Challenge not created", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateChallengeById(@PathVariable Long id, @RequestBody Challenge updatedChallenge) {
        Boolean isUpdated = challengeService.updateChallenge(id, updatedChallenge);
        if (isUpdated)
        {
            return new ResponseEntity<>("Challenge updated successfully", HttpStatus.OK);
        } else
        {
            return new ResponseEntity<>("Challenge not updated successfully", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteChallenge(@PathVariable Long id) {
        Boolean isDeleted = challengeService.deleteChallenge(id);
        if (isDeleted)
        {
            return new ResponseEntity<>("Challenge deleted successfully", HttpStatus.OK);
        } else
        {
            return new ResponseEntity<>("Challenge not deleted", HttpStatus.NOT_FOUND);
        }
    }

}
