package com.nhom25.SportShop.controller;

import com.nhom25.SportShop.dto.LoginDto;
import com.nhom25.SportShop.dto.LoginResponseDto;
import com.nhom25.SportShop.jwt.JwtUtil;
import com.nhom25.SportShop.security.UserDetailsServiceImpl;
import io.jsonwebtoken.impl.DefaultClaims;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Objects;

@CrossOrigin(allowedHeaders = "Authorization")
@RestController
public class AuthenticationController {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @ApiOperation(value = "Đăng nhập và lấy JWT")
    @PostMapping("/login")
    public ResponseEntity createAuthenticationToken(@RequestBody LoginDto loginDto) throws Exception {
        authenticate(loginDto.getUsername(), loginDto.getPassword());
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginDto.getUsername());
        String token = jwtUtil.generateToken(userDetails);
        if (userDetails.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN")))
            return ResponseEntity.ok(new LoginResponseDto(token, true));
        return ResponseEntity.ok(new LoginResponseDto(token, false));
    }

    @ApiOperation(value = "Refresh JWT")
    @GetMapping("/refresh-token")
    public ResponseEntity refreshAuthenticationToken(HttpServletRequest request) {
        DefaultClaims claims = (DefaultClaims) request.getAttribute("claims");
        Map<String, Object> claimsMap = JwtUtil.getMapFromClaims(claims);
        String token = jwtUtil.doGenerateRefreshToken(claimsMap, claims.getSubject());
        return ResponseEntity.ok(new LoginResponseDto(token));
    }


    private void authenticate(String username, String password) throws Exception {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);
        try {
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(username, password);
            Authentication auth = authenticationManager.authenticate(authenticationToken);
            SecurityContextHolder.getContext().setAuthentication(auth);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
