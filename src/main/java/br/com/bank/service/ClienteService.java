package br.com.bank.service;

import br.com.bank.model.Cliente;
import br.com.bank.repository.IClienteRepository;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ClienteService {

    private final IClienteRepository clienteRepository;

    public Cliente criarNovoUsuario(Cliente cliente) {
        if (clienteRepository.existsByCpf(cliente.getCpf())) {
            throw new RuntimeException(String.format("Customer with CPF %s already registered", cliente.getCpf()));
        }
        try {
            return clienteRepository.save(cliente);
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

        System.out.println(password);
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
