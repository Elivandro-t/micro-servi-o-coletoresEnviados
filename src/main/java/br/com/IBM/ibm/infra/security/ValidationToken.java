package br.com.IBM.ibm.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.stereotype.Service;

@Service
public class ValidationToken {
    public String validarToken(String token){
        try {
            var algoritimo = Algorithm.HMAC256("123456");
            return JWT.require(algoritimo)
                    .withIssuer("17100150")
                    .build().verify(token).getSubject();
        } catch (JWTVerificationException exception){
            throw new RuntimeException("token invalido ",exception);
            // Invalid signature/claims
        }
    }
}
