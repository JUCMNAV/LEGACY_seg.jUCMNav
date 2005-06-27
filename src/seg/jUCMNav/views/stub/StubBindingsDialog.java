package seg.jUCMNav.views.stub;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.forms.widgets.TableWrapLayout;
import org.eclipse.ui.internal.WorkbenchImages;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.create.AddInBindingCommand;
import seg.jUCMNav.model.commands.create.AddOutBindingCommand;
import seg.jUCMNav.model.commands.create.AddPluginCommand;
import seg.jUCMNav.model.commands.delete.DeleteInBindingCommand;
import seg.jUCMNav.model.commands.delete.DeleteOutBindingCommand;
import seg.jUCMNav.model.commands.delete.DeletePluginCommand;
import seg.jUCMNav.model.commands.transformations.ReplacePluginCommand;
import ucm.UcmPackage;
import ucm.map.EndPoint;
import ucm.map.InBinding;
import ucm.map.Map;
import ucm.map.MapPackage;
import ucm.map.NodeConnection;
import ucm.map.OutBinding;
import ucm.map.PathNode;
import ucm.map.PluginBinding;
import ucm.map.StartPoint;
import ucm.map.Stub;
import urn.URNspec;

/**
 * Created 2005-06-01
 * 
 * @author Etienne Tremblay
 */
public class StubBindingsDialog extends Dialog implements Adapter {
    //	 The toolkit for eclipse forms
    private FormToolkit toolkit;
    // The main form where all the controls will be
    private ScrolledForm form;

    // The description label.
    private Label descrip;

    // The stub we are representing here
    private Stub stub;
    // The associated URNspec of this stub
    private URNspec urnSpec;

    // Used by for EMF notification
    private Notifier target;

    // Sections in the form.
    private Section mapSection; // Select the plugin(s) of the stub.
    private Section pluginListSection; // List of all the PluginBindings with all there attributes.

    // The tree listing the PluginBindings.
    private Tree treeBindings;

    private Table tabMapIns; // The table for making in bindings with maps
    private TableColumn mapInsColumn; // It's first column (so that we can make it as wide as the table)
    private Table tabStubIns; // The table for making in bindings with stubs
    private TableColumn stubInsColumn; // It's first column (so that we can make it as wide as the table)

    private Table tabMapOuts; // The table for making out bindings with maps
    private TableColumn mapOutsColumn; // It's first column (so that we can make it as wide as the table)
    private Table tabStubOuts; // The table for making out bindings with stubs
    private TableColumn stubOutsColumn; // It's first column (so that we can make it as wide as the table)

    // The button for doing in bindings.
    private Button btInBind;
    //	 The button for doing out bindings.
    private Button btOutBind;

    // The editor from wich this dialog was opened.
    private CommandStack cmdStack;

    // The list of images to dispose at the end.
    private ArrayList images = new ArrayList();

    // The list containing all the maps you can plugin to.
    private Table tabMapList;
    private TableColumn tabMapListColumn;

    // Toolbar icons for undo/redo/delete
    private ToolItem btRedo;
    private ToolItem btUndo;
    private ToolItem btDelete;

    // The label containing the name of the selectedPlugin right now
    private Label selectedPluginLabel;
    // The composite containing the table to add in and out bindings.
    private Composite addPluginClient;

    // The number of executed command to date. Is decreased with undo
    private int executedCount = 0;
    // The total number of executed commands to date. Not decreased with undo. Reseted to executedCount when execute is called.
    private int totalExecuted;

    // The currently selected item in the tree view.
    private Object selectedItem;

    public StubBindingsDialog(Shell parentShell, CommandStack cmdStack) {
        super(parentShell);
        this.cmdStack = cmdStack;
        setShellStyle(SWT.SHELL_TRIM | SWT.APPLICATION_MODAL);
    }

