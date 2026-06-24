package pl.fernikq.poll.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.fernikq.poll.data.Poll;
import pl.fernikq.poll.data.PollOption;
import pl.fernikq.poll.data.dto.*;
import pl.fernikq.poll.data.dto.request.CreatePollRequest;
import pl.fernikq.poll.data.dto.response.CreatePollResponse;
import pl.fernikq.poll.data.mapper.PollOptionMapper;
import pl.fernikq.poll.data.repository.PollOptionRepository;
import pl.fernikq.poll.data.repository.PollRepository;
import pl.fernikq.poll.data.repository.PollVoteRepository;
import pl.fernikq.poll.exception.PollNotFoundException;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class PollService {

    private final PollRepository repository;
    private final PollVoteRepository pollVoteRepository;
    private final PollOptionRepository pollOptionRepository;
    private final PollOptionMapper pollOptionMapper;

    public Poll getPollById(Long pollId){
        return this.repository.findById(pollId).orElseThrow(() -> new RuntimeException("Not found"));
    }

    @Transactional
    public CreatePollResponse createPoll(CreatePollRequest request){
        Poll poll = Poll.builder().name(request.name()).createDate(LocalDateTime.now())
                .closeDate(request.closeDate()).options(new ArrayList<>()).build();
        this.repository.save(poll);
        request.options().forEach(string -> {
            PollOption pollOption = PollOption.builder()
                    .value(string).poll(poll).votesCount(0).build();
            poll.getOptions().add(pollOption);
            this.pollOptionRepository.save(pollOption);
        });
        return CreatePollResponse.builder()
                .id(poll.getId()).name(poll.getName()).createDate(poll.getCreateDate())
                .closeDate(poll.getCloseDate()).options(pollOptionMapper.toDTOList(poll.getOptions())).build();
    }

    @Transactional
    public void deletePoll(Long pollId) {
        Poll poll = this.repository.findById(pollId).orElseThrow(() -> new PollNotFoundException("Poll not found", pollId));
        this.pollOptionRepository.deleteOptionsByPollId(pollId);
        this.repository.delete(poll);
    }

    @Transactional
    public PollInfoDTO getInfoAboutPoll(Long pollId){
        Poll poll = this.repository.findById(pollId).orElseThrow(() -> new PollNotFoundException("Poll not found", pollId));
        return PollInfoDTO.builder().name(poll.getName())
                .createDate(poll.getCreateDate()).closeDate(poll.getCloseDate())
                .options(this.pollOptionMapper.toDTOList(poll.getOptions())).build();
    }
}
