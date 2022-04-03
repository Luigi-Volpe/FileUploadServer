package com.dwmarketing.fileupload.user;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    @Autowired
    private final UserRepository userRepository;


    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public Optional<User> getUser(Long id){
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()){
            throw new IllegalStateException("User Doesn't exist");
        }
        return optionalUser;
    }

    public void registerNewUser(User user){
        String userName = user.getName();
        Optional<User> UserWithTheSameName = userRepository.findUserByName(userName);
        if (UserWithTheSameName.isPresent()){
            throw new IllegalStateException("The name " + userName + " is already in use");
        }
        userRepository.save(user);
    }

    public void deleteUser(Long id){
        if (!userRepository.existsById(id)){
            throw new IllegalStateException("No user found with id " + id);
        }
        userRepository.deleteById(id);
    }



}
