package com.prep.api.controller.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageSource messagerSource;

	//capturado quando o recurso recebido possui mais propriedades que o registrado.
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return handleExceptionInternal(ex, this.createListErro("mensagem.invalida", ex), headers, status, request);
	}
	
	//capturado quando o recurso possui propriedades fora do desejado.
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return handleExceptionInternal(ex, createListErro(ex.getBindingResult()), headers, status, request);
	}
	
	//capturado quando o recurso não e encontrado no DB.
	@ExceptionHandler({ EmptyResultDataAccessException.class })
	public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex,
			WebRequest request) {
		return handleExceptionInternal(ex, this.createListErro("resouce.not_found",ex),
				new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}

	@ExceptionHandler( {InternalAuthenticationServiceException.class} )
	public ResponseEntity<Object> handleInternalAuthenticationServiceException(InternalAuthenticationServiceException ex, WebRequest request) {
		return handleExceptionInternal(ex, this.createListErro("resouce.not_found",ex),
				new HttpHeaders(), HttpStatus.OK, request);
	}

	@ExceptionHandler({ DataIntegrityViolationException.class })
	public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex,
			WebRequest request){
		return handleExceptionInternal(ex, this.createListErro("resouce.operacao_nao_permitida",ex),
				new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

	
	private List<Error> createListErro(BindingResult bindingResult){
		List<Error> errors = new ArrayList<>();
		
		for (FieldError fieldError : bindingResult.getFieldErrors()) {		
			errors.add(new Error(fieldError, messagerSource));
		}
		
		return errors;
	}

	private List<Error> createListErro(String stringMessagerSource, Exception ex) {
		return getError(new Error(stringMessagerSource, messagerSource, ex));
	}
	
	private List<Error> getError(Error erro){
		List<Error> errors = new ArrayList<>();
		errors.add(erro);
		return errors;
	}
}
