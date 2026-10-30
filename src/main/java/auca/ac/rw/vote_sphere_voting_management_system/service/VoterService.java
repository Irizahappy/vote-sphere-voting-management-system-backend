package auca.ac.rw.vote_sphere_voting_management_system.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import auca.ac.rw.vote_sphere_voting_management_system.entity.Voter;
import auca.ac.rw.vote_sphere_voting_management_system.repository.ElectionOfficerRepository;
import auca.ac.rw.vote_sphere_voting_management_system.repository.VoterRepository;

@Service
public class VoterService {

    private final VoterRepository voterRepository;
    private final ElectionOfficerRepository electionOfficerRepository;

    public VoterService(VoterRepository voterRepository, ElectionOfficerRepository electionOfficerRepository) {
        this.voterRepository = voterRepository;
        this.electionOfficerRepository = electionOfficerRepository;
    }

    public String save(Voter voter) {
        voterRepository.save(voter);
        return "Voter saved successfully";
    }

    public List<Voter> getAll() {
        return voterRepository.findAll();
    }

    public Optional<Voter> getById(UUID id) {
        return voterRepository.findById(id);
    }

    public String update(UUID id, Voter newVoter) {
        Optional<Voter> optionalVoter = voterRepository.findById(id);
        if (optionalVoter.isEmpty()) {
            return "The voter with that id does not exist";
        }
        Voter voter = optionalVoter.get();
        voter.setFirstName(newVoter.getFirstName());
        voter.setLastName(newVoter.getLastName());
        voter.setVoterNumber(newVoter.getVoterNumber());
        voter.setEmail(newVoter.getEmail());
        voterRepository.save(voter);
        return "Voter updated successfully";
    }

    public String delete(UUID id) {
        if (!voterRepository.existsById(id)) {
            return "The voter with that id does not exist";
        }
        voterRepository.deleteById(id);
        return "Voter deleted successfully";
    }

    public List<Voter> getByLastName(String lastName) {
        return voterRepository.findByLastNameIgnoreCaseOrderByFirstNameAsc(lastName);
    }

    public Optional<List<Voter>> getByElectionOfficer(UUID electionOfficerId) {
        if (!electionOfficerRepository.existsById(electionOfficerId)) {
            return Optional.empty();
        }
        return Optional.of(voterRepository.votersOfElectionOfficer(electionOfficerId));
    }
}