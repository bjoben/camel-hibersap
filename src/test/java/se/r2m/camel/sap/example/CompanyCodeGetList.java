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
import java.util.List;
import org.hibersap.annotations.Bapi;
import org.hibersap.annotations.Export;
import org.hibersap.annotations.Parameter;
import org.hibersap.annotations.ParameterType;
import org.hibersap.annotations.Table;
import org.hibersap.annotations.ThrowExceptionOnError;
import org.hibersap.bapi.BapiConstants;
import org.hibersap.bapi.BapiRet2;

@Bapi("BAPI_COMPANYCODE_GETLIST")
@ThrowExceptionOnError
public class CompanyCodeGetList implements Serializable {
	private static final long serialVersionUID = 1L;

	@Table
	@Parameter("COMPANYCODE_LIST")
	private List<CompanyCode> companyCodeList;

	@Export
	@Parameter(value = BapiConstants.RETURN, type = ParameterType.STRUCTURE)
	private BapiRet2 bapiReturn;

	public BapiRet2 getBapiReturn() {
		return bapiReturn;
	}

	public void setBapiReturn(BapiRet2 bapiReturn) {
		this.bapiReturn = bapiReturn;
	}

	public List<CompanyCode> getCompanyCodeList() {
		return companyCodeList;
	}

	public void setCompanyCodeList(List<CompanyCode> companyCodeList) {
		this.companyCodeList = companyCodeList;
	}

	@Override
	public String toString() {
		return "CompanyCodeGetList{" + "companyCodeList=" + companyCodeList + "bapiReturn=" + bapiReturn + '}';
	}


}
