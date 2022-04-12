package com.dwmarketing.fileupload.user;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@AllArgsConstructor
@RestController
@CrossOrigin(origins="http://localhost:3000")
@RequestMapping(path = "api/users")
public class UserController {

    @Autowired
    private final UserService userService;

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping( path = "/{userId}")
    public Optional<User> getUser(@PathVariable Long userId){
        return userService.getUser(userId);
    }

    @PostMapping
    public void registerNewUser(@RequestBody User user){
       userService.registerNewUser(user);
    }

    @DeleteMapping(path = "/{userId}")
    public void deleteUser(@PathVariable Long userId){
        userService.deleteUser(userId);
    }
}
