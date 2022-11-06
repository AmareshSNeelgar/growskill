package com.platform.growskill.entity;

import com.platform.growskill.enums.PaymentStatus;
import com.platform.growskill.util.ZonedDateTimeAttributeConverter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
public class PaymentHistory {

    @Id
    @Column(name="payment_id")
    public Long paymentId;

    @Column(name="course_id")
    public  Long courseId;

    @Column(name="student_id")
    public  Long studentId;

    @Column(name = "amount")
    public Double amount;

    @Column(name = "reference_id")
    public String referenceId;

    @Enumerated(EnumType.STRING)
    @Column(name="status")
    public PaymentStatus status;

    @CreatedDate
    @Convert(converter = ZonedDateTimeAttributeConverter.class)
    @Column(name = "created_date")
    public ZonedDateTime createdDate = ZonedDateTime.now();


    @LastModifiedDate
    @Convert(converter = ZonedDateTimeAttributeConverter.class)
    @Column(name = "updated_date")
    public ZonedDateTime updatedDate = ZonedDateTime.now();
}
