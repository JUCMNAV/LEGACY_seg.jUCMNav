package seg.jUCMNav.editparts;

import java.util.Iterator;
import java.util.Vector;

import org.eclipse.draw2d.IFigure;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.views.properties.IPropertySource;

import seg.jUCMNav.Messages;
import seg.jUCMNav.figures.PathNodeFigure;
import seg.jUCMNav.figures.StubFigure;
import seg.jUCMNav.scenarios.ScenarioUtils;
import seg.jUCMNav.views.property.StubPropertySource;
import seg.jUCMNav.views.stub.PluginListDialog;
import seg.jUCMNav.views.stub.StubBindingsDialog;
import ucm.UcmPackage;
import ucm.map.AspectKind;
import ucm.map.MapPackage;
import ucm.map.NodeConnection;
import ucm.map.PathNode;
import ucm.map.PluginBinding;
import ucm.map.PointcutKind;
import ucm.map.Stub;
import ucm.map.UCMmap;

/**
 * Editpart for Stubs. Adds double-click behaviour and different figures for static/dynamic/pointcut stubs.
 * 
 * Stub in/out labels are refreshed here. I must admit that the refreshing code is cryptic; was not documented during creation and is alot of trial&error.
 * 
 * @author Etienne Tremblay, jkealey, gunterm
 */
public class StubEditPart extends PathNodeEditPart {
    private PluginListDialog dlg;

    private StubFigure figure;

    /**
     * Creates a stub editpart.
     */
    public StubEditPart(PathNode model, UCMmap diagram) {
        super(model, diagram);
    }

    /**
     * Creates a StubFigure and sets its dynamic property.
     */
    protected IFigure createFigure() {
        Stub stub = (Stub) getModel();
        figure = new StubFigure();
        return figure;
    }

    /**
     * Returns a StubPropertySource
     */
    protected IPropertySource getPropertySource() {
        CommandStack cmdStack = getViewer().getEditDomain().getCommandStack();
        propertySource = new StubPropertySource((EObject) getModel(), cmdStack);
        return propertySource;
    }

    /**
     * Refreshes all incoming/outgoing connections when changes are made to connections.
     * 
     * @see seg.jUCMNav.editparts.PathNodeEditPart#notifyChanged(org.eclipse.emf.common.notify.Notification)
     */
    public void notifyChanged(Notification notification) {
        int featureId = notification.getFeatureID(UcmPackage.class);
        switch (featureId) {
        case MapPackage.STUB__PRED:
            for (Iterator iter = ((Stub) getModel()).getPred().iterator(); iter.hasNext();) {
                NodeConnection nc = (NodeConnection) iter.next();
                refreshNodeConnection(nc);
            }
            break;
        case MapPackage.STUB__SUCC:
            for (Iterator iter = ((Stub) getModel()).getSucc().iterator(); iter.hasNext();) {
                NodeConnection nc = (NodeConnection) iter.next();
                refreshNodeConnection(nc);
            }
            break;
        }

        super.notifyChanged(notification);
    }

    /**
     * What should be done when the stub is double clicked.
     * 
     * @see org.eclipse.gef.EditPart#performRequest(org.eclipse.gef.Request)
     */
    public void performRequest(Request req) {
        if (req.getType() == REQ_OPEN) {
            Stub stub = (Stub) getModel();

            Vector activeBindings = new Vector();

            EList bindings = stub.getBindings();

            for (Iterator iter = bindings.iterator(); iter.hasNext();) {
                EObject element = (EObject) iter.next();
                if (ScenarioUtils.getTraversalHitCount(element) > 0) {
                    activeBindings.add(element);
                }
            }

            if (activeBindings.size() == 0)
                activeBindings.addAll(bindings);

            if (activeBindings.size() == 1) {
                // if only one plugin, open it.
                UCMmap map = ((PluginBinding) activeBindings.get(0)).getPlugin();
                if (map != null)
                    ((UCMConnectionOnBottomRootEditPart) getRoot()).getMultiPageEditor().setActivePage(map);
            } else if (activeBindings.size() > 1) {
                // if multiple plugins, bring up selection window
                if (dlg != null) {
                    dlg.close();
                }
                dlg = new PluginListDialog(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), ((UCMConnectionOnBottomRootEditPart) getRoot())
                        .getMultiPageEditor());
                dlg.setInput(activeBindings);
                dlg.setMessage(Messages.getString("StubEditPart.selectPlugin")); //$NON-NLS-1$
                dlg.open();
            } else {
                // if none, bring up the bindings dialog.
                Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
                StubBindingsDialog d = new StubBindingsDialog(shell, ((UCMConnectionOnBottomRootEditPart) getRoot()).getMultiPageEditor()
                        .getDelegatingCommandStack());
                d.open(stub);
            }
        }
        super.performRequest(req);
    }

    /**
     * Refreshes stub labels and anything else the parent needs to refresh.
     * 
     * @return true if something to refresh was found.
     */
    protected boolean refreshDecorations() {
        boolean b = refreshStubLabels(getNodeFigure());
        // make sure we refresh everything that can be refreshed.
        b = super.refreshDecorations() || b;

        // inform if was refreshed.
        return b;

    }

    /**
     * Refreshes all the incoming/outgoing connections
     * 
     */
    public void refreshInOuts() {
        for (Iterator iter = ((Stub) getModel()).getPred().iterator(); iter.hasNext();) {
            NodeConnection nc = (NodeConnection) iter.next();
            refreshNodeConnection(nc);
        }

        for (Iterator iter = ((Stub) getModel()).getSucc().iterator(); iter.hasNext();) {
            NodeConnection nc = (NodeConnection) iter.next();
            refreshNodeConnection(nc);
        }
    }

    /**
     * Refreshes the stub labels if the stub is moved.
     * 
     * @param nodeFigure
     *            our figure
     * @return true if the labels were refreshed.
     */
    private boolean refreshStubLabels(PathNodeFigure nodeFigure) {
        boolean b = false;
        if (nodeFigure instanceof StubFigure && !needsMove(nodeFigure)) {
            for (Iterator iter = ((Stub) getModel()).getSucc().iterator(); iter.hasNext();) {
                NodeConnection nc = (NodeConnection) iter.next();
                b = refreshNodeConnection(nc, false) || b;
            }

            for (Iterator iter = ((Stub) getModel()).getPred().iterator(); iter.hasNext();) {
                NodeConnection nc = (NodeConnection) iter.next();
                b = refreshNodeConnection(nc, false) || b;
            }
        }
        return false;
    }

    /**
     * Refreshes the StubFigure
     */
    public void refreshVisuals() {
        Stub stub = (Stub) getNode();

        int pointcut = 0, aspect = 0;

        switch (stub.getAopointcut().getValue()) {
        case PointcutKind.NONE:
            pointcut = 0;
            break;
        case PointcutKind.REGULAR:
            pointcut = 1;
            break;
        case PointcutKind.REPLACEMENT:
            pointcut = 2;
            break;
        }
        
        switch (stub.getAspect().getValue()) {
        case AspectKind.NONE:
            aspect = 0;
            break;
        case AspectKind.REGULAR:
            aspect = 1;
            break;
        case AspectKind.ENTRANCE:
            aspect = 2;
            break;
        case AspectKind.EXIT:
            aspect = 3;
            break;
        case AspectKind.CONDITIONAL:
            aspect = 4;
            break;
        }

        figure.setStubType(stub.isDynamic(), pointcut, aspect, stub.isSynchronization(), stub.isBlocking(), stub.getRepetitionCount());

        super.refreshVisuals();
    }

}