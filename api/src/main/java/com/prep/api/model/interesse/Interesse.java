package com.prep.api.model.interesse;

import com.prep.api.model.ModelAbstract;
import com.prep.api.model.funcionario.Funcionario;
import com.prep.api.model.modulo.Modulo;
import com.prep.api.model.modulo.ModuloJPA;
import com.prep.api.model.pessoa.Pessoa;

import java.util.Date;
import java.util.List;
import java.util.Set;


public class Interesse  extends ModelAbstract {

    protected Long id;
    protected String status, tipo;
    protected Date dataDoCadastro;
    protected Pessoa pessoa;
    protected Funcionario funcionario;
    protected Set<? extends Modulo> modulos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Date getDataDoCadastro() {
        return dataDoCadastro;
    }

    public void setDataDoCadastro(Date dataDoCadastro) {
        this.dataDoCadastro = dataDoCadastro;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
}
