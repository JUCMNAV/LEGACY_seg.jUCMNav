    package seg.jUCMNav.model.commands.delete;

    import org.eclipse.gef.commands.Command;

import asd.ASDiagram;
import asd.Outcome;
import asd.Rule;
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
    public class DeleteRuleCommand extends Command implements JUCMNavCommand {

        private Rule rule;

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
        public DeleteRuleCommand(URNspec spec, Rule rule, ASDiagram asDiagram) {
            super();
            this.spec = spec;
            this.rule = rule;
            this.asDiagram = asDiagram;
            setLabel(Messages.getString("DeleteRuleCommand.deleteRule")); //$NON-NLS-1$
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
           
            for(Object o: rule.getRequiredOutcomes())
            {
            	rule.getRequiredOutcomes().remove((Outcome)o);
            }
 
            
            spec.getAsdspec().getRules().remove(rule);
            rule.getDiagrams().remove(asDiagram);
         
        }

        /*
         * (non-Javadoc)
         * 
         * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
         */
        public void testPreConditions() {
           /* assert rule != null : "pre belief"; //$NON-NLS-1$
            assert spec != null : "pre spec"; //$NON-NLS-1$
*/
         //  assert !spec.getAsdspec().getRules().contains(rule) : "pre rule in spec"; //$NON-NLS-1$
            
        }

        /*
         * (non-Javadoc)
         * 
         * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
         */
        public void testPostConditions() {
            /*assert rule != null : "post rule"; //$NON-NLS-1$
            assert spec != null : "post spec"; //$NON-NLS-1$

            assert spec.getAsdspec().getRules().contains(rule) : "pre rule in spec"; //$NON-NLS-1$
*/        }

        /**
         * 
         * @see org.eclipse.gef.commands.Command#undo()
         */
        public void undo() {
        	   
        	for(Object o: spec.getAsdspec().getOutcome())
        	{
        		if(o instanceof Outcome && ((Outcome) o).getName().equals(rule.getName()) && !asDiagram.getElements().contains(o))
        		{
        			rule.getRequiredOutcomes().add(o);
        		}
        	}
        	
        	spec.getAsdspec().getRules().add(rule);
        	  rule.getDiagrams().add(asDiagram);
        }
    }
