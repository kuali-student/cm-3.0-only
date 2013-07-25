/**
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

package org.kuali.student.common.messagebuilder.booleanmessage.ast.exceptions;

public class BooleanFunctionException extends RuntimeException {
    
    /** Class serial version uid */
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new boolean function exception.
     * 
     * @param cause Cause of this exception
     */
    public BooleanFunctionException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a new boolean function exception.
     * 
     * @param msg Error message description
     * @param cause Cause of this exception
     */
    public BooleanFunctionException(String msg, Throwable cause) {
        super(msg, cause);
    }

    /**
     * Constructs a new boolean function exception.
     * 
     * @param msg Error message description
     */
    public BooleanFunctionException(String msg) {
        super(msg);
    }
}
