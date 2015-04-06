/*
 * RestResponse.java	Jun 11, 2013
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

/**
 * <p>
 * </p>
 * 
 * @author vineet.waghdhare
 * @version 1.0
 * 
 */
public class RestResponse {

	public static enum StatusCode {
		SUCCESS, FAILURE
	};

	private StatusCode statusCode;

	private String content;
	
	private Object data;

	/**
	 * @return the statusCode
	 */
	public StatusCode getStatusCode() {
		return statusCode;
	}

	/**
	 * @param statusCode
	 *            the statusCode to set
	 */
	public void setStatusCode(StatusCode statusCode) {
		this.statusCode = statusCode;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	
	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
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
		return String.format("RestResponse {%n Status: %s %n Content: %s %n}", this.statusCode.name(), this.content);
	}
}
