package pl.fernikq.poll.authorization.data;

import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthUserEntityRepository extends JpaRepository<@NonNull AuthUserEntity, @NonNull Long> {

    Optional<AuthUserEntity> findByUsername(String username);
}
