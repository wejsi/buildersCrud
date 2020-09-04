package br.com.builder.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.builder.domain.Cliente;
import br.com.builder.domain.consulta.ClienteConsulta;
import br.com.builder.services.ClienteService;

@RestController
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@GetMapping("/cliente/{id}")
	public ResponseEntity<Cliente> obterCliente(@PathVariable("id") String id) {
		Cliente cliente = clienteService.buscar(Long.valueOf(id));
		if (cliente == null) {
			 ResponseEntity.ok("Nenhum cliente com ID: '" + id + "' foi encontrado.");
		}
		return ResponseEntity.ok(cliente);
	}
		
	@GetMapping("/clientes")
	public ResponseEntity<List<Cliente>> todos() {
		return ResponseEntity.ok(clienteService.todos());
	}
	
	@GetMapping(params="{pagina},{tamanho}", path="/clientes")
	public ResponseEntity<List<Cliente>> todosPaginado(@PathVariable("pagina") int pagina,
			@PathVariable("pagina") int tamanho) {
		return ResponseEntity.ok(clienteService.consultaPaginada(pagina, tamanho));
	}

	@PutMapping("/cliente/inserir")
	public ResponseEntity<Cliente> inserir(@RequestBody Cliente cliente) throws IOException {
		clienteService.inserir(cliente);
		return ResponseEntity.ok(cliente);
	}
	
	@PatchMapping("/cliente/{id}")
	public Cliente patch(@PathVariable("id") String id, 
			@RequestParam("nome") String nome, @RequestParam("cpf") String cpf, 
			@RequestParam("dataNascimento") String dataNascimento) throws IOException {
		Cliente cliente = clienteService.buscar(Long.valueOf(id));
		return clienteService.patch(cliente, nome, cpf, dataNascimento);
	}
	

	@PostMapping("/clientes/consultar")
	public List<Cliente> consultar(@RequestBody ClienteConsulta clienteConsulta) throws IOException {
		return clienteService.consultar(clienteConsulta);
	}
	

	@DeleteMapping("/cliente/{id}")
	public void remover(@PathVariable("id") String id) throws IOException {
		Cliente cliente = clienteService.buscar(Long.valueOf(id));
		clienteService.remover(cliente);
	}
	
}
