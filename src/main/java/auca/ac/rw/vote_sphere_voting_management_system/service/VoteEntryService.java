package auca.ac.rw.vote_sphere_voting_management_system.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import auca.ac.rw.vote_sphere_voting_management_system.entity.Ballot;
import auca.ac.rw.vote_sphere_voting_management_system.entity.BallotStatus;
import auca.ac.rw.vote_sphere_voting_management_system.entity.VoteEntry;
import auca.ac.rw.vote_sphere_voting_management_system.repository.BallotRepository;
import auca.ac.rw.vote_sphere_voting_management_system.repository.VoteEntryRepository;

@Service
public class VoteEntryService {

    private final VoteEntryRepository voteEntryRepository;
    private final BallotRepository ballotRepository;

    public VoteEntryService(VoteEntryRepository voteEntryRepository, BallotRepository ballotRepository) {
        this.voteEntryRepository = voteEntryRepository;
        this.ballotRepository = ballotRepository;
    }

    public String save(UUID ballotId, VoteEntry voteEntry) {
        Optional<Ballot> optionalBallot = ballotRepository.findById(ballotId);
        if (optionalBallot.isEmpty()) {
            return "The ballot with that id does not exist";
        }
        Ballot ballot = optionalBallot.get();
        if (ballot.getStatus() != BallotStatus.COUNTED) {
            return "Vote entries are only for COUNTED ballots";
        }
        voteEntry.setBallot(ballot);
        voteEntry.setCountedDate(LocalDate.now());
        voteEntryRepository.save(voteEntry);
        return "Vote entry saved successfully";
    }

    public List<VoteEntry> getByVoterNumber(String voterNumber) {
        return voteEntryRepository.findHistoryByVoterNumber(voterNumber);
    }

    public String delete(UUID id) {
        if (!voteEntryRepository.existsById(id)) {
            return "The vote entry with that id does not exist";
        }
        voteEntryRepository.deleteById(id);
        return "Vote entry deleted successfully";
    }
}
