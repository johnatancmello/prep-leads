package com.prep.api.model.pessoa;

import com.prep.api.model.ModelAbstract;
import com.prep.api.model.aluno.Aluno;
import com.prep.api.model.contato.Contato;
import com.prep.api.model.imagem.Imagem;
import com.prep.api.model.interesse.Interesse;
import com.prep.api.model.telefone.Telefone;
import com.prep.api.model.visita.Visita;

import java.util.Date;
import java.util.Set;

public class Pessoa extends ModelAbstract {

    protected Long id;
    protected String nome,
            status,
            localDoCadastro,
            ocupacao;
    protected Character sexo;
    protected Integer idade;
    protected Date dataDoCadastro;

    protected Pessoa responsavel;
    protected Aluno aluno;
    protected Imagem imagem;


    protected Set<? extends Telefone> telefones;
    protected Set<? extends Interesse> interesses;
    protected Set<? extends Contato> contatos;
    protected Set<? extends Visita> visitas;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
}
