package br.com.bank.model;

import br.com.bank.web.dto.ContaResponseDto;
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

    public static Transacao toDto(Transacao transacao){
        Transacao dto = new Transacao();
        dto.setId(transacao.getId());
        dto.setTipo(transacao.getTipo());
        dto.setValor(transacao.getValor());
        dto.setData(transacao.getData());
        return dto;
    }

    public static List<Transacao> toListDto(List<Transacao> transacoes){
        List<Transacao> dtos = new ArrayList<>();
        for (Transacao transacao : transacoes){
            dtos.add(Transacao.toDto(transacao));
        }
        return dtos;
    }
}
