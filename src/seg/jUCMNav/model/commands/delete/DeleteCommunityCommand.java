    package seg.jUCMNav.model.commands.delete;

    import org.eclipse.gef.commands.Command;

import asd.ASDiagram;
import asd.Community;
import asd.Outcome;
import grl.GRLGraph;
import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import urn.URNspec;
    /**
     * 
     * Deletes a belief to a {@link GRLGraph}
     * 
     * @author Jean-François Roy
     * 
     */
    public class DeleteCommunityCommand extends Command implements JUCMNavCommand {

        private Community community;

        // Graph where the element has been added.
        private URNspec spec;
        
        private ASDiagram asDiagram;

        /**
         * @param asDiagram 
         * @param graph
         *            graph where to add the belief
         * @param belief
         *            belief to add
         */
        public DeleteCommunityCommand(URNspec spec, Community community, ASDiagram asDiagram) {
            super();
            this.spec = spec;
            this.community = community;
            this.asDiagram = asDiagram;
            setLabel(Messages.getString("DeleteCommunityCommand.deleteCommunity")); //$NON-NLS-1$
        }

        /**
         * 
         * @see org.eclipse.gef.commands.Command#execute()
         */
        public void execute() {
            redo();
        }

        /**
         * 
         * @see org.eclipse.gef.commands.Command#redo()
         */
        public void redo() {
           
            for(Object o: community.getRequiredOutcomes())
            {
            	community.getRequiredOutcomes().remove((Outcome)o);
            }
 
            
            spec.getAsdspec().getCommunities().remove(community);
            community.getDiagrams().remove(asDiagram);
         
        }

        /*
         * (non-Javadoc)
         * 
         * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
         */
        public void testPreConditions() {
           /* assert community != null : "pre belief"; //$NON-NLS-1$
            assert spec != null : "pre spec"; //$NON-NLS-1$
*/
         //  assert !spec.getAsdspec().getCommunitys().contains(community) : "pre community in spec"; //$NON-NLS-1$
            
        }

        /*
         * (non-Javadoc)
         * 
         * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
         */
        public void testPostConditions() {
            /*assert community != null : "post community"; //$NON-NLS-1$
            assert spec != null : "post spec"; //$NON-NLS-1$

            assert spec.getAsdspec().getCommunitys().contains(community) : "pre community in spec"; //$NON-NLS-1$
*/        }

        /**
         * 
         * @see org.eclipse.gef.commands.Command#undo()
         */
        public void undo() {
        	   
        	for(Object o: spec.getAsdspec().getOutcome())
        	{
        		if(o instanceof Outcome && ((Outcome) o).getName().equals(community.getName()) && !asDiagram.getElements().contains(o))
        		{
        			community.getRequiredOutcomes().add(o);
        		}
        	}
        	
        	spec.getAsdspec().getCommunities().add(community);
        	  community.getDiagrams().add(asDiagram);
        }
    }
