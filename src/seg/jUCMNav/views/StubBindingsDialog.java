package seg.jUCMNav.views;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseTrackAdapter;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.graphics.Color;
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
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ImageHyperlink;
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
public class StubBindingsDialog extends Dialog implements ISelectionListener, Adapter {
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
	private Section addPluginSection; // Here the in/out of the stub and map are listed to make In/OutBindings

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

	private Table mapList;
	private TableColumn mapListColumn;

	private ImageHyperlink btRedo;
	private ImageHyperlink btUndo;
	private ImageHyperlink btDeletePlugin;

	private int executedCount = 0;
	private int totalExecuted;

	public StubBindingsDialog(Shell parentShell, CommandStack cmdStack) {
		super(parentShell);
		this.cmdStack = cmdStack;
		setShellStyle(SWT.SHELL_TRIM | SWT.APPLICATION_MODAL);
	}

	protected Control createDialogArea(Composite parent) {
		Composite area = (Composite) super.createDialogArea(parent);
		GridLayout l = (GridLayout) area.getLayout();
		l.marginWidth = 0;
		l.marginHeight = 0;
		GridData d = new GridData(GridData.FILL_BOTH);
		d.grabExcessHorizontalSpace = true;
		d.grabExcessVerticalSpace = true;
		area.setLayoutData(d);

		toolkit = new FormToolkit(area.getDisplay());
		form = toolkit.createScrolledForm(area);
		form.setLayoutData(d);
		form.setText(Messages.getString("StubBindingsDialog.stubBindings")); //$NON-NLS-1$
		TableWrapLayout layout = new TableWrapLayout();
		layout.numColumns = 3;
		form.getBody().setLayout(layout);
		TableWrapData td = new TableWrapData();

		final ExpandableComposite ec = toolkit.createExpandableComposite(form.getBody(), ExpandableComposite.TWISTIE | ExpandableComposite.EXPANDED);
		ec.setText(Messages.getString("StubBindingsDialog.stubDesc")); //$NON-NLS-1$
		descrip = toolkit.createLabel(ec, "", SWT.WRAP); //$NON-NLS-1$
		ec.setClient(descrip);
		td = new TableWrapData();
		td.colspan = 3;
		ec.setLayoutData(td);

		//		 Connect map section
		mapSection = toolkit.createSection(form.getBody(), Section.TWISTIE | Section.TITLE_BAR);
		mapSection.setText(Messages.getString("StubBindingsDialog.selectPluginMaps")); //$NON-NLS-1$
		td = new TableWrapData(TableWrapData.FILL);
		td.colspan = 1;
		td.grabHorizontal = true;
		td.grabVertical = true;
		mapSection.setLayoutData(td);
		td.align = TableWrapData.FILL;
		toolkit.createCompositeSeparator(mapSection);

		Composite mapClient = toolkit.createComposite(mapSection);
		GridLayout grid = new GridLayout();
		grid.numColumns = 1;
		mapClient.setLayout(grid);

		mapList = toolkit.createTable(mapClient, SWT.SINGLE | SWT.FULL_SELECTION | SWT.CHECK);
		mapList.setLinesVisible(true);
		mapList.setHeaderVisible(true);
		GridData g = new GridData(GridData.FILL_BOTH);
		g.grabExcessHorizontalSpace = true;
		g.grabExcessVerticalSpace = true;
		mapList.setLayoutData(g);
		mapListColumn = new TableColumn(mapList, SWT.NONE);
		mapListColumn.setWidth(150);
		mapListColumn.setText(Messages.getString("StubBindingsDialog.maps")); //$NON-NLS-1$

		mapList.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if(e.detail == SWT.CHECK)
					handlePluginSelected((TableItem) e.item);
			}
		});

		mapSection.setClient(mapClient);

		//		Composite listAddComp = new Composite(form.getBody(), SWT.NULL);

		// Plugin List section
		pluginListSection = toolkit.createSection(form.getBody(), Section.TWISTIE | Section.TITLE_BAR);
		td = new TableWrapData(TableWrapData.FILL);
		td.colspan = 1;
		td.grabVertical = true;
		pluginListSection.setLayoutData(td);
		pluginListSection.setText(Messages.getString("StubBindingsDialog.pluginList")); //$NON-NLS-1$
		toolkit.createCompositeSeparator(pluginListSection);
		Composite sectionClient = toolkit.createComposite(pluginListSection);
		grid = new GridLayout();
		grid.numColumns = 1;
		grid.makeColumnsEqualWidth = false;
		sectionClient.setLayout(grid);

		Composite treeButtons = toolkit.createComposite(sectionClient, SWT.NONE);
		grid = new GridLayout();
		grid.numColumns = 3;
		treeButtons.setLayout(grid);
		GridData t = new GridData(GridData.HORIZONTAL_ALIGN_END);
		t.grabExcessHorizontalSpace = true;
		treeButtons.setLayoutData(t);

		btDeletePlugin = toolkit.createImageHyperlink(treeButtons, SWT.NONE);
		btDeletePlugin.setVisible(false);
		Image image = WorkbenchImages.getImageDescriptor(ISharedImages.IMG_TOOL_DELETE).createImage();
		images.add(image);
		btDeletePlugin.setImage(image);
		btDeletePlugin.addMouseTrackListener(new MouseTrackAdapter() {
			public void mouseEnter(MouseEvent e) {
				btDeletePlugin.setBackground(new Color(null, 225, 225, 225));
			}

			public void mouseExit(MouseEvent e) {
				btDeletePlugin.setBackground(new Color(null, 255, 255, 255));
			}
		});
		t = new GridData(GridData.FILL_HORIZONTAL);
		t.grabExcessHorizontalSpace = false;
		btDeletePlugin.setLayoutData(t);

		btUndo = toolkit.createImageHyperlink(treeButtons, SWT.NONE);
		image = WorkbenchImages.getImageDescriptor(ISharedImages.IMG_TOOL_UNDO).createImage();
		images.add(image);
		btUndo.setImage(image);
		btUndo.addMouseTrackListener(new MouseTrackAdapter() {
			public void mouseEnter(MouseEvent e) {
				btUndo.setBackground(new Color(null, 225, 225, 225));
			}

			public void mouseExit(MouseEvent e) {
				btUndo.setBackground(new Color(null, 255, 255, 255));
			}
		});
		btUndo.addMouseListener(new MouseAdapter() {
			public void mouseUp(MouseEvent e) {
				undo();
			}
		});
		t = new GridData(GridData.FILL_HORIZONTAL);
		t.grabExcessHorizontalSpace = false;
		btUndo.setLayoutData(t);

		btUndo.setVisible(false);

		btRedo = toolkit.createImageHyperlink(treeButtons, SWT.NONE);
		image = WorkbenchImages.getImageDescriptor(ISharedImages.IMG_TOOL_REDO).createImage();
		images.add(image);
		btRedo.setImage(image);
		btRedo.addMouseTrackListener(new MouseTrackAdapter() {
			public void mouseEnter(MouseEvent e) {
				btRedo.setBackground(new Color(null, 225, 225, 225));
			}

			public void mouseExit(MouseEvent e) {
				btRedo.setBackground(new Color(null, 255, 255, 255));
			}
		});
		btRedo.addMouseListener(new MouseAdapter() {
			public void mouseUp(MouseEvent e) {
				redo();
			}
		});
		t = new GridData(GridData.FILL_HORIZONTAL);
		t.grabExcessHorizontalSpace = false;
		btRedo.setLayoutData(t);

		btRedo.setVisible(false);

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

		// Add Bindings section
		addPluginSection = toolkit.createSection(form.getBody(), Section.TWISTIE | Section.TITLE_BAR);
		addPluginSection.setText(Messages.getString("StubBindingsDialog.addPlugins")); //$NON-NLS-1$
		td = new TableWrapData(TableWrapData.FILL);
		td.colspan = 1;
		td.grabHorizontal = true;
		td.grabVertical = true;
		addPluginSection.setLayoutData(td);
		td.align = TableWrapData.FILL;

		toolkit.createCompositeSeparator(addPluginSection);

		Composite addPluginClient = toolkit.createComposite(addPluginSection);
		grid = new GridLayout();
		grid.numColumns = 3;
		grid.makeColumnsEqualWidth = false;
		addPluginClient.setLayout(grid);

		addPluginSection.setClient(addPluginClient);

		// Stub composite for creating new bindings.
		Composite stubComp = toolkit.createComposite(addPluginClient);
		grid = new GridLayout();
		grid.numColumns = 1;
		stubComp.setLayout(grid);
		GridData f = new GridData(GridData.FILL_BOTH);
		f.grabExcessHorizontalSpace = true;
		f.grabExcessVerticalSpace = true;
		stubComp.setLayoutData(f);

		Label lb = toolkit.createLabel(stubComp, Messages.getString("StubBindingsDialog.stub")); //$NON-NLS-1$

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
		mapInsColumn.setText(Messages.getString("StubBindingsDialog.in")); //$NON-NLS-1$

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
		mapOutsColumn.setText(Messages.getString("StubBindingsDialog.out")); //$NON-NLS-1$

		mapSection.setExpanded(true);
		pluginListSection.setExpanded(true);
		addPluginSection.setExpanded(true);

		return area;
	}

	/**
	 *  
	 */
	protected void handlePluginSelected(TableItem item) {
		Map map = (Map) item.getData();
		
		// If the item is checked
		if(item.getChecked()){	
			if (stub.isDynamic()) {
				AddPluginCommand plugin = new AddPluginCommand(stub, map);
				execute(plugin);
			} else {
				if (stub.getBindings().size() >= 1) {
					if (((PluginBinding) stub.getBindings().get(0)).getPlugin() != map) {
						ReplacePluginCommand plugin = new ReplacePluginCommand((PluginBinding) stub.getBindings().get(0), map);
						execute(plugin);
					}
				} else if (stub.getBindings().size() == 0) {
					AddPluginCommand plugin = new AddPluginCommand(stub, map);
					execute(plugin);
				}
			}
		}
		else {
			PluginBinding plugin = null;
			boolean finished = false;
			for (Iterator i = stub.getBindings().iterator(); i.hasNext() && !finished;) {
				PluginBinding plug = (PluginBinding) i.next();
				if(plug.getPlugin() == map) {
					finished = true;
					plugin = plug;
				}
			}
			DeletePluginCommand delete = new DeletePluginCommand(plugin);
			execute(delete);
		}
	}

	/**
	 * @param source
	 */
	protected void handleTreeBindingsSelected(TreeItem source) {
		Object data = source.getData();
		if (data != null) {
			if (data instanceof PluginBinding) {
				
			} else if (data instanceof InBinding) {
				System.out.println("InBinding"); //$NON-NLS-1$
			} else if (data instanceof OutBinding) {
				System.out.println("OutBinding"); //$NON-NLS-1$
			}
		}
	}

	protected void cancelPressed() {
		for (int i = executedCount; i > 0; i--) {
			getCommandStack().undo();
		}
		
		super.cancelPressed();
	}
	protected void createButtonsForButtonBar(Composite parent) {
		parent.getParent().setBackground(new Color(null, 255, 255, 255));
		parent.setBackground(new Color(null, 255, 255, 255));
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
		createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
	}

	protected Point getInitialSize() {
		return new Point(900, 500);
	}

	protected void execute(Command command) {
		if (command == null || !command.canExecute())
			return;
		getCommandStack().execute(command);

		executedCount++;
		totalExecuted = executedCount;

		btUndo.setVisible(true);
		btRedo.setVisible(false);

		refreshMapList();
		refreshBindingsTree();
		refreshInOutList();
	}

	protected void undo() {
		getCommandStack().undo();

		executedCount--;
		if (executedCount == 0)
			btUndo.setVisible(false);

		btRedo.setVisible(true);

		refreshMapList();
		refreshBindingsTree();
		refreshInOutList();
	}

	protected void redo() {
		getCommandStack().redo();

		executedCount++;
		if (executedCount == totalExecuted)
			btRedo.setVisible(false);

		refreshMapList();
		refreshBindingsTree();
		refreshInOutList();
	}

	/**
	 * @return
	 */
	private CommandStack getCommandStack() {
		return cmdStack;
	}

	/**
	 *  
	 */
	protected void handleOutBindClick() {
		if (tabStubOuts.getSelectionCount() >= 1 && tabMapOuts.getSelectionCount() >= 1 && stub.getBindings().size() > 0) {
			//			 Check that the selected Stub is not dynamic
			if (!stub.isDynamic()) {

				// Set the binding of the OutBinding to to first one in the list of the plugin.
				PluginBinding plug = (PluginBinding) stub.getBindings().get(0);

				// Get the selected EndPoint and NodeConnection in the map and stub out table.
				EndPoint end = (EndPoint) outMapList.get(tabMapOuts.getSelectionIndex());
				NodeConnection con = (NodeConnection) outStubList.get(tabStubOuts.getSelectionIndex());

				AddOutBindingCommand out = new AddOutBindingCommand(plug, end, con);
				execute(out);
			}
			btOutBind.setEnabled(false);
			if (!pluginListSection.isExpanded()) {
				pluginListSection.setExpanded(true);
				updateColumnWidth();
			}
		}
	}

	/**
	 * This method is called when the user click the in bind button.
	 */
	protected void handleInBindClick() {
		// Both list have something selected. And the stub has to have at least one plugin.
		if (tabStubIns.getSelectionCount() >= 1 && tabMapIns.getSelectionCount() >= 1 && stub.getBindings().size() > 0) {
			// Check that the selected Stub is not dynamic
			if (!stub.isDynamic()) {
				//				 Set the binding of the InBinding to to first one in the list of the plugin.
				PluginBinding plug = (PluginBinding) stub.getBindings().get(0);

				// Get the selected StartPoint and NodeConnection in the map and stub in table.
				StartPoint start = (StartPoint) inMapList.get(tabMapIns.getSelectionIndex());
				NodeConnection con = (NodeConnection) inStubList.get(tabStubIns.getSelectionIndex());

				AddInBindingCommand in = new AddInBindingCommand(plug, start, con);
				execute(in);
			}
			btInBind.setEnabled(false);
			if (!pluginListSection.isExpanded()) {
				pluginListSection.setExpanded(true);
				updateColumnWidth();
			}
		}
	}

	/**
	 * Ths method is called when the user selects a new map as a plugin to the Stub from the combobox.
	 */
	protected void handlePluginChanged(int selectedIndex) {
		//		Map map = (Map) mapsObjects.get(selectedIndex);

	}

	/**
	 * This method updates the width of columns of tables to make them as wide as possible.
	 */
	protected void updateColumnWidth() {
		mapInsColumn.setWidth(tabMapIns.getSize().x - 2);
		stubInsColumn.setWidth(tabStubIns.getSize().x - 2);
		mapOutsColumn.setWidth(tabMapOuts.getSize().x - 2);
		stubOutsColumn.setWidth(tabStubOuts.getSize().x - 2);
		mapListColumn.setWidth(mapList.getSize().x - 2);
	}

	/**
	 * We listen for the selection change of the workbench. When the selection is an EditPart and that the represented model is a Stub we can display the view.
	 * If we're not selecting a Stub EditPart, then just display nothing in the view.
	 * 
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.ISelectionListener#selectionChanged(org.eclipse.ui.IWorkbenchPart, org.eclipse.jface.viewers.ISelection)
	 */
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		IStructuredSelection selecteds = (IStructuredSelection) selection;
		List list = selecteds.toList();

		if (list.size() == 1) {
			if (list.get(0) instanceof EditPart) {
				EditPart selected = (EditPart) (list.get(0));
				if (selected.getModel() instanceof Stub) {
					setStub((Stub) selected.getModel());
					return;
				}
			}
		}
		setStub(null);
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
				refreshInOutList();

			} else {
				if (this.stub != null)
					this.stub.eAdapters().remove(this);
				this.stub = null;
				resetInfo();
			}
			form.reflow(true);
			updateColumnWidth();
		}
	}

	public int open(Stub stub) {
		create();
		getShell().setActive();
		getShell().setText(Messages.getString("StubBindingsDialog.stubBindings")); //$NON-NLS-1$
		Image image = (ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/Binding16.gif")).createImage(); //$NON-NLS-1$
		images.add(image);
		getShell().setImage(image);
		getShell().addShellListener(new ShellAdapter() {
			public void shellClosed(ShellEvent e) {
				for (Iterator i = images.iterator(); i.hasNext();) {
					Image image = (Image) i.next();
					image.dispose();
				}
			}
		});
		setStub(stub);
		updateColumnWidth();
//		form.reflow(true);
		return super.open();
	}

	/**
	 * Reset all the view information when the selected Stub is null.
	 */
	private void resetInfo() {
		descrip.setText(""); //$NON-NLS-1$
		treeBindings.removeAll();
		tabMapIns.removeAll();
		tabStubIns.removeAll();

		// Close the sections
		mapSection.setExpanded(false);
		pluginListSection.setExpanded(false);
		addPluginSection.setExpanded(false);
		//    	form.setText("No stub selected");
		//    	form.setForeground(new Color(null, 255, 0, 0));
		form.setVisible(false);
//		form.reflow(true);
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
		mapList.removeAll();
		List mapsList = stub.getPathGraph().getMap().getUcmspec().getMaps();

		ArrayList binded = new ArrayList();

		List plugins = stub.getBindings();
		for (Iterator i = plugins.iterator(); i.hasNext();) {
			PluginBinding plugin = (PluginBinding) i.next();
			binded.add(plugin.getPlugin());
		}

		TableItem item;
		for (Iterator i = mapsList.iterator(); i.hasNext();) {
			Map map = (Map) i.next();
			if (map != stub.getPathGraph().getMap()) {
				item = new TableItem(mapList, SWT.NONE);
				item.setText(map.getName());
				item.setData(map);
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
				subItem.setText(inBind.getStubEntry().getSource().getName() + "<->" + inBind.getStartPoint().getName()); //$NON-NLS-1$
				image = (ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/InBinding16.gif")).createImage(); //$NON-NLS-1$
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
				subItem.setText(outBind.getEndPoint().getName() + "<->" + outBind.getStubExit().getTarget().getName()); //$NON-NLS-1$
				image = (ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/OutBinding16.gif")).createImage(); //$NON-NLS-1$
				images.add(image);
				subItem.setImage(image);
				subItem.setData(outBind);
			}
			//			 We want everything expanded by default.
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
	 */
	protected void refreshInOutList() {
		tabMapIns.removeAll();
		tabStubIns.removeAll();
		tabMapOuts.removeAll();
		tabStubOuts.removeAll();

		inStubList = new ArrayList();
		inMapList = new ArrayList();
		outStubList = new ArrayList();
		outMapList = new ArrayList();

		// Get the list of all the incoming connections from outside to the stub to fill the 'in' list.
		List list = stub.getPred();
		TableItem item;
		for (Iterator i = list.iterator(); i.hasNext();) {
			NodeConnection con = (NodeConnection) i.next();
			if (!isNodeConnectionInBinded(con)) {
				inStubList.add(con);
				PathNode node = con.getSource();
				item = new TableItem(tabStubIns, SWT.NULL);
				item.setText(node.getName());
			}
		}

		list = stub.getSucc();
		for (Iterator i = list.iterator(); i.hasNext();) {
			NodeConnection con = (NodeConnection) i.next();
			if (!isNodeConnectionOutBinded(con)) {
				outStubList.add(con);
				PathNode node = con.getTarget();
				item = new TableItem(tabStubOuts, SWT.NULL);
				item.setText(node.getName());
			}
		}

		if (stub.getBindings().size() > 0) {
			// This code will only work for static stub right now.
			// Fill the list with all the startpoints of the plugin map.
			list = ((PluginBinding) stub.getBindings().get(0)).getPlugin().getPathGraph().getPathNodes();
			for (Iterator i = list.iterator(); i.hasNext();) {
				PathNode node = (PathNode) i.next();
				if (node instanceof StartPoint) {
					if (!isStartPointInBinded((StartPoint) node)) {
						inMapList.add(node);
						item = new TableItem(tabMapIns, SWT.NULL);
						item.setText(node.getName());
					}
				} else if (node instanceof EndPoint) {
					if (!isEndPointOutBinded((EndPoint) node)) {
						outMapList.add(node);
						item = new TableItem(tabMapOuts, SWT.NULL);
						item.setText(node.getName());
					}
				}
			}
		}

//		form.reflow(true);
	}

	/**
	 * @param con
	 * @return
	 */
	private boolean isNodeConnectionOutBinded(NodeConnection con) {
		if (stub.getBindings().size() > 0) {
			List outs = ((PluginBinding) stub.getBindings().get(0)).getOut();
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
	private boolean isEndPointOutBinded(EndPoint end) {
		if (stub.getBindings().size() > 0) {
			List ins = ((PluginBinding) stub.getBindings().get(0)).getOut();
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
	private boolean isStartPointInBinded(StartPoint start) {
		if (stub.getBindings().size() > 0) {
			List ins = ((PluginBinding) stub.getBindings().get(0)).getIn();
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
	private boolean isNodeConnectionInBinded(NodeConnection con) {
		if (stub.getBindings().size() > 0) {
			List ins = ((PluginBinding) stub.getBindings().get(0)).getIn();
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
		case MapPackage.MAP__UCMSPEC:
			System.out.println("A map has been added or deleted."); //$NON-NLS-1$
			break;
		case MapPackage.STUB__DESCRIPTION:
			descrip.setText(stub.getDescription());
//			form.reflow(true);
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