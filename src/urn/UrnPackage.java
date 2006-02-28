/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package urn;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
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
 * @model kind="package"
 * @generated
 */
public interface UrnPackage extends EPackage{
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
     * The feature id for the '<em><b>Next Global ID</b></em>' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int UR_NSPEC__NEXT_GLOBAL_ID = 7;

    /**
     * The feature id for the '<em><b>Ucmspec</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int UR_NSPEC__UCMSPEC = 8;

    /**
     * The feature id for the '<em><b>Grlspec</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int UR_NSPEC__GRLSPEC = 9;

    /**
     * The feature id for the '<em><b>Urndef</b></em>' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int UR_NSPEC__URNDEF = 10;

    /**
     * The feature id for the '<em><b>Urn Links</b></em>' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int UR_NSPEC__URN_LINKS = 11;

    /**
     * The number of structural features of the the '<em>UR Nspec</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int UR_NSPEC_FEATURE_COUNT = 12;

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
     * The feature id for the '<em><b>Urnspec</b></em>' container reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int UR_NLINK__URNSPEC = 0;

    /**
     * The feature id for the '<em><b>Grl Model Elements</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int UR_NLINK__GRL_MODEL_ELEMENTS = 1;

    /**
     * The feature id for the '<em><b>Ucm Model Elements</b></em>' reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int UR_NLINK__UCM_MODEL_ELEMENTS = 2;

    /**
     * The number of structural features of the the '<em>UR Nlink</em>' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     * @ordered
     */
    int UR_NLINK_FEATURE_COUNT = 3;


    /**
     * The meta object id for the '{@link urn.URNLinkType <em>URN Link Type</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see urn.URNLinkType
     * @see urn.impl.UrnPackageImpl#getURNLinkType()
     * @generated
     */
    int URN_LINK_TYPE = 2;


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
     * Returns the meta object for the attribute '{@link urn.URNspec#getNextGlobalID <em>Next Global ID</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the attribute '<em>Next Global ID</em>'.
     * @see urn.URNspec#getNextGlobalID()
     * @see #getURNspec()
     * @generated
     */
    EAttribute getURNspec_NextGlobalID();

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
     * Returns the meta object for the container reference '{@link urn.URNlink#getUrnspec <em>Urnspec</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the container reference '<em>Urnspec</em>'.
     * @see urn.URNlink#getUrnspec()
     * @see #getURNlink()
     * @generated
     */
    EReference getURNlink_Urnspec();

    /**
     * Returns the meta object for the reference '{@link urn.URNlink#getGrlModelElements <em>Grl Model Elements</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Grl Model Elements</em>'.
     * @see urn.URNlink#getGrlModelElements()
     * @see #getURNlink()
     * @generated
     */
    EReference getURNlink_GrlModelElements();

    /**
     * Returns the meta object for the reference '{@link urn.URNlink#getUcmModelElements <em>Ucm Model Elements</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for the reference '<em>Ucm Model Elements</em>'.
     * @see urn.URNlink#getUcmModelElements()
     * @see #getURNlink()
     * @generated
     */
    EReference getURNlink_UcmModelElements();

    /**
     * Returns the meta object for enum '{@link urn.URNLinkType <em>URN Link Type</em>}'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the meta object for enum '<em>URN Link Type</em>'.
     * @see urn.URNLinkType
     * @generated
     */
    EEnum getURNLinkType();

    /**
     * Returns the factory that creates the instances of the model.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the factory that creates the instances of the model.
     * @generated
     */
    UrnFactory getUrnFactory();

} //UrnPackage
