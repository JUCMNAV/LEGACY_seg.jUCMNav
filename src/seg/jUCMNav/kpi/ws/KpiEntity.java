/**
 * KpiEntity.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.3 Oct 05, 2005 (05:23:37 EDT) WSDL2Java emitter.
 */

package seg.jUCMNav.kpi.ws;

import java.io.Serializable;

/**
 * @author pchen
 * 
 */
public class KpiEntity implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 8557696420078578900L;

    private java.lang.String indicatorName;

    private java.lang.String kpiValue;

    private java.lang.String strategyName;

    public KpiEntity() {
    }

    public KpiEntity(java.lang.String indicatorName, java.lang.String kpiValue, java.lang.String strategyName) {
        this.indicatorName = indicatorName;
        this.kpiValue = kpiValue;
        this.strategyName = strategyName;
    }

    /**
     * Gets the indicatorName value for this KpiEntity.
     * 
     * @return indicatorName
     */
    public java.lang.String getIndicatorName() {
        return indicatorName;
    }

    /**
     * Sets the indicatorName value for this KpiEntity.
     * 
     * @param indicatorName
     */
    public void setIndicatorName(java.lang.String indicatorName) {
        this.indicatorName = indicatorName;
    }

    /**
     * Gets the kpiValue value for this KpiEntity.
     * 
     * @return kpiValue
     */
    public java.lang.String getKpiValue() {
        return kpiValue;
    }

    /**
     * Sets the kpiValue value for this KpiEntity.
     * 
     * @param kpiValue
     */
    public void setKpiValue(java.lang.String kpiValue) {
        this.kpiValue = kpiValue;
    }

    /**
     * Gets the strategyName value for this KpiEntity.
     * 
     * @return strategyName
     */
    public java.lang.String getStrategyName() {
        return strategyName;
    }

    /**
     * Sets the strategyName value for this KpiEntity.
     * 
     * @param strategyName
     */
    public void setStrategyName(java.lang.String strategyName) {
        this.strategyName = strategyName;
    }

    private java.lang.Object __equalsCalc = null;

    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof KpiEntity))
            return false;
        KpiEntity other = (KpiEntity) obj;
        if (this == obj)
            return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true
                && ((this.indicatorName == null && other.getIndicatorName() == null) || (this.indicatorName != null && this.indicatorName.equals(other
                        .getIndicatorName())))
                && ((this.kpiValue == null && other.getKpiValue() == null) || (this.kpiValue != null && this.kpiValue.equals(other.getKpiValue())))
                && ((this.strategyName == null && other.getStrategyName() == null) || (this.strategyName != null && this.strategyName.equals(other
                        .getStrategyName())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;

    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getIndicatorName() != null) {
            _hashCode += getIndicatorName().hashCode();
        }
        if (getKpiValue() != null) {
            _hashCode += getKpiValue().hashCode();
        }
        if (getStrategyName() != null) {
            _hashCode += getStrategyName().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc = new org.apache.axis.description.TypeDesc(KpiEntity.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://service.bpm.com/", "kpiEntity")); //$NON-NLS-1$ //$NON-NLS-2$
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("indicatorName"); //$NON-NLS-1$
        elemField.setXmlName(new javax.xml.namespace.QName("", "indicatorName")); //$NON-NLS-1$ //$NON-NLS-2$
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string")); //$NON-NLS-1$ //$NON-NLS-2$
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("kpiValue"); //$NON-NLS-1$
        elemField.setXmlName(new javax.xml.namespace.QName("", "kpiValue")); //$NON-NLS-1$ //$NON-NLS-2$
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string")); //$NON-NLS-1$ //$NON-NLS-2$
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("strategyName"); //$NON-NLS-1$
        elemField.setXmlName(new javax.xml.namespace.QName("", "strategyName")); //$NON-NLS-1$ //$NON-NLS-2$
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string")); //$NON-NLS-1$ //$NON-NLS-2$
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(java.lang.String mechType, java.lang.Class _javaType, javax.xml.namespace.QName _xmlType) {
        return new org.apache.axis.encoding.ser.BeanSerializer(_javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(java.lang.String mechType, java.lang.Class _javaType, javax.xml.namespace.QName _xmlType) {
        return new org.apache.axis.encoding.ser.BeanDeserializer(_javaType, _xmlType, typeDesc);
    }

}
