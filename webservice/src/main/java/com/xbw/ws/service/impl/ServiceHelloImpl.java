package com.xbw.ws.service.impl;

import com.xbw.ws.service.IService;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author xbw
 * @date 2019-11-28 17:28
 * @version 1.0
 * @description
 *
 */

@WebService(targetNamespace = "http://service.ws.xbw.com/", endpointInterface = "com.xbw.ws.service.IService")
public class ServiceHelloImpl implements IService {
	@Override
	public String sayHello(@WebParam(name = "name") String name) {
		if (name == null) {
			name = "World";
		}
		return String.format("Hello %s !", name);
	}

	@WebMethod(exclude = true)
	@Override
	public String sayBye() {
		return null;
	}
}
