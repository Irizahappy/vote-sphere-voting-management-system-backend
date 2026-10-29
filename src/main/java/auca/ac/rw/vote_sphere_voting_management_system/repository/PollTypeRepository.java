package auca.ac.rw.vote_sphere_voting_management_system.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import auca.ac.rw.vote_sphere_voting_management_system.entity.PollType;

public interface PollTypeRepository extends JpaRepository<PollType, UUID> {
}
