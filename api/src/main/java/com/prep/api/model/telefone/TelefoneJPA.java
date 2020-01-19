package com.prep.api.model.telefone;

import com.fasterxml.jackson.annotation.*;
import com.prep.api.model.pessoa.PessoaJPA;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "telefones")
@Embeddable
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class TelefoneJPA extends Telefone implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @ManyToOne
    @JoinColumn(name = "idPessoa", nullable = false)
    @JsonBackReference
    public PessoaJPA getPessoa() {
        return (PessoaJPA) pessoa;
    }

    public void setPessoa(PessoaJPA pessoa) {
        this.pessoa = pessoa;
    }

}
