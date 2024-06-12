package br.com.bank.service;

import br.com.bank.model.Cliente;
import br.com.bank.model.Conta;
import br.com.bank.model.Transacao;
import br.com.bank.repository.IClienteRepository;
import br.com.bank.repository.IContaRepository;
import br.com.bank.repository.ITransacaoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Service
public class TransacaoService {

    private final ITransacaoRepository transacaoRepository;
    private final IContaRepository contaRepository;
    private final IClienteRepository clienteRepository;

    public Transacao transacaoTipoPix(int idContaOrigem, int idContaDestinatario, double valor){
        Conta contaOrigem = contaRepository.findByClienteId(idContaOrigem);
        Conta contaDestinatario = contaRepository.findByClienteId(idContaDestinatario);

        contaOrigem.setSaldo(contaOrigem.getSaldo() - valor);
        contaDestinatario.setSaldo(contaDestinatario.getSaldo() + valor);

        contaRepository.save(contaOrigem);
        contaRepository.save(contaDestinatario);

        Cliente clienteDestinatario = clienteRepository.findById(contaDestinatario.getCliente().getId()).orElseThrow(
                () -> new RuntimeException("Cliente não encontrado!")
        );

        Transacao transacao = new Transacao();

        transacao.setTipo("Pix");
        transacao.setValor(valor);
        transacao.setContaOrigem(contaOrigem);
        transacao.setContaDestinatario(contaDestinatario);
        transacao.setData(LocalDateTime.now());

        return transacaoRepository.save(transacao);
    }


    public Transacao transacaoTipoTranferencia(int idContaOrigem, int idContaDestinatario, double valor){
        Conta contaOrigem = contaRepository.findByClienteId(idContaOrigem);
        Conta contaDestinatario = contaRepository.findByClienteId(idContaDestinatario);

        contaOrigem.setSaldo(contaOrigem.getSaldo() - valor);
        contaDestinatario.setSaldo(contaDestinatario.getSaldo() + valor);

        contaRepository.save(contaOrigem);
        contaRepository.save(contaDestinatario);

        Cliente clienteDestinatario = clienteRepository.findById(contaDestinatario.getCliente().getId()).orElseThrow(
                () -> new RuntimeException("Cliente não encontrado!")
        );

        Transacao transacao = new Transacao();

        transacao.setTipo("Tranferência");
        transacao.setValor(valor);
        transacao.setContaOrigem(contaOrigem);
        transacao.setContaDestinatario(contaDestinatario);
        transacao.setData(LocalDateTime.now());

        return transacaoRepository.save(transacao);
    }


    public Transacao transacaoTipoSaque(int idContaOrigem, int idContaDestinatario, double valor){
        Conta contaOrigem = contaRepository.findByClienteId(idContaOrigem);

        contaOrigem.setSaldo(contaOrigem.getSaldo() - valor);

        contaRepository.save(contaOrigem);

        Cliente cliente = clienteRepository.findById(contaOrigem.getCliente().getId()).orElseThrow(
                () -> new RuntimeException("Cliente não encontrado!")
        );

        Transacao transacao = new Transacao();

        transacao.setTipo("Saque");
        transacao.setValor(valor);
        transacao.setContaOrigem(contaOrigem);
        transacao.setContaDestinatario(contaOrigem);
        transacao.setData(LocalDateTime.now());

        return transacaoRepository.save(transacao);
    }

    public Transacao transacaoTipoDeposito(int idContaOrigem, int idContaDestinatario, double valor){
        Conta contaOrigem = contaRepository.findByClienteId(idContaOrigem);

        contaOrigem.setSaldo(contaOrigem.getSaldo() + valor);

        contaRepository.save(contaOrigem);

        Cliente cliente = clienteRepository.findById(contaOrigem.getCliente().getId()).orElseThrow(
                () -> new RuntimeException("Cliente não encontrado!")
        );

        Transacao transacao = new Transacao();

        transacao.setTipo("Saque");
        transacao.setValor(valor);
        transacao.setContaOrigem(contaOrigem);
        transacao.setContaDestinatario(contaOrigem);
        transacao.setData(LocalDateTime.now());

        return transacaoRepository.save(transacao);
    }

    public List<Transacao> listarTransacoesByContaOrigem(Conta conta){
        return transacaoRepository.findByContaOrigem(conta);
    }

    public List<Transacao> listarTransacoesByContaDestinatario(Conta conta){
        return transacaoRepository.findByContaDestinatario(conta);
    }

}
