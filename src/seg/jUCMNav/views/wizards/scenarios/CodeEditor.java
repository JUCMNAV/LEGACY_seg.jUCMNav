package seg.jUCMNav.views.wizards.scenarios;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWizard;

import seg.jUCMNav.Messages;
import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.model.commands.transformations.ChangeCodeCommand;
import ucm.map.FailurePoint;
import ucm.map.NodeConnection;
import ucm.map.OrFork;
import ucm.map.PathNode;
import ucm.map.PluginBinding;
import ucm.map.RespRef;
import ucm.map.WaitingPlace;
import ucm.scenario.ScenarioDef;
import urncore.Condition;
import urncore.Responsibility;

/**
 * Wizard for editing a responsibility's code or a condition.
 * 
 * @author jkealey
 */
public class CodeEditor extends Wizard {
    private CodeEditorPage page;
    private ISelection selection;
    private EObject defaultSelected;

    /**
     * The workbench page in which we are working
     */
    protected IWorkbenchPage workbenchPage;

    /**
     * Creates the editor
     */
    public CodeEditor() {
        super();
        setNeedsProgressMonitor(true);
        this.setWindowTitle(Messages.getString("CodeEditor.CodeEditor")); //$NON-NLS-1$
    }

    /**
     * Adding the page to the wizard.
     */
    public void addPages() {
        page = new CodeEditorPage(selection, defaultSelected);
        addPage(page);
    }

    /**
     * This method is called when 'Finish' button is pressed in the wizard. We will create an operation and run it using wizard as execution context.
     */
    public boolean performFinish() {
        final HashMap code = page.getAllCode();
        final HashMap labels = page.getAllLabels();
        final HashMap descriptions = page.getAllDescriptions();

        CommandStack cs = ((UCMNavMultiPageEditor) workbenchPage.getActiveEditor()).getDelegatingCommandStack();

        CompoundCommand cmd = new CompoundCommand();
        for (Iterator iter = code.entrySet().iterator(); iter.hasNext();) {
            Map.Entry entry = (Map.Entry) iter.next();
            cmd.add(new ChangeCodeCommand((EObject) entry.getKey(), entry.getValue().toString(), labels.get(entry.getKey()).toString(), descriptions.get(
                    entry.getKey()).toString()));
        }

        if (cmd.canExecute()) {
            cs.execute(cmd);
        }
        return true;

    }

    /**
     * Throws an error using the message.
     * 
     * @param message
     *            the error message.
     * @throws CoreException
     *             the generated exception.
     */
    private void throwCoreException(String message) throws CoreException {
        IStatus status = new Status(IStatus.ERROR, "seg.jUCMNav", IStatus.OK, message, null); //$NON-NLS-1$
        throw new CoreException(status);
    }

    /**
     * We will accept the selection in the workbench to see if we can initialize from it.
     * 
     * @see IWorkbenchWizard#init(IWorkbench, IStructuredSelection)
     */
    public void init(IWorkbench workbench, IStructuredSelection selection, EObject defaultSelected) {
        this.selection = selection;
        this.workbenchPage = workbench.getActiveWorkbenchWindow().getActivePage();
        this.defaultSelected = defaultSelected;

        if (selection == null) {
            Vector v = new Vector();

            // choose which object we are giving it.
            if (defaultSelected instanceof Responsibility) {

                Responsibility responsibility = (Responsibility) defaultSelected;
                for (Iterator iter = responsibility.getUrndefinition().getResponsibilities().iterator(); iter.hasNext();) {
                    v.add(iter.next());

                }
            } else if (defaultSelected instanceof RespRef) {

                RespRef respRef = (RespRef) defaultSelected;
                for (Iterator iter = respRef.getDiagram().getNodes().iterator(); iter.hasNext();) {
                    PathNode pn = (PathNode) iter.next();
                    if (pn instanceof RespRef && !v.contains(((RespRef) pn).getRespDef())) {
                        v.add(((RespRef) pn).getRespDef());
                    }
                }

                this.defaultSelected = respRef.getRespDef();
            } else if (defaultSelected instanceof OrFork || defaultSelected instanceof WaitingPlace || defaultSelected instanceof FailurePoint) {

                PathNode pn = (PathNode) defaultSelected;

                // note: if you do add related conditions to FailurePoint, you will need to edit the CodeEditorPage so that it knows if it is editing a
                // condition or code depending on the selection in the dropdown
                if (!(defaultSelected instanceof FailurePoint)) {
                    this.defaultSelected = null;

                    for (Iterator iter = pn.getSucc().iterator(); iter.hasNext();) {
                        NodeConnection nc = (NodeConnection) iter.next();
                        if (nc.getCondition() != null)
                            v.add(nc.getCondition());
                    }
                } else {
                    v.add(pn);
                }
            } else if (defaultSelected instanceof Condition) {
                Condition cond = (Condition) defaultSelected;
                if (cond.eContainer() instanceof NodeConnection) {
                    NodeConnection connection = (NodeConnection) cond.eContainer();
                    for (Iterator iter = connection.getSource().getSucc().iterator(); iter.hasNext();) {
                        NodeConnection conn = (NodeConnection) iter.next();
                        if (conn.getCondition() != null) {
                            v.add(conn.getCondition());
                        }

                    }

                } else if (cond.eContainer() instanceof PluginBinding) {
                    PluginBinding plug = (PluginBinding) cond.eContainer();

                    for (Iterator iter = plug.getStub().getBindings().iterator(); iter.hasNext();) {
                        PluginBinding pb = (PluginBinding) iter.next();
                        if (pb.getPrecondition() != null) {
                            v.add(pb.getPrecondition());
                        }

                    }

                } else if (cond.eContainer() instanceof ScenarioDef) {
                    ScenarioDef scenario = (ScenarioDef) cond.eContainer();
                    if (scenario.getPreconditions().contains(cond)) {
                        for (Iterator iter = scenario.getPreconditions().iterator(); iter.hasNext();) {
                            v.add(iter.next());
                        }
                    } else {
                        for (Iterator iter = scenario.getPostconditions().iterator(); iter.hasNext();) {
                            v.add(iter.next());
                        }
                    }

                } else // start/end pre/post condition.
                {
                    v.add(cond);
                }
            } else {
                v.add(defaultSelected);
            }
            this.selection = new StructuredSelection(v);
        }
    }
}