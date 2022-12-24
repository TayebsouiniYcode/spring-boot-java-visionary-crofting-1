package com.youcode.visionarycrofting.service;

import com.youcode.visionarycrofting.entity.Role;
import com.youcode.visionarycrofting.entity.User;

import java.util.List;

public interface UserService {
    User saveUSer( User user);
    Role saveRole( Role role);
    void addRoleToUSer(String username, String roleName);
    User getUser(String username);
    List<User> getUSers();
}
