package com.nhom25.SportShop.controller;

import com.nhom25.SportShop.constant.SystemConstant;
import com.nhom25.SportShop.dto.OtpValidationDto;
import com.nhom25.SportShop.service.UserService;
import com.nhom25.SportShop.verification.email.EmailService;
import com.nhom25.SportShop.verification.otp.OTPService;
import com.nhom25.SportShop.verification.otp.OTPValidation;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity generateOTP(@ApiParam(value = "Email người dùng", required = true, example = "user@gmail.com")
                                          @RequestParam("email") String email) {
        try
        {
            Integer otp = otpService.generateOTP(email);
            emailService.sendOTP(email, otp);
            return ResponseEntity.ok("Generated OTP");
        }
        catch (MailException e)
        {
            e.printStackTrace();
        }
        return ResponseEntity.badRequest().body("Send OTP Failed");
    }

    @ApiOperation(value = "Xác thực OTP")
    @GetMapping("/validate")
    public ResponseEntity validateOTP(@RequestBody OtpValidationDto otpDto) {
        Boolean valid = otpValidation.validateOTP(otpDto.getEmail(), otpDto.getOtp());
        if(valid != null)
        {
            if(valid.equals(true))
            {
                otpService.clearOTP(otpDto.getEmail());
                return ResponseEntity.ok("Correct OTP");
            }
            else if(valid.equals(false))
                return ResponseEntity.status(502).body("Incorrect OTP");
        }
        else
            return ResponseEntity.status(503).body("OTP is expired");
        return ResponseEntity.badRequest().build();
    }
}
