/**
 * TReturnInfo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */


package crmWebService;

public class TReturnInfo  implements java.io.Serializable {
    private java.lang.String rtnMsg;

    private java.lang.String outputPara;

    private int returnCode;

    public TReturnInfo() {
    }

    public TReturnInfo(
           java.lang.String rtnMsg,
           java.lang.String outputPara,
           int returnCode) {
           this.rtnMsg = rtnMsg;
           this.outputPara = outputPara;
           this.returnCode = returnCode;
    }


    /**
     * Gets the rtnMsg value for this TReturnInfo.
     * 
     * @return rtnMsg
     */
    public java.lang.String getRtnMsg() {
        return rtnMsg;
    }


    /**
     * Sets the rtnMsg value for this TReturnInfo.
     * 
     * @param rtnMsg
     */
    public void setRtnMsg(java.lang.String rtnMsg) {
        this.rtnMsg = rtnMsg;
    }


    /**
     * Gets the outputPara value for this TReturnInfo.
     * 
     * @return outputPara
     */
    public java.lang.String getOutputPara() {
        return outputPara;
    }


    /**
     * Sets the outputPara value for this TReturnInfo.
     * 
     * @param outputPara
     */
    public void setOutputPara(java.lang.String outputPara) {
        this.outputPara = outputPara;
    }


    /**
     * Gets the returnCode value for this TReturnInfo.
     * 
     * @return returnCode
     */
    public int getReturnCode() {
        return returnCode;
    }


    /**
     * Sets the returnCode value for this TReturnInfo.
     * 
     * @param returnCode
     */
    public void setReturnCode(int returnCode) {
        this.returnCode = returnCode;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof TReturnInfo)) return false;
        TReturnInfo other = (TReturnInfo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.rtnMsg==null && other.getRtnMsg()==null) || 
             (this.rtnMsg!=null &&
              this.rtnMsg.equals(other.getRtnMsg()))) &&
            ((this.outputPara==null && other.getOutputPara()==null) || 
             (this.outputPara!=null &&
              this.outputPara.equals(other.getOutputPara()))) &&
            this.returnCode == other.getReturnCode();
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
        if (getRtnMsg() != null) {
            _hashCode += getRtnMsg().hashCode();
        }
        if (getOutputPara() != null) {
            _hashCode += getOutputPara().hashCode();
        }
        _hashCode += getReturnCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TReturnInfo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:HsCRMWebSrvIntf", "TReturnInfo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("rtnMsg");
        elemField.setXmlName(new javax.xml.namespace.QName("", "RtnMsg"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("outputPara");
        elemField.setXmlName(new javax.xml.namespace.QName("", "OutputPara"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("returnCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ReturnCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
