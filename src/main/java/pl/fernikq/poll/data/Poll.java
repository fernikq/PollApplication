package pl.fernikq.poll.data;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Poll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false)
    private String name;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime createDate;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime closeDate;

    @NotNull
    @OneToMany(mappedBy = "poll", fetch = FetchType.LAZY)
    private List<PollOption> options;
}
