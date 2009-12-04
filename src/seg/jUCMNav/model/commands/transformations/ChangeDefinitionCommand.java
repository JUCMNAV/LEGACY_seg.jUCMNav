package seg.jUCMNav.model.commands.transformations;

import grl.ActorRef;
import grl.GRLNode;
import grl.IntentionalElement;
import grl.IntentionalElementRef;
import grl.kpimodel.KPIInformationElement;
import grl.kpimodel.KPIInformationElementRef;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import seg.jUCMNav.model.util.URNElementFinder;
import ucm.map.RespRef;
import urn.URNspec;
import urncore.ComponentLabel;
import urncore.IURNContainer;
import urncore.IURNContainerRef;
import urncore.Label;
import urncore.NodeLabel;
import urncore.Responsibility;

/**
 * Replaces the definition to which a RespRef or ComponentRef is associated to.
 * 
 * @author jkealey
 */
public class ChangeDefinitionCommand extends Command implements JUCMNavCommand {
    private EObject elem;
    private EObject oldDef, newDef;
    private URNspec urn;

    private Label lbl;
    private String name;

    public ChangeDefinitionCommand(URNspec urn, Label lbl, String newName) {
        this.lbl = lbl;
        this.name = newName;
        this.urn = urn;
        setLabel(Messages.getString("ChangeDefinitionCommand.ReplaceDefinition")); //$NON-NLS-1$
    }

    public ChangeDefinitionCommand(URNspec urn, GRLNode node, String newName) {
        this.elem = node;
        this.name = newName;
        this.urn = urn;
        setLabel(Messages.getString("ChangeDefinitionCommand.ReplaceDefinition")); //$NON-NLS-1$
    }

    /**
     * @return whether we can apply changes
     */
    public boolean canExecute() {
        if ((this.elem != null || lbl != null) && name != null && urn != null)
            return (name.compareTo("") != 0); //$NON-NLS-1$
        else
            return false;
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {

        if (this.elem == null) {
            if (lbl instanceof ComponentLabel) {
                this.elem = ((ComponentLabel) lbl).getContRef();
                this.oldDef = ((IURNContainerRef) this.elem).getContDef();
                if (((ComponentLabel) lbl).getContRef() instanceof ActorRef)
                    this.newDef = URNElementFinder.findActorByName(urn, name);
                else
                    this.newDef = URNElementFinder.findComponentByName(urn, name);

            } else if (lbl instanceof NodeLabel && ((NodeLabel) lbl).getNode() instanceof RespRef) {
                this.elem = ((NodeLabel) lbl).getNode();
                this.oldDef = ((RespRef) this.elem).getRespDef();
                this.newDef = URNElementFinder.findResponsibilityByName(urn, name);
            }
        } else {
            if (elem instanceof IntentionalElementRef) {
                this.oldDef = (((IntentionalElementRef) elem).getDef());
                this.newDef = URNElementFinder.findIntentionalElementByName(urn, name);
            } else if (elem instanceof KPIInformationElementRef) {
                this.oldDef = (((KPIInformationElementRef) elem).getDef());
                this.newDef = URNElementFinder.findKPIInformationElementByName(urn, name);
            }
        }
        redo();
    }

    public EObject getOldDef() {
        return oldDef;
    }

    public EObject getNewDef() {
        return newDef;
    }

    public Label getTargetLabel() {
        return lbl;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();

        if (elem instanceof RespRef) {
            RespRef respRef = (RespRef) elem;
            respRef.setRespDef((Responsibility) newDef);
        } else if (elem instanceof IURNContainerRef) {
            IURNContainerRef containerRef = (IURNContainerRef) elem;
            containerRef.setContDef((IURNContainer) newDef);
        } else if (elem instanceof IntentionalElementRef) {
            IntentionalElementRef intentionalElementRef = (IntentionalElementRef) elem;
            intentionalElementRef.setDef((IntentionalElement) newDef);
        } else if (elem instanceof KPIInformationElementRef) {
            KPIInformationElementRef informationElementRef = (KPIInformationElementRef) elem;
            informationElementRef.setDef((KPIInformationElement) newDef);
        }

        testPostConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert oldDef != null && newDef != null : "post old and new def valid"; //$NON-NLS-1$
        assert elem != null : "post no elemement to name!"; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert oldDef != null && newDef != null : "pre old and new def valid"; //$NON-NLS-1$
        assert elem != null : "pre no elemement to name!"; //$NON-NLS-1$

    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();
        if (elem instanceof RespRef) {
            RespRef respRef = (RespRef) elem;
            respRef.setRespDef((Responsibility) oldDef);
        } else if (elem instanceof IURNContainerRef) {
            IURNContainerRef containerRef = (IURNContainerRef) elem;
            containerRef.setContDef((IURNContainer) oldDef);
        } else if (elem instanceof IntentionalElementRef) {
            IntentionalElementRef intentionalElementRef = (IntentionalElementRef) elem;
            intentionalElementRef.setDef((IntentionalElement) oldDef);
        } else if (elem instanceof KPIInformationElementRef) {
            KPIInformationElementRef informationElementRef = (KPIInformationElementRef) elem;
            informationElementRef.setDef((KPIInformationElement) oldDef);
        }

        testPreConditions();
    }

}