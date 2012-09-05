package seg.jUCMNav.aourn.matcher;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.PlatformUI;

import ucm.map.Connect;
import ucm.map.DirectionArrow;
import ucm.map.EmptyPoint;
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
import urn.URNspec;
import urncore.URNmodelElement;

public class MatchableElementFactory {
	
	// cached list of all MatchableElements that have been created
	// only one MatchableElement subtype must be created for each element
	// (i.e. one element can be e.g. a Joinpoint and a PointcutElement but not two Joinpoints).
	// if an element is a joinpoint and a pointcutElement at the same time, two different
	// MatchableElements are created, because in this case different mappings are allowed for the
	// element (i.e., there may be one when the element plays the role of a joinpoint, and there 
	// may be another one when the element plays the role of the pointcutElement)
	private static HashMap<String, PointcutElement> pointcutElements = new HashMap<String, PointcutElement>();
	private static HashMap<String, Joinpoint> joinpoints = new HashMap<String, Joinpoint>();
	// TODO semanticJoinpoints model the alternatives considered by enhanced matching based on semantics
	// TODO the key may have to be more than just the ID, since one element may be included in several alternative streams
	// TODO the key could be ID+stubID+pluginID
	// TODO private static HashMap<String, Joinpoint> semanticJoinpoints = new HashMap<String, Joinpoint>();

	public static List<PathNode> createMatchableElements(URNspec urn, URNmodelElement aspectMap, HashSet<UCMmap> pointcutMaps) {
		List<PathNode> pathNodes = getJoinpoints(urn, aspectMap);
		createAllJoinpoints(pathNodes);
		List<PathNode> pointcutNodes = new ArrayList<PathNode>(); 
		for (Iterator iterator = pointcutMaps.iterator(); iterator.hasNext();) {
			UCMmap pointcutMap = (UCMmap) iterator.next();
			pointcutNodes.addAll(pointcutMap.getNodes());
		}
		createPointcutElements(pointcutNodes);
		List<PathNode> filteredJoinpoints = new ArrayList<PathNode>();
		for (Iterator iterator = joinpoints.values().iterator(); iterator.hasNext();) {
			Joinpoint joinpoint = (Joinpoint) iterator.next();
			PathNode pathNode = joinpoint.getElement();
			filteredJoinpoints.add(pathNode);
		}
		return filteredJoinpoints;
	}
	
    private static List<PathNode> getJoinpoints(URNspec urn, URNmodelElement aspectMap) {
    	List<PathNode> joinpoints = new ArrayList();
   		// TODO assumes that there is only one aspect map for this aspect in the model
    	List diagrams = urn.getUrndef().getSpecDiagrams();
    	for (Iterator iter = diagrams.iterator(); iter.hasNext();) {
			URNmodelElement diagram = (URNmodelElement) iter.next();
			if (diagram instanceof UCMmap && !diagram.equals(aspectMap)) {
				// don't include the map if it is a plug-in of only pointcut stubs
				boolean include = false;
				for (int i = 0; i < ((UCMmap) diagram).getParentStub().size(); i++) {
					Stub stub = ((PluginBinding) ((UCMmap) diagram).getParentStub().get(i)).getStub();
					if (stub.getAopointcut() == PointcutKind.NONE_LITERAL) {
						// it's not a pointcut stub, so let's include the plugin
						include = true;
					}
				}
				// also include any root map
				if (include || ((UCMmap) diagram).getParentStub().size() == 0) {
					joinpoints.addAll(((UCMmap) diagram).getNodes());					
				}
			}
		} 
    	return joinpoints;
    }

	
	private static void createAllJoinpoints(List pathNodes) {
		for (Iterator iter = pathNodes.iterator(); iter.hasNext();) {
			PathNode pathNode = (PathNode) iter.next();
			if (!joinpoints.containsKey(pathNode.getId())) {
				if (!isWhitespace(pathNode)) {
					Joinpoint joinpoint = new Joinpoint(pathNode);
					joinpoints.put(pathNode.getId(), joinpoint);					
				}
			}
		}
		for (Iterator iter = joinpoints.values().iterator(); iter.hasNext();) {
			Joinpoint joinpoint = (Joinpoint) iter.next();
			joinpoint.setNeighbors();
		}
		createSemanticAlternatives();
	}

