/*****************************************************************************************
 * MXPARSER LICENSE
 * Copyright 2010-2015 MARIUSZ GROMADA. All rights reserved. 
 * You may use this software under the condition of Simplified BSD License. 
 * Redistribution and use in source and binary forms, with or without modification, 
 * are permitted provided that the following conditions are met:
 * 1) Redistributions of source code must retain the above copyright notice, 
 *    this list of conditions and the following disclaimer.
 * 2) Redistributions in binary form must reproduce the above copyright notice, 
 *    this list of conditions and the following disclaimer in the documentation 
 *    and/or other materials provided with the distribution.
 *    
 * THIS SOFTWARE IS PROVIDED BY MARIUSZ GROMADA ``AS IS'' AND ANY EXPRESS OR IMPLIED 
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY 
 * AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL MARIUSZ GROMADA 
 * OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, 
 * OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE 
 * GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER 
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT 
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, 
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 * The views and conclusions contained in the software and documentation are those of the 
 * authors and should not be interpreted as representing official policies, either expressed
 * or implied, of MARIUSZ GROMADA.
 *******************************************************************************************/
package seg.jUCMNav.views.wizards.dynamicContexts;

import java.io.StringReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.mariuszgromada.math.mxparser.Argument;
import org.mariuszgromada.math.mxparser.Expression;

import grl.Actor;
import grl.ActorRef;
import grl.Contribution;
import grl.Decomposition;
import grl.Dependency;
import grl.ElementLink;
import grl.IntentionalElement;
import grl.IntentionalElementRef;
import grl.LinkRef;
import seg.jUCMNav.JUCMNavPlugin;
import seg.jUCMNav.Messages;
import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.editparts.ActorRefEditPart;
import seg.jUCMNav.editparts.ComponentRefEditPart;
import seg.jUCMNav.editparts.IntentionalElementEditPart;
import seg.jUCMNav.editparts.LinkRefEditPart;
import seg.jUCMNav.editparts.PathNodeEditPart;
import seg.jUCMNav.editparts.RespRefEditPart;
import seg.jUCMNav.editparts.StubEditPart;
import seg.jUCMNav.editparts.dynamicContextTreeEditparts.ChangeTreeEditPart;
import seg.jUCMNav.editparts.dynamicContextTreeEditparts.DynamicContextsUtils;
import seg.jUCMNav.figures.ColorManager;
import seg.jUCMNav.model.commands.create.AddBooleanChangeCommand;
import seg.jUCMNav.model.commands.create.AddDeactivationChangeCommand;
import seg.jUCMNav.model.commands.create.AddEnumerationChangeCommand;
import seg.jUCMNav.model.commands.create.AddNumericChangeCommand;
import seg.jUCMNav.model.commands.create.AddTextChangeCommand;
import seg.jUCMNav.model.commands.delete.DeleteChangeCommand;
import seg.jUCMNav.model.commands.transformations.UpdateBooleanChangeCommand;
import seg.jUCMNav.model.commands.transformations.UpdateDeactivationChangeCommand;
import seg.jUCMNav.model.commands.transformations.UpdateEnumChangeCommand;
import seg.jUCMNav.model.commands.transformations.UpdateNumericChangeCommand;
import seg.jUCMNav.model.commands.transformations.UpdateTextChangeCommand;
import seg.jUCMNav.model.util.URNNamingHelper;
import seg.jUCMNav.scenarios.ScenarioUtils;
import seg.jUCMNav.scenarios.model.UcmEnvironment;
import seg.jUCMNav.scenarios.parser.SimpleNode;
import seg.jUCMNav.scenarios.parser.jUCMNavParser;
import seg.jUCMNav.scenarios.parser.jUCMNavTypeChecker;
import seg.jUCMNav.views.dynamicContexts.DynamicContextsView;
import seg.jUCMNav.views.preferences.GeneralPreferencePage;
import seg.jUCMNav.views.preferences.ScenarioTraversalPreferences;
import seg.jUCMNav.views.wizards.scenarios.AddVariableWizard;
import ucm.map.ComponentRef;
import ucm.map.EndPoint;
import ucm.map.FailurePoint;
import ucm.map.NodeConnection;
import ucm.map.OrFork;
import ucm.map.PluginBinding;
import ucm.map.RespRef;
import ucm.map.StartPoint;
import ucm.map.Stub;
import ucm.map.Timer;
import ucm.map.WaitingPlace;
import ucm.scenario.Variable;
import urn.URNspec;
import urn.dyncontext.BooleanChange;
import urn.dyncontext.Change;
import urn.dyncontext.ConstantChange;
import urn.dyncontext.DeactivationChange;
import urn.dyncontext.DynamicContext;
import urn.dyncontext.DynamicContextGroup;
import urn.dyncontext.EnumChange;
import urn.dyncontext.FormulaChange;
import urn.dyncontext.LinearChange;
import urn.dyncontext.PropertyChange;
import urn.dyncontext.QuadraticChange;
import urn.dyncontext.TextChange;
import urncore.Component;
import urncore.GRLmodelElement;
import urncore.Responsibility;
import urncore.UCMmodelElement;
import urncore.URNmodelElement;

/**
 * The ManageChangeEditorPage contains the UI code for managing changes.
 * 
 * @author aprajita, sluthra
 */
public class ManageChangeEditorPage extends WizardPage {
	
	private ISelection selection;
    private Object parent;
    private static final String[] columnNames = { Messages.getString("ManageChangePage.column1"),  
    		Messages.getString("ManageChangePage.column2"), Messages.getString("ManageChangePage.column3"), Messages.getString("ManageChangePage.column4"), 
    		Messages.getString("ManageChangePage.column5"), Messages.getString("ManageChangePage.column6")}; //$NON-NLS-1$ //$NON-NLS-2$
    private Combo changes;
    private Combo dynamicContexts;
    private Combo affectedProperties;
    private Combo newValueDecomp;
    private MessageBox dialog;
    private String changeType;
    private String affectedProperty;
    private Table availableChanges;
    private DateTime startCalendar;
    private Date startDate;
    private DateTime endCalendar;
    private Date endDate;
    private Text newValueText;
    private Text newValueQuad;
    private Text newValueLin;
    private Text newValueCon;
    private Text newValueFor;
    private Button addButton;
    private Button updButton;
    private Button delButton;
    private Button deselButton;
	private Button createNewVariable;
	private Button addVariableToExpression;
    private URNspec urn;
    private DynamicContext currentDynContext, oldDynContext, selectedDynContext;
    private Change selectedChange;
    private int newValue;
    private float quadCoeff, linCoeff, conCoeff;
	private String newValueExpression;
	private Boolean wrongFormatRepFactor;
    private String formula;
    private Boolean activateDecomp;
    private Composite container;
    private IWorkbenchPage workbenchPage;
    private Boolean wrongFormat;
    
    //Store the value from where changes of included Dynamic Contexts start; Set in getAvailableChanges()
    int disabledFrom = -1;

	private Combo newValueFailureKind;
	private Combo newValueWaitKind;
	private Combo newValueCompKind;
	private Combo newValueDynamicStub;
	private Combo newValueSyncStub;
	private Combo newValueBlockStub;
	private Combo newValueCompProtection;
	private Combo newValueCompContext;
	private Text newValueRepFactor;
	private Text newValuePluginBindingPreCond;
	private Text newValueRespExpression;
	private Text newValueOrForkExp;
	private Text newValueFailureList;
	private Text newValueFailureExpression;
	private Text newValueFailureCondition;
	private Text newValueEndPtPostCond;
	private String errorMessage;
	private String pluginBindingPreCond;
	private String orForkExp;
	private String endPtPostCond;
	private String failureExp;
	private String failureCond;
	private String failureList;
	private String respExp;
	private int repFactor;
	private Combo newValueSelectVariable;

    /**
     * The selection contains a URNModelElement for which change needs to be added. Loaded in {@link #initialize()}.
     * 
     * @param selection
     */
    public ManageChangeEditorPage(ISelection selection, URNspec urn, IWorkbenchPage workbenchPage) {
        super("wizardPage"); //$NON-NLS-1$
        this.urn = urn;
        this.workbenchPage = workbenchPage;
        this.setImageDescriptor(ImageDescriptor.createFromFile(JUCMNavPlugin.class, "icons/perspectiveIcon.gif")); //$NON-NLS-1$

        // loaded in initialize()
        this.selection = selection;
    }

