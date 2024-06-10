package br.com.bank.web.dto;

import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ClienteCreateDto {

    private String name;

    private String cpf;

    private String email;

    private String phone;

    @Size(min = 6, max = 8)
    private String password;

}
