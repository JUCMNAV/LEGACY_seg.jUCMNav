package seg.jUCMNav.editparts.concernsTreeEditparts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.impl.EReferenceImpl;

import seg.jUCMNav.Messages;
import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.editparts.treeEditparts.OutlineRootEditPart;
import seg.jUCMNav.model.util.DelegatingElementComparator;
import urncore.Concern;

/**
 * RootTreeEditPart for the root in the Concern Outline
 * 
 * @author gunterm
 */
public class ConcernsRootTreeEditPart extends OutlineRootEditPart implements Adapter {

    // required for Adapter
    private Notifier target;

    /**
     * @param editor
     *            represents the editor
     */
    public ConcernsRootTreeEditPart(UCMNavMultiPageEditor editor) {
        super(editor);
    }

    /**
     * @return the sorted list of concerns plus a catch-all group for diagrams without concerns
     * @see seg.jUCMNav.editparts.treeEditparts.OutlineRootEditPart#getModelChildren()
     */
    protected List getModelChildren() {
        ArrayList list = new ArrayList();
        for (Iterator iter = ((UCMNavMultiPageEditor) getModel()).getModel().getUrndef().getConcerns().iterator(); iter.hasNext();) {
            Concern element = (Concern) iter.next();
            list.add(element);
        }
        Collections.sort(list, new DelegatingElementComparator());
        list.add(Messages.getString("ConcernsLabelTreeEditPart.DiagramsWithoutConcerns")); //$NON-NLS-1$
        return list;
    }

    /**
     * Listens to the URNdefinition
     * 
     * @see org.eclipse.gef.editparts.AbstractEditPart#activate()
     */
    public void activate() {
        if (!isActive())
            ((UCMNavMultiPageEditor) getModel()).getModel().getUrndef().eAdapters().add(this);
        super.activate();
    }

    /**
     * Stops listening to the URNdefinition
     * 
     * @see org.eclipse.gef.editparts.AbstractEditPart#deactivate()
     */
    public void deactivate() {
        if (isActive())
            ((UCMNavMultiPageEditor) getModel()).getModel().getUrndef().eAdapters().remove(this);
        super.deactivate();
    }

    /**
     * When the concerns of the URNdefinition change, refresh the children of this ConcernsRootTreeEditPart. There is no need to refresh the visuals of this
     * ConcernsRootTreeEditPart since it is not displayed.
     * 
     * @see org.eclipse.emf.common.notify.Adapter#notifyChanged(org.eclipse.emf.common.notify.Notification)
     */
    public void notifyChanged(Notification notification) {
        if (notification.getEventType() != Notification.REMOVING_ADAPTER || notification.getEventType() != Notification.MOVE) {
            // refresh only if concerns changed
            if (notification.getFeature() instanceof EReferenceImpl && ((EReferenceImpl) notification.getFeature()).getName().equals("concerns")) //$NON-NLS-1$
                refreshChildren();
        }
    }

    /**
     * @see org.eclipse.emf.common.notify.Adapter#getTarget()
     */
    public Notifier getTarget() {
        return target;
    }

    /**
     * @see org.eclipse.emf.common.notify.Adapter#setTarget(org.eclipse.emf.common.notify.Notifier)
     */
    public void setTarget(Notifier newTarget) {
        target = newTarget;
    }

    /**
     * @see org.eclipse.emf.common.notify.Adapter#isAdapterForType(java.lang.Object)
     */
    public boolean isAdapterForType(Object type) {
        return type.equals(getModel().getClass());
    }

}