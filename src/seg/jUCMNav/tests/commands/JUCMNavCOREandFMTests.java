package seg.jUCMNav.tests.commands;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import junit.framework.TestCase;
import seg.jUCMNav.core.COREFactory4URN;
import seg.jUCMNav.model.commands.delete.DeleteIntentionalElementCommand;
import seg.jUCMNav.model.commands.transformations.ChangeLinkCommand;
import urncore.Concern;
import urncore.IURNDiagram;
import urncore.URNmodelElement;
import ca.mcgill.sel.core.COREConcern;
import ca.mcgill.sel.core.COREFeature;
import ca.mcgill.sel.core.COREFeatureModel;
import ca.mcgill.sel.core.COREFeatureRelationshipType;
import ca.mcgill.sel.core.COREFeatureSelectionStatus;
import ca.mcgill.sel.core.COREImpactModel;
import ca.mcgill.sel.core.COREModel;
import ca.mcgill.sel.core.CoreFactory;
import fm.Feature;
import fm.FeatureDiagram;
import fm.FeatureModel;
import fm.MandatoryFMLink;
import fm.OptionalFMLink;
import fm.impl.FeatureModelImpl;
import fm.impl.FeatureModelImpl.EvaluationResult;
import grl.Decomposition;
import grl.DecompositionType;
import grl.ElementLink;
import grl.GRLGraph;
import grl.ImpactModel;
import grl.IntentionalElementRef;
import grl.LinkRef;

/**
 * Tests that run on fm.* To be run as PDE JUnit tests.
 * 
 * Uses interesting setUp()/tearDown();
 * 
 * @author jkealey
 * 
 */
public class JUCMNavCOREandFMTests extends TestCase {

	
	
    public static void main(String[] args) {

        junit.textui.TestRunner.run(JUCMNavCOREandFMTests.class);
    }
    
    public FeatureModel fm = null;
	public ImpactModel im = null;
	public FeatureDiagram fd = null;
	public IntentionalElementRef root = null;
	public GRLGraph ig = null;
	public Feature feature1 = null; 
	public COREFactory4URN fact = new COREFactory4URN();
	public COREConcern concern = null;
	public Feature child = null;
	public Feature grandchild = null;
	public Feature secondgrandchild = null;
	public Feature thirdgrandchild = null;
	public Feature grandgrandchild = null;
	
	
    /*
     * @see TestCase#setUp()
     */
    public void setUp() throws Exception {
        super.setUp();
    }

    /*
     * @see TestCase#tearDown()
     */
    public void tearDown() throws Exception {
        super.tearDown();
    }

    public void testAssertionsEnabled() {
        try {
            assert false;
            fail("Assertions must be enabled via JVM flag -ea or -enableassertions"); //$NON-NLS-1$
        } catch (AssertionError exception) {
            // Ignore
        }
    }

    /**
	 * Test case (iii): neither feature model nor impact model exist before creating
	 * COREModel
	 */
	public void testCreateCOREModelCaseIII() {

		concern = fact.createConcern("testing");
		
		assertEquals("testing", concern.getName());
		
		
		Iterator<COREModel> it = (Iterator<COREModel>) concern.getModels().iterator();
		while (it.hasNext()) {
			COREModel model = it.next();
			if (model instanceof COREFeatureModel && model instanceof FeatureModel) {
				if (fm != null)
					fail("more than one feature model exists");
				fm = (FeatureModel) model;
			} else if (model instanceof COREImpactModel && model instanceof ImpactModel) {
				if (im != null)
					fail("more than one impact model exists");
				im = (ImpactModel) model;
			}
		}
		assertEquals(2, concern.getModels().size());
		assertEquals(fm.getGrlspec(), im.getGrlspec());
		assertEquals(2, fm.getGrlspec().getUrnspec().getUrndef().getSpecDiagrams().size());
		assertEquals(1, fm.getGrlspec().getUrnspec().getUrndef().getConcerns().size());
		assertEquals("testing", ((Concern) fm.getGrlspec().getUrnspec().getUrndef().getConcerns().get(0)).getName());
		assertEquals(2, ((Concern) fm.getGrlspec().getUrnspec().getUrndef().getConcerns().get(0)).getSpecDiagrams().size());
		
		Iterator it2 = fm.getGrlspec().getUrnspec().getUrndef().getSpecDiagrams().iterator();
		while (it2.hasNext()) {
			IURNDiagram diagram = (IURNDiagram) it2.next();
			if (diagram instanceof FeatureDiagram) {
				fd = (FeatureDiagram) diagram;
				assertEquals("FeatureDiagram2", fd.getName());
				assertEquals("2", fd.getId());
				root = (IntentionalElementRef) fd.getNodes().get(0);
				assertEquals("testing", root.getDef().getName());
			}
			else if (diagram instanceof GRLGraph) {
				ig = (GRLGraph) diagram;
				assertEquals("GRLGraph14", ig.getName());				
				assertEquals("14", ig.getId());
			}
		}
	}
    
