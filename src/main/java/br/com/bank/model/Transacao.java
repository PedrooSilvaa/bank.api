package br.com.bank.model;

import br.com.bank.web.dto.ContaResponseDto;
import br.com.bank.web.dto.TransacaoResponseDto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "transacoes")
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String tipo;
    private Double valor;
    private LocalDateTime data;

    @OneToOne
    @JoinColumn(name = "contaOrigem")
    private Conta contaOrigem;


    @OneToOne
    @JoinColumn(name = "contaDestinatario")
    private Conta contaDestinatario;

    public static TransacaoResponseDto toDto(Transacao transacao){
        TransacaoResponseDto dto = new TransacaoResponseDto();
        dto.setId(transacao.getId());
        dto.setTipo(transacao.getTipo());
        dto.setValor(transacao.getValor());
        dto.setData(transacao.getData());
        dto.setIdOrigem(transacao.getContaOrigem().getId());
        dto.setIdDestinatario(transacao.getContaDestinatario().getId());
        return dto;
    }

    public static List<TransacaoResponseDto> toListDto(List<Transacao> transacoes){
        List<TransacaoResponseDto> dtos = new ArrayList<>();
        for (Transacao transacao : transacoes){
            dtos.add(Transacao.toDto(transacao));
        }
        return dtos;
    }
}
