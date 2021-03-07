package pl.sdaacademy.reservationapi.user;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.NoSuchElementException;

@Component
public class UserProvider implements UserDetailsService {

    private final UserRepository userRepository;

    private UserProvider(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(s).<NoSuchElementException>orElseThrow(()->{
            throw new NoSuchElementException("no user with provided name: " + s);
        });
        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(),
                Collections.emptyList());
    }
}
