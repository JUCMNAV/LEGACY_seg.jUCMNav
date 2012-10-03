/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package grl;

import org.eclipse.emf.common.util.EList;

import urncore.GRLmodelElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Evaluation Strategy</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link grl.EvaluationStrategy#getAuthor <em>Author</em>}</li>
 *   <li>{@link grl.EvaluationStrategy#getEvaluations <em>Evaluations</em>}</li>
 *   <li>{@link grl.EvaluationStrategy#getGroup <em>Group</em>}</li>
 *   <li>{@link grl.EvaluationStrategy#getGrlspec <em>Grlspec</em>}</li>
 *   <li>{@link grl.EvaluationStrategy#getIncludedStrategies <em>Included Strategies</em>}</li>
 *   <li>{@link grl.EvaluationStrategy#getParentStrategies <em>Parent Strategies</em>}</li>
 *   <li>{@link grl.EvaluationStrategy#getKpiInfoConfig <em>Kpi Info Config</em>}</li>
 * </ul>
 * </p>
 *
 * @see grl.GrlPackage#getEvaluationStrategy()
 * @model
 * @generated
 */
public interface EvaluationStrategy extends GRLmodelElement {
    /**
	 * Returns the value of the '<em><b>Author</b></em>' attribute.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Author</em>' attribute isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Author</em>' attribute.
	 * @see #setAuthor(String)
	 * @see grl.GrlPackage#getEvaluationStrategy_Author()
	 * @model
	 * @generated
	 */
    String getAuthor();

    /**
	 * Sets the value of the '{@link grl.EvaluationStrategy#getAuthor <em>Author</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Author</em>' attribute.
	 * @see #getAuthor()
	 * @generated
	 */
    void setAuthor(String value);

    /**
	 * Returns the value of the '<em><b>Evaluations</b></em>' containment reference list.
	 * The list contents are of type {@link grl.Evaluation}.
	 * It is bidirectional and its opposite is '{@link grl.Evaluation#getStrategies <em>Strategies</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Evaluations</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Evaluations</em>' containment reference list.
	 * @see grl.GrlPackage#getEvaluationStrategy_Evaluations()
	 * @see grl.Evaluation#getStrategies
	 * @model type="grl.Evaluation" opposite="strategies" containment="true"
	 * @generated
	 */
    EList getEvaluations();

    /**
	 * Returns the value of the '<em><b>Group</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link grl.StrategiesGroup#getStrategies <em>Strategies</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Group</em>' reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Group</em>' reference.
	 * @see #setGroup(StrategiesGroup)
	 * @see grl.GrlPackage#getEvaluationStrategy_Group()
	 * @see grl.StrategiesGroup#getStrategies
	 * @model opposite="strategies" required="true"
	 * @generated
	 */
    StrategiesGroup getGroup();

    /**
	 * Sets the value of the '{@link grl.EvaluationStrategy#getGroup <em>Group</em>}' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Group</em>' reference.
	 * @see #getGroup()
	 * @generated
	 */
    void setGroup(StrategiesGroup value);

    /**
	 * Returns the value of the '<em><b>Grlspec</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link grl.GRLspec#getStrategies <em>Strategies</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Grlspec</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Grlspec</em>' container reference.
	 * @see #setGrlspec(GRLspec)
	 * @see grl.GrlPackage#getEvaluationStrategy_Grlspec()
	 * @see grl.GRLspec#getStrategies
	 * @model opposite="strategies" required="true"
	 * @generated
	 */
    GRLspec getGrlspec();

    /**
	 * Sets the value of the '{@link grl.EvaluationStrategy#getGrlspec <em>Grlspec</em>}' container reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Grlspec</em>' container reference.
	 * @see #getGrlspec()
	 * @generated
	 */
    void setGrlspec(GRLspec value);

    /**
	 * Returns the value of the '<em><b>Included Strategies</b></em>' reference list.
	 * The list contents are of type {@link grl.EvaluationStrategy}.
	 * It is bidirectional and its opposite is '{@link grl.EvaluationStrategy#getParentStrategies <em>Parent Strategies</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Included Strategies</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Included Strategies</em>' reference list.
	 * @see grl.GrlPackage#getEvaluationStrategy_IncludedStrategies()
	 * @see grl.EvaluationStrategy#getParentStrategies
	 * @model type="grl.EvaluationStrategy" opposite="parentStrategies"
	 * @generated
	 */
	EList getIncludedStrategies();

				/**
	 * Returns the value of the '<em><b>Parent Strategies</b></em>' reference list.
	 * The list contents are of type {@link grl.EvaluationStrategy}.
	 * It is bidirectional and its opposite is '{@link grl.EvaluationStrategy#getIncludedStrategies <em>Included Strategies</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parent Strategies</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parent Strategies</em>' reference list.
	 * @see grl.GrlPackage#getEvaluationStrategy_ParentStrategies()
	 * @see grl.EvaluationStrategy#getIncludedStrategies
	 * @model type="grl.EvaluationStrategy" opposite="includedStrategies"
	 * @generated
	 */
	EList getParentStrategies();

				/**
	 * Returns the value of the '<em><b>Kpi Info Config</b></em>' containment reference list.
	 * The list contents are of type {@link grl.kpimodel.KPIInformationConfig}.
	 * It is bidirectional and its opposite is '{@link grl.kpimodel.KPIInformationConfig#getStrategies <em>Strategies</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Kpi Info Config</em>' containment reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Kpi Info Config</em>' containment reference list.
	 * @see grl.GrlPackage#getEvaluationStrategy_KpiInfoConfig()
	 * @see grl.kpimodel.KPIInformationConfig#getStrategies
	 * @model type="grl.kpimodel.KPIInformationConfig" opposite="strategies" containment="true"
	 * @generated
	 */
    EList getKpiInfoConfig();
    
    int compareTo(EvaluationStrategy strat);

} // EvaluationStrategy
