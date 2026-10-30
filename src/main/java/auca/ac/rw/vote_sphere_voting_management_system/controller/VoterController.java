
package auca.ac.rw.vote_sphere_voting_management_system.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import auca.ac.rw.vote_sphere_voting_management_system.entity.Voter;
import auca.ac.rw.vote_sphere_voting_management_system.service.VoterService;

@RestController
@RequestMapping("/api/voters")
public class VoterController {

    private final VoterService voterService;

    public VoterController(VoterService voterService) {
        this.voterService = voterService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody Voter voter) {
        String message = voterService.save(voter);
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    @GetMapping("/all")
    public List<Voter> getAll() {
        return voterService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable UUID id) {
        Optional<Voter> voter = voterService.getById(id);
        if (voter.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The voter with that id does not exist");
        }
        return ResponseEntity.status(HttpStatus.OK).body(voter.get());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody Voter voter) {
        String message = voterService.update(id, voter);
        if (message.equals("Voter updated successfully")) {
            return ResponseEntity.status(HttpStatus.OK).body(message);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String message = voterService.delete(id);
        if (message.equals("Voter deleted successfully")) {
            return ResponseEntity.status(HttpStatus.OK).body(message);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }

        @GetMapping("/by-last-name")
    public List<Voter> getByLastName(@RequestParam String lastName) {
        return voterService.getByLastName(lastName);
    }

        @GetMapping("/of-election-officer/{electionOfficerId}")
    public ResponseEntity<Object> getByElectionOfficer(@PathVariable UUID electionOfficerId) {
        Optional<List<Voter>> voters = voterService.getByElectionOfficer(electionOfficerId);
        if (voters.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The election officer with that id does not exist");
        }
        return ResponseEntity.status(HttpStatus.OK).body(voters.get());
    }
}