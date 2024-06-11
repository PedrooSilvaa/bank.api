package br.com.bank.service;

import br.com.bank.model.Cliente;
import br.com.bank.model.Conta;
import br.com.bank.repository.IClienteRepository;
import br.com.bank.repository.IContaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ContaService {

    private final IContaRepository contaRepository;
    private final IClienteRepository clienteRepository;

    public List<Conta> listConta(){
        return contaRepository.findAll();
    }

    public Conta findByContaByNumero(String numero, String password) {

        Conta conta = contaRepository.findByNumero(numero).orElseThrow(
                () -> new RuntimeException(String.format("Conta número: %s not found", numero)));

        Cliente cliente = clienteRepository.findById(conta.getCliente().getId()).orElseThrow(
                () -> new RuntimeException(String.format("Client Id: %s not found", conta.getCliente().getId())));


        if (!password.equals(cliente.getPassword())) {
            throw new RuntimeException("incorrect password ");
        }

        return conta;
    }

    public Conta findByIdCliente(int id){
        return contaRepository.findByClienteId(id);
    }

}
