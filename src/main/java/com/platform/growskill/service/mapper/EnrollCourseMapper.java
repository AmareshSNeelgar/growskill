package com.platform.growskill.service.mapper;

import com.platform.growskill.dto.EnrollCourseDto;
import com.platform.growskill.entity.EnRollCourse;
import com.platform.growskill.entity.PaymentHistory;
import com.platform.growskill.enums.EnrollStatus;
import com.platform.growskill.enums.PaymentStatus;

public class EnrollCourseMapper {

    private EnrollCourseMapper() {}

    public static final EnRollCourse AddEnroll(EnrollCourseDto body, Long userId){
        EnRollCourse eCourse = new EnRollCourse();
        eCourse.courseId = body.courseId;
        eCourse.studentId = userId;
        eCourse.status = EnrollStatus.PAYMENT_INITIATED;
        return eCourse;
    }

    public static final PaymentHistory addPayment(EnrollCourseDto body, Long userId){
        PaymentHistory ph = new PaymentHistory();
        ph.courseId = body.courseId;
        ph.studentId = userId;
        ph.status = PaymentStatus.INITIATED;
        ph.amount = body.amount;

        return ph;

    }
}
