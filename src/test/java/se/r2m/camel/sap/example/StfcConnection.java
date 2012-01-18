/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package se.r2m.camel.sap.example;

import java.io.Serializable;

import org.hibersap.annotations.Bapi;
import org.hibersap.annotations.Export;
import org.hibersap.annotations.Import;
import org.hibersap.annotations.Parameter;

@Bapi("STFC_CONNECTION")
public class StfcConnection implements Serializable {
	private static final long serialVersionUID = 1L;

	@Import
	@Parameter("REQUTEXT")
	public String requestText;

	@Export
	@Parameter("RESPTEXT")
	public String responseText;

	@Export
	@Parameter("ECHOTEXT")
	public String echoText;

	public String getEchoText() {
		return echoText;
	}

	public void setEchoText(String echoText) {
		this.echoText = echoText;
	}

	public String getRequestText() {
		return requestText;
	}

	public void setRequestText(String requestText) {
		this.requestText = requestText;
	}

	public String getResponseText() {
		return responseText;
	}

	public void setResponseText(String responseText) {
		this.responseText = responseText;
	}


	@Override
	public String toString() {
		return "StfcConnection{" + " reqeust=" + requestText + " response=" + responseText + " echo=" + echoText + "}";
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final StfcConnection other = (StfcConnection) obj;
		if ((this.requestText == null) ? (other.requestText != null) : !this.requestText.equals(other.requestText)) {
			return false;
		}
		if ((this.responseText == null) ? (other.responseText != null) : !this.responseText.equals(other.responseText)) {
			return false;
		}
		if ((this.echoText == null) ? (other.echoText != null) : !this.echoText.equals(other.echoText)) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		int hash = 3;
		hash = 47 * hash + (this.requestText != null ? this.requestText.hashCode() : 0);
		hash = 47 * hash + (this.responseText != null ? this.responseText.hashCode() : 0);
		hash = 47 * hash + (this.echoText != null ? this.echoText.hashCode() : 0);
		return hash;
	}



}
