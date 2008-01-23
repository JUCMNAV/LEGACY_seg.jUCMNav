package seg.jUCMNav.staticSemantic;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;

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
            for (Rule r : StaticSemanticDefMgr.loadDefinitions()) {
                if (r.isEnabled()) {
                    nTotal++;
                    try {
                        OCL<?, EClassifier, EOperation, ?, ?, ?, ?, ?, ?, Constraint, EClass, EObject> ocl;
                        ocl = OCL.newInstance(EcoreEnvironmentFactory.INSTANCE);
                        OCLHelper<EClassifier, EOperation, ?, Constraint> helper = ocl.createOCLHelper();
                        List<String> name = r.getClassifierAsList();
                        EClassifier e = ocl.getEnvironment().lookupClassifier(name);
                        if (e == null) {
                            String s = "Rule <" + r.getName() + ">: Classifier can't be found:\"" + r.getClassifier() + "\"";
                            problems.add(new StaticCheckingMsg(s));
                        } else {

                            for(String op: r.getUtilities())
                            {
                                helper.setContext(e);
                                helper.defineOperation(op);
                            }
                            helper.setContext(UrnPackage.Literals.UR_NSPEC);
                            OCLExpression<EClassifier> query = helper.createQuery(r.getContext());
                            Query<EClassifier, EClass, EObject> queryEval = ocl.createQuery(query);

                            @SuppressWarnings("unchecked")
                            List<EObject> objects = (List<EObject>) queryEval.evaluate(urn);

                            helper.setContext(e);

                            Constraint invariant = helper.createInvariant(r.getQuery());

                            Query<EClassifier, EClass, EObject> constraintEval = ocl.createQuery(invariant);

                            List<EObject> violatedObjs = constraintEval.reject(objects);
                            for (EObject o : violatedObjs) {
                                res = false;
                                String s = r.getName();
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
