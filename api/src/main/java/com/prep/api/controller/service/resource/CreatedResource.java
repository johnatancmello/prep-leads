package com.prep.api.controller.service.resource;

import org.springframework.context.ApplicationEvent;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class CreatedResource extends ApplicationEvent{
	
	private static final long serialVersionUID = 1L;
	private final HttpServletResponse httpServletResponse;
	private final Map<String,Long> id;
	
	public CreatedResource(Object source, HttpServletResponse httpServletResponse, Map<String,Long> id) {
		super(source);
		this.httpServletResponse = httpServletResponse;
		this.id = id;
	}

	public HttpServletResponse getHttpServletResponse() {
		return httpServletResponse;
	}

	public Map<String,Long> getId() {
		return id;
	}	
	
}
