package seg.jUCMNav.views.stub;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
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
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.forms.widgets.TableWrapLayout;
import org.eclipse.ui.internal.WorkbenchImages;

import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.Messages;
import seg.jUCMNav.figures.ColorManager;
import seg.jUCMNav.model.commands.change.ChangePluginBindingProbCommand;
import seg.jUCMNav.model.commands.change.ChangePluginBindingRepFactorCommand;
import seg.jUCMNav.model.commands.change.ChangePluginBindingTransCommand;
import seg.jUCMNav.model.commands.create.AddComponentBindingCommand;
import seg.jUCMNav.model.commands.create.AddInBindingCommand;
import seg.jUCMNav.model.commands.create.AddOutBindingCommand;
import seg.jUCMNav.model.commands.create.AddPluginCommand;
import seg.jUCMNav.model.commands.create.AddRespRefBindingCommand;
import seg.jUCMNav.model.commands.create.CreateMapCommand;
import seg.jUCMNav.model.commands.delete.internal.DeleteComponentBindingCommand;
import seg.jUCMNav.model.commands.delete.internal.DeleteInBindingCommand;
import seg.jUCMNav.model.commands.delete.internal.DeleteOutBindingCommand;
import seg.jUCMNav.model.commands.delete.internal.DeletePluginCommand;
import seg.jUCMNav.model.commands.delete.internal.DeleteRespBindingCommand;
import seg.jUCMNav.model.commands.transformations.ChangeDescriptionCommand;
import seg.jUCMNav.model.commands.transformations.ChangeLabelNameCommand;
import seg.jUCMNav.model.commands.transformations.ReplacePluginCommand;
import seg.jUCMNav.model.util.URNNamingHelper;
import seg.jUCMNav.views.wizards.scenarios.CodeEditor;
import ucm.UcmPackage;
import ucm.map.ComponentBinding;
import ucm.map.ComponentRef;
import ucm.map.EndPoint;
import ucm.map.InBinding;
import ucm.map.MapPackage;
import ucm.map.NodeConnection;
import ucm.map.OutBinding;
import ucm.map.PathNode;
import ucm.map.PluginBinding;
import ucm.map.RespRef;
import ucm.map.ResponsibilityBinding;
import ucm.map.StartPoint;
import ucm.map.Stub;
import ucm.map.UCMmap;
import urn.URNspec;
import urncore.Component;
import urncore.ComponentKind;
import urncore.Condition;
import urncore.IURNDiagram;
import urncore.Responsibility;

/**
 * The stub bindings dialog is the only way one can manage a stub's bindings: this stub can load which maps and how its in/out connections mapped to start/end
 * points in the plugin map.
 * 
 * @author Etienne Tremblay
 */
public class StubBindingsDialog extends Dialog implements Adapter {
    // The toolkit for eclipse forms
    private FormToolkit toolkit;
    // The main form where all the controls will be
    private ScrolledForm form;

    // The description label.
    private Text descrip;

    // The stub we are representing here
    private Stub stub;
    // The associated URNspec of this stub
    private URNspec urnSpec;

    // Used by for EMF notification
    private Notifier target;

    // Sections in the form.
    private Section mapSection; // Select the plugin(s) of the stub.
    private Section pluginListSection; // List of all the PluginBindings with
    // all there attributes.

    // The tree listing the PluginBindings.
    private Tree treeBindings;

    private Table tabMapIns; // The table for making in bindings with maps
    private TableColumn mapInsColumn; // It's first column (so that we can
    // make it as wide as the table)
    private Table tabStubIns; // The table for making in bindings with stubs
    private TableColumn stubInsColumn; // It's first column (so that we can
    // make it as wide as the table)

    private Table tabMapOuts; // The table for making out bindings with maps
    private TableColumn mapOutsColumn; // It's first column (so that we can
    // make it as wide as the table)
    private Table tabStubOuts; // The table for making out bindings with stubs
    private TableColumn stubOutsColumn; // It's first column (so that we can
    // make it as wide as the table)

    private Table tabParentComps; // The table for making out bindings with maps
    private TableColumn compParentColumn; // It's first column (so that we can
    // make it as wide as the table)
    private Table tabPluginComps; // The table for making out bindings with stubs
    private TableColumn compPluginColumn; // It's first column (so that we can
    // make it as wide as the table)

    private Table tabParentResps; // The table for making out bindings with maps
    private TableColumn respParentColumn; // It's first column (so that we can
    // make it as wide as the table)
    private Table tabPluginResps; // The table for making out bindings with stubs
    private TableColumn respPluginColumn; // It's first column (so that we can
    // make it as wide as the table)

    // The button for doing in bindings.
    private Button btInBind;
    // The button for doing out bindings.
    private Button btOutBind;
    // The button for doing component bindings
    private Button btCompBind;
    // The button for doing responsibility bindings
    private Button btRespBind;

    // The editor from wich this dialog was opened.
    private CommandStack cmdStack;

    // The list of images to dispose at the end.
    private ArrayList<Image> images = new ArrayList<Image>();

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
    // The total number of executed commands to date. Not decreased with undo.
    // Reseted to executedCount when execute is called.
    private int totalExecuted;

    // The currently selected item in the tree view.
    private Object selectedItem;

    // The text field for the condition expression of a PluginBinding.
    private Label txtExpCondition;

    // The text field for the label of a PluginBinding
    private Text txtLabelCondition;

    // The text field for the description of a PluginBinding
    private Text txtDescCondition;

    private boolean preventUpdate;

    // The text field and Double for the probability of a PluginBinding
    private Text txtProbability;
    private Text txtRepFactor;
    private Label lblProbabilityValid;
    private Label txtTransaction;
    private Button btChangeTrans;
    private Button okButton;

    private final int listHeight = 100;
    private final int columnWidth = 130;
    private Label lblRepFactorValid;

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
        //form.setText(Messages.getString("StubBindingsDialog.stubBindings")); //$NON-NLS-1$
        TableWrapLayout layout = new TableWrapLayout();
        layout.numColumns = 2;
        form.getBody().setLayout(layout);
        TableWrapData td = new TableWrapData();

        final ExpandableComposite ec = toolkit.createExpandableComposite(form.getBody(), ExpandableComposite.TWISTIE | ExpandableComposite.EXPANDED | SWT.FILL);
        ec.setText(Messages.getString("StubBindingsDialog.stubDesc")); //$NON-NLS-1$
        // descrip = toolkit.createLabel(ec, "", SWT.WRAP); //$NON-NLS-1$
        descrip = toolkit.createText(ec, "", SWT.BORDER | SWT.MULTI | SWT.V_SCROLL);//$NON-NLS-1$
        ec.setClient(descrip);
        td = new TableWrapData(TableWrapData.FILL_GRAB);
        td.colspan = 2;
        td.grabHorizontal = true;
        td.heightHint = 85;
        ec.setLayoutData(td);

        // Connect map section
        mapSection = toolkit.createSection(form.getBody(), ExpandableComposite.TWISTIE | ExpandableComposite.TITLE_BAR);
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
        g.heightHint = 490;
        g.widthHint = 200;
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

        final Button btCreateMap = new Button(mapClient, SWT.PUSH);
        g = new GridData();
        btCreateMap.setLayoutData(g);

        btCreateMap.setText(Messages.getString("StubBindingsDialog.CreateMap")); //$NON-NLS-1$
        btCreateMap.addMouseListener(new MouseAdapter() {
            public void mouseDown(MouseEvent e) {
                handleCreateMap();
            }
        });

        mapSection.setClient(mapClient);

        // Plugin List section
        pluginListSection = toolkit.createSection(form.getBody(), ExpandableComposite.TWISTIE | ExpandableComposite.TITLE_BAR);
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

