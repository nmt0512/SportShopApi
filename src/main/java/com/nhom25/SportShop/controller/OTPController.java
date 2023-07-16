package com.nhom25.SportShop.controller;

import com.nhom25.SportShop.dto.OtpValidationDto;
import com.nhom25.SportShop.response.ResponseData;
import com.nhom25.SportShop.response.ResponseUtils;
import com.nhom25.SportShop.service.UserService;
import com.nhom25.SportShop.verification.email.EmailService;
import com.nhom25.SportShop.verification.otp.OTPService;
import com.nhom25.SportShop.verification.otp.OTPValidation;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(maxAge = 7200)
@RestController
@RequestMapping("/otp")
public class OTPController {
    @Autowired
    private EmailService emailService;
    @Autowired
    private OTPService otpService;
    @Autowired
    private OTPValidation otpValidation;
    @Autowired
    private UserService userService;

    @ApiOperation(value = "Tạo mã OTP")
    @PostMapping("/generate-otp")
    public ResponseEntity<ResponseData<Void>> generateOTP(@ApiParam(value = "Email người dùng", required = true, defaultValue = "user@gmail.com")
                                      @RequestParam("email") String email) {
        try {
            Integer otp = otpService.generateOTP(email);
            emailService.sendOTP(email, otp);
            return ResponseUtils.created();
        } catch (MailException e) {
            return ResponseUtils.error(501, "Send OTP failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Xác thực OTP")
    @PostMapping("/validate")
    public ResponseEntity<ResponseData<Void>> validateOTP(@RequestBody OtpValidationDto otpDto) {
        Boolean valid = otpValidation.validateOTP(otpDto.getEmail(), otpDto.getOtp());
        if (valid != null) {
            if (valid.equals(true)) {
                otpService.clearOTP(otpDto.getEmail());
                return ResponseUtils.success();
            } else if (valid.equals(false))
                return ResponseUtils.error(400, "Incorrect", HttpStatus.BAD_REQUEST);
        }
        return ResponseUtils.error(400, "Expired", HttpStatus.BAD_REQUEST);
    }
}
