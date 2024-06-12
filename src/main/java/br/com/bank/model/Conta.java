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

    private double saldo;

    @ManyToOne // Mapeamento muitos para um
    @JoinColumn(name = "conta_origem_id") // Nome da coluna na tabela transacoes que referencia a conta origem
    private Conta contaOrigem;

    @ManyToOne // Mapeamento muitos para um
    @JoinColumn(name = "conta_destinatario_id") // Nome da coluna na tabela transacoes que referencia a conta destinatária
    private Conta contaDestinatario;

    public static ContaResponseDto toDto(Conta conta){
        ContaResponseDto dto = new ContaResponseDto();
        dto.setId(conta.getId());
        dto.setAgencia(conta.getAgencia());
        dto.setNumero(conta.getNumero());
        dto.setId_cliente(conta.getCliente().getId());
        dto.setSaldo(conta.getSaldo());
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
