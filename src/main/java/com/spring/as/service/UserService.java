package com.spring.as.service;

import com.spring.as.model.User;
import com.spring.as.model.VerificationToken;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    User getAuthorizedUser();

    void deleteAccount(String username);

    User createAccount(User user);

    void update(User user);

    List<User> getAllUsers();

    User getUser(String username);

    void createVerificationToken(User user, String token);

    VerificationToken getVerificationToken(String token);

}
