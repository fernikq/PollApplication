package pl.fernikq.poll.controller;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.fernikq.poll.data.dto.CreatePollRequest;
import pl.fernikq.poll.data.dto.CreatePollResponse;
import pl.fernikq.poll.data.dto.PollInfoDTO;
import pl.fernikq.poll.service.PollService;

@RestController
@RequestMapping("/poll")
@RequiredArgsConstructor
public class PollController {

    private final PollService pollService;

    @PostMapping("/create")
    public ResponseEntity<@NonNull CreatePollResponse> createPoll(CreatePollRequest request){
        return pollService.createPoll(request);
    }

    @PostMapping("/delete/{pollId}")
    public ResponseEntity<@NonNull Object> deletePoll(@PathVariable Long pollId){
        return pollService.deletePoll(pollId);
    }

    @GetMapping("/info/{pollId}")
    public ResponseEntity<@NonNull PollInfoDTO> getInfoAboutPoll(@PathVariable Long pollId){
        return pollService.getInfoAboutPoll(pollId);
    }
}
