package seg.jUCMNav.core;

import fm.Feature;
import fm.FeatureDiagram;
import fm.MandatoryFMLink;
import fm.OptionalFMLink;
import grl.Decomposition;
import grl.DecompositionType;
import grl.ElementLink;
import grl.GRLGraph;
import grl.GRLspec;
import grl.IntentionalElementRef;
import grl.impl.GRLspecImpl;
import grl.impl.GRLspecImpl.EvaluationResult;
import grl.impl.GRLspecImpl.SelectionStatus;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import junit.framework.TestCase;
import urncore.IURNDiagram;
import urncore.URNmodelElement;
import ca.mcgill.sel.core.COREConcern;
import ca.mcgill.sel.core.COREFeature;
import ca.mcgill.sel.core.COREFeatureModel;
import ca.mcgill.sel.core.COREFeatureRelationshipType;
import ca.mcgill.sel.core.COREImpactModel;
import ca.mcgill.sel.core.COREModel;

public class TestCORE extends TestCase {

	public static void main(String[] args) {
		COREFactory4URN fact = new COREFactory4URN();
		COREConcern concern = fact.createConcern("testing");

		assertEquals("testing", concern.getName());
		IntentionalElementRef root = null;
		GRLspec grl = null;
		FeatureDiagram fm = null;
		GRLGraph ig = null;
		Iterator<COREModel> it = (Iterator<COREModel>) concern.getModels().iterator();
		while (it.hasNext()) {
			COREModel model = it.next();
			if (model instanceof COREFeatureModel && model instanceof COREImpactModel && model instanceof GRLspec) {
				grl = (GRLspec) model;
				Iterator it2 = grl.getUrnspec().getUrndef().getSpecDiagrams().iterator();
				while (it2.hasNext()) {
					IURNDiagram diagram = (IURNDiagram) it2.next();
					if (diagram instanceof FeatureDiagram) {
						fm = (FeatureDiagram) diagram;
						assertEquals("FeatureDiagram2", fm.getName());
						assertEquals("2", fm.getId());
						root = (IntentionalElementRef) fm.getNodes().get(0);
						assertEquals("testing", root.getDef().getName());
					}
					else if (diagram instanceof GRLGraph) {
						ig = (GRLGraph) diagram;
						assertEquals("GRLGraph14", ig.getName());				
						assertEquals("14", ig.getId());
					}
				}
			}
		}
		if (root != null && root.getDef() instanceof Feature) {
			((Feature) root.getDef()).addFeature("child", COREFeatureRelationshipType.MANDATORY);

			assertEquals(2, fm.getNodes().size());
			Feature child = (Feature) ((IntentionalElementRef) fm.getNodes().get(1)).getDef();
			assertEquals("child", child.getName());
			assertEquals("15", child.getId());
			assertEquals(1, child.getLinksSrc().size());
			ElementLink link = (ElementLink) child.getLinksSrc().get(0);
			assertTrue(link instanceof MandatoryFMLink);

			child.delete();
			assertEquals(1, fm.getNodes().size());
			IntentionalElementRef root2 = (IntentionalElementRef) fm.getNodes().get(0);
			assertEquals(root2, root);

			((Feature) root.getDef()).addFeature("child", COREFeatureRelationshipType.OR);

			assertEquals(2, fm.getNodes().size());
			child = (Feature) ((IntentionalElementRef) fm.getNodes().get(1)).getDef();
			assertEquals("child", child.getName());
			assertEquals("18", child.getId());
			assertEquals(1, child.getLinksSrc().size());
			link = (ElementLink) child.getLinksSrc().get(0);
			assertTrue(link instanceof Decomposition);
			assertEquals(DecompositionType.OR_LITERAL, ((Feature) root.getDef()).getDecompositionType());

			child.addFeature("grandchild", COREFeatureRelationshipType.XOR);

			assertEquals(3, fm.getNodes().size());
			Feature grandchild = (Feature) ((IntentionalElementRef) fm.getNodes().get(2)).getDef();
			assertEquals("grandchild", grandchild.getName());
			assertEquals("21", grandchild.getId());
			assertEquals(1, grandchild.getLinksSrc().size());
			link = (ElementLink) grandchild.getLinksSrc().get(0);
			assertTrue(link instanceof Decomposition);
			assertEquals(DecompositionType.XOR_LITERAL, child.getDecompositionType());

			grandchild.addFeature("grandgrandchild", COREFeatureRelationshipType.OPTIONAL);

			assertEquals(4, fm.getNodes().size());
			Feature grandgrandchild = (Feature) ((IntentionalElementRef) fm.getNodes().get(3)).getDef();
			assertEquals("grandgrandchild", grandgrandchild.getName());
			assertEquals("24", grandgrandchild.getId());
			assertEquals(1, grandgrandchild.getLinksSrc().size());
			link = (ElementLink) grandgrandchild.getLinksSrc().get(0);
			assertTrue(link instanceof OptionalFMLink);

			child.addFeature("secondgrandchild", COREFeatureRelationshipType.XOR);

			assertEquals(5, fm.getNodes().size());
			Feature secondgrandchild = (Feature) ((IntentionalElementRef) fm.getNodes().get(4)).getDef();
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
			EvaluationResult er = ((GRLspecImpl) grl).select(features);

			Iterator<COREFeature> it2 = er.featureResult.keySet().iterator();
			while (it2.hasNext()) {
				COREFeature cf = it2.next();
				SelectionStatus ss = er.featureResult.get(cf);
				if (cf.getName().equals("testing"))
					assertTrue(SelectionStatus.SELECTED == ss);
				else if (cf.getName().equals("child"))
					assertTrue(SelectionStatus.USER_SELECTED == ss);
				else if (cf.getName().equals("grandchild"))
					assertTrue(SelectionStatus.WARNING == ss);
				else if (cf.getName().equals("secondgrandchild"))
					assertTrue(SelectionStatus.WARNING == ss);
				else if (cf.getName().equals("grandgrandchild"))
					assertTrue(SelectionStatus.NOT_SELECTED_NO_ACTION == ss);
			}
		} else {
			fail("root does not exist");
		}

		System.out.println("success");


		//		// TODO save core file
		//        ResourceManager.initialize();
		//        ResourceManager.registerExtensionFactory("core", new CoreResourceFactoryImpl());
		//		// C:\\Users\\gm\\Desktop\\TEMP\\
		//		ResourceManager.saveModel(concern, "example.core");
		//		
		// TODO save jucm file

	}

}
