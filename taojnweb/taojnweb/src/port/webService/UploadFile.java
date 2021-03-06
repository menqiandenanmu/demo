package webService;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for anonymous complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="in0" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *         &lt;element name="in1" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "in0", "in1" })
@XmlRootElement(name = "uploadFile")
public class UploadFile {

	@XmlElement(required = true, nillable = true)
	protected byte[] in0;
	@XmlElement(required = true, nillable = true)
	protected String in1;

	/**
	 * Gets the value of the in0 property.
	 * 
	 * @return possible object is byte[]
	 */
	public byte[] getIn0() {
		return in0;
	}

	/**
	 * Sets the value of the in0 property.
	 * 
	 * @param value
	 *            allowed object is byte[]
	 */
	public void setIn0(byte[] value) {
		this.in0 = ((byte[]) value);
	}

	/**
	 * Gets the value of the in1 property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getIn1() {
		return in1;
	}

	/**
	 * Sets the value of the in1 property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setIn1(String value) {
		this.in1 = value;
	}

}
