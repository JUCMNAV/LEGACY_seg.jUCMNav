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
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
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
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.forms.events.ExpansionAdapter;
import org.eclipse.ui.forms.events.ExpansionEvent;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.forms.widgets.TableWrapLayout;

import seg.jUCMNav.model.commands.create.AddInBinding;
import seg.jUCMNav.model.commands.create.AddOutBinding;
import seg.jUCMNav.model.commands.create.AddPlugin;
import seg.jUCMNav.model.commands.transformations.ReplacePlugin;
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
public class StubBindingsDialog extends Dialog  implements ISelectionListener, Adapter {
//	 The toolkit for eclipse forms
	private FormToolkit toolkit;
	// The main form where all the controls will be
	private ScrolledForm form;

	// The description label.
	private Label descrip;
	// The combobox listing all the maps for changing the main plugin of a static stub
	private Combo comboMaps;

	// Two list used with the comboMaps object. The mapNames is used to hold the names of all the maps
	// and then transform this list in an array that can fill the combobox. And then the mapsObjects contains
	// the corresonding map objects for easy access.
	private ArrayList mapNames = new ArrayList();
	private ArrayList mapsObjects = new ArrayList();

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

	public StubBindingsDialog(Shell parentShell, CommandStack cmdStack) {
		super(parentShell);
		parentShell.setActive();
		parentShell.setText("Stub Bindings");
		setShellStyle(SWT.SHELL_TRIM);
		this.cmdStack = cmdStack;
	}

	protected Control createDialogArea(Composite parent) {		
		Composite area = (Composite) super.createDialogArea(parent);
		GridLayout l = (GridLayout)area.getLayout();
		l.marginWidth = 0;
		l.marginHeight = 0;
		GridData d = new GridData(GridData.FILL_BOTH);
		d.grabExcessHorizontalSpace = true;
		d.grabExcessVerticalSpace = true;
		area.setLayoutData(d);

		toolkit = new FormToolkit(area.getDisplay());
		form = toolkit.createScrolledForm(area);
		form.setLayoutData(d);
		form.setText("Stub Bindings");
		TableWrapLayout layout = new TableWrapLayout();
		layout.numColumns = 2;
		form.getBody().setLayout(layout);
		TableWrapData td = new TableWrapData();

		final ExpandableComposite ec = toolkit.createExpandableComposite(form.getBody(), ExpandableComposite.TWISTIE | ExpandableComposite.EXPANDED);
		ec.setText("Stub Description");
		descrip = toolkit.createLabel(ec, "", SWT.WRAP);
		ec.setClient(descrip);
		td = new TableWrapData();
		td.colspan = 2;
		ec.setLayoutData(td);
		ec.addExpansionListener(new ExpansionAdapter() {
			public void expansionStateChanged(ExpansionEvent e) {
				form.reflow(true);
			}
		});

		//		 Connect map section
		mapSection = toolkit.createSection(form.getBody(), Section.TWISTIE);
		mapSection.setText("Connect Map(s)");
		td = new TableWrapData(TableWrapData.FILL);
		td.colspan = 2;
		td.grabHorizontal = true;
		td.grabVertical = true;
		mapSection.setLayoutData(td);
		td.align = TableWrapData.FILL;
		mapSection.addExpansionListener(new ExpansionAdapter() {
			public void expansionStateChanged(ExpansionEvent e) {
				form.reflow(true);
			}
		});
		toolkit.createCompositeSeparator(mapSection);

		Composite mapClient = toolkit.createComposite(mapSection);
		GridLayout grid = new GridLayout();
		grid.numColumns = 1;
		mapClient.setLayout(grid);

		comboMaps = new Combo(mapClient, SWT.DROP_DOWN | SWT.FLAT);
		comboMaps.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				handlePluginChanged(comboMaps.getSelectionIndex());
			}

			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		GridData gridData = new GridData();
		gridData.widthHint = 150;
		comboMaps.setLayoutData(gridData);
		toolkit.adapt(comboMaps, true, true);

		mapSection.setClient(mapClient);
		
//		Composite listAddComp = new Composite(form.getBody(), SWT.NULL);

