/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package grl.impl;

import grl.EvaluationStrategy;
import grl.GRLspec;
import grl.GrlPackage;
import grl.StrategiesGroup;
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
import org.eclipse.swt.widgets.Display;
import urncore.impl.GRLmodelElementImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Strategies Group</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link grl.impl.StrategiesGroupImpl#getStrategies <em>Strategies</em>}</li>
 *   <li>{@link grl.impl.StrategiesGroupImpl#getGrlspec <em>Grlspec</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class StrategiesGroupImpl extends GRLmodelElementImpl implements StrategiesGroup {
    /**
	 * The cached value of the '{@link #getStrategies() <em>Strategies</em>}' reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getStrategies()
	 * @generated
	 * @ordered
	 */
    protected EList strategies;

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected StrategiesGroupImpl() {
		super();
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected EClass eStaticClass() {
		return GrlPackage.Literals.STRATEGIES_GROUP;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList getStrategies() {
		if (strategies == null) {
			strategies = new EObjectWithInverseResolvingEList(EvaluationStrategy.class, this, GrlPackage.STRATEGIES_GROUP__STRATEGIES, GrlPackage.EVALUATION_STRATEGY__GROUP);
		}
		return strategies;
	}
    
    public void sortStrategies() {
    	
    	Display.getDefault().syncExec(new Runnable() {
			public void run() {
				//ECollections.sort(strategies); doesn't work because grl.impl.EvaluationStrategyImpl cannot be cast to java.lang.comparable
			}
		});
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public GRLspec getGrlspec() {
		if (eContainerFeatureID() != GrlPackage.STRATEGIES_GROUP__GRLSPEC) return null;
		return (GRLspec)eInternalContainer();
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetGrlspec(GRLspec newGrlspec, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newGrlspec, GrlPackage.STRATEGIES_GROUP__GRLSPEC, msgs);
		return msgs;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setGrlspec(GRLspec newGrlspec) {
		if (newGrlspec != eInternalContainer() || (eContainerFeatureID() != GrlPackage.STRATEGIES_GROUP__GRLSPEC && newGrlspec != null)) {
			if (EcoreUtil.isAncestor(this, newGrlspec))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newGrlspec != null)
				msgs = ((InternalEObject)newGrlspec).eInverseAdd(this, GrlPackage.GR_LSPEC__GROUPS, GRLspec.class, msgs);
			msgs = basicSetGrlspec(newGrlspec, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, GrlPackage.STRATEGIES_GROUP__GRLSPEC, newGrlspec, newGrlspec));
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case GrlPackage.STRATEGIES_GROUP__STRATEGIES:
				return ((InternalEList)getStrategies()).basicAdd(otherEnd, msgs);
			case GrlPackage.STRATEGIES_GROUP__GRLSPEC:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetGrlspec((GRLspec)otherEnd, msgs);
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
			case GrlPackage.STRATEGIES_GROUP__STRATEGIES:
				return ((InternalEList)getStrategies()).basicRemove(otherEnd, msgs);
			case GrlPackage.STRATEGIES_GROUP__GRLSPEC:
				return basicSetGrlspec(null, msgs);
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
			case GrlPackage.STRATEGIES_GROUP__GRLSPEC:
				return eInternalContainer().eInverseRemove(this, GrlPackage.GR_LSPEC__GROUPS, GRLspec.class, msgs);
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
			case GrlPackage.STRATEGIES_GROUP__STRATEGIES:
				return getStrategies();
			case GrlPackage.STRATEGIES_GROUP__GRLSPEC:
				return getGrlspec();
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
			case GrlPackage.STRATEGIES_GROUP__STRATEGIES:
				getStrategies().clear();
				getStrategies().addAll((Collection)newValue);
				return;
			case GrlPackage.STRATEGIES_GROUP__GRLSPEC:
				setGrlspec((GRLspec)newValue);
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
			case GrlPackage.STRATEGIES_GROUP__STRATEGIES:
				getStrategies().clear();
				return;
			case GrlPackage.STRATEGIES_GROUP__GRLSPEC:
				setGrlspec((GRLspec)null);
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
			case GrlPackage.STRATEGIES_GROUP__STRATEGIES:
				return strategies != null && !strategies.isEmpty();
			case GrlPackage.STRATEGIES_GROUP__GRLSPEC:
				return getGrlspec() != null;
		}
		return super.eIsSet(featureID);
	}

} //StrategiesGroupImpl
