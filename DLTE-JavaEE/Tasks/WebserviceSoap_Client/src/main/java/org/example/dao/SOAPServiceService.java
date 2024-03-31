
package org.example.dao;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "SOAPServiceService", targetNamespace = "http://dao.soap/", wsdlLocation = "http://localhost:1234/Account?wsdl")
public class SOAPServiceService
    extends Service
{

    private final static URL SOAPSERVICESERVICE_WSDL_LOCATION;
    private final static WebServiceException SOAPSERVICESERVICE_EXCEPTION;
    private final static QName SOAPSERVICESERVICE_QNAME = new QName("http://dao.soap/", "SOAPServiceService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:1234/Account?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        SOAPSERVICESERVICE_WSDL_LOCATION = url;
        SOAPSERVICESERVICE_EXCEPTION = e;
    }

    public SOAPServiceService() {
        super(__getWsdlLocation(), SOAPSERVICESERVICE_QNAME);
    }

    public SOAPServiceService(WebServiceFeature... features) {
        super(__getWsdlLocation(), SOAPSERVICESERVICE_QNAME, features);
    }

    public SOAPServiceService(URL wsdlLocation) {
        super(wsdlLocation, SOAPSERVICESERVICE_QNAME);
    }

    public SOAPServiceService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, SOAPSERVICESERVICE_QNAME, features);
    }

    public SOAPServiceService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public SOAPServiceService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns SOAPService
     */
    @WebEndpoint(name = "SOAPServicePort")
    public SOAPService getSOAPServicePort() {
        return super.getPort(new QName("http://dao.soap/", "SOAPServicePort"), SOAPService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns SOAPService
     */
    @WebEndpoint(name = "SOAPServicePort")
    public SOAPService getSOAPServicePort(WebServiceFeature... features) {
        return super.getPort(new QName("http://dao.soap/", "SOAPServicePort"), SOAPService.class, features);
    }

    private static URL __getWsdlLocation() {
        if (SOAPSERVICESERVICE_EXCEPTION!= null) {
            throw SOAPSERVICESERVICE_EXCEPTION;
        }
        return SOAPSERVICESERVICE_WSDL_LOCATION;
    }

}