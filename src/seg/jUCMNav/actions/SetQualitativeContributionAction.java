package seg.jUCMNav.actions;

import grl.Contribution;
import grl.ContributionType;
import grl.LinkRef;

import java.util.Iterator;
import java.util.Vector;

import org.eclipse.gef.commands.Command;
import org.eclipse.ui.IWorkbenchPart;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.Messages;
import seg.jUCMNav.editparts.LinkRefEditPart;
import seg.jUCMNav.model.commands.transformations.ChangeQualitativeContributionCommand;
import seg.jUCMNav.strategies.EvaluationStrategyManager;

/**
 * 
 * @author damyot
 */

public class SetQualitativeContributionAction extends URNSelectionAction {
    public static final String SET_QUALITATIVE_CONTRIBUTION = "seg.jUCMNav.SET_QUALITATIVE_CONTRIBUTION"; //$NON-NLS-1$
    private Vector linkRefs;
    private int id;
    private static String[] values = { "Make", "Some Positive", "Help", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        "Unknown", "Hurt", "Some Negative", "Break", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
        Messages.getString("SetEvaluation.Increase") + "   (y)", //$NON-NLS-1$ //$NON-NLS-2$ 
        Messages.getString("SetEvaluation.Decrease") + "   (u)" }; //$NON-NLS-1$ //$NON-NLS-2$ 

    public SetQualitativeContributionAction(IWorkbenchPart part, int id) {
        super(part);
        setId(SET_QUALITATIVE_CONTRIBUTION + id);
        this.id = id;

        if (id == 0)
            setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/Make.gif")); //$NON-NLS-1$
        else if (id == 1)
            setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/SomePositive.gif")); //$NON-NLS-1$
        else if (id == 2)
            setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/Help.gif")); //$NON-NLS-1$
        // No image for Unknown
        else if (id == 4)
            setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/Hurt.gif")); //$NON-NLS-1$
        else if (id == 5)
            setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/SomeNegative.gif")); //$NON-NLS-1$
        else if (id == 6)
            setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/Break.gif")); //$NON-NLS-1$
        else if (id == 7)
            setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/move_up.gif")); //$NON-NLS-1$
        else if (id == 8)
            setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/move_down.gif")); //$NON-NLS-1$

        setText(values[id]);
    }

    protected Command getCommand() {
        return new ChangeQualitativeContributionCommand(linkRefs, id);
    }

    /**
     * We need to have a link reference selected. For increase and decrease operations verify if possible
     */
    protected boolean calculateEnabled() {
        for (Iterator iter = getSelectedObjects().iterator(); iter.hasNext();) {
            Object obj = iter.next();
            if (!(obj instanceof LinkRefEditPart))
                return false;

            LinkRef lr = (LinkRef) (((LinkRefEditPart) obj).getModel());
            if (!(lr.getLink() instanceof Contribution))
                return false; // not a contribution

            if (id < ChangeQualitativeContributionCommand.INCREASE) // operation is not increase or decrease, skip further tests
                continue;

            //ContributionType oldQContrib = ((Contribution) lr.getLink()).getContribution();
            ContributionType oldQContrib = EvaluationStrategyManager.getInstance().getActiveContribution((Contribution)lr.getLink());

            if (id == ChangeQualitativeContributionCommand.INCREASE) { // increase operation, verify if possible
                if (oldQContrib == ContributionType.MAKE_LITERAL)
                    return false; // can't increase from MAKE
            } else if (id == ChangeQualitativeContributionCommand.DECREASE) { // decrease operation, verify if possible
                if (oldQContrib == ContributionType.BREAK_LITERAL)
                    return false; // can't decrease from BREAK
            }
        }

        linkRefs = new Vector(); // all tests passed, create list

        for (Iterator iter = getSelectedObjects().iterator(); iter.hasNext();) {
            LinkRef lr = (LinkRef) (((LinkRefEditPart) iter.next()).getModel());
            linkRefs.add(lr);
        }

        return true;
    }

    public static String generateId(int id) {
        return SET_QUALITATIVE_CONTRIBUTION + id;
    }

    public static String getId(String operation) {
        for (int index = 0; index < values.length; index++) {
            if (values[index].contains(operation))
                return SET_QUALITATIVE_CONTRIBUTION + index;
        }
        return null;
    }

}
