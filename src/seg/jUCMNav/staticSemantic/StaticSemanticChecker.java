package seg.jUCMNav.staticSemantic;

import java.io.FileInputStream;
import java.util.List;
import java.util.Vector;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.ocl.OCL;
import org.eclipse.ocl.OCLInput;
import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.Query;
import org.eclipse.ocl.ecore.Constraint;
import org.eclipse.ocl.ecore.EcoreEnvironmentFactory;
import org.eclipse.ocl.expressions.OCLExpression;
import org.eclipse.ocl.helper.OCLHelper;
import urn.*;

public class StaticSemanticChecker {

    // static MessageConsoleStream msg;
    private static StaticSemanticChecker instance = null;

    public static StaticSemanticChecker getInstance() {
        if (instance == null) {
            instance = new StaticSemanticChecker();
        }
        return instance;
    }

    private StaticSemanticChecker() {
    }

    public boolean check(URNspec urn, Vector problems) {
        boolean res = true;
        int nTotal = 0;
        int nViolated = 0;
        FileInputStream in = null;
        try {
            Rule[] rules = StaticSemanticDefMgr.loadDefinitions();
            for (int i=0;i<rules.length;++i) {
                Rule r = rules[i];
                if (r.isEnabled()) {
                    nTotal++;
                    try {
//                        OCL<?, EClassifier, EOperation, ?, ?, ?, ?, ?, ?, Constraint, EClass, EObject> ocl;
                        OCL ocl;

                        ocl = OCL.newInstance(EcoreEnvironmentFactory.INSTANCE);
                        OCLInput lib = new OCLInput(this.getClass().getResourceAsStream("library.ocl"));
                        ocl.parse(lib);
//                        OCLHelper<EClassifier, EOperation, ?, Constraint> helper = ocl.createOCLHelper();
                        OCLHelper helper = ocl.createOCLHelper();
                        List name = r.getClassifierAsList();
                        EClassifier e = (EClassifier) ocl.getEnvironment().lookupClassifier(name);
                        if (e == null) {
                            String s = "Rule <" + r.getName() + ">: Classifier can't be found:\"" + r.getClassifier() + "\"";
                            problems.add(new StaticCheckingMsg(s));
                        } else {

                            for(int k=0;k<r.getUtilities().size();++k)
                            {
                                String op = (String) r.getUtilities().get(k);
                                helper.setContext(e);
                                helper.defineOperation(op);
                            }
                            helper.setContext(UrnPackage.Literals.UR_NSPEC);
/*                            OCLExpression<EClassifier> query = helper.createQuery(r.getContext());
                            Query<EClassifier, EClass, EObject> queryEval = ocl.createQuery(query);
*/
                            OCLExpression query = helper.createQuery(r.getContext());
                            Query queryEval = ocl.createQuery(query);

//                            @SuppressWarnings("unchecked")
                            List objects = (List) queryEval.evaluate(urn);

                            helper.setContext(e);

                            Constraint invariant = (Constraint) helper.createInvariant(r.getQuery());

//                            Query<EClassifier, EClass, EObject> constraintEval = ocl.createQuery(invariant);
                            Query constraintEval = ocl.createQuery(invariant);

                            List violatedObjs = constraintEval.reject(objects);
                            for (int k=0;k< violatedObjs.size();++k) {
                                EObject o = (EObject) violatedObjs.get(k);
                                res = false;
                                String s = "";
                                if(StaticSemanticDefMgr.isShowDesc()){
                                    s = r.getDescription()+"("+r.getName()+")";
                                }else
                                {
                                    s = r.getName();
                                }
                                problems.add(new StaticCheckingMsg(s, o));
                            }
                            if (violatedObjs.size()>0) nViolated++;
                        }
                    } catch (ParserException e) {
                        String s = "Parse OCL expression error:" + e.getLocalizedMessage();
                        s += "\n\t" + r.getContext();
                        s += "\n\t" + r.getQuery();
                        problems.add(new StaticCheckingMsg(s));
                    }
                }
            }
        } catch (Exception e) {
            problems.add(new StaticCheckingMsg(e.getLocalizedMessage()));
        }
        //A summary information message is added
        String sumMsg = String.valueOf(nTotal) + " rules are checked totally."+ nViolated + " of them is/are violated.";
        StaticCheckingMsg summary = new StaticCheckingMsg(sumMsg);
        summary.setInfo();
        problems.insertElementAt(summary, 0);
        return res;
    }
}
