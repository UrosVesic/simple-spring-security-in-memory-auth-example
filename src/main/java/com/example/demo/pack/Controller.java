package com.example.demo.pack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author UrosVesic
 */
@RestController
public class Controller {

    @Autowired
    AuthenticationManager manager;

    @GetMapping("/hello-world")
    public HelloWorldBean helloWorld(){
        return new HelloWorldBean("Hello world");
    }

    @GetMapping("/admin")
    public HelloWorldBean helloWorldAdmin(){
        return new HelloWorldBean("Hello world - Admin");
    }

    @GetMapping("/user")
    public HelloWorldBean helloWorldUser(){
        return new HelloWorldBean("Hello world - User");
    }

    @PostMapping("/login")
    public boolean login(@RequestBody LoginRequest request){
        Authentication authenticate = manager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        return authenticate!=null;
    }
}
