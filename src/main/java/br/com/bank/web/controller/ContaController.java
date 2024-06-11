package br.com.bank.web.controller;

import br.com.bank.model.Cliente;
import br.com.bank.model.Conta;
import br.com.bank.service.ContaService;
import br.com.bank.web.dto.ClienteResponseDto;
import br.com.bank.web.dto.ContaResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/bank/contas")
public class ContaController {

    private final ContaService contaService;

    @GetMapping("/list")
    public ResponseEntity<List<ContaResponseDto>> findAll(){
        List<Conta> contas = contaService.listConta();
        return ResponseEntity.ok(Conta.toListDto(contas));
    }

    @GetMapping("/numero/{numero}")
    public ResponseEntity<ContaResponseDto> findByConta(@PathVariable String numero, @RequestParam String password){
        Conta conta = contaService.findByContaByNumero(numero, password);
        return ResponseEntity.ok(Conta.toDto(conta));
    }

    @GetMapping("/cliente/id/{id}")
    public ResponseEntity<ContaResponseDto> findContaByIdCliente(@PathVariable int id){
        Conta conta = contaService.findByIdCliente(id);
        return ResponseEntity.ok(Conta.toDto(conta));
    }
}
