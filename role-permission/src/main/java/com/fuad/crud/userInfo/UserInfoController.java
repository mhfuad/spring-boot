package com.fuad.crud.userInfo;

import com.fuad.crud.LoginRequest;
import com.fuad.crud.config.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        UserInfo userInfo = service.findByUsername(loginRequest.getUsername());

        if(!userInfo.getName().equals(loginRequest.getUsername())){
            return ResponseEntity.badRequest().body("User not found");
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
        Map<String, Object> response = new HashMap<>();
        response.put("token", jwtService.generateToken(loginRequest.getUsername()));
        response.put("role", getUserRoles(loginRequest.getUsername()));
        return ResponseEntity.badRequest().body(response);
    }

    @GetMapping("/user")
    public UserInfo getUserRoles(String name){
        return service.findByUsername(name);
    }
}
