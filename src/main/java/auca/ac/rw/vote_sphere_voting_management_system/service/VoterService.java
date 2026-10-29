package auca.ac.rw.vote_sphere_voting_management_system.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import auca.ac.rw.vote_sphere_voting_management_system.entity.Voter;
import auca.ac.rw.vote_sphere_voting_management_system.repository.VoterRepository;

@Service
public class VoterService {

    private final VoterRepository voterRepository;

    public VoterService(VoterRepository voterRepository) {
        this.voterRepository = voterRepository;
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
}
