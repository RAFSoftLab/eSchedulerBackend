//package com.eScheduler.eScheduler.filters;
//
//import com.eScheduler.eScheduler.model.UserLogin;
//import com.eScheduler.eScheduler.services.UserLoginService;
//import com.eScheduler.eScheduler.utils.JwtUtil;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//
//@Component
//public class JwtFilter extends OncePerRequestFilter {
//    private final UserLoginService userService;
//    private final JwtUtil jwtUtil;
//
//    public JwtFilter(UserLoginService userService, JwtUtil jwtUtil) {
//        this.userService = userService;
//        this.jwtUtil = jwtUtil;
//    }
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
//        String authHeader = httpServletRequest.getHeader("Authorization");
//        String jwt = null;
//        String username = null;
//
//        if(authHeader != null && authHeader.startsWith("Bearer ")) {
//            jwt = authHeader.substring(7);
//            username = jwtUtil.extractUsername(jwt);
//        }
//
////        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//
////            UserDetails userDetails = this.userService.(username);
//
////            if (jwtUtil.validateToken(jwt, userDetails)) {
//
////                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
////                        userDetails, null, userDetails.getAuthorities());
////                usernamePasswordAuthenticationToken
////                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
////                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
////            }
////        }
////        filterChain.doFilter(httpServletRequest, httpServletResponse);
//    }
//}
