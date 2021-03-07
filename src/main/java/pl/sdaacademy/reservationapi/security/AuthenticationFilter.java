package pl.sdaacademy.reservationapi.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import pl.sdaacademy.reservationapi.user.User;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.Optional;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private static final String AUTH_ENDPOINT_URL = "/login";
    private static final String ACCESS_CONTROL_EXPOSE_HEADER = "Access-Control-Expose-Header";

    private final AuthenticationManager authenticationManager;
    private final String authKey;
    private final String authType;
    private final String signInKey;
    private final String tokenExpirationTime;

    public AuthenticationFilter(AuthenticationManager authenticationManager,
                                String authKey,
                                String authType,
                                String signInKey,
                                String tokenExpirationTime) {
        this.authenticationManager = authenticationManager;
        this.authType = authType;
        this.authKey = authKey;
        this.signInKey = signInKey;
        this.tokenExpirationTime = tokenExpirationTime;
        setFilterProcessesUrl(AUTH_ENDPOINT_URL);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        Optional<User> userOptional = Optional.empty();
        try {
            userOptional = Optional.ofNullable(new ObjectMapper().readValue(request.getInputStream(), User.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
        User user = userOptional.<UsernameNotFoundException>orElseThrow(()->{
            throw new UsernameNotFoundException("user not authenticated!");
        });
        return authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), Collections.emptyList()));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String token = Jwts.builder()
                .setSubject(((org.springframework.security.core.userdetails.User)authResult.getPrincipal()).getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(tokenExpirationTime)))
                .signWith(SignatureAlgorithm.HS512, signInKey.getBytes())
                .compact();
        response.addHeader(ACCESS_CONTROL_EXPOSE_HEADER, authKey);
        response.addHeader(authKey, authType + " " + token);
    }
}
