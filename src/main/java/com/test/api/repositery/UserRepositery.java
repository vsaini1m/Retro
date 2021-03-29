package com.test.api.repositery;

import com.test.api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositery extends JpaRepository<User,Long> {


    User findByEmail(String email);
}
