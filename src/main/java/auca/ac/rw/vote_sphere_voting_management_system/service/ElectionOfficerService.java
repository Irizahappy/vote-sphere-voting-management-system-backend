package auca.ac.rw.vote_sphere_voting_management_system.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import auca.ac.rw.vote_sphere_voting_management_system.entity.ElectionOfficer;
import auca.ac.rw.vote_sphere_voting_management_system.repository.ElectionOfficerRepository;

@Service
public class ElectionOfficerService {

    private final ElectionOfficerRepository electionOfficerRepository;

    public ElectionOfficerService(ElectionOfficerRepository electionOfficerRepository) {
        this.electionOfficerRepository = electionOfficerRepository;
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
}
