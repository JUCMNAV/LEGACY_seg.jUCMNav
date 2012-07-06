/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package grl.kpimodel;

import grl.QualitativeLabel;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Qualitative Mapping</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link grl.kpimodel.QualitativeMapping#getRealWorldLabel <em>Real World Label</em>}</li>
 *   <li>{@link grl.kpimodel.QualitativeMapping#getEvaluation <em>Evaluation</em>}</li>
 *   <li>{@link grl.kpimodel.QualitativeMapping#getQualitativeEvaluation <em>Qualitative Evaluation</em>}</li>
 *   <li>{@link grl.kpimodel.QualitativeMapping#isExceeds <em>Exceeds</em>}</li>
 * </ul>
 * </p>
 *
 * @see grl.kpimodel.KpimodelPackage#getQualitativeMapping()
 * @model
 * @generated
 */
public interface QualitativeMapping extends EObject {
	/**
	 * Returns the value of the '<em><b>Real World Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Real World Label</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Real World Label</em>' attribute.
	 * @see #setRealWorldLabel(String)
	 * @see grl.kpimodel.KpimodelPackage#getQualitativeMapping_RealWorldLabel()
	 * @model
	 * @generated
	 */
	String getRealWorldLabel();

	/**
	 * Sets the value of the '{@link grl.kpimodel.QualitativeMapping#getRealWorldLabel <em>Real World Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Real World Label</em>' attribute.
	 * @see #getRealWorldLabel()
	 * @generated
	 */
	void setRealWorldLabel(String value);

	/**
	 * Returns the value of the '<em><b>Evaluation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Evaluation</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Evaluation</em>' attribute.
	 * @see #setEvaluation(int)
	 * @see grl.kpimodel.KpimodelPackage#getQualitativeMapping_Evaluation()
	 * @model
	 * @generated
	 */
	int getEvaluation();

	/**
	 * Sets the value of the '{@link grl.kpimodel.QualitativeMapping#getEvaluation <em>Evaluation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Evaluation</em>' attribute.
	 * @see #getEvaluation()
	 * @generated
	 */
	void setEvaluation(int value);

	/**
	 * Returns the value of the '<em><b>Qualitative Evaluation</b></em>' attribute.
	 * The literals are from the enumeration {@link grl.QualitativeLabel}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Qualitative Evaluation</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Qualitative Evaluation</em>' attribute.
	 * @see grl.QualitativeLabel
	 * @see #setQualitativeEvaluation(QualitativeLabel)
	 * @see grl.kpimodel.KpimodelPackage#getQualitativeMapping_QualitativeEvaluation()
	 * @model
	 * @generated
	 */
	QualitativeLabel getQualitativeEvaluation();

	/**
	 * Sets the value of the '{@link grl.kpimodel.QualitativeMapping#getQualitativeEvaluation <em>Qualitative Evaluation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Qualitative Evaluation</em>' attribute.
	 * @see grl.QualitativeLabel
	 * @see #getQualitativeEvaluation()
	 * @generated
	 */
	void setQualitativeEvaluation(QualitativeLabel value);

	/**
	 * Returns the value of the '<em><b>Exceeds</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Exceeds</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Exceeds</em>' attribute.
	 * @see #setExceeds(boolean)
	 * @see grl.kpimodel.KpimodelPackage#getQualitativeMapping_Exceeds()
	 * @model
	 * @generated
	 */
	boolean isExceeds();

	/**
	 * Sets the value of the '{@link grl.kpimodel.QualitativeMapping#isExceeds <em>Exceeds</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Exceeds</em>' attribute.
	 * @see #isExceeds()
	 * @generated
	 */
	void setExceeds(boolean value);

} // QualitativeMapping
