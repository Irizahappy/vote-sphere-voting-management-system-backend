package auca.ac.rw.vote_sphere_voting_management_system.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import auca.ac.rw.vote_sphere_voting_management_system.entity.PollingStation;

public interface PollingStationRepository extends JpaRepository<PollingStation, UUID> {
}