        Image image = (JUCMNavPlugin.getImage("icons/Binding16.gif")); //$NON-NLS-1$
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
        image = WorkbenchImages.getImage(ISharedImages.IMG_TOOL_REDO);
        images.add(image);
        btRedo.setImage(image);
        btRedo.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                redo();
            }
        });

        btRedo.setEnabled(false);

        image = (JUCMNavPlugin.getImage("icons/Binding16.gif")); //$NON-NLS-1$
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

        treeBindings.addKeyListener(new KeyListener() {

            public void keyPressed(KeyEvent e) {
                if (e.keyCode == SWT.DEL) {
                    delete();
                } else if ((e.stateMask == SWT.CTRL) && ((e.keyCode == 'z') || (e.keyCode == 'Z'))) {
                    if (btUndo.isEnabled())
                        undo();

                } else if ((e.stateMask == SWT.CTRL) && ((e.keyCode == 'y') || (e.keyCode == 'Y'))) {
                    if (btRedo.isEnabled())
                        redo();

                }
            }

            public void keyReleased(KeyEvent e) {

            }

        });
        pluginListSection.setClient(sectionClient);

        addPluginClient = toolkit.createComposite(sectionClient, SWT.BORDER);
        addPluginClient.setVisible(false);
        grid = new GridLayout();
        grid.numColumns = 1;
        grid.makeColumnsEqualWidth = false;
        addPluginClient.setLayout(grid);
        GridData f = new GridData(GridData.FILL_BOTH);
        f.grabExcessHorizontalSpace = true;
        f.grabExcessVerticalSpace = true;
        addPluginClient.setLayoutData(f);

        // Create tab folder containing controls to define new In/Out Bindings
        // and component and responsibility bindings.
        createTabFolder();

        // start of condition controls
        Composite compCondition = toolkit.createComposite(addPluginClient, SWT.BORDER);
        grid = new GridLayout();
        grid.numColumns = 3;
        compCondition.setLayout(grid);

        g = new GridData(GridData.FILL_BOTH);
        g.grabExcessHorizontalSpace = true;
        g.grabExcessVerticalSpace = true;
        g.horizontalSpan = 3;

        compCondition.setLayoutData(g);
        // compCondition.setBackground(new Color(null, 232, 247, 255));

        Label lblLabelCondition = toolkit.createLabel(compCondition, Messages.getString("StubBindingsDialog.Label")); //$NON-NLS-1$
        g = new GridData(GridData.FILL_BOTH);
        g.grabExcessHorizontalSpace = false;
        g.grabExcessVerticalSpace = true;
        lblLabelCondition.setLayoutData(g);
        // lblLabelCondition.setBackground(new Color(null, 232, 247, 255));

        txtLabelCondition = toolkit.createText(compCondition, "", SWT.BORDER); //$NON-NLS-1$
        g = new GridData(GridData.FILL_BOTH);
        g.grabExcessHorizontalSpace = true;
        g.grabExcessVerticalSpace = true;
        g.horizontalSpan = 2;

        txtLabelCondition.setLayoutData(g);
        txtLabelCondition.addModifyListener(new ModifyListener() {
            public void modifyText(ModifyEvent e) {
                handleUpdateLabels(false);
            }
        });

        Label lblExpCondition = toolkit.createLabel(compCondition, Messages.getString("StubBindingsDialog.Expression")); //$NON-NLS-1$
        g = new GridData(GridData.FILL_BOTH);
        g.grabExcessHorizontalSpace = false;
        g.grabExcessVerticalSpace = true;
        lblExpCondition.setLayoutData(g);
        // lblExpCondition.setBackground(new Color(null, 232, 247, 255));

        txtExpCondition = toolkit.createLabel(compCondition, "true"); //$NON-NLS-1$
        g = new GridData(GridData.FILL_BOTH);
        g.grabExcessHorizontalSpace = true;
        g.grabExcessVerticalSpace = true;

        txtExpCondition.setLayoutData(g);
        // txtExpCondition.addModifyListener(new ModifyListener() {
        // public void modifyText(ModifyEvent e) {
        // handleUpdateLabels(false);
        // }
        // });

        Button editCondition = toolkit.createButton(compCondition, Messages.getString("StubBindingsDialog.Edit"), SWT.BORDER); //$NON-NLS-1$
        g = new GridData(GridData.FILL_BOTH);
        g.grabExcessHorizontalSpace = false;
        g.grabExcessVerticalSpace = true;
        editCondition.setLayoutData(g);
        editCondition.addMouseListener(new MouseAdapter() {
            public void mouseUp(MouseEvent e) {
                Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
                CodeEditor wizard = new CodeEditor();
                PluginBinding plug = (PluginBinding) selectedPluginLabel.getData();

                wizard.init(PlatformUI.getWorkbench(), null, plug.getPrecondition());
                WizardDialog dialog = new WizardDialog(shell, wizard);
                dialog.open();
                refreshCondition();
            }
        });

        Label lblDescCondition = toolkit.createLabel(compCondition, Messages.getString("StubBindingsDialog.Description")); //$NON-NLS-1$
        g = new GridData(GridData.FILL_BOTH);
        g.grabExcessHorizontalSpace = false;
        g.grabExcessVerticalSpace = true;
        lblDescCondition.setLayoutData(g);

        // lblDescCondition.setBackground(new Color(null, 232, 247, 255));

        txtDescCondition = toolkit.createText(compCondition, "", SWT.BORDER); //$NON-NLS-1$
        g = new GridData(GridData.FILL_BOTH);
        g.grabExcessHorizontalSpace = true;
        g.grabExcessVerticalSpace = true;
        g.horizontalSpan = 2;
        txtDescCondition.setLayoutData(g);
        txtDescCondition.addModifyListener(new ModifyListener() {
            public void modifyText(ModifyEvent e) {
                handleUpdateLabels(false);
            }
        });

        // Transaction: label, value & button to toggle the value
        Label lblTransaction = toolkit.createLabel(compCondition, "Transaction:"); //$NON-NLS-1$
        g = new GridData(GridData.FILL_BOTH);
        g.grabExcessHorizontalSpace = false;
        g.grabExcessVerticalSpace = true;
        lblTransaction.setLayoutData(g);

        txtTransaction = toolkit.createLabel(compCondition, "" + true); //$NON-NLS-1$
        g = new GridData(GridData.FILL_BOTH);
        g.grabExcessHorizontalSpace = true;
        g.grabExcessVerticalSpace = true;
        txtTransaction.setLayoutData(g);

        btChangeTrans = new Button(compCondition, SWT.PUSH);
        g = new GridData(GridData.FILL_BOTH);
        g.grabExcessHorizontalSpace = false;
        g.grabExcessVerticalSpace = true;
        btChangeTrans.setLayoutData(g);
        btChangeTrans.setText("Toggle"); //$NON-NLS-1$
        btChangeTrans.addMouseListener(new MouseAdapter() {
            public void mouseDown(MouseEvent e) {
                handleUpdateTransaction();
            }
        });

        // Probability: label & value(double)
        Label lblProbability = toolkit.createLabel(compCondition, "Probability:"); //$NON-NLS-1$
        g = new GridData(GridData.FILL_BOTH);
        g.grabExcessHorizontalSpace = false;
        g.grabExcessVerticalSpace = true;
        lblProbability.setLayoutData(g);

        txtProbability = toolkit.createText(compCondition, "", SWT.BORDER); //$NON-NLS-1$
        g = new GridData();
        g.grabExcessHorizontalSpace = true;
        g.grabExcessVerticalSpace = true;
        g.horizontalSpan = 1;
        g.widthHint = 50;
        txtProbability.setLayoutData(g);
        txtProbability.addModifyListener(new ModifyListener() {
            public void modifyText(ModifyEvent event) {
                handleUpdateProbability();
            }
        });

        lblProbabilityValid = toolkit.createLabel(compCondition, ""); //$NON-NLS-1$
        g = new GridData(GridData.FILL_BOTH);
        g.grabExcessHorizontalSpace = false;
        g.grabExcessVerticalSpace = true;
        lblProbabilityValid.setLayoutData(g);


        // Probability: label & value(double)
        Label lblRepFactor = toolkit.createLabel(compCondition, Messages.getString("StubBindingsDialog.RepFactor")); //$NON-NLS-1$
        g = new GridData(GridData.FILL_BOTH);
        g.grabExcessHorizontalSpace = false;
        g.grabExcessVerticalSpace = true;
        lblRepFactor.setLayoutData(g);

        txtRepFactor = toolkit.createText(compCondition, "", SWT.BORDER); //$NON-NLS-1$
        g = new GridData();
        g.grabExcessHorizontalSpace = true;
        g.grabExcessVerticalSpace = true;
        g.horizontalSpan = 1;
        g.widthHint = 50;
        txtRepFactor.setLayoutData(g);
        txtRepFactor.addModifyListener(new ModifyListener() {
            public void modifyText(ModifyEvent event) {
                handleUpdateReplicationFactor();
            }
        });

        lblRepFactorValid = toolkit.createLabel(compCondition, ""); //$NON-NLS-1$
        g = new GridData(GridData.FILL_BOTH);
        g.grabExcessHorizontalSpace = false;
        g.grabExcessVerticalSpace = true;
        lblRepFactorValid.setLayoutData(g);

        // btUpdateLink = toolkit.createHyperlink(compCondition,
        // Messages.getString("StubBindingsDialog.Update"), SWT.NONE);
        // //$NON-NLS-1$
        // g = new GridData(GridData.FILL_BOTH);
        // g.grabExcessHorizontalSpace = true;
        // g.grabExcessVerticalSpace = true;
        // g.horizontalSpan = 2;
        // g.horizontalAlignment = GridData.END;
        // btUpdateLink.setLayoutData(g);
        // // btUpdateLink.setBackground(new Color(null, 232, 247, 255));
        //        
        // btUpdateLink.addMouseListener(new MouseAdapter() {
        // public void mouseDown(MouseEvent e) {
        // handleUpdateLabels();
        // }
        // });
        //        
        mapSection.setExpanded(true);
        pluginListSection.setExpanded(true);

        return area;
    }

    private void createTabFolder() {
        GridLayout grid;
        GridData g;
        Label lb;
        GridData f;

        CTabFolder tabFolder = new CTabFolder(addPluginClient, SWT.TOP);
        toolkit.adapt(tabFolder);
        tabFolder.setBorderVisible(true);
        tabFolder.setLayoutData(new GridData(GridData.FILL_BOTH));

        CTabItem item1 = new CTabItem(tabFolder, SWT.NONE);
        item1.setText(Messages.getString("StubBindingsDialog.StartEnd")); //$NON-NLS-1$

        Composite page1 = toolkit.createComposite(tabFolder);
        grid = new GridLayout();
        grid.numColumns = 3;
        grid.makeColumnsEqualWidth = false;
        page1.setLayout(grid);
        f = new GridData(GridData.FILL_BOTH);
        f.grabExcessHorizontalSpace = true;
        f.grabExcessVerticalSpace = true;
        page1.setLayoutData(f);

        item1.setControl(page1);

        tabFolder.setSelection(item1);

        Composite stubComp;
        Composite buttonComp;
        Composite mapComp;

        createInBindingsControls(page1);

        createOutBindingsControls(page1);

        // start of component binding controls
        CTabItem item2 = new CTabItem(tabFolder, SWT.NONE);
        item2.setText(Messages.getString("StubBindingsDialog.CompRespTab")); //$NON-NLS-1$

        Composite page2 = toolkit.createComposite(tabFolder);
        grid = new GridLayout();
        grid.numColumns = 3;
        grid.makeColumnsEqualWidth = false;
        page2.setLayout(grid);
        f = new GridData(GridData.FILL_BOTH);
        f.grabExcessHorizontalSpace = true;
        f.grabExcessVerticalSpace = true;
        page2.setLayoutData(f);

        item2.setControl(page2);

        createCompBindingsControls(page2);

        createRespBindingsControls(page2);
    }

    private void createCompBindingsControls(Composite page2) {
        GridLayout grid;
        GridData g;
        Label lb;
        Composite stubComp;
        Composite buttonComp;
        Composite mapComp;

        stubComp = toolkit.createComposite(page2);
        grid = new GridLayout();
        grid.numColumns = 1;
        stubComp.setLayout(grid);
        g = new GridData(GridData.FILL_BOTH);
        g.grabExcessHorizontalSpace = true;
        g.grabExcessVerticalSpace = true;
        stubComp.setLayoutData(g);

        lb = toolkit.createLabel(stubComp, Messages.getString("StubBindingsDialog.ParentMap")); //$NON-NLS-1$

        tabParentComps = toolkit.createTable(stubComp, SWT.SINGLE | SWT.FULL_SELECTION);
        tabParentComps.setLinesVisible(true);
        tabParentComps.setHeaderVisible(true);
        tabParentComps.addMouseListener(new MouseAdapter() {
            public void mouseUp(MouseEvent e) {
                if (tabParentComps.getSelectionCount() >= 1 && tabPluginComps.getSelectionCount() >= 1 && stub.getBindings().size() > 0)
                    btCompBind.setEnabled(true);
                else
                    btCompBind.setEnabled(false);
            }
        });
        g = new GridData(GridData.FILL_BOTH);
        g.grabExcessHorizontalSpace = true;
        g.grabExcessVerticalSpace = true;
        g.heightHint = listHeight;
        tabParentComps.setLayoutData(g);
        compParentColumn = new TableColumn(tabParentComps, SWT.NONE);
        compParentColumn.setWidth(columnWidth);
        compParentColumn.setText(Messages.getString("StubBindingsDialog.Components")); //$NON-NLS-1$

        buttonComp = toolkit.createComposite(page2);
        grid = new GridLayout();
        grid.numColumns = 1;
        grid.makeColumnsEqualWidth = true;
        buttonComp.setLayout(grid);
        g = new GridData(GridData.FILL_BOTH);
        g.grabExcessHorizontalSpace = true;
        g.grabExcessVerticalSpace = true;
        buttonComp.setLayoutData(g);

        btCompBind = toolkit.createButton(buttonComp, "<->", SWT.PUSH | SWT.FLAT); //$NON-NLS-1$
        btCompBind.setEnabled(false);
        g = new GridData();
        g.grabExcessHorizontalSpace = true;
        g.grabExcessVerticalSpace = true;
        g.horizontalAlignment = GridData.CENTER;
        g.verticalAlignment = GridData.CENTER;
        btCompBind.setLayoutData(g);
        btCompBind.addMouseListener(new MouseAdapter() {
            public void mouseUp(MouseEvent e) {
                handleCompBindClick();
            }
        });

        mapComp = toolkit.createComposite(page2);
        grid = new GridLayout();
        grid.numColumns = 1;
        mapComp.setLayout(grid);
        g = new GridData(GridData.FILL_BOTH);
        g.grabExcessHorizontalSpace = true;
        g.grabExcessVerticalSpace = true;
        mapComp.setLayoutData(g);

        lb = toolkit.createLabel(mapComp, Messages.getString("StubBindingsDialog.PluginMap")); //$NON-NLS-1$

        tabPluginComps = toolkit.createTable(mapComp, SWT.SINGLE | SWT.FULL_SELECTION);
        tabPluginComps.setLinesVisible(true);
        tabPluginComps.setHeaderVisible(true);
        tabPluginComps.addMouseListener(new MouseAdapter() {
            public void mouseUp(MouseEvent e) {
                if (tabParentComps.getSelectionCount() >= 1 && tabPluginComps.getSelectionCount() >= 1 && stub.getBindings().size() > 0)
                    btCompBind.setEnabled(true);
                else
                    btCompBind.setEnabled(false);
            }
        });
        g = new GridData(GridData.FILL_BOTH);
        g.grabExcessHorizontalSpace = true;
        g.grabExcessVerticalSpace = true;
        g.heightHint = listHeight;
        tabPluginComps.setLayoutData(g);
        compPluginColumn = new TableColumn(tabPluginComps, SWT.NONE);
        compPluginColumn.setWidth(columnWidth);
        compPluginColumn.setText(Messages.getString("StubBindingsDialog.Components")); //$NON-NLS-1$
    }

    private void createRespBindingsControls(Composite page2) {
        Composite stubComp = toolkit.createComposite(page2);
        GridLayout grid = new GridLayout();
        grid.numColumns = 1;
        stubComp.setLayout(grid);
        GridData g = new GridData(GridData.FILL_BOTH);
        g.grabExcessHorizontalSpace = true;
        g.grabExcessVerticalSpace = true;
        stubComp.setLayoutData(g);

        Label lb = toolkit.createLabel(stubComp, Messages.getString("StubBindingsDialog.ParentMap")); //$NON-NLS-1$

        tabParentResps = toolkit.createTable(stubComp, SWT.SINGLE | SWT.FULL_SELECTION);
        tabParentResps.setLinesVisible(true);
        tabParentResps.setHeaderVisible(true);
        tabParentResps.addMouseListener(new MouseAdapter() {
            public void mouseUp(MouseEvent e) {
                if (tabParentResps.getSelectionCount() >= 1 && tabPluginResps.getSelectionCount() >= 1 && stub.getBindings().size() > 0)
                    btRespBind.setEnabled(true);
                else
                    btRespBind.setEnabled(false);
            }
        });
        g = new GridData(GridData.FILL_BOTH);
        g.grabExcessHorizontalSpace = true;
        g.grabExcessVerticalSpace = true;
        g.heightHint = listHeight;
        tabParentResps.setLayoutData(g);
        respParentColumn = new TableColumn(tabParentResps, SWT.NONE);
        respParentColumn.setWidth(columnWidth);
        respParentColumn.setText(Messages.getString("LabelTreeEditPart.responsibilities")); //$NON-NLS-1$

        Composite buttonComp = toolkit.createComposite(page2);
        grid = new GridLayout();
        grid.numColumns = 1;
        grid.makeColumnsEqualWidth = true;
        buttonComp.setLayout(grid);
        g = new GridData(GridData.FILL_BOTH);
        g.grabExcessHorizontalSpace = true;
        g.grabExcessVerticalSpace = true;
        buttonComp.setLayoutData(g);

        btRespBind = toolkit.createButton(buttonComp, "<->", SWT.PUSH | SWT.FLAT); //$NON-NLS-1$
        btRespBind.setEnabled(false);
        g = new GridData();
        g.grabExcessHorizontalSpace = true;
        g.grabExcessVerticalSpace = true;
        g.horizontalAlignment = GridData.CENTER;
        g.verticalAlignment = GridData.CENTER;
        btRespBind.setLayoutData(g);
        btRespBind.addMouseListener(new MouseAdapter() {
            public void mouseUp(MouseEvent e) {
                handleRespBindClick();
            }
        });

        Composite mapComp = toolkit.createComposite(page2);
        grid = new GridLayout();
        grid.numColumns = 1;
        mapComp.setLayout(grid);
        g = new GridData(GridData.FILL_BOTH);
        g.grabExcessHorizontalSpace = true;
        g.grabExcessVerticalSpace = true;
        mapComp.setLayoutData(g);

        lb = toolkit.createLabel(mapComp, Messages.getString("StubBindingsDialog.PluginMap")); //$NON-NLS-1$

        tabPluginResps = toolkit.createTable(mapComp, SWT.SINGLE | SWT.FULL_SELECTION);
        tabPluginResps.setLinesVisible(true);
        tabPluginResps.setHeaderVisible(true);
        tabPluginResps.addMouseListener(new MouseAdapter() {
            public void mouseUp(MouseEvent e) {
                if (tabPluginResps.getSelectionCount() >= 1 && tabPluginResps.getSelectionCount() >= 1 && stub.getBindings().size() > 0)
                    btRespBind.setEnabled(true);
                else
                    btRespBind.setEnabled(false);
            }
        });
        g = new GridData(GridData.FILL_BOTH);
        g.grabExcessHorizontalSpace = true;
        g.grabExcessVerticalSpace = true;
        g.heightHint = listHeight;
        tabPluginResps.setLayoutData(g);
        respPluginColumn = new TableColumn(tabPluginResps, SWT.NONE);
        respPluginColumn.setWidth(columnWidth);
        respPluginColumn.setText(Messages.getString("LabelTreeEditPart.responsibilities")); //$NON-NLS-1$
    }

    private void createOutBindingsControls(Composite page1) {
        GridLayout grid;
        GridData g;
        Label lb;
        Composite stubComp;
        Composite buttonComp;
        Composite mapComp;
        // Out bindings controls
        stubComp = toolkit.createComposite(page1);
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
        g.heightHint = listHeight;
        tabStubOuts.setLayoutData(g);
        stubOutsColumn = new TableColumn(tabStubOuts, SWT.NONE);
        stubOutsColumn.setWidth(columnWidth);
        stubOutsColumn.setText(Messages.getString("StubBindingsDialog.out")); //$NON-NLS-1$

        buttonComp = toolkit.createComposite(page1);
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

        mapComp = toolkit.createComposite(page1);
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
        g.heightHint = listHeight;
        tabMapOuts.setLayoutData(g);
        mapOutsColumn = new TableColumn(tabMapOuts, SWT.NONE);
        mapOutsColumn.setWidth(columnWidth);
        mapOutsColumn.setText(Messages.getString("StubBindingsDialog.outMap")); //$NON-NLS-1$
    }

    private void createInBindingsControls(Composite page1) {
        GridLayout grid;
        GridData g;
        Label lb;
        GridData f;
        // Stub composite for creating new bindings.
        Composite stubComp = toolkit.createComposite(page1);
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
        g.heightHint = listHeight;
        tabStubIns.setLayoutData(g);
        stubInsColumn = new TableColumn(tabStubIns, SWT.NONE);
        stubInsColumn.setWidth(columnWidth);
        stubInsColumn.setText(Messages.getString("StubBindingsDialog.in")); //$NON-NLS-1$

        Composite buttonComp = toolkit.createComposite(page1);
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

        Composite mapComp = toolkit.createComposite(page1);
        grid = new GridLayout();
        grid.numColumns = 1;
        mapComp.setLayout(grid);
        g = new GridData(GridData.FILL_BOTH);
        g.grabExcessHorizontalSpace = true;
        g.grabExcessVerticalSpace = true;
        mapComp.setLayoutData(g);

        lb = toolkit.createLabel(mapComp, Messages.getString("StubBindingsDialog.Map")); //$NON-NLS-1$

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
        g.heightHint = listHeight;
        tabMapIns.setLayoutData(g);
        mapInsColumn = new TableColumn(tabMapIns, SWT.NONE);
        mapInsColumn.setWidth(columnWidth);
        mapInsColumn.setText(Messages.getString("StubBindingsDialog.inMap")); //$NON-NLS-1$
    }

    /**
     * 
     */
    protected void handleCreateMap() {
        CreateMapCommand cmd = new CreateMapCommand(urnSpec);
        execute(cmd);

        tabMapList.layout(false);
    }

    /**
     * Updates the curretly selected plugin binding with what is in the text boxes.
     * 
     * @param refresh
     *            should the data be re-loaded after modification?
     */
    protected void handleUpdateLabels(boolean refresh) {
        if (this.preventUpdate)
            return;

        PluginBinding plug = (PluginBinding) selectedPluginLabel.getData();
        Condition cond = plug.getPrecondition();
        if (cond.getLabel() != null && cond.getDescription() != null && cond.getExpression() != null && cond.getLabel().equals(txtLabelCondition.getText())
                && cond.getExpression().equals(txtExpCondition.getText()) && cond.getDescription().equals(txtDescCondition.getText()))
            return;
        ChangeLabelNameCommand label = new ChangeLabelNameCommand(cond, txtLabelCondition.getText());
        label.setExpression(txtExpCondition.getText());
        label.setDescription(txtDescCondition.getText());

        execute(label, false);

        if (refresh)
            refreshCondition();
    }

    private void refreshCondition() {
        PluginBinding plug = (PluginBinding) selectedPluginLabel.getData();

        this.preventUpdate = true;
        if (plug.getPrecondition() != null) {
            txtLabelCondition.setText(plug.getPrecondition().getLabel() == null ? "" : plug.getPrecondition().getLabel()); //$NON-NLS-1$
            // Replaces && with &&&& for correct visualization, see bug #556
            txtExpCondition.setText(plug.getPrecondition().getExpression() == null ? "" : plug.getPrecondition().getExpression().replaceAll("&&", "&&&&")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            txtDescCondition.setText(plug.getPrecondition().getDescription() == null ? "" : plug.getPrecondition().getDescription()); //$NON-NLS-1$
        } else {
            txtLabelCondition.setText(""); //$NON-NLS-1$
            txtExpCondition.setText(""); //$NON-NLS-1$
            txtDescCondition.setText(""); //$NON-NLS-1$

            txtLabelCondition.setEnabled(false);
            txtExpCondition.setEnabled(false);
            txtDescCondition.setEnabled(false);
        }
        this.preventUpdate = false;
    }

    /**
     * Listener when the probability value is modified.
     * 
     */
    protected void handleUpdateProbability() {
        if (this.preventUpdate)
            return;
        PluginBinding pluginBinding = selectedPluginBinding();
        if (pluginBinding != null) {
            try {
                Double probability = new Double(txtProbability.getText());
                if (pluginBinding.getProbability() != probability.doubleValue()) {
                    ChangePluginBindingProbCommand changeProb = new ChangePluginBindingProbCommand(pluginBinding, probability.doubleValue());
                    execute(changeProb, false);
                }
                okButton.setEnabled(true);
                lblProbabilityValid.setText(""); //$NON-NLS-1$
                lblProbabilityValid.setBackground(ColorManager.WHITE);
            } catch (Exception e) {
                okButton.setEnabled(false);
                lblProbabilityValid.setText("INVALID"); //$NON-NLS-1$
                lblProbabilityValid.setBackground(ColorManager.RED);
            }
        }
    }
    protected void handleUpdateReplicationFactor() {
        if (this.preventUpdate)
            return;
        PluginBinding pluginBinding = selectedPluginBinding();
        if (pluginBinding != null) {
            try {
                Integer repFactor = new Integer(txtRepFactor.getText());
                if (pluginBinding.getProbability() != repFactor.intValue()) {
                    ChangePluginBindingRepFactorCommand changeRepFactor = new ChangePluginBindingRepFactorCommand(pluginBinding, repFactor.intValue());
                    execute(changeRepFactor, false);
                }
                okButton.setEnabled(true);
                lblRepFactorValid.setText(""); //$NON-NLS-1$
                lblRepFactorValid.setBackground(ColorManager.WHITE);
            } catch (Exception e) {
                okButton.setEnabled(false);
                lblRepFactorValid.setText("INVALID"); //$NON-NLS-1$
                lblRepFactorValid.setBackground(ColorManager.RED);
            }
        }
    }

    /**
     * Listener when the Transaction value is changed (by the toggle button)
     * 
     */
    protected void handleUpdateTransaction() {
        if (this.preventUpdate)
            return;
        PluginBinding pluginBinding = selectedPluginBinding();
        boolean val = !pluginBinding.isTransaction();
        ChangePluginBindingTransCommand changeTrans = new ChangePluginBindingTransCommand(pluginBinding, val);
        execute(changeTrans, false);
        refreshTransaction();
    }

    /**
     * Displays value of Transaction for the selected PluginBinding
     * 
     */
    private void refreshTransaction() {
        PluginBinding pluginBinding = selectedPluginBinding();
        if (pluginBinding != null) {
            this.preventUpdate = true;
            txtTransaction.setText("" + pluginBinding.isTransaction()); //$NON-NLS-1$
            this.preventUpdate = false;
        }
    }

    /**
     * Displays value of Probability for the selected PluginBinding
     * 
     */
    private void refreshProbability() {
        PluginBinding pluginBinding = selectedPluginBinding();
        if (pluginBinding != null) {
            this.preventUpdate = true;
            txtProbability.setText("" + pluginBinding.getProbability()); //$NON-NLS-1$
            lblProbabilityValid.setText(""); //$NON-NLS-1$
            lblProbabilityValid.setBackground(ColorManager.WHITE);
            okButton.setEnabled(true);
            this.preventUpdate = false;
        }
    }

    /**
     * Displays value of Replication Factor for the selected PluginBinding
     * 
     */
    private void refreshReplicationFactor() {
        PluginBinding pluginBinding = selectedPluginBinding();
        if (pluginBinding != null) {
            this.preventUpdate = true;
            txtRepFactor.setText("" + pluginBinding.getReplicationFactor()); //$NON-NLS-1$
            lblRepFactorValid.setText(""); //$NON-NLS-1$
            lblRepFactorValid.setBackground(ColorManager.WHITE);
            okButton.setEnabled(true);
            this.preventUpdate = false;
        }
    }

    /**
     * Determines the addressed Plugin Binding based on currently selected item.
     * 
     * @return the currently selected Plugin Binding
     */
    private PluginBinding selectedPluginBinding() {
        PluginBinding plug;
        if (selectedItem instanceof PluginBinding) {
            plug = (PluginBinding) selectedItem;
        } else if (selectedItem instanceof InBinding) {
            plug = ((InBinding) selectedItem).getBinding();
        } else if (selectedItem instanceof OutBinding) {
            plug = ((OutBinding) selectedItem).getBinding();
        } else if (selectedItem instanceof ComponentBinding) {
            plug = ((ComponentBinding) selectedItem).getBinding();
        } else if (selectedItem instanceof ResponsibilityBinding) {
            plug = ((ResponsibilityBinding) selectedItem).getBinding();
        } else {
            //System.err.println("Unexpected Context:  What's the selected item?"); //$NON-NLS-1$
            plug = null;
        }
        return plug;
    }

    /**
     * Delete the selected item in the tree view. This will delete it in the model too with a command.
     */
    protected void delete() {
        if (selectedItem instanceof PluginBinding) {
            DeletePluginCommand delete = new DeletePluginCommand((PluginBinding) selectedItem);
            execute(delete);

            btDelete.setEnabled(false);

            // Refresh the add bindings view with nothing since the plugin was
            // deleted.
            setSelectedPluginView(null);
        } else if (selectedItem instanceof InBinding) {
            InBinding in = (InBinding) selectedItem;
            DeleteInBindingCommand delete = new DeleteInBindingCommand((InBinding) selectedItem);
            execute(delete);

            btDelete.setEnabled(false);

            // Refresh the add bindings view with nothing since the plugin was
            // deleted.
            setSelectedPluginView(in.getBinding());
        } else if (selectedItem instanceof OutBinding) {
            OutBinding out = (OutBinding) selectedItem;
            DeleteOutBindingCommand delete = new DeleteOutBindingCommand((OutBinding) selectedItem);
            execute(delete);

            btDelete.setEnabled(false);

            // Refresh the add bindings view with nothing since the plugin was
            // deleted.
            setSelectedPluginView(out.getBinding());
        } else if (selectedItem instanceof ComponentBinding) {
            ComponentBinding comp = (ComponentBinding) selectedItem;
            DeleteComponentBindingCommand delete = new DeleteComponentBindingCommand(comp);
            execute(delete);

            btDelete.setEnabled(false);

            // Refresh the add bindings view with nothing since the plugin was deleted.
            setSelectedPluginView(comp.getBinding());
        } else if (selectedItem instanceof ResponsibilityBinding) {
            ResponsibilityBinding resp = (ResponsibilityBinding) selectedItem;
            DeleteRespBindingCommand delete = new DeleteRespBindingCommand(resp);
            execute(delete);

            btDelete.setEnabled(false);

            // Refresh the add bindings view with nothing since the plugin was deleted.
            setSelectedPluginView(resp.getBinding());
        } else {
            System.err.println("Unexpected Context:  What's the selected item?"); //$NON-NLS-1$
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
        UCMmap map = (UCMmap) item.getData(); // Get the selected map
        PluginBinding selectedPlugin;

        // If the item is checked
        if (item.getChecked()) {
            // If the stub is dynamic
            if (stub.isDynamic()) {
                // Add a plugin to the dynamic stub
                AddPluginCommand plugin = new AddPluginCommand(stub, map);
                execute(plugin);

                // Refresh the add bindings view with the correct plugin (the
                // last one added).
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
                    // Here the static stub don't have any plugin yet, so add a
                    // new one.
                    AddPluginCommand plugin = new AddPluginCommand(stub, map);
                    execute(plugin);

                    // Refresh the add bindings view with the correct plugin
                    selectedPlugin = (PluginBinding) stub.getBindings().get(0);
                    setSelectedPluginView(selectedPlugin);
                }
            }
        } else { // The item is unchecked.
            PluginBinding plugin = null;
            boolean finished = false;
            // Loop through all PluginBindings to find the one wich plug to the
            // selected unchecked map.
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

            // Refresh the add bindings view with nothing since the plugin was
            // deleted.
            setSelectedPluginView(null);
        }
    }

    /**
     * Recursively reset the background color for all items.
     * 
     */
    protected void resetTreeItemsBackground() {
        Display d = treeBindings.getDisplay();

        treeBindings.setRedraw(false);
        setTreeItemBackground(treeBindings.getItem(0), null, null);
        treeBindings.setRedraw(true);
    }

    /**
     * Change UI to indicate this TreeItem is selected.
     * 
     * @param item
     */
    protected void selectTreeItem(TreeItem item) {
        Display display = item.getDisplay();

        item.setBackground(display.getSystemColor(SWT.COLOR_LIST_SELECTION));
        item.setForeground(display.getSystemColor(SWT.COLOR_LIST_SELECTION_TEXT));
    }

    /**
     * Recursively set the background color on a tree node from a root node.
     * 
     * @param root
     * @param color
     */
    protected void setTreeItemBackground(TreeItem root, Color backColor, Color frontColor) {
        if (root == null)
            return;

        root.setBackground(backColor);
        root.setForeground(frontColor);

        for (int i = 0; i < root.getItemCount(); i++) {
            TreeItem item = root.getItem(i);

            setTreeItemBackground(item, backColor, frontColor);
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

                resetTreeItemsBackground();

                selectTreeItem(source);

                // Refresh the add bindings view with the correct plugin.
                setSelectedPluginView((PluginBinding) data);
            } else if (data instanceof InBinding || data instanceof OutBinding) {
                selectedItem = data;

                resetTreeItemsBackground();

                selectTreeItem(source.getParentItem().getParentItem());

                // show the binding
                setSelectedPluginView((PluginBinding) source.getParentItem().getParentItem().getData());
            } else if (data instanceof ComponentBinding) {
                selectedItem = data;
                resetTreeItemsBackground();

                selectTreeItem(source.getParentItem().getParentItem());

                setSelectedPluginView(((ComponentBinding) data).getBinding());
            } else if (data instanceof ResponsibilityBinding) {
                selectedItem = data;

                resetTreeItemsBackground();

                selectTreeItem(source.getParentItem().getParentItem());

                setSelectedPluginView(((ResponsibilityBinding) data).getBinding());
            } else {
                setSelectedPluginView(null); // Erase the binding view
                btDelete.setEnabled(false);
                return;
            }
            btDelete.setEnabled(true);
        } else {
            if (source.getParentItem() != null) {
                selectedItem = (PluginBinding) source.getParentItem().getData();

                resetTreeItemsBackground();

                selectTreeItem(source.getParentItem());

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
        try {
            for (int i = executedCount; i > 0; i--) {
                getCommandStack().undo();
            }
        } catch (EmptyStackException ex) {
            // bug 387: because our add map command is only undoable if no other
            // add/delete map actions
            // were done, we cannot cancel the stub view if we add a map and
            // create a binding.

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

        if (!descrip.getText().equals(stub.getDescription()) && !(stub.getDescription() == null && descrip.getText().length() == 0)) {
            ChangeDescriptionCommand command = new ChangeDescriptionCommand(stub, descrip.getText());
            execute(command, false);

        }
        dispose();
        super.okPressed();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.dialogs.Dialog#createButtonsForButtonBar(org.eclipse.swt.widgets.Composite)
     */
    protected void createButtonsForButtonBar(Composite parent) {
        parent.getParent().setBackground(ColorManager.WHITE);
        parent.setBackground(ColorManager.WHITE);
        okButton = createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
        createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.window.Window#getInitialSize()
     */
    protected Point getInitialSize() {
        return new Point(1080, 810);
    }

    /**
     * Take a command and execute it in the command stack of the editor. Refreshes the map list and bindings tree.
     * 
     * @param command
     *            The command we want to execute.
     */
    protected void execute(Command command) {
        execute(command, true);
    }

    /**
     * Take a command and execute it in the command stack of the editor.
     * 
     * @param command
     *            The command we want to execute.
     * @param refresh
     *            should the map list and bindings tree be refreshed?
     */
    protected void execute(Command command, boolean refresh) {
        if (command == null || !command.canExecute())
            return;

        // avoid duplicate entries for plugin binding conditions
        if (getCommandStack().getUndoCommand() instanceof ChangeLabelNameCommand && command instanceof ChangeLabelNameCommand) {
            ChangeLabelNameCommand changeLabel1 = (ChangeLabelNameCommand) getCommandStack().getUndoCommand();
            ChangeLabelNameCommand changeLabel2 = (ChangeLabelNameCommand) command;
            if (changeLabel1.getRenamedLabel().equals(changeLabel2.getRenamedLabel())) {
                getCommandStack().undo();

                changeLabel1.setName(changeLabel2.getName());
                changeLabel1.setDescription(changeLabel2.getDescription());
                changeLabel1.setExpression(changeLabel2.getExpression());

                getCommandStack().redo();
                return;
            }
        }

        /*
         * TODO: seek to enhance the kludge by looking further back into the command stack (instead of stopping at the very last command)
         */
        // avoid duplicate entries for plugin binding probability
        if (getCommandStack().getUndoCommand() instanceof ChangePluginBindingProbCommand && command instanceof ChangePluginBindingProbCommand) {
            ChangePluginBindingProbCommand changeProbability1 = (ChangePluginBindingProbCommand) getCommandStack().getUndoCommand();
            ChangePluginBindingProbCommand changeProbability2 = (ChangePluginBindingProbCommand) command;
            if (changeProbability1.getPluginBinding() == changeProbability2.getPluginBinding()) {
                getCommandStack().undo();
                changeProbability1.setProbability(changeProbability2.getProbability());
                getCommandStack().redo();
                return;
            }
        }

        /*
         * TODO: seek to enhance the kludge by looking further back into the command stack (instead of stopping at the very last command)
         */
        // avoid duplicate entries for plugin binding transaction
        if (getCommandStack().getUndoCommand() instanceof ChangePluginBindingTransCommand && command instanceof ChangePluginBindingTransCommand) {
            ChangePluginBindingTransCommand changeTransaction1 = (ChangePluginBindingTransCommand) getCommandStack().getUndoCommand();
            ChangePluginBindingTransCommand changeTransaction2 = (ChangePluginBindingTransCommand) command;
            if (changeTransaction1.getPluginBinding() == changeTransaction2.getPluginBinding()) {
                getCommandStack().undo();
                changeTransaction1.setTransaction(changeTransaction2.getTransaction());
                getCommandStack().redo();
                return;
            }
        }

        // avoid duplicate entries for description
        // This only happens
        // if (getCommandStack().getUndoCommand() instanceof ChangeDescriptionCommand && command instanceof ChangeDescriptionCommand) {
        // ChangeDescriptionCommand changeDesc1 = (ChangeDescriptionCommand) getCommandStack().getUndoCommand();
        // ChangeDescriptionCommand changeDesc2 = (ChangeDescriptionCommand) command;
        // if (changeDesc1.getElement().equals(changeDesc2.getElement())) {
        // getCommandStack().undo();
        //
        // changeDesc1.setNewDescription(changeDesc2.getNewDescription());
        //
        // getCommandStack().redo();
        // return;
        // }
        // }
        getCommandStack().execute(command); // Execute the command

        // Reset totalExecuted to executedCount
        executedCount++;
        totalExecuted = executedCount;

        // Only undo should be enabled.
        btUndo.setEnabled(true);
        btRedo.setEnabled(false);

        if (refresh) {
            // Refresh
            refreshMapList();
            refreshBindingsTree();
        }
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

        setSelectedPluginView(null); // Since we don't know if the currently
        // selected plugin is still there,
        // update it with null
    }

    protected void redo() {
        getCommandStack().redo(); // Redo the last command

        executedCount++;
        if (executedCount == totalExecuted) // If we arrived to the maximum of
            // our redo stack, disable the
            // button.
            btRedo.setEnabled(false);

        // We should always be able to undo.
        btUndo.setEnabled(true);

        refreshMapList();
        refreshBindingsTree();

        setSelectedPluginView(null); // Since we don't know if the currently
        // selected plugin is still there,
        // update it with null
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
            // Check that the selected Stub is not dynamic
            if (!stub.isDynamic()) {
                // Set the binding of the OutBinding to to first one in the list
                // of the plugin.
                plug = (PluginBinding) stub.getBindings().get(0);
            } else {
                plug = (PluginBinding) selectedPluginLabel.getData();
            }

            // Get the selected EndPoint and NodeConnection in the map and stub
            // out table.
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
        // Both list have something selected. And the stub has to have at least
        // one plugin.
        if (tabStubIns.getSelectionCount() >= 1 && tabMapIns.getSelectionCount() >= 1 && stub.getBindings().size() > 0) {
            PluginBinding plug = null;
            // Check that the selected Stub is not dynamic
            if (!stub.isDynamic()) {
                // Set the binding of the OutBinding to to first one in the list
                // of the plugin.
                plug = (PluginBinding) stub.getBindings().get(0);
            } else {
                plug = (PluginBinding) selectedPluginLabel.getData();
            }
            // Get the selected StartPoint and NodeConnection in the map and
            // stub in table.
            StartPoint start = (StartPoint) inMapList.get(tabMapIns.getSelectionIndex());
            NodeConnection con = (NodeConnection) inStubList.get(tabStubIns.getSelectionIndex());

            AddInBindingCommand in = new AddInBindingCommand(plug, start, con);
            execute(in);
            setSelectedPluginView(plug);

            btInBind.setEnabled(false);
        }
    }

    /**
     * Handle the click on the button to create a new InBinding.
     */
    protected void handleCompBindClick() {
        // Both list have something selected. And the stub has to have at least
        // one plugin.
        if (tabParentComps.getSelectionCount() >= 1 && tabPluginComps.getSelectionCount() >= 1 && stub.getBindings().size() > 0) {
            PluginBinding plug = null;
            // Check that the selected Stub is not dynamic
            if (!stub.isDynamic()) {
                // Set the binding of the OutBinding to to first one in the list
                // of the plugin.
                plug = (PluginBinding) stub.getBindings().get(0);
            } else {
                plug = (PluginBinding) selectedPluginLabel.getData();
            }
            // Get the selected StartPoint and NodeConnection in the map and
            // stub in table.
            ComponentRef parent = (ComponentRef) parentCompList.get(tabParentComps.getSelectionIndex());
            ComponentRef plugin = (ComponentRef) pluginCompList.get(tabPluginComps.getSelectionIndex());

            if (plugin.getContDef() instanceof Component) {
                Component def = ((Component) plugin.getContDef());
                if (!(def.isContext() && def.getKind().equals(ComponentKind.TEAM_LITERAL))) {
                    boolean answer = MessageDialog
                    .openQuestion(
                            getShell(),
                            Messages.getString("StubBindingsDialog.InvalidPluginComponent"), Messages.getString("StubBindingsDialog.InvalidPluginComponentText")); //$NON-NLS-1$ //$NON-NLS-2$
                    if (!answer)
                        return;
                }

            }
            AddComponentBindingCommand in = new AddComponentBindingCommand(plug, parent, plugin);
            execute(in);
            setSelectedPluginView(plug);

            btCompBind.setEnabled(false);
        }
    }

    /**
     * Handles a click on the bind button for responsibility bindings
     */
    protected void handleRespBindClick() {
        if (tabParentResps.getSelectionCount() >= 1 && tabPluginResps.getSelectionCount() >= 1 && stub.getBindings().size() > 0) {
            PluginBinding plug = null;
            // Check that the selected Stub is not dynamic
            if (!stub.isDynamic()) {
                // Set the binding of the OutBinding to to first one in the list
                // of the plugin.
                plug = (PluginBinding) stub.getBindings().get(0);
            } else {
                plug = (PluginBinding) selectedPluginLabel.getData();
            }
            // Get the selected StartPoint and NodeConnection in the map and
            // stub in table.
            Responsibility parent = (Responsibility) parentRespList.get(tabParentResps.getSelectionIndex());
            RespRef plugin = (RespRef) pluginRespList.get(tabPluginResps.getSelectionIndex());

            if (plugin.getRespDef() instanceof Responsibility) {
                Responsibility def = ((Responsibility) plugin.getRespDef());
                if (!(def.isContext())) {
                    boolean answer = MessageDialog
                    .openQuestion(
                            getShell(),
                            Messages.getString("StubBindingsDialog.InvalidPluginResp"), Messages.getString("StubBindingsDialog.InvalidPluginRespText")); //$NON-NLS-1$ //$NON-NLS-2$
                    if (!answer)
                        return;
                }

            }
            AddRespRefBindingCommand in = new AddRespRefBindingCommand(plug, parent, plugin);
            execute(in);

            setSelectedPluginView(plug);

            btRespBind.setEnabled(false);
        }
    }

    /**
     * This method updates the width of columns of tables to make them as wide as possible.
     */
    protected void updateColumnWidth() {
        final int widthDif = 10;

        mapInsColumn.setWidth(tabMapIns.getSize().x - widthDif);
        stubInsColumn.setWidth(tabStubIns.getSize().x - widthDif);
        mapOutsColumn.setWidth(tabMapOuts.getSize().x - widthDif);
        stubOutsColumn.setWidth(tabStubOuts.getSize().x - widthDif);
        tabMapListColumn.setWidth(tabMapList.getSize().x - widthDif);
        compParentColumn.setWidth(tabParentComps.getSize().x - widthDif);
        compPluginColumn.setWidth(tabPluginComps.getSize().x - widthDif);
        respParentColumn.setWidth(tabParentResps.getSize().x - widthDif);
        respPluginColumn.setWidth(tabPluginResps.getSize().x - widthDif);
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
                urnSpec = stub.getDiagram().getUrndefinition().getUrnspec();

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
        Image image = (JUCMNavPlugin.getImage("icons/Binding16.gif")); //$NON-NLS-1$
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
        // for (Iterator i = images.iterator(); i.hasNext();) {
        // Image image = (Image) i.next();
        // image.dispose();
        // }
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
        List mapsList = stub.getDiagram().getUrndefinition().getSpecDiagrams();

        // Array wich will contain all binded maps to this stub
        ArrayList binded = new ArrayList();

        List plugins = stub.getBindings();
        // Loop through all the pluginbindings and find the maps wich are binded
        // to this map.
        for (Iterator i = plugins.iterator(); i.hasNext();) {
            PluginBinding plugin = (PluginBinding) i.next();
            binded.add(plugin.getPlugin());
        }

        TableItem item;
        // For each map
        for (Iterator i = mapsList.iterator(); i.hasNext();) {
            IURNDiagram g = (IURNDiagram) i.next();
            if (g instanceof UCMmap) {
                UCMmap map = (UCMmap) g;
                // The map can't be the same as the one the stub in contained
                // in.
                if (map != stub.getDiagram()) {
                    item = new TableItem(tabMapList, SWT.NONE);
                    item.setText(map.getName() + Messages.getString("StubBindingsDialog.CommaIDColon") + map.getId()); //$NON-NLS-1$
                    item.setData(map);
                    // If the map is binded to this stub, check the item.
                    if (binded.contains(map)) {
                        item.setChecked(true);
                    }
                }
            }
        }
    }

    /**
     * Refresh with the correct info the tree of the bindings of this stub. The tree will build itself with the following structure: <br>
     * Bindings <br>
     * |-+ Stub <->Map <br>
     * | |-+ In bindings <br>
     * | | |-+ StartPoint <->EmptyPoint <br>
     * | |-+ Out bindings <br>
     * | | |-+ EndPoint <->EmptyPoint <br>
     */
    private void refreshBindingsTree() {
        treeBindings.removeAll();

        List list = stub.getBindings();
        TreeItem root = new TreeItem(treeBindings, SWT.NULL);
        root.setText(Messages.getString("StubBindingsDialog.bindings")); //$NON-NLS-1$
        TreeItem item; // This represents a PluginBinding
        TreeItem subLabelItem; // An item for a label under item. This item
        // cannot be deleted/selected.
        TreeItem subItem; // This represent a In/OutBinding

        Image image;

        // Loop through all the PluginBindings
        for (Iterator i = list.iterator(); i.hasNext();) {
            item = root;
            PluginBinding binding = (PluginBinding) i.next();
            // Generate a tree item under root for this PluginBinding
            item = new TreeItem(item, SWT.NULL);
            item.setText(binding.getStub().getName()
                    + " <-> " + binding.getPlugin().getName() + Messages.getString("StubBindingsDialog.CommaIDColon") + binding.getPlugin().getId()); //$NON-NLS-1$ //$NON-NLS-2$
            image = (JUCMNavPlugin.getImage("icons/Binding16.gif")); //$NON-NLS-1$
            images.add(image);
            item.setImage(image);
            item.setData(binding);

            // Then add a label for InBindings under this item
            List in = binding.getIn();

            if (in.size() > 0) {
                subLabelItem = new TreeItem(item, SWT.NULL);
                subLabelItem.setText(Messages.getString("StubBindingsDialog.inBindings")); //$NON-NLS-1$
                image = (JUCMNavPlugin.getImage("icons/inBinding16.gif")); //$NON-NLS-1$
                images.add(image);
                subLabelItem.setImage(image);

                // Loop through all the InBindings and add them under the InBinding
                // label
                for (Iterator j = in.iterator(); j.hasNext();) {
                    InBinding inBind = (InBinding) j.next();
                    subItem = new TreeItem(subLabelItem, SWT.NULL);
                    subItem.setText("IN" + (stub.getPred().indexOf(inBind.getStubEntry()) + 1) + " <-> " + inBind.getStartPoint().getName()); //$NON-NLS-1$ //$NON-NLS-2$
                    image = (JUCMNavPlugin.getImage("icons/inBinding16.gif")); //$NON-NLS-1$
                    images.add(image);
                    subItem.setImage(image);
                    subItem.setData(inBind);
                }
                subLabelItem.setExpanded(true); // We want everything expanded by default.
            }

            List out = binding.getOut();
            if (out.size() > 0) {
                // The add the label for OutBindings under the PluginBinding item
                subLabelItem = new TreeItem(item, SWT.NULL);
                subLabelItem.setText(Messages.getString("StubBindingsDialog.outBindings")); //$NON-NLS-1$
                image = (JUCMNavPlugin.getImage("icons/outBinding16.gif")); //$NON-NLS-1$
                images.add(image);
                subLabelItem.setImage(image);

                // Loop through all the OutBindings and add them under the
                // OutBinding label
                for (Iterator j = out.iterator(); j.hasNext();) {
                    OutBinding outBind = (OutBinding) j.next();
                    subItem = new TreeItem(subLabelItem, SWT.NULL);
                    subItem.setText("OUT" + (stub.getSucc().indexOf(outBind.getStubExit()) + 1) + " <-> " + outBind.getEndPoint().getName()); //$NON-NLS-1$ //$NON-NLS-2$
                    image = (JUCMNavPlugin.getImage("icons/outBinding16.gif")); //$NON-NLS-1$
                    images.add(image);
                    subItem.setImage(image);
                    subItem.setData(outBind);
                }
                // We want everything expanded by default.
                subLabelItem.setExpanded(true);
            }

            List comps = binding.getComponents();

            if (comps.size() > 0) {
                // The add the label for Component Bindings under the PluginBinding item
                subLabelItem = new TreeItem(item, SWT.NULL);
                subLabelItem.setText(Messages.getString("StubBindingsDialog.ComponentBindings")); //$NON-NLS-1$
                image = (JUCMNavPlugin.getImage("icons/componentBinding16.gif")); //$NON-NLS-1$
                images.add(image);
                subLabelItem.setImage(image);

                // Loop through all the Component Bindings and add them under the
                // Component Bindings label

                for (Iterator j = comps.iterator(); j.hasNext();) {
                    ComponentBinding compBind = (ComponentBinding) j.next();
                    subItem = new TreeItem(subLabelItem, SWT.NULL);
                    subItem.setText(URNNamingHelper.getName(compBind.getParentComponent()) + " <-> " + URNNamingHelper.getName(compBind.getPluginComponent())); //$NON-NLS-1$ 
                    image = (JUCMNavPlugin.getImage("icons/componentBinding16.gif")); //$NON-NLS-1$
                    images.add(image);
                    subItem.setImage(image);
                    subItem.setData(compBind);
                }
                // We want everything expanded by default.
                subLabelItem.setExpanded(true);
            }

            List refs = binding.getResponsibilities();

            if (refs.size() > 0) {
                // The add the label for Component Bindings under the PluginBinding item
                subLabelItem = new TreeItem(item, SWT.NULL);
                subLabelItem.setText(Messages.getString("StubBindingsDialog.respBindings")); //$NON-NLS-1$
                image = (JUCMNavPlugin.getImage("icons/RespBinding16.gif")); //$NON-NLS-1$
                images.add(image);
                subLabelItem.setImage(image);

                // Loop through all the Component Bindings and add them under the
                // Component Bindings label

                for (Iterator j = refs.iterator(); j.hasNext();) {
                    ResponsibilityBinding refBind = (ResponsibilityBinding) j.next();
                    subItem = new TreeItem(subLabelItem, SWT.NULL);
                    subItem.setText(URNNamingHelper.getName(refBind.getParentResp()) + " <-> " + URNNamingHelper.getName(refBind.getPluginResp())); //$NON-NLS-1$ 
                    image = (JUCMNavPlugin.getImage("icons/RespBinding16.gif")); //$NON-NLS-1$
                    images.add(image);
                    subItem.setImage(image);
                    subItem.setData(refBind);
                }
                // We want everything expanded by default.
                subLabelItem.setExpanded(true);
            }

            item.setExpanded(true);
        }
        root.setExpanded(true);
    }

    // The list of all the incoming connections from outside to the stub
    private ArrayList inStubList;
    // The list of all the StartPoints of the binded map.
    private ArrayList<StartPoint> inMapList;
    // The list of all the outgoing connections from the stub to the outside.
    private ArrayList outStubList;
    // The list of all the EndPoints of the binded map.
    private ArrayList<EndPoint> outMapList;
    // The list of all the parent components of the binded map
    private ArrayList<ComponentRef> parentCompList;
    // The list of all the plug-in copmonents of the binded map
    private ArrayList<ComponentRef> pluginCompList;
    // The list of all the parent components of the binded map
    private ArrayList<Responsibility> parentRespList;
    // The list of all the plug-in responsibility of the binded map
    private ArrayList<RespRef> pluginRespList;

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
        tabParentComps.removeAll();
        tabPluginComps.removeAll();
        tabParentResps.removeAll();
        tabPluginResps.removeAll();

        // Clear the arrays
        inStubList = new ArrayList();
        inMapList = new ArrayList();
        outStubList = new ArrayList();
        outMapList = new ArrayList();
        parentCompList = new ArrayList();
        pluginCompList = new ArrayList();
        parentRespList = new ArrayList();
        pluginRespList = new ArrayList();

        // If the user selected a plugin
        if (selectedPlugin != null) {
            // Update the label
            selectedPluginLabel
            .setText(selectedPlugin.getStub().getName()
                    + " <-> " + selectedPlugin.getPlugin().getName() + Messages.getString("StubBindingsDialog.CommaIDColon") + selectedPlugin.getPlugin().getId()); //$NON-NLS-1$ //$NON-NLS-2$
            selectedPluginLabel.setFont(new Font(null, new FontData("", 8, SWT.BOLD))); //$NON-NLS-1$
            selectedPluginLabel.setData(selectedPlugin);
            addPluginClient.setVisible(true);

            // Initialize those images to add to the table items.
            Image start = (JUCMNavPlugin.getImage("icons/Start16.gif")); //$NON-NLS-1$
            Image end = (JUCMNavPlugin.getImage("icons/End16.gif")); //$NON-NLS-1$
            Image in = (JUCMNavPlugin.getImage("icons/inBinding16.gif")); //$NON-NLS-1$
            Image out = (JUCMNavPlugin.getImage("icons/outBinding16.gif")); //$NON-NLS-1$
            Image comp = (JUCMNavPlugin.getImage("icons/Component16.gif")); //$NON-NLS-1$
            Image resp = (JUCMNavPlugin.getImage("icons/Resp16.gif")); //$NON-NLS-1$
            images.add(in);
            images.add(out);
            images.add(start);
            images.add(end);
            images.add(comp);
            images.add(resp);

            // Get the list of all the incoming connections from outside to the
            // stub to fill the 'in' list.
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

            list = selectedPlugin.getPlugin().getNodes();
            // Loop through all the nodes of the map and add the start and end
            // points to the lists.
            for (Iterator i = list.iterator(); i.hasNext();) {
                PathNode node = (PathNode) i.next();
                // The node is a start point
                if (node instanceof StartPoint) {
                    if (!isStartPointInBinded((StartPoint) node, selectedPlugin)) {
                        inMapList.add((StartPoint) node);
                        item = new TableItem(tabMapIns, SWT.NULL);
                        item.setText(node.getName());
                        item.setImage(start);
                    }
                    // The node is an EndPoint
                } else if (node instanceof EndPoint) {
                    if (!isEndPointOutBinded((EndPoint) node, selectedPlugin)) {
                        outMapList.add((EndPoint) node);
                        item = new TableItem(tabMapOuts, SWT.NULL);
                        item.setText(node.getName());
                        item.setImage(end);
                    }
                }
            }

            list = ((UCMmap) stub.getDiagram()).getContRefs();
            for (Iterator iterator = list.iterator(); iterator.hasNext();) {
                ComponentRef c = (ComponentRef) iterator.next();
                parentCompList.add(c);
                item = new TableItem(tabParentComps, SWT.NULL);
                item.setText(URNNamingHelper.getName(c));
                item.setImage(comp);
            }
            list = selectedPlugin.getPlugin().getContRefs();
            for (Iterator iterator = list.iterator(); iterator.hasNext();) {
                ComponentRef c = (ComponentRef) iterator.next();
                if (c.getPluginBindings().size() == 0) {
                    pluginCompList.add(c);
                    item = new TableItem(tabPluginComps, SWT.NULL);
                    item.setText(URNNamingHelper.getName(c));
                    item.setImage(comp);
                }
            }

            list = urnSpec.getUrndef().getResponsibilities();
            for (Iterator iterator = list.iterator(); iterator.hasNext();) {
                Responsibility r = (Responsibility) iterator.next();
                parentRespList.add(r);
                item = new TableItem(tabParentResps, SWT.NULL);
                item.setText(URNNamingHelper.getName(r));
                item.setImage(resp);
            }

            list = selectedPlugin.getPlugin().getNodes();
            for (Iterator iterator = list.iterator(); iterator.hasNext();) {
                Object c = (Object) iterator.next();
                if (c instanceof RespRef) {
                    RespRef r = (RespRef) c;
                    if (r.getPluginBindings().size() == 0) {
                        pluginRespList.add(r);
                        item = new TableItem(tabPluginResps, SWT.NULL);
                        item.setText(URNNamingHelper.getName(r));
                        item.setImage(resp);
                    }
                }
            }

            refreshCondition();
            refreshTransaction();
            refreshProbability();
            refreshReplicationFactor();
        } else {
            selectedPluginLabel.setText(Messages.getString("StubBindingsDialog.noPluginSelected")); //$NON-NLS-1$
            selectedPluginLabel.setFont(new Font(null, new FontData("", 8, SWT.BOLD))); //$NON-NLS-1$
            addPluginClient.setVisible(false);
        }
    }

    /**
     * @param con
     *            the connection to check
     * @param plugin
     *            the binding in which to check
     * @return true if the connection is bound
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
     * @param end
     *            the point to check
     * @param plugin
     *            the binding in which to check
     * @return true if the end point is bound
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
        // Always say no so that it becomes re-usable.
        // if (stub.getBindings().size() > 0) {
        // List ins = plugin.getIn();
        // for (Iterator i = ins.iterator(); i.hasNext();) {
        // InBinding in = (InBinding) i.next();
        // if (in.getStartPoint() == start)
        // return true;
        // }
        // }
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
            if (stub.getDescription() != null)
                descrip.setText(stub.getDescription());
            else
                descrip.setText(""); //$NON-NLS-1$
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