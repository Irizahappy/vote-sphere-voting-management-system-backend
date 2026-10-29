package auca.ac.rw.vote_sphere_voting_management_system.service;

import java.util.List;
import java.time.LocalDate;
import auca.ac.rw.vote_sphere_voting_management_system.entity.BallotStatus;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import auca.ac.rw.vote_sphere_voting_management_system.entity.Ballot;
import auca.ac.rw.vote_sphere_voting_management_system.repository.BallotRepository;

@Service
public class BallotService {

    private final BallotRepository ballotRepository;

    public BallotService(BallotRepository ballotRepository) {
        this.ballotRepository = ballotRepository;
    }

    public String save(Ballot ballot) {
        ballotRepository.save(ballot);
        return "Ballot saved successfully";
    }

    public List<Ballot> getAll() {
        return ballotRepository.findAll();
    }

    public Optional<Ballot> getById(UUID id) {
        return ballotRepository.findById(id);
    }

    public String update(UUID id, Ballot newBallot) {
        Optional<Ballot> optionalBallot = ballotRepository.findById(id);
        if (optionalBallot.isEmpty()) {
            return "The ballot with that id does not exist";
        }
        Ballot ballot = optionalBallot.get();
        ballot.setPollDate(newBallot.getPollDate());
        ballot.setStatus(newBallot.getStatus());
        ballot.setPollQuestion(newBallot.getPollQuestion());
        ballot.setVoter(newBallot.getVoter());
        ballot.setElectionOfficer(newBallot.getElectionOfficer());
        ballotRepository.save(ballot);
        return "Ballot updated successfully";
    }

    public String delete(UUID id) {
        if (!ballotRepository.existsById(id)) {
            return "The ballot with that id does not exist";
        }
        ballotRepository.deleteById(id);
        return "Ballot deleted successfully";
    }

        public List<Ballot> getByStatus(BallotStatus status) {
        return ballotRepository.findByStatusOrderByPollDateAsc(status);
    }

    public List<Ballot> getBetween(LocalDate start, LocalDate end) {
        return ballotRepository.findByPollDateBetweenOrderByPollDateAsc(start, end);
    }
}
