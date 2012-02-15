package seg.jUCMNav.editpolicies.directEditPolicy;

import java.util.Vector;

import seg.jUCMNav.model.util.URNElementFinder;
import urn.URNspec;

public class ActorProposalProvider extends NotThatSimpleContentProposalProvider {

    public ActorProposalProvider(URNspec urn) {
        super(null);
        setFiltering(true);

        Vector v = URNElementFinder.getActorNames(urn);

        String[] proposals = new String[v.size()];
        for (int i = 0; i < v.size(); i++)
            proposals[i] = v.get(i).toString();
        setProposals(proposals);
    }
}
