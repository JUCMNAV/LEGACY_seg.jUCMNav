package seg.jUCMNav.editparts.treeEditparts;

import grl.Contribution;
import grl.Decomposition;
import grl.Dependency;
import grl.ElementLink;

import org.eclipse.gef.EditPolicy;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.TreeItem;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.editpolicies.element.ElementLinkComponentEditPolicy;
import seg.jUCMNav.figures.ColorManager;

/**
 * TreeEditPart for the Element Link. 
 * 
 * @author Jean-François Roy
 *
 */
public class ElementLinkTreeEditPart extends UrnModelElementTreeEditPart {

    /**
     * @param model
     *          the ElementLink 
     */
    public ElementLinkTreeEditPart(Object model) {
        super(model);
    }

    /**
     * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
     */
    protected void createEditPolicies() {
        installEditPolicy(EditPolicy.COMPONENT_ROLE, new ElementLinkComponentEditPolicy());
    }
    
    /**
     * @return the ElementLink definition
     */
    protected ElementLink getElementLink() {
        return (ElementLink) getModel();
    }
    
    /**
     * Returns the icon appropriate for this element type
     */
    protected Image getImage() {
        if (super.getImage() == null) {       
            if (getElementLink() instanceof Contribution){
                Contribution contrib = (Contribution)getElementLink();
                if (getParent().getModel() == contrib.getSrc()){
                    setImage((JUCMNavPlugin.getImage( "icons/ContributionSrc16.gif"))); //$NON-NLS-1$
                } else if (getParent().getModel() == contrib.getDest()){
                    setImage((JUCMNavPlugin.getImage( "icons/ContributionDest16.gif"))); //$NON-NLS-1$
                } else {
                    setImage((JUCMNavPlugin.getImage( "icons/Contribution16.gif"))); //$NON-NLS-1$
                }
            } else if (getElementLink() instanceof Decomposition){
                Decomposition decomp = (Decomposition)getElementLink();
                if (getParent().getModel() == decomp.getSrc()){
                    setImage((JUCMNavPlugin.getImage( "icons/DecompositionSrc16.gif"))); //$NON-NLS-1$
                } else if (getParent().getModel() == decomp.getDest()){
                    setImage((JUCMNavPlugin.getImage( "icons/DecompositionDest16.gif"))); //$NON-NLS-1$
                } else{
                    setImage((JUCMNavPlugin.getImage( "icons/Decomposition16.gif"))); //$NON-NLS-1$
                }
            } else if (getElementLink() instanceof Dependency){
                Dependency depend = (Dependency)getElementLink();
                if (getParent().getModel() == depend.getSrc()){
                    setImage((JUCMNavPlugin.getImage( "icons/DependencySrc16.gif"))); //$NON-NLS-1$
                } else if (getParent().getModel() == depend.getDest()){
                    setImage((JUCMNavPlugin.getImage( "icons/DependencyDest16.gif"))); //$NON-NLS-1$
                } else{
                    setImage((JUCMNavPlugin.getImage( "icons/Dependency16.gif"))); //$NON-NLS-1$
                }
            }
        }

        return super.getImage();
    }
    
    /**
     * Sets unused ElementLink to a lighter color.
     * 
     * @see org.eclipse.gef.editparts.AbstractTreeEditPart#refreshVisuals()
     */
    protected void refreshVisuals() {
        if (getElementLink().getRefs().size() == 0)
            ((TreeItem) widget).setForeground(ColorManager.DARKGRAY);
        else
            ((TreeItem) widget).setForeground(ColorManager.BLACK);
        getImage();
        super.refreshVisuals();
    }
}
