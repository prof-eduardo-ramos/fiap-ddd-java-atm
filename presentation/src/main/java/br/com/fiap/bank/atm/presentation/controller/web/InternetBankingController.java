package br.com.fiap.bank.atm.presentation.controller.web;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.fiap.bank.atm.application.ContaService;
import br.com.fiap.bank.atm.application.dto.ContaResponseDTO;
import br.com.fiap.bank.atm.application.dto.MovimentacaoResponseDTO;
import br.com.fiap.bank.atm.presentation.dto.form.OperacaoFormDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/internet-banking")
public class InternetBankingController {

    private final ContaService contaService;

    public InternetBankingController(ContaService contaService) {
        this.contaService = contaService;
    }

    @GetMapping
    public String index(Model model) {
        List<ContaResponseDTO> contas = contaService.buscarTodas();
        model.addAttribute("contas", contas);
        return "index";
    }

    @GetMapping("/{idConta}")
    public String dashboard(Model model, @PathVariable UUID idConta) {
        // Consultando os dados da conta
        ContaResponseDTO conta = contaService.consultarConta(idConta);
        List<MovimentacaoResponseDTO> extrato = contaService.consultarMovimentacoes(idConta);
        model.addAttribute("conta", conta);
        model.addAttribute("extrato", extrato);
        return "dashboard";
    }

    @GetMapping("/{idConta}/operacao")
    public String exibirFormularioOperacao(Model model, @PathVariable UUID idConta) {
        OperacaoFormDTO formDTO = new OperacaoFormDTO();
        // Consultando os dados da conta
        ContaResponseDTO conta = contaService.consultarConta(idConta);
        formDTO.setIdConta(idConta);
        model.addAttribute("conta", conta);
        model.addAttribute("operacaoForm", formDTO);
        return "operacao"; // Retorna templates/operacao.html
    }

    @PostMapping("/{idConta}/operacao")
    public String processarOperacao(@ModelAttribute("operacaoForm") OperacaoFormDTO operacaoForm,
            @PathVariable UUID idConta,
            BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {

        // Consultando os dados da conta
        ContaResponseDTO conta = contaService.consultarConta(idConta);
        // operacaoForm.setIdConta(idConta);
        model.addAttribute("conta", conta);
        model.addAttribute("operacaoForm", operacaoForm);

        // Se o Bean Validation encontrar erros, devolvemos a mesma view HTML
        if (bindingResult.hasErrors()) {
            return "operacao";
        }

        try {

            if ("SAQUE".equalsIgnoreCase(operacaoForm.getTipoOperacao())) {
                // Utilizando o método realizarSaque do ContaService
                contaService.realizarSaque(idConta, operacaoForm.getValor());
                redirectAttributes.addFlashAttribute("mensagemSucesso", "Saque realizado com sucesso!");
            } else if ("DEPOSITO".equalsIgnoreCase(operacaoForm.getTipoOperacao())) {
                // Utilizando o método realizarDeposito do ContaService
                contaService.realizarDeposito(idConta, operacaoForm.getValor());
                redirectAttributes.addFlashAttribute("mensagemSucesso", "Depósito realizado com sucesso!");
            }
        } catch (Exception e) {
            model.addAttribute("mensagemErro", e.getMessage());
            return "operacao";
        }

        // REDIRECT (Evita duplo-submit se o usuário apertar F5)
        return "redirect:/internet-banking/{idConta}";
    }

}
