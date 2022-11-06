package com.platform.growskill.dto;

import com.platform.growskill.enums.CourseType;

import java.time.ZonedDateTime;

public class CourseDto {

    public String name;
    public CourseType type;
    public ZonedDateTime startDate;
    public ZonedDateTime endDate;
    public Double fee;

}
