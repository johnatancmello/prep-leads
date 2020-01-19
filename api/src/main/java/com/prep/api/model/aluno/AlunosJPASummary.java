package com.prep.api.model.aluno;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.prep.api.model.funcionario.FuncionarioJPASummary;
import com.prep.api.model.pessoa.PessoaJPASummary;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "alunos")
@Embeddable
public class AlunosJPASummary extends Aluno {
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

    @OneToOne()
    @NotNull
    @JoinColumn(name = "id_pessoa")
    public PessoaJPASummary getPessoa() {
        return (PessoaJPASummary) pessoa;
    }

    public void setPessoa(PessoaJPASummary pessoa) {
        this.pessoa = pessoa;
    }

    @OneToOne
    @NotNull
    @JoinColumn(name = "id_funcionario")
    @JsonIgnore
    public FuncionarioJPASummary getFuncionario() {
        return (FuncionarioJPASummary) funcionario;
    }

    public void setFuncionario(FuncionarioJPASummary funcionario) {
        this.funcionario = funcionario;
    }
}
