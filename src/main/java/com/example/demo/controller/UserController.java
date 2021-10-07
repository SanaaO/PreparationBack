package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.security.LoginRequest;
import com.example.demo.security.LoginResponse;
import com.example.demo.security.TokenUtil;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    TokenUtil tokenUtil;

    @PostMapping(value = "/register")
    public ResponseEntity signIn(@RequestBody User user) throws Exception {

        if (user == null) {
            return ResponseEntity.badRequest().body("Can't create user with empty fields !");
        } else if (userService.existsByUsername(user.getUsername())) {
            return ResponseEntity.badRequest().body("username already exist !");
        }

        User newuser = new User(user.getUsername(), user.getPassword(),
                user.getName(), user.getRole(), user.getBirthday());
        this.userService.createUser(newuser);
        //Autologin after register
        LoginRequest loginrequest = new LoginRequest(user.getUsername() , user.getPassword());
        return ResponseEntity.ok(this.login(loginrequest));
    }

    @PostMapping(value = "/login")
    public ResponseEntity login(@RequestBody LoginRequest loginRequest) throws Exception {

        LoginResponse response = null;

        try {
            //handle authentication
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );
            //System.out.println("auth : " + authentication);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // fetch the user details by the userdetailservice to create the token based
            // on it
            UserDetails userDetails = userService.loadUserByUsername(loginRequest.getUsername());
            //System.out.println("userDetails : " + userDetails);

            //get user principals to get user id
            User user = (User) authentication.getPrincipal();
            // generate jwt
            String token = tokenUtil.GenerateToken(userDetails);

            //we will get userid, token and authorities as response
            response = new LoginResponse(user.getId(),token, authentication.getAuthorities());

        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }


        return ResponseEntity.ok(response);
    }

}
