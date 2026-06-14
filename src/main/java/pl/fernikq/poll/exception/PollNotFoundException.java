package pl.fernikq.poll.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PollNotFoundException extends RuntimeException {

    private final String message;
    private final Long pollId;

}
