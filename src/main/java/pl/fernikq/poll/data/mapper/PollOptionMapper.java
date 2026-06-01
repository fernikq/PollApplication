package pl.fernikq.poll.data.mapper;

import org.springframework.stereotype.Component;
import pl.fernikq.poll.data.PollOption;
import pl.fernikq.poll.data.dto.PollOptionDTO;

import java.util.List;

@Component
public class PollOptionMapper {

    public PollOptionDTO toDTO(PollOption pollOption){
        return PollOptionDTO.builder().id(pollOption.getId()).value(pollOption.getValue()).build();
    }
    public List<PollOptionDTO> toDTOList(List<PollOption> options){
        return options.stream().map(pollOption -> PollOptionDTO.builder().id(pollOption.getId()).value(pollOption.getValue()).build()).toList();
    }
}
