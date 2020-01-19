package com.prep.api.repository.pessoa.impl.predicate;

import com.prep.api.model.imagem.ImagemJPA;
import com.prep.api.model.pessoa.PessoaJPASummary;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class PessoaSummaryRepositoryImplPredicate extends PessoaRepositoryImplPredicate {


    public void addIdImagem(Long idImagem, CriteriaBuilder builder, Root root,
                              List<Predicate> predicates) {
        if (idImagem != null) {
            Join<PessoaJPASummary, ImagemJPA> join = root.join("imagem");
            predicates.add(builder.equal(join.get("id"), idImagem));
        }
    }


}
