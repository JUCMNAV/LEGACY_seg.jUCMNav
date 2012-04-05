package seg.jUCMNav.views.wizards.scenarios;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.Messages;
import seg.jUCMNav.model.util.URNNamingHelper;
import seg.jUCMNav.scenarios.ScenarioUtils;
import seg.jUCMNav.scenarios.parser.SimpleNode;
import seg.jUCMNav.views.preferences.GeneralPreferencePage;
import seg.jUCMNav.views.preferences.ScenarioTraversalPreferences;
import ucm.map.FailurePoint;
import ucm.map.NodeConnection;
import ucm.scenario.ScenarioDef;
import ucm.scenario.Variable;
import urn.URNspec;
import urncore.Condition;
import urncore.Responsibility;
import urncore.URNmodelElement;

/**
 * The page actually containing the code editor for responsibilities and conditions.
 * 
 */
public class CodeEditorPage extends WizardPage {
    private Text codeText;
    private Text labelText;
    private Label labelLabel;
    private Text descriptionText;

    private ISelection selection;
    private Responsibility resp;
    private FailurePoint failure;
    private Condition cond;
    private NodeConnection nc;

    private URNspec urn;
    private List variables;
    private Combo possibilities;

    private Vector allPossibilities;
    private EObject defaultSelected;
    private HashMap code;
    private HashMap labels;
    private HashMap descriptions;

    private ModifyListener modifyListener = new ModifyListener() {
        public void modifyText(ModifyEvent e) {
            dialogChanged();
        }
    };

    /**
     * The selection contains either a responsibility or a condition. Loaded in {@link #initialize()}.
     * 
     * @param selection
     */
    public CodeEditorPage(ISelection selection, EObject defaultSelected) {
        super("wizardPage"); //$NON-NLS-1$

        this.setImageDescriptor(ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/perspectiveIcon.gif")); //$NON-NLS-1$

        // loaded in initialize()
        this.selection = selection;
        this.defaultSelected = defaultSelected;
        this.code = new HashMap();
        this.labels = new HashMap();
        this.descriptions = new HashMap();
        this.allPossibilities = new Vector();
    }

    /**
     * Creates the page.
     */
    public void createControl(Composite parent) {
        PlatformUI.getWorkbench().getHelpSystem().setHelp(parent, "seg.jUCMNav.scenario_codeeditor"); //$NON-NLS-1$
        Composite container = new Composite(parent, SWT.NULL);

        GridLayout layout = new GridLayout();
        container.setLayout(layout);
        layout.numColumns = 2;
        layout.verticalSpacing = 5;

        possibilities = new Combo(container, SWT.DROP_DOWN | SWT.READ_ONLY);

        possibilities.addSelectionListener(new SelectionListener() {

            public void widgetSelected(SelectionEvent e) {
                // single click.

                if (possibilities.getSelectionIndex() >= 0) {
                    EObject o = (EObject) allPossibilities.get(possibilities.getSelectionIndex());
                    if (o != defaultSelected) {
                        defaultSelected = o;
                        setupText(o);

                        if (defaultSelected instanceof Responsibility)
                            resp = (Responsibility) defaultSelected;
                        else if (defaultSelected instanceof FailurePoint)
                            failure = (FailurePoint) defaultSelected;
                        else if (defaultSelected instanceof NodeConnection)
                            nc = (NodeConnection) defaultSelected;
                        else
                            cond = (Condition) defaultSelected;
                    }
                }
            }

            public void widgetDefaultSelected(SelectionEvent e) {
                // double click.

            }

        });

        GridData gd = new GridData(GridData.FILL_HORIZONTAL);
        gd.horizontalSpan = 2;
        possibilities.setLayoutData(gd);

        labelLabel = new Label(container, SWT.NULL);
        gd = new GridData(GridData.FILL_HORIZONTAL);
        gd.horizontalSpan = 2;
        labelLabel.setLayoutData(gd);

        labelText = new Text(container, SWT.BORDER);
        gd = new GridData(GridData.FILL_HORIZONTAL);
        gd.horizontalSpan = 2;
        labelText.setLayoutData(gd);
        labelText.addModifyListener(modifyListener);

        Label label = new Label(container, SWT.NULL);
        label.setText(Messages.getString("CodeEditorPage.Description")); //$NON-NLS-1$
        gd = new GridData(GridData.FILL_HORIZONTAL);
        gd.horizontalSpan = 2;
        label.setLayoutData(gd);

        descriptionText = new Text(container, SWT.BORDER);
        gd = new GridData(GridData.FILL_HORIZONTAL);
        gd.horizontalSpan = 2;
        descriptionText.setLayoutData(gd);
        descriptionText.addModifyListener(modifyListener);

        // label over the code box.
        label = new Label(container, SWT.NULL);
        label.setText(Messages.getString("CodeEditorPage.EnterTheCode")); //$NON-NLS-1$

        // label over the variable box
        label = new Label(container, SWT.NULL);
        label.setText(Messages.getString("CodeEditorPage.DoubleClickOnVariable")); //$NON-NLS-1$

        // simple multi-line scrollable text-box that grows with box.
        codeText = new Text(container, SWT.BORDER | SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);

        gd = new GridData(GridData.FILL_BOTH);
        codeText.setLayoutData(gd);
        codeText.addModifyListener(modifyListener);

        // variable list
        variables = new List(container, SWT.SINGLE | SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
        variables.addSelectionListener(new SelectionListener() {

            public void widgetSelected(SelectionEvent e) {
                // single click.

            }

            public void widgetDefaultSelected(SelectionEvent e) {
                // double click.

                if (variables.getSelection().length > 0) {
                    codeText.insert(variables.getSelection()[0]);
                    codeText.forceFocus();
                }

            }

        });

        gd = new GridData(GridData.FILL_VERTICAL);

        gd.verticalSpan = 2;
        gd.widthHint = 100;
        gd.heightHint = 200;
        variables.setLayoutData(gd);

        // Button to open the new variable wizard.
        Button button = new Button(container, SWT.PUSH);
        button.setText(Messages.getString("CodeEditorPage.CreateNewVariable")); //$NON-NLS-1$
        button.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
                AddVariableWizard wizard = new AddVariableWizard();

                if (urn != null) {
                    wizard.init(PlatformUI.getWorkbench(), new StructuredSelection(urn));
                    WizardDialog dialog = new WizardDialog(shell, wizard);
                    dialog.open();
                    initVariables();
                    dialogChanged();

                }
            }
        });

