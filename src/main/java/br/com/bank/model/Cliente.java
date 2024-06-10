package br.com.bank.model;

import br.com.bank.web.dto.ClienteCreateDto;
import br.com.bank.web.dto.ClienteResponseDto;
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
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "cpf", unique = true, length = 11, nullable = false)
    private String cpf;

    @Column(name = "email", unique = true, length = 100, nullable = false)
    private String email;

    @Column(name = "phone", unique = true, length = 15)
    private String phone;

    @Column(name = "senha", nullable = false)
    private String password;

    public static Cliente toCliente(ClienteCreateDto clienteCreateDto){
        Cliente cliente = new Cliente();
        cliente.setName(clienteCreateDto.getName());
        cliente.setCpf(clienteCreateDto.getCpf());
        cliente.setEmail(clienteCreateDto.getEmail());
        cliente.setPhone(clienteCreateDto.getPhone());
        cliente.setPassword(clienteCreateDto.getPassword());
        return cliente;
    }

    public static ClienteResponseDto toDto(Cliente cliente){
        ClienteResponseDto dto = new ClienteResponseDto();
        dto.setId(cliente.getId());
        dto.setName(cliente.getName());
        dto.setCpf(cliente.getCpf());
        return dto;
    }

    public static List<ClienteResponseDto> toListDto(List<Cliente> clientes){
        List<ClienteResponseDto> dtos = new ArrayList<>();
        for (Cliente cliente : clientes){
            dtos.add(Cliente.toDto(cliente));
        }
        return dtos;
    }
}
