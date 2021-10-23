package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    //load user by username (in our case email)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        return user;
    }

    @Bean
    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public User createUser(User user) {
        //crypt password before saving into database
        user.setPassword(passwordEncoder().encode(user.getPassword()));
        return this.userRepository.save(user);
    }

    //to verify if the user already exists
    public boolean existsByUsername(String email) {
        return this.userRepository.existsByEmail(email);
    }


    public User findById(Long id) throws Exception {
        return this.userRepository.findById(id).orElseThrow(() -> new Exception("user not found "));
    }


    public User updateUser(User user) {
        return this.userRepository.save(user);
    }

}
