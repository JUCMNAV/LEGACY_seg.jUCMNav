package seg.jUCMNav.views.wizards.scenarios;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.PlatformUI;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.create.CreateVariableInitializationCommand;
import seg.jUCMNav.model.commands.delete.DeleteVariableInitializationCommand;
import seg.jUCMNav.model.commands.transformations.ChangeCodeCommand;
import seg.jUCMNav.model.util.URNmodelElementNameComparator;
import seg.jUCMNav.scenarios.ScenarioUtils;
import ucm.scenario.Initialization;
import ucm.scenario.ScenarioDef;
import ucm.scenario.Variable;

/**
 * The page actually containing the code for initializing variables.
 * 
 * 
 */
public class ScenarioInitializationsPage extends WizardPage {
    private String[] boolean_values = { "true", "false" }; //$NON-NLS-1$ //$NON-NLS-2$
    private String[] enum_values;
    private int commandCount;
    private HashMap initializations;
    private ScenarioDef parent;
    private ISelection selection;
    private String[] titles = {
            Messages.getString("ScenarioInitializationsPage.Variable"), Messages.getString("ScenarioInitializationsPage.Type"), Messages.getString("ScenarioInitializationsPage.Initialization") }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

    private Table variables;

    private TableViewer viewer;

    /**
     * The selection contains a scenario definition for which we want to include another scenario. Loaded in {@link #initialize()}.
     * 
     * @param selection
     */
    public ScenarioInitializationsPage(ISelection selection) {
        super("wizardPage"); //$NON-NLS-1$

        this.setImageDescriptor(ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/perspectiveIcon.gif")); //$NON-NLS-1$

        setTitle(Messages.getString("ScenarioInitializationsPage.VariableInitializations")); //$NON-NLS-1$
        setDescription(Messages.getString("ScenarioInitializationsPage.PleaseSelectVariables")); //$NON-NLS-1$

        // loaded in initialize()
        this.selection = selection;
        commandCount = 0;
    }

    private void attachCellEditors(final TableViewer viewer, final Composite parent) {
        viewer.setCellModifier(new ICellModifier() {
            public boolean canModify(Object element, String property) {
                String type = ((Variable) element).getType();

                if (property.equals(titles[2])) {
                    if (type.equals(ScenarioUtils.sTypeInteger) && viewer.getCellEditors()[2] instanceof ComboBoxCellEditor) {
                        viewer.getCellEditors()[2].dispose();
                        viewer.getCellEditors()[2] = new TextCellEditor(parent);
                    } else if (!type.equals(ScenarioUtils.sTypeInteger)) {
                        if (viewer.getCellEditors()[2] instanceof TextCellEditor) {
                            viewer.getCellEditors()[2].dispose();
                            if (type.equals(ScenarioUtils.sTypeBoolean))
                                viewer.getCellEditors()[2] = new ComboBoxCellEditor(parent, boolean_values, SWT.READ_ONLY);
                            else {
                                enum_values = ((Variable) element).getEnumerationType().getValues().split(","); //$NON-NLS-1$
                                viewer.getCellEditors()[2] = new ComboBoxCellEditor(parent, enum_values, SWT.READ_ONLY);
                            }

                        } else {
                            ComboBoxCellEditor cbce = (ComboBoxCellEditor) viewer.getCellEditors()[2];
                            // make sure we have correct values.
                            if (type.equals(ScenarioUtils.sTypeBoolean)) {
                                if (cbce.getItems() != boolean_values)
                                    cbce.setItems(boolean_values);
                            } else {

                                enum_values = ((Variable) element).getEnumerationType().getValues().split(","); //$NON-NLS-1$
                                if (cbce.getItems() != enum_values)
                                    cbce.setItems(enum_values);
                            }
                        }

                    }
                    viewer.getCellEditors()[2].setValue(getValue(element, property));
                }

                return (property.equals(titles[2]));
            }

            public Object getValue(Object element, String property) {
                if (property.equals(titles[0]))
                    return ((Variable) element).getName();
                else if (property.equals(titles[1]))
                    return ((Variable) element).getType();
                else {
                    if (getInitialization((Variable) element) != null) {
                        if (((Variable) element).getType().equals(ScenarioUtils.sTypeInteger))
                            return getInitialization((Variable) element).getValue();
                        else if (((Variable) element).getType().equals(ScenarioUtils.sTypeBoolean))
                            return getInitialization((Variable) element).getValue().equals(boolean_values[0]) ? new Integer(0) : new Integer(1);
                        else {
                            enum_values = ((Variable) element).getEnumerationType().getValues().split(","); //$NON-NLS-1$
                            for (int i = 0; i < enum_values.length; i++) {
                                if (getInitialization((Variable) element) != null && getInitialization((Variable) element).getValue() != null
                                        && getInitialization((Variable) element).getValue().equals(enum_values[i]))
                                    return new Integer(i);
                            }
                            return new Integer(0);
                        }
                    } else {
                        if (((Variable) element).getType().equals(ScenarioUtils.sTypeInteger))
                            return "0"; //$NON-NLS-1$
                        else
                            return Integer.valueOf(0);
                    }
                }
            }

            public void modify(Object element, String property, Object value) {
                TableItem tableItem = (TableItem) element;
                Variable data = (Variable) tableItem.getData();

                if (getInitialization(data) == null) {
                    toggleInitialization(data);
                    tableItem.setChecked(true);
                }
                ChangeCodeCommand command = null;

                if (data.getType().equals(ScenarioUtils.sTypeInteger)) {

                    try {
                        String val = Integer.toString(Integer.parseInt(value.toString()));
                        if (!getInitialization(data).getValue().equals(val))
                            command = new ChangeCodeCommand(getInitialization(data), val);

                    } catch (NumberFormatException ex) {
                        if (!getInitialization(data).getValue().equals("0")) //$NON-NLS-1$
                            command = new ChangeCodeCommand(getInitialization(data), "0"); //$NON-NLS-1$
                    }

                } else if (data.getType().equals(ScenarioUtils.sTypeBoolean)) {
                    if (!getInitialization(data).getValue().equals(boolean_values[((Integer) value).intValue()]))
                        command = new ChangeCodeCommand(getInitialization(data), boolean_values[((Integer) value).intValue()]);
                } else
                    command = new ChangeCodeCommand(getInitialization(data), enum_values[((Integer) value).intValue()]);

                if (command != null && getInitialization(data) != null)
                    execute(command);

                viewer.refresh(data);
            }
        });

        viewer.setCellEditors(new CellEditor[] { new TextCellEditor(parent), null, new ComboBoxCellEditor(parent, boolean_values, SWT.READ_ONLY) });

        viewer.setColumnProperties(titles);
    }

    private void attachContentProvider(TableViewer viewer) {
        viewer.setContentProvider(new IStructuredContentProvider() {
            public void dispose() {
            }

            public Object[] getElements(Object inputElement) {
                return (Object[]) inputElement;
            }

            public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
            }
        });
    }

