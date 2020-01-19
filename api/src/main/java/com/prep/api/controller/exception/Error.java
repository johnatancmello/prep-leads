package com.prep.api.controller.exception;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.FieldError;

public class Error {

	private final String mensagemUsuario, mensagemDesenvolvedor;

	public Error(String mensagemUsuario, String mensagemDesenvolvedor) {
		this.mensagemUsuario = mensagemUsuario;
		this.mensagemDesenvolvedor = mensagemDesenvolvedor;
	}

	public Error(FieldError fieldError, MessageSource messagerSource) {
		this.mensagemUsuario = messagerSource
				.getMessage(fieldError, LocaleContextHolder.getLocale());
		this.mensagemDesenvolvedor = fieldError.toString();
	}
	
	public Error(String stringMessagerSource, MessageSource messagerSource,
			Exception ex) {
		this.mensagemUsuario = messagerSource
				.getMessage(stringMessagerSource, null, LocaleContextHolder.getLocale());
		//mensagemDesenvolvedor = ex.getCause() != null ? ex.getCause().toString() : ex.toString();//Operador Ternário
		mensagemDesenvolvedor = ExceptionUtils.getRootCauseMessage(ex);//Usando Bibliotéca de detalhação de erros org.apache.commons
	}
	
	public String getMensagemUsuario() {
		return mensagemUsuario;
	}	

	public String getMensagemDesenvolvedor() {
		return mensagemDesenvolvedor;
	}

}
