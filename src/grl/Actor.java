/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package grl;

import org.eclipse.emf.common.util.EList;

import urncore.IURNContainer;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Actor</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link grl.Actor#getImportance <em>Importance</em>}</li>
 *   <li>{@link grl.Actor#getImportanceQuantitative <em>Importance Quantitative</em>}</li>
 *   <li>{@link grl.Actor#getGrlspec <em>Grlspec</em>}</li>
 *   <li>{@link grl.Actor#getIncludedActors <em>Included Actors</em>}</li>
 *   <li>{@link grl.Actor#getIncludingActor <em>Including Actor</em>}</li>
 *   <li>{@link grl.Actor#getCollapsedRefs <em>Collapsed Refs</em>}</li>
 * </ul>
 * </p>
 *
 * @see grl.GrlPackage#getActor()
 * @model
 * @generated
 */
public interface Actor extends GRLLinkableElement, IURNContainer {
    /**
	 * Returns the value of the '<em><b>Importance</b></em>' attribute.
	 * The default value is <code>"None"</code>.
	 * The literals are from the enumeration {@link grl.ImportanceType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Importance</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Importance</em>' attribute.
	 * @see grl.ImportanceType
	 * @see #setImportance(ImportanceType)
	 * @see grl.GrlPackage#getActor_Importance()
	 * @model default="None"
	 * @generated
	 */
	ImportanceType getImportance();

	/**
	 * Sets the value of the '{@link grl.Actor#getImportance <em>Importance</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Importance</em>' attribute.
	 * @see grl.ImportanceType
	 * @see #getImportance()
	 * @generated
	 */
	void setImportance(ImportanceType value);

	/**
	 * Returns the value of the '<em><b>Importance Quantitative</b></em>' attribute.
	 * The default value is <code>"0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Importance Quantitative</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Importance Quantitative</em>' attribute.
	 * @see #setImportanceQuantitative(int)
	 * @see grl.GrlPackage#getActor_ImportanceQuantitative()
	 * @model default="0"
	 * @generated
	 */
	int getImportanceQuantitative();

	/**
	 * Sets the value of the '{@link grl.Actor#getImportanceQuantitative <em>Importance Quantitative</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Importance Quantitative</em>' attribute.
	 * @see #getImportanceQuantitative()
	 * @generated
	 */
	void setImportanceQuantitative(int value);

				/**
	 * Returns the value of the '<em><b>Grlspec</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link grl.GRLspec#getActors <em>Actors</em>}'.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Grlspec</em>' container reference isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Grlspec</em>' container reference.
	 * @see #setGrlspec(GRLspec)
	 * @see grl.GrlPackage#getActor_Grlspec()
	 * @see grl.GRLspec#getActors
	 * @model opposite="actors" required="true"
	 * @generated
	 */
    GRLspec getGrlspec();

    /**
	 * Sets the value of the '{@link grl.Actor#getGrlspec <em>Grlspec</em>}' container reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Grlspec</em>' container reference.
	 * @see #getGrlspec()
	 * @generated
	 */
    void setGrlspec(GRLspec value);

				/**
	 * Returns the value of the '<em><b>Included Actors</b></em>' reference list.
	 * The list contents are of type {@link grl.Actor}.
	 * It is bidirectional and its opposite is '{@link grl.Actor#getIncludingActor <em>Including Actor</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Included Actors</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Included Actors</em>' reference list.
	 * @see grl.GrlPackage#getActor_IncludedActors()
	 * @see grl.Actor#getIncludingActor
	 * @model type="grl.Actor" opposite="includingActor"
	 * @generated
	 */
	EList getIncludedActors();

				/**
	 * Returns the value of the '<em><b>Including Actor</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link grl.Actor#getIncludedActors <em>Included Actors</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Including Actor</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Including Actor</em>' reference.
	 * @see #setIncludingActor(Actor)
	 * @see grl.GrlPackage#getActor_IncludingActor()
	 * @see grl.Actor#getIncludedActors
	 * @model opposite="includedActors"
	 * @generated
	 */
	Actor getIncludingActor();

				/**
	 * Sets the value of the '{@link grl.Actor#getIncludingActor <em>Including Actor</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Including Actor</em>' reference.
	 * @see #getIncludingActor()
	 * @generated
	 */
	void setIncludingActor(Actor value);

				/**
	 * Returns the value of the '<em><b>Collapsed Refs</b></em>' reference list.
	 * The list contents are of type {@link grl.CollapsedActorRef}.
	 * It is bidirectional and its opposite is '{@link grl.CollapsedActorRef#getActor <em>Actor</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Collapsed Refs</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Collapsed Refs</em>' reference list.
	 * @see grl.GrlPackage#getActor_CollapsedRefs()
	 * @see grl.CollapsedActorRef#getActor
	 * @model type="grl.CollapsedActorRef" opposite="actor"
	 * @generated
	 */
	EList getCollapsedRefs();

} // Actor
