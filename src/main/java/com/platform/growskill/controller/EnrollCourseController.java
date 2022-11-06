package com.platform.growskill.controller;

import com.platform.growskill.dto.EnRollPaymentResp;
import com.platform.growskill.dto.EnrollCourseDto;
import com.platform.growskill.dto.PaymentConfirmationDto;
import com.platform.growskill.service.EnRollCourseService;
import com.platform.growskill.util.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("course/enroll")
public class EnrollCourseController {

    @Autowired
    private EnRollCourseService enRollCourseService;

    @RequestMapping(value = "payment", method = RequestMethod.POST)
    public ResponseEntity<CommonResponse<EnRollPaymentResp>> initiatePayment(@RequestBody EnrollCourseDto body , @RequestHeader Long userId){
        CommonResponse<EnRollPaymentResp> resp = enRollCourseService.enRollCourse(body,userId);
        return new ResponseEntity< CommonResponse<EnRollPaymentResp>>(resp,HttpStatus.valueOf(resp.status));

    }

    @RequestMapping(value = "payment/{tnxId}/confirmation", method = RequestMethod.POST)
    public ResponseEntity<CommonResponse> paymentConfirmation(@PathVariable Long tnxId, @RequestBody PaymentConfirmationDto body){
        CommonResponse resp = enRollCourseService.paymentConfirmation(tnxId,body);
        return new ResponseEntity< CommonResponse>(resp,HttpStatus.valueOf(resp.status));

    }
}
