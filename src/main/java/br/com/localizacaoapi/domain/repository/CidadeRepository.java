package br.com.localizacaoapi.domain.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.localizacaoapi.domain.model.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long>, JpaSpecificationExecutor<Cidade> {
	
	Page<Cidade> findAll(Pageable pageable);
	
	List<Cidade> findByNome(String nome);
	
	List<Cidade> findByNomeStartingWith(String nome);
	
	List<Cidade> findByNomeEndingWith(String nome);
	
	List<Cidade> findByNomeContaining(String nome);
	
	List<Cidade> findByHabitantes(Long habitantes);
	
	List<Cidade> findByHabitantesLessThanEqual(Long habitantes);
	
	List<Cidade> findByHabitantesGreaterThanEqual(Long habitantes);
	
	List<Cidade> findByHabitantesLessThanAndNomeLike(Long habitantes, String nome);
	
	/** @Query(value = "update cidade as c set c.habitantes = :habitantes where c.id = :id", nativeQuery = true)
	Cidade updateCityHabitantes(@Param("habitantes") Long habitantes, @Param("id") Long id); ***/
	
	/** @Query(value = "update cidade as c set c.nome = :nome where c.id = :id", nativeQuery = true)
	Cidade updateCityNome(@Param("nome") String nome, @Param("id") Long id); ***/

}