    private void attachLabelProvider(TableViewer viewer) {
        viewer.setLabelProvider(new ITableLabelProvider() {
            public void addListener(ILabelProviderListener listener) {
            }

            public void dispose() {
            }

            public Image getColumnImage(Object element, int columnIndex) {
                return null;
            }

            public String getColumnText(Object element, int columnIndex) {
                switch (columnIndex) {
                case 0:
                    return ((Variable) element).getName();
                case 1:
                    return ((Variable) element).getType();
                case 2:
                    if (getInitialization((Variable) element) != null)
                        return getInitialization((Variable) element).getValue();
                    else
                        return ""; //$NON-NLS-1$
                default:
                    return "Invalid column: " + columnIndex; //$NON-NLS-1$
                }
            }

            public boolean isLabelProperty(Object element, String property) {
                return false;
            }

            public void removeListener(ILabelProviderListener lpl) {
            }
        });
    }

    private TableViewer buildAndLayoutTable(final Table table) {
        TableViewer tableViewer = new TableViewer(table);

        variables.setHeaderVisible(true);
        variables.setLinesVisible(true);

        for (int i = 0; i < titles.length; i++) {
            TableColumn column = new TableColumn(variables, SWT.NONE);
            column.setText(titles[i]);
            column.setWidth(100);
        }

        return tableViewer;
    }