	/**
	 * Tests the AddFeature method of fm.Feature
	 */
	public void testAddFeature() {
		
		testCreateCOREModelCaseIII();
		 
		if (root != null && root.getDef() instanceof Feature) {
			((Feature) root.getDef()).addFeature("child", COREFeatureRelationshipType.MANDATORY);
			
			assertEquals(2, fd.getNodes().size());
			child = (Feature) ((IntentionalElementRef) fd.getNodes().get(1)).getDef();
			assertEquals("child", child.getName());
			assertEquals("15", child.getId());
			assertEquals(1, child.getLinksSrc().size());
			ElementLink link = (ElementLink) child.getLinksSrc().get(0);
			assertTrue(link instanceof MandatoryFMLink);
			
			child.delete();
			assertEquals(1, fd.getNodes().size());
			IntentionalElementRef root2 = (IntentionalElementRef) fd.getNodes().get(0);
			assertEquals(root2, root);

			((Feature) root.getDef()).addFeature("child", COREFeatureRelationshipType.OR);

			assertEquals(2, fd.getNodes().size());
			child = (Feature) ((IntentionalElementRef) fd.getNodes().get(1)).getDef();
			assertEquals("child", child.getName());
			assertEquals("18", child.getId());
			assertEquals(1, child.getLinksSrc().size());
			link = (ElementLink) child.getLinksSrc().get(0);
			assertTrue(link instanceof Decomposition);
			assertEquals(DecompositionType.OR_LITERAL, ((Feature) root.getDef()).getDecompositionType());

			child.addFeature("grandchild", COREFeatureRelationshipType.XOR);

			assertEquals(3, fd.getNodes().size());
			grandchild = (Feature) ((IntentionalElementRef) fd.getNodes().get(2)).getDef();
			assertEquals("grandchild", grandchild.getName());
			assertEquals("21", grandchild.getId());
			assertEquals(1, grandchild.getLinksSrc().size());
			link = (ElementLink) grandchild.getLinksSrc().get(0);
			assertTrue(link instanceof Decomposition);
			assertEquals(DecompositionType.XOR_LITERAL, child.getDecompositionType());

			grandchild.addFeature("grandgrandchild", COREFeatureRelationshipType.OPTIONAL);

			assertEquals(4, fd.getNodes().size());
			grandgrandchild = (Feature) ((IntentionalElementRef) fd.getNodes().get(3)).getDef();
			assertEquals("grandgrandchild", grandgrandchild.getName());
			assertEquals("24", grandgrandchild.getId());
			assertEquals(1, grandgrandchild.getLinksSrc().size());
			link = (ElementLink) grandgrandchild.getLinksSrc().get(0);
			assertTrue(link instanceof OptionalFMLink);

			child.addFeature("secondgrandchild", COREFeatureRelationshipType.XOR);

			assertEquals(5, fd.getNodes().size());
			secondgrandchild = (Feature) ((IntentionalElementRef) fd.getNodes().get(4)).getDef();
			assertEquals("secondgrandchild", secondgrandchild.getName());
			assertEquals("27", secondgrandchild.getId());
			assertEquals(1, secondgrandchild.getLinksSrc().size());
			link = (ElementLink) secondgrandchild.getLinksSrc().get(0);
			assertTrue(link instanceof Decomposition);
			assertEquals(DecompositionType.XOR_LITERAL, child.getDecompositionType());
			
			// test FeatureModel.select(...) with an empty list as input
			List<COREFeature> emptyFeatureList = new LinkedList<COREFeature>();
			EvaluationResult emptyFeatureListER = ((FeatureModelImpl)fm).select(emptyFeatureList);
			
			assertTrue(emptyFeatureListER != null);
			Iterator<COREFeature> it99 = emptyFeatureListER.featureResult.keySet().iterator();
			while (it99.hasNext()) {
				COREFeature cf = it99.next();
				COREFeatureSelectionStatus ss = emptyFeatureListER.featureResult.get(cf);
				
				//System.out.println( cf.getName() + " : " + ss);
			}
			
			// test FeatureModel.select(...) with an non-empty list as input
			List<COREFeature> features = new ArrayList<COREFeature>();
			features.add((COREFeature) child);
			features.add((COREFeature) grandchild);
			features.add((COREFeature) secondgrandchild);
			EvaluationResult er = ((FeatureModelImpl) fm).select(features);
			
			Iterator<COREFeature> it4 = er.featureResult.keySet().iterator();
			while (it4.hasNext()) {
				COREFeature cf = it4.next();
				COREFeatureSelectionStatus ss = er.featureResult.get(cf);
				if (cf.getName().equals("testing"))
					assertTrue(COREFeatureSelectionStatus.AUTO_SELECTED == ss);
				else if (cf.getName().equals("child"))
					assertTrue(COREFeatureSelectionStatus.USER_SELECTED == ss);
				else if (cf.getName().equals("grandchild"))
					assertTrue(COREFeatureSelectionStatus.WARNING_USER_SELECTED == ss);
				else if (cf.getName().equals("secondgrandchild"))
					assertTrue(COREFeatureSelectionStatus.WARNING_USER_SELECTED == ss);
				else if (cf.getName().equals("grandgrandchild"))
					assertTrue(COREFeatureSelectionStatus.NOT_SELECTED_NO_ACTION == ss);
			}
		} else {
			fail("root does not exist");
		}
	}

