package auca.ac.rw.vote_sphere_voting_management_system.entity;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.*;

@Entity
@Table(name = "vote_entry")
public class VoteEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String optionChosen;
    private String remark;
    private int weight;
    private LocalDate countedDate;

    @ManyToOne
    @JoinColumn(name = "ballot_id")
    private Ballot ballot;

    public VoteEntry() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getOptionChosen() {
        return optionChosen;
    }

    public void setOptionChosen(String optionChosen) {
        this.optionChosen = optionChosen;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public LocalDate getCountedDate() {
        return countedDate;
    }

    public void setCountedDate(LocalDate countedDate) {
        this.countedDate = countedDate;
    }

    public Ballot getBallot() {
        return ballot;
    }

    public void setBallot(Ballot ballot) {
        this.ballot = ballot;
    }
}