package auca.ac.rw.vote_sphere_voting_management_system.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import auca.ac.rw.vote_sphere_voting_management_system.entity.Voter;

public interface VoterRepository extends JpaRepository<Voter, UUID> {

    List<Voter> findByLastNameIgnoreCaseOrderByFirstNameAsc(String lastName);

    @Query("""
        SELECT DISTINCT b.voter FROM Ballot b
        WHERE b.electionOfficer.id = :electionOfficerId
        """)
    List<Voter> votersOfElectionOfficer(@Param("electionOfficerId") UUID electionOfficerId);
}