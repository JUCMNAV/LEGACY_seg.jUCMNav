package seg.jUCMNav.editpolicies.directEditPolicy;

import java.util.Vector;

import org.eclipse.jface.fieldassist.SimpleContentProposalProvider;

import seg.jUCMNav.model.util.URNElementFinder;
import urn.URNspec;

public class IntentionalElementProposalProvider extends SimpleContentProposalProvider {

    public IntentionalElementProposalProvider(URNspec urn) {
        super(null);
        setFiltering(true);

        Vector v = URNElementFinder.getIntentionalElementNames(urn);

        String[] proposals = new String[v.size()];
        for (int i = 0; i < v.size(); i++)
            proposals[i] = v.get(i).toString();
        setProposals(proposals);

    }

}
