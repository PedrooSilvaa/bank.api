package br.com.bank.repository;

import br.com.bank.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IContaRepository extends JpaRepository<Conta, Integer> {
    Optional<Conta> findByNumero(String numero);
    Conta findByClienteId(int clienteId);

}
