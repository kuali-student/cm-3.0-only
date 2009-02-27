package org.kuali.student.lum.lu.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.kuali.student.core.entity.Type;

@Entity
@Table(name="KS_REQ_COMP_TYPE_T")
public class ReqComponentType extends Type<ReqComponentTypeAttribute> {
    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "OWNER")
    private List<ReqComponentTypeAttribute> attributes;
    
    @ManyToMany
    @JoinTable(name = "KS_REQCOMP_TYPE_REQCOMPFLD_TYPE_T", joinColumns = @JoinColumn(name = "REQ_COMP_FIELD_TYPE_ID"), inverseJoinColumns = @JoinColumn(name = "REQ_COMP_TYPE_ID"))
    private List<ReqComponentFieldType> reqCompFieldTypes;

    public List<ReqComponentTypeAttribute> getAttributes() {
        if(null == attributes) {
            attributes = new ArrayList<ReqComponentTypeAttribute>();
        }
        return attributes;
    }

    public void setAttributes(List<ReqComponentTypeAttribute> attributes) {
        this.attributes = attributes;
    }

    public List<ReqComponentFieldType> getReqCompFieldTypes() {
        return reqCompFieldTypes;
    }

    public void setReqCompFieldTypes(List<ReqComponentFieldType> reqCompFieldTypes) {
        this.reqCompFieldTypes = reqCompFieldTypes;
    }	
}
