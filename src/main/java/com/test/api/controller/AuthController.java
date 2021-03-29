package com.test.api.controller;

import com.test.api.configuration.CustomUserDetails;
import com.test.api.dto.JwtResponse;
import com.test.api.dto.LoginUserDto;
import com.test.api.dto.RegisterUserDto;
import com.test.api.entity.User;
import com.test.api.healper.JwtUtil;
import com.test.api.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class AuthController {

    private final UserService userService;
    private final JwtResponse jwtResponse;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;

    @PostMapping("/token")
    public ResponseEntity<JwtResponse> authorize(@RequestBody LoginUserDto loginUserDto){

        System.out.println(loginUserDto);

        try {

            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUserDto.getEmail(),loginUserDto.getPassword()));



        }catch (Exception e){
            e.printStackTrace();
        }


        ///fine area id password is correct

        //genrate token

        UserDetails userDetails = this.userDetailsService.loadUserByUsername(loginUserDto.getEmail());

        String token = this.jwtUtil.generateToken(userDetails);

        System.out.println("token is"+token);

        return ResponseEntity.ok().body(new JwtResponse(token));




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
