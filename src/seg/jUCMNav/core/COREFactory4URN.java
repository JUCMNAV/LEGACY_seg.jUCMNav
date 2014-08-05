package seg.jUCMNav.core;

import java.util.Iterator;
import java.util.List;

import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.concerns.AssignConcernDiagramCommand;
import seg.jUCMNav.model.commands.concerns.InternalCreateConcernCommand;
import seg.jUCMNav.model.commands.concerns.UpdateConcernCommand;
import seg.jUCMNav.model.commands.create.AddIntentionalElementRefCommand;
import seg.jUCMNav.model.commands.create.CreateFMDCommand;
import seg.jUCMNav.model.commands.create.CreateGrlGraphCommand;
import seg.jUCMNav.model.commands.transformations.ChangeGrlNodeNameCommand;
import seg.jUCMNav.strategies.util.FeatureUtil;
import urn.URNspec;
import urncore.Concern;
import urncore.IURNDiagram;
import ca.mcgill.sel.core.COREConcern;
import ca.mcgill.sel.core.COREFeatureModel;
import ca.mcgill.sel.core.COREImpactModel;
import ca.mcgill.sel.core.COREModel;
import ca.mcgill.sel.core.util.AbstractConcernFactory;
import fm.Feature;
import fm.FeatureDiagram;
import fm.FeatureModel;
import grl.GRLGraph;
import grl.GRLNode;
import grl.ImpactModel;
import grl.IntentionalElementRef;

public class COREFactory4URN extends AbstractConcernFactory {
	// the general idea for the CORE interface implementation is to use already existing commands as much as possible to
	// leverage existing checks for well-formedness constraints etc.
	
	// each method needs to call setCOREInterfaceActive(true) at the beginning of the method and call returnResult(...) or 
	// setCOREInterfaceActive(false) when exiting the method
	private static boolean COREInterfaceActive = false;
	
	// the following constants are used to replace behavior of the Eclipse environment
	public static String AUTHOR_NAME = "CORE";
	public static boolean POSITIVE_RANGE = false;
	public static boolean ALWAYS_DELETE = true;
	
	@Override
	protected COREFeatureModel createFeatureModel(COREConcern cc) {
		setCOREInterfaceActive(true);
		// consistency checks: correct parameter, does feature model exist? does feature diagram exist? does the root feature exist and is it placed on a feature diagram?
		if (cc == null)
			return (COREFeatureModel) returnResult(null);
		
		FeatureModel fm = null;
		FeatureModel newFM = null;
		ImpactModel im = null;
		FeatureDiagram fd = null;
		Feature root = null;
		boolean rootIsOnDiagram = false;
		
		// go through all models of the COREconcern
		Iterator<COREModel> it = cc.getModels().iterator();
		while (it.hasNext()) {
			COREModel model = it.next();
			// there are three cases: (i) feature model exists, (ii) only impact model exists, (iii) neither feature nor impact model exists
			if (model instanceof COREFeatureModel && model instanceof FeatureModel) {
				// case (i) --> a feature model exists, so null will be returned
				fm = (FeatureModel) model;
				break;
			}
			else if (model instanceof COREImpactModel && model instanceof ImpactModel)
				im = (ImpactModel) model;
		}
		if (fm == null && im != null) {
			// case (ii) --> feature model needs to be added to the COREconcern
			newFM = im.getGrlspec().getFeatureModel();
			// set fm to the newFM, so that the following checks are performed
			fm = newFM;
		}
		if (fm != null) {
			// a feature model exists, but does a feature diagram exist?
			Iterator it2 = fm.getGrlspec().getUrnspec().getUrndef().getSpecDiagrams().iterator();
			while (it2.hasNext()) {
				IURNDiagram diagram = (IURNDiagram) it2.next();
				if (diagram instanceof FeatureDiagram) {
					// feature diagram exists, but does a root feature exist?
					fd = (FeatureDiagram) diagram;
					List<Feature> roots = FeatureUtil.getRootFeatures(fm.getGrlspec());
					if (!roots.isEmpty()) {
						// root feature exists (take the first one as URN does not constrain feature models to one root), but is it placed on a feature diagram?
						root = roots.get(0);
						Iterator it3 = root.getRefs().iterator();
						while (it3.hasNext()) {
							GRLNode node = (GRLNode) it3.next();
							if (node.getDiagram() instanceof FeatureDiagram) {
								// root is placed on a feature diagram 
								rootIsOnDiagram = true;
								break;
							}
						}
					}
					break;
				}
			}
		}
		
		URNspec urn = null;
		if (fm == null) {
			// if feature model does not exist (i.e., case (iii) --> feature model needs to be added to the COREconcern), create the urn model with 
			// the feature model and one feature diagram (the factory creates the feature/impact model)
			urn = ModelCreationFactory.getNewURNspec(false, false, true);
			// get the created feature model
			newFM = urn.getGrlspec().getFeatureModel();
			// get the created feature diagram
			Iterator it4 = urn.getUrndef().getSpecDiagrams().iterator();
			while (it4.hasNext()) {
				IURNDiagram diagram = (IURNDiagram) it4.next();
				if (diagram instanceof FeatureDiagram)
					fd = (FeatureDiagram) diagram;
			}
		} else {
			urn = fm.getGrlspec().getUrnspec(); 
			if (fd == null) {
				// if feature model already exists, but feature diagram has not been added, add a new feature diagram to the existing URN model
				CreateFMDCommand cfCmd = new CreateFMDCommand(urn);
				if (cfCmd.canExecute())
					cfCmd.execute();
				else
					return (COREFeatureModel) returnResult(null);
				fd = cfCmd.getDiagram();
			}
		} 
		// now a feature diagram should exist, if not something went wrong...
		if (fd == null)
			return (COREFeatureModel) returnResult(null);
		
		// if root feature does not exist, create root feature with the same name as concern and add to feature diagram
		// if root feature exists but has not been placed on a feature diagram, add the existing root feature to the feature diagram 
		if (root == null || !rootIsOnDiagram) {
			IntentionalElementRef ref = (IntentionalElementRef) ModelCreationFactory.getNewObject(urn, IntentionalElementRef.class, ModelCreationFactory.FEATURE);
			if (root != null) {
				ref.setDef(root);
			}
	        AddIntentionalElementRefCommand aierCmd = new AddIntentionalElementRefCommand(fd, ref);
	        if (aierCmd.canExecute())
	        	aierCmd.execute();
	        else
	        	return (COREFeatureModel) returnResult(null);
	        ChangeGrlNodeNameCommand cgnnCmd = new ChangeGrlNodeNameCommand(ref, cc.getName());
	        if (cgnnCmd.canExecute())
	        	cgnnCmd.execute();
	        else
	        	return (COREFeatureModel) returnResult(null);			
		}
        
        Concern concern = createURNConcern(cc, urn, fd);
        if (concern == null)
        	return (COREFeatureModel) returnResult(null);
		
		// return feature model
		return (COREFeatureModel) returnResult(newFM);
	}
	
