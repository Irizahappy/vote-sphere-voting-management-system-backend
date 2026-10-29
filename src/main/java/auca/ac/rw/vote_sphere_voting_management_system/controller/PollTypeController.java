package auca.ac.rw.vote_sphere_voting_management_system.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import auca.ac.rw.vote_sphere_voting_management_system.entity.PollType;
import auca.ac.rw.vote_sphere_voting_management_system.service.PollTypeService;

@RestController
@RequestMapping("/api/poll-types")
public class PollTypeController {

    private final PollTypeService pollTypeService;

    public PollTypeController(PollTypeService pollTypeService) {
        this.pollTypeService = pollTypeService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody PollType pollType) {
        String message = pollTypeService.save(pollType);
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    @GetMapping("/all")
    public List<PollType> getAll() {
        return pollTypeService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable UUID id) {
        Optional<PollType> pollType = pollTypeService.getById(id);
        if (pollType.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The poll type with that id does not exist");
        }
        return ResponseEntity.status(HttpStatus.OK).body(pollType.get());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable UUID id, @RequestBody PollType pollType) {
        String message = pollTypeService.update(id, pollType);
        if (message.equals("Poll type updated successfully")) {
            return ResponseEntity.status(HttpStatus.OK).body(message);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        String message = pollTypeService.delete(id);
        if (message.equals("Poll type deleted successfully")) {
            return ResponseEntity.status(HttpStatus.OK).body(message);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }
}
