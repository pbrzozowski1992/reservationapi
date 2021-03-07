package pl.sdaacademy.reservationapi.user;

import org.springframework.stereotype.Component;

@Component
public class UserTransformer {

    public UserDTO toDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserName(user.getUsername());
        return userDTO;
    }
}
