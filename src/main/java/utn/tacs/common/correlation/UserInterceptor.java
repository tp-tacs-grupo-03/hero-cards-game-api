package utn.tacs.common.correlation;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import utn.tacs.domain.PlayerStats;
import utn.tacs.domain.repositories.UsersRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;

@AllArgsConstructor
@Service
public class UserInterceptor implements HandlerInterceptor {

    private UsersRepository usersRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PlayerStats playerStats = new PlayerStats(authentication.getName());

        Map<String, Object> claims = ((Jwt) authentication.getPrincipal()).getClaims();
        playerStats.setImage(claims.get("https:picture").toString() + UUID.randomUUID().toString());
        playerStats.setName(claims.get("https:username").toString());

        usersRepository.upsert(playerStats);

        return true;
    }
}
