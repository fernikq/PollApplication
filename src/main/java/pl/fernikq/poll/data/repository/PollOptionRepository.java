package pl.fernikq.poll.data.repository;

import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.fernikq.poll.data.PollOption;

public interface PollOptionRepository extends JpaRepository<@NonNull PollOption, @NonNull Long> {
}
