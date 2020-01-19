package com.prep.api.model.contato;

public enum ContatoStatus {

    SEM_INTERESSE("SEM INTERESSE"),
    AGENDADO("AGENDADO"),
    NAO_COMPARECEU("NÃO COMPARECEU"),
    COMPARECEU("COMPARECEU");

    private String text;

    ContatoStatus(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }

}
