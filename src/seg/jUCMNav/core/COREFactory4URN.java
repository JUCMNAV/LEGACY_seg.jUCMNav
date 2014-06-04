package seg.jUCMNav.core;

import java.util.Iterator;

import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.concerns.AssignConcernDiagramCommand;
import seg.jUCMNav.model.commands.concerns.InternalCreateConcernCommand;
import seg.jUCMNav.model.commands.concerns.UpdateConcernCommand;
import seg.jUCMNav.model.commands.create.AddIntentionalElementRefCommand;
import seg.jUCMNav.model.commands.create.CreateFMDCommand;
import seg.jUCMNav.model.commands.create.CreateGrlGraphCommand;
import seg.jUCMNav.model.commands.transformations.ChangeGrlNodeNameCommand;
import urn.URNspec;
import urncore.Concern;
import urncore.IURNDiagram;
import ca.mcgill.sel.core.COREConcern;
import ca.mcgill.sel.core.COREFeatureModel;
import ca.mcgill.sel.core.COREImpactModel;
import ca.mcgill.sel.core.COREModel;
import ca.mcgill.sel.core.CoreFactory;
import ca.mcgill.sel.core.util.AbstractConcernFactory;
import fm.FeatureModel;
import grl.GRLGraph;
import grl.IntentionalElementRef;

public class COREFactory4URN extends AbstractConcernFactory {
	// the general idea for the CORE interface implementation is to use already existing commands as much as possible to
	// leverage existing checks for well-formedness constraints etc.
	
	public static String AUTHOR_NAME = "CORE";
	public static boolean POSITIVE_RANGE = false;
	
	@Override
	protected COREFeatureModel createFeatureModel(COREConcern cc) {
		// consistency checks: correct parameter, feature model does not exist, does impact model exist?
		if (cc == null)
			return null;
		GRLGraph im = null;		
		Iterator<COREModel> it = cc.getModels().iterator();
		while (it.hasNext()) {
			COREModel model = it.next();
			if (model instanceof COREFeatureModel && model instanceof FeatureModel)
				return null;
			// need to check that model is not a FeatureModel because any FeatureModel is also a GRLGraph/COREImpactModel
			if (model instanceof COREImpactModel && model instanceof GRLGraph && !(model instanceof FeatureModel))
				im = (GRLGraph) model;			
		}
		
		// if impact model already exists, add feature model to the existing URN model
		URNspec urn = null;
		FeatureModel fm = null;		
		if (im != null) {
			// use existing urn model to add feature model
			urn = im.getUrndefinition().getUrnspec();
			CreateFMDCommand cfCmd = new CreateFMDCommand(urn);
			if (cfCmd.canExecute())
				cfCmd.execute();
			else
				return null;
			fm = cfCmd.getDiagram();
		}
		else {
			// creates the urn model with one feature model
			urn = ModelCreationFactory.getNewURNspec(false, false, true);
			// get the created feature model
			Iterator it2 = urn.getUrndef().getSpecDiagrams().iterator();
			while (it2.hasNext()) {
				IURNDiagram diagram = (IURNDiagram) it2.next();
				if (diagram instanceof FeatureModel)
					fm = (FeatureModel) diagram;
			}
			if (fm == null)
				return null;
		}
		
		// create root feature with the same name as concern and add to feature model
		IntentionalElementRef ref = (IntentionalElementRef) ModelCreationFactory.getNewObject(urn, IntentionalElementRef.class, ModelCreationFactory.FEATURE);
        AddIntentionalElementRefCommand aierCmd = new AddIntentionalElementRefCommand(fm, ref);
        if (aierCmd.canExecute())
        	aierCmd.execute();
        else
        	return null;
        ChangeGrlNodeNameCommand cgnnCmd = new ChangeGrlNodeNameCommand(ref, cc.getName());
        if (cgnnCmd.canExecute())
        	cgnnCmd.execute();
        else
        	return null;
        
        Concern concern = createURNConcern(cc, urn, fm);
        if (concern == null)
        	return null;
		
		// return feature model
		return (COREFeatureModel) fm;
	}

	@Override
	protected COREImpactModel createImpactModel(COREConcern cc) {
		// consistency checks: correct parameter, impact model does not exist, does feature model exist?
		if (cc == null)
			return null;
		FeatureModel fm = null;		
		Iterator<COREModel> it = cc.getModels().iterator();
		while (it.hasNext()) {
			COREModel model = it.next();
			if (model instanceof COREFeatureModel && model instanceof FeatureModel)
				fm = (FeatureModel) model;
			// need to check that model is not a FeatureModel because any FeatureModel is also a GRLGraph/COREImpactModel
			if (model instanceof COREImpactModel && model instanceof GRLGraph && !(model instanceof FeatureModel))
				return null;			
		}
		
		// if feature model already exists, add impact model to the existing URN model
		URNspec urn = null;
		GRLGraph im = null;		
		if (fm != null) {
			// use existing urn model to add feature model
			urn = fm.getUrndefinition().getUrnspec();
			CreateGrlGraphCommand cggCmd = new CreateGrlGraphCommand(urn);
			if (cggCmd.canExecute())
				cggCmd.execute();
			else
				return null;
			im = cggCmd.getDiagram();
		}
		else {
			// creates the urn model with one impact model
			urn = ModelCreationFactory.getNewURNspec(false, true, false);
			// get the created impact model
			Iterator it2 = urn.getUrndef().getSpecDiagrams().iterator();
			while (it2.hasNext()) {
				IURNDiagram diagram = (IURNDiagram) it2.next();
				// need to check that model is not a FeatureModel because any FeatureModel is also a GRLGraph
				if (diagram instanceof GRLGraph && !(diagram instanceof FeatureModel))
					im = (GRLGraph) diagram;
			}
			if (im == null)
				return null;
		}
		
        Concern concern = createURNConcern(cc, urn, im);
        if (concern == null)
        	return null;
        
		// return impact model
		return (COREImpactModel) im;
	}

	private Concern createURNConcern(COREConcern cc, URNspec urn, GRLGraph model) {
		// does concern already exist in URN? (assumes there is only at the most one concern defined)
        Concern concern = null;
        Iterator it3 = urn.getUrndef().getConcerns().iterator();
        if (it3.hasNext()) {
        	// use existing concern, but rename it
        	concern = (Concern) it3.next();
        	UpdateConcernCommand ucCmd = new UpdateConcernCommand(concern, cc.getName(), "");
        	if (ucCmd.canExecute())
        		ucCmd.execute();
        	else
        		return null;
        }
        else {
    		// create concern and name it
    		InternalCreateConcernCommand iccCmd = new InternalCreateConcernCommand(urn, cc.getName(), "");
    		if (iccCmd.canExecute())
    			iccCmd.execute();
    		else
    			return null;
    		concern = iccCmd.getConcern();        	
        }
        
		// assign model to the concern
		AssignConcernDiagramCommand acdCmd = new AssignConcernDiagramCommand(model, concern);
		if (acdCmd.canExecute())
			acdCmd.execute();
		else
			return null;
				
		// associate the concern in urn with the COREConcern 
		concern.setCoreConcern(cc);
		
		return concern;
	}
	
}
