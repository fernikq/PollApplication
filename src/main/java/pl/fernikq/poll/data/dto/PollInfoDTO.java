package pl.fernikq.poll.data.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record PollInfoDTO(

        String name,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime createDate,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime closeDate,
        List<PollOptionDTO> options

) {}
