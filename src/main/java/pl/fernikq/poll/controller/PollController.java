package pl.fernikq.poll.controller;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.fernikq.poll.data.dto.CreatePollRequest;
import pl.fernikq.poll.data.dto.CreatePollResponse;
import pl.fernikq.poll.service.PollService;

@RestController
@RequestMapping("/poll")
@RequiredArgsConstructor
public class PollController {

    private final PollService pollService;

    @PostMapping
    public ResponseEntity<@NonNull CreatePollResponse> createPoll(CreatePollRequest request){
        return pollService.createPoll(request);
    }
}
