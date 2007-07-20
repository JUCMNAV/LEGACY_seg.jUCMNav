package seg.jUCMNav.views.dnd;

import grl.Actor;
import grl.ActorRef;
import grl.IntentionalElement;
import grl.IntentionalElementRef;
import grl.kpimodel.KPIInformationElement;
import grl.kpimodel.KPIInformationElementRef;

import org.eclipse.gef.dnd.TemplateTransferDropTargetListener;
import org.eclipse.gef.requests.CreationFactory;

import seg.jUCMNav.editors.UrnEditor;
import seg.jUCMNav.model.ModelCreationFactory;
import ucm.map.ComponentRef;
import ucm.map.RespRef;
import urncore.Component;
import urncore.Responsibility;

/**
 * Drag target setup on the UcmEditor and GrlEditor.
 * 
 * @author jkealey, pchen
 */
public class UrnTemplateTransferDropTargetListener extends TemplateTransferDropTargetListener {

    protected UrnEditor editor;
    private Object lastTemplate;
    private CreationFactory lastFactory;

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
        this.editor = editor;
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

            lastFactory = new ModelCreationFactory(editor.getModel().getUrndefinition().getUrnspec(), ComponentRef.class, ((Component) definition).getKind()
                    .getValue(), definition);
        }

        if (template instanceof Responsibility || template instanceof RespRef) {
            Object definition = template;

            if (definition instanceof RespRef)
                definition = ((RespRef) definition).getRespDef();

            lastFactory = new ModelCreationFactory(editor.getModel().getUrndefinition().getUrnspec(), RespRef.class, definition);
        }

        if (template instanceof Actor || template instanceof ActorRef) {
            Object definition = template;

            if (definition instanceof ActorRef)
                definition = ((ActorRef) definition).getContDef();

            lastFactory = new ModelCreationFactory(editor.getModel().getUrndefinition().getUrnspec(), ActorRef.class, definition);
        }

        if (template instanceof IntentionalElement || template instanceof IntentionalElementRef) {
            Object definition = template;

            if (definition instanceof IntentionalElementRef)
                definition = ((IntentionalElementRef) definition).getDef();

            lastFactory = new ModelCreationFactory(editor.getModel().getUrndefinition().getUrnspec(), IntentionalElementRef.class,
                    ((IntentionalElement) definition).getType().getValue(), definition);

        }

        if (template instanceof KPIInformationElement || template instanceof KPIInformationElementRef) {
            Object definition = template;

            if (definition instanceof KPIInformationElementRef)
                definition = ((KPIInformationElementRef) definition).getDef();

            lastFactory = new ModelCreationFactory(editor.getModel().getUrndefinition().getUrnspec(), KPIInformationElementRef.class, definition);

        }

        return lastFactory;
    }

}
