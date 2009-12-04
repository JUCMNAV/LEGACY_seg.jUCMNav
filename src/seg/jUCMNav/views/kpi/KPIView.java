package seg.jUCMNav.views.kpi;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.editparts.AbstractTreeEditPart;
import org.eclipse.gef.editparts.ScalableFreeformRootEditPart;
import org.eclipse.gef.ui.parts.ScrollingGraphicalViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IPartListener2;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.part.ViewPart;

import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.editors.UrnEditDomain;
import seg.jUCMNav.editparts.kpiTreeEditparts.KPIRootEditPart;
import seg.jUCMNav.editparts.kpiViewEditparts.KPIViewEditPartFactory;
import seg.jUCMNav.strategies.EvaluationStrategyManager;
import seg.jUCMNav.views.JUCMNavRefreshableView;
import seg.jUCMNav.views.preferences.DisplayPreferences;

/**
 * 
 * The KPI view.
 * 
 * @author pchen
 * 
 */
public class KPIView extends ViewPart implements IPartListener2, ISelectionChangedListener, ISelectionListener, JUCMNavRefreshableView {
    private ScrollingGraphicalViewer viewer;
    private ScalableFreeformRootEditPart root;

    public void createPartControl(Composite parent) {
        // Create the viewer
        viewer = new ScrollingGraphicalViewer();
        viewer.setEditDomain(new UrnEditDomain(null));
        viewer.createControl(parent);

        // viewer.addSelectionChangedListener(this);
        // -- getSite().setSelectionProvider(viewer);
        getSite().getPage().addPartListener(this);
        getSite().getPage().addSelectionListener(this);

        // Set root editpart
        root = new ScalableFreeformRootEditPart();
        viewer.setRootEditPart(root);

        // Register the view
        DisplayPreferences.getInstance().registerListener(this);
    }

    /**
     * Removes listeners
     * 
     * @see org.eclipse.ui.part.IPage#dispose()
     */
    public void dispose() {
        getSite().getPage().removePartListener(this);
        getSite().getPage().removeSelectionListener(this);
        //getSite().getPage().removePostSelectionListener(this);
        DisplayPreferences.getInstance().unregisterListener(this);

        if (viewer != null) {
            Object p = viewer.getRootEditPart();
            if (p instanceof AbstractTreeEditPart) {
                ((AbstractTreeEditPart) p).setModel(null);
            }

            if (viewer.getEditDomain() instanceof UrnEditDomain) {
                UrnEditDomain domain = (UrnEditDomain) viewer.getEditDomain();
                domain.dispose();
            }
        }

        // dispose
        super.dispose();

        viewer = null;
        root = null;
    }

    /**
     * Passing the focus request to the viewer's control.
     */
    public void setFocus() {
        if (viewer.getContents() != null) {
            viewer.getControl().setFocus();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.IPartListener2#partActivated(org.eclipse.ui.IWorkbenchPartReference)
     */
    public void partActivated(IWorkbenchPartReference partRef) {
        if (partRef.getPart(false) == this || partRef.getPart(false) instanceof UCMNavMultiPageEditor) {
            setEditor(partRef);
        } else {
            // bug 709 - if we are no longer selecting a UCM editor, flush the current selection.
            if (!(partRef.getPage().getActiveEditor() instanceof UCMNavMultiPageEditor)) {
                viewer.setContents(null);
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.IPartListener2#partBroughtToTop(org.eclipse.ui.IWorkbenchPartReference)
     */
    public void partBroughtToTop(IWorkbenchPartReference partRef) {
        // If the part brought to top is this view, then update the input.
        if (partRef.getPart(false) == this) {
            setEditor(partRef);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.IPartListener2#partClosed(org.eclipse.ui.IWorkbenchPartReference)
     */
    public void partClosed(IWorkbenchPartReference partRef) {
        if (partRef.getPart(false) instanceof UCMNavMultiPageEditor && partRef.getPage().getActiveEditor() == null) {
            viewer.setContents(null);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.IPartListener2#partDeactivated(org.eclipse.ui.IWorkbenchPartReference)
     */
    public void partDeactivated(IWorkbenchPartReference partRef) {
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.IPartListener2#partHidden(org.eclipse.ui.IWorkbenchPartReference)
     */
    public void partHidden(IWorkbenchPartReference partRef) {
        // viewer.setContents(null);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.IPartListener2#partInputChanged(org.eclipse.ui.IWorkbenchPartReference)
     */
    public void partInputChanged(IWorkbenchPartReference partRef) {
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.IPartListener2#partOpened(org.eclipse.ui.IWorkbenchPartReference)
     */
    public void partOpened(IWorkbenchPartReference partRef) {
        if (partRef.getPart(false) instanceof UCMNavMultiPageEditor || partRef.getPart(false) == this) {
            setEditor(partRef);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.IPartListener2#partVisible(org.eclipse.ui.IWorkbenchPartReference)
     */
    public void partVisible(IWorkbenchPartReference partRef) {
        if (partRef.getPart(false) instanceof UCMNavMultiPageEditor || partRef.getPart(false) == this) {
            setEditor(partRef);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.viewers.ISelectionChangedListener#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
     */
    public void selectionChanged(SelectionChangedEvent event) {
    }

    public void selectionChanged(IWorkbenchPart part, ISelection selection) {
        handleSelectionChanged(part, selection);
    }

    protected void handleSelectionChanged(IWorkbenchPart part, ISelection selection) {
        if (part == this)
            return;
        if (!(selection instanceof IStructuredSelection))
            return;
        if (EvaluationStrategyManager.getInstance().getEvaluationStrategy() != null) {
            IStructuredSelection sel = (IStructuredSelection) selection;
            if (sel.size() > 0) {
                Object obj = sel.getFirstElement();

                // removed the SELECTED_PRIMARY condition due to following two issues:
                // When clicking on the blank area of the current UCM/GRL map, no any SELECTED_PRIMARY returned;
                // After closing jucm file and reopening it, choosing the items in KPI list view will not return SELECTED_PRIMARY.
                // if (obj != null && obj instanceof EditPart && ((EditPart) obj).getSelected() == EditPart.SELECTED_PRIMARY) {
                if (obj != null && obj instanceof EditPart && !(obj instanceof KPIRootEditPart)) {
                    if (viewer.getContents() != null && viewer.getContents().getModel() != null) {
                        if ((viewer.getContents().getModel()).equals(((EditPart) obj).getModel())) {
                            return;
                        }

                        viewer.getContents().setModel(null);
                    }
                    viewer.setContents(null);
                    viewer.setContents(((EditPart) obj).getModel());
                }
            }
        } else {
            viewer.setContents(null);
        }
    }

    public Object getAdapter(Class adapter) {
        return super.getAdapter(adapter);
    }

    private void setEditor(IWorkbenchPartReference partRef) {
        if (partRef.getPage().getActiveEditor() instanceof UCMNavMultiPageEditor) {
            setEditor((UCMNavMultiPageEditor) partRef.getPage().getActiveEditor());
        }
    }

    /**
     * @param editor
     */
    private void setEditor(UCMNavMultiPageEditor editor) {
        viewer.setEditDomain(new UrnEditDomain(null));
        viewer.setEditPartFactory(new KPIViewEditPartFactory());
        EvaluationStrategyManager.getInstance().setKPIViewer(viewer);
    }

    public void refreshView() {
        viewer.setContents(viewer.getContents());

    }

}
