package seg.jUCMNav.editparts.treeEditparts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.swt.graphics.Image;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.Messages;
import seg.jUCMNav.model.util.DelegatingElementComparator;
import seg.jUCMNav.views.preferences.DisplayPreferences;
import urn.URNspec;
import urncore.URNmodelElement;

/**
 * The TreeEditPart associated with URNspec.
 * 
 * @author Etienne Tremblay, jkealey, gunterm
 */
public class URNspecTreeEditPart extends UrnModelElementTreeEditPart {

    protected boolean onlyDefinitions;

    /**
     * @param model
     *            the URNspec being edited.
     * @param onlyDefinitions
     *            do we show only UCM/GRL definitions
     */
    public URNspecTreeEditPart(URNspec model, boolean onlyDefinitions) {
        super(model);
        this.onlyDefinitions = onlyDefinitions;
    }

    /**
     * Listens to both the URNspec and the UCMspec
     * 
     * @see org.eclipse.gef.EditPart#activate()
     */
    public void activate() {
        if (!isActive()) {
            getURNspec().getUcmspec().eAdapters().add(this);
            getURNspec().getGrlspec().eAdapters().add(this);
            getURNspec().getUrndef().eAdapters().add(this);
        }
        super.activate();
    }

    /**
     * 
     * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
     */
    protected void createEditPolicies() {
        // installEditPolicy(EditPolicy.COMPONENT_ROLE, new ResponsibilityComponentEditPolicy());
    }

    /**
     * Stops listening to both the URNspec and the UCMspec.
     * 
     * @see org.eclipse.gef.EditPart#deactivate()
     */
    public void deactivate() {
        if (isActive()) {
            getURNspec().getUcmspec().eAdapters().remove(this);
            getURNspec().getGrlspec().eAdapters().remove(this);
            getURNspec().getUrndef().eAdapters().remove(this);
        }
        super.deactivate();
    }

    /**
     * @return the icon associated with URNspec
     */
    protected Image getImage() {
        if (super.getImage() == null)
            setImage((JUCMNavPlugin.getImage("icons/icon16.gif"))); //$NON-NLS-1$
        return super.getImage();
    }

    /**
     * @return the sorted list of maps and the component and responsibility definition labels
     */
    protected List getModelChildren() {
        boolean globalFilter = DisplayPreferences.getInstance().isGlobalFilterEnabled();
        ArrayList list = new ArrayList();
        if (!onlyDefinitions) {
            for (Iterator iterator = getURNspec().getUrndef().getSpecDiagrams().iterator(); iterator.hasNext();) {
                URNmodelElement object = (URNmodelElement) iterator.next();
                if (!DisplayPreferences.getInstance().isElementFiltered(object))
                    list.add(object);
            }
        }
        // We want to keep the spec diagram in the order of the tabs
        Collections.sort(list, new DelegatingElementComparator());

        // Instead of having all type of definition in the main category, we divided defs in grl and ucm

        if (!globalFilter || DisplayPreferences.getInstance().getShowUCMS())
            list.add(Messages.getString("URNspecTreeEditPart.ucmDefs")); //$NON-NLS-1$
        if (!globalFilter || DisplayPreferences.getInstance().getShowGRLS())
            list.add(Messages.getString("URNspecTreeEditPart.grlDefs")); //$NON-NLS-1$
        if ((!globalFilter || (!onlyDefinitions && DisplayPreferences.getInstance().getShowConcerns())) && (DisplayPreferences.getInstance().isAdvancedControlEnabled() && DisplayPreferences.getInstance().isShowAspect()))
            list.add(Messages.getString("URNspecTreeEditPart.concerns")); //$NON-NLS-1$

        return list;

    }

    /**
     * @return the URNspec name.
     */
    protected String getText() {
        return getURNspec().getName();
    }

    /**
     * @return the URNspec being edited.
     */
    private URNspec getURNspec() {
        return (URNspec) getModel();
    }

}