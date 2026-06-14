package pl.fernikq.poll.service;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.fernikq.poll.data.PollOption;
import pl.fernikq.poll.data.PollVote;
import pl.fernikq.poll.data.dto.response.PollVoteResponse;
import pl.fernikq.poll.data.repository.PollVoteRepository;

@Service
@RequiredArgsConstructor
public class PollVoteService {

    private final PollVoteRepository repository;
    private final PollOptionService pollOptionService;

    @Transactional
    public PollVoteResponse vote(Long pollOptionId){
        PollOption pollOption = pollOptionService.getPollOptionById(pollOptionId);
        PollVote pollVote = PollVote.builder()
                .option(pollOption).build();
        Long pollId = pollOption.getPoll().getId();
        this.repository.save(pollVote);
        return new PollVoteResponse(pollVote.getId() ,pollId, pollOptionId);
    }

    public Long getAmountOfVotes(Long pollOptionId){
        return this.repository.countByOptionId(pollOptionId);
    }
}
