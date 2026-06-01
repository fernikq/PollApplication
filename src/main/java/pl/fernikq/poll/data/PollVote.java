package pl.fernikq.poll.data;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class PollVote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @JoinColumn(nullable = false)
    @ManyToOne
    private PollOption option;
}
