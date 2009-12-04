package seg.jUCMNav.editparts.concernsTreeEditparts;

import grl.GRLGraph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.EReferenceImpl;
import org.eclipse.gef.EditPart;
import org.eclipse.swt.graphics.Image;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.Messages;
import seg.jUCMNav.editparts.treeEditparts.LabelTreeEditPart;
import seg.jUCMNav.model.util.DelegatingElementComparator;
import ucm.map.UCMmap;
import urn.URNspec;
import urncore.Concern;
import urncore.IURNDiagram;
import urncore.URNdefinition;

/**
 * TreeEditPart for a label in the Concern Outline. Only two labels exists in this context. They are used as a group for root maps/GRL graphs without concerns
 * and as a group for recursive maps that are not shown anywhere else in the outline.
 * 
 * @author gunterm
 */
public class ConcernsLabelTreeEditPart extends LabelTreeEditPart {

    // have to keep track of the elements a label needs to observe because the
    // elements (Concerns and UCMmaps) change dynamically
    private List observedConcerns = new ArrayList();
    private List observedMaps = new ArrayList();

    /**
     * @param model
     *            is a String containing the name of the group
     * @param root
     *            is the edited URNspec
     */
    public ConcernsLabelTreeEditPart(Object model, URNspec root) {
        super(model, root);
    }

    /**
     * @return the sorted list of diagrams in the group either root maps and GRL graphs that do not have a concern or recursive maps (these are maps that are
     *         part of an infinite loop because a map is included in itself either directly or indirectly)
     * @see seg.jUCMNav.editparts.treeEditparts.LabelTreeEditPart#getModelChildren()
     */
    protected List getModelChildren() {
        if (getLabel().equals(Messages.getString("ConcernsLabelTreeEditPart.DiagramsWithoutConcerns"))) //$NON-NLS-1$
            return getRootMapsGrlGraphsWithoutConcerns();
        else if (getLabel().equals(Messages.getString("ConcernsLabelTreeEditPart.RecursiveMaps"))) //$NON-NLS-1$
            return getRecursiveMaps();
        else
            return new ArrayList();
    }

    /**
     * @return the sorted list of root maps and GRL graphs that do not have a concern
     */
    private List getRootMapsGrlGraphsWithoutConcerns() {
        ArrayList list = new ArrayList();
        ArrayList listGrl = new ArrayList();
        // add all root maps and GRL graphs in separate lists if no concern is assigned to them
        for (Iterator iter = root.getUrndef().getSpecDiagrams().iterator(); iter.hasNext();) {
            IURNDiagram diagram = (IURNDiagram) iter.next();
            if (diagram.getConcern() == null) {
                if (diagram instanceof UCMmap && ((UCMmap) diagram).getParentStub().isEmpty())
                    list.add(diagram);
                else if (diagram instanceof GRLGraph)
                    // TODO CONCERNS: not all GRL graphs should be on this list (no pointcut graphs)
                    // this will be tricky as the connection between pointcut graphs and advice graphs will have to be
                    // found dynamically (also the status of pointcut graph will also have to be deduced from the
                    // elements on the GRL graph))
                    listGrl.add(diagram);
            }
        }
        Collections.sort(list, new DelegatingElementComparator());
        Collections.sort(listGrl, new DelegatingElementComparator());
        // add this group to ensure that all UCMs are shown in the tree
        list.add(Messages.getString("ConcernsLabelTreeEditPart.RecursiveMaps")); //$NON-NLS-1$
        list.addAll(listGrl);
        return list;
    }

    /**
     * deal with a special case: maps that contain each other recursively either directly or indirectly (they need to be included if they are not already shown
     * in the outline)
     * 
     * @return the sorted list of recursive maps
     */
    private List getRecursiveMaps() {
        ArrayList list = new ArrayList();
        // add all UCMs to the list
        for (Iterator iter = root.getUrndef().getSpecDiagrams().iterator(); iter.hasNext();) {
            IURNDiagram element = (IURNDiagram) iter.next();
            if (element instanceof UCMmap)
                list.add(element);
        }
        // get the root of the tree and go through the whole tree; remove each map that is found in
        // the tree from the list; the maps remaining in the list have not yet been shown in the tree
        removeMaps(getRoot().getContents(), list);
        return list;
    }

    /**
     * removes the model of the editPart from the list if it is contained in the list, then continues recursively with the children of the editPart
     * 
     * @param editPart
     *            of the outline that is visited recursively
     * @param list
     *            of maps that are possibly not shown in the outline
     */
    private void removeMaps(EditPart editPart, ArrayList list) {
        // only continue if the edit part exists and the list of remaining maps is not empty
        if (editPart != null && !list.isEmpty()) {
            // remove the model of the edit part from the list of remaining maps
            Object model = editPart.getModel();
            if (model != null && list.contains(model))
                list.remove(model);
            // continue recursively with the children of the edit part but do not take the children
            // of "Recursive Maps" into account (if we do, then this will only work the first
            // time. the second time, the recursive root maps are already in the tree (in the
            // "Recursive Maps" group). if we continue with the children of "Recursive Maps"
            // then the returned list will be empty and the updated tree will not contain any
            // recursive maps.)
            if (model != null && (!(model instanceof String) || !model.equals(Messages.getString("ConcernsLabelTreeEditPart.RecursiveMaps")))) { //$NON-NLS-1$
                List children = editPart.getChildren();
                if (children != null) {
                    for (Iterator iter = children.iterator(); iter.hasNext();) {
                        EditPart element = (EditPart) iter.next();
                        removeMaps(element, list);
                    }
                }
            }
        }
    }

