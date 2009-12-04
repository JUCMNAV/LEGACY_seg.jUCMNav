package seg.jUCMNav.model.commands.metadata;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.actions.hyperlinks.HyperlinkUtils;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import seg.jUCMNav.model.util.MetadataHelper;
import urn.URNspec;
import urncore.URNmodelElement;

/**
 * Changes the hyperlink metadata associated with a URN model element.
 * 
 * @author damyot
 */
public class ChangeHyperlinkCommand extends Command implements JUCMNavCommand {
    private URNspec urnspec;
    private URNmodelElement urnelem;
    private String oldHyperlink;
    private String newHyperlink;

    public ChangeHyperlinkCommand(URNspec spec, EObject obj, String hyperlink) {
        urnspec = spec;
        if (obj instanceof URNmodelElement) {
            this.urnelem = (URNmodelElement) obj;
            this.newHyperlink = hyperlink;
            setLabel(Messages.getString("ActionRegistryManager.ChangeHyperlinkAction")); //$NON-NLS-1$
        }
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        oldHyperlink = MetadataHelper.getMetaData(urnelem, HyperlinkUtils.HYPERLINK);
        redo();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();

        if (newHyperlink != null)
            MetadataHelper.addMetaData(urnspec, urnelem, HyperlinkUtils.HYPERLINK, newHyperlink);
        else
            MetadataHelper.removeMetaData(urnelem, HyperlinkUtils.HYPERLINK);

        testPostConditions();
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert urnelem != null : "post no elemement to modify!"; //$NON-NLS-1$
        assert urnspec != null : "post no URN specification to modify!"; //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert urnelem != null : "post no elemement to modify!"; //$NON-NLS-1$
        assert urnspec != null : "post no URN specification to modify!"; //$NON-NLS-1$
    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();

        if (oldHyperlink != null)
            MetadataHelper.addMetaData(urnspec, urnelem, HyperlinkUtils.HYPERLINK, oldHyperlink);
        else
            MetadataHelper.removeMetaData(urnelem, HyperlinkUtils.HYPERLINK);

        testPreConditions();
    }
}
