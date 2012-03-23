package seg.jUCMNav.views.dnd;

import grl.Actor;
import grl.ActorRef;
import grl.ContributionContext;
import grl.EvaluationStrategy;
import grl.IntentionalElement;
import grl.IntentionalElementRef;
import grl.kpimodel.KPIInformationElement;
import grl.kpimodel.KPIInformationElementRef;

import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.dnd.TemplateTransferDropTargetListener;
import org.eclipse.gef.requests.CreationFactory;

import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.editors.UrnEditor;
import seg.jUCMNav.editparts.strategyTreeEditparts.StrategyRootEditPart;
import seg.jUCMNav.model.ModelCreationFactory;
import ucm.map.ComponentRef;
import ucm.map.RespRef;
import ucm.scenario.ScenarioDef;
import urn.URNspec;
import urncore.Component;
import urncore.Responsibility;

/**
 * Drag target setup on the UcmEditor and GrlEditor.
 * 
 * @author jkealey, pchen
 */
public class UrnTemplateTransferDropTargetListener extends TemplateTransferDropTargetListener {

    // leak bug 631.
    // protected UrnEditor editor;
    private Object lastTemplate;
    private CreationFactory lastFactory;
    private URNspec urn;

    /**
     * Creates a listener for drop events of type UrnTemplateTransfer.
     * 
     * @param editor
     *            The editor with a reference to the URNspec to be passed on to the ModelCreationFactory instance that is created by getFactory)
     */
    public UrnTemplateTransferDropTargetListener(UrnEditor editor) {
        super(editor.getGraphicalViewer());
        setEnablementDeterminedByCommand(true);
        setTransfer(UrnTemplateTransfer.getInstance());
        // this.editor = editor;
        this.urn = editor.getModel().getUrndefinition().getUrnspec();
    }
    
    public UrnTemplateTransferDropTargetListener(EditPartViewer viewer, URNspec urn) 
    {
        super(viewer);
        setEnablementDeterminedByCommand(true);
        setTransfer(UrnTemplateTransfer.getInstance());
        // this.editor = editor;
        this.urn = urn;

    }

    /**
     * Given a template, return a proper instance of ModelCreationFactory.
     * 
     * @see org.eclipse.gef.dnd.TemplateTransferDropTargetListener#getFactory(java.lang.Object)
     */
    protected CreationFactory getFactory(Object template) {
        // return new SimpleFactory((Class) template);
        if (lastTemplate == template)
            return lastFactory;
        else {
            lastTemplate = template;
            lastFactory = null;
        }

        if (template instanceof ComponentRef || template instanceof Component) {
            Object definition = template;

            if (definition instanceof ComponentRef)
                definition = ((ComponentRef) definition).getContDef();

            lastFactory = new ModelCreationFactory(urn, ComponentRef.class, ((Component) definition).getKind().getValue(), definition);
        }

        if (template instanceof Responsibility || template instanceof RespRef) {
            Object definition = template;

            if (definition instanceof RespRef)
                definition = ((RespRef) definition).getRespDef();

            lastFactory = new ModelCreationFactory(urn, RespRef.class, definition);
        }

        if (template instanceof Actor || template instanceof ActorRef) {
            Object definition = template;

            if (definition instanceof ActorRef)
                definition = ((ActorRef) definition).getContDef();

            lastFactory = new ModelCreationFactory(urn, ActorRef.class, definition);
        }

        if (template instanceof IntentionalElement || template instanceof IntentionalElementRef) {
            Object definition = template;

            if (definition instanceof IntentionalElementRef)
                definition = ((IntentionalElementRef) definition).getDef();

            lastFactory = new ModelCreationFactory(urn, IntentionalElementRef.class, ((IntentionalElement) definition).getType().getValue(), definition);

        }

        if (template instanceof KPIInformationElement || template instanceof KPIInformationElementRef) {
            Object definition = template;

            if (definition instanceof KPIInformationElementRef)
                definition = ((KPIInformationElementRef) definition).getDef();

            lastFactory = new ModelCreationFactory(urn, KPIInformationElementRef.class, definition);

        }

        // scenario tree view is a tad different. 
        if (template instanceof ScenarioDef || template instanceof EvaluationStrategy || template instanceof ContributionContext) {
            URNspec urn2 = urn;
            
            if (urn2 == null) {
                StrategyRootEditPart part = (StrategyRootEditPart)(getViewer().getContents());
                if (part!=null && part.getModel() instanceof UCMNavMultiPageEditor)
                    urn2 = ((UCMNavMultiPageEditor) part.getModel()).getModel();
            }
            
            if (template instanceof EvaluationStrategy) {
                lastFactory = new ModelCreationFactory(urn2, EvaluationStrategy.class, template);
            }
            
            if (template instanceof ScenarioDef) {
                lastFactory = new ModelCreationFactory(urn2, ScenarioDef.class, template);
            }
            
            if (template instanceof ContributionContext) {
                lastFactory = new ModelCreationFactory(urn2, ContributionContext.class, template);
            }
        }
        return lastFactory;
    }

}
