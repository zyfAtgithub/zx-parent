
package com.ct10000.beian;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for jrd_querybeianstatus element declaration.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;element name="jrd_querybeianstatus">
 *   &lt;complexType>
 *     &lt;complexContent>
 *       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *         &lt;sequence>
 *           &lt;element name="jrdId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *           &lt;element name="userName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *           &lt;element name="randVal" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *           &lt;element name="time" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *           &lt;element name="hashAlgorithm" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *           &lt;element name="queryConditionType" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *           &lt;element name="queryCondition" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *           &lt;element name="sign" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;/sequence>
 *       &lt;/restriction>
 *     &lt;/complexContent>
 *   &lt;/complexType>
 * &lt;/element>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "jrdId",
    "userName",
    "randVal",
    "time",
    "hashAlgorithm",
    "queryConditionType",
    "queryCondition",
    "sign"
})
@XmlRootElement(name = "jrd_querybeianstatus")
public class JrdQuerybeianstatus {

    @XmlElement(namespace = "http://beian.ct10000.com/queryBeianStatus", required = true, type = Long.class, nillable = true)
    protected Long jrdId;
    @XmlElement(namespace = "http://beian.ct10000.com/queryBeianStatus", required = true, nillable = true)
    protected String userName;
    @XmlElement(namespace = "http://beian.ct10000.com/queryBeianStatus", required = true, nillable = true)
    protected String randVal;
    @XmlElement(namespace = "http://beian.ct10000.com/queryBeianStatus")
    protected long time;
    @XmlElement(namespace = "http://beian.ct10000.com/queryBeianStatus")
    protected int hashAlgorithm;
    @XmlElement(namespace = "http://beian.ct10000.com/queryBeianStatus")
    protected int queryConditionType;
    @XmlElement(namespace = "http://beian.ct10000.com/queryBeianStatus", required = true, nillable = true)
    protected String queryCondition;
    @XmlElement(namespace = "http://beian.ct10000.com/queryBeianStatus", required = true, nillable = true)
    protected String sign;

    /**
     * Gets the value of the jrdId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getJrdId() {
        return jrdId;
    }

    /**
     * Sets the value of the jrdId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setJrdId(Long value) {
        this.jrdId = value;
    }

    /**
     * Gets the value of the userName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the value of the userName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserName(String value) {
        this.userName = value;
    }

    /**
     * Gets the value of the randVal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRandVal() {
        return randVal;
    }

    /**
     * Sets the value of the randVal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRandVal(String value) {
        this.randVal = value;
    }

    /**
     * Gets the value of the time property.
     * 
     */
    public long getTime() {
        return time;
    }

    /**
     * Sets the value of the time property.
     * 
     */
    public void setTime(long value) {
        this.time = value;
    }

    /**
     * Gets the value of the hashAlgorithm property.
     * 
     */
    public int getHashAlgorithm() {
        return hashAlgorithm;
    }

    /**
     * Sets the value of the hashAlgorithm property.
     * 
     */
    public void setHashAlgorithm(int value) {
        this.hashAlgorithm = value;
    }

    /**
     * Gets the value of the queryConditionType property.
     * 
     */
    public int getQueryConditionType() {
        return queryConditionType;
    }

    /**
     * Sets the value of the queryConditionType property.
     * 
     */
    public void setQueryConditionType(int value) {
        this.queryConditionType = value;
    }

    /**
     * Gets the value of the queryCondition property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQueryCondition() {
        return queryCondition;
    }

    /**
     * Sets the value of the queryCondition property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQueryCondition(String value) {
        this.queryCondition = value;
    }

    /**
     * Gets the value of the sign property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSign() {
        return sign;
    }

    /**
     * Sets the value of the sign property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSign(String value) {
        this.sign = value;
    }

}
