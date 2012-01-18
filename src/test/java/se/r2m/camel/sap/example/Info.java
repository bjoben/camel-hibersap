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

import org.hibersap.annotations.BapiStructure;
import org.hibersap.annotations.Parameter;

@BapiStructure
public class Info implements Serializable {
	private static final long serialVersionUID = 1L;

	@Parameter("RFCDBSYS")
	String dbSys;

	@Parameter("RFCIPADDR")
	String ipAddress;

	@Parameter("RFCHOST")
	String host;

	public String getDbSys() {
		return dbSys;
	}

	public void setDbSys(String dbSys) {
		this.dbSys = dbSys;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	@Override
	public String toString() {
		return "Info{" + "dbSys=" + dbSys + "ipAddress=" + ipAddress + "host=" + host + '}';
	}


	//RFCPROTO:        011
	//RFCCHARTYP:        1100
	//RFCINTTYP:        BIG
	//RFCFLOTYP:        IE3
	//RFCDEST:        seslco25_KFT_00
	//RFCHOST:        seslco25
	//RFCSYSID:        KFT
	//RFCDATABS:        KFT
	//RFCDBHOST:        seslco25
	//RFCDBSYS:        ORACLE
	//RFCSAPRL:        46C
	//RFCMACH:          370
	//RFCOPSYS:        SunOS
	//RFCTZONE:          3600
	//RFCDAYST:
	//RFCIPADDR:        139.96.113.36
	//RFCKERNRL:        46D
	//RFCHOST2:        seslco25
	//RFCSI_RESV:
}
