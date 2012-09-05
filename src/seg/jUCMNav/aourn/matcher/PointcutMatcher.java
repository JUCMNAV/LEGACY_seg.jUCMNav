package seg.jUCMNav.aourn.matcher;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seg.jUCMNav.aourn.matcher.exceptions.ContradictoryMappingException;
import seg.jUCMNav.aourn.matcher.exceptions.DuplicateMappingException;
import seg.jUCMNav.aourn.matcher.exceptions.IncompatibleDirectionsException;
import seg.jUCMNav.aourn.matcher.exceptions.MatchingFailedException;
import ucm.map.Anything;
import ucm.map.ComponentRef;
import ucm.map.PathNode;
import ucm.map.RespRef;
import ucm.map.UCMmap;
import urncore.IURNContainer;
import urncore.Metadata;
import urncore.Responsibility;
import urncore.URNmodelElement;

public class PointcutMatcher {

	// matches a single pointcutMap against a list of pathNodes from the base model
	public static MatchList match(UCMmap pointcutMap, List pathNodes) throws MatchingFailedException {
		MatchList result = new MatchList();
		PointcutElement pointcutElement = MatchableElementFactory.getInitialMatchablePointcutElement(pointcutMap);
		assert pointcutElement != null : "PointcutElement does not exist"; //$NON-NLS-1$
		for (Iterator iterator = pathNodes.iterator(); iterator.hasNext();) {
			PathNode pathNode = (PathNode) iterator.next();
			Joinpoint joinpoint = MatchableElementFactory.getJoinpoint(pathNode);			
//		for (Iterator iter = MatchableElementFactory.getJoinpoints().iterator(); iter.hasNext();) {
//			Joinpoint joinpoint = (Joinpoint) iter.next();
			if (matchElement(pointcutElement, joinpoint)) {
				Match match = new Match();
				Mapping mapping = null;
				try {
					mapping = new Mapping(pointcutElement, joinpoint);
					match.add(mapping);
				} catch (Exception e1) {
					// cannot happen because it is the first mapping being added to the match
					assert true == false : "Internal error"; //$NON-NLS-1$
				}
				try {
					MatchList matches = matchNeighbors(mapping, null, match);
					result.getMatchList().addAll(matches.getMatchList());
				} catch (MatchingFailedException e) {
					// continue with the next joinpoint, nothing is added to result
				}
			}
		}
		if (result.getMatchList().isEmpty())
			throw new MatchingFailedException();
		return result;
	}
	
	public static boolean matchNeighbor(MatchableNeighbor pointcutNeighbor, MatchableNeighbor joinpointNeighbor) throws IncompatibleDirectionsException {
		// check whether the direction of links between elements matches and then match the actual elements
		// if the neighbor is on a "timeout" path, then allow it to be matched against a pointcut element that is not on a "timeout" path if this 
		// pointcut element is a forward neighbor of a pointcut start point
		if (pointcutNeighbor.isForward() == joinpointNeighbor.isForward() && pointcutNeighbor.isConnect() == joinpointNeighbor.isConnect() && 
				(pointcutNeighbor.isTimeout() == joinpointNeighbor.isTimeout() || pointcutNeighbor.isFromPointcutStartPoint())) {
			return matchElement((PointcutElement) pointcutNeighbor.getElement(), (Joinpoint) joinpointNeighbor.getElement());
		}
		throw new IncompatibleDirectionsException();
	}
	
	// always returns true if matching the beginning/end of a pointcut expression (pointcut start/end points)
	// always returns true if the anything pointcut element is matched
	// returns true if the following matches:
	//		type of pointcutElement/joinpoint
	//		name of pointcutElement/joinpoint (*, exact match, and regular expression match supported, 
	//                                         ignores variable prefix, i.e., $Variable = name)
	//      metadata of pointcutElement/joinpoint
	//		TODO add remaining matching criteria
	// returns false otherwise
	public static boolean matchElement(PointcutElement pointcutElement, Joinpoint joinpoint) {
		if (pointcutElement.isPointcutStartOrEndPoint())
			return true;
		if (pointcutElement.getElement() instanceof Anything)
			return true;
		if (pointcutElement.getElement().getClass() != joinpoint.getElement().getClass())
			return false;
		if (!namesAreMatching(removeVariablePrefix(pointcutElement.getName()), joinpoint.getName()))
			return false;
		if (!namesAreMatching(removeVariablePrefix(pointcutElement.getContainerName()), joinpoint.getContainerName()))
			return false;
		if (metadataAreMatching(pointcutElement.getElement(), joinpoint.getElement()))
			return true;
		// TODO match metadata on components
		return false;
	}

