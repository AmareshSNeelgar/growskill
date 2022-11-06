package com.platform.growskill.controller;

import com.platform.growskill.dto.CourseDto;
import com.platform.growskill.dto.CourseFilterDto;
import com.platform.growskill.entity.Course;
import com.platform.growskill.enums.CourseStatus;
import com.platform.growskill.service.CourseService;
import com.platform.growskill.util.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @RequestMapping(value = "",method = RequestMethod.POST)
    public ResponseEntity<CommonResponse<Course>> addCourse(@RequestHeader Long userId, @RequestBody CourseDto body){
        CommonResponse<Course> resp = courseService.addCourse(body,userId);
        return new ResponseEntity<CommonResponse<Course>>(resp, HttpStatus.valueOf(resp.status));
    }

    @RequestMapping(value = "/get/all",method = RequestMethod.POST)
    public  ResponseEntity<CommonResponse<List<Course>>> getAllCourse(@RequestBody CourseFilterDto body){
        CommonResponse<List<Course>> resp = courseService.getCourse(body);
        return new ResponseEntity<CommonResponse<List<Course>>>(resp, HttpStatus.valueOf(resp.status));
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public  ResponseEntity<CommonResponse<Course>> updateCourseStatus(@RequestHeader Long userId, @PathVariable Long id, @RequestParam CourseStatus status){
        CommonResponse<Course> resp = courseService.updateCourseStatus(id,userId,status);
        return new ResponseEntity<CommonResponse<Course>>(resp, HttpStatus.valueOf(resp.status));
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public  ResponseEntity<CommonResponse> deleteCourse(@RequestHeader Long userId, @PathVariable Long id){
        CommonResponse resp = courseService.deleteCourse(id,userId);
        return new ResponseEntity<CommonResponse>(resp, HttpStatus.valueOf(resp.status));
    }


}
