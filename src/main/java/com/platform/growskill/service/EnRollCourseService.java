package com.platform.growskill.service;

import com.platform.growskill.dao.PaymentHistoryDao;
import com.platform.growskill.dto.EnRollPaymentResp;
import com.platform.growskill.dto.EnrollCourseDto;
import com.platform.growskill.dto.PaymentConfirmationDto;
import com.platform.growskill.entity.Course;
import com.platform.growskill.entity.EnRollCourse;
import com.platform.growskill.entity.PaymentHistory;
import com.platform.growskill.entity.User;
import com.platform.growskill.enums.EnrollStatus;
import com.platform.growskill.enums.PaymentStatus;
import com.platform.growskill.repo.CourseRepo;
import com.platform.growskill.repo.EnrollCourseRepo;
import com.platform.growskill.repo.PaymentHistoryRepo;
import com.platform.growskill.repo.UserRepo;
import com.platform.growskill.service.mapper.EnrollCourseMapper;
import com.platform.growskill.util.CommonResponse;
import com.platform.growskill.util.MessageCodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EnRollCourseService {

    @Autowired
    private EnrollCourseRepo enrollCourseRepo;

    @Autowired
    private PaymentHistoryRepo paymentHistoryRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PaymentHistoryDao paymentHistoryDao;

    @Autowired
    private CourseRepo courseRepo;


    public CommonResponse<EnRollPaymentResp> enRollCourse(EnrollCourseDto body, Long userid) {
        Optional<User> oldObj = userRepo.findById(userid);
        if (oldObj.isPresent()) {
            return new CommonResponse(HttpStatus.BAD_REQUEST.value(), null, MessageCodes.EMAIL_ALREADY_EXIST);
        }

        Optional<EnRollCourse> enCourseData = enrollCourseRepo.getByStudentId(userid, body.courseId);
        if (enCourseData.isPresent()) {
            PaymentHistory phResp = paymentHistoryDao.getLatestPaymentByStudentId(userid, body.courseId).get(0);
            EnRollPaymentResp eRep = new EnRollPaymentResp();
            eRep.tnxId = phResp.paymentId;
            eRep.status = phResp.status;
            return new CommonResponse<EnRollPaymentResp>(HttpStatus.BAD_REQUEST.value(), eRep, MessageCodes.USER_ALREADY_ENROLLED);
        }


        Optional<Course> cResp = courseRepo.findById(body.courseId);
        if (cResp.isEmpty()) {
            return new CommonResponse(HttpStatus.BAD_REQUEST.value(), null, MessageCodes.INVALID_COURSE_ID);
        }

        // Adding Enrollment Record
        EnRollCourse enRoll = EnrollCourseMapper.AddEnroll(body, userid);
        enrollCourseRepo.save(enRoll);
        // Adding Payment History
        PaymentHistory ph = EnrollCourseMapper.addPayment(body, userid);
        PaymentHistory phResp = paymentHistoryRepo.save(ph);

        EnRollPaymentResp eRep = new EnRollPaymentResp();
        eRep.tnxId = phResp.paymentId;
        eRep.status = phResp.status;
        return new CommonResponse<EnRollPaymentResp>(HttpStatus.OK.value(), eRep, MessageCodes.PAYMENT_INITIATED_SUCCESS);
    }


    public CommonResponse paymentConfirmation(Long tnxId, PaymentConfirmationDto body) {

        Optional<PaymentHistory> ph = paymentHistoryRepo.findById(tnxId);
        if(ph.isEmpty()){
            return new CommonResponse(HttpStatus.OK.value(), null, MessageCodes.INVALID_PAYMENT_ID);
        }
        PaymentHistory uData = ph.get();
        EnRollCourse ecData = enrollCourseRepo.findById(uData.courseId).get();

        if(uData.status == PaymentStatus.INITIATED){

            switch (body.status) {
                case FAILED : {
                    ecData.status = EnrollStatus.CERTIFIED_FAILED;
                    uData.status = PaymentStatus.FAILED;
                    break;
                }
                case PENDING : {
                    ecData.status = EnrollStatus.PAYMENT_FAILED;
                    uData.status = PaymentStatus.PENDING;
                    break;
                }
                case SUCCESS: {
                    ecData.status = EnrollStatus.ENROLLED;
                    uData.status = PaymentStatus.SUCCESS;
                    break;
                }
            }
            paymentHistoryRepo.save(uData);
            enrollCourseRepo.save(ecData);
            return new CommonResponse(HttpStatus.OK.value(),null, MessageCodes.PAYMENT_CONFIRMATION_UPDATED);
        } else {
            return new CommonResponse(HttpStatus.BAD_REQUEST.value(),null, MessageCodes.PAYMENT_ALREADY_PROCESSED);
        }

    }
}
