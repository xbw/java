package com.xbw.ws.service.impl;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.BindingType;
import javax.xml.ws.soap.SOAPBinding;

import com.xbw.ws.service.IService;

/**
 *
 * @author xbw
 * @date 2019-11-28 17:28
 * @version 1.0
 * @description
 *
 */
@BindingType(SOAPBinding.SOAP12HTTP_BINDING)
//@BindingType(SOAPBinding.SOAP12HTTP_MTOM_BINDING)// 通过启用消息传输优化机制 (MTOM)，可以按最优方式发送和接收二进制数据，而不会带来在 XML文档中嵌入二进制数据所需的数据编码开销。
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
