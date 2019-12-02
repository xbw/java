package com.xbw.ws.service.impl;

import javax.jws.WebService;

import com.xbw.ws.service.IService;
import org.springframework.stereotype.Component;

/**
 *
 * @author xbw
 * @date 2019-11-28 17:28
 * @version 1.0
 * @description
 *
 */
@Component
@WebService(targetNamespace = "http://service.ws.xbw.com/", endpointInterface = "com.xbw.ws.service.IService")
public class ServiceImpl implements IService {

	@Override
	public String sayHello(String name) {
		if (name == null) {
			name = "ServiceImpl";
		}
		return String.format("Hello %s !", name);
	}

	@Override
	public String sayBye() {
		return "Bye ServiceImpl !";
	}

}
