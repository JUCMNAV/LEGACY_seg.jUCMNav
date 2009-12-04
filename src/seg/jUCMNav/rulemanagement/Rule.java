package seg.jUCMNav.rulemanagement;

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

/**
 * This class represents a static checking rule. The rule contains 7 properties:
 * <ul>
 * <li><b>The rule name</b>
 * <li><b>The rule classifier</b>, which is a package name followed by the class name in the form of: packagename::classname,for example,
 * urncore::Responsibility.
 * <li><b>The OCL context expression</b>, which is an OCL query expression under the context of URNspec and must return a sequence of objects which have a type
 * specified in the <b>rule classifier</b>. Notice, allInstance() cannot be used.
 * <li><b>The OCL invariant expression</b>, which is under the context of the rule classifier and allInstance() cannot be used.
 * <li><b>The rule description</b>, which gives a brief explanation about the rule.
 * <li><b>The rule enabled/disabled indicator</b>, which is true if enabled, otherwise false.
 * <li><b>The rule utility definitions</b>. A utility is an additional operation used in <b>the rule invariant expression</b> and the format is as same as that
 * in defining an additional operation in an OCL document. The utility is defined under the context of the <b>rule classifier</b>.
 * <li><b>The rule error level</b>, which indicates whether this should be an Error (false) or a Warning (true)
 * </ul>
 * 
 * @author Byrne Yan
 * 
 */
public class Rule {
    /**
     * The rule name
     */
    private String name;
    /**
     * The OCL context expression
     */
    private String context;
    /**
     * The rule classifier
     */
    private String classifier;
    /**
     * The OCL invariant expression
     */
    private String query;
    /**
     * The rule description
     */
    private String description;
    /**
     * The rule utilities
     */
    private List utilities = new ArrayList();
    /**
     * The rule enabled/disabled indicator
     */
    private boolean enabled;
    /**
     * Indicates if the rule should generate an error or a warning
     */
    private boolean warningOnly = false;
    /**
     * Holds error messages found when checking the validation of the rule.
     */
    private String errors;

    /**
     * Construct a rule with the rule name and the rule is disabled and all other fields are empty.
     * 
     * @param name
     *            The rule name
     */
    public Rule(String name) {
        this.context = "urn::URNspec"; //$NON-NLS-1$
        this.classifier = ""; //$NON-NLS-1$
        this.query = ""; //$NON-NLS-1$
        this.name = name;
        this.enabled = false;
        this.warningOnly = false;
        this.description = ""; //$NON-NLS-1$
        this.errors = ""; //$NON-NLS-1$
        this.utilities = new ArrayList();
    }

    /**
     * Construct a rule with properties except utilities.
     * 
     * @param name
     *            The rule name
     * @param classifier
     *            The rule classifier
     * @param context
     *            The rule context expression
     * @param query
     *            The rule invariant expression
     * @param enabled
     *            The enabled/disabled indicator
     * @param warningOnly
     *            The error/warning indicator
     * @param description
     *            The rule description
     */
    public Rule(String name, String classifier, String context, String query, boolean enabled, boolean warningOnly, String description) {
        this.context = context;
        this.classifier = classifier;
        this.query = query;
        this.name = name;
        this.enabled = enabled;
        this.warningOnly = warningOnly;
        this.description = description;
        this.errors = ""; //$NON-NLS-1$
        this.utilities = new ArrayList();
    }

    /**
     * Add a utility
     * 
     * @param utility
     *            A string of utility definition
     */
    public void addUtility(String utility) {
        utilities.add(utility);
    }

    /**
     * Returns the context expression
     */
    public String getContext() {
        return context;
    }

    /**
     * Returns the invariant expression
     */
    public String getQuery() {
        return query;
    }

    /**
     * Returns the rule classifier
     */
    public String getClassifier() {
        return classifier;
    }

    /**
     * Returns the rule name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns a list of names, which are separated from <b>the rule classifier</b> by delimiter '::'.
     */
    public List getClassifierAsList() {
        ArrayList array = new ArrayList();
        StringTokenizer tok = new StringTokenizer(classifier, "::"); //$NON-NLS-1$
        while (tok.hasMoreTokens()) {
            array.add(tok.nextToken());
        }
        return array;
    }

