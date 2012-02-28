package seg.jUCMNav.aourn.matcher;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.PlatformUI;

import seg.jUCMNav.Messages;
import seg.jUCMNav.aourn.matcher.exceptions.ContradictoryMappingException;
import seg.jUCMNav.aourn.matcher.exceptions.DuplicateMappingException;
import ucm.map.Anything;
import ucm.map.NodeConnection;
import ucm.map.PathNode;

public class Match {

	private List<Mapping> match = new ArrayList<Mapping>();
	
	public Match() {
	}
	
	public Match(Match match) {
		this.match = new ArrayList<Mapping>();
		for (Iterator iter = match.getMatch().iterator(); iter.hasNext();) {
			Mapping mapping = (Mapping) iter.next();
			this.match.add(mapping);
		}
	}

	public List<Mapping> getMatch() {
		return match;
	}
	
	// duplicate mappings are ignored
	// the original newMappings is returned without the duplicate mappings 
	// exception is thrown if mapping is added that contradicts an already existing mapping in mappings
	public Match add(Match newMatch) throws ContradictoryMappingException {
		Match resultMatch = new Match(newMatch);
		for (Iterator iter = newMatch.getMatch().iterator(); iter.hasNext();) {
			Mapping newMapping = (Mapping) iter.next();
			try {
				add(newMapping);
			} catch (DuplicateMappingException e) {
				// duplicates do not need to be considered further
				resultMatch.getMatch().remove(newMapping);
			}
		}
		return resultMatch;
	}
	
