package br.com.bank.repository;

import br.com.bank.model.Conta;
import br.com.bank.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ITransacaoRepository extends JpaRepository<Transacao, Integer> {

    List<Transacao> findByContaOrigem(Conta conta);

    List<Transacao> findByContaDestinatario(Conta conta);
}
