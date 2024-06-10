package br.com.bank.web.dto;

import br.com.bank.model.Cliente;
import br.com.bank.service.ClienteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/bank/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    @PostMapping("/create")
    public ResponseEntity<ClienteResponseDto> createCliente(@RequestBody Cliente cliente){
        Cliente clienteNovo = clienteService.criarNovoUsuario(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(Cliente.toDto(clienteNovo));
    }

    @GetMapping("/list")
    public ResponseEntity<List<ClienteResponseDto>> listCliente(){
        List<Cliente> clientes = clienteService.listClientes();
        return ResponseEntity.ok(Cliente.toListDto(clientes));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<ClienteResponseDto> findById(@PathVariable int id){
        Cliente cliente = clienteService.findClienteById(id);
        return ResponseEntity.ok(Cliente.toDto(cliente));
    }
    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<ClienteResponseDto> findByCpf(@PathVariable String cpf, @RequestParam String password){
        Cliente cliente = clienteService.findClienteByCpf(cpf, password);
        return ResponseEntity.ok(Cliente.toDto(cliente));
    }
    @GetMapping("/email/{email}")
    public ResponseEntity<ClienteResponseDto> findByEmail(@PathVariable String email){
        Cliente cliente = clienteService.findClienteByEmail(email);
        return ResponseEntity.ok(Cliente.toDto(cliente));
    }
    @GetMapping("/phone/{phone}")
    public ResponseEntity<ClienteResponseDto> findByPhone(@PathVariable String phone){
        Cliente cliente = clienteService.findClienteByPhone(phone);
        return ResponseEntity.ok(Cliente.toDto(cliente));
    }

}
