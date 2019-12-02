package com.xbw.ws;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Endpoint;
import javax.xml.ws.Service;

import com.xbw.ws.service.IService;
import com.xbw.ws.service.common.Common;
import com.xbw.ws.service.impl.ServiceByeImpl;
import com.xbw.ws.service.impl.ServiceHelloImpl;
import com.xbw.ws.service.impl.ServiceImpl;

/**
 *
 * @author xbw
 * @date 2019-11-29 13:41
 * @version 1.0
 * @description
 *
 */
public class JaxWsMain {
	/**
	 * @WebMethod(exclude = true) wsdl中有此节点且为实现类方法
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("ServiceImpl:");
		IService service = new ServiceImpl();
		server(Common.SERVICE_LOCATION, service);
		client(Common.SERVICE_LOCATION, Common.SERVICE);
		System.out.println();

		System.out.println("ServiceHelloImpl:");
		service = new ServiceHelloImpl();
		server(Common.SERVICE_LOCATION_HELLO, service);
		client(Common.SERVICE_LOCATION_HELLO, Common.SERVICE_HELLO);
		System.out.println();

		System.out.println("ServiceByeImpl:");
		service = new ServiceByeImpl();
		server(Common.SERVICE_LOCATION_BYE, service);
		client(Common.SERVICE_LOCATION_BYE, Common.SERVICE_BYE);
		System.out.println();
	}

	public static void server(String wsdlLocation, IService service) {
		Endpoint.publish(wsdlLocation, service);
	}

	public static void client(String wsdlLocation, QName serviceName) {
		try {
			URL url = new URL(wsdlLocation);
			Service service = Service.create(url, serviceName);
			IService iService = service.getPort(IService.class);
			String response = iService.sayHello("JAX-WS World");
			System.out.println("sayHello>>" + response);
			response = iService.sayBye();
			System.out.println("sayBye>>" + response);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

}
