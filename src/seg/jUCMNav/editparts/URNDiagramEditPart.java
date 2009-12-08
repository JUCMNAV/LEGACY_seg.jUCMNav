package seg.jUCMNav.editparts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.Assert;
import org.eclipse.draw2d.FreeformLayer;
import org.eclipse.draw2d.FreeformLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LayoutManager;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

import seg.jUCMNav.model.util.SpecificationComponentRefAreaComparator;
import urncore.IURNContainerRef;
import urncore.IURNDiagram;
import urncore.IURNNode;

/**
 * Editpart for a use case map and grl diagrams. Contains the drawing board where everything is inserted.
 * 
 * Parent of all other edit parts in the editor.
 * 
 * This edit part and those calling it don't follow GEF standards. It may be possible to refactor our code so that it is cleaner, but we have not yet attempted.
 * Don't use this class as an example for edit part behaviour.
 * 
 * @author Etienne Tremblay, jkealey
 */
public abstract class URNDiagramEditPart extends ModelElementEditPart {

    protected EditPart editPartInProcess = null;

    /**
     * Creates an editpart for the specificationdiagram
     * 
     * @param diagram
     *            the specification diagram
     */
    public URNDiagramEditPart(IURNDiagram diagram) {
        setModel(diagram);
    }

    /**
     * Had to be overridden to take into account the fact that components are at the end of the children list of editpart. Since this function is called with a
     * different index for normal child and components, we had to change this.
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
        if (child.getModel() instanceof IURNContainerRef)
            i += getSpecificationNodeEditParts().size();

        children.add(i, child);
        child.setParent(this);
        addChildVisual(child, index);
        child.addNotify();

        if (isActive())
            child.activate();
        fireChildAdded(child, index);
        editPartInProcess = null;
    }

    /**
     * @see #removeChildVisual(EditPart childEditPart)
     * @see org.eclipse.gef.editparts.AbstractEditPart#addChildVisual(org.eclipse.gef.EditPart, int)
     */
    protected void addChildVisual(EditPart childEditPart, int index) {
        if (!(childEditPart.getModel() instanceof IURNContainerRef)) {
            IFigure child = ((GraphicalEditPart) childEditPart).getFigure();
            getContentPane().add(child, index);
        } else {
            IFigure child = ((GraphicalEditPart) childEditPart).getFigure();
            getLayer(URNRootEditPart.COMPONENT_LAYER).add(child, index);
        }
    }

    /**
     * Creates the freeform layout
     * 
     * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
     */
    protected IFigure createFigure() {
        FreeformLayer layer = new FreeformLayer();
        layer.setLayoutManager(new FreeformLayout());
        // layer.setBorder(new LineBorder(1));
        return layer;
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
            if (edit.getModel() instanceof IURNContainerRef)
                comps.add(edit);
        }

