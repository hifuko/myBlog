package com.ivana.dao;

import com.ivana.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

//<User, Long>: object, id
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsernameAndPassword(String username, String password);



}
