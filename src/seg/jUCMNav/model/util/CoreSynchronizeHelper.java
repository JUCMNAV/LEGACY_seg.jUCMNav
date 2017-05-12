package seg.jUCMNav.model.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jface.preference.IPreferenceStore;

import ca.mcgill.sel.commons.emf.util.ResourceManager;
import ca.mcgill.sel.core.COREConcern;
import ca.mcgill.sel.core.COREFeature;
import ca.mcgill.sel.core.COREFeatureRelationshipType;
import ca.mcgill.sel.core.CoreFactory;
import ca.mcgill.sel.core.util.COREModelUtil;
import ca.mcgill.sel.core.util.CoreResourceFactoryImpl;

import fm.Feature;
import fm.FeatureDiagram;
import fm.MandatoryFMLink;
import fm.OptionalFMLink;
import grl.Decomposition;
import grl.DecompositionType;
import grl.ElementLink;
import grl.IntentionalElement;
import grl.IntentionalElementRef;
import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.Messages;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.create.CreateElementLinkCommand;
import seg.jUCMNav.model.commands.delete.DeleteElementLinkCommand;
import seg.jUCMNav.model.commands.delete.DeleteIntentionalElementCommand;
import urn.URNspec;
import urncore.Concern;
import urncore.IURNDiagram;

public class CoreSynchronizeHelper {
	
	public static final String CORE_FILE_EXTENSION = "core"; //$NON-NLS-1$

	public static HashMap<COREFeature, Feature> oldmappedCoreFeatureAndFeature = new HashMap<COREFeature, Feature>();
	public static HashMap<COREFeature, Feature> newmappedCoreFeatureAndFeature = new HashMap<COREFeature, Feature>();

	public static boolean synchronizeDirection;

