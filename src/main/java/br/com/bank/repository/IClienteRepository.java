package br.com.bank.repository;

import br.com.bank.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IClienteRepository extends JpaRepository<Cliente, Integer> {

    boolean existsByCpf(String cpf);

    Optional<Cliente> findByCpf(String cpf);

    Optional<Cliente> findByPhone(String phone);

    Optional<Cliente> findByEmail(String email);

    void deleteByCpf(String cpf);


}
