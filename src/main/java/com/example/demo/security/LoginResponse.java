package com.example.demo.security;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class LoginResponse {

    private Long userid;
    private String token;
    private Collection<? extends GrantedAuthority> authorities;


    public LoginResponse() {
    }

    public LoginResponse(Long userid, String token, Collection<? extends GrantedAuthority> authorities) {
        this.userid = userid;
        this.token = token;
        this.authorities = authorities;
    }


    public Long getUserid() {
        return userid;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }


    public String getToken() {
        return token;

    }
}
