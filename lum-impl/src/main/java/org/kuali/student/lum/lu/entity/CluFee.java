/*
 * Copyright 2008 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl1.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.student.lum.lu.entity;
 
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.kuali.student.common.util.UUIDHelper;
import org.kuali.student.core.entity.AttributeOwner;

@Entity
@Table(name = "KSLU_CLU_FEE")
public class CluFee implements AttributeOwner<CluFeeAttribute>{

    @Id
    @Column(name = "ID")
    private String id;
    
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "OWNER")
    private List<CluFeeAttribute> attributes;
	
	@PrePersist
	public final void prePersist() {
		this.id = UUIDHelper.genStringUUID(this.id);
	}
	
    public List<CluFeeAttribute> getAttributes() {
        if (attributes == null) {
            attributes = new ArrayList<CluFeeAttribute>();
        }
        return attributes;
    }

    public void setAttributes(List<CluFeeAttribute> attributes) {
        this.attributes = attributes;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}