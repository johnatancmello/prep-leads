package com.prep.api.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("prepweb")
public class PrepWebProperty {

	private final Security security = new Security();

	public Security getSecurity() {
		return security;
	}

	public static class Security {

		private boolean enableHttps;
		
		private String originAllowed = "http://192.168.0.103";

		public boolean isEnableHttps() {
			return enableHttps;
		}

		public void setEnableHttps(boolean enableHttps) {
			this.enableHttps = enableHttps;
		}

		public String getOriginAllowed() {
			return originAllowed;
		}

		public void setOriginAllowed(String originAllowed) {
			this.originAllowed = originAllowed;
		}
		
	}
	
}
