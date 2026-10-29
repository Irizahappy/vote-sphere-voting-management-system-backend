package auca.ac.rw.vote_sphere_voting_management_system.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "poll_type")
public class PollType {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "pollTypes")
    private List<ElectionOfficer> electionOfficers = new ArrayList<>();

    public PollType() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ElectionOfficer> getElectionOfficers() {
        return electionOfficers;
    }

    public void setElectionOfficers(List<ElectionOfficer> electionOfficers) {
        this.electionOfficers = electionOfficers;
    }
}