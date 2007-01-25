package seg.jUCMNav.views.wizards.metadata;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.MenuAdapter;
import org.eclipse.swt.events.MenuEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.Messages;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.util.URNNamingHelper;
import urn.URNspec;
import urncore.Metadata;
import urncore.URNmodelElement;

/**
 * The page actually containing the metadata editor for urn model elements.
 * 
 * @author pchen
 */
public class MetadataEditorPage extends WizardPage {
    private Shell shell;
    private Composite container;
    private Table metadataTable;
    private static final String[] columnNames = { Messages.getString("MetadataEditorPage.column1"), Messages.getString("MetadataEditorPage.column2") }; //$NON-NLS-1$ //$NON-NLS-2$

    private ISelection selection;
    private URNmodelElement urnelem;
    private URNspec urn;
    private Label typeLabel;
    private Label elementLabel;
    private Combo typeOfElements;
    private Combo possibilities;

    private Vector allPossibilities;
    private Vector selectedPossibilities;
    private EObject defaultSelected;
    private HashMap metadataMap;
    private String[] copyBuffer;

    private Button buttonAdd;
    private Button buttonEdit;
    private Button buttonRemove;
    private Button buttonRemoveAll;

    private int lastSortColumn = -1;

    private SelectionListener metadataTableSelectionListener = new SelectionAdapter() {
        public void widgetDefaultSelected(SelectionEvent e) {
            TableItem[] items = metadataTable.getSelection();
            if (items.length > 0) {
                editEntry(items[0]);
            }
        }

        public void widgetSelected(SelectionEvent e) {
            checkButtonStatus();
        }
    };

    private FocusListener metadataTableFocusListener = new FocusAdapter() {
        public void focusGained(FocusEvent e) {
            checkButtonStatus();
        }

        public void focusLost(FocusEvent e) {
            checkButtonStatus();
        }
    };

    private KeyListener metadataTableKeyListener = new KeyAdapter() {
        public void keyReleased(KeyEvent e) {
            if ((e.stateMask == SWT.CTRL) && ((e.keyCode == 'c') || (e.keyCode == 'C'))) {
                TableItem[] items = metadataTable.getSelection();
                if (items.length > 0) {
                    copyEntry(items[0]);
                }
            } else if ((e.stateMask == SWT.CTRL) && ((e.keyCode == 'v') || (e.keyCode == 'V'))) {
                pasteEntry();
            }
        }

        public void keyPressed(KeyEvent e) {
            if (e.keyCode == SWT.DEL) {
                TableItem[] items = metadataTable.getSelection();
                if (items.length > 0) {
                    removeEntry(items[0]);
                }
            }
        }
    };

