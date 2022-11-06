package com.platform.growskill.entity;

import com.platform.growskill.enums.CourseStatus;
import com.platform.growskill.enums.CourseType;
import com.platform.growskill.util.ZonedDateTimeAttributeConverter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.sql.Time;
import java.time.ZonedDateTime;

@Entity
@Table(name = "course")
public class
Course {

    @Id
    @Column(name = "course_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @Column(name="name")
    public String name;

    @Enumerated(EnumType.STRING)
    @Column(name="type")
    public CourseType type;

    @Column(name="isDownload")
    public boolean isDownload = true;

    @Column(name="status")
    public CourseStatus status  = CourseStatus.RUNNING;

    @Convert(converter = ZonedDateTimeAttributeConverter.class)
    @Column(name = "start_date")
    public ZonedDateTime startDate ;


    @Convert(converter = ZonedDateTimeAttributeConverter.class)
    @Column(name = "end_date")
    public ZonedDateTime endDate;

    @Column(name="fee")
    public Double fees;

    @CreatedDate
    @Convert(converter = ZonedDateTimeAttributeConverter.class)
    @Column(name = "created_date")
    public ZonedDateTime createdDate = ZonedDateTime.now();


    @LastModifiedDate
    @Convert(converter = ZonedDateTimeAttributeConverter.class)
    @Column(name = "updated_date")
    public ZonedDateTime updatedDate = ZonedDateTime.now();
}
