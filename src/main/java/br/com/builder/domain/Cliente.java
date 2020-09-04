package br.com.builder.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cli_id")
	private Long id;

	@Column(name = "cli_nome")
	private String nome;

	@Column(name = "cli_cpf")
	private String cpf;

	@Column(name = "cli_dt_nascimento")
	private String dataNascimento;

}
