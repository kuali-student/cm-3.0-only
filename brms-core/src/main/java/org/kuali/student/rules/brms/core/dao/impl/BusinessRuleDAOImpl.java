/*
 * Copyright 2007 The Kuali Foundation Licensed under the Educational Community License, Version 1.0 (the "License"); you may
 * not use this file except in compliance with the License. You may obtain a copy of the License at
 * http://www.opensource.org/licenses/ecl1.php Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing permissions and limitations under the License.
 */
package org.kuali.student.rules.brms.core.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.kuali.student.rules.brms.core.dao.BusinessRuleDAO;
import org.kuali.student.rules.internal.common.entity.BusinessRule;
import org.springframework.stereotype.Repository;

/**
 * Implements DAO interface for operations on Functional Business Rule entity using Spring JPA
 * 
 * @author Kuali Student Team (zdenek.kuali@gmail.com)
 */
@Repository
public class BusinessRuleDAOImpl implements BusinessRuleDAO {

    private EntityManager entityManager;

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    /**
     * Persists BusinessRule in database.
     * 
     * @see org.kuali.student.rules.brms.core.dao.BusinessRuleDAO#createBusinessRule(BusinessRule rule)
     */
    public BusinessRule createBusinessRule(BusinessRule rule) {
        entityManager.persist(rule);
        return rule;
    }

    /**
     * Updates BusinessRule in database.
     * 
     * @see org.kuali.student.rules.brms.core.dao.BusinessRuleDAO#updateBusinessRule(BusinessRule rule)
     */
    public BusinessRule updateBusinessRule(BusinessRule rule) {
        entityManager.merge(rule);
        return rule;
    }

    /**
     * Deletes BusinessRule from database.
     * 
     * @see org.kuali.student.rules.brms.core.dao.BusinessRuleDAO#deleteBusinessRule(BusinessRule rule)
     */
    public boolean deleteBusinessRule(BusinessRule ruleSet) {
        entityManager.remove(ruleSet);
        return true; // until I know better what needs to happen
    }

    /**
     * Deletes BusinessRule from database.
     * 
     * @see org.kuali.student.rules.brms.core.dao.BusinessRuleDAO#deleteBusinessRule(BusinessRule rule)
     */
    public boolean deleteBusinessRule(String id) {
        entityManager.remove(lookupBusinessRuleUsingId(id));
        return true;
    }

    /**
     * Finds BusinessRule in database.
     * 
     * @see org.kuali.student.rules.brms.core.dao.BusinessRuleDAO#lookupBusinessRuleUsingId(String id)
     */
    public BusinessRule lookupBusinessRuleUsingId(String id) {
        return entityManager.find(BusinessRule.class, id);
    }

    /**
     * Finds BusinessRule in database.
     * 
     * @see org.kuali.student.rules.brms.core.dao.BusinessRuleDAO#lookupBusinessRuleUsingId(String ruleIdentifier)
     */
    @SuppressWarnings(value = {"unchecked"})
    public BusinessRule lookupBusinessRuleUsingIdentifier(String ruleIdentifier) {

        Query query = entityManager.createNamedQuery("BusinessRule.findByIdentifier");
        query.setParameter("ruleIdentifier", ruleIdentifier);
        BusinessRule businessRule = (BusinessRule) query.getSingleResult();
        return businessRule;
    }

    /**
     * Finds one or more BusinessRule in database based on given parameters
     * 
     * @see org.kuali.student.rules.brms.core.dao.BusinessRuleDAO#lookupCompiledRuleIDs(String ruleIdentifier)
     */
    @SuppressWarnings(value = {"unchecked"})
    public List<BusinessRule> lookupCompiledIDs(String businessRuleType, String anchor) {
        Query query = entityManager.createNamedQuery("BusinessRule.findByAgendaType");
        query.setParameter("businessRuleType", businessRuleType);
        query.setParameter("anchor", anchor);
        List<BusinessRule> businessRule = query.getResultList();
        return businessRule;
    }
}