	/**
	 * Tests the method "rename" of fm.Feature
	 */
	public void testRename() {

		createTestingModel();
		
		feature1 = child;
		feature1.rename("Feature1NewName");
		assertTrue(feature1.getName().compareTo("Feature1NewName") == 0);

	}

	/**
	 * Tests the method "changeParent" of fm.Feature
	 */
	public void testChangeParent() {
		
		createTestingModel();
		
		Feature nodeToBeChangedParent = secondgrandchild;
		Feature oldParent = child;
		
		assertTrue(root.getPred().size() == 1); 
		nodeToBeChangedParent.changeParent((Feature)root.getDef(), COREFeatureRelationshipType.MANDATORY);
		// test if we correctly deleted the link with the old parent
		assertTrue(((IntentionalElementRef) fd.getNodes().get(1)).getPred().size() == 2);
		assertTrue(root.getPred().size() == 2); 
		// test if we moved the right element
		LinkRef newLink = ((LinkRef)root.getPred().get(root.getPred().size()-1));
		// test if it's the right type of link
		assertTrue(newLink.getLink() instanceof MandatoryFMLink);
		// test if we moved the right element
		assertTrue(((Feature)((IntentionalElementRef)(newLink.getSource())).getDef()).getName().compareTo("secondgrandchild") == 0);
		nodeToBeChangedParent.changeParent(oldParent, COREFeatureRelationshipType.XOR);
		assertTrue(root.getPred().size() == 1); 
		
	}

