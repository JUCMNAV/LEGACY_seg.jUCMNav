package seg.jUCMNav.views;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.gef.EditPart;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
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
import org.eclipse.ui.part.ViewPart;

import seg.jUCMNav.model.ModelCreationFactory;
import ucm.map.Map;
import ucm.map.MapPackage;
import ucm.map.PluginBinding;
import ucm.map.Stub;
import urn.URNspec;

/**
 * Created 2005-05-28
 * 
 * @author Etienne Tremblay
 */
public class StubBindingsView extends ViewPart implements ISelectionListener, Adapter {
	private FormToolkit toolkit;
	private ScrolledForm form;
	
	private Table tabBindings;
	private Label descrip;
	private Combo maps;
	
	private URNspec urnSpec;
	private Stub stub;
	
	private ArrayList mapNames = new ArrayList();
	private ArrayList mapsObjects = new ArrayList();
	
	private Notifier target;
	
	// Sections in the form.
	private Section mapSection;
	private Section pluginListSection;
	private Section addPluginSection;
	
	/**
	 * 
	 */
	public StubBindingsView() {
		super();
	}

	public void createPartControl(Composite parent) {
		toolkit = new FormToolkit(parent.getDisplay());
		form = toolkit.createScrolledForm(parent);
		form.setText("Stub Bindings");
		TableWrapLayout layout = new TableWrapLayout();
		layout.numColumns = 2;
		form.getBody().setLayout(layout);
		TableWrapData td = new TableWrapData();
		ExpandableComposite ec = toolkit.createExpandableComposite(form.getBody(), ExpandableComposite.TWISTIE);
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
		mapSection = toolkit.createSection(form.getBody(),Section.TWISTIE);
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
		
		maps = new Combo(mapClient, SWT.DROP_DOWN);
		maps.addSelectionListener(new SelectionListener(){
			public void widgetSelected(SelectionEvent e) {
				Map map = (Map)mapsObjects.get(maps.getSelectionIndex());
				
				if(stub.isDynamic()) {
					PluginBinding binding = (PluginBinding)ModelCreationFactory.getNewObject(urnSpec, PluginBinding.class);
					binding.setPlugin(map);
					binding.setStub(stub);
				}
				else {
					if(stub.getBindings().size() > 0) {
						PluginBinding binding = (PluginBinding)stub.getBindings().get(0);
						binding.setPlugin(map);
					} else {
						PluginBinding binding = (PluginBinding)ModelCreationFactory.getNewObject(urnSpec, PluginBinding.class);
						binding.setPlugin(map);
						binding.setStub(stub);
					}
				}
				
				refreshBindingsList();
			}
			public void widgetDefaultSelected(SelectionEvent e) {}
		});
		GridData gridData = new GridData();
		gridData.widthHint = 150;
		maps.setLayoutData(gridData);
		toolkit.adapt(maps, true, true);
		
		mapSection.setClient(mapClient);
		
		// Plugin List section
		pluginListSection = toolkit.createSection(form.getBody(), Section.DESCRIPTION | Section.TWISTIE);
		td = new TableWrapData(TableWrapData.FILL);
		td.colspan = 2;
		pluginListSection.setLayoutData(td);
		pluginListSection.addExpansionListener(new ExpansionAdapter() {
			public void expansionStateChanged(ExpansionEvent e) {
				form.reflow(true);
			}
		});
		pluginListSection.setText("Plugins List");
		toolkit.createCompositeSeparator(pluginListSection);
		pluginListSection.setDescription("List of plugin bindings.");
		Composite sectionClient = toolkit.createComposite(pluginListSection);
		grid = new GridLayout();
		grid.numColumns = 1;
		sectionClient.setLayout(grid);
		
		// The table listing the plugins.
		tabBindings = toolkit.createTable(sectionClient, SWT.SINGLE | SWT.FULL_SELECTION);
		tabBindings.setLinesVisible(true);
		tabBindings.setHeaderVisible(true);
		GridData t = new GridData(GridData.FILL_BOTH);
		t.grabExcessHorizontalSpace = true;
		t.heightHint = 100;
		tabBindings.setLayoutData(t);
		TableColumn tableColumn = new TableColumn(tabBindings, SWT.NONE);
		tableColumn.setWidth(100);
		tableColumn.setText("Bindings");
		pluginListSection.setClient(sectionClient);
		
		// Added Plugin section
		addPluginSection = toolkit.createSection(form.getBody(),Section.TWISTIE);
		addPluginSection.setText("Add Bindigs");
		td = new TableWrapData(TableWrapData.FILL);
		td.colspan = 2;
		td.grabHorizontal = true;
		td.grabVertical = true;
		addPluginSection.setLayoutData(td);
		td.align = TableWrapData.FILL;
		addPluginSection.addExpansionListener(new ExpansionAdapter() {
			public void expansionStateChanged(ExpansionEvent e) {
				form.reflow(true);
			}
		});
		toolkit.createCompositeSeparator(addPluginSection);
		
		Composite addPluginClient = toolkit.createComposite(addPluginSection);
		grid = new GridLayout();
		grid.numColumns = 3;
		grid.makeColumnsEqualWidth = true;
		addPluginClient.setLayout(grid);
		
		addPluginSection.setClient(addPluginClient);
		
		// Stub composite for creating new plugings.
		Composite stubComp = toolkit.createComposite(addPluginClient);
		grid = new GridLayout();
		grid.numColumns = 1;
		stubComp.setLayout(grid);
		
		Label lb = toolkit.createLabel(stubComp, "Stub");
		
		Table tabStubIns = toolkit.createTable(stubComp, SWT.NULL);
		tabStubIns.setLinesVisible(true);
		tabStubIns.setHeaderVisible(true);
		tabStubIns.setLayoutData(new GridData(GridData.FILL_BOTH));
		tableColumn = new TableColumn(tabStubIns, SWT.NONE);
		tableColumn.setWidth(50);
		tableColumn.setText("In");
		
		
		Composite buttonComp = toolkit.createComposite(addPluginClient);
		grid = new GridLayout();
		grid.numColumns = 1;
		grid.makeColumnsEqualWidth = true;
		buttonComp.setLayout(grid);
		
		Button btBind = toolkit.createButton(buttonComp, "<->", SWT.PUSH | SWT.FLAT);
		GridData g = new GridData();
		g.horizontalAlignment = GridData.CENTER;
		btBind.setLayoutData(g);
		
		Composite mapComp = toolkit.createComposite(addPluginClient);
		grid = new GridLayout();
		grid.numColumns = 1;
		mapComp.setLayout(grid);
		lb = toolkit.createLabel(mapComp, "Map");
		
		Table tabMapIns = toolkit.createTable(mapComp, SWT.NULL);
		tabMapIns.setLinesVisible(true);
		tabMapIns.setHeaderVisible(true);
		tabMapIns.setLayoutData(new GridData(GridData.FILL_BOTH));
		tableColumn = new TableColumn(tabMapIns, SWT.NONE);
		tableColumn.setWidth(50);
		tableColumn.setText("In");
		
		getViewSite().getPage().addSelectionListener(this);
	}

