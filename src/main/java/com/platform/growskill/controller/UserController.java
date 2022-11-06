package com.platform.growskill.controller;


import com.platform.growskill.dto.LoginDto;
import com.platform.growskill.dto.UserDto;
import com.platform.growskill.entity.User;
import com.platform.growskill.service.UserService;
import com.platform.growskill.util.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/",method = RequestMethod.POST )
    public ResponseEntity  addUser(@RequestBody UserDto body){
        CommonResponse resp  = userService.addUser(body);
       return new ResponseEntity(resp, HttpStatus.valueOf(resp.status));
    }

    @RequestMapping(value = "/",method = RequestMethod.GET )
    public ResponseEntity  getAll(){
        CommonResponse resp  = userService.findAll();
        return new ResponseEntity(resp, HttpStatus.valueOf(resp.status));
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST )
    public ResponseEntity<CommonResponse<User>>  getAll(@RequestBody LoginDto body){
        CommonResponse<User> resp  = userService.login(body);
        return new ResponseEntity<CommonResponse<User>>(resp, HttpStatus.valueOf(resp.status));
    }
}
