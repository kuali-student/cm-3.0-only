package org.kuali.student.common.ui.client.widgets;


import org.kuali.student.common.ui.client.widgets.impl.KSRichTextToolbarImpl;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RichTextArea;

/**
 * KSRichTextToolbar provides an interface for adding different text formatting styles to text in a
 * a RichTextArea.
 * 
 * The class implementation is a modified version of the one found in the GWT Samples.
 * 
 * @author Kuali Student Team
 *
 */
public class KSRichTextToolbar extends KSRichTextToolbarAbstract{ 

    private KSRichTextToolbarAbstract richTextToolbar = GWT.create(KSRichTextToolbarImpl.class);

    /**
     * Creates a new toolbar that drives the given rich text area.
     * 
     * @param richText the rich text area to be controlled
     */
    public KSRichTextToolbar(RichTextArea richText) {
        init(richText);
        initWidget(richTextToolbar);
    }



    /**
     * Returns true if the toolbar is being interacted with, false otherwise.
     * 
     * @return true if the toolbar is being interacted with, false otherwise.
     */
    public boolean inUse(){
        return richTextToolbar.inUse();
    }



    /**
     * Initializes this toolbar with the specified options.
     * 
     * @see org.kuali.student.common.ui.client.widgets.KSRichTextToolbarAbstract#init(com.google.gwt.user.client.ui.RichTextArea
     *      
     */
    @Override
    protected void init(RichTextArea richText) {
        richTextToolbar.init(richText);
        
    }

}
