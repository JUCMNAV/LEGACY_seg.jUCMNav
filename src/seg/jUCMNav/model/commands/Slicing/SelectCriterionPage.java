package seg.jUCMNav.model.commands.Slicing;
import java.util.ArrayList;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.List;
public class SelectCriterionPage extends WizardPage  {
   Boolean RemoveType=true;
   String fileName;
  
	private Composite container;
	ArrayList<String> listItems=new ArrayList<String>();
	String criterion_name;
	List variables,selectedList;
	public static final String PAGE_NAME = "CriterionSelection";
	protected SelectCriterionPage(ArrayList<String> listItems, String criterion_name) {
		
		super(PAGE_NAME,"SelectCriterionPage",null);
		
		if(listItems!=null)
		{
		setTitle("Selection of the Slicing Criteria");

	    setDescription("The slicing algorithm computes dependencies with respect to zero, one or many variables");
	    this.listItems=listItems;
        this.criterion_name=criterion_name;
	   
		}
	    else
	    {
	    	setTitle("Selection of the Slicing Criteria");

		    setDescription("The slicing algorithm computes dependencies with respect to zero, one or many variables");
		    		
	    	
	    }
		setPageComplete(false);	
	}
	 @Override

	  public void createControl(Composite parent) {

		 final Button btn_moveToSelected;
		 final Button btn_moveFromSelected;
		 GridData grd;
	    container = new Composite(parent, SWT.NONE);
	 
	   GridLayout gr=new GridLayout();
	   gr.numColumns=3;
	   gr.horizontalSpacing=30;
	   gr.marginHeight=5;
	    container.setLayout(gr);
	    
	    Label lblselect = new Label(container, SWT.NONE);

	    lblselect.setText("Variables within "+criterion_name);
	    Label lblmove = new Label(container, SWT.NONE);

	    lblmove.setText("");
	    Label lblselected = new Label(container, SWT.NONE);

	    lblselected.setText("Selected variables");

//first place the variable list
	    //declare the list of available variables 
		variables=new List(container, SWT.MULTI | SWT.BORDER | SWT.V_SCROLL);
		
		
		//fill the list with variables,if it's null mean no variables are available
		//if so disable the lists along with buttons 
		if(listItems!=null)
		for(String item:listItems)
			variables.add(item);
		grd=new GridData(SWT.FILL, SWT.FILL, false, true);
		
	    grd.verticalSpan=2;
		
			
		variables.setLayoutData(grd);
	
		//add the ">" button
	 btn_moveToSelected=new Button(container, SWT.PUSH);
	
	btn_moveToSelected.setText(">");
	grd=new GridData(SWT.BORDER,SWT.BORDER,false,false);
	
	 btn_moveToSelected.setLayoutData(grd);
	 btn_moveToSelected.setEnabled(false);
	 // third, add the selected list
	 selectedList=new List(container, SWT.MULTI | SWT.BORDER | SWT.V_SCROLL);
	 grd=new GridData(SWT.FILL, SWT.FILL, true, true);
	 grd.verticalSpan=2;
	 	selectedList.setLayoutData(grd);
		if(listItems==null)
			selectedList.setEnabled(false);
		
		// add the < button
	  btn_moveFromSelected=new Button(container, SWT.PUSH);
	btn_moveFromSelected.setText("<");
	grd=new GridData(SWT.BORDER,SWT.BORDER,false,false);
	
	btn_moveFromSelected.setLayoutData(grd);
	btn_moveFromSelected.setEnabled(false);
	setControl(container);
	
    lblmove.setText("");
    
	//*******************************
	
	Group group=new Group(container, SWT.SHADOW_ETCHED_IN);
    group.setText("Output");
    GridLayout gr2=new GridLayout();
	   gr2.numColumns=1;
	   gr2.horizontalSpacing=30;
       gr2.marginHeight=0;
	   group.setLayout(gr2);
	   
	//add color radio button
 	final Button btn_color = new Button(group, SWT.RADIO);
 	btn_color.setText("Mark the original UCM");
     grd = new GridData(GridData.FILL_HORIZONTAL);
     grd.horizontalSpan = 1;
     btn_color.setLayoutData(grd);
     btn_color.setSelection(false);
    
	//add remove radio button
		
	   final Button btn_remove = new Button(group, SWT.RADIO);
		btn_remove.setText("Create a reduced UCM (choose a file name)");
	    grd = new GridData(GridData.FILL_HORIZONTAL);
	    grd.horizontalSpan =1;
	    btn_remove.setLayoutData(grd);
	    btn_remove.setSelection(true);
	    
	    /*add the label with text of the file name
	    Label lbl_FileName=new Label(container, SWT.NONE);
	    lbl_FileName.setText("File Name");
	    */
	    
	   final Text txt_fileName = new Text(group, SWT.BORDER);
	   grd = new GridData(GridData.FILL_HORIZONTAL);
	   // grd.verticalSpan=2;
	  
	    txt_fileName.setLayoutData(grd);
	txt_fileName.setText("");
	    fileName=txt_fileName.getText();
		
	    //******************Add Listeners***************************
	  
	    if(listItems!=null)
	    {
		variables.addSelectionListener(new SelectionListener() {
		     		@Override
			public void widgetSelected(SelectionEvent e) {
				if(variables.getSelectionIndices().length>0)
			btn_moveToSelected.setEnabled(true);
				btn_moveFromSelected.setEnabled(false);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		      });
		 //event when selecting new item and move it to the selected list
		btn_moveToSelected.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				String[] selected=variables.getSelection();
				for(String item:selected )
				{
					selectedList.add(item);
					variables.remove(item);
					
				}
			btn_moveToSelected.setEnabled(false);
			btn_moveFromSelected.setEnabled(false);
			//if(btn_color.getSelection() ||(btn_remove.getSelection() && (!txt_fileName.getText().isEmpty() || !txt_fileName.getText().equals("") )))		
			//setPageComplete(true);
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		//Listener for selected list
		selectedList.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(selectedList.getSelectionCount()>0)
					btn_moveFromSelected.setEnabled(true);
				btn_moveToSelected.setEnabled(false);
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		//when moving an item from selected list
	btn_moveFromSelected.addSelectionListener(new SelectionListener() {
		
		@Override
		public void widgetSelected(SelectionEvent e) {
			String[] selected=selectedList.getSelection();
			for(String item:selected )
			{
				selectedList.remove(item);
				variables.add(item);
			}
			btn_moveFromSelected.setEnabled(false);
			btn_moveToSelected.setEnabled(false);
			//if there no items in the selected, finish button will be diabled
			//if(selectedList.getItemCount()<1)
				//setPageComplete(false);
		}
		
		@Override
		public void widgetDefaultSelected(SelectionEvent e) {
			// TODO Auto-generated method stub
			
		}
	});

	 }
    btn_color.addSelectionListener(new SelectionListener() {
		
		@Override
		public void widgetSelected(SelectionEvent e) {
			// TODO Auto-generated method stub
		
			if(btn_color.getSelection())
			{
				txt_fileName.setEnabled(false);
                 RemoveType=false;
                 setPageComplete(true);	
			}
		}
		
		@Override
		public void widgetDefaultSelected(SelectionEvent e) {
			// TODO Auto-generated method stub
			
		}
	});
    
    btn_remove.addSelectionListener(new SelectionListener() {
		
		@Override
		public void widgetSelected(SelectionEvent e) {
			// TODO Auto-generated method stub
			if(btn_remove.getSelection())
			{
				RemoveType=true;
				txt_fileName.setEnabled(true);
				//txt_fileName.setText("");
				 fileName=txt_fileName.getText();
				 if(fileName==null || fileName.isEmpty())
				 setPageComplete(false);	
			}
		}
		
		@Override
		public void widgetDefaultSelected(SelectionEvent e) {
			// TODO Auto-generated method stub
			
		}
	});
	    
    txt_fileName.addSelectionListener(new SelectionListener() {
		
		@Override
		public void widgetSelected(SelectionEvent e) {
			// TODO Auto-generated method stub
			if(txt_fileName.getText().isEmpty() || txt_fileName.getText()=="" || txt_fileName.getText()==null)
			{
				setPageComplete(false);
			}
			else
			{
				setPageComplete(true);
				 fileName=txt_fileName.getText();
			}
		}
		
		@Override
		public void widgetDefaultSelected(SelectionEvent e) {
			// TODO Auto-generated method stub
			
		}
	});
    
    txt_fileName.addModifyListener(new ModifyListener() {
		
		@Override
		public void modifyText(ModifyEvent e) {
			// TODO Auto-generated method stub
			if(txt_fileName.getText().isEmpty() || txt_fileName.getText()=="" || txt_fileName.getText()==null)
			{
				btn_remove.setText("Create a reduced UCM (choose a file name)");
				setPageComplete(false);
				 fileName=txt_fileName.getText();
			}
			else
			{
				btn_remove.setText("Create a reduced UCM (choose a file name)");
				setPageComplete(true);
				 fileName=txt_fileName.getText();
			}
		}
	});
	 }
	
}
