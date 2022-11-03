package br.com.localizacaoapi.domain.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.localizacaoapi.domain.model.Cidade;
import br.com.localizacaoapi.domain.service.CidadeService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/cidade")
@Api(value = "API REST LOCALIZACAO")
@CrossOrigin(origins = "*")
public class CidadeController {
	
	@Autowired
	private CidadeService cidadeService;

	
	@PostMapping("/cadastrar")
	@Operation(description = "Cadastra uma cidade e retorna a cidade cadastrada")
	public ResponseEntity<Cidade> registerCity(@RequestBody Cidade cidade){
		return cidadeService.registerCity(cidade);
	}
	
	@PutMapping("/atualizar")
	@Operation(description = "Atualizar uma cidade cadastrada e retorna a cidade atualizada")
	public ResponseEntity<Cidade> updateCity(@RequestBody Cidade cidade){
		return cidadeService.updateCity(cidade);
	}
	
	@DeleteMapping("deletar/{id}")
	@Operation(description = "Deleta uma cidade cadastrada")
	public ResponseEntity<String> deleteCity(@PathVariable(value = "id") Long id){
		return cidadeService.deleteCity(id);
	}
	
	@GetMapping("/listar-todos")
	@Operation(description = "Retorna uma lista de cidades")
	public ResponseEntity<List<Cidade>> listAllCity(){
		return cidadeService.listAllCity();
	}
	
	@GetMapping("/listar-por-habitantes")
	@Operation(description = "Busca cidades de acordo com o número de habitantes informado e retorna uma lista de cidades")
	public ResponseEntity<List<Cidade>> listAllCityByHabitantes(@RequestParam Long habitantes){
		return cidadeService.listAllCityByHabitantes(habitantes);
	}
	
	@GetMapping("/listar-por-nome")
	@Operation(description = "Busca cidades de acordo com o nome informado e retorna uma lista de cidades")
	public ResponseEntity<List<Cidade>> listaAllByName(@RequestParam String nome){
		return cidadeService.listAllNomeLike(nome);
	}
	
	@GetMapping("/buscar-por-id")
	@Operation(description = "Busca uma cidade de acordo com o id informado e retorna uma cidade")
	public ResponseEntity<Optional<Cidade>> findById(@RequestParam Long id){
		return cidadeService.findById(id);
	}
	
	@GetMapping("/listar-intervalo-habitantes")
	@Operation(description = "Busca cidades de acordo com o intervalo informado e retorna uma lista de cidade")
	public ResponseEntity<List<Cidade>> listAllHabitantesBetween(@RequestParam Long habitantesMax, @RequestParam Long habitantesMin){
		return cidadeService.listAllHabitantesBetween(habitantesMax,habitantesMin);
	}
	
	@GetMapping("/listar-habitantes-maior-que")
	@Operation(description = "Busca cidades que possuam um número maior de habitantes do que foi informado e retorna uma lista")
	public ResponseEntity<List<Cidade>> listAllCityHabitantesGreaterThan(@RequestParam Long habitantes){
		return cidadeService.listAllCityHabitantesGreaterThan(habitantes);
	}
	
	@GetMapping("/listar-habitantes-menor-que")
	@Operation(description = "Busca cidades que possuam um número menor de habitantes do que foi informado e retorna uma lista\"")
	public ResponseEntity<List<Cidade>> listAllCityHabitantesLessThan(@RequestParam Long habitantes){
		return cidadeService.listAllCityHabitantesLessThan(habitantes);
	}
}
