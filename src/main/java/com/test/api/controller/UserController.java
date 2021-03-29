package com.test.api.controller;

import com.test.api.entity.User;
import com.test.api.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;





    //update user

    @PostMapping("/update")
    public ResponseEntity UpdateUser(@RequestBody User user){
        User user1 = userService.updateUser(user);

        if (user1==null){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }else {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }

    }


    //find user
    @GetMapping("/get/{id}")
    public ResponseEntity findUserById(@PathVariable("id") long id){
        User userById = this.userService.getUserById(id);

        if (userById==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }else {
            return ResponseEntity.ok().body(userById);
        }
    }

    //delete user

    @GetMapping("/delete/{id}")
    public ResponseEntity deleteUSerById(@PathVariable("id") long id){

      if ( this.userService.deleteUserById(id)){
          return ResponseEntity.ok().build();
      }else {
          return ResponseEntity.badRequest().build();
      }
    }


    @GetMapping("all")
    public ResponseEntity getAll(){
        List<User> allUsers = this.userService.getAllUsers();

        return ResponseEntity.ok().body(allUsers);

    }

}
