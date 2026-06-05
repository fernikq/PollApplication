package pl.fernikq.poll.data.dto;

import lombok.Builder;

@Builder
public record CreatePollOptionDTO(

        Long pollId,
        String value
) {}
