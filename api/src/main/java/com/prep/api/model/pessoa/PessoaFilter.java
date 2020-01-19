package com.prep.api.model.pessoa;

import java.util.Date;

public class PessoaFilter extends Pessoa {

    private Long idImagem;

    private Date dataDoCadastroDe;

    private Date dataDoCadastroAte;

    public Date getDataDoCadastroDe() {
        return dataDoCadastroDe;
    }

    public void setDataDoCadastroDe(Date dataDoCadastroDe) {
        this.dataDoCadastroDe = dataDoCadastroDe;
    }

    public Date getDataDoCadastroAte() {
        return dataDoCadastroAte;
    }

    public void setDataDoCadastroAte(Date dataDoCadastroAte) {
        this.dataDoCadastroAte = dataDoCadastroAte;
    }

    public Long getIdImagem() {
        return idImagem;
    }

    public void setIdImagem(Long idImagem) {
        this.idImagem = idImagem;
    }
}
