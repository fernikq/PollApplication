package pl.fernikq.poll.data.repository;

import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.fernikq.poll.data.PollVote;

public interface PollVoteRepository extends JpaRepository<@NonNull PollVote, @NonNull Long> {


    long countByOptionId(Long pollOptionId);
}
