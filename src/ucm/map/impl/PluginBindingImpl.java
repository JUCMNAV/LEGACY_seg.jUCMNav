/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ucm.map.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import ucm.map.Condition;
import ucm.map.InBinding;
import ucm.map.Map;
import ucm.map.MapPackage;
import ucm.map.OutBinding;
import ucm.map.PluginBinding;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Plugin Binding</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link ucm.map.impl.PluginBindingImpl#getId <em>Id</em>}</li>
 *   <li>{@link ucm.map.impl.PluginBindingImpl#getRepetitionCount <em>Repetition Count</em>}</li>
 *   <li>{@link ucm.map.impl.PluginBindingImpl#getProbability <em>Probability</em>}</li>
 *   <li>{@link ucm.map.impl.PluginBindingImpl#getIn <em>In</em>}</li>
 *   <li>{@link ucm.map.impl.PluginBindingImpl#getOut <em>Out</em>}</li>
 *   <li>{@link ucm.map.impl.PluginBindingImpl#getPlugin <em>Plugin</em>}</li>
 *   <li>{@link ucm.map.impl.PluginBindingImpl#getPrecondition <em>Precondition</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PluginBindingImpl extends EObjectImpl implements PluginBinding {
	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected String id = ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getRepetitionCount() <em>Repetition Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRepetitionCount()
	 * @generated
	 * @ordered
	 */
	protected static final int REPETITION_COUNT_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getRepetitionCount() <em>Repetition Count</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRepetitionCount()
	 * @generated
	 * @ordered
	 */
	protected int repetitionCount = REPETITION_COUNT_EDEFAULT;

	/**
	 * The default value of the '{@link #getProbability() <em>Probability</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProbability()
	 * @generated
	 * @ordered
	 */
	protected static final double PROBABILITY_EDEFAULT = 1.0;

	/**
	 * The cached value of the '{@link #getProbability() <em>Probability</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProbability()
	 * @generated
	 * @ordered
	 */
	protected double probability = PROBABILITY_EDEFAULT;

	/**
	 * The cached value of the '{@link #getIn() <em>In</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIn()
	 * @generated
	 * @ordered
	 */
	protected EList in = null;

	/**
	 * The cached value of the '{@link #getOut() <em>Out</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOut()
	 * @generated
	 * @ordered
	 */
	protected EList out = null;

	/**
	 * The cached value of the '{@link #getPlugin() <em>Plugin</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPlugin()
	 * @generated
	 * @ordered
	 */
	protected Map plugin = null;

	/**
	 * The cached value of the '{@link #getPrecondition() <em>Precondition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrecondition()
	 * @generated
	 * @ordered
	 */
	protected Condition precondition = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PluginBindingImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return MapPackage.eINSTANCE.getPluginBinding();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setId(String newId) {
		String oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MapPackage.PLUGIN_BINDING__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getRepetitionCount() {
		return repetitionCount;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRepetitionCount(int newRepetitionCount) {
		int oldRepetitionCount = repetitionCount;
		repetitionCount = newRepetitionCount;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MapPackage.PLUGIN_BINDING__REPETITION_COUNT, oldRepetitionCount, repetitionCount));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public double getProbability() {
		return probability;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setProbability(double newProbability) {
		double oldProbability = probability;
		probability = newProbability;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MapPackage.PLUGIN_BINDING__PROBABILITY, oldProbability, probability));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getIn() {
		if (in == null) {
			in = new EObjectContainmentEList(InBinding.class, this, MapPackage.PLUGIN_BINDING__IN);
		}
		return in;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getOut() {
		if (out == null) {
			out = new EObjectContainmentEList(OutBinding.class, this, MapPackage.PLUGIN_BINDING__OUT);
		}
		return out;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Map getPlugin() {
		if (plugin != null && plugin.eIsProxy()) {
			Map oldPlugin = plugin;
			plugin = (Map)eResolveProxy((InternalEObject)plugin);
			if (plugin != oldPlugin) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, MapPackage.PLUGIN_BINDING__PLUGIN, oldPlugin, plugin));
			}
		}
		return plugin;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Map basicGetPlugin() {
		return plugin;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPlugin(Map newPlugin, NotificationChain msgs) {
		Map oldPlugin = plugin;
		plugin = newPlugin;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MapPackage.PLUGIN_BINDING__PLUGIN, oldPlugin, newPlugin);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPlugin(Map newPlugin) {
		if (newPlugin != plugin) {
			NotificationChain msgs = null;
			if (plugin != null)
				msgs = ((InternalEObject)plugin).eInverseRemove(this, MapPackage.MAP__PARENT_STUB, Map.class, msgs);
			if (newPlugin != null)
				msgs = ((InternalEObject)newPlugin).eInverseAdd(this, MapPackage.MAP__PARENT_STUB, Map.class, msgs);
			msgs = basicSetPlugin(newPlugin, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MapPackage.PLUGIN_BINDING__PLUGIN, newPlugin, newPlugin));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Condition getPrecondition() {
		return precondition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPrecondition(Condition newPrecondition, NotificationChain msgs) {
		Condition oldPrecondition = precondition;
		precondition = newPrecondition;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MapPackage.PLUGIN_BINDING__PRECONDITION, oldPrecondition, newPrecondition);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPrecondition(Condition newPrecondition) {
		if (newPrecondition != precondition) {
			NotificationChain msgs = null;
			if (precondition != null)
				msgs = ((InternalEObject)precondition).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MapPackage.PLUGIN_BINDING__PRECONDITION, null, msgs);
			if (newPrecondition != null)
				msgs = ((InternalEObject)newPrecondition).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MapPackage.PLUGIN_BINDING__PRECONDITION, null, msgs);
			msgs = basicSetPrecondition(newPrecondition, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MapPackage.PLUGIN_BINDING__PRECONDITION, newPrecondition, newPrecondition));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case MapPackage.PLUGIN_BINDING__PLUGIN:
					if (plugin != null)
						msgs = ((InternalEObject)plugin).eInverseRemove(this, MapPackage.MAP__PARENT_STUB, Map.class, msgs);
					return basicSetPlugin((Map)otherEnd, msgs);
				default:
					return eDynamicInverseAdd(otherEnd, featureID, baseClass, msgs);
			}
		}
		if (eContainer != null)
			msgs = eBasicRemoveFromContainer(msgs);
		return eBasicSetContainer(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case MapPackage.PLUGIN_BINDING__IN:
					return ((InternalEList)getIn()).basicRemove(otherEnd, msgs);
				case MapPackage.PLUGIN_BINDING__OUT:
					return ((InternalEList)getOut()).basicRemove(otherEnd, msgs);
				case MapPackage.PLUGIN_BINDING__PLUGIN:
					return basicSetPlugin(null, msgs);
				case MapPackage.PLUGIN_BINDING__PRECONDITION:
					return basicSetPrecondition(null, msgs);
				default:
					return eDynamicInverseRemove(otherEnd, featureID, baseClass, msgs);
			}
		}
		return eBasicSetContainer(null, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case MapPackage.PLUGIN_BINDING__ID:
				return getId();
			case MapPackage.PLUGIN_BINDING__REPETITION_COUNT:
				return new Integer(getRepetitionCount());
			case MapPackage.PLUGIN_BINDING__PROBABILITY:
				return new Double(getProbability());
			case MapPackage.PLUGIN_BINDING__IN:
				return getIn();
			case MapPackage.PLUGIN_BINDING__OUT:
				return getOut();
			case MapPackage.PLUGIN_BINDING__PLUGIN:
				if (resolve) return getPlugin();
				return basicGetPlugin();
			case MapPackage.PLUGIN_BINDING__PRECONDITION:
				return getPrecondition();
		}
		return eDynamicGet(eFeature, resolve);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(EStructuralFeature eFeature, Object newValue) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case MapPackage.PLUGIN_BINDING__ID:
				setId((String)newValue);
				return;
			case MapPackage.PLUGIN_BINDING__REPETITION_COUNT:
				setRepetitionCount(((Integer)newValue).intValue());
				return;
			case MapPackage.PLUGIN_BINDING__PROBABILITY:
				setProbability(((Double)newValue).doubleValue());
				return;
			case MapPackage.PLUGIN_BINDING__IN:
				getIn().clear();
				getIn().addAll((Collection)newValue);
				return;
			case MapPackage.PLUGIN_BINDING__OUT:
				getOut().clear();
				getOut().addAll((Collection)newValue);
				return;
			case MapPackage.PLUGIN_BINDING__PLUGIN:
				setPlugin((Map)newValue);
				return;
			case MapPackage.PLUGIN_BINDING__PRECONDITION:
				setPrecondition((Condition)newValue);
				return;
		}
		eDynamicSet(eFeature, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eUnset(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case MapPackage.PLUGIN_BINDING__ID:
				setId(ID_EDEFAULT);
				return;
			case MapPackage.PLUGIN_BINDING__REPETITION_COUNT:
				setRepetitionCount(REPETITION_COUNT_EDEFAULT);
				return;
			case MapPackage.PLUGIN_BINDING__PROBABILITY:
				setProbability(PROBABILITY_EDEFAULT);
				return;
			case MapPackage.PLUGIN_BINDING__IN:
				getIn().clear();
				return;
			case MapPackage.PLUGIN_BINDING__OUT:
				getOut().clear();
				return;
			case MapPackage.PLUGIN_BINDING__PLUGIN:
				setPlugin((Map)null);
				return;
			case MapPackage.PLUGIN_BINDING__PRECONDITION:
				setPrecondition((Condition)null);
				return;
		}
		eDynamicUnset(eFeature);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case MapPackage.PLUGIN_BINDING__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case MapPackage.PLUGIN_BINDING__REPETITION_COUNT:
				return repetitionCount != REPETITION_COUNT_EDEFAULT;
			case MapPackage.PLUGIN_BINDING__PROBABILITY:
				return probability != PROBABILITY_EDEFAULT;
			case MapPackage.PLUGIN_BINDING__IN:
				return in != null && !in.isEmpty();
			case MapPackage.PLUGIN_BINDING__OUT:
				return out != null && !out.isEmpty();
			case MapPackage.PLUGIN_BINDING__PLUGIN:
				return plugin != null;
			case MapPackage.PLUGIN_BINDING__PRECONDITION:
				return precondition != null;
		}
		return eDynamicIsSet(eFeature);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (id: ");
		result.append(id);
		result.append(", repetitionCount: ");
		result.append(repetitionCount);
		result.append(", probability: ");
		result.append(probability);
		result.append(')');
		return result.toString();
	}

} //PluginBindingImpl
