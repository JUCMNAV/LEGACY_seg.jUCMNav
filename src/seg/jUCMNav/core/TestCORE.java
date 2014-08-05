package seg.jUCMNav.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import junit.framework.TestCase;
import seg.jUCMNav.model.commands.delete.DeleteIntentionalElementCommand;
import urncore.Concern;
import urncore.IURNDiagram;
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

public class TestCORE extends TestCase {

	public static void main(String[] args) {
		COREFactory4URN fact = new COREFactory4URN();
		// test case (iii): neither feature model nor impact model exist
		COREConcern concern = fact.createConcern("testing");
		
		assertEquals("testing", concern.getName());
		
		FeatureModel fm = null;
		ImpactModel im = null;
		FeatureDiagram fd = null;
		IntentionalElementRef root = null;
		GRLGraph ig = null;
		
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
		
		if (root != null && root.getDef() instanceof Feature) {
			((Feature) root.getDef()).addFeature("child", COREFeatureRelationshipType.MANDATORY);

			assertEquals(2, fd.getNodes().size());
			Feature child = (Feature) ((IntentionalElementRef) fd.getNodes().get(1)).getDef();
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
			Feature grandchild = (Feature) ((IntentionalElementRef) fd.getNodes().get(2)).getDef();
			assertEquals("grandchild", grandchild.getName());
			assertEquals("21", grandchild.getId());
			assertEquals(1, grandchild.getLinksSrc().size());
			link = (ElementLink) grandchild.getLinksSrc().get(0);
			assertTrue(link instanceof Decomposition);
			assertEquals(DecompositionType.XOR_LITERAL, child.getDecompositionType());

			grandchild.addFeature("grandgrandchild", COREFeatureRelationshipType.OPTIONAL);

			assertEquals(4, fd.getNodes().size());
			Feature grandgrandchild = (Feature) ((IntentionalElementRef) fd.getNodes().get(3)).getDef();
			assertEquals("grandgrandchild", grandgrandchild.getName());
			assertEquals("24", grandgrandchild.getId());
			assertEquals(1, grandgrandchild.getLinksSrc().size());
			link = (ElementLink) grandgrandchild.getLinksSrc().get(0);
			assertTrue(link instanceof OptionalFMLink);

			child.addFeature("secondgrandchild", COREFeatureRelationshipType.XOR);

			assertEquals(5, fd.getNodes().size());
			Feature secondgrandchild = (Feature) ((IntentionalElementRef) fd.getNodes().get(4)).getDef();
			assertEquals("secondgrandchild", secondgrandchild.getName());
			assertEquals("27", secondgrandchild.getId());
			assertEquals(1, secondgrandchild.getLinksSrc().size());
			link = (ElementLink) secondgrandchild.getLinksSrc().get(0);
			assertTrue(link instanceof Decomposition);
			assertEquals(DecompositionType.XOR_LITERAL, child.getDecompositionType());

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
		
		System.out.println("success");
	}

}
