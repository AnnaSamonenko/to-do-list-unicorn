package com.spring.as.dao;

import com.spring.as.entity.UserDTO;

public interface UserDAO extends GenericDAO {

    void update(UserDTO u);

    void create(UserDTO u);

}
