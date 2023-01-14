package com.nhom25.SportShop.jwt;

import com.nhom25.SportShop.constant.SystemConstant;
import com.nhom25.SportShop.security.UserDetailsServiceImpl;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        String username = null;
        String jwtToken = null;
        if (request.getHeader(SystemConstant.AUTHORIZATION_HEADER) != null) {
            String requestTokenHeader = request.getHeader(SystemConstant.AUTHORIZATION_HEADER);
            if (SecurityContextHolder.getContext().getAuthentication() == null) {
                jwtToken = requestTokenHeader.substring(7);
                try {
                    username = jwtUtil.getUsernameFromToken(jwtToken);
                } catch (IllegalArgumentException | MalformedJwtException e) {
                    String url = request.getRequestURL().toString();
                    if(!request.getRequestURL().toString().contains("/home"))
                    {
                        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                        response.getOutputStream().println("jwt_wrong");
                    }
                    else
                    {
                        response.setStatus(HttpServletResponse.SC_OK);
                    }
                } catch (ExpiredJwtException e) {
                    if(request.getRequestURL().toString().contains("refresh-token"))
                    {
                        request.setAttribute("claims", e.getClaims());
                    }
                    else
                    {
                        response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
                        response.getOutputStream().println("jwt_expired");
                    }
                }
            }
        }

        if (username != null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encodedPassword = passwordEncoder.encode(userDetails.getPassword());
            if (jwtUtil.validateToken(jwtToken, userDetails)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails.getUsername(),
                                encodedPassword, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        chain.doFilter(request, response);
    }
}