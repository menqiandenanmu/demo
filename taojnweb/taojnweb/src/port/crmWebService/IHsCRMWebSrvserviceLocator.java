/**
 * IHsCRMWebSrvserviceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */


package crmWebService;

public class IHsCRMWebSrvserviceLocator extends org.apache.axis.client.Service implements IHsCRMWebSrvservice {

    public IHsCRMWebSrvserviceLocator() {
    }


    public IHsCRMWebSrvserviceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public IHsCRMWebSrvserviceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for IHsCRMWebSrvPort
    private java.lang.String IHsCRMWebSrvPort_address = "http://60.190.136.234:83/HsCrmWebSrv/HsCRMWebSrv.dll/soap/IHsCRMWebSrv";
    //private java.lang.String IHsCRMWebSrvPort_address = "http://60.190.136.234:83/HsCrmWebSrv/HsCRMWebSrv.dll/wsdl/IHsCRMWebSrv";

    public java.lang.String getIHsCRMWebSrvPortAddress() {
        return IHsCRMWebSrvPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String IHsCRMWebSrvPortWSDDServiceName = "IHsCRMWebSrvPort";

    public java.lang.String getIHsCRMWebSrvPortWSDDServiceName() {
        return IHsCRMWebSrvPortWSDDServiceName;
    }

    public void setIHsCRMWebSrvPortWSDDServiceName(java.lang.String name) {
        IHsCRMWebSrvPortWSDDServiceName = name;
    }

    public IHsCRMWebSrv getIHsCRMWebSrvPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(IHsCRMWebSrvPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getIHsCRMWebSrvPort(endpoint);
    }

    public IHsCRMWebSrv getIHsCRMWebSrvPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            IHsCRMWebSrvbindingStub _stub = new IHsCRMWebSrvbindingStub(portAddress, this);
            _stub.setPortName(getIHsCRMWebSrvPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setIHsCRMWebSrvPortEndpointAddress(java.lang.String address) {
        IHsCRMWebSrvPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (IHsCRMWebSrv.class.isAssignableFrom(serviceEndpointInterface)) {
                IHsCRMWebSrvbindingStub _stub = new IHsCRMWebSrvbindingStub(new java.net.URL(IHsCRMWebSrvPort_address), this);
                _stub.setPortName(getIHsCRMWebSrvPortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("IHsCRMWebSrvPort".equals(inputPortName)) {
            return getIHsCRMWebSrvPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://tempuri.org/", "IHsCRMWebSrvservice");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://tempuri.org/", "IHsCRMWebSrvPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("IHsCRMWebSrvPort".equals(portName)) {
            setIHsCRMWebSrvPortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
