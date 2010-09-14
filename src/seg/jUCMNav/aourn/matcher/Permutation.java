package seg.jUCMNav.aourn.matcher;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Permutation {

	private static List<List<MatchableNeighbor>> permutation;
	
    public static List<List<MatchableNeighbor>> getPermutations(List<MatchableNeighbor> pathNodes) {
    	permutation = new ArrayList<List<MatchableNeighbor>>();
    	getPermutations(new ArrayList<MatchableNeighbor>(), pathNodes);
    	return permutation;
    }

    private static void getPermutations(List<MatchableNeighbor> result, List<MatchableNeighbor> rest) {
    	if (rest.size() == 0) {
    			permutation.add(result);
    	}
    	else {
    		for(int i = 0; i < rest.size(); i++) {
    			List<MatchableNeighbor> newResult = copy(result);
    			newResult.add(rest.get(i));
    			List<MatchableNeighbor> newRest = copy(rest);
    			newRest.remove(i);
    			getPermutations(newResult, newRest);            	
    		}
    	}
    }
    
    private static List<MatchableNeighbor> copy(List<MatchableNeighbor> list) {
    	List<MatchableNeighbor> copiedList = new ArrayList<MatchableNeighbor>();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			MatchableNeighbor matchablePathNode = (MatchableNeighbor) iter.next();
			copiedList.add(matchablePathNode);
		}
		return copiedList;
    }
    
}
