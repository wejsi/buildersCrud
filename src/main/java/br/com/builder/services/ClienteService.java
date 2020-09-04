package br.com.builder.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.OrderComparator.OrderSourceProvider;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.expression.spel.ast.OpInc;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import br.com.builder.domain.Cliente;
import br.com.builder.domain.consulta.ClienteConsulta;
import br.com.builder.repository.ClienteDao;
import br.com.builder.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
 private ClienteDao	clienteRepositoryCustom;

	public void remover(Cliente cliente) {
		clienteRepository.delete(cliente);
	}

	public void inserir(Cliente cliente) {
		clienteRepository.save(cliente);
	}


	public Cliente buscar(Long id) {
		Optional<Cliente> opCli = clienteRepository.findById(id);
		return opCli.isPresent() ? opCli.get() : null;
	}

	public List<Cliente> todos() {
		return clienteRepository.findAll();
	}

	public Cliente patch(Cliente cliente, String nome, String cpf,
			String dataNascimento) {

		if (!StringUtils.isEmpty(nome)) {
			cliente.setNome(nome);
		}
		if (!StringUtils.isEmpty(cpf)) {
			cliente.setCpf(cpf);
		}
		if (!StringUtils.isEmpty(dataNascimento)) {
			cliente.setDataNascimento(dataNascimento);
		}
		return cliente;
	}

	public List<Cliente> consultaPaginada(int page, int size) {
		Pageable pageRequest = PageRequest.of(page, size);
		return (List<Cliente>) clienteRepository.findAll(pageRequest);
	}

	public List<Cliente> consultar(ClienteConsulta clienteConsulta) {
		return (List<Cliente>) clienteRepositoryCustom.consultar(clienteConsulta);
	}

}
