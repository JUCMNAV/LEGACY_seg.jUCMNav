/**
 */
package fm;

import ca.mcgill.sel.core.COREFeature;

import grl.IntentionalElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Feature</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link fm.Feature#isSelectable <em>Selectable</em>}</li>
 *   <li>{@link fm.Feature#getCoreFeature <em>Core Feature</em>}</li>
 * </ul>
 *
 * @see fm.FmPackage#getFeature()
 * @model
 * @generated
 */
public interface Feature extends IntentionalElement {
	/**
	 * Returns the value of the '<em><b>Selectable</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Selectable</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Selectable</em>' attribute.
	 * @see #setSelectable(boolean)
	 * @see fm.FmPackage#getFeature_Selectable()
	 * @model default="false"
	 * @generated
	 */
	boolean isSelectable();

	/**
	 * Sets the value of the '{@link fm.Feature#isSelectable <em>Selectable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Selectable</em>' attribute.
	 * @see #isSelectable()
	 * @generated
	 */
	void setSelectable(boolean value);

	/**
	 * Returns the value of the '<em><b>Core Feature</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Core Feature</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Core Feature</em>' reference.
	 * @see #setCoreFeature(COREFeature)
	 * @see fm.FmPackage#getFeature_CoreFeature()
	 * @model
	 * @generated
	 */
	COREFeature getCoreFeature();

	/**
	 * Sets the value of the '{@link fm.Feature#getCoreFeature <em>Core Feature</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Core Feature</em>' reference.
	 * @see #getCoreFeature()
	 * @generated
	 */
	void setCoreFeature(COREFeature value);

} // Feature
