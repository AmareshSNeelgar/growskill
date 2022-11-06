package com.platform.growskill.service;


import com.platform.growskill.dao.CourseDao;
import com.platform.growskill.dto.CourseDto;
import com.platform.growskill.dto.CourseFilterDto;
import com.platform.growskill.dto.UpdateCourse;
import com.platform.growskill.entity.Course;
import com.platform.growskill.entity.User;
import com.platform.growskill.enums.AccountType;
import com.platform.growskill.enums.CourseStatus;
import com.platform.growskill.repo.CourseRepo;
import com.platform.growskill.repo.UserRepo;
import com.platform.growskill.service.mapper.CourseMapper;
import com.platform.growskill.util.CommonResponse;
import com.platform.growskill.util.MessageCodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    @Autowired
    private CourseRepo courseRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CourseDao courseDao;

    public CommonResponse userValidation(Long userId){
        Optional<User> user = userRepo.findById(userId);
        if(user.isEmpty()){
            return new CommonResponse(HttpStatus.BAD_REQUEST.value(),null, MessageCodes.INVALID_USER_ID);
        }
        if(user.get().accountType != AccountType.ADMIN) {
            return new CommonResponse(HttpStatus.BAD_REQUEST.value(),null, MessageCodes.ACCESS_DENIED);
        }

        return new CommonResponse(HttpStatus.OK.value(),null, MessageCodes.SUCCESS);
    }


    public  CommonResponse<Course>  addCourse(CourseDto body, Long userId){
        CommonResponse vResp = userValidation(userId);
        if(vResp.status == HttpStatus.BAD_REQUEST.value()){
            return  vResp;
        }
        Course saveObj = CourseMapper.addCourse(body);
        Course resp = courseRepo.save(saveObj);
        return  new CommonResponse<Course>(HttpStatus.OK.value(), resp,MessageCodes.ADD_COURSE_SUCCESS);
    }

    public  CommonResponse<Course>  updateCourse(UpdateCourse body, Long userId, Long courseId){
        CommonResponse vResp = userValidation(userId);
        if(vResp.status == HttpStatus.BAD_REQUEST.value()){
            return  vResp;
        }
        Optional<Course> cResp   = courseRepo.findById(courseId);
        if(cResp.isEmpty()){
            return  new CommonResponse<>(HttpStatus.BAD_REQUEST.value(),null,MessageCodes.INVALID_COURSE_ID);
        }
        Course uData = cResp.get();
         CourseMapper.updateCourse(body,uData);
        Course resp = courseRepo.save(uData);
        return  new CommonResponse<Course>(HttpStatus.OK.value(), resp,MessageCodes.UPDATE_COURSE_SUCCESS);
    }

    public CommonResponse<List<Course>> getCourse(CourseFilterDto body){
        List<Course> cResp = courseDao.getCourse(body);
        return  new CommonResponse<List<Course>>(HttpStatus.OK.value(), cResp,MessageCodes.GET_COURSE_SUCCESS);
    }

    public CommonResponse<Course> updateCourseStatus(Long courseId, Long userId, CourseStatus status){
        CommonResponse vResp = userValidation(userId);
        if(vResp.status == HttpStatus.BAD_REQUEST.value()){
            return  vResp;
        }
        Optional<Course> cResp = courseRepo.findById(courseId);
        if(cResp.isEmpty()){
            return new CommonResponse(HttpStatus.BAD_REQUEST.value(), null,MessageCodes.INVALID_COURSE_ID);
        }
        Course uData = cResp.get();
        uData.status = status;
        courseRepo.save(uData);
        return new CommonResponse<Course>(HttpStatus.BAD_REQUEST.value(), null,MessageCodes.INVALID_COURSE_ID);
    }

    public CommonResponse deleteCourse(Long userId,Long courseId){
        CommonResponse vResp = userValidation(userId);
        if(vResp.status == HttpStatus.BAD_REQUEST.value()){
            return  vResp;
        }
        courseRepo.deleteById(courseId);
        return new CommonResponse(HttpStatus.BAD_REQUEST.value(), null,MessageCodes.DELETE_COURSE_SUCCESS);
    }
}
