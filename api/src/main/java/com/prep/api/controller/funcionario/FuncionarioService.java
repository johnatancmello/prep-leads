package com.prep.api.controller.funcionario;

import com.prep.api.model.funcionario.Funcionario;
import com.prep.api.model.funcionario.FuncionarioFilter;
import com.prep.api.model.funcionario.FuncionarioJPA;
import com.prep.api.model.funcionario.FuncionarioJPASummary;
import com.prep.api.repository.funcionario.FuncionarioRepository;
import com.prep.api.repository.funcionario.FuncionarioSummaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FuncionarioService {

	@Autowired
	private FuncionarioSummaryRepository summaryRepository;
	
	@Autowired
	private FuncionarioRepository repository;
	
	public Page<Funcionario> filter(FuncionarioFilter filter, Pageable pageable) {
		return repository.filter(filter, pageable);
	}

	public Page<Funcionario> filterInSummary(FuncionarioFilter filter, Pageable pageable) {
		return summaryRepository.filter(filter, pageable);
	}

	public Funcionario getFuncionariobyId(Long id) {
		Optional<FuncionarioJPA> optionalFuncionarioJPA = repository.findById(id);
		return optionalFuncionarioJPA.orElseThrow(() -> new UsernameNotFoundException("Não encontrado!"));
	}

	public Funcionario getFuncionarioSummarybyId(Long id) {
		Optional<FuncionarioJPASummary> optionalFuncionarioJPA = summaryRepository.findById(id);
		return optionalFuncionarioJPA.orElseThrow(() -> new UsernameNotFoundException("Não encontrado!"));
	}

	public ResponseEntity<Funcionario> alterar(FuncionarioJPA funcionario){
		if (funcionario.getUsuario().getSenha() == null || funcionario.getUsuario().getSenha().isEmpty()) {
			FuncionarioJPA funcionarioNoBanco = repository.findById(funcionario.getId())
					.orElseThrow(() -> new UsernameNotFoundException("Não encontrado!"));
			funcionario.getUsuario().setSenha(funcionarioNoBanco.getUsuario().getSenha());
		}

		return ResponseEntity.status(HttpStatus.OK).body(repository.save(funcionario));

	}

	public void deleteById(Long id){
		repository.deleteById(id);
	}


	/*public Page<MetaJPA> getMeta(FuncionarioFilter filter, Pageable pageable) {
		List<MetaJPA> metas = new ArrayList<>();
		if (filter.getId() != null){
			List<FuncionarioMeta> funcionarioMetas = funcionarioMetaRepository.findAllByIdFuncionario(filter.getId());
			funcionarioMetas.forEach(elemt -> {
				metas.add(metaRepository.findById(elemt.getIdMeta()).get());
			});
		}
		return new PageImpl(metas, pageable, 10L);
	}*/

	/*public ResponseEntity<PessoaJPA> getPessoa(FuncionarioFilter filter) {
		PessoaJPA pessoa = repository.getPessoaByIdFuncionario(filter.getId());
		if (pessoa != null){
			return ResponseEntity.ok(pessoa);
		}
		return ResponseEntity.notFound().build();
	}

	public ResponseEntity<String> getNome(FuncionarioFilter filter) {
		PessoaJPA pessoa = repository.getPessoaByIdFuncionario(filter.getId());
		if (pessoa != null){
			return ResponseEntity.ok(pessoa.getNome());
		}
		return ResponseEntity.notFound().build();
	}*/

	public ResponseEntity<Funcionario> add(FuncionarioJPA funcionario) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(funcionario));
	}
}
