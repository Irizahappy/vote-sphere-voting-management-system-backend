package auca.ac.rw.vote_sphere_voting_management_system.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import auca.ac.rw.vote_sphere_voting_management_system.entity.Ballot;
import auca.ac.rw.vote_sphere_voting_management_system.entity.VoteEntry;

public interface VoteEntryRepository extends JpaRepository<VoteEntry, UUID> {

    @Query("""
        SELECT d FROM VoteEntry d
        WHERE d.ballot.voter.voterNumber = :voterNumber
        ORDER BY d.countedDate DESC
        """)
    List<VoteEntry> findHistoryByVoterNumber(@Param("voterNumber") String voterNumber);

    boolean existsByBallot(Ballot ballot);
}