	public static void synchronizeJUCMWithCore(URNspec urn, COREConcern concern, String fullPath) {

		oldmappedCoreFeatureAndFeature = newmappedCoreFeatureAndFeature;
		newmappedCoreFeatureAndFeature = new HashMap<COREFeature, Feature>();

		ArrayList<Feature> featuresInJUCM = new ArrayList<Feature>();

		for (Iterator iter = urn.getGrlspec().getIntElements().iterator(); iter.hasNext();) {
			IntentionalElement element = (IntentionalElement) iter.next();
			if (element instanceof Feature) {
				Feature currFeature = (Feature) element;
				featuresInJUCM.add(currFeature);
				if (((Feature) element).getCoreFeature() != null && !((Feature) element).getCoreFeature().eIsProxy()
						&& concern.getFeatureModel().getFeatures().contains(((Feature) element).getCoreFeature())) {
					newmappedCoreFeatureAndFeature.put((currFeature).getCoreFeature(), currFeature);
				}
			}
		}
		ArrayList<Feature> featureLinkedTree = new ArrayList<Feature>();
		if (findRootFeature(urn) != null) {
			Stack<Feature> stack = new Stack<Feature>();
			Feature rootFeature = findRootFeature(urn);
			stack.push(rootFeature);

			while (!stack.isEmpty()) {
				Feature curr_Feature = stack.pop();
				featureLinkedTree.add(curr_Feature);

				for (Iterator iterator = curr_Feature.getLinksDest().iterator(); iterator.hasNext();) {
					ElementLink link = (ElementLink) iterator.next();
					if (link.getSrc() instanceof Feature) {
						stack.push((Feature) link.getSrc());
					}
				}
			}
		}
		// synchronization direction is from jucm to core
		if (!getDirection()) {

			Feature rootFeature = findRootFeature(urn);
			if (rootFeature == null) {
				// define the name of root Feature
				String fileName = fullPath.substring(fullPath.lastIndexOf("\\") + 1); //$NON-NLS-1$
				String rootFeatureName = fileName.substring(0, 1).toUpperCase() + fileName.substring(1);

				createRootFeature(urn, rootFeatureName);
			}
			/*
			 * check if the root Feature is referenced to the root COREFeature
			 * of COREConcern if not reset that reference of Feature to the
			 * correct one
			 */
			COREFeature rootCoreFeature = rootFeature.getCoreFeature();
			if ((rootCoreFeature == null && concern.getFeatureModel().getRoot() != null)
					|| (rootCoreFeature != null && !rootCoreFeature.equals(concern.getFeatureModel().getRoot()))) {

				rootCoreFeature = concern.getFeatureModel().getRoot();
				rootFeature.setCoreFeature(rootCoreFeature);

			}
			checkElementName(rootFeature, rootCoreFeature);
			// create a list to store the visited COREFeaturess
			ArrayList<COREFeature> visitedCoreFeatures = new ArrayList<COREFeature>();
			visitedCoreFeatures.add(rootCoreFeature);

			synchronizeUtil(rootFeature, concern, visitedCoreFeatures);
			//
			// EList strategies = urn.getGrlspec().getStrategies();
			// for(EvaluationStrategy currStrategy :new
			// ArrayList<EvaluationStrategy>(strategies)){
			//
			// currStrategy¡£
			// }

			ArrayList<COREFeature> coreFeatureList = new ArrayList<COREFeature>(
					concern.getFeatureModel().getFeatures());
			for (COREFeature currCoreFeature : coreFeatureList) {
				if (!visitedCoreFeatures.contains(currCoreFeature)) {
					// the structure of core file is incorrect, it exists
					// Features not connected to the Tree starting from the root
					if (currCoreFeature.getParent() != null) {
						currCoreFeature.getParent().getChildren().remove(currCoreFeature);
					}
					currCoreFeature.setParent(null);

					for (IntentionalElement intElement : (List<IntentionalElement>) urn.getGrlspec().getIntElements()) {
						if (intElement instanceof Feature) {
							Feature feature = (Feature) intElement;
							if (feature.getCoreFeature() != null && feature.getCoreFeature().equals(currCoreFeature)) {
								feature.setCoreFeature(null);
							}
						}
					}

					concern.getFeatureModel().getFeatures().remove(currCoreFeature);
				}
			}

			// adjustParentRelationshipType(rootCoreFeature, concern);
			// save to change to concern
			doSave(concern, fullPath);
		}

		// synchronization direction is from core to jucm
		if (getDirection()) {

			COREFeature rootCoreFeature = concern.getFeatureModel().getRoot();
			Feature rootFeature = findRootFeature(urn);
			// if root feature in jucm is null create the new root feature link
			// rootfeature and rootCoreFeature
			if (rootFeature == null) {
				// root feature doesn't exist
				String fileName = fullPath.substring(fullPath.lastIndexOf("\\") + 1); //$NON-NLS-1$
				String rootFeatureName = fileName.substring(0, 1).toUpperCase() + fileName.substring(1);

				createRootFeature(urn, rootFeatureName);
			}
			// if root feature is not null but rootfeature and rootCoreFeature
			// are wrongly linked
			if (rootFeature != null) {
				if (!rootFeature.getCoreFeature().equals(rootCoreFeature)) {
					if (rootFeature.getCoreFeature() == null) {
						rootFeature.setCoreFeature(rootCoreFeature);
					} else {

						COREFeature oldCoreFeature = rootFeature.getCoreFeature();
						newmappedCoreFeatureAndFeature.remove(oldCoreFeature, rootFeature);
						rootFeature.setCoreFeature(rootCoreFeature);
					}
				}
			}
			newmappedCoreFeatureAndFeature.put(rootCoreFeature, rootFeature);
			ArrayList<Feature> visitedFeatures = new ArrayList<Feature>();
			visitedFeatures.add(rootFeature);
			checkElementName(rootFeature, rootCoreFeature);
			// start to synchronize from core to JUCM
			synchronizeCoreToJUCMUtil(rootFeature, rootCoreFeature, newmappedCoreFeatureAndFeature, visitedFeatures,
					urn);

			ArrayList<IntentionalElement> conFeatureList = new ArrayList<IntentionalElement>(featureLinkedTree);
			IPreferenceStore preferenceStore = JUCMNavPlugin.getDefault().getPreferenceStore();
			preferenceStore.setValue("PREF_DELDEFINITION", Messages.getString("DeletePreferences_Always"));//$NON-NLS-1$

			for (IntentionalElement iter : conFeatureList) {
				if (iter instanceof Feature) {
					if (!visitedFeatures.contains(iter) && iter.getRefs().size() > 0) {
						DeleteIntentionalElementCommand command = new DeleteIntentionalElementCommand(iter);
						if (command.canExecute()) {
							command.execute();
						}
					}
				}
			}

			preferenceStore.setDefault("PREF_DELDEFINITION", Messages.getString("DeletePreferences_Prompt"));//$NON-NLS-1$
		}

	}

