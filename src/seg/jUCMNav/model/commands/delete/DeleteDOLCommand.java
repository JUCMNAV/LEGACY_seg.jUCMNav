    package seg.jUCMNav.model.commands.delete;

    import org.eclipse.gef.commands.Command;

import asd.ASDiagram;
import asd.Outcome;
import asd.DivisionOfLabour;
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
    public class DeleteDOLCommand extends Command implements JUCMNavCommand {

        private DivisionOfLabour dol;

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
        public DeleteDOLCommand(URNspec spec, DivisionOfLabour dol, ASDiagram asDiagram) {
            super();
            this.spec = spec;
            this.dol = dol;
            this.asDiagram = asDiagram;
            setLabel(Messages.getString("DeleteDivisionOfLabourCommand.deleteDivisionOfLabour")); //$NON-NLS-1$
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
           
            for(Object o: dol.getRequiredOutcomes())
            {
            	dol.getRequiredOutcomes().remove((Outcome)o);
            }
 
            
            spec.getAsdspec().getDols().remove(dol);
            dol.getDiagrams().remove(asDiagram);
         
        }

        /*
         * (non-Javadoc)
         * 
         * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
         */
        public void testPreConditions() {
           /* assert dol != null : "pre belief"; //$NON-NLS-1$
            assert spec != null : "pre spec"; //$NON-NLS-1$
*/
         //  assert !spec.getAsdspec().getDivisionOfLabours().contains(dol) : "pre dol in spec"; //$NON-NLS-1$
            
        }

        /*
         * (non-Javadoc)
         * 
         * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
         */
        public void testPostConditions() {
            /*assert dol != null : "post dol"; //$NON-NLS-1$
            assert spec != null : "post spec"; //$NON-NLS-1$

            assert spec.getAsdspec().getDivisionOfLabours().contains(dol) : "pre dol in spec"; //$NON-NLS-1$
*/        }

        /**
         * 
         * @see org.eclipse.gef.commands.Command#undo()
         */
        public void undo() {
        	   
        	for(Object o: spec.getAsdspec().getOutcome())
        	{
        		if(o instanceof Outcome && ((Outcome) o).getName().equals(dol.getName()) && !asDiagram.getElements().contains(o))
        		{
        			dol.getRequiredOutcomes().add(o);
        		}
        	}
        	
        	spec.getAsdspec().getDols().add(dol);
        	  dol.getDiagrams().add(asDiagram);
        }
    }
