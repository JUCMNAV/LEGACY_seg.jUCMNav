package seg.jUCMNav.aourn.composer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.PlatformUI;

import seg.jUCMNav.aourn.composer.exceptions.CompositionNotRequired;
import seg.jUCMNav.aourn.composer.exceptions.MalformedAspectMap;
import seg.jUCMNav.aourn.matcher.Joinpoint;
import seg.jUCMNav.aourn.matcher.Match;
import seg.jUCMNav.aourn.matcher.MatchList;
import seg.jUCMNav.aourn.matcher.MatchableElementFactory;
import seg.jUCMNav.aourn.matcher.MatchableNeighbor;
import seg.jUCMNav.aourn.matcher.PointcutElement;
import ucm.map.Connect;
import ucm.map.EndPoint;
import ucm.map.InBinding;
import ucm.map.NodeConnection;
import ucm.map.OutBinding;
import ucm.map.PathNode;
import ucm.map.PluginBinding;
import ucm.map.PointcutKind;
import ucm.map.StartPoint;
import ucm.map.Stub;
import ucm.map.UCMmap;
import urncore.URNmodelElement;

public class UCMAspectComposer {
	
	// contains all the mappings for each aspect marker to be added to the model given one match
	// potentially, one aspect marker is added for each in-path and out-path of the pointcut stub
	private static HashMap<InoutInfo, AspectMarkerMappings> amms;
	// contains all the in-paths and out-paths of each pointcut stub
	private static List<InoutInfo> inout;
	// list of the final results of the of the composer, contains all the information to actually 
	// insert all aspect markers; is returned to the ApplyConcernAction to enable undo
	private static List<AspectMarkerMappings> result;
	// indicates an interleaving composition
	private static boolean interleaving;
	// counter for the numbering of matches
	private static int counter = 1;
	
	// adds aspect markers to the model based on the results from a single pointcutMap of a single aspect
	public static List<AspectMarkerMappings> compose(UCMmap aspectMap, UCMmap pointcutMap, MatchList matchList) throws MalformedAspectMap, CompositionNotRequired {
		result = new ArrayList<AspectMarkerMappings>();
		boolean updateFlag = false;
		// get all the in/out-paths that have plug-in bindings with the pointcut map
		// throw exception if none such in/out-path exists; also sets interleaving
		if (!setupInout(aspectMap, pointcutMap)) {
			throw new CompositionNotRequired();
		}
		// TODO add scanning here since it only needs to be done once per aspect map!
		for (Iterator iter = matchList.getMatchList().iterator(); iter.hasNext();) {
			Match match = (Match) iter.next();
			amms = new HashMap<InoutInfo, AspectMarkerMappings>();
			// Step 0 - Preprocessing
			composeUCMPreprocessing(match, pointcutMap);
			// Step 1
			composeUCMStep1(match, counter++, aspectMap);
			// Step 2 (may throw exception)
			composeUCMStep2(counter);
			// Step 3
			composeUCMStep3();
			copyResultsForThisMatch();
			updateFlag = true;
		}
		if (!updateFlag) {
			throw new CompositionNotRequired();
		}
		return result;
	}

	private static boolean setupInout(UCMmap aspectMap, UCMmap pointcutMap) throws MalformedAspectMap {
		inout = new ArrayList<InoutInfo>();
		int counter = 0;
		for (Iterator iter = aspectMap.getNodes().iterator(); iter.hasNext();) {
			PathNode pathnode = (PathNode) iter.next();
			// needs to be a pointcut stub with at least one plug-in map (i.e., pointcut map)
			if (pathnode instanceof Stub && !((Stub) pathnode).getAopointcut().equals(PointcutKind.NONE_LITERAL) && ((Stub) pathnode).getBindings().size() > 0) {
				counter++;
				for (Iterator iterator = ((Stub) pathnode).getBindings().iterator(); iterator.hasNext();) {
					PluginBinding pl = (PluginBinding) iterator.next();
					// only setup the inout for the pointcut map that is currently composed!
					if (pl.getPlugin().equals(pointcutMap)) {
						List inBindings = pl.getIn();
						List outBindings = pl.getOut();
						boolean replacement = ((Stub) pathnode).getAopointcut() == PointcutKind.REPLACEMENT_LITERAL;
						// validity checks for replacement pointcut stub
						if (replacement && !validReplacementAspectMap((Stub) pathnode, inBindings, outBindings)) {
							throw new MalformedAspectMap();
						}
						InoutInfo inoutInfo;
						for (Iterator iter2 = inBindings.iterator(); iter2.hasNext();) {
							InBinding inBinding = (InBinding) iter2.next();
							inoutInfo = new InoutInfo(inBinding.getStubEntry(), inBinding, null, replacement);
							inout.add(inoutInfo);
						}
						for (Iterator iter3 = outBindings.iterator(); iter3.hasNext();) {
							OutBinding outBinding = (OutBinding) iter3.next();
							inoutInfo = new InoutInfo(outBinding.getStubExit(), null, outBinding, replacement);
							inout.add(inoutInfo);
						}
					}
				}
			}
		}
		interleaving = counter > 1;
		return counter > 0;
	}
	
