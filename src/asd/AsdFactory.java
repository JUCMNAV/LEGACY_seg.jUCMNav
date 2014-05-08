/**
 */
package asd;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see asd.AsdPackage
 * @generated
 */
public interface AsdFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	AsdFactory eINSTANCE = asd.impl.AsdFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>AS Dspec</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>AS Dspec</em>'.
	 * @generated
	 */
	ASDspec createASDspec();

	/**
	 * Returns a new object of class '<em>AS Diagram</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>AS Diagram</em>'.
	 * @generated
	 */
	ASDiagram createASDiagram();

	/**
	 * Returns a new object of class '<em>Mediation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Mediation</em>'.
	 * @generated
	 */
	Mediation createMediation();

	/**
	 * Returns a new object of class '<em>AS Network</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>AS Network</em>'.
	 * @generated
	 */
	ASNetwork createASNetwork();

	/**
	 * Returns a new object of class '<em>AS Dlayout</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>AS Dlayout</em>'.
	 * @generated
	 */
	ASDlayout createASDlayout();

	/**
	 * Returns a new object of class '<em>Tool</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Tool</em>'.
	 * @generated
	 */
	Tool createTool();

	/**
	 * Returns a new object of class '<em>Rule</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Rule</em>'.
	 * @generated
	 */
	Rule createRule();

	/**
	 * Returns a new object of class '<em>Division Of Labour</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Division Of Labour</em>'.
	 * @generated
	 */
	DivisionOfLabour createDivisionOfLabour();

	/**
	 * Returns a new object of class '<em>Community</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Community</em>'.
	 * @generated
	 */
	Community createCommunity();

	/**
	 * Returns a new object of class '<em>Subject</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Subject</em>'.
	 * @generated
	 */
	Subject createSubject();

	/**
	 * Returns a new object of class '<em>Object</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Object</em>'.
	 * @generated
	 */
	Object createObject();

	/**
	 * Returns a new object of class '<em>Motivation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Motivation</em>'.
	 * @generated
	 */
	Motivation createMotivation();

	/**
	 * Returns a new object of class '<em>Outcome</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Outcome</em>'.
	 * @generated
	 */
	Outcome createOutcome();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	AsdPackage getAsdPackage();

} //AsdFactory
