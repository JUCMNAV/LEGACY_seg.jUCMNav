package seg.jUCMNav.views.wizards.performance;

import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.Messages;
import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.model.commands.create.CreateDemandCommand;
import seg.jUCMNav.model.commands.delete.DeleteDemandCommand;
import ucm.map.RespRef;
import ucm.performance.Demand;
import ucm.performance.ExternalOperation;
import ucm.performance.GeneralResource;
import urn.URNspec;
import urncore.Component;
import urncore.Responsibility;

/**
 * The page that actually manages responsibilities' demands (resources)
 * 
 * @author jack
 */
public class ManageDemandPage extends WizardPage {

    private Shell shell;
    private Composite container;
    private Label typeLabel;
    private EObject defaultSelected;
    private URNspec spec;
    private Component[] components;
    private Combo allcomponents;
    private Composite selectInOut;
    private Responsibility responsibility;
    private List inList;
    private Composite buttons;
    private Button left;
    private Button right;
    private List availList;
    private ExternalOperation[] availExternOpns;
    private ExternalOperation[] boundExternOpns;
    private Demand[] demands;
    private Demand selectedDemand = null;
    private String quantity;
    private IWorkbenchPage workbenchPage;
    private ExternalOperation extOp;
    private Canvas cv;
    private Text demand;
    Button updateButton;

    /**
     * 
     * @param workbenchPage
     * @param defaultSelected
     *            responsibility for which demand will be managed
     */
    public ManageDemandPage(IWorkbenchPage workbenchPage, EObject defaultSelected) {
        super("wizardPage"); //$NON-NLS-1$
        this.setImageDescriptor(JUCMNavPlugin.getImageDescriptor("icons/perspectiveIcon.gif")); //$NON-NLS-1$
        this.workbenchPage = workbenchPage;
        this.defaultSelected = defaultSelected;

        setTitle(Messages.getString("ManageDemandPage.ManageDemand")); //$NON-NLS-1$
        setDescription(Messages.getString("ManageDemandPage.SpecifyExtOp")); //$NON-NLS-1$

    }

