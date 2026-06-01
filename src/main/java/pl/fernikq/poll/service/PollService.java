package pl.fernikq.poll.service;

import jakarta.transaction.Transactional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pl.fernikq.poll.data.Poll;
import pl.fernikq.poll.data.PollOption;
import pl.fernikq.poll.data.dto.CreatePollRequest;
import pl.fernikq.poll.data.dto.CreatePollResponse;
import pl.fernikq.poll.data.dto.PollInfoDTO;
import pl.fernikq.poll.data.mapper.PollOptionMapper;
import pl.fernikq.poll.data.repository.PollOptionRepository;
import pl.fernikq.poll.data.repository.PollRepository;
import pl.fernikq.poll.data.repository.PollVoteRepository;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PollService {

    private final PollRepository repository;
    private final PollVoteRepository pollVoteRepository;
    private final PollOptionRepository pollOptionRepository;
    private final PollOptionMapper pollOptionMapper;

    @Transactional
    public ResponseEntity<@NonNull CreatePollResponse> createPoll(CreatePollRequest request){
        Poll poll = Poll.builder().name(request.name()).createDate(LocalDateTime.now())
                .closeDate(request.closeDate()).build();
        List<PollOption> pollOptions = new ArrayList<>();
        request.options().forEach(string -> {
            PollOption pollOption = new PollOption();
            pollOption.setValue(string);
            pollOption.setPoll(poll);
            pollOptions.add(pollOption);
        });
        poll.setOptions(pollOptions);
        this.repository.save(poll);
        CreatePollResponse createPollResponse = CreatePollResponse
                .builder().id(poll.getId()).name(poll.getName()).createDate(poll.getCreateDate())
                .closeDate(poll.getCloseDate()).options(pollOptionMapper.toDTOList(poll.getOptions())).build();
        return ResponseEntity.status(HttpStatus.CREATED).location(this.getURi(createPollResponse.id())).body(createPollResponse);
    }

    public ResponseEntity<@NonNull Object> deletePoll(Long pollId){
        Optional<Poll> poll = this.repository.findById(pollId);
        if(poll.isPresent()){
            this.repository.delete(poll.get());
            return ResponseEntity.status(HttpStatus.OK).body(pollId);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(pollId);//TODO
    }

    public ResponseEntity<@NonNull PollInfoDTO> getInfoAboutPoll(Long pollId){
        Optional<Poll> pollOptional = this.repository.findById(pollId);
        if(pollOptional.isPresent()){
            Poll poll = pollOptional.get();
            PollInfoDTO pollInfoDTO = PollInfoDTO.builder().name(poll.getName())
                    .createDate(poll.getCreateDate()).closeDate(poll.getCloseDate())
                    .options(this.pollOptionMapper.toDTOList(poll.getOptions())).build();
            return ResponseEntity.status(HttpStatus.OK).body(pollInfoDTO);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();//TODO
    }

    private URI getURi(Long id){
        return ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(id).toUri();
    }
}
