package org.tempuri;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;

/**
 * This class was generated by the JAX-WS RI. JAX-WS RI 2.1.3-hudson-390-
 * Generated source version: 2.0
 * <p>
 * An example of how this class may be used:
 * 
 * <pre>
 * IHsCRMWebSrvservice service = new IHsCRMWebSrvservice();
 * IHsCRMWebSrv portType = service.getIHsCRMWebSrvPort();
 * portType.iWsPosCommOperate(...);
 * </pre>
 * 
 * </p>
 * 
 */
@WebServiceClient(name = "IHsCRMWebSrvservice", targetNamespace = "http://tempuri.org/", wsdlLocation = "http://60.190.136.234:83/HsCrmWebSrv/HsCRMWebSrv.dll/wsdl/IHsCRMWebSrv?wsdl")
public class IHsCRMWebSrvservice extends Service {

	private final static URL IHSCRMWEBSRVSERVICE_WSDL_LOCATION;
	private final static Logger logger = Logger
			.getLogger(org.tempuri.IHsCRMWebSrvservice.class.getName());

	static {
		URL url = null;
		try {
			URL baseUrl;
			baseUrl = org.tempuri.IHsCRMWebSrvservice.class.getResource(".");
			url = new URL(baseUrl,
					"http://60.190.136.234:83/HsCrmWebSrv/HsCRMWebSrv.dll/wsdl/IHsCRMWebSrv?wsdl");
		} catch (MalformedURLException e) {
			logger
					.warning("Failed to create URL for the wsdl Location: 'http://222.173.107.76:8000/HisComSvr/HsCRMWebSrv.dll/wsdl/IHsCRMWebSrv?wsdl', retrying as a local file");
			logger.warning(e.getMessage());
		}
		IHSCRMWEBSRVSERVICE_WSDL_LOCATION = url;
	}

	public IHsCRMWebSrvservice(URL wsdlLocation, QName serviceName) {
		super(wsdlLocation, serviceName);
	}

	public IHsCRMWebSrvservice() {
		super(IHSCRMWEBSRVSERVICE_WSDL_LOCATION, new QName(
				"http://tempuri.org/", "IHsCRMWebSrvservice"));
	}

	/**
	 * 
	 * @return returns IHsCRMWebSrv
	 */
	@WebEndpoint(name = "IHsCRMWebSrvPort")
	public IHsCRMWebSrv getIHsCRMWebSrvPort() {
		return super.getPort(new QName("http://tempuri.org/",
				"IHsCRMWebSrvPort"), IHsCRMWebSrv.class);
	}

}
