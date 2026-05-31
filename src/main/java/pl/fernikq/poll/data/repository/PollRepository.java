package pl.fernikq.poll.data.repository;

import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.fernikq.poll.data.Poll;

public interface PollRepository extends JpaRepository<@NonNull Poll, @NonNull Long> {



}
