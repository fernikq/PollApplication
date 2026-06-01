package pl.fernikq.poll.data.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record CreatePollRequest(

        String name,
        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        @Schema(type = "string", pattern = "yyyy-MM-dd HH:mm:ss", example = "2026-06-01 15:30:00")
        LocalDateTime closeDate,
        List<String> options
) {}
