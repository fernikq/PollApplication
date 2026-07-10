package pl.fernikq.poll.authorization;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.filter.OncePerRequestFilter;
import pl.fernikq.poll.authorization.service.AuthUserDetailsService;
import pl.fernikq.poll.util.JwtUtils;

import java.io.IOException;
import java.util.Objects;

@RequiredArgsConstructor
public class JWTAuthFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;
    private final AuthUserDetailsService authUserDetailsService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        String authenticationHeader = request.getHeader("Authorization");
        if(Objects.nonNull(authenticationHeader) && authenticationHeader.contains("Bearer ")){
            String jwtToken = authenticationHeader.substring(7);
            if(this.jwtUtils.validateToken(jwtToken)){
                String username = this.jwtUtils.getUsernameFromToken(jwtToken);
                UserDetails userDetails = null;
                try {
                    userDetails = this.authUserDetailsService.loadUserByUsername(username);
                }catch (UsernameNotFoundException exception){
                    SecurityContextHolder.clearContext();
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                }
                if(Objects.nonNull(userDetails)) {
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
        }
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();
        return path.contains("/auth/register") || path.contains("/auth/login");
    }
}
