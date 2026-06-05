package pl.fernikq.poll.data.repository;

import jakarta.transaction.Transactional;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.fernikq.poll.data.PollOption;

public interface PollOptionRepository extends JpaRepository<@NonNull PollOption, @NonNull Long> {

    @Modifying
    @Transactional
    @Query("DELETE FROM #{#entityName} option WHERE option.poll.id = :pollId")
    void deleteOptionsByPollId(@Param("pollId") Long pollId);
}