    /**
     * The selection contains urn model elements. Loaded in
     * 
     * @param selection
     * @param defaultSelected
     */
    public MetadataEditorPage(ISelection selection, EObject defaultSelected) {
        super("wizardPage"); //$NON-NLS-1$

        this.setImageDescriptor(ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/perspectiveIcon.gif")); //$NON-NLS-1$

        this.selection = selection;
        this.defaultSelected = defaultSelected;
        this.metadataMap = new HashMap();
        this.allPossibilities = new Vector();
        this.selectedPossibilities = new Vector();
    }

    private void checkButtonStatus() {
        TableItem[] items = metadataTable.getSelection();
        if (items.length > 0) {
            buttonEdit.setEnabled(true);
            buttonRemove.setEnabled(true);
        } else {
            buttonEdit.setEnabled(false);
            buttonRemove.setEnabled(false);
        }
        
        if (possibilities.getItemCount() > 0) {
            buttonAdd.setEnabled(true);
            buttonRemoveAll.setEnabled(true);
        } else {
            buttonAdd.setEnabled(false);
            buttonRemoveAll.setEnabled(false);
            buttonEdit.setEnabled(false);
            buttonRemove.setEnabled(false);
        }
    }

    /**
     * Creates the page.
     */
    public void createControl(Composite parent) {
        container = new Composite(parent, SWT.NULL);
        shell = container.getShell();

        GridLayout layout = new GridLayout();
        container.setLayout(layout);
        layout.numColumns = 5;
        layout.verticalSpacing = 5;

        typeLabel = new Label(container, SWT.NONE);
        typeLabel.setText(Messages.getString("MetadataEditorPage.typeLabel")); //$NON-NLS-1$
        
        typeOfElements = new Combo(container, SWT.DROP_DOWN | SWT.READ_ONLY);
        typeOfElements.addSelectionListener(new SelectionListener() {
            public void widgetSelected(SelectionEvent e) {
                // single click.
                metadataTable.removeAll();
                
                if (typeOfElements.getSelectionIndex() >= 0) {
                    refreshPossibilityLabels();
                    
                    if (possibilities.getItemCount() > 0) {
                        possibilities.select(0);
    
                        EObject o = (EObject) selectedPossibilities.get(0);
                        if (o != defaultSelected) {
                            defaultSelected = o;
                            setupMetadata(o);
    
                            urnelem = (URNmodelElement) defaultSelected;
                        }
                    } else {
                        defaultSelected = null;
                        urnelem = null;
                    }
                }
                
                checkButtonStatus();
            }

            public void widgetDefaultSelected(SelectionEvent e) {
                // double click.
            }
        });

        GridData gd = new GridData(GridData.FILL_HORIZONTAL);
        gd.horizontalSpan = 5;
        gd.widthHint = 150;
        typeOfElements.setLayoutData(gd);
        
        elementLabel = new Label(container, SWT.NONE);
        elementLabel.setText(Messages.getString("MetadataEditorPage.urnelemLabel")); //$NON-NLS-1$
        
        possibilities = new Combo(container, SWT.DROP_DOWN | SWT.READ_ONLY);
        possibilities.setVisibleItemCount(25);
        possibilities.addSelectionListener(new SelectionListener() {
            public void widgetSelected(SelectionEvent e) {
                // single click.
                metadataTable.removeAll();
                
                if (possibilities.getSelectionIndex() >= 0) {
                    EObject o = (EObject) selectedPossibilities.get(possibilities.getSelectionIndex());
                    if (o != defaultSelected) {
                        defaultSelected = o;
                        setupMetadata(o);

                        urnelem = (URNmodelElement) defaultSelected;
                    }
                }

                checkButtonStatus();
            }

            public void widgetDefaultSelected(SelectionEvent e) {
                // double click.
            }

        });

        gd = new GridData(GridData.FILL_HORIZONTAL);
        gd.horizontalSpan = 5;
        gd.widthHint = 250;
        possibilities.setLayoutData(gd);

        // Table to contain metadata entries
        metadataTable = new Table(container, SWT.SINGLE | SWT.BORDER | SWT.FULL_SELECTION);
        metadataTable.setHeaderVisible(true);
        metadataTable.setMenu(createPopUpMenu());

        metadataTable.addSelectionListener(metadataTableSelectionListener);
        metadataTable.addFocusListener(metadataTableFocusListener);
        metadataTable.addKeyListener(metadataTableKeyListener);

        for (int i = 0; i < columnNames.length; i++) {
            TableColumn column = new TableColumn(metadataTable, SWT.NONE);
            column.setText(columnNames[i]);
            column.setWidth(250);
            final int columnIndex = i;

            column.addSelectionListener(new SelectionAdapter() {
                public void widgetSelected(SelectionEvent e) {
                    sort(columnIndex);
                }
            });
        }

        gd = new GridData(GridData.FILL_HORIZONTAL);
        gd.horizontalSpan = 5;
        gd.heightHint = 250;
        metadataTable.setLayoutData(gd);

        // Button to add new metadata.
        buttonAdd = new Button(container, SWT.PUSH);
        buttonAdd.setText(Messages.getString("MetadataEditorPage.button_addNewMetadata")); //$NON-NLS-1$
        buttonAdd.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                newEntry();
            }
        });

        // Button to add new metadata.
        buttonEdit = new Button(container, SWT.PUSH);
        buttonEdit.setText(Messages.getString("MetadataEditorPage.button_editMetadata")); //$NON-NLS-1$
        buttonEdit.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                TableItem[] items = metadataTable.getSelection();
                if (items.length > 0) {
                    editEntry(items[0]);
                }
            }
        });

        // Button to remove the selected metadata.
        buttonRemove = new Button(container, SWT.PUSH);
        buttonRemove.setText(Messages.getString("MetadataEditorPage.button_removeMetadata")); //$NON-NLS-1$
        buttonRemove.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                TableItem[] items = metadataTable.getSelection();
                if (items.length > 0) {
                    removeEntry(items[0]);
                }
            }
        });

        // Button to remove all metadata.
        buttonRemoveAll = new Button(container, SWT.PUSH);
        buttonRemoveAll.setText(Messages.getString("MetadataEditorPage.button_removeAllMetadata")); //$NON-NLS-1$
        buttonRemoveAll.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                removeAllEntries();
            }
        });

        initialize();

        initTypeOfElementsLabels();
        typeOfElements.select(0);
        refreshPossibilityLabels();
        if (possibilities.getItemCount() > 0) {
            possibilities.select(selectedPossibilities.indexOf(defaultSelected));
        }

        setTitle(Messages.getString("MetadataEditorPage.title")); //$NON-NLS-1$

        setControl(container);
        metadataTable.forceFocus();

    }

    /**
     * Creates all items located in the popup menu and associates all the menu items with their appropriate functions.
     * 
     * @return Menu The created popup menu.
     */
    private Menu createPopUpMenu() {
        Menu popUpMenu = new Menu(shell, SWT.POP_UP);

        // New
        MenuItem item = new MenuItem(popUpMenu, SWT.CASCADE);
        item.setText(Messages.getString("MetadataEditorPage.popup_new")); //$NON-NLS-1$
        item.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                newEntry();
            }
        });

        new MenuItem(popUpMenu, SWT.SEPARATOR);

        // Edit
        item = new MenuItem(popUpMenu, SWT.CASCADE);
        item.setText(Messages.getString("MetadataEditorPage.popup_edit")); //$NON-NLS-1$
        item.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                TableItem[] items = metadataTable.getSelection();
                if (items.length > 0) {
                    editEntry(items[0]);
                }
            }
        });

        // Copy
        item = new MenuItem(popUpMenu, SWT.CASCADE);
        item.setText(Messages.getString("MetadataEditorPage.popup_copy")); //$NON-NLS-1$
        item.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                TableItem[] items = metadataTable.getSelection();
                if (items.length > 0) {
                    copyEntry(items[0]);
                }
            }
        });

        // Paste
        item = new MenuItem(popUpMenu, SWT.CASCADE);
        item.setText(Messages.getString("MetadataEditorPage.popup_paste")); //$NON-NLS-1$
        item.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                pasteEntry();
            }
        });

        // Remove
        item = new MenuItem(popUpMenu, SWT.CASCADE);
        item.setText(Messages.getString("MetadataEditorPage.popup_remove")); //$NON-NLS-1$
        item.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                TableItem[] items = metadataTable.getSelection();
                if (items.length > 0) {
                    removeEntry(items[0]);
                }
            }
        });

        /**
         * Adds a listener to handle enabling and disabling some items in the Edit submenu.
         */
        popUpMenu.addMenuListener(new MenuAdapter() {
            public void menuShown(MenuEvent e) {
                Menu menu = (Menu) e.widget;
                MenuItem[] items = menu.getItems();
                int pcount = possibilities.getItemCount();
                int scount = metadataTable.getSelectionCount();
                
                if (pcount > 0) {
                    items[0].setEnabled(true); // new
                    items[2].setEnabled(scount > 0); // edit
                    items[3].setEnabled(scount > 0); // copy
                    items[4].setEnabled(copyBuffer != null); // paste
                    items[5].setEnabled(scount > 0); // remove
                } else {
                    items[0].setEnabled(false); // new
                    items[2].setEnabled(false); // edit
                    items[3].setEnabled(false); // copy
                    items[4].setEnabled(false); // paste
                    items[5].setEnabled(false); // remove
                }
            }
        });

        new MenuItem(popUpMenu, SWT.SEPARATOR);

        return popUpMenu;
    }

    private void newEntry() {
        MetadataEntryDialog dialog = new MetadataEntryDialog(shell);
        dialog.setTitle(Messages.getString("MetadataEntryDialog.title_add")); //$NON-NLS-1$
        dialog.setLabels(columnNames);
        String[] data = dialog.open();

        if (data != null) {
            TableItem item = new TableItem(metadataTable, SWT.NONE);
            item.setText(data);

            metadataChanged();
        }
    }

    private void copyEntry(TableItem item) {
        copyBuffer = new String[metadataTable.getColumnCount()];
        for (int i = 0; i < copyBuffer.length; i++) {
            copyBuffer[i] = item.getText(i);

            // sometimes the menushow event is not working
            MenuItem pasteItem = metadataTable.getMenu().getItem(4);
            pasteItem.setEnabled(copyBuffer != null); // paste
        }
    }

    private void pasteEntry() {
        if (copyBuffer != null) {
            TableItem item = new TableItem(metadataTable, SWT.NONE);
            item.setText(copyBuffer);

            metadataChanged();
        }
    }

    private void editEntry(TableItem item) {
        MetadataEntryDialog dialog = new MetadataEntryDialog(shell);
        dialog.setTitle(Messages.getString("MetadataEntryDialog.title_edit")); //$NON-NLS-1$
        dialog.setLabels(columnNames);
        String[] values = new String[metadataTable.getColumnCount()];

        for (int i = 0; i < values.length; i++) {
            values[i] = item.getText(i);
        }

        dialog.setValues(values);
        values = dialog.open();

        if (values != null) {
            item.setText(values);
            metadataChanged();
        }
    }

    private void removeEntry(TableItem item) {
        item.dispose();
        metadataChanged();
    }

    private void removeAllEntries() {
        metadataTable.removeAll();
        metadataChanged();
    }

    private void sort(int column) {
        if (metadataTable.getItemCount() <= 1) {
            return;
        }

        TableItem[] items = metadataTable.getItems();
        String[][] data = new String[items.length][metadataTable.getColumnCount()];
        for (int i = 0; i < items.length; i++) {
            for (int j = 0; j < metadataTable.getColumnCount(); j++) {
                data[i][j] = items[i].getText(j);
            }
        }

        Arrays.sort(data, new RowComparator(column));

        if (lastSortColumn != column) {
            metadataTable.setSortColumn(metadataTable.getColumn(column));
            metadataTable.setSortDirection(SWT.DOWN);
            for (int i = 0; i < data.length; i++) {
                items[i].setText(data[i]);
            }
            lastSortColumn = column;
        } else {
            // reverse order if the current column is selected again
            metadataTable.setSortDirection(SWT.UP);
            int j = data.length - 1;
            for (int i = 0; i < data.length; i++) {
                items[i].setText(data[j--]);
            }
            lastSortColumn = -1;
        }

    }

    private void initTypeOfElementsLabels() {
        for (int i = 0; i < URNmodelElementType.urnElementTypeNames.length; i++) {
            typeOfElements.add(URNmodelElementType.urnElementTypeNames[i]);
        }
    }

    private void refreshPossibilityLabels() {
        selectedPossibilities.clear();
        for (int i = 0; i < allPossibilities.size(); i++) {
            EObject element = (EObject) allPossibilities.get(i);

            Class choosedType = (Class) URNmodelElementType.urnElementTypes.get(typeOfElements.getItem(typeOfElements.getSelectionIndex()));
            if (choosedType.isInstance(element)) {
                selectedPossibilities.add(element);
            }
        }

        EObject[] pArray = (EObject[]) selectedPossibilities.toArray(new EObject[0]);
        Arrays.sort(pArray, new EObjectComparator());

        possibilities.removeAll();
        selectedPossibilities.clear();
        for (int i = 0; i < pArray.length; i++) {
            URNmodelElement curUrnelem = (URNmodelElement) pArray[i];
            String name = URNNamingHelper.getName(curUrnelem) + " (" + curUrnelem.getId() + ")"; //$NON-NLS-1$ //$NON-NLS-2$

            selectedPossibilities.add(curUrnelem);
            possibilities.add(name);
        }
    }

    /**
     * Tests if the current workbench selection is a suitable container to use.
     */
    private void initialize() {
        if (selection != null && selection.isEmpty() == false && selection instanceof IStructuredSelection) {
            IStructuredSelection ssel = (IStructuredSelection) selection;
            initPossibilities(ssel);

            if (defaultSelected == null) {
                defaultSelected = (EObject) ssel.getFirstElement();
            }

            setupMetadata(defaultSelected);

            if (defaultSelected instanceof URNmodelElement) {
                urnelem = (URNmodelElement) defaultSelected;
            }

            EObject o;
            if (urnelem != null) {
                o = urnelem.eContainer();

                while (o != null) {
                    if (o instanceof URNspec) {
                        urn = (URNspec) o;
                    }

                    o = o.eContainer();
                }
            }
        }
    }

    private void setupMetadata(Object obj) {
        // Remove related listeners on metadataTable in here

        if (obj != null && obj instanceof URNmodelElement) {
            urnelem = (URNmodelElement) obj;

            // put urnelem into metadata table
            metadataTable.removeAll();

            Metadata[] metadataArray;
            if (metadataMap.get(urnelem) != null) {
                metadataArray = (Metadata[]) metadataMap.get(urnelem);
            } else {
                EList metadataList = urnelem.getMetadata();
                metadataArray = (Metadata[]) metadataList.toArray(new Metadata[0]);
            }

            String[][] tableInfo = new String[metadataArray.length][metadataTable.getColumnCount()];
            int rowIndex = 0;
            for (int i = 0; i < metadataArray.length; i++) {
                String[] line = decodeLine(metadataArray[i]);
                if (line != null) {
                    tableInfo[rowIndex++] = line;
                }
            }

            if (rowIndex != metadataArray.length) {
                String[][] result = new String[rowIndex][metadataTable.getColumnCount()];
                System.arraycopy(tableInfo, 0, result, 0, rowIndex);
                tableInfo = result;
            }

            Arrays.sort(tableInfo, new RowComparator(0));

            for (int i = 0; i < tableInfo.length; i++) {
                TableItem item = new TableItem(metadataTable, SWT.NONE);
                item.setText(tableInfo[i]);
            }
        } else {
            metadataTable.removeAll();
        }

        // Restore listeners on metadataTable in here
    }

    /**
     * Converts a metadata object to a String array representing a table entry.
     */
    private String[] decodeLine(Metadata metadata) {
        String[] parsedLine = null;

        if (metadata != null) {
            parsedLine = new String[metadataTable.getColumnCount()];

            // parse Name
            parsedLine[0] = metadata.getName();
            // parse Value
            parsedLine[1] = metadata.getValue();
        }

        return parsedLine;
    }

    private void initPossibilities(IStructuredSelection ssel) {
        boolean found = false;

        EObject[] eobjs = new EObject[ssel.size()];
        Iterator iter = ssel.iterator();
        int index = 0;
        while (iter.hasNext()) {
            EObject element = (EObject) iter.next();
            eobjs[index++] = element;
            
            if (element == defaultSelected) {
                found = true;
            }
        }

        for (int i = 0; i < eobjs.length; i++) {
            allPossibilities.add(eobjs[i]);
        }

        // ignore it if it wasn't in the list.
        if (!found) {
            defaultSelected = null;
        }
    }

    /**
     * Add the changed urn model element into metadataMap
     */
    private void metadataChanged() {
        Metadata[] metadataFromTable = getMetadataFromTable();
        metadataMap.put(urnelem, metadataFromTable);
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

        refreshPossibilityLabels();
        possibilities.setEnabled(isPageComplete());
    }

    /**
     * Returns the urn model element for which the pseudo-code is being edited.
     * 
     * @return the urn model element being edited.
     */
    public URNmodelElement getURNmodelElement() {
        return urnelem;
    }

    /**
     * The metadata from the metadata table.
     * 
     * @return the metadata
     */
    public Metadata[] getMetadataFromTable() {
        TableItem[] items = metadataTable.getItems();
        Metadata[] metadataArray = new Metadata[items.length];

        for (int i = 0; i < items.length; i++) {
            Metadata tempMetadata = (Metadata) ModelCreationFactory.getNewObject(urn, Metadata.class);

            tempMetadata.setName(items[i].getText(0));
            tempMetadata.setValue(items[i].getText(1));

            metadataArray[i] = tempMetadata;
        }

        return metadataArray;
    }

    /**
     * Metadata for all objects that were passed. Assumed to be always valid.
     * 
     * @return a hashmap of eobject->list of metadata
     */
    public HashMap getAllMetadata() {
        return metadataMap;
    }

}
