package com.fuad.crud.userInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UserInfoController {

    @Autowired
    private UserInfoService service;

    @PostMapping("/register")
    public String addUser(@RequestBody UserInfo userInfo){
        return service.saveUser(userInfo);
    }
}
