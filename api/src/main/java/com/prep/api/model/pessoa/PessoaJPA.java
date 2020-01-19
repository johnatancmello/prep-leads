package com.prep.api.model.pessoa;

import com.fasterxml.jackson.annotation.*;
import com.prep.api.model.aluno.AlunoJPA;
import com.prep.api.model.contato.ContatoJPA;
import com.prep.api.model.imagem.ImagemJPA;
import com.prep.api.model.interesse.InteresseJPA;
import com.prep.api.model.telefone.TelefoneJPA;
import com.prep.api.model.visita.VisitaJPA;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "pessoas")
@Embeddable
public class PessoaJPA extends Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@NotNull
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@NotNull
	public String getLocalDoCadastro() {
		return localDoCadastro;
	}

	public void setLocalDoCadastro(String localDoCadastro) {
		this.localDoCadastro = localDoCadastro;
	}

	public Character getSexo() {
		return sexo;
	}

	public void setSexo(Character sexo) {
		this.sexo = sexo;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	@NotNull
	public Date getDataDoCadastro() {
		return dataDoCadastro;
	}

	public void setDataDoCadastro(Date dataDoCadastro) {
		this.dataDoCadastro = dataDoCadastro;
	}

	public String getOcupacao() {
		return ocupacao;
	}

	public void setOcupacao(String ocupacao) {
		this.ocupacao = ocupacao;
	}



	@JoinColumn(name = "id_responsavel")
	@OneToOne(cascade = CascadeType.ALL)
	public PessoaJPA getResponsavel() {
		return (PessoaJPA) responsavel;
	}

	public void setResponsavel(PessoaJPA responsavel) {
		this.responsavel = responsavel;
	}

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, optional = true)
	@JoinColumn(name = "id_imagem", nullable = true)
	public ImagemJPA getImagem() {
		return (ImagemJPA) imagem;
	}

	public void setImagem(ImagemJPA imagem) {
		this.imagem = imagem;
	}



	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "pessoa")
	@JsonManagedReference
	public Set<TelefoneJPA> getTelefones() {
		return (Set<TelefoneJPA>) telefones;
	}

	public void setTelefones(Set<TelefoneJPA> telefones) {
		this.telefones = telefones;
	}



	@OneToMany(mappedBy = "pessoa", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonIgnore
	public Set<InteresseJPA>  getInteresses(){
		return (Set<InteresseJPA>) interesses;
	}

	public void setInteresses(Set<InteresseJPA> interesses){
		this.interesses = interesses;
	}



	@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
	@OneToOne(mappedBy = "pessoa", cascade = CascadeType.REMOVE)
	public AlunoJPA getAluno(){
		return (AlunoJPA) aluno;
	}

	public void setAluno(AlunoJPA aluno){
		this.aluno = aluno;
	}



	@OneToMany(mappedBy = "pessoa", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonIgnore
	public Set<ContatoJPA>  getContatos(){
		return (Set<ContatoJPA>) contatos;
	}

	public void setContatos(Set<ContatoJPA> contatos){
		this.contatos = contatos;
	}



	@OneToMany(mappedBy = "pessoa", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JsonIgnore
	public Set<VisitaJPA>  getVisitas(){
		return (Set<VisitaJPA>) visitas;
	}

	public void setVisitas(Set<VisitaJPA> contatos){
		this.visitas = visitas;
	}
}
