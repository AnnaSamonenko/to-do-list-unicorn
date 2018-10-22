package com.spring.as.controller.rest;

import com.spring.as.entity.User;
import com.spring.as.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/user")
public class UserRestController {

    @Autowired
    private UserService userService;

//    @Autowired
//    private RoleDAO roleDAO;

    @GetMapping(path = "/all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(path = "/{id}")
    public User getUser(@PathVariable("id") int id) {
        return userService.getUser(id);
    }

    @PostMapping("/add")
    public void create(@RequestBody User user) {
        user.setRole("user");
        userService.create(user);
    }
}
