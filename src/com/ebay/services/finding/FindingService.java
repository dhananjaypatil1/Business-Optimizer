
package com.ebay.services.finding;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.1 in JDK 6
 * Generated source version: 2.1
 * 
 */
@WebServiceClient(name = "FindingService", targetNamespace = "http://www.ebay.com/marketplace/search/v1/services", wsdlLocation = "FindingService.wsdl")
public class FindingService
    extends Service
{

    private final static URL FINDINGSERVICE_WSDL_LOCATION;

    static {
        URL url = null;
        try {
            url = new URL("file:/C:/william_dev/eBaySDK/715/findingkit/FindingService.wsdl");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        FINDINGSERVICE_WSDL_LOCATION = url;
    }

    public FindingService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public FindingService() {
        super(FINDINGSERVICE_WSDL_LOCATION, new QName("http://www.ebay.com/marketplace/search/v1/services", "FindingService"));
    }

    /**
     * 
     * @return
     *     returns FindingServicePortType
     */
    @WebEndpoint(name = "FindingServiceSOAPPort")
    public FindingServicePortType getFindingServiceSOAPPort() {
        return (FindingServicePortType)super.getPort(new QName("http://www.ebay.com/marketplace/search/v1/services", "FindingServiceSOAPPort"), FindingServicePortType.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns FindingServicePortType
     */
    @WebEndpoint(name = "FindingServiceSOAPPort")
    public FindingServicePortType getFindingServiceSOAPPort(WebServiceFeature... features) {
        return (FindingServicePortType)super.getPort(new QName("http://www.ebay.com/marketplace/search/v1/services", "FindingServiceSOAPPort"), FindingServicePortType.class, features);
    }

}
