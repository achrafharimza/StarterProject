package com.starterProject.services;


import com.starterProject.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;


import java.util.List;


public interface UserService extends UserDetailsService {

    List<UserDto> getUsers();
    UserDto addUser(UserDto user);


}
