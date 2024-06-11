package br.com.bank.model;

import br.com.bank.web.dto.ClienteResponseDto;
import br.com.bank.web.dto.ContaResponseDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "contas")
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String agencia;
    private String numero;

    @OneToOne
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    private Cliente cliente;

    public static ContaResponseDto toDto(Conta conta){
        ContaResponseDto dto = new ContaResponseDto();
        dto.setId(conta.getId());
        dto.setAgencia(conta.getAgencia());
        dto.setNumero(conta.getNumero());
        dto.setId_cliente(conta.getCliente().getId());
        return dto;
    }

    public static List<ContaResponseDto> toListDto(List<Conta> contas){
        List<ContaResponseDto> dtos = new ArrayList<>();
        for (Conta conta : contas){
            dtos.add(Conta.toDto(conta));
        }
        return dtos;
    }
}