	/*
	 * check if the name of pairs of Features and COREFeatures are same if not
	 * reset the name attribute parameter rootFeature is Feature rootCoreFeature
	 * is COREFeature of core file
	 */
	private static void checkElementName(Feature rootFeature, COREFeature rootCoreFeature) {
		// TODO Auto-generated method stub
		if (rootFeature.getCoreFeature() != rootCoreFeature)
			return;
		boolean synchDirect = getDirection();
		if (rootFeature.getName() != rootCoreFeature.getName()) {
			if (!synchDirect)
				rootCoreFeature.setName(rootFeature.getName());
			else
				rootFeature.setName(rootCoreFeature.getName());
		}
	}

	/**
	 * util of synchronization from jucm to core
	 * 
	 * @param parentFeature
	 * @param concern
	 * @param visitedCoreFeatures
	 */

	public static void synchronizeUtil(Feature parentFeature, COREConcern concern,
			ArrayList<COREFeature> visitedCoreFeatures) {

		COREFeature parentCoreFeature = parentFeature.getCoreFeature();

		for (ElementLink link : (List<ElementLink>) parentFeature.getLinksDest()) {
			if (link.getSrc() instanceof Feature) {
				Feature currFeature = (Feature) link.getSrc();

				COREFeature currCoreFeature = currFeature.getCoreFeature();
				if (currCoreFeature == null
						|| (currCoreFeature != null
								&& !concern.getFeatureModel().getFeatures().contains(currCoreFeature))
						|| (currCoreFeature != null && visitedCoreFeatures.contains(currCoreFeature))) {
					if (parentCoreFeature.getChildren().contains(currCoreFeature)) {
						// currCoreFeature is removed from the children list of
						// parentCoreFeature
						// it is destroyed?
						// parentCoreFeature.getChildren().remove(currCoreFeature);
						// currCoreFeature.setParent(null);
						currFeature.setCoreFeature(null);
					}
					COREFeature newCoreFeature = CoreFactory.eINSTANCE.createCOREFeature();
					newCoreFeature.setName(currFeature.getName());
					newCoreFeature.setParentRelationship(COREFeatureRelationshipType.MANDATORY);
					newCoreFeature.setParent(parentCoreFeature);

					parentCoreFeature.getChildren().add(newCoreFeature);
					concern.getFeatureModel().getFeatures().add(newCoreFeature);
					currFeature.setCoreFeature(newCoreFeature);
					currCoreFeature = newCoreFeature;

				}

				if (currCoreFeature.getParent() != null) {
					if (!currCoreFeature.getParent().equals(parentCoreFeature)) {
						currCoreFeature.setParent(parentCoreFeature);
					}
				} else {
					currCoreFeature.setParent(parentCoreFeature);
				}

				checkRelationshipType(link, currCoreFeature);
				checkElementName(currFeature, currCoreFeature);
				visitedCoreFeatures.add(currCoreFeature);

				synchronizeUtil(currFeature, concern, visitedCoreFeatures);
			}

		}
		return;
	}

