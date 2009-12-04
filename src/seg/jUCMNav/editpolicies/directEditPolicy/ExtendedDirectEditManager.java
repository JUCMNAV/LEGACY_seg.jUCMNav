package seg.jUCMNav.editpolicies.directEditPolicy;

import grl.ActorRef;
import grl.GRLNode;
import grl.IntentionalElementRef;
import grl.kpimodel.KPIInformationElementRef;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.tools.CellEditorLocator;
import org.eclipse.gef.tools.DirectEditManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ICellEditorValidator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;

import seg.jUCMNav.Messages;
import seg.jUCMNav.editparts.URNRootEditPart;
import seg.jUCMNav.figures.LabelElementFigure;
import seg.jUCMNav.model.commands.transformations.ChangeDefinitionCommand;
import seg.jUCMNav.model.commands.transformations.ChangeGrlNodeNameCommand;
import seg.jUCMNav.model.commands.transformations.ChangeLabelNameCommand;
import ucm.map.RespRef;
import urn.URNspec;
import urncore.ComponentLabel;
import urncore.Label;
import urncore.NodeLabel;

/**
 * 
 * A generic DirectEdit manager to be used for labels which includes validation functionality by adding the ICellEditorValidator on startup
 * 
 * Class taken from some example with only a few lines modified.
 * 
 * @author Phil Zoio
 * 
 */
public class ExtendedDirectEditManager extends DirectEditManager {

    Font figureFont;
    protected VerifyListener verifyListener;
    protected LabelElementFigure label;
    protected String originalValue;
    private boolean committing = false;
    private ICellEditorValidator validator = null;

    /**
     * Creates a new ActivityDirectEditManager with the given attributes.
     * 
     * @param source
     *            the source EditPart
     * @param editorType
     *            type of editor
     * @param locator
     *            the CellEditorLocator
     */
    public ExtendedDirectEditManager(GraphicalEditPart source, Class editorType, CellEditorLocator locator, LabelElementFigure label,
            ICellEditorValidator validator) {
        super(source, editorType, locator);
        this.label = label;
        this.originalValue = label.getEditableText();
        this.validator = validator;
    }

    /**
     * @see org.eclipse.gef.tools.DirectEditManager#bringDown()
     */
    protected void bringDown() {
        try {
            Font disposeFont = figureFont;
            figureFont = null;
            super.bringDown();
            if (disposeFont != null)
                disposeFont.dispose();
        } catch (IllegalArgumentException ex) {
            figureFont = null;
        }
    }

