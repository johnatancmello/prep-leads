package com.prep.api.model.visita;

public enum VisitaStatus {

    AGENDADO("AGENDADO"),
    NAO_COMPARECEU("NÃO COMPARECEU"),
    SEM_INTERESSE("SEM INTERESSE"),
    PENSANDO("PENSANDO"),
    RETORNO("RETORNO"),
    MATRICULADO("MATRICULADO");

    private String tex;

    VisitaStatus(String tex) {
        this.tex = tex;
    }

    @Override
    public String toString() {
        return tex;
    }
}
