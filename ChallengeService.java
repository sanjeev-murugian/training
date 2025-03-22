package com.embarkx.challengeapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ChallengeService
{

    @Autowired
    private ChallengeRepository challengeRepo;

    public List<Challenge> getChallenge() {
        return challengeRepo.findAll();
    }

    public Challenge getChallengeByMonth(String month) {
        Optional<Challenge> challenge =
                challengeRepo.findByMonthIgnoreCase(month);

        return challenge.orElse(null);
    }

    public Boolean addChallenges(Challenge challenge) {
        if (Objects.nonNull(challenge))
        {
            challengeRepo.save(challenge);
            return true;
        } else
        {
            return false;
        }
    }

    public Boolean updateChallenge(Long challengeId, Challenge updateChallenge) {
        Optional<Challenge> challenge = challengeRepo.findById(challengeId);
        if (challenge.isPresent())
        {
            Challenge challengeToUpdate = challenge.get();

            challengeToUpdate.setMonth(updateChallenge.getMonth());
            challengeToUpdate.setDescription(updateChallenge.getDescription());
            challengeRepo.save(challengeToUpdate);
            return true;
        }
        return false;
    }

    public Boolean deleteChallenge(Long id)
    {
        Optional<Challenge> challenge = challengeRepo.findById(id);
        if (challenge.isPresent())
        {
            challengeRepo.deleteById(id);
            return true;
        }
        return false;
    }
}