	private static boolean metadataAreMatching(URNmodelElement pointcutElement, URNmodelElement joinpointElement) {
		// TODO this only works for UCM at the moment
		List<Metadata> list = new ArrayList<Metadata>();
		list.addAll(pointcutElement.getMetadata());
		if (pointcutElement instanceof RespRef) {
			Responsibility rDef = ((RespRef) pointcutElement).getRespDef();
			list.addAll(rDef.getMetadata());
		}
		if (pointcutElement instanceof ComponentRef) {
			IURNContainer cDef = ((ComponentRef) pointcutElement).getContDef();
			list.addAll(((URNmodelElement) cDef).getMetadata());
		}
		List<Metadata> listJP = new ArrayList<Metadata>();
		list.addAll(joinpointElement.getMetadata());
		if (joinpointElement instanceof RespRef) {
			Responsibility rDef = ((RespRef) joinpointElement).getRespDef();
			listJP.addAll(rDef.getMetadata());
		}
		if (joinpointElement instanceof ComponentRef) {
			IURNContainer cDef = ((ComponentRef) joinpointElement).getContDef();
			listJP.addAll(((URNmodelElement) cDef).getMetadata());
		}
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Metadata m = (Metadata) iterator.next();
			boolean matched = false;
			for (Iterator iterator2 = listJP.iterator(); iterator2.hasNext();) {
				Metadata mJP = (Metadata) iterator2.next();
				if (namesAreMatching(m.getName(), mJP.getName()) && namesAreMatching(m.getValue(), mJP.getValue())) {
					matched = true;
					break;
				}
			}
			if (!matched) {
				list.clear();
				listJP.clear();
				return false;				
			}
		}
		list.clear();
		listJP.clear();
		return true;
	}

	private static String removeVariablePrefix(String name) {
		if (name.startsWith("$") && name.contains("=")) {
			name = name.substring(name.indexOf("=") + 1);
			while (name.startsWith(" "))
				name = name.substring(1);
		}
		return name;
	}

	private static boolean namesAreMatching(String pointcutName, String joinpointName) {
		// TODO right now everything starting with a * is matched, this is a quick workaround to allow
		// TODO any number of * to be matched, should be changed back to equals once proper tagging of 
		// TODO responsibility references works, so that multiple pointcut maps can share the responsibility *
		if (pointcutName.startsWith("*") || pointcutName.equals(joinpointName)) //$NON-NLS-1$
			return true;
		else {
		    Pattern p = Pattern.compile(pointcutName);
		    Matcher m;
		    m = p.matcher(joinpointName);
		    if (m.matches())
				return true;
		}
    	return false;
	}

	// tries to match the neighbors of the mapping's pointcutElement and the neighbors of the mapping's joinpoint
	// does not match the elements of the previous mapping since they have already been checked
	// adds a valid match between these neighbors to currentResult and returns it as a MatchList
	public static MatchList matchNeighbors(Mapping mapping, Mapping earlierMapping, Match currentResult) throws MatchingFailedException {
		MatchList result = new MatchList();
		MatchList matchResult = PointcutMatcher.matchPermutations(mapping, earlierMapping);
		if (matchResult.getMatchList().isEmpty()) {
			// recursion stops because there was nothing left to match; return the current match result
			result.getMatchList().add(currentResult);
			return result;
		}
		for (Iterator iter = matchResult.getMatchList().iterator(); iter.hasNext();) {
			Match match = (Match) iter.next();
			Match permutationResult = new Match(currentResult);
			try {
				// duplicate mappings will not be added to permutationResult, returned mappings do not contain duplicates
				match = permutationResult.add(match);
				// if no exception is thrown, continue matching recursively with the newly found mappings in variable match
				MatchList mergeResult = new MatchList();
				if (match.getMatch().isEmpty()) {
					// match is empty if only duplicates were found; in this case, the merge result is the current result!
					// this also means that there is nothing left to match and the recursion therefore stops here with currentResult as a valid result
					mergeResult.getMatchList().add(permutationResult);
				}
				else {			
					// match is not empty if something was found; in this case, continue with the recursion to deal with the newly 
					// found mappings and get an updated mergeResult!
					for (Iterator iterator = match.getMatch().iterator(); iterator.hasNext();) {
						Mapping foundMapping = (Mapping) iterator.next();
						MatchList recursionResult = PointcutMatcher.matchNeighbors(foundMapping, mapping, permutationResult);
						mergeResult.merge(recursionResult);
					}
				}
				result.getMatchList().addAll(mergeResult.getMatchList());
			} catch (ContradictoryMappingException e) {
				// nothing is added to results, don't throw exception but give the next permutation a chance
			} catch (MatchingFailedException e) {
				// nothing is added to results, don't throw exception but give the next permutation a chance
			}
		}
		if (result.getMatchList().isEmpty())
			throw new MatchingFailedException();
		return result;
	}

	//@SuppressWarnings("unchecked")
	private static MatchList matchPermutations(Mapping mapping, Mapping earlierMapping) throws MatchingFailedException {
		PointcutElement pointcutElement = mapping.getPointcutElement();
		Joinpoint joinpoint = mapping.getJoinpoint(); 
		MatchList matchList = new MatchList();
		List<MatchableNeighbor> pointcutNeighbors = pointcutElement.getNeighbors(mapping);
		List<MatchableNeighbor> joinpointNeighbors = joinpoint.getNeighbors();
		int size1 = pointcutNeighbors.size();
		int size2 = joinpointNeighbors.size();
		// no need to check the previous mapping again, therefore remove these elements from the neighbors
		// note that this is crucially important for anything pointcut elements
		// 1) loop detection for the anything pointcut element depends on it (detection is based on duplicate mappings)
		// 2) there is an interaction between the anything pointcut element and a choice point that could cause the series of mappings for the 
		// anything pointcut element to proceed in different directions (this cannot be allowed for the anything pointcut element because 
		// whatever is matched by it follows one path direction only)
		if (earlierMapping != null) {
			pointcutNeighbors = removeEarlierMapping(pointcutNeighbors, earlierMapping.getPointcutElement());
			joinpointNeighbors = removeEarlierMapping(joinpointNeighbors, earlierMapping.getJoinpoint());
		}
		if (pointcutElement.isPointcutEndPoint()) {
			// an end point of a pointcut map may be matched against a joinpoint that has neighbors following it.
			// therefore all PathNodes following the joinpoint have to be ignored. hence, the joinpointNeighbors are 
			// reduced to only those that are not "forward" neighbors;
			// there is always only one PointcutElement before an end point
			List<MatchableNeighbor> backwardJoinpoints = new ArrayList<MatchableNeighbor>();
			for (Iterator iter = joinpointNeighbors.iterator(); iter.hasNext();) {
				MatchableNeighbor joinpointNeighbor = (MatchableNeighbor) iter.next();
				if (!joinpointNeighbor.isForward()) {
					backwardJoinpoints.add(joinpointNeighbor);
				}
			}
			joinpointNeighbors = backwardJoinpoints;
		}
		if (pointcutElement.isPointcutStartPoint()) {
			// a start point of a pointcut map may be matched against a joinpoint that has neighbors preceding it.
			// therefore all PathNodes preceding the joinpoint have to be ignored. hence, the joinpointNeighbors are 
			// reduced to only those that are "forward" neighbors;
			// there is always only one PointcutElement after a start point
			List<MatchableNeighbor> forwardJoinpoints = new ArrayList<MatchableNeighbor>();
			for (Iterator iter = joinpointNeighbors.iterator(); iter.hasNext();) {
				MatchableNeighbor joinpointNeighbor = (MatchableNeighbor) iter.next();
				if (joinpointNeighbor.isForward()) {
					forwardJoinpoints.add(joinpointNeighbor);
				}
			}
			joinpointNeighbors = forwardJoinpoints;
		}
		// TODO this could probably be moved up a bit
		// if there are no more neighbors of the pointcut element left, then we are done
		if (pointcutNeighbors.size() == 0)
			return matchList;
		// all permutations of the joinpointNeighbors have to be matched against the pointcutNeighbors only if the size of the first list
		// is the same or greater than the size of the second list
		if (joinpointNeighbors.size() < pointcutNeighbors.size())
			throw new MatchingFailedException();
		// at this point, the semantic alternative is added to the joinpointNeighbors (since this neighbor is also an option to be matched)
		boolean semantics = (joinpoint.getSemanticNeighbor() != null);
		if (semantics) {
			joinpointNeighbors.add(joinpoint.getSemanticNeighbor());		
		}
		// the chosen approach gets first all permutations, and then cuts them to the required size of the pointcutNeighbors
		// note that there will be a mismatch of forward and backward neighbors in the two lists when the joinpointNeighbors list needs to be cut
		// these mismatches could be removed from the list of permutations but this is more effort than simply trying to match (if there is a 
		// mismatch, there won't be a successful match)
		List<List<MatchableNeighbor>> permutations = Permutation.getPermutations(joinpointNeighbors);
		// reduce the size of the joinpointNeighbors to the size of the pointcutNeighbors
		int reduceBy = joinpointNeighbors.size() - pointcutNeighbors.size();
		if (reduceBy > 0) {
			int position = pointcutNeighbors.size();
			for (Iterator iter = permutations.iterator(); iter.hasNext();) {
				List<MatchableNeighbor> permJoinpoints = (List<MatchableNeighbor>) iter.next();
				for (int i = 0; i < reduceBy; i++) {
					permJoinpoints.remove(position);
				}
			}			
		}
		// unfortunately, this approach results in duplicates in the permutation list; therefore, remove duplicate entries
		List<List<MatchableNeighbor>> cleanedPermutations = new ArrayList<List<MatchableNeighbor>>();
		for (int i = 0; i < permutations.size(); i++) {
			List<MatchableNeighbor> permJoinpoints = permutations.get(i);
			int j = i + 1;
			boolean duplicate = false;
			while (j < permutations.size() && !duplicate) {
				duplicate = findDuplicate(permJoinpoints, permutations.get(j));
				j++;
			}
			if (!duplicate) {
				cleanedPermutations.add(permJoinpoints);
			}
		}
		permutations = cleanedPermutations;
		// if the semantic neighbor was added, a permutation may contain both the semantic neighbor and the original, replaced neighbor
		// these permutations need to be removed
		if (semantics) {
			MatchableNeighbor semanticNeighbor = joinpoint.getSemanticNeighbor();
			MatchableNeighbor replacedNeighbor = joinpoint.getReplacedNeighbor();
			List<List<MatchableNeighbor>> cleanedPermutations2 = new ArrayList<List<MatchableNeighbor>>();
			for (int i = 0; i < permutations.size(); i++) {
				List<MatchableNeighbor> permJoinpoints = permutations.get(i);
				if (!(permJoinpoints.contains(semanticNeighbor) && permJoinpoints.contains(replacedNeighbor))) {
					cleanedPermutations2.add(permJoinpoints);
				}
			}
			permutations = cleanedPermutations2;			
		}
		// finally, now we can actually match each permutation
		boolean success = false;
		for (Iterator iter = permutations.iterator(); iter.hasNext();) {
			List<MatchableNeighbor> permJoinpoints = (List<MatchableNeighbor>) iter.next();
			try {
				Match mappings = PointcutMatcher.matchPermutations2(pointcutNeighbors, permJoinpoints, mapping);
				matchList.getMatchList().add(mappings);
				// at least one match succeeded
				success = true;
			} catch (MatchingFailedException e) {
				// ignore this failed result and do not add mappings to matchList, other permutations may succeed
			}
		}
		if (!success)
			throw new MatchingFailedException();
		return matchList;
	}

	// the size of pointcutElements and joinpoints is now the same; match each pair as they appear in the lists
	private static Match matchPermutations2(List<MatchableNeighbor> pointcutElements, List<MatchableNeighbor> joinpoints, Mapping mapping) throws MatchingFailedException {
		Match match = new Match();
		Iterator iter2 = joinpoints.iterator();
		for (Iterator iter = pointcutElements.iterator(); iter.hasNext();) {
			MatchableNeighbor pointcutElement = (MatchableNeighbor) iter.next();
			MatchableNeighbor joinpoint = (MatchableNeighbor) iter2.next();
			Mapping newMapping;
			try {
				if (matchNeighbor(pointcutElement, joinpoint)) {
					// to avoid duplicate matches, the matched joinpoint of a pointcut start/end point must be on the same map as 
					// the matched joinpoints of its neighbors
					if (mapping.getPointcutElement().isPointcutStartOrEndPoint() || pointcutElement.getElement().isPointcutStartOrEndPoint()) {
						if (!mapping.getJoinpoint().getElement().getDiagram().equals(joinpoint.getElement().getElement().getDiagram())) {
							throw new MatchingFailedException();
						}
					}
					// TODO @@@ alternative
					newMapping = new Mapping((PointcutElement) pointcutElement.getElement(), (Joinpoint) joinpoint.getElement());
					// if this is a mapping for the anything pointcut element, then it can only be the first one for the anything pointcut element
					// therefore indicate the mapping as such (a second mapping to the same anything pointcut element can only happen in the else block
					// of this if statement)
					if (newMapping.getPointcutElement().getElement() instanceof Anything) {
						newMapping.setFirst(true);
						newMapping.setLast(true);
					}
				}
				else if (mapping.getPointcutElement().getElement() instanceof Anything) {
					// a mapping with an anything pointcut element already exists and matchNeighbor failed, therefore add a mapping between
					// the anything pointcut element and the joinpoint; first and last indicators must also be set
					// TODO @@@ alternative
					newMapping = new Mapping(mapping.getPointcutElement(), (Joinpoint) joinpoint.getElement());
					if (!joinpoint.isForward()) {
						mapping.setFirst(false);
						newMapping.setFirst(true);
					} else {
						mapping.setLast(false);
						newMapping.setLast(true);
					}
				}
				else {
					throw new MatchingFailedException();
				}
			} catch (IncompatibleDirectionsException e) {
				throw new MatchingFailedException();
			}
			try {
				match.add(newMapping);
			} catch (DuplicateMappingException e) {
				// ignore duplicates (match.add does not add them to mappings)
			} catch (ContradictoryMappingException e) {
				throw new MatchingFailedException();
			}
		}
		return match;
	}

	// returns true if list2 is a duplicate of list1; both lists are the same size, compare each element to find out
	private static boolean findDuplicate(List<MatchableNeighbor> list1, List<MatchableNeighbor> list2) {
		for (int i = 0; i < list1.size(); i++) {
			if (!list1.get(i).equals(list2.get(i))) {
				return false;
			}
		}
		return true;
	}

	// return the list with the given element removed
	private static List<MatchableNeighbor> removeEarlierMapping(List<MatchableNeighbor> neighbors, MatchableElement earlierMappingElement) {
		List<MatchableNeighbor> result = new ArrayList<MatchableNeighbor>();
		result.addAll(neighbors);
		boolean found = false;
		int i = 0; 
		while (!found && i < result.size()) {
			MatchableNeighbor mn = result.get(i);
			if (earlierMappingElement.equals(mn.getElement())) {
				result.remove(i);
				found = true;
			}
			i++;
		}
		return result;
	}
	
	public static void dispose() {
		MatchableElementFactory.clearCache();
	}

}
