package com.cg.ecommerceCandyshop.backend.infraestruture.jwt;

import com.cg.ecommerceCandyshop.backend.infraestruture.service.CustomUserDetailService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import static com.cg.ecommerceCandyshop.backend.infraestruture.jwt.Constants.*;
@Slf4j
public class JWTValidate {
    /// valida que el token venga en la peticion
    public static boolean tokenExists(HttpServletRequest request, HttpServletResponse response){
        String header = request.getHeader(HEADER_AUTHORIZATION);
    log.info("Header: {}", header);
        if (header == null || !header.startsWith(TOKEN_BEARER_PREFIX) )
            return false;
        return true;
    }

    //vlida que el token sea el correcto
    public static Claims JWTValid(HttpServletRequest request){
        String jwtToken = request.getHeader(HEADER_AUTHORIZATION).replace(TOKEN_BEARER_PREFIX, "");
        log.info("JWT Token: {}", jwtToken);
        try { Claims claims = Jwts.parserBuilder() .setSigningKey(getSignedKey(SUPER_SECRET_KEY))
                .build() .parseClaimsJws(jwtToken).getBody(); log.info("Claims: {}", claims); return claims; }
        catch (JwtException e) { log.error("Error parsing JWT Token: {}", e.getMessage()); throw e; }
    }

    // autenticar al usuario en el flujo spring
    public static  void setAuthetication(Claims claims, CustomUserDetailService customUserDetailService){
        UserDetails userDetails = customUserDetailService.loadUserByUsername(claims.getSubject());
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

}