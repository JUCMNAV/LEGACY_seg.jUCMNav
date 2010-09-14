package seg.jUCMNav.aourn.matcher;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import seg.jUCMNav.aourn.matcher.exceptions.ContradictoryMappingException;

public class MatchList {
	
	private List<Match> matchList = new ArrayList<Match>();

	public List<Match> getMatchList() {
		return matchList;
	}
	
	// merges two MatchLists with n and m lists respectively 
	// results in nxm lists (in the best case)
	// if there are contradictions when merging a list pair, this pair is ignored but other pairs are given a chance
	// if at the end the result is an empty list, then the merging failed and ContradictoryMappingException is thrown
	public void merge(MatchList newMatchList) throws ContradictoryMappingException {
		if (newMatchList == null || newMatchList.getMatchList().size() == 0) {
			// matchList remains the same
		} else if (matchList.size() == 0) {
			// newMatchList is the result
			matchList = newMatchList.getMatchList();
		} else {
			// create nxm list (minus contradictory lists)
			MatchList mergedMatchList = new MatchList();
			for (Iterator iter = matchList.iterator(); iter.hasNext();) {
				Match firstMatch = (Match) iter.next();
				for (Iterator iterator = newMatchList.getMatchList().iterator(); iterator.hasNext();) {
					Match secondMatch = (Match) iterator.next();
					Match mergedMatch = new Match(firstMatch);
					try {
						mergedMatch.add(secondMatch);
						mergedMatchList.getMatchList().add(mergedMatch);
					} catch (ContradictoryMappingException e) {
						// ignore this pair of lists (do not add it), but give all other 
						// pairs a chance (they may merge fine)
					}
				}
			}
			matchList = mergedMatchList.getMatchList();
			if (matchList.size() == 0) {
				throw new ContradictoryMappingException();
			}
		}
	}

}
