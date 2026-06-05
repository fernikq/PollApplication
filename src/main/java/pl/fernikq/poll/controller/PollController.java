package pl.fernikq.poll.controller;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.fernikq.poll.data.dto.*;
import pl.fernikq.poll.service.PollService;

@RestController
@RequestMapping("/poll")
@RequiredArgsConstructor
public class PollController {

    private final PollService pollService;

    @PostMapping("/create")
    public ResponseEntity<@NonNull CreatePollResponse> createPoll(@RequestBody CreatePollRequest request){
        return pollService.createPoll(request);
    }

    @DeleteMapping("/delete/{pollId}")
    public ResponseEntity<@NonNull Object> deletePoll(@PathVariable Long pollId){
        return pollService.deletePoll(pollId);
    }

    @GetMapping("/info/{pollId}")
    public ResponseEntity<@NonNull PollInfoDTO> getInfoAboutPoll(@PathVariable Long pollId){
        return pollService.getInfoAboutPoll(pollId);
    }

    @PostMapping("/addOption")
    public ResponseEntity<@NonNull PollOptionDTO> addPollOption(@RequestBody CreatePollOptionDTO createPollOptionDTO){
        return pollService.addPollOption(createPollOptionDTO);
    }

    @DeleteMapping("/deleteOption/{pollOptionId}")
    public ResponseEntity<@NonNull Object> removePollOption(@PathVariable Long pollOptionId){
        return pollService.deletePollOption(pollOptionId);
    }
}
