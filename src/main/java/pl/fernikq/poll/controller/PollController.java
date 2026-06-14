package pl.fernikq.poll.controller;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.fernikq.poll.data.dto.*;
import pl.fernikq.poll.data.dto.request.CreatePollOptionRequest;
import pl.fernikq.poll.data.dto.request.CreatePollRequest;
import pl.fernikq.poll.data.dto.response.CreatePollResponse;
import pl.fernikq.poll.data.dto.response.PollVoteResponse;
import pl.fernikq.poll.service.PollOptionService;
import pl.fernikq.poll.service.PollService;
import pl.fernikq.poll.service.PollVoteService;
import pl.fernikq.poll.util.UriUtil;

@RestController
@RequestMapping("/polls")
@RequiredArgsConstructor
public class PollController {

    private final PollService pollService;
    private final PollOptionService pollOptionService;
    private final PollVoteService pollVoteService;

    @PostMapping("")
    public ResponseEntity<@NonNull CreatePollResponse> createPoll(@RequestBody CreatePollRequest request){
        CreatePollResponse createPollResponse = pollService.createPoll(request);
        return ResponseEntity.created(UriUtil.getURI(createPollResponse.id())).body(createPollResponse);
    }

    @DeleteMapping("/{pollId}")
    public ResponseEntity<@NonNull Object> deletePoll(@PathVariable Long pollId) {
        pollService.deletePoll(pollId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(pollId);
    }

    @GetMapping("/{pollId}")
    public ResponseEntity<@NonNull PollInfoDTO> getInfoAboutPoll(@PathVariable Long pollId){
        PollInfoDTO pollInfoDTO = pollService.getInfoAboutPoll(pollId);
        return ResponseEntity.status(HttpStatus.OK).body(pollInfoDTO);
    }

    @PostMapping("/options")
    public ResponseEntity<@NonNull PollOptionDTO> addPollOption(@RequestBody CreatePollOptionRequest createPollOptionRequest){
        PollOptionDTO pollOptionDTO = pollOptionService.addPollOption(createPollOptionRequest);
        return ResponseEntity.status(HttpStatus.CREATED).location(UriUtil.getURI(pollOptionDTO.id())).body(pollOptionDTO);
    }

    @DeleteMapping("/options/{pollOptionId}")
    public ResponseEntity<@NonNull Object> removePollOption(@PathVariable Long pollOptionId){
        pollOptionService.deletePollOption(pollOptionId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(pollOptionId);
    }

    @PostMapping("/votes/{pollOptionId}")
    public ResponseEntity<@NonNull PollVoteResponse> vote(@PathVariable Long pollOptionId){
        PollVoteResponse pollVoteResponse = this.pollVoteService.vote(pollOptionId);
        return ResponseEntity.status(HttpStatus.OK).body(pollVoteResponse);
    }

    //TODO, test
    @GetMapping("/options/{pollOptionId}")
    public ResponseEntity<@NonNull Long> test(@PathVariable Long pollOptionId) {
        Long amount = this.pollVoteService.getAmountOfVotes(pollOptionId);
        return ResponseEntity.status(HttpStatus.OK).body(amount);
    }
}