        // 
        initialize();

        refreshPossibilityLabels();

        possibilities.select(allPossibilities.indexOf(defaultSelected));

        if (isResponsibility()) {
            setTitle(Messages.getString("CodeEditorPage.ResponsibilityEditor")); //$NON-NLS-1$
            setDescription(Messages.getString("CodeEditorPage.PleaseEnterResponsibility")); //$NON-NLS-1$
        } else if (isFailurePoint()) {
            setTitle(Messages.getString("CodeEditorPage.FailureExpressionEditor")); //$NON-NLS-1$
            setDescription(Messages.getString("CodeEditorPage.FailurePointPseudoCode")); //$NON-NLS-1$
        } else if (isNodeConnection()) {
            setTitle(Messages.getString("CodeEditorPage.ThresholdEditor")); //$NON-NLS-1$
            setDescription(Messages.getString("CodeEditorPage.ThresholdPseudoCode")); //$NON-NLS-1$
        } else {
            setDescription(Messages.getString("CodeEditorPage.PleaseEnterCondition")); //$NON-NLS-1$
            setTitle(Messages.getString("CodeEditorPage.ConditionEditor")); //$NON-NLS-1$
        }

        dialogChanged();
        setControl(container);
        codeText.forceFocus();

    }

    private void refreshPossibilityLabels() {
        boolean add = possibilities.getItemCount() == 0;

        for (int i = 0; i < allPossibilities.size(); i++) {
            EObject element = (EObject) allPossibilities.get(i);

            if (element instanceof URNmodelElement) {
                String name = URNNamingHelper.getName((URNmodelElement) element);
                if (labels.get(element) != null && labels.get(element).toString().length() > 0)
                    name = labels.get(element).toString();

                if (add)
                    possibilities.add(name);
                else {
                    possibilities.setItem(i, name);
                }

            } else if (element instanceof urncore.Condition) {

                if (add)
                    possibilities.add(labels.get(element) != null && labels.get(element).toString().length() > 0 ? labels.get(element).toString()
                            : URNNamingHelper.getName((urncore.Condition) element));
                else {
                    possibilities.setItem(i, labels.get(element) != null && labels.get(element).toString().length() > 0 ? labels.get(element).toString()
                            : URNNamingHelper.getName((urncore.Condition) element, code.get(element).toString()));
                }

            } else if (element instanceof NodeConnection)
            {
                if (add)
                    possibilities.add(Messages.getString("CodeEditorPage.Threshold")); //$NON-NLS-1$
                else
                    possibilities.setItem(i, Messages.getString("CodeEditorPage.Threshold")); //$NON-NLS-1$
            }

        }
    }

    /**
     * Tests if the current workbench selection is a suitable container to use.
     */
    private void initialize() {
        if (selection != null && selection.isEmpty() == false && selection instanceof IStructuredSelection) {
            IStructuredSelection ssel = (IStructuredSelection) selection;

            initPossibilities(ssel);

            if (defaultSelected == null)
                defaultSelected = (EObject) ssel.getFirstElement();

            setupText(defaultSelected);
        }

        if (defaultSelected instanceof Responsibility)
            resp = (Responsibility) defaultSelected;
        else if (defaultSelected instanceof FailurePoint)
            failure = (FailurePoint) defaultSelected;
        else if (defaultSelected instanceof NodeConnection)
            nc = (NodeConnection) defaultSelected;
        else
            cond = (Condition) defaultSelected;

        // assuming same of all items in list.
        EObject o;
        if (resp != null)
            urn = resp.getUrndefinition().getUrnspec();
        else if (failure != null)
            urn = failure.getDiagram().getUrndefinition().getUrnspec();
        else if (nc != null)
            urn = nc.getDiagram().getUrndefinition().getUrnspec();
        else {
            o = cond.eContainer();
            while (o != null) {
                if (o instanceof URNspec) {
                    urn = (URNspec) o;
                }
                o = o.eContainer();
            }

        }

        initVariables();
        try {
            ScenarioUtils.getEnvironment(urn).refresh(); // bug 825. 
        } catch (Exception ex)
        {
        
        }
    }

    private void setupText(Object obj) {
        codeText.removeModifyListener(modifyListener);
        labelText.removeModifyListener(modifyListener);
        descriptionText.removeModifyListener(modifyListener);

        labelText.setEnabled(true);
        descriptionText.setEnabled(true);
        
        if (obj instanceof Responsibility || obj instanceof FailurePoint) {
            if (obj instanceof Responsibility)
                resp = (Responsibility) obj;
            else
                failure = (FailurePoint) obj;

            if (code.get(obj) == null)
                codeText.setText(""); //$NON-NLS-1$
            else
                codeText.setText(code.get(obj).toString());

            labelLabel.setText(Messages.getString("CodeEditorPage.Name")); //$NON-NLS-1$

            if (labels.get(obj) == null)
                labelText.setText(""); //$NON-NLS-1$
            else
                labelText.setText(labels.get(obj).toString());

            if (descriptions.get(obj) == null)
                descriptionText.setText(""); //$NON-NLS-1$
            else
                descriptionText.setText(descriptions.get(obj).toString());

        } else if (obj instanceof Condition) {
            cond = (Condition) obj;

            if (code.get(cond) == null)
                codeText.setText("true"); //$NON-NLS-1$
            else
                codeText.setText(code.get(cond).toString());

            labelLabel.setText(Messages.getString("CodeEditorPage.Label")); //$NON-NLS-1$

            if (labels.get(cond) != null) {
                labelText.setText(labels.get(cond).toString());
            } else {
                if (cond.getLabel() == null)
                    labelText.setText(""); //$NON-NLS-1$
                else
                    labelText.setText(cond.getLabel());
            }
            if (cond.getDescription() == null)
                descriptionText.setText(""); //$NON-NLS-1$
            else
                descriptionText.setText(cond.getDescription());

        } else if (obj instanceof NodeConnection) {
            nc = (NodeConnection) obj;

            if (code.get(nc) == null)
                codeText.setText("true"); //$NON-NLS-1$
            else
                codeText.setText(code.get(nc).toString());

            labelLabel.setText(Messages.getString("CodeEditorPage.Label")); //$NON-NLS-1$

            labelText.setText("");//$NON-NLS-1$
            labelText.setEnabled(false);
            descriptionText.setText(""); //$NON-NLS-1$
            descriptionText.setEnabled(false);
        }        
        codeText.addModifyListener(modifyListener);
        labelText.addModifyListener(modifyListener);
        descriptionText.addModifyListener(modifyListener);

    }

    private void initPossibilities(IStructuredSelection ssel) {
        boolean found = false;
        for (Iterator iter = ssel.iterator(); iter.hasNext();) {
            EObject element = (EObject) iter.next();
            allPossibilities.add(element);

            if (element == defaultSelected)
                found = true;

            if (element instanceof Responsibility) {
                Responsibility r = (Responsibility) element;
                if (r.getExpression() == null)
                    code.put(element, ""); //$NON-NLS-1$
                else
                    code.put(element, r.getExpression());

                if (r.getName() == null)
                    labels.put(element, ""); //$NON-NLS-1$
                else
                    labels.put(element, r.getName());

                if (r.getDescription() == null)
                    descriptions.put(element, ""); //$NON-NLS-1$
                else
                    descriptions.put(element, r.getDescription());

            } else if (element instanceof FailurePoint) {
                FailurePoint f = (FailurePoint) element;
                if (f.getExpression() == null)
                    code.put(element, ""); //$NON-NLS-1$
                else
                    code.put(element, f.getExpression());

                if (f.getName() == null)
                    labels.put(element, ""); //$NON-NLS-1$
                else
                    labels.put(element, f.getName());

                if (f.getDescription() == null)
                    descriptions.put(element, ""); //$NON-NLS-1$
                else
                    descriptions.put(element, f.getDescription());

            } else if (element instanceof Condition) {
                Condition c = (Condition) element;
                if (c.getExpression() == null)
                    code.put(element, "true"); //$NON-NLS-1$
                else
                    code.put(element, c.getExpression());

                if (c.getLabel() == null)
                    labels.put(element, ""); //$NON-NLS-1$
                else
                    labels.put(element, c.getLabel());

                if (c.getDescription() == null)
                    descriptions.put(element, ""); //$NON-NLS-1$
                else
                    descriptions.put(element, c.getDescription());
            } else if (element instanceof NodeConnection) {
                NodeConnection c = (NodeConnection) element;
                if (c.getThreshold() == null)
                    code.put(element, ""); //$NON-NLS-1$
                else
                    code.put(element, c.getThreshold());

                labels.put(element, ""); //$NON-NLS-1$
                descriptions.put(element, ""); //$NON-NLS-1$
            }
        }

        // ignore it if it wasn't in the list.
        if (!found)
            defaultSelected = null;
    }

    private void initVariables() {
        Vector v = new Vector();

        for (int i = 0; i < urn.getUcmspec().getVariables().size(); i++) {
            v.add(((Variable) urn.getUcmspec().getVariables().get(i)).getName());
        }

        Collections.sort(v);

        Vector v2 = URNNamingHelper.getGrlVariableNames(urn);

        if (!ScenarioTraversalPreferences.getShouldIntegrateStrategyVariables())
            v2.clear(); // don't add GRL variables.

        String[] vars = new String[v.size() + v2.size()];

        int i = 0;
        for (; i < v.size(); i++) {
            vars[i] = v.get(i).toString();
        }
        for (; i - v.size() < v2.size(); i++) {
            vars[i] = v2.get(i - v.size()).toString();
        }
        variables.setItems(vars);

    }

    /**
     * Ensures that the pseudo-code is legal (syntax and type checking)
     */
    private void dialogChanged() {
        if (isResponsibility()) {
            String msg = URNNamingHelper.isNameValid(resp, labelText.getText());
            if (msg.length() > 0) {
                updateStatus(msg);
                setPageComplete(false);
                return;

            }
        }

        labels.put(defaultSelected, labelText.getText());
        descriptions.put(defaultSelected, descriptionText.getText());

        if ((isEditingExpression() || isNodeConnection()) && ScenarioUtils.isEmptyResponsibility(getCode())) {
            code.put(defaultSelected, ""); //$NON-NLS-1$
            updateStatus(null);
        } else {
            Object o;

            if (isResponsibility())
                o = ScenarioUtils.parse(getCode(), ScenarioUtils.getEnvironment(resp), true);
            else if (isFailurePoint())
                o = ScenarioUtils.parse(getCode(), ScenarioUtils.getEnvironment(failure), true);
            else if (isNodeConnection())
                o = ScenarioUtils.parseInteger(getCode(), ScenarioUtils.getEnvironment(nc));
            else
                o = ScenarioUtils.parse(getCode(), ScenarioUtils.getEnvironment(cond), false);

            if (o instanceof SimpleNode) {
                code.put(defaultSelected, getCode());
                updateStatus(null);
            } else if (ScenarioUtils.IS_ELSE_CONDITION_ALLOWED && !isEditingExpression() && ScenarioUtils.isElseCondition(getCode())
                    && !(cond.eContainer() instanceof ScenarioDef)) {
                // bug 497: we will allow else in conditions.
                if (allPossibilities.size() > 1) {
                    code.put(defaultSelected, getCode());
                    updateStatus(null);
                } else {
                    updateStatus(Messages.getString("CodeEditorPage.YouMayOnlyUseElseWhenOtherConditionsExist")); //$NON-NLS-1$
                }

            } else {

                if (!GeneralPreferencePage.getStrictCodeEditor()) {
                    code.put(defaultSelected, getCode());
                }
                updateStatus((String) o);
            }
        }

    }

    private boolean isEditingExpression() {
        return isResponsibility() || isFailurePoint();
    }

    /**
     * Updates the status of the window
     * 
     * @param message
     *            the error message or null if no error message.
     */
    private void updateStatus(String message) {
        setErrorMessage(message);

        if (GeneralPreferencePage.getStrictCodeEditor())
            setPageComplete(message == null);
        else
            setPageComplete(true);

        refreshPossibilityLabels();
        possibilities.setEnabled(isPageComplete());

    }

    /**
     * Returns the responsibility for which the pseudo-code is being edited.
     * 
     * @return the responsibility being edited.
     */
    public Responsibility getResponsibility() {
        return resp;
    }

    /**
     * Returns the condition for which the pseudo-code is being edited.
     * 
     * @return the condition being edited.
     */
    public Condition getCondition() {
        return cond;
    }

    /**
     * Are we editing a responsibility or a condition?
     * 
     * @return true if responsibility, false otherwise.
     */
    public boolean isResponsibility() {
        return resp != null;
    }

    /**
     * Are we editing a failure point?
     * 
     * @return true if failure point, false otherwise.
     */
    public boolean isFailurePoint() {
        return failure != null;
    }
    
    /**
     * Are we editing a node connection?
     * 
     * @return true if node connection, false otherwise.
     */
    public boolean isNodeConnection() {
        return nc != null;
    }

    /**
     * The pseudo-code entered in the text box.
     * 
     * @return the pseudo-code
     */
    public String getCode() {
        return codeText.getText();
    }

    /**
     * Code for all objects that were passed. Assumed to be always valid.
     * 
     * @return a hashmap of eobject->string
     */
    public HashMap getAllCode() {
        return code;
    }

    /**
     * Labels for conditions, names for responsibilities. Assumed to be always valid.
     * 
     * @return a hashmap of eobject->string
     */
    public HashMap getAllLabels() {
        return labels;
    }

    /**
     * Descriptions for all objects that were passed. Assumed to be always valid.
     * 
     * @return a hashmap of eobject->string
     */
    public HashMap getAllDescriptions() {
        return descriptions;
    }
}
