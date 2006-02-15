/**
 * 
 */
package seg.jUCMNav.editparts;

import java.util.Iterator;

import org.eclipse.draw2d.LayeredPane;
import org.eclipse.gef.editparts.ScalableFreeformRootEditPart;

import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.model.util.EvaluationScenarioManager;

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
    
    protected boolean scenarioView;
    
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
        if (EvaluationScenarioManager.getInstance().getEvaluationScenario() != null){
            scenarioView = true;
        } else{
            scenarioView = false;
        }
    }

    /**
     * @return number of current mode
     */
    public int getMode() {
        return this.mode;
    }

    /**
     * Overridden to allow access from export image wizard. 
     */
    public LayeredPane getScaledLayers() {
        return super.getScaledLayers();
    }
    
    /**
     * @return Returns the MultiPageEditor. This is bad design but used to simplify some stub binding code. 
     */
    public UCMNavMultiPageEditor getMultiPageEditor() {
        return multiPageEditor;
    }
    
    public abstract void setMode(int mode);


    public boolean isScenarioView() {
        return scenarioView;
    }

    public void setScenarioView(boolean view) {
        scenarioView = view;
        for (Iterator iter = getChildren().iterator(); iter.hasNext();) {
            URNDiagramEditPart element = (URNDiagramEditPart) iter.next();
            element.refreshVisuals();
        }
    }
    
    public void refreshChildren() {
        for (Iterator iter = getChildren().iterator(); iter.hasNext();) {
            URNDiagramEditPart element = (URNDiagramEditPart) iter.next();
            element.refreshVisuals();
        }
    }
}
