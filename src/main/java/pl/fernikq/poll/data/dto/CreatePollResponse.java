package pl.fernikq.poll.data.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record CreatePollResponse(

        Long id,
        String name,
        LocalDateTime createDate,
        LocalDateTime closeDate,
        List<PollOptionDTO> options
) {}
