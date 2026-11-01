package auca.ac.rw.vote_sphere_voting_management_system.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import auca.ac.rw.vote_sphere_voting_management_system.entity.Ballot;
import auca.ac.rw.vote_sphere_voting_management_system.entity.BallotStatus;

public interface BallotRepository extends JpaRepository<Ballot, UUID> {

    List<Ballot> findByStatusOrderByPollDateAsc(BallotStatus status);

    List<Ballot> findByPollDateBetweenOrderByPollDateAsc(LocalDate start, LocalDate end);

    boolean existsByElectionOfficerIdAndPollDateAndStatusNot(UUID electionOfficerId, LocalDate pollDate, BallotStatus status);

    @Query("SELECT b.status, COUNT(b) FROM Ballot b GROUP BY b.status")
    List<Object[]> statsByStatus();

    @Modifying
    @Query("""
        UPDATE Ballot b SET b.status = :spoiled
        WHERE b.electionOfficer.id = :electionOfficerId
        AND b.pollDate = :date
        AND b.status <> :counted
        """)
    int cancelDay(@Param("electionOfficerId") UUID electionOfficerId,
                  @Param("date") LocalDate date,
                  @Param("spoiled") BallotStatus spoiled,
                  @Param("counted") BallotStatus counted);
}