/**
 * KPIValueWebServiceBeanServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package seg.jUCMNav.kpi.ws;

/**
 * @author pchen
 * 
 */
public class KPIValueWebServiceBeanServiceLocator extends org.apache.axis.client.Service implements seg.jUCMNav.kpi.ws.KPIValueWebServiceBeanService {

    /**
     * 
     */
    private static final long serialVersionUID = 5376103902552499816L;

    public KPIValueWebServiceBeanServiceLocator(String address) {
        this.KPIValueWebServiceBeanPort_address = address;
    }

    public KPIValueWebServiceBeanServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public KPIValueWebServiceBeanServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for KPIValueWebServiceBeanPort
    private java.lang.String KPIValueWebServiceBeanPort_address = ""; //$NON-NLS-1$

    public java.lang.String getKPIValueWebServiceBeanPortAddress() {
        return KPIValueWebServiceBeanPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String KPIValueWebServiceBeanPortWSDDServiceName = "KPIValueWebServiceBeanPort"; //$NON-NLS-1$

    public java.lang.String getKPIValueWebServiceBeanPortWSDDServiceName() {
        return KPIValueWebServiceBeanPortWSDDServiceName;
    }

    public void setKPIValueWebServiceBeanPortWSDDServiceName(java.lang.String name) {
        KPIValueWebServiceBeanPortWSDDServiceName = name;
    }

    public seg.jUCMNav.kpi.ws.KPIValueWebServiceBean getKPIValueWebServiceBeanPort() throws javax.xml.rpc.ServiceException {
        java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(KPIValueWebServiceBeanPort_address);
        } catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getKPIValueWebServiceBeanPort(endpoint);
    }

    public seg.jUCMNav.kpi.ws.KPIValueWebServiceBean getKPIValueWebServiceBeanPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            seg.jUCMNav.kpi.ws.KPIValueWebServiceBeanPortBindingStub _stub = new seg.jUCMNav.kpi.ws.KPIValueWebServiceBeanPortBindingStub(portAddress, this);
            _stub.setPortName(getKPIValueWebServiceBeanPortWSDDServiceName());
            return _stub;
        } catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setKPIValueWebServiceBeanPortEndpointAddress(java.lang.String address) {
        KPIValueWebServiceBeanPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation. If this service has no port for the given interface, then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (seg.jUCMNav.kpi.ws.KPIValueWebServiceBean.class.isAssignableFrom(serviceEndpointInterface)) {
                seg.jUCMNav.kpi.ws.KPIValueWebServiceBeanPortBindingStub _stub = new seg.jUCMNav.kpi.ws.KPIValueWebServiceBeanPortBindingStub(new java.net.URL(
                        KPIValueWebServiceBeanPort_address), this);
                _stub.setPortName(getKPIValueWebServiceBeanPortWSDDServiceName());
                return _stub;
            }
        } catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " //$NON-NLS-1$
                + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName())); //$NON-NLS-1$
    }

    /**
     * For the given interface, get the stub implementation. If this service has no port for the given interface, then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("KPIValueWebServiceBeanPort".equals(inputPortName)) { //$NON-NLS-1$
            return getKPIValueWebServiceBeanPort();
        } else {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://service.bpm.com/", "KPIValueWebServiceBeanService"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://service.bpm.com/", "KPIValueWebServiceBeanPort")); //$NON-NLS-1$ //$NON-NLS-2$
        }
        return ports.iterator();
    }

    /**
     * Set the endpoint address for the specified port name.
     */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {

        if ("KPIValueWebServiceBeanPort".equals(portName)) { //$NON-NLS-1$
            setKPIValueWebServiceBeanPortEndpointAddress(address);
        } else { // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName); //$NON-NLS-1$
        }
    }

    /**
     * Set the endpoint address for the specified port name.
     */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
