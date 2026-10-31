package auca.ac.rw.vote_sphere_voting_management_system.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import auca.ac.rw.vote_sphere_voting_management_system.entity.Ballot;
import auca.ac.rw.vote_sphere_voting_management_system.entity.BallotStatus;
import auca.ac.rw.vote_sphere_voting_management_system.repository.BallotRepository;
import auca.ac.rw.vote_sphere_voting_management_system.repository.VoteEntryRepository;

@Service
public class BallotService {

    private final BallotRepository ballotRepository;
    private final VoteEntryRepository voteEntryRepository;

    public BallotService(BallotRepository ballotRepository, VoteEntryRepository voteEntryRepository) {
        this.ballotRepository = ballotRepository;
        this.voteEntryRepository = voteEntryRepository;
    }

    public String save(Ballot ballot) {
        if (ballot.getPollDate().isBefore(LocalDate.now())) {
            return "Ballot date cannot be in the past";
        }
        boolean alreadyManaging = ballotRepository.existsByElectionOfficerIdAndPollDateAndStatusNot(
                ballot.getElectionOfficer().getId(), ballot.getPollDate(), BallotStatus.SPOILED);
        if (alreadyManaging) {
            return "Election officer already manages a ballot on that date";
        }
        ballot.setStatus(BallotStatus.ISSUED);
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
        ballot.setPollQuestion(newBallot.getPollQuestion());
        ballot.setVoter(newBallot.getVoter());
        ballot.setElectionOfficer(newBallot.getElectionOfficer());
        ballotRepository.save(ballot);
        return "Ballot updated successfully";
    }

    public String delete(UUID id) {
        Optional<Ballot> optionalBallot = ballotRepository.findById(id);
        if (optionalBallot.isEmpty()) {
            return "The ballot with that id does not exist";
        }
        if (voteEntryRepository.existsByBallot(optionalBallot.get())) {
            return "That ballot has vote entries, delete them first";
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

    public String changeStatus(UUID id, BallotStatus newStatus) {
        Optional<Ballot> optionalBallot = ballotRepository.findById(id);
        if (optionalBallot.isEmpty()) {
            return "The ballot with that id does not exist";
        }
        Ballot ballot = optionalBallot.get();
        BallotStatus currentStatus = ballot.getStatus();

        boolean allowed = false;
        if (currentStatus == BallotStatus.ISSUED
                && (newStatus == BallotStatus.CAST || newStatus == BallotStatus.SPOILED)) {
            allowed = true;
        }
        if (currentStatus == BallotStatus.CAST
                && (newStatus == BallotStatus.COUNTED || newStatus == BallotStatus.SPOILED)) {
            allowed = true;
        }

        if (!allowed) {
            return "Cannot change status from " + currentStatus + " to " + newStatus;
        }
        ballot.setStatus(newStatus);
        ballotRepository.save(ballot);
        return "Ballot status updated successfully";
    }
}