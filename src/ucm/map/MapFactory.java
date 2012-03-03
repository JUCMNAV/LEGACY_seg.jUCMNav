/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package ucm.map;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see ucm.map.MapPackage
 * @generated
 */
public interface MapFactory extends EFactory {
    /**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @generated
	 */
    MapFactory eINSTANCE = ucm.map.impl.MapFactoryImpl.init();

    /**
	 * Returns a new object of class '<em>And Join</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>And Join</em>'.
	 * @generated
	 */
    AndJoin createAndJoin();

    /**
	 * Returns a new object of class '<em>Out Binding</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>Out Binding</em>'.
	 * @generated
	 */
    OutBinding createOutBinding();

    /**
	 * Returns a new object of class '<em>In Binding</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>In Binding</em>'.
	 * @generated
	 */
    InBinding createInBinding();

    /**
	 * Returns a new object of class '<em>Resp Ref</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>Resp Ref</em>'.
	 * @generated
	 */
    RespRef createRespRef();

    /**
	 * Returns a new object of class '<em>Or Join</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>Or Join</em>'.
	 * @generated
	 */
    OrJoin createOrJoin();

    /**
	 * Returns a new object of class '<em>Or Fork</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>Or Fork</em>'.
	 * @generated
	 */
    OrFork createOrFork();

    /**
	 * Returns a new object of class '<em>Connect</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>Connect</em>'.
	 * @generated
	 */
    Connect createConnect();

    /**
	 * Returns a new object of class '<em>Node Connection</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>Node Connection</em>'.
	 * @generated
	 */
    NodeConnection createNodeConnection();

    /**
	 * Returns a new object of class '<em>Waiting Place</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>Waiting Place</em>'.
	 * @generated
	 */
    WaitingPlace createWaitingPlace();

    /**
	 * Returns a new object of class '<em>Stub</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>Stub</em>'.
	 * @generated
	 */
    Stub createStub();

    /**
	 * Returns a new object of class '<em>End Point</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>End Point</em>'.
	 * @generated
	 */
    EndPoint createEndPoint();

    /**
	 * Returns a new object of class '<em>Start Point</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>Start Point</em>'.
	 * @generated
	 */
    StartPoint createStartPoint();

    /**
	 * Returns a new object of class '<em>UC Mmap</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>UC Mmap</em>'.
	 * @generated
	 */
    UCMmap createUCMmap();

    /**
	 * Returns a new object of class '<em>Plugin Binding</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>Plugin Binding</em>'.
	 * @generated
	 */
    PluginBinding createPluginBinding();

    /**
	 * Returns a new object of class '<em>Component Ref</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>Component Ref</em>'.
	 * @generated
	 */
    ComponentRef createComponentRef();

    /**
	 * Returns a new object of class '<em>Timer</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>Timer</em>'.
	 * @generated
	 */
    Timer createTimer();

    /**
	 * Returns a new object of class '<em>And Fork</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>And Fork</em>'.
	 * @generated
	 */
    AndFork createAndFork();

    /**
	 * Returns a new object of class '<em>Empty Point</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>Empty Point</em>'.
	 * @generated
	 */
    EmptyPoint createEmptyPoint();

    /**
	 * Returns a new object of class '<em>Direction Arrow</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>Direction Arrow</em>'.
	 * @generated
	 */
    DirectionArrow createDirectionArrow();

    /**
	 * Returns a new object of class '<em>Component Binding</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Component Binding</em>'.
	 * @generated
	 */
	ComponentBinding createComponentBinding();

				/**
	 * Returns a new object of class '<em>Anything</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>Anything</em>'.
	 * @generated
	 */
    Anything createAnything();

    /**
	 * Returns a new object of class '<em>Failure Point</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>Failure Point</em>'.
	 * @generated
	 */
    FailurePoint createFailurePoint();

    /**
	 * Returns a new object of class '<em>Responsibility Binding</em>'.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return a new object of class '<em>Responsibility Binding</em>'.
	 * @generated
	 */
    ResponsibilityBinding createResponsibilityBinding();

                /**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
    MapPackage getMapPackage();

} //MapFactory
