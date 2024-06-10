package br.com.bank.web.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ClienteResponseDto {

    private Integer id;

    private String name;

    private String cpf;


}
