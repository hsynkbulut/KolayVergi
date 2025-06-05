package com.kolayvergi.security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;
import io.jsonwebtoken.security.SignatureException;

import java.security.Key;
import java.util.Date;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtService {

    @Value("${app.jwt.secret}")
    private String jwtSecret;

    @Value("${app.jwt.expiration}")
    private int jwtExpirationInMs;

    @Value("${app.jwt.refresh-expiration}")
    private int jwtRefreshExpirationInMs;

    private Key key;
    private final MessageSource messageSource;

    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }

    public String generateToken(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(key)
                .compact();
    }

    public String generateRefreshToken(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtRefreshExpirationInMs);

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(key)
                .compact();
    }

    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (SignatureException ex) {
            log.error(messageSource.getMessage("auth.jwt_signature_invalid", null, LocaleContextHolder.getLocale()));
        } catch (MalformedJwtException ex) {
            log.error(messageSource.getMessage("auth.jwt_invalid", null, LocaleContextHolder.getLocale()));
        } catch (ExpiredJwtException ex) {
            log.error(messageSource.getMessage("auth.jwt_expired", null, LocaleContextHolder.getLocale()));
        } catch (UnsupportedJwtException ex) {
            log.error(messageSource.getMessage("auth.jwt_unsupported", null, LocaleContextHolder.getLocale()));
        } catch (IllegalArgumentException ex) {
            log.error(messageSource.getMessage("auth.jwt_claims_empty", null, LocaleContextHolder.getLocale()));
        }
        return false;
    }
} 