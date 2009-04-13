package seg.jUCMNav.metrics;

import java.io.FileInputStream;
import java.util.List;
import java.util.Vector;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.ocl.OCL;
import org.eclipse.ocl.OCLInput;
import org.eclipse.ocl.Query;
import org.eclipse.ocl.ecore.Constraint;
import org.eclipse.ocl.ecore.EcoreEnvironmentFactory;
import org.eclipse.ocl.expressions.OCLExpression;
import org.eclipse.ocl.helper.OCLHelper;

import seg.jUCMNav.Messages;
import seg.jUCMNav.rulemanagement.Rule;
import seg.jUCMNav.rulemanagement.RuleManagementCheckingMessage;
import seg.jUCMNav.rulemanagement.RuleManagementDefinitionManager;
import urn.URNspec;
import urn.UrnPackage;

/**
 * 
 * @author Anisur Rahman
 *
 */
public class MetricsCalculator {
	private static MetricsCalculator instance;
	
	public static MetricsCalculator getInstance(){
		if(instance == null){
			instance = new MetricsCalculator();
		}
		return instance;
	}
	
	private MetricsCalculator(){		
	}
	
	public void calculate(URNspec urn, Vector result){
		int nTotal = 0;
		FileInputStream in = null;
		try{
			List rules = MetricsDefinitionManager.instance().getRules();
			for(int i=0; i<rules.size();i++){
				Rule r = (Rule)rules.get(i);
				if(r.isEnabled()){
					nTotal++;
					try{
						OCL ocl = OCL.newInstance(EcoreEnvironmentFactory.INSTANCE);
						OCLInput lib = new OCLInput(RuleManagementDefinitionManager.class.getResourceAsStream("library.ocl")); //$NON-NLS-1$
	                    ocl.parse(lib);
	                    OCLHelper helper = ocl.createOCLHelper();
	                    List name = r.getClassifierAsList();
	                    EClassifier e = (EClassifier) ocl.getEnvironment().lookupClassifier(name);
                        if (e == null) {
                            String s = Messages.getString("MetricsCalculator.Rule") + r.getName() + Messages.getString("MetricsCalculator.ClassifierNotFound") + r.getClassifier() + "\""; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                            result.add(new RuleManagementCheckingMessage(s));
                        } else {

                            for(int k=0;k<r.getUtilities().size();++k)
                            {
                                String op = (String) r.getUtilities().get(k);
                                helper.setContext(e);
                                helper.defineOperation(op);
                            }
                            helper.setContext(UrnPackage.Literals.UR_NSPEC);

                           // OCLExpression query = helper.createQuery(r.getContext()+"->asSequence()"); //$NON-NLS-1$
                            OCLExpression query = helper.createQuery(r.getContext()+""); //$NON-NLS-1$
                            System.out.println("Rule query: " + r.getContext()+"->asSequence()");
                            Query queryEval = ocl.createQuery(query);

 //                         @SuppressWarnings("unchecked")
           //                 List objects = (List) queryEval.evaluate(urn);
                            Object o =  queryEval.evaluate(urn);

                            helper.setContext(e);

                            Constraint invariant = (Constraint) helper.createInvariant(r.getQuery());
                            String s = "";
                            
                           // s = r.getName()+ " : " + objects.size();
                            if(o instanceof Integer){
                            	s = r.getName()+ " : " + ((Integer)o).intValue();
                            }else if(o instanceof Double){
                            	s = r.getName()+ " : " + ((Double)o).doubleValue();
                            }else if(o instanceof Long){
                            	s = r.getName()+ " : " + ((Long)o).longValue();
                            }else if(o instanceof Short){
                            	s = r.getName()+ " : " + ((Short)o).shortValue();
                            }else if(o instanceof Float){
                            	s = r.getName()+ " : " + ((Float)o).floatValue();
                            }else if(o instanceof String){
                            	s = r.getName()+ " : " + o;                           
                            }else if (o != null){
                            	s = r.getName()+ " : " + o.toString();
                            }  else{                          
                            	s = r.getName()+ " : " ;
                            }
                            
                           // result.add(new RuleManagementCheckingMessage(s, null));
                            RuleManagementCheckingMessage message = new RuleManagementCheckingMessage(s, null);
                            message.setInfo();
                            result.add(message);
                           
                            Query constraintEval = ocl.createQuery(invariant);

                          //  List violatedObjs = constraintEval.reject(objects);
/*                            
                            for (int k=0;k< violatedObjs.size();++k) {
                                EObject o = (EObject) violatedObjs.get(k);
                               // res = false;
                                String s = ""; //$NON-NLS-1$
                                if(StaticSemanticDefMgr.instance().isShowDesc()){
                                    s = r.getDescription()+" ("+r.getName()+")"; //$NON-NLS-1$ //$NON-NLS-2$
                                }else
                                {
                                    s = r.getName();
                                }
                                result.add(new RuleManagementCheckingMessage(s, o));
                            }
                            if (violatedObjs.size()>0) nViolated++;
 */                           
                        }
						
					}catch(//Parser
							Exception e){
						String s = Messages.getString("MetricsCalculator.ParseError") + e.getLocalizedMessage(); //$NON-NLS-1$
                        s += "\n\t" + r.getContext(); //$NON-NLS-1$
                        s += "\n\t" + r.getQuery(); //$NON-NLS-1$
                        result.add(new RuleManagementCheckingMessage(s));
						
					}
					
				}
				
			}
			
		}catch (Exception e){
			result.add(new RuleManagementCheckingMessage(e.getLocalizedMessage()));
			
		}
		
		//add a summary message here if required
		
	}
	

}
