package com.gmail.samonenko.service;

import com.gmail.samonenko.model.User;
import com.gmail.samonenko.model.VerificationToken;
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
