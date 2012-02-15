package seg.jUCMNav.editpolicies.directEditPolicy;

import java.util.ArrayList;

import org.eclipse.jface.fieldassist.ContentProposal;
import org.eclipse.jface.fieldassist.IContentProposal;
import org.eclipse.jface.fieldassist.SimpleContentProposalProvider;

public class NotThatSimpleContentProposalProvider extends SimpleContentProposalProvider {
    
    private String[] proposals;
    public NotThatSimpleContentProposalProvider(String[] proposals) {
        super(proposals);
        this.proposals = proposals; 
    }
    public void setProposals(String[] items) {
        this.proposals = items;
        super.setProposals(items);
    }

    
    public IContentProposal[] getProposals(String contents, int position) {
        ArrayList list = new ArrayList();
        
        if (proposals!=null) { 
            for (int i = 0; i < proposals.length; i++) {
                if (proposals[i].length() >= contents.length()
                        && proposals[i].substring(0, contents.length())
                                .equalsIgnoreCase(contents)) {
                    list.add(new ContentProposal(proposals[i]));
                }
            }
            
            // bug 787
            // add the currently typed in string as the default choice, to avoid pressing enter and having our text replaced 
            int index = -1;
            for (int i=0; i < list.size(); i++)
            {
                if (((ContentProposal)list.get(i)).getContent().equals(contents)) {
                    index =i;
                    break;
                }
            }
            
            // move to first if already in the list, otherwise add it.  
            if (index>=0) 
                list.remove(index);
            list.add(0, new ContentProposal(contents));
        
        }
        return (IContentProposal[]) list.toArray(new IContentProposal[list.size()]);
    }
    
}