		// Plugin List section
		pluginListSection = toolkit.createSection(form.getBody(), Section.DESCRIPTION | Section.TWISTIE);
		td = new TableWrapData(TableWrapData.FILL);
		td.colspan = 1;
		td.grabHorizontal = true;
		td.grabVertical = true;
		pluginListSection.setLayoutData(td);
		pluginListSection.addExpansionListener(new ExpansionAdapter() {
			public void expansionStateChanged(ExpansionEvent e) {
				form.reflow(true);
				updateColumnWidth();
			}
		});
		pluginListSection.setText("Plugins List");
		toolkit.createCompositeSeparator(pluginListSection);
		pluginListSection.setDescription("List of plugin bindings.");
		Composite sectionClient = toolkit.createComposite(pluginListSection);
		grid = new GridLayout();
		grid.numColumns = 1;
		sectionClient.setLayout(grid);

		// The tree listing the plugins.
		treeBindings = toolkit.createTree(sectionClient, SWT.SINGLE);
		GridData t = new GridData(GridData.FILL_BOTH);
		t.grabExcessHorizontalSpace = true;
		t.heightHint = 120;
		t.widthHint = 200;
		treeBindings.setLayoutData(t);

		pluginListSection.setClient(sectionClient);

		// Add Plugin section
		addPluginSection = toolkit.createSection(form.getBody(), Section.TWISTIE);
		addPluginSection.setText("Add Bindigs");
		td = new TableWrapData(TableWrapData.FILL);
		td.colspan = 1;
		td.grabHorizontal = true;
		td.grabVertical = true;
		addPluginSection.setLayoutData(td);
		td.align = TableWrapData.FILL;
		addPluginSection.addExpansionListener(new ExpansionAdapter() {
			public void expansionStateChanged(ExpansionEvent e) {
				form.reflow(true);
				updateColumnWidth();
			}
		});
		toolkit.createCompositeSeparator(addPluginSection);

		Composite addPluginClient = toolkit.createComposite(addPluginSection);
		grid = new GridLayout();
		grid.numColumns = 3;
		grid.makeColumnsEqualWidth = false;
		addPluginClient.setLayout(grid);

		addPluginSection.setClient(addPluginClient);

		// Stub composite for creating new plugings.
		Composite stubComp = toolkit.createComposite(addPluginClient);
		grid = new GridLayout();
		grid.numColumns = 1;
		stubComp.setLayout(grid);
		GridData g = new GridData(GridData.FILL_BOTH);
		g.grabExcessHorizontalSpace = true;
		g.grabExcessVerticalSpace = true;
		stubComp.setLayoutData(g);

		Label lb = toolkit.createLabel(stubComp, "Stub");

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
		tabStubIns.setLayoutData(g);
		stubInsColumn = new TableColumn(tabStubIns, SWT.NONE);
		stubInsColumn.setWidth(50);
		stubInsColumn.setText("In");

		Composite buttonComp = toolkit.createComposite(addPluginClient);
		grid = new GridLayout();
		grid.numColumns = 1;
		grid.makeColumnsEqualWidth = true;
		buttonComp.setLayout(grid);
		g = new GridData(GridData.FILL_BOTH);
		g.grabExcessHorizontalSpace = true;
		g.grabExcessVerticalSpace = true;
		buttonComp.setLayoutData(g);

		btInBind = toolkit.createButton(buttonComp, "<->", SWT.PUSH | SWT.FLAT);
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

		lb = toolkit.createLabel(mapComp, "Map");

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
		g.verticalSpan = 5;
		tabMapIns.setLayoutData(g);
		mapInsColumn = new TableColumn(tabMapIns, SWT.NONE);
		mapInsColumn.setWidth(50);
		mapInsColumn.setText("In");
		
		// Out bindings controls
		stubComp = toolkit.createComposite(addPluginClient);
		grid = new GridLayout();
		grid.numColumns = 1;
		stubComp.setLayout(grid);
		g = new GridData(GridData.FILL_BOTH);
		g.grabExcessHorizontalSpace = true;
		g.grabExcessVerticalSpace = true;
		stubComp.setLayoutData(g);

		lb = toolkit.createLabel(stubComp, "Stub");

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
		tabStubOuts.setLayoutData(g);
		stubOutsColumn = new TableColumn(tabStubOuts, SWT.NONE);
		stubOutsColumn.setWidth(50);
		stubOutsColumn.setText("Out");

		buttonComp = toolkit.createComposite(addPluginClient);
		grid = new GridLayout();
		grid.numColumns = 1;
		grid.makeColumnsEqualWidth = true;
		buttonComp.setLayout(grid);
		g = new GridData(GridData.FILL_BOTH);
		g.grabExcessHorizontalSpace = true;
		g.grabExcessVerticalSpace = true;
		buttonComp.setLayoutData(g);

