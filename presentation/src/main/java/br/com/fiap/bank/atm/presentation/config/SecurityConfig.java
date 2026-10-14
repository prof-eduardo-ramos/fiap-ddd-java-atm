package br.com.fiap.bank.atm.presentation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    /**
     * Conversor customizado para garantir que as claims de scope/roles
     * do JWT sejam convertidas com o prefixo 'ROLE_' e 'SCOPE_'.
     */
    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter grantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();

        // Mapeia tanto claim "scope" quanto aceita a authority padrão
        grantedAuthoritiesConverter.setAuthorityPrefix("ROLE_");
        grantedAuthoritiesConverter.setAuthoritiesClaimName("scope");

        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(grantedAuthoritiesConverter);
        return jwtAuthenticationConverter;
    }

    /**
     * Cadeia de filtros principal da API REST (/api/**).
     */
    @Bean
    @Order(1)
    public SecurityFilterChain apiSecurityFilterChain(HttpSecurity http) throws Exception {
        return http
                .securityMatcher("/api/**")
                // 1. Desativa CSRF (indesejado e dispensável em APIs REST com tokens Bearer)
                .csrf(csrf -> csrf.disable())

                // 2. Transforma o contexto em 100% STATELESS (sem cookies de sessão JSESSIONID)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // 3. Matriz de Autorização por URL
                .authorizeHttpRequests(auth -> auth
                        // Rota de emissão de tokens é pública
                        .requestMatchers("/api/auth/**").permitAll()
                        // Todas as demais operações exigem explicitamente a Role ADMIN
                        .requestMatchers("/api/contas/**").hasRole("ADMIN")
                        .anyRequest().authenticated())

                // 4. Habilita o suporte a OAuth2 Resource Server com processador JWT nativo
                .oauth2ResourceServer(
                        oauth2 -> oauth2.jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter())))

                .build();
    }

    /**
     * Cadeia de filtros para o Console H2 e navegação de suporte.
     */
    @Bean
    @Order(2)
    public SecurityFilterChain webSecurityFilterChain(HttpSecurity http) throws Exception {
        return http
                .securityMatcher("/h2-console/**", "/internet-banking/**")
                .csrf(csrf -> csrf.disable())
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/h2-console/**").permitAll()
                        .requestMatchers("/internet-banking/**").permitAll()
                        .anyRequest().permitAll())
                .build();
    }
}
