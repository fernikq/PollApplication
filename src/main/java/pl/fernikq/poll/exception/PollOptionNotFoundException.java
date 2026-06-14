package pl.fernikq.poll.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PollOptionNotFoundException extends RuntimeException {

    private final String message;
    private final Long pollOptionId;

}
