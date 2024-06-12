package br.com.bank.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
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

}
