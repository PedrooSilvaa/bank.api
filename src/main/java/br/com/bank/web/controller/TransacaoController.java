package br.com.bank.web.controller;

import br.com.bank.model.Conta;
import br.com.bank.model.Transacao;
import br.com.bank.service.ContaService;
import br.com.bank.service.TransacaoService;
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

    @PostMapping("/pix")
    public ResponseEntity<Transacao> createTransacaoPix(@RequestParam int idOrigem, int idDestinatario, double valor){
        Transacao transacao = transacaoService.transacaoTipoPix(idOrigem, idDestinatario, valor);
        return ResponseEntity.ok(transacao);
    }
    @PostMapping("/tranferencia")
    public ResponseEntity<Transacao> createTransacaoTranferencia(@RequestParam int idOrigem, int idDestinatario, double valor){
        Transacao transacao = transacaoService.transacaoTipoTranferencia(idOrigem, idDestinatario, valor);
        return ResponseEntity.ok(transacao);
    }

    @PostMapping("/saque")
    public ResponseEntity<Transacao> createTransacaoSaque(@RequestParam int idOrigem, int idDestinatario, double valor){
        Transacao transacao = transacaoService.transacaoTipoSaque(idOrigem, idDestinatario, valor);
        return ResponseEntity.ok(transacao);
    }

    @PostMapping("/deposito")
    public ResponseEntity<Transacao> createTransacaoDeposito(@RequestParam int idOrigem, int idDestinatario, double valor){
        Transacao transacao = transacaoService.transacaoTipoDeposito(idOrigem, idDestinatario, valor);
        return ResponseEntity.ok(transacao);
    }

    @GetMapping("list/origem/{id}")
        public List<Transacao> listarTransacoesOrigem(@PathVariable int id){
            Conta conta = contaService.findByIdCliente(id);
            List<Transacao> transacoes = transacaoService.listarTransacoesByContaDestinatario(conta);
            return transacoes;
        }

    @GetMapping("list/destino/{id}")
    public List<Transacao> listarTransacoesDestinatario(@PathVariable int id){
        Conta conta = contaService.findByIdCliente(id);
        List<Transacao> transacoes = transacaoService.listarTransacoesByContaDestinatario(conta);
        return transacoes;
    }

}