    protected Control createDialogArea(Composite parent) {
        Composite area = (Composite) super.createDialogArea(parent);
        area.setBackground(new Color(null, 255, 255, 255));
        GridLayout l = (GridLayout) area.getLayout();
        l.marginWidth = 0;
        l.marginHeight = 0;
        GridData d = new GridData(GridData.FILL_BOTH);
        d.grabExcessHorizontalSpace = true;
        d.grabExcessVerticalSpace = true;
        area.setLayoutData(d);

        toolkit = new FormToolkit(area.getDisplay());

        form = toolkit.createScrolledForm(area);
        d = new GridData(GridData.FILL_BOTH);
        d.grabExcessHorizontalSpace = true;
        d.grabExcessVerticalSpace = true;
        form.setLayoutData(d);
        form.setText(Messages.getString("StubBindingsDialog.stubBindings")); //$NON-NLS-1$
        TableWrapLayout layout = new TableWrapLayout();
        layout.numColumns = 2;
        form.getBody().setLayout(layout);
        TableWrapData td = new TableWrapData();

        final ExpandableComposite ec = toolkit.createExpandableComposite(form.getBody(), ExpandableComposite.TWISTIE | ExpandableComposite.EXPANDED);
        ec.setText(Messages.getString("StubBindingsDialog.stubDesc")); //$NON-NLS-1$
        descrip = toolkit.createLabel(ec, "", SWT.WRAP); //$NON-NLS-1$
        ec.setClient(descrip);
        td = new TableWrapData();
        td.colspan = 2;
        ec.setLayoutData(td);

        //		 Connect map section
        mapSection = toolkit.createSection(form.getBody(), Section.TWISTIE | Section.TITLE_BAR);
        mapSection.setText(Messages.getString("StubBindingsDialog.selectPluginMaps")); //$NON-NLS-1$
        td = new TableWrapData(TableWrapData.FILL);
        td.colspan = 1;
        td.grabVertical = true;
        mapSection.setLayoutData(td);
        td.align = TableWrapData.FILL;
        toolkit.createCompositeSeparator(mapSection);

        Composite mapClient = toolkit.createComposite(mapSection);
        GridLayout grid = new GridLayout();
        grid.numColumns = 1;
        mapClient.setLayout(grid);

        tabMapList = toolkit.createTable(mapClient, SWT.SINGLE | SWT.FULL_SELECTION | SWT.CHECK);
        tabMapList.setLinesVisible(true);
        tabMapList.setHeaderVisible(true);
        GridData g = new GridData(GridData.FILL_BOTH);
        g.grabExcessHorizontalSpace = true;
        g.grabExcessVerticalSpace = true;
        tabMapList.setLayoutData(g);
        tabMapListColumn = new TableColumn(tabMapList, SWT.NONE);
        tabMapListColumn.setWidth(150);
        tabMapListColumn.setText(Messages.getString("StubBindingsDialog.maps")); //$NON-NLS-1$

        tabMapList.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                if (e.detail == SWT.CHECK)
                    handlePluginChecked((TableItem) e.item);
            }
        });

        mapSection.setClient(mapClient);

        // Plugin List section
        pluginListSection = toolkit.createSection(form.getBody(), Section.TWISTIE | Section.TITLE_BAR);
        td = new TableWrapData(TableWrapData.FILL_GRAB);
        td.colspan = 1;
        td.grabHorizontal = true;
        td.grabVertical = true;
        pluginListSection.setLayoutData(td);
        pluginListSection.setText(Messages.getString("StubBindingsDialog.pluginList")); //$NON-NLS-1$
        toolkit.createCompositeSeparator(pluginListSection);

        Composite sectionClient = toolkit.createComposite(pluginListSection);
        td = new TableWrapData(TableWrapData.FILL_GRAB);
        td.colspan = 1;
        td.grabHorizontal = true;
        td.grabVertical = true;
        sectionClient.setLayoutData(td);

        grid = new GridLayout();
        grid.numColumns = 2;
        grid.makeColumnsEqualWidth = false;
        sectionClient.setLayout(grid);

        Image image = (ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Binding16.gif")).createImage(); //$NON-NLS-1$
        images.add(image);

        Composite lb1 = toolkit.createComposite(sectionClient);
        grid = new GridLayout();
        grid.numColumns = 3;
        grid.makeColumnsEqualWidth = false;
        lb1.setLayout(grid);
        GridData t = new GridData(GridData.FILL_HORIZONTAL);
        t.grabExcessHorizontalSpace = true;
        t.grabExcessVerticalSpace = false;
        lb1.setLayoutData(t);
        Label lb = toolkit.createLabel(lb1, ""); //$NON-NLS-1$
        lb.setImage(image);
        t = new GridData(GridData.FILL_HORIZONTAL);
        t.grabExcessHorizontalSpace = false;
        lb.setLayoutData(t);
        lb = toolkit.createLabel(lb1, Messages.getString("StubBindingsDialog.pluginTree")); //$NON-NLS-1$
        lb.setFont(new Font(null, new FontData("", 8, SWT.BOLD))); //$NON-NLS-1$
        t = new GridData(GridData.FILL_HORIZONTAL);
        t.grabExcessHorizontalSpace = false;
        lb.setLayoutData(t);

        ToolBar toolBar = new ToolBar(lb1, SWT.FLAT | SWT.RIGHT);
        toolBar.setBackground(new Color(null, 255, 255, 255));

        d = new GridData(GridData.FILL_BOTH);
        d.grabExcessHorizontalSpace = true;
        d.grabExcessVerticalSpace = true;
        d.horizontalAlignment = SWT.RIGHT;
        toolBar.setLayoutData(d);

        btDelete = new ToolItem(toolBar, SWT.PUSH);
        btDelete.setEnabled(false);
        btDelete.setToolTipText(Messages.getString("StubBindingsDialog.deleteBinding")); //$NON-NLS-1$
        image = WorkbenchImages.getImageDescriptor(ISharedImages.IMG_TOOL_DELETE).createImage();
        images.add(image);
        btDelete.setImage(image);
        btDelete.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                delete();
            }
        });

        btUndo = new ToolItem(toolBar, SWT.PUSH);
        btUndo.setToolTipText(Messages.getString("StubBindingsDialog.undoPreviousAction")); //$NON-NLS-1$
        image = WorkbenchImages.getImageDescriptor(ISharedImages.IMG_TOOL_UNDO).createImage();
        images.add(image);
        btUndo.setImage(image);
        btUndo.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                undo();
            }
        });

        btUndo.setEnabled(false);

        btRedo = new ToolItem(toolBar, SWT.PUSH);
        btRedo.setToolTipText(Messages.getString("StubBindingsDialog.redoPreviousAction")); //$NON-NLS-1$
        image = WorkbenchImages.getImageDescriptor(ISharedImages.IMG_TOOL_REDO).createImage();
        images.add(image);
        btRedo.setImage(image);
        btRedo.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                redo();
            }
        });

        btRedo.setEnabled(false);

        image = (ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Binding16.gif")).createImage(); //$NON-NLS-1$
        images.add(image);

        Composite lb2 = toolkit.createComposite(sectionClient);
        grid = new GridLayout();
        grid.numColumns = 3;
        grid.makeColumnsEqualWidth = false;
        lb2.setLayout(grid);
        t = new GridData(GridData.FILL_HORIZONTAL);
        t.grabExcessHorizontalSpace = true;
        t.grabExcessVerticalSpace = false;
        lb2.setLayoutData(t);
        lb = toolkit.createLabel(lb2, ""); //$NON-NLS-1$
        lb.setImage(image);
        t = new GridData(GridData.FILL_HORIZONTAL);
        t.grabExcessHorizontalSpace = false;
        lb.setLayoutData(t);
        lb = toolkit.createLabel(lb2, Messages.getString("StubBindingsDialog.addBindingsFor")); //$NON-NLS-1$
        lb.setFont(new Font(null, new FontData("", 8, SWT.BOLD))); //$NON-NLS-1$
        t = new GridData(GridData.FILL_HORIZONTAL);
        t.grabExcessHorizontalSpace = false;
        lb.setLayoutData(t);
        selectedPluginLabel = toolkit.createLabel(lb2, Messages.getString("StubBindingsDialog.noPluginSelected")); //$NON-NLS-1$
        t = new GridData(GridData.FILL_HORIZONTAL);
        t.grabExcessHorizontalSpace = true;
        selectedPluginLabel.setLayoutData(t);

        // The tree listing the plugins.
        treeBindings = toolkit.createTree(sectionClient, SWT.SINGLE | SWT.BORDER);
        t = new GridData(GridData.FILL_BOTH);
        t.grabExcessHorizontalSpace = true;
        t.grabExcessVerticalSpace = true;
        t.heightHint = 160;
        t.widthHint = 200;
        treeBindings.setLayoutData(t);
        treeBindings.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                handleTreeBindingsSelected((TreeItem) e.item);
            }
        });

        pluginListSection.setClient(sectionClient);

        addPluginClient = toolkit.createComposite(sectionClient, SWT.BORDER);
        addPluginClient.setVisible(false);
        grid = new GridLayout();
        grid.numColumns = 3;
        grid.makeColumnsEqualWidth = false;
        addPluginClient.setLayout(grid);
        GridData f = new GridData(GridData.FILL_BOTH);
        f.grabExcessHorizontalSpace = true;
        f.grabExcessVerticalSpace = true;
        addPluginClient.setLayoutData(f);

        // Stub composite for creating new bindings.
        Composite stubComp = toolkit.createComposite(addPluginClient);
        grid = new GridLayout();
        grid.numColumns = 1;
        stubComp.setLayout(grid);
        f = new GridData(GridData.FILL_BOTH);
        f.grabExcessHorizontalSpace = true;
        f.grabExcessVerticalSpace = true;
        stubComp.setLayoutData(f);

        lb = toolkit.createLabel(stubComp, Messages.getString("StubBindingsDialog.stub")); //$NON-NLS-1$

        tabStubIns = toolkit.createTable(stubComp, SWT.SINGLE | SWT.FULL_SELECTION);
        tabStubIns.setLinesVisible(true);
        tabStubIns.setHeaderVisible(true);
        tabStubIns.addMouseListener(new MouseAdapter() {
            public void mouseUp(MouseEvent e) {
                if (tabStubIns.getSelectionCount() >= 1 && tabMapIns.getSelectionCount() >= 1 && stub.getBindings().size() > 0)
                    btInBind.setEnabled(true);
                else
                    btInBind.setEnabled(false);
            }
        });
        g = new GridData(GridData.FILL_BOTH);
        g.grabExcessHorizontalSpace = true;
        g.grabExcessVerticalSpace = true;
        g.heightHint = 75;
        tabStubIns.setLayoutData(g);
        stubInsColumn = new TableColumn(tabStubIns, SWT.NONE);
        stubInsColumn.setWidth(50);
        stubInsColumn.setText(Messages.getString("StubBindingsDialog.in")); //$NON-NLS-1$

        Composite buttonComp = toolkit.createComposite(addPluginClient);
        grid = new GridLayout();
        grid.numColumns = 1;
        grid.makeColumnsEqualWidth = true;
        buttonComp.setLayout(grid);
        g = new GridData(GridData.FILL_BOTH);
        g.grabExcessHorizontalSpace = true;
        g.grabExcessVerticalSpace = true;
        buttonComp.setLayoutData(g);

        btInBind = toolkit.createButton(buttonComp, "<->", SWT.PUSH | SWT.FLAT); //$NON-NLS-1$
        btInBind.setEnabled(false);
        g = new GridData();
        g.grabExcessHorizontalSpace = true;
        g.grabExcessVerticalSpace = true;
        g.horizontalAlignment = GridData.CENTER;
        g.verticalAlignment = GridData.CENTER;
        btInBind.setLayoutData(g);
        btInBind.addMouseListener(new MouseAdapter() {
            public void mouseUp(MouseEvent e) {
                handleInBindClick();
            }
        });

        Composite mapComp = toolkit.createComposite(addPluginClient);
        grid = new GridLayout();
        grid.numColumns = 1;
        mapComp.setLayout(grid);
        g = new GridData(GridData.FILL_BOTH);
        g.grabExcessHorizontalSpace = true;
        g.grabExcessVerticalSpace = true;
        mapComp.setLayoutData(g);

        lb = toolkit.createLabel(mapComp, Messages.getString("StubBindingsDialog.map")); //$NON-NLS-1$

        tabMapIns = toolkit.createTable(mapComp, SWT.SINGLE | SWT.FULL_SELECTION);
        tabMapIns.setLinesVisible(true);
        tabMapIns.setHeaderVisible(true);
        tabMapIns.addMouseListener(new MouseAdapter() {
            public void mouseUp(MouseEvent e) {
                if (tabStubIns.getSelectionCount() >= 1 && tabMapIns.getSelectionCount() >= 1 && stub.getBindings().size() > 0)
                    btInBind.setEnabled(true);
                else
                    btInBind.setEnabled(false);
            }
        });
        g = new GridData(GridData.FILL_BOTH);
        g.grabExcessHorizontalSpace = true;
        g.grabExcessVerticalSpace = true;
        g.heightHint = 75;
        tabMapIns.setLayoutData(g);
        mapInsColumn = new TableColumn(tabMapIns, SWT.NONE);
        mapInsColumn.setWidth(50);
        mapInsColumn.setText(Messages.getString("StubBindingsDialog.inMap")); //$NON-NLS-1$

        // Out bindings controls
        stubComp = toolkit.createComposite(addPluginClient);
        grid = new GridLayout();
        grid.numColumns = 1;
        stubComp.setLayout(grid);
        g = new GridData(GridData.FILL_BOTH);
        g.grabExcessHorizontalSpace = true;
        g.grabExcessVerticalSpace = true;
        stubComp.setLayoutData(g);

        lb = toolkit.createLabel(stubComp, Messages.getString("StubBindingsDialog.stub")); //$NON-NLS-1$

        tabStubOuts = toolkit.createTable(stubComp, SWT.SINGLE | SWT.FULL_SELECTION);
        tabStubOuts.setLinesVisible(true);
        tabStubOuts.setHeaderVisible(true);
        tabStubOuts.addMouseListener(new MouseAdapter() {
            public void mouseUp(MouseEvent e) {
                if (tabStubOuts.getSelectionCount() >= 1 && tabMapOuts.getSelectionCount() >= 1 && stub.getBindings().size() > 0)
                    btOutBind.setEnabled(true);
                else
                    btOutBind.setEnabled(false);
            }
        });
        g = new GridData(GridData.FILL_BOTH);
        g.grabExcessHorizontalSpace = true;
        g.grabExcessVerticalSpace = true;
        g.heightHint = 75;
        tabStubOuts.setLayoutData(g);
        stubOutsColumn = new TableColumn(tabStubOuts, SWT.NONE);
        stubOutsColumn.setWidth(50);
        stubOutsColumn.setText(Messages.getString("StubBindingsDialog.out")); //$NON-NLS-1$

        buttonComp = toolkit.createComposite(addPluginClient);
        grid = new GridLayout();
        grid.numColumns = 1;
        grid.makeColumnsEqualWidth = true;
        buttonComp.setLayout(grid);
        g = new GridData(GridData.FILL_BOTH);
        g.grabExcessHorizontalSpace = true;
        g.grabExcessVerticalSpace = true;
        buttonComp.setLayoutData(g);

        btOutBind = toolkit.createButton(buttonComp, "<->", SWT.PUSH | SWT.FLAT); //$NON-NLS-1$
        btOutBind.setEnabled(false);
        g = new GridData();
        g.grabExcessHorizontalSpace = true;
        g.grabExcessVerticalSpace = true;
        g.horizontalAlignment = GridData.CENTER;
        g.verticalAlignment = GridData.CENTER;
        btOutBind.setLayoutData(g);
        btOutBind.addMouseListener(new MouseAdapter() {
            public void mouseUp(MouseEvent e) {
                handleOutBindClick();
            }
        });

        mapComp = toolkit.createComposite(addPluginClient);
        grid = new GridLayout();
        grid.numColumns = 1;
        mapComp.setLayout(grid);
        g = new GridData(GridData.FILL_BOTH);
        g.grabExcessHorizontalSpace = true;
        g.grabExcessVerticalSpace = true;
        mapComp.setLayoutData(g);

        lb = toolkit.createLabel(mapComp, Messages.getString("StubBindingsDialog.map")); //$NON-NLS-1$

        tabMapOuts = toolkit.createTable(mapComp, SWT.SINGLE | SWT.FULL_SELECTION);
        tabMapOuts.setLinesVisible(true);
        tabMapOuts.setHeaderVisible(true);
        tabMapOuts.addMouseListener(new MouseAdapter() {
            public void mouseUp(MouseEvent e) {
                if (tabStubOuts.getSelectionCount() >= 1 && tabMapOuts.getSelectionCount() >= 1 && stub.getBindings().size() > 0)
                    btOutBind.setEnabled(true);
                else
                    btOutBind.setEnabled(false);
            }
        });
        g = new GridData(GridData.FILL_BOTH);
        g.grabExcessHorizontalSpace = true;
        g.grabExcessVerticalSpace = true;
        g.heightHint = 75;
        tabMapOuts.setLayoutData(g);
        mapOutsColumn = new TableColumn(tabMapOuts, SWT.NONE);
        mapOutsColumn.setWidth(50);
        mapOutsColumn.setText(Messages.getString("StubBindingsDialog.outMap")); //$NON-NLS-1$

        mapSection.setExpanded(true);
        pluginListSection.setExpanded(true);

        return area;
    }

    /**
     * Delete the selected item in the tree view. This will delete it in the model too.
     */
    protected void delete() {
        if (selectedItem instanceof PluginBinding) {
            DeletePluginCommand delete = new DeletePluginCommand((PluginBinding) selectedItem);
            execute(delete);

            btDelete.setEnabled(false);

            // Refresh the add bindings view with nothing since the plugin was deleted.
            setSelectedPluginView(null);
        } else if (selectedItem instanceof InBinding) {
            InBinding in = (InBinding) selectedItem;
            DeleteInBindingCommand delete = new DeleteInBindingCommand((InBinding) selectedItem);
            execute(delete);

            btDelete.setEnabled(false);

            // Refresh the add bindings view with nothing since the plugin was deleted.
            setSelectedPluginView(in.getBinding());
        } else if (selectedItem instanceof OutBinding) {
            OutBinding out = (OutBinding) selectedItem;
            DeleteOutBindingCommand delete = new DeleteOutBindingCommand((OutBinding) selectedItem);
            execute(delete);

            btDelete.setEnabled(false);

            // Refresh the add bindings view with nothing since the plugin was deleted.
            setSelectedPluginView(out.getBinding());
        }
    }

    /**
     * Handle when the user checks an item in the map list table. Depending if the stub is dynamic or not the function will add a new plugin to the stub or
     * replace the last one with the new map selected. The add bindings view will get updated with the new selected plugin.
     * 
     * @param item
     *            The checked/unchecked item in the map list table.
     */
    protected void handlePluginChecked(TableItem item) {
        Map map = (Map) item.getData(); // Get the selected map
        PluginBinding selectedPlugin;

        // If the item is checked
        if (item.getChecked()) {
            // If the stub is dynamic
            if (stub.isDynamic()) {
                // Add a plugin to the dynamic stub
                AddPluginCommand plugin = new AddPluginCommand(stub, map);
                execute(plugin);

                // Refresh the add bindings view with the correct plugin (the last one added).
                selectedPlugin = (PluginBinding) stub.getBindings().get(stub.getBindings().size() - 1);
                setSelectedPluginView(selectedPlugin);
            } else { // The stub is static
                // If this stub have one or more plugin
                if (stub.getBindings().size() >= 1) {
                    if (((PluginBinding) stub.getBindings().get(0)).getPlugin() != map) {
                        ReplacePluginCommand plugin = new ReplacePluginCommand((PluginBinding) stub.getBindings().get(0), map);
                        execute(plugin);

                        // Refresh the add bindings view with the correct plugin
                        selectedPlugin = (PluginBinding) stub.getBindings().get(0);
                        setSelectedPluginView(selectedPlugin);
                    }
                } else if (stub.getBindings().size() == 0) {
                    // Here the static stub don't have any plugin yet, so add a new one.
                    AddPluginCommand plugin = new AddPluginCommand(stub, map);
                    execute(plugin);

                    //	Refresh the add bindings view with the correct plugin
                    selectedPlugin = (PluginBinding) stub.getBindings().get(0);
                    setSelectedPluginView(selectedPlugin);
                }
            }
        } else { // The item is unchecked.
            PluginBinding plugin = null;
            boolean finished = false;
            // Loop through all PluginBindings to find the one wich plug to the selected unchecked map.
            for (Iterator i = stub.getBindings().iterator(); i.hasNext() && !finished;) {
                PluginBinding plug = (PluginBinding) i.next();
                if (plug.getPlugin() == map) {
                    finished = true;
                    plugin = plug;
                }
            }
            // Delete this plugin.
            DeletePluginCommand delete = new DeletePluginCommand(plugin);
            execute(delete);

            // Refresh the add bindings view with nothing since the plugin was deleted.
            setSelectedPluginView(null);
        }
    }

    /**
     * Handle the event when the user click on a tree item. This will update the view for adding bindings and will update the delete button.
     * 
     * @param source
     *            The tree item where the event occured.
     */
    protected void handleTreeBindingsSelected(TreeItem source) {
        Object data = source.getData();
        if (data != null) {
            // Depending on the data object of the tree item
            if (data instanceof PluginBinding) {
                selectedItem = data;
                // Refresh the add bindings view with the correct plugin.
                setSelectedPluginView((PluginBinding) data);
            } else if (data instanceof InBinding || data instanceof OutBinding) {
                selectedItem = data;
                // show the binding
                setSelectedPluginView((PluginBinding) source.getParentItem().getParentItem().getData());
            } else {
                setSelectedPluginView(null); // Erase the binding view
                btDelete.setEnabled(false);
                return;
            }
            btDelete.setEnabled(true);
        } else {
            if (source.getParentItem() != null) {
                setSelectedPluginView((PluginBinding) source.getParentItem().getData());
            }
            btDelete.setEnabled(false);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.dialogs.Dialog#cancelPressed()
     */
    protected void cancelPressed() {
        for (int i = executedCount; i > 0; i--) {
            getCommandStack().undo();
        }

        dispose();

        super.cancelPressed();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.dialogs.Dialog#okPressed()
     */
    protected void okPressed() {
        dispose();
        super.okPressed();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.dialogs.Dialog#createButtonsForButtonBar(org.eclipse.swt.widgets.Composite)
     */
    protected void createButtonsForButtonBar(Composite parent) {
        parent.getParent().setBackground(new Color(null, 255, 255, 255));
        parent.setBackground(new Color(null, 255, 255, 255));
        createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
        createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.window.Window#getInitialSize()
     */
    protected Point getInitialSize() {
        return new Point(900, 500);
    }

    /**
     * Take a command and execute it in the command stack of the editor.
     * 
     * @param command
     *            The command we want to execute.
     */
    protected void execute(Command command) {
        if (command == null || !command.canExecute())
            return;
        getCommandStack().execute(command); // Execute the command

        // Reset totalExecuted to executedCount
        executedCount++;
        totalExecuted = executedCount;

        // Only undo should be enabled.
        btUndo.setEnabled(true);
        btRedo.setEnabled(false);

        // Refresh
        refreshMapList();
        refreshBindingsTree();
    }

    protected void undo() {
        getCommandStack().undo(); // Undo the last command

        executedCount--;
        if (executedCount == 0) // If we can't undo anything, then disable undo.
            btUndo.setEnabled(false);

        // We should be able to redo.
        btRedo.setEnabled(true);

        refreshMapList();
        refreshBindingsTree();

        setSelectedPluginView(null); // Since we don't know if the currently selected plugin is still there, update it with null
    }

    protected void redo() {
        getCommandStack().redo(); // Redo the last command

        executedCount++;
        if (executedCount == totalExecuted) // If we arrived to the maximum of our redo stack, disable the button.
            btRedo.setEnabled(false);

        // We should always be able to undo.
        btUndo.setEnabled(true);

        refreshMapList();
        refreshBindingsTree();

        setSelectedPluginView(null); // Since we don't know if the currently selected plugin is still there, update it with null
    }

    /**
     * @return The command stack of the editor.
     */
    private CommandStack getCommandStack() {
        return cmdStack;
    }

    /**
     * Handle the click on the button to create a new OutBinding.
     */
    protected void handleOutBindClick() {
        if (tabStubOuts.getSelectionCount() >= 1 && tabMapOuts.getSelectionCount() >= 1 && stub.getBindings().size() > 0) {
            PluginBinding plug = null;
            //			 Check that the selected Stub is not dynamic
            if (!stub.isDynamic()) {
                // Set the binding of the OutBinding to to first one in the list of the plugin.
                plug = (PluginBinding) stub.getBindings().get(0);
            } else {
                plug = (PluginBinding) selectedPluginLabel.getData();
            }

            // Get the selected EndPoint and NodeConnection in the map and stub out table.
            EndPoint end = (EndPoint) outMapList.get(tabMapOuts.getSelectionIndex());
            NodeConnection con = (NodeConnection) outStubList.get(tabStubOuts.getSelectionIndex());

            AddOutBindingCommand out = new AddOutBindingCommand(plug, end, con);
            execute(out);
            setSelectedPluginView(plug);

            btOutBind.setEnabled(false);
        }
    }

    /**
     * Handle the click on the button to create a new InBinding.
     */
    protected void handleInBindClick() {
        // Both list have something selected. And the stub has to have at least one plugin.
        if (tabStubIns.getSelectionCount() >= 1 && tabMapIns.getSelectionCount() >= 1 && stub.getBindings().size() > 0) {
            PluginBinding plug = null;
            //			 Check that the selected Stub is not dynamic
            if (!stub.isDynamic()) {
                // Set the binding of the OutBinding to to first one in the list of the plugin.
                plug = (PluginBinding) stub.getBindings().get(0);
            } else {
                plug = (PluginBinding) selectedPluginLabel.getData();
            }
            // Get the selected StartPoint and NodeConnection in the map and stub in table.
            StartPoint start = (StartPoint) inMapList.get(tabMapIns.getSelectionIndex());
            NodeConnection con = (NodeConnection) inStubList.get(tabStubIns.getSelectionIndex());

            AddInBindingCommand in = new AddInBindingCommand(plug, start, con);
            execute(in);
            setSelectedPluginView(plug);

            btInBind.setEnabled(false);
        }
    }

    /**
     * This method updates the width of columns of tables to make them as wide as possible.
     */
    protected void updateColumnWidth() {
        mapInsColumn.setWidth(tabMapIns.getSize().x - 2);
        stubInsColumn.setWidth(tabStubIns.getSize().x - 2);
        mapOutsColumn.setWidth(tabMapOuts.getSize().x - 2);
        stubOutsColumn.setWidth(tabStubOuts.getSize().x - 2);
        tabMapListColumn.setWidth(tabMapList.getSize().x - 2);
    }

    /**
     * This method is called when the selected Stub changes.
     * 
     * @param stub
     *            The stub beeing selected. null if we don't have any Stub selected.
     */
    public void setStub(Stub stub) {
        if (stub != this.stub) {
            if (stub != null) {
                if (this.stub != null)
                    this.stub.eAdapters().remove(this);
                stub.eAdapters().add(this);

                this.stub = stub;
                urnSpec = stub.getPathGraph().getMap().getUcmspec().getUrnspec();

                refreshDescription();
                refreshBindingsTree();
                refreshMapList();

            } else {
                if (this.stub != null)
                    this.stub.eAdapters().remove(this);
                this.stub = null;
            }
            form.reflow(true);
            updateColumnWidth();
        }
    }

    /**
     * Open the window with a predefined stub. This is the official way to open this dialog.
     * 
     * @param newStub
     *            The stub to initialize the dialog with.
     * @return the return code
     */
    public int open(Stub newStub) {
        create();
        getShell().setActive();
        getShell().setText(Messages.getString("StubBindingsDialog.stubBindings")); //$NON-NLS-1$
        Image image = (ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Binding16.gif")).createImage(); //$NON-NLS-1$
        images.add(image);
        getShell().setImage(image);
        getShell().addShellListener(new ShellAdapter() {
            public void shellClosed(ShellEvent e) {
                dispose();
            }
        });
        setStub(newStub);
        updateColumnWidth();
        return super.open();
    }

    /**
     * Called before closing this dialog. Dispose all images and remove this dialog as a listener of the stub.
     */
    protected void dispose() {
        for (Iterator i = images.iterator(); i.hasNext();) {
            Image image = (Image) i.next();
            image.dispose();
        }
        stub.eAdapters().remove(this);
    }

    /**
     * Refresh the description of the selected Stub.
     */
    private void refreshDescription() {
        if (stub.getDescription() != null)
            descrip.setText(stub.getDescription());
        else {
            descrip.setText(""); //$NON-NLS-1$

        }
    }

    /**
     * Refresh the map list display in the combobox listing the possible maps to plugin to.
     */
    private void refreshMapList() {
        // Clear the list of maps
        tabMapList.removeAll();
        // Get the list of all maps in the .jucm.
        List mapsList = stub.getPathGraph().getMap().getUcmspec().getMaps();

        // Array wich will contain all binded maps to this stub
        ArrayList binded = new ArrayList();

        List plugins = stub.getBindings();
        // Loop through all the pluginbindings and find the maps wich are binded to this map.
        for (Iterator i = plugins.iterator(); i.hasNext();) {
            PluginBinding plugin = (PluginBinding) i.next();
            binded.add(plugin.getPlugin());
        }

        TableItem item;
        // For each map
        for (Iterator i = mapsList.iterator(); i.hasNext();) {
            Map map = (Map) i.next();
            // The map can't be the same as the one the stub in contained in.
            if (map != stub.getPathGraph().getMap()) {
                item = new TableItem(tabMapList, SWT.NONE);
                item.setText(map.getName());
                item.setData(map);
                // If the map is binded to this stub, check the item.
                if (binded.contains(map)) {
                    item.setChecked(true);
                }
            }
        }
    }

    /**
     * Refresh with the correct info the tree of the bindings of this stub. The tree will build itself with the following structure: <br>
     * Bindings <br>
     * |-+ Stub <->Map <br>| |-+ In bindings <br>| | |-+ StartPoint <->EmptyPoint <br>| |-+ Out bindings <br>| | |-+ EndPoint <->EmptyPoint <br>
     */
    private void refreshBindingsTree() {
        treeBindings.removeAll();

        List list = stub.getBindings();
        TreeItem root = new TreeItem(treeBindings, SWT.NULL);
        root.setText(Messages.getString("StubBindingsDialog.bindings")); //$NON-NLS-1$
        TreeItem item; // This represents a PluginBinding
        TreeItem subLabelItem; // An item for a label under item. This item cannot be deleted/selected.
        TreeItem subItem; // This represent a In/OutBinding

        Image image;

        // Loop through all the PluginBindings
        for (Iterator i = list.iterator(); i.hasNext();) {
            item = root;
            PluginBinding binding = (PluginBinding) i.next();
            // Generate a tree item under root for this PluginBinding
            item = new TreeItem(item, SWT.NULL);
            item.setText(binding.getStub().getName() + " <-> " + binding.getPlugin().getName()); //$NON-NLS-1$
            image = (ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Binding16.gif")).createImage(); //$NON-NLS-1$
            images.add(image);
            item.setImage(image);
            item.setData(binding);

            // Then add a label for InBindings under this item
            subLabelItem = new TreeItem(item, SWT.NULL);
            subLabelItem.setText(Messages.getString("StubBindingsDialog.inBindings")); //$NON-NLS-1$

            // Loop through all the InBindings and add them under the InBinding label
            List in = binding.getIn();
            for (Iterator j = in.iterator(); j.hasNext();) {
                InBinding inBind = (InBinding) j.next();
                subItem = new TreeItem(subLabelItem, SWT.NULL);
                subItem.setText("IN" + (stub.getPred().indexOf(inBind.getStubEntry()) + 1) + " <-> " + inBind.getStartPoint().getName()); //$NON-NLS-1$ //$NON-NLS-2$
                image = (ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/inBinding16.gif")).createImage(); //$NON-NLS-1$
                images.add(image);
                subItem.setImage(image);
                subItem.setData(inBind);
            }
            subLabelItem.setExpanded(true); // We want everything expanded by default.

            // The add the label for OutBindings under the PluginBinding item
            subLabelItem = new TreeItem(item, SWT.NULL);
            subLabelItem.setText(Messages.getString("StubBindingsDialog.outBindings")); //$NON-NLS-1$

            // Loop through all the OutBindings and add them under the OutBinding label
            List out = binding.getOut();
            for (Iterator j = out.iterator(); j.hasNext();) {
                OutBinding outBind = (OutBinding) j.next();
                subItem = new TreeItem(subLabelItem, SWT.NULL);
                subItem.setText("OUT" + (stub.getSucc().indexOf(outBind.getStubExit()) + 1) + " <-> " + outBind.getEndPoint().getName()); //$NON-NLS-1$ //$NON-NLS-2$
                image = (ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/outBinding16.gif")).createImage(); //$NON-NLS-1$
                images.add(image);
                subItem.setImage(image);
                subItem.setData(outBind);
            }
            //	We want everything expanded by default.
            subLabelItem.setExpanded(true);
            item.setExpanded(true);
        }
        root.setExpanded(true);
    }

    // The list of all the incoming connections from outside to the stub
    private ArrayList inStubList;
    // The list of all the StartPoints of the binded map.
    private ArrayList inMapList;
    // The list of all the outgoing connections from the stub to the outside.
    private ArrayList outStubList;
    // The list of all the EndPoints of the binded map.
    private ArrayList outMapList;

    /**
     * Refresh the lists and tables for the ins/out of the Stub and Map of a PluginBinding.
     * 
     * @param selectedPlugin
     *            The currently selected PluginBinding.
     */
    protected void setSelectedPluginView(PluginBinding selectedPlugin) {
        // Remove everything from the tables
        tabMapIns.removeAll();
        tabStubIns.removeAll();
        tabMapOuts.removeAll();
        tabStubOuts.removeAll();

        // Clear the arrays
        inStubList = new ArrayList();
        inMapList = new ArrayList();
        outStubList = new ArrayList();
        outMapList = new ArrayList();

        // If the user selected a plugin
        if (selectedPlugin != null) {
            // Update the label
            selectedPluginLabel.setText(selectedPlugin.getStub().getName() + " <-> " + selectedPlugin.getPlugin().getName()); //$NON-NLS-1$
            selectedPluginLabel.setFont(new Font(null, new FontData("", 8, SWT.BOLD)));
            selectedPluginLabel.setData(selectedPlugin);
            addPluginClient.setVisible(true);

            // Initialize those images to add to the table items.
            Image start = (ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Start16.gif")).createImage(); //$NON-NLS-1$
            Image end = (ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/End16.gif")).createImage(); //$NON-NLS-1$
            Image in = (ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/inBinding16.gif")).createImage(); //$NON-NLS-1$
            Image out = (ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/outBinding16.gif")).createImage(); //$NON-NLS-1$
            images.add(in);
            images.add(out);
            images.add(start);
            images.add(end);

            // Get the list of all the incoming connections from outside to the stub to fill the 'in' list.
            List list = stub.getPred();
            TableItem item;
            int j = 1;
            // This will fill the list for the entries of the stub.
            for (Iterator i = list.iterator(); i.hasNext();) {
                NodeConnection con = (NodeConnection) i.next();
                if (!isNodeConnectionInBinded(con, selectedPlugin)) {
                    inStubList.add(con);
                    item = new TableItem(tabStubIns, SWT.NULL);
                    item.setText(Messages.getString("StubBindingsDialog.IN") + j); //$NON-NLS-1$
                    item.setImage(in);
                }
                j++;
            }

            list = stub.getSucc();
            j = 1;
            // This will fill the list for the exists of the stub.
            for (Iterator i = list.iterator(); i.hasNext();) {
                NodeConnection con = (NodeConnection) i.next();
                if (!isNodeConnectionOutBinded(con, selectedPlugin)) {
                    outStubList.add(con);
                    item = new TableItem(tabStubOuts, SWT.NULL);
                    item.setText(Messages.getString("StubBindingsDialog.OUT") + j); //$NON-NLS-1$
                    item.setImage(out);
                }
                j++;
            }

            list = selectedPlugin.getPlugin().getPathGraph().getPathNodes();
            // Loop through all the nodes of the map and add the start and end points to the lists.
            for (Iterator i = list.iterator(); i.hasNext();) {
                PathNode node = (PathNode) i.next();
                // The node is a start point
                if (node instanceof StartPoint) {
                    if (!isStartPointInBinded((StartPoint) node, selectedPlugin)) {
                        inMapList.add(node);
                        item = new TableItem(tabMapIns, SWT.NULL);
                        item.setText(node.getName());
                        item.setImage(start);
                    }
                    // The node is an EndPoint
                } else if (node instanceof EndPoint) {
                    if (!isEndPointOutBinded((EndPoint) node, selectedPlugin)) {
                        outMapList.add(node);
                        item = new TableItem(tabMapOuts, SWT.NULL);
                        item.setText(node.getName());
                        item.setImage(end);
                    }
                }
            }
        } else {
            selectedPluginLabel.setText(Messages.getString("StubBindingsDialog.noPluginSelected")); //$NON-NLS-1$
            selectedPluginLabel.setFont(new Font(null, new FontData("", 8, SWT.BOLD))); //$NON-NLS-1$
            addPluginClient.setVisible(false);
        }
    }

    /**
     * @param con
     * @return
     */
    private boolean isNodeConnectionOutBinded(NodeConnection con, PluginBinding plugin) {
        if (stub.getBindings().size() > 0) {
            List outs = plugin.getOut();
            for (Iterator i = outs.iterator(); i.hasNext();) {
                OutBinding out = (OutBinding) i.next();
                if (out.getStubExit() == con)
                    return true;
            }
        }
        return false;
    }

    /**
     * @param point
     * @return
     */
    private boolean isEndPointOutBinded(EndPoint end, PluginBinding plugin) {
        if (stub.getBindings().size() > 0) {
            List ins = plugin.getOut();
            for (Iterator i = ins.iterator(); i.hasNext();) {
                OutBinding out = (OutBinding) i.next();
                if (out.getEndPoint() == end)
                    return true;
            }
        }
        return false;
    }

    /**
     * Utility method to know if a StartPoint is binded with the selected Stub.
     * 
     * @param start
     *            The StartPoint you want to know is binded or not.
     * @return True if the StartPoint is contained in an InBinding of the selected Stub PluginBinding. Else return false.
     */
    private boolean isStartPointInBinded(StartPoint start, PluginBinding plugin) {
        if (stub.getBindings().size() > 0) {
            List ins = plugin.getIn();
            for (Iterator i = ins.iterator(); i.hasNext();) {
                InBinding in = (InBinding) i.next();
                if (in.getStartPoint() == start)
                    return true;
            }
        }
        return false;
    }

    /**
     * Utility method to know if a NodeConnection is binded in an InBinding with the selected Stub
     * 
     * @param con
     *            The NodeConnection you want to know is binded or not.
     * @return True if the NodeConnection is contained in an InBinding of the selected Stub PluginBinding. Else return false.
     */
    private boolean isNodeConnectionInBinded(NodeConnection con, PluginBinding plugin) {
        if (stub.getBindings().size() > 0) {
            List ins = plugin.getIn();
            for (Iterator i = ins.iterator(); i.hasNext();) {
                InBinding in = (InBinding) i.next();
                if (in.getStubEntry() == con)
                    return true;
            }
        }
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.emf.common.notify.Adapter#notifyChanged(org.eclipse.emf.common.notify.Notification)
     */
    public void notifyChanged(Notification notification) {
        int featureId = notification.getFeatureID(UcmPackage.class);
        switch (featureId) {
        case MapPackage.STUB__DESCRIPTION:
            descrip.setText(stub.getDescription());
            break;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.emf.common.notify.Adapter#getTarget()
     */
    public Notifier getTarget() {
        return target;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.emf.common.notify.Adapter#setTarget(org.eclipse.emf.common.notify.Notifier)
     */
    public void setTarget(Notifier newTarget) {
        target = newTarget;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.emf.common.notify.Adapter#isAdapterForType(java.lang.Object)
     */
    public boolean isAdapterForType(Object type) {
        return type.equals(URNspec.class);
    }
}