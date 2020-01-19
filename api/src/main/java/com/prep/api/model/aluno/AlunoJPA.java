package com.prep.api.model.aluno;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.prep.api.model.funcionario.FuncionarioJPASummary;
import com.prep.api.model.pessoa.PessoaJPA;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "alunos")
@Embeddable
public class AlunoJPA extends Aluno {

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

    @OneToOne(cascade = CascadeType.ALL)
    @NotNull
    @JoinColumn(name = "id_pessoa")
    public PessoaJPA getPessoa() {
        return (PessoaJPA) pessoa;
    }

    public void setPessoa(PessoaJPA pessoa) {
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
}
