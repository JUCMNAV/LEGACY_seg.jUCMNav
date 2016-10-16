/**
 */
package urn.dyncontext;

import grl.ContributionContext;
import grl.EvaluationStrategy;

import org.eclipse.emf.common.util.EList;

import ucm.scenario.ScenarioDef;

import urn.URNspec;

import urncore.URNmodelElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Dynamic Context</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link urn.dyncontext.DynamicContext#getParentContexts <em>Parent Contexts</em>}</li>
 *   <li>{@link urn.dyncontext.DynamicContext#getIncludedContexts <em>Included Contexts</em>}</li>
 *   <li>{@link urn.dyncontext.DynamicContext#getStrategy <em>Strategy</em>}</li>
 *   <li>{@link urn.dyncontext.DynamicContext#getContributionContext <em>Contribution Context</em>}</li>
 *   <li>{@link urn.dyncontext.DynamicContext#getScenario <em>Scenario</em>}</li>
 *   <li>{@link urn.dyncontext.DynamicContext#getUrnspec <em>Urnspec</em>}</li>
 *   <li>{@link urn.dyncontext.DynamicContext#getGroups <em>Groups</em>}</li>
 *   <li>{@link urn.dyncontext.DynamicContext#getChanges <em>Changes</em>}</li>
 * </ul>
 *
 * @see urn.dyncontext.DyncontextPackage#getDynamicContext()
 * @model
 * @generated
 */
public interface DynamicContext extends URNmodelElement {
	/**
	 * Returns the value of the '<em><b>Parent Contexts</b></em>' reference list.
	 * The list contents are of type {@link urn.dyncontext.DynamicContext}.
	 * It is bidirectional and its opposite is '{@link urn.dyncontext.DynamicContext#getIncludedContexts <em>Included Contexts</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parent Contexts</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parent Contexts</em>' reference list.
	 * @see urn.dyncontext.DyncontextPackage#getDynamicContext_ParentContexts()
	 * @see urn.dyncontext.DynamicContext#getIncludedContexts
	 * @model type="urn.dyncontext.DynamicContext" opposite="includedContexts"
	 * @generated
	 */
	EList getParentContexts();

	/**
	 * Returns the value of the '<em><b>Included Contexts</b></em>' reference list.
	 * The list contents are of type {@link urn.dyncontext.DynamicContext}.
	 * It is bidirectional and its opposite is '{@link urn.dyncontext.DynamicContext#getParentContexts <em>Parent Contexts</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Included Contexts</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Included Contexts</em>' reference list.
	 * @see urn.dyncontext.DyncontextPackage#getDynamicContext_IncludedContexts()
	 * @see urn.dyncontext.DynamicContext#getParentContexts
	 * @model type="urn.dyncontext.DynamicContext" opposite="parentContexts"
	 * @generated
	 */
	EList getIncludedContexts();

	/**
	 * Returns the value of the '<em><b>Strategy</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Strategy</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Strategy</em>' reference.
	 * @see #setStrategy(EvaluationStrategy)
	 * @see urn.dyncontext.DyncontextPackage#getDynamicContext_Strategy()
	 * @model
	 * @generated
	 */
	EvaluationStrategy getStrategy();

	/**
	 * Sets the value of the '{@link urn.dyncontext.DynamicContext#getStrategy <em>Strategy</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Strategy</em>' reference.
	 * @see #getStrategy()
	 * @generated
	 */
	void setStrategy(EvaluationStrategy value);

	/**
	 * Returns the value of the '<em><b>Contribution Context</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Contribution Context</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Contribution Context</em>' reference.
	 * @see #setContributionContext(ContributionContext)
	 * @see urn.dyncontext.DyncontextPackage#getDynamicContext_ContributionContext()
	 * @model
	 * @generated
	 */
	ContributionContext getContributionContext();

	/**
	 * Sets the value of the '{@link urn.dyncontext.DynamicContext#getContributionContext <em>Contribution Context</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Contribution Context</em>' reference.
	 * @see #getContributionContext()
	 * @generated
	 */
	void setContributionContext(ContributionContext value);

	/**
	 * Returns the value of the '<em><b>Scenario</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Scenario</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Scenario</em>' reference.
	 * @see #setScenario(ScenarioDef)
	 * @see urn.dyncontext.DyncontextPackage#getDynamicContext_Scenario()
	 * @model
	 * @generated
	 */
	ScenarioDef getScenario();

	/**
	 * Sets the value of the '{@link urn.dyncontext.DynamicContext#getScenario <em>Scenario</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Scenario</em>' reference.
	 * @see #getScenario()
	 * @generated
	 */
	void setScenario(ScenarioDef value);

	/**
	 * Returns the value of the '<em><b>Urnspec</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link urn.URNspec#getDynamicContexts <em>Dynamic Contexts</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Urnspec</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Urnspec</em>' container reference.
	 * @see #setUrnspec(URNspec)
	 * @see urn.dyncontext.DyncontextPackage#getDynamicContext_Urnspec()
	 * @see urn.URNspec#getDynamicContexts
	 * @model opposite="dynamicContexts" required="true"
	 * @generated
	 */
	URNspec getUrnspec();

	/**
	 * Sets the value of the '{@link urn.dyncontext.DynamicContext#getUrnspec <em>Urnspec</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Urnspec</em>' container reference.
	 * @see #getUrnspec()
	 * @generated
	 */
	void setUrnspec(URNspec value);

	/**
	 * Returns the value of the '<em><b>Groups</b></em>' reference list.
	 * The list contents are of type {@link urn.dyncontext.DynamicContextGroup}.
	 * It is bidirectional and its opposite is '{@link urn.dyncontext.DynamicContextGroup#getContexts <em>Contexts</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Groups</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Groups</em>' reference list.
	 * @see urn.dyncontext.DyncontextPackage#getDynamicContext_Groups()
	 * @see urn.dyncontext.DynamicContextGroup#getContexts
	 * @model type="urn.dyncontext.DynamicContextGroup" opposite="contexts" required="true"
	 * @generated
	 */
	EList getGroups();

	/**
	 * Returns the value of the '<em><b>Changes</b></em>' containment reference list.
	 * The list contents are of type {@link urn.dyncontext.Change}.
	 * It is bidirectional and its opposite is '{@link urn.dyncontext.Change#getContext <em>Context</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Changes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Changes</em>' containment reference list.
	 * @see urn.dyncontext.DyncontextPackage#getDynamicContext_Changes()
	 * @see urn.dyncontext.Change#getContext
	 * @model type="urn.dyncontext.Change" opposite="context" containment="true"
	 * @generated
	 */
	EList getChanges();

} // DynamicContext
