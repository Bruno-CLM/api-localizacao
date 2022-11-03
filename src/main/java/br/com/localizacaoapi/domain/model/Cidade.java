package br.com.localizacaoapi.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_cidade")
public class Cidade {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@Column(length = 60)
	private String nome;
	
	private Long habitantes;
	
	public Cidade() {
		super();
	}
	
	public Cidade(Long id, String nome, Long habitantes) {
		super();
		this.id = id;
		this.nome = nome;
		this.habitantes = habitantes;
	}
	
	public Cidade(String nome, Long habitantes) {
		super();
		this.nome = nome;
		this.habitantes = habitantes;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id =  id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getHabitantes() {
		return habitantes;
	}

	public void setHabitantes(Long habitantes) {
		this.habitantes = habitantes;
	}

	@Override
	public String toString() {
		return "Cidade [id=" + id + ", nome=" + nome + ", habitantes=" + habitantes + "]";
	}
}