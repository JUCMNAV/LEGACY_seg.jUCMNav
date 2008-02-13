package seg.jUCMNav.views.preferences.staticSemantic;

import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.window.IShellProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import seg.jUCMNav.staticSemantic.Rule;
import seg.jUCMNav.staticSemantic.RuleGroup;
import seg.jUCMNav.staticSemantic.StaticSemanticDefMgr;

public class GroupEditDialog extends Dialog {

    private static final int BTN_ID_AddToGroup = IDialogConstants.CANCEL_ID + 1;
    private static final int BTN_ID_AddAllToGroup = IDialogConstants.CANCEL_ID + 2;
    private static final int BTN_ID_RemoveFromGroup = IDialogConstants.CANCEL_ID + 3;
    private static final int BTN_ID_RemoveAllFromGroup = IDialogConstants.CANCEL_ID + 4;
    private Table members;
    private Table nonMembers;
    
    private RuleGroup group;
    private Text txtName;
    
    public GroupEditDialog(Shell parentShell) {
        super(parentShell);
        // TODO Auto-generated constructor stub
        this.setShellStyle(this.getShellStyle() | SWT.RESIZE);
    }

    public GroupEditDialog(IShellProvider parentShell) {
        super(parentShell);
        // TODO Auto-generated constructor stub
        this.setShellStyle(this.getShellStyle() | SWT.RESIZE);
    }

    protected void cancelPressed() {
        // TODO Auto-generated method stub
        super.cancelPressed();
    }

