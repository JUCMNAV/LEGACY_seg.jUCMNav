/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package urn;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see urn.UrnFactory
 * @generated
 */
public interface UrnPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "urn";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http:///urn.ecore";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "urn";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	UrnPackage eINSTANCE = urn.impl.UrnPackageImpl.init();

	/**
	 * The meta object id for the '{@link urn.impl.URNspecImpl <em>UR Nspec</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see urn.impl.URNspecImpl
	 * @see urn.impl.UrnPackageImpl#getURNspec()
	 * @generated
	 */
	int UR_NSPEC = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UR_NSPEC__NAME = 0;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UR_NSPEC__DESCRIPTION = 1;

	/**
	 * The feature id for the '<em><b>Author</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UR_NSPEC__AUTHOR = 2;

	/**
	 * The feature id for the '<em><b>Created</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UR_NSPEC__CREATED = 3;

	/**
	 * The feature id for the '<em><b>Modified</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UR_NSPEC__MODIFIED = 4;

	/**
	 * The feature id for the '<em><b>Spec Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UR_NSPEC__SPEC_VERSION = 5;

	/**
	 * The feature id for the '<em><b>Urn Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UR_NSPEC__URN_VERSION = 6;

	/**
	 * The feature id for the '<em><b>Ucmspec</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UR_NSPEC__UCMSPEC = 7;

	/**
	 * The feature id for the '<em><b>Grlspec</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UR_NSPEC__GRLSPEC = 8;

	/**
	 * The feature id for the '<em><b>Urndef</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UR_NSPEC__URNDEF = 9;

	/**
	 * The feature id for the '<em><b>Urn Links</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UR_NSPEC__URN_LINKS = 10;

	/**
	 * The number of structural features of the the '<em>UR Nspec</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UR_NSPEC_FEATURE_COUNT = 11;

	/**
	 * The meta object id for the '{@link urn.impl.URNlinkImpl <em>UR Nlink</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see urn.impl.URNlinkImpl
	 * @see urn.impl.UrnPackageImpl#getURNlink()
	 * @generated
	 */
	int UR_NLINK = 1;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UR_NLINK__ID = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UR_NLINK__NAME = 1;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UR_NLINK__DESCRIPTION = 2;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UR_NLINK__KIND = 3;

	/**
	 * The feature id for the '<em><b>Grl Elems</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UR_NLINK__GRL_ELEMS = 4;

	/**
	 * The feature id for the '<em><b>Ucm Elems</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UR_NLINK__UCM_ELEMS = 5;

	/**
	 * The number of structural features of the the '<em>UR Nlink</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UR_NLINK_FEATURE_COUNT = 6;


	/**
	 * Returns the meta object for class '{@link urn.URNspec <em>UR Nspec</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>UR Nspec</em>'.
	 * @see urn.URNspec
	 * @generated
	 */
	EClass getURNspec();

	/**
	 * Returns the meta object for the attribute '{@link urn.URNspec#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see urn.URNspec#getName()
	 * @see #getURNspec()
	 * @generated
	 */
	EAttribute getURNspec_Name();

	/**
	 * Returns the meta object for the attribute '{@link urn.URNspec#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see urn.URNspec#getDescription()
	 * @see #getURNspec()
	 * @generated
	 */
	EAttribute getURNspec_Description();

	/**
	 * Returns the meta object for the attribute '{@link urn.URNspec#getAuthor <em>Author</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Author</em>'.
	 * @see urn.URNspec#getAuthor()
	 * @see #getURNspec()
	 * @generated
	 */
	EAttribute getURNspec_Author();

	/**
	 * Returns the meta object for the attribute '{@link urn.URNspec#getCreated <em>Created</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Created</em>'.
	 * @see urn.URNspec#getCreated()
	 * @see #getURNspec()
	 * @generated
	 */
	EAttribute getURNspec_Created();

	/**
	 * Returns the meta object for the attribute '{@link urn.URNspec#getModified <em>Modified</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Modified</em>'.
	 * @see urn.URNspec#getModified()
	 * @see #getURNspec()
	 * @generated
	 */
	EAttribute getURNspec_Modified();

	/**
	 * Returns the meta object for the attribute '{@link urn.URNspec#getSpecVersion <em>Spec Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Spec Version</em>'.
	 * @see urn.URNspec#getSpecVersion()
	 * @see #getURNspec()
	 * @generated
	 */
	EAttribute getURNspec_SpecVersion();

	/**
	 * Returns the meta object for the attribute '{@link urn.URNspec#getUrnVersion <em>Urn Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Urn Version</em>'.
	 * @see urn.URNspec#getUrnVersion()
	 * @see #getURNspec()
	 * @generated
	 */
	EAttribute getURNspec_UrnVersion();

	/**
	 * Returns the meta object for the containment reference '{@link urn.URNspec#getUcmspec <em>Ucmspec</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Ucmspec</em>'.
	 * @see urn.URNspec#getUcmspec()
	 * @see #getURNspec()
	 * @generated
	 */
	EReference getURNspec_Ucmspec();

	/**
	 * Returns the meta object for the containment reference '{@link urn.URNspec#getGrlspec <em>Grlspec</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Grlspec</em>'.
	 * @see urn.URNspec#getGrlspec()
	 * @see #getURNspec()
	 * @generated
	 */
	EReference getURNspec_Grlspec();

	/**
	 * Returns the meta object for the containment reference '{@link urn.URNspec#getUrndef <em>Urndef</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Urndef</em>'.
	 * @see urn.URNspec#getUrndef()
	 * @see #getURNspec()
	 * @generated
	 */
	EReference getURNspec_Urndef();

	/**
	 * Returns the meta object for the containment reference list '{@link urn.URNspec#getUrnLinks <em>Urn Links</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Urn Links</em>'.
	 * @see urn.URNspec#getUrnLinks()
	 * @see #getURNspec()
	 * @generated
	 */
	EReference getURNspec_UrnLinks();

	/**
	 * Returns the meta object for class '{@link urn.URNlink <em>UR Nlink</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>UR Nlink</em>'.
	 * @see urn.URNlink
	 * @generated
	 */
	EClass getURNlink();

	/**
	 * Returns the meta object for the attribute '{@link urn.URNlink#getId <em>Id</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see urn.URNlink#getId()
	 * @see #getURNlink()
	 * @generated
	 */
	EAttribute getURNlink_Id();

	/**
	 * Returns the meta object for the attribute '{@link urn.URNlink#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see urn.URNlink#getName()
	 * @see #getURNlink()
	 * @generated
	 */
	EAttribute getURNlink_Name();

	/**
	 * Returns the meta object for the attribute '{@link urn.URNlink#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see urn.URNlink#getDescription()
	 * @see #getURNlink()
	 * @generated
	 */
	EAttribute getURNlink_Description();

	/**
	 * Returns the meta object for the attribute '{@link urn.URNlink#getKind <em>Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Kind</em>'.
	 * @see urn.URNlink#getKind()
	 * @see #getURNlink()
	 * @generated
	 */
	EAttribute getURNlink_Kind();

	/**
	 * Returns the meta object for the reference list '{@link urn.URNlink#getGrlElems <em>Grl Elems</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Grl Elems</em>'.
	 * @see urn.URNlink#getGrlElems()
	 * @see #getURNlink()
	 * @generated
	 */
	EReference getURNlink_GrlElems();

	/**
	 * Returns the meta object for the reference list '{@link urn.URNlink#getUcmElems <em>Ucm Elems</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Ucm Elems</em>'.
	 * @see urn.URNlink#getUcmElems()
	 * @see #getURNlink()
	 * @generated
	 */
	EReference getURNlink_UcmElems();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	UrnFactory getUrnFactory();

} //UrnPackage
