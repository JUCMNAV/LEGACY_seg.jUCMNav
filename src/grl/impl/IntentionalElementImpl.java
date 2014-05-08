/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package grl.impl;

import grl.DecompositionType;
import grl.GRLspec;
import grl.GrlPackage;
import grl.ImportanceType;
import grl.IntentionalElement;
import grl.IntentionalElementRef;
import grl.IntentionalElementType;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Intentional Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link grl.impl.IntentionalElementImpl#getType <em>Type</em>}</li>
 *   <li>{@link grl.impl.IntentionalElementImpl#getDecompositionType <em>Decomposition Type</em>}</li>
 *   <li>{@link grl.impl.IntentionalElementImpl#getImportance <em>Importance</em>}</li>
 *   <li>{@link grl.impl.IntentionalElementImpl#getImportanceQuantitative <em>Importance Quantitative</em>}</li>
 *   <li>{@link grl.impl.IntentionalElementImpl#getLineColor <em>Line Color</em>}</li>
 *   <li>{@link grl.impl.IntentionalElementImpl#getFillColor <em>Fill Color</em>}</li>
 *   <li>{@link grl.impl.IntentionalElementImpl#isFilled <em>Filled</em>}</li>
 *   <li>{@link grl.impl.IntentionalElementImpl#getGrlspec <em>Grlspec</em>}</li>
 *   <li>{@link grl.impl.IntentionalElementImpl#getRefs <em>Refs</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class IntentionalElementImpl extends GRLLinkableElementImpl implements IntentionalElement {
    /**
	 * The default value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
    protected static final IntentionalElementType TYPE_EDEFAULT = IntentionalElementType.SOFTGOAL_LITERAL;

    /**
	 * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
    protected IntentionalElementType type = TYPE_EDEFAULT;

    /**
	 * The default value of the '{@link #getDecompositionType() <em>Decomposition Type</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getDecompositionType()
	 * @generated
	 * @ordered
	 */
    protected static final DecompositionType DECOMPOSITION_TYPE_EDEFAULT = DecompositionType.AND_LITERAL;

    /**
	 * The cached value of the '{@link #getDecompositionType() <em>Decomposition Type</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getDecompositionType()
	 * @generated
	 * @ordered
	 */
    protected DecompositionType decompositionType = DECOMPOSITION_TYPE_EDEFAULT;

    /**
	 * The default value of the '{@link #getImportance() <em>Importance</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImportance()
	 * @generated
	 * @ordered
	 */
	protected static final ImportanceType IMPORTANCE_EDEFAULT = ImportanceType.NONE_LITERAL;

				/**
	 * The cached value of the '{@link #getImportance() <em>Importance</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImportance()
	 * @generated
	 * @ordered
	 */
	protected ImportanceType importance = IMPORTANCE_EDEFAULT;

				/**
	 * The default value of the '{@link #getImportanceQuantitative() <em>Importance Quantitative</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImportanceQuantitative()
	 * @generated
	 * @ordered
	 */
	protected static final int IMPORTANCE_QUANTITATIVE_EDEFAULT = 0;

				/**
	 * The cached value of the '{@link #getImportanceQuantitative() <em>Importance Quantitative</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getImportanceQuantitative()
	 * @generated
	 * @ordered
	 */
	protected int importanceQuantitative = IMPORTANCE_QUANTITATIVE_EDEFAULT;

				/**
	 * The default value of the '{@link #getLineColor() <em>Line Color</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getLineColor()
	 * @generated
	 * @ordered
	 */
    protected static final String LINE_COLOR_EDEFAULT = null;

    /**
	 * The cached value of the '{@link #getLineColor() <em>Line Color</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getLineColor()
	 * @generated
	 * @ordered
	 */
    protected String lineColor = LINE_COLOR_EDEFAULT;

    /**
	 * The default value of the '{@link #getFillColor() <em>Fill Color</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getFillColor()
	 * @generated
	 * @ordered
	 */
    protected static final String FILL_COLOR_EDEFAULT = null;

    /**
	 * The cached value of the '{@link #getFillColor() <em>Fill Color</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getFillColor()
	 * @generated
	 * @ordered
	 */
    protected String fillColor = FILL_COLOR_EDEFAULT;

    /**
	 * The default value of the '{@link #isFilled() <em>Filled</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isFilled()
	 * @generated
	 * @ordered
	 */
    protected static final boolean FILLED_EDEFAULT = false;

    /**
	 * The cached value of the '{@link #isFilled() <em>Filled</em>}' attribute.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #isFilled()
	 * @generated
	 * @ordered
	 */
    protected boolean filled = FILLED_EDEFAULT;

    /**
	 * The cached value of the '{@link #getRefs() <em>Refs</em>}' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getRefs()
	 * @generated
	 * @ordered
	 */
    protected EList refs;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected IntentionalElementImpl() {
		super();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass() {
		return GrlPackage.Literals.INTENTIONAL_ELEMENT;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public IntentionalElementType getType() {
		return type;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setType(IntentionalElementType newType) {
		IntentionalElementType oldType = type;
		type = newType == null ? TYPE_EDEFAULT : newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.INTENTIONAL_ELEMENT__TYPE, oldType, type));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public DecompositionType getDecompositionType() {
		return decompositionType;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setDecompositionType(DecompositionType newDecompositionType) {
		DecompositionType oldDecompositionType = decompositionType;
		decompositionType = newDecompositionType == null ? DECOMPOSITION_TYPE_EDEFAULT : newDecompositionType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.INTENTIONAL_ELEMENT__DECOMPOSITION_TYPE, oldDecompositionType, decompositionType));
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ImportanceType getImportance() {
		return importance;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setImportance(ImportanceType newImportance) {
		ImportanceType oldImportance = importance;
		importance = newImportance == null ? IMPORTANCE_EDEFAULT : newImportance;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.INTENTIONAL_ELEMENT__IMPORTANCE, oldImportance, importance));
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getImportanceQuantitative() {
		return importanceQuantitative;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setImportanceQuantitative(int newImportanceQuantitative) {
		int oldImportanceQuantitative = importanceQuantitative;
		importanceQuantitative = newImportanceQuantitative;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.INTENTIONAL_ELEMENT__IMPORTANCE_QUANTITATIVE, oldImportanceQuantitative, importanceQuantitative));
	}

				/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public String getLineColor() {
		return lineColor;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setLineColor(String newLineColor) {
		String oldLineColor = lineColor;
		lineColor = newLineColor;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.INTENTIONAL_ELEMENT__LINE_COLOR, oldLineColor, lineColor));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public String getFillColor() {
		return fillColor;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setFillColor(String newFillColor) {
		String oldFillColor = fillColor;
		fillColor = newFillColor;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.INTENTIONAL_ELEMENT__FILL_COLOR, oldFillColor, fillColor));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public boolean isFilled() {
		return filled;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setFilled(boolean newFilled) {
		boolean oldFilled = filled;
		filled = newFilled;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.INTENTIONAL_ELEMENT__FILLED, oldFilled, filled));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public GRLspec getGrlspec() {
		if (eContainerFeatureID() != GrlPackage.INTENTIONAL_ELEMENT__GRLSPEC) return null;
		return (GRLspec)eInternalContainer();
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetGrlspec(GRLspec newGrlspec, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newGrlspec, GrlPackage.INTENTIONAL_ELEMENT__GRLSPEC, msgs);
		return msgs;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setGrlspec(GRLspec newGrlspec) {
		if (newGrlspec != eInternalContainer() || (eContainerFeatureID() != GrlPackage.INTENTIONAL_ELEMENT__GRLSPEC && newGrlspec != null)) {
			if (EcoreUtil.isAncestor(this, newGrlspec))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newGrlspec != null)
				msgs = ((InternalEObject)newGrlspec).eInverseAdd(this, GrlPackage.GR_LSPEC__INT_ELEMENTS, GRLspec.class, msgs);
			msgs = basicSetGrlspec(newGrlspec, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.INTENTIONAL_ELEMENT__GRLSPEC, newGrlspec, newGrlspec));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList getRefs() {
		if (refs == null) {
			refs = new EObjectWithInverseResolvingEList(IntentionalElementRef.class, this, GrlPackage.INTENTIONAL_ELEMENT__REFS, GrlPackage.INTENTIONAL_ELEMENT_REF__DEF);
		}
		return refs;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case GrlPackage.INTENTIONAL_ELEMENT__GRLSPEC:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetGrlspec((GRLspec)otherEnd, msgs);
			case GrlPackage.INTENTIONAL_ELEMENT__REFS:
				return ((InternalEList)getRefs()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case GrlPackage.INTENTIONAL_ELEMENT__GRLSPEC:
				return basicSetGrlspec(null, msgs);
			case GrlPackage.INTENTIONAL_ELEMENT__REFS:
				return ((InternalEList)getRefs()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case GrlPackage.INTENTIONAL_ELEMENT__GRLSPEC:
				return eInternalContainer().eInverseRemove(this, GrlPackage.GR_LSPEC__INT_ELEMENTS, GRLspec.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case GrlPackage.INTENTIONAL_ELEMENT__TYPE:
				return getType();
			case GrlPackage.INTENTIONAL_ELEMENT__DECOMPOSITION_TYPE:
				return getDecompositionType();
			case GrlPackage.INTENTIONAL_ELEMENT__IMPORTANCE:
				return getImportance();
			case GrlPackage.INTENTIONAL_ELEMENT__IMPORTANCE_QUANTITATIVE:
				return new Integer(getImportanceQuantitative());
			case GrlPackage.INTENTIONAL_ELEMENT__LINE_COLOR:
				return getLineColor();
			case GrlPackage.INTENTIONAL_ELEMENT__FILL_COLOR:
				return getFillColor();
			case GrlPackage.INTENTIONAL_ELEMENT__FILLED:
				return isFilled() ? Boolean.TRUE : Boolean.FALSE;
			case GrlPackage.INTENTIONAL_ELEMENT__GRLSPEC:
				return getGrlspec();
			case GrlPackage.INTENTIONAL_ELEMENT__REFS:
				return getRefs();
		}
		return super.eGet(featureID, resolve, coreType);
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case GrlPackage.INTENTIONAL_ELEMENT__TYPE:
				setType((IntentionalElementType)newValue);
				return;
			case GrlPackage.INTENTIONAL_ELEMENT__DECOMPOSITION_TYPE:
				setDecompositionType((DecompositionType)newValue);
				return;
			case GrlPackage.INTENTIONAL_ELEMENT__IMPORTANCE:
				setImportance((ImportanceType)newValue);
				return;
			case GrlPackage.INTENTIONAL_ELEMENT__IMPORTANCE_QUANTITATIVE:
				setImportanceQuantitative(((Integer)newValue).intValue());
				return;
			case GrlPackage.INTENTIONAL_ELEMENT__LINE_COLOR:
				setLineColor((String)newValue);
				return;
			case GrlPackage.INTENTIONAL_ELEMENT__FILL_COLOR:
				setFillColor((String)newValue);
				return;
			case GrlPackage.INTENTIONAL_ELEMENT__FILLED:
				setFilled(((Boolean)newValue).booleanValue());
				return;
			case GrlPackage.INTENTIONAL_ELEMENT__GRLSPEC:
				setGrlspec((GRLspec)newValue);
				return;
			case GrlPackage.INTENTIONAL_ELEMENT__REFS:
				getRefs().clear();
				getRefs().addAll((Collection)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eUnset(int featureID) {
		switch (featureID) {
			case GrlPackage.INTENTIONAL_ELEMENT__TYPE:
				setType(TYPE_EDEFAULT);
				return;
			case GrlPackage.INTENTIONAL_ELEMENT__DECOMPOSITION_TYPE:
				setDecompositionType(DECOMPOSITION_TYPE_EDEFAULT);
				return;
			case GrlPackage.INTENTIONAL_ELEMENT__IMPORTANCE:
				setImportance(IMPORTANCE_EDEFAULT);
				return;
			case GrlPackage.INTENTIONAL_ELEMENT__IMPORTANCE_QUANTITATIVE:
				setImportanceQuantitative(IMPORTANCE_QUANTITATIVE_EDEFAULT);
				return;
			case GrlPackage.INTENTIONAL_ELEMENT__LINE_COLOR:
				setLineColor(LINE_COLOR_EDEFAULT);
				return;
			case GrlPackage.INTENTIONAL_ELEMENT__FILL_COLOR:
				setFillColor(FILL_COLOR_EDEFAULT);
				return;
			case GrlPackage.INTENTIONAL_ELEMENT__FILLED:
				setFilled(FILLED_EDEFAULT);
				return;
			case GrlPackage.INTENTIONAL_ELEMENT__GRLSPEC:
				setGrlspec((GRLspec)null);
				return;
			case GrlPackage.INTENTIONAL_ELEMENT__REFS:
				getRefs().clear();
				return;
		}
		super.eUnset(featureID);
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case GrlPackage.INTENTIONAL_ELEMENT__TYPE:
				return type != TYPE_EDEFAULT;
			case GrlPackage.INTENTIONAL_ELEMENT__DECOMPOSITION_TYPE:
				return decompositionType != DECOMPOSITION_TYPE_EDEFAULT;
			case GrlPackage.INTENTIONAL_ELEMENT__IMPORTANCE:
				return importance != IMPORTANCE_EDEFAULT;
			case GrlPackage.INTENTIONAL_ELEMENT__IMPORTANCE_QUANTITATIVE:
				return importanceQuantitative != IMPORTANCE_QUANTITATIVE_EDEFAULT;
			case GrlPackage.INTENTIONAL_ELEMENT__LINE_COLOR:
				return LINE_COLOR_EDEFAULT == null ? lineColor != null : !LINE_COLOR_EDEFAULT.equals(lineColor);
			case GrlPackage.INTENTIONAL_ELEMENT__FILL_COLOR:
				return FILL_COLOR_EDEFAULT == null ? fillColor != null : !FILL_COLOR_EDEFAULT.equals(fillColor);
			case GrlPackage.INTENTIONAL_ELEMENT__FILLED:
				return filled != FILLED_EDEFAULT;
			case GrlPackage.INTENTIONAL_ELEMENT__GRLSPEC:
				return getGrlspec() != null;
			case GrlPackage.INTENTIONAL_ELEMENT__REFS:
				return refs != null && !refs.isEmpty();
		}
		return super.eIsSet(featureID);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (type: ");
		result.append(type);
		result.append(", decompositionType: ");
		result.append(decompositionType);
		result.append(", importance: ");
		result.append(importance);
		result.append(", importanceQuantitative: ");
		result.append(importanceQuantitative);
		result.append(", lineColor: ");
		result.append(lineColor);
		result.append(", fillColor: ");
		result.append(fillColor);
		result.append(", filled: ");
		result.append(filled);
		result.append(')');
		return result.toString();
	}

} //IntentionalElementImpl