	/**
	 * util of synchronization from core to jucm
	 * 
	 * @param parentFeature
	 * @param parentCoreFeature
	 * @param mappedCoreFeatureAndFeature
	 * @param visitedFeatures
	 * @param urn
	 */
	public static void synchronizeCoreToJUCMUtil(Feature parentFeature, COREFeature parentCoreFeature,
			HashMap<COREFeature, Feature> mappedCoreFeatureAndFeature, ArrayList<Feature> visitedFeatures,
			URNspec urn) {

		for (COREFeature currCoreFeature : parentCoreFeature.getChildren()) {
			Feature currFeature = mappedCoreFeatureAndFeature.get(currCoreFeature);
			// COREFeature isn't linked a Feature
			if (currFeature == null) {
				IntentionalElementRef featureRef = (IntentionalElementRef) ModelCreationFactory.getNewObject(urn,
						IntentionalElementRef.class, ModelCreationFactory.FEATURE);
				currFeature = (Feature) featureRef.getDef();
				currFeature.setName(currCoreFeature.getName());
				urn.getGrlspec().getIntElements().add(currFeature);
				IURNDiagram featureDiagram = ((IntentionalElementRef) parentFeature.getRefs().get(0)).getDiagram();
				featureRef.setDiagram(featureDiagram);
				currFeature.setCoreFeature(currCoreFeature);

				mappedCoreFeatureAndFeature.put(currCoreFeature, currFeature);
				checkRelationshipType2(null, currCoreFeature.getParentRelationship(), currFeature, parentFeature);

			} else {
				// COREFeature is linked to a Feature, but no ElementLink
				// between parent Feature and child Feature
				ElementLink findedLink = null;
				for (ElementLink link : (List<ElementLink>) currFeature.getLinksSrc()) {
					if (link.getDest().equals(parentFeature)) {
						findedLink = link;
					}
				}
				boolean findPair = false;
				for (IntentionalElementRef srcRef : (List<IntentionalElementRef>) currFeature.getRefs()) {
					for (IntentionalElementRef destRef : (List<IntentionalElementRef>) parentFeature.getRefs()) {
						if (srcRef.getDiagram().equals(destRef.getDiagram())) {
							findPair = true;
						}
					}

				}
				// traverse the graphs and find no refs of currFeature and
				// parentFeature is in the same diagram
				if (!findPair) {
					IntentionalElementRef featureRef = (IntentionalElementRef) ModelCreationFactory.getNewObject(urn,
							IntentionalElementRef.class, ModelCreationFactory.FEATURE);
					featureRef.setDef(currFeature);
					IURNDiagram featureDiagram = ((IntentionalElementRef) parentFeature.getRefs().get(0)).getDiagram();
					featureRef.setDiagram(featureDiagram);
				}
				// findedLink either = null or = certain elementlink
				checkRelationshipType2(findedLink, currCoreFeature.getParentRelationship(), currFeature, parentFeature);
			}
			visitedFeatures.add(currFeature);
			checkElementName(currFeature, currCoreFeature);
			synchronizeCoreToJUCMUtil(currFeature, currCoreFeature, mappedCoreFeatureAndFeature, visitedFeatures, urn);
		}

		return;
	}

	private static void checkRelationshipType(ElementLink link, COREFeature coreFeature) {
		// synchronization is from jucm to core
		COREFeatureRelationshipType parentRelationship = null;
		if (link instanceof Decomposition) {
			DecompositionType type = ((IntentionalElement) link.getDest()).getDecompositionType();
			if (type.getValue() == DecompositionType.AND) {
				parentRelationship = COREFeatureRelationshipType.MANDATORY;
			}
			if (type.getValue() == DecompositionType.OR) {
				// if the original parentRelationship is OPTIONAL it will change
				// its parentRelationshipType
				// from OPTIONAL to OR as well
				parentRelationship = COREFeatureRelationshipType.OR;
			}
			if (type.getValue() == DecompositionType.XOR)
				parentRelationship = COREFeatureRelationshipType.XOR;
		} else if (link instanceof MandatoryFMLink) {
			parentRelationship = COREFeatureRelationshipType.MANDATORY;
		} else if (link instanceof OptionalFMLink) {
			parentRelationship = COREFeatureRelationshipType.OPTIONAL;
		}
		coreFeature.setParentRelationship(parentRelationship);
	}
	// match the link in jucm to that in core

