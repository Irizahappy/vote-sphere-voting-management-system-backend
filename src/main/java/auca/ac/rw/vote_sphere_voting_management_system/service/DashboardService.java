package auca.ac.rw.vote_sphere_voting_management_system.service;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import auca.ac.rw.vote_sphere_voting_management_system.entity.BallotStatus;
import auca.ac.rw.vote_sphere_voting_management_system.repository.BallotRepository;
import auca.ac.rw.vote_sphere_voting_management_system.repository.ElectionOfficerRepository;
import auca.ac.rw.vote_sphere_voting_management_system.repository.PollingStationRepository;
import auca.ac.rw.vote_sphere_voting_management_system.repository.VoteEntryRepository;
import auca.ac.rw.vote_sphere_voting_management_system.repository.VoterRepository;

@Service
public class DashboardService {

    private final VoterRepository voterRepository;
    private final ElectionOfficerRepository electionOfficerRepository;
    private final PollingStationRepository pollingStationRepository;
    private final BallotRepository ballotRepository;
    private final VoteEntryRepository voteEntryRepository;

    public DashboardService(VoterRepository voterRepository,
            ElectionOfficerRepository electionOfficerRepository,
            PollingStationRepository pollingStationRepository,
            BallotRepository ballotRepository,
            VoteEntryRepository voteEntryRepository) {
        this.voterRepository = voterRepository;
        this.electionOfficerRepository = electionOfficerRepository;
        this.pollingStationRepository = pollingStationRepository;
        this.ballotRepository = ballotRepository;
        this.voteEntryRepository = voteEntryRepository;
    }

    public Map<String, Long> getSummary() {
        Map<String, Long> summary = new LinkedHashMap<>();
        summary.put("voters", voterRepository.count());
        summary.put("electionOfficers", electionOfficerRepository.count());
        summary.put("freePollingStations", (long) pollingStationRepository.freeStations().size());
        summary.put("ballotsToday", ballotRepository.countByPollDate(LocalDate.now()));
        summary.put("issued", ballotRepository.countByStatus(BallotStatus.ISSUED));
        summary.put("voteEntries", voteEntryRepository.count());
        return summary;
    }
}
