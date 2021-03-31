package com.test.api.services;

import com.test.api.entity.User;
import com.test.api.repositery.UserRepositery;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepositery userRepositery;


    public User addNewUser(User user){


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
}
