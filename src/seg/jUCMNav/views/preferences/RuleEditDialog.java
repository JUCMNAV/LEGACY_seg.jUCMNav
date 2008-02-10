/**
 * 
 */
package seg.jUCMNav.views.preferences;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.window.IShellProvider;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

/**
 * @author Byrne
 *
 */

import seg.jUCMNav.staticSemantic.Rule;
public class RuleEditDialog extends Dialog implements SelectionListener {

    private static final String BUTTON_DEFINE_A_NEW_UTILITY = "New";
    private static final String BUTTON_DEFINE_A_EDIT_UTILITY = "Edit";
    private static final String BUTTON_DEFINE_A_DELETE_UTILITY = "Delete";
    private String title="Dialog";
    private String name="";
    private String query="";
    private String description="";
    private String context="";
    private String constraint="";
    
    private Text txtName;
    private Text txtContext;
    private Text txtQuery;
    private Text txtCheck;
    private Text txtDesc;

    private Button btnNew;
    private Button btnEdit;
    private Button btnDelete;
 
    List utilities = new ArrayList();
    private Table table;
    
    /**
     * @param parentShell
     */
    public RuleEditDialog(Shell parentShell) {
        super(parentShell);
     }

    /**
     * @param parentShell
     */
    public RuleEditDialog(IShellProvider parentShell) {
        super(parentShell);
     }

    protected Control createDialogArea(Composite parent) {
/*        FillLayout fl = new FillLayout();
        fl.marginHeight = 10;
        fl.marginWidth = 10;
        parent.setLayoutData(fl);
*/        
        Composite composite = (Composite) super.createDialogArea(parent);
              
        Composite c1 = new Composite(parent,SWT.NULL);
        GridLayout layout1 = new GridLayout();
        layout1.numColumns = 1;
        c1.setLayout(layout1);

        Label lblName = new Label(c1,SWT.LEFT);
        lblName.setText("Rule Name:");
        txtName = new Text(c1,SWT.SINGLE | SWT.BORDER);
        /*txtName.addModifyListener(new ModifyListener(){
            @Override
            public void modifyText(ModifyEvent e) {
                eventTextNameModify();
                
            }
        }
        );*/
        txtName.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        txtName.setSize(200, SWT.DEFAULT);
//        txtName.setEditable(false);
               
//        txtDesc.setEditable(false);
        
        Label lblContext = new Label(c1,SWT.LEFT);
        lblContext.setText("Context:");
        txtContext = new Text(c1,SWT.MULTI | SWT.BORDER);
        /*txtContext.addModifyListener(new ModifyListener(){
            @Override
            public void modifyText(ModifyEvent e) {
                eventTextNameModify();
                
            }
        }
        );*/
        txtContext.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        txtContext.setSize(200, SWT.DEFAULT);
//       txtContext.setEditable(false);
        
        Label lblQuery = new Label(c1,SWT.LEFT);
        lblQuery.setText("OCL query expression of collecting all objects to be checked:");
        txtQuery = new Text(c1,SWT.MULTI | SWT.BORDER);
       /* txtQuery.addModifyListener(new ModifyListener(){
            @Override
            public void modifyText(ModifyEvent e) {
                eventTextNameModify();
                
            }
        }
        );*/
        txtQuery.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL));
//        txtCheck.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        txtQuery.setSize(600, SWT.DEFAULT);
//        txtQuery.setEditable(false);
        
        Label lblCheck = new Label(c1,SWT.LEFT);
        lblCheck.setText("OCL constraint expression:");
        txtCheck = new Text(c1,SWT.MULTI | SWT.BORDER);
        /*txtCheck.addModifyListener(new ModifyListener(){
            @Override
            public void modifyText(ModifyEvent e) {
                eventTextNameModify();
                
            }
        }
        );*/
//        txtCheck.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL));
        txtCheck.setLayoutData(new GridData(GridData.FILL_BOTH));
        txtCheck.setSize(600, 600);
//        txtCheck.setEditable(false);
        
        Label lblDesc = new Label(c1,SWT.LEFT);
        lblDesc.setText("Rule Description:");
        txtDesc = new Text(c1,SWT.MULTI | SWT.BORDER);
        txtDesc.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        txtDesc.setSize(600, SWT.DEFAULT);
        
        Label lblUtilities = new Label(c1,SWT.LEFT);
        lblDesc.setText("Description:");
        
        table = new Table (c1,  SWT.BORDER |SWT.SINGLE|SWT.FULL_SELECTION);
        table.setLinesVisible(true);
        table.setHeaderVisible(true);      
        TableColumn column = new TableColumn (table, SWT.NONE);
        column.setText("Utilities");
        column.setWidth(600);
        
