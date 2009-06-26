//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.0 in JDK 1.6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2008.10.21 at 02:14:18 PM PDT 
//


package org.kuali.student.core.dictionary.newmodel.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.dto.dictionary.student.kuali.org}fieldDescriptor"/>
 *         &lt;element name="readOnly" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *       &lt;attribute name="key" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "fieldDescriptor",
    "constraintDescriptor",
    "selector",
    "dynamic",
    "maxOccurs",
    "minOccurs"
})
@XmlRootElement(name = "field")
public class Field implements Serializable{

    private static final long serialVersionUID = 1L;

    @XmlAttribute(required = true)
    protected String key;

    @XmlAttribute 
    protected String id;
    
    @XmlElement //TODO there is no required here but there should be
    protected FieldDescriptor fieldDescriptor;
    
    @XmlElement 
    protected ObjectStructure objectStructure;
    
    @XmlElement
    protected ConstraintDescriptor constraintDescriptor;
    
    @XmlElement
    protected boolean selector;
    
    @XmlElement
    protected boolean dyncamic;
    
    
    /**
     * Gets the value of the fieldDescriptor property.
     * 
     * @return
     *     possible object is
     *     {@link FieldDescriptor }
     *     
     */
    public FieldDescriptor getFieldDescriptor() {
        return fieldDescriptor;
    }

    /**
     * Sets the value of the fieldDescriptor property.
     * 
     * @param value
     *     allowed object is
     *     {@link FieldDescriptor }
     *     
     */
    public void setFieldDescriptor(FieldDescriptor value) {
        this.fieldDescriptor = value;
    }

	public boolean isSelector() {
		return selector;
	}

	public void setSelector(boolean isSelector) {
		this.selector = isSelector;
	}

	/**
     * Gets the value of the key property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKey() {
        return key;
    }

    /**
     * Sets the value of the key property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKey(String value) {
        this.key = value;
    }

	/**
	 * @return the constraintDescriptor
	 */
	public ConstraintDescriptor getConstraintDescriptor() {
		return constraintDescriptor;
	}

	/**
	 * @param constraintDescriptor the constraintDescriptor to set
	 */
	public void setConstraintDescriptor(ConstraintDescriptor constraintDescriptor) {
		this.constraintDescriptor = constraintDescriptor;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the objectStructure
	 */
	public ObjectStructure getObjectStructure() {
		return objectStructure;
	}

	/**
	 * @param objectStructure the objectStructure to set
	 */
	public void setObjectStructure(ObjectStructure objectStructure) {
		this.objectStructure = objectStructure;
	}

	/**
	 * @return the dyncamic
	 */
	public boolean isDyncamic() {
		return dyncamic;
	}

	/**
	 * @param dyncamic the dyncamic to set
	 */
	public void setDyncamic(boolean dyncamic) {
		this.dyncamic = dyncamic;
	}

}
