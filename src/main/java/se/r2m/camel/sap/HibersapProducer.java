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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.apache.camel.AsyncCallback;
import org.apache.camel.AsyncProcessor;
import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultProducer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibersap.annotations.Bapi;
import org.hibersap.session.Session;
import org.hibersap.session.SessionManager;

public class HibersapProducer extends DefaultProducer implements AsyncProcessor {

	private static final Log log = LogFactory.getLog(HibersapProducer.class);
	private HibersapEndpoint endpoint;

	public HibersapProducer(HibersapEndpoint endpoint) {
		super(endpoint);
		this.endpoint = endpoint;
	}

	@Override
	public void process(Exchange exchange) throws Exception {
		SessionManager sessionManager = endpoint.getSessionManager();
		log.debug("process(exchange) called, getting serializable body");
		Object body = exchange.getIn().getMandatoryBody(Serializable.class);

		log.debug("checking if hibersap annotation present");
		if (!body.getClass().isAnnotationPresent(Bapi.class)) {
		        log.error("No HiberSAP @Bapi Annotation in Body Class: " + body.getClass().getName());
			throw new IllegalArgumentException("body must be a class with @org.hibersap.annotations.Bapi annotations");
		}

		// Copy request object as the Hibersap code will make changes to our original request object.
		Object copy = copy(body);

		log.debug("opening session");

		final Session session = sessionManager.openSession();
		try {
			// session.beginTransaction();
			log.debug("executing function call");
			session.execute(copy);

			if (exchange.getPattern().isOutCapable()) {
				exchange.getOut().setBody(copy);
			}
			// session.getTransaction().commit();
		} finally {
			session.close();
		}
	}


	protected Object copy(Object obj) throws Exception {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ObjectOutputStream os = new ObjectOutputStream(out);
		os.writeObject(obj);
		os.close();

		ObjectInputStream is = new ObjectInputStream(new ByteArrayInputStream(out.toByteArray()));
		Object copy = is.readObject();
		is.close();
		return copy;
	}

	@Override
	public boolean process(Exchange exchange, AsyncCallback callback) {
		boolean sync = true;
		try {
			process(exchange);
		} catch (Exception e) {
			exchange.setException(e);
		} finally {
			callback.done(sync);
		}

		return sync;
	}
}
