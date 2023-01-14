package com.nhom25.SportShop.verification.otp;

import com.nhom25.SportShop.constant.SystemConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OTPValidation {
    @Autowired
    private OTPService otpService;
    public String validateOTP(String email, Integer otpReceived)
    {
        int serverOtp = otpService.getOtp(email);
        if(serverOtp > 0)
        {
            if(serverOtp == otpReceived)
            {
                otpService.clearOTP(email);
                return SystemConstant.OTP_ACCEPT;
            }
            else
                return SystemConstant.OTP_REJECT;
        }
        else
            return SystemConstant.OTP_EXPIRED;
    }
}