	private static void checkRelationshipType2(ElementLink link, COREFeatureRelationshipType parentRelationship,
			IntentionalElement source, IntentionalElement target) {

		URNspec urn = source.getGrlspec().getUrnspec();
		boolean createNewLink = false;
		ElementLink newlink = null;
		if (parentRelationship == COREFeatureRelationshipType.NONE) {
			if (link != null) {
				DeleteElementLinkCommand command = new DeleteElementLinkCommand(link);
				if (command.canExecute()) {
					command.execute();
				}
			}
		} else if (parentRelationship == COREFeatureRelationshipType.MANDATORY) {
			// the original link between source and target is not
			// MandatoryFMLink, it could be OptionalFMLink or General
			// Contribution
			// type for IntentionalElement other than Feature, could be
			// Decomposition

			if (link == null || !(link instanceof MandatoryFMLink)) {

				// delete all of the ElementLinks whose sources are the siblings
				// of current source Feature
				// and the type of ElementLink is Decomposition
				ArrayList<ElementLink> replace = new ArrayList<ElementLink>(target.getLinksDest());

				for (Iterator itr = replace.iterator(); itr.hasNext();) {
					ElementLink nextLink = (ElementLink) itr.next();
					if (nextLink != link && nextLink instanceof Decomposition && nextLink.getSrc() instanceof Feature) {
						DeleteElementLinkCommand command = new DeleteElementLinkCommand(nextLink);
						if (command.canExecute()) {
							command.execute();
						}
					}
				}
				// before change the link from Decomposition to OptionalFMLink,
				// remove the DecompositionType
				// And is the default DecompositionType it is set
				if (link instanceof Decomposition) {
					target.setDecompositionType(DecompositionType.AND_LITERAL);
				}
				newlink = (MandatoryFMLink) ModelCreationFactory.getNewObject(urn, MandatoryFMLink.class);
				createNewLink = true;
			}

		} else if (parentRelationship == COREFeatureRelationshipType.OPTIONAL) {
			// if link == null will go to createNewLink
			// if link is not OptionalFMLink
			if (!(link instanceof OptionalFMLink)) {

				// delete all of the ElementLinks whose sources are the siblings
				// of current source Feature
				// and the type of ElementLink is Decomposition
				ArrayList<ElementLink> replace = new ArrayList<ElementLink>(target.getLinksDest());

				for (Iterator itr = replace.iterator(); itr.hasNext();) {
					ElementLink nextLink = (ElementLink) itr.next();
					if (nextLink != link && nextLink instanceof Decomposition && nextLink.getSrc() instanceof Feature) {
						DeleteElementLinkCommand command = new DeleteElementLinkCommand(nextLink);
						if (command.canExecute()) {
							command.execute();
						}
					}
				}
				// before change the link from Decomposition to OptionalFMLink,
				// remove the DecompositionType
				if (link instanceof Decomposition) {
					target.setDecompositionType(DecompositionType.AND_LITERAL);
				}
				newlink = (OptionalFMLink) ModelCreationFactory.getNewObject(urn, OptionalFMLink.class);
				createNewLink = true;
			}

		} else if (parentRelationship == COREFeatureRelationshipType.OR) {
			if (!(link instanceof Decomposition)) {

				// delete all of the ElementLinks whose sources are the siblings
				// of current source Feature
				// and the type of ElementLink is Contribution
				ArrayList<ElementLink> replace = new ArrayList<ElementLink>(target.getLinksDest());

				for (Iterator itr = replace.iterator(); itr.hasNext();) {
					ElementLink nextLink = (ElementLink) itr.next();
					if (nextLink != link && !(nextLink instanceof Decomposition)
							&& nextLink.getSrc() instanceof Feature) {
						DeleteElementLinkCommand command = new DeleteElementLinkCommand(nextLink);
						if (command.canExecute()) {
							command.execute();
						}
					}
				}

				(target).setDecompositionType(DecompositionType.OR_LITERAL);
				newlink = (Decomposition) ModelCreationFactory.getNewObject(urn, Decomposition.class,
						DecompositionType.OR);
				createNewLink = true;
			} else {
				DecompositionType type = (target).getDecompositionType();
				if (type.getValue() != DecompositionType.OR) {
					(target).setDecompositionType(DecompositionType.OR_LITERAL);
				}
			}
		} else if (parentRelationship == COREFeatureRelationshipType.XOR) {
			// if the original link between source and target is null or is
			// contribution
			if (!(link instanceof Decomposition)) {

				// delete all of the ElementLinks whose sources are the siblings
				// of current source Feature
				// and the type of ElementLink is Contribution
				ArrayList<ElementLink> replace = new ArrayList<ElementLink>(target.getLinksDest());

				for (Iterator itr = replace.iterator(); itr.hasNext();) {
					ElementLink nextLink = (ElementLink) itr.next();
					if (nextLink != link && !(nextLink instanceof Decomposition)
							&& nextLink.getSrc() instanceof Feature) {
						DeleteElementLinkCommand command = new DeleteElementLinkCommand(nextLink);
						if (command.canExecute()) {
							command.execute();
						}
					}
				}
				(target).setDecompositionType(DecompositionType.XOR_LITERAL);
				newlink = (Decomposition) ModelCreationFactory.getNewObject(urn, Decomposition.class,
						DecompositionType.XOR);
				createNewLink = true;
			} else {
				DecompositionType type = (target).getDecompositionType();
				if (type.getValue() != DecompositionType.XOR) {
					(target).setDecompositionType(DecompositionType.XOR_LITERAL);
				}
			}
		}
		// create new link
		if (createNewLink) {

			if (link != null) {
				DeleteElementLinkCommand delcommand = new DeleteElementLinkCommand(link);
				// stop the popup window of warning that definition shall be
				// deleted or not
				if (delcommand.canExecute()) {
					delcommand.execute();
				}
			}
			// for synchronize from core to jucm file, and test case 1
			if (link == null) {
				ArrayList<ElementLink> replaceSLink = new ArrayList<ElementLink>(source.getLinksSrc());

				for (ElementLink slink : replaceSLink) {
					if ((slink.getDest() instanceof Feature) && !slink.getDest().equals(target)) {

						DeleteElementLinkCommand delcommand = new DeleteElementLinkCommand(slink);
						// stop the popup window of warning that definition
						// shall be deleted or not
						if (delcommand.canExecute()) {
							delcommand.execute();
						}

					}
				}
			}
			// build up refs between destinated linkSource and linTarget
			// Definition are setup as well
			CreateElementLinkCommand celCmd = new CreateElementLinkCommand(urn, source, newlink, null);
			celCmd.setTarget(target);
			if (celCmd.canExecute()) {
				celCmd.execute();
			}

		}

	}

