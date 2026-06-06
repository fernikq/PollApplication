package pl.fernikq.poll.data.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import pl.fernikq.poll.data.dto.PollOptionDTO;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record CreatePollResponse(

        Long id,
        String name,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime createDate,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime closeDate,
        List<PollOptionDTO> options
) {}
