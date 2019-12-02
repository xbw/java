package com.xbw.ws.service;

import javax.jws.WebService;

@WebService
public interface IService {
	public default String sayHello(String name) {
		if (name == null) {
			name = "IService";
		}
		return String.format("Hello %s !", name);
	}

	public default String sayBye() {
		return "Bye IService !";
	}
//	public String sayHello(String name);
//
//	public String sayBye();
}
