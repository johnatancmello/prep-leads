package com.prep.api.controller.funcionario;

import com.prep.api.model.funcionario.Funcionario;
import com.prep.api.model.funcionario.FuncionarioFilter;
import com.prep.api.model.funcionario.FuncionarioJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

	@Autowired
	private FuncionarioService service;

	@GetMapping
	public Page<Funcionario> filter(FuncionarioFilter filter, Pageable pageable) {
		return service.filter(filter, pageable);
	}

	@GetMapping("/{id}")
	public Funcionario getFuncionarioById(@PathVariable Long id) {
		return service.getFuncionariobyId(id);
	}

	@GetMapping("/summary/{id}")
	public Funcionario getFuncionarioSummaryById(@PathVariable Long id) {
		return service.getFuncionarioSummarybyId(id);
	}

	@GetMapping("/summary")
	public Page<Funcionario> filterInSummary(FuncionarioFilter filter, Pageable pageable) {
		return service.filterInSummary(filter, pageable);
	}


	@PutMapping
	public ResponseEntity<Funcionario> alterar(@Valid @RequestBody FuncionarioJPA funcionario) {
		return service.alterar(funcionario);
	}

	/*@GetMapping("/metas")
	public Page<MetaJPA> getMeta(FuncionarioFilter filter, Pageable pageable) {
		return funcionarioService.getMeta(filter, pageable);
	}*/

	/*@GetMapping("/pessoa")
	public ResponseEntity<PessoaJPA> getMeta(FuncionarioFilter filter) {
		return funcionarioService.getPessoa(filter);
	}

	@GetMapping("/pessoa/nome")
	public ResponseEntity<String> getNome(FuncionarioFilter filter) {
		return funcionarioService.getNome(filter);
	}*/

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteById(@PathVariable Long id){
		service.deleteById(id);
	}

	@PostMapping
	//@PreAuthorize("hasAuthority('ROLE_ALTERAR_FORMACOES') and #oauth2.hasScope('write')")
	public ResponseEntity<Funcionario> add(@Valid @RequestBody FuncionarioJPA funcionario) {
		return service.add(funcionario);
	}
}