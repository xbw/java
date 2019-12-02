package com.xbw.ws.service.impl;

import javax.jws.WebMethod;
import javax.jws.WebService;

import com.xbw.ws.service.IService;

/**
 *
 * @author xbw
 * @date 2019-11-28 17:28
 * @version 1.0
 * @description
 *
 */
@WebService(targetNamespace = "http://service.ws.xbw.com/", endpointInterface = "com.xbw.ws.service.IService")
public class ServiceByeImpl implements IService {
	@WebMethod(exclude = true)
	@Override
	public String sayHello(String name) {
		return null;
	}

	@Override
	public String sayBye() {
		return "Bye ServiceByeImpl !";
	}

}
