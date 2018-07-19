    package seg.jUCMNav.model.commands.delete;

    import org.eclipse.gef.commands.Command;

import asd.ASDiagram;
import asd.Outcome;
import asd.Tool;
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
    public class DeleteToolCommand extends Command implements JUCMNavCommand {

        private Tool tool;

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
        public DeleteToolCommand(URNspec spec, Tool tool, ASDiagram asDiagram) {
            super();
            this.spec = spec;
            this.tool = tool;
            this.asDiagram = asDiagram;
            setLabel(Messages.getString("DeleteToolCommand.deleteTool")); //$NON-NLS-1$
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
           
            for(Object o: tool.getRequiredOutcomes())
            {
            	tool.getRequiredOutcomes().remove((Outcome)o);
            }
 
            
            spec.getAsdspec().getTools().remove(tool);
            tool.getDiagrams().remove(asDiagram);
         
        }

        /*
         * (non-Javadoc)
         * 
         * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
         */
        public void testPreConditions() {
           /* assert tool != null : "pre belief"; //$NON-NLS-1$
            assert spec != null : "pre spec"; //$NON-NLS-1$
*/
         //  assert !spec.getAsdspec().getTools().contains(tool) : "pre tool in spec"; //$NON-NLS-1$
            
        }

        /*
         * (non-Javadoc)
         * 
         * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
         */
        public void testPostConditions() {
            /*assert tool != null : "post tool"; //$NON-NLS-1$
            assert spec != null : "post spec"; //$NON-NLS-1$

            assert spec.getAsdspec().getTools().contains(tool) : "pre tool in spec"; //$NON-NLS-1$
*/        }

        /**
         * 
         * @see org.eclipse.gef.commands.Command#undo()
         */
        public void undo() {
        	
        	
        	   
        	for(Object o: spec.getAsdspec().getOutcome())
        	{
        		if(o instanceof Outcome && ((Outcome) o).getName().equals(tool.getName()) && !asDiagram.getElements().contains(o))
        		{
        			tool.getRequiredOutcomes().add(o);
        		}
        	}
        	
        	spec.getAsdspec().getTools().add(tool);
        	  tool.getDiagrams().add(asDiagram);
        }
    }
