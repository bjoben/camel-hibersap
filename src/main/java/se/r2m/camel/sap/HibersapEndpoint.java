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


import org.apache.camel.Consumer;
import org.apache.camel.Processor;
import org.apache.camel.Producer;
import org.apache.camel.impl.DefaultEndpoint;
import org.hibersap.session.SessionManager;

/**
 * Represents an Hibersap session manager endpoint to an SAP system.
 * 
 */
public class HibersapEndpoint extends DefaultEndpoint {

	// The Hibersap session manager
	private SessionManager sessionManager = null;

	public SessionManager getSessionManager() {
		return sessionManager;
	}


	public HibersapEndpoint(String uri, HibersapComponent component, SessionManager sessionManager) {
		super(uri, component);
		this.sessionManager = sessionManager;
	}

	@Override
	public Producer createProducer() throws Exception {
		return new HibersapProducer(this);
	}

	@Override
	public Consumer createConsumer(Processor processor) throws Exception {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

}
