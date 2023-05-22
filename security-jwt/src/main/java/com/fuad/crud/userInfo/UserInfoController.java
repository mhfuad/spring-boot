package com.fuad.crud.userInfo;

import com.fuad.crud.LoginRequest;
import com.fuad.crud.config.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UserInfoController {

    @Autowired
    private UserInfoService service;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public String addUser(@RequestBody UserInfo userInfo){
        return service.saveUser(userInfo);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest){
        UserInfo userInfo = service.findByUsername(loginRequest.getUsername());

        if(!userInfo.getName().equals(loginRequest.getUsername())){
            return "User name not match";
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );
        if (!authentication.isAuthenticated()) {
             throw new UsernameNotFoundException("Invalid user request");
        }
        return jwtService.generateToken(loginRequest.getUsername());
    }
}
