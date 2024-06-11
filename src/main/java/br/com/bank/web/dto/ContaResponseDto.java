package br.com.bank.web.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ContaResponseDto {

    private Integer id;
    private String agencia;
    private String numero;
    private Integer id_cliente;


}
