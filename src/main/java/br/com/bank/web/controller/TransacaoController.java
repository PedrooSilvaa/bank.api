package br.com.bank.web.controller;

import br.com.bank.model.Conta;
import br.com.bank.model.Transacao;
import br.com.bank.service.ContaService;
import br.com.bank.service.TransacaoService;
import br.com.bank.web.dto.ContaResponseDto;
import br.com.bank.web.dto.TransacaoResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/bank/transacao")
public class TransacaoController {

    private final TransacaoService transacaoService;
    private final ContaService contaService;

    @PostMapping("/pix/{idOrigem}/{numeroContaDestinatario}/{valor}")
    public ResponseEntity<TransacaoResponseDto> createTransacaoPix(@PathVariable int idOrigem, @PathVariable String numeroContaDestinatario, @PathVariable double valor){
        Transacao transacao = transacaoService.transacaoTipoPix(idOrigem, numeroContaDestinatario, valor);
        return ResponseEntity.ok(Transacao.toDto(transacao));
    }

    @PostMapping("/transferencia/{idOrigem}/{numeroContaDestinatario}/{valor}")
    public ResponseEntity<TransacaoResponseDto> createTransacaoTranferencia(@PathVariable int idOrigem, @PathVariable String numeroContaDestinatario, @PathVariable double valor){
        Transacao transacao = transacaoService.transacaoTipoTranferencia(idOrigem, numeroContaDestinatario, valor);
        return ResponseEntity.ok(Transacao.toDto(transacao));
    }

    @PostMapping("/saque/{idOrigem}/{idDestinatario}/{valor}")
    public ResponseEntity<TransacaoResponseDto> createTransacaoSaque(@PathVariable int idOrigem, @PathVariable int idDestinatario, @PathVariable double valor){
        Transacao transacao = transacaoService.transacaoTipoSaque(idOrigem, idDestinatario, valor);
        return ResponseEntity.ok(Transacao.toDto(transacao));
    }

    @PostMapping("/deposito/{idOrigem}/{idDestinatario}/{valor}")
    public ResponseEntity<TransacaoResponseDto> createTransacaoDeposito(@PathVariable int idOrigem, @PathVariable int idDestinatario, @PathVariable double valor){
        Transacao transacao = transacaoService.transacaoTipoDeposito(idOrigem, idDestinatario, valor);
        return ResponseEntity.ok(Transacao.toDto(transacao));
    }

    @GetMapping("list/origem/{id}")
        public ResponseEntity<List<TransacaoResponseDto>> listarTransacoesOrigem(@PathVariable int id){
        Conta conta = contaService.findByIdCliente(id);
        List<Transacao> transacaos = transacaoService.listarTransacoesByContaOrigem(conta);
        return ResponseEntity.ok(Transacao.toListDto(transacaos));
        }

    @GetMapping("list/destino/{id}")
    public ResponseEntity<List<TransacaoResponseDto>> listarTransacoesDestinatario(@PathVariable int id){
        Conta conta = contaService.findByIdCliente(id);
        List<Transacao> transacaos = transacaoService.listarTransacoesByContaDestinatario(conta);
        return ResponseEntity.ok(Transacao.toListDto(transacaos));
    }

}
