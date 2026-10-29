package auca.ac.rw.vote_sphere_voting_management_system.entity;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.*;

@Entity
@Table(name = "ballot")
public class Ballot {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private LocalDate pollDate;

    @Enumerated(EnumType.STRING)
    private BallotStatus status;

    private String pollQuestion;

    @ManyToOne
    @JoinColumn(name = "voter_id")
    private Voter voter;

    @ManyToOne
    @JoinColumn(name = "election_officer_id")
    private ElectionOfficer electionOfficer;

    public Ballot() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDate getPollDate() {
        return pollDate;
    }

    public void setPollDate(LocalDate pollDate) {
        this.pollDate = pollDate;
    }

    public BallotStatus getStatus() {
        return status;
    }

    public void setStatus(BallotStatus status) {
        this.status = status;
    }

    public String getPollQuestion() {
        return pollQuestion;
    }

    public void setPollQuestion(String pollQuestion) {
        this.pollQuestion = pollQuestion;
    }

    public Voter getVoter() {
        return voter;
    }

    public void setVoter(Voter voter) {
        this.voter = voter;
    }

    public ElectionOfficer getElectionOfficer() {
        return electionOfficer;
    }

    public void setElectionOfficer(ElectionOfficer electionOfficer) {
        this.electionOfficer = electionOfficer;
    }
}