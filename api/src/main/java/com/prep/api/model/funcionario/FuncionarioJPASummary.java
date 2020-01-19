package com.prep.api.model.funcionario;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.prep.api.model.meta.MetaJPA;
import com.prep.api.model.pessoa.PessoaJPASummary;
import com.prep.api.model.setor.SetorJPA;
import com.prep.api.model.usuario.UsuarioJPA;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "funcionarios")
@Embeddable
public class FuncionarioJPASummary extends Funcionario implements Serializable {

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
    @JsonIgnore
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @JsonIgnore
    public Integer getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(Integer cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    @Valid
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_usuario")
    @JsonIgnore
    public UsuarioJPA getUsuario() {
        return (UsuarioJPA) usuario;
    }

    public void setUsuario(UsuarioJPA usuario) {
        this.usuario = usuario;
    }

    @NotNull
    @OneToOne()
    @JoinColumn(name = "id_setor")
    @JsonIgnore
    public SetorJPA getSetor() {
        return (SetorJPA) setor;
    }

    public void setSetor(SetorJPA setor) {
        this.setor = setor;
    }

    @Valid
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_pessoa")
    @JsonIgnoreProperties("aluno")
    public PessoaJPASummary getPessoa() {
        return (PessoaJPASummary) pessoa;
    }

    public void setPessoa(PessoaJPASummary pessoa) {
        this.pessoa = pessoa;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "funcionarios_metas",
            joinColumns = @JoinColumn(name = "id_funcionario"),
            inverseJoinColumns = @JoinColumn(name = "id_meta"))
    @JsonIgnore
    public Set<MetaJPA> getMetas() {
        return (Set<MetaJPA>) metas;
    }

    public void setMetas(Set<MetaJPA> metas) {
        this.metas = metas;
    }
}
