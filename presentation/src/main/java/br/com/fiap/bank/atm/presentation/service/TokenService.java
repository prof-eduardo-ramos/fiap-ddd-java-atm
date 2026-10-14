package br.com.fiap.bank.atm.presentation.service;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import br.com.fiap.bank.atm.presentation.dto.auth.LoginClientRequestDTO;
import br.com.fiap.bank.atm.presentation.dto.auth.TokenResponseDTO;

@Service
public class TokenService {

    private final JwtEncoder jwtEncoder;

    @Value("${fiap.security.jwt.expiration-seconds}")
    private Long validadeSegundos;

    @Value("${fiap.security.oauth2.client.client-id}")
    private String configuredClientId;

    @Value("${fiap.security.oauth2.client.client-secret}")
    private String configuredClientSecret;

    public TokenService(JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;
    }

    public TokenResponseDTO autenticarEGerarToken(LoginClientRequestDTO request) {
        // Validação rigorosa do Client ID e Client Secret informados
        if (!configuredClientId.equals(request.clientId()) ||
                !configuredClientSecret.equals(request.clientSecret())) {
            throw new BadCredentialsException("ClientId ou ClientSecret inválidos.");
        }

        Instant agora = Instant.now();
        Instant expiracao = agora.plusSeconds(validadeSegundos);

        // Montagem do conjunto de Claims do JWT com a autoridade ADMIN
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("fiap-bank-atm-auth-server")
                .issuedAt(agora)
                .expiresAt(expiracao)
                .subject(request.clientId())
                // O prefixo 'scope' ou 'roles' mapeia para GrantedAuthority no Resource Server
                .claim("scope", "ADMIN")
                .claim("roles", "ROLE_ADMIN")
                .build();

        // Assinatura digital com JWS Algorithm HS256
        JwsHeader header = JwsHeader.with(() -> "HS256").build();
        String jwtToken = this.jwtEncoder.encode(JwtEncoderParameters.from(header, claims)).getTokenValue();

        return new TokenResponseDTO(
                jwtToken,
                "Bearer",
                validadeSegundos,
                "ADMIN");
    }
}
