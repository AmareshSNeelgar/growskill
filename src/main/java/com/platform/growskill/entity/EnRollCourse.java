package com.platform.growskill.entity;

import com.platform.growskill.enums.EnrollStatus;
import com.platform.growskill.util.ZonedDateTimeAttributeConverter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
public class EnRollCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @Column(name="student_id")
    public Long studentId;

    @Column(name="course_id")
    public Long courseId;

    @Enumerated(EnumType.STRING)
    @Column(name="status")
    public EnrollStatus status;

    @Column(name = "is_certified")
    public boolean isCertified = false;

    @CreatedDate
    @Convert(converter = ZonedDateTimeAttributeConverter.class)
    @Column(name = "created_date")
    public ZonedDateTime createdDate = ZonedDateTime.now();


    @LastModifiedDate
    @Convert(converter = ZonedDateTimeAttributeConverter.class)
    @Column(name = "updated_date")
    public ZonedDateTime updatedDate = ZonedDateTime.now();
}
