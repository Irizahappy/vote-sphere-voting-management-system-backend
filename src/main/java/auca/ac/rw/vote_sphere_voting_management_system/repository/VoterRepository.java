package auca.ac.rw.vote_sphere_voting_management_system.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import auca.ac.rw.vote_sphere_voting_management_system.entity.Voter;

public interface VoterRepository extends JpaRepository<Voter, UUID> {
}
