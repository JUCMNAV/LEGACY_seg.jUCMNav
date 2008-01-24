/**
 * 
 */
package seg.jUCMNav.views.preferences;

import java.util.ArrayList;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import seg.jUCMNav.staticSemantic.Rule;
import seg.jUCMNav.staticSemantic.StaticSemanticDefMgr;

/**
 * @author Byrne
 *
 */
public class StaticSemanticPreferencePage extends PreferencePage implements IWorkbenchPreferencePage, SelectionListener {
    private static final String APPDATA_UTILITIES_NUMBER = "UtilitiesNumber";
    private static final String APPDATA_CHECKBOX = "CheckBox";
    private static final String APPDATA_UTILITY = "Utility";
    private static final String BUTTON_EDIT_THE_SELECTED_RULE = "Edit";
    private static final String BUTTON_DEFINE_A_NEW_RULE = "New";
    private static final String BUTTON_DELETE = "Delete";
    private static final String BUTTON_EXPORT = "Export";
    private static final String BUTTON_IMPORT = "Import";

    private static final int BTN_COLUMN_CHECK = 0;
    private static final int TBL_COLUMN_NAME = 1;
    private static final int TBL_COLUMN_DESCRIPTION = 5;
    private static final int TBL_COLUMN_CONTEXT = 3;
    private static final int TBL_COLUMN_CLASSIFIER = 2;
    private static final int TBL_COLUMN_CONSTRAINT = 4;
    private static final int TBL_COLUMN_UTILITY = 6;
    private Table table;
    private Button btnNew;
    private Button btnEdit;
    private Button btnDelete;
    private Button btnExport;
    private Button btnImport;
   
