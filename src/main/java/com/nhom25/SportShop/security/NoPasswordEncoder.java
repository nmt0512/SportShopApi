//package com.nhom25.SportShop.security;
//
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//public class NoPasswordEncoder implements PasswordEncoder {
//
//	@Override
//	public String encode(CharSequence rawPassword) {
//		return rawPassword.toString();
//	}
//
//	@Override
//	public boolean matches(CharSequence rawPassword, String encodedPassword) {
//		return rawPassword.toString().equals(encodedPassword);
//	}
//
//	public static PasswordEncoder getInstance() {
//        return INSTANCE;
//    }
//
//    private static final PasswordEncoder INSTANCE = new NoPasswordEncoder();
//
//    private NoPasswordEncoder() {
//    }
//}
