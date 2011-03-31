/*
 * Copyright 2009 The Kuali Foundation
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
package org.kuali.student.common.infc;

import java.util.List;

/**
 * Criteria for a generic query
 */
public interface ComparisonInfc  {

  
    /**
     * Name: Field Key
     * <p/>
     * Dot path notation to identity the name of field to be compared
     */
    public String getFieldKey();

    /**
     * Name: Operator
     * <p/>
     * Operator to use to compare the field to the value, =, in, >, <, !=, >=, <=, like, between
     *
     * Complex objects can only be checked to see if they are null or not null.
     * Only in operators can take a list of values all others must take a single value
     *
     * TODO: Decide for complex Lists can they be checked to see how many occurences they have?
     * TODO: Decide on other operators such as "like" or in range xxx-xxxx
     * TODO: Deicde on operators to search collections inside such as any
     * TODO: Decide how to search on dynamic attributes
     *
     */
    public String getOperator();

    /**
     * Name: Criteria Value(s)
     * <p/>
     * Value to be compared
     */
    public List<String> getValues();

    /**
     * Check if should ignore case when doing the comparison
     *
     * Default is false
     *
     * @return true if should ignore case of both the specified values and the value in the field
     */
    public boolean isIgnoreCase ();
}