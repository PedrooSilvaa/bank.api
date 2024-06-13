package br.com.bank.web.dto;

import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TransacaoResponseDto {

    private Integer id;
    private String tipo;
    private Double valor;
    private int idOrigem;
    private int idDestinatario;
    private LocalDateTime data;

}
