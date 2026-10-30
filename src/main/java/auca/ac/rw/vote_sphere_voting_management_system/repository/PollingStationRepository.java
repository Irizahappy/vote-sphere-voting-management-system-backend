package auca.ac.rw.vote_sphere_voting_management_system.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import auca.ac.rw.vote_sphere_voting_management_system.entity.PollingStation;

public interface PollingStationRepository extends JpaRepository<PollingStation, UUID> {

    @Query("""
        SELECT l FROM PollingStation l
        WHERE NOT EXISTS (SELECT p FROM ElectionOfficer p WHERE p.pollingStation = l)
        ORDER BY l.stationNumber
        """)
    List<PollingStation> freeStations();
}