    /**
     * Creates the page.
     */
    public void createControl(Composite parent) {
        PlatformUI.getWorkbench().getHelpSystem().setHelp(parent, "seg.jUCMNav.managedemand"); //$NON-NLS-1$
        container = new Composite(parent, SWT.NULL);
        shell = container.getShell();
        GridLayout containerLayout = new GridLayout();
        containerLayout.numColumns = 3;
        container.setLayout(containerLayout);

        Label lin = new Label(container, SWT.NULL);
        lin.setText(Messages.getString("ManageDemandPage.RequiredExtOp")); //$NON-NLS-1$

        Label blank1 = new Label(container, SWT.NULL);
        blank1.setText(""); //$NON-NLS-1$

        Label lavail = new Label(container, SWT.NULL);
        lavail.setText(Messages.getString("ManageDemandPage.AvailableExtOp")); //$NON-NLS-1$

        inList = new List(container, SWT.READ_ONLY | SWT.V_SCROLL);
        inList.setSize(150, 150);
        inList.addSelectionListener(inListListener);
        GridData gd1 = new GridData();
        gd1.horizontalSpan = 1;
        gd1.heightHint = 200;
        gd1.widthHint = 200;
        inList.setLayoutData(gd1);

        cv = new Canvas(container, SWT.NULL);
        GridData gd9 = new GridData();
        gd9.horizontalSpan = 1;
        cv.setLayoutData(gd9);
        cv.setLayout(new FillLayout(SWT.VERTICAL));

        left = new Button(cv, SWT.Activate);
        left.setText(Messages.getString("ManageDemandPage.Add")); //$NON-NLS-1$
        left.setEnabled(false);
        left.addSelectionListener(leftBtnListener);

        right = new Button(cv, SWT.Activate);
        right.setText(Messages.getString("ManageDemandPage.Remove")); //$NON-NLS-1$
        right.setEnabled(false);
        right.addSelectionListener(rightBtnListener);

        // avail:
        availList = new List(container, SWT.READ_ONLY | SWT.V_SCROLL);
        availList.setSize(150, 150);
        availList.addSelectionListener(availListListener);
        GridData gd2 = new GridData();
        gd2.horizontalSpan = 1;
        gd2.heightHint = 200;
        gd2.widthHint = 200;
        availList.setLayoutData(gd2);

        Label demandLabel = new Label(container, SWT.NULL);
        GridData gd3 = new GridData(GridData.FILL_HORIZONTAL);
        gd3.horizontalSpan = 3;
        demandLabel.setLayoutData(gd3);
        demandLabel.setText(Messages.getString("ManageDemandPage.Demand")); //$NON-NLS-1$
        demand = new Text(container, SWT.BORDER);
        GridData gd4 = new GridData(GridData.FILL_HORIZONTAL);
        gd4.horizontalSpan = 1;
        demand.setLayoutData(gd4);
        demand.addModifyListener(new ModifyListener() {
            public void modifyText(ModifyEvent e) {
                boolean changed = false;
                String qtyString = selectedDemand.getQuantity();
                String qtyUser = demand.getText().toString();
                if (!qtyString.equals(qtyUser)) {
                    changed = true;
                }
                if (isValid(qtyUser) != null) {
                    setErrorMessage(Messages.getString("ManageDemandPage.QuantityMustBeReal")); //$NON-NLS-1$
                } else {
                    setErrorMessage(null);
                    updateButton.setEnabled(changed);
                }
            }
        });

        updateButton = new Button(container, SWT.PUSH);
        updateButton.setText(Messages.getString("ManageDemandPage.Uodate")); //$NON-NLS-1$
        updateButton.setEnabled(false);
        updateButton.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                String newQty = demand.getText();
                selectedDemand.setQuantity(newQty);
                updateButton.setEnabled(false);
            }
        });

        initialize();
        setControl(container);

    }

    public String isValid(Object value) {
        double doubleValue = -1;
        try {
            doubleValue = Double.parseDouble(value.toString());
            return null;
        } catch (NumberFormatException exc) {
            return Messages.getString("EObjectPropertySource.notValidNumber"); //$NON-NLS-1$
        }
    }

    private SelectionListener inListListener = new SelectionAdapter() {
        public void widgetSelected(SelectionEvent e) {
            inListChanged();
        }
    };

    private void inListChanged() {
        if (inList.getSelectionIndex() != -1) {
            extOp = boundExternOpns[inList.getSelectionIndex()];
            right.setEnabled(true);
            availList.deselectAll();
            left.setEnabled(false);
            boolean found = false;
            for (Iterator checkDem = responsibility.getDemands().iterator(); checkDem.hasNext();) {
                Demand adem = (Demand) checkDem.next();
                ExternalOperation agen = (ExternalOperation) adem.getResource();
                if (agen.equals(extOp)) {
                    selectedDemand = adem;
                    found = true;
                }
            }
            if (found) {
                demand.setText(selectedDemand.getQuantity());
            }
            demand.setEnabled(true);
        } else {
            right.setEnabled(false);
            demand.setEnabled(false);
            updateButton.setEnabled(false);
        }
    }

    private SelectionListener rightBtnListener = new SelectionAdapter() {
        public void widgetSelected(SelectionEvent e) {
            removeRes();
        }
    };

    private void removeRes() {
        Demand itemToRemove = null;
        int i = 0;
        int itemToRemoveIndex = 0;
        for (Iterator demand = responsibility.getDemands().iterator(); demand.hasNext();) {
            Demand dem = (Demand) demand.next();
            if (dem.getResource().equals(extOp)) {
                itemToRemove = dem;
                itemToRemoveIndex = i;
            }
            i++;
        }
        if (itemToRemove != null) {
            removeResCmd(itemToRemove);
            updateLists();
        }
    }

    private SelectionListener availListListener = new SelectionAdapter() {
        public void widgetSelected(SelectionEvent e) {
            availListChanged();
        }
    };

    private void availListChanged() {
        if (availList.getSelectionIndex() != -1) {
            extOp = availExternOpns[availList.getSelectionIndex()];
            left.setEnabled(true);
            inList.deselectAll();
            right.setEnabled(false);
            demand.setEnabled(false);
            updateButton.setEnabled(false);
        } else {
            left.setEnabled(false);
        }
    }

    private SelectionListener leftBtnListener = new SelectionAdapter() {
        public void widgetSelected(SelectionEvent e) {
            createResCmd();
            updateLists();
        }
    };

    public void createResCmd() {
        CommandStack cs = ((UCMNavMultiPageEditor) workbenchPage.getActiveEditor()).getDelegatingCommandStack();
        CompoundCommand command = new CompoundCommand();
        quantity = "0"; //$NON-NLS-1$
        CreateDemandCommand createCmd = new CreateDemandCommand(spec, extOp, quantity, responsibility);
        command.add(createCmd);

        // use a command to be undoable.
        if (command.canExecute())
            cs.execute(command);
    }

    public void removeResCmd(Demand demandToRemove) {
        CommandStack cs = ((UCMNavMultiPageEditor) workbenchPage.getActiveEditor()).getDelegatingCommandStack();
        CompoundCommand command = new CompoundCommand();

        DeleteDemandCommand deleteCmd = new DeleteDemandCommand(spec, extOp, demandToRemove, responsibility);
        command.add(deleteCmd);

        // use a command to be undoable.
        if (command.canExecute())
            cs.execute(command);
    }

    /**
     * Ensures that the selection is legal
     */
    private void dialogChanged() {

    }

    /**
     * Tests if the current workbench selection is a suitable container to use.
     */
    private void initialize() {
        if (defaultSelected != null) {
            if (defaultSelected instanceof RespRef) {
                responsibility = ((RespRef) defaultSelected).getRespDef();
                spec = responsibility.getUrndefinition().getUrnspec();
            }
        }
        updateLists();
    }

    private void updateLists() {
        GeneralResource genRes;
        if (spec != null) {
            availExternOpns = new ExternalOperation[spec.getUrndef().getUrnspec().getUcmspec().getResources().size()];
        }
        if (responsibility != null) {
            boundExternOpns = new ExternalOperation[responsibility.getDemands().size()];
        }
        int indexRes = 0;
        int indexDem = 0;
        inList.removeAll();
        availList.removeAll();
        boolean found;
        for (Iterator resource = spec.getUrndef().getUrnspec().getUcmspec().getResources().iterator(); resource.hasNext();) {
            genRes = (GeneralResource) resource.next();
            if (genRes instanceof ExternalOperation) {
                ExternalOperation externOpn = (ExternalOperation) genRes;
                found = false;
                for (Iterator checkDem = responsibility.getDemands().iterator(); checkDem.hasNext();) {
                    Demand adem = (Demand) checkDem.next();
                    ExternalOperation agen = (ExternalOperation) adem.getResource();
                    found = found || agen.equals(externOpn);
                }
                if (!found) {
                    availList.add(((ExternalOperation) externOpn).getName() + Messages.getString("ManageDemandPage.ExternalOperation")); //$NON-NLS-1$
                    availExternOpns[indexRes] = externOpn;
                    indexRes++;
                } else {
                    inList.add(((ExternalOperation) externOpn).getName() + Messages.getString("ManageDemandPage.ExternalOperation")); //$NON-NLS-1$
                    boundExternOpns[indexDem] = externOpn;
                    indexDem++;
                }
            }
        }
        inList.deselectAll();
        inListChanged();
        availList.deselectAll();
        availListChanged();
    }

    /**
     * Updates the status of the window
     * 
     * @param message
     *            the error message or null if no error message.
     */
    private void updateStatus(String message) {
        setErrorMessage(message);
        setPageComplete(message == null);
    }

    private ExternalOperation getResource() {
        return extOp;
    }

    private String getQuantity() {
        return quantity;
    }

    private Responsibility getResponsibility() {
        return responsibility;
    }
}
