package auca.ac.rw.vote_sphere_voting_management_system.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import auca.ac.rw.vote_sphere_voting_management_system.entity.Ballot;
import auca.ac.rw.vote_sphere_voting_management_system.entity.BallotStatus;
import auca.ac.rw.vote_sphere_voting_management_system.service.BallotService;

@RestController
@RequestMapping("/api/ballots")
public class BallotController {

    private final BallotService ballotService;

    public BallotController(BallotService ballotService) {
        this.ballotService = ballotService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Ballot ballot) {
        String message = ballotService.save(ballot);
        if (message.equals("Ballot saved successfully")) {
            return ResponseEntity.status(HttpStatus.CREATED).body(message);
        }
        if (message.equals("Ballot date cannot be in the past")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
    }

    @GetMapping("/all")
    public List<Ballot> getAll() {
        return ballotService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable UUID id) {
        Optional<Ballot> ballot = ballotService.getById(id);
        if (ballot.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The ballot with that id does not exist");
        }
        return ResponseEntity.status(HttpStatus.OK).body(ballot.get());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody Ballot ballot) {
        String message = ballotService.update(id, ballot);
        if (message.equals("Ballot updated successfully")) {
            return ResponseEntity.status(HttpStatus.OK).body(message);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String message = ballotService.delete(id);
        if (message.equals("Ballot deleted successfully")) {
            return ResponseEntity.status(HttpStatus.OK).body(message);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }

    @GetMapping("/by-status")
    public List<Ballot> getByStatus(@RequestParam BallotStatus status) {
        return ballotService.getByStatus(status);
    }

    @GetMapping("/between")
    public List<Ballot> getBetween(@RequestParam String start, @RequestParam String end) {
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);
        return ballotService.getBetween(startDate, endDate);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<String> changeStatus(@PathVariable UUID id, @RequestParam BallotStatus status) {
        String message = ballotService.changeStatus(id, status);
        if (message.equals("Ballot status updated successfully")) {
            return ResponseEntity.status(HttpStatus.OK).body(message);
        }
        if (message.equals("The ballot with that id does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }
}