package pl.fernikq.poll.data.dto.response;

public record PollVoteResponse(

        Long pollVoteId,
        Long pollId,
        Long pollOptionId
) {}
