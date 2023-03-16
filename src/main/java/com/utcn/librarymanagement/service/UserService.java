package com.utcn.librarymanagement.service;

import com.utcn.librarymanagement.dto.UserAddDTO;
import com.utcn.librarymanagement.dto.UserDTO;
import com.utcn.librarymanagement.model.User;
import com.utcn.librarymanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Optional;

@Service
public class UserService {

    UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public UserDTO insertUser(UserAddDTO userDTO){
        User user = userRepository.save(convertDTOToUser(userDTO));
        return convertUserToDTO(user);
    }

    public UserDTO login(String email, String password){
        String encryptedPassword = Base64.getEncoder().encodeToString(password.getBytes());
        Optional<User> user = userRepository.findByEmailAndPassword(email, encryptedPassword);
        if(user.isPresent()){
            return convertUserToDTO(user.get());
        } else {
            return null;
        }
    }

    public User convertDTOToUser(UserAddDTO userDTO){
        return User.builder()
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .email(userDTO.getEmail())
                .phoneNumber(userDTO.getPhoneNumber())
                .password(Base64.getEncoder().encodeToString(userDTO.getPassword().getBytes()))
                .build();
    }

    public UserDTO convertUserToDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phoneNumber(user.getPhoneNumber())
                .email(user.getEmail())
                .build();
    }

}
