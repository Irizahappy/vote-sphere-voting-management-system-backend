package auca.ac.rw.vote_sphere_voting_management_system.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import auca.ac.rw.vote_sphere_voting_management_system.entity.PollType;

public interface PollTypeRepository extends JpaRepository<PollType, UUID> {

    @Query("SELECT t FROM PollType t WHERE t.electionOfficers IS EMPTY")
    List<PollType> unusedPollTypes();
}