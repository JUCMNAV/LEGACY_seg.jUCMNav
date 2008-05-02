package seg.jUCMNav.kpi.ws;

/**
 * @author pchen
 * 
 */
public class KPIValueWebServiceBeanProxy implements seg.jUCMNav.kpi.ws.KPIValueWebServiceBean {
    private String _endpoint = null;
    private seg.jUCMNav.kpi.ws.KPIValueWebServiceBean kPIValueWebServiceBean = null;

    // Use to get a proxy class for KPIValueWebServiceBeanPort
    private java.lang.String KPIValueWebServiceBeanPort_address = ""; //$NON-NLS-1$

    public KPIValueWebServiceBeanProxy(String address) {
        this.KPIValueWebServiceBeanPort_address = address;
        _initKPIValueWebServiceBeanProxy();
    }

    private void _initKPIValueWebServiceBeanProxy() {
        try {
            kPIValueWebServiceBean = (new seg.jUCMNav.kpi.ws.KPIValueWebServiceBeanServiceLocator(KPIValueWebServiceBeanPort_address))
                    .getKPIValueWebServiceBeanPort();
            if (kPIValueWebServiceBean != null) {
                if (_endpoint != null)
                    ((javax.xml.rpc.Stub) kPIValueWebServiceBean)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint); //$NON-NLS-1$
                else
                    _endpoint = (String) ((javax.xml.rpc.Stub) kPIValueWebServiceBean)._getProperty("javax.xml.rpc.service.endpoint.address"); //$NON-NLS-1$
            }

        } catch (javax.xml.rpc.ServiceException serviceException) {
        }
    }

    public String getEndpoint() {
        return _endpoint;
    }

    public void setEndpoint(String endpoint) {
        _endpoint = endpoint;
        if (kPIValueWebServiceBean != null)
            ((javax.xml.rpc.Stub) kPIValueWebServiceBean)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint); //$NON-NLS-1$

    }

    public seg.jUCMNav.kpi.ws.KPIValueWebServiceBean getKPIValueWebServiceBean() {
        if (kPIValueWebServiceBean == null)
            _initKPIValueWebServiceBeanProxy();
        return kPIValueWebServiceBean;
    }

    public seg.jUCMNav.kpi.ws.KpiEntity[] retrieveKPIValues(seg.jUCMNav.kpi.ws.KpiEntity[] kpiEntities) throws java.rmi.RemoteException {
        if (kPIValueWebServiceBean == null)
            _initKPIValueWebServiceBeanProxy();
        return kPIValueWebServiceBean.retrieveKPIValues(kpiEntities);
    }

}