        return comps;
    }

    /**
     * @return the componentrefs, ordered by area.
     */
    protected List getComponents() {
        List list = new ArrayList();

        // get all component references
        for (Iterator i = getDiagram().getContRefs().iterator(); i.hasNext();) {
            list.add(i.next());
        }

        // sort them by ascending area
        Collections.sort(list, new SpecificationComponentRefAreaComparator());
        // reverse the list so that our largest components are in the back.
        Collections.reverse(list);
        return list;
    }

    /**
     * Depending if the editPart currently in process is a component or not, return a different content pane.
     * 
     * @see org.eclipse.gef.GraphicalEditPart#getContentPane()
     */
    public IFigure getContentPane() {
        if (editPartInProcess != null) {
            if (editPartInProcess.getModel() instanceof IURNContainerRef)
                return getLayer(URNRootEditPart.COMPONENT_LAYER);
            else
                return super.getContentPane();
        } else
            return super.getContentPane();
    }

    /**
     * 
     * @return the top level SpecificationDiagram used to store all information. Contains ComponentRefs.
     */
    protected IURNDiagram getDiagram() {
        return (IURNDiagram) getModel();
    }

    /**
     * @return Return all the specificationnode editparts children of this editpart.
     */
    protected List getSpecificationNodeEditParts() {
        List children = getChildren();
        List nodes = new ArrayList();

        for (Iterator j = children.iterator(); j.hasNext();) {
            EditPart edit = (EditPart) j.next();
            if (edit.getModel() instanceof IURNNode)
                nodes.add(edit);
        }

        return nodes;
    }

    /**
     * This function was overridden to make it possible to refresh both normal objects and components objects. Both have to be considered differently because
     * components are now in their own layer: COMPONENT_LAYER which is a top level layer at the bottom most level (under connections).
     * 
     * Components editparts are children of this editpart but the component figures are children of COMPONENT_LAYER. This concept doesn't work well with the
     * default behavior of refreshChildren because the default behavior is to add or reorder children within the children figures of the figure of this edit
     * part.
     * 
     * @see org.eclipse.gef.editparts.AbstractEditPart#refreshChildren()
     */
    public void refreshChildren() {
        if (getViewer()==null) return;
        int i;
        EditPart editPart;
        Object model;
        

        HashMap modelToEditPart = new HashMap();
        List children = getChildren();
        List comps = getComponentEditParts(); // All the components of the model
        List nodes = getSpecificationNodeEditParts(); // All the path nodes.

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

            if (!(model instanceof IURNContainerRef)) {
                // Do a quick check to see if editPart[index] == model[index]
                if (index < nodes.size() && ((EditPart) nodes.get(index)).getModel() == model) {
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

            // Look to see if the EditPart is already around but in the wrong location
            editPart = (EditPart) modelToEditPart.get(model);

            // If we found the model in the editpart children list
            if (editPart != null) {
                // We have to reorder it (with the good index for components or normal objects)
                if (editPart.getModel() instanceof IURNContainerRef)
                    reorderChild(editPart, comp++);
                else
                    reorderChild(editPart, index++);
            } else {
                // An editpart for this model doesn't exist yet. Create and insert one.
                editPart = createChild(model);
                if (!(model instanceof IURNContainerRef)) {
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
        // UCMNavMultiPageEditor editor = ((ConnectionOnBottomRootEditPart) getParent()).getMultiPageEditor();
        // if (editor.getPageCount() > 0 && editor.getActivePage() > -1) {
        // MapTreeEditPart part = (MapTreeEditPart) ((UcmOutlinePage) editor.getAdapter(IContentOutlinePage.class)).getViewer().getEditPartRegistry().get(
        // getMap());
        // if (part != null)
        // part.refresh();
        //
        // }
        //
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
        }
    }

    /**
     * Remove the child from the parent figure. Remove components from the COMPONENT_LAYER, do the normal behavior.
     * 
     * @see org.eclipse.gef.editparts.AbstractEditPart#removeChildVisual(org.eclipse.gef.EditPart)
     */
    protected void removeChildVisual(EditPart childEditPart) {
        if (!(childEditPart.getModel() instanceof IURNContainerRef)) {
            IFigure child = ((GraphicalEditPart) childEditPart).getFigure();
            getContentPane().remove(child);
        } else {
            IFigure child = ((GraphicalEditPart) childEditPart).getFigure();
            getLayer(URNRootEditPart.COMPONENT_LAYER).remove(child);
        }
    }

    /**
     * Had to be overridden to take into account the fact that components are at the end of the children list of editpart. Since this function is called with a
     * different index for normal child and components, we had to change this.
     * 
     * @see #addChild(EditPart child, int index)
     * @see org.eclipse.gef.editparts.AbstractEditPart#reorderChild(org.eclipse.gef.EditPart, int)
     */
    protected void reorderChild(EditPart child, int index) {
        editPartInProcess = child;
        int i = index;
        if (child.getModel() instanceof IURNContainerRef)
            i += getSpecificationNodeEditParts().size();

        // Save the constraint of the child so that it does not
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
     * Overrode the default behavior to setContraint on the component layer for components.
     * 
     * @see org.eclipse.gef.GraphicalEditPart#setLayoutConstraint(org.eclipse.gef.EditPart, org.eclipse.draw2d.IFigure, java.lang.Object)
     */
    public void setLayoutConstraint(EditPart child, IFigure childFigure, Object constraint) {
        if (child.getModel() instanceof IURNContainerRef)
            getLayer(URNRootEditPart.COMPONENT_LAYER).setConstraint(childFigure, constraint);
        else
            super.setLayoutConstraint(child, childFigure, constraint);
    }

}