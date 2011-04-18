/*
 * Copyright 2010 The Kuali Foundation Licensed under the
 * Educational Community License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may
 * obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package org.kuali.student.r2.common.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.kuali.student.common.infc.ModelBuilder;
import org.kuali.student.common.infc.DateRange;
import org.w3c.dom.Element;

@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DateRangeInfo", propOrder = {"start", "end", "_futureElements"})
public class DateRangeInfo implements DateRange, Serializable {
	
	@XmlElement
	private final Date start;
	
	@XmlElement
        private final Date end;
	
    @XmlAnyElement
    private final List<Element> _futureElements;    
	
	private DateRangeInfo() {
            start = null;
            end = null;
		_futureElements = null;
	}
		
	public DateRangeInfo(DateRange dateRange) {
                this.start = dateRange.getStart();
                this.end = dateRange.getEnd();
    	this._futureElements = null;
	}

    /**
     * @return the start date
     */
    @Override
    public Date getStart() {
        return start;
    }

    /**
     * @return the end date
     */
    @Override
    public Date getEnd() {
        return end;
    }
    
    public static class Builder implements ModelBuilder<DateRangeInfo>, DateRange {
		private Date start;
		private Date end;

		public Builder() {}
    	
    	public Builder(DateRange dateRange) {
                this.start = dateRange.getStart();
                this.end = dateRange.getEnd();
    	}

        public DateRangeInfo build() {
            return new DateRangeInfo(this);
        }

        @Override
        public Date getStart() {
            return start;
        }

        public void setStart(Date start) {
            this.start = start;
        }

        @Override
        public Date getEnd() {
            return end;
        }

        public void setEnd(Date end) {
            this.end = end;
        }
    }
}