	public static void adjustParentRelationshipType(COREFeature root, COREConcern concern) {
		if (root == null)
			return;
		int MadatoryOrOptional = -1;
		ArrayList<COREFeature> XORFeatureList = new ArrayList<COREFeature>();
		ArrayList<COREFeature> ORFeatureList = new ArrayList<COREFeature>();

		for (COREFeature curr : root.getChildren()) {
			if (curr.getParentRelationship() == COREFeatureRelationshipType.MANDATORY) {
				MadatoryOrOptional = 0;
			}
			if (curr.getParentRelationship() == COREFeatureRelationshipType.OPTIONAL) {
				MadatoryOrOptional = 1;
			}
			if (curr.getParentRelationship() == COREFeatureRelationshipType.XOR) {
				XORFeatureList.add(curr);
			}
			if (curr.getParentRelationship() == COREFeatureRelationshipType.OR) {
				ORFeatureList.add(curr);
			}
		}
		if (MadatoryOrOptional == Integer.valueOf(0) || MadatoryOrOptional == Integer.valueOf(1)) {

			if (!XORFeatureList.isEmpty()) {
				COREFeature Dummy = CoreFactory.eINSTANCE.createCOREFeature();
				Dummy.setName("Dummy");
				Dummy.setParent(root);
				Dummy.setParentRelationship(COREFeatureRelationshipType.MANDATORY);
				root.getChildren().remove(XORFeatureList);
				root.getChildren().add(Dummy);
				concern.getFeatureModel().getFeatures().add(Dummy);
				for (int i = 0; i < XORFeatureList.size(); i++) {
					XORFeatureList.get(i).setParent(Dummy);
				}
			}
			if (!ORFeatureList.isEmpty() && MadatoryOrOptional == Integer.valueOf(0)) {

				COREFeature Dummy1 = CoreFactory.eINSTANCE.createCOREFeature();
				Dummy1.setName("Dummy");
				Dummy1.setParent(root);
				Dummy1.setParentRelationship(COREFeatureRelationshipType.MANDATORY);
				root.getChildren().remove(ORFeatureList);
				root.getChildren().add(Dummy1);
				concern.getFeatureModel().getFeatures().add(Dummy1);
				for (int i = 0; i < ORFeatureList.size(); i++) {
					ORFeatureList.get(i).setParent(Dummy1);
				}

			}
			if (!ORFeatureList.isEmpty() && MadatoryOrOptional == Integer.valueOf(1)) {
				for (COREFeature curr : root.getChildren()) {
					if (curr.getParentRelationship() == COREFeatureRelationshipType.OPTIONAL) {
						curr.setParentRelationship(COREFeatureRelationshipType.OR);
					}
				}
			}
		}

	}

