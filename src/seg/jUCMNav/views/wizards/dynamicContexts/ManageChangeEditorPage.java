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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;

import org.eclipse.gef.commands.CommandStack;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
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
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.mariuszgromada.math.mxparser.*;

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
import seg.jUCMNav.editparts.IntentionalElementEditPart;
import seg.jUCMNav.editparts.LinkRefEditPart;
import seg.jUCMNav.editparts.dynamicContextTreeEditparts.ChangeTreeEditPart;
import seg.jUCMNav.editparts.dynamicContextTreeEditparts.DynamicContextsUtils;
import seg.jUCMNav.model.commands.create.AddDeactivationChangeCommand;
import seg.jUCMNav.model.commands.create.AddEnumerationChangeCommand;
import seg.jUCMNav.model.commands.create.AddNumericChangeCommand;
import seg.jUCMNav.model.commands.delete.DeleteChangeCommand;
import seg.jUCMNav.model.commands.transformations.UpdateDeactivationChangeCommand;
import seg.jUCMNav.model.commands.transformations.UpdateEnumChangeCommand;
import seg.jUCMNav.model.commands.transformations.UpdateNumericChangeCommand;
import seg.jUCMNav.model.util.URNNamingHelper;
import seg.jUCMNav.views.dynamicContexts.DynamicContextsView;
import seg.jUCMNav.views.preferences.GeneralPreferencePage;
import urn.URNspec;
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

