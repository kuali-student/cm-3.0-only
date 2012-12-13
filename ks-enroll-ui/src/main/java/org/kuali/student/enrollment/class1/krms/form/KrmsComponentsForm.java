/**
 * Copyright 2012 The Kuali Foundation Licensed under the
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
 *
 * Created by Paul on 2012/11/22
 */
package org.kuali.student.enrollment.class1.krms.form;

import org.kuali.rice.krad.web.form.UifFormBase;
import org.kuali.student.enrollment.class1.krms.dto.PropositionEditor;

/**
 * This class //TODO ...
 *
 * @author Kuali Student Team
 */
public class KrmsComponentsForm extends UifFormBase {

    private PropositionEditor proposition;

    private String courseSuggest;

    private String multipleCourseType;

    public PropositionEditor getProposition() {
        return proposition;
    }

    public void setProposition(PropositionEditor proposition) {
        this.proposition = proposition;
    }

    public String getCourseSuggest() {
        return courseSuggest;
    }

    public void setCourseSuggest(String courseSuggest) {
        this.courseSuggest = courseSuggest;
    }

    public String getMultipleCourseType() {
        return multipleCourseType;
    }

    public void setMultipleCourseType(String multipleCourseType) {
        this.multipleCourseType = multipleCourseType;
    }



}