	/**
	 * Tests the method "changeLink" of fm.Feature
	 */
	public void testChangeLink() {
		
		createTestingModel();
	
		Feature featureToChangeLink = secondgrandchild;
		ElementLink linkToChange = (ElementLink) featureToChangeLink.getLinksSrc().get(0);
		int oldPosition = child.getLinksDest().indexOf(linkToChange);
		
		featureToChangeLink.changeLink(COREFeatureRelationshipType.MANDATORY);

		// test if it's the right type of link
		assertTrue(child.getLinksDest().get(oldPosition) instanceof MandatoryFMLink);
		// test if the feature is still at the same position regarding it's parent
		assertTrue( ((URNmodelElement)((ElementLink)child.getLinksDest().get(oldPosition)).getSrc()).getId() == 
				((URNmodelElement)featureToChangeLink).getId());
		
		featureToChangeLink.changeLink(COREFeatureRelationshipType.OPTIONAL);
		// test if it's the right type of link
		assertTrue(child.getLinksDest().get(oldPosition) instanceof OptionalFMLink);
		
		featureToChangeLink.changeLink(COREFeatureRelationshipType.XOR);
		// test if it's the right type of link
		assertTrue(child.getLinksDest().get(oldPosition) instanceof Decomposition);

		featureToChangeLink.changeLink(COREFeatureRelationshipType.OR);
		// test if it's the right type of link
		assertTrue(child.getLinksDest().get(oldPosition) instanceof Decomposition);
		
		ChangeLinkCommand changeLinkCmd = 
				new ChangeLinkCommand(ChangeLinkCommand.FEATURE_MANDATORY_RELATIONSHIP, ((IntentionalElementRef) fd.getNodes().get(5)));
		if ( changeLinkCmd.canExecute()){
			changeLinkCmd.execute();
		}
		// test if it's the right type of link
		assertTrue(child.getLinksDest().get(oldPosition) instanceof MandatoryFMLink);
	}
	
    /**
     * Tests most of the cases for creating Feature and Impact
     * Models 
     */
	
