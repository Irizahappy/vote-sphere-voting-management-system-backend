package auca.ac.rw.vote_sphere_voting_management_system.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import auca.ac.rw.vote_sphere_voting_management_system.entity.VoteEntry;
import auca.ac.rw.vote_sphere_voting_management_system.service.VoteEntryService;

@RestController
@RequestMapping("/api/vote-entries")
public class VoteEntryController {

    private final VoteEntryService voteEntryService;

    public VoteEntryController(VoteEntryService voteEntryService) {
        this.voteEntryService = voteEntryService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestParam UUID ballotId, @RequestBody VoteEntry voteEntry) {
        String message = voteEntryService.save(ballotId, voteEntry);
        if (message.equals("Vote entry saved successfully")) {
            return ResponseEntity.status(HttpStatus.CREATED).body(message);
        }
        if (message.equals("The ballot with that id does not exist")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }

    @GetMapping("/voter/{voterNumber}")
    public List<VoteEntry> getByVoterNumber(@PathVariable String voterNumber) {
        return voteEntryService.getByVoterNumber(voterNumber);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String message = voteEntryService.delete(id);
        if (message.equals("Vote entry deleted successfully")) {
            return ResponseEntity.status(HttpStatus.OK).body(message);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }
}
