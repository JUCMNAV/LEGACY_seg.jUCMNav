package seg.jUCMNav.editparts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.eclipse.draw2d.ConnectionLayer;
import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.FreeformLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayoutManager;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.gef.editpolicies.RootComponentEditPolicy;
import org.eclipse.jface.util.Assert;

import seg.jUCMNav.editpolicies.layout.MapAndPathGraphXYLayoutEditPolicy;
import seg.jUCMNav.figures.router.UCMConnectionRouter;
import seg.jUCMNav.model.util.ComponentRefAreaComparator;
import ucm.UcmPackage;
import ucm.map.ComponentRef;
import ucm.map.Connect;
import ucm.map.EndPoint;
import ucm.map.Map;
import ucm.map.MapPackage;
import ucm.map.NodeConnection;
import ucm.map.PathGraph;
import ucm.map.PathNode;
import ucm.map.StartPoint;

/**
 * Top level edit part. Contains the drawing board where everything is inserted.
 * 
 * @author Etienne Tremblay, jkealey
 */
public class MapAndPathGraphEditPart extends ModelElementEditPart {

    protected EditPart editPartInProcess = null;

    public MapAndPathGraphEditPart(Map map) {
        setModel(map);
        //		map.getPathGraph().eAdapters().add(this);
    }

    /**
     * ( Creates the freeform layout
     * 
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
     */
    protected IFigure createFigure() {
        FreeformLayer layer = new FreeformLayer();

        layer.setLayoutManager(new FreeformLayout());
        layer.setBorder(new LineBorder(1));
        return layer;
    }

    /**
     * Creates our top level edit policities.
     * 
     * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
     */
    protected void createEditPolicies() {
        // This install a container policy
        //		installEditPolicy(EditPolicy.CONTAINER_ROLE, new UcmEditPolicy());
        // This install the layout edit policy. Wich commands are used for
        // create/move/resize etc...

        installEditPolicy(EditPolicy.LAYOUT_ROLE, new MapAndPathGraphXYLayoutEditPolicy());
        //		installEditPolicy(EditPolicy.NODE_ROLE, null);
        //		installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE, null);
        //		installEditPolicy(EditPolicy.SELECTION_FEEDBACK_ROLE, null);
        installEditPolicy(EditPolicy.COMPONENT_ROLE, new RootComponentEditPolicy());
    }

    /**
     * This function was overwrited to make it possible to refresh both normal objects and components objects. Both have to be considered differently because
     * components are now in their own layer: COMPONENT_LAYER wich is a top level layer at the bottom most level (under connections).
     * 
     * Components editparts are children of this editpart but the component figures are children of COMPONENT_LAYER. This concept doesn't work well with the
     * default behavior of refreshChildren because the default behavior is to add or reorder children within the children figures of the figure of this edit
     * part.
     * 
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.editparts.AbstractEditPart#refreshChildren()
     */
    public void refreshChildren() {
        int i;
        EditPart editPart;
        Object model;

        HashMap modelToEditPart = new HashMap();
        List children = getChildren();
        List comps = getComponentEditParts(); // All the components of the model
        List pathNodes = getPathNodeEditParts(); // All the path nodes.

        // Udate the hashmap (model, editpart)
        for (i = 0; i < children.size(); i++) {
            editPart = (EditPart) children.get(i);
            modelToEditPart.put(editPart.getModel(), editPart);
        }

        // The list of all the model children
        List modelObjects = getModelChildren();

        // We need two index. One for the normal elements and one for the comonents.
        int index = 0;
        int comp = 0;

        // For each elements of the model
        for (i = 0; i < modelObjects.size(); i++) {
            model = modelObjects.get(i);

            if (!(model instanceof ComponentRef)) {
                // Do a quick check to see if editPart[index] == model[index]
                if (index < pathNodes.size() && ((EditPart) pathNodes.get(index)).getModel() == model) {
                    // This editpart is already updated, so just increase the counter and do nothing.
                    index++;
                    continue;
                }
            } else {
                // Do a quick check to see if editPart[comp] == model[comp]
                if (comp < comps.size() && ((EditPart) comps.get(comp)).getModel() == model) {
                    // This editpart is already updated, so just increase the counter and do nothing.
                    comp++;
                    continue;
                }
            }

            //Look to see if the EditPart is already around but in the wrong location
            editPart = (EditPart) modelToEditPart.get(model);

            // If we found the model in the editpart children list
            if (editPart != null) {
                // We have to reorder it (with the good index for components or normal objects)
                if (editPart.getModel() instanceof ComponentRef)
                    reorderChild(editPart, comp++);
                else
                    reorderChild(editPart, index++);
            } else {
                //An editpart for this model doesn't exist yet. Create and insert one.
                editPart = createChild(model);
                if (!(model instanceof ComponentRef)) {
                    addChild(editPart, index++);
                } else {
                    addChild(editPart, comp++);
                }

            }
        }
        List trash = new ArrayList();

        // Pass through all the editpart children and trash the ones that are not in the model list anymore
        for (Iterator iter = children.iterator(); iter.hasNext();) {
            EditPart edit = (EditPart) iter.next();
            if (!modelObjects.contains(edit.getModel()))
                trash.add(edit);
        }

        // Remove the trashed object from the editpart children
        for (i = 0; i < trash.size(); i++) {
            EditPart ep = (EditPart) trash.get(i);
            removeChild(ep);
        }

        // if we ever want to notify the outline view when of changes instead of having the pathgraph inform it, here is the code.
        // for now, doesn't change anything because refreshChildren() is also called too often.
        //
        //        UCMNavMultiPageEditor editor = ((ConnectionOnBottomRootEditPart) getParent()).getMultiPageEditor();
        //        if (editor.getPageCount() > 0 && editor.getActivePage() > -1) {
        //            MapTreeEditPart part = (MapTreeEditPart) ((UcmOutlinePage) editor.getAdapter(IContentOutlinePage.class)).getViewer().getEditPartRegistry().get(
        //                    getMap());
        //            if (part != null)
        //                part.refresh();
        //
        //        }
        //
    }

