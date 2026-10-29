package auca.ac.rw.vote_sphere_voting_management_system.entity;

import java.util.UUID;

import jakarta.persistence.*;

@Entity
@Table(name = "polling_station")
public class PollingStation {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private int stationNumber;
    private String name;

    public PollingStation() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getStationNumber() {
        return stationNumber;
    }

    public void setStationNumber(int stationNumber) {
        this.stationNumber = stationNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}