package seg.jUCMNav.editors.resourceManagement;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.common.util.EList;

import seg.jUCMNav.core.COREFactory4URN;
import ucm.map.impl.MapPackageImpl;
import urn.URNspec;
import urncore.Concern;
import urncore.IURNDiagram;
import ca.mcgill.sel.core.COREConcern;
import ca.mcgill.sel.core.COREFeatureModel;
import ca.mcgill.sel.core.COREImpactModel;
import ca.mcgill.sel.core.COREModel;
import ca.mcgill.sel.core.CoreFactory;
import fm.Feature;
import fm.FeatureDiagram;
import fm.FeatureModel;
import grl.ImpactModel;
import grl.IntentionalElementRef;

/**
 * This class is used to load and save CORE concern from the file system.
 * 
 * @see EmfModelManager
 * @author Patrice Boulet
 */
public class COREmodelManager extends EmfModelManager {
   
	COREFactory4URN fact = new COREFactory4URN();
    Concern concern = null;
	COREConcern coreConcern = null;
	IPath coreConcernPath = null;
	File coreFile = null;
	
	public COREmodelManager(){
		super();
	}
	
	public COREmodelManager( Concern concern){
		super();
		this.concern = concern;
		if ( concern.getCoreConcern() != null)
			this.coreConcern = concern.getCoreConcern();
	}
    
    /**
     * Creates a new COREConcern.
     * 
     * @param path
     *            the location where the new file should be created.
     * @return a new COREConcern
     */
    public COREConcern createCoreConcern(File path) {
        createResource(path);
        resource.getContents().add(coreConcern);
        return coreConcern;
    }

