package com.nhom10.webts.security.jwt;

import com.nhom10.webts.security.services.UserDetailsImpl;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
//cài đặt xác thực JWT (JSON Web Tokens).
@Component
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${app.jwtSecret}")
    private String jwtSecret;

    @Value("${app.jwtExpirationMs}")
    private int jwtExpirationMs;

    //nhận đối tượng authentication được Spring Security cung cấp sau khi xác thực và trả về một chuỗi token JWT.
    public String generateJwtToken(Authentication authentication) {

        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject((userPrincipal.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }
    //nhận một chuỗi token JWT và trả về tên người dùng được lưu trữ trong token.
    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    //nhận một chuỗi token JWT và kiểm tra tính hợp lệ của token.
    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }
}
//Đây là file cài đặt xác thực JWT (JSON Web Tokens).
//
//Code được chia thành 3 phần chính:
//1. Phần khai báo và cài đặt các thuộc tính cần thiết để tạo và kiểm tra JWT.
//private String jwtSecret;
//private int jwtExpirationMs;
//
//
//2. Phần tạo và xác thực token.
//    - Phương thức generateJwtToken() nhận đối tượng authentication được Spring Security cung cấp sau khi xác thực và trả về một chuỗi token JWT.
//    - Phương thức getUserNameFromJwtToken() nhận một chuỗi token JWT và trả về tên người dùng được lưu trữ trong token.
//    - Phương thức validateJwtToken() nhận một chuỗi token JWT và kiểm tra tính hợp lệ của token.
//public String generateJwtToken(Authentication authentication) { ... }
//public String getUserNameFromJwtToken(String token) { ... }
//public boolean validateJwtToken(String authToken) { ... }
//
//
//3. Phần cấu hình bổ sung cho các thuộc tính jwtSecret và jwtExpirationMs để sử dụng ở phần 1.
//@Value("${app.jwtSecret}")
//private String jwtSecret;
//
//@Value("${app.jwtExpirationMs}")
//private int jwtExpirationMs;
