package com.prep.api.model.pessoa;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.prep.api.model.aluno.AlunosJPASummary;
import com.prep.api.model.contato.ContatoJPASummary;
import com.prep.api.model.imagem.ImagemJPA;
import com.prep.api.model.interesse.InteresseJPA;
import com.prep.api.model.telefone.TelefoneJPA;
import com.prep.api.model.visita.VisitaJPASummary;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "pessoas")
@Embeddable
public class PessoaJPASummary extends Pessoa implements Serializable {

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

    @JsonIgnore
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @NotNull
    @JsonIgnore
    public String getLocalDoCadastro() {
        return localDoCadastro;
    }

    public void setLocalDoCadastro(String localDoCadastro) {
        this.localDoCadastro = localDoCadastro;
    }

    @JsonIgnore
    public Character getSexo() {
        return sexo;
    }

    public void setSexo(Character sexo) {
        this.sexo = sexo;
    }

    @JsonIgnore
    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    @NotNull
    @JsonIgnore
    public Date getDataDoCadastro() {
        return dataDoCadastro;
    }

    public void setDataDoCadastro(Date dataDoCadastro) {
        this.dataDoCadastro = dataDoCadastro;
    }

    @JoinColumn(name = "id_responsavel")
    @OneToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    public PessoaJPA getResponsavel() {
        return (PessoaJPA) responsavel;
    }

    public void setResponsavel(PessoaJPA responsavel) {
        this.responsavel = responsavel;
    }

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, optional = true)
    @JoinColumn(name = "id_imagem", nullable = true)
    @JsonIgnoreProperties({"dados", "tamanho", "tipo", "nome"})
    public ImagemJPA getImagem() {
        return (ImagemJPA) imagem;
    }

    public void setImagem(ImagemJPA imagem) {
        this.imagem = imagem;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "pessoa", orphanRemoval = true)
    @JsonManagedReference()
    @JsonIgnore
    public Set<TelefoneJPA> getTelefones() {
        return (Set<TelefoneJPA>) telefones;
    }

    public void setTelefones(Set<TelefoneJPA> telefones) {
        this.telefones = telefones;
    }

    @JsonIgnore
    public String getOcupacao() {
        return ocupacao;
    }

    public void setOcupacao(String ocupacao) {
        this.ocupacao = ocupacao;
    }

    @OneToMany(mappedBy = "pessoa", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    public Set<InteresseJPA>  getInteresses(){
        return (Set<InteresseJPA>) interesses;
    }

    public void setInteresses(Set<InteresseJPA> interesses){
        this.interesses = interesses;
    }

    @OneToMany(mappedBy = "pessoa", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    public Set<ContatoJPASummary>  getContatos(){
        return (Set<ContatoJPASummary>) contatos;
    }

    public void setContatos(Set<ContatoJPASummary> contatos){
        this.contatos = contatos;
    }

    @OneToMany(mappedBy = "pessoa", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    public Set<VisitaJPASummary>  getVisitas(){
        return (Set<VisitaJPASummary>) visitas;
    }

    public void setVisitas(Set<VisitaJPASummary> contatos){
        this.visitas = visitas;
    }

    @OneToOne(mappedBy = "pessoa")
    @JsonIgnoreProperties("pessoa")
    public AlunosJPASummary getAluno(){
        return (AlunosJPASummary) aluno;
    }

    public void setAluno(AlunosJPASummary aluno){
        this.aluno = aluno;
    }

}