    /**
     * @see org.eclipse.gef.tools.DirectEditManager#initCellEditor()
     */
    protected void initCellEditor() {

        if (getCellEditor() instanceof AutocompleteTextCellEditor) {
            URNspec urn = ((URNRootEditPart) getEditPart().getRoot()).getMultiPageEditor().getModel();
            if (getEditPart().getModel() instanceof Label) {
                Label lbl = (Label) getEditPart().getModel();
                if (lbl instanceof ComponentLabel) {
                    if (((ComponentLabel) lbl).getContRef() instanceof ActorRef)
                        ((AutocompleteTextCellEditor) getCellEditor()).enableContentProposal(new ActorProposalProvider(urn), null, null);
                    else
                        ((AutocompleteTextCellEditor) getCellEditor()).enableContentProposal(new ComponentProposalProvider(urn), null, null);
                } else if (lbl instanceof NodeLabel) {
                    // only responsibilities.
                    if (((NodeLabel) lbl).getNode() instanceof RespRef) {
                        ((AutocompleteTextCellEditor) getCellEditor()).enableContentProposal(new ResponsibilityProposalProvider(urn), null, null);
                    }
                }
            } else if (getEditPart().getModel() instanceof GRLNode) {
                GRLNode node = (GRLNode) getEditPart().getModel();
                if (node instanceof IntentionalElementRef) {
                    ((AutocompleteTextCellEditor) getCellEditor()).enableContentProposal(new IntentionalElementProposalProvider(urn), null, null);
                } else if (node instanceof KPIInformationElementRef) {
                    ((AutocompleteTextCellEditor) getCellEditor()).enableContentProposal(new KPIInformationElementProposalProvider(urn), null, null);
                }
            }

        }
        Text text = (Text) getCellEditor().getControl();

        // add the verifyListener to apply changes to the control size
        verifyListener = new VerifyListener() {

            /**
             * Changes the size of the editor control to reflect the changed text
             */
            public void verifyText(VerifyEvent event) {
                Text text = (Text) getCellEditor().getControl();
                String oldText = text.getText();
                String leftText = oldText.substring(0, event.start);
                String rightText = oldText.substring(event.end, oldText.length());
                GC gc = new GC(text);
                if (leftText == null)
                    leftText = ""; //$NON-NLS-1$
                if (rightText == null)
                    rightText = ""; //$NON-NLS-1$

                Point size = gc.textExtent(leftText + event.text + rightText);

                gc.dispose();
                if (size.x != 0)
                    size = text.computeSize(size.x, SWT.DEFAULT);
                else {
                    // just make it square
                    size.x = size.y;
                }
                getCellEditor().getControl().setSize(size.x, size.y);
            }

        };
        text.addVerifyListener(verifyListener);

        // set the initial value of the
        originalValue = this.label.getEditableText();
        getCellEditor().setValue(originalValue);

        // calculate the font size of the underlying
        IFigure figure = (getEditPart()).getFigure();
        figureFont = figure.getFont();
        FontData data = figureFont.getFontData()[0];
        Dimension fontSize = new Dimension(0, data.getHeight());

        // set the font to be used
        this.label.translateToAbsolute(fontSize);
        data.setHeight(fontSize.height);
        figureFont = new Font(null, data);

        // set the validator for the CellEditor
        getCellEditor().setValidator(validator);

        text.setFont(figureFont);

        // jkealey: bug 271; wasn't nice at 400% without this
        text.pack();

        text.selectAll();
    }

    /**
     * Commits the current value of the cell editor by getting a {@link Command}from the source edit part and executing it via the {@link CommandStack}.
     * Finally, {@link #bringDown()}is called to perform and necessary cleanup.
     */
    protected void commit() {

        if (committing)
            return;
        committing = true;
        try {

            // we set the cell editor control to invisible to remove any
            // possible flicker
            getCellEditor().getControl().setVisible(false);
            if (isDirty()) {
                CommandStack stack = getEditPart().getViewer().getEditDomain().getCommandStack();
                Command command = getEditPart().getCommand(getDirectEditRequest());

                if (command != null && command.canExecute())
                    stack.execute(command);
                else if (command instanceof ChangeLabelNameCommand || command instanceof ChangeGrlNodeNameCommand) {
                    boolean confirm = MessageDialog.openConfirm(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), Messages
                            .getString("ExtendedDirectEditManager.NameAlreadyInUse"), Messages.getString("ExtendedDirectEditManager.OtherDefinitionExists")); //$NON-NLS-1$ //$NON-NLS-2$
                    if (confirm) {
                        URNspec urn = ((URNRootEditPart) getEditPart().getRoot()).getMultiPageEditor().getModel();
                        ChangeDefinitionCommand cmd = null;

                        if (command instanceof ChangeLabelNameCommand) {
                            ChangeLabelNameCommand rename = ((ChangeLabelNameCommand) command);
                            cmd = new ChangeDefinitionCommand(urn, rename.getRenamedLabel(), rename.getName());
                        } else if (command instanceof ChangeGrlNodeNameCommand) {
                            ChangeGrlNodeNameCommand rename = ((ChangeGrlNodeNameCommand) command);
                            cmd = new ChangeDefinitionCommand(urn, rename.getElement(), rename.getName());

                        }
                        if (cmd != null && cmd.canExecute())
                            stack.execute(cmd);

                    }
                }

            }
        } finally {
            bringDown();
            committing = false;
        }
    }

    /**
     * Need to override so as to remove the verify listener
     */
    protected void unhookListeners() {
        super.unhookListeners();
        Text text = (Text) getCellEditor().getControl();
        text.removeVerifyListener(verifyListener);
        verifyListener = null;
    }

}