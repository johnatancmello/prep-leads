package com.prep.api.model.interesse;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.prep.api.model.funcionario.FuncionarioJPASummary;
import com.prep.api.model.modulo.ModuloJPA;
import com.prep.api.model.pessoa.PessoaJPA;
import com.prep.api.model.pessoa.PessoaJPASummary;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "interesses")
@Embeddable
public class InteresseJPASummary extends Interesse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotNull
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @NotNull
    public Date getDataDoCadastro() {
        return dataDoCadastro;
    }

    public void setDataDoCadastro(Date dataDoCadastro) {
        this.dataDoCadastro = dataDoCadastro;
    }

    @ManyToOne
    @NotNull
    @JoinColumn(name = "id_pessoa")
    public PessoaJPASummary getPessoa() {
        return (PessoaJPASummary) pessoa;
    }

    public void setPessoa(PessoaJPASummary pessoa) {
        this.pessoa = pessoa;
    }

    @ManyToOne
    @NotNull
    @JoinColumn(name = "id_funcionario")
    public FuncionarioJPASummary getFuncionario() {
        return (FuncionarioJPASummary) funcionario;
    }

    public void setFuncionario(FuncionarioJPASummary funcionario) {
        this.funcionario = funcionario;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "interesses_modulos",
            joinColumns = @JoinColumn(name = "id_interesse"),
            inverseJoinColumns = @JoinColumn(name = "id_modulo"))
    @JsonIgnore
    public Set<ModuloJPA> getModulos() {
        return (Set<ModuloJPA>) modulos;
    }

    public void setModulos(Set<ModuloJPA> modulos) {
        this.modulos = modulos;
    }
}
