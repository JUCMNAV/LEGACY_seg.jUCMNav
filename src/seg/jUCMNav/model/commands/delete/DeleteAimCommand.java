    package seg.jUCMNav.model.commands.delete;

    import org.eclipse.gef.commands.Command;

import asd.ASDiagram;
import asd.Aim;
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
    public class DeleteAimCommand extends Command implements JUCMNavCommand {

        private Aim aim;

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
        public DeleteAimCommand(URNspec spec, Aim aim, ASDiagram asDiagram) {
            super();
            this.spec = spec;
            this.aim = aim;
            this.asDiagram = asDiagram;
            setLabel(Messages.getString("DeleteAimCommand.deleteAim")); //$NON-NLS-1$
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
           
            
    testPostConditions();
            
    
   
            
            spec.getAsdspec().getObjects().remove(aim);
            aim.getDiagrams().remove(asDiagram);
            testPreConditions();
        }

        /*
         * (non-Javadoc)
         * 
         * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
         */
        public void testPreConditions() {
            assert aim != null : "pre belief"; //$NON-NLS-1$
            assert spec != null : "pre spec"; //$NON-NLS-1$

           assert !spec.getAsdspec().getOutcome().contains(aim) : "pre aim in spec"; //$NON-NLS-1$
            
        }

        /*
         * (non-Javadoc)
         * 
         * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
         */
        public void testPostConditions() {
            assert aim != null : "post aim"; //$NON-NLS-1$
            assert spec != null : "post spec"; //$NON-NLS-1$

            assert spec.getAsdspec().getObjects().contains(aim) : "pre aim in spec"; //$NON-NLS-1$
        }

        /**
         * 
         * @see org.eclipse.gef.commands.Command#undo()
         */
        public void undo() {
        	 testPreConditions();
        
             //   graph.getNodes().add(aim);
                spec.getAsdspec().getObjects().add(aim);
                aim.getDiagrams().add(asDiagram);

                testPostConditions();
        }
    }