    public void testCreatingFeatureAndImpactModels() {
    	 testCreateCOREModelCaseIII();
		
		// creating feature model - test case (ii): feature model does not exist, impact model exists 
		concern.getModels().remove(fm);
		assertEquals(1, concern.getModels().size());
		COREFeatureModel fm2 = fact.createFeatureModel(concern);
		assertEquals(fm, fm2);
		assertEquals(1, concern.getModels().size());
		assertEquals(2, fm.getGrlspec().getUrnspec().getUrndef().getSpecDiagrams().size());
		assertEquals(1, fm.getGrlspec().getUrnspec().getUrndef().getConcerns().size());
		assertEquals("testing", ((Concern) fm.getGrlspec().getUrnspec().getUrndef().getConcerns().get(0)).getName());
		assertEquals(2, ((Concern) fm.getGrlspec().getUrnspec().getUrndef().getConcerns().get(0)).getSpecDiagrams().size());
		concern.getModels().add(fm);
		assertEquals(2, concern.getModels().size());
		
		// creating feature model - test case (i): feature model exists, impact model does not exist
		concern.getModels().remove(im);
		assertEquals(1, concern.getModels().size());
		fm2 = fact.createFeatureModel(concern);
		assertNull(fm2);
		assertEquals(1, concern.getModels().size());
		assertEquals(2, fm.getGrlspec().getUrnspec().getUrndef().getSpecDiagrams().size());
		assertEquals(1, fm.getGrlspec().getUrnspec().getUrndef().getConcerns().size());
		assertEquals("testing", ((Concern) fm.getGrlspec().getUrnspec().getUrndef().getConcerns().get(0)).getName());
		assertEquals(2, ((Concern) fm.getGrlspec().getUrnspec().getUrndef().getConcerns().get(0)).getSpecDiagrams().size());
		concern.getModels().add(im);
		assertEquals(2, concern.getModels().size());
		
		// creating impact model - test case (ii): feature model does not exist, impact model exists 
		concern.getModels().remove(fm);
		assertEquals(1, concern.getModels().size());
		COREImpactModel im2 = fact.createImpactModel(concern);
		assertNull(im2);
		assertEquals(1, concern.getModels().size());
		assertEquals(2, fm.getGrlspec().getUrnspec().getUrndef().getSpecDiagrams().size());
		assertEquals(1, fm.getGrlspec().getUrnspec().getUrndef().getConcerns().size());
		assertEquals("testing", ((Concern) fm.getGrlspec().getUrnspec().getUrndef().getConcerns().get(0)).getName());
		assertEquals(2, ((Concern) fm.getGrlspec().getUrnspec().getUrndef().getConcerns().get(0)).getSpecDiagrams().size());
		concern.getModels().add(fm);
		assertEquals(2, concern.getModels().size());
				
		// creating impact model - test case (i): feature model exists, impact model does not exist
		concern.getModels().remove(im);
		assertEquals(1, concern.getModels().size());
		im2 = fact.createImpactModel(concern);
		assertEquals(im, im2);
		assertEquals(1, concern.getModels().size());
		assertEquals(2, fm.getGrlspec().getUrnspec().getUrndef().getSpecDiagrams().size());
		assertEquals(1, fm.getGrlspec().getUrnspec().getUrndef().getConcerns().size());
		assertEquals("testing", ((Concern) fm.getGrlspec().getUrnspec().getUrndef().getConcerns().get(0)).getName());
		assertEquals(2, ((Concern) fm.getGrlspec().getUrnspec().getUrndef().getConcerns().get(0)).getSpecDiagrams().size());
		concern.getModels().add(im);
		assertEquals(2, concern.getModels().size());
		
		// feature model exists, but root feature is not placed on a feature diagram
		COREConcern concern2 = fact.createConcern("testNoRoot");
		FeatureDiagram fd2 = (FeatureDiagram) ((FeatureModel) concern2.getModels().get(0)).getGrlspec().getUrnspec().getUrndef().getSpecDiagrams().get(0);
		Feature root3 = (Feature) ((FeatureModel) concern2.getModels().get(0)).getGrlspec().getIntElements().get(0);
		IntentionalElementRef ref = (IntentionalElementRef) root3.getRefs().get(0);
		assertEquals("testNoRoot", root3.getName());
		assertEquals(fd2, ref.getDiagram());
		// not going through commands for deleting the GRLNode for the root feature - directly changing the data structure
		fd2.getNodes().remove(ref);
        ref.setContRef(null);
        ref.setDef(null);
        // now test that creating the feature model again will place the root back on the feature diagram
        fm2 = fact.createFeatureModel(concern2);
		assertNull(fm2);
		fd2 = (FeatureDiagram) ((FeatureModel) concern2.getModels().get(0)).getGrlspec().getUrnspec().getUrndef().getSpecDiagrams().get(0);
		root3 = (Feature) ((FeatureModel) concern2.getModels().get(0)).getGrlspec().getIntElements().get(0);
		ref = (IntentionalElementRef) root3.getRefs().get(0);
		assertEquals("testNoRoot", root3.getName());
		assertEquals(fd2, ref.getDiagram());

		// feature model exists, but feature diagram does not exist
		concern2 = fact.createConcern("testNoDiagram");
		fd2 = (FeatureDiagram) ((FeatureModel) concern2.getModels().get(0)).getGrlspec().getUrnspec().getUrndef().getSpecDiagrams().get(0);
		root3 = (Feature) ((FeatureModel) concern2.getModels().get(0)).getGrlspec().getIntElements().get(0);
		ref = (IntentionalElementRef) root3.getRefs().get(0);
		assertEquals("testNoDiagram", root3.getName());
		assertEquals(fd2, ref.getDiagram());
		// delete the root feature
		COREFactory4URN.setCOREInterfaceActive(true);
		DeleteIntentionalElementCommand dieCmd = new DeleteIntentionalElementCommand(root3);
		dieCmd.execute();
		COREFactory4URN.setCOREInterfaceActive(false);
		// not going through commands for deleting the feature diagram - directly changing the data structure
        fd2.setConcern(null);
        fd2.getUrndefinition().getSpecDiagrams().remove(fd2);
        // now test that creating the feature model again will recreate the feature diagram and the root feature
        fm2 = fact.createFeatureModel(concern2);
		assertNull(fm2);
		fd2 = (FeatureDiagram) ((FeatureModel) concern2.getModels().get(0)).getGrlspec().getUrnspec().getUrndef().getSpecDiagrams().get(1);
		root3 = (Feature) ((FeatureModel) concern2.getModels().get(0)).getGrlspec().getIntElements().get(0);
		ref = (IntentionalElementRef) root3.getRefs().get(0);
		assertEquals("testNoDiagram", root3.getName());
		assertEquals(fd2, ref.getDiagram());
		
		// test creating an impact model before creating a feature model
        COREConcern concern3 = CoreFactory.eINSTANCE.createCOREConcern();
        concern3.setName("ImpactModelFirst");
		COREImpactModel im3 = fact.createImpactModel(concern3);
		assertEquals(0, concern3.getModels().size());
		assertEquals(1, ((ImpactModel) im3).getGrlspec().getUrnspec().getUrndef().getSpecDiagrams().size());
		assertTrue(((ImpactModel) im3).getGrlspec().getUrnspec().getUrndef().getSpecDiagrams().get(0) instanceof GRLGraph);
		assertEquals(1, ((ImpactModel) im3).getGrlspec().getUrnspec().getUrndef().getConcerns().size());
		assertEquals("ImpactModelFirst", ((Concern) ((ImpactModel) im3).getGrlspec().getUrnspec().getUrndef().getConcerns().get(0)).getName());
		assertEquals(1, ((Concern) ((ImpactModel) im3).getGrlspec().getUrnspec().getUrndef().getConcerns().get(0)).getSpecDiagrams().size());
	

    }

