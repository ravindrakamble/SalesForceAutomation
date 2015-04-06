/*
 * RestRequest.java	Jun 11, 2013
 *
 * Copyright (c) 2013 HappiestMinds Tech Pvt LTD. 
 * All rights reserved. 
 * 
 * No part of this document may be reproduced or transmitted in any form or by 
 * any means, electronic or mechanical, whether now known or later invented, 
 * for any purpose without the prior and express written consent of HappiestMinds 
 * 
 */
package com.r2apps.sfa.http;

import java.util.Map;

/**
 * <p>
 * </p>
 * 
 * @author vineet.waghdhare
 * @version 1.0
 * 
 */
public class RestRequest {

	private String targetUrl;

	private String queryString;

	private Map<String, String> headers;

	private String contentType;

	private String charEncoding;

	private String payload;

	/**
	 * @return the targetUrl
	 */
	public String getTargetUrl() {
		return targetUrl;
	}

	/**
	 * @param targetUrl
	 *            the targetUrl to set
	 */
	public void setTargetUrl(String targetUrl) {
		this.targetUrl = targetUrl;
	}

	/**
	 * @return the queryString
	 */
	public String getQueryString() {
		return queryString;
	}

	/**
	 * @param queryString
	 *            the queryString to set
	 */
	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}

	/**
	 * @return the headers
	 */
	public Map<String, String> getHeaders() {
		return headers;
	}

	/**
	 * @param headers
	 *            the headers to set
	 */
	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}

	/**
	 * @return the contentType
	 */
	public String getContentType() {
		return contentType;
	}

	/**
	 * @param contentType
	 *            the contentType to set
	 */
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	/**
	 * @return the charEncoding
	 */
	public String getCharEncoding() {
		return charEncoding;
	}

	/**
	 * @param charEncoding
	 *            the charEncoding to set
	 */
	public void setCharEncoding(String charEncoding) {
		this.charEncoding = charEncoding;
	}

	/**
	 * @return the payload
	 */
	public String getPayload() {
		return payload;
	}

	/**
	 * @param payload
	 *            the payload to set
	 */
	public void setPayload(String payload) {
		this.payload = payload;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see Object#toString()
	 * 
	 *      <p>
	 *      </p>
	 * @return
	 */
	@Override
	public String toString() {
		String restResquest = String.format(
				"RestResquest {%n Target Url: %s %n Query String: %s %n Content Type: %s %n Char Encoding: %s %n Payload: %s %n}",
				this.targetUrl, this.queryString, this.contentType, this.charEncoding, this.payload);
		return restResquest;
	}
}