    /**
     * Had to be overwrited to take into account the fact that components are at the end of the children list of editpart Since this function is called with a
     * different index for normal child and components, we had to change this.
     * 
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.editparts.AbstractEditPart#addChild(org.eclipse.gef.EditPart, int)
     */
    protected void addChild(EditPart child, int index) {
        editPartInProcess = child;
        Assert.isNotNull(child);
        if (index == -1)
            index = getChildren().size();
        if (children == null)
            children = new ArrayList(2);

        int i = index;
        if (child.getModel() instanceof ComponentRef)
            i += getPathNodeEditParts().size();

        children.add(i, child);
        child.setParent(this);
        addChildVisual(child, index);
        child.addNotify();

        if (isActive())
            child.activate();
        fireChildAdded(child, index);
        editPartInProcess = null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see addChild(EditPart child, int index)
     * @see org.eclipse.gef.editparts.AbstractEditPart#reorderChild(org.eclipse.gef.EditPart, int)
     */
    protected void reorderChild(EditPart child, int index) {
        editPartInProcess = child;
        int i = index;
        if (child.getModel() instanceof ComponentRef)
            i += getPathNodeEditParts().size();

        //		 Save the constraint of the child so that it does not
        // get lost during the remove and re-add.
        IFigure childFigure = ((GraphicalEditPart) child).getFigure();
        LayoutManager layout = getContentPane().getLayoutManager();
        Object constraint = null;
        if (layout != null)
            constraint = layout.getConstraint(childFigure);

        removeChildVisual(child);
        List children = getChildren();
        children.remove(child);
        children.add(i, child);
        addChildVisual(child, index);

        setLayoutConstraint(child, childFigure, constraint);

        editPartInProcess = null;
    }

    /**
     * Depending if the editPart currently in process is a component or not, return a different content pane.
     * 
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.GraphicalEditPart#getContentPane()
     */
    public IFigure getContentPane() {
        if (editPartInProcess != null) {
            if (editPartInProcess.getModel() instanceof ComponentRef)
                return getLayer(ConnectionOnBottomRootEditPart.COMPONENT_LAYER);
            else
                return super.getContentPane();
        } else
            return super.getContentPane();
    }

    /**
     * Overwrite the default behavior to setContraint on the component layer for components.
     * 
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.GraphicalEditPart#setLayoutConstraint(org.eclipse.gef.EditPart, org.eclipse.draw2d.IFigure, java.lang.Object)
     */
    public void setLayoutConstraint(EditPart child, IFigure childFigure, Object constraint) {
        if (child.getModel() instanceof ComponentRef)
            getLayer(ConnectionOnBottomRootEditPart.COMPONENT_LAYER).setConstraint(childFigure, constraint);
        else
            super.setLayoutConstraint(child, childFigure, constraint);
    }

    /**
     * Remove the child from the parent figure. Remove components from the COMPONENT_LAYER, do the normal behavior.
     * 
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.editparts.AbstractEditPart#removeChildVisual(org.eclipse.gef.EditPart)
     */
    protected void removeChildVisual(EditPart childEditPart) {
        if (!(childEditPart.getModel() instanceof ComponentRef)) {
            IFigure child = ((GraphicalEditPart) childEditPart).getFigure();
            getContentPane().remove(child);
        } else {
            IFigure child = ((GraphicalEditPart) childEditPart).getFigure();
            getLayer(ConnectionOnBottomRootEditPart.COMPONENT_LAYER).remove(child);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see removeChildVisual(EditPart childEditPart)
     * @see org.eclipse.gef.editparts.AbstractEditPart#addChildVisual(org.eclipse.gef.EditPart, int)
     */
    protected void addChildVisual(EditPart childEditPart, int index) {
        if (!(childEditPart.getModel() instanceof ComponentRef)) {
            IFigure child = ((GraphicalEditPart) childEditPart).getFigure();
            getContentPane().add(child, index);
        } else {
            IFigure child = ((GraphicalEditPart) childEditPart).getFigure();
            getLayer(ConnectionOnBottomRootEditPart.COMPONENT_LAYER).add(child, index);
        }
    }

    /**
     * Return all the components editparts from the children list.
     * 
     * @return All the components editparts children of this editpart.
     */
    protected List getComponentEditParts() {
        List children = getChildren();
        List comps = new ArrayList();

        for (Iterator j = children.iterator(); j.hasNext();) {
            EditPart edit = (EditPart) j.next();
            if (edit.getModel() instanceof ComponentRef)
                comps.add(edit);
        }

        return comps;
    }

    /**
     * @return Return all the pathnode editparts children of this editpart.
     */
    protected List getPathNodeEditParts() {
        List children = getChildren();
        List pathNodes = new ArrayList();

        for (Iterator j = children.iterator(); j.hasNext();) {
            EditPart edit = (EditPart) j.next();
            if (edit.getModel() instanceof PathNode)
                pathNodes.add(edit);
        }

        return pathNodes;
    }

    /**
     * 
     * @return the top level ucm.map.Map used to store all information. Contains ComponentRefs and a PathGraph.
     */
    private Map getMap() {
        return (Map) getModel();
    }

    /**
     * 
     * @return The Map's path graph, used to insert nodes and connections.
     */
    private PathGraph getPathGraph() {
        return ((Map) getModel()).getPathGraph();
    }

    /**
     * Returns the map&pathgraph's children: ComponentRefs and PathNodes and PathNode Labels, ordered in such a way that they don't interfere with each other on
     * the board.
     * 
     * @see org.eclipse.gef.editparts.AbstractEditPart#getModelChildren()
     */
    protected List getModelChildren() {
        List list = getComponents();
        list.addAll(getPathNodes());
        list.addAll(getLabels());
        list.addAll(getConditions());
        return list;
    }

    private List getConditions() {
        List list = new ArrayList();

        for (Iterator i = getPathGraph().getNodeConnections().iterator(); i.hasNext();) {
            NodeConnection nc = (NodeConnection) i.next();
            if (nc.getCondition() != null) {
                list.add(nc.getCondition());
            }
        }
        for (Iterator i = getPathGraph().getPathNodes().iterator(); i.hasNext();) {
            PathNode pn = (PathNode) i.next();
            if (pn instanceof StartPoint && ((StartPoint) pn).getPrecondition() != null) {
                list.add(((StartPoint) pn).getPrecondition());
            } else if (pn instanceof EndPoint && ((EndPoint) pn).getPostcondition() != null) {
                list.add(((EndPoint) pn).getPostcondition());
            }
        }

        return list;
    }

    /**
     * @return
     */
    private List getPathNodes() {
        List list = new ArrayList();

        // put the nodes on top because they are always over components.
        for (Iterator i = getPathGraph().getPathNodes().iterator(); i.hasNext();) {
            PathNode pn = (PathNode) i.next();
            if (!(pn instanceof Connect)) {
                list.add(pn);
            }
        }

        return list;
    }

    /**
     * @return
     */
    private List getLabels() {
        List list = new ArrayList();

        // put the labels on top because they are always over components.
        for (Iterator i = getPathGraph().getPathNodes().iterator(); i.hasNext();) {
            PathNode node = (PathNode) i.next();
            if (node.getLabel() != null) {
                list.add(node.getLabel());
            }
        }

        for (Iterator i = getMap().getCompRefs().iterator(); i.hasNext();) {
            ComponentRef component = (ComponentRef) i.next();
            if (component.getLabel() != null) {
                list.add(component.getLabel());
            }
        }

        return list;
    }

    /**
     * 
     * @return
     */
    private List getComponents() {
        List list = new ArrayList();
        Object o = new Object();

        // get all component references
        for (Iterator i = getMap().getCompRefs().iterator(); i.hasNext();) {
            list.add(i.next());
        }

        // sort them by ascending area
        Collections.sort(list, new ComponentRefAreaComparator());
        // reverse the list so that our largest components are in the back.
        Collections.reverse(list);
        return list;
    }

    private int countChanged = 1;

    /**
     * Change listener. Has to handle when its children are changed and when we might have to reorder them.
     * 
     * @see org.eclipse.emf.common.notify.Adapter#notifyChanged(org.eclipse.emf.common.notify.Notification)
     */
    public void notifyChanged(Notification notification) {
        int type = notification.getEventType();
        int featureId = notification.getFeatureID(UcmPackage.class);
        switch (type) {
        case Notification.ADD:
        case Notification.ADD_MANY:
            // Don't call refreshChildren if a NodeConnection is sending a notification.
            if (!(notification.getNewValue() instanceof NodeConnection))
                refreshChildren();
            break;
        case Notification.REMOVE:
        case Notification.REMOVE_MANY:
            // Don't call refreshChildren if a NodeConnection is sending a notification.
            if (!(notification.getOldValue() instanceof NodeConnection))
                refreshChildren();
            break;
        case Notification.SET:
            switch (featureId) {
            case MapPackage.COMPONENT_REF__LABEL:
            case MapPackage.PATH_NODE__LABEL:
                refreshChildren();
                break;
            // duplicate with pn_label case MapPackage.COMPONENT_REF__WIDTH:
            case MapPackage.COMPONENT_REF__HEIGHT:
                if (notification.getNotifier() instanceof ComponentRef) {
                    // Wait until we received a notification for both width and height
                    // This way we don't call refreshChildren two times.
                    if (countChanged == 2) {
                        // we might have to reorder the children so as to put the largest components in the back.
                        refreshChildren();
                        countChanged = 1;
                    } else
                        countChanged++;
                }
                break;
            }
            //            refreshVisuals();
            break;
        }

        refreshChildren();
    }

    /**
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.editparts.AbstractEditPart#registerVisuals()
     */
    protected void registerVisuals() {
        ConnectionLayer cLayer = (ConnectionLayer) getLayer(LayerConstants.CONNECTION_LAYER);
        cLayer.setConnectionRouter(new UCMConnectionRouter(getViewer().getEditPartRegistry(), getPathGraph()));

        super.registerVisuals();
    }

    /**
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.editparts.ModelElementEditPart#refreshVisuals()
     */
    protected void refreshVisuals() {
        for (Iterator iter = getChildren().iterator(); iter.hasNext();) {
            AbstractGraphicalEditPart element = (AbstractGraphicalEditPart) iter.next();
            element.refresh();
            // refresh stub labels; doing this in its refreshVisuals crashed the app with a heap space error.
            if (element instanceof StubEditPart)
                ((StubEditPart) element).refreshInOuts();
        }
    }

    /**
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.EditPart#activate()
     */
    public void activate() {
        if (!isActive()) {
            getPathGraph().eAdapters().add(this);
            getMap().eAdapters().add(this);
        }
        super.activate();
    }

    /**
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.EditPart#deactivate()
     */
    public void deactivate() {
        if (isActive()) {
            getMap().eAdapters().remove(this);
            getPathGraph().eAdapters().remove(this);
        }
        super.deactivate();
    }

}