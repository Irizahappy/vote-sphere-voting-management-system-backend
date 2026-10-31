package auca.ac.rw.vote_sphere_voting_management_system.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import auca.ac.rw.vote_sphere_voting_management_system.entity.ElectionOfficer;
import auca.ac.rw.vote_sphere_voting_management_system.service.ElectionOfficerService;

@RestController
@RequestMapping("/api/election-officers")
public class ElectionOfficerController {

    private final ElectionOfficerService electionOfficerService;

    public ElectionOfficerController(ElectionOfficerService electionOfficerService) {
        this.electionOfficerService = electionOfficerService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody ElectionOfficer electionOfficer) {
        String message = electionOfficerService.save(electionOfficer);
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    @GetMapping("/all")
    public List<ElectionOfficer> getAll() {
        return electionOfficerService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable UUID id) {
        Optional<ElectionOfficer> electionOfficer = electionOfficerService.getById(id);
        if (electionOfficer.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The election officer with that id does not exist");
        }
        return ResponseEntity.status(HttpStatus.OK).body(electionOfficer.get());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody ElectionOfficer electionOfficer) {
        String message = electionOfficerService.update(id, electionOfficer);
        if (message.equals("Election officer updated successfully")) {
            return ResponseEntity.status(HttpStatus.OK).body(message);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String message = electionOfficerService.delete(id);
        if (message.equals("Election officer deleted successfully")) {
            return ResponseEntity.status(HttpStatus.OK).body(message);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }

    @GetMapping("/by-poll-type")
    public List<ElectionOfficer> getByPollType(@RequestParam String name) {
        return electionOfficerService.getByPollType(name);
    }

    @PutMapping("/{electionOfficerId}/poll-types/{pollTypeId}")
    public ResponseEntity<String> addPollType(@PathVariable UUID electionOfficerId, @PathVariable UUID pollTypeId) {
        String message = electionOfficerService.addPollType(electionOfficerId, pollTypeId);
        if (message.equals("Poll type added to the election officer")) {
            return ResponseEntity.status(HttpStatus.OK).body(message);
        }
        if (message.equals("The election officer already has that poll type")) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }

    @PatchMapping("/{electionOfficerId}/polling-station")
    public ResponseEntity<String> changePollingStation(@PathVariable UUID electionOfficerId,
            @RequestParam int stationNumber) {
        String message = electionOfficerService.changePollingStation(electionOfficerId, stationNumber);
        if (message.equals("Polling station changed successfully")) {
            return ResponseEntity.status(HttpStatus.OK).body(message);
        }
        if (message.equals("That polling station is taken by another election officer")) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }
}