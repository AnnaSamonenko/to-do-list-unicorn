package com.spring.as.service;

import com.spring.as.model.Roles;
import com.spring.as.repository.UserDAO;
import com.spring.as.repository.VerificationTokenDAO;
import com.spring.as.model.User;
import com.spring.as.model.VerificationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@PropertySource("classpath:validation.properties")
public class UserServiceImpl implements UserService {

    @Autowired
    private Environment env;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private VerificationTokenDAO tokenDAO;

    @Override
    public List<User> getAllUsers() {
        return userDAO.getAll();
    }

    @Override
    public User getUser(String username) {
        verifyingOfUserPresence(username);
        return userDAO.read(username);
    }

    @Override
    public User createAccount(User user) {
        if (userDAO.read(user.getUsername()) != null)
            throw new IllegalArgumentException(env.getProperty("username.username_present"));
        if (userDAO.isEmailPresent(user.getEmail()))
            throw new IllegalArgumentException(env.getProperty("email.already_present"));

        user.setRole(Roles.USER_ROLE.name());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDAO.create(user);
        return user;
    }

    @Override
    public void update(User user) {
        userDAO.update(user);
    }

    @Override
    public void deleteAccount(String username) {
        verifyingOfUserPresence(username);
        userDAO.delete(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        verifyingOfUserPresence(username);
        return userDAO.read(username);
    }

    @Override
    public User getAuthorizedUser() {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        String username = currentUser.getName();
        return (User) loadUserByUsername(username);
    }

    @Override
    public VerificationToken getVerificationToken(String verificationToken) {
        return tokenDAO.findByToken(verificationToken);
    }

    @Override
    public void createVerificationToken(User user, String token) {
        VerificationToken verificationToken = new VerificationToken(token, user);
        tokenDAO.save(verificationToken);
    }

    private void verifyingOfUserPresence(String username) {
        if (userDAO.read(username) == null)
            throw new UsernameNotFoundException(env.getProperty("username.not_found") + " " + username);
    }
}
