package pl.sdaacademy.reservationapi.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import pl.sdaacademy.reservationapi.security.AuthenticationFilter;
import pl.sdaacademy.reservationapi.security.AuthorizationFilter;
import pl.sdaacademy.reservationapi.user.UserProvider;

@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserProvider userProvider;
    private final PasswordEncoder passwordEncoder;
    private final String authType;
    private final String authKey;
    private final String signInKey;
    private final String tokenExpirationTime;

    public WebSecurityConfiguration(UserProvider userProvider,
                                    PasswordEncoder passwordEncoder,
                                    @Value("${crrs.auth_key}") String authKey,
                                    @Value("${crrs.auth_type}") String authType,
                                    @Value("${crrs.signin_key}") String signInKey,
                                    @Value("${crrs.token_expiration_time}") String tokenExpirationTime) {
        this.userProvider = userProvider;
        this.passwordEncoder = passwordEncoder;
        this.authKey = authKey;
        this.authType = authType;
        this.signInKey = signInKey;
        this.tokenExpirationTime = tokenExpirationTime;
    }

    private static final String[] AUTH_WHITELIST = {
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/h2-console/**"
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().cors();
        http.authorizeRequests()
                .antMatchers(AUTH_WHITELIST).permitAll()
                .antMatchers("/users").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(new AuthenticationFilter(authenticationManager(), authKey, authType, signInKey, tokenExpirationTime))
                .addFilter(new AuthorizationFilter(authenticationManager(), authKey, authType, signInKey));
        http.headers().frameOptions().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userProvider).passwordEncoder(passwordEncoder);
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }
}