	// a replacement pointcut stub is only allowed to have one in/out-path and must be preceded by only a start point
	private static boolean validReplacementAspectMap(Stub stub,	List inBindings, List outBindings) {
		if (inBindings.size() != 1 || outBindings.size() != 1)
			return false;
		PathNode pn = (PathNode) ((NodeConnection) stub.getPred().get(0)).getSource(); 
		if (!(pn instanceof StartPoint) || pn.getPred().size() != 0) 
			return false;
		return true;
	}

	// TODO refactor detection of whitespace (see also MatchableElement) to reduce duplication
	private static void composeUCMPreprocessing(Match match, UCMmap pointcutMap) {
		// TODO this is still only working for one pointcut map
		if (interleaving) {
			for (Iterator iter = pointcutMap.getNodes().iterator(); iter.hasNext();) {
				PathNode pn = (PathNode) iter.next();
				// find all end points connected to a start point without a condition
				if (pn instanceof EndPoint) {
					// check if end point is connected to a start point without a condition
					if (pn.getSucc().size() > 0) {
						// there is always at the most one node after an end point
						PathNode pn2 = ((PathNode) ((NodeConnection) pn.getSucc().get(0)).getTarget());
						if (pn2 instanceof Connect) {
							// there is always one node after a connect
							PathNode pn3 = ((PathNode) ((NodeConnection) pn2.getSucc().get(0)).getTarget());
							if (pn3 instanceof StartPoint && ((StartPoint) pn3).getPrecondition().getLabel() == "") { //$NON-NLS-1$
								// found such end point - now create mappings for end/start point pair based on their neighbors
								match.duplicateMappings(pn, pn3);							
							}
						}
					}
				}
			}
		}
	}
	
	private static void composeUCMStep1(Match match, int counter, UCMmap aspectMap) {
		for (int i = 0; i < inout.size(); i++) {
			AspectMarkerMappings amm = new AspectMarkerMappings(counter, aspectMap);
			// the pointcutElement is either a start or an end point on the pointcut map
			PathNode pointcutElement = inout.get(i).getPointcutElement();
			// a MatchableElement corresponding to a start point or end point has exactly one matchable neighbor, get both
			// the matchable element and the matchable neighbor and then find the mappings with these elements in the match
			PointcutElement pce = MatchableElementFactory.getMatchablePointcutElement((PathNode) pointcutElement);
			MatchableNeighbor mn = pce.getNeighbors().get(0);
			// find the mapping with this neighbor, get its joinpoint, and the first mapping is complete!
			// but take the anything pointcut element into account!
			Joinpoint joinpointNeighbor = match.findMapping((PointcutElement) mn.getElement(), pointcutElement instanceof StartPoint).getJoinpoint();
			amm.setFirstMapping(joinpointNeighbor.getElement(), inout.get(i));
			// find the mapping of the matchable element corresponding to a start/end point and get its joinpoint
			Joinpoint joinpoint = match.findMapping((PointcutElement) pce).getJoinpoint();
			// the insertion point is the NodeConnection closest to the joinpoint that is between the joinpoint and the joinpointNeighbor;
			// if the pointcut element is an end point, then the path direction from the joinpointNeighbor to the jointpoint (matched to the end point)
			// is forward; otherwise, it is backward
			NodeConnection insertionPoint = findInsertionPoint(joinpoint, joinpointNeighbor, pointcutElement instanceof EndPoint);
			amm.setInsertionPoint(insertionPoint);
			amm.setReplacement(inout.get(i).isReplacement());
			amms.put(inout.get(i), amm);
		}
	}

	private static NodeConnection findInsertionPoint(Joinpoint joinpoint, Joinpoint joinpointNeighbor, boolean forward) {
		List<NodeConnection> candidates = new ArrayList<NodeConnection>();
		List<PathNode> currentPathNodes = new ArrayList<PathNode>();
		// look at all node connections of the joinpoint in either forward or backward direction and check if one points towards the joinpointNeighbor
		// if the neighbor cannot be found, check the neighbors two hops away, and so on until the neighbor is found
		// guarantee: both elements are always on the same map - for normal matching this is a given, enhanced matching based on semantics ensures
		//            that a start/end of the pointcut expression is always on the same map as the next/previous element
		// guarantee: there is always a straight path between the joinpoint and the joinpointNeighbor
		if (forward) {
			candidates.addAll(joinpointNeighbor.getElement().getSucc());
			for (int i = 0; i < candidates.size(); i++) {
				currentPathNodes.add((PathNode) ((NodeConnection) candidates.get(i)).getTarget());
			}
		} else {
			candidates.addAll(joinpointNeighbor.getElement().getPred());
			for (int i = 0; i < candidates.size(); i++) {
				currentPathNodes.add((PathNode) ((NodeConnection) candidates.get(i)).getSource());
			}		}
		int foundCandidate = checkSetofNeighbors(currentPathNodes, forward, joinpoint);
		return candidates.get(foundCandidate);
	}

