package seg.jUCMNav.model.commands.helpers;

import java.util.Collection;

import org.eclipse.emf.common.command.Command;

import grl.Dependency;
import grl.IntentionalElement;
import seg.jUCMNav.Messages;
import seg.jUCMNav.strategies.EvaluationStrategyManager;
import urn.URNspec;

public class AddDependencyElementLinkCommandHelper implements Command {

    private IntentionalElement depender, dependee;
    private URNspec urnspec;
    private Dependency link;
    private String position;
    
    /**
     * 
     */
    public AddDependencyElementLinkCommandHelper(URNspec urn, IntentionalElement dependee, Dependency link, String position) {
        
        this.position = position;
        this.urnspec = urn;
        this.link = link;
        this.dependee = dependee;

//  /      setLabel(Messages.getString("AddDependencyElementLinkCommand.addDependency")); //$NON-NLS-1$
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        // disallow source -> source connections
        if (dependee.equals(depender)) {
            return false;
        }
        return true;
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        redo();
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();

        // Set the source and destination

        depender.getLinksSrc().add(link);
       
        if( position != null ){
            dependee.getLinksDest().add(Integer.valueOf(position), link);
        }else{
            dependee.getLinksDest().add(link);
        }
        
        urnspec.getGrlspec().getLinks().add(link);

        EvaluationStrategyManager.getInstance().calculateEvaluation();

        testPostConditions();
    }

    /**
     * Set the target endpoint for the connection.
     * 
     * @param target
     *            that target endpoint (a non-null IntentionalElement instance)
     */
    public void setTarget(IntentionalElement target) {
        this.depender = target;
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert link != null : "pre link"; //$NON-NLS-1$
        assert urnspec != null : "pre urn spec"; //$NON-NLS-1$
        assert dependee != null : "pre dependee"; //$NON-NLS-1$
        assert depender != null : "pre depender"; //$NON-NLS-1$

        assert !urnspec.getGrlspec().getLinks().contains(link) : "pre link in spec"; //$NON-NLS-1$
        assert !depender.getLinksSrc().contains(link) : "pre link in source"; //$NON-NLS-1$
        assert !dependee.getLinksDest().contains(link) : "pre link in destination"; //$NON-NLS-1$

    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert link != null : "post link"; //$NON-NLS-1$
        assert urnspec != null : "post urn spec"; //$NON-NLS-1$
        assert dependee != null : "post dependee"; //$NON-NLS-1$
        assert depender != null : "post depender"; //$NON-NLS-1$

        assert urnspec.getGrlspec().getLinks().contains(link) : "post link in spec"; //$NON-NLS-1$
        assert depender.getLinksSrc().contains(link) : "post link in source"; //$NON-NLS-1$
        assert dependee.getLinksDest().contains(link) : "post link in destination"; //$NON-NLS-1$
    }

    /**
     * 
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();

        // remove the source
        depender.getLinksSrc().remove(link);
        dependee.getLinksDest().remove(link);

        urnspec.getGrlspec().getLinks().remove(link);

        EvaluationStrategyManager.getInstance().calculateEvaluation();

        testPreConditions();
    }

    @Override
    public boolean canUndo() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Collection<?> getResult() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection<?> getAffectedObjects() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getLabel() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getDescription() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Command chain(Command command) {
        // TODO Auto-generated method stub
        return null;
    }
}