	@Override
	protected COREImpactModel createImpactModel(COREConcern cc) {
		setCOREInterfaceActive(true);
		// consistency checks: correct parameter, does impact model exist? does impact graph exist?
		if (cc == null)
			return (COREImpactModel) returnResult(null);
		
		ImpactModel im = null;
		ImpactModel newIM = null;
		FeatureModel fm = null;
		GRLGraph ig = null;
		
		// go through all models of the COREconcern
		Iterator<COREModel> it = cc.getModels().iterator();
		while (it.hasNext()) {
			COREModel model = it.next();
			// there are three cases: (i) impact model exists, (ii) only feature model exists, (iii) neither feature nor impact model exists
			if (model instanceof COREImpactModel && model instanceof ImpactModel) {
				// case (i) --> an impact model exists, so null will be returned
				im = (ImpactModel) model;
				break;
			}
			else if (model instanceof COREFeatureModel && model instanceof FeatureModel)
				fm = (FeatureModel) model;
		}
		if (im == null && fm != null) {
			// case (ii) --> impact model needs to be added to the COREconcern
			newIM = fm.getGrlspec().getImpactModel();
			// set im to the newIM, so that the following checks are performed
			im = newIM;
		}
		if (im != null) {
			// an impact model exists, but does an impact graph exist?
			Iterator it2 = im.getGrlspec().getUrnspec().getUrndef().getSpecDiagrams().iterator();
			while (it2.hasNext()) {
				IURNDiagram diagram = (IURNDiagram) it2.next();
				if (diagram instanceof GRLGraph && !(diagram instanceof FeatureDiagram)) {
					// impact graph exists
					ig = (GRLGraph) diagram;
					break;
				}
			}
		}
		
		URNspec urn = null;
		if (im == null) {
			// if impact model does not exist (i.e., case (iii) --> impact model needs to be added to the COREconcern), create the urn model with 
			// the impact model and one impact graph (the factory creates the feature/impact model)
			urn = ModelCreationFactory.getNewURNspec(false, true, false);
			// get the created impact model
			newIM = urn.getGrlspec().getImpactModel();
			// get the created impact graph
			Iterator it3 = urn.getUrndef().getSpecDiagrams().iterator();
			while (it3.hasNext()) {
				IURNDiagram diagram = (IURNDiagram) it3.next();
				if (diagram instanceof GRLGraph && !(diagram instanceof FeatureDiagram))
					ig = (GRLGraph) diagram;
			}
		} else {
			urn = im.getGrlspec().getUrnspec();			
			if (ig == null) {
				// if impact model already exists, but impact graph has not been added, add a new impact graph to the existing URN model
				CreateGrlGraphCommand cggCmd = new CreateGrlGraphCommand(urn);
				if (cggCmd.canExecute())
					cggCmd.execute();
				else
					return (COREImpactModel) returnResult(null);
				ig = cggCmd.getDiagram();
			}
		}
		// now an impact graph should exist, if not something went wrong...
		if (ig == null)
			return (COREImpactModel) returnResult(null);
		
        Concern concern = createURNConcern(cc, urn, ig);
        if (concern == null)
        	return (COREImpactModel) returnResult(null);
		
		// return impact model
		return (COREImpactModel) returnResult(newIM);
	}
	
	private Concern createURNConcern(COREConcern cc, URNspec urn, GRLGraph diagram) {
		// does concern already exist in URN? (assumes there is only at the most one concern defined)
        Concern concern = null;
        Iterator it = urn.getUrndef().getConcerns().iterator();
        if (it.hasNext()) {
        	// use existing concern, but rename it
        	concern = (Concern) it.next();
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
        
		// assign feature diagram/impact graph to the concern
		AssignConcernDiagramCommand acdCmd = new AssignConcernDiagramCommand(diagram, concern);
		if (acdCmd.canExecute())
			acdCmd.execute();
		else
			return null;
				
		// associate the concern in urn with the COREConcern 
		concern.setCoreConcern(cc);
		
		return concern;
	}
	
	public static Object returnResult(Object o) {
		COREInterfaceActive = false;
		return o;
	}

	public static boolean isCOREInterfaceActive() {
		return COREInterfaceActive;
	}

	public static void setCOREInterfaceActive(boolean cOREInterfaceActive) {
		COREInterfaceActive = cOREInterfaceActive;
	}

}