	/*
	 * create root Feature of urnspec if it doesn't exist
	 * 
	 * @param URNspec urn: to which urn the root Feature resides
	 * 
	 * @param String rootName: the name of the root Feature
	 */
	public static void createRootFeature(URNspec urn, String rootName) {
		if (findRootFeature(urn) != null) {
			return;
		} else {
			// root feature doesn't exist
			IntentionalElementRef featureRef = (IntentionalElementRef) ModelCreationFactory.getNewObject(urn,
					IntentionalElementRef.class, ModelCreationFactory.FEATURE);
			Feature rootFeature = (Feature) featureRef.getDef();
			rootFeature.setName(rootName);
			MetadataHelper.addMetaData(urn, rootFeature, "CoURN", "rootFeature");
			urn.getGrlspec().getIntElements().add(rootFeature);
			boolean addFeatureRef = false;
			for (IURNDiagram diagram : (List<IURNDiagram>) urn.getUrndef().getSpecDiagrams()) {
				if (diagram instanceof FeatureDiagram) {

					featureRef.setDiagram(diagram);
					addFeatureRef = true;
				}

			}
			if (!addFeatureRef) {
				FeatureDiagram fd = (FeatureDiagram) ModelCreationFactory.getNewObject(urn, FeatureDiagram.class);
				featureRef.setDiagram(fd);
				urn.getUrndef().getSpecDiagrams().add(fd);
			}

		}

	}

	public static Feature findRootFeature(URNspec urn) {

		for (IntentionalElement element : (List<IntentionalElement>) urn.getGrlspec().getIntElements()) {
			if (element instanceof Feature) {
				String value = MetadataHelper.getMetaData(element, "CoURN");
				if (value != null && value.equalsIgnoreCase("root feature")) {
					return (Feature) element;
				}
			}
		}
		return null;
	}

	public static boolean getDirection() {
		return synchronizeDirection;
	}

	public static void setSynchronizeDirection(boolean direction) {
		synchronizeDirection = direction;
	}