    /**
     * Creates the page.
     */
    public void createControl(Composite parent) {
        PlatformUI.getWorkbench().getHelpSystem().setHelp(parent, "seg.jUCMNav.scenario_init"); //$NON-NLS-1$

        Composite container = new Composite(parent, SWT.NULL);

        GridLayout layout = new GridLayout();
        container.setLayout(layout);
        layout.numColumns = 1;
        layout.verticalSpacing = 5;

        // label over the code box.
        Label label = new Label(container, SWT.NULL);
        label.setText(getDescription());

        initialize();

        variables = new Table(container, SWT.CHECK | SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL | SWT.FULL_SELECTION);
        viewer = buildAndLayoutTable(variables);

        attachContentProvider(viewer);
        attachLabelProvider(viewer);
        attachCellEditors(viewer, variables);

        Vector v = new Vector(getParentScenario().getGroup().getUcmspec().getVariables());
        Collections.sort(v, new URNmodelElementNameComparator());

        for (int i = 0; i < v.size(); i++) {
            Variable var = (Variable) v.get(i);
            TableItem item = new TableItem(variables, SWT.NONE, i);
            item.setData(var);
            initTableItem(var, item);
        }

        variables.addListener(SWT.Selection, new Listener() {
            public void handleEvent(Event event) {
                Variable var = ((Variable) event.item.getData());

                if (event.detail == SWT.CHECK) {

                    toggleInitialization(var);
                    initTableItem(var, (TableItem) event.item);

                }

                // String string = event.detail == SWT.CHECK ? "Checked" :
                // "Selected";
                // System.out.println (event.item + " " + string);
            }

        });

        GridData gd = new GridData(GridData.FILL_BOTH);
        variables.setLayoutData(gd);

        setControl(container);

    }

    private void execute(Command command) {
        CommandStack cs = ((ScenarioInitializations) getWizard()).getCommandStack();
        if (command.canExecute()) {
            commandCount++;
            cs.execute(command);
        }
    }

    public int getCommandCount() {
        return commandCount;
    }

    private Initialization getInitialization(Variable var) {
        return ((Initialization) initializations.get(var));
    }

    /**
     * Returns the parent scenario that was initially given
     * 
     * @return the child
     */
    public ScenarioDef getParentScenario() {
        return parent;
    }

    /**
     * Tests if the current workbench selection is a suitable container to use.
     */
    private void initialize() {
        if (selection != null && selection.isEmpty() == false && selection instanceof IStructuredSelection) {
            IStructuredSelection ssel = (IStructuredSelection) selection;
            if (ssel.size() > 1)
                return;
            Object obj = ssel.getFirstElement();
            if (obj instanceof ScenarioDef) {
                parent = (ScenarioDef) obj;

                initializations = new HashMap();
                for (Iterator iter = parent.getGroup().getUcmspec().getVariables().iterator(); iter.hasNext();) {
                    Variable var = (Variable) iter.next();
                    Initialization initialization = null;
                    for (Iterator iterator = parent.getInitializations().iterator(); iterator.hasNext();) {
                        Initialization init = (Initialization) iterator.next();
                        if (init.getVariable() == var)
                            initialization = init;
                    }
                    initializations.put(var, initialization);
                }
            }
        }

    }

    private void initTableItem(Variable var, TableItem item) {
        if (getInitialization(var) != null) {
            item.setText(new String[] { var.getName(), var.getType(), getInitialization(var).getValue() });
            item.setChecked(true);
        } else {
            item.setText(new String[] { var.getName(), var.getType(), "" }); //$NON-NLS-1$
            item.setChecked(false);
        }
    }

    private void toggleInitialization(Variable var) {
        if (getInitialization(var) != null) {
            DeleteVariableInitializationCommand command = new DeleteVariableInitializationCommand(getInitialization(var));
            execute(command);
            initializations.put(var, null);

        } else {

            CreateVariableInitializationCommand command = new CreateVariableInitializationCommand(var, getParentScenario());
            execute(command);
            initializations.put(var, command.getInitialization());
        }
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

}
