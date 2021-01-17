package com.helpdesk.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.helpdesk.api.dto.UserDTO;
import com.helpdesk.api.services.UserService;


@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(params = { "email" })
    public UserDTO findUserByEmail(@RequestParam(name = "email", required = true) String email) {
        return UserDTO.from(this.userService.findUserByEmail(email));
    }

}
