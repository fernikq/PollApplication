package pl.fernikq.poll.data.dto;

import lombok.Builder;

@Builder
public record PollOptionDTO(

        Long id,
        String value
) {}
