package pl.sdaacademy.reservationapi.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserTransformer userTransformer;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, UserTransformer userTransformer, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userTransformer = userTransformer;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDTO addUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userTransformer.toDTO(userRepository.save(user));
    }
}
