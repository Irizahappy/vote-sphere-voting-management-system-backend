
package auca.ac.rw.vote_sphere_voting_management_system.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.*;

@Entity
@Table(name = "election_officer")
public class ElectionOfficer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String firstName;
    private String lastName;
    private String email;

    @ManyToOne
    @JoinColumn(name = "polling_station_id")
    private PollingStation pollingStation;

    @ManyToMany
    @JoinTable(name = "election_officer_poll_type")
    private List<PollType> pollTypes = new ArrayList<>();

    public ElectionOfficer() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public PollingStation getPollingStation() {
        return pollingStation;
    }

    public void setPollingStation(PollingStation pollingStation) {
        this.pollingStation = pollingStation;
    }

    public List<PollType> getPollTypes() {
        return pollTypes;
    }

    public void setPollTypes(List<PollType> pollTypes) {
        this.pollTypes = pollTypes;
    }
}