package pl.fernikq.poll.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.fernikq.poll.data.Poll;
import pl.fernikq.poll.data.PollOption;
import pl.fernikq.poll.data.dto.PollOptionDTO;
import pl.fernikq.poll.data.dto.request.CreatePollOptionRequest;
import pl.fernikq.poll.data.repository.PollOptionRepository;
import pl.fernikq.poll.exception.PollOptionNotFoundException;

@Service
@RequiredArgsConstructor
public class PollOptionService {

    private final PollOptionRepository repository;
    private final PollService pollService;

    public PollOption getPollOptionById(Long pollOptionId){
        return this.repository.findById(pollOptionId).orElseThrow(() -> new PollOptionNotFoundException("Not found", pollOptionId));
    }

    @Transactional
    public PollOptionDTO addPollOption(CreatePollOptionRequest createPollOptionRequest){
        Poll poll = this.pollService.getPollById(createPollOptionRequest.pollId());
        PollOption pollOption = PollOption.builder()
                            .value(createPollOptionRequest.value()).poll(poll).build();
        this.repository.save(pollOption);
        poll.getOptions().add(pollOption);
        return PollOptionDTO.builder().id(pollOption.getId()).value(pollOption.getValue()).build();
    }

    @Transactional
    public void deletePollOption(Long pollOptionId){
        PollOption pollOption = this.repository.findById(pollOptionId)
                .orElseThrow(() -> new PollOptionNotFoundException("Not found", pollOptionId));
        Poll poll = pollOption.getPoll();
        poll.getOptions().remove(pollOption);
        this.repository.delete(pollOption);
    }
}
