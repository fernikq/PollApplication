package pl.fernikq.poll.data.dto.request;

import lombok.Builder;

@Builder
public record CreatePollOptionRequest(

        Long pollId,
        String value
) {}
