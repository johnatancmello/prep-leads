package com.prep.api.controller.service.resource.listener;

import com.prep.api.controller.service.resource.CreatedResource;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.util.Map;

@Component
public class CreatedResourceListener implements ApplicationListener<CreatedResource> {

	@Override
	public void onApplicationEvent(CreatedResource createdResource) {
		addHeaderLocation(createdResource.getHttpServletResponse(), createdResource.getId());
	}

	private void addHeaderLocation(HttpServletResponse httpServletResponse, Map<String,Long> id) {
		this.writeHeader(httpServletResponse, this.getUri(id));
	}

	private URI getUri(Map<String,Long> id) {
		if (id.size() == 1) {
			return getUri1(id.get("id1"));
		} else if (id.size() == 2) {
			return getUri2(id.get("id1"), id.get("id2"));
		}
		return null;
	}

	private URI getUri1(Long id) {
		return ServletUriComponentsBuilder.fromCurrentRequestUri()
				.path("/{id1}")
				.buildAndExpand(id).toUri();
	}

	private URI getUri2(Long id1, Long id2) {
		return ServletUriComponentsBuilder.fromCurrentRequestUri()
				.path("/{id1}-{id2}")
				.buildAndExpand(id1, id2).toUri();
	}

	private void writeHeader(HttpServletResponse httpServletResponse, URI uri) {
		httpServletResponse.setHeader("Location", uri.toASCIIString());
	}

}