/**
 * The ManageChangeEditorPage contains the UI code for managing changes.
 * 
 * @author aprajita
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
    private URNspec urn;
    private DynamicContext currentDynContext, oldDynContext, selectedDynContext;
    private Change selectedChange;
    private int newValue;
    private float quadCoeff, linCoeff, conCoeff;
    private String formula;
    private Boolean activateDecomp;
    private Composite container;
    private IWorkbenchPage workbenchPage;
    private Boolean wrongFormat;

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

        setTitle(Messages.getString("ManageChangePage.ManageChange")); //$NON-NLS-1$
        
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
                String[][] availChanges = getAvailableChanges();
                availableChanges.removeAll();
                if (availChanges != null) {
        	        for (int i = 0; i < availChanges.length; i++) {
        	        	if ((availChanges[i][0].startsWith("Selected") && affectedProperty.startsWith("Selected")) || (availChanges[i][0].equals(affectedProperty))) {
	        	            TableItem item = new TableItem(availableChanges, SWT.NONE);
	        	            item.setText(new String[] {availChanges[i][0], availChanges[i][1], availChanges[i][2], availChanges[i][3], availChanges[i][4], availChanges[i][5]});
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
			                items[i].dispose();
			                TableItem newItem = new TableItem(availableChanges, SWT.NONE, j);
			                newItem.setText(oldItem);
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
            	    	DateFormat df = new SimpleDateFormat("MM/dd/yyyy"); 
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
	        }
        }
        availableChanges.addSelectionListener(new SelectionListener() {
            public void widgetSelected(SelectionEvent e) {
            	updateItemInDialog(availableChanges.getSelectionIndex());
            	dialogChanged();
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
            	if (changes.getText().equals("Constant Change") || changes.getText().equals("Linear Change"))
            		newValueText.setEnabled(true);
            	else
            		newValueText.setEnabled(false);
            	
            	if (changes.getText().equals("Enumeration Change"))
            		newValueDecomp.setEnabled(true);
            	else
            		newValueDecomp.setEnabled(false);
            	
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
        
        //Button to add change
        addButton = new Button(container, SWT.PUSH);
        addButton.setText("Add Change");
        addButton.addSelectionListener(new SelectionListener() {
			
        	@Override
			public void widgetSelected(SelectionEvent e) {
				dialogChanged();
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
				else{
					Change addedChange = addChange();
					TableItem item = new TableItem(availableChanges, SWT.NONE);
					DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
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
				else{
					DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
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
                	DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
    				java.util.List availChanges = DynamicContextsUtils.getAllAvailableChanges(ManageChangeEditorPage.this.parent, currentDynContext, urn);
    		    	Change changeToDelete = null;
    		    	for (Iterator iter = availChanges.iterator(); iter.hasNext();) {
    		    		Change change = (Change) iter.next();
    		    		if (df.format(change.getStart()).equals(availableChanges.getItem(availableChanges.getSelectionIndex()).getText(1)) && 
    		    				(((availableChanges.getItem(availableChanges.getSelectionIndex()).getText(0).startsWith("Selected") && change instanceof DeactivationChange) 
    		    						|| (!(change instanceof DeactivationChange) && ((PropertyChange)change).getAffectedProperty().equals(availableChanges.getItem(availableChanges.getSelectionIndex()).getText(0)))))){
    		    			changeToDelete = change;
    		    			break;			    			
    		    		}
    		    	}
    		    	deleteChange(changeToDelete);
    		    	availableChanges.remove(availableChanges.getSelectionIndex());
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
        		affectedProperties.deselectAll();
            	affectedProperties.setEnabled(true);
            	changes.deselectAll();
            	Date today = new Date();
            	startCalendar.setDate(today.getYear()+1900, today.getMonth(), today.getDate());
            	startCalendar.setTime(0, 0, 0);
            	endCalendar.setDate(today.getYear()+1900, today.getMonth(), today.getDate());
            	endCalendar.setTime(0, 0, 0);
            	newValueText.setText("");
            	newValueDecomp.deselectAll();
            	newValueQuad.setText("");
            	newValueLin.setText("");
            	newValueCon.setText("");
            	newValueFor.setText("");
            	availableChanges.deselectAll();
            	addButton.setEnabled(true);
            	updButton.setEnabled(false);
            	delButton.setEnabled(false);
            	deselButton.setEnabled(false);
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
            String[][] availChanges1 = getAvailableChanges();
            availableChanges.removeAll();
            if (availChanges1 != null) {
    	        for (int i = 0; i < availChanges1.length; i++) {
    	            TableItem item = new TableItem(availableChanges, SWT.NONE);
    	            item.setText(new String[] {availChanges1[i][0], availChanges1[i][1], availChanges1[i][2], availChanges1[i][3], availChanges1[i][4], availChanges1[i][5]});
    	        }
            }
            availableChanges.update();
            availableChanges.setEnabled(true);
            affectedProperties.setEnabled(true);
        }
        
        //Select the change in ManageChange page if opened from property view 
        if (selectedChange != null) {
        	DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        	for (int i = 0; i < availableChanges.getItemCount(); i++) {
        		if (availableChanges.getItem(i).getText(0).startsWith("Selected")) {
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
    
    /*
     * Checks if the available changes overlap with the new change's start or end date
     */
    private boolean checkForOverlap() {
    	java.util.List availableChanges = DynamicContextsUtils.getAllAvailableChanges(parent, currentDynContext, urn);
    	for (Iterator iter = availableChanges.iterator(); iter.hasNext();) {
    		Change change = (Change) iter.next();
    		if ((affectedProperty.startsWith("Selected") && change instanceof DeactivationChange) || ((change instanceof PropertyChange) && ((PropertyChange)change).getAffectedProperty().equals(affectedProperty))) {
    			if ((change.getStart().before(startDate) && change.getEnd().after(startDate)) || change.getStart().equals(startDate) || 
    					(change.getStart().after(startDate) && change.getStart().before(endDate)))
        			return false;
        		
    		}
    	}
    	return true;
    }
    
    /*
     * Updates the fields in the dialog according to the selected change
     */
    private void updateItemInDialog(int i) {
    	int index = dynamicContexts.getSelectionIndex();
        oldDynContext = (DynamicContext) urn.getDynamicContexts().get(index);
    	String affProperty = availableChanges.getItem(i).getText(0);
    	affectedProperties.setText(affProperty);
    	affectedProperties.setEnabled(false);
    	changes.setItems(new String[] {availableChanges.getItem(i).getText(3)});
    	changes.setText(availableChanges.getItem(i).getText(3));
    	changes.setEnabled(false);
    	String sDate = availableChanges.getItem(i).getText(1);
    	String eDate = availableChanges.getItem(i).getText(2);
    	startCalendar.setDate(Integer.parseInt(sDate.substring(6, 10)), Integer.parseInt(sDate.substring(0, 2)) - 1, Integer.parseInt(sDate.substring(3, 5)));
    	startCalendar.setTime(0, 0, 0);
    	startCalendar.update();
    	endCalendar.setTime(0, 0, 0);
    	endCalendar.setDate(Integer.parseInt(eDate.substring(6, 10)), Integer.parseInt(eDate.substring(0, 2)) - 1, Integer.parseInt(eDate.substring(3, 5)));
    	endCalendar.update();
    	addButton.setEnabled(false);
    	updButton.setEnabled(true);
    	delButton.setEnabled(true);
    	deselButton.setEnabled(true);
    	if (changes.getText().equals("Constant Change") || changes.getText().equals("Linear Change")) {
    		newValueText.setText(availableChanges.getItem(i).getText(4));
        	newValueText.setEnabled(true);
    	}
    	else
    		newValueText.setEnabled(false);
    	
    	if (changes.getText().equals("Enumeration Change")) {
    		newValueDecomp.setText(availableChanges.getItem(i).getText(4));
    		newValueDecomp.setEnabled(true);
    	}
    	else
    		newValueDecomp.setEnabled(false);
    	
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
    	}
    	
    	if (changes.getText().equals("Formula Change")) {
    		newValueFor.setText(availableChanges.getItem(i).getText(5));
        	newValueFor.setEnabled(true);
    	}
    	else
    		newValueFor.setEnabled(false);
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
    		if (change1 != change && ((affectedProperty.startsWith("Selected") && change1 instanceof DeactivationChange) 
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
	    	if (affectedProperty.contains("Selected")){
	    		possibleChanges = new String[1];
	    		possibleChanges[0] = "Deactivation Change";
	    	} else if (affectedProperty.equals("Decomposition Type")) {
	    		possibleChanges = new String[1];
	    		possibleChanges[0] = "Enumeration Change";
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
    			possibleAffProps[0] = "Selected Depenedency Link";
            } else if (lr instanceof Decomposition) {
            	possibleAffProps = new String[1];
    			possibleAffProps[0] = "Selected Decomposition Link";
            }
    		
    	} else if (this.parent instanceof ActorRefEditPart || this.parent instanceof ActorRef){
    		possibleAffProps = new String[3];
    		possibleAffProps[0] = "Selected Actor";
    		possibleAffProps[1] = "Count";
    		possibleAffProps[2] = "Quantitative Importance";
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
	    	ArrayList changesStrings = new ArrayList();
	    	strings = new String[availableChanges.size()][6];
	    	int i = 0;
	        for (Iterator iter = availableChanges.iterator(); iter.hasNext();) {
	            Change possibleChange = (Change) iter.next();
	            //strings[i][0] = URNNamingHelper.getName(possibleChange.getContext());
	            if (possibleChange instanceof DeactivationChange){
	            	strings[i][0] = getPossibleAffectedProperties()[0];
	            	strings[i][3] = "Deactivation Change";
		            strings[i][4] = " ";
		            strings[i][5] = " ";
	            } else if (possibleChange instanceof EnumChange) {
	            	strings[i][0] = ((PropertyChange) possibleChange).getAffectedProperty();
	            	strings[i][3] = "Enumeration Change";
		            strings[i][4] = ((EnumChange)possibleChange).getNewValue();
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
	            DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
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
	 * Adds a change according to the values selected in the dialog box
	 */
    private Change addChange() {
    	Change addedChange = null;
    	CommandStack cs = ((UCMNavMultiPageEditor) workbenchPage.getActiveEditor()).getDelegatingCommandStack();
    	if (changeType.equals("Deactivation Change")){
			AddDeactivationChangeCommand command = new AddDeactivationChangeCommand(ManageChangeEditorPage.this.parent, currentDynContext, changeType, startDate, endDate, urn);
			if (command.canExecute()){
				cs.execute(command);
				updateStatus("Change Added!!");
				addedChange = (DeactivationChange) command.getAddedChange();
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
        
    	if (selection != null && selection.isEmpty() == false && selection instanceof IStructuredSelection) {
            IStructuredSelection ssel = (IStructuredSelection) selection;
            if (ssel.size() > 1)
                return;
            Object obj = ssel.getFirstElement();
            if ((obj instanceof LinkRefEditPart) || (obj instanceof IntentionalElementEditPart) ||
            		(obj instanceof ActorRefEditPart)) {
                parent = obj;
                
            } else if (obj instanceof ChangeTreeEditPart) {
            	Change change = (Change) ((ChangeTreeEditPart) obj).getModel();
            	this.urn = change.getContext().getUrnspec();
            	parent = change.getElement();
            	selectedChange = change;
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
    	if (changes.getSelectionIndex() < 0)
            updateStatus(Messages.getString("ManageChangePage.SelectChangeType")); //$NON-NLS-1$
        else if (changes.getSelectionIndex() >= 0) {
            changeType = null;
            int index = changes.getSelectionIndex();
            changeType = changes.getItem(index);
            updateStatus(null);
        }
        if (affectedProperties.getSelectionIndex() < 0)
            updateStatus(Messages.getString("ManageChangePage.PleaseSelectAffectedProperty")); //$NON-NLS-1$
        else if (affectedProperties.getSelectionIndex() >= 0) {
            affectedProperty = null;
            int index = affectedProperties.getSelectionIndex();
            affectedProperty = affectedProperties.getItem(index);
            updateStatus(null);
        }
        
        if (dynamicContexts.getSelectionIndex() < 0)
            updateStatus(Messages.getString("ManageChangePage.PleaseSelectDynamicContext")); //$NON-NLS-1$
        else if (dynamicContexts.getSelectionIndex() >= 0) {
            currentDynContext = null;
            int index = dynamicContexts.getSelectionIndex();
            currentDynContext = (DynamicContext) urn.getDynamicContexts().get(index);
            updateStatus(null);
        }
        
        
        if (newValueText.getText() == null || newValueText.getText().trim() == "")
        	newValue = 0;
        else {
        	try {
        		newValue = Integer.parseInt(newValueText.getText());
        	} catch (NumberFormatException e) {
        		wrongFormat = true;
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
        	try {
        		quadCoeff = Float.parseFloat(newValueQuad.getText());
        	} catch (NumberFormatException e) {
        		wrongFormat = true;
        	}
        	
        }
        
        if (newValueLin.getText() == null || newValueLin.getText().trim() == "")
        	linCoeff = 0.0f;
        else {
        	try {
        		linCoeff = Float.parseFloat(newValueLin.getText());
        	} catch (NumberFormatException e) {
        		wrongFormat = true;
        	}        	
        }
        
        if (newValueCon.getText() == null || newValueCon.getText().trim() == "")
        	conCoeff = 0.0f;
        else {
        	try {
        		conCoeff = Float.parseFloat(newValueCon.getText());
        	} catch (NumberFormatException e) {
        		wrongFormat = true;
        	}        	
        }
        
        if (newValueFor.getText() == null || newValueFor.getText().trim() == "")
        	formula = null;
        else {
        	
        	//Using mxParser for parsing formula
        	Argument t = new Argument("t");
        	Expression e = new Expression(newValueFor.getText(), t);
        	if (e.checkSyntax())
        		formula = newValueFor.getText();
        	else
        		wrongFormat = true;
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

    }
    
    @SuppressWarnings("deprecation")
	public void resetPage(){
    	dynamicContexts.deselectAll();
    	affectedProperties.deselectAll();
    	affectedProperties.setEnabled(true);
    	changes.deselectAll();
    	Date today = new Date();
    	startCalendar.setDate(today.getYear()+1900, today.getMonth(), today.getDate());
    	startCalendar.setTime(0, 0, 0);
    	endCalendar.setDate(today.getYear()+1900, today.getMonth(), today.getDate());
    	endCalendar.setTime(0, 0, 0);
    	newValueText.setText("");
    	newValueQuad.setText("");
    	newValueLin.setText("");
    	newValueCon.setText("");
    	newValueFor.setText("");
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
     * Returns the parent element that was initially given
     * 
     * @return the child
     */
    public Object getParentElement() {
        return parent;
    }
}
