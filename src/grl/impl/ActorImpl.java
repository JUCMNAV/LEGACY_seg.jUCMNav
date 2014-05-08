/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package grl.impl;

import grl.Actor;
import grl.CollapsedActorRef;
import grl.GRLspec;
import grl.GrlPackage;

import grl.ImportanceType;
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

import urncore.IURNContainer;
import urncore.IURNContainerRef;
import urncore.UrncorePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Actor</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link grl.impl.ActorImpl#getLineColor <em>Line Color</em>}</li>
 *   <li>{@link grl.impl.ActorImpl#getFillColor <em>Fill Color</em>}</li>
 *   <li>{@link grl.impl.ActorImpl#isFilled <em>Filled</em>}</li>
 *   <li>{@link grl.impl.ActorImpl#getContRefs <em>Cont Refs</em>}</li>
 *   <li>{@link grl.impl.ActorImpl#getImportance <em>Importance</em>}</li>
 *   <li>{@link grl.impl.ActorImpl#getImportanceQuantitative <em>Importance Quantitative</em>}</li>
 *   <li>{@link grl.impl.ActorImpl#getGrlspec <em>Grlspec</em>}</li>
 *   <li>{@link grl.impl.ActorImpl#getIncludedActors <em>Included Actors</em>}</li>
 *   <li>{@link grl.impl.ActorImpl#getIncludingActor <em>Including Actor</em>}</li>
 *   <li>{@link grl.impl.ActorImpl#getCollapsedRefs <em>Collapsed Refs</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ActorImpl extends GRLLinkableElementImpl implements Actor {
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
	 * The cached value of the '{@link #getContRefs() <em>Cont Refs</em>}' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getContRefs()
	 * @generated
	 * @ordered
	 */
    protected EList contRefs;

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
	 * The cached value of the '{@link #getIncludedActors() <em>Included Actors</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIncludedActors()
	 * @generated
	 * @ordered
	 */
	protected EList includedActors;

				/**
	 * The cached value of the '{@link #getIncludingActor() <em>Including Actor</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIncludingActor()
	 * @generated
	 * @ordered
	 */
	protected Actor includingActor;

				/**
	 * The cached value of the '{@link #getCollapsedRefs() <em>Collapsed Refs</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCollapsedRefs()
	 * @generated
	 * @ordered
	 */
	protected EList collapsedRefs;

				/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected ActorImpl() {
		super();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass() {
		return GrlPackage.Literals.ACTOR;
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
			eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.ACTOR__LINE_COLOR, oldLineColor, lineColor));
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
			eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.ACTOR__FILL_COLOR, oldFillColor, fillColor));
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
			eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.ACTOR__FILLED, oldFilled, filled));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList getContRefs() {
		if (contRefs == null) {
			contRefs = new EObjectWithInverseResolvingEList(IURNContainerRef.class, this, GrlPackage.ACTOR__CONT_REFS, UrncorePackage.IURN_CONTAINER_REF__CONT_DEF);
		}
		return contRefs;
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
			eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.ACTOR__IMPORTANCE, oldImportance, importance));
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
			eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.ACTOR__IMPORTANCE_QUANTITATIVE, oldImportanceQuantitative, importanceQuantitative));
	}

				/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public GRLspec getGrlspec() {
		if (eContainerFeatureID() != GrlPackage.ACTOR__GRLSPEC) return null;
		return (GRLspec)eInternalContainer();
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetGrlspec(GRLspec newGrlspec, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newGrlspec, GrlPackage.ACTOR__GRLSPEC, msgs);
		return msgs;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setGrlspec(GRLspec newGrlspec) {
		if (newGrlspec != eInternalContainer() || (eContainerFeatureID() != GrlPackage.ACTOR__GRLSPEC && newGrlspec != null)) {
			if (EcoreUtil.isAncestor(this, newGrlspec))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newGrlspec != null)
				msgs = ((InternalEObject)newGrlspec).eInverseAdd(this, GrlPackage.GR_LSPEC__ACTORS, GRLspec.class, msgs);
			msgs = basicSetGrlspec(newGrlspec, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.ACTOR__GRLSPEC, newGrlspec, newGrlspec));
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getIncludedActors() {
		if (includedActors == null) {
			includedActors = new EObjectWithInverseResolvingEList(Actor.class, this, GrlPackage.ACTOR__INCLUDED_ACTORS, GrlPackage.ACTOR__INCLUDING_ACTOR);
		}
		return includedActors;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Actor getIncludingActor() {
		if (includingActor != null && includingActor.eIsProxy()) {
			InternalEObject oldIncludingActor = (InternalEObject)includingActor;
			includingActor = (Actor)eResolveProxy(oldIncludingActor);
			if (includingActor != oldIncludingActor) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, GrlPackage.ACTOR__INCLUDING_ACTOR, oldIncludingActor, includingActor));
			}
		}
		return includingActor;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Actor basicGetIncludingActor() {
		return includingActor;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetIncludingActor(Actor newIncludingActor, NotificationChain msgs) {
		Actor oldIncludingActor = includingActor;
		includingActor = newIncludingActor;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, GrlPackage.ACTOR__INCLUDING_ACTOR, oldIncludingActor, newIncludingActor);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIncludingActor(Actor newIncludingActor) {
		if (newIncludingActor != includingActor) {
			NotificationChain msgs = null;
			if (includingActor != null)
				msgs = ((InternalEObject)includingActor).eInverseRemove(this, GrlPackage.ACTOR__INCLUDED_ACTORS, Actor.class, msgs);
			if (newIncludingActor != null)
				msgs = ((InternalEObject)newIncludingActor).eInverseAdd(this, GrlPackage.ACTOR__INCLUDED_ACTORS, Actor.class, msgs);
			msgs = basicSetIncludingActor(newIncludingActor, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.ACTOR__INCLUDING_ACTOR, newIncludingActor, newIncludingActor));
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getCollapsedRefs() {
		if (collapsedRefs == null) {
			collapsedRefs = new EObjectWithInverseResolvingEList(CollapsedActorRef.class, this, GrlPackage.ACTOR__COLLAPSED_REFS, GrlPackage.COLLAPSED_ACTOR_REF__ACTOR);
		}
		return collapsedRefs;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case GrlPackage.ACTOR__CONT_REFS:
				return ((InternalEList)getContRefs()).basicAdd(otherEnd, msgs);
			case GrlPackage.ACTOR__GRLSPEC:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetGrlspec((GRLspec)otherEnd, msgs);
			case GrlPackage.ACTOR__INCLUDED_ACTORS:
				return ((InternalEList)getIncludedActors()).basicAdd(otherEnd, msgs);
			case GrlPackage.ACTOR__INCLUDING_ACTOR:
				if (includingActor != null)
					msgs = ((InternalEObject)includingActor).eInverseRemove(this, GrlPackage.ACTOR__INCLUDED_ACTORS, Actor.class, msgs);
				return basicSetIncludingActor((Actor)otherEnd, msgs);
			case GrlPackage.ACTOR__COLLAPSED_REFS:
				return ((InternalEList)getCollapsedRefs()).basicAdd(otherEnd, msgs);
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
			case GrlPackage.ACTOR__CONT_REFS:
				return ((InternalEList)getContRefs()).basicRemove(otherEnd, msgs);
			case GrlPackage.ACTOR__GRLSPEC:
				return basicSetGrlspec(null, msgs);
			case GrlPackage.ACTOR__INCLUDED_ACTORS:
				return ((InternalEList)getIncludedActors()).basicRemove(otherEnd, msgs);
			case GrlPackage.ACTOR__INCLUDING_ACTOR:
				return basicSetIncludingActor(null, msgs);
			case GrlPackage.ACTOR__COLLAPSED_REFS:
				return ((InternalEList)getCollapsedRefs()).basicRemove(otherEnd, msgs);
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
			case GrlPackage.ACTOR__GRLSPEC:
				return eInternalContainer().eInverseRemove(this, GrlPackage.GR_LSPEC__ACTORS, GRLspec.class, msgs);
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
			case GrlPackage.ACTOR__LINE_COLOR:
				return getLineColor();
			case GrlPackage.ACTOR__FILL_COLOR:
				return getFillColor();
			case GrlPackage.ACTOR__FILLED:
				return isFilled() ? Boolean.TRUE : Boolean.FALSE;
			case GrlPackage.ACTOR__CONT_REFS:
				return getContRefs();
			case GrlPackage.ACTOR__IMPORTANCE:
				return getImportance();
			case GrlPackage.ACTOR__IMPORTANCE_QUANTITATIVE:
				return new Integer(getImportanceQuantitative());
			case GrlPackage.ACTOR__GRLSPEC:
				return getGrlspec();
			case GrlPackage.ACTOR__INCLUDED_ACTORS:
				return getIncludedActors();
			case GrlPackage.ACTOR__INCLUDING_ACTOR:
				if (resolve) return getIncludingActor();
				return basicGetIncludingActor();
			case GrlPackage.ACTOR__COLLAPSED_REFS:
				return getCollapsedRefs();
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
			case GrlPackage.ACTOR__LINE_COLOR:
				setLineColor((String)newValue);
				return;
			case GrlPackage.ACTOR__FILL_COLOR:
				setFillColor((String)newValue);
				return;
			case GrlPackage.ACTOR__FILLED:
				setFilled(((Boolean)newValue).booleanValue());
				return;
			case GrlPackage.ACTOR__CONT_REFS:
				getContRefs().clear();
				getContRefs().addAll((Collection)newValue);
				return;
			case GrlPackage.ACTOR__IMPORTANCE:
				setImportance((ImportanceType)newValue);
				return;
			case GrlPackage.ACTOR__IMPORTANCE_QUANTITATIVE:
				setImportanceQuantitative(((Integer)newValue).intValue());
				return;
			case GrlPackage.ACTOR__GRLSPEC:
				setGrlspec((GRLspec)newValue);
				return;
			case GrlPackage.ACTOR__INCLUDED_ACTORS:
				getIncludedActors().clear();
				getIncludedActors().addAll((Collection)newValue);
				return;
			case GrlPackage.ACTOR__INCLUDING_ACTOR:
				setIncludingActor((Actor)newValue);
				return;
			case GrlPackage.ACTOR__COLLAPSED_REFS:
				getCollapsedRefs().clear();
				getCollapsedRefs().addAll((Collection)newValue);
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
			case GrlPackage.ACTOR__LINE_COLOR:
				setLineColor(LINE_COLOR_EDEFAULT);
				return;
			case GrlPackage.ACTOR__FILL_COLOR:
				setFillColor(FILL_COLOR_EDEFAULT);
				return;
			case GrlPackage.ACTOR__FILLED:
				setFilled(FILLED_EDEFAULT);
				return;
			case GrlPackage.ACTOR__CONT_REFS:
				getContRefs().clear();
				return;
			case GrlPackage.ACTOR__IMPORTANCE:
				setImportance(IMPORTANCE_EDEFAULT);
				return;
			case GrlPackage.ACTOR__IMPORTANCE_QUANTITATIVE:
				setImportanceQuantitative(IMPORTANCE_QUANTITATIVE_EDEFAULT);
				return;
			case GrlPackage.ACTOR__GRLSPEC:
				setGrlspec((GRLspec)null);
				return;
			case GrlPackage.ACTOR__INCLUDED_ACTORS:
				getIncludedActors().clear();
				return;
			case GrlPackage.ACTOR__INCLUDING_ACTOR:
				setIncludingActor((Actor)null);
				return;
			case GrlPackage.ACTOR__COLLAPSED_REFS:
				getCollapsedRefs().clear();
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
			case GrlPackage.ACTOR__LINE_COLOR:
				return LINE_COLOR_EDEFAULT == null ? lineColor != null : !LINE_COLOR_EDEFAULT.equals(lineColor);
			case GrlPackage.ACTOR__FILL_COLOR:
				return FILL_COLOR_EDEFAULT == null ? fillColor != null : !FILL_COLOR_EDEFAULT.equals(fillColor);
			case GrlPackage.ACTOR__FILLED:
				return filled != FILLED_EDEFAULT;
			case GrlPackage.ACTOR__CONT_REFS:
				return contRefs != null && !contRefs.isEmpty();
			case GrlPackage.ACTOR__IMPORTANCE:
				return importance != IMPORTANCE_EDEFAULT;
			case GrlPackage.ACTOR__IMPORTANCE_QUANTITATIVE:
				return importanceQuantitative != IMPORTANCE_QUANTITATIVE_EDEFAULT;
			case GrlPackage.ACTOR__GRLSPEC:
				return getGrlspec() != null;
			case GrlPackage.ACTOR__INCLUDED_ACTORS:
				return includedActors != null && !includedActors.isEmpty();
			case GrlPackage.ACTOR__INCLUDING_ACTOR:
				return includingActor != null;
			case GrlPackage.ACTOR__COLLAPSED_REFS:
				return collapsedRefs != null && !collapsedRefs.isEmpty();
		}
		return super.eIsSet(featureID);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public int eBaseStructuralFeatureID(int derivedFeatureID, Class baseClass) {
		if (baseClass == IURNContainer.class) {
			switch (derivedFeatureID) {
				case GrlPackage.ACTOR__LINE_COLOR: return UrncorePackage.IURN_CONTAINER__LINE_COLOR;
				case GrlPackage.ACTOR__FILL_COLOR: return UrncorePackage.IURN_CONTAINER__FILL_COLOR;
				case GrlPackage.ACTOR__FILLED: return UrncorePackage.IURN_CONTAINER__FILLED;
				case GrlPackage.ACTOR__CONT_REFS: return UrncorePackage.IURN_CONTAINER__CONT_REFS;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public int eDerivedStructuralFeatureID(int baseFeatureID, Class baseClass) {
		if (baseClass == IURNContainer.class) {
			switch (baseFeatureID) {
				case UrncorePackage.IURN_CONTAINER__LINE_COLOR: return GrlPackage.ACTOR__LINE_COLOR;
				case UrncorePackage.IURN_CONTAINER__FILL_COLOR: return GrlPackage.ACTOR__FILL_COLOR;
				case UrncorePackage.IURN_CONTAINER__FILLED: return GrlPackage.ACTOR__FILLED;
				case UrncorePackage.IURN_CONTAINER__CONT_REFS: return GrlPackage.ACTOR__CONT_REFS;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (lineColor: ");
		result.append(lineColor);
		result.append(", fillColor: ");
		result.append(fillColor);
		result.append(", filled: ");
		result.append(filled);
		result.append(", importance: ");
		result.append(importance);
		result.append(", importanceQuantitative: ");
		result.append(importanceQuantitative);
		result.append(')');
		return result.toString();
	}

} //ActorImpl