	private static int checkSetofNeighbors(List<PathNode> currentPathNodes, boolean forward, Joinpoint joinpoint) {
		List<PathNode> newPathNodes = new ArrayList<PathNode>();
		for (int i = 0; i < currentPathNodes.size(); i++) {
			PathNode pn = currentPathNodes.get(i);
			if (pn.equals(joinpoint.getElement())) {
				return i;
			} else {
				// get the next candidates but only if we're not at the end/start of a path
				if (forward) {
					if (pn.getSucc().size() > 0) {
						newPathNodes.add((PathNode) ((NodeConnection) pn.getSucc().get(0)).getTarget());	
					}
				} else {
					if (pn.getPred().size() > 0) {
						newPathNodes.add((PathNode) ((NodeConnection) pn.getPred().get(0)).getSource());						
					}
				}
			}
		}
		assert (newPathNodes.size() > 0) : "internal error"; //$NON-NLS-1$
		return checkSetofNeighbors(newPathNodes, forward, joinpoint);
	}
	
	private static void composeUCMStep2(int counter) throws MalformedAspectMap {
		boolean showInfoMessages = false;
		// TODO assumes that there is only one pointcut stub on the aspect map
		// find all start/end points and pointcut stubs reachable from an in/out-path of the pointcut stub
		List<HashSet<PathNode>> elList = new ArrayList<HashSet<PathNode>>();
		for (int i = 0; i < inout.size(); i++) {
			HashSet<PathNode> elSet = new HashSet<PathNode>();
			elSet.addAll(scanAspectMap(inout.get(i)));
			elList.add(elSet);
		}
		String capture;
		if (counter == 2) {
			capture = capture(elList);
			if (showInfoMessages)
				MessageDialog.openConfirm(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), "Scanning Result", capture); //$NON-NLS-1$ //$NON-NLS-2$
		}
        // now find the elements for the second mapping and create them!
        createSecondMappings(elList);
        if (counter == 2) {
        	capture = capture();
        	if (showInfoMessages)
        		MessageDialog.openConfirm(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), "Second Mappings", capture); //$NON-NLS-1$ //$NON-NLS-2$
        }
	}

	private static HashSet<PathNode> scanAspectMap(InoutInfo inout) {
		boolean forward = inout.getScanDirection();
		PathNode startNode;
		if (forward) {
			startNode = (PathNode) inout.getInoutPath().getTarget();
		} else {
			startNode = (PathNode) inout.getInoutPath().getSource();
		}
		HashSet<PathNode> candidates = new HashSet<PathNode>();
		candidates.add(startNode);
		HashSet<PathNode> visitedNodes = new HashSet<PathNode>();
		visitedNodes.add(startNode);
		HashSet<PathNode> result = new HashSet<PathNode>();
		while (!candidates.isEmpty()) {
			HashSet<PathNode> newCandidates = new HashSet<PathNode>();
			HashSet<PathNode> filteredCandidates = new HashSet<PathNode>();
			for (Iterator iter = candidates.iterator(); iter.hasNext();) {
				PathNode e = (PathNode) iter.next();
				if (e instanceof StartPoint && (((StartPoint) e).getSucc().size() + ((StartPoint) e).getPred().size() == 1)) {
					// ignore local start points
					if (!((StartPoint) e).isLocal()) {
						result.add(e);						
					}
				} else if (e instanceof EndPoint && (((EndPoint) e).getSucc().size() + ((EndPoint) e).getPred().size() == 1)) {
					// ignore local end points
					if (!((EndPoint) e).isLocal()) {
						result.add(e);						
					}
				} else if (e instanceof Stub && ((Stub) e).getAopointcut() != PointcutKind.NONE_LITERAL) {
					result.add(e);
				} else {
					if (forward) {
						for (int i = 0; i < e.getSucc().size(); i++) {
							newCandidates.add((PathNode) ((NodeConnection) e.getSucc().get(i)).getTarget());
						}
					} else {
						for (int i = 0; i < e.getPred().size(); i++) {
							newCandidates.add((PathNode) ((NodeConnection) e.getPred().get(i)).getSource());
						}
					}
					// make sure that a candidate node has not been visited before during the scan
					// to avoid going into an infinite loop, add only non-visited candidates to the
					// filteredCandidates
					for (Iterator iter2 = newCandidates.iterator(); iter2.hasNext();) {
						PathNode candidate = (PathNode) iter2.next();
						if (!visitedNodes.contains(candidate)) {
							filteredCandidates.add(candidate);
							visitedNodes.add(candidate);							
						}
					}
				}
			}
			candidates.clear();
			candidates.addAll(filteredCandidates);
		}
		return result;
	}
	
	private static void createSecondMappings(List<HashSet<PathNode>> elList) throws MalformedAspectMap {
		// check if any inout does not have any start/end points and pointcut stubs resulting from the scan
		// if yes, the aspect map is malformed
		for (int i = 0; i < elList.size(); i++) {
			HashSet<PathNode> elSet = elList.get(i);
			if (elSet.size() == 0) {
				throw new MalformedAspectMap();
			} else {
				// remove all pointcut stubs from the set as they are not needed anymore
				// this makes the processing in the do/while loop below easier
				HashSet<PathNode> toberemoved = new HashSet<PathNode>();
				for (Iterator iter = elSet.iterator(); iter.hasNext();) {
					PathNode pn = (PathNode) iter.next();
					if (pn instanceof Stub) {
						toberemoved.add(pn);
					}
				}
				elSet.removeAll(toberemoved);
			}
		}
		boolean nothingChanged = true;
		boolean allDone = true;
		do {
			nothingChanged = true;
			allDone = true;
			HashSet<PathNode> claimed = new HashSet<PathNode>();
			for (int i = 0; i < elList.size(); i++) {
				HashSet<PathNode> elSet = elList.get(i);
				// Only try to create mapping, if we have not yet found the mapping.
				if (!amms.get(inout.get(i)).isAllCreated()) {
					// Attempt to create the second mapping based on the elements returned by the scan:
					// can be done if there is only one start/end point in the set
					if (elSet.size() == 1) {
						// create second mapping, remember the claimed PathNode (setSecondMapping sets isAllCreated to true)
						PathNode el = elSet.iterator().next();
						amms.get(inout.get(i)).setSecondMapping(el);
						claimed.add(el);
						nothingChanged = false;
					} else if (elSet.size() == 0) {
						// no second mapping needs to be created; calling setSecondMapping anyway ensures that isAllCreated is set to true!
						amms.get(inout.get(i)).setSecondMapping(null);
					} else {
						// If we get to this point, we were not successful in creating the required 
						// second mapping and we are not yet done with this inout
						allDone = false;
					}
				}
			}
			// If unsuccessful, remove those start and end points claimed by other mappings in this loop.
			// This will allow other mappings to be created in the next loop.
			if (!allDone && !nothingChanged) {
				for (int i = 0; i < elList.size(); i++) {
					HashSet<PathNode> elSet = elList.get(i);
					elSet.removeAll(claimed);
				}
			}
		} while (!allDone && !nothingChanged);
		// If there are still in/out-paths for which all required mappings could not be established, the aspect map is malformed.
		if (!allDone) {
			throw new MalformedAspectMap();
		}
	}
		
	private static void composeUCMStep3() {
		// nothing to do at the moment
	}

	private static void copyResultsForThisMatch() {
		for (Iterator iterator = amms.values().iterator(); iterator.hasNext();) {
			AspectMarkerMappings a = (AspectMarkerMappings) iterator.next();
			result.add(a);
		}
	}
	
	public static void resetCounter() {
		counter = 1;
	}

	public static void dispose() {
		if (result != null)
			result.clear();
		result = null;
		if (amms != null)
			amms.clear();
		amms = null;
		if (inout != null)
			inout.clear();
		inout = null;
	}

	private static String capture(List<HashSet<PathNode>> result) {
		String capture = ""; //$NON-NLS-1$
		for (int i = 0; i < result.size(); i++) {
			capture += i+1 + ": "; //$NON-NLS-1$
			for (Iterator iter = result.get(i).iterator(); iter.hasNext();) {
				URNmodelElement e = (URNmodelElement) iter.next();
				capture += e.getName() + "[" + e.getId() +"]   "; //$NON-NLS-1$ //$NON-NLS-2$
			}
			capture += "     -----     "; //$NON-NLS-1$
		}
		return capture;
	}

	private static String capture() {
		String capture = ""; //$NON-NLS-1$
		for (int i = 0; i < inout.size(); i++) {
			capture += i+1 + ": "; //$NON-NLS-1$
			PathNode pn = amms.get(inout.get(i)).getSecondMapping();
			if (pn != null)
				capture += pn.getName() + "[" + pn.getId() +"]   "; //$NON-NLS-1$ //$NON-NLS-2$
			capture += "     -----     "; //$NON-NLS-1$
		}
		return capture;
	}
	
}
