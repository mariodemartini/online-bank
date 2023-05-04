package br.com.geradordedevs.onlinebank.services.impl;

import br.com.geradordedevs.onlinebank.exceptions.TokenException;
import br.com.geradordedevs.onlinebank.exceptions.enums.TokenEnum;
import br.com.geradordedevs.onlinebank.services.LoginService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class LoginServiceImpl implements LoginService {

    private String secret = "C#R6x4%80VCy";
    private String issuer = "online-bank";

    @Override
    public String generateToken(String email) {
        log.info("generating JWT token for email {}", email);

        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.create()
                    .withIssuer(issuer)
                    .withClaim("sub", email)
                    .withExpiresAt(new Date(Instant.now().toEpochMilli() + TimeUnit.HOURS.toMillis(1)))//1h
                    .sign(algorithm);

        } catch (JWTCreationException exception) {
            log.warn("error trying to generate JWT token");
            throw new TokenException(TokenEnum.INVALID_TOKEN);
        }
    }
    @Override
    public void validate(String token) {
        if (token == null) {
            log.warn("required token");
            throw new TokenException(TokenEnum.REQUIRED_TOKEN);
        }

        log.info("validating token");
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(issuer)
                    .build();
            DecodedJWT jwt = verifier.verify(token);

            String email = jwt.getClaim("sub").asString();
            Date dataExpiracao = jwt.getExpiresAt();

            validateExpirationToken(token, dataExpiracao);

        } catch (JWTVerificationException exception) {
            log.warn("validate token fail");
            throw new TokenException(TokenEnum.INVALID_TOKEN);
        }
    }

    private void validateExpirationToken(String token, Date expiresAt) {
        log.info("checking token expiration {}", token);
        if (expiresAt.before(new Date())) {
            log.warn("Token {} is expired", token);
            throw new TokenException(TokenEnum.EXPIRED_TOKEN);
        }
    }

}