    /**
     * Creates the page.
     */
    public void createControl(Composite parent) {
    	initialize();
    	
        PlatformUI.getWorkbench().getHelpSystem().setHelp(parent, "seg.jUCMNav.scenario_includescenario"); //$NON-NLS-1$
        container = new Composite(parent, SWT.NULL);

        GridLayout layout = new GridLayout();
        container.setLayout(layout);
        layout.numColumns = 4;
        layout.verticalSpacing = 5;
        layout.marginBottom = 30;
        
        GridData gd;
        Label elementLabel = new Label(container, SWT.NONE);
        elementLabel.setText(Messages.getString("ManageChangePage.SelectDynamicContext")); //$NON-NLS-1$
        
        //Select Dynamic Context Combo
        dynamicContexts = new Combo(container, SWT.DROP_DOWN | SWT.READ_ONLY);
        dynamicContexts.setItems(getDynamicContexts());
        dynamicContexts.addSelectionListener(new SelectionListener() {
            public void widgetSelected(SelectionEvent e) {
            	oldDynContext = currentDynContext;
                dialogChanged();
                if (oldDynContext == null)
                	oldDynContext = currentDynContext;
                String[][] availChanges = getAvailableChanges();
                availableChanges.removeAll();
                if (availChanges != null) {
        	        for (int i = 0; i < availChanges.length; i++) {
        	            TableItem item = new TableItem(availableChanges, SWT.NONE);
        	            item.setText(new String[] {availChanges[i][0], availChanges[i][1], availChanges[i][2], availChanges[i][3], availChanges[i][4], availChanges[i][5]});
        	            
        	            //If the item belongs to included dynamic contexts (indicated by disabledFrom index), then grey out that item
        	            if (disabledFrom != -1 && i >= disabledFrom) {
        	            	item.setBackground(ColorManager.GRAY);
        	            }
        	        }
                }
                availableChanges.update();
                availableChanges.setEnabled(true);
                affectedProperties.setEnabled(true);
            }

            public void widgetDefaultSelected(SelectionEvent e) {
                // double-click does nothing.

            }
        });

        gd = new GridData();
        gd.horizontalAlignment = GridData.FILL;
 		gd.horizontalSpan = 1;
 		dynamicContexts.setLayoutData(gd);
        
 		//Labels for empty spaces
        elementLabel = new Label(container, SWT.NONE);
        elementLabel = new Label(container, SWT.NONE);
        elementLabel = new Label(container, SWT.NONE);
        elementLabel = new Label(container, SWT.NONE);
        
        elementLabel = new Label(container, SWT.NONE);
        elementLabel.setText(Messages.getString("ManageChangePage.AvailableChanges")); //$NON-NLS-1$
        
        //Label for empty space
        elementLabel = new Label(container, SWT.NONE);
        
        elementLabel = new Label(container, SWT.NONE);
        elementLabel.setText(Messages.getString("ManageChangePage.SelectAffectedProperty")); //$NON-NLS-1$
        
        //Select Affected Property combo
        affectedProperties = new Combo(container, SWT.DROP_DOWN | SWT.READ_ONLY);
        affectedProperties.setItems(getPossibleAffectedProperties());
        affectedProperties.addSelectionListener(new SelectionListener() {
            public void widgetSelected(SelectionEvent e) {
                dialogChanged();
                changes.setItems(getPossibleChanges());
                changes.setEnabled(true);
				changes.select(0);
				checkForChanges();
                String[][] availChanges = getAvailableChanges();
                availableChanges.removeAll();
                if (availChanges != null) {
        	        for (int i = 0; i < availChanges.length; i++) {
        	        	if ((availChanges[i][0].startsWith("Selected") && affectedProperty.startsWith("Selected")) || (availChanges[i][0].equals(affectedProperty))) {
	        	            TableItem item = new TableItem(availableChanges, SWT.NONE);
	        	            item.setText(new String[] {availChanges[i][0], availChanges[i][1], availChanges[i][2], availChanges[i][3], availChanges[i][4], availChanges[i][5]});
	        	            
	        	            //If the item belongs to included dynamic contexts (indicated by disabledFrom index), then grey out that item
	        	            if (disabledFrom != -1 && i >= disabledFrom) {
	        	            	item.setBackground(ColorManager.GRAY);
	        	            }
        	        	}
        	        }
                }
                availableChanges.update();
                
            }

            public void widgetDefaultSelected(SelectionEvent e) {
                // double-click does nothing.

            }
        });

        gd = new GridData();
        gd.horizontalAlignment = GridData.FILL;
 		gd.horizontalSpan = 1;
        affectedProperties.setLayoutData(gd);
        
        //Table for listing changes
        availableChanges = new Table(container, SWT.SINGLE | SWT.BORDER | SWT.FULL_SELECTION);
        availableChanges.setHeaderVisible(true);
        availableChanges.setLinesVisible(true);
        Listener sortListener = new Listener() {
			@Override
			public void handleEvent(Event e) {
			    TableColumn sortColumn = availableChanges.getSortColumn();
			    TableColumn selectedColumn = (TableColumn) e.widget;
			    int dir = availableChanges.getSortDirection();
			    if (sortColumn == selectedColumn) {
			        dir = dir == SWT.UP ? SWT.DOWN : SWT.UP;
			    } else {
			    	availableChanges.setSortColumn(selectedColumn);
			        dir = SWT.UP;
			    }
			    TableItem[] items = availableChanges.getItems();
			    final Comparator<TableItem> comparator = (Comparator<TableItem>) selectedColumn.getData();
			    for (int i = 1; i < items.length; i++) {
			        for (int j = 0; j < i; j++) {
			            if ((comparator.compare(items[i], items[j]) < 0 && dir == SWT.UP) || (comparator.compare(items[i], items[j]) > 0 && dir == SWT.DOWN)) {
			                String[] oldItem = new String[availableChanges.getColumnCount()];
			                for (int h = 0; h < availableChanges.getColumnCount(); h++) {
			                	oldItem[h] = items[i].getText(h);
			                }
			                Color background = items[i].getBackground();
			                items[i].dispose();
			                TableItem newItem = new TableItem(availableChanges, SWT.NONE, j);
			                newItem.setText(oldItem);
			                
			                //The background color of the items should remain same even after sorting
			                newItem.setBackground(background);
			                items = availableChanges.getItems();
			                break;
			            }
			        }
			    }
			    availableChanges.setSortDirection(dir);
			}
		};
        for (int i = 0; i < columnNames.length; i++) {
            TableColumn column = new TableColumn(availableChanges, SWT.NONE);
            column.setText(columnNames[i]);
            if (i == 0 || i == 3)
            	column.setWidth(130);
            else if (i == 1 || i == 2 || i == 4)
            	column.setWidth(80);
            else
            	column.setWidth(100);
            if (i == 0) {
            	column.setData(new Comparator<TableItem>() {
            	    @Override
            	    public int compare(TableItem t1, TableItem t2) {
            	        return t1.getText(0).compareTo(t2.getText(0));
            	    }
            	});
            	column.addListener(SWT.Selection, sortListener);
            }
            if (i == 1) {
            	column.setData(new Comparator<TableItem>() {
            	    @Override
            	    public int compare(TableItem t1, TableItem t2) {
            	    	DateFormat df = new SimpleDateFormat("dd-MMM-yyyy"); 
            	    	Date startDate1 = null;
            	    	Date startDate2 = null;
            	    	try {
            	    		startDate1 = df.parse(t1.getText(1));
            	    		startDate2 = df.parse(t2.getText(1));
            	    	} catch (ParseException e) {
            	    	       e.printStackTrace();
            	    	}
            	        return startDate1.compareTo(startDate2);
            	    }
            	});
            	column.addListener(SWT.Selection, sortListener);
            	
            }
        }
        String[][] availChanges = getAvailableChanges();
        availableChanges.removeAll();
        if (availChanges != null) {
	        for (int i = 0; i < availChanges.length; i++) {
	            TableItem item = new TableItem(availableChanges, SWT.NONE);
	            item.setText(new String[] {availChanges[i][0], availChanges[i][1], availChanges[i][2], availChanges[i][3], availChanges[i][4], availChanges[i][5]});
	            
	            //If the item belongs to included dynamic contexts (indicated by disabledFrom index), then grey out that item
	            if (disabledFrom != -1 && i >= disabledFrom) {
	            	item.setBackground(ColorManager.GRAY);
	            }
	        }
        }
        
        availableChanges.addSelectionListener(new SelectionListener() {
            public void widgetSelected(SelectionEvent e) {
            	
            	//If the change belongs to included dynamic contexts, clear the dialog box
            	//Otherwise update the ManageChange dialog box with selected change's data
            	if (availableChanges.getItem(availableChanges.getSelectionIndex()).getBackground().equals(ColorManager.GRAY)) {
            		clearChangeData();
            	} else {
	            	updateItemInDialog(availableChanges.getSelectionIndex());
	            	dialogChanged();
            	}
            }

            public void widgetDefaultSelected(SelectionEvent e) {
                // double-click does nothing.

            }
        });
        
        gd = new GridData();
        gd.verticalAlignment = GridData.FILL;
        gd.verticalSpan = 10;
        gd.horizontalSpan = 2;
        gd.grabExcessVerticalSpace = true;
        gd.horizontalAlignment = GridData.FILL;
        gd.grabExcessHorizontalSpace = true;
        availableChanges.setLayoutData(gd);
        
        Label label = new Label(container, SWT.NULL);
        label.setText(Messages.getString("ManageChangePage.typeLabel")); //$NON-NLS-1$
        
        //Select type of change combo
        changes = new Combo(container, SWT.DROP_DOWN | SWT.READ_ONLY);
        changes.setEnabled(false);

        changes.addSelectionListener(new SelectionListener() {
            public void widgetSelected(SelectionEvent e) {
            	dialogChanged();
            }

            public void widgetDefaultSelected(SelectionEvent e) {
                // double-click does nothing.

            }
        });
        gd = new GridData();
        gd.horizontalAlignment = GridData.FILL;
 		gd.horizontalSpan = 1;
        changes.setLayoutData(gd);

        label = new Label(container, SWT.NULL);
        label.setText(Messages.getString("ManageChangePage.SelectStartDate")); //$NON-NLS-1$
        
        //Calendar to select start date
        startCalendar = new DateTime(container, SWT.DATE | SWT.DROP_DOWN | SWT.MEDIUM);
        startCalendar.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				dialogChanged();
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
        
        label = new Label(container, SWT.NULL);
        label.setText(Messages.getString("ManageChangePage.SelectEndDate")); //$NON-NLS-1$
        
        //Calendar to select end date
        endCalendar = new DateTime(container, SWT.DATE | SWT.DROP_DOWN | SWT.MEDIUM);
        endCalendar.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				dialogChanged();
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
        
		if (this.parent instanceof urncore.Condition) {
			if (((TextChange) selectedChange).getAffectedProperty().startsWith("Pre-Condition") || ((TextChange) selectedChange).getAffectedProperty().contains("Replication factor")) {

				label = new Label(container, SWT.NULL);
				label.setText(Messages.getString("ManageChangePage.SelectBooleanforDynamicStub")); //$NON-NLS-1$

				//Combo to select booleanValue for Dynamic Stub
				newValueDynamicStub = new Combo(container, SWT.DROP_DOWN | SWT.READ_ONLY);
				String[] dynamicStub = {"true", "false"};
				newValueDynamicStub.setItems(dynamicStub);
				newValueDynamicStub.setEnabled(false);
				newValueDynamicStub.addListener(SWT.Verify, new Listener() {
					public void handleEvent(Event e) {
						dialogChanged();
					}
				});
				newValueDynamicStub.setLayoutData(gd);

				label = new Label(container, SWT.NULL);
				label.setText(Messages.getString("ManageChangePage.SelectBooleanforSyncStub")); //$NON-NLS-1$

				//Combo to select booleanValue for Sync Stub
				newValueSyncStub = new Combo(container, SWT.DROP_DOWN | SWT.READ_ONLY);
				String[] syncStub = {"true", "false"};
				newValueSyncStub.setItems(syncStub);
				newValueSyncStub.setEnabled(false);
				newValueSyncStub.addListener(SWT.Verify, new Listener() {
					public void handleEvent(Event e) {
						dialogChanged();
					}
				});
				newValueSyncStub.setLayoutData(gd);

				label = new Label(container, SWT.NULL);
				label.setText(Messages.getString("ManageChangePage.SelectBooleanforBlockStub")); //$NON-NLS-1$

				//Combo to select booleanValue for Block Stub
				newValueBlockStub = new Combo(container, SWT.DROP_DOWN | SWT.READ_ONLY);
				String[] blockStub = {"true", "false"};
				newValueBlockStub.setItems(blockStub);
				newValueBlockStub.setEnabled(false);
				newValueBlockStub.addListener(SWT.Verify, new Listener() {
					public void handleEvent(Event e) {
						dialogChanged();
					}
				});
				newValueBlockStub.setLayoutData(gd);

				label = new Label(container, SWT.NULL);
				label.setText(Messages.getString("ManageChangePage.EnterReplicationFactor")); //$NON-NLS-1$

				//Text box to enter Replication Factor of a PluginBinding
				newValueRepFactor = new Text(container, SWT.BORDER);
				newValueRepFactor.setEnabled(false);
				newValueRepFactor.addListener(SWT.Verify, new Listener() {
					public void handleEvent(Event e) {
						dialogChanged();
					}
				});
				newValueRepFactor.setLayoutData(gd);

				label = new Label(container, SWT.NULL);
				label.setText(Messages.getString("ManageChangePage.EnterPreConditionPluginBinding")); //$NON-NLS-1$

				//Text box to enter Pre-Condition of a PluginBinding
				newValuePluginBindingPreCond = new Text(container, SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
				newValuePluginBindingPreCond.setEnabled(false);
				newValuePluginBindingPreCond.addListener(SWT.Verify, new Listener() {
					public void handleEvent(Event e) {
						dialogChanged();
					}
				});
				newValuePluginBindingPreCond.setLayoutData(gd);

				label = new Label(container, SWT.NULL);
				label.setText(Messages.getString("ManageChangePage.SelectVariable")); //$NON-NLS-1$

				createNewValueSelectVariable();
				newValueSelectVariable.setLayoutData(gd);

				createNewVariableButton();
				createNewVariable.setLayoutData(gd);

				//Button to add a variable to expression
				addVariableToExpression = new Button(container, SWT.PUSH);
				addVariableToExpression.setText("Add Variable to Condition");
				addVariableToExpression.setEnabled(false);
				addVariableToExpression.addSelectionListener(new SelectionListener() {

					@Override
					public void widgetSelected(SelectionEvent e) {
						if (newValueSelectVariable.getText().isEmpty())
							setErrorMessage("Please select a variable!!");
						else {
							setErrorMessage(null);
							if (!(newValuePluginBindingPreCond.getText().isEmpty())) {
								newValuePluginBindingPreCond.insert(newValueSelectVariable.getText());
								newValuePluginBindingPreCond.setSelection(newValuePluginBindingPreCond.getText().length());
							} else {
								newValuePluginBindingPreCond.setText(newValueSelectVariable.getText());
								newValuePluginBindingPreCond.setSelection(newValueSelectVariable.getText().length());
							}
						}	
					}

					@Override
					public void widgetDefaultSelected(SelectionEvent e) {

					}

				});
				addVariableToExpression.setLayoutData(gd);

				for (int i = 1; i < 3; i++) {
					label = new Label(container, SWT.NULL);
					label.setText("");
				}

			}

			else if (((TextChange) selectedChange).getAffectedProperty().equals("Failure List/Start Point Pre-Condition")) {

				label = new Label(container, SWT.NULL);
				label.setText(Messages.getString("ManageChangePage.SelectFailureKind")); //$NON-NLS-1$

				//Combo to select newValue for Failure Kind
				newValueFailureKind = new Combo(container, SWT.DROP_DOWN | SWT.READ_ONLY);
				String[] failureKinds = {"FAILURE", "ABORT", "NONE"};
				newValueFailureKind.setItems(failureKinds);
				newValueFailureKind.setEnabled(false);
				newValueFailureKind.addListener(SWT.Verify, new Listener() {
					public void handleEvent(Event e) {
						dialogChanged();
					}
				});
				newValueFailureKind.setLayoutData(gd);

				label = new Label(container, SWT.NULL);
				label.setText(Messages.getString("ManageChangePage.EnterFailureList/StartPointPreCondition")); //$NON-NLS-1$

				//Text box to enter Failure List/Start Point Pre-Condition
				newValueFailureList = new Text(container, SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
				newValueFailureList.setEnabled(false);
				newValueFailureList.addListener(SWT.Verify, new Listener() {
					public void handleEvent(Event e) {
						dialogChanged();
					}
				});
				newValueFailureList.setLayoutData(gd);

				label = new Label(container, SWT.NULL);
				label.setText(Messages.getString("ManageChangePage.SelectVariable")); //$NON-NLS-1$

				createNewValueSelectVariable();
				newValueSelectVariable.setLayoutData(gd);

				createNewVariableButton();
				createNewVariable.setLayoutData(gd);

				//Button to add a variable to expression
				addVariableToExpression = new Button(container, SWT.PUSH);
				addVariableToExpression.setText("Add Variable to Failure List");
				addVariableToExpression.setEnabled(false);
				addVariableToExpression.addSelectionListener(new SelectionListener() {

					@Override
					public void widgetSelected(SelectionEvent e) {
						if (newValueSelectVariable.getText().isEmpty())
							setErrorMessage("Please select a variable!!");
						else {
							setErrorMessage(null);
							if (!(newValueFailureList.getText().isEmpty())) {
								newValueFailureList.insert(newValueSelectVariable.getText());
								newValueFailureList.setSelection(newValueFailureList.getText().length());
							} else {
								newValueFailureList.setText(newValueSelectVariable.getText());
								newValueFailureList.setSelection(newValueSelectVariable.getText().length());
							}
						}	
					}

					@Override
					public void widgetDefaultSelected(SelectionEvent e) {

					}

				});
				addVariableToExpression.setLayoutData(gd);

				for (int i = 1; i < 5; i++) {
					label = new Label(container, SWT.NULL);
					label.setText("");
				}
			}

			else if (((TextChange) selectedChange).getAffectedProperty().startsWith("Expression")) {

				label = new Label(container, SWT.NULL);
				label.setText(Messages.getString("ManageChangePage.EnterOrForkExp")); //$NON-NLS-1$

				//Text box to enter OR Fork Expression
				newValueOrForkExp = new Text(container, SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
				newValueOrForkExp.setEnabled(false);
				newValueOrForkExp.addListener(SWT.Verify, new Listener() {
					public void handleEvent(Event e) {
						dialogChanged();
					}
				});
				newValueOrForkExp.setLayoutData(gd);

				label = new Label(container, SWT.NULL);
				label.setText(Messages.getString("ManageChangePage.SelectVariable")); //$NON-NLS-1$

				createNewValueSelectVariable();
				newValueSelectVariable.setLayoutData(gd);

				createNewVariableButton();
				createNewVariable.setLayoutData(gd);

				//Button to add a variable to expression
				addVariableToExpression = new Button(container, SWT.PUSH);
				addVariableToExpression.setText("Add Variable to Expression");
				addVariableToExpression.setEnabled(false);
				addVariableToExpression.addSelectionListener(new SelectionListener() {

					@Override
					public void widgetSelected(SelectionEvent e) {
						if (newValueSelectVariable.getText().isEmpty())
							setErrorMessage("Please select a variable!!");
						else {
							setErrorMessage(null);
							if (!(newValueOrForkExp.getText().isEmpty())) {
								newValueOrForkExp.insert(newValueSelectVariable.getText());
								newValueOrForkExp.setSelection(newValueOrForkExp.getText().length());
							} else {
								newValueOrForkExp.setText(newValueSelectVariable.getText());
								newValueOrForkExp.setSelection(newValueSelectVariable.getText().length());
							}
						}	
					}

					@Override
					public void widgetDefaultSelected(SelectionEvent e) {

					}

				});
				addVariableToExpression.setLayoutData(gd);

				for (int i = 1; i < 7; i++) {
					label = new Label(container, SWT.NULL);
					label.setText("");
				}
			}

			else if (((TextChange) selectedChange).getAffectedProperty().equals("Failure Condition")) {

				label = new Label(container, SWT.NULL);
				label.setText(Messages.getString("ManageChangePage.EnterFailureCondition")); //$NON-NLS-1$

				//Text box to enter Failure condition of a Failure Point
				newValueFailureCondition = new Text(container, SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
				newValueFailureCondition.setEnabled(false);
				newValueFailureCondition.addListener(SWT.Verify, new Listener() {
					public void handleEvent(Event e) {
						dialogChanged();
					}
				});
				newValueFailureCondition.setLayoutData(gd);

				label = new Label(container, SWT.NULL);
				label.setText(Messages.getString("ManageChangePage.EnterFailureExpression")); //$NON-NLS-1$

				//Text box to enter Failure expression of a Failure Point
				newValueFailureExpression = new Text(container, SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
				newValueFailureExpression.setEnabled(false);
				newValueFailureExpression.addListener(SWT.Verify, new Listener() {
					public void handleEvent(Event e) {
						dialogChanged();
					}
				});
				newValueFailureExpression.setLayoutData(gd);

				label = new Label(container, SWT.NULL);
				label.setText(Messages.getString("ManageChangePage.SelectVariable")); //$NON-NLS-1$

				createNewValueSelectVariable();
				newValueSelectVariable.setLayoutData(gd);

				createNewVariableButton();
				createNewVariable.setLayoutData(gd);

				//Button to add a variable to expression
				addVariableToExpression = new Button(container, SWT.PUSH);
				addVariableToExpression.setText("Add Variable to Condition/Expression");
				addVariableToExpression.setEnabled(false);
				addVariableToExpression.addSelectionListener(new SelectionListener() {

					@Override
					public void widgetSelected(SelectionEvent e) {
						if (newValueSelectVariable.getText().isEmpty())
							setErrorMessage("Please select a variable!!");
						else {
							setErrorMessage(null);
							if ((!(newValueFailureExpression.getText().isEmpty())) && newValueFailureExpression.isEnabled()) {
								newValueFailureExpression.insert(newValueSelectVariable.getText());
								newValueFailureExpression.setSelection(newValueFailureExpression.getText().length());
							} else {
								if (newValueFailureExpression.isEnabled()) {
									newValueFailureExpression.setText(newValueSelectVariable.getText());
									newValueFailureExpression.setSelection(newValueSelectVariable.getText().length());
								}
							}

							if ((!(newValueFailureCondition.getText().isEmpty())) && newValueFailureCondition.isEnabled()) {
								newValueFailureCondition.insert(newValueSelectVariable.getText());
								newValueFailureCondition.setSelection(newValueFailureCondition.getText().length());
							} else {
								if (newValueFailureCondition.isEnabled()) {
									newValueFailureCondition.setText(newValueSelectVariable.getText());
									newValueFailureCondition.setSelection(newValueSelectVariable.getText().length());
								}
							}
						}	
					}

					@Override
					public void widgetDefaultSelected(SelectionEvent e) {

					}
				});
				addVariableToExpression.setLayoutData(gd);

				for (int i = 1; i < 5; i++) {
					label = new Label(container, SWT.NULL);
					label.setText("");
				}
			}

			else if (((TextChange) selectedChange).getAffectedProperty().equals("Post-Condition")) {

				label = new Label(container, SWT.NULL);
				label.setText(Messages.getString("ManageChangePage.EnterEndPtPostCond")); //$NON-NLS-1$

				//Text box to enter End Point Post-Condition
				newValueEndPtPostCond = new Text(container, SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
				newValueEndPtPostCond.setEnabled(false);
				newValueEndPtPostCond.addListener(SWT.Verify, new Listener() {
					public void handleEvent(Event e) {
						dialogChanged();
					}
				});
				newValueEndPtPostCond.setLayoutData(gd);

				label = new Label(container, SWT.NULL);
				label.setText(Messages.getString("ManageChangePage.SelectVariable")); //$NON-NLS-1$

				createNewValueSelectVariable();
				newValueSelectVariable.setLayoutData(gd);

				createNewVariableButton();
				createNewVariable.setLayoutData(gd);

				//Button to add a variable to expression
				addVariableToExpression = new Button(container, SWT.PUSH);
				addVariableToExpression.setText("Add Variable to Condition");
				addVariableToExpression.setEnabled(false);
				addVariableToExpression.addSelectionListener(new SelectionListener() {

					@Override
					public void widgetSelected(SelectionEvent e) {
						if (newValueSelectVariable.getText().isEmpty())
							setErrorMessage("Please select a variable!!");
						else {
							setErrorMessage(null);
							if (!(newValueEndPtPostCond.getText().isEmpty())) {
								newValueEndPtPostCond.insert(newValueSelectVariable.getText());
								newValueEndPtPostCond.setSelection(newValueEndPtPostCond.getText().length());
							} else {
								newValueEndPtPostCond.setText(newValueSelectVariable.getText());
								newValueEndPtPostCond.setSelection(newValueSelectVariable.getText().length());
							}
						}	
					}

					@Override
					public void widgetDefaultSelected(SelectionEvent e) {

					}
				});
				addVariableToExpression.setLayoutData(gd);


				for (int i = 1; i < 7; i++) {
					label = new Label(container, SWT.NULL);
					label.setText("");
				}
			}
		}

		if (this.parent instanceof StubEditPart || this.parent instanceof PluginBinding || this.parent instanceof Stub) {

			label = new Label(container, SWT.NULL);
			label.setText(Messages.getString("ManageChangePage.SelectBooleanforDynamicStub")); //$NON-NLS-1$

			//Combo to select booleanValue for Dynamic Stub
			newValueDynamicStub = new Combo(container, SWT.DROP_DOWN | SWT.READ_ONLY);
			String[] dynamicStub = {"true", "false"};
			newValueDynamicStub.setItems(dynamicStub);
			newValueDynamicStub.setEnabled(false);
			newValueDynamicStub.addListener(SWT.Verify, new Listener() {
				public void handleEvent(Event e) {
					dialogChanged();
				}
			});
			newValueDynamicStub.setLayoutData(gd);

			label = new Label(container, SWT.NULL);
			label.setText(Messages.getString("ManageChangePage.SelectBooleanforSyncStub")); //$NON-NLS-1$

			//Combo to select booleanValue for Sync Stub
			newValueSyncStub = new Combo(container, SWT.DROP_DOWN | SWT.READ_ONLY);
			String[] syncStub = {"true", "false"};
			newValueSyncStub.setItems(syncStub);
			newValueSyncStub.setEnabled(false);
			newValueSyncStub.addListener(SWT.Verify, new Listener() {
				public void handleEvent(Event e) {
					dialogChanged();
				}
			});
			newValueSyncStub.setLayoutData(gd);

			label = new Label(container, SWT.NULL);
			label.setText(Messages.getString("ManageChangePage.SelectBooleanforBlockStub")); //$NON-NLS-1$

			//Combo to select booleanValue for Block Stub
			newValueBlockStub = new Combo(container, SWT.DROP_DOWN | SWT.READ_ONLY);
			String[] blockStub = {"true", "false"};
			newValueBlockStub.setItems(blockStub);
			newValueBlockStub.setEnabled(false);
			newValueBlockStub.addListener(SWT.Verify, new Listener() {
				public void handleEvent(Event e) {
					dialogChanged();
				}
			});
			newValueBlockStub.setLayoutData(gd);

			label = new Label(container, SWT.NULL);
			label.setText(Messages.getString("ManageChangePage.EnterReplicationFactor")); //$NON-NLS-1$

			//Text box to enter Replication Factor of a PluginBinding
			newValueRepFactor = new Text(container, SWT.BORDER);
			newValueRepFactor.setEnabled(false);
			newValueRepFactor.addListener(SWT.Verify, new Listener() {
				public void handleEvent(Event e) {
					dialogChanged();
				}
			});
			newValueRepFactor.setLayoutData(gd);

			label = new Label(container, SWT.NULL);
			label.setText(Messages.getString("ManageChangePage.EnterPreConditionPluginBinding")); //$NON-NLS-1$

			//Text box to enter Pre-Condition of a PluginBinding
			newValuePluginBindingPreCond = new Text(container, SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
			newValuePluginBindingPreCond.setEnabled(false);
			newValuePluginBindingPreCond.addListener(SWT.Verify, new Listener() {
				public void handleEvent(Event e) {
					dialogChanged();
				}
			});
			newValuePluginBindingPreCond.setLayoutData(gd);

			label = new Label(container, SWT.NULL);
			label.setText(Messages.getString("ManageChangePage.SelectVariable")); //$NON-NLS-1$

			createNewValueSelectVariable();
			newValueSelectVariable.setLayoutData(gd);

			createNewVariableButton();
			createNewVariable.setLayoutData(gd);

			//Button to add a variable to expression
			addVariableToExpression = new Button(container, SWT.PUSH);
			addVariableToExpression.setText("Add Variable to Condition");
			addVariableToExpression.setEnabled(false);
			addVariableToExpression.addSelectionListener(new SelectionListener() {

				@Override
				public void widgetSelected(SelectionEvent e) {
					if (newValueSelectVariable.getText().isEmpty())
						setErrorMessage("Please select a variable!!");
					else {
						setErrorMessage(null);
						if (!(newValuePluginBindingPreCond.getText().isEmpty())) {
							newValuePluginBindingPreCond.insert(newValueSelectVariable.getText());
							newValuePluginBindingPreCond.setSelection(newValuePluginBindingPreCond.getText().length());
						} else {
							newValuePluginBindingPreCond.setText(newValueSelectVariable.getText());
							newValuePluginBindingPreCond.setSelection(newValueSelectVariable.getText().length());
						}
					}	
				}

				@Override
				public void widgetDefaultSelected(SelectionEvent e) {

				}

			});
			addVariableToExpression.setLayoutData(gd);

			for (int i = 1; i < 3; i++) {
				label = new Label(container, SWT.NULL);
				label.setText("");
			}
		} else if (this.parent instanceof RespRefEditPart || this.parent instanceof Responsibility) {

			label = new Label(container, SWT.NULL);
			label.setText(Messages.getString("ManageChangePage.EnterRespExpression")); //$NON-NLS-1$

			//TextArea box to enter Responsibility Expression
			newValueRespExpression = new Text(container, SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
			newValueRespExpression.setEnabled(false);
			newValueRespExpression.addListener(SWT.Verify, new Listener() {
				public void handleEvent(Event e) {
					dialogChanged();
				}
			});
			newValueRespExpression.setLayoutData(new GridData(gd.FILL_BOTH));

			label = new Label(container, SWT.NULL);
			label.setText(Messages.getString("ManageChangePage.SelectVariable")); //$NON-NLS-1$

			createNewValueSelectVariable();
			newValueSelectVariable.setLayoutData(gd);

			createNewVariableButton();
			createNewVariable.setLayoutData(gd);

			//Button to add a variable to expression
			addVariableToExpression = new Button(container, SWT.PUSH);
			addVariableToExpression.setText("Add Variable to Expression");
			addVariableToExpression.setEnabled(false);
			addVariableToExpression.addSelectionListener(new SelectionListener() {

				@Override
				public void widgetSelected(SelectionEvent e) {
					if (newValueSelectVariable.getText().isEmpty())
						setErrorMessage("Please select a variable!!");
					else {
						setErrorMessage(null);
						if (!(newValueRespExpression.getText().isEmpty())) {
							newValueRespExpression.insert(newValueSelectVariable.getText());
							newValueRespExpression.setSelection(newValueRespExpression.getText().length());
						} else {
							newValueRespExpression.setText(newValueSelectVariable.getText());
							newValueRespExpression.setSelection(newValueSelectVariable.getText().length());
						}
					}	
				}

				@Override
				public void widgetDefaultSelected(SelectionEvent e) {

				}
			});
			addVariableToExpression.setLayoutData(gd);

			for (int i = 1; i < 7; i++) {
				label = new Label(container, SWT.NULL);
				label.setText("");
			}
		} else if (this.parent instanceof PathNodeEditPart) {
			if ((((PathNodeEditPart) this.parent).getModel()) instanceof FailurePoint) { 

				label = new Label(container, SWT.NULL);
				label.setText(Messages.getString("ManageChangePage.EnterFailureCondition")); //$NON-NLS-1$

				//Text box to enter Failure condition of a Failure Point
				newValueFailureCondition = new Text(container, SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
				newValueFailureCondition.setEnabled(false);
				newValueFailureCondition.addListener(SWT.Verify, new Listener() {
					public void handleEvent(Event e) {
						dialogChanged();
					}
				});
				newValueFailureCondition.setLayoutData(gd);

				label = new Label(container, SWT.NULL);
				label.setText(Messages.getString("ManageChangePage.EnterFailureExpression")); //$NON-NLS-1$

				//Text box to enter Failure expression of a Failure Point
				newValueFailureExpression = new Text(container, SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
				newValueFailureExpression.setEnabled(false);
				newValueFailureExpression.addListener(SWT.Verify, new Listener() {
					public void handleEvent(Event e) {
						dialogChanged();
					}
				});
				newValueFailureExpression.setLayoutData(gd);

				label = new Label(container, SWT.NULL);
				label.setText(Messages.getString("ManageChangePage.SelectVariable")); //$NON-NLS-1$

				createNewValueSelectVariable();
				newValueSelectVariable.setLayoutData(gd);

				createNewVariableButton();
				createNewVariable.setLayoutData(gd);

				//Button to add a variable to expression
				addVariableToExpression = new Button(container, SWT.PUSH);
				addVariableToExpression.setText("Add Variable to Expression/Condition");
				addVariableToExpression.setEnabled(false);
				addVariableToExpression.addSelectionListener(new SelectionListener() {

					@Override
					public void widgetSelected(SelectionEvent e) {
						if (newValueSelectVariable.getText().isEmpty())
							setErrorMessage("Please select a variable!!");
						else {
							setErrorMessage(null);
							if ((!(newValueFailureExpression.getText().isEmpty())) && newValueFailureExpression.isEnabled()) {
								newValueFailureExpression.insert(newValueSelectVariable.getText());
								newValueFailureExpression.setSelection(newValueFailureExpression.getText().length());
							} else {
								if (newValueFailureExpression.isEnabled()) {
									newValueFailureExpression.setText(newValueSelectVariable.getText());
									newValueFailureExpression.setSelection(newValueSelectVariable.getText().length());
								}
							}

							if ((!(newValueFailureCondition.getText().isEmpty())) && newValueFailureCondition.isEnabled()) {
								newValueFailureCondition.insert(newValueSelectVariable.getText());
								newValueFailureCondition.setSelection(newValueFailureCondition.getText().length());
							} else {
								if (newValueFailureCondition.isEnabled()) {
									newValueFailureCondition.setText(newValueSelectVariable.getText());
									newValueFailureCondition.setSelection(newValueSelectVariable.getText().length());
								}
							}
						}	
					}

					@Override
					public void widgetDefaultSelected(SelectionEvent e) {


					}

				});
				addVariableToExpression.setLayoutData(gd);

				for (int i = 1; i < 5; i++) {
					label = new Label(container, SWT.NULL);
					label.setText("");
				}
			} else if ((((PathNodeEditPart) this.parent).getModel()) instanceof StartPoint) { 

				label = new Label(container, SWT.NULL);
				label.setText(Messages.getString("ManageChangePage.SelectFailureKind")); //$NON-NLS-1$

				//Combo to select newValue for Failure Kind
				newValueFailureKind = new Combo(container, SWT.DROP_DOWN | SWT.READ_ONLY);
				String[] failureKinds = {"FAILURE", "ABORT", "NONE"};
				newValueFailureKind.setItems(failureKinds);
				newValueFailureKind.setEnabled(false);
				newValueFailureKind.addListener(SWT.Verify, new Listener() {
					public void handleEvent(Event e) {
						dialogChanged();
					}
				});
				newValueFailureKind.setLayoutData(gd);

				label = new Label(container, SWT.NULL);
				label.setText(Messages.getString("ManageChangePage.EnterFailureList/StartPointPreCondition")); //$NON-NLS-1$

				//Text box to enter Failure List/Start Point Pre-Condition
				newValueFailureList = new Text(container, SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
				newValueFailureList.setEnabled(false);
				newValueFailureList.addListener(SWT.Verify, new Listener() {
					public void handleEvent(Event e) {
						dialogChanged();
					}
				});
				newValueFailureList.setLayoutData(gd);

				label = new Label(container, SWT.NULL);
				label.setText(Messages.getString("ManageChangePage.SelectVariable")); //$NON-NLS-1$

				createNewValueSelectVariable();
				newValueSelectVariable.setLayoutData(gd);

				createNewVariableButton();
				createNewVariable.setLayoutData(gd);

				//Button to add a variable to expression
				addVariableToExpression = new Button(container, SWT.PUSH);
				addVariableToExpression.setText("Add Variable to Failure List");
				addVariableToExpression.setEnabled(false);
				addVariableToExpression.addSelectionListener(new SelectionListener() {

					@Override
					public void widgetSelected(SelectionEvent e) {
						if (newValueSelectVariable.getText().isEmpty())
							setErrorMessage("Please select a variable!!");
						else {
							setErrorMessage(null);
							if (!(newValueFailureList.getText().isEmpty())) {
								newValueFailureList.insert(newValueSelectVariable.getText());
								newValueFailureList.setSelection(newValueFailureList.getText().length());
							} else {
								newValueFailureList.setText(newValueSelectVariable.getText());
								newValueFailureList.setSelection(newValueSelectVariable.getText().length());
							}
						}	
					}

					@Override
					public void widgetDefaultSelected(SelectionEvent e) {

					}

				});
				addVariableToExpression.setLayoutData(gd);

				for (int i = 1; i < 5; i++) {
					label = new Label(container, SWT.NULL);
					label.setText("");
				}

			} else if ((((PathNodeEditPart) (ManageChangeEditorPage.this.parent)).getModel() instanceof Timer) || (((PathNodeEditPart) (ManageChangeEditorPage.this.parent)).getModel() instanceof WaitingPlace)) {

				label = new Label(container, SWT.NULL);
				label.setText(Messages.getString("ManageChangePage.SelectWaitKind")); //$NON-NLS-1$

				//Combo to select newValue for Wait Kind
				newValueWaitKind = new Combo(container, SWT.DROP_DOWN | SWT.READ_ONLY);
				String[] waitKinds = {"TRANSIENT", "PERSISTENT"};
				newValueWaitKind.setItems(waitKinds);
				newValueWaitKind.setEnabled(false);
				newValueWaitKind.addListener(SWT.Verify, new Listener() {
					public void handleEvent(Event e) {
						dialogChanged();
					}
				});
				newValueWaitKind.setLayoutData(gd);

				for (int i = 1; i < 11; i++) {
					label = new Label(container, SWT.NULL);
					label.setText("");
				}

			} else if (((PathNodeEditPart) this.parent).getModel() instanceof OrFork) {

				label = new Label(container, SWT.NULL);
				label.setText(Messages.getString("ManageChangePage.EnterOrForkExp")); //$NON-NLS-1$

				//Text box to enter OR Fork Expression
				newValueOrForkExp = new Text(container, SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
				newValueOrForkExp.setEnabled(false);
				newValueOrForkExp.addListener(SWT.Verify, new Listener() {
					public void handleEvent(Event e) {
						dialogChanged();
					}
				});
				newValueOrForkExp.setLayoutData(gd);

				label = new Label(container, SWT.NULL);
				label.setText(Messages.getString("ManageChangePage.SelectVariable")); //$NON-NLS-1$

				createNewValueSelectVariable();
				newValueSelectVariable.setLayoutData(gd);

				createNewVariableButton();
				createNewVariable.setLayoutData(gd);

				//Button to add a variable to expression
				addVariableToExpression = new Button(container, SWT.PUSH);
				addVariableToExpression.setText("Add Variable to Expression");
				addVariableToExpression.setEnabled(false);
				addVariableToExpression.addSelectionListener(new SelectionListener() {

					@Override
					public void widgetSelected(SelectionEvent e) {
						if (newValueSelectVariable.getText().isEmpty())
							setErrorMessage("Please select a variable!!");
						else {
							setErrorMessage(null);
							if (!(newValueOrForkExp.getText().isEmpty())) {
								newValueOrForkExp.insert(newValueSelectVariable.getText());
								newValueOrForkExp.setSelection(newValueOrForkExp.getText().length());
							} else {
								newValueOrForkExp.setText(newValueSelectVariable.getText());
								newValueOrForkExp.setSelection(newValueSelectVariable.getText().length());
							}
						}	
					}

					@Override
					public void widgetDefaultSelected(SelectionEvent e) {

					}

				});
				addVariableToExpression.setLayoutData(gd);

				for (int i = 1; i < 7; i++) {
					label = new Label(container, SWT.NULL);
					label.setText("");
				}
			} else if (((PathNodeEditPart) this.parent).getModel() instanceof EndPoint) {

				label = new Label(container, SWT.NULL);
				label.setText(Messages.getString("ManageChangePage.EnterEndPtPostCond")); //$NON-NLS-1$

				//Text box to enter End Point Post-Condition
				newValueEndPtPostCond = new Text(container, SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
				newValueEndPtPostCond.setEnabled(false);
				newValueEndPtPostCond.addListener(SWT.Verify, new Listener() {
					public void handleEvent(Event e) {
						dialogChanged();
					}
				});
				newValueEndPtPostCond.setLayoutData(gd);

				label = new Label(container, SWT.NULL);
				label.setText(Messages.getString("ManageChangePage.SelectVariable")); //$NON-NLS-1$

				createNewValueSelectVariable();
				newValueSelectVariable.setLayoutData(gd);

				createNewVariableButton();
				createNewVariable.setLayoutData(gd);

				//Button to add a variable to expression
				addVariableToExpression = new Button(container, SWT.PUSH);
				addVariableToExpression.setText("Add Variable to Condition");
				addVariableToExpression.setEnabled(false);
				addVariableToExpression.addSelectionListener(new SelectionListener() {

					@Override
					public void widgetSelected(SelectionEvent e) {
						if (newValueSelectVariable.getText().isEmpty())
							setErrorMessage("Please select a variable!!");
						else {
							setErrorMessage(null);
							if (!(newValueEndPtPostCond.getText().isEmpty())) {
								newValueEndPtPostCond.insert(newValueSelectVariable.getText());
								newValueEndPtPostCond.setSelection(newValueEndPtPostCond.getText().length());
							} else {
								newValueEndPtPostCond.setText(newValueSelectVariable.getText());
								newValueEndPtPostCond.setSelection(newValueSelectVariable.getText().length());
							}
						}	
					}

					@Override
					public void widgetDefaultSelected(SelectionEvent e) {

					}

				});
				addVariableToExpression.setLayoutData(gd);


				for (int i = 1; i < 7; i++) {
					label = new Label(container, SWT.NULL);
					label.setText("");
				}
			}
		} else if (this.parent instanceof FailurePoint) {

			label = new Label(container, SWT.NULL);
			label.setText(Messages.getString("ManageChangePage.EnterFailureCondition")); //$NON-NLS-1$

			//Text box to enter Failure condition of a Failure Point
			newValueFailureCondition = new Text(container, SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
			newValueFailureCondition.setEnabled(false);
			newValueFailureCondition.addListener(SWT.Verify, new Listener() {
				public void handleEvent(Event e) {
					dialogChanged();
				}
			});
			newValueFailureCondition.setLayoutData(gd);

			label = new Label(container, SWT.NULL);
			label.setText(Messages.getString("ManageChangePage.EnterFailureExpression")); //$NON-NLS-1$

			//Text box to enter Failure expression of a Failure Point
			newValueFailureExpression = new Text(container, SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
			newValueFailureExpression.setEnabled(false);
			newValueFailureExpression.addListener(SWT.Verify, new Listener() {
				public void handleEvent(Event e) {
					dialogChanged();
				}
			});
			newValueFailureExpression.setLayoutData(gd);

			label = new Label(container, SWT.NULL);
			label.setText(Messages.getString("ManageChangePage.SelectVariable")); //$NON-NLS-1$

			createNewValueSelectVariable();
			newValueSelectVariable.setLayoutData(gd);

			createNewVariableButton();
			createNewVariable.setLayoutData(gd);

			//Button to add a variable to expression
			addVariableToExpression = new Button(container, SWT.PUSH);
			addVariableToExpression.setText("Add Variable to Expression/Condition");
			addVariableToExpression.setEnabled(false);
			addVariableToExpression.addSelectionListener(new SelectionListener() {

				public void widgetSelected(SelectionEvent e) {
					if (newValueSelectVariable.getText().isEmpty())
						setErrorMessage("Please select a variable!!");
					else {
						setErrorMessage(null);
						if ((!(newValueFailureExpression.getText().isEmpty())) && newValueFailureExpression.isEnabled()) {
							newValueFailureExpression.insert(newValueSelectVariable.getText());
							newValueFailureExpression.setSelection(newValueFailureExpression.getText().length());
						} else {
							if (newValueFailureExpression.isEnabled()) {
								newValueFailureExpression.setText(newValueSelectVariable.getText());
								newValueFailureExpression.setSelection(newValueSelectVariable.getText().length());
							}
						}

						if ((!(newValueFailureCondition.getText().isEmpty())) && newValueFailureCondition.isEnabled()) {
							newValueFailureCondition.insert(newValueSelectVariable.getText());
							newValueFailureCondition.setSelection(newValueFailureCondition.getText().length());
						} else {
							if (newValueFailureCondition.isEnabled()) {
								newValueFailureCondition.setText(newValueSelectVariable.getText());
								newValueFailureCondition.setSelection(newValueSelectVariable.getText().length());
							}
						}
					}	
				}

				@Override
				public void widgetDefaultSelected(SelectionEvent e) {

				}

			});
			addVariableToExpression.setLayoutData(gd);

			for (int i = 1; i < 5; i++) {
				label = new Label(container, SWT.NULL);
				label.setText("");
			}

		} else if (this.parent instanceof StartPoint) {

			label = new Label(container, SWT.NULL);
			label.setText(Messages.getString("ManageChangePage.SelectFailureKind")); //$NON-NLS-1$

			//Combo to select newValue for Failure Kind
			newValueFailureKind = new Combo(container, SWT.DROP_DOWN | SWT.READ_ONLY);
			String[] failureKinds = {"FAILURE", "ABORT", "NONE"};
			newValueFailureKind.setItems(failureKinds);
			newValueFailureKind.setEnabled(false);
			newValueFailureKind.addListener(SWT.Verify, new Listener() {
				public void handleEvent(Event e) {
					dialogChanged();
				}
			});
			newValueFailureKind.setLayoutData(gd);

			label = new Label(container, SWT.NULL);
			label.setText(Messages.getString("ManageChangePage.EnterFailureList/StartPointPreCondition")); //$NON-NLS-1$

			//Text box to enter Failure List/Start Point Pre-Condition
			newValueFailureList = new Text(container, SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
			newValueFailureList.setEnabled(false);
			newValueFailureList.addListener(SWT.Verify, new Listener() {
				public void handleEvent(Event e) {
					dialogChanged();
				}
			});
			newValueFailureList.setLayoutData(gd);

			label = new Label(container, SWT.NULL);
			label.setText(Messages.getString("ManageChangePage.SelectVariable")); //$NON-NLS-1$

			createNewValueSelectVariable();
			newValueSelectVariable.setLayoutData(gd);

			createNewVariableButton();
			createNewVariable.setLayoutData(gd);

			//Button to add a variable to expression
			addVariableToExpression = new Button(container, SWT.PUSH);
			addVariableToExpression.setText("Add Variable to Failure List");
			addVariableToExpression.setEnabled(false);
			addVariableToExpression.addSelectionListener(new SelectionListener() {

				@Override
				public void widgetSelected(SelectionEvent e) {
					if (newValueSelectVariable.getText().isEmpty())
						setErrorMessage("Please select a variable!!");
					else {
						setErrorMessage(null);
						if (!(newValueFailureList.getText().isEmpty())) {
							newValueFailureList.insert(newValueSelectVariable.getText());
							newValueFailureList.setSelection(newValueFailureList.getText().length());
						} else {
							newValueFailureList.setText(newValueSelectVariable.getText());
							newValueFailureList.setSelection(newValueSelectVariable.getText().length());
						}
					}	
				}

				@Override
				public void widgetDefaultSelected(SelectionEvent e) {

				}

			});
			addVariableToExpression.setLayoutData(gd);

			for (int i = 1; i < 5; i++) {
				label = new Label(container, SWT.NULL);
				label.setText("");
			}

		} else if (this.parent instanceof Timer || this.parent instanceof WaitingPlace) {

			label = new Label(container, SWT.NULL);
			label.setText(Messages.getString("ManageChangePage.SelectWaitKind")); //$NON-NLS-1$

			//Combo to select newValue for Wait Kind
			newValueWaitKind = new Combo(container, SWT.DROP_DOWN | SWT.READ_ONLY);
			String[] waitKinds = {"TRANSIENT", "PERSISTENT"};
			newValueWaitKind.setItems(waitKinds);
			newValueWaitKind.setEnabled(false);
			newValueWaitKind.addListener(SWT.Verify, new Listener() {
				public void handleEvent(Event e) {
					dialogChanged();
				}
			});
			newValueWaitKind.setLayoutData(gd);

			for (int i = 1; i < 11; i++) {
				label = new Label(container, SWT.NULL);
				label.setText("");
			}

		} else if (this.parent instanceof ComponentRefEditPart || this.parent instanceof Component) {

			label = new Label(container, SWT.NULL);
			label.setText(Messages.getString("ManageChangePage.SelectComponentKind")); //$NON-NLS-1$

			//Combo to select newValue for Component Kind
			newValueCompKind = new Combo(container, SWT.DROP_DOWN | SWT.READ_ONLY);
			String[] compKinds = {"TEAM", "OBJECT", "PROCESS", "AGENT", "ACTOR", "OTHER"};
			newValueCompKind.setItems(compKinds);
			newValueCompKind.setEnabled(false);
			newValueCompKind.addListener(SWT.Verify, new Listener() {
				public void handleEvent(Event e) {
					dialogChanged();
				}
			});
			newValueCompKind.setLayoutData(gd);

			label = new Label(container, SWT.NULL);
			label.setText(Messages.getString("ManageChangePage.SelectComponentProtection")); //$NON-NLS-1$

			//Combo to select booleanValue for 'protected' property of Component
			newValueCompProtection = new Combo(container, SWT.DROP_DOWN | SWT.READ_ONLY);
			String[] compProtection = {"true", "false"};
			newValueCompProtection.setItems(compProtection);
			newValueCompProtection.setEnabled(false);
			newValueCompProtection.addListener(SWT.Verify, new Listener() {
				public void handleEvent(Event e) {
					dialogChanged();
				}
			});
			newValueCompProtection.setLayoutData(gd);

			label = new Label(container, SWT.NULL);
			label.setText(Messages.getString("ManageChangePage.SelectComponentContext")); //$NON-NLS-1$

			//Combo to select booleanValue for 'context' property of Component
			newValueCompContext = new Combo(container, SWT.DROP_DOWN | SWT.READ_ONLY);
			String[] compContext = {"true", "false"};
			newValueCompContext.setItems(compContext);
			newValueCompContext.setEnabled(false);
			newValueCompContext.addListener(SWT.Verify, new Listener() {
				public void handleEvent(Event e) {
					dialogChanged();
				}
			});
			newValueCompContext.setLayoutData(gd);

			for (int i = 1; i < 7; i++) {
				label = new Label(container, SWT.NULL);
				label.setText("");
			}

		} 

		if (this.parent instanceof GRLmodelElement || this.parent instanceof ActorRefEditPart
				|| this.parent instanceof IntentionalElementEditPart || this.parent instanceof LinkRefEditPart) {

        label = new Label(container, SWT.NULL);
        label.setText(Messages.getString("ManageChangePage.EnterNewValue")); //$NON-NLS-1$
        
        //Text box to enter newValue
        newValueText = new Text(container, SWT.BORDER);
        newValueText.setEnabled(false);
        newValueText.addListener(SWT.Verify, new Listener() {
        	public void handleEvent(Event e) {
                String string = e.text;
                char[] chars = new char[string.length()];
                string.getChars(0, chars.length, chars, 0);
                for (int i = 0; i < chars.length; i++) {
                  if (!(('0' <= chars[i] && chars[i] <= '9') || chars[i] == '-')) {
                    e.doit = false;
                    return;
                  }
                }
              }
		});
        gd = new GridData();
        gd.horizontalAlignment = GridData.FILL;
 		gd.horizontalSpan = 1;
 		newValueText.setLayoutData(gd);
        
        label = new Label(container, SWT.NULL);
        label.setText(Messages.getString("ManageChangePage.SelectDecompType")); //$NON-NLS-1$
        
        //Combo to select newValue for Decomposition
        newValueDecomp = new Combo(container, SWT.DROP_DOWN | SWT.READ_ONLY);
        String[] decompTypes = {"AND", "OR", "XOR"};
        newValueDecomp.setItems(decompTypes);
        newValueDecomp.setEnabled(false);
        newValueDecomp.addListener(SWT.Verify, new Listener() {
        	public void handleEvent(Event e) {
        		dialogChanged();
              }
		});
        newValueDecomp.setLayoutData(gd);
        
        label = new Label(container, SWT.NULL);
        label.setText(Messages.getString("ManageChangePage.EnterQuadraticCoeff")); //$NON-NLS-1$
        
        //Text box to enter quadraticCoefficient
        newValueQuad = new Text(container, SWT.BORDER);
        newValueQuad.setEnabled(false);
        newValueQuad.addListener(SWT.Verify, new Listener() {
        	public void handleEvent(Event e) {
                String string = e.text;
                char[] chars = new char[string.length()];
                string.getChars(0, chars.length, chars, 0);
                for (int i = 0; i < chars.length; i++) {
                  if (!(('0' <= chars[i] && chars[i] <= '9') || chars[i] == '-' || chars[i] == '.')) {
                    e.doit = false;
                    return;
                  }
                }
              }
		});
        newValueQuad.setLayoutData(gd);
        
        label = new Label(container, SWT.NULL);
        label.setText(Messages.getString("ManageChangePage.EnterLinearCoeff")); //$NON-NLS-1$
        
        //Text box to enter linearCoefficient
        newValueLin = new Text(container, SWT.BORDER);
        newValueLin.setEnabled(false);
        newValueLin.addListener(SWT.Verify, new Listener() {
        	public void handleEvent(Event e) {
                String string = e.text;
                char[] chars = new char[string.length()];
                string.getChars(0, chars.length, chars, 0);
                for (int i = 0; i < chars.length; i++) {
                  if (!(('0' <= chars[i] && chars[i] <= '9') || chars[i] == '-' || chars[i] == '.')) {
                    e.doit = false;
                    return;
                  }
                }
              }
		});
        newValueLin.setLayoutData(gd);
        
        label = new Label(container, SWT.NULL);
        label.setText(Messages.getString("ManageChangePage.EnterConstant")); //$NON-NLS-1$
        
        //Text box to enter constant
        newValueCon = new Text(container, SWT.BORDER);
        newValueCon.setEnabled(false);
        newValueCon.addListener(SWT.Verify, new Listener() {
        	public void handleEvent(Event e) {
                String string = e.text;
                char[] chars = new char[string.length()];
                string.getChars(0, chars.length, chars, 0);
                for (int i = 0; i < chars.length; i++) {
                  if (!(('0' <= chars[i] && chars[i] <= '9') || chars[i] == '-' || chars[i] == '.')) {
                    e.doit = false;
                    return;
                  }
                }
              }
		});
        newValueCon.setLayoutData(gd);
        
        label = new Label(container, SWT.NULL);
        label.setText(Messages.getString("ManageChangePage.EnterFormula")); //$NON-NLS-1$
        
        //Text box to enter formula
        newValueFor = new Text(container, SWT.BORDER);
        newValueFor.setEnabled(false);
        newValueFor.addListener(SWT.Verify, new Listener() {
        	public void handleEvent(Event e) {
                
              }
		});
        newValueFor.setLayoutData(gd);
		}
        //Button to add change
        addButton = new Button(container, SWT.PUSH);
        addButton.setText("Add Change");
        addButton.addSelectionListener(new SelectionListener() {
			
        	@Override
			public void widgetSelected(SelectionEvent e) {
				dialogChanged();
				if (ManageChangeEditorPage.this.parent instanceof UCMmodelElement || ManageChangeEditorPage.this.parent instanceof ComponentRefEditPart
						|| ManageChangeEditorPage.this.parent instanceof RespRefEditPart || ManageChangeEditorPage.this.parent instanceof PathNodeEditPart
						|| ManageChangeEditorPage.this.parent instanceof PluginBinding || ManageChangeEditorPage.this.parent instanceof urncore.Condition) {
				if (currentDynContext == null)
					setErrorMessage("Please select a Dynamic Context");
				else if (ManageChangeEditorPage.this.parent == null)
					setErrorMessage("No element selected!!");
				else if (affectedProperty == null)
					setErrorMessage("Please select the affected attribute of the selected element!!");
				else if (changeType == null)
					setErrorMessage("Please select a change type!!");
				else if (!startDate.before(endDate))
					setErrorMessage("Start Date should be before End Date!!");
				else if (checkForOverlap() != true)
					setErrorMessage("This change overlaps with existing change!!");
					else if (ManageChangeEditorPage.this.parent instanceof ComponentRefEditPart || ManageChangeEditorPage.this.parent instanceof Component) {
						if (changeType.equals("Enumeration Change") && newValueCompKind.getSelectionIndex() < 0)
							setErrorMessage("Please select a Component Kind!!");
						else if (changeType.equals("Boolean Change") && newValueCompProtection.getSelectionIndex() < 0 && newValueCompProtection.getEnabled() == true)
							setErrorMessage("Please select a boolean value for Component Protection!!");
						else if (changeType.equals("Boolean Change") && newValueCompContext.getSelectionIndex() < 0 && newValueCompContext.getEnabled() == true)
							setErrorMessage("Please select a boolean value for Component Context!!");
						else {
							Change addedChange = addChange();
							TableItem item = new TableItem(availableChanges, SWT.NONE);
							DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
							if (changeType.equals("Deactivation Change"))
								item.setText(new String[] {affectedProperty, df.format(addedChange.getStart()), 
										df.format(addedChange.getEnd()), changeType, "", ""});
							else if (changeType.equals("Enumeration Change"))
								item.setText(new String[] {((EnumChange) addedChange).getAffectedProperty(), df.format(addedChange.getStart()), 
										df.format(addedChange.getEnd()), changeType, ((EnumChange) addedChange).getNewValue(), ""});
							else if (changeType.equals("Boolean Change")) {
								String value = Boolean.toString(((BooleanChange) addedChange).isNewValue());
								item.setText(new String[] {((BooleanChange) addedChange).getAffectedProperty(), df.format(addedChange.getStart()), 
										df.format(addedChange.getEnd()), changeType, value, ""});
							}
							else if (changeType.equals("Text Change"))
								item.setText(new String[] {((TextChange) addedChange).getAffectedProperty(), df.format(addedChange.getStart()), 
										df.format(addedChange.getEnd()), changeType, ((TextChange) addedChange).getNewValue(), ""});
						}
					}
					else if (changeType.equals("Enumeration Change") && ManageChangeEditorPage.this.parent instanceof PathNodeEditPart) {
						if (((PathNodeEditPart) (ManageChangeEditorPage.this.parent)).getModel() instanceof StartPoint) {
							if (newValueFailureKind.getSelectionIndex() < 0)
								setErrorMessage("Please select a Failure Kind!!");
							else {
								Change addedChange = addChange();
								TableItem item = new TableItem(availableChanges, SWT.NONE);
								DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
								if (changeType.equals("Enumeration Change"))
									item.setText(new String[] {((EnumChange) addedChange).getAffectedProperty(), df.format(addedChange.getStart()), 
											df.format(addedChange.getEnd()), changeType, ((EnumChange) addedChange).getNewValue(), ""});
							}
						} else if (((PathNodeEditPart) (ManageChangeEditorPage.this.parent)).getModel() instanceof Timer || ((PathNodeEditPart) (ManageChangeEditorPage.this.parent)).getModel() instanceof WaitingPlace) {
							if (newValueWaitKind.getSelectionIndex() < 0 && newValueWaitKind.getEnabled() == true)
								setErrorMessage("Please select a Wait Kind!!");
							else {
								Change addedChange = addChange();
								TableItem item = new TableItem(availableChanges, SWT.NONE);
								DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
								if (changeType.equals("Enumeration Change"))
									item.setText(new String[] {((EnumChange) addedChange).getAffectedProperty(), df.format(addedChange.getStart()), 
											df.format(addedChange.getEnd()), changeType, ((EnumChange) addedChange).getNewValue(), ""});
							}

						}
					}
					else if (ManageChangeEditorPage.this.parent instanceof urncore.Condition && changeType.equals("Text Change")) {
						if (affectedProperty.startsWith("Pre-Condition") && newValueSelectVariable.getItemCount() == 0 && newValueSelectVariable.getText().isEmpty() && newValueSelectVariable.isEnabled() == true)
							setErrorMessage("Please create a new Variable!!");
						else if (affectedProperty.startsWith("Expression") && newValueSelectVariable.getItemCount() == 0 && newValueSelectVariable.getText().isEmpty() && newValueSelectVariable.isEnabled() == true)
							setErrorMessage("Please create a new Variable!!");
						else if (affectedProperty.equals("Post-Condition") && newValueSelectVariable.getItemCount() == 0 && newValueSelectVariable.getText().isEmpty() && newValueSelectVariable.isEnabled() == true)
							setErrorMessage("Please create a new Variable!!");
						else if (affectedProperty.equals("Failure List/Start Point Pre-Condition") && newValueSelectVariable.getItemCount() == 0 && newValueSelectVariable.getText().isEmpty() && newValueSelectVariable.isEnabled() == true)
							setErrorMessage("Please create a new Variable!!");
						else if (affectedProperty.equals("Failure Condition") && newValueSelectVariable.getItemCount() == 0 && newValueSelectVariable.getText().isEmpty() && newValueSelectVariable.isEnabled() == true)
							setErrorMessage("Please create a new Variable!!");
						else if (affectedProperty.startsWith("Pre-Condition") && newValueSelectVariable.getText().isEmpty() && newValueSelectVariable.isEnabled() == true)
							setErrorMessage("Please select a Variable!!");
						else if (affectedProperty.startsWith("Expression") && newValueSelectVariable.getText().isEmpty() && newValueSelectVariable.isEnabled() == true)
							setErrorMessage("Please select a Variable!!");
						else if (affectedProperty.equals("Post-Condition") && newValueSelectVariable.getText().isEmpty() && newValueSelectVariable.isEnabled() == true)
							setErrorMessage("Please select a Variable!!");
						else if (affectedProperty.equals("Failure List/Start Point Pre-Condition") && newValueSelectVariable.getText().isEmpty() && newValueSelectVariable.isEnabled() == true)
							setErrorMessage("Please select a Variable!!");
						else if (affectedProperty.equals("Failure Condition") && newValueSelectVariable.getText().isEmpty() && newValueSelectVariable.isEnabled() == true)
							setErrorMessage("Please select a Variable!!");
						else if (affectedProperty.startsWith("Pre-Condition") && newValuePluginBindingPreCond.getText().isEmpty() && newValuePluginBindingPreCond.getEnabled() == true)
							setErrorMessage("Please enter the Pre Condition!!");
						else if (affectedProperty.equals("Post-Condition") && newValueEndPtPostCond != null && newValueEndPtPostCond.getText().isEmpty() && newValueEndPtPostCond.getEnabled() == true)
							setErrorMessage("Please enter the Post Condition!!");
						else if (affectedProperty.startsWith("Expression") && newValueOrForkExp.getText().isEmpty() && newValueOrForkExp.getEnabled() == true)
							setErrorMessage("Please enter the Expression!!");
						else if (affectedProperty.equals("Failure Condition") && newValueFailureCondition.getText().isEmpty() && newValueFailureCondition.getEnabled() == true)
							setErrorMessage("Please enter the Failure Condition!!");
						else if (affectedProperty.equals("Failure List/Start Point Pre-Condition") && newValueFailureList.getText().isEmpty() && newValueFailureList.getEnabled() == true)
							setErrorMessage("Please enter the Failure List/Start Point Pre-Condition!!");
						else if (affectedProperty.startsWith("Pre-Condition") && wrongFormat.equals(true) && newValuePluginBindingPreCond.isEnabled() == true)
							setErrorMessage(errorMessage);
						else if (affectedProperty.startsWith("Expression") && wrongFormat.equals(true) && newValueOrForkExp.getEnabled() == true)
							setErrorMessage(errorMessage);
						else if (affectedProperty.equals("Post-Condition") && wrongFormat.equals(true) && newValueEndPtPostCond.getEnabled() == true)
							setErrorMessage(errorMessage);
						else if (affectedProperty.equals("Failure List/Start Point Pre-Condition") && wrongFormat.equals(true) && newValueFailureList.getEnabled() == true)
							setErrorMessage(errorMessage);
						else if (affectedProperty.equals("Failure Condition") && wrongFormat.equals(true) && newValueFailureCondition.getEnabled() == true)
							setErrorMessage(errorMessage);
						else {
							Change addedChange = addChange();
							TableItem item = new TableItem(availableChanges, SWT.NONE);
							DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
							item.setText(new String[] {((TextChange) addedChange).getAffectedProperty(), df.format(addedChange.getStart()), 
									df.format(addedChange.getEnd()), changeType, ((TextChange) addedChange).getNewValue(), ""});
						}
					}
					else if (ManageChangeEditorPage.this.parent instanceof RespRefEditPart || ManageChangeEditorPage.this.parent instanceof Responsibility) {
						if (changeType.equals("Text Change") && newValueSelectVariable.getItemCount() == 0 && newValueSelectVariable.getText().isEmpty() && newValueSelectVariable.isEnabled() == true)
							setErrorMessage("Please create a new Variable!!");
						else if (changeType.equals("Text Change") && newValueSelectVariable.getText().isEmpty() && newValueSelectVariable.isEnabled() == true)
							setErrorMessage("Please select a Variable!!");
						else if (changeType.equals("Text Change") && newValueRespExpression.isEnabled() == true && (newValueRespExpression.getText().isEmpty() || newValueRespExpression.getText().trim() == ""))
							setErrorMessage("Please enter the Expression!!");
						else if (changeType.equals("Text Change") && wrongFormat.equals(true) && newValueRespExpression.isEnabled() == true)
							setErrorMessage(errorMessage);
						else {
							Change addedChange = addChange();
							TableItem item = new TableItem(availableChanges, SWT.NONE);
							DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
							if (changeType.equals("Deactivation Change"))
								item.setText(new String[] {affectedProperty, df.format(addedChange.getStart()), 
										df.format(addedChange.getEnd()), changeType, "", ""});
							else if (changeType.equals("Text Change"))
								item.setText(new String[] {((TextChange) addedChange).getAffectedProperty(), df.format(addedChange.getStart()), 
										df.format(addedChange.getEnd()), changeType, ((TextChange) addedChange).getNewValue(), ""});
						}
					}
					else if (ManageChangeEditorPage.this.parent instanceof PathNodeEditPart && changeType.equals("Text Change")) {
						if ((((PathNodeEditPart) (ManageChangeEditorPage.this.parent)).getModel() instanceof FailurePoint) && affectedProperty.equals("Failure Expression") && newValueSelectVariable.getItemCount() == 0 && newValueSelectVariable.getText().isEmpty() && newValueSelectVariable.isEnabled() == true)
							setErrorMessage("Please create a new Variable!!");
						else if ((((PathNodeEditPart) (ManageChangeEditorPage.this.parent)).getModel() instanceof FailurePoint) && affectedProperty.equals("Failure Expression") && newValueSelectVariable.getText().isEmpty() && newValueSelectVariable.isEnabled() == true)
							setErrorMessage("Please select a Variable!!");
						else if ((((PathNodeEditPart) (ManageChangeEditorPage.this.parent)).getModel() instanceof FailurePoint) && affectedProperty.equals("Failure Expression") && newValueFailureExpression.getText().isEmpty() && newValueFailureExpression.getEnabled() == true)
							setErrorMessage("Please enter the Failure Expression!!");
						else if (affectedProperty.equals("Failure Expression") && (((PathNodeEditPart) (ManageChangeEditorPage.this.parent)).getModel() instanceof FailurePoint) && wrongFormat.equals(true) && newValueFailureExpression.getEnabled() == true)
							setErrorMessage(errorMessage);
						else {
							Change addedChange = addChange();
							TableItem item = new TableItem(availableChanges, SWT.NONE);
							DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
							item.setText(new String[] {((TextChange) addedChange).getAffectedProperty(), df.format(addedChange.getStart()), 
									df.format(addedChange.getEnd()), changeType, ((TextChange) addedChange).getNewValue(), ""});
						}
					}
					else if (ManageChangeEditorPage.this.parent instanceof StartPoint && changeType.equals("Enumeration Change") && newValueFailureKind.getSelectionIndex() < 0 && newValueFailureKind.getEnabled() == true)
						setErrorMessage("Please select a Failure Kind!!");
					else if ((ManageChangeEditorPage.this.parent instanceof Timer || ManageChangeEditorPage.this.parent instanceof WaitingPlace) && changeType.equals("Enumeration Change") && newValueWaitKind.getSelectionIndex() < 0 && newValueWaitKind.getEnabled() == true)
						setErrorMessage("Please select a Wait Kind!!");
					else if (changeType.equals("Boolean Change") && newValueDynamicStub.getSelectionIndex() < 0 && (newValueDynamicStub.getEnabled() == true))
						setErrorMessage("Please select a boolean value for Dynamic Stub!!");
					else if (changeType.equals("Boolean Change") && newValueSyncStub.getSelectionIndex() < 0 && (newValueSyncStub.getEnabled() == true))
						setErrorMessage("Please select a boolean value for Synchronizing Stub!!");
					else if (changeType.equals("Boolean Change") && newValueBlockStub.getSelectionIndex() < 0 && (newValueBlockStub.getEnabled() == true))
						setErrorMessage("Please select a boolean value for Blocking Stub!!");
					else if (ManageChangeEditorPage.this.parent instanceof PluginBinding && changeType.equals("Text Change") && newValueRepFactor.getText().isEmpty() && newValueRepFactor.getEnabled() == true)
						setErrorMessage("Please enter the Replication Factor!!");
					else if (ManageChangeEditorPage.this.parent instanceof PluginBinding && changeType.equals("Text Change") && wrongFormatRepFactor.equals(true)  && newValueRepFactor.getEnabled() == true)
						setErrorMessage("Please enter a valid Replication Factor!!");
					else if (ManageChangeEditorPage.this.parent instanceof FailurePoint && changeType.equals("Text Change") && affectedProperty.equals("Failure Expression") && newValueSelectVariable.getItemCount() == 0 && newValueSelectVariable.getText().isEmpty() && newValueSelectVariable.isEnabled() == true)
						setErrorMessage("Please create a new Variable!!");
					else if (ManageChangeEditorPage.this.parent instanceof FailurePoint && changeType.equals("Text Change") && affectedProperty.equals("Failure Expression") && newValueSelectVariable.getText().isEmpty() && newValueSelectVariable.isEnabled() == true)
						setErrorMessage("Please select a Variable!!");
					else if (ManageChangeEditorPage.this.parent instanceof FailurePoint && changeType.equals("Text Change") && affectedProperty.equals("Failure Expression") && newValueFailureExpression.getText().isEmpty() && newValueFailureExpression.getEnabled() == true)
						setErrorMessage("Please enter the Failure Expression!!");
					else if (ManageChangeEditorPage.this.parent instanceof FailurePoint && changeType.equals("Text Change") && affectedProperty.equals("Failure Expression") && wrongFormat.equals(true) && newValueFailureExpression.getEnabled() == true)
						setErrorMessage(errorMessage);
					else {
						Change addedChange = addChange();
						TableItem item = new TableItem(availableChanges, SWT.NONE);
						DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
						if (changeType.equals("Deactivation Change"))
							item.setText(new String[] {affectedProperty, df.format(addedChange.getStart()), 
									df.format(addedChange.getEnd()), changeType, "", ""});
						else if (changeType.equals("Enumeration Change"))
							item.setText(new String[] {((EnumChange) addedChange).getAffectedProperty(), df.format(addedChange.getStart()), 
									df.format(addedChange.getEnd()), changeType, ((EnumChange) addedChange).getNewValue(), ""});
						else if (changeType.equals("Boolean Change")) {
							String value = Boolean.toString(((BooleanChange) addedChange).isNewValue());
							item.setText(new String[] {((BooleanChange) addedChange).getAffectedProperty(), df.format(addedChange.getStart()), 
									df.format(addedChange.getEnd()), changeType, value, ""});
						} else if (changeType.equals("Text Change"))
							item.setText(new String[] {((TextChange) addedChange).getAffectedProperty(), df.format(addedChange.getStart()), 
									df.format(addedChange.getEnd()), changeType, ((TextChange) addedChange).getNewValue(), ""});
					}
				}
				else if (ManageChangeEditorPage.this.parent instanceof GRLmodelElement || ManageChangeEditorPage.this.parent instanceof ActorRefEditPart
						|| ManageChangeEditorPage.this.parent instanceof IntentionalElementEditPart || ManageChangeEditorPage.this.parent instanceof LinkRefEditPart) {
					if (currentDynContext == null)
						setErrorMessage("Please select a Dynamic Context");
					else if (ManageChangeEditorPage.this.parent == null)
						setErrorMessage("No element selected!!");
					else if (affectedProperty == null)
						setErrorMessage("Please select the affected attribute of the selected element!!");
					else if (changeType == null)
						setErrorMessage("Please select a change type!!");
					else if (!startDate.before(endDate))
						setErrorMessage("Start Date should be before End Date!!");
					else if (checkForOverlap() != true)
						setErrorMessage("This change overlaps with existing change!!");
					else if (changeType.equals("Enumeration Change") && newValueDecomp.getSelectionIndex() < 0 && (ManageChangeEditorPage.this.parent instanceof IntentionalElementEditPart || ManageChangeEditorPage.this.parent instanceof IntentionalElement))
						setErrorMessage("Please select a Decomposition Type!!");
				else if ((changeType.equals("Constant Change") || changeType.equals("Linear Change")) && (newValueText.getText() == null || newValueText.getText().trim() == ""))
					setErrorMessage("Please enter the new Value!!");
				else if (changeType.equals("Enumeration Change") && newValueDecomp.getSelectionIndex() < 0)
					setErrorMessage("Please select a Decomposition Type!!");
				else if (changeType.equals("Quadratic Change") && (newValueQuad.getText() == null || newValueQuad.getText().trim() == "" || 
						newValueLin.getText() == null || newValueLin.getText().trim() == "" || newValueCon.getText() == null || 
							newValueCon.getText().trim() == ""))
					setErrorMessage("Please enter all coefficients!!");
				else if ((changeType.equals("Formula Change")) && (newValueFor.getText() == null || newValueFor.getText().trim() == ""))
					setErrorMessage("Please enter the formula!!");
				else if(wrongFormat)
					setErrorMessage(Messages.getString("ManageChangePage.NumberFormatWrong"));
					else {
					Change addedChange = addChange();
					TableItem item = new TableItem(availableChanges, SWT.NONE);
					DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
					if (changeType.equals("Constant Change"))
						item.setText(new String[] {((ConstantChange) addedChange).getAffectedProperty(), df.format(addedChange.getStart()), 
		            		df.format(addedChange.getEnd()), changeType, Integer.toString(((ConstantChange) addedChange).getNewValue()), ""});
					else if (changeType.equals("Linear Change"))
						item.setText(new String[] {((LinearChange) addedChange).getAffectedProperty(), df.format(addedChange.getStart()), 
			            		df.format(addedChange.getEnd()), changeType, Integer.toString(((LinearChange) addedChange).getNewValue()), ""});
					else if (changeType.equals("Quadratic Change")) {
							String formula = Float.toString(((QuadraticChange)addedChange).getQuadraticCoefficient()) + "t²+" +
	            				Float.toString(((QuadraticChange)addedChange).getLinearCoefficient()) + "t+" +
	            				Float.toString(((QuadraticChange)addedChange).getConstant());
						item.setText(new String[] {((QuadraticChange) addedChange).getAffectedProperty(), df.format(addedChange.getStart()), 
			            		df.format(addedChange.getEnd()), changeType, "", formula});
					} 
					else if (changeType.equals("Formula Change"))
						item.setText(new String[] {((FormulaChange) addedChange).getAffectedProperty(), df.format(addedChange.getStart()), 
			            		df.format(addedChange.getEnd()), changeType, "", ((FormulaChange) addedChange).getFormula()});					
					else if (changeType.equals("Deactivation Change"))
						item.setText(new String[] {affectedProperty, df.format(addedChange.getStart()), 
			            		df.format(addedChange.getEnd()), changeType, "", ""});
					else if (changeType.equals("Enumeration Change"))
						item.setText(new String[] {((EnumChange) addedChange).getAffectedProperty(), df.format(addedChange.getStart()), 
			            		df.format(addedChange.getEnd()), changeType, ((EnumChange) addedChange).getNewValue(), ""});
				}
			}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
        
        gd = new GridData();
        gd.widthHint = 100;
 		gd.horizontalSpan = 1;
        addButton.setLayoutData(gd);
        
        //Button to update change
        updButton = new Button(container, SWT.PUSH);
        updButton.setText("Update Change");
        updButton.setEnabled(false);
        updButton.addSelectionListener(new SelectionListener() {
			
        	@Override
			public void widgetSelected(SelectionEvent e) {
        		dialogChanged();
				if (ManageChangeEditorPage.this.parent instanceof UCMmodelElement || ManageChangeEditorPage.this.parent instanceof ComponentRefEditPart
						|| ManageChangeEditorPage.this.parent instanceof RespRefEditPart || ManageChangeEditorPage.this.parent instanceof PathNodeEditPart
						|| ManageChangeEditorPage.this.parent instanceof PluginBinding || ManageChangeEditorPage.this.parent instanceof urncore.Condition) {
					if (currentDynContext != oldDynContext && oldDynContext != null)
						setErrorMessage("Can't update Dynamic Context of a Change");
					else if (ManageChangeEditorPage.this.parent == null)
						setErrorMessage("No element selected!!");
					else if (affectedProperty == null)
						setErrorMessage("Please selected the affected attribute of the selected element!!");
					else if (changeType == null)
						setErrorMessage("Please select a change type!!");
					else if (!startDate.before(endDate))
						setErrorMessage("Start Date should be before End Date!!");
					else if (ManageChangeEditorPage.this.parent instanceof ComponentRefEditPart || ManageChangeEditorPage.this.parent instanceof Component) {
						if (changeType.equals("Enumeration Change") && newValueCompKind.getSelectionIndex() < 0)
							setErrorMessage("Please select a Component Kind!!");
						else if (changeType.equals("Boolean Change") && newValueCompProtection.getSelectionIndex() < 0 && (newValueCompProtection.getEnabled() == true))
							setErrorMessage("Please select a boolean value for Component Protection!!");
						else if (changeType.equals("Boolean Change") && newValueCompContext.getSelectionIndex() < 0 && (newValueCompContext.getEnabled() == true))
							setErrorMessage("Please select a boolean value for Component Context!!");
						else {
							DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
							java.util.List availChanges = DynamicContextsUtils.getAllAvailableChanges(ManageChangeEditorPage.this.parent, currentDynContext, urn);
							Change changeToUpdate = null;
							for (Iterator iter = availChanges.iterator(); iter.hasNext();) {
								Change change = (Change) iter.next();        				
								if (availableChanges.getItem(availableChanges.getSelectionIndex()).getText(0).startsWith("Selected") || availableChanges.getItem(availableChanges.getSelectionIndex()).getText(0).startsWith("Select")) {
									if (df.format(change.getStart()).equals(availableChanges.getItem(availableChanges.getSelectionIndex()).getText(1)) &&
											change instanceof DeactivationChange){
										changeToUpdate = change;
										break;			    			
									}
								} else {
									if (df.format(change.getStart()).equals(availableChanges.getItem(availableChanges.getSelectionIndex()).getText(1)) && 
											!(change instanceof DeactivationChange) && 
											((PropertyChange)change).getAffectedProperty().equals(availableChanges.getItem(availableChanges.getSelectionIndex()).getText(0))){
										changeToUpdate = change;
										break;			    			
									}
								}
							}
							if (checkForOverlap(changeToUpdate) != true)
								setErrorMessage("This change overlaps with existing change!!");
							else {
								Change updatedChange = updChange(changeToUpdate);
								TableItem item = availableChanges.getItem(availableChanges.getSelectionIndex());
								if (changeType.equals("Deactivation Change"))
									item.setText(new String[] {affectedProperty, df.format(updatedChange.getStart()), 
											df.format(updatedChange.getEnd()), changeType, "", ""});
								else if (changeType.equals("Enumeration Change"))
									item.setText(new String[] {((EnumChange) updatedChange).getAffectedProperty(), df.format(updatedChange.getStart()), 
											df.format(updatedChange.getEnd()), changeType, ((EnumChange) updatedChange).getNewValue(), ""});
								else if (changeType.equals("Boolean Change")) {
									String value = Boolean.toString(((BooleanChange) updatedChange).isNewValue());
									item.setText(new String[] {((BooleanChange) updatedChange).getAffectedProperty(), df.format(updatedChange.getStart()), 
											df.format(updatedChange.getEnd()), changeType, value, ""});
								}
							}
						}
					} else if (ManageChangeEditorPage.this.parent instanceof StubEditPart || ManageChangeEditorPage.this.parent instanceof Stub || ManageChangeEditorPage.this.parent instanceof PluginBinding) {
						if (changeType.equals("Boolean Change") && newValueDynamicStub.getSelectionIndex() < 0 && (newValueDynamicStub.getEnabled() == true))
							setErrorMessage("Please select a boolean value for Dynamic Stub!!");
						else if (changeType.equals("Boolean Change") && newValueSyncStub.getSelectionIndex() < 0 && (newValueSyncStub.getEnabled() == true))
							setErrorMessage("Please select a boolean value for Synchronizing Stub!!");
						else if (changeType.equals("Boolean Change") && newValueBlockStub.getSelectionIndex() < 0 && (newValueBlockStub.getEnabled() == true))
							setErrorMessage("Please select a boolean value for Blocking Stub!!");
						else if (changeType.equals("Text Change") && affectedProperty.startsWith("Replication factor") && (newValueRepFactor.getText() == null || newValueRepFactor.getText().trim() == ""))
							setErrorMessage("Please enter the Replication Factor!!");
						else if (changeType.equals("Text Change") && affectedProperty.startsWith("Replication factor") && newValueRepFactor.getEnabled() == true && wrongFormatRepFactor.equals(true))
							setErrorMessage("Please enter a valid Replication Factor!!");
						else if (changeType.equals("Text Change") && affectedProperty.startsWith("Pre-Condition") && newValueSelectVariable.getItemCount() == 0 && newValueSelectVariable.getText().isEmpty() && newValueSelectVariable.isEnabled() == true)
							setErrorMessage("Please create a new Variable!!");
						else if (changeType.equals("Text Change") && affectedProperty.startsWith("Expression") && newValueSelectVariable.getItemCount() == 0 && newValueSelectVariable.getText().isEmpty() && newValueSelectVariable.isEnabled() == true)
							setErrorMessage("Please create a new Variable!!");
						else if (changeType.equals("Text Change") && affectedProperty.equals("Post-Condition") && newValueSelectVariable.getItemCount() == 0 && newValueSelectVariable.getText().isEmpty() && newValueSelectVariable.isEnabled() == true)
							setErrorMessage("Please create a new Variable!!");
						else if (changeType.equals("Text Change") && affectedProperty.equals("Failure List/Start Point Pre-Condition") && newValueSelectVariable.getItemCount() == 0 && newValueSelectVariable.getText().isEmpty() && newValueSelectVariable.isEnabled() == true)
							setErrorMessage("Please create a new Variable!!");
						else if (changeType.equals("Text Change") && affectedProperty.equals("Failure Condition") && newValueSelectVariable.getItemCount() == 0 && newValueSelectVariable.getText().isEmpty() && newValueSelectVariable.isEnabled() == true)
							setErrorMessage("Please create a new Variable!!");
						else if (changeType.equals("Text Change") && affectedProperty.equals("Failure Expression") && newValueSelectVariable.getItemCount() == 0 && newValueSelectVariable.getText().isEmpty() && newValueSelectVariable.isEnabled() == true)
							setErrorMessage("Please create a new Variable!!");
						else if (changeType.equals("Text Change") && affectedProperty.startsWith("Pre-Condition") && newValueSelectVariable.getText().isEmpty() && newValueSelectVariable.isEnabled() == true)
							setErrorMessage("Please select a Variable!!");
						else if (changeType.equals("Text Change") && affectedProperty.startsWith("Expression") && newValueSelectVariable.getText().isEmpty() && newValueSelectVariable.isEnabled() == true)
							setErrorMessage("Please select a Variable!!");
						else if (changeType.equals("Text Change") && affectedProperty.equals("Post-Condition") && newValueSelectVariable.getText().isEmpty() && newValueSelectVariable.isEnabled() == true)
							setErrorMessage("Please select a Variable!!");
						else if (changeType.equals("Text Change") && affectedProperty.equals("Failure List/Start Point Pre-Condition") && newValueSelectVariable.getText().isEmpty() && newValueSelectVariable.isEnabled() == true)
							setErrorMessage("Please select a Variable!!");
						else if (changeType.equals("Text Change") && affectedProperty.equals("Failure Condition") && newValueSelectVariable.getText().isEmpty() && newValueSelectVariable.isEnabled() == true)
							setErrorMessage("Please select a Variable!!");
						else if (changeType.equals("Text Change") && affectedProperty.equals("Failure Expression") && newValueSelectVariable.getText().isEmpty() && newValueSelectVariable.isEnabled() == true)
							setErrorMessage("Please select a Variable!!");
						else if (changeType.equals("Text Change") && affectedProperty.startsWith("Pre-Condition") && newValuePluginBindingPreCond.getText().isEmpty() && newValuePluginBindingPreCond.getEnabled() == true)
							setErrorMessage("Please enter the Pre Condition!!");
						else if (changeType.equals("Text Change") && affectedProperty.equals("Post-Condition") && newValueEndPtPostCond.getText().isEmpty() && newValueEndPtPostCond.getEnabled() == true)
							setErrorMessage("Please enter the Post Condition!!");
						else if (changeType.equals("Text Change") && affectedProperty.startsWith("Expression") && newValueOrForkExp.getText().isEmpty() && newValueOrForkExp.getEnabled() == true)
							setErrorMessage("Please enter the Expression!!");
						else if (changeType.equals("Text Change") && affectedProperty.equals("Failure Condition") && newValueFailureCondition.getText().isEmpty() && newValueFailureCondition.getEnabled() == true)
							setErrorMessage("Please enter the Failure Condition!!");
						else if (changeType.equals("Text Change") && affectedProperty.equals("Failure Expression") && newValueFailureExpression.getText().isEmpty() && newValueFailureExpression.getEnabled() == true)
							setErrorMessage("Please enter the Failure Expression!!");
						else if (changeType.equals("Text Change") && affectedProperty.equals("Failure List/Start Point Pre-Condition") && newValueFailureList.getText().isEmpty() && newValueFailureList.getEnabled() == true)
							setErrorMessage("Please enter the Failure List/Start Point Pre-Condition!!");
						else if (changeType.equals("Text Change") && affectedProperty.startsWith("Pre-Condition") && wrongFormat.equals(true) && newValuePluginBindingPreCond.isEnabled() == true)
							setErrorMessage(errorMessage);
						else if (changeType.equals("Text Change") && affectedProperty.startsWith("Expression") && wrongFormat.equals(true) && newValueOrForkExp.getEnabled() == true)
							setErrorMessage(errorMessage);
						else if (changeType.equals("Text Change") && affectedProperty.equals("Post-Condition") && wrongFormat.equals(true) && newValueEndPtPostCond.getEnabled() == true)
							setErrorMessage(errorMessage);
						else if (changeType.equals("Text Change") && affectedProperty.equals("Failure List/Start Point Pre-Condition") && wrongFormat.equals(true) && newValueFailureList.getEnabled() == true)
							setErrorMessage(errorMessage);
						else if (changeType.equals("Text Change") && affectedProperty.equals("Failure Condition") && wrongFormat.equals(true) && newValueFailureCondition.getEnabled() == true)
							setErrorMessage(errorMessage);
						else if (changeType.equals("Text Change") && affectedProperty.equals("Failure Expression") && wrongFormat.equals(true) && newValueFailureExpression.getEnabled() == true)
							setErrorMessage(errorMessage);
						else {
							DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
							java.util.List availChanges = DynamicContextsUtils.getAllAvailableChanges(ManageChangeEditorPage.this.parent, currentDynContext, urn);
							Change changeToUpdate = null;
							for (Iterator iter = availChanges.iterator(); iter.hasNext();) {
								Change change = (Change) iter.next();        				
								if (availableChanges.getItem(availableChanges.getSelectionIndex()).getText(0).startsWith("Selected") || availableChanges.getItem(availableChanges.getSelectionIndex()).getText(0).startsWith("Select")) {
									if (df.format(change.getStart()).equals(availableChanges.getItem(availableChanges.getSelectionIndex()).getText(1)) &&
											change instanceof DeactivationChange){
										changeToUpdate = change;
										break;			    			
									}
								} else {
									if (df.format(change.getStart()).equals(availableChanges.getItem(availableChanges.getSelectionIndex()).getText(1)) && 
											!(change instanceof DeactivationChange) && 
											((PropertyChange)change).getAffectedProperty().equals(availableChanges.getItem(availableChanges.getSelectionIndex()).getText(0))){
										if (change instanceof TextChange) {
											if (affectedProperty.startsWith("Replication factor"))
												((TextChange) change).setNewValue(String.valueOf(newValue));
											else
												((TextChange) change).setNewValue(newValueExpression);
											changeToUpdate = change;
											break;
										} else {
											changeToUpdate = change;
											break;	
										}			    			
									}
								}
							}
							if (checkForOverlap(changeToUpdate) != true)
								setErrorMessage("This change overlaps with existing change!!");
							else {
								Change updatedChange = updChange(changeToUpdate);
								TableItem item = availableChanges.getItem(availableChanges.getSelectionIndex());
								if (changeType.equals("Deactivation Change"))
									item.setText(new String[] {affectedProperty, df.format(updatedChange.getStart()), 
											df.format(updatedChange.getEnd()), changeType, "", ""});
								else if (changeType.equals("Boolean Change")) {
									String value = Boolean.toString(((BooleanChange) updatedChange).isNewValue());
									item.setText(new String[] {((BooleanChange) updatedChange).getAffectedProperty(), df.format(updatedChange.getStart()), 
											df.format(updatedChange.getEnd()), changeType, value, ""});
								}
								else if (changeType.equals("Text Change"))
									item.setText(new String[] {((TextChange) updatedChange).getAffectedProperty(), df.format(updatedChange.getStart()), 
											df.format(updatedChange.getEnd()), changeType, ((TextChange) updatedChange).getNewValue(), ""});
							}
						}
					}
					else if ((ManageChangeEditorPage.this.parent instanceof RespRefEditPart || ManageChangeEditorPage.this.parent instanceof Responsibility)) {
						if (changeType.equals("Text Change") && newValueSelectVariable.getItemCount() == 0 && newValueSelectVariable.getText().isEmpty() && newValueSelectVariable.isEnabled() == true)
							setErrorMessage("Please create a new Variable!!");
						else if (changeType.equals("Text Change") && newValueSelectVariable.getText().isEmpty() && newValueSelectVariable.isEnabled() == true)
							setErrorMessage("Please select a Variable!!");
						else if (changeType.equals("Text Change") && newValueRespExpression.isEnabled() == true && (newValueRespExpression.getText().isEmpty() || newValueRespExpression.getText().trim() == ""))
							setErrorMessage("Please enter the Expression!!");
						else if (changeType.equals("Text Change") && wrongFormat.equals(true) && newValueRespExpression.isEnabled() == true)
							setErrorMessage(errorMessage);
						else {
							DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
							java.util.List availChanges = DynamicContextsUtils.getAllAvailableChanges(ManageChangeEditorPage.this.parent, currentDynContext, urn);
							Change changeToUpdate = null;
							for (Iterator iter = availChanges.iterator(); iter.hasNext();) {
								Change change = (Change) iter.next();        				
								if (availableChanges.getItem(availableChanges.getSelectionIndex()).getText(0).startsWith("Selected") || availableChanges.getItem(availableChanges.getSelectionIndex()).getText(0).startsWith("Select")) {
									if (df.format(change.getStart()).equals(availableChanges.getItem(availableChanges.getSelectionIndex()).getText(1)) &&
											change instanceof DeactivationChange){
										changeToUpdate = change;
										break;			    			
									}
								} else {
									if (df.format(change.getStart()).equals(availableChanges.getItem(availableChanges.getSelectionIndex()).getText(1)) && 
											!(change instanceof DeactivationChange) && 
											((PropertyChange)change).getAffectedProperty().equals(availableChanges.getItem(availableChanges.getSelectionIndex()).getText(0))){
										if (change instanceof TextChange) {
											((TextChange) change).setNewValue(newValueExpression);
											changeToUpdate = change;
											break;
										} else {
											changeToUpdate = change;
											break;	
										}			    			
									}
								}
							}
							if (checkForOverlap(changeToUpdate) != true)
								setErrorMessage("This change overlaps with existing change!!");
							else {
								Change updatedChange = updChange(changeToUpdate);
								TableItem item = availableChanges.getItem(availableChanges.getSelectionIndex());
								if (changeType.equals("Deactivation Change"))
									item.setText(new String[] {affectedProperty, df.format(updatedChange.getStart()), 
											df.format(updatedChange.getEnd()), changeType, "", ""});
								else if (changeType.equals("Text Change"))
									item.setText(new String[] {((TextChange) updatedChange).getAffectedProperty(), df.format(updatedChange.getStart()), 
											df.format(updatedChange.getEnd()), changeType, ((TextChange) updatedChange).getNewValue(), ""});
							}
						}
					}

					else if (ManageChangeEditorPage.this.parent instanceof PathNodeEditPart) {
						if (((PathNodeEditPart) (ManageChangeEditorPage.this.parent)).getModel() instanceof StartPoint) {
							if (changeType.equals("Enumeration Change") && newValueFailureKind.getSelectionIndex() < 0)
								setErrorMessage("Please select a Failure Kind!!");
							else if (changeType.equals("Text Change") && affectedProperty.equals("Failure List/Start Point Pre-Condition") && newValueSelectVariable.getItemCount() == 0 && newValueSelectVariable.getText().isEmpty() && newValueSelectVariable.isEnabled() == true)
								setErrorMessage("Please create a new Variable!!");
							else if (changeType.equals("Text Change") && affectedProperty.equals("Failure List/Start Point Pre-Condition") && newValueSelectVariable.getText().isEmpty() && newValueSelectVariable.isEnabled() == true)
								setErrorMessage("Please select a Variable!!");
							else if (affectedProperty.equals("Failure List/Start Point Pre-Condition") && newValueFailureList.getText().isEmpty() && newValueFailureList.getEnabled() == true)
								setErrorMessage("Please enter the Failure List/Start Point Pre-Condition!!");
							else if (affectedProperty.equals("Failure List/Start Point Pre-Condition") && wrongFormat.equals(true) && newValueFailureList.getEnabled() == true)
								setErrorMessage(errorMessage);
							else {
								DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
								java.util.List availChanges = DynamicContextsUtils.getAllAvailableChanges(ManageChangeEditorPage.this.parent, currentDynContext, urn);
								Change changeToUpdate = null;
								for (Iterator iter = availChanges.iterator(); iter.hasNext();) {
									Change change = (Change) iter.next();        				
									if (availableChanges.getItem(availableChanges.getSelectionIndex()).getText(0).startsWith("Selected") || availableChanges.getItem(availableChanges.getSelectionIndex()).getText(0).startsWith("Select")) {
										if (df.format(change.getStart()).equals(availableChanges.getItem(availableChanges.getSelectionIndex()).getText(1)) &&
												change instanceof DeactivationChange){
											changeToUpdate = change;
											break;			    			
										}
									} else {
										if (df.format(change.getStart()).equals(availableChanges.getItem(availableChanges.getSelectionIndex()).getText(1)) && 
												!(change instanceof DeactivationChange) && 
												((PropertyChange)change).getAffectedProperty().equals(availableChanges.getItem(availableChanges.getSelectionIndex()).getText(0))){
											if (change instanceof TextChange) {
												((TextChange) change).setNewValue(newValueExpression);
												changeToUpdate = change;
												break;
											} else {
												changeToUpdate = change;
												break;	
											}			    			
										}
									}
								}
								if (checkForOverlap(changeToUpdate) != true)
									setErrorMessage("This change overlaps with existing change!!");
								else {
									Change updatedChange = updChange(changeToUpdate);
									TableItem item = availableChanges.getItem(availableChanges.getSelectionIndex());
									if (changeType.equals("Enumeration Change"))
										item.setText(new String[] {((EnumChange) updatedChange).getAffectedProperty(), df.format(updatedChange.getStart()), 
												df.format(updatedChange.getEnd()), changeType, ((EnumChange) updatedChange).getNewValue(), ""});
									else if (changeType.equals("Text Change"))
										item.setText(new String[] {((TextChange) updatedChange).getAffectedProperty(), df.format(updatedChange.getStart()), 
												df.format(updatedChange.getEnd()), changeType, ((TextChange) updatedChange).getNewValue(), ""});
								}
							}
						} else if (((PathNodeEditPart) (ManageChangeEditorPage.this.parent)).getModel() instanceof FailurePoint) {
							if (changeType.equals("Text Change") && affectedProperty.equals("Failure Condition") && newValueSelectVariable.getItemCount() == 0 && newValueSelectVariable.getText().isEmpty() && newValueSelectVariable.isEnabled() == true)
								setErrorMessage("Please create a new Variable!!");
							else if (changeType.equals("Text Change") && affectedProperty.equals("Failure Condition") && newValueSelectVariable.getText().isEmpty() && newValueSelectVariable.isEnabled() == true)
								setErrorMessage("Please select a Variable!!");
							else if (changeType.equals("Text Change") && affectedProperty.equals("Failure Condition") && newValueFailureCondition.getText().isEmpty() && newValueFailureCondition.getEnabled() == true)
								setErrorMessage("Please enter the Failure Condition!!");
							else if (changeType.equals("Text Change") && affectedProperty.equals("Failure Condition") && wrongFormat.equals(true) && newValueFailureCondition.getEnabled() == true)
								setErrorMessage(errorMessage);
							else if (changeType.equals("Text Change") && affectedProperty.equals("Failure Expression") && newValueSelectVariable.getItemCount() == 0 && newValueSelectVariable.getText().isEmpty() && newValueSelectVariable.isEnabled() == true)
								setErrorMessage("Please create a new Variable!!");
							else if (changeType.equals("Text Change") && affectedProperty.equals("Failure Expression") && newValueSelectVariable.getText().isEmpty() && newValueSelectVariable.isEnabled() == true)
								setErrorMessage("Please select a Variable!!");
							else if (changeType.equals("Text Change") && affectedProperty.equals("Failure Expression") && newValueFailureExpression.getText().isEmpty() && newValueFailureExpression.getEnabled() == true)
								setErrorMessage("Please enter the Failure Expression!!");
							else if (changeType.equals("Text Change") && affectedProperty.equals("Failure Expression") && wrongFormat.equals(true) && newValueFailureExpression.getEnabled() == true)
								setErrorMessage(errorMessage);
							else {
								DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
								java.util.List availChanges = DynamicContextsUtils.getAllAvailableChanges(ManageChangeEditorPage.this.parent, currentDynContext, urn);
								Change changeToUpdate = null;
								for (Iterator iter = availChanges.iterator(); iter.hasNext();) {
									Change change = (Change) iter.next();        				
									if (availableChanges.getItem(availableChanges.getSelectionIndex()).getText(0).startsWith("Selected") || availableChanges.getItem(availableChanges.getSelectionIndex()).getText(0).startsWith("Select")) {
										if (df.format(change.getStart()).equals(availableChanges.getItem(availableChanges.getSelectionIndex()).getText(1)) &&
												change instanceof DeactivationChange){
											changeToUpdate = change;
											break;			    			
										}
									} else {
										if (df.format(change.getStart()).equals(availableChanges.getItem(availableChanges.getSelectionIndex()).getText(1)) && 
												!(change instanceof DeactivationChange) && 
												((PropertyChange)change).getAffectedProperty().equals(availableChanges.getItem(availableChanges.getSelectionIndex()).getText(0))){
											if (change instanceof TextChange) {
												((TextChange) change).setNewValue(newValueExpression);
												changeToUpdate = change;
												break;
											} else {
												changeToUpdate = change;
												break;	
											}			    			
										}
									}
								}
								if (checkForOverlap(changeToUpdate) != true)
									setErrorMessage("This change overlaps with existing change!!");
								else {
									Change updatedChange = updChange(changeToUpdate);
									TableItem item = availableChanges.getItem(availableChanges.getSelectionIndex());
									if (changeType.equals("Deactivation Change"))
										item.setText(new String[] {affectedProperty, df.format(updatedChange.getStart()), 
												df.format(updatedChange.getEnd()), changeType, "", ""});
									else if (changeType.equals("Text Change"))
										item.setText(new String[] {((TextChange) updatedChange).getAffectedProperty(), df.format(updatedChange.getStart()), 
												df.format(updatedChange.getEnd()), changeType, ((TextChange) updatedChange).getNewValue(), ""});
								}
							}
						} else if (((PathNodeEditPart) (ManageChangeEditorPage.this.parent)).getModel() instanceof OrFork) {
							if (affectedProperty.startsWith("Expression") && newValueSelectVariable.getItemCount() == 0 && newValueSelectVariable.getText().isEmpty() && newValueSelectVariable.isEnabled() == true)
								setErrorMessage("Please create a new Variable!!");
							else if ( affectedProperty.startsWith("Expression") && newValueSelectVariable.getText().isEmpty() && newValueSelectVariable.isEnabled() == true)
								setErrorMessage("Please select a Variable!!");
							else if (affectedProperty.startsWith("Expression") && newValueOrForkExp.getText().isEmpty() && newValueOrForkExp.getEnabled() == true)
								setErrorMessage("Please enter the Expression!!");
							else if (affectedProperty.startsWith("Expression") && wrongFormat.equals(true) && newValueOrForkExp.getEnabled() == true)
								setErrorMessage(errorMessage);
							else {
								DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
								java.util.List availChanges = DynamicContextsUtils.getAllAvailableChanges(ManageChangeEditorPage.this.parent, currentDynContext, urn);
								Change changeToUpdate = null;
								for (Iterator iter = availChanges.iterator(); iter.hasNext();) {
									Change change = (Change) iter.next();        				
									if (df.format(change.getStart()).equals(availableChanges.getItem(availableChanges.getSelectionIndex()).getText(1)) && 
											!(change instanceof DeactivationChange) && 
											((PropertyChange)change).getAffectedProperty().equals(availableChanges.getItem(availableChanges.getSelectionIndex()).getText(0))){
										((TextChange) change).setNewValue(newValueExpression);
										changeToUpdate = change;
										break;
									}

								}
								if (checkForOverlap(changeToUpdate) != true)
									setErrorMessage("This change overlaps with existing change!!");
								else {
									Change updatedChange = updChange(changeToUpdate);
									TableItem item = availableChanges.getItem(availableChanges.getSelectionIndex());
									item.setText(new String[] {((TextChange) updatedChange).getAffectedProperty(), df.format(updatedChange.getStart()), 
											df.format(updatedChange.getEnd()), changeType, ((TextChange) updatedChange).getNewValue(), ""});
								}
							}
						} else if (((PathNodeEditPart) (ManageChangeEditorPage.this.parent)).getModel() instanceof EndPoint) {
							if (affectedProperty.equals("Post-Condition") && newValueSelectVariable.getItemCount() == 0 && newValueSelectVariable.getText().isEmpty() && newValueSelectVariable.isEnabled() == true)
								setErrorMessage("Please create a new Variable!!");
							else if (affectedProperty.equals("Post-Condition") && newValueSelectVariable.getText().isEmpty() && newValueSelectVariable.isEnabled() == true)
								setErrorMessage("Please select a Variable!!");
							else if (affectedProperty.equals("Post-Condition") && newValueEndPtPostCond.getText().isEmpty() && newValueEndPtPostCond.getEnabled() == true)
								setErrorMessage("Please enter the Post Condition!!");
							else if (affectedProperty.equals("Post-Condition") && wrongFormat.equals(true) && newValueEndPtPostCond.getEnabled() == true)
								setErrorMessage(errorMessage);
							else {
								DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
								java.util.List availChanges = DynamicContextsUtils.getAllAvailableChanges(ManageChangeEditorPage.this.parent, currentDynContext, urn);
								Change changeToUpdate = null;
								for (Iterator iter = availChanges.iterator(); iter.hasNext();) {
									Change change = (Change) iter.next();        				
									if (df.format(change.getStart()).equals(availableChanges.getItem(availableChanges.getSelectionIndex()).getText(1)) && 
											!(change instanceof DeactivationChange) && 
											((PropertyChange)change).getAffectedProperty().equals(availableChanges.getItem(availableChanges.getSelectionIndex()).getText(0))){
										((TextChange) change).setNewValue(newValueExpression);
										changeToUpdate = change;
										break;
									}

								}
								if (checkForOverlap(changeToUpdate) != true)
									setErrorMessage("This change overlaps with existing change!!");
								else {
									Change updatedChange = updChange(changeToUpdate);
									TableItem item = availableChanges.getItem(availableChanges.getSelectionIndex());
									item.setText(new String[] {((TextChange) updatedChange).getAffectedProperty(), df.format(updatedChange.getStart()), 
											df.format(updatedChange.getEnd()), changeType, ((TextChange) updatedChange).getNewValue(), ""});
								}
							}
						} else if (((PathNodeEditPart) (ManageChangeEditorPage.this.parent)).getModel() instanceof Timer || ((PathNodeEditPart) (ManageChangeEditorPage.this.parent)).getModel() instanceof WaitingPlace) {
							if (changeType.equals("Enumeration Change") && newValueWaitKind.getSelectionIndex() < 0 && newValueWaitKind.getEnabled() == true)
								setErrorMessage("Please select a Wait Kind!!");
							else {
								DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
								java.util.List availChanges = DynamicContextsUtils.getAllAvailableChanges(ManageChangeEditorPage.this.parent, currentDynContext, urn);
								Change changeToUpdate = null;
								for (Iterator iter = availChanges.iterator(); iter.hasNext();) {
									Change change = (Change) iter.next();        				
									if (availableChanges.getItem(availableChanges.getSelectionIndex()).getText(0).startsWith("Selected") || availableChanges.getItem(availableChanges.getSelectionIndex()).getText(0).startsWith("Select")) {
										if (df.format(change.getStart()).equals(availableChanges.getItem(availableChanges.getSelectionIndex()).getText(1)) &&
												change instanceof DeactivationChange){
											changeToUpdate = change;
											break;			    			
										}
									} else {
										if (df.format(change.getStart()).equals(availableChanges.getItem(availableChanges.getSelectionIndex()).getText(1)) && 
												!(change instanceof DeactivationChange) && 
												((PropertyChange)change).getAffectedProperty().equals(availableChanges.getItem(availableChanges.getSelectionIndex()).getText(0))){
											changeToUpdate = change;
											break;			    			
										}
									}
								}
								if (checkForOverlap(changeToUpdate) != true)
									setErrorMessage("This change overlaps with existing change!!");
								else {
									Change updatedChange = updChange(changeToUpdate);
									TableItem item = availableChanges.getItem(availableChanges.getSelectionIndex());
									if (changeType.equals("Enumeration Change"))
										item.setText(new String[] {((EnumChange) updatedChange).getAffectedProperty(), df.format(updatedChange.getStart()), 
												df.format(updatedChange.getEnd()), changeType, ((EnumChange) updatedChange).getNewValue(), ""});
								}
							}

						}
						else if (affectedProperty.startsWith("Pre-Condition") && newValueSelectVariable.getItemCount() == 0 && newValueSelectVariable.getText().isEmpty() && newValueSelectVariable.isEnabled() == true)
							setErrorMessage("Please create a new Variable!!");
						else if (affectedProperty.startsWith("Pre-Condition") && newValueSelectVariable.getText().isEmpty() && newValueSelectVariable.isEnabled() == true)
							setErrorMessage("Please select a Variable!!");
						else if (affectedProperty.startsWith("Pre-Condition") && newValuePluginBindingPreCond.getText().isEmpty() && newValuePluginBindingPreCond.getEnabled() == true)
							setErrorMessage("Please enter the Pre Condition!!");
						else if (affectedProperty.startsWith("Pre-Condition") && wrongFormat.equals(true) && newValuePluginBindingPreCond.isEnabled() == true)
							setErrorMessage(errorMessage);
						else {
							DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
							java.util.List availChanges = DynamicContextsUtils.getAllAvailableChanges(ManageChangeEditorPage.this.parent, currentDynContext, urn);
							Change changeToUpdate = null;
							for (Iterator iter = availChanges.iterator(); iter.hasNext();) {
								Change change = (Change) iter.next();        				
								if (availableChanges.getItem(availableChanges.getSelectionIndex()).getText(0).startsWith("Selected") || availableChanges.getItem(availableChanges.getSelectionIndex()).getText(0).startsWith("Select")) {
									if (df.format(change.getStart()).equals(availableChanges.getItem(availableChanges.getSelectionIndex()).getText(1)) &&
											change instanceof DeactivationChange){
										changeToUpdate = change;
										break;			    			
									}
								} else {
									if (df.format(change.getStart()).equals(availableChanges.getItem(availableChanges.getSelectionIndex()).getText(1)) && 
											!(change instanceof DeactivationChange) && 
											((PropertyChange)change).getAffectedProperty().equals(availableChanges.getItem(availableChanges.getSelectionIndex()).getText(0))){
										if (change instanceof TextChange) {
											if (affectedProperty.startsWith("Replication factor"))
												((TextChange) change).setNewValue(String.valueOf(newValue));
											else
												((TextChange) change).setNewValue(newValueExpression);
											changeToUpdate = change;
											break;
										} else {
											changeToUpdate = change;
											break;	
										}
									}
								}
							}
							if (checkForOverlap(changeToUpdate) != true)
								setErrorMessage("This change overlaps with existing change!!");
							else {
								Change updatedChange = updChange(changeToUpdate);
								TableItem item = availableChanges.getItem(availableChanges.getSelectionIndex());
								if (changeType.equals("Enumeration Change"))
									item.setText(new String[] {((EnumChange) updatedChange).getAffectedProperty(), df.format(updatedChange.getStart()), 
											df.format(updatedChange.getEnd()), changeType, ((EnumChange) updatedChange).getNewValue(), ""});
								else if (changeType.equals("Boolean Change")) {
									String value = Boolean.toString(((BooleanChange) updatedChange).isNewValue());
									item.setText(new String[] {((BooleanChange) updatedChange).getAffectedProperty(), df.format(updatedChange.getStart()), 
											df.format(updatedChange.getEnd()), changeType, value, ""});
								}
								else if (changeType.equals("Text Change"))
									item.setText(new String[] {((TextChange) updatedChange).getAffectedProperty(), df.format(updatedChange.getStart()), 
											df.format(updatedChange.getEnd()), changeType, ((TextChange) updatedChange).getNewValue(), ""});

							}
						}
					} else if (changeType.equals("Boolean Change") && affectedProperty.equals("Dynamic Stub") && newValueDynamicStub.getSelectionIndex() < 0 && (newValueDynamicStub.getEnabled() == true))
						setErrorMessage("Please select a boolean value for Dynamic Stub!!");
					else if (changeType.equals("Boolean Change") && affectedProperty.equals("Synchronizing Stub") && newValueSyncStub.getSelectionIndex() < 0 && (newValueSyncStub.getEnabled() == true))
						setErrorMessage("Please select a boolean value for Synchronizing Stub!!");
					else if (changeType.equals("Boolean Change") && affectedProperty.equals("Blocking Stub") && newValueBlockStub.getSelectionIndex() < 0 && (newValueBlockStub.getEnabled() == true))
						setErrorMessage("Please select a boolean value for Blocking Stub!!");
					else if (changeType.equals("Text Change") && affectedProperty.startsWith("Replication factor") && (newValueRepFactor.getText() == null || newValueRepFactor.getText().trim() == ""))
						setErrorMessage("Please enter the Replication Factor!!");
					else if (changeType.equals("Text Change") && affectedProperty.startsWith("Replication factor") && newValueRepFactor.getEnabled() == true && wrongFormatRepFactor.equals(true))
						setErrorMessage("Please enter a valid Replication Factor!!");
					else if (ManageChangeEditorPage.this.parent instanceof StartPoint && changeType.equals("Enumeration Change") && newValueFailureKind.getSelectionIndex() < 0 && newValueFailureKind.getEnabled() == true)
						setErrorMessage("Please select the Failure Kind!!");
					else if (ManageChangeEditorPage.this.parent instanceof PluginBinding && changeType.equals("Text Change") && (newValueRepFactor.getText() == null || newValueRepFactor.getText().trim() == ""))
						setErrorMessage("Please enter the Replication Factor!!");
					else if (ManageChangeEditorPage.this.parent instanceof PluginBinding && changeType.equals("Text Change") && wrongFormatRepFactor.equals(true))
						setErrorMessage("Please enter a valid Replication Factor!!");
					else if ((ManageChangeEditorPage.this.parent instanceof Timer || ManageChangeEditorPage.this.parent instanceof WaitingPlace) && newValueWaitKind.getSelectionIndex() < 0 && newValueWaitKind.getEnabled() == true)
						setErrorMessage("Please select a Wait Kind!!");
					else if (affectedProperty.startsWith("Pre-Condition") && newValueSelectVariable.getItemCount() == 0 && newValueSelectVariable.getText().isEmpty() && newValueSelectVariable.isEnabled() == true)
						setErrorMessage("Please create a new Variable!!");
					else if (affectedProperty.startsWith("Expression") && newValueSelectVariable.getItemCount() == 0 && newValueSelectVariable.getText().isEmpty() && newValueSelectVariable.isEnabled() == true)
						setErrorMessage("Please create a new Variable!!");
					else if (affectedProperty.equals("Post-Condition") && newValueSelectVariable.getItemCount() == 0 && newValueSelectVariable.getText().isEmpty() && newValueSelectVariable.isEnabled() == true)
						setErrorMessage("Please create a new Variable!!");
					else if (affectedProperty.equals("Failure List/Start Point Pre-Condition") && newValueSelectVariable.getItemCount() == 0 && newValueSelectVariable.getText().isEmpty() && newValueSelectVariable.isEnabled() == true)
						setErrorMessage("Please create a new Variable!!");
					else if (affectedProperty.equals("Failure Condition") && newValueSelectVariable.getItemCount() == 0 && newValueSelectVariable.getText().isEmpty() && newValueSelectVariable.isEnabled() == true)
						setErrorMessage("Please create a new Variable!!");
					else if (affectedProperty.startsWith("Pre-Condition") && newValueSelectVariable.getText().isEmpty() && newValueSelectVariable.isEnabled() == true)
						setErrorMessage("Please select a Variable!!");
					else if (affectedProperty.startsWith("Expression") && newValueSelectVariable.getText().isEmpty() && newValueSelectVariable.isEnabled() == true)
						setErrorMessage("Please select a Variable!!");
					else if (affectedProperty.equals("Post-Condition") && newValueSelectVariable.getText().isEmpty() && newValueSelectVariable.isEnabled() == true)
						setErrorMessage("Please select a Variable!!");
					else if (affectedProperty.equals("Failure List/Start Point Pre-Condition") && newValueSelectVariable.getText().isEmpty() && newValueSelectVariable.isEnabled() == true)
						setErrorMessage("Please select a Variable!!");
					else if (affectedProperty.equals("Failure Condition") && newValueSelectVariable.getText().isEmpty() && newValueSelectVariable.isEnabled() == true)
						setErrorMessage("Please select a Variable!!");
					else if (affectedProperty.startsWith("Pre-Condition") && newValuePluginBindingPreCond.getText().isEmpty() && newValuePluginBindingPreCond.getEnabled() == true)
						setErrorMessage("Please enter the Pre Condition!!");
					else if (affectedProperty.equals("Post-Condition") && newValueEndPtPostCond.getText().isEmpty() && newValueEndPtPostCond.getEnabled() == true)
						setErrorMessage("Please enter the Post Condition!!");
					else if (affectedProperty.startsWith("Expression") && newValueOrForkExp.getText().isEmpty() && newValueOrForkExp.getEnabled() == true)
						setErrorMessage("Please enter the Expression!!");
					else if (affectedProperty.equals("Failure Condition") && newValueFailureCondition.getText().isEmpty() && newValueFailureCondition.getEnabled() == true)
						setErrorMessage("Please enter the Failure Condition!!");
					else if (affectedProperty.equals("Failure List/Start Point Pre-Condition") && newValueFailureList.getText().isEmpty() && newValueFailureList.getEnabled() == true)
						setErrorMessage("Please enter the Failure List/Start Point Pre-Condition!!");
					else if (changeType.equals("Text Change") && affectedProperty.equals("Failure Expression") && newValueSelectVariable.getItemCount() == 0 && newValueSelectVariable.getText().isEmpty() && newValueSelectVariable.isEnabled() == true)
						setErrorMessage("Please create a new Variable!!");
					else if (changeType.equals("Text Change") && affectedProperty.equals("Failure Expression") && newValueSelectVariable.getText().isEmpty() && newValueSelectVariable.isEnabled() == true)
						setErrorMessage("Please select a Variable!!");
					else if (changeType.equals("Text Change") && affectedProperty.equals("Failure Expression") && newValueFailureExpression.getText().isEmpty() && newValueFailureExpression.getEnabled() == true)
						setErrorMessage("Please enter the Failure Expression!!");
					else if (changeType.equals("Text Change") && affectedProperty.equals("Failure Expression") && wrongFormat.equals(true) && newValueFailureExpression.getEnabled() == true)
						setErrorMessage(errorMessage);
					else if (affectedProperty.startsWith("Pre-Condition") && wrongFormat.equals(true) && newValuePluginBindingPreCond.isEnabled() == true)
						setErrorMessage(errorMessage);
					else if (affectedProperty.startsWith("Expression") && wrongFormat.equals(true) && newValueOrForkExp.getEnabled() == true)
						setErrorMessage(errorMessage);
					else if (affectedProperty.equals("Post-Condition") && wrongFormat.equals(true) && newValueEndPtPostCond.getEnabled() == true)
						setErrorMessage(errorMessage);
					else if (affectedProperty.equals("Failure List/Start Point Pre-Condition") && wrongFormat.equals(true) && newValueFailureList.getEnabled() == true)
						setErrorMessage(errorMessage);
					else if (affectedProperty.equals("Failure Condition") && wrongFormat.equals(true) && newValueFailureCondition.getEnabled() == true)
						setErrorMessage(errorMessage);
					else if (ManageChangeEditorPage.this.parent instanceof urncore.Condition) {
						if (changeType.equals("Boolean Change") && affectedProperty.equals("Dynamic Stub") && newValueDynamicStub.getSelectionIndex() < 0 && (newValueDynamicStub.getEnabled() == true))
							setErrorMessage("Please select a boolean value for Dynamic Stub!!");
						else if (changeType.equals("Boolean Change") && affectedProperty.equals("Synchronizing Stub") && newValueSyncStub.getSelectionIndex() < 0 && (newValueSyncStub.getEnabled() == true))
							setErrorMessage("Please select a boolean value for Synchronizing Stub!!");
						else if (changeType.equals("Boolean Change") && affectedProperty.equals("Blocking Stub") && newValueBlockStub.getSelectionIndex() < 0 && (newValueBlockStub.getEnabled() == true))
							setErrorMessage("Please select a boolean value for Blocking Stub!!");
						else if (changeType.equals("Text Change") && affectedProperty.startsWith("Replication factor") && (newValueRepFactor.getText() == null || newValueRepFactor.getText().trim() == ""))
							setErrorMessage("Please enter the Replication Factor!!");
						else if (changeType.equals("Text Change") && affectedProperty.startsWith("Replication factor") && newValueRepFactor.getEnabled() == true && wrongFormatRepFactor.equals(true))
							setErrorMessage("Please enter a valid Replication Factor!!");
						else if (changeType.equals("Text Change") && affectedProperty.startsWith("Pre-Condition") && newValueSelectVariable.getItemCount() == 0 && newValueSelectVariable.getText().isEmpty() && newValueSelectVariable.isEnabled() == true)
							setErrorMessage("Please create a new Variable!!");
						else if (changeType.equals("Text Change") && affectedProperty.startsWith("Expression") && newValueSelectVariable.getItemCount() == 0 && newValueSelectVariable.getText().isEmpty() && newValueSelectVariable.isEnabled() == true)
							setErrorMessage("Please create a new Variable!!");
						else if (changeType.equals("Text Change") && affectedProperty.equals("Post-Condition") && newValueSelectVariable.getItemCount() == 0 && newValueSelectVariable.getText().isEmpty() && newValueSelectVariable.isEnabled() == true)
							setErrorMessage("Please create a new Variable!!");
						else if (changeType.equals("Text Change") && affectedProperty.equals("Failure List/Start Point Pre-Condition") && newValueSelectVariable.getItemCount() == 0 && newValueSelectVariable.getText().isEmpty() && newValueSelectVariable.isEnabled() == true)
							setErrorMessage("Please create a new Variable!!");
						else if (changeType.equals("Text Change") && affectedProperty.equals("Failure Condition") && newValueSelectVariable.getItemCount() == 0 && newValueSelectVariable.getText().isEmpty() && newValueSelectVariable.isEnabled() == true)
							setErrorMessage("Please create a new Variable!!");
						else if (changeType.equals("Text Change") && affectedProperty.equals("Failure Expression") && newValueSelectVariable.getItemCount() == 0 && newValueSelectVariable.getText().isEmpty() && newValueSelectVariable.isEnabled() == true)
							setErrorMessage("Please create a new Variable!!");
						else if (changeType.equals("Text Change") && affectedProperty.startsWith("Pre-Condition") && newValueSelectVariable.getText().isEmpty() && newValueSelectVariable.isEnabled() == true)
							setErrorMessage("Please select a Variable!!");
						else if (changeType.equals("Text Change") && affectedProperty.startsWith("Expression") && newValueSelectVariable.getText().isEmpty() && newValueSelectVariable.isEnabled() == true)
							setErrorMessage("Please select a Variable!!");
						else if (changeType.equals("Text Change") && affectedProperty.equals("Post-Condition") && newValueSelectVariable.getText().isEmpty() && newValueSelectVariable.isEnabled() == true)
							setErrorMessage("Please select a Variable!!");
						else if (changeType.equals("Text Change") && affectedProperty.equals("Failure List/Start Point Pre-Condition") && newValueSelectVariable.getText().isEmpty() && newValueSelectVariable.isEnabled() == true)
							setErrorMessage("Please select a Variable!!");
						else if (changeType.equals("Text Change") && affectedProperty.equals("Failure Condition") && newValueSelectVariable.getText().isEmpty() && newValueSelectVariable.isEnabled() == true)
							setErrorMessage("Please select a Variable!!");
						else if (changeType.equals("Text Change") && affectedProperty.equals("Failure Expression") && newValueSelectVariable.getText().isEmpty() && newValueSelectVariable.isEnabled() == true)
							setErrorMessage("Please select a Variable!!");
						else if (changeType.equals("Text Change") && affectedProperty.startsWith("Pre-Condition") && newValuePluginBindingPreCond.getText().isEmpty() && newValuePluginBindingPreCond.getEnabled() == true)
							setErrorMessage("Please enter the Pre Condition!!");
						else if (changeType.equals("Text Change") && affectedProperty.equals("Post-Condition") && newValueEndPtPostCond.getText().isEmpty() && newValueEndPtPostCond.getEnabled() == true)
							setErrorMessage("Please enter the Post Condition!!");
						else if (changeType.equals("Text Change") && affectedProperty.startsWith("Expression") && newValueOrForkExp.getText().isEmpty() && newValueOrForkExp.getEnabled() == true)
							setErrorMessage("Please enter the Expression!!");
						else if (changeType.equals("Text Change") && affectedProperty.equals("Failure Condition") && newValueFailureCondition.getText().isEmpty() && newValueFailureCondition.getEnabled() == true)
							setErrorMessage("Please enter the Failure Condition!!");
						else if (changeType.equals("Text Change") && affectedProperty.equals("Failure Expression") && newValueFailureExpression.getText().isEmpty() && newValueFailureExpression.getEnabled() == true)
							setErrorMessage("Please enter the Failure Expression!!");
						else if (changeType.equals("Text Change") && affectedProperty.equals("Failure List/Start Point Pre-Condition") && newValueFailureList.getText().isEmpty() && newValueFailureList.getEnabled() == true)
							setErrorMessage("Please enter the Failure List/Start Point Pre-Condition!!");
						else if (changeType.equals("Text Change") && affectedProperty.startsWith("Pre-Condition") && wrongFormat.equals(true) && newValuePluginBindingPreCond.isEnabled() == true)
							setErrorMessage(errorMessage);
						else if (changeType.equals("Text Change") && affectedProperty.startsWith("Expression") && wrongFormat.equals(true) && newValueOrForkExp.getEnabled() == true)
							setErrorMessage(errorMessage);
						else if (changeType.equals("Text Change") && affectedProperty.equals("Post-Condition") && wrongFormat.equals(true) && newValueEndPtPostCond.getEnabled() == true)
							setErrorMessage(errorMessage);
						else if (changeType.equals("Text Change") && affectedProperty.equals("Failure List/Start Point Pre-Condition") && wrongFormat.equals(true) && newValueFailureList.getEnabled() == true)
							setErrorMessage(errorMessage);
						else if (changeType.equals("Text Change") && affectedProperty.equals("Failure Condition") && wrongFormat.equals(true) && newValueFailureCondition.getEnabled() == true)
							setErrorMessage(errorMessage);
						else if (changeType.equals("Text Change") && affectedProperty.equals("Failure Expression") && wrongFormat.equals(true) && newValueFailureExpression.getEnabled() == true)
							setErrorMessage(errorMessage);
						else {
							DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
							java.util.List availChanges = DynamicContextsUtils.getAllAvailableChanges(ManageChangeEditorPage.this.parent, currentDynContext, urn);
							Change changeToUpdate = null;
							for (Iterator iter = availChanges.iterator(); iter.hasNext();) {
								Change change = (Change) iter.next();        				
								if (availableChanges.getItem(availableChanges.getSelectionIndex()).getText(0).startsWith("Selected") || availableChanges.getItem(availableChanges.getSelectionIndex()).getText(0).startsWith("Select")) {
									if (df.format(change.getStart()).equals(availableChanges.getItem(availableChanges.getSelectionIndex()).getText(1)) &&
											change instanceof DeactivationChange){
										changeToUpdate = change;
										break;			    			
									}
								} else {
									if (df.format(change.getStart()).equals(availableChanges.getItem(availableChanges.getSelectionIndex()).getText(1)) && 
											!(change instanceof DeactivationChange) && 
											((PropertyChange)change).getAffectedProperty().equals(availableChanges.getItem(availableChanges.getSelectionIndex()).getText(0))){
										if (change instanceof TextChange) {
											if (affectedProperty.startsWith("Replication factor"))
												((TextChange) change).setNewValue(String.valueOf(newValue));
											else
												((TextChange) change).setNewValue(newValueExpression);
											changeToUpdate = change;
											break;
										} else {
											changeToUpdate = change;
											break;	
										}			    			
									}
								}
							}
							if (checkForOverlap(changeToUpdate) != true)
								setErrorMessage("This change overlaps with existing change!!");
							else {
								Change updatedChange = updChange(changeToUpdate);
								TableItem item = availableChanges.getItem(availableChanges.getSelectionIndex());
								if (changeType.equals("Deactivation Change"))
									item.setText(new String[] {affectedProperty, df.format(updatedChange.getStart()), 
											df.format(updatedChange.getEnd()), changeType, "", ""});
								else if (changeType.equals("Enumeration Change"))
									item.setText(new String[] {((EnumChange) updatedChange).getAffectedProperty(), df.format(updatedChange.getStart()), 
											df.format(updatedChange.getEnd()), changeType, ((EnumChange) updatedChange).getNewValue(), ""});
								else if (changeType.equals("Boolean Change")) {
									String value = Boolean.toString(((BooleanChange) updatedChange).isNewValue());
									item.setText(new String[] {((BooleanChange) updatedChange).getAffectedProperty(), df.format(updatedChange.getStart()), 
											df.format(updatedChange.getEnd()), changeType, value, ""});
								}
								else if (changeType.equals("Text Change"))
									item.setText(new String[] {((TextChange) updatedChange).getAffectedProperty(), df.format(updatedChange.getStart()), 
											df.format(updatedChange.getEnd()), changeType, ((TextChange) updatedChange).getNewValue(), ""});

							}
						}	
					}
					else {
						DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
						java.util.List availChanges = DynamicContextsUtils.getAllAvailableChanges(ManageChangeEditorPage.this.parent, currentDynContext, urn);
						Change changeToUpdate = null;
						for (Iterator iter = availChanges.iterator(); iter.hasNext();) {
							Change change = (Change) iter.next();        				
							if (availableChanges.getItem(availableChanges.getSelectionIndex()).getText(0).startsWith("Selected") || availableChanges.getItem(availableChanges.getSelectionIndex()).getText(0).startsWith("Select")) {
								if (df.format(change.getStart()).equals(availableChanges.getItem(availableChanges.getSelectionIndex()).getText(1)) &&
										change instanceof DeactivationChange){
									changeToUpdate = change;
									break;			    			
								}
							} else {
								if (df.format(change.getStart()).equals(availableChanges.getItem(availableChanges.getSelectionIndex()).getText(1)) && 
										!(change instanceof DeactivationChange) && 
										((PropertyChange)change).getAffectedProperty().equals(availableChanges.getItem(availableChanges.getSelectionIndex()).getText(0))){
									if (change instanceof TextChange) {
										if (affectedProperty.startsWith("Replication factor"))
											((TextChange) change).setNewValue(String.valueOf(newValue));
										else
											((TextChange) change).setNewValue(newValueExpression);
										changeToUpdate = change;
										break;
									} else {
										changeToUpdate = change;
										break;	
									}			    			
								}
							}
						}
						if (checkForOverlap(changeToUpdate) != true)
							setErrorMessage("This change overlaps with existing change!!");
						else {
							Change updatedChange = updChange(changeToUpdate);
							TableItem item = availableChanges.getItem(availableChanges.getSelectionIndex());
							if (changeType.equals("Deactivation Change"))
								item.setText(new String[] {affectedProperty, df.format(updatedChange.getStart()), 
										df.format(updatedChange.getEnd()), changeType, "", ""});
							else if (changeType.equals("Enumeration Change"))
								item.setText(new String[] {((EnumChange) updatedChange).getAffectedProperty(), df.format(updatedChange.getStart()), 
										df.format(updatedChange.getEnd()), changeType, ((EnumChange) updatedChange).getNewValue(), ""});
							else if (changeType.equals("Boolean Change")) {
								String value = Boolean.toString(((BooleanChange) updatedChange).isNewValue());
								item.setText(new String[] {((BooleanChange) updatedChange).getAffectedProperty(), df.format(updatedChange.getStart()), 
										df.format(updatedChange.getEnd()), changeType, value, ""});
							}
							else if (changeType.equals("Text Change"))
								item.setText(new String[] {((TextChange) updatedChange).getAffectedProperty(), df.format(updatedChange.getStart()), 
										df.format(updatedChange.getEnd()), changeType, ((TextChange) updatedChange).getNewValue(), ""});

						}
					}
				}

				if (ManageChangeEditorPage.this.parent instanceof GRLmodelElement || ManageChangeEditorPage.this.parent instanceof ActorRefEditPart
						|| ManageChangeEditorPage.this.parent instanceof IntentionalElementEditPart || ManageChangeEditorPage.this.parent instanceof LinkRefEditPart) {

        		if (currentDynContext != oldDynContext && oldDynContext != null)
					setErrorMessage("Can't update Dynamic Context of a Change");
				else if (ManageChangeEditorPage.this.parent == null)
					setErrorMessage("No element selected!!");
				else if (affectedProperty == null)
					setErrorMessage("Please selected the affected attribute of the selected element!!");
				else if (changeType == null)
					setErrorMessage("Please select a change type!!");
				else if (!startDate.before(endDate))
					setErrorMessage("Start Date should be before End Date!!");
				else if ((changeType.equals("Constant Change") || changeType.equals("Linear Change")) && (newValueText.getText() == null || newValueText.getText().trim() == ""))
					setErrorMessage("Please enter the new Value!!");
					else if (changeType.equals("Enumeration Change") && newValueDecomp.getSelectionIndex() < 0 && (ManageChangeEditorPage.this.parent instanceof IntentionalElementEditPart || ManageChangeEditorPage.this.parent instanceof IntentionalElement))
					setErrorMessage("Please select a Decomposition Type!!");
				else if (changeType.equals("Quadratic Change") && (newValueQuad.getText() == null || newValueQuad.getText().trim() == "" || 
						newValueLin.getText() == null || newValueLin.getText().trim() == "" || newValueCon.getText() == null || 
							newValueCon.getText().trim() == ""))
					setErrorMessage("Please enter all coefficients!!");
				else if ((changeType.equals("Formula Change")) && (newValueFor.getText() == null || newValueFor.getText().trim() == ""))
					setErrorMessage("Please enter the formula!!");
				else if(wrongFormat)
					setErrorMessage(Messages.getString("ManageChangePage.NumberFormatWrong"));
				else{
					DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
					java.util.List availChanges = DynamicContextsUtils.getAllAvailableChanges(ManageChangeEditorPage.this.parent, currentDynContext, urn);
			    	Change changeToUpdate = null;
			    	for (Iterator iter = availChanges.iterator(); iter.hasNext();) {
			    		Change change = (Change) iter.next();
			    		if (availableChanges.getItem(availableChanges.getSelectionIndex()).getText(0).startsWith("Selected")) {
				    		if (df.format(change.getStart()).equals(availableChanges.getItem(availableChanges.getSelectionIndex()).getText(1)) &&
				    				change instanceof DeactivationChange){
				    			changeToUpdate = change;
				    			break;			    			
				    		}
        				} else {
        					if (df.format(change.getStart()).equals(availableChanges.getItem(availableChanges.getSelectionIndex()).getText(1)) && 
        							!(change instanceof DeactivationChange) && 
				    				((PropertyChange)change).getAffectedProperty().equals(availableChanges.getItem(availableChanges.getSelectionIndex()).getText(0))){
				    			changeToUpdate = change;
				    			break;			    			
				    		}
							}
			    	}
			    	if (checkForOverlap(changeToUpdate) != true)
								setErrorMessage("This change overlaps with existing change!!");
				    	 else {
				    		 Change updatedChange = updChange(changeToUpdate);
				    		 TableItem item = availableChanges.getItem(availableChanges.getSelectionIndex());
				    		 if (changeType.equals("Constant Change"))
				    			 item.setText(new String[] {((ConstantChange) updatedChange).getAffectedProperty(), df.format(updatedChange.getStart()), 
				    					 df.format(updatedChange.getEnd()), changeType, Integer.toString(((ConstantChange) updatedChange).getNewValue()), ""});
				    		 else if (changeType.equals("Linear Change"))
				    			 item.setText(new String[] {((LinearChange) updatedChange).getAffectedProperty(), df.format(updatedChange.getStart()), 
				    					 df.format(updatedChange.getEnd()), changeType, Integer.toString(((LinearChange) updatedChange).getNewValue()), ""});
				    		 else if (changeType.equals("Quadratic Change")) {
								String formula = Float.toString(((QuadraticChange)updatedChange).getQuadraticCoefficient()) + "t²+" +
				            				Float.toString(((QuadraticChange)updatedChange).getLinearCoefficient()) + "t+" +
				            				Float.toString(((QuadraticChange)updatedChange).getConstant());
									item.setText(new String[] {((QuadraticChange) updatedChange).getAffectedProperty(), df.format(updatedChange.getStart()), 
						            		df.format(updatedChange.getEnd()), changeType, "", formula});
							 }
				    		 else if (changeType.equals("Formula Change"))
									item.setText(new String[] {((FormulaChange) updatedChange).getAffectedProperty(), df.format(updatedChange.getStart()), 
						            		df.format(updatedChange.getEnd()), changeType, "", ((FormulaChange) updatedChange).getFormula()});
							 else if (changeType.equals("Deactivation Change"))
								item.setText(new String[] {affectedProperty, df.format(updatedChange.getStart()), 
						            		df.format(updatedChange.getEnd()), changeType, "", ""});
							 else if (changeType.equals("Enumeration Change"))
				    			 item.setText(new String[] {((EnumChange) updatedChange).getAffectedProperty(), df.format(updatedChange.getStart()), 
				    					 df.format(updatedChange.getEnd()), changeType, ((EnumChange) updatedChange).getNewValue(), ""});
							 
				    		 
				    	 }
			    	
				}
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
        
        updButton.setLayoutData(gd);
        
        //Button to delete change
        delButton = new Button(container, SWT.PUSH);
        delButton.setText("Delete Change");
        delButton.setEnabled(false);
        delButton.addSelectionListener(new SelectionListener() {
			
        	@Override
			public void widgetSelected(SelectionEvent e) {
        		dialog = new MessageBox(container.getShell(), SWT.ICON_QUESTION | SWT.OK| SWT.CANCEL);
        		dialog.setText("Warning");
        		dialog.setMessage(Messages.getString("ManageChangePage.DeleteConfirmation"));
        		int msgId = dialog.open();
        		switch(msgId) {
                case SWT.OK:
                	DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
    				java.util.List availChanges = DynamicContextsUtils.getAllAvailableChanges(ManageChangeEditorPage.this.parent, currentDynContext, urn);
    		    	Change changeToDelete = null;
    		    	for (Iterator iter = availChanges.iterator(); iter.hasNext();) {
    		    		Change change = (Change) iter.next();
    		    		if (df.format(change.getStart()).equals(availableChanges.getItem(availableChanges.getSelectionIndex()).getText(1)) && 
								((((availableChanges.getItem(availableChanges.getSelectionIndex()).getText(0).startsWith("Selected") || availableChanges.getItem(availableChanges.getSelectionIndex()).getText(0).startsWith("Select")) && change instanceof DeactivationChange) 
    		    						|| (!(change instanceof DeactivationChange) && ((PropertyChange)change).getAffectedProperty().equals(availableChanges.getItem(availableChanges.getSelectionIndex()).getText(0)))))){
    		    			changeToDelete = change;
    		    			break;			    			
    		    		}
    		    	}
    		    	deleteChange(changeToDelete);
    		    	availableChanges.remove(availableChanges.getSelectionIndex());
    		    	
    		    	//Clear out all the fields
    		    	clearChangeData();
    		    	
                case SWT.CANCEL:
                  // does nothing ...
              }
        		
		    	
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
        
        delButton.setLayoutData(gd);
        
        //Button to deselect change from table
        deselButton = new Button(container, SWT.PUSH);
        deselButton.setText("Deselect");
        deselButton.setEnabled(false);
        deselButton.addSelectionListener(new SelectionListener() {
			
        	@Override
			public void widgetSelected(SelectionEvent e) {
        		clearChangeData();
        	}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
        
        deselButton.setLayoutData(gd);
        
        //Update the Manage Changes Page if a Dynamic Context is selected when the page is opened
        if (selectedDynContext != null) {
        	dynamicContexts.setText(URNNamingHelper.getName((DynamicContextGroup) selectedDynContext.getGroups().get(0)) + ":" + URNNamingHelper.getName(selectedDynContext));
        	dialogChanged();
			if (ManageChangeEditorPage.this.parent instanceof StubEditPart) {
				List<PluginBinding> bindings = ((Stub)((StubEditPart) ManageChangeEditorPage.this.parent).getModel()).getBindings();
				for (PluginBinding p : bindings) {
					String plugin = "Select " + p.getPlugin().getName();
					String expression = "Pre-Condition- " + p.getPlugin().getName();
					if (plugin.equals(affectedProperty))
						ManageChangeEditorPage.this.parent = p;
					else if (expression.equals(affectedProperty))
						ManageChangeEditorPage.this.parent = p.getPrecondition();
				}				
			}

            String[][] availChanges1 = getAvailableChanges();
            availableChanges.removeAll();
            if (availChanges1 != null) {
    	        for (int i = 0; i < availChanges1.length; i++) {
    	            TableItem item = new TableItem(availableChanges, SWT.NONE);
    	            item.setText(new String[] {availChanges1[i][0], availChanges1[i][1], availChanges1[i][2], availChanges1[i][3], availChanges1[i][4], availChanges1[i][5]});
    	            
    	            //If the item belongs to included dynamic contexts (indicated by disabledFrom index), then grey out that item
    	            if (disabledFrom != -1 && i >= disabledFrom) {
    	            	item.setBackground(ColorManager.GRAY);
    	            }
    	        }
            }
            availableChanges.update();
            availableChanges.setEnabled(true);
            affectedProperties.setEnabled(true);
        }
        
        //Select the change in ManageChange page if opened from property view 
        if (selectedChange != null) {
        	DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        	for (int i = 0; i < availableChanges.getItemCount(); i++) {
				if (availableChanges.getItem(i).getText(0).startsWith("Selected") || availableChanges.getItem(i).getText(0).startsWith("Select")) {
		    		if (selectedChange instanceof DeactivationChange && 
		    				df.format(selectedChange.getStart()).equals(availableChanges.getItem(i).getText(1))){
		    			availableChanges.select(i);
		    			availableChanges.notifyListeners(SWT.Selection, new Event());
		    			break;			    			
		    		}
				} else {
					if (df.format(selectedChange.getStart()).equals(availableChanges.getItem(i).getText(1)) && !(selectedChange instanceof DeactivationChange) && 
		    				((PropertyChange)selectedChange).getAffectedProperty().equals(availableChanges.getItem(i).getText(0))){
		    			availableChanges.select(i);
		    			availableChanges.notifyListeners(SWT.Selection, new Event());
		    			break;			    			
		    		}
				}
        			
        	}
        	
        	
        }
        dialogChanged();
        setControl(container);
        container.pack();

	}

	/**
	 * Method to create a button to create a new variable
	 */
	private void createNewVariableButton() {
		//Button to create a new variable
		createNewVariable = new Button(container, SWT.PUSH);
		createNewVariable.setText("Create New Variable");
		createNewVariable.setEnabled(false);
		createNewVariable.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
				AddVariableWizard wizard = new AddVariableWizard();

				if (urn != null) {
					wizard.init(PlatformUI.getWorkbench(), new StructuredSelection(urn));
					WizardDialog dialog = new WizardDialog(shell, wizard);
					dialog.open();

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
					newValueSelectVariable.setItems(vars);
				}
			}
		});
	}

	/**
	 * Method to create a comboBox to select variable
	 */
	private void createNewValueSelectVariable() {
		//Combo to select variable
		newValueSelectVariable = new Combo(container, SWT.DROP_DOWN | SWT.READ_ONLY);
		newValueSelectVariable.setEnabled(false);
		if (urn != null) {

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
			newValueSelectVariable.setItems(vars);
		}
    }
    
    private void clearChangeData() {
    	affectedProperties.deselectAll();
    	affectedProperties.setEnabled(true);
    	changes.deselectAll();
    	Date today = new Date();
    	startCalendar.setDate(today.getYear()+1900, today.getMonth(), today.getDate());
    	startCalendar.setTime(0, 0, 0);
    	endCalendar.setDate(today.getYear()+1900, today.getMonth(), today.getDate());
    	endCalendar.setTime(0, 0, 0);

		if (ManageChangeEditorPage.this.parent instanceof StartPoint) { 
			newValueFailureKind.deselectAll();
			newValueFailureList.setText("");
			newValueSelectVariable.deselectAll();
		}
		else if (ManageChangeEditorPage.this.parent instanceof Timer || ManageChangeEditorPage.this.parent instanceof WaitingPlace)
			newValueWaitKind.deselectAll();
		else if (this.parent instanceof RespRefEditPart || this.parent instanceof Responsibility) {
			newValueRespExpression.setText("");
			newValueSelectVariable.deselectAll();
		}
		else if (this.parent instanceof StubEditPart || this.parent instanceof PluginBinding || this.parent instanceof Stub) {
			newValueDynamicStub.deselectAll();
			newValueSyncStub.deselectAll();
			newValueBlockStub.deselectAll();
			newValueRepFactor.setText("");
			newValuePluginBindingPreCond.setText("");
			newValueSelectVariable.deselectAll();
		}
		else if (ManageChangeEditorPage.this.parent instanceof PathNodeEditPart) {
			if ((((PathNodeEditPart) this.parent).getModel()) instanceof StartPoint) {
				newValueFailureKind.deselectAll();
				newValueFailureList.setText("");
				newValueSelectVariable.deselectAll();	
			}
			else if ((((PathNodeEditPart) this.parent).getModel()) instanceof EndPoint) {
				newValueEndPtPostCond.setText("");
				newValueSelectVariable.deselectAll();
			}
			else if ((((PathNodeEditPart) this.parent).getModel()) instanceof FailurePoint) {
				newValueFailureExpression.setText("");
				newValueFailureCondition.setText("");
				newValueSelectVariable.deselectAll();
			}
			else if ((((PathNodeEditPart) this.parent).getModel()) instanceof OrFork) {
				newValueOrForkExp.setText("");
				newValueSelectVariable.deselectAll();
			}
			else if ((((PathNodeEditPart) (ManageChangeEditorPage.this.parent)).getModel() instanceof Timer) || (((PathNodeEditPart) (ManageChangeEditorPage.this.parent)).getModel() instanceof WaitingPlace))
				newValueWaitKind.deselectAll();
		}
		else if (this.parent instanceof ComponentRefEditPart || this.parent instanceof Component) {
			newValueCompKind.deselectAll();
			newValueCompProtection.deselectAll();
			newValueCompContext.deselectAll();
		}
		else if (this.parent instanceof urncore.Condition && affectedProperty.startsWith("Pre-Condition")) {
			newValueRepFactor.setText("");
			newValuePluginBindingPreCond.setText("");
			newValueSelectVariable.deselectAll();
		}
		else if (this.parent instanceof urncore.Condition && affectedProperty.equals("Failure List/Start Point Pre-Condition")) {
			newValueFailureList.setText("");
			newValueSelectVariable.deselectAll();
		}
		else if (this.parent instanceof urncore.Condition && affectedProperty.equals("Post-Condition")) {
			newValueEndPtPostCond.setText("");
			newValueSelectVariable.deselectAll();
		}
		else if (this.parent instanceof urncore.Condition && affectedProperty.startsWith("Expression")) {
			newValueOrForkExp.setText("");
			newValueSelectVariable.deselectAll();
		}
		else if (this.parent instanceof urncore.Condition && (affectedProperty.equals("Failure Condition") || affectedProperty.equals("Failure Expression"))) {
			newValueFailureCondition.setText("");
			newValueFailureExpression.setText("");
			newValueSelectVariable.deselectAll();
		}
		else if (this.parent instanceof FailurePoint) {
			newValueFailureExpression.setText("");
			newValueFailureCondition.setText("");
			newValueSelectVariable.deselectAll();
		}

		if (this.parent instanceof GRLmodelElement || this.parent instanceof ActorRefEditPart
				|| this.parent instanceof IntentionalElementEditPart || this.parent instanceof LinkRefEditPart) {

    	newValueText.setText("");
    	newValueDecomp.deselectAll();
    	newValueQuad.setText("");
    	newValueLin.setText("");
    	newValueCon.setText("");
    	newValueFor.setText("");
		}

    	availableChanges.deselectAll();
    	addButton.setEnabled(true);
    	updButton.setEnabled(false);
    	delButton.setEnabled(false);
    	deselButton.setEnabled(false);
    }
    
    /*
     * Checks if the available changes overlap with the new change's start or end date
     */
    private boolean checkForOverlap() {
    	java.util.List availableChanges = DynamicContextsUtils.getAllAvailableChanges(parent, currentDynContext, urn);
    	for (Iterator iter = availableChanges.iterator(); iter.hasNext();) {
    		Change change = (Change) iter.next();
			if (((affectedProperty.startsWith("Selected") || affectedProperty.startsWith("Select")) && change instanceof DeactivationChange)
					|| ((change instanceof PropertyChange) && ((PropertyChange)change).getAffectedProperty().equals(affectedProperty))) {
    			if ((change.getStart().before(startDate) && change.getEnd().after(startDate)) || change.getStart().equals(startDate) || 
    					(change.getStart().after(startDate) && change.getStart().before(endDate)))
					return false;
    		}
    	}
    	return true;
	}


	/*
	 * Checks for the different types of changes and enables the corresponding Combo or textbox  
	 */
	private void checkForChanges() {
		if (ManageChangeEditorPage.this.parent instanceof StubEditPart) {
			if (affectedProperty.contains("Replication factor")) {
				List<PluginBinding> bindings = ((Stub)((StubEditPart) ManageChangeEditorPage.this.parent).getModel()).getBindings();
				for (PluginBinding p : bindings) {
					String plugin = p.getPlugin().getName();
					if (affectedProperty.contains(plugin))
						ManageChangeEditorPage.this.parent = p;
				}
			} else if (affectedProperty.startsWith("Pre-Condition-")) {
				List<PluginBinding> bindings = ((Stub)((StubEditPart) ManageChangeEditorPage.this.parent).getModel()).getBindings();
				for (PluginBinding p : bindings) {
					String plugin = p.getPlugin().getName();
					if (affectedProperty.contains(plugin))
						ManageChangeEditorPage.this.parent = p.getPrecondition();
				}
			} else {
				List<PluginBinding> bindings = ((Stub)((StubEditPart) ManageChangeEditorPage.this.parent).getModel()).getBindings();
				for (PluginBinding p : bindings) {
					String plugin = "Select " + p.getPlugin().getName();
					if (plugin.equals(affectedProperty))
						ManageChangeEditorPage.this.parent = p;
				}
			}
		}
		if (ManageChangeEditorPage.this.parent instanceof PluginBinding) {
			if (affectedProperty.equals("Dynamic Stub") || affectedProperty.equals("Synchronizing Stub") || affectedProperty.equals("Blocking Stub"))
				ManageChangeEditorPage.this.parent = ((PluginBinding) ManageChangeEditorPage.this.parent).getStub();
			else if (affectedProperty.startsWith("Pre-Condition-")) {
				List<PluginBinding> bindings = ((PluginBinding) ManageChangeEditorPage.this.parent).getStub().getBindings();
				for (PluginBinding p : bindings) {
					String preCondPluginBinding = p.getPlugin().getName();
					if (affectedProperty.contains(preCondPluginBinding))
						ManageChangeEditorPage.this.parent = p.getPrecondition();
				}
			}
			else if (affectedProperty.contains("Replication factor")) {
				String plugin = affectedProperty;
				List<PluginBinding> bindings = ((PluginBinding) ManageChangeEditorPage.this.parent).getStub().getBindings();
				for (PluginBinding p : bindings) {
					String repFactorPluginBinding = p.getPlugin().getName();
					if (plugin.contains(repFactorPluginBinding))
						ManageChangeEditorPage.this.parent = p;
				}
			} else {
				String plugin = affectedProperty;
				List<PluginBinding> bindings = ((PluginBinding) ManageChangeEditorPage.this.parent).getStub().getBindings();
				for (PluginBinding p : bindings) {
					String binding = "Select " + p.getPlugin().getName();
					if (binding.equals(plugin))
						ManageChangeEditorPage.this.parent = p;
				}
			}
		}
		if (ManageChangeEditorPage.this.parent instanceof Stub) {
			if (affectedProperty.contains("Replication factor")) {
				String plugin = affectedProperty;
				List<PluginBinding> bindings = ((Stub) ManageChangeEditorPage.this.parent).getBindings();
				for (PluginBinding p : bindings) {
					String repFactorPluginBinding = p.getPlugin().getName();
					if (plugin.contains(repFactorPluginBinding))
						ManageChangeEditorPage.this.parent = p;
				}
			} else if (affectedProperty.startsWith("Pre-Condition-")) {
				List<PluginBinding> bindings = ((Stub) ManageChangeEditorPage.this.parent).getBindings();
				for (PluginBinding p : bindings) {
					String plugin = p.getPlugin().getName();
					if (affectedProperty.contains(plugin))
						ManageChangeEditorPage.this.parent = p.getPrecondition();
				}
			} 
		}
		if (ManageChangeEditorPage.this.parent instanceof urncore.Condition) {
			if (affectedProperty.startsWith("Replication factor") || affectedProperty.startsWith("Select UCM")) {
				List<PluginBinding> bindings = ((urncore.Condition) (ManageChangeEditorPage.this.parent)).getPluginBinding().getStub().getBindings();
				for (PluginBinding p : bindings) {
					if (affectedProperty.contains(p.getPlugin().getName()))
						ManageChangeEditorPage.this.parent = p;
				}
			}
			else if (affectedProperty.equals("Dynamic Stub") || affectedProperty.equals("Synchronizing Stub") || affectedProperty.equals("Blocking Stub"))
				ManageChangeEditorPage.this.parent = ((urncore.Condition) ManageChangeEditorPage.this.parent).getPluginBinding().getStub();
			else if (affectedProperty.startsWith("Expression")) {
				String id = ((OrFork) ((urncore.Condition) ManageChangeEditorPage.this.parent).getNodeConnection().getSource()).getId();
				List<NodeConnection> ncs = ((urncore.Condition) ManageChangeEditorPage.this.parent).getNodeConnection().getDiagram().getConnections();
				for (NodeConnection n : ncs) {
					if (n.getSource() instanceof OrFork) {
						String branch = "Expression- Branch: " + (n.getSource().getSucc().indexOf(n) + 1);
						String sourceId = ((OrFork) n.getSource()).getId();
						if (branch.equals(affectedProperty) && sourceId.equals(id))
							ManageChangeEditorPage.this.parent = n.getCondition();
					}
				}
			}
			else if (affectedProperty.startsWith("Pre-Condition-")) {
				List<PluginBinding> bindings = ((urncore.Condition) ManageChangeEditorPage.this.parent).getPluginBinding().getStub().getBindings();
				for (PluginBinding p : bindings) {
					if (affectedProperty.contains(p.getPlugin().getName()))
						ManageChangeEditorPage.this.parent = p.getPrecondition();
				}
			}
			else if (affectedProperty.equals("Failure Condition")) {
				NodeConnection ncs = ((urncore.Condition) ManageChangeEditorPage.this.parent).getNodeConnection();
				if (ncs.getSource() instanceof FailurePoint)
					ManageChangeEditorPage.this.parent = ((FailurePoint) ncs.getSource());
			}
			else if (affectedProperty.equals("Failure Kind"))
				ManageChangeEditorPage.this.parent = ((urncore.Condition) ManageChangeEditorPage.this.parent).getStartPoint();
			else if (affectedProperty.equals("Failure Expression") || affectedProperty.startsWith("Selected")) {
				NodeConnection ncs = ((urncore.Condition) ManageChangeEditorPage.this.parent).getNodeConnection();
				if (ncs.getSource() instanceof FailurePoint)
					ManageChangeEditorPage.this.parent = ncs.getSource();
			}
		}
		if (ManageChangeEditorPage.this.parent instanceof PathNodeEditPart) {
			if (((PathNodeEditPart) ManageChangeEditorPage.this.parent).getModel() instanceof StartPoint && affectedProperty.equals("Failure List/Start Point Pre-Condition"))
				ManageChangeEditorPage.this.parent = ((StartPoint) ((PathNodeEditPart) ManageChangeEditorPage.this.parent).getModel()).getPrecondition();
			else if (((PathNodeEditPart) ManageChangeEditorPage.this.parent).getModel() instanceof StartPoint && affectedProperty.equals("Failure Kind"))
				ManageChangeEditorPage.this.parent = (StartPoint) ((PathNodeEditPart) ManageChangeEditorPage.this.parent).getModel();
			else if (((PathNodeEditPart) ManageChangeEditorPage.this.parent).getModel() instanceof EndPoint && affectedProperty.equals("Post-Condition"))
				ManageChangeEditorPage.this.parent = ((EndPoint) ((PathNodeEditPart) ManageChangeEditorPage.this.parent).getModel()).getPostcondition();
			else if (((PathNodeEditPart) ManageChangeEditorPage.this.parent).getModel() instanceof FailurePoint && (affectedProperty.startsWith("Selected") || affectedProperty.equals("Failure Expression")))
				ManageChangeEditorPage.this.parent = (FailurePoint) ((PathNodeEditPart) ManageChangeEditorPage.this.parent).getModel();
			else if (((PathNodeEditPart) ManageChangeEditorPage.this.parent).getModel() instanceof OrFork && affectedProperty.startsWith("Expression")) {
				String branchName = affectedProperty;
				String id = ((OrFork) ((PathNodeEditPart) ManageChangeEditorPage.this.parent).getModel()).getId();
				List<NodeConnection> ncs = ((PathNodeEditPart) ManageChangeEditorPage.this.parent).getDiagram().getConnections();
				for (NodeConnection n : ncs) {
					if (n.getSource() instanceof OrFork) {
						String branch = "Expression- Branch: " + (n.getSource().getSucc().indexOf(n) + 1);
						String sourceId = ((OrFork) n.getSource()).getId();
						if (branch.equals(branchName) && id.equals(sourceId))
							ManageChangeEditorPage.this.parent = n.getCondition();
					}
				}
			}
			else if (((PathNodeEditPart) ManageChangeEditorPage.this.parent).getModel() instanceof FailurePoint && affectedProperty.equals("Failure Condition")) {
				String id = ((FailurePoint) ((PathNodeEditPart) this.parent).getModel()).getId();
				List<NodeConnection> ncs = ((PathNodeEditPart) ManageChangeEditorPage.this.parent).getDiagram().getConnections();
				for (NodeConnection n : ncs) {
					if (n.getSource() instanceof FailurePoint) {
						String sourceId = ((FailurePoint) n.getSource()).getId();
						if (id.equals(sourceId))
							ManageChangeEditorPage.this.parent = n.getCondition();
					}
				}
			}
		}
		if (ManageChangeEditorPage.this.parent instanceof EndPoint && affectedProperty.equals("Post-Condition"))
			ManageChangeEditorPage.this.parent = ((EndPoint) ManageChangeEditorPage.this.parent).getPostcondition();
		else if (ManageChangeEditorPage.this.parent instanceof StartPoint && affectedProperty.equals("Failure List/Start Point Pre-Condition"))
			ManageChangeEditorPage.this.parent = ((StartPoint) ManageChangeEditorPage.this.parent).getPrecondition();
		else if (ManageChangeEditorPage.this.parent instanceof StartPoint && affectedProperty.equals("Failure Kind"))
			ManageChangeEditorPage.this.parent = (StartPoint) ManageChangeEditorPage.this.parent;
		else if (ManageChangeEditorPage.this.parent instanceof FailurePoint && (affectedProperty.startsWith("Selected") || affectedProperty.equals("Failure Expression")))
			ManageChangeEditorPage.this.parent = (FailurePoint) ManageChangeEditorPage.this.parent;
		else if ((ManageChangeEditorPage.this.parent instanceof OrFork && affectedProperty.startsWith("Expression"))) {
			String branchName = affectedProperty;
			List<NodeConnection> ncs = ((OrFork) (ManageChangeEditorPage.this.parent)).getDiagram().getConnections();
			for (NodeConnection n : ncs) {
				if (n.getSource() instanceof OrFork) {
					String branch = "Expression- Branch: " + (n.getSource().getSucc().indexOf(n) + 1);
					if (branch.equals(branchName))
						ManageChangeEditorPage.this.parent = n.getCondition();
				}
			}
		} else if (ManageChangeEditorPage.this.parent instanceof FailurePoint && affectedProperty.equals("Failure Condition")) {
			List<NodeConnection> ncs = ((FailurePoint) (ManageChangeEditorPage.this.parent)).getDiagram().getConnections();
			for (NodeConnection n : ncs) {
				if (n.getSource() instanceof FailurePoint)
					ManageChangeEditorPage.this.parent = n.getCondition();
			}
		}

		if (changes.getText().equals("Enumeration Change")) {
			if(ManageChangeEditorPage.this.parent instanceof IntentionalElementEditPart || ManageChangeEditorPage.this.parent instanceof IntentionalElement)
				newValueDecomp.setEnabled(true);
			else if (ManageChangeEditorPage.this.parent instanceof ComponentRefEditPart || ManageChangeEditorPage.this.parent instanceof Component) {
				newValueCompKind.setEnabled(true);
				newValueCompProtection.setEnabled(false);
				newValueCompProtection.deselectAll();
				newValueCompContext.setEnabled(false);
				newValueCompContext.deselectAll();
			}
			else if (ManageChangeEditorPage.this.parent instanceof PathNodeEditPart) {
				if (((PathNodeEditPart) (ManageChangeEditorPage.this.parent)).getModel() instanceof StartPoint) {
					newValueFailureKind.setEnabled(true);
					newValueFailureList.setEnabled(false);
					newValueFailureList.setText("");
					createNewVariable.setEnabled(false);
					addVariableToExpression.setEnabled(false);
					newValueSelectVariable.setEnabled(false);
					newValueSelectVariable.deselectAll();
				} else if ((((PathNodeEditPart) (ManageChangeEditorPage.this.parent)).getModel() instanceof Timer) || (((PathNodeEditPart) (ManageChangeEditorPage.this.parent)).getModel() instanceof WaitingPlace))
					newValueWaitKind.setEnabled(true);
			}
			else if (ManageChangeEditorPage.this.parent instanceof StartPoint) {
				newValueFailureKind.setEnabled(true);
				newValueFailureList.setEnabled(false);
				newValueFailureList.setText("");
				createNewVariable.setEnabled(false);
				addVariableToExpression.setEnabled(false);
				newValueSelectVariable.setEnabled(false);
				newValueSelectVariable.deselectAll();
			}
			else if (ManageChangeEditorPage.this.parent instanceof Timer || ManageChangeEditorPage.this.parent instanceof WaitingPlace)
				newValueWaitKind.setEnabled(true);
		} else {
			if (ManageChangeEditorPage.this.parent instanceof IntentionalElementEditPart || ManageChangeEditorPage.this.parent instanceof IntentionalElement)
				newValueDecomp.setEnabled(false);
			else if (ManageChangeEditorPage.this.parent instanceof ComponentRefEditPart || ManageChangeEditorPage.this.parent instanceof Component) {
				newValueCompKind.setEnabled(false);
				newValueCompProtection.setEnabled(false);
				newValueCompContext.setEnabled(false);
			} else if (ManageChangeEditorPage.this.parent instanceof StartPoint)
				newValueFailureKind.setEnabled(false);
			else if (ManageChangeEditorPage.this.parent instanceof Timer || ManageChangeEditorPage.this.parent instanceof WaitingPlace)
				newValueWaitKind.setEnabled(false);
			else if (ManageChangeEditorPage.this.parent instanceof PathNodeEditPart) {
				if ((((PathNodeEditPart) ManageChangeEditorPage.this.parent).getModel()) instanceof StartPoint)
					newValueFailureKind.setEnabled(false);
				else if ((((PathNodeEditPart) (ManageChangeEditorPage.this.parent)).getModel() instanceof Timer) || (((PathNodeEditPart) (ManageChangeEditorPage.this.parent)).getModel() instanceof WaitingPlace))
					newValueWaitKind.setEnabled(false);
			}
		}

		if (changes.getText().equals("Boolean Change")) {
			if (ManageChangeEditorPage.this.parent instanceof ComponentRefEditPart || ManageChangeEditorPage.this.parent instanceof Component) {
				if (affectedProperty.equals("Component Protection")) {
					newValueCompProtection.setEnabled(true);
					newValueCompContext.setEnabled(false);
					newValueCompContext.deselectAll();
					newValueCompKind.setEnabled(false);
					newValueCompKind.deselectAll();
				}
				else if (affectedProperty.equals("Component Context")) {
					newValueCompContext.setEnabled(true);
					newValueCompProtection.setEnabled(false);
					newValueCompProtection.deselectAll();
					newValueCompKind.setEnabled(false);
					newValueCompKind.deselectAll();
				}
			} else if (ManageChangeEditorPage.this.parent instanceof StubEditPart || ManageChangeEditorPage.this.parent instanceof Stub || ManageChangeEditorPage.this.parent instanceof PluginBinding) {
				if (affectedProperty.equals("Dynamic Stub")) {
					newValueDynamicStub.setEnabled(true);
					newValueSyncStub.setEnabled(false);
					newValueSyncStub.deselectAll();
					newValueBlockStub.setEnabled(false);
					newValueBlockStub.deselectAll();
					newValueRepFactor.setText("");
					newValueRepFactor.setEnabled(false);
					newValuePluginBindingPreCond.setEnabled(false);
					newValuePluginBindingPreCond.setText("");
					addVariableToExpression.setEnabled(false);
					createNewVariable.setEnabled(false);
					newValueSelectVariable.setEnabled(false);
					newValueSelectVariable.deselectAll();
				} else if (affectedProperty.equals("Synchronizing Stub")) {
					newValueSyncStub.setEnabled(true);
					newValueDynamicStub.setEnabled(false);
					newValueDynamicStub.deselectAll();
					newValueBlockStub.setEnabled(false);
					newValueBlockStub.deselectAll();
					newValueRepFactor.setText("");
					newValueRepFactor.setEnabled(false);
					newValuePluginBindingPreCond.setEnabled(false);
					newValuePluginBindingPreCond.setText("");
					addVariableToExpression.setEnabled(false);
					createNewVariable.setEnabled(false);
					newValueSelectVariable.setEnabled(false);
					newValueSelectVariable.deselectAll();
				} else if (affectedProperty.equals("Blocking Stub")) {
					newValueBlockStub.setEnabled(true);
					newValueDynamicStub.setEnabled(false);
					newValueDynamicStub.deselectAll();
					newValueSyncStub.setEnabled(false);
					newValueSyncStub.deselectAll();
					newValueRepFactor.setText("");
					newValueRepFactor.setEnabled(false);
					newValuePluginBindingPreCond.setEnabled(false);
					newValuePluginBindingPreCond.setText("");
					addVariableToExpression.setEnabled(false);
					createNewVariable.setEnabled(false);
					newValueSelectVariable.setEnabled(false);
					newValueSelectVariable.deselectAll();
				}
			}
		} else {
			if (ManageChangeEditorPage.this.parent instanceof ComponentRefEditPart || ManageChangeEditorPage.this.parent instanceof Component) {
				newValueCompProtection.setEnabled(false);
				newValueCompContext.setEnabled(false);
			} else if (ManageChangeEditorPage.this.parent instanceof StubEditPart || ManageChangeEditorPage.this.parent instanceof Stub) {
				newValueDynamicStub.setEnabled(false);
				newValueDynamicStub.deselectAll();
				newValueSyncStub.setEnabled(false);
				newValueSyncStub.deselectAll();
				newValueBlockStub.setEnabled(false);
				newValueBlockStub.deselectAll();
				newValueRepFactor.setEnabled(false);
				newValueRepFactor.setText("");
				newValuePluginBindingPreCond.setEnabled(false);
				newValuePluginBindingPreCond.setText("");
			} else if (ManageChangeEditorPage.this.parent instanceof urncore.Condition && affectedProperty.startsWith("Pre-Condition-")) {
				newValueDynamicStub.setEnabled(false);
				newValueDynamicStub.deselectAll();
				newValueSyncStub.setEnabled(false);
				newValueSyncStub.deselectAll();
				newValueBlockStub.setEnabled(false);
				newValueBlockStub.deselectAll();
				newValueRepFactor.setEnabled(false);
				newValueRepFactor.setText("");
			}
		}

		if (changes.getText().equals("Text Change")) {
			if (ManageChangeEditorPage.this.parent instanceof StubEditPart || ManageChangeEditorPage.this.parent instanceof Stub || ManageChangeEditorPage.this.parent instanceof PluginBinding) {
				if (affectedProperty.contains("Replication factor")) {
					newValueRepFactor.setEnabled(true);
					newValueDynamicStub.setEnabled(false);
					newValueDynamicStub.deselectAll();
					newValueSyncStub.setEnabled(false);
					newValueSyncStub.deselectAll();
					newValueBlockStub.setEnabled(false);
					newValueBlockStub.deselectAll();
					newValuePluginBindingPreCond.setEnabled(false);
					newValuePluginBindingPreCond.setText("");
					addVariableToExpression.setEnabled(false);
					createNewVariable.setEnabled(false);
					newValueSelectVariable.setEnabled(false);
					newValueSelectVariable.deselectAll();
				} else if (affectedProperty.startsWith("Pre-Condition")) {
					newValuePluginBindingPreCond.setEnabled(true);
					newValueSelectVariable.setEnabled(true);
					createNewVariable.setEnabled(true);
					addVariableToExpression.setEnabled(true);
					newValueDynamicStub.setEnabled(false);
					newValueDynamicStub.deselectAll();
					newValueSyncStub.setEnabled(false);
					newValueSyncStub.deselectAll();
					newValueBlockStub.setEnabled(false);
					newValueBlockStub.deselectAll();
					newValueRepFactor.setEnabled(false);
					newValueRepFactor.setText("");
				}
			}
			else if (ManageChangeEditorPage.this.parent instanceof urncore.Condition && affectedProperty.startsWith("Pre-Condition")) {
				newValuePluginBindingPreCond.setEnabled(true);
				newValueSelectVariable.setEnabled(true);
				createNewVariable.setEnabled(true);
				addVariableToExpression.setEnabled(true);
				newValueDynamicStub.setEnabled(false);
				newValueDynamicStub.deselectAll();
				newValueSyncStub.setEnabled(false);
				newValueSyncStub.deselectAll();
				newValueBlockStub.setEnabled(false);
				newValueBlockStub.deselectAll();
				newValueRepFactor.setEnabled(false);
			} else if (ManageChangeEditorPage.this.parent instanceof urncore.Condition && affectedProperty.startsWith("Expression")) {
				if (newValueExpression != null) {
					newValueOrForkExp.setText("");
					newValueSelectVariable.deselectAll();
				}

				newValueOrForkExp.setEnabled(true);
				newValueSelectVariable.setEnabled(true);
				createNewVariable.setEnabled(true);
				addVariableToExpression.setEnabled(true);
			} else if (ManageChangeEditorPage.this.parent instanceof urncore.Condition && affectedProperty.equals("Post-Condition")) {
				newValueEndPtPostCond.setEnabled(true);
				newValueSelectVariable.setEnabled(true);
				createNewVariable.setEnabled(true);
				addVariableToExpression.setEnabled(true);
			} else if (ManageChangeEditorPage.this.parent instanceof urncore.Condition && affectedProperty.equals("Failure List/Start Point Pre-Condition")) {
				newValueFailureList.setEnabled(true);
				newValueSelectVariable.setEnabled(true);
				createNewVariable.setEnabled(true);
				addVariableToExpression.setEnabled(true);
				newValueFailureKind.setEnabled(false);
				newValueFailureKind.deselectAll();
			} else if (ManageChangeEditorPage.this.parent instanceof urncore.Condition && affectedProperty.equals("Failure Condition")) {
				newValueFailureCondition.setEnabled(true);
				newValueSelectVariable.setEnabled(true);
				newValueSelectVariable.deselectAll();
				createNewVariable.setEnabled(true);
				addVariableToExpression.setEnabled(true);
				newValueFailureExpression.setEnabled(false);
				newValueFailureExpression.setText("");
			}
			else if (ManageChangeEditorPage.this.parent instanceof StartPoint) {
				if (affectedProperty.equals("Failure List/Start Point Pre-Condition")) {
					newValueFailureList.setEnabled(true);
					newValueFailureKind.setEnabled(false);
					newValueFailureKind.deselectAll();
				}
			}
			else if (ManageChangeEditorPage.this.parent instanceof FailurePoint) {
				if (affectedProperty.equals("Failure Expression")) {
					newValueFailureCondition.setEnabled(false);
					newValueFailureCondition.setText("");
					newValueSelectVariable.setEnabled(true);
					newValueSelectVariable.deselectAll();
					createNewVariable.setEnabled(true);
					addVariableToExpression.setEnabled(true);
					newValueFailureExpression.setEnabled(true);
				}
			}
			else if (ManageChangeEditorPage.this.parent instanceof RespRefEditPart || ManageChangeEditorPage.this.parent instanceof Responsibility) {
				newValueRespExpression.setEnabled(true);
				newValueSelectVariable.setEnabled(true);
				newValueSelectVariable.deselectAll();
				createNewVariable.setEnabled(true);
				addVariableToExpression.setEnabled(true);
			}
			else if (ManageChangeEditorPage.this.parent instanceof PathNodeEditPart) {
				if (((PathNodeEditPart) (ManageChangeEditorPage.this.parent)).getModel() instanceof StartPoint) {
					if (affectedProperty.equals("Failure List/Start Point Pre-Condition")) {
						newValueFailureList.setEnabled(true);
						newValueFailureKind.setEnabled(false);
						newValueFailureKind.deselectAll();
					}
				}
				else if (((PathNodeEditPart) (ManageChangeEditorPage.this.parent)).getModel() instanceof FailurePoint) {
					if (affectedProperty.equals("Failure Condition")) {
						newValueFailureCondition.setEnabled(true);
						newValueSelectVariable.setEnabled(true);
						createNewVariable.setEnabled(true);
						addVariableToExpression.setEnabled(true);
						newValueFailureExpression.setEnabled(false);
					} else if (affectedProperty.equals("Failure Expression")) {
						newValueFailureCondition.setEnabled(false);
						newValueSelectVariable.setEnabled(true);
						createNewVariable.setEnabled(true);
						addVariableToExpression.setEnabled(true);
						newValueFailureExpression.setEnabled(true);
					}
				}
				else if (((PathNodeEditPart) (ManageChangeEditorPage.this.parent)).getModel() instanceof EndPoint)
					newValueEndPtPostCond.setEnabled(true);
				else if (((PathNodeEditPart) (ManageChangeEditorPage.this.parent)).getModel() instanceof EndPoint)
					newValueEndPtPostCond.setEnabled(true);
			}
		} else {
			if (ManageChangeEditorPage.this.parent instanceof urncore.Condition && affectedProperty.startsWith("Expression")) {
				newValueOrForkExp.setEnabled(false);
				newValueSelectVariable.setEnabled(false);
				createNewVariable.setEnabled(false);
				addVariableToExpression.setEnabled(false);
			}
			else if (ManageChangeEditorPage.this.parent instanceof urncore.Condition && affectedProperty.equals("Post-Condition")) {
				newValueEndPtPostCond.setEnabled(false);
				newValueSelectVariable.setEnabled(false);
				createNewVariable.setEnabled(false);
				addVariableToExpression.setEnabled(false);
			}
			else if (ManageChangeEditorPage.this.parent instanceof urncore.Condition && affectedProperty.equals("Failure List/Start Point Pre-Condition")) {
				newValueFailureList.setEnabled(false);
				newValueSelectVariable.setEnabled(false);
				createNewVariable.setEnabled(false);
				addVariableToExpression.setEnabled(false);
			}
			else if (ManageChangeEditorPage.this.parent instanceof StubEditPart || ManageChangeEditorPage.this.parent instanceof Stub || ManageChangeEditorPage.this.parent instanceof PluginBinding || ManageChangeEditorPage.this.parent instanceof urncore.Condition) {
				newValueRepFactor.setEnabled(false);
				newValueRepFactor.setText("");
				newValuePluginBindingPreCond.setEnabled(false);
				newValuePluginBindingPreCond.setText("");
				newValueSelectVariable.setEnabled(false);
				newValueSelectVariable.deselectAll();
				createNewVariable.setEnabled(false);
				addVariableToExpression.setEnabled(false);
			}
			else if (ManageChangeEditorPage.this.parent instanceof RespRefEditPart || ManageChangeEditorPage.this.parent instanceof Responsibility) {
				newValueRespExpression.setEnabled(false);
				newValueRespExpression.setText("");
				newValueSelectVariable.setEnabled(false);
				newValueSelectVariable.deselectAll();
				createNewVariable.setEnabled(false);
				addVariableToExpression.setEnabled(false);
			}
			else if (ManageChangeEditorPage.this.parent instanceof StartPoint) {
				if (affectedProperty.equals("Failure List/Start Point Pre-Condition"))
					newValueFailureList.setEnabled(false);
			}
			else if (ManageChangeEditorPage.this.parent instanceof FailurePoint) {
				newValueFailureExpression.setEnabled(false);
				newValueFailureExpression.setText("");
				newValueFailureCondition.setEnabled(false);
				newValueFailureCondition.setText("");
				newValueSelectVariable.setEnabled(false);
				newValueSelectVariable.deselectAll();
				createNewVariable.setEnabled(false);
				addVariableToExpression.setEnabled(false);
			}
			else if (ManageChangeEditorPage.this.parent instanceof EndPoint)
				newValueEndPtPostCond.setEnabled(false);
			else if (ManageChangeEditorPage.this.parent instanceof PathNodeEditPart) {
				if (((PathNodeEditPart) (ManageChangeEditorPage.this.parent)).getModel() instanceof StartPoint) {
					if (affectedProperty.equals("Failure List/Start Point Pre-Condition"))
						newValueFailureList.setEnabled(false);
				}
				else if (((PathNodeEditPart) (ManageChangeEditorPage.this.parent)).getModel() instanceof FailurePoint) {
					newValueFailureExpression.setEnabled(false);
					newValueFailureCondition.setEnabled(false);
					newValueSelectVariable.setEnabled(false);
					createNewVariable.setEnabled(false);
					addVariableToExpression.setEnabled(false);
				}
				else if (((PathNodeEditPart) (ManageChangeEditorPage.this.parent)).getModel() instanceof EndPoint)
					newValueEndPtPostCond.setEnabled(false);
				else if (((PathNodeEditPart) (ManageChangeEditorPage.this.parent)).getModel() instanceof OrFork) {
					newValueOrForkExp.setEnabled(false);
					newValueSelectVariable.setEnabled(false);
					createNewVariable.setEnabled(false);
					addVariableToExpression.setEnabled(false);
				}
			}
		}

		if (ManageChangeEditorPage.this.parent instanceof GRLmodelElement || ManageChangeEditorPage.this.parent instanceof ActorRefEditPart
				|| ManageChangeEditorPage.this.parent instanceof IntentionalElementEditPart || ManageChangeEditorPage.this.parent instanceof LinkRefEditPart) {

			if (changes.getText().equals("Constant Change") || changes.getText().equals("Linear Change"))
				newValueText.setEnabled(true);
			else
				newValueText.setEnabled(false);

			if (changes.getText().equals("Quadratic Change")) {
				newValueQuad.setEnabled(true);
				newValueLin.setEnabled(true);
				newValueCon.setEnabled(true);
			}
			else {
				newValueQuad.setEnabled(false);
				newValueLin.setEnabled(false);
				newValueCon.setEnabled(false);
			}

			if (changes.getText().equals("Formula Change"))
				newValueFor.setEnabled(true);
			else
				newValueFor.setEnabled(false);
		}

    }
    
    /*
     * Updates the fields in the dialog according to the selected change
     */
    private void updateItemInDialog(int i) {
    	Date start = new Date();
    	Date end = new Date();
    	int index = dynamicContexts.getSelectionIndex();
        oldDynContext = (DynamicContext) urn.getDynamicContexts().get(index);
    	String affProperty = availableChanges.getItem(i).getText(0);
    	affectedProperties.setText(affProperty);
    	affectedProperties.setEnabled(false);
    	changes.setItems(new String[] {availableChanges.getItem(i).getText(3)});
    	changes.setText(availableChanges.getItem(i).getText(3));
    	changes.setEnabled(false);
    	DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
    	String sDate = availableChanges.getItem(i).getText(1);
    	String eDate = availableChanges.getItem(i).getText(2);
    	try {
			start = df.parse(sDate);
			end = df.parse(eDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	startCalendar.setDate(start.getYear() + 1900, start.getMonth(), start.getDate());
    	startCalendar.setTime(0, 0, 0);
    	startCalendar.update();
    	endCalendar.setTime(0, 0, 0);
    	endCalendar.setDate(end.getYear() + 1900, end.getMonth(), end.getDate());
    	endCalendar.update();
    	addButton.setEnabled(false);
    	updButton.setEnabled(true);
    	delButton.setEnabled(true);
    	deselButton.setEnabled(true);
		if (changes.getText().equals("Enumeration Change")) {
			if(ManageChangeEditorPage.this.parent instanceof IntentionalElementEditPart || ManageChangeEditorPage.this.parent instanceof IntentionalElement) {
				newValueDecomp.setText(availableChanges.getItem(i).getText(4));
				newValueDecomp.setEnabled(true);
			} else if (ManageChangeEditorPage.this.parent instanceof StartPoint) {
				newValueFailureKind.setText(availableChanges.getItem(i).getText(4));
				newValueFailureKind.setEnabled(true);
				newValueFailureList.setText("");
				newValueFailureList.setEnabled(false);
				createNewVariable.setEnabled(false);
				newValueSelectVariable.setEnabled(false);
				newValueSelectVariable.deselectAll();
				addVariableToExpression.setEnabled(false);
			} else if (ManageChangeEditorPage.this.parent instanceof urncore.Condition && affProperty.equals("Failure Kind")) {
				newValueFailureKind.setText(availableChanges.getItem(i).getText(4));
				newValueFailureKind.setEnabled(true);
				newValueFailureList.setText("");
				newValueFailureList.setEnabled(false);
				createNewVariable.setEnabled(false);
				newValueSelectVariable.setEnabled(false);
				newValueSelectVariable.deselectAll();
				addVariableToExpression.setEnabled(false);
			} else if (ManageChangeEditorPage.this.parent instanceof PathNodeEditPart) {
				if ((((PathNodeEditPart) this.parent).getModel()) instanceof StartPoint) {
					if (affProperty.equals("Failure Kind")) {
						newValueFailureKind.setText(availableChanges.getItem(i).getText(4));
						newValueFailureKind.setEnabled(true);
						newValueFailureList.setEnabled(false);
						newValueFailureList.setText("");
						createNewVariable.setEnabled(false);
						newValueSelectVariable.setEnabled(false);
						newValueSelectVariable.deselectAll();
						addVariableToExpression.setEnabled(false);
					} else if (affProperty.equals("Failure List")) {
						newValueFailureList.setText(availableChanges.getItem(i).getText(4));
						newValueFailureList.setEnabled(true);
						newValueFailureKind.setEnabled(false);
						newValueFailureKind.setText("");
						createNewVariable.setEnabled(true);
						newValueSelectVariable.setEnabled(true);
						if (newValueSelectVariable.getItemCount() > 1) {
							String[] items = newValueSelectVariable.getItems();
							for (int i1 = 0; i1 < items.length; i1++) {
								String a1 = newValueSelectVariable.getItem(i1);
								if (newValueFailureList.getText().contains(a1))
									newValueSelectVariable.setText(a1);
							}
						} else
							newValueSelectVariable.setText(newValueSelectVariable.getItem(0));
						addVariableToExpression.setEnabled(true);
					}
				} else if ((((PathNodeEditPart) (ManageChangeEditorPage.this.parent)).getModel() instanceof Timer) || (((PathNodeEditPart) (ManageChangeEditorPage.this.parent)).getModel() instanceof WaitingPlace)) {
					newValueWaitKind.setText(availableChanges.getItem(i).getText(4));
					newValueWaitKind.setEnabled(true);        				
				}
			} else if (ManageChangeEditorPage.this.parent instanceof Timer || ManageChangeEditorPage.this.parent instanceof WaitingPlace) {
				newValueWaitKind.setText(availableChanges.getItem(i).getText(4));
				newValueWaitKind.setEnabled(true);
			} else if (ManageChangeEditorPage.this.parent instanceof ComponentRefEditPart || ManageChangeEditorPage.this.parent instanceof Component) {
				newValueCompKind.setText(availableChanges.getItem(i).getText(4));
				newValueCompKind.setEnabled(true);
				newValueCompContext.setEnabled(false);
				newValueCompContext.deselectAll();
				newValueCompProtection.setEnabled(false);
				newValueCompProtection.deselectAll();
			}    		
		}
		else {
			if(this.parent instanceof GRLmodelElement || this.parent instanceof ActorRefEditPart
					|| this.parent instanceof IntentionalElementEditPart || this.parent instanceof LinkRefEditPart)
				newValueDecomp.setEnabled(false);
			else if (ManageChangeEditorPage.this.parent instanceof StartPoint) {
				if (affProperty.equals("Failure List/Start Point Pre-Condition")) {
					newValueFailureKind.setEnabled(false);
					newValueFailureKind.deselectAll();
					newValueSelectVariable.setEnabled(true);
					addVariableToExpression.setEnabled(true);
					createNewVariable.setEnabled(true);
				}
				else {
					newValueFailureList.setEnabled(false);
					newValueFailureList.setText("");
				}
			}
			else if (ManageChangeEditorPage.this.parent instanceof Timer || ManageChangeEditorPage.this.parent instanceof WaitingPlace)
				newValueWaitKind.setEnabled(false);
			else if (ManageChangeEditorPage.this.parent instanceof PathNodeEditPart) {
				if ((((PathNodeEditPart) this.parent).getModel()) instanceof StartPoint) {
					if (affProperty.equals("Failure List/Start Point Pre-Condition")) {
						newValueFailureKind.setEnabled(false);
						newValueFailureKind.setText("");
						newValueFailureKind.deselectAll();
					}
				}
				else if ((((PathNodeEditPart) (ManageChangeEditorPage.this.parent)).getModel() instanceof Timer) || (((PathNodeEditPart) (ManageChangeEditorPage.this.parent)).getModel() instanceof WaitingPlace))
					newValueWaitKind.setEnabled(false);
			} else if (this.parent instanceof ComponentRefEditPart || this.parent instanceof Component) {
				newValueCompKind.setEnabled(false);
				newValueCompKind.deselectAll();
				newValueCompContext.setEnabled(false);
				newValueCompContext.deselectAll();
				newValueCompProtection.setEnabled(false);
				newValueCompProtection.deselectAll();
			}
		}

		if (changes.getText().equals("Boolean Change")) {
			if (this.parent instanceof StubEditPart || this.parent instanceof Stub || this.parent instanceof PluginBinding) {
				if (affProperty.equals("Dynamic Stub")) {
					newValueDynamicStub.setText(availableChanges.getItem(i).getText(4));
					newValueDynamicStub.setEnabled(true);
					newValueSyncStub.setEnabled(false);
					newValueSyncStub.deselectAll();
					newValueBlockStub.setEnabled(false);
					newValueBlockStub.deselectAll();
					newValueRepFactor.setText("");
					newValueRepFactor.setEnabled(false);
					newValuePluginBindingPreCond.setEnabled(false);
					newValuePluginBindingPreCond.setText("");
					addVariableToExpression.setEnabled(false);
					newValueSelectVariable.deselectAll();
					newValueSelectVariable.setEnabled(false);
					createNewVariable.setEnabled(false);
				} else if (affProperty.equals("Synchronizing Stub")) {
					newValueSyncStub.setText(availableChanges.getItem(i).getText(4));
					newValueSyncStub.setEnabled(true);
					newValueDynamicStub.setEnabled(false);
					newValueDynamicStub.deselectAll();
					newValueBlockStub.setEnabled(false);
					newValueBlockStub.deselectAll();
					newValueRepFactor.setText("");
					newValueRepFactor.setEnabled(false);
					newValuePluginBindingPreCond.setEnabled(false);
					newValuePluginBindingPreCond.setText("");
					addVariableToExpression.setEnabled(false);
					newValueSelectVariable.deselectAll();
					newValueSelectVariable.setEnabled(false);
					createNewVariable.setEnabled(false);
				}  else if (affProperty.equals("Blocking Stub")) {
					newValueBlockStub.setText(availableChanges.getItem(i).getText(4));
					newValueBlockStub.setEnabled(true);
					newValueDynamicStub.setEnabled(false);
					newValueDynamicStub.deselectAll();
					newValueSyncStub.setEnabled(false);
					newValueSyncStub.deselectAll();
					newValueRepFactor.setText("");
					newValueRepFactor.setEnabled(false);
					newValuePluginBindingPreCond.setEnabled(false);
					newValuePluginBindingPreCond.setText("");
					addVariableToExpression.setEnabled(false);
					newValueSelectVariable.deselectAll();
					newValueSelectVariable.setEnabled(false);
					createNewVariable.setEnabled(false);
				}
			} else if (this.parent instanceof urncore.Condition) {
				if (affProperty.equals("Dynamic Stub")) {
					newValueDynamicStub.setText(availableChanges.getItem(i).getText(4));
					newValueDynamicStub.setEnabled(true);
					newValueSyncStub.setEnabled(false);
					newValueSyncStub.deselectAll();
					newValueBlockStub.setEnabled(false);
					newValueBlockStub.deselectAll();
					newValueRepFactor.setText("");
					newValueRepFactor.setEnabled(false);
					newValuePluginBindingPreCond.setEnabled(false);
					newValuePluginBindingPreCond.setText("");
					addVariableToExpression.setEnabled(false);
					newValueSelectVariable.deselectAll();
					newValueSelectVariable.setEnabled(false);
					createNewVariable.setEnabled(false);
				} else if (affProperty.equals("Synchronizing Stub")) {
					newValueSyncStub.setText(availableChanges.getItem(i).getText(4));
					newValueSyncStub.setEnabled(true);
					newValueDynamicStub.setEnabled(false);
					newValueDynamicStub.deselectAll();
					newValueBlockStub.setEnabled(false);
					newValueBlockStub.deselectAll();
					newValueRepFactor.setText("");
					newValueRepFactor.setEnabled(false);
					newValuePluginBindingPreCond.setEnabled(false);
					newValuePluginBindingPreCond.setText("");
					addVariableToExpression.setEnabled(false);
					newValueSelectVariable.deselectAll();
					newValueSelectVariable.setEnabled(false);
					createNewVariable.setEnabled(false);
				}  else if (affProperty.equals("Blocking Stub")) {
					newValueBlockStub.setText(availableChanges.getItem(i).getText(4));
					newValueBlockStub.setEnabled(true);
					newValueDynamicStub.setEnabled(false);
					newValueDynamicStub.deselectAll();
					newValueSyncStub.setEnabled(false);
					newValueSyncStub.deselectAll();
					newValueRepFactor.setText("");
					newValueRepFactor.setEnabled(false);
					newValuePluginBindingPreCond.setEnabled(false);
					newValuePluginBindingPreCond.setText("");
					addVariableToExpression.setEnabled(false);
					newValueSelectVariable.deselectAll();
					newValueSelectVariable.setEnabled(false);
					createNewVariable.setEnabled(false);
				}
			} else if (this.parent instanceof ComponentRefEditPart || this.parent instanceof Component) {
				if (affProperty.equals("Component Protection")) {
					newValueCompProtection.setText(availableChanges.getItem(i).getText(4));
					newValueCompProtection.setEnabled(true);
					newValueCompContext.setEnabled(false);
					newValueCompContext.deselectAll();
				} else if (affProperty.equals("Component Context")) {
					newValueCompContext.setText(availableChanges.getItem(i).getText(4));
					newValueCompContext.setEnabled(true);
					newValueCompProtection.setEnabled(false);
					newValueCompProtection.deselectAll();
				}
			}
		} else {
			if (this.parent instanceof StubEditPart || this.parent instanceof Stub || this.parent instanceof PluginBinding) {
				if (affProperty.startsWith("Select")) {
					newValueDynamicStub.setEnabled(false);
					newValueDynamicStub.deselectAll();
					newValueSyncStub.setEnabled(false);
					newValueSyncStub.deselectAll();
					newValueBlockStub.setEnabled(false);
					newValueBlockStub.deselectAll();
					newValueRepFactor.setEnabled(false);
					newValueRepFactor.setText("");
					newValuePluginBindingPreCond.setText("");
					newValuePluginBindingPreCond.setEnabled(false);
					addVariableToExpression.setEnabled(false);
					newValueSelectVariable.setEnabled(false);
					newValueSelectVariable.deselectAll();
					createNewVariable.setEnabled(false);
				}
			}  else if (this.parent instanceof urncore.Condition && affProperty.startsWith("Select UCM")) {
				newValueRepFactor.setEnabled(false);
				newValueRepFactor.setText("");
				newValuePluginBindingPreCond.setText("");
				newValuePluginBindingPreCond.setEnabled(false);
				addVariableToExpression.setEnabled(false);
				newValueSelectVariable.setEnabled(false);
				newValueSelectVariable.deselectAll();
				createNewVariable.setEnabled(false);
				newValueDynamicStub.setEnabled(false);
				newValueDynamicStub.deselectAll();
				newValueSyncStub.setEnabled(false);
				newValueSyncStub.deselectAll();
				newValueBlockStub.setEnabled(false);
				newValueBlockStub.deselectAll();
			} else if (this.parent instanceof ComponentRefEditPart || this.parent instanceof Component) {
				newValueCompProtection.setEnabled(false);
				newValueCompContext.setEnabled(false);
			}
		}

		if (changes.getText().equals("Text Change")) {
			if (this.parent instanceof RespRefEditPart || this.parent instanceof Responsibility) {
				newValueRespExpression.setEnabled(true);
				newValueRespExpression.setText(availableChanges.getItem(i).getText(4));
				createNewVariable.setEnabled(true);
				addVariableToExpression.setEnabled(true);
				newValueSelectVariable.setEnabled(true);
				if (newValueSelectVariable.getItemCount() > 1) {
					String[] items = newValueSelectVariable.getItems();
					for (int i1 = 0; i1 < items.length; i1++) {
						String a1 = newValueSelectVariable.getItem(i1);
						if (newValueRespExpression.getText().contains(a1))
							newValueSelectVariable.setText(a1);
					}
				} else
					newValueSelectVariable.setText(newValueSelectVariable.getItem(0));
			} else if (this.parent instanceof PluginBinding || this.parent instanceof StubEditPart || this.parent instanceof Stub) {
				if (affProperty.contains("Replication factor")) {
					newValueRepFactor.setText(availableChanges.getItem(i).getText(4));
					newValueRepFactor.setEnabled(true);
					newValueDynamicStub.setEnabled(false);
					newValueDynamicStub.deselectAll();
					newValueBlockStub.setEnabled(false);
					newValueBlockStub.deselectAll();
					newValueSyncStub.setEnabled(false);
					newValueSyncStub.deselectAll();
					newValuePluginBindingPreCond.setEnabled(false);
					newValuePluginBindingPreCond.setText("");
					addVariableToExpression.setEnabled(false);
					newValueSelectVariable.deselectAll();
					newValueSelectVariable.setEnabled(false);
					createNewVariable.setEnabled(false);
				} else if (affProperty.startsWith("Pre-Condition")) {
					newValuePluginBindingPreCond.setText(availableChanges.getItem(i).getText(4));
					newValuePluginBindingPreCond.setEnabled(true);
					newValueDynamicStub.setEnabled(false);
					newValueDynamicStub.deselectAll();
					newValueBlockStub.setEnabled(false);
					newValueBlockStub.deselectAll();
					newValueSyncStub.setEnabled(false);
					newValueSyncStub.deselectAll();
					newValueRepFactor.setEnabled(false);
					newValueRepFactor.setText("");
					addVariableToExpression.setEnabled(true);
					createNewVariable.setEnabled(true);
					newValueSelectVariable.setEnabled(true);
					if (newValueSelectVariable.getItemCount() > 1) {
						String[] items = newValueSelectVariable.getItems();
						for (int i1 = 0; i1 < items.length; i1++) {
							String a1 = newValueSelectVariable.getItem(i1);
							if (newValuePluginBindingPreCond.getText().contains(a1))
								newValueSelectVariable.setText(a1);
						}
					} else
						newValueSelectVariable.setText(newValueSelectVariable.getItem(0));	
				}
			} else if (this.parent instanceof urncore.Condition && affProperty.startsWith("Pre-Condition-")) {
				newValuePluginBindingPreCond.setText(availableChanges.getItem(i).getText(4));
				newValuePluginBindingPreCond.setEnabled(true);
				newValueRepFactor.setEnabled(false);
				newValueRepFactor.setText("");
				newValueDynamicStub.setEnabled(false);
				newValueDynamicStub.deselectAll();
				newValueSyncStub.setEnabled(false);
				newValueSyncStub.deselectAll();
				newValueBlockStub.setEnabled(false);
				newValueBlockStub.deselectAll();
				addVariableToExpression.setEnabled(true);
				createNewVariable.setEnabled(true);
				newValueSelectVariable.setEnabled(true);
				if (newValueSelectVariable.getItemCount() > 1) {
					String[] items = newValueSelectVariable.getItems();
					for (int i1 = 0; i1 < items.length; i1++) {
						String a1 = newValueSelectVariable.getItem(i1);
						if (newValuePluginBindingPreCond.getText().contains(a1))
							newValueSelectVariable.setText(a1);
					}
				} else
					newValueSelectVariable.setText(newValueSelectVariable.getItem(0));
			} else if (this.parent instanceof urncore.Condition && affProperty.startsWith("Replication factor-")) {
				newValueRepFactor.setText(availableChanges.getItem(i).getText(4));
				newValueRepFactor.setEnabled(true);
				newValuePluginBindingPreCond.setText("");
				newValuePluginBindingPreCond.setEnabled(false);
				newValueDynamicStub.setEnabled(false);
				newValueDynamicStub.deselectAll();
				newValueSyncStub.setEnabled(false);
				newValueSyncStub.deselectAll();
				newValueBlockStub.setEnabled(false);
				newValueBlockStub.deselectAll();
				addVariableToExpression.setEnabled(false);
				createNewVariable.setEnabled(false);
				newValueSelectVariable.setEnabled(false);
				newValueSelectVariable.deselectAll();
			} else if (this.parent instanceof urncore.Condition && affProperty.equals("Failure List/Start Point Pre-Condition")) {
				newValueFailureList.setText(availableChanges.getItem(i).getText(4));
				newValueFailureList.setEnabled(true);
				addVariableToExpression.setEnabled(true);
				createNewVariable.setEnabled(true);
				newValueSelectVariable.setEnabled(true);
				if (newValueSelectVariable.getItemCount() > 1) {
					String[] items = newValueSelectVariable.getItems();
					for (int i1 = 0; i1 < items.length; i1++) {
						String a1 = newValueSelectVariable.getItem(i1);
						if (newValueFailureList.getText().contains(a1))
							newValueSelectVariable.setText(a1);
					}
				}
				else
					newValueSelectVariable.setText(newValueSelectVariable.getItem(0));
				newValueFailureKind.setEnabled(false);
				newValueFailureKind.deselectAll();
			} else if (this.parent instanceof urncore.Condition && affProperty.startsWith("Expression")) {
				newValueOrForkExp.setText(availableChanges.getItem(i).getText(4));
				newValueOrForkExp.setEnabled(true);
				addVariableToExpression.setEnabled(true);
				createNewVariable.setEnabled(true);
				newValueSelectVariable.setEnabled(true);
				if (newValueSelectVariable.getItemCount() > 1) {
					String[] items = newValueSelectVariable.getItems();
					for (int i1 = 0; i1 < items.length; i1++) {
						String a1 = newValueSelectVariable.getItem(i1);
						if (newValueOrForkExp.getText().contains(a1))
							newValueSelectVariable.setText(a1);
					}
				} else
					newValueSelectVariable.setText(newValueSelectVariable.getItem(0));
			} else if (this.parent instanceof urncore.Condition && affProperty.equals("Post-Condition")) {
				newValueEndPtPostCond.setText(availableChanges.getItem(i).getText(4));
				newValueEndPtPostCond.setEnabled(true);
				addVariableToExpression.setEnabled(true);
				createNewVariable.setEnabled(true);
				newValueSelectVariable.setEnabled(true);
				if (newValueSelectVariable.getItemCount() > 1) {
					String[] items = newValueSelectVariable.getItems();
					for (int i1 = 0; i1 < items.length; i1++) {
						String a1 = newValueSelectVariable.getItem(i1);
						if (newValueEndPtPostCond.getText().contains(a1))
							newValueSelectVariable.setText(a1);
					}
				} else
					newValueSelectVariable.setText(newValueSelectVariable.getItem(0));
			} else if (this.parent instanceof urncore.Condition && affProperty.equals("Failure Condition")) {
				newValueFailureCondition.setText(availableChanges.getItem(i).getText(4));
				newValueFailureCondition.setEnabled(true);
				createNewVariable.setEnabled(true);
				addVariableToExpression.setEnabled(true);
				newValueSelectVariable.setEnabled(true);
				if (newValueSelectVariable.getItemCount() > 1) {
					String[] items = newValueSelectVariable.getItems();
					for (int i1 = 0; i1 < items.length; i1++) {
						String a1 = newValueSelectVariable.getItem(i1);
						if (newValueFailureCondition.getText().contains(a1))
							newValueSelectVariable.setText(a1);
					}
				} else
					newValueSelectVariable.setText(newValueSelectVariable.getItem(0));
				newValueFailureExpression.setEnabled(false);
				newValueFailureExpression.setText("");
			} else if (this.parent instanceof urncore.Condition && affProperty.equals("Failure Expression")) {
				newValueFailureExpression.setText(availableChanges.getItem(i).getText(4));
				newValueFailureExpression.setEnabled(true);
				createNewVariable.setEnabled(true);
				addVariableToExpression.setEnabled(true);
				newValueSelectVariable.setEnabled(true);
				if (newValueSelectVariable.getItemCount() > 1) {
					String[] items = newValueSelectVariable.getItems();
					for (int i1 = 0; i1 < items.length; i1++) {
						String a1 = newValueSelectVariable.getItem(i1);
						if (newValueFailureExpression.getText().contains(a1))
							newValueSelectVariable.setText(a1);
					}
				} else
					newValueSelectVariable.setText(newValueSelectVariable.getItem(0));
				newValueFailureCondition.setEnabled(false);
				newValueFailureCondition.setText("");
			} else if (this.parent instanceof FailurePoint) {
				createNewVariable.setEnabled(true);
				addVariableToExpression.setEnabled(true);
				newValueSelectVariable.setEnabled(true);
				if (affProperty.equals("Failure Condition")) {
					newValueFailureCondition.setText(availableChanges.getItem(i).getText(4));
					newValueFailureCondition.setEnabled(true);
					if (newValueSelectVariable.getItemCount() > 1) {
						String[] items = newValueSelectVariable.getItems();
						for (int i1 = 0; i1 < items.length; i1++) {
							String a1 = newValueSelectVariable.getItem(i1);
							if (newValueFailureCondition.getText().contains(a1))
								newValueSelectVariable.setText(a1);
						}
					} else
						newValueSelectVariable.setText(newValueSelectVariable.getItem(0));
					newValueFailureExpression.setEnabled(false);
					newValueFailureExpression.setText("");
				} else if (affProperty.equals("Failure Expression")) {
					newValueFailureExpression.setText(availableChanges.getItem(i).getText(4));
					newValueFailureExpression.setEnabled(true);
					if (newValueSelectVariable.getItemCount() > 1) {
						String[] items = newValueSelectVariable.getItems();
						for (int i1 = 0; i1 < items.length; i1++) {
							String a1 = newValueSelectVariable.getItem(i1);
							if (newValueFailureExpression.getText().contains(a1))
								newValueSelectVariable.setText(a1);
						}
					} else
						newValueSelectVariable.setText(newValueSelectVariable.getItem(0));
					newValueFailureCondition.setEnabled(false);
					newValueFailureCondition.setText("");
				}
			} else if (this.parent instanceof StartPoint && affProperty.equals("Failure List/Start Point Pre-Condition")) {
				newValueFailureList.setText(availableChanges.getItem(i).getText(4));
				newValueFailureList.setEnabled(true);
				createNewVariable.setEnabled(true);
				newValueSelectVariable.setEnabled(true);
				if (newValueSelectVariable.getItemCount() > 1) {
					String[] items = newValueSelectVariable.getItems();
					for (int i1 = 0; i1 < items.length; i1++) {
						String a1 = newValueSelectVariable.getItem(i1);
						if (newValueFailureList.getText().contains(a1))
							newValueSelectVariable.setText(a1);
					}
				} else
					newValueSelectVariable.setText(newValueSelectVariable.getItem(0));
				addVariableToExpression.setEnabled(true);
				newValueFailureKind.setEnabled(false);
				newValueFailureKind.setText("");
				newValueFailureKind.deselectAll();
			} else if (this.parent instanceof PathNodeEditPart) {
				if ((((PathNodeEditPart) this.parent).getModel()) instanceof OrFork) {
					newValueOrForkExp.setText(availableChanges.getItem(i).getText(4));
					newValueOrForkExp.setEnabled(true);
					addVariableToExpression.setEnabled(true);
					createNewVariable.setEnabled(true);
					newValueSelectVariable.setEnabled(true);
					if (newValueSelectVariable.getItemCount() > 1) {
						String[] items = newValueSelectVariable.getItems();
						for (int i1 = 0; i1 < items.length; i1++) {
							String a1 = newValueSelectVariable.getItem(i1);
							if (newValueOrForkExp.getText().contains(a1))
								newValueSelectVariable.setText(a1);
						}
					} else
						newValueSelectVariable.setText(newValueSelectVariable.getItem(0));
				}
				else if ((((PathNodeEditPart) this.parent).getModel()) instanceof FailurePoint) {
					if (affProperty.equals("Failure Expression")) {
						newValueFailureExpression.setText(availableChanges.getItem(i).getText(4));
						newValueFailureExpression.setEnabled(true);
						newValueFailureCondition.setEnabled(false);
						newValueFailureCondition.setText("");
						createNewVariable.setEnabled(true);
						addVariableToExpression.setEnabled(true);
						newValueSelectVariable.setEnabled(true);
						if (newValueSelectVariable.getItemCount() > 1) {
							String[] items = newValueSelectVariable.getItems();
							for (int i1 = 0; i1 < items.length; i1++) {
								String a1 = newValueSelectVariable.getItem(i1);
								if (newValueFailureExpression.getText().contains(a1))
									newValueSelectVariable.setText(a1);
							}
						} else
							newValueSelectVariable.setText(newValueSelectVariable.getItem(0));
					} else if (affProperty.equals("Failure Condition")) {
						newValueFailureCondition.setText(availableChanges.getItem(i).getText(4));
						newValueFailureCondition.setEnabled(true);
						newValueFailureExpression.setEnabled(false);
						newValueFailureExpression.setText("");
						createNewVariable.setEnabled(true);
						addVariableToExpression.setEnabled(true);
						newValueSelectVariable.setEnabled(true);
						if (newValueSelectVariable.getItemCount() > 1) {
							String[] items = newValueSelectVariable.getItems();
							for (int i1 = 0; i1 < items.length; i1++) {
								String a1 = newValueSelectVariable.getItem(i1);
								if (newValueFailureCondition.getText().contains(a1))
									newValueSelectVariable.setText(a1);
							}
						} else
							newValueSelectVariable.setText(newValueSelectVariable.getItem(0));
					}

				}
				else if ((((PathNodeEditPart) this.parent).getModel()) instanceof EndPoint) {
					newValueEndPtPostCond.setText(availableChanges.getItem(i).getText(4));
					newValueEndPtPostCond.setEnabled(true);
					addVariableToExpression.setEnabled(true);
					createNewVariable.setEnabled(true);
					newValueSelectVariable.setEnabled(true);
					if (newValueSelectVariable.getItemCount() > 1) {
						String[] items = newValueSelectVariable.getItems();
						for (int i1 = 0; i1 < items.length; i1++) {
							String a1 = newValueSelectVariable.getItem(i1);
							if (newValueEndPtPostCond.getText().contains(a1))
								newValueSelectVariable.setText(a1);
						}
					} else
						newValueSelectVariable.setText(newValueSelectVariable.getItem(0));
				}
				else if ((((PathNodeEditPart) this.parent).getModel()) instanceof StartPoint) {
					if (affProperty.equals("Failure List/Start Point Pre-Condition")) {
						newValueFailureList.setText(availableChanges.getItem(i).getText(4));
						newValueFailureList.setEnabled(true);
						createNewVariable.setEnabled(true);
						addVariableToExpression.setEnabled(true);
						newValueSelectVariable.setEnabled(true);
						if (newValueSelectVariable.getItemCount() > 1) {
							String[] items = newValueSelectVariable.getItems();
							for (int i1 = 0; i1 < items.length; i1++) {
								String a1 = newValueSelectVariable.getItem(i1);
								if (newValueFailureList.getText().contains(a1))
									newValueSelectVariable.setText(a1);
							}
						} else
							newValueSelectVariable.setText(newValueSelectVariable.getItem(0));
					}
				}
			}
		} else {
			if (this.parent instanceof RespRefEditPart || this.parent instanceof Responsibility) {
				newValueRespExpression.setEnabled(false);
				newValueRespExpression.setText("");
				createNewVariable.setEnabled(false);
				addVariableToExpression.setEnabled(false);
				newValueSelectVariable.setEnabled(false);
				newValueSelectVariable.deselectAll();
			}
			else if (this.parent instanceof PluginBinding || this.parent instanceof StubEditPart) {
				if (affProperty.contains("Replication factor")) {
					newValueRepFactor.setEnabled(false);
					newValueRepFactor.setText("");
				}
				else if (affProperty.startsWith("Pre-Condition")) {
					newValuePluginBindingPreCond.setEnabled(false);
					newValuePluginBindingPreCond.setText("");
				}
			}
			else if (this.parent instanceof urncore.Condition && affProperty.startsWith("Pre-Condition")) {
				newValuePluginBindingPreCond.setEnabled(false);
				newValuePluginBindingPreCond.setText("");
			}
			else if (this.parent instanceof urncore.Condition && affProperty.equals("Failure Condition")) {
				newValueFailureCondition.setEnabled(false);
				newValueFailureCondition.setText("");
				createNewVariable.setEnabled(false);
				addVariableToExpression.setEnabled(false);
				newValueSelectVariable.setEnabled(false);
				newValueSelectVariable.deselectAll();
			}
			else if (this.parent instanceof urncore.Condition && affProperty.equals("Selected Element") && this.getTitle().contains("FailurePoint")) {
				newValueFailureCondition.setEnabled(false);
				newValueFailureCondition.setText("");
				createNewVariable.setEnabled(false);
				addVariableToExpression.setEnabled(false);
				newValueSelectVariable.setEnabled(false);
				newValueSelectVariable.deselectAll();
				newValueFailureExpression.setEnabled(false);
				newValueFailureExpression.setText("");
			}
			else if (this.parent instanceof PathNodeEditPart) {
				if ((((PathNodeEditPart) this.parent).getModel()) instanceof OrFork) {
					newValueOrForkExp.setEnabled(false);
					newValueOrForkExp.setText("");
				}
				else if ((((PathNodeEditPart) this.parent).getModel()) instanceof FailurePoint) {
					if (affProperty.equals("Failure Expression")) {
						newValueFailureCondition.setText("");
						newValueFailureCondition.setEnabled(false);
					}
					else {
						newValueFailureExpression.setEnabled(false);
						newValueFailureExpression.setText("");
						newValueFailureCondition.setEnabled(false);
						newValueFailureCondition.setText("");
						addVariableToExpression.setEnabled(false);
						newValueSelectVariable.setEnabled(false);
						newValueSelectVariable.deselectAll();
						createNewVariable.setEnabled(false);
					}
				}
				else if ((((PathNodeEditPart) this.parent).getModel()) instanceof EndPoint)
					newValueEndPtPostCond.setEnabled(false);
				else if ((((PathNodeEditPart) this.parent).getModel()) instanceof StartPoint) {
					if (affProperty.equals("Failure List/Start Point Pre-Condition")) {
						newValueFailureList.setEnabled(false);
						newValueFailureList.setText("");
					}
				}
			}
			else if (this.parent instanceof FailurePoint && affProperty.equals("Selected Element")) {
				newValueFailureExpression.setEnabled(false);
				newValueFailureCondition.setEnabled(false);
				newValueFailureExpression.setText("");
				newValueFailureCondition.setText("");
				createNewVariable.setEnabled(false);
				newValueSelectVariable.setEnabled(false);
				newValueSelectVariable.deselectAll();
				addVariableToExpression.setEnabled(false);
			}
			else if (this.parent instanceof StartPoint && affProperty.equals("Failure List/Start Point Pre-Condition")) {
				newValueFailureList.setEnabled(false);
				newValueFailureList.setText("");
			}
		}

		if (this.parent instanceof GRLmodelElement || this.parent instanceof ActorRefEditPart
				|| this.parent instanceof IntentionalElementEditPart || this.parent instanceof LinkRefEditPart) {

    	if (changes.getText().equals("Constant Change") || changes.getText().equals("Linear Change")) {
    		newValueText.setText(availableChanges.getItem(i).getText(4));
        	newValueText.setEnabled(true);
    	}
			else {
    		newValueText.setEnabled(false);
				newValueText.setText("");
    	}
    	
    	if (changes.getText().equals("Quadratic Change")) {
    		String formula = availableChanges.getItem(i).getText(5);
    		newValueQuad.setText(formula.substring(0, formula.indexOf("t")));
        	newValueQuad.setEnabled(true);
        	newValueLin.setText(formula.substring(formula.indexOf("+") + 1, formula.indexOf("t+")));
        	newValueLin.setEnabled(true);
        	newValueCon.setText(formula.substring(formula.indexOf("t+") + 2));
        	newValueCon.setEnabled(true);
    	}
    	else {
    		newValueQuad.setEnabled(false);
    		newValueLin.setEnabled(false);
    		newValueCon.setEnabled(false);
				newValueQuad.setText("");
				newValueLin.setText("");
				newValueCon.setText("");
    	}
    	
    	if (changes.getText().equals("Formula Change")) {
    		newValueFor.setText(availableChanges.getItem(i).getText(5));
        	newValueFor.setEnabled(true);
    	}
			else {
    		newValueFor.setEnabled(false);
				newValueFor.setText("");
    }
		}
	}	
    
    /*
     * Checks if the change overlaps with any other of the available changes
     * 
     * @param change
     * 				change to be checked
     */
    private boolean checkForOverlap(Change change) {
    	java.util.List availableChanges = DynamicContextsUtils.getAllAvailableChanges(parent, currentDynContext, urn);
    	for (Iterator iter = availableChanges.iterator(); iter.hasNext();) {
    		Change change1 = (Change) iter.next();
			if (change1 != change && (((affectedProperty.startsWith("Selected") || affectedProperty.startsWith("Select")) && change1 instanceof DeactivationChange) 
    				|| (!(change1 instanceof DeactivationChange) && ((PropertyChange)change1).getAffectedProperty().equals(affectedProperty)))) {
    			if ((change1.getStart().before(startDate) && change1.getEnd().after(startDate)) || change1.getStart().equals(startDate) || 
    					(change1.getStart().after(startDate) && change1.getStart().before(endDate)))
					return false;
    		}
    	}
    	return true;
    }
    
    /*
     * Returns a list of all available Dynamic Contexts
     */
    private String[] getDynamicContexts() {
    	String[] dynamicContexts = null;
    	if (urn.getDynamicContexts().size() != 0) {
	    	dynamicContexts = new String[urn.getDynamicContexts().size()];
	    	for (int i = 0; i < urn.getDynamicContexts().size(); i++) {
	    		DynamicContext context= (DynamicContext) urn.getDynamicContexts().get(i);
	    		dynamicContexts[i] = URNNamingHelper.getName((DynamicContextGroup) context.getGroups().get(0)) + ":" + URNNamingHelper.getName(context);
	    	}
    	}
    	return dynamicContexts;
    }
    
    /*
     * Returns a list of changes possible according to the selected element and "affected property"
     */
    private String[] getPossibleChanges() {
    	String[] possibleChanges = null;
    	if (affectedProperty != null) {
			if (affectedProperty.contains("Selected") || affectedProperty.contains("Select")){
	    		possibleChanges = new String[1];
	    		possibleChanges[0] = "Deactivation Change";
			} else if ((affectedProperty.equals("Decomposition Type")) || (affectedProperty.equals("Failure Kind"))
					|| (affectedProperty.equals("Wait Kind")) || (affectedProperty.equals("Component Kind"))) {
	    		possibleChanges = new String[1];
	    		possibleChanges[0] = "Enumeration Change";
			} else if ((affectedProperty.equals("Dynamic Stub")) || (affectedProperty.equals("Synchronizing Stub"))
					|| (affectedProperty.equals("Blocking Stub")) || (affectedProperty.equals("Component Protection"))
					|| (affectedProperty.equals("Component Context"))) {
				possibleChanges = new String[1];
				possibleChanges[0] = "Boolean Change";
			} else if ((affectedProperty.equals("Responsibility Expression")) || (affectedProperty.contains("Replication factor"))
					|| (affectedProperty.startsWith("Pre-Condition-")) || (affectedProperty.equals("Failure Condition"))
					|| (affectedProperty.equals("Failure Expression")) || (affectedProperty.startsWith("Expression"))
					|| (affectedProperty.equals("Failure List/Start Point Pre-Condition")) || (affectedProperty.equals("Post-Condition"))) {
				possibleChanges = new String[1];
				possibleChanges[0] = "Text Change";
	    	} else {
	    		possibleChanges = new String[4];
	    		possibleChanges[0] = "Constant Change";
	    		possibleChanges[1] = "Linear Change";
	    		possibleChanges[2] = "Quadratic Change";
	    		possibleChanges[3] = "Formula Change";
	    	}
    	} else {
    		possibleChanges = new String[1];
    		possibleChanges[0] = "";
    	}
        return possibleChanges;
    }
    
    /*
     * Returns a list of possible affected properties according to the selected element
     */
    private String[] getPossibleAffectedProperties() {
    	String[] possibleAffProps = null;
    	if (this.parent instanceof LinkRefEditPart || this.parent instanceof ElementLink){
    		ElementLink lr;
    		if (this.parent instanceof LinkRefEditPart)
    			lr = ((LinkRef) (((LinkRefEditPart) this.parent).getModel())).getLink();
    		else
    			lr = (ElementLink) parent;
            if (lr instanceof Contribution) {
            	possibleAffProps = new String[2];
    			possibleAffProps[0] = "Selected Contribution Link";
    			possibleAffProps[1] = "Quantitative Contribution";
            } else if (lr instanceof Dependency) {
            	possibleAffProps = new String[1];
				possibleAffProps[0] = "Selected Dependency Link";
            } else if (lr instanceof Decomposition) {
            	possibleAffProps = new String[1];
    			possibleAffProps[0] = "Selected Decomposition Link";
            }
    		
    	} else if (this.parent instanceof ActorRefEditPart || this.parent instanceof Actor){
    		possibleAffProps = new String[3];
    		possibleAffProps[0] = "Selected Actor";
    		possibleAffProps[1] = "Count";
    		possibleAffProps[2] = "Quantitative Importance";
    	} else if(this.parent instanceof RespRefEditPart || this.parent instanceof Responsibility) {
			possibleAffProps = new String[2];
     		possibleAffProps[0] = "Selected Element";
			possibleAffProps[1] = "Responsibility Expression";
     	} else if(this.parent instanceof ComponentRefEditPart || this.parent instanceof Component) {
			possibleAffProps = new String[4];
			possibleAffProps[0] = "Selected Element";
			possibleAffProps[1] = "Component Kind";
			possibleAffProps[2] = "Component Protection";
			possibleAffProps[3] = "Component Context";
		} else if (this.parent instanceof StubEditPart) {
			List<String> pluginNames = new ArrayList<String>();
			List<String> repFactorPluginNames = new ArrayList<String>();
			List<String> affProperties = new ArrayList<String>();
			List<String> preCondPluginNames = new ArrayList<String>();

			List<PluginBinding> pluginBindings = ((Stub) ((StubEditPart) this.parent).getModel()).getBindings();
			for (PluginBinding p : pluginBindings) {
				String name = "Select " + p.getPlugin().getName();
				String repFactorNames = "Replication factor- " + p.getPlugin().getName();
				String preCondNames = "Pre-Condition- " + p.getPlugin().getName();
				pluginNames.add(name);
				repFactorPluginNames.add(repFactorNames);
				preCondPluginNames.add(preCondNames);
			}

			affProperties.addAll(pluginNames);
			affProperties.add("Dynamic Stub");
			affProperties.add("Synchronizing Stub");
			affProperties.add("Blocking Stub");
			affProperties.addAll(repFactorPluginNames);
			affProperties.addAll(preCondPluginNames);

			possibleAffProps = affProperties.toArray(new String[affProperties.size()]);

		} else if (this.parent instanceof Stub) {
			List<String> pluginNames = new ArrayList<String>();
			List<String> repFactorPluginNames = new ArrayList<String>();
			List<String> affProperties = new ArrayList<String>();
			List<String> preCondPluginNames = new ArrayList<String>();

			List<PluginBinding> pluginBindings = ((Stub) this.parent).getBindings();
			for (PluginBinding p : pluginBindings) {
				String name = "Select " + p.getPlugin().getName();
				String repFactorNames = "Replication factor- " + p.getPlugin().getName();
				String preCondNames = "Pre-Condition- " + p.getPlugin().getName();
				pluginNames.add(name);
				repFactorPluginNames.add(repFactorNames);
				preCondPluginNames.add(preCondNames);
			}

			affProperties.addAll(pluginNames);
			affProperties.add("Dynamic Stub");
			affProperties.add("Synchronizing Stub");
			affProperties.add("Blocking Stub");
			affProperties.addAll(repFactorPluginNames);
			affProperties.addAll(preCondPluginNames);

			possibleAffProps = affProperties.toArray(new String[affProperties.size()]);

		} else if (this.parent instanceof PathNodeEditPart) { 
			if (((PathNodeEditPart) (this.parent)).getModel() instanceof FailurePoint) {
				possibleAffProps = new String[3];
				possibleAffProps[0] = "Selected Element";
				possibleAffProps[1] = "Failure Condition";
				possibleAffProps[2] = "Failure Expression";
			} else if (((PathNodeEditPart) (this.parent)).getModel() instanceof OrFork) {
				List<String> branchNames = new ArrayList<String>();
				List<String> affProperties = new ArrayList<String>();

				String id = ((OrFork) ((PathNodeEditPart) this.parent).getModel()).getId();

				List<NodeConnection> branches = ((PathNodeEditPart) this.parent).getDiagram().getConnections();
				for (NodeConnection n : branches) {
					if (n.getSource() instanceof OrFork) {
						String sourceId = ((OrFork) n.getSource()).getId();
						if (id.equals(sourceId)) {
							String branch = "Expression- Branch: " + (n.getSource().getSucc().indexOf(n) + 1);
							branchNames.add(branch);
						}
					}
				}

				affProperties.addAll(branchNames);

				possibleAffProps = affProperties.toArray(new String[affProperties.size()]);

			}
			else if (((PathNodeEditPart) (this.parent)).getModel() instanceof StartPoint) {
				possibleAffProps = new String[2];
				possibleAffProps[0] = "Failure Kind";
				possibleAffProps[1] = "Failure List/Start Point Pre-Condition";
			} else if (((PathNodeEditPart) (this.parent)).getModel() instanceof EndPoint) {
      		possibleAffProps = new String[1];
				possibleAffProps[0] = "Post-Condition";
			} else if ((((PathNodeEditPart) (this.parent)).getModel() instanceof Timer) || (((PathNodeEditPart) (this.parent)).getModel() instanceof WaitingPlace))  {
				possibleAffProps = new String[1];
				possibleAffProps[0] = "Wait Kind";
			} 
		} else if (this.parent instanceof PluginBinding) {

			List<String> affProperties = new ArrayList<String>();
			List<String> pluginNames = new ArrayList<String>();
			List<String> repFactorPluginNames = new ArrayList<String>();
			List<String> preCondPluginNames = new ArrayList<String>();

			List<PluginBinding> bindings = ((PluginBinding) parent).getStub().getBindings();
			for (PluginBinding p : bindings) {
				String plugin = "Select " + p.getPlugin().getName();
				String repFactorNames = "Replication factor- " + p.getPlugin().getName();
				String preCondNames = "Pre-Condition- " + p.getPlugin().getName();
				pluginNames.add(plugin);
				repFactorPluginNames.add(repFactorNames);
				preCondPluginNames.add(preCondNames);
			}

			affProperties.addAll(pluginNames);
			affProperties.add("Dynamic Stub");
			affProperties.add("Synchronizing Stub");
			affProperties.add("Blocking Stub");
			affProperties.addAll(repFactorPluginNames);
			affProperties.addAll(preCondPluginNames);

			possibleAffProps = affProperties.toArray(new String[affProperties.size()]);

		} else if (this.parent instanceof urncore.Condition) {
			if (this.getAffectedProperty() != null && this.getAffectedProperty().startsWith("Expression")) {
				List<String> branchNames = new ArrayList<String>();
				List<String> affProperties = new ArrayList<String>();

				List<NodeConnection> branches = ((urncore.Condition) this.parent).getNodeConnection().getDiagram().getConnections();
				for (NodeConnection n : branches) {
					if (n.getSource() instanceof OrFork) {
						//String id = ((OrFork) ((urncore.Condition) this.parent).getNodeConnection().getSource()).getId();
						//String sourceId = ((OrFork) n.getSource()).getId();
						//if (id.equals(sourceId)) {
						//String a = URNNamingHelper.getNameFromExpression(n.getCondition().getExpression());
						//if (a.equals("true") || a.equals("false")) {
						String branch = "Expression- Branch: " + (n.getSource().getSucc().indexOf(n) + 1);
						branchNames.add(branch);
						//} else {
						//	String branch = "Expression- Branch: " + a;
						//branchNames.add(branch);
						//}
						//}
					}
				}

				affProperties.addAll(branchNames);

				possibleAffProps = affProperties.toArray(new String[affProperties.size()]);
			}
			else if ((this.getTitle().contains("FailurePoint") && ((urncore.Condition) this.parent).getNodeConnection().getSource() instanceof FailurePoint)
					|| (((TextChange) ManageChangeEditorPage.this.selectedChange).getAffectedProperty().equals("Failure Condition"))) {
				possibleAffProps = new String[3];
     		possibleAffProps[0] = "Selected Element";
				possibleAffProps[1] = "Failure Condition";
				possibleAffProps[2] = "Failure Expression";
			}
			else if (((urncore.Condition) this.parent).getEndPoint() instanceof EndPoint) {
				possibleAffProps = new String[1];
				possibleAffProps[0] = "Post-Condition";
			}

			else if (((TextChange) ManageChangeEditorPage.this.selectedChange).getAffectedProperty().startsWith("Pre-Condition- ")
					|| ((TextChange) ManageChangeEditorPage.this.selectedChange).getAffectedProperty().startsWith("Replication factor")) {

				List<String> affProperties = new ArrayList<String>();
				List<String> pluginNames = new ArrayList<String>();
				List<String> repFactorPluginNames = new ArrayList<String>();
				List<String> preCondPluginNames = new ArrayList<String>();

				List<PluginBinding> bindings = ((urncore.Condition) parent).getPluginBinding().getStub().getBindings();
				for (PluginBinding p : bindings) {
					String plugin = "Select " + p.getPlugin().getName();
					String repFactorNames =  "Replication factor- " + p.getPlugin().getName();
					String preCondNames = "Pre-Condition- " + p.getPlugin().getName();
					pluginNames.add(plugin);
					repFactorPluginNames.add(repFactorNames);
					preCondPluginNames.add(preCondNames);
				}
				affProperties.addAll(pluginNames);
				affProperties.add("Dynamic Stub");
				affProperties.add("Synchronizing Stub");
				affProperties.add("Blocking Stub");
				affProperties.addAll(repFactorPluginNames);
				affProperties.addAll(preCondPluginNames);

				possibleAffProps = affProperties.toArray(new String[affProperties.size()]);
			}
			else if (((TextChange) ManageChangeEditorPage.this.selectedChange).getAffectedProperty().equals("Failure List/Start Point Pre-Condition")) {
				possibleAffProps = new String[2];
				possibleAffProps[0] = "Failure Kind";
				possibleAffProps[1] = "Failure List/Start Point Pre-Condition";
			}
			else if (((TextChange) ManageChangeEditorPage.this.selectedChange).getAffectedProperty().equals("Responsibility Expression")) {
				possibleAffProps = new String[2];
				possibleAffProps[0] = "Selected Element";
				possibleAffProps[1] = "Responsibility Expression";
			}
			else if (((TextChange) ManageChangeEditorPage.this.selectedChange).getAffectedProperty().startsWith("Expression")) {

				List<String> branchNames = new ArrayList<String>();
				List<String> affProperties = new ArrayList<String>();

				String id = ((OrFork) ((urncore.Condition) this.parent).getNodeConnection().getSource()).getId();
				List<NodeConnection> branches = ((urncore.Condition) this.parent).getNodeConnection().getDiagram().getConnections();
				for (NodeConnection n : branches) {
					if (n.getSource() instanceof OrFork) {
						String sourceId = ((OrFork) n.getSource()).getId();
						if (id.equals(sourceId)) {
							String branch = "Expression- Branch: " + (n.getSource().getSucc().indexOf(n) + 1);
							branchNames.add(branch);
						}
					}
				}

				affProperties.addAll(branchNames);

				possibleAffProps = affProperties.toArray(new String[affProperties.size()]);

			}
			else if (((TextChange) ManageChangeEditorPage.this.selectedChange).getAffectedProperty().equals("Post-Condition")) {
				possibleAffProps = new String[1];
				possibleAffProps[0] = "Post-Condition";
			}
		} else if (this.parent instanceof FailurePoint) {
			possibleAffProps = new String[3];
			possibleAffProps[0] = "Selected Element";
			possibleAffProps[1] = "Failure Condition";
			possibleAffProps[2] = "Failure Expression";
		} else if (this.parent instanceof StartPoint) {
			possibleAffProps = new String[2];
			possibleAffProps[0] = "Failure Kind";
			possibleAffProps[1] = "Failure List/Start Point Pre-Condition";
		} else if (this.parent instanceof Timer || this.parent instanceof WaitingPlace) {
			possibleAffProps = new String[1];
			possibleAffProps[0] = "Wait Kind";
    	} else if (this.parent instanceof IntentionalElementEditPart || this.parent instanceof IntentionalElement){
    		if (activateDecomp) {
	    		possibleAffProps = new String[4];
	    		possibleAffProps[0] = "Selected Element";
	    		possibleAffProps[1] = "Element's Evaluation";
	    		possibleAffProps[2] = "Quantitative Importance";
	    		possibleAffProps[3] = "Decomposition Type";
    		} else {
    			possibleAffProps = new String[3];
	    		possibleAffProps[0] = "Selected Element";
	    		possibleAffProps[1] = "Element's Evaluation";
	    		possibleAffProps[2] = "Quantitative Importance";
    		}
    	}

        return possibleAffProps;
    }
    
    /*
     * Returns a list of all available changes for the selected element and dynamic context
     */
    private String[][] getAvailableChanges() {
    	String[][] strings;
    	if (currentDynContext != null) {
	    	java.util.List availableChanges = DynamicContextsUtils.getAllAvailableChanges(parent, currentDynContext, urn);
	    	
	    	//From this index onwards, changes will belong to included dynamic contexts and hence, should be greyed out in table
	    	disabledFrom = availableChanges.size();
	    	
	    	//Get all the changes from included contexts as well, if required
	    	getIncludedChanges(currentDynContext, availableChanges);
	    	
	    	ArrayList changesStrings = new ArrayList();
	    	strings = new String[availableChanges.size()][6];
	    	int i = 0;
	        for (Iterator iter = availableChanges.iterator(); iter.hasNext();) {
	            Change possibleChange = (Change) iter.next();
	            if (possibleChange instanceof DeactivationChange){
					if (parent instanceof PluginBinding) {
						strings[i][0] = "Select " + ((PluginBinding) parent).getPlugin().getName();
						strings[i][3] = "Deactivation Change";
						strings[i][4] = " ";
						strings[i][5] = " ";
					} else if ((parent instanceof StubEditPart || parent instanceof Stub || parent instanceof urncore.Condition) && possibleChange.getElement() instanceof PluginBinding) {
						PluginBinding p = (PluginBinding) possibleChange.getElement();
						strings[i][0] = "Select " + p.getPlugin().getName();
						strings[i][3] = "Deactivation Change";
						strings[i][4] = " ";
						strings[i][5] = " ";
					} else {
	            	strings[i][0] = getPossibleAffectedProperties()[0];
	            	strings[i][3] = "Deactivation Change";
		            strings[i][4] = " ";
		            strings[i][5] = " ";
					}
				}
				else if (possibleChange instanceof EnumChange) {
	            	strings[i][0] = ((PropertyChange) possibleChange).getAffectedProperty();
	            	strings[i][3] = "Enumeration Change";
		            strings[i][4] = ((EnumChange)possibleChange).getNewValue();
					strings[i][5] = " ";
				} else if (possibleChange instanceof TextChange) {
					strings[i][0] = ((PropertyChange) possibleChange).getAffectedProperty();
					strings[i][3] = "Text Change";
					strings[i][4] = ((TextChange) possibleChange).getNewValue();
					strings[i][5] = " ";
				} else if (possibleChange instanceof BooleanChange) {
					strings[i][0] = ((PropertyChange) possibleChange).getAffectedProperty();
					strings[i][3] = "Boolean Change";
					strings[i][4] = Boolean.toString(((BooleanChange) possibleChange).isNewValue());
		            strings[i][5] = " ";
	            } else if (possibleChange instanceof LinearChange){
	            	strings[i][0] = ((PropertyChange) possibleChange).getAffectedProperty();
	            	strings[i][3] = "Linear Change";
		            strings[i][4] = Integer.toString(((LinearChange)possibleChange).getNewValue());
		            strings[i][5] = " ";
	            } else if (possibleChange instanceof QuadraticChange){
	            	strings[i][0] = ((PropertyChange) possibleChange).getAffectedProperty();
	            	strings[i][3] = "Quadratic Change";
		            strings[i][4] = " ";
					strings[i][5] = Float.toString(((QuadraticChange)possibleChange).getQuadraticCoefficient()) + "t²+" +
            				Float.toString(((QuadraticChange)possibleChange).getLinearCoefficient()) + "t+" +
            				Float.toString(((QuadraticChange)possibleChange).getConstant());
	            } else if (possibleChange instanceof FormulaChange){
	            	strings[i][0] = ((PropertyChange) possibleChange).getAffectedProperty();
	            	strings[i][3] = "Formula Change";
		            strings[i][4] = " ";
		            strings[i][5] = ((FormulaChange) possibleChange).getFormula();
	            } else {
	            	strings[i][0] = ((PropertyChange) possibleChange).getAffectedProperty();
	            	strings[i][3] = "Constant Change";
		            strings[i][4] = Integer.toString(((ConstantChange)possibleChange).getNewValue());
		            strings[i][5] = " ";
	            }
	            DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
	            strings[i][1] = df.format(possibleChange.getStart());
	            strings[i][2] = df.format(possibleChange.getEnd());
	            
	            i++;
	        }
    	} else {
    		strings = null;
    	}
    	
        return strings;
    }
    
    /*
	 * Adds the changes of included dynamic contexts, if any, to a list
	 */
    private void getIncludedChanges(DynamicContext dyn, List availableChanges) {
    	for (Iterator iter = dyn.getIncludedContexts().iterator(); iter.hasNext();) {
	    	DynamicContext context = (DynamicContext) iter.next();
			availableChanges.addAll(DynamicContextsUtils.getAllAvailableChanges(parent, context, urn));
			getIncludedChanges(context, availableChanges);
		}
    }
    
	/*
	 * Adds a change according to the values selected in the dialog box
	 */
    private Change addChange() {
    	Change addedChange = null;
    	CommandStack cs = ((UCMNavMultiPageEditor) workbenchPage.getActiveEditor()).getDelegatingCommandStack();
    	if (changeType.equals("Deactivation Change")){
			if (ManageChangeEditorPage.this.parent instanceof StubEditPart) {
				List<PluginBinding> pluginBindings = ((Stub)((StubEditPart) parent).getModel()).getBindings();
				for (PluginBinding p : pluginBindings) {
					String plugin = "Select " + p.getPlugin().getName();
					if (plugin.equals(getAffectedProperty()))
						ManageChangeEditorPage.this.parent = p;
				}
			AddDeactivationChangeCommand command = new AddDeactivationChangeCommand(ManageChangeEditorPage.this.parent, currentDynContext, changeType, startDate, endDate, urn);
			if (command.canExecute()){
				cs.execute(command);
				updateStatus("Change Added!!");
				addedChange = (DeactivationChange) command.getAddedChange();
			}
			}
			else {
				AddDeactivationChangeCommand command = new AddDeactivationChangeCommand(ManageChangeEditorPage.this.parent, currentDynContext, changeType, startDate, endDate, urn);
				if (command.canExecute()){
					cs.execute(command);
					updateStatus("Change Added!!");
					addedChange = (DeactivationChange) command.getAddedChange();
				}
			}
		} else if (changeType.equals("Constant Change")){
			AddNumericChangeCommand command = new AddNumericChangeCommand(ManageChangeEditorPage.this.parent, currentDynContext, changeType, affectedProperty, startDate, endDate, newValue, 0.0f, 0.0f, 0.0f, "", urn);
			if (command.canExecute()){
				cs.execute(command);
				updateStatus("Change Added!!");
				addedChange = (ConstantChange) command.getAddedChange();
			}
		} else if (changeType.equals("Linear Change")){
			AddNumericChangeCommand command = new AddNumericChangeCommand(ManageChangeEditorPage.this.parent, currentDynContext, changeType, affectedProperty, startDate, endDate, newValue, 0.0f, 0.0f, 0.0f, "", urn);
			if (command.canExecute()){
				cs.execute(command);
				updateStatus("Change Added!!");
				addedChange = (LinearChange) command.getAddedChange();
			}
		} else if (changeType.equals("Quadratic Change")){
			AddNumericChangeCommand command = new AddNumericChangeCommand(ManageChangeEditorPage.this.parent, currentDynContext, changeType, affectedProperty, startDate, endDate, 0, quadCoeff, linCoeff, conCoeff, "", urn);
			if (command.canExecute()){
				cs.execute(command);
				updateStatus("Change Added!!");
				addedChange = (QuadraticChange) command.getAddedChange();
			}
		} else if (changeType.equals("Formula Change")){
			AddNumericChangeCommand command = new AddNumericChangeCommand(ManageChangeEditorPage.this.parent, currentDynContext, changeType, affectedProperty, startDate, endDate, 0, 0.0f, 0.0f, 0.0f, formula, urn);
			if (command.canExecute()){
				cs.execute(command);
				updateStatus("Change Added!!");
				addedChange = (FormulaChange) command.getAddedChange();
			}
		} else if (changeType.equals("Enumeration Change")){
			AddEnumerationChangeCommand command = new AddEnumerationChangeCommand(ManageChangeEditorPage.this.parent, currentDynContext, changeType, affectedProperty, startDate, endDate, Integer.toString(newValue), urn);
			if (command.canExecute()){
				cs.execute(command);
				updateStatus("Change Added!!");
				addedChange = (EnumChange) command.getAddedChange();
			}
		} else if (changeType.equals("Boolean Change")){
			AddBooleanChangeCommand command = new AddBooleanChangeCommand(ManageChangeEditorPage.this.parent, currentDynContext, changeType, affectedProperty, startDate, endDate, Integer.toString(newValue), urn);
			if (command.canExecute()){
				cs.execute(command);
				updateStatus("Change Added!!");
				addedChange = (BooleanChange) command.getAddedChange();
			}
		} else if (changeType.equals("Text Change")){
			if (affectedProperty.startsWith("Replication factor")) {
				AddTextChangeCommand command = new AddTextChangeCommand(ManageChangeEditorPage.this.parent, currentDynContext, changeType, affectedProperty, startDate, endDate, Integer.toString(newValue), urn);
				if (command.canExecute()){
					cs.execute(command);
					updateStatus("Change Added!!");
					addedChange = (TextChange) command.getAddedChange();
				}
			} else {
				AddTextChangeCommand command = new AddTextChangeCommand(ManageChangeEditorPage.this.parent, currentDynContext, changeType, affectedProperty, startDate, endDate, newValueExpression, urn);
				if (command.canExecute()){
					cs.execute(command);
					updateStatus("Change Added!!");
					addedChange = (TextChange) command.getAddedChange();
				}
			}
		}
    	return addedChange;
    }
    
    /*
	 * Updates the change according to the values selected in the dialog box
	 * 
	 * @param changeToUpdate
	 * 						change that needs to be updated
	 */
    private Change updChange(Change changeToUpdate){
    	Change updatedChange = null;
    	CommandStack cs = ((UCMNavMultiPageEditor) workbenchPage.getActiveEditor()).getDelegatingCommandStack();
    	if (changeType.equals("Constant Change")){
			UpdateNumericChangeCommand command = new UpdateNumericChangeCommand(ManageChangeEditorPage.this.parent, (ConstantChange) changeToUpdate, changeType, affectedProperty, startDate, endDate, newValue, 0.0f, 0.0f, 0.0f, "",urn);
			if (command.canExecute()){
				cs.execute(command);
				updateStatus("Change Updated!!");
				updatedChange = (ConstantChange) command.getUpdatedChange();
			}
		} else if (changeType.equals("Linear Change")){
			UpdateNumericChangeCommand command = new UpdateNumericChangeCommand(ManageChangeEditorPage.this.parent, (LinearChange) changeToUpdate, changeType, affectedProperty, startDate, endDate, newValue, 0.0f, 0.0f, 0.0f, "", urn);
			if (command.canExecute()){
				cs.execute(command);
				updateStatus("Change Updated!!");
				updatedChange = (LinearChange) command.getUpdatedChange();
			}
		} else if (changeType.equals("Quadratic Change")){
			UpdateNumericChangeCommand command = new UpdateNumericChangeCommand(ManageChangeEditorPage.this.parent, (QuadraticChange) changeToUpdate, changeType, affectedProperty, startDate, endDate, 0, quadCoeff, linCoeff, conCoeff, "", urn);
			if (command.canExecute()){
				cs.execute(command);
				updateStatus("Change Updated!!");
				updatedChange = (QuadraticChange) command.getUpdatedChange();
			}
		} else if (changeType.equals("Formula Change")){
			UpdateNumericChangeCommand command = new UpdateNumericChangeCommand(ManageChangeEditorPage.this.parent, (FormulaChange) changeToUpdate, changeType, affectedProperty, startDate, endDate, 0, 0.0f, 0.0f, 0.0f, formula, urn);
			if (command.canExecute()){
				cs.execute(command);
				updateStatus("Change Updated!!");
				updatedChange = (FormulaChange) command.getUpdatedChange();
			}
		} else if (changeType.equals("Deactivation Change")){
			UpdateDeactivationChangeCommand command = new UpdateDeactivationChangeCommand(ManageChangeEditorPage.this.parent, (DeactivationChange) changeToUpdate, changeType, startDate, endDate, urn);
			if (command.canExecute()){
				cs.execute(command);
				updateStatus("Change Updated!!");
				updatedChange = (DeactivationChange) command.getUpdatedChange();
			}
		} else if (changeType.equals("Enumeration Change")){
			UpdateEnumChangeCommand command = new UpdateEnumChangeCommand(ManageChangeEditorPage.this.parent, (EnumChange) changeToUpdate, startDate, endDate, Integer.toString(newValue), urn);
			if (command.canExecute()){
				cs.execute(command);
				updateStatus("Change Updated!!");
				updatedChange = (EnumChange) command.getUpdatedChange();
			}
		} else if (changeType.equals("Boolean Change")){
			UpdateBooleanChangeCommand command = new UpdateBooleanChangeCommand(ManageChangeEditorPage.this.parent, (BooleanChange) changeToUpdate, startDate, endDate, Integer.toString(newValue), urn);
			if (command.canExecute()){
				cs.execute(command);
				updateStatus("Change Updated!!");
				updatedChange = (BooleanChange) command.getUpdatedChange();
			}
		} else if (changeType.equals("Text Change")){
			if (affectedProperty.startsWith("Replication factor")) {
				UpdateTextChangeCommand command = new UpdateTextChangeCommand(ManageChangeEditorPage.this.parent, (TextChange) changeToUpdate, startDate, endDate, Integer.toString(newValue), urn);
				if (command.canExecute()){
					cs.execute(command);
					updateStatus("Change Updated!!");
					updatedChange = (TextChange) command.getUpdatedChange();
				}
			} else{
				UpdateTextChangeCommand command = new UpdateTextChangeCommand(ManageChangeEditorPage.this.parent, (TextChange) changeToUpdate, startDate, endDate, newValueExpression, urn);
				if (command.canExecute()){
					cs.execute(command);
					updateStatus("Change Updated!!");
					updatedChange = (TextChange) command.getUpdatedChange();
				}
			}
		}
    	
    	return updatedChange;
    }
    
    /*
	 * Deletes the requested change
	 * 
	 * @param changeToDelete
	 * 						change that needs to be deleted
	 */
    private void deleteChange(Change changeToDelete){
    	CommandStack cs = ((UCMNavMultiPageEditor) workbenchPage.getActiveEditor()).getDelegatingCommandStack();
    	DeleteChangeCommand command = new DeleteChangeCommand(changeToDelete);
		if (command.canExecute()){
			cs.execute(command);
			updateStatus("Change Deleted!!");
		}
    	
    }
    

    /**
     * Tests if the current workbench selection is a suitable container to use.
     */
    private void initialize() {
    	activateDecomp = false;

        oldDynContext = null;
        currentDynContext = null;
        selectedDynContext = null;
        selectedChange = null;
		String nameInSingleLine = null;
        
    	if (selection != null && selection.isEmpty() == false && selection instanceof IStructuredSelection) {
            IStructuredSelection ssel = (IStructuredSelection) selection;
            if (ssel.size() > 1)
                return;
            Object obj = ssel.getFirstElement();
            if ((obj instanceof LinkRefEditPart) || (obj instanceof IntentionalElementEditPart) ||
            		(obj instanceof ActorRefEditPart) || (obj instanceof RespRefEditPart) 
					|| (obj instanceof ComponentRefEditPart) || (obj instanceof PathNodeEditPart)) {
                parent = obj;

				if (parent instanceof RespRefEditPart) {
					if (((RespRef) ((RespRefEditPart) parent).getModel()).getRespDef().getName().contains("\r")
							|| ((RespRef) ((RespRefEditPart) parent).getModel()).getRespDef().getName().contains("\n")) {
						nameInSingleLine = ((RespRef) ((RespRefEditPart) parent).getModel()).getRespDef().getName().replace("\n"," ");
						nameInSingleLine = nameInSingleLine.replace("\r"," ");
						if (nameInSingleLine.contains("  "))
							nameInSingleLine = nameInSingleLine.replaceAll("\\s+", " ");
						setTitle(Messages.getString("ManageChangePage.ManageChange") + " (" + nameInSingleLine + ") ("
								+ ((RespRef) ((RespRefEditPart) parent).getModel()).getRespDef().getId() + ")");
					} else
						setTitle(Messages.getString("ManageChangePage.ManageChange") + " (" + ((RespRef) ((RespRefEditPart) parent).getModel()).getRespDef().getName() + ") ("
								+ ((RespRef) ((RespRefEditPart) parent).getModel()).getRespDef().getId() + ")");

				} else if (parent instanceof ComponentRefEditPart) {
					if (((Component) ((ComponentRef) ((ComponentRefEditPart) parent).getModel()).getContDef()).getName().contains("\r")
							|| ((Component) ((ComponentRef) ((ComponentRefEditPart) parent).getModel()).getContDef()).getName().contains("\n")) {
						nameInSingleLine = ((Component) ((ComponentRef) ((ComponentRefEditPart) parent).getModel()).getContDef()).getName().replace("\n", " ");
						nameInSingleLine = nameInSingleLine.replace("\r"," ");
						if (nameInSingleLine.contains("  "))
							nameInSingleLine = nameInSingleLine.replaceAll("\\s+", " ");
						setTitle(Messages.getString("ManageChangePage.ManageChange") + " (" + nameInSingleLine + ") ("
								+ ((Component) ((ComponentRef) ((ComponentRefEditPart) parent).getModel()).getContDef()).getId() + ")");
					} else
						setTitle(Messages.getString("ManageChangePage.ManageChange") + " (" + ((Component) ((ComponentRef) ((ComponentRefEditPart) parent).getModel()).getContDef()).getName() + ") ("
								+ ((Component) ((ComponentRef) ((ComponentRefEditPart) parent).getModel()).getContDef()).getId() + ")");
				} else if (parent instanceof IntentionalElementEditPart) {
					if (((IntentionalElement) ((IntentionalElementRef) ((IntentionalElementEditPart) parent).getModel()).getDef()).getName().contains("\r")
							|| ((IntentionalElement) ((IntentionalElementRef) ((IntentionalElementEditPart) parent).getModel()).getDef()).getName().contains("\n")) {
						nameInSingleLine = ((IntentionalElement) ((IntentionalElementRef) ((IntentionalElementEditPart) parent).getModel()).getDef()).getName().replace("\n", " ");
						nameInSingleLine = nameInSingleLine.replace("\r"," ");
						if (nameInSingleLine.contains("  "))
							nameInSingleLine = nameInSingleLine.replaceAll("\\s+", " ");
						setTitle(Messages.getString("ManageChangePage.ManageChange") + " (" + nameInSingleLine + ") ("
								+ ((IntentionalElement) ((IntentionalElementRef) ((IntentionalElementEditPart) parent).getModel()).getDef()).getId() + ")");
					} else
						setTitle(Messages.getString("ManageChangePage.ManageChange") + " (" + ((IntentionalElement) ((IntentionalElementRef) ((IntentionalElementEditPart) parent).getModel()).getDef()).getName() + ") ("
								+ ((IntentionalElement) ((IntentionalElementRef) ((IntentionalElementEditPart) parent).getModel()).getDef()).getId() + ")");
				} else if (parent instanceof LinkRefEditPart) {
					if (((LinkRef) ((LinkRefEditPart) parent).getModel()).getLink().getName().contains("\r")
							|| ((LinkRef) ((LinkRefEditPart) parent).getModel()).getLink().getName().contains("\n")) {
						nameInSingleLine = ((LinkRef) ((LinkRefEditPart) parent).getModel()).getLink().getName().replace("\n", " ");
						nameInSingleLine = nameInSingleLine.replace("\r"," ");
						if (nameInSingleLine.contains("  "))
							nameInSingleLine = nameInSingleLine.replaceAll("\\s+", " ");
						setTitle(Messages.getString("ManageChangePage.ManageChange") + " (" + nameInSingleLine + ") ("
								+ ((LinkRef) ((LinkRefEditPart) parent).getModel()).getLink().getId() + ")");
					} else
						setTitle(Messages.getString("ManageChangePage.ManageChange") + " (" + ((LinkRef) ((LinkRefEditPart) parent).getModel()).getLink().getName() + ") ("
								+ ((LinkRef) ((LinkRefEditPart) parent).getModel()).getLink().getId() + ")");
				} else if (parent instanceof ActorRefEditPart) {
					if (((Actor) ((ActorRef)((ActorRefEditPart) parent).getModel()).getContDef()).getName().contains("\r")
							|| ((Actor) ((ActorRef)((ActorRefEditPart) parent).getModel()).getContDef()).getName().contains("\n")) {
						nameInSingleLine = ((Actor) ((ActorRef)((ActorRefEditPart) parent).getModel()).getContDef()).getName().replace("\n", " ");
						nameInSingleLine = nameInSingleLine.replace("\r"," ");
						if (nameInSingleLine.contains("  "))
							nameInSingleLine = nameInSingleLine.replaceAll("\\s+", " ");
						setTitle(Messages.getString("ManageChangePage.ManageChange") + " (" + nameInSingleLine + ") ("
								+ ((Actor) ((ActorRef)((ActorRefEditPart) parent).getModel()).getContDef()).getId() + ")");
					} else
						setTitle(Messages.getString("ManageChangePage.ManageChange") + " (" + ((Actor) ((ActorRef)((ActorRefEditPart) parent).getModel()).getContDef()).getName() + ") ("
								+ ((Actor) ((ActorRef)((ActorRefEditPart) parent).getModel()).getContDef()).getId() + ")");
				} else {
					if (((URNmodelElement) (((EditPart) parent).getModel())).getName().contains("\r") || ((URNmodelElement) (((EditPart) parent).getModel())).getName().contains("\n")) {
						nameInSingleLine = ((URNmodelElement) (((EditPart) parent).getModel())).getName().replace("\n", " ");
						nameInSingleLine = nameInSingleLine.replace("\r"," ");
						if (nameInSingleLine.contains("  "))
							nameInSingleLine = nameInSingleLine.replaceAll("\\s+", " ");
						setTitle(Messages.getString("ManageChangePage.ManageChange") + " (" + nameInSingleLine + ") ("
								+ ((URNmodelElement) (((EditPart) parent).getModel())).getId() + ")");
					} else
						setTitle(Messages.getString("ManageChangePage.ManageChange") + " (" + ((URNmodelElement) (((EditPart) parent).getModel())).getName() + ") ("
								+ ((URNmodelElement) (((EditPart) parent).getModel())).getId() + ")");
				}
                
            } else if (obj instanceof ChangeTreeEditPart) {
            	Change change = (Change) ((ChangeTreeEditPart) obj).getModel();
            	this.urn = change.getContext().getUrnspec();
            	parent = change.getElement();
            	selectedChange = change;

				if (parent instanceof PluginBinding) {
					if (((PluginBinding) parent).getPlugin().getName().contains("\r") || ((PluginBinding) parent).getPlugin().getName().contains("\n")) {
						nameInSingleLine = ((PluginBinding) parent).getPlugin().getName().replace("\n", " ");
						nameInSingleLine = nameInSingleLine.replace("\r"," ");
						if (nameInSingleLine.contains("  "))
							nameInSingleLine = nameInSingleLine.replaceAll("\\s+", " ");
						setTitle(Messages.getString("ManageChangePage.ManageChange") + " (" + nameInSingleLine + ") ("
								+ ((PluginBinding) parent).getPlugin().getId() + ")");
					} else
						setTitle(Messages.getString("ManageChangePage.ManageChange") + " (" + ((PluginBinding) parent).getPlugin().getName() + ") ("
								+ ((PluginBinding) parent).getPlugin().getId() + ")");
				} else if (parent instanceof urncore.Condition) {
					if (((TextChange) change).getAffectedProperty().startsWith("Pre-Condition")) {
						if (((urncore.Condition) parent).getPluginBinding().getStub().getName().contains("\r")
								|| ((urncore.Condition) parent).getPluginBinding().getStub().getName().contains("\n")) {
							nameInSingleLine = ((urncore.Condition) parent).getPluginBinding().getStub().getName().replace("\n", " ");
							nameInSingleLine = nameInSingleLine.replace("\r"," ");
							if (nameInSingleLine.contains("  "))
								nameInSingleLine = nameInSingleLine.replaceAll("\\s+", " ");
							setTitle(Messages.getString("ManageChangePage.ManageChange") + " (" + nameInSingleLine + ") ("
									+ ((urncore.Condition) parent).getPluginBinding().getStub().getId() + ")");
						} else
							setTitle(Messages.getString("ManageChangePage.ManageChange") + " (" + ((urncore.Condition) parent).getPluginBinding().getStub().getName() + ") ("
									+ ((urncore.Condition) parent).getPluginBinding().getStub().getId() + ")");
					} else if (((TextChange) change).getAffectedProperty().equals("Failure List/Start Point Pre-Condition")) {
						if (((urncore.Condition) parent).getStartPoint().getName().contains("\r")
								|| ((urncore.Condition) parent).getStartPoint().getName().contains("\n")) {
							nameInSingleLine = ((urncore.Condition) parent).getStartPoint().getName().replace("\n", " ");
							nameInSingleLine = nameInSingleLine.replace("\r"," ");
							if (nameInSingleLine.contains("  "))
								nameInSingleLine = nameInSingleLine.replaceAll("\\s+", " ");
							setTitle(Messages.getString("ManageChangePage.ManageChange") + " (" + nameInSingleLine + ") ("
									+ ((urncore.Condition) parent).getStartPoint().getId() + ")");
						} else
							setTitle(Messages.getString("ManageChangePage.ManageChange") + " (" + ((urncore.Condition) parent).getStartPoint().getName() + ") ("
									+ ((urncore.Condition) parent).getStartPoint().getId() + ")");
					} else if (((TextChange) change).getAffectedProperty().equals("Post-Condition")) {
						if (((urncore.Condition) parent).getEndPoint().getName().contains("\r")
								|| ((urncore.Condition) parent).getEndPoint().getName().contains("\n")) {
							nameInSingleLine = ((urncore.Condition) parent).getEndPoint().getName().replace("\n", " ");
							nameInSingleLine = nameInSingleLine.replace("\r"," ");
							if (nameInSingleLine.contains("  "))
								nameInSingleLine = nameInSingleLine.replaceAll("\\s+", " ");
							setTitle(Messages.getString("ManageChangePage.ManageChange") + " (" + nameInSingleLine + ") ("
									+ ((urncore.Condition) parent).getEndPoint().getId() + ")");
						} else
							setTitle(Messages.getString("ManageChangePage.ManageChange") + " (" + ((urncore.Condition) parent).getEndPoint().getName() + ") ("
									+ ((urncore.Condition) parent).getEndPoint().getId() + ")");
					} else if (((TextChange) change).getAffectedProperty().startsWith("Expression")) {
						List<NodeConnection> ncs = ((NodeConnection) ((urncore.Condition) parent).eContainer()).getDiagram().getConnections();
						for (NodeConnection n : ncs) {
							if (n.getSource() instanceof OrFork) {
								if (((OrFork) n.getSource()).getName().contains("\r") || ((OrFork) n.getSource()).getName().contains("\n")) {
									nameInSingleLine = ((OrFork) n.getSource()).getName().replace("\n", " ");
									nameInSingleLine = nameInSingleLine.replace("\r"," ");
									if (nameInSingleLine.contains("  "))
										nameInSingleLine = nameInSingleLine.replaceAll("\\s+", " ");
									setTitle(Messages.getString("ManageChangePage.ManageChange") + " (" + nameInSingleLine + ") ("
											+ ((OrFork) n.getSource()).getId() + ")");
								} else
									setTitle(Messages.getString("ManageChangePage.ManageChange") + " (" + ((OrFork) n.getSource()).getName() + ") ("
											+ ((OrFork) n.getSource()).getId() + ")");
							}
						}
					} else if (((TextChange) change).getAffectedProperty().equals("Failure Condition")) {
						if (((NodeConnection)((urncore.Condition) parent).eContainer()).getSource() instanceof FailurePoint) {
							if (((FailurePoint)((NodeConnection)((urncore.Condition) parent).eContainer()).getSource()).getName().contains("\r")
									|| ((FailurePoint)((NodeConnection)((urncore.Condition) parent).eContainer()).getSource()).getName().contains("\n")) {
								nameInSingleLine = ((FailurePoint)((NodeConnection)((urncore.Condition) parent).eContainer()).getSource()).getName().replace("\n", " ");
								nameInSingleLine = nameInSingleLine.replace("\r"," ");
								if (nameInSingleLine.contains("  "))
									nameInSingleLine = nameInSingleLine.replaceAll("\\s+", " ");
								setTitle(Messages.getString("ManageChangePage.ManageChange") + " (" + nameInSingleLine + ") ("
										+ ((FailurePoint)((NodeConnection)((urncore.Condition) parent).eContainer()).getSource()).getId() + ")");
							} else
								setTitle(Messages.getString("ManageChangePage.ManageChange") + " (" + ((FailurePoint)((NodeConnection)((urncore.Condition) parent).eContainer()).getSource()).getName() + ") ("
										+ ((FailurePoint)((NodeConnection)((urncore.Condition) parent).eContainer()).getSource()).getId() + ")");
						}
					}
				} else if (parent instanceof LinkRef) {
					if (((LinkRef) parent).getLink().getName().contains("\r") || ((LinkRef) parent).getLink().getName().contains("\n")) {
						nameInSingleLine = ((LinkRef) parent).getLink().getName().replace("\n", " ");
						nameInSingleLine = nameInSingleLine.replace("\r"," ");
						if (nameInSingleLine.contains("  "))
							nameInSingleLine = nameInSingleLine.replaceAll("\\s+", " ");
						setTitle(Messages.getString("ManageChangePage.ManageChange") + " (" + nameInSingleLine + ") ("
								+ ((LinkRef) parent).getLink().getId() +")");
					} else
						setTitle(Messages.getString("ManageChangePage.ManageChange") + " (" + ((LinkRef) parent).getLink().getName() + ") ("
								+ ((LinkRef) parent).getLink().getId() +")");
				} else if (parent instanceof Actor) {
					if (((Actor) parent).getName().contains("\r") || ((Actor) parent).getName().contains("\n")) {
						nameInSingleLine = ((Actor) parent).getName().replace("\n", " ");
						nameInSingleLine = nameInSingleLine.replace("\r"," ");
						if (nameInSingleLine.contains("  "))
							nameInSingleLine = nameInSingleLine.replaceAll("\\s+", " ");
						setTitle(Messages.getString("ManageChangePage.ManageChange") + " (" + nameInSingleLine + ") ("
								+ ((Actor) parent).getId() +")");
					} else
						setTitle(Messages.getString("ManageChangePage.ManageChange") + " (" + ((Actor) parent).getName() + ") ("
								+ ((Actor) parent).getId() +")");
				} else {
					if (((URNmodelElement) parent).getName().contains("\r") || ((URNmodelElement) parent).getName().contains("\n")) {
						nameInSingleLine = ((URNmodelElement) parent).getName().replace("\n", " ");
						nameInSingleLine = nameInSingleLine.replace("\r"," ");
						if (nameInSingleLine.contains("  "))
							nameInSingleLine = nameInSingleLine.replaceAll("\\s+", " ");
						setTitle(Messages.getString("ManageChangePage.ManageChange") + " (" + nameInSingleLine + ") ("
								+ ((URNmodelElement) parent).getId() +")");
					} else
						setTitle(Messages.getString("ManageChangePage.ManageChange") + " (" + ((URNmodelElement) parent).getName() + ") ("
								+ ((URNmodelElement) parent).getId() +")");
				} 
            }
            
          //If a Change is selected or a Dynamic Context is already selected
            if (obj instanceof ChangeTreeEditPart) {
            	Change change = (Change) ((ChangeTreeEditPart) obj).getModel();
            	currentDynContext = change.getContext();
            	selectedDynContext = currentDynContext;
            	
            } else if (PlatformUI.getWorkbench().getActiveWorkbenchWindow() != null && PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage() != null
                    && PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findViewReference("seg.jUCMNav.views.DynamicContextsView") != null) { //$NON-NLS-1$
            	DynamicContextsView dv = (DynamicContextsView) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findViewReference("seg.jUCMNav.views.DynamicContextsView").getView(false);
                if (dv != null && dv.getDynamicContext() != null) {
                	currentDynContext = dv.getDynamicContext();
                	selectedDynContext = currentDynContext;
                }
            }
            
            if (parent instanceof IntentionalElementEditPart) {
            	IntentionalElementRef elt = ((IntentionalElementRef)((IntentionalElementEditPart) this.parent).getModel());
            	for (Object link: elt.getDef().getLinksDest()) {
                    if (link instanceof Decomposition) {
                    	activateDecomp = true;
                    	break;
                    }
                }
            	
            } else if (parent instanceof IntentionalElement) {
            	IntentionalElement elt = (IntentionalElement) this.parent;
            	for (Object link: elt.getLinksDest()) {
                    if (link instanceof Decomposition) {
                    	activateDecomp = true;
                    	break;
                    }
                }
            }
            
        }
        
    }

    /**
     * Ensures that the selection is legal
     */
    private void dialogChanged() {
    	wrongFormat = false;
		wrongFormatRepFactor = false;
    	if (changes.getSelectionIndex() < 0)
            updateErrorStatus(Messages.getString("ManageChangePage.SelectChangeType")); //$NON-NLS-1$
        else if (changes.getSelectionIndex() >= 0) {
            changeType = null;
            int index = changes.getSelectionIndex();
            changeType = changes.getItem(index);
            updateErrorStatus(null);
        }
        if (affectedProperties.getSelectionIndex() < 0)
        	updateErrorStatus(Messages.getString("ManageChangePage.PleaseSelectAffectedProperty")); //$NON-NLS-1$
        else if (affectedProperties.getSelectionIndex() >= 0) {
            affectedProperty = null;
            int index = affectedProperties.getSelectionIndex();
            affectedProperty = affectedProperties.getItem(index);
            updateErrorStatus(null);
        }
        
        if (dynamicContexts.getSelectionIndex() < 0)
        	updateErrorStatus(Messages.getString("ManageChangePage.PleaseSelectDynamicContext")); //$NON-NLS-1$
        else if (dynamicContexts.getSelectionIndex() >= 0) {
            currentDynContext = null;
            int index = dynamicContexts.getSelectionIndex();
            currentDynContext = (DynamicContext) urn.getDynamicContexts().get(index);
            updateErrorStatus(null);
        }
        
		if (this.parent instanceof GRLmodelElement || this.parent instanceof ActorRefEditPart
				|| this.parent instanceof IntentionalElementEditPart || this.parent instanceof LinkRefEditPart) {
        if (newValueText.getText() == null || newValueText.getText().trim() == "")
        	newValue = 0;
        else {
				if (newValueText.isEnabled()) {
        	try {
        		newValue = Integer.parseInt(newValueText.getText());
        	} catch (NumberFormatException e) {
        		wrongFormat = true;
        	}
        }
			}
        
        if (newValueDecomp.getSelectionIndex() >= 0) {
        	int index = newValueDecomp.getSelectionIndex();
            String value = newValueDecomp.getItem(index);
            if (value.equals("AND"))
            	newValue = 0;
            else if (value.equals("OR"))
            	newValue = 1;
            else if (value.equals("XOR"))
            	newValue = 2;
        }
        

        if (newValueQuad.getText() == null || newValueQuad.getText().trim() == "")
        	quadCoeff = 0.0f;
        else {
				if (newValueQuad.isEnabled()) {
        	try {
        		quadCoeff = Float.parseFloat(newValueQuad.getText());
        	} catch (NumberFormatException e) {
        		wrongFormat = true;
        	}
				}
        }
        
        if (newValueLin.getText() == null || newValueLin.getText().trim() == "")
        	linCoeff = 0.0f;
        else {
				if (newValueLin.isEnabled()) {
        	try {
        		linCoeff = Float.parseFloat(newValueLin.getText());
        	} catch (NumberFormatException e) {
        		wrongFormat = true;
					} 
				}
        }
        
        if (newValueCon.getText() == null || newValueCon.getText().trim() == "")
        	conCoeff = 0.0f;
        else {
				if (newValueCon.isEnabled()) {
        	try {
        		conCoeff = Float.parseFloat(newValueCon.getText());
        	} catch (NumberFormatException e) {
        		wrongFormat = true;
					} 
				}
        }
        
        if (newValueFor.getText() == null || newValueFor.getText().trim() == "")
        	formula = null;
        else {
				if (newValueFor.isEnabled()) {
        	
        	//Using mxParser for parsing formula
        	Argument t = new Argument("t");
        	Expression e = new Expression(newValueFor.getText(), t);
        	if (e.checkSyntax())
        		formula = newValueFor.getText();
        	else
        		wrongFormat = true;
        }
			}
		}

		if (this.parent instanceof urncore.Condition) {

			try {
				if (newValuePluginBindingPreCond.getEnabled() ==  true && newValuePluginBindingPreCond.getText().isEmpty() || newValuePluginBindingPreCond.getText().trim() == "")
					pluginBindingPreCond = null;
				else {
					SimpleNode n = null;
					// syntax checking
					try {
						jUCMNavParser.ReInit(new StringReader(newValuePluginBindingPreCond.getText()));
						n = jUCMNavParser.Start();
					} catch (Throwable t) {
						errorMessage = Messages.getString("ScenarioUtils.ParserErrorOccurred") + t.getMessage(); //$NON-NLS-1$
						wrongFormat = true;
					}

					// type checking
					UcmEnvironment env = ScenarioUtils.getEnvironment((urncore.Condition) parent);
					jUCMNavTypeChecker checker = new jUCMNavTypeChecker(n, env);

					if (!checker.isValidCondition()) {
						errorMessage =  Messages.getString("ScenarioUtils.IsNotAValidCondition"); //$NON-NLS-1$
						wrongFormat = true;
					} else
						newValueExpression = newValuePluginBindingPreCond.getText();
				}
			} catch (IllegalArgumentException ex) {
				errorMessage =  Messages.getString("ScenarioUtils.TypeCheckerErrorOccurred") + ex.getMessage(); //$NON-NLS-1$
				wrongFormat = true;
			} catch (NullPointerException e) {

			}

			try {
				if (newValueOrForkExp.getEnabled() == true && newValueOrForkExp.getText().isEmpty() || newValueOrForkExp.getText().trim() == "")
					orForkExp = null;
				else {
					SimpleNode n = null;
					// syntax checking
					try {
						jUCMNavParser.ReInit(new StringReader(newValueOrForkExp.getText()));
						n = jUCMNavParser.Start();
					} catch (Throwable t) {
						errorMessage = Messages.getString("ScenarioUtils.ParserErrorOccurred") + t.getMessage(); //$NON-NLS-1$
						wrongFormat = true;
					}

					// type checking
					UcmEnvironment env = ScenarioUtils.getEnvironment((urncore.Condition) parent);
					jUCMNavTypeChecker checker = new jUCMNavTypeChecker(n, env);

					if (!checker.isValidCondition()) {
						errorMessage =  Messages.getString("ScenarioUtils.IsNotAValidCondition"); //$NON-NLS-1$
						wrongFormat = true;
					} else
						newValueExpression = newValueOrForkExp.getText();
				}
			} catch (IllegalArgumentException ex) {
				errorMessage =  Messages.getString("ScenarioUtils.TypeCheckerErrorOccurred") + ex.getMessage(); //$NON-NLS-1$
				wrongFormat = true;
			} catch (NullPointerException e) {

			}


			try {
				if (newValueEndPtPostCond.getEnabled() == true && newValueEndPtPostCond.getText().isEmpty() || newValueEndPtPostCond.getText().trim() == "")
					endPtPostCond = null;
				else {
					SimpleNode n = null;
					// syntax checking
					try {
						jUCMNavParser.ReInit(new StringReader(newValueEndPtPostCond.getText()));
						n = jUCMNavParser.Start();
					} catch (Throwable t) {
						errorMessage = Messages.getString("ScenarioUtils.ParserErrorOccurred") + t.getMessage(); //$NON-NLS-1$
						wrongFormat = true;
					}

					// type checking
					UcmEnvironment env = ScenarioUtils.getEnvironment((urncore.Condition) parent);
					jUCMNavTypeChecker checker = new jUCMNavTypeChecker(n, env);

					if (!checker.isValidCondition()) {
						errorMessage =  Messages.getString("ScenarioUtils.IsNotAValidCondition"); //$NON-NLS-1$
						wrongFormat = true;
					} else
						newValueExpression = newValueEndPtPostCond.getText();
				}
			} catch (IllegalArgumentException ex) {
				errorMessage =  Messages.getString("ScenarioUtils.TypeCheckerErrorOccurred") + ex.getMessage(); //$NON-NLS-1$
				wrongFormat = true;
			} catch (NullPointerException e) {

			}

			try {
				if (affectedProperty.equals("Failure Expression")) {

					try {
						if (newValueFailureExpression.getEnabled() == true && newValueFailureExpression.getText().isEmpty())
							failureExp = null;
						else {
							SimpleNode n = null;
							// syntax checking
							try {
								jUCMNavParser.ReInit(new StringReader(newValueFailureExpression.getText()));
								n = jUCMNavParser.StartResponsibility();
							} catch (Throwable t) {
								errorMessage = Messages.getString("ScenarioUtils.ParserErrorOccurred") + t.getMessage(); //$NON-NLS-1$
								wrongFormat = true;
							}

							// type checking
							UcmEnvironment env = ScenarioUtils.getEnvironment((urncore.Condition) parent);
							jUCMNavTypeChecker checker = new jUCMNavTypeChecker(n, env);

							if (!checker.isValidResponsibility()) {
								errorMessage = Messages.getString("ScenarioUtils.IsNotAValidResponsibility"); //$NON-NLS-1$
								wrongFormat = true;
							}
							else
								newValueExpression = newValueFailureExpression.getText();
						}
					} catch (IllegalArgumentException ex) {
						errorMessage = Messages.getString("ScenarioUtils.TypeCheckerErrorOccurred") + ex.getMessage(); //$NON-NLS-1$
						wrongFormat = true;
					} catch (NullPointerException e) {

					}
				}

				else if (affectedProperty.equals("Failure Condition")) {

					try {
						if (newValueFailureCondition.getEnabled() == true && newValueFailureCondition.getText().isEmpty() || newValueFailureCondition.getText().trim() == "")
							failureCond = null;
						else {
							SimpleNode n = null;
							// syntax checking
							try {
								jUCMNavParser.ReInit(new StringReader(newValueFailureCondition.getText()));
								n = jUCMNavParser.Start();
							} catch (Throwable t) {
								errorMessage = Messages.getString("ScenarioUtils.ParserErrorOccurred") + t.getMessage(); //$NON-NLS-1$
								wrongFormat = true;
							}

							// type checking
							UcmEnvironment env = ScenarioUtils.getEnvironment((urncore.Condition) parent);
							jUCMNavTypeChecker checker = new jUCMNavTypeChecker(n, env);

							if (!checker.isValidCondition()) {
								errorMessage = Messages.getString("ScenarioUtils.IsNotAValidCondition"); //$NON-NLS-1$
								wrongFormat = true;
							} else
								newValueExpression = newValueFailureCondition.getText();
						}
					} catch (IllegalArgumentException ex) {
						errorMessage =  Messages.getString("ScenarioUtils.TypeCheckerErrorOccurred") + ex.getMessage(); //$NON-NLS-1$
						wrongFormat = true;
					} catch (NullPointerException e) {

					}
				} } catch (NullPointerException e) {

				}

			try {
				if (newValueFailureKind.getSelectionIndex() >= 0) {
					int index = newValueFailureKind.getSelectionIndex();
					String value = newValueFailureKind.getItem(index);
					if (value.equals("FAILURE"))
						newValue = 0;
					else if (value.equals("ABORT"))
						newValue = 1;
					else if (value.equals("NONE"))
						newValue = 2;
				}
				if (newValueFailureList.getEnabled() == true && newValueFailureList.getText().isEmpty() || newValueFailureList.getText().trim() == "")
					failureList = null;
				else {
					SimpleNode n = null;
					// syntax checking
					try {
						jUCMNavParser.ReInit(new StringReader(newValueFailureList.getText()));
						n = jUCMNavParser.Start();
					} catch (Throwable t) {
						errorMessage = Messages.getString("ScenarioUtils.ParserErrorOccurred") + t.getMessage(); //$NON-NLS-1$
						wrongFormat = true;
					}

					// type checking
					UcmEnvironment env = ScenarioUtils.getEnvironment((urncore.Condition) parent);
					jUCMNavTypeChecker checker = new jUCMNavTypeChecker(n, env);

					if (!checker.isValidCondition()) {
						errorMessage =  Messages.getString("ScenarioUtils.IsNotAValidCondition"); //$NON-NLS-1$
						wrongFormat = true;
					} else
						newValueExpression = newValueFailureList.getText();
				}
			} catch (IllegalArgumentException ex) {
				errorMessage =  Messages.getString("ScenarioUtils.TypeCheckerErrorOccurred") + ex.getMessage(); //$NON-NLS-1$
				wrongFormat = true;
			} catch (NullPointerException e) {

			}
		}

		if (this.parent instanceof PathNodeEditPart) {

			if ((((PathNodeEditPart) this.parent).getModel()) instanceof OrFork) {
				try {
					if (newValueOrForkExp.getEnabled() == true && newValueOrForkExp.getText().isEmpty() || newValueOrForkExp.getText().trim() == "")
						orForkExp = null;
					else {
						SimpleNode n = null;
						// syntax checking
						try {
							jUCMNavParser.ReInit(new StringReader(newValueOrForkExp.getText()));
							n = jUCMNavParser.Start();
						} catch (Throwable t) {
							errorMessage = Messages.getString("ScenarioUtils.ParserErrorOccurred") + t.getMessage(); //$NON-NLS-1$
							wrongFormat = true;
						}

						// type checking
						UcmEnvironment env = ScenarioUtils.getEnvironment((OrFork) ((PathNodeEditPart) parent).getModel());
						jUCMNavTypeChecker checker = new jUCMNavTypeChecker(n, env);

						if (!checker.isValidCondition()) {
							errorMessage =  Messages.getString("ScenarioUtils.IsNotAValidCondition"); //$NON-NLS-1$
							wrongFormat = true;
						} else
							newValueExpression = newValueOrForkExp.getText();
					}
				} catch (IllegalArgumentException ex) {
					errorMessage =  Messages.getString("ScenarioUtils.TypeCheckerErrorOccurred") + ex.getMessage(); //$NON-NLS-1$
					wrongFormat = true;
				} catch (NullPointerException e) {

				}
			}

			else if ((((PathNodeEditPart) this.parent).getModel()) instanceof EndPoint) {
				try {
					if (newValueEndPtPostCond.getEnabled() == true && newValueEndPtPostCond.getText().isEmpty() || newValueEndPtPostCond.getText().trim() == "")
						endPtPostCond = null;
					else {
						SimpleNode n = null;
						// syntax checking
						try {
							jUCMNavParser.ReInit(new StringReader(newValueEndPtPostCond.getText()));
							n = jUCMNavParser.Start();
						} catch (Throwable t) {
							errorMessage = Messages.getString("ScenarioUtils.ParserErrorOccurred") + t.getMessage(); //$NON-NLS-1$
							wrongFormat = true;
						}

						// type checking
						UcmEnvironment env = ScenarioUtils.getEnvironment((EndPoint) ((PathNodeEditPart) parent).getModel());
						jUCMNavTypeChecker checker = new jUCMNavTypeChecker(n, env);

						if (!checker.isValidCondition()) {
							errorMessage =  Messages.getString("ScenarioUtils.IsNotAValidCondition"); //$NON-NLS-1$
							wrongFormat = true;
						} else
							newValueExpression = newValueEndPtPostCond.getText();
					}
				} catch (IllegalArgumentException ex) {
					errorMessage =  Messages.getString("ScenarioUtils.TypeCheckerErrorOccurred") + ex.getMessage(); //$NON-NLS-1$
					wrongFormat = true;
				} catch (NullPointerException e) {

				}
			}

			else if ((((PathNodeEditPart) this.parent).getModel()) instanceof FailurePoint) {
				try {
					if (affectedProperty.equals("Failure Expression")) {

						try {
							if (newValueFailureExpression.getEnabled() == true && newValueFailureExpression.getText().isEmpty())
								failureExp = null;
							else {
								SimpleNode n = null;
								// syntax checking
								try {
									jUCMNavParser.ReInit(new StringReader(newValueFailureExpression.getText()));
									n = jUCMNavParser.StartResponsibility();
								} catch (Throwable t) {
									errorMessage = Messages.getString("ScenarioUtils.ParserErrorOccurred") + t.getMessage(); //$NON-NLS-1$
									wrongFormat = true;
								}

								// type checking
								UcmEnvironment env = ScenarioUtils.getEnvironment((FailurePoint) ((PathNodeEditPart) parent).getModel());
								jUCMNavTypeChecker checker = new jUCMNavTypeChecker(n, env);

								if (!checker.isValidResponsibility()) {
									errorMessage = Messages.getString("ScenarioUtils.IsNotAValidResponsibility"); //$NON-NLS-1$
									wrongFormat = true;
								}
								else
									newValueExpression = newValueFailureExpression.getText();
							}
						} catch (IllegalArgumentException ex) {
							errorMessage = Messages.getString("ScenarioUtils.TypeCheckerErrorOccurred") + ex.getMessage(); //$NON-NLS-1$
							wrongFormat = true;
						} catch (NullPointerException e) {

						}
					}

					else if (affectedProperty.equals("Failure Condition")) {

						try {
							if (newValueFailureCondition.getEnabled() == true && newValueFailureCondition.getText().isEmpty() || newValueFailureCondition.getText().trim() == "")
								failureCond = null;
							else {
								SimpleNode n = null;
								// syntax checking
								try {
									jUCMNavParser.ReInit(new StringReader(newValueFailureCondition.getText()));
									n = jUCMNavParser.Start();
								} catch (Throwable t) {
									errorMessage = Messages.getString("ScenarioUtils.ParserErrorOccurred") + t.getMessage(); //$NON-NLS-1$
									wrongFormat = true;
								}

								// type checking
								UcmEnvironment env = ScenarioUtils.getEnvironment((FailurePoint) ((PathNodeEditPart) parent).getModel());
								jUCMNavTypeChecker checker = new jUCMNavTypeChecker(n, env);

								if (!checker.isValidCondition()) {
									errorMessage =  Messages.getString("ScenarioUtils.IsNotAValidCondition"); //$NON-NLS-1$
									wrongFormat = true;
								} else
									newValueExpression = newValueFailureCondition.getText();
							}
						} catch (IllegalArgumentException ex) {
							errorMessage =  Messages.getString("ScenarioUtils.TypeCheckerErrorOccurred") + ex.getMessage(); //$NON-NLS-1$
							wrongFormat = true;
						} catch (NullPointerException e) {

						}
					} } catch (NullPointerException e) {

					}
			}

			else if ((((PathNodeEditPart) (ManageChangeEditorPage.this.parent)).getModel() instanceof Timer) || (((PathNodeEditPart) (ManageChangeEditorPage.this.parent)).getModel() instanceof WaitingPlace)) {
				if (newValueWaitKind.getSelectionIndex() >= 0) {
					int index = newValueWaitKind.getSelectionIndex();
					String value = newValueWaitKind.getItem(index);
					if (value.equals("TRANSIENT"))
						newValue = 0;
					else if (value.equals("PERSISTENT"))
						newValue = 1;
				}
			}

			else if ((((PathNodeEditPart) this.parent).getModel()) instanceof StartPoint) {
				try {
					if (newValueFailureKind.getSelectionIndex() >= 0) {
						int index = newValueFailureKind.getSelectionIndex();
						String value = newValueFailureKind.getItem(index);
						if (value.equals("FAILURE"))
							newValue = 0;
						else if (value.equals("ABORT"))
							newValue = 1;
						else if (value.equals("NONE"))
							newValue = 2;
					}

					if (newValueFailureList.getEnabled() == true && newValueFailureList.getText().isEmpty() || newValueFailureList.getText().trim() == "")
						failureList = null;
					else {
						SimpleNode n = null;
						// syntax checking
						try {
							jUCMNavParser.ReInit(new StringReader(newValueFailureList.getText()));
							n = jUCMNavParser.Start();
						} catch (Throwable t) {
							errorMessage = Messages.getString("ScenarioUtils.ParserErrorOccurred") + t.getMessage(); //$NON-NLS-1$
							wrongFormat = true;
						}

						// type checking
						UcmEnvironment env = ScenarioUtils.getEnvironment((StartPoint) ((PathNodeEditPart) parent).getModel());
						jUCMNavTypeChecker checker = new jUCMNavTypeChecker(n, env);

						if (!checker.isValidCondition()) {
							errorMessage =  Messages.getString("ScenarioUtils.IsNotAValidCondition"); //$NON-NLS-1$
							wrongFormat = true;
						} else
							newValueExpression = newValueFailureList.getText();
					}
				} catch (IllegalArgumentException ex) {
					errorMessage =  Messages.getString("ScenarioUtils.TypeCheckerErrorOccurred") + ex.getMessage(); //$NON-NLS-1$
					wrongFormat = true;
				} catch (NullPointerException e) {

				}
			}
		}

		if (this.parent instanceof FailurePoint) {
			try {
				if (affectedProperty.equals("Failure Expression")) {
					try {
						if (newValueFailureExpression.getEnabled() == true && newValueFailureExpression.getText().isEmpty())
							failureExp = null;
						else {
							SimpleNode n = null;
							// syntax checking
							try {
								jUCMNavParser.ReInit(new StringReader(newValueFailureExpression.getText()));
								n = jUCMNavParser.StartResponsibility();
							} catch (Throwable t) {
								errorMessage = Messages.getString("ScenarioUtils.ParserErrorOccurred") + t.getMessage(); //$NON-NLS-1$
								wrongFormat = true;
							}

							// type checking
							UcmEnvironment env = ScenarioUtils.getEnvironment((FailurePoint) parent);
							jUCMNavTypeChecker checker = new jUCMNavTypeChecker(n, env);

							if (!checker.isValidResponsibility()) {
								errorMessage = Messages.getString("ScenarioUtils.IsNotAValidResponsibility"); //$NON-NLS-1$
								wrongFormat = true;
							}
							else
								newValueExpression = newValueFailureExpression.getText();
						}
					} catch (IllegalArgumentException ex) {
						errorMessage = Messages.getString("ScenarioUtils.TypeCheckerErrorOccurred") + ex.getMessage(); //$NON-NLS-1$
						wrongFormat = true;
					} catch (NullPointerException e) {

					}
				}

				else if (affectedProperty.equals("Failure Condition")) {

					try {
						if (newValueFailureCondition.getEnabled() == true && newValueFailureCondition.getText().isEmpty() || newValueFailureCondition.getText().trim() == "")
							failureCond = null;
						else {
							SimpleNode n = null;
							// syntax checking
							try {
								jUCMNavParser.ReInit(new StringReader(newValueFailureCondition.getText()));
								n = jUCMNavParser.Start();
							} catch (Throwable t) {
								errorMessage = Messages.getString("ScenarioUtils.ParserErrorOccurred") + t.getMessage(); //$NON-NLS-1$
								wrongFormat = true;
							}

							// type checking
							UcmEnvironment env = ScenarioUtils.getEnvironment((FailurePoint) parent);
							jUCMNavTypeChecker checker = new jUCMNavTypeChecker(n, env);

							if (!checker.isValidCondition()) {
								errorMessage =  Messages.getString("ScenarioUtils.IsNotAValidCondition"); //$NON-NLS-1$
								wrongFormat = true;
							} else
								newValueExpression = newValueFailureCondition.getText();
						}
					} catch (IllegalArgumentException ex) {
						errorMessage =  Messages.getString("ScenarioUtils.TypeCheckerErrorOccurred") + ex.getMessage(); //$NON-NLS-1$
						wrongFormat = true;
					} catch (NullPointerException e) {

					}
				}
			} catch (NullPointerException e) {

			}
		}

		if (this.parent instanceof RespRefEditPart) {
			if (newValueRespExpression.getEnabled() == true && newValueRespExpression.getText().isEmpty() || newValueRespExpression.getText().trim() == "")
				respExp = null;
			else {
				SimpleNode n = null;
				// syntax checking
				try {
					jUCMNavParser.ReInit(new StringReader(newValueRespExpression.getText()));
					n = jUCMNavParser.StartResponsibility();
				} catch (Throwable t) {
					errorMessage = Messages.getString("ScenarioUtils.ParserErrorOccurred") + t.getMessage(); //$NON-NLS-1$
					wrongFormat = true;
				}

				// type checking
				try {
					UcmEnvironment env = ScenarioUtils.getEnvironment(((RespRef)((RespRefEditPart) parent).getModel()).getRespDef());
					jUCMNavTypeChecker checker = new jUCMNavTypeChecker(n, env);

					if (!checker.isValidResponsibility()) {
						errorMessage = Messages.getString("ScenarioUtils.IsNotAValidResponsibility"); //$NON-NLS-1$
						wrongFormat = true;
					}
					else
						newValueExpression = newValueRespExpression.getText();
				} catch (IllegalArgumentException ex) {
					errorMessage = Messages.getString("ScenarioUtils.TypeCheckerErrorOccurred") + ex.getMessage(); //$NON-NLS-1$
					wrongFormat = true;
				} catch (NullPointerException e) {

				}
			}
		}

		if (this.parent instanceof Responsibility) {
			if (newValueRespExpression.getText().isEmpty() || newValueRespExpression.getText().trim() == "")
				respExp = null;
			else {
				SimpleNode n = null;
				// syntax checking
				try {
					jUCMNavParser.ReInit(new StringReader(newValueRespExpression.getText()));
					n = jUCMNavParser.StartResponsibility();
				} catch (Throwable t) {
					errorMessage = Messages.getString("ScenarioUtils.ParserErrorOccurred") + t.getMessage(); //$NON-NLS-1$
					wrongFormat = true;
				}

				// type checking
				try {
					UcmEnvironment env = ScenarioUtils.getEnvironment((Responsibility) parent);
					jUCMNavTypeChecker checker = new jUCMNavTypeChecker(n, env);

					if (!checker.isValidResponsibility()) {
						errorMessage = Messages.getString("ScenarioUtils.IsNotAValidResponsibility"); //$NON-NLS-1$
						wrongFormat = true;
					}
					else
						newValueExpression = newValueRespExpression.getText();
				} catch (IllegalArgumentException ex) {
					errorMessage = Messages.getString("ScenarioUtils.TypeCheckerErrorOccurred") + ex.getMessage(); //$NON-NLS-1$
					wrongFormat = true;
				} catch (NullPointerException e) {

				}
			}
		}

		try {
			if ((this.parent instanceof StubEditPart || this.parent instanceof Stub || this.parent instanceof PluginBinding || this.parent instanceof urncore.Condition)
					&& (affectedProperty.startsWith("Replication factor") || affectedProperty.startsWith("Pre-Condition") || affectedProperty.startsWith("Select UCM")
							|| affectedProperty.equals("Dynamic Stub") || affectedProperty.equals("Synchronizing Stub") || affectedProperty.equals("Blocking Stub"))) {

				if (newValueDynamicStub.getEnabled() == true && newValueDynamicStub.getSelectionIndex() >= 0) {
					int index = newValueDynamicStub.getSelectionIndex();
					String value = newValueDynamicStub.getItem(index);
					if (value.equals("true"))
						newValue = 0;
					else if (value.equals("false"))
						newValue = 1;
				} else if (newValueSyncStub.getEnabled() == true && newValueSyncStub.getSelectionIndex() >= 0) {
					int index = newValueSyncStub.getSelectionIndex();
					String value = newValueSyncStub.getItem(index);
					if (value.equals("true"))
						newValue = 0;
					else if (value.equals("false"))
						newValue = 1;
				} else if (newValueBlockStub.getEnabled() == true && newValueBlockStub.getSelectionIndex() >= 0) {
					int index = newValueBlockStub.getSelectionIndex();
					String value = newValueBlockStub.getItem(index);
					if (value.equals("true"))
						newValue = 0;
					else if (value.equals("false"))
						newValue = 1;
				}

				if (newValueRepFactor.getText().isEmpty() || newValueRepFactor.getText().trim() == "")
					repFactor = 0; 
				else {
					try {
						newValue = Integer.parseInt(newValueRepFactor.getText());
					} catch (NumberFormatException e) {
						wrongFormatRepFactor = true;
					}
				}

				try {
					if (newValuePluginBindingPreCond.getEnabled() ==  true && newValuePluginBindingPreCond.getText().isEmpty() || newValuePluginBindingPreCond.getText().trim() == "")
						pluginBindingPreCond = null;
					else {
						SimpleNode n = null;
						// syntax checking
						try {
							jUCMNavParser.ReInit(new StringReader(newValuePluginBindingPreCond.getText()));
							n = jUCMNavParser.Start();
						} catch (Throwable t) {
							errorMessage = Messages.getString("ScenarioUtils.ParserErrorOccurred") + t.getMessage(); //$NON-NLS-1$
							wrongFormat = true;
						}

						// type checking
						if (this.parent instanceof urncore.Condition) {
							UcmEnvironment env = ScenarioUtils.getEnvironment((urncore.Condition) parent);
							jUCMNavTypeChecker checker = new jUCMNavTypeChecker(n, env);

							if (!checker.isValidCondition()) {
								errorMessage =  Messages.getString("ScenarioUtils.IsNotAValidCondition"); //$NON-NLS-1$
								wrongFormat = true;
							} else
								newValueExpression = newValuePluginBindingPreCond.getText();
						}
						else if (this.parent instanceof Stub) {
							UcmEnvironment env = ScenarioUtils.getEnvironment((Stub) parent);
							jUCMNavTypeChecker checker = new jUCMNavTypeChecker(n, env);

							if (!checker.isValidCondition()) {
								errorMessage =  Messages.getString("ScenarioUtils.IsNotAValidCondition"); //$NON-NLS-1$
								wrongFormat = true;
							} else
								newValueExpression = newValuePluginBindingPreCond.getText();
						}
						else if (this.parent instanceof StubEditPart) {
							UcmEnvironment env = ScenarioUtils.getEnvironment((Stub) ((StubEditPart) parent).getModel());
							jUCMNavTypeChecker checker = new jUCMNavTypeChecker(n, env);

							if (!checker.isValidCondition()) {
								errorMessage =  Messages.getString("ScenarioUtils.IsNotAValidCondition"); //$NON-NLS-1$
								wrongFormat = true;
							} else
								newValueExpression = newValuePluginBindingPreCond.getText();
						}
						else if (this.parent instanceof PluginBinding) {
							UcmEnvironment env = ScenarioUtils.getEnvironment((PluginBinding) parent);
							jUCMNavTypeChecker checker = new jUCMNavTypeChecker(n, env);

							if (!checker.isValidCondition()) {
								errorMessage =  Messages.getString("ScenarioUtils.IsNotAValidCondition"); //$NON-NLS-1$
								wrongFormat = true;
							} else
								newValueExpression = newValuePluginBindingPreCond.getText();
						}

					}
				} catch (IllegalArgumentException ex) {
					errorMessage =  Messages.getString("ScenarioUtils.TypeCheckerErrorOccurred") + ex.getMessage(); //$NON-NLS-1$
					wrongFormat = true;
				}

			}
		} catch(NullPointerException e) {

		}

		if (this.parent instanceof StartPoint) {
			try {
				if (newValueFailureKind.getSelectionIndex() >= 0) {
					int index = newValueFailureKind.getSelectionIndex();
					String value = newValueFailureKind.getItem(index);
					if (value.equals("FAILURE"))
						newValue = 0;
					else if (value.equals("ABORT"))
						newValue = 1;
					else if (value.equals("NONE"))
						newValue = 2;
				}

				if (newValueFailureList.getEnabled() == true && newValueFailureList.getText().isEmpty() || newValueFailureList.getText().trim() == "")
					failureList = null;
				else {
					SimpleNode n = null;
					// syntax checking
					try {
						jUCMNavParser.ReInit(new StringReader(newValueFailureList.getText()));
						n = jUCMNavParser.Start();
					} catch (Throwable t) {
						errorMessage = Messages.getString("ScenarioUtils.ParserErrorOccurred") + t.getMessage(); //$NON-NLS-1$
						wrongFormat = true;
					}

					// type checking

					UcmEnvironment env = ScenarioUtils.getEnvironment((StartPoint) parent);
					jUCMNavTypeChecker checker = new jUCMNavTypeChecker(n, env);

					if (!checker.isValidCondition()) {
						errorMessage =  Messages.getString("ScenarioUtils.IsNotAValidCondition"); //$NON-NLS-1$
						wrongFormat = true;
					} else
						newValueExpression = newValueFailureList.getText();
				}
			} catch (IllegalArgumentException ex) {
				errorMessage =  Messages.getString("ScenarioUtils.TypeCheckerErrorOccurred") + ex.getMessage(); //$NON-NLS-1$
				wrongFormat = true;
			} catch (NullPointerException e) {

			}
		}

		if (this.parent instanceof Timer || this.parent instanceof WaitingPlace) {
			if (newValueWaitKind.getSelectionIndex() >= 0) {
				int index = newValueWaitKind.getSelectionIndex();
				String value = newValueWaitKind.getItem(index);
				if (value.equals("TRANSIENT"))
					newValue = 0;
				else if (value.equals("PERSISTENT"))
					newValue = 1;
			}
		}

		if (this.parent instanceof ComponentRefEditPart || this.parent instanceof Component) {
			if (newValueCompKind.getEnabled() == true && newValueCompKind.getSelectionIndex() >= 0) {
				int index = newValueCompKind.getSelectionIndex();
				String value = newValueCompKind.getItem(index);
				if (value.equals("TEAM"))
					newValue = 0;
				else if (value.equals("OBJECT"))
					newValue = 1;
				else if (value.equals("PROCESS"))
					newValue = 2;
				else if (value.equals("AGENT"))
					newValue = 3;
				else if (value.equals("ACTOR"))
					newValue = 4;
				else if (value.equals("OTHER"))
					newValue = 5;
			} else if (newValueCompProtection.getEnabled() == true && newValueCompProtection.getSelectionIndex() >= 0) {
				int index = newValueCompProtection.getSelectionIndex();
				String value = newValueCompProtection.getItem(index);
				if (value.equals("true"))
					newValue = 0;
				else if (value.equals("false"))
					newValue = 1;
			} else if (newValueCompContext.getEnabled() == true && newValueCompContext.getSelectionIndex() >= 0) {
				int index = newValueCompContext.getSelectionIndex();
				String value = newValueCompContext.getItem(index);
				if (value.equals("true"))
					newValue = 0;
				else if (value.equals("false"))
					newValue = 1;
			}
		}
        
        Calendar startCal = Calendar.getInstance();
        Calendar endCal = Calendar.getInstance();
        startCal.set(startCalendar.getYear(), startCalendar.getMonth(), startCalendar.getDay(), 0, 0, 0);
        startCal.set(Calendar.MILLISECOND, 0);
        endCal.set(endCalendar.getYear(), endCalendar.getMonth(), endCalendar.getDay(), 0, 0, 0);
        endCal.set(Calendar.MILLISECOND, 0);
        startDate = startCal.getTime();
        endDate = endCal.getTime();
       
    }
    

    /**
     * Updates the error status of the window
     * 
     * @param message
     *            the error message or null if no error message.
     */
    private void updateErrorStatus(String message) {
        setErrorMessage(message);

        if (GeneralPreferencePage.getStrictCodeEditor())
            setPageComplete(message == null);
        else
            setPageComplete(true);

    }
    
    /**
     * Updates the status of the window
     * 
     * @param message
     *            the message or null if no message.
     */
    private void updateStatus(String message) {
        setMessage(message);

        if (GeneralPreferencePage.getStrictCodeEditor())
            setPageComplete(message == null);
        else
            setPageComplete(true);

    }
    
    @SuppressWarnings("deprecation")
	public void resetPage(){
    	dynamicContexts.deselectAll();
    	affectedProperties.deselectAll();
    	affectedProperties.setEnabled(true);
		changes.setEnabled(true);
    	changes.deselectAll();
    	Date today = new Date();
    	startCalendar.setDate(today.getYear()+1900, today.getMonth(), today.getDate());
    	startCalendar.setTime(0, 0, 0);
    	endCalendar.setDate(today.getYear()+1900, today.getMonth(), today.getDate());
    	endCalendar.setTime(0, 0, 0);

		if(this.parent instanceof GRLmodelElement || this.parent instanceof ActorRefEditPart
				|| this.parent instanceof IntentionalElementEditPart || this.parent instanceof LinkRefEditPart) {
			newValueDecomp.setEnabled(false);
    	newValueText.setText("");
    	newValueQuad.setText("");
    	newValueLin.setText("");
    	newValueCon.setText("");
    	newValueFor.setText("");
		} else if (this.parent instanceof ComponentRefEditPart || this.parent instanceof Component) {
			newValueCompKind.setEnabled(true);
			newValueCompKind.deselectAll();
			newValueCompProtection.setEnabled(true);
			newValueCompProtection.deselectAll();
			newValueCompContext.setEnabled(true);
			newValueCompContext.deselectAll();
		} else if (this.parent instanceof StubEditPart || this.parent instanceof Stub || this.parent instanceof PluginBinding
				|| (this.parent instanceof urncore.Condition && (affectedProperty.startsWith("Pre-Condition") || affectedProperty.startsWith("Replication factor")
						|| affectedProperty.equals("Dynamic Stub") || affectedProperty.equals("Synchronizing Stub")
						|| affectedProperty.equals("Blocking Stub") || affectedProperty.startsWith("Select UCM")))) {
			newValueDynamicStub.setEnabled(true);
			newValueDynamicStub.deselectAll();
			newValueSyncStub.setEnabled(true);
			newValueSyncStub.deselectAll();
			newValueBlockStub.setEnabled(true);
			newValueBlockStub.deselectAll();
			newValueRepFactor.setText("");
			newValuePluginBindingPreCond.setText("");
			newValuePluginBindingPreCond.setEnabled(true);
			addVariableToExpression.setEnabled(true);
			newValueSelectVariable.setEnabled(true);
			newValueSelectVariable.deselectAll();
			createNewVariable.setEnabled(true);
		} else if (this.parent instanceof RespRefEditPart || this.parent instanceof Responsibility) {
			newValueRespExpression.setText("");
			newValueRespExpression.setEnabled(true);
			createNewVariable.setEnabled(true);
			newValueSelectVariable.setEnabled(true);
			newValueSelectVariable.deselectAll();
			addVariableToExpression.setEnabled(true);
		} else if (this.parent instanceof PathNodeEditPart) {
			if (((PathNodeEditPart) (ManageChangeEditorPage.this.parent)).getModel() instanceof StartPoint) {
				newValueFailureKind.deselectAll();
				newValueFailureKind.setEnabled(true);
				newValueFailureList.setEnabled(true);
				newValueFailureList.setText("");
				addVariableToExpression.setEnabled(true);
				newValueSelectVariable.setEnabled(true);
				newValueSelectVariable.deselectAll();
				createNewVariable.setEnabled(true);
			}
			else if (((PathNodeEditPart) (ManageChangeEditorPage.this.parent)).getModel() instanceof EndPoint) {
				newValueEndPtPostCond.setText("");
				addVariableToExpression.setEnabled(true);
				newValueSelectVariable.setEnabled(true);
				newValueSelectVariable.deselectAll();
				createNewVariable.setEnabled(true);
			}
			else if (((PathNodeEditPart) (ManageChangeEditorPage.this.parent)).getModel() instanceof FailurePoint) {
				newValueFailureExpression.setText("");
				newValueFailureExpression.setEnabled(true);
				newValueFailureCondition.setText("");
				newValueFailureCondition.setEnabled(true);
				addVariableToExpression.setEnabled(true);
				newValueSelectVariable.setEnabled(true);
				newValueSelectVariable.deselectAll();
				createNewVariable.setEnabled(true);
			}
			else if (((PathNodeEditPart) (ManageChangeEditorPage.this.parent)).getModel() instanceof OrFork) {
				newValueOrForkExp.setText("");
				newValueOrForkExp.setEnabled(true);
				addVariableToExpression.setEnabled(true);
				newValueSelectVariable.setEnabled(true);
				newValueSelectVariable.deselectAll();
				createNewVariable.setEnabled(true);
			}
			else if ((((PathNodeEditPart) (ManageChangeEditorPage.this.parent)).getModel() instanceof Timer) || (((PathNodeEditPart) (ManageChangeEditorPage.this.parent)).getModel() instanceof WaitingPlace)) {
				newValueWaitKind.setEnabled(true);
				newValueWaitKind.deselectAll();
			}
		} else if ((this.parent instanceof StartPoint || this.parent instanceof urncore.Condition) && (affectedProperty.equals("Failure List/Start Point Pre-Condition") || affectedProperty.equals("Failure Kind"))) {
			newValueFailureKind.deselectAll();
			newValueFailureKind.setEnabled(true);
			newValueFailureList.setEnabled(true);
			newValueFailureList.setText("");
			addVariableToExpression.setEnabled(true);
			newValueSelectVariable.setEnabled(true);
			newValueSelectVariable.deselectAll();
			createNewVariable.setEnabled(true);
		}
		else if (this.parent instanceof urncore.Condition && affectedProperty.equals("Post-Condition")) {
			newValueEndPtPostCond.setText("");
			newValueEndPtPostCond.setEnabled(true);
			addVariableToExpression.setEnabled(true);
			newValueSelectVariable.setEnabled(true);
			newValueSelectVariable.deselectAll();
			createNewVariable.setEnabled(true);
		}
		else if ((this.parent instanceof FailurePoint|| this.parent instanceof urncore.Condition) && (affectedProperty.equals("Failure Condition") || affectedProperty.equals("Failure Expression") || affectedProperty.startsWith("Selected"))) {
			newValueFailureExpression.setText("");
			newValueFailureExpression.setEnabled(true);
			newValueFailureCondition.setText("");
			newValueFailureCondition.setEnabled(true);
			addVariableToExpression.setEnabled(true);
			newValueSelectVariable.setEnabled(true);
			newValueSelectVariable.deselectAll();
			createNewVariable.setEnabled(true);
		}
		else if (this.parent instanceof urncore.Condition && affectedProperty.startsWith("Expression")) {
			newValueOrForkExp.setText("");
			newValueOrForkExp.setEnabled(true);
			addVariableToExpression.setEnabled(true);
			newValueSelectVariable.setEnabled(true);
			newValueSelectVariable.deselectAll();
			createNewVariable.setEnabled(true);
		}
		else if (this.parent instanceof Timer || this.parent instanceof WaitingPlace) {
			newValueWaitKind.setEnabled(true);
			newValueWaitKind.deselectAll();
		}

    	availableChanges.removeAll();
    	availableChanges.setEnabled(true);
    	addButton.setEnabled(true);
    	updButton.setEnabled(false);
    	delButton.setEnabled(false);
    	deselButton.setEnabled(false);
    }
    
    /**
     * Returns the numeric change type selected
     * 
     * @return the child
     */
    public String getchangeType() {
        return changeType;
    }
    
    /**
     * Returns the property affected
     * 
     * @return the child
     */
    public String getAffectedProperty() {
        return affectedProperty;
    }
    
    /**
     * Returns the start date
     * 
     * @return the child
     */
    public Date getStartDate() {
        return startDate;
    }
    
    /**
     * Returns the end date
     * 
     * @return the child
     */
    public Date getEndDate() {
        return endDate;
    }
    
    /**
     * Returns the new Value
     * 
     * @return the child
     */
    public int getNewValue() {
        return newValue;
	}

	/**
	 * Returns the new Value Expression
	 * 
	 * @return the child
	 */
	public String newValueExpression() {
		return newValueExpression;
    }

    /**
     * Returns the parent element that was initially given
     * 
     * @return the child
     */
    public Object getParentElement() {
        return parent;
    }
}
