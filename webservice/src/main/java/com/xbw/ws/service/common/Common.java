package com.xbw.ws.service.common;

import javax.xml.namespace.QName;

/**
 * @author xbw
 * @version 1.0
 * @date 2019-12-02 09:30
 * @description
 */
public class Common {
    public final static String LOCATION = "http://127.0.0.1:8080/services/";
    public final static String NAMESPACEURI = "http://service.ws.xbw.com/";

    public final static String SERVICE_LOCATION = LOCATION + "ws";
    public final static String SERVICE_LOCATION_WSDL = SERVICE_LOCATION + "?wsdl";
    public final static QName SERVICE = new QName(NAMESPACEURI, "ServiceImplService");
    public final static QName SERVICE_PORT = new QName(NAMESPACEURI, "ServiceImplPort");

    public final static String SERVICE_LOCATION_HELLO = LOCATION + "hello";
    public final static String SERVICE_LOCATION_HELLO_WSDL = SERVICE_LOCATION_HELLO + "?wsdl";
    public final static QName SERVICE_HELLO = new QName(NAMESPACEURI, "ServiceHelloImplService");
    public final static QName SERVICE_PORT_HELLO = new QName(NAMESPACEURI, "ServiceHelloImplPort");

    public final static String SERVICE_LOCATION_BYE = LOCATION + "bye";
    public final static String SERVICE_LOCATION_BYE_WSDL = SERVICE_LOCATION_BYE + "?wsdl";
    public final static QName SERVICE_BYE = new QName(NAMESPACEURI, "ServiceByeImplService");
    public final static QName SERVICE_PORT_BYE = new QName(NAMESPACEURI, "ServiceByeImplPort");

}
