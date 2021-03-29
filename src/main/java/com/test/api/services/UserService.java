package com.test.api.services;

import com.test.api.dto.LoginUserDto;
import com.test.api.dto.RegisterUserDto;
import com.test.api.entity.User;
import com.test.api.repositery.UserRepositery;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepositery userRepositery;

    private final PasswordEncoder passwordEncoder;

    public User addNewUser(RegisterUserDto registerUserDto){

        User user=new User();
        user.setName(registerUserDto.getName());
        user.setEmail(registerUserDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerUserDto.getPassword()));

        user.setRole("USER");

         return userRepositery.save(user);
    }

    public User updateUser(User user){
        user.setId(user.getId());
        return this.userRepositery.save(user);
    }

    public User getUserById(long id){
        return this.userRepositery.findById(id).get();
    }

    public boolean deleteUserById(long id){

        try {
            this.userRepositery.deleteById(id);

        }catch (Exception e){

            e.printStackTrace();
            return false;
        }

        return true;
    }

    public List<User> getAllUsers() {
       return this.userRepositery.findAll();
    }

    public void AuthorizationUser(LoginUserDto loginUserDto) {

        User byEmail = this.userRepositery.findByEmail(loginUserDto.getEmail());



    }
}
