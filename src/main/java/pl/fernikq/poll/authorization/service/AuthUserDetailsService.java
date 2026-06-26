package pl.fernikq.poll.authorization.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.fernikq.poll.authorization.data.AuthUserEntity;
import pl.fernikq.poll.authorization.data.AuthUserEntityRepository;

@Service
@RequiredArgsConstructor
public class AuthUserDetailsService implements UserDetailsService {

    private final AuthUserEntityRepository userRepository;

    @Override
    public @NonNull UserDetails loadUserByUsername(@NonNull String username) throws UsernameNotFoundException {
        AuthUserEntity authUserEntity = this.userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username: " + username + " doesn't exists"));
        return User.builder().username(username).authorities("ROLE_USER").password(authUserEntity.getPassword()).build();
    }
}
