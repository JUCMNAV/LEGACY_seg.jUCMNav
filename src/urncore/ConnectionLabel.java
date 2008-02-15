/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package urncore;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Connection Label</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link urncore.ConnectionLabel#getConnection <em>Connection</em>}</li>
 * </ul>
 * </p>
 *
 * @see urncore.UrncorePackage#getConnectionLabel()
 * @model
 * @generated
 */
public interface ConnectionLabel extends Label {
	/**
	 * Returns the value of the '<em><b>Connection</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link urncore.IURNConnection#getLabel <em>Label</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Connection</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Connection</em>' container reference.
	 * @see #setConnection(IURNConnection)
	 * @see urncore.UrncorePackage#getConnectionLabel_Connection()
	 * @see urncore.IURNConnection#getLabel
	 * @model opposite="label" required="true"
	 * @generated
	 */
	IURNConnection getConnection();

	/**
	 * Sets the value of the '{@link urncore.ConnectionLabel#getConnection <em>Connection</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Connection</em>' container reference.
	 * @see #getConnection()
	 * @generated
	 */
	void setConnection(IURNConnection value);

} // ConnectionLabel