    /* (non-Javadoc)
     * @see org.eclipse.jface.preference.PreferencePage#createContents(org.eclipse.swt.widgets.Composite)
     */
    protected Control createContents(Composite parent) {
        Label label1 = new Label(parent,SWT.LEFT);
        label1.setText("Rules defined:");
        
        table = new Table (parent,  SWT.BORDER |SWT.SINGLE|SWT.FULL_SELECTION);
        table.setLinesVisible(true);
        table.setHeaderVisible(true);
//        table.addSelectionListener(this);
        
        String[] titles = {" ","Name", "Context", "Query Expression", "Constraint Expression", "Descreption","Utilities"};
        for (int i=0; i<titles.length; i++) {
            TableColumn column = new TableColumn (table, SWT.NONE);
            column.setText (titles [i]);
        }
        for(int i=0;i< table.getColumns().length;++i)
        {
            TableColumn tc = table.getColumns()[i];
            tc.pack();
        }
       
        table.setSize (table.computeSize (SWT.DEFAULT, SWT.DEFAULT));
        table.setLayoutData(new GridData(400,400));
        
        Composite c = new Composite(parent,SWT.NULL);
        GridLayout layout = new GridLayout();
        layout.numColumns = 5;
        c.setLayout(layout);
    
        c.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL));   
        
        btnNew = new Button(c,SWT.PUSH);
        btnNew.setText(BUTTON_DEFINE_A_NEW_RULE);
        btnNew.addSelectionListener(this);

        btnEdit = new Button(c,SWT.PUSH);
        btnEdit.setText(BUTTON_EDIT_THE_SELECTED_RULE);
        btnEdit.addSelectionListener(this);
        
        btnDelete = new Button(c,SWT.PUSH);
        btnDelete.setText(BUTTON_DELETE);
        btnDelete.addSelectionListener(this);
        
        btnImport = new Button(c,SWT.PUSH);
        btnImport.setText(BUTTON_IMPORT);
        btnImport.addSelectionListener(this);

        btnExport = new Button(c,SWT.PUSH);
        btnExport.setText(BUTTON_EXPORT);
        btnExport.addSelectionListener(this);
        

        initializeValues();
        return null;
    }

    private void initializeValues() {
        Rule[] rules = StaticSemanticDefMgr.loadDefinitions();
        populateTable(rules);
        table.pack();
        table.setFocus();
        table.setSelection(0);
    }

    /* (non-Javadoc)
     * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
     */
    public void init(IWorkbench workbench) {
//        do nothing

    }

    /* (non-Javadoc)
     * @see org.eclipse.swt.events.SelectionListener#widgetDefaultSelected(org.eclipse.swt.events.SelectionEvent)
     */
    public void widgetDefaultSelected(SelectionEvent e) {
//  do nothing
    }

    /* (non-Javadoc)
     * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse.swt.events.SelectionEvent)
     */
    public void widgetSelected(SelectionEvent e) {
        if(e.getSource() instanceof Button)
        {
            Button btn = (Button)e.getSource();
            if(btn.getText().compareTo(BUTTON_DEFINE_A_NEW_RULE)==0)
            {
                performNew();
            }else if( btn.getText().compareTo(BUTTON_EDIT_THE_SELECTED_RULE)==0)
            {
               performEdit();
            }else if(btn.getText().compareTo(BUTTON_DELETE)==0)
            {
                performDelete();
            }else if(btn.getText().compareTo(BUTTON_IMPORT)==0)
            {
                performImport();
            }else if(btn.getText().compareTo(BUTTON_EXPORT)==0)
            {
                performExport();
            }
        }
    }
    private void performExport() {
        TableItem[] items= table.getSelection();
        if(items.length>0)
        {
            FileDialog dlg = new FileDialog(getControl().getShell(),SWT.SAVE);
            String file = dlg.open();
            if(file!=null)
            {
                Rule[] rules = new Rule[items.length];
                for(int i=0;i<items.length;++i)
                {
                    rules[i] = retriveOneRule(items[i]);
                }
                StaticSemanticDefMgr.Export(rules, file);
            }
        }
    }

    private void performImport() {
        
        FileDialog dlg = new FileDialog(getControl().getShell(),SWT.OPEN);
        String file = dlg.open();
        if(file!=null)
        {
            //save modification before import new rules
            performOk();
            
            Rule[] rules = StaticSemanticDefMgr.Import(file,getControl().getShell());
            this.populateTable(rules);
        }
    }

    private void performDelete() {
        int idx = table.getSelectionIndex();
        deleteRule(idx);
    }

    /**
     * @param idx
     */
    private void deleteRule(int idx) {
        TableEditor editor = (TableEditor) table.getItem(idx).getData(APPDATA_CHECKBOX);
        editor.getEditor().dispose();
        editor.dispose();
        table.remove(idx);
    }

    private void performEdit() {
        RuleEditDialog dlg = new RuleEditDialog(getControl().getShell());
        
        TableItem item = table.getSelection()[0];
        
        dlg.setTitle("Edit a rule");
        dlg.setName(item.getText(TBL_COLUMN_NAME));
        dlg.setQuery(item.getText(TBL_COLUMN_CONTEXT));
        dlg.setContext(item.getText(TBL_COLUMN_CLASSIFIER));
        dlg.setDescription(item.getText(TBL_COLUMN_DESCRIPTION));
        dlg.setConstraint(item.getText(TBL_COLUMN_CONSTRAINT));
        
        ArrayList utilities = new ArrayList();
        
        String sNumber = item.getData(APPDATA_UTILITIES_NUMBER).toString();
        int nNumber = Integer.parseInt(sNumber);
        
        for(int j=0;j<nNumber;++j)
        {
            utilities.add(item.getData( APPDATA_UTILITY+j).toString());
        }
        dlg.setUtilites(utilities);
        
        if(Window.OK==dlg.open())
        {
            TableEditor editor = (TableEditor) item.getData(APPDATA_CHECKBOX);
            Button chk = (Button) editor.getEditor();
            
            Rule r = new Rule(dlg.getName(),dlg.getContext(),dlg.getQuery(),dlg.getConstraint(),chk.getSelection(),dlg.getDescription());
            for(int i=0;i<dlg.getUtilities().size();++i)
            {
                String s = (String) dlg.getUtilities().get(i);
                r.addUtility(s);
            }
            updateRule(table.getSelection()[0],r);
        }       
    }

    private void performNew() {

        RuleEditDialog dlg = new RuleEditDialog(getControl().getShell());
        dlg.setTitle("Define a new rule");
        if(Window.OK==dlg.open())
        {
            Rule r = new Rule(dlg.getName(),dlg.getContext(),dlg.getQuery(),dlg.getConstraint(),true,dlg.getDescription());
            for(int i=0;i<dlg.getUtilities().size();++i)
            {
                String s = (String) dlg.getUtilities().get(i);
                r.addUtility(s);
            }
            appendRule(r);
            table.setSelection(table.getItemCount()-1);
        }
    }

    /**
     * 
     * 
     */
    private void appendRule(Rule r) {
        TableItem item = new TableItem (table, SWT.NONE);
        
        TableEditor editor = new TableEditor (table);
        Button btnSelect = new Button(table,SWT.CHECK);
        editor.grabHorizontal = true;
        btnSelect.setSelection(r.isEnabled());
        editor.setEditor(btnSelect, item, BTN_COLUMN_CHECK);
        
        item.setData(APPDATA_CHECKBOX,editor);
/*        item.setData(APPDATA_UTILITIES_NUMBER,r.getUtilities().size());
        for(int i=0;i<r.getUtilities().size();++i)
        {
            item.setData(APPDATA_UTILITY+i,r.getUtilities().get(i));                    
        }*/
        updateRule(item, r);
    }

    private void updateRule(TableItem item, Rule r) {
        TableEditor editor = (TableEditor) item.getData(APPDATA_CHECKBOX);
        Button chk = (Button) editor.getEditor();
        
        chk.setSelection(r.isEnabled());
        item.setText(TBL_COLUMN_NAME, r.getName());
        item.setText(TBL_COLUMN_DESCRIPTION, r.getDescription());
        item.setText(TBL_COLUMN_CLASSIFIER, r.getClassifier());
        item.setText(TBL_COLUMN_CONTEXT, r.getContext());
        item.setText(TBL_COLUMN_CONSTRAINT, r.getQuery());
        item.setText(TBL_COLUMN_UTILITY, String.valueOf(r.getUtilities().size()));
        
        
        item.setData(APPDATA_UTILITIES_NUMBER, new Integer( r.getUtilities().size()));
        
        int nNumber = Integer.parseInt(item.getData(APPDATA_UTILITIES_NUMBER).toString());
        
        
        for(int i =0;i<r.getUtilities().size();++i)
        {
            String u = (String) r.getUtilities().get(i);
            item.setData( APPDATA_UTILITY + i, u);
        }        
    }

    protected void performDefaults() {
        super.performDefaults();
        Rule[] rules = StaticSemanticDefMgr.getDefaultDefinitions();
        while(table.getItemCount()>0)
        {
            deleteRule(table.getItemCount()-1);
        }
        populateTable(rules);
    }

    private void populateTable(Rule[] rules) {
       for(int i=0;i<rules.length;++i)
       {
           Rule r = rules[i];
           appendRule(r);
       }
        
    }

    public boolean performOk() {
        Rule[] rules = retriveRules();
        StaticSemanticDefMgr.saveDefinitions(rules);
        return true;
    }

    private Rule[] retriveRules() {
        Rule[] rules = new Rule[table.getItemCount()];
        for(int i=0;i<table.getItemCount();++i)
        {
            rules[i] = retriveOneRule(table.getItem(i));
        }
        return rules;
    }

    /**
     * @param item
     */
    private Rule retriveOneRule(TableItem item) {
        
        TableEditor editor = (TableEditor) item.getData(APPDATA_CHECKBOX);
        Button chk = (Button) editor.getEditor();
        Rule r = new Rule();
        r.setName(item.getText(TBL_COLUMN_NAME));
        r.setContext(item.getText(TBL_COLUMN_CONTEXT));
        r.setClassifier(item.getText(TBL_COLUMN_CLASSIFIER));
        r.setDescription(item.getText(TBL_COLUMN_DESCRIPTION));
        r.setQuery(item.getText(TBL_COLUMN_CONSTRAINT));
        r.setEnabled(chk.getSelection());
        
        
        int nNumber = Integer.parseInt(item.getData(APPDATA_UTILITIES_NUMBER).toString());
        
        for(int j=0;j<nNumber;++j)
        {
            r.addUtility(item.getData( APPDATA_UTILITY+j).toString());
        }
        return r;
    }
    
}
