package com.spring.as.controller.rest;

import com.spring.as.entity.User;
import com.spring.as.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/user")
public class UserRestController {

    @Autowired
    private UserService userService;

    @GetMapping(path = "/all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(path = "/{id}")
    public User getUser(@PathVariable("id") int id) {
        return userService.getUser(id);
    }

    @PostMapping("/add")
    public String register(@RequestBody User user) {
        user.setRole("user");
        userService.create(user);
        Authentication auth = new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);
        return "redirect:/index";
    }

}
