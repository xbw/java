package com.xbw.ws;

import com.xbw.ws.service.IService;
import com.xbw.ws.service.common.Common;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.endpoint.dynamic.DynamicClientFactory;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author xbw
 * @version 1.0
 * @date 2019-11-29 15:56
 * @description
 */
public class Main {

    public static void main(String[] args) throws MalformedURLException {
        spring();

        System.out.println("JAX-WS:");
        client(Common.SERVICE_LOCATION, Common.SERVICE);
        System.out.println();

        System.out.println("CXF:");
        client(Common.SERVICE_LOCATION_WSDL, "sayHello", "CXF World");
        client(Common.SERVICE_LOCATION_WSDL, "sayBye");
        System.out.println();

//		calculator();
    }

    public static void spring() {
        // ClassPathXmlApplicationContext 是读取 src 目录下的配置文件
//        ApplicationContext context = new ClassPathXmlApplicationContext("/main/webapp/WEB-INF/applicationContext.xml");
        //  FileSystemXmlApplicationContext 即系统文件路径，文件的目录。
        ApplicationContext context = new FileSystemXmlApplicationContext("src/main/webapp/WEB-INF/applicationContext.xml");
        IService service = (IService) context.getBean("serviceImpl");
        String result = service.sayHello("Spring");
        System.out.print(result);
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

    public static void client(String wsdlUrl, String method, Object... args) {
        DynamicClientFactory clientFactory = JaxWsDynamicClientFactory.newInstance();
        try {
            Client client = clientFactory.createClient(wsdlUrl);
            Object[] objects = client.invoke(method, args);
            System.out.println(method + ">>" + objects[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void calculator() {
        JaxWsDynamicClientFactory clientFactory = JaxWsDynamicClientFactory.newInstance();
        Client client = clientFactory.createClient("http://www.dneonline.com/calculator.asmx?wsdl");
        Object[] rs;
        try {
            rs = client.invoke("Add", 1, 2);
            System.out.println(rs[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