	// compare each existing mapping in mappings with the newMapping
	// exceptions are thrown if newMapping is a duplicate or contradictory mapping
	public void add(Mapping newMapping) throws DuplicateMappingException, ContradictoryMappingException { 
		for (Iterator iter = match.iterator(); iter.hasNext();) {
			Mapping currentMapping = (Mapping) iter.next();
			boolean identicalJoinpoint = false;
			boolean identicalPointcutElement = false;
			if (currentMapping.getJoinpoint().getId().equals(newMapping.getJoinpoint().getId()))
				identicalJoinpoint = true;
			if (currentMapping.getPointcutElement().getId().equals(newMapping.getPointcutElement().getId()))
				identicalPointcutElement = true;
			if (identicalJoinpoint && identicalPointcutElement) {
				if (currentMapping.getPointcutElement().getElement() instanceof Anything) {
					// a duplicate mapping for an anything pointcut element indicates a loop because the anything pointcut element is only explored in
					// one direction (therefore, the previous mapping is never tested again and cannot result in a duplicate)
					// loop, however, are not not allowed as the match has to be the shortest path; therefore, the mapping is contradictory
					// on the other hand, if there is a loop on the pointcut map, then a duplicate mapping can occur with an anything pointcut element
					// this case can be differentiated by looking at the first/last indicators: if they are the same, then the duplicate is allowed
					if (newMapping.isFirst() == currentMapping.isFirst() && newMapping.isLast() == currentMapping.isLast()) {
						throw new DuplicateMappingException();
					}
					MessageDialog.openConfirm(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), "Info", "Disallowed anything loop!"); //$NON-NLS-1$ //$NON-NLS-2$
					throw new ContradictoryMappingException();
				}
				else {
					throw new DuplicateMappingException();
				}
			}
			if (identicalJoinpoint || identicalPointcutElement) {
				// consider special valid case where the border startpoint and border endpoint of the pointcut map are mapped to the same base path node
				if (!(identicalJoinpoint && currentMapping.getPointcutElement().isPointcutStartOrEndPoint() &&
						newMapping.getPointcutElement().isPointcutStartOrEndPoint())) {
					// consider special case of anything pointcut element which may be matched multiple times
					if (!(identicalPointcutElement && currentMapping.getPointcutElement().getElement() instanceof Anything)) {
						throw new ContradictoryMappingException();						
					}
				}
			}
		}
		match.add(newMapping);
	}
	
	// given a pointcutElement, finds the mapping with this pointcutElement in the match
	public Mapping findMapping(PointcutElement pointcutElement) {
		for (int i = 0; i < getMatch().size(); i++) {
			if (getMatch().get(i).getPointcutElement().equals(pointcutElement)) {
				return getMatch().get(i);
			}
		}
		return null;
	}

	// takes the anything pointcut element into account when finding a mapping
	public Mapping findMapping(PointcutElement pointcutElement, boolean forward) {
		// consider the special case for the anything pointcut element since many mappings may exist for it in the match
		// if the direction is forward, then the first mapping of the anything pointcut element is returned;
		// if the direction is backward, then the last mapping of the anything pointcut element is returned;
		if (pointcutElement instanceof Anything) {
			for (int i = 0; i < getMatch().size(); i++) {
				if (getMatch().get(i).getPointcutElement().equals(pointcutElement)) {
					if ((getMatch().get(i).isFirst() && forward) || (getMatch().get(i).isLast() && !forward))
						return getMatch().get(i);
				}
			}			
		} else {
			return findMapping(pointcutElement);
		}
		return null;
	}

	// given a connected end/start point pair from the pointcut map, find the first adjacent path nodes for the end and start point that has a mapping
	// create a duplicate mapping for the start point based on the mapping of the path node before the end point
	// create a duplicate mapping for the end point based on the mapping of the path node after the start point
	public void duplicateMappings(PathNode endPoint, PathNode startPoint) {
		PathNode pnAfterStartPoint = startPoint;
		Mapping mAfterStartPoint = null;
		PathNode nextPathNode;
		boolean found = false;
		while (!found) {
			nextPathNode = (PathNode) ((NodeConnection) pnAfterStartPoint.getSucc().get(0)).getTarget();
			// get the PointcutElement of the next path node and try to find its mapping
			PointcutElement pe = MatchableElementFactory.getMatchablePointcutElement(nextPathNode);
			if (pe != null) {
				Mapping mapping = findMapping(pe);
				if (mapping != null) {
					found = true;
					mAfterStartPoint = mapping;
				}
			}
			pnAfterStartPoint = nextPathNode;
		}
		PathNode pnBeforeEndPoint = endPoint;
		Mapping mBeforeEndPoint = null;
		found = false;
		while (!found) {
			nextPathNode = (PathNode) ((NodeConnection) pnBeforeEndPoint.getPred().get(0)).getSource();
			// get the PointcutElement of the next path node and try to find its mapping
			PointcutElement pe = MatchableElementFactory.getMatchablePointcutElement(nextPathNode);
			if (pe != null) {
				Mapping mapping = findMapping(pe);
				if (mapping != null) {
					found = true;
					mBeforeEndPoint = mapping;
				}
			}
			pnBeforeEndPoint = nextPathNode;
		}
		// create the new mappings - the process assumes that start/end points on pointcut maps have exactly one neighbor!
		// the pointcut element created for the end point must have one "backward" neighbor
		MatchableNeighbor mnEndPoint = new MatchableNeighbor(mBeforeEndPoint.getPointcutElement().getElement(), false, false, false, mBeforeEndPoint.getPointcutElement());
		PointcutElement peEndPoint = MatchableElementFactory.createMatchablePointcutElement(endPoint, mnEndPoint);
		Mapping mappingForEndPoint = new Mapping(peEndPoint, mAfterStartPoint.getJoinpoint());
		// the pointcut element created for the start point must have one "forward" neighbor
		MatchableNeighbor mnStartPoint = new MatchableNeighbor(mAfterStartPoint.getPointcutElement().getElement(), true, false, false, mAfterStartPoint.getPointcutElement());
		PointcutElement peStartPoint = MatchableElementFactory.createMatchablePointcutElement(startPoint, mnStartPoint);
		Mapping mappingForStartPoint = new Mapping(peStartPoint, mBeforeEndPoint.getJoinpoint());
		// add them to the match (without checking for duplicates/contradictions since there will be some - not relevant anymore)
		match.add(mappingForEndPoint);
		MessageDialog.openConfirm(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), Messages.getString("Match.Info"), Messages.getString("Match.AddedMappingForEndPoint") + mappingForEndPoint.getPointcutElement().getId() + Messages.getString("Match.CloseBracketTo") + mappingForEndPoint.getJoinpoint().getName() + "[" + mappingForEndPoint.getJoinpoint().getId() + "]!"); 		 //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
		match.add(mappingForStartPoint);
		MessageDialog.openConfirm(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), Messages.getString("Match.Info"), Messages.getString("Match.AddedMappingForStartPoint") + mappingForStartPoint.getPointcutElement().getId() + Messages.getString("Match.CloseBracketTo") + mappingForStartPoint.getJoinpoint().getName() + "[" + mappingForStartPoint.getJoinpoint().getId() + "]!");  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
	}
	
}