	public void dispose() {
		getViewSite().getPage().removeSelectionListener(this);
		super.dispose();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ui.part.WorkbenchPart#setFocus()
	 */
	public void setFocus() {
		form.setFocus();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.ISelectionListener#selectionChanged(org.eclipse.ui.IWorkbenchPart, org.eclipse.jface.viewers.ISelection)
	 */
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		IStructuredSelection selecteds = (IStructuredSelection) selection;
		List list = selecteds.toList();

        if (list.size() == 1) {
        	if(list.get(0) instanceof EditPart) {
	            EditPart selected = (EditPart) (list.get(0));
	            if(selected.getModel() instanceof Stub)
	            	setStub((Stub)selected.getModel());
	            else 
	            	setStub(null);
        	}
        }
	}
	
	public void setStub(Stub stub){
		if(stub != this.stub) {
			this.stub = stub;
			if(stub != null){
				urnSpec = stub.getPathGraph().getMap().getUcmspec().getUrnspec();
				
				// Expand those sections by default
				mapSection.setExpanded(true);
            	pluginListSection.setExpanded(true);
				
				refreshDescription();
				refreshBindingsList();
				refreshMapList();
				
			} else {
				resetInfo();
			}
			form.reflow(true);
		}
	}

	/**
	 * 
	 */
	private void resetInfo() {
		descrip.setText("");
		tabBindings.clearAll();
		maps.setItems(new String[0]);
		
		// Close the sections
		mapSection.setExpanded(false);
    	pluginListSection.setExpanded(false);
	}

	private void refreshDescription() {
		if(stub.getDescription() != null)
			descrip.setText(stub.getDescription());
		else
			descrip.setText("");
	}

	private void refreshMapList() {
		List mapsList = stub.getPathGraph().getMap().getUcmspec().getMaps();
		
		mapNames.clear();
		mapsObjects.clear();
		
		ArrayList bindedMaps = new ArrayList();
		List bindings = stub.getBindings();
//		for (Iterator k = bindings.iterator(); k.hasNext();) {
//			PluginBinding bind = (PluginBinding) k.next();
//			bindedMaps.add(bind.getPlugin());
//		}
		
		for (Iterator i = mapsList.iterator(); i.hasNext();) {
			Map map = (Map) i.next();
			if(!bindedMaps.contains(map) && stub.getPathGraph().getMap() != map)
				mapsObjects.add(map);
		}
		String[] items = new String[mapsObjects.size()];
		int j=0;
		for (Iterator i = mapsObjects.iterator(); i.hasNext();) {
			Map s = (Map) i.next();
			mapNames.add(s.getName());
			items[j] = s.getName();
			j++;
		}
		maps.setItems(items);
		
		if(!stub.isDynamic()){
			if(bindings.size() > 0){
				PluginBinding b = (PluginBinding)bindings.get(0);
				int in = mapNames.indexOf(b.getPlugin().getName());
				maps.select(in);
			}
		}
	}

	private void refreshBindingsList() {
		tabBindings.removeAll();
		
		List list = stub.getBindings();
		TableItem item;
		for (Iterator i = list.iterator(); i.hasNext();) {
			PluginBinding binding = (PluginBinding) i.next();
			item = new TableItem(tabBindings, SWT.NULL);
			item.setText(binding.getStub().getName() + " <-> " + binding.getPlugin().getName());
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.common.notify.Adapter#notifyChanged(org.eclipse.emf.common.notify.Notification)
	 */
	public void notifyChanged(Notification notification) {
		int featureId = notification.getFeatureID(MapPackage.class);
		switch (featureId) {
			case MapPackage.MAP__UCMSPEC:
				System.out.println("A map has been added or deleted.");
				break;
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.common.notify.Adapter#getTarget()
	 */
	public Notifier getTarget() {
		return target;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.common.notify.Adapter#setTarget(org.eclipse.emf.common.notify.Notifier)
	 */
	public void setTarget(Notifier newTarget) {
		target = newTarget;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.common.notify.Adapter#isAdapterForType(java.lang.Object)
	 */
	public boolean isAdapterForType(Object type) {
		return type.equals(URNspec.class);
	}
}