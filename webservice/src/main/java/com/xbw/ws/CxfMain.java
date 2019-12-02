package com.xbw.ws;

import com.xbw.ws.service.IService;
import com.xbw.ws.service.common.Common;
import com.xbw.ws.service.impl.ServiceImpl;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.endpoint.dynamic.DynamicClientFactory;
import org.apache.cxf.frontend.ServerFactoryBean;
import org.apache.cxf.jaxws.JaxWsClientFactoryBean;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

import javax.jws.WebMethod;
import javax.xml.namespace.QName;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xbw
 * @version 1.0
 * @date 2019-11-29 13:41
 * @description
 */
public class CxfMain {
    /**
     * @param args
     * @WebMethod(exclude = true) wsdl中无此节点
     */
    public static void main(String[] args) {
        System.out.println("ServiceImpl:");
        IService service = new ServiceImpl();
        server(Common.SERVICE_LOCATION, service);
//        client(Common.SERVICE_LOCATION_WSDL, Common.SERVICE);
//        System.out.println();
//
//        System.out.println("ServiceHelloImpl:");
//        service = new ServiceHelloImpl();
//        server(Common.SERVICE_LOCATION_HELLO, service);
//        client(Common.SERVICE_LOCATION_HELLO_WSDL, Common.SERVICE_HELLO);
//        System.out.println();
//
//        System.out.println("ServiceByeImpl:");
//        service = new ServiceByeImpl();
//        server(Common.SERVICE_LOCATION_BYE, service);
//        client(Common.SERVICE_LOCATION_BYE_WSDL, Common.SERVICE_BYE);
//        System.out.println();
//
        client(Common.SERVICE_LOCATION_WSDL);
        client(Common.SERVICE_LOCATION_WSDL, "sayHello", "CXF World");
        client(Common.SERVICE_LOCATION_WSDL, "sayBye");
    }

    public static void server(String wsdlLocation, IService service) {
        ServerFactoryBean serverFactory = new JaxWsServerFactoryBean();
        serverFactory.setAddress(wsdlLocation);
        serverFactory.setServiceClass(service.getClass());
        serverFactory.setServiceBean(service);
        serverFactory.create();
    }

    public static void client(String wsdlLocation) {
        System.out.println("JaxWsProxyFactoryBean");
        JaxWsProxyFactoryBean factoryBean = new JaxWsProxyFactoryBean();
        factoryBean.setServiceClass(IService.class);
        factoryBean.setAddress(wsdlLocation);
        IService service = (IService) factoryBean.create();
        System.out.println("sayHello>>" + service.sayHello("CXF World"));
        System.out.println("sayBye>>" + service.sayBye());
    }



    public static void client(String wsdlLocation, QName serviceName) {
        System.out.println("JaxWsDynamicClientFactory");
        DynamicClientFactory clientFactory = JaxWsDynamicClientFactory.newInstance();
        try {
            Client client = clientFactory.createClient(wsdlLocation, serviceName);
            Object[] objects = client.invoke("sayHello", "CXF World");
            System.out.println("sayHello>>" + objects[0]);
            objects = client.invoke("sayBye");
            System.out.println("sayBye>>" + objects[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void client(String wsdlUrl, String method, Object... args) {
        System.out.println("JaxWsDynamicClientFactory");
        DynamicClientFactory clientFactory = JaxWsDynamicClientFactory.newInstance();
        try {
            Client client = clientFactory.createClient(wsdlUrl);
            Object[] objects = client.invoke(method, args);
            System.out.println(method + ">>" + objects[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void clientByJaxWsClientFactoryBean(String wsdlLocation) {
        System.out.println("JaxWsClientFactoryBean");
        JaxWsClientFactoryBean factoryBean = new JaxWsClientFactoryBean();
        factoryBean.setServiceClass(IService.class);
        factoryBean.setAddress(wsdlLocation);

        Client client = factoryBean.create();

        Map operationNames = new HashMap<String, String>();
        Method[] methods = IService.class.getMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(WebMethod.class)) {
                WebMethod webMethodAnnotation = method.getAnnotation(WebMethod.class);
                if (!webMethodAnnotation.operationName().equals("")) {
                    operationNames.put(method.getName(), webMethodAnnotation.operationName());
                    continue;
                }
            }
            operationNames.put(method.getName(), method.getName());
        }
        System.out.println(operationNames.entrySet());
    }
}
