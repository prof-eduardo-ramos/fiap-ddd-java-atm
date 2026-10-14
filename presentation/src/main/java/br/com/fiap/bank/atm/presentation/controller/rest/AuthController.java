package br.com.fiap.bank.atm.presentation.controller.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.bank.atm.presentation.dto.auth.LoginClientRequestDTO;
import br.com.fiap.bank.atm.presentation.dto.auth.TokenResponseDTO;
import br.com.fiap.bank.atm.presentation.service.TokenService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final TokenService tokenService;

    public AuthController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping("/token")
    public ResponseEntity<TokenResponseDTO> emitirToken(@Valid @RequestBody LoginClientRequestDTO request) {
        TokenResponseDTO token = tokenService.autenticarEGerarToken(request);
        return ResponseEntity.ok(token);
    }
}
