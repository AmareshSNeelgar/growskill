package com.platform.growskill.service.mapper;

import com.platform.growskill.dto.CourseDto;
import com.platform.growskill.dto.UpdateCourse;
import com.platform.growskill.entity.Course;

public class CourseMapper {

private CourseMapper(){}

    public static  final Course addCourse(CourseDto body){
        Course saveObj = new  Course();
        saveObj.name = body.name;
        saveObj.startDate = body.startDate;
        saveObj.type = body.type;
        saveObj.endDate = body.endDate;
        saveObj.fees = body.fee;
        return saveObj;
    }


    public static  final Course updateCourse(UpdateCourse body, Course oldData){

    if(body.startDate != null) oldData.startDate = body.startDate;
    if(body.endDate != null) oldData.endDate = body.endDate;
    if(!body.name.isEmpty()) oldData.name = body.name;
    if(body.fee != null) oldData.fees = body.fee;
    return oldData;
    }

}
