package seg.jUCMNav.model.commands.helpers;

import java.util.Collection;

import org.eclipse.emf.common.command.Command;

import fm.FeatureModel;
import grl.ImpactModel;
import seg.jUCMNav.Messages;
import urncore.Concern;
import ca.mcgill.sel.core.COREConcern;
import ca.mcgill.sel.core.COREFeatureModel;
import ca.mcgill.sel.core.COREImpactModel;
import ca.mcgill.sel.core.COREModel;

public class UpdateConcernCommandHelper implements Command {

    
    // the concern to be changed
    private Concern concern;
    // the new info
    private String name;
    private String description;
    // undo information
    private String oldName;
    private String oldDescription;
    private boolean updateJucmCoreConcern;

    /**
     * @param concern
     *            to be changed
     * @param name
     *            of the concern
     * @param description
     *            of the concern
     */
    public UpdateConcernCommandHelper(Concern concern, String name, String description) {
        this.concern = concern;
        this.name = name;
        this.description = description;
        this.updateJucmCoreConcern = false;
     //   setLabel(Messages.getString("UpdateConcernCommand.UpdateConcern")); //$NON-NLS-1$
    }
    
    /**
     * @param concern
     *            to be changed
     * @param name
     *            of the concern
     * @param description
     *            of the concern
     */
    public UpdateConcernCommandHelper(Concern concern, boolean updateJucmCoreConcern) {
        this.concern = concern;
        this.name = concern.getName();
        this.description = concern.getDescription();
        this.updateJucmCoreConcern = updateJucmCoreConcern;
     //   setLabel(Messages.getString("UpdateConcernCommand.UpdateConcern")); //$NON-NLS-1$
    }

    /**
     * checks all conditions of testPreConditions that can be checked before execute()
     * 
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() {
        return testConditionNotNull();
    }

    /**
     * @see org.eclipse.gef.commands.Command#execute()
     */
    public void execute() {
        // remember for undo
        oldName = concern.getName();
        oldDescription = concern.getDescription();
        redo();
    }

    /**
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() {
        testPreConditions();
        concern.setName(name);
        concern.setDescription(description);
        
        if( updateJucmCoreConcern){
            // update COREConcern of current.jucm file.
            if( concern.getCoreConcern() != null){
                COREConcern coreConcern = concern.getCoreConcern();
                coreConcern.setName(name);
                if( coreConcern.getModels() != null && coreConcern.getModels().size() > 0){
                    COREModel featureModelToRemove = null;
                    COREModel impactModelToRemove = null;
                    
                    for ( COREModel model : coreConcern.getModels()){
                        if( model instanceof FeatureModel){
                            featureModelToRemove = (COREFeatureModel)model;
                        }else if ( model instanceof ImpactModel){
                            impactModelToRemove = (COREImpactModel)model;
                        }
                    }
                    // remove the old models from list
                    if( featureModelToRemove != null)
                        coreConcern.getModels().remove(featureModelToRemove);
                    if( impactModelToRemove != null)
                        coreConcern.getModels().remove(impactModelToRemove);
                    // update list with up to date models
                    if ( concern.getUrndefinition().getUrnspec().getGrlspec().getFeatureModel() != null )
                            coreConcern.getModels().add(concern.getUrndefinition().getUrnspec().getGrlspec().getFeatureModel());
                    if (concern.getUrndefinition().getUrnspec().getGrlspec().getImpactModel() != null)
                        coreConcern.getModels().add(concern.getUrndefinition().getUrnspec().getGrlspec().getImpactModel());
                    }
                }
        }
        
        testPostConditions();
    }

    /**
     * @see org.eclipse.gef.commands.Command#undo()
     */
    public void undo() {
        testPostConditions();
        concern.setName(oldName);
        concern.setDescription(oldDescription);
        testPreConditions();
    }

    /**
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() {
        assert concern != null && concern.getName() != null && concern.getDescription() != null : "post not null"; //$NON-NLS-1$
        assert concern.getName().equals(name) && concern.getDescription().equals(description) : "post concern name/description changed"; //$NON-NLS-1$
    }

    /**
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() {
        assert testConditionNotNull() : "pre not null"; //$NON-NLS-1$
        assert concern.getName().equals(oldName) && concern.getDescription().equals(oldDescription) : "pre concern name/description are original"; //$NON-NLS-1$
    }

    /**
     * @return true if condition is met, false otherwise
     */
    private boolean testConditionNotNull() {
        return concern != null && name != null && description != null;
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
