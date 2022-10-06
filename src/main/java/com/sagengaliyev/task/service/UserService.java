package com.sagengaliyev.task.service;

import com.sagengaliyev.task.dto.UserDTO;
import com.sagengaliyev.task.mapper.UserMapper;
import com.sagengaliyev.task.models.User;
import com.sagengaliyev.task.repositories.UsersRepository;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
public class UserService  {

    private final UsersRepository usersRepository;
    private final UserMapper userMapper;

    public UserService(UsersRepository usersRepository, UserMapper userMapper) {
        this.usersRepository = usersRepository;
        this.userMapper = userMapper;
    }
    public List<User> getUsers()
    {
        return usersRepository.findAll();
    }


    /**
     * Логика заданиЯ №2: Добавление нового пользователя.
     * @param userDTO
     * @return
     */
    public String register(UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        usersRepository.save(user);
        return "You've successfully been registered! Your user ID: " + user.getId();
    }

    /**
     * Логика задания №4:Изменение статуса пользователя (Online, Offline).
     * @param userDTO
     * @param principal
     * @return
     */
    public String updateStatus (UserDTO userDTO, Principal principal){
        Long id = userDTO.getId();
        String newStatus = userDTO.getNewStatus();
        User user = usersRepository.findByEmail(principal.getName()).orElseThrow(() -> new RuntimeException("unable to find user by username: " + principal.getName()));
        String oldStatus = String.valueOf(user.getStatus());
        usersRepository.updateById(id, newStatus);

        return "Status has been successfully updated!" + "user ID: " + id + "\n" + " old status: " + oldStatus + "\n" + " new status: " + newStatus;
    }

    public Optional<User> findByEmail(String email){
        return usersRepository.findByEmail(email);
    }

    /**
     * Логика задания №3:Получение информации о пользователе
     * @param id
     * @return
     */
    public Optional<User> getById(Long id){
        Optional<User> user = usersRepository.findById(id);
        return user;
    }
}
