package br.com.localizacaoapi.domain.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.localizacaoapi.domain.model.Cidade;
import br.com.localizacaoapi.domain.repository.CidadeRepository;
import br.com.localizacaoapi.domain.repository.specs.CidadeSpecs;

@Service
public class CidadeService {
	
	
	Logger logger = LoggerFactory.getLogger(CidadeService.class);

	@Autowired
	private CidadeRepository cidadeRepository;
	
	private Specification<Cidade> specs;
	
	//Post cadastrar
	public ResponseEntity<Cidade> registerCity(Cidade cidade){
		try {
			validationCityFields(cidade);
			cidade = cidadeRepository.save(cidade);
			return new ResponseEntity<Cidade>(cidade, HttpStatus.CREATED);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return new ResponseEntity<Cidade>(cidade, HttpStatus.BAD_REQUEST);
	}
	
	//Put atualizar
	public ResponseEntity<Cidade> updateCity(Cidade cidade){
		try {
			validationCityFields(cidade);
			cidade = cidadeRepository.save(cidade);
			return new ResponseEntity<Cidade>(cidade, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return new ResponseEntity<Cidade>(cidade, HttpStatus.BAD_REQUEST);
	}
	
	public ResponseEntity<String> deleteCity(Long id){
		Optional<Cidade> cidade = null;
		specs = CidadeSpecs.idEqual(id);
		try {
			cidade = cidadeRepository.findOne(specs);
			validationCity(cidade);
			Cidade cidadeNew = new Cidade();
			cidadeNew.setHabitantes(cidade.get().getHabitantes());
			cidadeNew.setNome(cidade.get().getNome());
			cidadeNew.setId(id);
			cidadeRepository.delete(cidadeNew);
			return new ResponseEntity<String>("Deletado com sucesso", HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return new ResponseEntity<String>("Error", HttpStatus.BAD_REQUEST);
	}
	
	//Get listar-todos
	public ResponseEntity<List<Cidade>> listAllCity(){
		List<Cidade> listCity = null;
		try {
			listCity = cidadeRepository.findAll();
			validationListSize(listCity);
			return new ResponseEntity<List<Cidade>>(listCity, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return new ResponseEntity<List<Cidade>>(listCity, HttpStatus.NO_CONTENT);
	}
	
	//Get 
	public ResponseEntity<List<Cidade>> listAllCityStartingWith(String nome){
		List<Cidade> listCity = null;
		try {
			listCity = cidadeRepository.findByNomeStartingWith(nome);
			validationListSize(listCity);
			return new ResponseEntity<List<Cidade>>(listCity, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return new ResponseEntity<List<Cidade>>(listCity, HttpStatus.NO_CONTENT);
	} 
	
	//Get
	public ResponseEntity<List<Cidade>> listAllCityEndingWith(String nome){
		List<Cidade> listCity = null;
		try {
			listCity = cidadeRepository.findByNomeEndingWith(nome);
			validationListSize(listCity);
			return new ResponseEntity<List<Cidade>>(listCity, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return new ResponseEntity<List<Cidade>>(listCity, HttpStatus.NO_CONTENT);
	}
	
	//Get
	public ResponseEntity<List<Cidade>> listAllCityNomeContaining(String nome){
		List<Cidade> listCity = null;
		try {
			listCity = cidadeRepository.findByNomeContaining(nome);
			validationListSize(listCity);
			return new ResponseEntity<List<Cidade>>(listCity, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return new ResponseEntity<List<Cidade>>(listCity, HttpStatus.NO_CONTENT);
	}
	
	//Get listar-por-habitantes
	public ResponseEntity<List<Cidade>> listAllCityByHabitantes(Long habitantes){
		List<Cidade> listCity = null;
		try {
			listCity = cidadeRepository.findByHabitantes(habitantes);
			validationListSize(listCity);
			return new ResponseEntity<List<Cidade>>(listCity, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return new ResponseEntity<List<Cidade>>(listCity, HttpStatus.NO_CONTENT);
	}
	
	//Get listar-habitantes-menor-que
	public ResponseEntity<List<Cidade>> listAllCityHabitantesLessThan(Long habitantes){
		List<Cidade> listCity = null;
		specs = CidadeSpecs.habitantesLessThan(habitantes);
		try {
			listCity = cidadeRepository.findAll(specs);
			validationListSize(listCity);
			return new ResponseEntity<List<Cidade>>(listCity, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return new ResponseEntity<List<Cidade>>(listCity, HttpStatus.NO_CONTENT);
	}
	
	//Get listar-habitantes-maior-que
	public ResponseEntity<List<Cidade>> listAllCityHabitantesGreaterThan(Long habitantes){
		List<Cidade> listCity = null;
		specs = CidadeSpecs.habitantesGreaterThan(habitantes);
		try {
			listCity = cidadeRepository.findAll(specs);
			validationListSize(listCity);
			return new ResponseEntity<List<Cidade>>(listCity, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return new ResponseEntity<List<Cidade>>(listCity, HttpStatus.NO_CONTENT);
	}
	
	//Get
	public ResponseEntity<List<Cidade>> listAllCityHabitantesLessThanAndNomeLike(Long habitantes, String nome){
		List<Cidade> listCity = null;
		try {
			listCity = cidadeRepository.findByHabitantesLessThanAndNomeLike(habitantes,nome);
			validationListSize(listCity);
			return new ResponseEntity<List<Cidade>>(listCity, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return new ResponseEntity<List<Cidade>>(listCity, HttpStatus.NO_CONTENT);
	}
	
	//Get buscar-por-id
	public ResponseEntity<Optional<Cidade>> findById(Long id){
		Optional<Cidade> cidade = null;
		specs = CidadeSpecs.idEqual(id);
		try {
			cidade = cidadeRepository.findOne(specs);
			validationCity(cidade);
			return new ResponseEntity<Optional<Cidade>>(cidade, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return new ResponseEntity<Optional<Cidade>>(cidade, HttpStatus.NO_CONTENT);
	}
	
	//Get listar-por-nome
	public ResponseEntity<List<Cidade>> listAllByNameCity(String nome){
		List<Cidade> listCity = null;
		specs = CidadeSpecs.nomeEqual(nome);
		try {
			listCity = cidadeRepository.findAll(specs);
			validationListSize(listCity);
			return new ResponseEntity<List<Cidade>>(listCity, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return new ResponseEntity<List<Cidade>>(listCity, HttpStatus.NO_CONTENT);
	}
	
	//Get listar-intervalo-habitantes
	public ResponseEntity<List<Cidade>> listAllHabitantesBetween(Long habitantesMax, Long habitantesMin){
		List<Cidade> listCity = null;
		specs = CidadeSpecs.habitantesBetween(habitantesMin, habitantesMax);
		try {
			listCity = cidadeRepository.findAll(specs);
			validationListSize(listCity);
			return new ResponseEntity<List<Cidade>>(listCity, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return new ResponseEntity<List<Cidade>>(listCity, HttpStatus.NO_CONTENT);
	}
	
	//Get
	public ResponseEntity<List<Cidade>> listAllNomeLike(String nome){
		List<Cidade> listCity = null;
		specs = CidadeSpecs.nomeLike(nome);
		try {
			listCity = cidadeRepository.findAll(specs);
			validationListSize(listCity);
			return new ResponseEntity<List<Cidade>>(listCity, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return new ResponseEntity<List<Cidade>>(listCity, HttpStatus.NO_CONTENT);
	}
	
	/*** Validations  ***/
	
	private void validationListSize(List<Cidade> listCity) {
		if(listCity.equals(null) || listCity.isEmpty()) {
			throw new NullPointerException();
		}
	}
	
	private void validationCity(Optional<Cidade> cidade) {
		if(cidade.equals(null)) {
			throw new NullPointerException();
		}
	}
	
	private void validationCityFields(Cidade cidade) {
		if(cidade.equals(null)) {
			throw new NullPointerException();
		}
		
		if(cidade.getNome().equals(null) || cidade.getNome().equals("")) {
			throw new NullPointerException("Campo cidade vazio");
		}
		
		if(cidade.getHabitantes().equals(null)) {
			throw new NullPointerException("Campo habitantes vazio");
		}
	}

}
