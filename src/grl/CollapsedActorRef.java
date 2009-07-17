/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package grl;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Collapsed Actor Ref</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link grl.CollapsedActorRef#getActor <em>Actor</em>}</li>
 * </ul>
 * </p>
 *
 * @see grl.GrlPackage#getCollapsedActorRef()
 * @model
 * @generated
 */
public interface CollapsedActorRef extends GRLNode {

	/**
	 * Returns the value of the '<em><b>Actor</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link grl.Actor#getCollapsedRefs <em>Collapsed Refs</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Actor</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Actor</em>' reference.
	 * @see #setActor(Actor)
	 * @see grl.GrlPackage#getCollapsedActorRef_Actor()
	 * @see grl.Actor#getCollapsedRefs
	 * @model opposite="collapsedRefs" required="true"
	 * @generated
	 */
	Actor getActor();

	/**
	 * Sets the value of the '{@link grl.CollapsedActorRef#getActor <em>Actor</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Actor</em>' reference.
	 * @see #getActor()
	 * @generated
	 */
	void setActor(Actor value);
} // CollapsedActorRef