	private static void createSemanticAlternatives() {
		for (Iterator iter = joinpoints.values().iterator(); iter.hasNext();) {
			Joinpoint joinpoint = (Joinpoint) iter.next();
			PathNode pn = joinpoint.getElement();
			// TODO this should also work for static aspect markers (right now only static stubs are supported)
			// TODO this should also work for static stubs with more than one in/out-path and more than one plug-in map
			// find static stubs
			if (pn instanceof Stub && !((Stub) pn).isDynamic()) {
				// with proper in/out-bindings
				StartPoint s = null;
				EndPoint e = null;
				List pb = ((Stub) pn).getBindings();
				if (pb.size() == 1) {
					List in = ((PluginBinding) pb.get(0)).getIn(); 
					if (in.size() == 1) {
						s = ((InBinding) in.get(0)).getStartPoint();
					}
					List out = ((PluginBinding) pb.get(0)).getOut(); 
					if (out.size() == 1) {
						e = ((OutBinding) out.get(0)).getEndPoint();
					}
				}
				if (s != null && e != null) {
					// TODO this only works for one level right now - must be extended, so that a series of start points/stubs/start points/stubs 
					// TODO is supported (same for end points)
					// TODO this also needs to take recursion into account once extended to several levels!
					// TODO the case where the same plug-in map is used by multiple static stubs also needs to be considered (alternatives of two
					// TODO different path nodes are linked in that they have to be used together and not with an alternative from another group, e.g. 
					// TODO same group for going into the stub and leaving the stub, so that one isn't using an alternative for going into stub A and then
					// TODO leaving through stub B) - this is solved by redoing the whole map in the alternative stream ("before" the stub gets you in, 
					// TODO "before" the end point gets you out!) or by tagging the group of alternatives; after matching is complete one could remove the
					// TODO results that use incompatible groups
					// TODO this also should ensure that the neighbors of the alternative stream are set up correctly; semantic joinpoints must be used -
					// TODO are they unique?
					// check if the found start/end points are matchable
					Joinpoint sJoinpoint = joinpoints.get(s.getId());
					Joinpoint eJoinpoint = joinpoints.get(e.getId());
					if (sJoinpoint != null && eJoinpoint != null) {
						// find the Joinpoint before the Stub and after the Stub
						// TODO assumes that there is only one forward and one backward neighbor
						Joinpoint beforeStub;
						Joinpoint afterStub;
						if (joinpoint.getNeighbors().get(0).isForward()) {
							afterStub = (Joinpoint) joinpoint.getNeighbors().get(0).getElement();
							beforeStub = (Joinpoint) joinpoint.getNeighbors().get(1).getElement();
						} else {
							beforeStub = (Joinpoint) joinpoint.getNeighbors().get(0).getElement();
							afterStub = (Joinpoint) joinpoint.getNeighbors().get(1).getElement();
						}
						// find the Joinpoint after the start point
						Joinpoint afterStartPoint = null;
						for (int i = 0; i < sJoinpoint.getNeighbors().size(); i++) {
							MatchableNeighbor mn = sJoinpoint.getNeighbors().get(i);
							if (mn.isForward()) {
								afterStartPoint = (Joinpoint) mn.getElement();
							}
						}
						// find the Joinpoint before the end point
						Joinpoint beforeEndPoint = null;
						for (int i = 0; i < eJoinpoint.getNeighbors().size(); i++) {
							MatchableNeighbor mn = eJoinpoint.getNeighbors().get(i);
							if (!mn.isForward()) {
								beforeEndPoint = (Joinpoint) mn.getElement();
							}
						}
						// set alternative neighbors for the found matchable elements (this allows for enhanced matching based on semantics)
						// the alternative neighbor for the element before the stub is the element after the start point (it replaces the stub)
						MatchableNeighbor mnAfterStartPoint = new MatchableNeighbor(afterStartPoint.getElement(), true, false, false, joinpoint);
						beforeStub.replaceWithSemanticNeighbor(joinpoint, mnAfterStartPoint);
						// the alternative neighbor for the element after the stub is the element before the end point (it replaces the stub)
						MatchableNeighbor mnBeforeEndPoint = new MatchableNeighbor(beforeEndPoint.getElement(), false, false, false, joinpoint);
						afterStub.replaceWithSemanticNeighbor(joinpoint, mnBeforeEndPoint);
						// the alternative neighbor for the element after the start point is the element before the stub (it replaces the start point)
						MatchableNeighbor mnBeforeStub = new MatchableNeighbor(beforeStub.getElement(), false, false, false, joinpoint);
						afterStartPoint.replaceWithSemanticNeighbor(sJoinpoint, mnBeforeStub);
						// the alternative neighbor for the element before the end point is the element after the stub (it replaces the end point)
						MatchableNeighbor mnAfterStub = new MatchableNeighbor(afterStub.getElement(), true, false, false, joinpoint);
						beforeEndPoint.replaceWithSemanticNeighbor(eJoinpoint, mnAfterStub);
						MessageDialog.openConfirm(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), "Info", "Found stub " + pn.getName() + " [" + pn.getId() + "]"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
					}
				}
			}
			
		}
	}

