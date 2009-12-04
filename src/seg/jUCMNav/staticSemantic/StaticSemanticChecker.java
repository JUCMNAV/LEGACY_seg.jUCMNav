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

import seg.jUCMNav.Messages;
import seg.jUCMNav.rulemanagement.Rule;
import seg.jUCMNav.rulemanagement.RuleManagementCheckingMessage;
import seg.jUCMNav.rulemanagement.RuleManagementDefinitionManager;
import urn.URNspec;
import urn.UrnPackage;

/**
 * This class encapsulates the rule checking action.
 * 
 * @author Byrne Yan
 * 
 */
public class StaticSemanticChecker {

    private static StaticSemanticChecker instance = null;

    /**
     * Returns the singleton instance of StaticSemanticChecker
     */
    public static StaticSemanticChecker getInstance() {
        if (instance == null) {
            instance = new StaticSemanticChecker();
        }
        return instance;
    }

    /**
     * Prevent the StaticSemanticChecker from being created outside the class
     */
    private StaticSemanticChecker() {
    }

    /**
     * Check all activated rules on the specified the URNSpec object. All violations and a checking summary are saved in the problems.
     * 
     * @param urn
     *            a URNSpec object
     * @param problems
     *            a vector which holds rule violations
     * @return true if no rule is violated, otherwise false
     * @see StaticSemanticDefMgr
     */
    public boolean check(URNspec urn, Vector problems) {
        boolean res = true;
        int nTotal = 0;
        int nViolated = 0;
        FileInputStream in = null;
        try {
            // OCL<?, EClassifier, EOperation, ?, ?, ?, ?, ?, ?, Constraint, EClass, EObject> ocl;
            OCL ocl;

            ocl = OCL.newInstance(EcoreEnvironmentFactory.INSTANCE);
            // OCLInput lib = new OCLInput(this.getClass().getResourceAsStream("library.ocl")); //$NON-NLS-1$
            OCLInput lib = new OCLInput(RuleManagementDefinitionManager.class.getResourceAsStream("library.ocl")); //$NON-NLS-1$
            ocl.parse(lib);
            // OCLHelper<EClassifier, EOperation, ?, Constraint> helper = ocl.createOCLHelper();
            OCLHelper helper = ocl.createOCLHelper();
            List rules = StaticSemanticDefMgr.instance().getRules();
            for (int i = 0; i < rules.size(); ++i) {
                Rule r = (Rule) rules.get(i);
                if (r.isEnabled()) {
                    nTotal++;
                    try {
                        List name = r.getClassifierAsList();
                        EClassifier e = (EClassifier) ocl.getEnvironment().lookupClassifier(name);
                        if (e == null) {
                            String s = Messages.getString("StaticSemanticChecker.Rule") + r.getName() + Messages.getString("StaticSemanticChecker.ClassifierNotFound") + r.getClassifier() + "\""; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                            problems.add(new RuleManagementCheckingMessage(s));
                        } else {

                            for (int k = 0; k < r.getUtilities().size(); ++k) {
                                String op = (String) r.getUtilities().get(k);
                                helper.setContext(e);
                                helper.defineOperation(op);
                            }
                            helper.setContext(UrnPackage.Literals.UR_NSPEC);
                            OCLExpression query = helper.createQuery(r.getContext() + "->asSequence()"); //$NON-NLS-1$
                            Query queryEval = ocl.createQuery(query);

                            List objects = (List) queryEval.evaluate(urn);

                            helper.setContext(e);

                            Constraint invariant = (Constraint) helper.createInvariant(r.getQuery());

                            Query constraintEval = ocl.createQuery(invariant);

                            List violatedObjs = constraintEval.reject(objects);
                            for (int k = 0; k < violatedObjs.size(); ++k) {
                                EObject o = (EObject) violatedObjs.get(k);
                                res = false;
                                String s = ""; //$NON-NLS-1$
                                if (StaticSemanticDefMgr.instance().isShowDesc()) {
                                    s = r.getDescription() + " (" + r.getName() + ")"; //$NON-NLS-1$ //$NON-NLS-2$
                                } else {
                                    s = r.getDescription();
                                }
                                problems.add(new RuleManagementCheckingMessage(s, o, r.getWarningOnly()));
                            }
                            if (violatedObjs.size() > 0)
                                nViolated++;
                        }
                    } catch (ParserException e) {
                        String s = Messages.getString("StaticSemanticChecker.ParseError") + e.getLocalizedMessage(); //$NON-NLS-1$
                        s += "\n\t" + r.getContext(); //$NON-NLS-1$
                        s += "\n\t" + r.getQuery(); //$NON-NLS-1$
                        problems.add(new RuleManagementCheckingMessage(s));
                    }
                }
            }
        } catch (Exception e) {
            problems.add(new RuleManagementCheckingMessage(e.getLocalizedMessage()));
        }

        // A summary information message is added
        String sumMsg = String.valueOf(nTotal)
                + Messages.getString("StaticSemanticChecker.RulesChecked") + nViolated + Messages.getString("StaticSemanticChecker.Violated"); //$NON-NLS-1$ //$NON-NLS-2$
        RuleManagementCheckingMessage summary = new RuleManagementCheckingMessage(sumMsg);
        summary.setInfo();
        problems.insertElementAt(summary, 0);
        return res;
    }
}
