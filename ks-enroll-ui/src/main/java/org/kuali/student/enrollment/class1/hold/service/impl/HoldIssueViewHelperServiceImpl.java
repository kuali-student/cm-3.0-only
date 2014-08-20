package org.kuali.student.enrollment.class1.hold.service.impl;

import org.apache.commons.lang.StringUtils;
import org.kuali.rice.core.api.criteria.Predicate;
import org.kuali.rice.core.api.criteria.QueryByCriteria;
import org.kuali.student.common.uif.service.impl.KSViewHelperServiceImpl;
import org.kuali.student.enrollment.class1.hold.dto.HoldIssueInfoWrapper;
import org.kuali.student.enrollment.class1.hold.form.HoldIssueManagementForm;
import org.kuali.student.enrollment.class1.hold.service.HoldIssueViewHelperService;
import org.kuali.student.enrollment.class1.hold.util.HoldIssueConstants;
import org.kuali.student.enrollment.class1.hold.util.HoldIssueResourceLoader;
import org.kuali.student.r2.core.hold.dto.HoldIssueInfo;

import java.util.ArrayList;
import java.util.List;

import static org.kuali.rice.core.api.criteria.PredicateFactory.and;
import static org.kuali.rice.core.api.criteria.PredicateFactory.equal;
import static org.kuali.rice.core.api.criteria.PredicateFactory.like;

/**
 * Created with IntelliJ IDEA.
 * User: Blue Team (SA)
 * Date: 17 July 2014
 * <p/>
 * Implementation of the HoldIssueViewHelperService that contains helper methods that support the Hold Issue Maintenance Controller.
 */
public class HoldIssueViewHelperServiceImpl extends KSViewHelperServiceImpl implements HoldIssueViewHelperService {



    @Override
    public List<HoldIssueInfoWrapper> searchHolds(HoldIssueManagementForm holdIssueFrom) {

        List<HoldIssueInfoWrapper> holdIssueInfoWrappers = new ArrayList<HoldIssueInfoWrapper>();
        List<HoldIssueInfo> holdIssueInfos = new ArrayList<HoldIssueInfo>();
        try {
            QueryByCriteria.Builder query = buildQueryByCriteria(holdIssueFrom.getName(),holdIssueFrom.getTypeKey(),holdIssueFrom.getState(),holdIssueFrom.getOrganizationId(),holdIssueFrom.getDescr());
            holdIssueInfos = HoldIssueResourceLoader.getHoldService().searchForHoldIssues(query.build(), createContextInfo());

            for (HoldIssueInfo holdIssueInfo : holdIssueInfos) {
                HoldIssueInfoWrapper holdIssueInfoWrapper = new HoldIssueInfoWrapper();
                holdIssueInfoWrapper.setHoldIssueInfo(holdIssueInfo);
                //Test
                holdIssueInfoWrapper.setCode("Test");
                holdIssueInfoWrapper.setAuthorization("Authorization");
                holdIssueInfoWrapper.setFirstDate("08/19/2014");
                holdIssueInfoWrapper.setLastDate("12/31/2014");
                holdIssueInfoWrappers.add(holdIssueInfoWrapper);
            }
        } catch (Exception e) {

            convertServiceExceptionsToUI(e);
        }
        return holdIssueInfoWrappers;
    }


    private static QueryByCriteria.Builder buildQueryByCriteria(String name, String type,String state, String orgId, String descr){

        QueryByCriteria.Builder qBuilder = QueryByCriteria.Builder.create();
        List<Predicate> pList = new ArrayList<Predicate>();
        Predicate p;

        qBuilder.setPredicates();
        if (StringUtils.isNotBlank(name)){
            p = like(HoldIssueConstants.HOLD_ISSUE_NAME, "%" + name + "%");
            pList.add(p);
        }

        if (StringUtils.isNotBlank(type)){
            p = like(HoldIssueConstants.HOLD_ISSUE_TYPE, "%" + type + "%");
            pList.add(p);
        }

        if (StringUtils.isNotBlank(state)){
            p = equal(HoldIssueConstants.HOLD_ISSUE_STATE, state);
            pList.add(p);
        }

        if (StringUtils.isNotBlank(orgId)){
            p = equal(HoldIssueConstants.HOLD_ISSUE_ORG_ID, orgId);
            pList.add(p);
        }

        if (StringUtils.isNotBlank(descr)){
            p = like(HoldIssueConstants.HOLD_ISSUE_DESCR_PLAIN, "%" + descr + "%");
            pList.add(p);
        }

        if (!pList.isEmpty()){
            Predicate[] preds = new Predicate[pList.size()];
            pList.toArray(preds);
            qBuilder.setPredicates(and(preds));
        }
        return qBuilder;
    }

}
