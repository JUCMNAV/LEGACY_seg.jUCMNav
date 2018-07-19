package seg.jUCMNav.model.commands.delete;

    import org.eclipse.gef.commands.Command;

import asd.ASDiagram;
import asd.Subject;
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
    public class DeleteSubjectCommand extends Command implements JUCMNavCommand {

        private Subject subject;

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
        public DeleteSubjectCommand(URNspec spec, Subject subject, ASDiagram asDiagram) {
            super();
            this.spec = spec;
            this.subject = subject;
            this.asDiagram = asDiagram;
            setLabel(Messages.getString("DeleteSubjectCommand.deleteSubject")); //$NON-NLS-1$
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
            
            subject.getDiagrams().remove(asDiagram);
            spec.getAsdspec().getSubjects().remove(subject);

            testPreConditions();
        }

        /*
         * (non-Javadoc)
         * 
         * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
         */
        public void testPreConditions() {
            assert subject != null : "pre belief"; //$NON-NLS-1$
            assert spec != null : "pre spec"; //$NON-NLS-1$

           assert !spec.getAsdspec().getSubjects().contains(subject) : "pre subject in spec"; //$NON-NLS-1$
            
        }

        /*
         * (non-Javadoc)
         * 
         * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
         */
        public void testPostConditions() {
            assert subject != null : "post subject"; //$NON-NLS-1$
            assert spec != null : "post spec"; //$NON-NLS-1$

            assert spec.getAsdspec().getSubjects().contains(subject) : "pre subject in spec"; //$NON-NLS-1$
        }

        /**
         * 
         * @see org.eclipse.gef.commands.Command#undo()
         */
        public void undo() {
        	 testPreConditions();

             //   graph.getNodes().add(subject);
                spec.getAsdspec().getSubjects().add(subject);
                subject.getDiagrams().add(asDiagram);

                testPostConditions();
        }
    }