    /**
     * Creates a new COREConcern.
     * 
     * @param path
     *            the location where the new file should be created.
     * @param coreConcern
     *            the initial contents of the file
     * @return a new COREConcern
     */
    public COREConcern createCoreConcern(File path, COREConcern coreConcern) {
        createResource(path);
        resource.getContents().add(coreConcern);
        try {
            save(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return coreConcern;
    }

    /**
     * Creates a new COREConcern.
     * 
     * @param path
     *            the location where the new file should be created.
     * @return a new Package
     */
    public COREConcern createCoreConcern(IPath path) {
        createResource(path);
        coreConcernPath = (IPath)path.clone();
        resource.getContents().add(coreConcern);
        return coreConcern;
    }

    /**
     * Creates a new COREConcern.
     * 
     * @param path
     *            the location where the new file should be created.
     * @param coreConcern
     *            the initial contents of the file
     * @return a new Package
     */
    public COREConcern createCoreConcern(IPath path, COREConcern coreConcern) {
        createResource(path);
        resource.getContents().add(coreConcern);
        try {
            save(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return coreConcern;
    }

    /**
     * Returns the Package serialization filename.
     */
    protected String getFileExtension() {
        return ".core"; //$NON-NLS-1$

    }

    /**
     * Gets the top level model elements.
     * 
     * @return top level model elements
     */
    public COREConcern getModel() {
    	COREConcern coreConcern = null;
    	
    	if (null == model) {
            EList l = resource.getContents();
            Iterator i = l.iterator();
            while (i.hasNext()) {
                Object o = i.next();
                if (o instanceof COREConcern){
                  model = (COREConcern) o;
                }
            }
    	}
        return (COREConcern) model;
    }

    /**
     * Initialize EMF
     */
    protected void init() {
        // Initialize the ucm package
    		MapPackageImpl.init();

    }
    
    /**
     * Set concern
     */
    protected void setConcern(Concern concern) {
       this.concern = concern;
    }
    
    /**
     * Get concern
     */
    protected Concern getConcern() {
    	return concern;
    }
    
    /**
     * Get coreConcern
     */
    protected COREConcern getCOREConcern() {
    	return coreConcern;
    }
    
    /**
     * Set coreConcern
     */
    protected void setCOREConcern(COREConcern coreConcern) {
    	this.coreConcern = coreConcern;
    }
    
    /**
     * Get coreConcernPath
     */
    protected IPath getcoreConcernPath() {
    	return coreConcernPath;
    }
    
    /**
     * Set coreFile
     */
    protected void setCoreFile(File coreFile) {
    	this.coreFile = coreFile;
    }
    
    /**
     * Get coreFile
     */
    protected File getCoreFile() {
    	return coreFile;
    }
    
    /**
     * Updates COREConcern with .jucm file
     * 
     * @param urnspec
     * 		current URNspec
     */
    @SuppressWarnings("unchecked")
	protected void updateCoreConcern(URNspec urnspec) {
    	if ( coreConcern != null){
    		if( coreConcern.getInterface() == null){
    			coreConcern.setInterface(CoreFactory.eINSTANCE.createCOREInterface());
    		}
    	
    	FeatureModel jucmFeatureModel = null;
    	ImpactModel jucmImpactModel = null;
    	
    	if( concern!= null){
			jucmFeatureModel  = concern.getUrndefinition().getUrnspec().getGrlspec().getFeatureModel();
			jucmImpactModel  = concern.getUrndefinition().getUrnspec().getGrlspec().getImpactModel();
    	}
    	
    	if ( coreConcern.getModels() != null){
    		
			boolean featureModelSyncDone = false;
			boolean impactModelSyncDone = false;
			COREFeatureModel coreFeatureModel = null;
			COREImpactModel coreImpactModel = null;

	    	for ( COREModel coreModel : (List<COREModel>) coreConcern.getModels()){
		    		
	    		// FeatureModel already in .core file?
		    	if( coreModel instanceof FeatureModel ){ // YES	
		    		coreFeatureModel = (FeatureModel) coreModel;		    	
		    	}
		    		
		  		// ImpactModel already in .core file?
		    	if( coreModel instanceof ImpactModel ){ // YES
		    		coreImpactModel = (ImpactModel) coreModel;
		    	}
    		}
	    	
	    	if( coreFeatureModel != null){
		    	// FeatureModel in .core equals the FeatureModel in .jucm?
	    		if ( ! coreFeatureModel.equals(jucmFeatureModel) ){ // NO
	    			// Overwrite the .core FeatureModel with .jucm's FeatureModel
	    			coreConcern.getModels().remove(coreFeatureModel);
	    			coreConcern.getModels().add((COREFeatureModel)jucmFeatureModel);
	    			featureModelSyncDone = true;
	    		}
	    	}
    		
	    	if( coreImpactModel != null){
	    		// ImpactModel in .core equals the ImpactModel in .jucm?
	    		if ( ! coreImpactModel.equals(jucmImpactModel) ){ // NO
	    			// Overwrite the .core ImpacteModel with .jucm's FeatureModel
	    			coreConcern.getModels().remove(coreImpactModel);
	    			coreConcern.getModels().add((COREImpactModel)jucmImpactModel);
	    			impactModelSyncDone = true;
	    		}
	    	}
    		
	    	if ( !featureModelSyncDone ){
	    		if( jucmFeatureModel != null ){
	    			coreConcern.getModels().add(jucmFeatureModel);
	    		}else{ // means this is an old file and the model wasn't created at the creation of the GRLspec
	    			// TODO: Is this a case we have to handle?
	    		}
	    	}
	    	if ( !impactModelSyncDone && jucmFeatureModel != null ){
	    		if( jucmImpactModel != null ){
	    			coreConcern.getModels().add(jucmImpactModel);
	    		}else{ // means this is an old file and the model wasn't created at the creation of the GRLspec
	    			// TODO: Is this a case we have to handle?
	    		}
	    	}
    	}
    	
    	// overwrite .core selectable list with .jucm selectable features.
    	coreConcern.getInterface().getSelectable().clear();
    	for ( IURNDiagram diagram : (List<IURNDiagram>)concern.getSpecDiagrams()){
    		if ( diagram instanceof FeatureDiagram ){
    			for( IntentionalElementRef intElemRef : (List<IntentionalElementRef>)((FeatureDiagram)diagram).getNodes()){
    				if( intElemRef.getDef() instanceof Feature){
    	    			
    	    			Feature feature = (Feature)intElemRef.getDef();
    	    			if( feature.isSelectable()){
    	    				coreConcern.getInterface().getSelectable().add(feature);
    	    			}
    	
    	    		}
    			}
    		}
    	}
    
    	}
    }
}