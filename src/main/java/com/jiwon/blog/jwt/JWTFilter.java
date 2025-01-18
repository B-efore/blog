package com.jiwon.blog.jwt;

import com.jiwon.blog.dto.request.CustomUserDetails;
import com.jiwon.blog.service.CustomUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JWTFilter extends OncePerRequestFilter {

    private final CustomUserDetailsService customUserDetailsService;
    private final JWTUtil jwtUtil;

    public JWTFilter(CustomUserDetailsService customUserDetailsService, JWTUtil jwtUtil) {
        this.customUserDetailsService = customUserDetailsService;
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = getTokenFromRequest(request);

        if(StringUtils.hasText(token) && !jwtUtil.isExpired(token)) {

            String email = jwtUtil.getEmail(token);
            CustomUserDetails userDetails = (CustomUserDetails) customUserDetailsService.loadUserByUsername(email);

            Authentication authToken = new UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    userDetails.getAuthorities()
            );

            SecurityContextHolder.getContext().setAuthentication(authToken);
        }

        filterChain.doFilter(request, response);
    }

    private String getTokenFromRequest(HttpServletRequest request)
    {
        String bearerToken = request.getHeader("Authorization");

        if(bearerToken.startsWith("Bearer ") && StringUtils.hasText(bearerToken)) {
            return bearerToken.substring(7);
        }

        return null;
    }
}
