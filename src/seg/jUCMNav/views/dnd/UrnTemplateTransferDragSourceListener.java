package seg.jUCMNav.views.dnd;

import grl.ContributionContext;
import grl.EvaluationStrategy;
import grl.IntentionalElement;
import grl.IntentionalElementRef;
import grl.kpimodel.KPIInformationElement;
import grl.kpimodel.KPIInformationElementRef;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.dnd.TemplateTransferDragSourceListener;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.ui.PlatformUI;

import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.editparts.treeEditparts.OutlineRootEditPart;
import seg.jUCMNav.views.outline.UrnTreeViewer;
import ucm.map.RespRef;
import ucm.scenario.ScenarioDef;
import urn.URNspec;
import urncore.IURNContainer;
import urncore.IURNContainerRef;
import urncore.Responsibility;

/**
 * Drag source setup on the UrnOutlinePage
 * 
 * @author jkealey, pchen
 */
public class UrnTemplateTransferDragSourceListener extends TemplateTransferDragSourceListener {

    /**
     * Constructs a new listener for the specified EditPartViewer. The TemplateTransferDragSourceListener will only be enabled when a single EditPart is
     * selected
     * 
     * @param viewer
     *            the EditPartViewer that is the drag source
     */
    public UrnTemplateTransferDragSourceListener(EditPartViewer viewer) {
        super(viewer, UrnTemplateTransfer.getInstance());
    }

    /**
     * A helper method that returns <code>null</code> or the <i>template</i> Object from the currently selected EditPart.
     * 
     * @return the template
     */
    protected Object getTemplate() {
        List selection = getViewer().getSelectedEditParts();
        if (selection.size() == 1) {
            EditPart editpart = (EditPart) getViewer().getSelectedEditParts().get(0);
            Object model = editpart.getModel();
            if (model instanceof IURNContainer || model instanceof Responsibility || model instanceof IntentionalElement
                    || model instanceof KPIInformationElement)
                return model;
            else if (model instanceof IURNContainerRef)
                return ((IURNContainerRef) model).getContDef();
            else if (model instanceof RespRef)
                return ((RespRef) model).getRespDef();
            else if (model instanceof IntentionalElementRef)
                return ((IntentionalElementRef) model).getDef();
            else if (model instanceof KPIInformationElementRef)
                return ((KPIInformationElementRef) model).getDef();
            else if (model instanceof ScenarioDef || model instanceof EvaluationStrategy || model instanceof ContributionContext)
                return model;
        }
        return null;
    }

    public void dragFinished(DragSourceEvent event) {
        // ModelCreationFactory creates useless refs that are not added when dragged here.
        URNspec urn;

        UCMNavMultiPageEditor editor = null;
        if (getViewer() instanceof UrnTreeViewer) {
            editor = (UCMNavMultiPageEditor) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
            // ((UrnTreeViewer)getViewer()).getContents().getModel() you can get parent urn from this
        } else if (getViewer() instanceof OutlineRootEditPart)
            editor = ((UCMNavMultiPageEditor) ((OutlineRootEditPart) getViewer().getRootEditPart().getChildren().get(0)).getModel());

        if (editor != null) {
            urn = editor.getModel();
            cleanUnusedRespRefs(urn);
            cleanUnusedContRefs(urn);
            cleanUnusedIntElemntRef(urn);
            cleanUnusedKPIInformationElemntRef(urn);
        }
    }

    private void cleanUnusedRespRefs(URNspec urn) {
        ArrayList toRemove = new ArrayList();
        for (Iterator iter = urn.getUrndef().getResponsibilities().iterator(); iter.hasNext();) {
            Responsibility resp = (Responsibility) iter.next();
            for (Iterator iterator = resp.getRespRefs().iterator(); iterator.hasNext();) {
                RespRef ref = (RespRef) iterator.next();
                if (ref.getDiagram() == null)
                    toRemove.add(ref);
            }
        }

        for (Iterator iter = toRemove.iterator(); iter.hasNext();) {
            RespRef ref = (RespRef) iter.next();
            ref.setRespDef(null);
        }
    }

    private void cleanUnusedContRefs(URNspec urn) {
        ArrayList toRemove = new ArrayList();
        for (Iterator iter = urn.getUrndef().getComponents().iterator(); iter.hasNext();) {
            IURNContainer cont = (IURNContainer) iter.next();
            for (Iterator iterator = cont.getContRefs().iterator(); iterator.hasNext();) {
                IURNContainerRef ref = (IURNContainerRef) iterator.next();
                if (ref.getDiagram() == null)
                    toRemove.add(ref);
            }
        }

        for (Iterator iter = urn.getGrlspec().getActors().iterator(); iter.hasNext();) {
            IURNContainer cont = (IURNContainer) iter.next();
            for (Iterator iterator = cont.getContRefs().iterator(); iterator.hasNext();) {
                IURNContainerRef ref = (IURNContainerRef) iterator.next();
                if (ref.getDiagram() == null)
                    toRemove.add(ref);
            }
        }

        for (Iterator iter = toRemove.iterator(); iter.hasNext();) {
            IURNContainerRef ref = (IURNContainerRef) iter.next();
            ref.setContDef(null);
        }
    }

    private void cleanUnusedIntElemntRef(URNspec urn) {
        ArrayList toRemove = new ArrayList();
        for (Iterator iter = urn.getGrlspec().getIntElements().iterator(); iter.hasNext();) {
            IntentionalElement elem = (IntentionalElement) iter.next();
            for (Iterator iterator = elem.getRefs().iterator(); iterator.hasNext();) {
                IntentionalElementRef ref = (IntentionalElementRef) iterator.next();
                if (ref.getDiagram() == null)
                    toRemove.add(ref);
            }
        }

        for (Iterator iter = toRemove.iterator(); iter.hasNext();) {
            IntentionalElementRef ref = (IntentionalElementRef) iter.next();
            ref.setDef(null);
        }
    }

    private void cleanUnusedKPIInformationElemntRef(URNspec urn) {
        ArrayList toRemove = new ArrayList();
        for (Iterator iter = urn.getGrlspec().getKpiInformationElements().iterator(); iter.hasNext();) {
            KPIInformationElement elem = (KPIInformationElement) iter.next();
            for (Iterator iterator = elem.getRefs().iterator(); iterator.hasNext();) {
                KPIInformationElementRef ref = (KPIInformationElementRef) iterator.next();
                if (ref.getDiagram() == null)
                    toRemove.add(ref);
            }
        }

        for (Iterator iter = toRemove.iterator(); iter.hasNext();) {
            KPIInformationElementRef ref = (KPIInformationElementRef) iter.next();
            ref.setDef(null);
        }
    }
}
