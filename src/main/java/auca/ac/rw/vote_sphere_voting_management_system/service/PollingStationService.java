package auca.ac.rw.vote_sphere_voting_management_system.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import auca.ac.rw.vote_sphere_voting_management_system.entity.PollingStation;
import auca.ac.rw.vote_sphere_voting_management_system.repository.PollingStationRepository;

@Service
public class PollingStationService {

    private final PollingStationRepository pollingStationRepository;

    public PollingStationService(PollingStationRepository pollingStationRepository) {
        this.pollingStationRepository = pollingStationRepository;
    }

    public String save(PollingStation pollingStation) {
        pollingStationRepository.save(pollingStation);
        return "Polling station saved successfully";
    }

    public List<PollingStation> getAll() {
        return pollingStationRepository.findAll();
    }

    public Optional<PollingStation> getById(UUID id) {
        return pollingStationRepository.findById(id);
    }

    public String update(UUID id, PollingStation newPollingStation) {
        Optional<PollingStation> optionalPollingStation = pollingStationRepository.findById(id);
        if (optionalPollingStation.isEmpty()) {
            return "The polling station with that id does not exist";
        }
        PollingStation pollingStation = optionalPollingStation.get();
        pollingStation.setStationNumber(newPollingStation.getStationNumber());
        pollingStation.setName(newPollingStation.getName());
        pollingStationRepository.save(pollingStation);
        return "Polling station updated successfully";
    }

    public String delete(UUID id) {
        if (!pollingStationRepository.existsById(id)) {
            return "The polling station with that id does not exist";
        }
        pollingStationRepository.deleteById(id);
        return "Polling station deleted successfully";
    }
}
