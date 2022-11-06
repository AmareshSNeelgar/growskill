package com.platform.growskill.service.mapper;

import com.platform.growskill.dto.UserDto;
import com.platform.growskill.entity.User;

public class UserMapper {

    private  UserMapper() {}

    public static  final User addUser(UserDto body){
        User saveObj = new User();
        saveObj.email = body.email;
        saveObj.firstName= body.firstName;
        saveObj.lastName= body.lastName;
        saveObj.accountType = body.type;
        return  saveObj;
    }

}
