package org.kuali.rice.krms.tree.node;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: SW
 * Date: 2013/02/20
 * Time: 10:10 AM
 * To change this template use File | Settings | File Templates.
 */
public class CompareTreeNode implements Serializable {

    private String original;
    private List<String> originalItems;
    private String compared;
    private List<String> comparedItems;

    public CompareTreeNode(){
    }

    public CompareTreeNode(String original, String compared){
        this.original = original;
        this.compared = compared;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public List<String> getOriginalItems() {
        return originalItems;
    }

    public void setOriginalItems(List<String> originalItems) {
        this.originalItems = originalItems;
    }

    public String getCompared() {
        return compared;
    }

    public void setCompared(String compared) {
        this.compared = compared;
    }

    public List<String> getComparedItems() {
        return comparedItems;
    }

    public void setComparedItems(List<String> comparedItems) {
        this.comparedItems = comparedItems;
    }
}
