package com.prep.api.model.interesse;

public enum InteresseStatus {
    PENDENTE("PENDENTE"),
    SEM_INTERESSE("SEM INTERESSE"),
    MATRICULADO("MATRICULADO");

    private String text;

    InteresseStatus(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
