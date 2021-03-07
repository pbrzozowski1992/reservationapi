package pl.sdaacademy.reservationapi.security;

import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class AuthorizationFilter extends BasicAuthenticationFilter {

    private final String authKey;
    private final String authType;
    private final String signInKey;

    public AuthorizationFilter(AuthenticationManager authenticationManager,
                               String authKey,
                               String authType,
                               String signInKey) {
        super(authenticationManager);
        this.authKey = authKey;
        this.authType = authType;
        this.signInKey = signInKey;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(authKey);
        if (header == null || !header.startsWith(authType)) {
            chain.doFilter(request, response);
            return;
        }
        UsernamePasswordAuthenticationToken authenticationToken = proceedAuthentication(header);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken proceedAuthentication(String header) {
        try {
            String user = Jwts.parser().setSigningKey(signInKey.getBytes())
                    .parseClaimsJws(header.replace(authType, ""))
                    .getBody()
                    .getSubject();
            return user != null ? new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>()) : null;
        } catch (Exception e) {
            throw new IllegalArgumentException("Token not valid!");
        }
    }
}
