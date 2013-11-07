/**
 * 
 */
package seg.jUCMNav.editparts;

import java.util.Iterator;

import org.eclipse.draw2d.ConnectionLayer;
import org.eclipse.draw2d.LayeredPane;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.gef.AutoexposeHelper;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.editparts.ScalableFreeformRootEditPart;
import org.eclipse.gef.editparts.ViewportAutoexposeHelper;

import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.figures.router.UCMConnectionRouter;
import seg.jUCMNav.scenarios.ScenarioUtils;
import seg.jUCMNav.strategies.EvaluationStrategyManager;
import ucm.map.NodeConnection;
import ucm.map.UCMmap;

/**
 * Root edit part of any jUCMNav editor. This class is used to manage all editors using the same EditPart
 * 
 * @author Jean-François Roy
 * 
 */
public abstract class URNRootEditPart extends ScalableFreeformRootEditPart {

    // Used to simplify some stub binding code.
    private UCMNavMultiPageEditor multiPageEditor;

    protected boolean strategyView;
    protected boolean scenarioView;

    public static final String COMPONENT_LAYER = "COMPONENT"; //$NON-NLS-1$

    // what is the current view mode for this editor.
    protected int mode = 0;

    /**
     * 
     * @param editor
     *            the multi page editor
     */
    public URNRootEditPart(UCMNavMultiPageEditor editor) {
        super();
        double zoomLevels[] = { 0.1, 0.25, .5, .75, 1.0, 1.5, 2.0, 2.5, 3, 4 };
        getZoomManager().setZoomLevels(zoomLevels);
        
        multiPageEditor = editor;
        if (EvaluationStrategyManager.getInstance().getEvaluationStrategy() != null) {
            strategyView = true;
        } else {
            strategyView = false;
        }

        scenarioView = ScenarioUtils.getActiveScenario(editor.getModel()) != null;
    }

    public Object getAdapter(Class adapter) {
        if (adapter == AutoexposeHelper.class /* || adapter == ExposeHelper.class */)
            return new ViewportAutoexposeHelper(this, new Insets(50, 50, 50, 50));
        return super.getAdapter(adapter);
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

    public void setMultiPageEditor(UCMNavMultiPageEditor multiPageEditor) {
        this.multiPageEditor = multiPageEditor;
    }

    public abstract void setMode(int mode);

    public boolean isStrategyView() {
        return strategyView;
    }

    public boolean isScenarioView() {
        return scenarioView;
    }

    public void setStrategyView(boolean view) {
        strategyView = view;
        for (Iterator iter = getChildren().iterator(); iter.hasNext();) {
            URNDiagramEditPart element = (URNDiagramEditPart) iter.next();
            if (element instanceof GrlGraphEditPart) {
                element.refreshVisuals();
            }
        }
    }

    public void setScenarioView(boolean view) {
        scenarioView = view;
        for (Iterator iter = getChildren().iterator(); iter.hasNext();) {
            URNDiagramEditPart element = (URNDiagramEditPart) iter.next();
            if (element instanceof UCMMapEditPart) {
                UCMMapEditPart map = (UCMMapEditPart) element;
                element.refreshVisuals();

                ConnectionLayer cLayer = (ConnectionLayer) getLayer(LayerConstants.CONNECTION_LAYER);

                if (cLayer.getConnectionRouter() instanceof UCMConnectionRouter) {
                    ((UCMConnectionRouter) cLayer.getConnectionRouter()).refreshConnections();

                    PathNodeEditPart child = null;

                    for (Iterator iterator = map.getChildren().iterator(); iterator.hasNext();) {
                        EditPart part = (EditPart) iterator.next();

                        if (part instanceof PathNodeEditPart) {
                            child = (PathNodeEditPart) part;
                            break;
                        }
                    }

                    // using a method already in the code to refresh all node connections.
                    if (child != null) {
                        for (Iterator iterator = ((UCMmap) map.getModel()).getConnections().iterator(); iterator.hasNext();) {
                            NodeConnection nc = (NodeConnection) iterator.next();
                            child.refreshNodeConnection(nc);
                        }
                    }
                }
            }
        }
    }

    public void refreshChildren() {
        for (Iterator iter = getChildren().iterator(); iter.hasNext();) {
            URNDiagramEditPart element = (URNDiagramEditPart) iter.next();
            element.refreshVisuals();
        }
    }
}
