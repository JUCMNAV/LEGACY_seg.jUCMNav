package seg.jUCMNav.views.wizards.scenarios;

import java.util.HashMap;
import java.util.Iterator;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
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
import seg.jUCMNav.scenarios.ScenarioUtils;
import ucm.scenario.ScenarioDef;
import ucm.scenario.ScenarioGroup;
import urn.URNspec;

/**
 * This page allows setting initializations for existing scenarios when a new variable is created.
 * 
 */
public class AddVariableWizardInitsPage extends WizardPage {
    private String[] boolean_values = { "true", "false" }; //$NON-NLS-1$ //$NON-NLS-2$
    private HashMap initializations;
    private String[] titles = {
            Messages.getString("AddVariableWizardInitsPage.Scenario"), Messages.getString("AddVariableWizardInitsPage.ScenarioGroup"), Messages.getString("AddVariableWizardInitsPage.Initialization") }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    private URNspec urn;

    private Table scenarios;

    public String[] enum_values;

    private TableViewer viewer;

    /**
     * Initializes the wizard page with the URNspec.
     * 
     * @param urn
     */
    public AddVariableWizardInitsPage(URNspec urn) {
        super("wizardPage"); //$NON-NLS-1$

        this.setImageDescriptor(ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/perspectiveIcon.gif")); //$NON-NLS-1$

        setTitle(Messages.getString("AddVariableWizardInitsPage.NewVariableWizard")); //$NON-NLS-1$
        setDescription(Messages.getString("AddVariableWizardInitsPage.SelectVariableInitialValue")); //$NON-NLS-1$
        this.urn = urn;
    }

    /**
     * Creates the page.
     */
    public void createControl(Composite parent) {
        PlatformUI.getWorkbench().getHelpSystem().setHelp(parent, "seg.jUCMNav.scenario_addvariableinits"); //$NON-NLS-1$
        Composite container = new Composite(parent, SWT.NULL);

        GridLayout layout = new GridLayout();
        container.setLayout(layout);
        layout.numColumns = 1;
        layout.verticalSpacing = 5;

        Label label = new Label(container, SWT.NULL);
        label.setText(Messages.getString("AddVariableWizardInitsPage.SetInitialValues")); //$NON-NLS-1$

        scenarios = new Table(container, SWT.CHECK | SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL | SWT.FULL_SELECTION);
        viewer = buildAndLayoutTable(scenarios);

        attachContentProvider(viewer);
        attachLabelProvider(viewer);
        attachCellEditors(viewer, scenarios);

        initializations = new HashMap();
        for (Iterator iter = urn.getUcmspec().getScenarioGroups().iterator(); iter.hasNext();) {
            ScenarioGroup group = (ScenarioGroup) iter.next();
            for (Iterator iterator = group.getScenarios().iterator(); iterator.hasNext();) {
                ScenarioDef scenario = (ScenarioDef) iterator.next();
                TableItem item = new TableItem(scenarios, SWT.NONE);
                item.setData(scenario);
                initTableItem(scenario, item);
            }
        }

        scenarios.addListener(SWT.Selection, new Listener() {
            public void handleEvent(Event event) {
                ScenarioDef scenario = ((ScenarioDef) event.item.getData());

                if (event.detail == SWT.CHECK) {

                    toggleInitialization(scenario);
                    initTableItem(scenario, (TableItem) event.item);

                }

                // String string = event.detail == SWT.CHECK ? "Checked" :
                // "Selected";
                // System.out.println (event.item + " " + string);
            }

        });

        GridData gd = new GridData(GridData.FILL_BOTH);
        scenarios.setLayoutData(gd);

        dialogChanged();
        setControl(container);

    }

    public void setupInitializations() {
        initializations = new HashMap();
        if (viewer != null) {
            for (Iterator iter = urn.getUcmspec().getScenarioGroups().iterator(); iter.hasNext();) {
                ScenarioGroup group = (ScenarioGroup) iter.next();
                for (Iterator iterator = group.getScenarios().iterator(); iterator.hasNext();) {
                    ScenarioDef scenario = (ScenarioDef) iterator.next();
                    viewer.refresh(scenario);
                }
            }

            for (int i = 0; i < viewer.getTable().getItems().length; i++) {
                TableItem item = viewer.getTable().getItems()[i];
                item.setChecked(getInitialization((ScenarioDef) item.getData()) != null);

            }
        }
    }

    /**
     * Ensures that the selection is legal
     */
    private void dialogChanged() {
        // always OK
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

    private String getVariableType() {
        AddVariableWizard wizard = (AddVariableWizard) getWizard();
        AddVariableWizardPage page1 = (AddVariableWizardPage) wizard.getPages()[0];
        return page1.getVariableType();
    }

    private void attachCellEditors(final TableViewer viewer, final Composite parent) {
        viewer.setCellModifier(new ICellModifier() {
            public boolean canModify(Object element, String property) {

                if (property.equals(titles[2])) {
                    if (getVariableType().equals(ScenarioUtils.sTypeInteger) && viewer.getCellEditors()[2] instanceof ComboBoxCellEditor) {
                        viewer.getCellEditors()[2].dispose();
                        viewer.getCellEditors()[2] = new TextCellEditor(parent);
                    } else if (!getVariableType().equals(ScenarioUtils.sTypeInteger)) {
                        if (viewer.getCellEditors()[2] instanceof TextCellEditor) {
                            viewer.getCellEditors()[2].dispose();
                            if (getVariableType().equals(ScenarioUtils.sTypeBoolean))
                                viewer.getCellEditors()[2] = new ComboBoxCellEditor(parent, boolean_values, SWT.READ_ONLY);
                            else
                                viewer.getCellEditors()[2] = new ComboBoxCellEditor(parent, enum_values, SWT.READ_ONLY);

                        } else {
                            ComboBoxCellEditor cbce = (ComboBoxCellEditor) viewer.getCellEditors()[2];
                            // make sure we have correct values.
                            if (getVariableType().equals(ScenarioUtils.sTypeBoolean)) {
                                if (cbce.getItems() != boolean_values)
                                    cbce.setItems(boolean_values);
                            } else {
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
                    return ((ScenarioDef) element).getName();
                else if (property.equals(titles[1]))
                    return ((ScenarioDef) element).getGroup().getName();
                else {
                    if (getInitialization((ScenarioDef) element) != null) {
                        if (getVariableType().equals(ScenarioUtils.sTypeInteger))
                            return getInitialization((ScenarioDef) element);
                        else if (getVariableType().equals(ScenarioUtils.sTypeBoolean))
                            return getInitialization((ScenarioDef) element).equals(boolean_values[0]) ? new Integer(0) : new Integer(1);
                        else {
                            for (int i = 0; i < enum_values.length; i++) {
                                if (getInitialization((ScenarioDef) element).equals(enum_values[i]))
                                    return new Integer(i);
                            }
                            return new Integer(0);
                        }
                    } else {
                        if (getVariableType().equals(ScenarioUtils.sTypeInteger))
                            return "0"; //$NON-NLS-1$
                        else
                            return Integer.valueOf(0);
                    }
                }
            }

            public void modify(Object element, String property, Object value) {
                TableItem tableItem = (TableItem) element;
                ScenarioDef data = (ScenarioDef) tableItem.getData();

                if (getInitialization(data) == null) {
                    toggleInitialization(data);
                    tableItem.setChecked(true);
                }

                if (getVariableType().equals(ScenarioUtils.sTypeInteger)) {

                    try {
                        String val = Integer.toString(Integer.parseInt(value.toString()));
                        setInitialization(data, val);

                    } catch (NumberFormatException ex) {
                        setInitialization(data, "0"); //$NON-NLS-1$
                    }

                } else if (getVariableType().equals(ScenarioUtils.sTypeBoolean)) {
                    setInitialization(data, boolean_values[((Integer) value).intValue()]);
                } else
                    setInitialization(data, enum_values[((Integer) value).intValue()]);

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
                    return ((ScenarioDef) element).getName();
                case 1:
                    return ((ScenarioDef) element).getGroup().getName();
                case 2:
                    if (getInitialization((ScenarioDef) element) != null)
                        return getInitialization((ScenarioDef) element);
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

        scenarios.setHeaderVisible(true);
        scenarios.setLinesVisible(true);

        for (int i = 0; i < titles.length; i++) {
            TableColumn column = new TableColumn(scenarios, SWT.NONE);
            column.setText(titles[i]);
            column.setWidth(125);
        }

        return tableViewer;
    }

    private String getInitialization(ScenarioDef scenario) {
        return ((String) initializations.get(scenario));
    }

    private void setInitialization(ScenarioDef scenario, String value) {
        if (value == null)
            initializations.remove(scenario);
        else
            initializations.put(scenario, value);
    }

    private void initTableItem(ScenarioDef scenario, TableItem item) {
        if (getInitialization(scenario) != null) {
            item.setText(new String[] { scenario.getName(), scenario.getGroup().getName(), getInitialization(scenario) });
            item.setChecked(true);
        } else {
            item.setText(new String[] { scenario.getName(), scenario.getGroup().getName(), "" }); //$NON-NLS-1$
            item.setChecked(false);
        }
    }

    private void toggleInitialization(ScenarioDef scenario) {
        if (getInitialization(scenario) != null) {
            initializations.remove(scenario);

        } else {
            if (getVariableType().equals(ScenarioUtils.sTypeBoolean)) {
                initializations.put(scenario, "false"); //$NON-NLS-1$
            } else if (getVariableType().equals(ScenarioUtils.sTypeInteger)) {
                initializations.put(scenario, "0"); //$NON-NLS-1$
            } else {
                initializations.put(scenario, enum_values[0]);
            }
        }
    }

    public HashMap getInitializations() {
        return initializations;
    }

}
