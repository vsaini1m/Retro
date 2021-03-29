package com.test.api.configuration;

import com.test.api.entity.User;
import com.test.api.repositery.UserRepositery;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;



public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepositery userRepositery;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user=userRepositery.findByEmail(email);

        if (user==null)
            throw new UsernameNotFoundException("User not found of "+email);
        else {
            CustomUserDetails customUserDetails=new CustomUserDetails(user);
            return customUserDetails;

        }


    }


}
