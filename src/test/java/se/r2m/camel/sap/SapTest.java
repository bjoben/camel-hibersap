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
package se.r2m.camel.sap;

import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import se.r2m.camel.sap.example.CompanyCode;
import se.r2m.camel.sap.example.CompanyCodeGetList;
import se.r2m.camel.sap.example.StfcConnection;
import se.r2m.camel.sap.example.SystemInfo;

@ContextConfiguration
public class SapTest extends CamelTestSupport {

	@Test
	public void testSessionManagerAndCamelConfig() {

		resolveMandatoryEndpoint("hibersap:A12");

		// Session manager A13 and A14 is the same. Just identified differently.
		resolveMandatoryEndpoint("hibersap:A13");
		resolveMandatoryEndpoint("hibersap:A14");

		try {
			resolveMandatoryEndpoint("hibersap:A15");
			fail("there is no session manager named A15");
		} catch (Exception e) {

		}

		try {
			StfcConnection request = new StfcConnection();
			request.setRequestText("test sap");
			template.requestBody("direct:A12", request, StfcConnection.class);
			fail("StfcConnection is not mapped on session manager A12");
		} catch (Exception e) {

		}

	}

	//@Test
	public void testStfcConnection() throws Exception {

		StfcConnection request = new StfcConnection();
		request.setRequestText("test sap");

		StfcConnection response = template.requestBody("direct:A13", request, StfcConnection.class);
		assertNotNull(response);
		assertNotNull(response.getEchoText());
		System.out.println("request: " + request);
		System.out.println("response: " + response);
		assertTrue(request.equals(response) == false);

	}

	// @Test
	public void testSystemInfo() throws Exception {

		SystemInfo systemInfo = new SystemInfo();
		systemInfo = template.requestBody("direct:A14", systemInfo, SystemInfo.class);
		assertNotNull(systemInfo);
		assertNotNull(systemInfo.getInfo());
		System.out.println("systemInfo: " + systemInfo);

	}

	// @Test
	public void testCompanyCodeList() throws Exception {

		CompanyCodeGetList companyList = new CompanyCodeGetList();
		companyList = template.requestBody("direct:A13", companyList, CompanyCodeGetList.class);
		assertNotNull(companyList);
		assertNotNull(companyList.getBapiReturn());
		System.out.println("return type: " + companyList.getBapiReturn().getType());
		assertTrue(companyList.getCompanyCodeList().isEmpty() == false);
		for (CompanyCode cc : companyList.getCompanyCodeList()) {
			System.out.println("company: " + cc);
		}


	}
	
//
//	@Override
//	protected AbstractXmlApplicationContext createApplicationContext() {
//		return new ClassPathXmlApplicationContext("META-INF/spring/test-context.xml");
//	}
}
