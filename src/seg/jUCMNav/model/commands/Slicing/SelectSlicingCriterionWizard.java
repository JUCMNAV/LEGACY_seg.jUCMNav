package seg.jUCMNav.model.commands.Slicing;
import java.util.ArrayList;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;

import seg.jUCMNav.actions.StaticSlicingAction;

public class SelectSlicingCriterionWizard extends Wizard{
	IWorkbenchPage workbench;
	IStructuredSelection selection;
	ArrayList<String> listItems;
	String criterion_Name;
 public	Boolean Removetype;
	public String fileName;
	
	private ArrayList <String> selected=new ArrayList<String>();
	 public SelectSlicingCriterionWizard(ArrayList<String> listItems,String criterionName) {

		    super();
		    this.criterion_Name=criterionName;
		    this.listItems=listItems;
		    getSelectedVariables().clear();
		    setNeedsProgressMonitor(true);
		    

		  }

	
	@Override
	public boolean performFinish() {
		
		StaticSlicingAction.dialogFinished=true;
		SelectCriterionPage pg= (SelectCriterionPage) getPage(SelectCriterionPage.PAGE_NAME);
		if(listItems!=null && pg.selectedList!=null && pg.selectedList.getItemCount()>0)
		for(String item:pg.selectedList.getItems())
			
			getSelectedVariables().add(item);
		else
			selected=null;
		Removetype=pg.RemoveType;
		fileName=pg.fileName;
		return true;
	}

	@Override
	public boolean performCancel(){
		StaticSlicingAction.dialogFinished=false;
		return true;
	}
	
	 @Override

	  public void addPages() {
		 SelectCriterionPage criterionpage;
		
		  criterionpage=new SelectCriterionPage(listItems,criterion_Name);
		 
		 addPage(criterionpage);
	 }
	 @Override

	  public String getWindowTitle() {

	    return "UCM Static Slicing Options";

	  }
/*public void init(IWorkbench workbench, IStructuredSelection selection)
{
	this.workbench=workbench.getActiveWorkbenchWindow().getActivePage();
	this.selection=selection;
}*/


	public ArrayList <String> getSelectedVariables() {
		return selected;
	}


	public void setSelectedVariables(ArrayList <String> selected) {
		this.selected = selected;
	}
}
