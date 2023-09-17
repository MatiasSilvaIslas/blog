package com.matias.blog.security.jwt;

import com.matias.blog.security.entity.MainUser;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtProvider {
    private final static Logger logger = LoggerFactory.getLogger(JwtProvider.class);
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private int expiration;

    public String generateToken(Authentication authentication){
        MainUser mainUser = (MainUser) authentication.getPrincipal();
        List<String> roles = mainUser.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        return Jwts.builder()
                .setSubject(mainUser.getUsername())
                .claim("roles",roles)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + expiration *1000))
                .signWith(getSecret(secret))
                .compact();
    }
    public String getUserNameFromToken(String token){
        return Jwts.parserBuilder().setSigningKey(getSecret(secret)).build().parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateToken(String token){
        try{
            Jwts.parserBuilder().setSigningKey(getSecret(secret)).build().parseClaimsJws(token);
            return true;
        }catch (MalformedJwtException e){
            logger.error("malformed token");
        }catch (UnsupportedJwtException e){
            logger.error("unsupported token");
        }catch (ExpiredJwtException e){
            logger.error("Expirated token");
        }catch (IllegalArgumentException e){
            logger.error("empty token");
        }catch (SignatureException e){
            logger.error("signature failure");
        }
        return false;
    }

//    public String refreshToken(JwtDto jwtDto) throws ParseException {
//        try {
//            Jwts.parserBuilder().setSigningKey(getSecret(secret)).build().parseClaimsJws(jwtDto.getToken());
//        } catch (ExpiredJwtException e) {
//            JWT jwt = JWTParser.parse(jwtDto.getToken());
//            JWTClaimsSet claims = jwt.getJWTClaimsSet();
//            String nombreUsuario = claims.getSubject();
//            List<String> roles = (List<String>) claims.getClaim("roles");
//
//            return Jwts.builder()
//                    .setSubject(nombreUsuario)
//                    .claim("roles", roles)
//                    .setIssuedAt(new Date())
//                    .setExpiration(new Date(new Date().getTime() + expiration))
//                    .signWith(getSecret(secret))
//                    .compact();
//        }
//        return null;
//    }

    private Key getSecret(String secret){
        byte[] secretBytes = Decoders.BASE64URL.decode(secret);
        return Keys.hmacShaKeyFor(secretBytes);
    }
}
