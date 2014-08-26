package org.kuali.student.enrollment.class1.hold.rule;

import edu.emory.mathcs.backport.java.util.Collections;
import org.apache.commons.lang.StringUtils;
import org.kuali.rice.core.api.util.RiceKeyConstants;
import org.kuali.rice.krad.maintenance.MaintenanceDocument;
import org.kuali.rice.krad.util.GlobalVariables;
import org.kuali.rice.krad.util.KRADConstants;
import org.kuali.student.common.uif.rule.KsMaintenanceDocumentRuleBase;
import org.kuali.student.common.util.security.ContextUtils;
import org.kuali.student.enrollment.class1.hold.dto.HoldIssueMaintenanceWrapper;
import org.kuali.student.enrollment.class1.hold.util.HoldResourceLoader;
import org.kuali.student.r2.common.datadictionary.DataDictionaryValidator;
import org.kuali.student.r2.common.dto.ContextInfo;
import org.kuali.student.r2.common.dto.RichTextInfo;
import org.kuali.student.r2.common.dto.ValidationResultInfo;
import org.kuali.student.r2.core.constants.HoldServiceConstants;
import org.kuali.student.r2.core.hold.dto.HoldIssueInfo;

import java.util.List;

public class HoldIssueRule extends KsMaintenanceDocumentRuleBase {

    @Override
    protected boolean isDocumentValidForSave(MaintenanceDocument maintenanceDocument) {
        if ( ! super.isDocumentValidForSave(maintenanceDocument)) {
            return false;
        }

        boolean isValid = true;
        HoldIssueMaintenanceWrapper holdWrapper = (HoldIssueMaintenanceWrapper)maintenanceDocument.getNewMaintainableObject().getDataObject();
        if (StringUtils.isBlank(holdWrapper.getHoldIssue().getStateKey())) {
           holdWrapper.getHoldIssue().setStateKey(HoldServiceConstants.ISSUE_ACTIVE_STATE_KEY);
        }

        isValid &= validateHold(holdWrapper);

        return isValid;
    }

    /**
     * Performs a service layer validation of the HoldIssue. This validation is repeated in the call to
     *
     * @param holdIssueWrapper
     * @return  True if the validation succeeds. Otherwise, false.
     */
    private boolean validateHold(HoldIssueMaintenanceWrapper holdIssueWrapper) {

        List<ValidationResultInfo> errors = transformValidationErrors(getValidationErrors(holdIssueWrapper));

        //  If any errors were found, put them in the message map.
        if (!errors.isEmpty()) {
            for (ValidationResultInfo error : errors) {
                GlobalVariables.getMessageMap().putError(error.getElement(), RiceKeyConstants.ERROR_CUSTOM, error.getMessage());
            }
            return false;
        }

        return true;
    }

    private List<ValidationResultInfo> getValidationErrors(HoldIssueMaintenanceWrapper holdIssueWrapper) {

        List<ValidationResultInfo> errors = Collections.emptyList();
        HoldIssueInfo holdIssueInfo = holdIssueWrapper.getHoldIssue();
        try {
            errors = HoldResourceLoader.getHoldService().validateHoldIssue(DataDictionaryValidator.ValidationType.FULL_VALIDATION.toString(), holdIssueInfo, createContextInfo());
        } catch (Exception e) {
            //  Capture the error if the service call fails.
            GlobalVariables.getMessageMap().putError(KRADConstants.GLOBAL_ERRORS, RiceKeyConstants.ERROR_CUSTOM, e.getMessage());
        }

        return errors;
    }

    private List<ValidationResultInfo> transformValidationErrors( List<ValidationResultInfo> validationErrors ) {

        for( ValidationResultInfo error : validationErrors ) {
            String elementPath = error.getElement();

            if( StringUtils.equals(elementPath, "holdIssue.name") ) {
                elementPath = "document.newMaintainableObject.dataObject.holdIssue.name";
            }

            if( StringUtils.equals(elementPath, "holdIssue.typeKey") ) {
                elementPath = "document.newMaintainableObject.dataObject.holdIssue.typeKey";
            }

            if( StringUtils.equals(elementPath, "holdIssue.organizationId") ) {
                elementPath = "document.newMaintainableObject.dataObject.holdIssue.organizationId";
            }
            error.setElement( elementPath );
        }

        return validationErrors;
    }

    private ContextInfo createContextInfo() {
        return ContextUtils.createDefaultContextInfo();
    }
}
