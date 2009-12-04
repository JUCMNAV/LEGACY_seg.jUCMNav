package seg.jUCMNav.model.commands.transformations;

import grl.IntentionalElementRef;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import urncore.Comment;
import urncore.IURNContainerRef;

/**
 * Changes an element's color
 * 
 * @author jkealey, damyot
 */
public class ChangeColorCommand extends Command implements JUCMNavCommand {
    private EObject element;
    private String newColor, oldColor;
    private boolean isFilled;

    public ChangeColorCommand(EObject obj, String newColor) {
        this.element = obj;
        this.newColor = newColor;

        setLabel(Messages.getString("ChangeColorCommand.ChangeElementColor")); //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        if (element instanceof IURNContainerRef) {
            IURNContainerRef containerRef = (IURNContainerRef) element;
            if (containerRef.getContDef() != null) {
                oldColor = containerRef.getContDef().getFillColor();
                isFilled = containerRef.getContDef().isFilled();
            }
        } else if (element instanceof IntentionalElementRef) {
            IntentionalElementRef ieRef = (IntentionalElementRef) element;
            if (ieRef.getDef() != null) {
                oldColor = ieRef.getDef().getFillColor();
                isFilled = ieRef.getDef().isFilled();
            }
        } else if (element instanceof Comment) {
            Comment c = (Comment) element;
            oldColor = c.getFillColor();
        }
        redo();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();

        if (element instanceof IURNContainerRef) {
            IURNContainerRef containerRef = (IURNContainerRef) element;
            if (containerRef.getContDef() != null) {
                containerRef.getContDef().setFillColor(newColor);
                containerRef.getContDef().setFilled(newColor != null);
            }
        } else if (element instanceof IntentionalElementRef) {
            IntentionalElementRef ieRef = (IntentionalElementRef) element;
            if (ieRef.getDef() != null) {
                ieRef.getDef().setFillColor(newColor);
                ieRef.getDef().setFilled(newColor != null);
            }
        } else if (element instanceof Comment) {
            Comment c = (Comment) element;
            c.setFillColor(newColor);
        }

        testPostConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert element != null : "post no elem to change description!"; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert element != null : "pre no elem to change description"; //$NON-NLS-1$

    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();

        if (element instanceof IURNContainerRef) {
            IURNContainerRef containerRef = (IURNContainerRef) element;
            if (containerRef.getContDef() != null) {
                containerRef.getContDef().setFillColor(oldColor);
                containerRef.getContDef().setFilled(isFilled);
            }
        } else if (element instanceof IntentionalElementRef) {
            IntentionalElementRef ieRef = (IntentionalElementRef) element;
            if (ieRef.getDef() != null) {
                ieRef.getDef().setFillColor(oldColor);
                ieRef.getDef().setFilled(isFilled);
            }
        } else if (element instanceof Comment) {
            Comment c = (Comment) element;
            c.setFillColor(oldColor);
        }

        testPreConditions();
    }

    public EObject getElement() {
        return element;
    }

    public void setElement(EObject element) {
        this.element = element;
    }

    public String getNewColor() {
        return newColor;
    }

    public void setNewColor(String newColor) {
        this.newColor = newColor;
    }

    public String getOldColor() {
        return oldColor;
    }

    public void setOldColor(String oldColor) {
        this.oldColor = oldColor;
    }

}