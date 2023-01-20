package com.nhom25.SportShop.verification.otp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OTPValidation {
    @Autowired
    private OTPService otpService;
    public Boolean validateOTP(String email, Integer otpReceived)
    {
        int serverOtp = otpService.getOtp(email);
        if(serverOtp > 0)
        {
            if(serverOtp == otpReceived)
            {
                otpService.clearOTP(email);
                return true;
            }
            else
                return false;
        }
        else
            return null;
    }
}
