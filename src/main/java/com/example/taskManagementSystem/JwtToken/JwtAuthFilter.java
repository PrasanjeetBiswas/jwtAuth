package com.example.taskManagementSystem.JwtToken;

import java.io.IOException;
import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.lang.Collections;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter{

    private final jwtToken jToken;

    JwtAuthFilter(jwtToken jToken){
        this.jToken = jToken;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
                // Authorization header lo
            String authHeader =     request.getHeader("Authorization");
            String token = null;
            String email = null;

            // check kro bearer token hai ya nahi
            if(authHeader!=null && authHeader.startsWith("Bearer ")){
                // remove bearer
                token = authHeader.substring(7);
                email = jToken.extract(token);
            }

            if(email!=null && SecurityContextHolder.getContext().getAuthentication() == null){
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email,null,Collections.emptyList());
                // db email se verify hoga
                // identifier, password always = null, role

                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }

            filterChain.doFilter(request, response);
    }

}
