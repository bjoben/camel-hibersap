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

import org.apache.camel.Endpoint;
import org.apache.camel.impl.DefaultComponent;

import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibersap.configuration.AnnotationConfiguration;
import org.hibersap.configuration.xml.SessionManagerConfig;
import org.hibersap.session.SessionManager;

/**
 * Camel component that manages {@link HibersapEndpoint}.
 *
 */
public class HibersapComponent extends DefaultComponent {

	private static final Log log = LogFactory.getLog(HibersapComponent.class);

	@Override
	protected Endpoint createEndpoint(String uri, String remaining, Map<String, Object> parameters) throws Exception {
		log.info(String.format("creating sap endpoint: uri=%s, remaining=%s", uri, remaining));
		for (String key : parameters.keySet()) {
			log.debug("parameter key: " + parameters.get(key));
		}

		if (remaining == null) {
			throw new IllegalArgumentException("need hibersap session manager configuration in endpoint uri");
		}

		AnnotationConfiguration config = null;
		try {   
			config = new AnnotationConfiguration(remaining);
		} catch (Exception e) {
			;
		}


		if (config == null) {

			SessionManagerConfig sessionConfig =
					getCamelContext().getRegistry().lookup(remaining, SessionManagerConfig.class);

			if (sessionConfig == null) {
				Map<String, SessionManagerConfig> configs = 
						getCamelContext().getRegistry().lookupByType(SessionManagerConfig.class);

				for (SessionManagerConfig c : configs.values()) {
					if (c.getName().equals(remaining)) {
						sessionConfig = c;
					}
				}

			}

			if (sessionConfig != null) {
				config = new AnnotationConfiguration(sessionConfig);
			}

		}

		if  (config == null) {
			throw new IllegalArgumentException("no hibersap session manager configuration found with name " + remaining);
		}

		SessionManagerConfig sessionConfig = config.getSessionManagerConfig();

		// allow overriding some values by endpoint
		setProperties(sessionConfig, parameters);

		SessionManager sessionManager = config.buildSessionManager();

		Endpoint endpoint = new HibersapEndpoint(uri, this, sessionManager);
		return endpoint ;
	}

}
