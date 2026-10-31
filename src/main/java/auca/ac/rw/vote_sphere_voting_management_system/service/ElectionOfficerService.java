package auca.ac.rw.vote_sphere_voting_management_system.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import auca.ac.rw.vote_sphere_voting_management_system.entity.ElectionOfficer;
import auca.ac.rw.vote_sphere_voting_management_system.entity.PollType;
import auca.ac.rw.vote_sphere_voting_management_system.entity.PollingStation;
import auca.ac.rw.vote_sphere_voting_management_system.repository.ElectionOfficerRepository;
import auca.ac.rw.vote_sphere_voting_management_system.repository.PollTypeRepository;
import auca.ac.rw.vote_sphere_voting_management_system.repository.PollingStationRepository;

@Service
public class ElectionOfficerService {

    private final ElectionOfficerRepository electionOfficerRepository;
    private final PollTypeRepository pollTypeRepository;
    private final PollingStationRepository pollingStationRepository;

    public ElectionOfficerService(ElectionOfficerRepository electionOfficerRepository,
            PollTypeRepository pollTypeRepository,
            PollingStationRepository pollingStationRepository) {
        this.electionOfficerRepository = electionOfficerRepository;
        this.pollTypeRepository = pollTypeRepository;
        this.pollingStationRepository = pollingStationRepository;
    }

    public String save(ElectionOfficer electionOfficer) {
        electionOfficerRepository.save(electionOfficer);
        return "Election officer saved successfully";
    }

    public List<ElectionOfficer> getAll() {
        return electionOfficerRepository.findAll();
    }

    public Optional<ElectionOfficer> getById(UUID id) {
        return electionOfficerRepository.findById(id);
    }

    public String update(UUID id, ElectionOfficer newElectionOfficer) {
        Optional<ElectionOfficer> optionalElectionOfficer = electionOfficerRepository.findById(id);
        if (optionalElectionOfficer.isEmpty()) {
            return "The election officer with that id does not exist";
        }
        ElectionOfficer electionOfficer = optionalElectionOfficer.get();
        electionOfficer.setFirstName(newElectionOfficer.getFirstName());
        electionOfficer.setLastName(newElectionOfficer.getLastName());
        electionOfficer.setEmail(newElectionOfficer.getEmail());
        electionOfficer.setPollingStation(newElectionOfficer.getPollingStation());
        electionOfficerRepository.save(electionOfficer);
        return "Election officer updated successfully";
    }

    public String delete(UUID id) {
        if (!electionOfficerRepository.existsById(id)) {
            return "The election officer with that id does not exist";
        }
        electionOfficerRepository.deleteById(id);
        return "Election officer deleted successfully";
    }

    public List<ElectionOfficer> getByPollType(String name) {
        return electionOfficerRepository.searchByPollTypeName(name);
    }

    public String addPollType(UUID electionOfficerId, UUID pollTypeId) {
        Optional<ElectionOfficer> optionalElectionOfficer = electionOfficerRepository.findById(electionOfficerId);
        if (optionalElectionOfficer.isEmpty()) {
            return "The election officer with that id does not exist";
        }
        Optional<PollType> optionalPollType = pollTypeRepository.findById(pollTypeId);
        if (optionalPollType.isEmpty()) {
            return "That poll type does not exist";
        }
        ElectionOfficer electionOfficer = optionalElectionOfficer.get();
        PollType pollType = optionalPollType.get();

        if (electionOfficer.getPollTypes().contains(pollType)) {
            return "The election officer already has that poll type";
        }
        electionOfficer.getPollTypes().add(pollType);
        electionOfficerRepository.save(electionOfficer);
        return "Poll type added to the election officer";
    }

    public String changePollingStation(UUID electionOfficerId, int stationNumber) {
        Optional<ElectionOfficer> optionalElectionOfficer = electionOfficerRepository.findById(electionOfficerId);
        if (optionalElectionOfficer.isEmpty()) {
            return "The election officer with that id does not exist";
        }
        Optional<PollingStation> optionalPollingStation = pollingStationRepository.findByStationNumber(stationNumber);
        if (optionalPollingStation.isEmpty()) {
            return "The polling station with that stationNumber does not exist";
        }
        ElectionOfficer electionOfficer = optionalElectionOfficer.get();
        PollingStation pollingStation = optionalPollingStation.get();

        if (electionOfficerRepository.existsByPollingStation(pollingStation)) {
            return "That polling station is taken by another election officer";
        }
        electionOfficer.setPollingStation(pollingStation);
        electionOfficerRepository.save(electionOfficer);
        return "Polling station changed successfully";
    }
}