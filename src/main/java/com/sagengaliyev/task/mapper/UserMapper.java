package com.sagengaliyev.task.mapper;

import com.sagengaliyev.task.dto.UserDTO;
import com.sagengaliyev.task.models.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    private final BCryptPasswordEncoder encoder;

    public UserMapper(BCryptPasswordEncoder encoder) {
        this.encoder = encoder;
    }


    public UserDTO toDTO(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(encoder.encode(user.getPassword()));
        userDTO.setImageUrl(user.getImageUrl());
        userDTO.setNewStatus(user.getNewStatus());
        return userDTO;
    }

    public User toEntity(UserDTO userDTO){
        User user = new User();
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(encoder.encode(userDTO.getPassword()));
        user.setImageUrl(userDTO.getImageUrl());
        user.setNewStatus(userDTO.getNewStatus());
        return user;
    }

    public User toEntityFromId(Long id) {
        User user = new User();
        user.setId(id);
        return user;
    }
}