    /**
     * Returns the rule description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the rule description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Query if the rule is activated or not
     * 
     * @return true if activated, otherwise false.
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * Enable/disable the rule
     * 
     * @param enabled
     *            true to enable the rule, false to disable the rule.
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * Set the rule name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Set the context expression
     */
    public void setContext(String context) {
        this.context = context;
    }

    /**
     * Set the rule classifier
     */
    public void setClassifier(String classifier) {
        this.classifier = classifier;
    }

    /**
     * Set the invariant expression
     */
    public void setQuery(String query) {
        this.query = query;
    }

    /**
     * Get the error/warning level
     */
    public boolean getWarningOnly() {
        return warningOnly;
    }

    /**
     * Set the error/warning level
     */
    public void setWarningOnly(boolean warningOnly) {
        this.warningOnly = warningOnly;
    }

    /**
     * Returns a list of definitions of utilities.
     */
    public List getUtilities() {
        return utilities;
    }

    /**
     * Check if the rule classifier, context expression, invariant expression and utilities are valid or not under the standard utility library which is defined
     * in the file "library.ocl". If any invalidation is found, the corresponding error messages are saved and then can be obtained by the method getErrors().
     * 
     * @return true if all elements are valid, otherwise false
     * @see #getErrors()
     */
    public boolean isValid() {

        // OCL<?, EClassifier, EOperation, ?, ?, ?, ?, ?, ?, Constraint, EClass, EObject> ocl;
        OCL ocl;
        ocl = OCL.newInstance(EcoreEnvironmentFactory.INSTANCE);
        OCLInput lib = new OCLInput(this.getClass().getResourceAsStream("library.ocl")); //$NON-NLS-1$
        try {
            ocl.parse(lib);
        } catch (ParserException e2) {
            errors += "Library error: " + e2.getMessage(); //$NON-NLS-1$
            return false;
        }
        // OCLHelper<EClassifier, EOperation, ?, Constraint> helper = ocl.createOCLHelper();
        OCLHelper helper = ocl.createOCLHelper();
        // verify context
        List name = this.getClassifierAsList();
        EClassifier e = (EClassifier) ocl.getEnvironment().lookupClassifier(name);
        if (e == null) {
            errors += "Classifier " + this.getClassifier() + " is invalid."; //$NON-NLS-1$ //$NON-NLS-2$
            return false;
        }
        // verify utilities
        for (int i = 0; i < this.getUtilities().size(); i++) {
            String op = (String) this.getUtilities().get(i);
            try {
                helper.setContext(e);
                helper.defineOperation(op);
            } catch (ParserException e1) {
                errors += "Utilities #" + (i + 1) + " error: " + e1.getMessage(); //$NON-NLS-1$ //$NON-NLS-2$
                return false;
            }
        }
        // verify query constraint
        try {
            helper.setContext(UrnPackage.Literals.UR_NSPEC);
            // OCLExpression<EClassifier> query;
            OCLExpression query;
            query = helper.createQuery(this.getContext());
            // Query<EClassifier, EClass, EObject> queryEval = ocl.createQuery(query);
            Query queryEval = ocl.createQuery(query);
        } catch (ParserException e1) {
            errors += "Query constraint error: " + e1.getMessage(); //$NON-NLS-1$
            return false;
        }
        // verify invariant constraint
        try {
            helper.setContext(e);
            /*
             * Constraint invariant = helper.createInvariant(this.getQuery()); Query<EClassifier, EClass, EObject> constraintEval = ocl.createQuery(invariant);
             */
            Constraint invariant = (Constraint) helper.createInvariant(this.getQuery());
            Query constraintEval = ocl.createQuery(invariant);

        } catch (ParserException e1) {
            errors += "Invariant constraint error: " + e1.getMessage(); //$NON-NLS-1$
            return false;
        }

        return true;
    }

    /**
     * Returns errors found during checking rule validation.
     * 
     * @return a string of error message
     * @see #isValid()
     */
    public String getErrors() {
        return errors;
    }

    /**
     * Delete all utility definitions
     */
    public void clearUtilities() {
        utilities.clear();
    }

}
