package hscrmwebsrvintf;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for TReturnInfo complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="TReturnInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="RtnMsg" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="OutputPara" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ReturnCode" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TReturnInfo", propOrder = { "rtnMsg", "outputPara",
		"returnCode" })
public class TReturnInfo {

	@XmlElement(name = "RtnMsg", required = true)
	protected String rtnMsg;
	@XmlElement(name = "OutputPara", required = true)
	protected String outputPara;
	@XmlElement(name = "ReturnCode")
	protected int returnCode;

	/**
	 * Gets the value of the rtnMsg property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getRtnMsg() {
		return rtnMsg;
	}

	/**
	 * Sets the value of the rtnMsg property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setRtnMsg(String value) {
		this.rtnMsg = value;
	}

	/**
	 * Gets the value of the outputPara property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getOutputPara() {
		return outputPara;
	}

	/**
	 * Sets the value of the outputPara property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setOutputPara(String value) {
		this.outputPara = value;
	}

	/**
	 * Gets the value of the returnCode property.
	 * 
	 */
	public int getReturnCode() {
		return returnCode;
	}

	/**
	 * Sets the value of the returnCode property.
	 * 
	 */
	public void setReturnCode(int value) {
		this.returnCode = value;
	}

}
