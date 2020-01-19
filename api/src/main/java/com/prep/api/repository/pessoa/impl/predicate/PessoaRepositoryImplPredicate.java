package com.prep.api.repository.pessoa.impl.predicate;

import com.prep.api.model.ModelAbstract;
import com.prep.api.model.imagem.ImagemJPA;
import com.prep.api.model.pessoa.PessoaFilter;
import com.prep.api.model.pessoa.PessoaJPA;
import com.prep.api.repository.RepositoryImplPredicateAbstract;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;

public class PessoaRepositoryImplPredicate extends RepositoryImplPredicateAbstract {

    public void addpredicates(ModelAbstract filter, CriteriaBuilder builder, Root root,
                              List<Predicate> predicates) {
        PessoaFilter pessoaFilter = (PessoaFilter) filter;
        addId(pessoaFilter.getId(), builder, root, predicates);
        addIdImagem(pessoaFilter.getIdImagem(), builder, root, predicates);
        addNome(pessoaFilter.getNome(), builder, root, predicates);
        addIdade(pessoaFilter.getIdade(), builder, root, predicates);
        addSexo(pessoaFilter.getSexo(), builder, root, predicates);
        addStatus(pessoaFilter.getStatus(), builder, root, predicates);
        addLocalDoCadastro(pessoaFilter.getLocalDoCadastro(), builder, root, predicates);
        addDataDeCadastroDe(pessoaFilter.getDataDoCadastroDe(), builder, root, predicates);
        addDataDeCadastroAte(pessoaFilter.getDataDoCadastroAte(), builder, root, predicates);
    }

    public void addIdImagem(Long idImagem, CriteriaBuilder builder, Root root,
                              List<Predicate> predicates) {
        if (idImagem != null) {
            Join<PessoaJPA, ImagemJPA> join = root.join("imagem");
            predicates.add(builder.equal(join.get("id"), idImagem));
        }
    }

    public void addIdade(Integer idade, CriteriaBuilder builder, Root root,
                                    List<Predicate> predicates) {
        if (idade != null) {
            predicates.add(builder.equal(root.get("idade"),idade));
        }
    }

    public void addSexo(Character sexo, CriteriaBuilder builder, Root root,
                                 List<Predicate> predicates) {
        if (sexo != null) {
            predicates.add(builder.equal(root.get("sexo"), sexo));
        }
    }

    public void addLocalDoCadastro(String localDoCadastro, CriteriaBuilder builder, Root root,
                                  List<Predicate> predicates) {
        if (!StringUtils.isEmpty(localDoCadastro)) {
            predicates.add(builder.like(builder.upper(root.get("localDoCadastro")), localDoCadastro.toUpperCase()));
        }
    }

    public void addDataDeCadastroDe(Date dataDoCadastroDe, CriteriaBuilder builder,
                                           Root root, List<Predicate> predicates) {
        if (!StringUtils.isEmpty(dataDoCadastroDe)) {
            predicates.add(
                    builder.greaterThanOrEqualTo(root.get("dataDoCadastro"), dataDoCadastroDe));
        }
    }

    public void addDataDeCadastroAte(Date dataDoCadastroAte, CriteriaBuilder builder,
                                             Root root, List<Predicate> predicates) {
        if (!StringUtils.isEmpty(dataDoCadastroAte)) {
            predicates
                    .add(builder.lessThanOrEqualTo(root.get("dataDoCadastro"), dataDoCadastroAte));
        }
    }

}
