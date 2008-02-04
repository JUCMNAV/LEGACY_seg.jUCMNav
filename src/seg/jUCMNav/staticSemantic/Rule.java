package seg.jUCMNav.staticSemantic;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.ocl.OCL;
import org.eclipse.ocl.OCLInput;
import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.Query;
import org.eclipse.ocl.ecore.Constraint;
import org.eclipse.ocl.ecore.EcoreEnvironmentFactory;
import org.eclipse.ocl.expressions.OCLExpression;
import org.eclipse.ocl.helper.OCLHelper;

import urn.UrnPackage;

public class Rule {
	private String name;
	private String context;
	private String classifier;
	private String query;
	private String description;
	private List utilities;
	private boolean    enabled;
	private String errors;
	public Rule()
	{
        this.context = "";
        this.classifier = "";
        this.query = "";
        this.name = "";
        this.enabled = false;
        this.description = "";
        this.errors = "";
        this.utilities = new ArrayList() ;
        }
	/**
	 * @param context	OCL expression that returns a Sequence of a classifier specified in the second parameter 
	 * @param classifier	qualified name path, for example, Package::SubPackage:ClassName
	 * @param query		OCL expression that describes an invariant in the context specified in the second parameter
	 */
	public Rule(String name,String classifier,String context, String query,boolean enabled, String description)
	{
		this.context = context;
		this.classifier = classifier;
		this.query = query;
		this.name = name;
		this.enabled = enabled;
		this.description = description;
		this.errors = "";
		this.utilities = new ArrayList() ;
	}

	public void addUtility(String untility)
	{
	    utilities.add(untility);
	}
	public String getContext() {
		return context;
	}
	public String getQuery() {
		return query;
	}
	public String getClassifier() {
		return classifier;
	}
	public String getName()
	{
		return name;
	}
	public List getClassifierAsList()
	{
		ArrayList array = new ArrayList();
		StringTokenizer tok = new StringTokenizer(classifier,"::");
		while (tok.hasMoreTokens()) {
	         array.add(tok.nextToken());
	     }
		return array;
	}
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setContext(String context) {
        this.context = context;
    }
    public void setClassifier(String classifier) {
        this.classifier = classifier;
    }
    public void setQuery(String query) {
        this.query = query;
    }
    public List getUtilities() {
        return utilities;
    }	
    public boolean isValid()
    {
     
//        OCL<?, EClassifier, EOperation, ?, ?, ?, ?, ?, ?, Constraint, EClass, EObject> ocl;
        OCL ocl;
        ocl = OCL.newInstance(EcoreEnvironmentFactory.INSTANCE);
        OCLInput lib = new OCLInput(this.getClass().getResourceAsStream("library.ocl"));
        try {
            ocl.parse(lib);
        } catch (ParserException e2) {
            errors+= "Library error:" + e2.getMessage();
            return false;
        }
//        OCLHelper<EClassifier, EOperation, ?, Constraint> helper = ocl.createOCLHelper();
        OCLHelper helper = ocl.createOCLHelper();
        //verify context
        List name = this.getClassifierAsList();
        EClassifier e = (EClassifier) ocl.getEnvironment().lookupClassifier(name);
        if (e == null) {
            errors += "Classifier " + this.getClassifier() + " is invalid.";
            return false;
        }        
        //verify utilities
        for(int i =0; i<this.getUtilities().size();++i)
        {
            String op =(String) this.getUtilities().get(i); 
            try {
                helper.setContext(e);
                helper.defineOperation(op);
            } catch (ParserException e1) {
                errors+= "Utilities #"+ (i+1)+" error:" + e1.getMessage();
                return false;
            }           
        }
        //verify query constraint
        try {
            helper.setContext(UrnPackage.Literals.UR_NSPEC);
//            OCLExpression<EClassifier> query;
            OCLExpression query;
            query = helper.createQuery(this.getContext());
//            Query<EClassifier, EClass, EObject> queryEval = ocl.createQuery(query);
            Query queryEval = ocl.createQuery(query);
        } catch (ParserException e1) {
            errors+="Query constraint error:" + e1.getMessage();
            return false;
        }
        //verify incariant constraint
        try {
            helper.setContext(e);
/*            Constraint invariant = helper.createInvariant(this.getQuery());
            Query<EClassifier, EClass, EObject> constraintEval = ocl.createQuery(invariant);
*/
            Constraint invariant = (Constraint) helper.createInvariant(this.getQuery());
            Query constraintEval = ocl.createQuery(invariant);
            
        } catch (ParserException e1) {
            errors+= "Invariant contraint error:" + e1.getMessage();
            return false;
        }
  
        return true;
    }
    public String getErrors() {
        return errors;
    }
}
