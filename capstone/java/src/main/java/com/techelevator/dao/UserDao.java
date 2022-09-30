package com.techelevator.dao;

import com.techelevator.model.ServiceRequest;
import com.techelevator.model.User;

import java.security.Principal;
import java.util.List;

public interface UserDao {

    List<User> findAll();

    User getUserById(int userId);

    User findByUsername(String username);

    int findIdByUsername(String username);

    boolean create(String username, String password, String role);

    String findRoleByUserName (String username);

    /*
    user should be able to see request submitted
    includes everything, vehicle, contact info, and service requested
    */

    /*
    user should be able to see repair estimate info
     */
}
