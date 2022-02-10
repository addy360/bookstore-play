package services;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Map;

public class JwtService {
    Algorithm algorithm;

    public JwtService() {
        this.algorithm = Algorithm.HMAC256("Super secret work");
    }

    public String generateJwt(Map payload) {
        String token = "";
        try {
            token = JWT.create()
                    .withPayload(payload)
                    .sign(algorithm);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return token;
    }

    public DecodedJWT verifyJwt(String token) {
        try {
            JWTVerifier verifier = JWT.require(algorithm).build();
            return verifier.verify(token);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
