/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ucm.map.util;

import ca.mcgill.sel.core.COREModel;
import ca.mcgill.sel.core.CORENamedElement;
import java.util.List;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import ucm.map.*;
import urncore.IURNConnection;
import urncore.IURNContainerRef;
import urncore.IURNDiagram;
import urncore.IURNNode;
import urncore.UCMmodelElement;
import urncore.URNmodelElement;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see ucm.map.MapPackage
 * @generated
 */
public class MapSwitch {
    /**
	 * The cached model package
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    protected static MapPackage modelPackage;

    /**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    public MapSwitch() {
		if (modelPackage == null) {
			modelPackage = MapPackage.eINSTANCE;
		}
	}

    /**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
    public Object doSwitch(EObject theEObject) {
		return doSwitch(theEObject.eClass(), theEObject);
	}

    /**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
    protected Object doSwitch(EClass theEClass, EObject theEObject) {
		if (theEClass.eContainer() == modelPackage) {
			return doSwitch(theEClass.getClassifierID(), theEObject);
		}
		else {
			List eSuperTypes = theEClass.getESuperTypes();
			return
				eSuperTypes.isEmpty() ?
					defaultCase(theEObject) :
					doSwitch((EClass)eSuperTypes.get(0), theEObject);
		}
	}

    /**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
    protected Object doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case MapPackage.AND_JOIN: {
				AndJoin andJoin = (AndJoin)theEObject;
				Object result = caseAndJoin(andJoin);
				if (result == null) result = casePathNode(andJoin);
				if (result == null) result = caseUCMmodelElement(andJoin);
				if (result == null) result = caseIURNNode(andJoin);
				if (result == null) result = caseURNmodelElement(andJoin);
				if (result == null) result = caseCORENamedElement(andJoin);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MapPackage.OUT_BINDING: {
				OutBinding outBinding = (OutBinding)theEObject;
				Object result = caseOutBinding(outBinding);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MapPackage.IN_BINDING: {
				InBinding inBinding = (InBinding)theEObject;
				Object result = caseInBinding(inBinding);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MapPackage.RESP_REF: {
				RespRef respRef = (RespRef)theEObject;
				Object result = caseRespRef(respRef);
				if (result == null) result = casePathNode(respRef);
				if (result == null) result = caseUCMmodelElement(respRef);
				if (result == null) result = caseIURNNode(respRef);
				if (result == null) result = caseURNmodelElement(respRef);
				if (result == null) result = caseCORENamedElement(respRef);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MapPackage.OR_JOIN: {
				OrJoin orJoin = (OrJoin)theEObject;
				Object result = caseOrJoin(orJoin);
				if (result == null) result = casePathNode(orJoin);
				if (result == null) result = caseUCMmodelElement(orJoin);
				if (result == null) result = caseIURNNode(orJoin);
				if (result == null) result = caseURNmodelElement(orJoin);
				if (result == null) result = caseCORENamedElement(orJoin);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MapPackage.OR_FORK: {
				OrFork orFork = (OrFork)theEObject;
				Object result = caseOrFork(orFork);
				if (result == null) result = casePathNode(orFork);
				if (result == null) result = caseUCMmodelElement(orFork);
				if (result == null) result = caseIURNNode(orFork);
				if (result == null) result = caseURNmodelElement(orFork);
				if (result == null) result = caseCORENamedElement(orFork);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MapPackage.CONNECT: {
				Connect connect = (Connect)theEObject;
				Object result = caseConnect(connect);
				if (result == null) result = casePathNode(connect);
				if (result == null) result = caseUCMmodelElement(connect);
				if (result == null) result = caseIURNNode(connect);
				if (result == null) result = caseURNmodelElement(connect);
				if (result == null) result = caseCORENamedElement(connect);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MapPackage.NODE_CONNECTION: {
				NodeConnection nodeConnection = (NodeConnection)theEObject;
				Object result = caseNodeConnection(nodeConnection);
				if (result == null) result = caseIURNConnection(nodeConnection);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MapPackage.WAITING_PLACE: {
				WaitingPlace waitingPlace = (WaitingPlace)theEObject;
				Object result = caseWaitingPlace(waitingPlace);
				if (result == null) result = casePathNode(waitingPlace);
				if (result == null) result = caseUCMmodelElement(waitingPlace);
				if (result == null) result = caseIURNNode(waitingPlace);
				if (result == null) result = caseURNmodelElement(waitingPlace);
				if (result == null) result = caseCORENamedElement(waitingPlace);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MapPackage.STUB: {
				Stub stub = (Stub)theEObject;
				Object result = caseStub(stub);
				if (result == null) result = casePathNode(stub);
				if (result == null) result = caseUCMmodelElement(stub);
				if (result == null) result = caseIURNNode(stub);
				if (result == null) result = caseURNmodelElement(stub);
				if (result == null) result = caseCORENamedElement(stub);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MapPackage.PATH_NODE: {
				PathNode pathNode = (PathNode)theEObject;
				Object result = casePathNode(pathNode);
				if (result == null) result = caseUCMmodelElement(pathNode);
				if (result == null) result = caseIURNNode(pathNode);
				if (result == null) result = caseURNmodelElement(pathNode);
				if (result == null) result = caseCORENamedElement(pathNode);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MapPackage.END_POINT: {
				EndPoint endPoint = (EndPoint)theEObject;
				Object result = caseEndPoint(endPoint);
				if (result == null) result = casePathNode(endPoint);
				if (result == null) result = caseUCMmodelElement(endPoint);
				if (result == null) result = caseIURNNode(endPoint);
				if (result == null) result = caseURNmodelElement(endPoint);
				if (result == null) result = caseCORENamedElement(endPoint);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MapPackage.START_POINT: {
				StartPoint startPoint = (StartPoint)theEObject;
				Object result = caseStartPoint(startPoint);
				if (result == null) result = casePathNode(startPoint);
				if (result == null) result = caseUCMmodelElement(startPoint);
				if (result == null) result = caseIURNNode(startPoint);
				if (result == null) result = caseURNmodelElement(startPoint);
				if (result == null) result = caseCORENamedElement(startPoint);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MapPackage.UC_MMAP: {
				UCMmap ucMmap = (UCMmap)theEObject;
				Object result = caseUCMmap(ucMmap);
				if (result == null) result = caseUCMmodelElement(ucMmap);
				if (result == null) result = caseIURNDiagram(ucMmap);
				if (result == null) result = caseCOREModel(ucMmap);
				if (result == null) result = caseURNmodelElement(ucMmap);
				if (result == null) result = caseCORENamedElement(ucMmap);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MapPackage.PLUGIN_BINDING: {
				PluginBinding pluginBinding = (PluginBinding)theEObject;
				Object result = casePluginBinding(pluginBinding);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MapPackage.COMPONENT_REF: {
				ComponentRef componentRef = (ComponentRef)theEObject;
				Object result = caseComponentRef(componentRef);
				if (result == null) result = caseUCMmodelElement(componentRef);
				if (result == null) result = caseIURNContainerRef(componentRef);
				if (result == null) result = caseURNmodelElement(componentRef);
				if (result == null) result = caseCORENamedElement(componentRef);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MapPackage.TIMER: {
				Timer timer = (Timer)theEObject;
				Object result = caseTimer(timer);
				if (result == null) result = caseWaitingPlace(timer);
				if (result == null) result = casePathNode(timer);
				if (result == null) result = caseUCMmodelElement(timer);
				if (result == null) result = caseIURNNode(timer);
				if (result == null) result = caseURNmodelElement(timer);
				if (result == null) result = caseCORENamedElement(timer);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MapPackage.AND_FORK: {
				AndFork andFork = (AndFork)theEObject;
				Object result = caseAndFork(andFork);
				if (result == null) result = casePathNode(andFork);
				if (result == null) result = caseUCMmodelElement(andFork);
				if (result == null) result = caseIURNNode(andFork);
				if (result == null) result = caseURNmodelElement(andFork);
				if (result == null) result = caseCORENamedElement(andFork);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MapPackage.EMPTY_POINT: {
				EmptyPoint emptyPoint = (EmptyPoint)theEObject;
				Object result = caseEmptyPoint(emptyPoint);
				if (result == null) result = casePathNode(emptyPoint);
				if (result == null) result = caseUCMmodelElement(emptyPoint);
				if (result == null) result = caseIURNNode(emptyPoint);
				if (result == null) result = caseURNmodelElement(emptyPoint);
				if (result == null) result = caseCORENamedElement(emptyPoint);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MapPackage.DIRECTION_ARROW: {
				DirectionArrow directionArrow = (DirectionArrow)theEObject;
				Object result = caseDirectionArrow(directionArrow);
				if (result == null) result = casePathNode(directionArrow);
				if (result == null) result = caseUCMmodelElement(directionArrow);
				if (result == null) result = caseIURNNode(directionArrow);
				if (result == null) result = caseURNmodelElement(directionArrow);
				if (result == null) result = caseCORENamedElement(directionArrow);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MapPackage.COMPONENT_BINDING: {
				ComponentBinding componentBinding = (ComponentBinding)theEObject;
				Object result = caseComponentBinding(componentBinding);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MapPackage.ANYTHING: {
				Anything anything = (Anything)theEObject;
				Object result = caseAnything(anything);
				if (result == null) result = casePathNode(anything);
				if (result == null) result = caseUCMmodelElement(anything);
				if (result == null) result = caseIURNNode(anything);
				if (result == null) result = caseURNmodelElement(anything);
				if (result == null) result = caseCORENamedElement(anything);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MapPackage.FAILURE_POINT: {
				FailurePoint failurePoint = (FailurePoint)theEObject;
				Object result = caseFailurePoint(failurePoint);
				if (result == null) result = casePathNode(failurePoint);
				if (result == null) result = caseUCMmodelElement(failurePoint);
				if (result == null) result = caseIURNNode(failurePoint);
				if (result == null) result = caseURNmodelElement(failurePoint);
				if (result == null) result = caseCORENamedElement(failurePoint);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case MapPackage.RESPONSIBILITY_BINDING: {
				ResponsibilityBinding responsibilityBinding = (ResponsibilityBinding)theEObject;
				Object result = caseResponsibilityBinding(responsibilityBinding);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>And Join</em>'.
	 * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>And Join</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
    public Object caseAndJoin(AndJoin object) {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Out Binding</em>'.
	 * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Out Binding</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
    public Object caseOutBinding(OutBinding object) {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>In Binding</em>'.
	 * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>In Binding</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
    public Object caseInBinding(InBinding object) {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Resp Ref</em>'.
	 * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Resp Ref</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
    public Object caseRespRef(RespRef object) {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Or Join</em>'.
	 * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Or Join</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
    public Object caseOrJoin(OrJoin object) {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Or Fork</em>'.
	 * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Or Fork</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
    public Object caseOrFork(OrFork object) {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Connect</em>'.
	 * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Connect</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
    public Object caseConnect(Connect object) {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Node Connection</em>'.
	 * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Node Connection</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
    public Object caseNodeConnection(NodeConnection object) {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Waiting Place</em>'.
	 * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Waiting Place</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
    public Object caseWaitingPlace(WaitingPlace object) {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Stub</em>'.
	 * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Stub</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
    public Object caseStub(Stub object) {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Path Node</em>'.
	 * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Path Node</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
    public Object casePathNode(PathNode object) {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>End Point</em>'.
	 * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>End Point</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
    public Object caseEndPoint(EndPoint object) {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Start Point</em>'.
	 * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Start Point</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
    public Object caseStartPoint(StartPoint object) {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>UC Mmap</em>'.
	 * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>UC Mmap</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
    public Object caseUCMmap(UCMmap object) {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Plugin Binding</em>'.
	 * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Plugin Binding</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
    public Object casePluginBinding(PluginBinding object) {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Component Ref</em>'.
	 * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Component Ref</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
    public Object caseComponentRef(ComponentRef object) {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Timer</em>'.
	 * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Timer</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
    public Object caseTimer(Timer object) {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>And Fork</em>'.
	 * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>And Fork</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
    public Object caseAndFork(AndFork object) {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Empty Point</em>'.
	 * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Empty Point</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
    public Object caseEmptyPoint(EmptyPoint object) {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Direction Arrow</em>'.
	 * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Direction Arrow</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
    public Object caseDirectionArrow(DirectionArrow object) {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Component Binding</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Component Binding</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseComponentBinding(ComponentBinding object) {
		return null;
	}

				/**
	 * Returns the result of interpreting the object as an instance of '<em>Anything</em>'.
	 * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Anything</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
    public Object caseAnything(Anything object) {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Failure Point</em>'.
	 * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Failure Point</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
    public Object caseFailurePoint(FailurePoint object) {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>Responsibility Binding</em>'.
	 * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Responsibility Binding</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
    public Object caseResponsibilityBinding(ResponsibilityBinding object) {
		return null;
	}

                /**
	 * Returns the result of interpreting the object as an instance of '<em>CORE Named Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CORE Named Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseCORENamedElement(CORENamedElement object) {
		return null;
	}

																/**
	 * Returns the result of interpreting the object as an instance of '<em>UR Nmodel Element</em>'.
	 * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>UR Nmodel Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
    public Object caseURNmodelElement(URNmodelElement object) {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>UC Mmodel Element</em>'.
	 * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>UC Mmodel Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
    public Object caseUCMmodelElement(UCMmodelElement object) {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>IURN Node</em>'.
	 * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IURN Node</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
    public Object caseIURNNode(IURNNode object) {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>IURN Connection</em>'.
	 * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IURN Connection</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
    public Object caseIURNConnection(IURNConnection object) {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>IURN Diagram</em>'.
	 * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IURN Diagram</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
    public Object caseIURNDiagram(IURNDiagram object) {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>CORE Model</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>CORE Model</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public Object caseCOREModel(COREModel object) {
		return null;
	}

				/**
	 * Returns the result of interpreting the object as an instance of '<em>IURN Container Ref</em>'.
	 * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch.
     * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>IURN Container Ref</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
    public Object caseIURNContainerRef(IURNContainerRef object) {
		return null;
	}

    /**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
     * This implementation returns null;
     * returning a non-null result will terminate the switch, but this is the last case anyway.
     * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
    public Object defaultCase(EObject object) {
		return null;
	}

} //MapSwitch
