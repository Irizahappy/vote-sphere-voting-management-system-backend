package auca.ac.rw.vote_sphere_voting_management_system.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import auca.ac.rw.vote_sphere_voting_management_system.entity.PollingStation;
import auca.ac.rw.vote_sphere_voting_management_system.service.PollingStationService;

@RestController
@RequestMapping("/api/polling-stations")
public class PollingStationController {

    private final PollingStationService pollingStationService;

    public PollingStationController(PollingStationService pollingStationService) {
        this.pollingStationService = pollingStationService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody PollingStation pollingStation) {
        String message = pollingStationService.save(pollingStation);
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    @GetMapping("/all")
    public List<PollingStation> getAll() {
        return pollingStationService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable UUID id) {
        Optional<PollingStation> pollingStation = pollingStationService.getById(id);
        if (pollingStation.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The polling station with that id does not exist");
        }
        return ResponseEntity.status(HttpStatus.OK).body(pollingStation.get());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody PollingStation pollingStation) {
        String message = pollingStationService.update(id, pollingStation);
        if (message.equals("Polling station updated successfully")) {
            return ResponseEntity.status(HttpStatus.OK).body(message);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String message = pollingStationService.delete(id);
        if (message.equals("Polling station deleted successfully")) {
            return ResponseEntity.status(HttpStatus.OK).body(message);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }

        @GetMapping("/free")
    public List<PollingStation> getFree() {
        return pollingStationService.getFree();
    }
}
