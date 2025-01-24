package com.starterProject.controller;


import com.starterProject.dto.UserDto;
import com.starterProject.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")

public class UserController {
    @Autowired
    UserService userService;

    //------- All  : -------------------------------------------------------------------

    @GetMapping("/all")

    public ResponseEntity<Map<String,Object>> getAll(){

        Map<String, Object> response = new HashMap();
        response.put("status_code", "000");
        response.put("status_label", "request successful");
        try {
            List<UserDto> userDto = userService.getUsers();
            response.put("response_data", userDto);

            } catch (Exception ex) {
                response.put("status_code", "001");
                response.put("response_data", ex.getMessage());
            }

        return ResponseEntity.ok(response);

    }



    //------- Add  : ------------------------------------------------------------------

    @PostMapping("/add")
    public ResponseEntity<Map<String,Object>> create(@RequestBody UserDto userDto )  {
        Map<String, Object> response = new HashMap();
        response.put("status_code", "000");
        response.put("status_label", "request successful");
        try {
            UserDto newuser = userService.addUser(userDto);
            response.put("response_data", newuser);

        } catch (Exception ex) {
            response.put("status_code", "001");
            response.put("response_data", ex.getMessage());
        }

        return new ResponseEntity(response, HttpStatus.CREATED);
    }

}