    protected Control createDialogArea(Composite parent) {
        Composite control = (Composite) super.createDialogArea(parent);
        
        Composite top = new Composite(control,SWT.NONE);
        GridLayout leftLayout = new GridLayout();
        leftLayout.numColumns = 1;
        top.setLayout(leftLayout);
        top.setLayoutData(new GridData(GridData.FILL_BOTH ));
        
        Composite middle = new Composite(control,SWT.NONE);
        GridLayout middleLayout = new GridLayout();
        middleLayout.numColumns = 4;
        middle.setLayout(middleLayout);
        middle.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

        Composite bottom = new Composite(control,SWT.NONE);
        GridLayout rightLayout = new GridLayout();
        rightLayout.numColumns = 1;
        bottom.setLayout(leftLayout);
        bottom.setLayoutData(new GridData(GridData.FILL_BOTH));
        
        //Top panel
        Composite head = new Composite(top,SWT.NONE);
        GridLayout headLayout = new GridLayout();
        headLayout.numColumns = 2;
        head.setLayout(headLayout);
        head.setLayoutData(new GridData(GridData.FILL_BOTH ));
        
        Label lblName  = new Label(head,SWT.None);
        lblName.setText("Group name:");
        txtName = new Text(head,SWT.BORDER);
        txtName.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        if(group!=null)
            txtName.setText(group.getName());
       
        members = new Table(top,SWT.MULTI|SWT.FULL_SELECTION|SWT.BORDER);
        members.setHeaderVisible(true);
        String[] titles = { "Name", "Descreption", "Context", "Query Expression", "Constraint Expression" };
        int[] widths = { 150, 50, 50, 50, 50 };
        for (int i = 0; i < titles.length; i++) {
            TableColumn column = new TableColumn(members, SWT.LEFT);
            column.setText(titles[i]);
            column.setWidth(widths[i]);
        }
        members.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL
                | GridData.VERTICAL_ALIGN_FILL | GridData.GRAB_VERTICAL
                |GridData.VERTICAL_ALIGN_BEGINNING));
        
        //Middle panel
        
        //Since CreateButton increases one coulmn of parent's layout, to keep the number of column not changed, we decrease one first
        middleLayout.numColumns --;
         
        this.createButton(middle, BTN_ID_AddToGroup, "^", false);
        middleLayout.numColumns --;
        this.createButton(middle, BTN_ID_AddAllToGroup, "^^^", false);
        middleLayout.numColumns --;
        this.createButton(middle, BTN_ID_RemoveFromGroup, "V", false);
        middleLayout.numColumns --;
        this.createButton(middle, BTN_ID_RemoveAllFromGroup, "VVV", false);
      
        //Bottom panel
        Label lblNonMembers  = new Label(bottom,SWT.None);
        lblNonMembers.setText("Other rules:");
        nonMembers = new Table(bottom,SWT.MULTI|SWT.FULL_SELECTION|SWT.BORDER);
        nonMembers.setHeaderVisible(true);
        for (int i = 0; i < titles.length; i++) {
            TableColumn column = new TableColumn(nonMembers, SWT.LEFT);
            column.setText(titles[i]);
            column.setWidth(widths[i]);
        }
        nonMembers.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.GRAB_HORIZONTAL 
                | GridData.VERTICAL_ALIGN_FILL | GridData.GRAB_VERTICAL
                |GridData.VERTICAL_ALIGN_BEGINNING));
       
        if(group!=null){
            List rules = group.getRules();
            for(int i=0;i<rules.size();++i)
            {
                appendRule(members,(Rule) rules.get(i));
            }
        }
        List allRules = StaticSemanticDefMgr.instance().getRules();
        for(int i=0;i<allRules.size();++i)
        {
            Rule r = (Rule) allRules.get(i);
            if(group==null || !group.contain(r)){
                appendRule(nonMembers,r);
            }
        }
        
        return control;
    }

    private void appendRule(Table table, Rule rule) {
        TableItem item = new TableItem (table, SWT.NONE);
        item.setData(rule);
        item.setText(new String[]{rule.getName(),rule.getDescription(),rule.getClassifier(),
                rule.getContext(),rule.getQuery()});
        
    }

    protected void okPressed() {
        if(txtName.getText().trim().length()==0)
        {
            MessageBox msg = new MessageBox(this.getShell(), SWT.ICON_ERROR);
            msg.setMessage("Group name should not be empty.");
            msg.setText("Invalidated group difinition");
            msg.open();
            return;
        }
        if( (group==null || group.getName().compareTo(txtName.getText())!=0) 
                && StaticSemanticDefMgr.instance().lookupGroup(txtName.getText())!=null)
        {
            MessageBox msg = new MessageBox(this.getShell(), SWT.ICON_ERROR);
            msg.setMessage("Group name duplicates");
            msg.setText("Invalidated group difinition");
            msg.open();
            return;
        }
        if(group==null)//create mode
        {
            group = StaticSemanticDefMgr.instance().creatRuelGroup(txtName.getText());                    
        }
        group.removeAll();
        TableItem[] items = members.getItems();
        for(int i=0;i<items.length;++i)
        {
            group.addRule((Rule) items[i].getData());
        }
        super.okPressed();
    }

    public void setGroup(RuleGroup group) {
        this.group = group;
    }

    protected void configureShell(Shell newShell) {
        // TODO Auto-generated method stub
        super.configureShell(newShell);
        if(group!=null)
            newShell.setText("Edit a group");
        else
            newShell.setText("Create a group");
    }

    protected void buttonPressed(int buttonId) {
        switch( buttonId)
        {
            case BTN_ID_AddToGroup:
                addSelectionToGroup();
                break;
            case BTN_ID_AddAllToGroup:
                addAllToGroup();
                break;
            case BTN_ID_RemoveFromGroup:
                removeSelectionFromGroup();
                break;
            case BTN_ID_RemoveAllFromGroup:
                removeAllFromGroup();
                break;
        }
            
        super.buttonPressed(buttonId);
    }

    private void removeAllFromGroup() {
        TableItem[] itmes = members.getItems();
        for(int i=0;i<itmes.length;++i)
        {
            appendRule(nonMembers,(Rule) itmes[i].getData());
        }
        members.removeAll();        
    }

 
    private void removeSelectionFromGroup() {
        TableItem[] itmes = members.getSelection();
        for(int i=0;i<itmes.length;++i)
        {
            appendRule(nonMembers,(Rule) itmes[i].getData());
        }
        members.remove(members.getSelectionIndices());        
    }

    private void addAllToGroup() {
        TableItem[] itmes = nonMembers.getItems();
        for(int i=0;i<itmes.length;++i)
        {
            appendRule(members,(Rule) itmes[i].getData());
        }
        nonMembers.removeAll();        
    }

    private void addSelectionToGroup() {
        TableItem[] itmes = nonMembers.getSelection();
        for(int i=0;i<itmes.length;++i)
        {
            appendRule(members,(Rule) itmes[i].getData());
        }
        nonMembers.remove(nonMembers.getSelectionIndices());        
    }

    public RuleGroup getGroup() {
        return group;
    }

}
