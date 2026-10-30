package auca.ac.rw.vote_sphere_voting_management_system.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import auca.ac.rw.vote_sphere_voting_management_system.entity.ElectionOfficer;

public interface ElectionOfficerRepository extends JpaRepository<ElectionOfficer, UUID> {

    @Query("""
        SELECT DISTINCT p FROM ElectionOfficer p
        JOIN p.pollTypes c
        WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :name, '%'))
        """)
    List<ElectionOfficer> searchByPollTypeName(@Param("name") String name);
}