    /**
     * Label text is inferred from the model element.
     * 
     * @see seg.jUCMNav.editparts.treeEditparts.LabelTreeEditPart#getText()
     */
    protected String getText() {
        if (getLabel().equals(Messages.getString("ConcernsLabelTreeEditPart.DiagramsWithoutConcerns")) || getLabel().equals(Messages.getString("ConcernsLabelTreeEditPart.RecursiveMaps"))) //$NON-NLS-1$ //$NON-NLS-2$
            return getLabel();
        else
            return null;
    }

    /**
     * Return icon associated with the label
     * 
     * @see seg.jUCMNav.editparts.treeEditparts.LabelTreeEditPart#getImage()
     */
    protected Image getImage() {
        if (super.getImage() == null)
            setImage((JUCMNavPlugin.getImage("icons/ucm16.gif"))); //$NON-NLS-1$
        return super.getImage();
    }

    /**
     * Listens also to each Concern and each UCMMap
     * 
     * @see seg.jUCMNav.editparts.treeEditparts.LabelTreeEditPart#activate()
     */
    public void activate() {
        if (!isActive()) {
            matchObservedToConcerns();
            matchObservedToMaps();
        }
        super.activate();
    }

    /**
     * Stops listening to each Concern and each UCMmap
     * 
     * @see seg.jUCMNav.editparts.treeEditparts.LabelTreeEditPart#deactivate()
     */
    public void deactivate() {
        if (isActive()) {
            stopListening(observedConcerns);
            stopListening(observedMaps);
        }
        super.deactivate();
    }

    /**
     * When concerns are added or removed from the URNdefinition, start/stop listening to the concern. When maps are added or removed from the URNdefinition,
     * start/stop listening to the map.
     * 
     * @see seg.jUCMNav.editparts.treeEditparts.UrnModelElementTreeEditPart#notifyChanged(org.eclipse.emf.common.notify.Notification)
     */
    public void notifyChanged(Notification notification) {
        // only start listening to a new concern
        if ((notification.getEventType() == Notification.ADD || notification.getEventType() == Notification.ADD_MANY)
                && notification.getNotifier() instanceof URNdefinition) {
            if (notification.getFeature() instanceof EReferenceImpl && ((EReferenceImpl) notification.getFeature()).getName().equals("concerns")) //$NON-NLS-1$
                matchObservedToConcerns();
        }
        super.notifyChanged(notification);
        // only stop listening to removed concerns
        if ((notification.getEventType() == Notification.REMOVE || notification.getEventType() == Notification.REMOVE_MANY)
                && notification.getNotifier() instanceof URNdefinition) {
            if (notification.getFeature() instanceof EReferenceImpl && ((EReferenceImpl) notification.getFeature()).getName().equals("concerns")) //$NON-NLS-1$
                matchObservedToConcerns();
        }
    }

    /**
     * ensures that the list of observed concerns matches the current list of concerns in the model, adds new concerns and starts listening to them, removes
     * deleted concerns and stops listening to them
     */
    private void matchObservedToConcerns() {
        List newObservedConcerns = new ArrayList();
        for (Iterator iter = root.getUrndef().getConcerns().iterator(); iter.hasNext();) {
            Concern concern = (Concern) iter.next();
            if (observedConcerns.contains(concern)) {
                // no change - keep listening to the concern
                newObservedConcerns.add(concern);
                observedConcerns.remove(concern);
            } else {
                // add new concern to observed
                concern.eAdapters().add(this);
                newObservedConcerns.add(concern);
            }
        }
        // remaining observerd concerns have to be removed
        for (Iterator iter = observedConcerns.iterator(); iter.hasNext();) {
            ((Concern) iter.next()).eAdapters().remove(this);
        }
        // update the observed concern list
        observedConcerns = newObservedConcerns;
    }

    /**
     * ensures that the list of observed maps matches the current list of maps in the model, adds new maps and starts listening to them, removes deleted maps
     * and stops listening to them
     */
    private void matchObservedToMaps() {
        List newObservedMaps = new ArrayList();
        for (Iterator iter = root.getUrndef().getSpecDiagrams().iterator(); iter.hasNext();) {
            IURNDiagram diagram = (IURNDiagram) iter.next();
            if (diagram instanceof UCMmap) {
                if (observedMaps.contains(diagram)) {
                    // no change - keep listening to the map
                    newObservedMaps.add(diagram);
                    observedMaps.remove(diagram);
                } else {
                    // add new map to observed
                    diagram.eAdapters().add(this);
                    newObservedMaps.add(diagram);
                }
            }
        }
        // remaining observerd maps have to be removed
        for (Iterator iter = observedMaps.iterator(); iter.hasNext();) {
            ((UCMmap) iter.next()).eAdapters().remove(this);
        }
        // update the observed maps list
        observedMaps = newObservedMaps;
    }

    /**
     * removes this label from the list of listeners
     * 
     * @param observed
     *            is either the list of Concerns or the list of UCMmaps
     */
    private void stopListening(List observed) {
        for (Iterator iter = observed.iterator(); iter.hasNext();) {
            EObject element = (EObject) iter.next();
            element.eAdapters().remove(this);
        }
        observed.clear();
    }

}