    /**
     * Creates a FeatureModel to test methods.
     * 
     * Visualization of the model ( nodes are Features ) :
     * 
     *  		        root
     *			   		 /  
     *			   		OR   
     *		    	   /
     *  		    child  ---XOR---- thirdgrandchild	
     *  		   /     \
     *           XOR    XOR
     *           /		   \
     *     grandchild	 secondgrandchild
     *        /
     *    OPTIONAL
     *    	/
     * grandgrandchild
     * 
     */
    
	private void createTestingModel(){
		
		concern = fact.createConcern("testing");
	
		Iterator<COREModel> it = (Iterator<COREModel>) concern.getModels().iterator();
		while (it.hasNext()) {
			COREModel model = it.next();
			if (model instanceof COREFeatureModel && model instanceof FeatureModel) {
				fm = (FeatureModel) model;
			} else if (model instanceof COREImpactModel && model instanceof ImpactModel) {
				im = (ImpactModel) model;
			}
		}
		
		Iterator it2 = fm.getGrlspec().getUrnspec().getUrndef().getSpecDiagrams().iterator();
		while (it2.hasNext()) {
			IURNDiagram diagram = (IURNDiagram) it2.next();
			if (diagram instanceof FeatureDiagram) {
				fd = (FeatureDiagram) diagram;
				root = (IntentionalElementRef) fd.getNodes().get(0);
			}
			else if (diagram instanceof GRLGraph) {
				ig = (GRLGraph) diagram;
			}
		} 
		
		if (root != null && root.getDef() instanceof Feature) {
			((Feature) root.getDef()).addFeature("child", COREFeatureRelationshipType.MANDATORY);

			
			IntentionalElementRef root2 = (IntentionalElementRef) fd.getNodes().get(0);
			((Feature) root.getDef()).addFeature("child", COREFeatureRelationshipType.OR);

			child = (Feature) ((IntentionalElementRef) fd.getNodes().get(1)).getDef();
			child.addFeature("grandchild", COREFeatureRelationshipType.XOR);
			grandchild = (Feature) ((IntentionalElementRef) fd.getNodes().get(3)).getDef();
			grandchild.addFeature("grandgrandchild", COREFeatureRelationshipType.OPTIONAL);
			grandgrandchild = (Feature) ((IntentionalElementRef) fd.getNodes().get(4)).getDef();
			child.addFeature("secondgrandchild", COREFeatureRelationshipType.XOR);
			secondgrandchild = (Feature) ((IntentionalElementRef) fd.getNodes().get(5)).getDef();
			child.addFeature("thirdgrandchild", COREFeatureRelationshipType.XOR);
			thirdgrandchild = (Feature) ((IntentionalElementRef) fd.getNodes().get(6)).getDef();
			
			
			List<COREFeature> features = new ArrayList<COREFeature>();
			features.add((COREFeature) child);
			features.add((COREFeature) grandchild);
			features.add((COREFeature) secondgrandchild);
			EvaluationResult er = ((FeatureModelImpl) fm).select(features);

		} else {
			fail("root does not exist");
		}
	}
	
}