package pl.fernikq.poll.data.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record CreatePollRequest(

        String name,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        @Schema(type = "string", pattern = "yyyy-MM-dd HH:mm:ss", example = "2026-06-01 15:30:00")
        LocalDateTime closeDate,
        List<String> options
) {}
