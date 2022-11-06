package com.platform.growskill.util;

import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class BcryptPasswordEncoder {

    public String passwordEncoder(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return  passwordEncoder.encode(password);
    }


    public Boolean match(String currentPassword, String ExistingPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return  passwordEncoder.matches(currentPassword, ExistingPassword);
    }
}
