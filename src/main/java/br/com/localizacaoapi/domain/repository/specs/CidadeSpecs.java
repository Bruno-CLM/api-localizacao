package br.com.localizacaoapi.domain.repository.specs;

import org.springframework.data.jpa.domain.Specification;

import br.com.localizacaoapi.domain.model.Cidade;


public abstract class CidadeSpecs {

	public static Specification<Cidade> idEqual(Long id){
		return (root, query, cb) -> cb.equal(root.get("id"), id);
	}
	
	public static Specification<Cidade> nomeEqual(String nome){
		return (root, query, cb) -> cb.equal(root.get("nome"), nome);
	}
	
	public static Specification<Cidade> habitantesGreaterThan(Long habitantes){
		return (root, query, cb) -> cb.greaterThan(root.get("habitantes"), habitantes);
	}
	
	public static Specification<Cidade> habitantesLessThan(Long habitantes){
		return (root, query, cb) -> cb.lessThan(root.get("habitantes"), habitantes);
	}
	
	public static Specification<Cidade> habitantesBetween(Long habitantesMin, Long habitantesMax){
		return (root, query, cb) -> cb.between(root.get("habitantes"), habitantesMin, habitantesMax);
	}
	
	public static Specification<Cidade> nomeLike(String nome){
		return (root, query, cb) -> cb.like(cb.upper(root.get("nome")), "%" + nome + "%".toUpperCase());
	}	

}
