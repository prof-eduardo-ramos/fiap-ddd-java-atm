package br.com.fiap.bank.atm.presentation.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.bank.atm.application.ContaService;
import br.com.fiap.bank.atm.application.dto.ContaRequestDTO;
import br.com.fiap.bank.atm.application.dto.ContaResponseDTO;
import br.com.fiap.bank.atm.application.dto.MovimentacaoResponseDTO;
import br.com.fiap.bank.atm.application.dto.TransacaoRequestDTO;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/contas")
public class ContaController {

    private final ContaService contaService;

    public ContaController(ContaService contaService) {
        this.contaService = contaService;
    }

    @PostMapping
    public ResponseEntity<ContaResponseDTO> criarConta(@RequestBody ContaRequestDTO request) {
        ContaResponseDTO novaConta = contaService.cadastrarNovaConta(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaConta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContaResponseDTO> buscarConta(@PathVariable UUID id) {
        ContaResponseDTO dto = contaService.consultarConta(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<ContaResponseDTO>> buscarTodasAsContas() {
        List<ContaResponseDTO> contas = contaService.buscarTodas();
        return ResponseEntity.ok(contas);
    }

    @GetMapping("/{id}/movimentacoes")
    public ResponseEntity<List<MovimentacaoResponseDTO>> obterMovimentacoes(@PathVariable UUID id) {
        List<MovimentacaoResponseDTO> movimentacoes = contaService.consultarMovimentacoes(id);
        return ResponseEntity.ok(movimentacoes);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarConta(@PathVariable UUID id) {
        // contaService.deletarConta(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/deposito")
    public ResponseEntity<Void> realizarDeposito(@PathVariable UUID id, @RequestBody TransacaoRequestDTO request) {
        contaService.realizarDeposito(id, request.valor());
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/saque")
    public ResponseEntity<Void> realizarSaque(@PathVariable UUID id, @RequestBody TransacaoRequestDTO request) {
        contaService.realizarSaque(id, request.valor());
        return ResponseEntity.noContent().build();
    }

}
