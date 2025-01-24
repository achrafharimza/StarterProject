package com.starterProject.services.impl;


import com.starterProject.dto.UserDto;
import com.starterProject.dto.services.IMapClassWithDto;
import com.starterProject.entities.UserEntity;
import com.starterProject.services.UserService;
import com.starterProject.repositories.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    userRepository userRepository;
    @Autowired
    IMapClassWithDto<UserEntity, UserDto> userMapping;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public  List<UserDto> getUsers() {
        List<UserEntity> usersListEntity  = userRepository.findAll();
        List<UserDto> usersListDto  =userMapping.convertListToListDto(usersListEntity,UserDto.class);

        return usersListDto;
    }

    @Override
    public UserDto addUser(UserDto user) {

        UserEntity ifUserexiste = userRepository.findByEmail(user.getEmail());
        if(ifUserexiste != null) throw new RuntimeException("user deja exist");
        String pass = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(pass);
        UserEntity userEntity = userMapping.convertToEntity(user, UserEntity.class);
        userEntity = userRepository.save(userEntity);
        return userMapping.convertToDto(userEntity, UserDto.class);
    }


    /////////////////////////////////////////jwt
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(email);
        System.out.println("login load");



        if(userEntity == null) throw new UsernameNotFoundException(email);

        return new org.springframework.security.core.userdetails.User(userEntity.getEmail(), userEntity.getPassword(), new ArrayList<>());
    }
}