		btOutBind = toolkit.createButton(buttonComp, "<->", SWT.PUSH | SWT.FLAT);
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

		lb = toolkit.createLabel(mapComp, "Map");

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
		g.verticalSpan = 5;
		tabMapOuts.setLayoutData(g);
		mapOutsColumn = new TableColumn(tabMapOuts, SWT.NONE);
		mapOutsColumn.setWidth(50);
		mapOutsColumn.setText("Out");
		
		mapSection.setExpanded(true);
		pluginListSection.setExpanded(true);
		addPluginSection.setExpanded(true);
		
		return area;
	}

	protected void createButtonsForButtonBar(Composite parent) {
		parent.getParent().setBackground(new Color(null, 255, 255, 255));
		parent.setBackground(new Color(null, 255, 255, 255));
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
		createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
	}

	protected Point getInitialSize() {
		return new Point(800, 600);
	}
	
	protected void execute(Command command){
		if (command == null || !command.canExecute())
			return;
		getCommandStack().execute(command);
	}
	
	protected void undo(){
		getCommandStack().undo();
	}
	
	protected void redo(){
		getCommandStack().redo();
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
				EndPoint end = (EndPoint)outMapList.get(tabMapOuts.getSelectionIndex());
				NodeConnection con = (NodeConnection)outStubList.get(tabStubOuts.getSelectionIndex());
				
				AddOutBinding out = new AddOutBinding(plug, end, con);
				execute(out);

				refreshInOutList();
				refreshBindingsTree();
			}
			btOutBind.setEnabled(false);
			if(!pluginListSection.isExpanded()){
				pluginListSection.setExpanded(true);
				updateColumnWidth();
			}
		}
	}

	/**
	 * This method is called when the user click the in bind button.
	 */
	protected void handleInBindClick() {
		// Both list have something selected.  And the stub has to have at least one plugin.
		if (tabStubIns.getSelectionCount() >= 1 && tabMapIns.getSelectionCount() >= 1 && stub.getBindings().size() > 0) {
			// Check that the selected Stub is not dynamic
			if (!stub.isDynamic()) {
//				 Set the binding of the InBinding to to first one in the list of the plugin.
				PluginBinding plug = (PluginBinding) stub.getBindings().get(0);

				// Get the selected StartPoint and NodeConnection in the map and stub in table.
				StartPoint start = (StartPoint)inMapList.get(tabMapIns.getSelectionIndex());
				NodeConnection con = (NodeConnection)inStubList.get(tabStubIns.getSelectionIndex());
				
				AddInBinding in = new AddInBinding(plug, start, con);
				execute(in);

				refreshInOutList();
				refreshBindingsTree();
			}
			btInBind.setEnabled(false);
			if(!pluginListSection.isExpanded()){
				pluginListSection.setExpanded(true);
				updateColumnWidth();
			}
		}
	}

	/**
	 * Ths method is called when the user selects a new map as a plugin to the Stub from the combobox.
	 */
	protected void handlePluginChanged(int selectedIndex) {
		Map map = (Map) mapsObjects.get(selectedIndex);

		if (stub.isDynamic()) {
//			PluginBinding binding = (PluginBinding) ModelCreationFactory.getNewObject(urnSpec, PluginBinding.class);
//			binding.setPlugin(map);
//			binding.setStub(stub);
		} else {
			if (stub.getBindings().size() == 1) {
				if(((PluginBinding)stub.getBindings().get(0)).getPlugin() != map) {
					ReplacePlugin plugin = new ReplacePlugin((PluginBinding)stub.getBindings().get(0));
					
					execute(plugin);
					
					refreshInOutList();
					refreshBindingsTree();
				}
			} else if(stub.getBindings().size() == 0) {
				AddPlugin plugin = new AddPlugin(stub, map);
				
				execute(plugin);
				
				refreshInOutList();
				refreshBindingsTree();
			}
		}
	}

	/**
	 * This method updates the width of columns of tables to make them as wide as possible.
	 */
	protected void updateColumnWidth() {
		mapInsColumn.setWidth(tabMapIns.getSize().x - 1);
		stubInsColumn.setWidth(tabStubIns.getSize().x - 1);
		mapOutsColumn.setWidth(tabMapOuts.getSize().x - 1);
		stubOutsColumn.setWidth(tabStubOuts.getSize().x - 1);
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
		getParentShell().setText("Stub Bindings");
		setStub(stub);
		return super.open();
	}
	
	/**
	 * Reset all the view information when the selected Stub is null.
	 */
	private void resetInfo() {
		descrip.setText("");
		treeBindings.removeAll();
		comboMaps.setItems(new String[0]);
		tabMapIns.removeAll();
		tabStubIns.removeAll();

		// Close the sections
		mapSection.setExpanded(false);
		pluginListSection.setExpanded(false);
		addPluginSection.setExpanded(false);
		//    	form.setText("No stub selected");
		//    	form.setForeground(new Color(null, 255, 0, 0));
		form.setVisible(false);
		form.reflow(true);
	}

	/**
	 * Refresh the description of the selected Stub.
	 */
	private void refreshDescription() {
		if (stub.getDescription() != null)
			descrip.setText(stub.getDescription());
		else
			descrip.setText("");
	}

	/**
	 * Refresh the map list display in the combobox listing the possible maps to plugin to.
	 */
	private void refreshMapList() {
		List mapsList = stub.getPathGraph().getMap().getUcmspec().getMaps();

		mapNames.clear();
		mapsObjects.clear();

		ArrayList bindedMaps = new ArrayList();
		List bindings = stub.getBindings();

		for (Iterator i = mapsList.iterator(); i.hasNext();) {
			Map map = (Map) i.next();
			if (!bindedMaps.contains(map) && stub.getPathGraph().getMap() != map)
				mapsObjects.add(map);
		}
		String[] items = new String[mapsObjects.size()];
		int j = 0;
		for (Iterator i = mapsObjects.iterator(); i.hasNext();) {
			Map s = (Map) i.next();
			mapNames.add(s.getName());
			items[j] = s.getName();
			j++;
		}
		comboMaps.setItems(items);

		if (!stub.isDynamic()) {
			if (bindings.size() > 0) {
				PluginBinding b = (PluginBinding) bindings.get(0);
				int in = mapNames.indexOf(b.getPlugin().getName());
				comboMaps.select(in);
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
		root.setText("Bindings");
		TreeItem item;
		TreeItem subLabelItem;
		TreeItem subItem;
		for (Iterator i = list.iterator(); i.hasNext();) {
			item = root;
			PluginBinding binding = (PluginBinding) i.next();
			item = new TreeItem(item, SWT.NULL);
			item.setText(binding.getStub().getName() + " <-> " + binding.getPlugin().getName());

			subLabelItem = new TreeItem(item, SWT.NULL);
			subLabelItem.setText("In bindings");

			List in = binding.getIn();
			for (Iterator j = in.iterator(); j.hasNext();) {
				InBinding inBind = (InBinding) j.next();
				subItem = new TreeItem(subLabelItem, SWT.NULL);
				subItem.setText(inBind.getStubEntry().getSource().getName() + "<->" + inBind.getStartPoint().getName());
			}
			subLabelItem.setExpanded(true);

			subLabelItem = new TreeItem(item, SWT.NULL);
			subLabelItem.setText("Out bindings");

			List out = binding.getOut();
			for (Iterator j = out.iterator(); j.hasNext();) {
				OutBinding outBind = (OutBinding) j.next();
				subItem = new TreeItem(subLabelItem, SWT.NULL);
				subItem.setText(outBind.getEndPoint().getName() + "<->" + outBind.getStubExit().getTarget().getName());
			}
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

		if(stub.getBindings().size() > 0) {
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
				} else if(node instanceof EndPoint){
					if (!isEndPointOutBinded((EndPoint) node)) {
						outMapList.add(node);
						item = new TableItem(tabMapOuts, SWT.NULL);
						item.setText(node.getName());
					}
				}
			}
		}

		form.reflow(true);
	}

	/**
	 * @param con
	 * @return
	 */
	private boolean isNodeConnectionOutBinded(NodeConnection con) {
		if(stub.getBindings().size() > 0) {
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
		if(stub.getBindings().size() > 0) {
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
		if(stub.getBindings().size() > 0) {
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
		if(stub.getBindings().size() > 0) {
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
			System.out.println("A map has been added or deleted.");
			break;
		case MapPackage.STUB__DESCRIPTION:
			descrip.setText(stub.getDescription());
			form.reflow(true);
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
