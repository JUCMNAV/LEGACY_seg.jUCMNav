package seg.jUCMNav.model.commands.create;

import grl.Evaluation;
import grl.EvaluationStrategy;
import grl.GRLGraph;
import grl.IntentionalElement;
import grl.IntentionalElementRef;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.commands.Command;

import seg.jUCMNav.Messages;
import seg.jUCMNav.model.commands.JUCMNavCommand;
import urn.URNspec;
import urncore.GRLmodelElement;

public class ShowEvaluationIntentionalElementCommand extends Command implements JUCMNavCommand
{
    private URNspec urnspec;
    private IntentionalElement grlelem;
    private IntentionalElementRef grlelemRef;
    private GRLGraph grlGraph;
    
    ArrayList<EvaluationStrategy> urnspecEvaluationStrategyList;
    //ArrayList<EvaluationStrategy> desiredEvaluationStrategyList;
    ArrayList<ArrayList<IntentionalElement>> positiveEvaluationIEList;
    ArrayList<IntentionalElementRef> existingIntentionalElementRefsList;
    ArrayList<IntentionalElement> existingIntentionalElementList;
  
    public ShowEvaluationIntentionalElementCommand(URNspec spec, EObject obj, IntentionalElementRef objRef) 
    {
        urnspec = spec;
      
        if (obj instanceof GRLmodelElement) 
        {
            this.grlelem = (IntentionalElement) obj;
            this.grlelemRef = (IntentionalElementRef) objRef;
            this.grlGraph = (GRLGraph) objRef.getDiagram();
            urnspecEvaluationStrategyList = new ArrayList<EvaluationStrategy>(urnspec.getGrlspec().getStrategies());
            existingIntentionalElementRefsList = new ArrayList<IntentionalElementRef>(grlGraph.getNodes());
            existingIntentionalElementList = new ArrayList<IntentionalElement>();
            
            for (IntentionalElementRef IER : existingIntentionalElementRefsList)
                existingIntentionalElementList.add(IER.getDef());
            
            if (urnspecEvaluationStrategyList.size() != 0)
                System.out.println("\n\nEvaluationSterategyList is not empty");
          
            System.out.println("In Command constructor for ShowEvaluationIntentionalElementCommand!!!");
          
            setLabel(Messages.getString("ActionRegistryManager.ShowEvaluationIntentionalElement")); //$NON-NLS-1$
        }
    }
    
    /**
     * @see org.eclipse.gef.commands.Command#canExecute()
     */
    public boolean canExecute() 
    {
        boolean executable = false;
        List <Evaluation> esList;
        List <IntentionalElement> ieList;
        positiveEvaluationIEList = new ArrayList<ArrayList<IntentionalElement>>();
        //desiredEvaluationStrategyList = new ArrayList<EvaluationStrategy>();
        
        for (EvaluationStrategy ES : urnspecEvaluationStrategyList)
            positiveEvaluationIEList.add(new ArrayList<IntentionalElement>());
                       
        if (urnspec != null && grlelem != null && urnspecEvaluationStrategyList.size() != 0)
        {
            for (int i = 0; i < urnspecEvaluationStrategyList.size(); i++)
            {
                //positiveEvaluationIEList.set(i, new ArrayList<IntentionalElement>());
                esList = new ArrayList<Evaluation>(urnspecEvaluationStrategyList.get(i).getEvaluations());
                ieList = new ArrayList<IntentionalElement>();
                              
                for (Evaluation e : esList)
                {
                    if (e.getEvaluation() > 0)
                        positiveEvaluationIEList.get(i).add(e.getIntElement());
                }
            }            
                        
            executable = true;
            
            System.out.println("positiveEvaluationIEList size is : " + positiveEvaluationIEList.size());            
        }
        
        //for (int i = 0; i < positiveEvaluationIEList.size(); i++)
            //for (int j = 0; j < positiveEvaluationIEList.get(i).size(); j++)
                //System.out.println("Strategy number " + i + " " + positiveEvaluationIEList.get(i).get(j));
        
        return executable;
    }
    
    /*
    **
    * @see org.eclipse.gef.commands.Command#execute()
    */
    public void execute() 
    {
        
        
        
       
        redo();   
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.commands.Command#redo()
     */
    public void redo() 
    {
        
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPostConditions()
     */
    public void testPostConditions() 
    {
        assert grlelem != null : "post no elemement to modify!"; //$NON-NLS-1$
        assert urnspec != null : "post no URN specification to modify!"; //$NON-NLS-1$
        assert grlGraph != null : "post no grl Grpah to modify"; //$NON-NLS-1$
        assert grlelemRef != null : "post no objRef to modify"; //$NON-NLS-1$        
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.model.commands.JUCMNavCommand#testPreConditions()
     */
    public void testPreConditions() 
    {
        assert grlelem != null : "pre no elemement to modify!"; //$NON-NLS-1$
        assert urnspec != null : "pre no URN specification to modify!"; //$NON-NLS-1$
        assert grlGraph != null : "pre no grl Grpah to modify"; //$NON-NLS-1$
        assert grlelemRef != null : "pre no objRef to modify"; //$NON-NLS-1$        
    }
}
