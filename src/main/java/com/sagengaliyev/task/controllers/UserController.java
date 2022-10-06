package com.sagengaliyev.task.controllers;

import com.sagengaliyev.task.dto.UserDTO;
import com.sagengaliyev.task.models.User;
import com.sagengaliyev.task.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/show")
    public ResponseEntity<List<User>> getUsers(){
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }

    /**
     * Задание №3:Получение информации о пользователе.
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<Optional<User>> getById(@PathVariable Long id){
        Optional<User> user = userService.getById(id);
        if (user==null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(user,HttpStatus.OK);
        }
    }

    /**
     * Задание №2: Добавление нового пользователя.
     * @param userDTO
     * @return
     */
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserDTO userDTO){
        return new ResponseEntity<>(userService.register(userDTO), HttpStatus.OK);
    }

    @GetMapping("/info")
    public String daoTestPage(Principal principal) {
        User user = userService.findByEmail(principal.getName()).orElseThrow(() -> new RuntimeException("unable to fing user by username: " + principal.getName()));
        return "authenticated user with name: " + user.getName() + " email: " + user.getEmail() + " id: "  + user.getId();

    }

    /**
     * Задание №4: Изменение статуса пользователя (Online, Offline).
     * @param userDTO
     * @param principal
     * @return
     */
    @PutMapping(value = "/update-status",consumes = "application/json")
    public ResponseEntity<String> updateStatus(@RequestBody UserDTO userDTO, Principal principal){

        return new ResponseEntity<>(userService.updateStatus(userDTO, principal), HttpStatus.OK);
    }




}
