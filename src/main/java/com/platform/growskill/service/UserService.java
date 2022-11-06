package com.platform.growskill.service;

import com.platform.growskill.dto.LoginDto;
import com.platform.growskill.dto.UserDto;
import com.platform.growskill.entity.User;
import com.platform.growskill.repo.UserRepo;
import com.platform.growskill.service.mapper.UserMapper;
import com.platform.growskill.util.BcryptPasswordEncoder;
import com.platform.growskill.util.CommonResponse;
import com.platform.growskill.util.MessageCodes;
import org.hibernate.mapping.List;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.Optional;


@Service
public class UserService {

    private Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BcryptPasswordEncoder passwordEncoder;

    public CommonResponse  addUser(UserDto body){
        Optional<User> oldObj = userRepo.getByEmail(body.email);
        if(oldObj.isPresent()){
          return new CommonResponse(HttpStatus.BAD_REQUEST.value(), null,MessageCodes.EMAIL_ALREADY_EXIST);
        }
        User saveObj = UserMapper.addUser(body);
        saveObj.password = passwordEncoder.passwordEncoder(body.password);
        userRepo.save(saveObj);
        return new CommonResponse(HttpStatus.OK.value(),null, MessageCodes.CREATE_USER_SUCCESS);
    }

    public  CommonResponse findAll(){
       Iterable<User> resp = userRepo.findAll();
        return new CommonResponse(HttpStatus.OK.value(),resp, MessageCodes.CREATE_USER_SUCCESS);
    }

    public CommonResponse<User>  login(LoginDto body){
        Optional<User> oldObj = userRepo.getByEmail(body.email);
        if(oldObj.isEmpty()){
            return new CommonResponse(HttpStatus.BAD_REQUEST.value(), null,MessageCodes.USER_DOES_NOT_EXIST);
        }
        User uData= oldObj.get();
        if(!passwordEncoder.match(body.password, uData.password)){
            return new CommonResponse(HttpStatus.BAD_REQUEST.value(), null,MessageCodes.INVALID_PASSWORD);
        }
        return new CommonResponse<User>(HttpStatus.OK.value(),oldObj.get(), MessageCodes.CREATE_USER_SUCCESS);
    }


}
