
package org.kuali.student.core.enumerable.service.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.kuali.student.core.dictionary.dto.EnumeratedValue;

/**
 * This class was generated by Apache CXF 2.1.4
 * Fri Feb 13 09:40:30 EST 2009
 * Generated source version: 2.1.4
 */

@XmlRootElement(name = "getEnumerationResponse", namespace = "http://org.kuali.student/core/organization")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getEnumerationResponse", namespace = "http://org.kuali.student/core/organization")

public class GetEnumerationResponse {

    @XmlElement(name = "return")
    private java.util.List<EnumeratedValue> _return;

    public java.util.List<EnumeratedValue> getReturn() {
        return this._return;
    }

    public void setReturn(java.util.List<EnumeratedValue> new_return)  {
        this._return = new_return;
    }

}

