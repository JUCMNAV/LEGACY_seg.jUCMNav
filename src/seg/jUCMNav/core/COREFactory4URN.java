package seg.jUCMNav.core;

import java.util.Iterator;

import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.concerns.AssignConcernDiagramCommand;
import seg.jUCMNav.model.commands.concerns.InternalCreateConcernCommand;
import seg.jUCMNav.model.commands.create.AddIntentionalElementRefCommand;
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
	
	// TODO singleton for now until the abstract methods are switched to static
	private static COREFactory4URN instance = null;
	public static String AUTHOR_NAME = "CORE author";
	public static boolean POSITIVE_RANGE = false;
	
	private COREFactory4URN coreFactory4URN() {
		instance = this;
		return this;
	}
	
	public COREFactory4URN getInstance() {
		if (instance != null)
			return instance;
		return coreFactory4URN();
	}
	
	@Override
	public COREConcern createConcern() {
		// TODO assumes name = "Concern XYZ" at the moment, should be passed into method, as should the author name
		// TODO COREInterface is not created (should be done in CORE)
		String name = "Concern XYZ"; //$NON-NLS-1$
		return createConcern(name, AUTHOR_NAME);
	}
	
	public COREConcern createConcern(String concernName, String authorName) {
		// the general idea for the CORE interface implementation is to use already existing commands as much as possible to
		// leverage existing checks for well-formedness constraints etc.
		
		if (concernName == null || authorName == null)
			return null;
		
		// creates the urn model with one feature model and one impact model
		URNspec urn = ModelCreationFactory.getNewURNspec(false, true, true);
		FeatureModel fm = null;
		GRLGraph im = null;
		Iterator it = urn.getUrndef().getSpecDiagrams().iterator();
		while (it.hasNext()) {
			IURNDiagram diagram = (IURNDiagram) it.next();
			// need to first check for FeatureModel because any FeatureModel is also a GRLGraph
			if (diagram instanceof FeatureModel)
				fm = (FeatureModel) diagram;
			else if (diagram instanceof GRLGraph)
				im = (GRLGraph) diagram;
		}
		if (fm == null || im == null)
			return null;
		
		// create concern and name it
		InternalCreateConcernCommand iccCmd = new InternalCreateConcernCommand(urn, concernName, "");
		if (iccCmd.canExecute())
			iccCmd.execute();
		else
			return null;
		Concern concern = iccCmd.getConcern();
		
		// assign both models to the concern
		AssignConcernDiagramCommand acdCmd = new AssignConcernDiagramCommand(fm, concern);
		if (acdCmd.canExecute())
			acdCmd.execute();
		else
			return null;
		AssignConcernDiagramCommand acdCmd2 = new AssignConcernDiagramCommand(im, concern);
		if (acdCmd2.canExecute())
			acdCmd2.execute();
		else
			return null;
				
		// create root feature with the same name as concern and add to feature model
		IntentionalElementRef ref = (IntentionalElementRef) ModelCreationFactory.getNewObject(urn, IntentionalElementRef.class, ModelCreationFactory.FEATURE);
        AddIntentionalElementRefCommand aierCmd = new AddIntentionalElementRefCommand(fm, ref);
        if (aierCmd.canExecute())
        	aierCmd.execute();
        else
        	return null;
        ChangeGrlNodeNameCommand cgnnCmd = new ChangeGrlNodeNameCommand(ref, concernName);
        if (cgnnCmd.canExecute())
        	cgnnCmd.execute();
        else
        	return null;
		
		// create COREconcern, name it, and assign the two models
		CoreFactory fact = CoreFactory.eINSTANCE;
		COREConcern cc = fact.createCOREConcern();
		cc.setName(concernName);
		cc.getModels().add(fm);
		cc.getModels().add(im);
		
		// associate the concern in urn with the COREConcern 
		concern.setCoreConcern(cc);
		
		// return concern
		return cc;
	}

	@Override
	public COREFeatureModel getFeatureModel(COREConcern concern) {
		// TODO only returns first feature model of concern
		// TODO should really be a list of all feature models
		Iterator<COREModel> it = concern.getModels().iterator();
		while (it.hasNext()) {
			COREModel model = it.next();
			if (model instanceof FeatureModel)
				return (COREFeatureModel) model;
		}
		return null;
	}

	@Override
	public COREImpactModel getImpactModel(COREConcern concern) {
		// TODO only returns first impact model of concern
		// TODO should really be a list of all impact models
		Iterator<COREModel> it = concern.getModels().iterator();
		while (it.hasNext()) {
			COREModel model = it.next();
			if (!(model instanceof FeatureModel) && model instanceof GRLGraph)
				return (COREImpactModel) model;
		}
		return null;
	}

}