	private static void createPointcutElements(List pathNodes) {
		for (Iterator iter = pathNodes.iterator(); iter.hasNext();) {
			PathNode pathNode = (PathNode) iter.next();
			if (!pointcutElements.containsKey(pathNode.getId())) {
				if (!isWhitespace(pathNode)) {
					PointcutElement pointcutElement = new PointcutElement(pathNode);
					pointcutElements.put(pathNode.getId(), pointcutElement);					
				}
			}			
		}
		for (Iterator iter = pointcutElements.values().iterator(); iter.hasNext();) {
			PointcutElement pointcutElement = (PointcutElement) iter.next();
			pointcutElement.setNeighbors();
		}		
	}
	
	private static boolean isWhitespace(PathNode pathNode) {
		// empty points, direction arrows, and connects need to be ignored because they are internal nodes and considered whitespace
		if (pathNode instanceof EmptyPoint || pathNode instanceof DirectionArrow || pathNode instanceof Connect) {
			return true;
		}
		// end points connected to start points without condition need to be ignored because they are considered whitespace
		if (pathNode instanceof EndPoint) {
			// check if end point is connected to a start point without a condition; if yes, ignore them
			// if an end point is connected to a start point, the Connect node is in between the two nodes
			if (pathNode.getSucc().size() > 0) {
				// there is always at the most one node after an end point
				pathNode = ((PathNode) ((NodeConnection) pathNode.getSucc().get(0)).getTarget());
				if (pathNode instanceof Connect) {
					// there is always one node after a connect
					pathNode = ((PathNode) ((NodeConnection) pathNode.getSucc().get(0)).getTarget());
					if (pathNode instanceof StartPoint && ((StartPoint) pathNode).getPrecondition().getLabel() == "") { //$NON-NLS-1$
						 return true;
					}
				}
			}
		}
		if (pathNode instanceof StartPoint && ((StartPoint) pathNode).getPrecondition().getLabel() == "") { //$NON-NLS-1$
			// check if start point without a condition is connected to an end point; if yes, ignore them
			// if an end point is connected to a start point, the Connect node is in between the two nodes
			if (pathNode.getPred().size() > 0) {
				// there is always at the most one node before a start point
				pathNode = ((PathNode) ((NodeConnection) pathNode.getPred().get(0)).getSource());
				if (pathNode instanceof Connect) {
					// there is always one node before a connect
					pathNode = ((PathNode) ((NodeConnection) pathNode.getPred().get(0)).getSource());
					if (pathNode instanceof EndPoint) {
						return true;
					}
				}				
			}
		}
		return false;
	}

	// the matchablePathNode is an already created MatchableElement which therefore already belongs to either
	// the joinpoints or the pointcutElements. the pathNode is a neighbor of the matchablePathNode and 
	// therefore belongs to the same group. 
	public static MatchableElement getMatchablePathNode(PathNode pathNode, MatchableElement matchablePathNode) {
		if (matchablePathNode instanceof Joinpoint)
			return joinpoints.get(pathNode.getId());
		else if (matchablePathNode instanceof PointcutElement)
			return pointcutElements.get(pathNode.getId());
		else
			return null;
	}

	// find a PathNode in the existing MatchableElements for pointcut elements 
	public static PointcutElement getMatchablePointcutElement(PathNode pathNode) {
			return pointcutElements.get(pathNode.getId());
	}
	
	public static PointcutElement createMatchablePointcutElement(PathNode pathNode, MatchableNeighbor neighbor) {
		if (pointcutElements.get(pathNode.getId()) != null) 
			return null;
		PointcutElement pointcutElement = new PointcutElement(pathNode);
		pointcutElement.getNeighbors().add(neighbor);
		pointcutElements.put(pathNode.getId(), pointcutElement);
		return pointcutElement;
	}
	
	public static PointcutElement getInitialMatchablePointcutElement(UCMmap pointcutMap) {
		for (Iterator iter = pointcutMap.getNodes().iterator(); iter.hasNext();) {
			PathNode pathNode = (PathNode) iter.next();
			if (pathNode instanceof StartPoint) {
				if (pointcutElements.containsKey(pathNode.getId())) {
					return pointcutElements.get(pathNode.getId());
				}
			}
		}
		return null;
	}

	public static Collection<Joinpoint> getJoinpoints() {
		return joinpoints.values();
	}

	public static Joinpoint getJoinpoint(PathNode pathNode) {
		return joinpoints.get(pathNode.getId());
	}

	public static void clearCache() {
		pointcutElements = new HashMap<String, PointcutElement>();
		joinpoints = new HashMap<String, Joinpoint>();		
	}

}
