package br.com.bank.service;

import br.com.bank.model.Cliente;
import br.com.bank.model.Conta;
import br.com.bank.repository.IClienteRepository;
import br.com.bank.repository.IContaRepository;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ClienteService {

    private final IClienteRepository clienteRepository;
    private final IContaRepository contaRepository;

    public Cliente criarNovoUsuario(Cliente cliente) {
        if (clienteRepository.existsByCpf(cliente.getCpf())) {
            throw new RuntimeException(String.format("Customer with CPF %s already registered", cliente.getCpf()));
        }
        try {
            Cliente clienteNovo = clienteRepository.save(cliente);

            Conta conta = new Conta();
            conta.setAgencia("0001");
            conta.setNumero("4321");
            conta.setCliente(clienteNovo);

            contaRepository.save(conta);

            clienteNovo.setConta(conta);
            clienteRepository.save(clienteNovo);

            return clienteNovo;
        } catch (DataIntegrityViolationException ex) {
            throw new RuntimeException("Erro ao salvar cliente", ex);
        }
    }

    public List<Cliente> listClientes(){
        return clienteRepository.findAll();
    }

    public Cliente findClienteById(int id){
        return clienteRepository.findById(id).orElseThrow(
                () -> new RuntimeException(String.format("Client id %s not found", id)));
    }

    public Cliente findClienteByCpf(String cpf, String password){

        Cliente cliente = clienteRepository.findByCpf(cpf).orElseThrow(
                () -> new RuntimeException(String.format("Client CPF: %s not found", cpf)));

        if (!password.equals(cliente.getPassword())) {
            throw new RuntimeException("incorrect password " + "Senha fornecida: '" + password + "'" + "Senha do cliente: '" + cliente.getPassword() + "'");
        }

        return clienteRepository.findByCpf(cpf).orElseThrow(
                () -> new RuntimeException(String.format("Client CPF: %s not found", cpf)));
    }


    public Cliente findClienteByEmail(String email){
        return clienteRepository.findByEmail(email).orElseThrow(
                () -> new RuntimeException(String.format("Client email: %s not found", email)));
    }

    public Cliente findClienteByPhone(String phone){
        return clienteRepository.findByPhone(phone).orElseThrow(
                () -> new RuntimeException(String.format("Client phone: %s not found", phone)));
    }



}