	public static void setUp(URNspec urn, String coreFileName, String filePath, boolean coreToJUCM) {
		setSynchronizeDirection(coreToJUCM);

		// Initialize ResourceManager (will initialize OCL).
		ResourceManager.initialize(false, false);
		// Initialize packages.
		// CorePackage.eINSTANCE.eClass();
		// RamPackage.eINSTANCE.eClass();
		// Register resource factories.
		ResourceManager.registerExtensionFactory(CORE_FILE_EXTENSION, new CoreResourceFactoryImpl());

		// Initialize adapter factories.
		// AdapterFactoryRegistry.INSTANCE.addAdapterFactory(RamItemProviderAdapterFactory.class);
		// AdapterFactoryRegistry.INSTANCE.addAdapterFactory(CoreItemProviderAdapterFactory.class);
		COREConcern concern = null;

		try {
			concern = (COREConcern) ResourceManager.loadModel(filePath + "." + CORE_FILE_EXTENSION);
			// concern = (COREConcern) (new UrnModelManager()).loadModel(filePath + "." +Constants.CORE_FILE_EXTENSION);
			
			if (concern != null) {
				Concern current = (Concern) urn.getUrndef().getConcerns().get(0);
				if (current.getCoreConcern() != null) {
					COREConcern concern1 = ((Concern) urn.getUrndef().getConcerns().get(0)).getCoreConcern();
					concern = concern1;
				} else {
					current.setCoreConcern(concern);
				}

			}
		} catch (Exception e1) {
			e1.printStackTrace();
			System.out.println("the corresponding model is not created, doesn't exist");

			File file = new File(filePath.concat(".core")); //$NON-NLS-1$

			if (!file.exists()) {
				try {

					file.createNewFile();

				} catch (Exception e2) {
					e2.printStackTrace();
					System.out.println("The core file is not created successfully");  //$NON-NLS-1$
				}
				String fileName = filePath.substring(filePath.lastIndexOf("\\") + 1);      //$NON-NLS-1$
				concern = COREModelUtil.createConcern(fileName + "." + CORE_FILE_EXTENSION); //$NON-NLS-1$
				String rootFeatureName = fileName.substring(0, 1).toUpperCase() + fileName.substring(1);

				concern.getFeatureModel().getRoot().setName(rootFeatureName);
				urn.getGrlspec().getFeatureModel().setCoreFeatureModel(concern.getFeatureModel());
				((Concern) urn.getUrndef().getConcerns().get(0)).setCoreConcern(concern);

				Feature intElement = CoreSynchronizeHelper.findRootFeature(urn);
				if (intElement == null) {
					// root feature doesn't exist
					IntentionalElementRef featureRef = (IntentionalElementRef) ModelCreationFactory.getNewObject(urn,
							IntentionalElementRef.class, ModelCreationFactory.FEATURE);
					Feature rootFeature = (Feature) featureRef.getDef();
					rootFeature.setName(rootFeatureName);
					MetadataHelper.addMetaData(urn, rootFeature, "CoURN", "rootFeature"); //$NON-NLS-1$ //$NON-NLS-1$
					urn.getGrlspec().getIntElements().add(rootFeature);
					boolean addFeatureRef = false;
					for (IURNDiagram diagram : (List<IURNDiagram>) urn.getUrndef().getSpecDiagrams()) {
						if (diagram instanceof FeatureDiagram) {

							featureRef.setDiagram(diagram);
							addFeatureRef = true;
						}

					}
					if (!addFeatureRef) {
						FeatureDiagram fd = (FeatureDiagram) ModelCreationFactory.getNewObject(urn,
								FeatureDiagram.class);
						featureRef.setDiagram(fd);
						urn.getUrndef().getSpecDiagrams().add(fd);
					}

				}
				intElement.setCoreFeature(concern.getFeatureModel().getRoot());
				doSave(concern, filePath.concat(".core")); //$NON-NLS-1$
			} else {
				System.out.println("The file is created but not catched by resourcemanager");
			}

		}
		// start to synchronize
		synchronizeJUCMWithCore(urn, concern, filePath);
	}
 
	// Save Concern file to the specified path directly if the concern has been loaded 
	// Otherwise, save the concern onto its containing resource
	public static void doSave(COREConcern concern, String pullPath) {

		// ResourceSet resourceSetImpl = new ResourceSetImpl();
		// ((ResourceSetImpl) resourceSetImpl).setURIResourceMap(new
		// HashMap<URI, Resource>());

		// Resource resource =
		// resourceSetImpl.getResource(URI.createFileURI(pullPath),false);
		Resource resource = concern.eResource();
		if (resource == null) {
			try {
				ResourceManager.saveModel(concern, pullPath);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {

			try {
				concern.eResource().save(Collections.EMPTY_MAP);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
