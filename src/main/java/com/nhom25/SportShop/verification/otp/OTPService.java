package com.nhom25.SportShop.verification.otp;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class OTPService {
    private static final Integer EXPIRE_MINS = 10;
    private LoadingCache<String, Integer> otpCache;
    public OTPService(){
        super();
        otpCache = CacheBuilder.newBuilder().
                expireAfterWrite(EXPIRE_MINS, TimeUnit.MINUTES)
                .build(new CacheLoader<String, Integer>() {
                    public Integer load(String key) {
                        return 0;
                    }
                });
    }

    public Integer generateOTP(String key){
        Random random = new Random();
        Integer otp = 100000 + random.nextInt(900000);
        otpCache.put(key, otp);
        return otp;
    }

    public Integer getOtp(String key){
        try{
            return otpCache.get(key);
        }
        catch (Exception e){
            return 0;
        }
    }

    public void clearOTP(String key){
        otpCache.invalidate(key);
    }

}
