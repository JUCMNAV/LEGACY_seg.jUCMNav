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
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import ucm.map.ComponentBinding;
import ucm.map.InBinding;
import ucm.map.MapPackage;
import ucm.map.OutBinding;
import ucm.map.PluginBinding;
import ucm.map.ResponsibilityBinding;
import ucm.map.Stub;
import ucm.map.UCMmap;
import urncore.Condition;
import urncore.UrncorePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Plugin Binding</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link ucm.map.impl.PluginBindingImpl#getId <em>Id</em>}</li>
 *   <li>{@link ucm.map.impl.PluginBindingImpl#getProbability <em>Probability</em>}</li>
 *   <li>{@link ucm.map.impl.PluginBindingImpl#isTransaction <em>Transaction</em>}</li>
 *   <li>{@link ucm.map.impl.PluginBindingImpl#getReplicationFactor <em>Replication Factor</em>}</li>
 *   <li>{@link ucm.map.impl.PluginBindingImpl#getIn <em>In</em>}</li>
 *   <li>{@link ucm.map.impl.PluginBindingImpl#getOut <em>Out</em>}</li>
 *   <li>{@link ucm.map.impl.PluginBindingImpl#getStub <em>Stub</em>}</li>
 *   <li>{@link ucm.map.impl.PluginBindingImpl#getPlugin <em>Plugin</em>}</li>
 *   <li>{@link ucm.map.impl.PluginBindingImpl#getPrecondition <em>Precondition</em>}</li>
 *   <li>{@link ucm.map.impl.PluginBindingImpl#getComponents <em>Components</em>}</li>
 *   <li>{@link ucm.map.impl.PluginBindingImpl#getResponsibilities <em>Responsibilities</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PluginBindingImpl extends MinimalEObjectImpl.Container implements PluginBinding {
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
	 * The default value of the '{@link #isTransaction() <em>Transaction</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isTransaction()
	 * @generated
	 * @ordered
	 */
	protected static final boolean TRANSACTION_EDEFAULT = false;

    /**
	 * The cached value of the '{@link #isTransaction() <em>Transaction</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isTransaction()
	 * @generated
	 * @ordered
	 */
	protected boolean transaction = TRANSACTION_EDEFAULT;

    /**
	 * The default value of the '{@link #getReplicationFactor() <em>Replication Factor</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReplicationFactor()
	 * @generated
	 * @ordered
	 */
	protected static final int REPLICATION_FACTOR_EDEFAULT = 1;

				/**
	 * The cached value of the '{@link #getReplicationFactor() <em>Replication Factor</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReplicationFactor()
	 * @generated
	 * @ordered
	 */
	protected int replicationFactor = REPLICATION_FACTOR_EDEFAULT;

				/**
	 * The cached value of the '{@link #getIn() <em>In</em>}' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getIn()
	 * @generated
	 * @ordered
	 */
    protected EList in;

    /**
	 * The cached value of the '{@link #getOut() <em>Out</em>}' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getOut()
	 * @generated
	 * @ordered
	 */
    protected EList out;

    /**
	 * The cached value of the '{@link #getPlugin() <em>Plugin</em>}' reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getPlugin()
	 * @generated
	 * @ordered
	 */
    protected UCMmap plugin;

    /**
	 * The cached value of the '{@link #getPrecondition() <em>Precondition</em>}' containment reference.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getPrecondition()
	 * @generated
	 * @ordered
	 */
    protected Condition precondition;

    /**
	 * The cached value of the '{@link #getComponents() <em>Components</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getComponents()
	 * @generated
	 * @ordered
	 */
	protected EList components;

				/**
	 * The cached value of the '{@link #getResponsibilities() <em>Responsibilities</em>}' containment reference list.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @see #getResponsibilities()
	 * @generated
	 * @ordered
	 */
    protected EList responsibilities;

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
		return MapPackage.Literals.PLUGIN_BINDING;
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
	public boolean isTransaction() {
		return transaction;
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTransaction(boolean newTransaction) {
		boolean oldTransaction = transaction;
		transaction = newTransaction;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MapPackage.PLUGIN_BINDING__TRANSACTION, oldTransaction, transaction));
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getReplicationFactor() {
		return replicationFactor;
	}

				/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReplicationFactor(int newReplicationFactor) {
		int oldReplicationFactor = replicationFactor;
		replicationFactor = newReplicationFactor;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MapPackage.PLUGIN_BINDING__REPLICATION_FACTOR, oldReplicationFactor, replicationFactor));
	}

				/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList getIn() {
		if (in == null) {
			in = new EObjectContainmentWithInverseEList(InBinding.class, this, MapPackage.PLUGIN_BINDING__IN, MapPackage.IN_BINDING__BINDING);
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
			out = new EObjectContainmentWithInverseEList(OutBinding.class, this, MapPackage.PLUGIN_BINDING__OUT, MapPackage.OUT_BINDING__BINDING);
		}
		return out;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public Stub getStub() {
		if (eContainerFeatureID() != MapPackage.PLUGIN_BINDING__STUB) return null;
		return (Stub)eInternalContainer();
	}

    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetStub(Stub newStub, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newStub, MapPackage.PLUGIN_BINDING__STUB, msgs);
		return msgs;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public void setStub(Stub newStub) {
		if (newStub != eInternalContainer() || (eContainerFeatureID() != MapPackage.PLUGIN_BINDING__STUB && newStub != null)) {
			if (EcoreUtil.isAncestor(this, newStub))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newStub != null)
				msgs = ((InternalEObject)newStub).eInverseAdd(this, MapPackage.STUB__BINDINGS, Stub.class, msgs);
			msgs = basicSetStub(newStub, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MapPackage.PLUGIN_BINDING__STUB, newStub, newStub));
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public UCMmap getPlugin() {
		if (plugin != null && plugin.eIsProxy()) {
			InternalEObject oldPlugin = (InternalEObject)plugin;
			plugin = (UCMmap)eResolveProxy(oldPlugin);
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
    public UCMmap basicGetPlugin() {
		return plugin;
	}

    /**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public NotificationChain basicSetPlugin(UCMmap newPlugin, NotificationChain msgs) {
		UCMmap oldPlugin = plugin;
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
    public void setPlugin(UCMmap newPlugin) {
		if (newPlugin != plugin) {
			NotificationChain msgs = null;
			if (plugin != null)
				msgs = ((InternalEObject)plugin).eInverseRemove(this, MapPackage.UC_MMAP__PARENT_STUB, UCMmap.class, msgs);
			if (newPlugin != null)
				msgs = ((InternalEObject)newPlugin).eInverseAdd(this, MapPackage.UC_MMAP__PARENT_STUB, UCMmap.class, msgs);
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
				msgs = ((InternalEObject)precondition).eInverseRemove(this, UrncorePackage.CONDITION__PLUGIN_BINDING, Condition.class, msgs);
			if (newPrecondition != null)
				msgs = ((InternalEObject)newPrecondition).eInverseAdd(this, UrncorePackage.CONDITION__PLUGIN_BINDING, Condition.class, msgs);
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
	public EList getComponents() {
		if (components == null) {
			components = new EObjectContainmentWithInverseEList(ComponentBinding.class, this, MapPackage.PLUGIN_BINDING__COMPONENTS, MapPackage.COMPONENT_BINDING__BINDING);
		}
		return components;
	}

				/**
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public EList getResponsibilities() {
		if (responsibilities == null) {
			responsibilities = new EObjectContainmentWithInverseEList(ResponsibilityBinding.class, this, MapPackage.PLUGIN_BINDING__RESPONSIBILITIES, MapPackage.RESPONSIBILITY_BINDING__BINDING);
		}
		return responsibilities;
	}

                /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case MapPackage.PLUGIN_BINDING__IN:
				return ((InternalEList)getIn()).basicAdd(otherEnd, msgs);
			case MapPackage.PLUGIN_BINDING__OUT:
				return ((InternalEList)getOut()).basicAdd(otherEnd, msgs);
			case MapPackage.PLUGIN_BINDING__STUB:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetStub((Stub)otherEnd, msgs);
			case MapPackage.PLUGIN_BINDING__PLUGIN:
				if (plugin != null)
					msgs = ((InternalEObject)plugin).eInverseRemove(this, MapPackage.UC_MMAP__PARENT_STUB, UCMmap.class, msgs);
				return basicSetPlugin((UCMmap)otherEnd, msgs);
			case MapPackage.PLUGIN_BINDING__PRECONDITION:
				if (precondition != null)
					msgs = ((InternalEObject)precondition).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MapPackage.PLUGIN_BINDING__PRECONDITION, null, msgs);
				return basicSetPrecondition((Condition)otherEnd, msgs);
			case MapPackage.PLUGIN_BINDING__COMPONENTS:
				return ((InternalEList)getComponents()).basicAdd(otherEnd, msgs);
			case MapPackage.PLUGIN_BINDING__RESPONSIBILITIES:
				return ((InternalEList)getResponsibilities()).basicAdd(otherEnd, msgs);
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
			case MapPackage.PLUGIN_BINDING__IN:
				return ((InternalEList)getIn()).basicRemove(otherEnd, msgs);
			case MapPackage.PLUGIN_BINDING__OUT:
				return ((InternalEList)getOut()).basicRemove(otherEnd, msgs);
			case MapPackage.PLUGIN_BINDING__STUB:
				return basicSetStub(null, msgs);
			case MapPackage.PLUGIN_BINDING__PLUGIN:
				return basicSetPlugin(null, msgs);
			case MapPackage.PLUGIN_BINDING__PRECONDITION:
				return basicSetPrecondition(null, msgs);
			case MapPackage.PLUGIN_BINDING__COMPONENTS:
				return ((InternalEList)getComponents()).basicRemove(otherEnd, msgs);
			case MapPackage.PLUGIN_BINDING__RESPONSIBILITIES:
				return ((InternalEList)getResponsibilities()).basicRemove(otherEnd, msgs);
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
			case MapPackage.PLUGIN_BINDING__STUB:
				return eInternalContainer().eInverseRemove(this, MapPackage.STUB__BINDINGS, Stub.class, msgs);
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
			case MapPackage.PLUGIN_BINDING__ID:
				return getId();
			case MapPackage.PLUGIN_BINDING__PROBABILITY:
				return new Double(getProbability());
			case MapPackage.PLUGIN_BINDING__TRANSACTION:
				return isTransaction() ? Boolean.TRUE : Boolean.FALSE;
			case MapPackage.PLUGIN_BINDING__REPLICATION_FACTOR:
				return new Integer(getReplicationFactor());
			case MapPackage.PLUGIN_BINDING__IN:
				return getIn();
			case MapPackage.PLUGIN_BINDING__OUT:
				return getOut();
			case MapPackage.PLUGIN_BINDING__STUB:
				return getStub();
			case MapPackage.PLUGIN_BINDING__PLUGIN:
				if (resolve) return getPlugin();
				return basicGetPlugin();
			case MapPackage.PLUGIN_BINDING__PRECONDITION:
				return getPrecondition();
			case MapPackage.PLUGIN_BINDING__COMPONENTS:
				return getComponents();
			case MapPackage.PLUGIN_BINDING__RESPONSIBILITIES:
				return getResponsibilities();
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
			case MapPackage.PLUGIN_BINDING__ID:
				setId((String)newValue);
				return;
			case MapPackage.PLUGIN_BINDING__PROBABILITY:
				setProbability(((Double)newValue).doubleValue());
				return;
			case MapPackage.PLUGIN_BINDING__TRANSACTION:
				setTransaction(((Boolean)newValue).booleanValue());
				return;
			case MapPackage.PLUGIN_BINDING__REPLICATION_FACTOR:
				setReplicationFactor(((Integer)newValue).intValue());
				return;
			case MapPackage.PLUGIN_BINDING__IN:
				getIn().clear();
				getIn().addAll((Collection)newValue);
				return;
			case MapPackage.PLUGIN_BINDING__OUT:
				getOut().clear();
				getOut().addAll((Collection)newValue);
				return;
			case MapPackage.PLUGIN_BINDING__STUB:
				setStub((Stub)newValue);
				return;
			case MapPackage.PLUGIN_BINDING__PLUGIN:
				setPlugin((UCMmap)newValue);
				return;
			case MapPackage.PLUGIN_BINDING__PRECONDITION:
				setPrecondition((Condition)newValue);
				return;
			case MapPackage.PLUGIN_BINDING__COMPONENTS:
				getComponents().clear();
				getComponents().addAll((Collection)newValue);
				return;
			case MapPackage.PLUGIN_BINDING__RESPONSIBILITIES:
				getResponsibilities().clear();
				getResponsibilities().addAll((Collection)newValue);
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
			case MapPackage.PLUGIN_BINDING__ID:
				setId(ID_EDEFAULT);
				return;
			case MapPackage.PLUGIN_BINDING__PROBABILITY:
				setProbability(PROBABILITY_EDEFAULT);
				return;
			case MapPackage.PLUGIN_BINDING__TRANSACTION:
				setTransaction(TRANSACTION_EDEFAULT);
				return;
			case MapPackage.PLUGIN_BINDING__REPLICATION_FACTOR:
				setReplicationFactor(REPLICATION_FACTOR_EDEFAULT);
				return;
			case MapPackage.PLUGIN_BINDING__IN:
				getIn().clear();
				return;
			case MapPackage.PLUGIN_BINDING__OUT:
				getOut().clear();
				return;
			case MapPackage.PLUGIN_BINDING__STUB:
				setStub((Stub)null);
				return;
			case MapPackage.PLUGIN_BINDING__PLUGIN:
				setPlugin((UCMmap)null);
				return;
			case MapPackage.PLUGIN_BINDING__PRECONDITION:
				setPrecondition((Condition)null);
				return;
			case MapPackage.PLUGIN_BINDING__COMPONENTS:
				getComponents().clear();
				return;
			case MapPackage.PLUGIN_BINDING__RESPONSIBILITIES:
				getResponsibilities().clear();
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
			case MapPackage.PLUGIN_BINDING__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case MapPackage.PLUGIN_BINDING__PROBABILITY:
				return probability != PROBABILITY_EDEFAULT;
			case MapPackage.PLUGIN_BINDING__TRANSACTION:
				return transaction != TRANSACTION_EDEFAULT;
			case MapPackage.PLUGIN_BINDING__REPLICATION_FACTOR:
				return replicationFactor != REPLICATION_FACTOR_EDEFAULT;
			case MapPackage.PLUGIN_BINDING__IN:
				return in != null && !in.isEmpty();
			case MapPackage.PLUGIN_BINDING__OUT:
				return out != null && !out.isEmpty();
			case MapPackage.PLUGIN_BINDING__STUB:
				return getStub() != null;
			case MapPackage.PLUGIN_BINDING__PLUGIN:
				return plugin != null;
			case MapPackage.PLUGIN_BINDING__PRECONDITION:
				return precondition != null;
			case MapPackage.PLUGIN_BINDING__COMPONENTS:
				return components != null && !components.isEmpty();
			case MapPackage.PLUGIN_BINDING__RESPONSIBILITIES:
				return responsibilities != null && !responsibilities.isEmpty();
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
		result.append(" (id: ");
		result.append(id);
		result.append(", probability: ");
		result.append(probability);
		result.append(", transaction: ");
		result.append(transaction);
		result.append(", replicationFactor: ");
		result.append(replicationFactor);
		result.append(')');
		return result.toString();
	}

} //PluginBindingImpl
