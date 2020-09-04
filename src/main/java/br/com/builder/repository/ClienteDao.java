package br.com.builder.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import br.com.builder.domain.Cliente;
import br.com.builder.domain.consulta.ClienteConsulta;

@Repository
public class ClienteDao {

	EntityManager em;

	public List<Cliente> consultar(ClienteConsulta clienteConsulta) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Cliente> criteria = cb.createQuery(Cliente.class);
		Root<Cliente> cliente = criteria.from(Cliente.class);
		List<Predicate> predicates = new ArrayList<>();

		if (!StringUtils.isEmpty(clienteConsulta.getCpf())) {
			predicates.add(cb.equal(cliente.get("cpf"),
					clienteConsulta.getCpf()));
		}
		if (!StringUtils.isEmpty(clienteConsulta.getNome())) {
			predicates.add(cb.like(cliente.get("nome"),
					"%" + clienteConsulta.getNome() + "%"));
		}

		if (!StringUtils.isEmpty(clienteConsulta.getDataNascimento())) {
			predicates.add(cb.equal(cliente.get("dataNascimento"),
					clienteConsulta.getDataNascimento()));
		}
		criteria.where(predicates.toArray(new Predicate[0]));
		return em.createQuery(criteria).getResultList();
	}

}
