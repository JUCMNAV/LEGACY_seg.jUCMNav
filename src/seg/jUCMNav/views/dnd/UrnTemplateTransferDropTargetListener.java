package seg.jUCMNav.views.dnd;

import grl.Actor;
import grl.ActorRef;
import grl.IntentionalElement;
import grl.IntentionalElementRef;

import org.eclipse.gef.dnd.TemplateTransferDropTargetListener;
import org.eclipse.gef.requests.CreationFactory;

import seg.jUCMNav.editors.UrnEditor;
import seg.jUCMNav.model.ModelCreationFactory;
import ucm.map.ComponentRef;
import ucm.map.RespRef;
import urncore.Component;
import urncore.Responsibility;

public class UrnTemplateTransferDropTargetListener extends TemplateTransferDropTargetListener {

    protected UrnEditor editor;

    public UrnTemplateTransferDropTargetListener(UrnEditor editor) {
        super(editor.getGraphicalViewer());
        setEnablementDeterminedByCommand(true);
        setTransfer(UrnTemplateTransfer.getInstance());
        this.editor = editor;
    }

    protected CreationFactory getFactory(Object template) {
        // return new SimpleFactory((Class) template);

        if (template instanceof ComponentRef || template instanceof Component) {
            Object definition = template;

            if (definition instanceof ComponentRef)
                definition = ((ComponentRef) definition).getContDef();

            return new ModelCreationFactory(editor.getModel().getUrndefinition().getUrnspec(), ComponentRef.class, definition);
        }

        if (template instanceof Responsibility || template instanceof RespRef) {
            Object definition = template;

            if (definition instanceof RespRef)
                definition = ((RespRef) definition).getRespDef();

            return new ModelCreationFactory(editor.getModel().getUrndefinition().getUrnspec(), RespRef.class, definition);
        }

        if (template instanceof Actor || template instanceof ActorRef) {
            Object definition = template;

            if (definition instanceof ActorRef)
                definition = ((ActorRef) definition).getContDef();

            return new ModelCreationFactory(editor.getModel().getUrndefinition().getUrnspec(), ActorRef.class, definition);
        }

        if (template instanceof IntentionalElement || template instanceof IntentionalElementRef) 
        {
            Object definition = template;

            if (definition instanceof IntentionalElementRef)
                definition = ((IntentionalElementRef) definition).getDef();
            
            return new ModelCreationFactory(editor.getModel().getUrndefinition().getUrnspec(), IntentionalElementRef.class, ((IntentionalElement)definition).getType().getValue(), definition);
            
        }

        return null;
    }

}
