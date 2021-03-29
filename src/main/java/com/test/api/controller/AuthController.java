package com.test.api.controller;

import com.test.api.dto.LoginUserDto;
import com.test.api.dto.RegisterUserDto;
import com.test.api.entity.User;
import com.test.api.repositery.UserRepositery;
import com.test.api.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    @PostMapping("/login") public void authorize(@RequestBody LoginUserDto loginUserDto){



        this.userService.AuthorizationUser(loginUserDto);
    }


    //register user

    @PostMapping("/singup")
    public ResponseEntity registerUser(@RequestBody RegisterUserDto registerUserDto){
        User user1 = userService.addNewUser(registerUserDto);

        if (user1==null){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }else {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }

    }


}
