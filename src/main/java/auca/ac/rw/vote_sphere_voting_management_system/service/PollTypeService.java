package auca.ac.rw.vote_sphere_voting_management_system.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import auca.ac.rw.vote_sphere_voting_management_system.entity.PollType;
import auca.ac.rw.vote_sphere_voting_management_system.repository.PollTypeRepository;

@Service
public class PollTypeService {

    private final PollTypeRepository pollTypeRepository;

    public PollTypeService(PollTypeRepository pollTypeRepository) {
        this.pollTypeRepository = pollTypeRepository;
    }

    public String save(PollType pollType) {
        pollTypeRepository.save(pollType);
        return "Poll type saved successfully";
    }

    public List<PollType> getAll() {
        return pollTypeRepository.findAll();
    }

    public Optional<PollType> getById(UUID id) {
        return pollTypeRepository.findById(id);
    }

    public String update(UUID id, PollType newPollType) {
        Optional<PollType> optionalPollType = pollTypeRepository.findById(id);
        if (optionalPollType.isEmpty()) {
            return "The poll type with that id does not exist";
        }
        PollType pollType = optionalPollType.get();
        pollType.setName(newPollType.getName());
        pollTypeRepository.save(pollType);
        return "Poll type updated successfully";
    }

    public String delete(UUID id) {
        if (!pollTypeRepository.existsById(id)) {
            return "The poll type with that id does not exist";
        }
        pollTypeRepository.deleteById(id);
        return "Poll type deleted successfully";
    }

        public List<PollType> getUnused() {
        return pollTypeRepository.unusedPollTypes();
    }
}
