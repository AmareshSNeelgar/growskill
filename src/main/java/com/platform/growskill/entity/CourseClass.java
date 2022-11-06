package com.platform.growskill.entity;

import com.platform.growskill.enums.ClassStatus;
import com.platform.growskill.enums.CourseStatus;
import com.platform.growskill.enums.WeekDays;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Table(name="course_class")
public class CourseClass {

    @Id
    @Column(name="class_id")
    public Long id;

    @Column(name="course_id")
    public Long courseId;

    @Column(name="instructor_id")
    public Long instructorId;

    @Column(name="topicName")
    public String topicName;

    @Column(name="status")
    public ClassStatus status;

    @Column(name = "starts_At")
    public Time startsAt;

    @Column(name = "ends_At")
    public Time endsAt;

    @Column(name="link")
    public  String link;

    @Enumerated(EnumType.STRING)
    @Column(name = "conducted_on")
    public WeekDays conductedOn;

}
