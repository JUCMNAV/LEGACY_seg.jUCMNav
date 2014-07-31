/**
 */
package asd.impl;

import asd.ASDspec;
import asd.ASNetwork;
import asd.AsdPackage;
import asd.Community;
import asd.DivisionOfLabour;
import asd.Motivation;
import asd.Outcome;
import asd.Rule;
import asd.Subject;
import asd.Tool;
import java.util.Collection;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>AS Dspec</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link asd.impl.ASDspecImpl#getASNetwork <em>AS Network</em>}</li>
 *   <li>{@link asd.impl.ASDspecImpl#getMotivation <em>Motivation</em>}</li>
 *   <li>{@link asd.impl.ASDspecImpl#getOutcome <em>Outcome</em>}</li>
 *   <li>{@link asd.impl.ASDspecImpl#getDols <em>Dols</em>}</li>
 *   <li>{@link asd.impl.ASDspecImpl#getSubjects <em>Subjects</em>}</li>
 *   <li>{@link asd.impl.ASDspecImpl#getCommunities <em>Communities</em>}</li>
 *   <li>{@link asd.impl.ASDspecImpl#getObjects <em>Objects</em>}</li>
 *   <li>{@link asd.impl.ASDspecImpl#getOutcomes <em>Outcomes</em>}</li>
 *   <li>{@link asd.impl.ASDspecImpl#getMotivations <em>Motivations</em>}</li>
 *   <li>{@link asd.impl.ASDspecImpl#getTools <em>Tools</em>}</li>
 *   <li>{@link asd.impl.ASDspecImpl#getRules <em>Rules</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ASDspecImpl extends MinimalEObjectImpl.Container implements ASDspec {
	/**
	 * The cached value of the '{@link #getASNetwork() <em>AS Network</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getASNetwork()
	 * @generated
	 * @ordered
	 */
	protected EList asNetwork;

	/**
	 * The cached value of the '{@link #getMotivation() <em>Motivation</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMotivation()
	 * @generated
	 * @ordered
	 */
	protected EList motivation;

	/**
	 * The cached value of the '{@link #getOutcome() <em>Outcome</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutcome()
	 * @generated
	 * @ordered
	 */
	protected EList outcome;

	/**
	 * The cached value of the '{@link #getDols() <em>Dols</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDols()
	 * @generated
	 * @ordered
	 */
	protected EList dols;

	/**
	 * The cached value of the '{@link #getSubjects() <em>Subjects</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSubjects()
	 * @generated
	 * @ordered
	 */
	protected EList subjects;

	/**
	 * The cached value of the '{@link #getCommunities() <em>Communities</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCommunities()
	 * @generated
	 * @ordered
	 */
	protected EList communities;

	/**
	 * The cached value of the '{@link #getObjects() <em>Objects</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getObjects()
	 * @generated
	 * @ordered
	 */
	protected EList objects;

	/**
	 * The cached value of the '{@link #getOutcomes() <em>Outcomes</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOutcomes()
	 * @generated
	 * @ordered
	 */
	protected EList outcomes;

	/**
	 * The cached value of the '{@link #getMotivations() <em>Motivations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMotivations()
	 * @generated
	 * @ordered
	 */
	protected EList motivations;

	/**
	 * The cached value of the '{@link #getTools() <em>Tools</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTools()
	 * @generated
	 * @ordered
	 */
	protected EList tools;

	/**
	 * The cached value of the '{@link #getRules() <em>Rules</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRules()
	 * @generated
	 * @ordered
	 */
	protected EList rules;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ASDspecImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return AsdPackage.Literals.AS_DSPEC;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getASNetwork() {
		if (asNetwork == null) {
			asNetwork = new EObjectWithInverseResolvingEList.ManyInverse(ASNetwork.class, this, AsdPackage.AS_DSPEC__AS_NETWORK, AsdPackage.AS_NETWORK__AS_DSPEC);
		}
		return asNetwork;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getMotivation() {
		if (motivation == null) {
			motivation = new EObjectResolvingEList(Motivation.class, this, AsdPackage.AS_DSPEC__MOTIVATION);
		}
		return motivation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getOutcome() {
		if (outcome == null) {
			outcome = new EObjectResolvingEList(Outcome.class, this, AsdPackage.AS_DSPEC__OUTCOME);
		}
		return outcome;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getDols() {
		if (dols == null) {
			dols = new EObjectContainmentWithInverseEList(DivisionOfLabour.class, this, AsdPackage.AS_DSPEC__DOLS, AsdPackage.DIVISION_OF_LABOUR__ASD_SPEC);
		}
		return dols;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getSubjects() {
		if (subjects == null) {
			subjects = new EObjectContainmentWithInverseEList(Subject.class, this, AsdPackage.AS_DSPEC__SUBJECTS, AsdPackage.SUBJECT__ASD_SPEC);
		}
		return subjects;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getCommunities() {
		if (communities == null) {
			communities = new EObjectContainmentWithInverseEList(Community.class, this, AsdPackage.AS_DSPEC__COMMUNITIES, AsdPackage.COMMUNITY__ASD_SPEC);
		}
		return communities;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getObjects() {
		if (objects == null) {
			objects = new EObjectContainmentWithInverseEList(asd.Object.class, this, AsdPackage.AS_DSPEC__OBJECTS, AsdPackage.OBJECT__ASD_SPEC);
		}
		return objects;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getOutcomes() {
		if (outcomes == null) {
			outcomes = new EObjectContainmentWithInverseEList(Outcome.class, this, AsdPackage.AS_DSPEC__OUTCOMES, AsdPackage.OUTCOME__ASD_SPEC);
		}
		return outcomes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getMotivations() {
		if (motivations == null) {
			motivations = new EObjectContainmentWithInverseEList(Motivation.class, this, AsdPackage.AS_DSPEC__MOTIVATIONS, AsdPackage.MOTIVATION__ASD_SPEC);
		}
		return motivations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getTools() {
		if (tools == null) {
			tools = new EObjectContainmentWithInverseEList(Tool.class, this, AsdPackage.AS_DSPEC__TOOLS, AsdPackage.TOOL__ASD_SPEC);
		}
		return tools;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getRules() {
		if (rules == null) {
			rules = new EObjectContainmentWithInverseEList(Rule.class, this, AsdPackage.AS_DSPEC__RULES, AsdPackage.RULE__ASD_SPEC);
		}
		return rules;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case AsdPackage.AS_DSPEC__AS_NETWORK:
				return ((InternalEList)getASNetwork()).basicAdd(otherEnd, msgs);
			case AsdPackage.AS_DSPEC__DOLS:
				return ((InternalEList)getDols()).basicAdd(otherEnd, msgs);
			case AsdPackage.AS_DSPEC__SUBJECTS:
				return ((InternalEList)getSubjects()).basicAdd(otherEnd, msgs);
			case AsdPackage.AS_DSPEC__COMMUNITIES:
				return ((InternalEList)getCommunities()).basicAdd(otherEnd, msgs);
			case AsdPackage.AS_DSPEC__OBJECTS:
				return ((InternalEList)getObjects()).basicAdd(otherEnd, msgs);
			case AsdPackage.AS_DSPEC__OUTCOMES:
				return ((InternalEList)getOutcomes()).basicAdd(otherEnd, msgs);
			case AsdPackage.AS_DSPEC__MOTIVATIONS:
				return ((InternalEList)getMotivations()).basicAdd(otherEnd, msgs);
			case AsdPackage.AS_DSPEC__TOOLS:
				return ((InternalEList)getTools()).basicAdd(otherEnd, msgs);
			case AsdPackage.AS_DSPEC__RULES:
				return ((InternalEList)getRules()).basicAdd(otherEnd, msgs);
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
			case AsdPackage.AS_DSPEC__AS_NETWORK:
				return ((InternalEList)getASNetwork()).basicRemove(otherEnd, msgs);
			case AsdPackage.AS_DSPEC__DOLS:
				return ((InternalEList)getDols()).basicRemove(otherEnd, msgs);
			case AsdPackage.AS_DSPEC__SUBJECTS:
				return ((InternalEList)getSubjects()).basicRemove(otherEnd, msgs);
			case AsdPackage.AS_DSPEC__COMMUNITIES:
				return ((InternalEList)getCommunities()).basicRemove(otherEnd, msgs);
			case AsdPackage.AS_DSPEC__OBJECTS:
				return ((InternalEList)getObjects()).basicRemove(otherEnd, msgs);
			case AsdPackage.AS_DSPEC__OUTCOMES:
				return ((InternalEList)getOutcomes()).basicRemove(otherEnd, msgs);
			case AsdPackage.AS_DSPEC__MOTIVATIONS:
				return ((InternalEList)getMotivations()).basicRemove(otherEnd, msgs);
			case AsdPackage.AS_DSPEC__TOOLS:
				return ((InternalEList)getTools()).basicRemove(otherEnd, msgs);
			case AsdPackage.AS_DSPEC__RULES:
				return ((InternalEList)getRules()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case AsdPackage.AS_DSPEC__AS_NETWORK:
				return getASNetwork();
			case AsdPackage.AS_DSPEC__MOTIVATION:
				return getMotivation();
			case AsdPackage.AS_DSPEC__OUTCOME:
				return getOutcome();
			case AsdPackage.AS_DSPEC__DOLS:
				return getDols();
			case AsdPackage.AS_DSPEC__SUBJECTS:
				return getSubjects();
			case AsdPackage.AS_DSPEC__COMMUNITIES:
				return getCommunities();
			case AsdPackage.AS_DSPEC__OBJECTS:
				return getObjects();
			case AsdPackage.AS_DSPEC__OUTCOMES:
				return getOutcomes();
			case AsdPackage.AS_DSPEC__MOTIVATIONS:
				return getMotivations();
			case AsdPackage.AS_DSPEC__TOOLS:
				return getTools();
			case AsdPackage.AS_DSPEC__RULES:
				return getRules();
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
			case AsdPackage.AS_DSPEC__AS_NETWORK:
				getASNetwork().clear();
				getASNetwork().addAll((Collection)newValue);
				return;
			case AsdPackage.AS_DSPEC__MOTIVATION:
				getMotivation().clear();
				getMotivation().addAll((Collection)newValue);
				return;
			case AsdPackage.AS_DSPEC__OUTCOME:
				getOutcome().clear();
				getOutcome().addAll((Collection)newValue);
				return;
			case AsdPackage.AS_DSPEC__DOLS:
				getDols().clear();
				getDols().addAll((Collection)newValue);
				return;
			case AsdPackage.AS_DSPEC__SUBJECTS:
				getSubjects().clear();
				getSubjects().addAll((Collection)newValue);
				return;
			case AsdPackage.AS_DSPEC__COMMUNITIES:
				getCommunities().clear();
				getCommunities().addAll((Collection)newValue);
				return;
			case AsdPackage.AS_DSPEC__OBJECTS:
				getObjects().clear();
				getObjects().addAll((Collection)newValue);
				return;
			case AsdPackage.AS_DSPEC__OUTCOMES:
				getOutcomes().clear();
				getOutcomes().addAll((Collection)newValue);
				return;
			case AsdPackage.AS_DSPEC__MOTIVATIONS:
				getMotivations().clear();
				getMotivations().addAll((Collection)newValue);
				return;
			case AsdPackage.AS_DSPEC__TOOLS:
				getTools().clear();
				getTools().addAll((Collection)newValue);
				return;
			case AsdPackage.AS_DSPEC__RULES:
				getRules().clear();
				getRules().addAll((Collection)newValue);
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
			case AsdPackage.AS_DSPEC__AS_NETWORK:
				getASNetwork().clear();
				return;
			case AsdPackage.AS_DSPEC__MOTIVATION:
				getMotivation().clear();
				return;
			case AsdPackage.AS_DSPEC__OUTCOME:
				getOutcome().clear();
				return;
			case AsdPackage.AS_DSPEC__DOLS:
				getDols().clear();
				return;
			case AsdPackage.AS_DSPEC__SUBJECTS:
				getSubjects().clear();
				return;
			case AsdPackage.AS_DSPEC__COMMUNITIES:
				getCommunities().clear();
				return;
			case AsdPackage.AS_DSPEC__OBJECTS:
				getObjects().clear();
				return;
			case AsdPackage.AS_DSPEC__OUTCOMES:
				getOutcomes().clear();
				return;
			case AsdPackage.AS_DSPEC__MOTIVATIONS:
				getMotivations().clear();
				return;
			case AsdPackage.AS_DSPEC__TOOLS:
				getTools().clear();
				return;
			case AsdPackage.AS_DSPEC__RULES:
				getRules().clear();
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
			case AsdPackage.AS_DSPEC__AS_NETWORK:
				return asNetwork != null && !asNetwork.isEmpty();
			case AsdPackage.AS_DSPEC__MOTIVATION:
				return motivation != null && !motivation.isEmpty();
			case AsdPackage.AS_DSPEC__OUTCOME:
				return outcome != null && !outcome.isEmpty();
			case AsdPackage.AS_DSPEC__DOLS:
				return dols != null && !dols.isEmpty();
			case AsdPackage.AS_DSPEC__SUBJECTS:
				return subjects != null && !subjects.isEmpty();
			case AsdPackage.AS_DSPEC__COMMUNITIES:
				return communities != null && !communities.isEmpty();
			case AsdPackage.AS_DSPEC__OBJECTS:
				return objects != null && !objects.isEmpty();
			case AsdPackage.AS_DSPEC__OUTCOMES:
				return outcomes != null && !outcomes.isEmpty();
			case AsdPackage.AS_DSPEC__MOTIVATIONS:
				return motivations != null && !motivations.isEmpty();
			case AsdPackage.AS_DSPEC__TOOLS:
				return tools != null && !tools.isEmpty();
			case AsdPackage.AS_DSPEC__RULES:
				return rules != null && !rules.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ASDspecImpl
