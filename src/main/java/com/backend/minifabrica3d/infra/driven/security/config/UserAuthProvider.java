package com.backend.minifabrica3d.infra.driven.security.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.backend.minifabrica3d.domain.model.User;
import com.backend.minifabrica3d.infra.driven.jpa.entities.Role;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class UserAuthProvider {

    @Value("${security.jwt.token.secret-key:secret-key}")
    private String secretKey;

    @PostConstruct
    public void init(){
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken( User user ) {
        Date now = new Date();
        Date validity = new Date( now.getTime() + 10_800_000 ); //Expira en 3hs
        String[] arrayRoles = { user.getRol().name() };

        return JWT.create()
                .withIssuer( user.getEmail() )
                .withIssuedAt( now )
                .withExpiresAt( validity )
                .withClaim("username", user.getUsername())
                .withClaim("id", user.getId())
                .withArrayClaim("roles", arrayRoles)
                .sign(Algorithm.HMAC256(secretKey));

    }

    public Authentication validityToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        JWTVerifier verifier = JWT.require(algorithm)
                .build();

        DecodedJWT decoded = verifier.verify(token);

        Set<SimpleGrantedAuthority> roles = Arrays.stream(decoded.getClaim( "roles" ).asArray( String.class ))
                .map( rol -> Role.valueOf(rol).getAuthority() )
                .collect( Collectors.toSet() );

        User user = User.builder()
                .email( decoded.getSubject() )
                .username( decoded.getClaim( "username" ).asString() )
                .id( decoded.getClaim("id").asInt() )
                .build();

        return new UsernamePasswordAuthenticationToken( user, null, roles );
    }



}