/*        for(TableColumn tc: table.getColumns())
        {
            tc.pack();
        } */      
        //table.setSize (table.computeSize (SWT.DEFAULT, SWT.DEFAULT));
 //       table.setSize(600, 600);
        table.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL|GridData.GRAB_VERTICAL|GridData.HORIZONTAL_ALIGN_FILL | GridData.VERTICAL_ALIGN_FILL));
                
        
        Composite c = new Composite(c1,SWT.NULL);
        GridLayout layout = new GridLayout();
        layout.numColumns = 3;
        c.setLayout(layout);
    
        c.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL));   
        btnNew = new Button(c,SWT.PUSH);
        btnNew.setText(BUTTON_DEFINE_A_NEW_UTILITY);
        btnNew.addSelectionListener(this);

        btnEdit = new Button(c,SWT.PUSH);
        btnEdit.setText(BUTTON_DEFINE_A_EDIT_UTILITY);
        btnEdit.addSelectionListener(this);

        btnDelete = new Button(c,SWT.PUSH);
        btnDelete.setText(BUTTON_DEFINE_A_DELETE_UTILITY);
        btnDelete.addSelectionListener(this);

        init();
        return composite;
    }

    private void init() {
        txtName.setText(getName());
        txtContext.setText(getContext());
        txtDesc.setText(getDescription());
        txtCheck.setText(getConstraint());
        txtQuery.setText(getQuery());
        for(int i =0; i< utilities.size();++i)
        {
            String s = (String) utilities.get(i);
            appendUtility(s);
        }
        table.pack();
        table.setFocus();
        table.setSelection(0);
       
    }

    protected void configureShell(Shell newShell) {
        super.configureShell(newShell);
        newShell.setText(title);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUtilites(List utilities)
    {
        this.utilities = utilities;
    }
    
    protected void okPressed() {
        this.setName(txtName.getText());
        this.setContext(txtContext.getText());
        this.setQuery(txtQuery.getText());
        this.setConstraint(txtCheck.getText());
        this.setDescription(txtDesc.getText());
        utilities.clear();
        for(int i =0;i<table.getItems().length;++i)
        {
            TableItem item = table.getItems()[i];
            utilities.add(item.getText());
        }
        
        Rule r = new Rule(name,context,query,constraint,false,description);
        for(int i=0;i<utilities.size();++i)
        {
            String s = (String) utilities.get(i);
            r.addUtility(s);
        }       
        if(r.isValid()){
            super.okPressed();
        }else
        {
            MessageBox msg = new MessageBox(this.getShell(), SWT.ICON_ERROR);
            msg.setMessage(r.getErrors());
            msg.setText("Invalidated rule difinition");
            msg.open();            
        }        
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getConstraint() {
        return constraint;
    }

    public void setConstraint(String constraint) {
        this.constraint = constraint;
    }

    public void widgetDefaultSelected(SelectionEvent e) {
       //do nothing        
    }

    public void widgetSelected(SelectionEvent e) {
        if(e.getSource() instanceof Button)
        {
            Button btn = (Button)e.getSource();
            if(btn.getText().compareTo(BUTTON_DEFINE_A_NEW_UTILITY)==0)
            {
                performNewUtility();
            }else
            if(btn.getText().compareTo(BUTTON_DEFINE_A_EDIT_UTILITY)==0)
            {
                performEditUtility();
            }else
            if(btn.getText().compareTo(BUTTON_DEFINE_A_DELETE_UTILITY)==0)
            {
                performDeleteUtility(table.getSelectionIndex());
            }
        }       
        
    }

    private void performDeleteUtility(int idx) {
        table.remove(idx);
        table.select(idx);
        
    }

    private void performEditUtility() {
        RuleUtilityEditDialog dlg = new RuleUtilityEditDialog(this.getShell());
        dlg.setTitle("Modify a utility");
        dlg.setText(table.getSelection()[0].getText());
        if(Window.OK==dlg.open())
        {
            updateUtility(table.getSelection()[0], dlg.getText());
        }
    }

    private void updateUtility(TableItem item, String text) {
        item.setText(0,text);       
    }

    private void performNewUtility() {
        
        RuleUtilityEditDialog dlg = new RuleUtilityEditDialog(this.getShell());
        dlg.setTitle("Define a new utility");
        if(Window.OK==dlg.open())
        {
            appendUtility(dlg.getText());
            table.setSelection(table.getItemCount()-1);
        }   
    }

    private void appendUtility(String text) {
        TableItem item = new TableItem (table, SWT.NONE);
        table.select(table.getItemCount()-1);
        updateUtility(item,text);
    }

    public List getUtilities() {
        return utilities;
    }



}
