package auca.ac.rw.vote_sphere_voting_management_system.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import auca.ac.rw.vote_sphere_voting_management_system.entity.Ballot;
import auca.ac.rw.vote_sphere_voting_management_system.entity.BallotStatus;

public interface BallotRepository extends JpaRepository<Ballot, UUID> {

    List<Ballot> findByStatusOrderByPollDateAsc(BallotStatus status);

    List<Ballot> findByPollDateBetweenOrderByPollDateAsc(LocalDate start, LocalDate end);

    boolean existsByElectionOfficerIdAndPollDateAndStatusNot(UUID electionOfficerId, LocalDate pollDate, BallotStatus status);
}