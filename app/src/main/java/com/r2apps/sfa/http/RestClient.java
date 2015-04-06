/*
 * RestClient.java	Jun 11, 2013
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

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import android.text.TextUtils;
import android.util.Log;

/**
 * @author Ravindra Kambale
 * 
 */
public class RestClient {

String TAG = "RestClient";
	/**
	 * 
	 * @param request
	 *            : RestRequest
	 * @return response : RestResponse
	 */
	public RestResponse get(RestRequest request) {

		RestResponse response = new RestResponse();
		// Default: set the response status code to Failure
		response.setStatusCode(RestResponse.StatusCode.FAILURE);

		if (null != request && !TextUtils.isEmpty(request.getTargetUrl())) {
			Log.i(TAG, "Executing GET request with URL: " + request.getTargetUrl() + ", Query String: " + request.getQueryString());

			BufferedReader reader = null;
			HttpResponse httpResponse = null;
			StringBuilder responseStr = null;
			String responseLine = "";
			DefaultHttpClient httpClient = new DefaultHttpClient();

			try {
				StringBuffer uri = new StringBuffer(request.getTargetUrl());
				if (!TextUtils.isEmpty(request.getQueryString())) {
					uri.append(request.getQueryString());
				}
				HttpGet getRequest = new HttpGet(uri.toString());

				if(request.getHeaders() != null){
					for (Map.Entry<String, String> header : request.getHeaders().entrySet()) {
						getRequest.addHeader(header.getKey(), header.getValue());
					}
				}
				httpResponse = httpClient.execute(getRequest);

				if (httpResponse.getStatusLine().getStatusCode() == 200) {
					reader = new BufferedReader(new InputStreamReader((httpResponse.getEntity().getContent())));
					responseStr = new StringBuilder();
					while ((responseLine = reader.readLine()) != null) {
						responseStr.append(responseLine);
					}
					response.setContent(responseStr.toString());
					response.setStatusCode(RestResponse.StatusCode.SUCCESS);
				} else {
					Log.e(TAG,"Failed Http GET Response Code: " + httpResponse.getStatusLine().getStatusCode());
				}

			} catch (Exception e) {
				e.printStackTrace();
				Log.e(TAG,"Exception executing HTTP GET: " + e.getMessage());
			} finally {
				Log.e(TAG,"Shutting down httpClient connection");
				httpClient.getConnectionManager().shutdown();
			}

		} else {
			Log.e(TAG,"Skipped executing incomplete GET request with URL: " + request.getTargetUrl() + ", Query String: "
					+ request.getQueryString());
		}

		return response;
	}

	/**
	 * 
	 * @param request
	 *            : RestRequest
	 * @return response : RestResponse
	 */
	public RestResponse post(RestRequest request) {

		RestResponse response = new RestResponse();
		// Default: set the response status code to Failure
		response.setStatusCode(RestResponse.StatusCode.FAILURE);

		if (null != request && !TextUtils.isEmpty(request.getPayload())) {
			Log.i(TAG,"Executing POST request with URL: " + request.getTargetUrl() + ", Payload: " + request.getPayload());

			BufferedReader reader = null;
			HttpResponse httpResponse = null;
			StringBuilder responseStr = null;
			String responseLine = "";
			DefaultHttpClient httpClient = new DefaultHttpClient();

			try {
				HttpPost httpPost = new HttpPost(request.getTargetUrl());
				StringEntity payload = new StringEntity(request.getPayload(), request.getCharEncoding());
				payload.setContentType(request.getContentType());
				httpPost.setEntity(payload);
				if(request.getHeaders() != null){
				for (Map.Entry<String, String> header : request.getHeaders().entrySet()) {
					httpPost.addHeader(header.getKey(), header.getValue());
				}
				}
				httpResponse = httpClient.execute(httpPost);

				if (httpResponse.getStatusLine().getStatusCode() == 200) {
					reader = new BufferedReader(new InputStreamReader((httpResponse.getEntity().getContent())));
					responseStr = new StringBuilder();
					while ((responseLine = reader.readLine()) != null) {
						responseStr.append(responseLine);
					}
					response.setContent(responseStr.toString());
					response.setStatusCode(RestResponse.StatusCode.SUCCESS);
				} else {
					Log.e(TAG,"Failed Http POST Response Code: " + httpResponse.getStatusLine().getStatusCode());
				}

			} catch (Exception e) {
				Log.e(TAG,"Exception executing HTTP POST: " + e.getMessage());
			} finally {
				httpClient.getConnectionManager().shutdown();
			}

		} else {
			Log.e(TAG,"Skipped executing incomplete POST request with URL: " + request.getTargetUrl() + ", Payload: "
					+ request.getPayload());
		}

		return response;
	}
}
