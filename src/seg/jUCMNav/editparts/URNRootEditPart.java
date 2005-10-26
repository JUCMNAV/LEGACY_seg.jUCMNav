/**
 * 
 */
package seg.jUCMNav.editparts;

import org.eclipse.gef.editparts.ScalableFreeformRootEditPart;

import seg.jUCMNav.editors.UCMNavMultiPageEditor;

/**
 * Root edit part of any jUCMNav editor.
 * This class is used to manage all editors using the same EditPart
 * 
 * @author Jean-François Roy
 *
 */
public abstract class URNRootEditPart extends ScalableFreeformRootEditPart {

    // Used to simplify some stub binding code. 
    private UCMNavMultiPageEditor multiPageEditor;
    

    public static final String COMPONENT_LAYER = "COMPONENT"; //$NON-NLS-1$

    // what is the current view mode for this editor. 
    protected int mode = 0;

    /**
     * 
     * @param editor the multi page editor
     */
    public URNRootEditPart(UCMNavMultiPageEditor editor) {
        super();
        multiPageEditor = editor;
    }

    /**
     * @return number of current mode
     */
    public int getMode() {
        return this.mode;
    }

    /**
     * @return Returns the MultiPageEditor. This is bad design but used to simplify some stub binding code. 
     */
    public UCMNavMultiPageEditor getMultiPageEditor() {
        return multiPageEditor;
    }
    
    public abstract void setMode(int mode);
}
