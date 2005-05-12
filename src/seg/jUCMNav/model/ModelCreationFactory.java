package seg.jUCMNav.model;

import java.util.Hashtable;

import org.eclipse.gef.requests.CreationFactory;

import seg.jUCMNav.model.commands.changeConstraints.SetConstraintComponentRefCommand;
import ucm.UcmFactory;
import ucm.map.AndFork;
import ucm.map.ComponentRef;
import ucm.map.EmptyPoint;
import ucm.map.EndPoint;
import ucm.map.MapFactory;
import ucm.map.NodeConnection;
import ucm.map.OrFork;
import ucm.map.PathNode;
import ucm.map.RespRef;
import ucm.map.StartPoint;
import ucm.map.Stub;
import urn.URNspec;
import urn.UrnFactory;
import urncore.Component;
import urncore.ComponentKind;
import urncore.ComponentLabel;
import urncore.NodeLabel;
import urncore.UCMmodelElement;
import urncore.UrncoreFactory;

/**
 * Created on 2005-01-30
 * 
 * This class implements the CreationFactory to be used as the central point to obtain new model elements. It sets up the default values for all new elements.
 * It in turn uses the EMF-generated factories to create the model instances
 * 
 * Our application will use the static getNewObject methods to access the factories. The palette needs to be passed a CreationFactory; that is the reason of the
 * non-static methods.
 * 
 * Since it would make no sense to provide a URNspec to be able to obtain one, an additional specific method was created for this task: getNewURNspec().
 * 
 * @author ddean, jkealey
 *  
 */
public class ModelCreationFactory implements CreationFactory {
    private Class targetClass;
    private int type;
    private URNspec urn;
    public static Hashtable htPrefixes;

    static {
        htPrefixes = new Hashtable();
        // to be used for shorthands.
        htPrefixes.put(StartPoint.class, "Start");
        htPrefixes.put(EndPoint.class, "End");

    }

    /**
     * @param urn
     *            The URNspec which contains information about the last ID created (for unique IDs). Use null if the class does not have an id/name.
     * @param targetClass
     *            The class we need to create from this factory.
     */
    public ModelCreationFactory(URNspec urn, Class targetClass) {
        this.urn = urn;
        this.targetClass = targetClass;
        this.type = 0;
    }

    /**
     * @param urn
     *            The URNspec which contains information about the last ID created (for unique IDs). Use null if the class does not have an id/name.
     * @param targetClass
     *            The class we need to create from this factory.
     * @param type
     *            If this is a ComponentRef, we can pass the ComponentKind.
     */
    public ModelCreationFactory(URNspec urn, Class targetClass, int type) {
        this.urn = urn;
        this.targetClass = targetClass;
        this.type = type;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.gef.requests.CreationFactory#getObjectType()
     */
    public Object getObjectType() {
        return targetClass;
    }

    public Object getNewObject() {
        return getNewObject(urn, targetClass, type);
    }

    /**
     * Equivalent to getNewObject(urn, targetClass, 0);
     * 
     * @param targetClass
     *            the class to obtain a new instance of
     * @return
     */
    public static Object getNewObject(URNspec urn, Class targetClass) {
        return getNewObject(urn, targetClass, 0);
    }

    /**
     * Returns a new model element preset with its default values. Note that no exception will be thrown for unknown classes but there will be a message printed
     * on the standard output to facilitate debugging for new developers.
     * 
     * @param URNspec
     *            The URNspec containing the ID seed. Use null if the targetClass does not have an id/name.
     * 
     * @see org.eclipse.gef.requests.CreationFactory#getNewObject()
     */
    public static Object getNewObject(URNspec urn, Class targetClass, int type) {
        MapFactory factory = MapFactory.eINSTANCE;
        Object result = null;
        if (targetClass != null) {
            if (targetClass.equals(URNspec.class)) {
                result = getNewURNspec();

            } else if (targetClass.equals(EmptyPoint.class)) {
                result = factory.createEmptyPoint();
            } else if (targetClass.equals(NodeConnection.class)) {
                result = factory.createNodeConnection();
            } else if (targetClass.equals(RespRef.class)) {
                // should create responsibility definition
                result = factory.createRespRef();
                ((PathNode) result).setLabel(UrncoreFactory.eINSTANCE.createNodeLabel());
            } else if (targetClass.equals(StartPoint.class)) {
                result = factory.createStartPoint();
                ((PathNode) result).setLabel(UrncoreFactory.eINSTANCE.createNodeLabel());
            } else if (targetClass.equals(EndPoint.class)) {
                result = factory.createEndPoint();
                ((PathNode) result).setLabel(UrncoreFactory.eINSTANCE.createNodeLabel());
            } else if (targetClass.equals(NodeLabel.class)) {
                UrncoreFactory urncoreFactory = UrncoreFactory.eINSTANCE;
                result = urncoreFactory.createNodeLabel();
            } else if (targetClass.equals(ComponentLabel.class)) {
                UrncoreFactory urncoreFactory = UrncoreFactory.eINSTANCE;
                result = urncoreFactory.createComponentLabel();
            } else if (targetClass.equals(ComponentRef.class)) {
                // create the component ref
                result = factory.createComponentRef();

                // new component refs must have a component definition
                Component compdef = UrncoreFactory.eINSTANCE.createComponent();
                ((ComponentRef) result).setCompDef(compdef);

                // define the ComponentKind according to what was set in the construction
                compdef.setKind(ComponentKind.get(type));
                compdef.setId(getNewID(urn));
                compdef.setName(getPrefix(compdef.getClass()) + compdef.getId());

                ((ComponentRef) result).setHeight(SetConstraintComponentRefCommand.DEFAULT_HEIGHT);
                ((ComponentRef) result).setWidth(SetConstraintComponentRefCommand.DEFAULT_WIDTH);
                
                ((ComponentRef) result).setLabel(UrncoreFactory.eINSTANCE.createComponentLabel());
            } else if (targetClass.equals(OrFork.class)) {
                result = factory.createOrFork();
            } else if (targetClass.equals(AndFork.class)) {
                result = factory.createAndFork();
            } else if (targetClass.equals(Stub.class)) {
                result = factory.createStub();
                ((PathNode) result).setLabel(UrncoreFactory.eINSTANCE.createNodeLabel());
            } else {
                System.out.println("Unknown class passed to ModelCreationFactory");
            }
        }

        if (result instanceof UCMmodelElement) {
            UCMmodelElement elem = (UCMmodelElement) result;
            if (elem.getId() == null || elem.getId().length() == 0)
                elem.setId(getNewID(urn));
            if (elem.getName() == null || elem.getName().length() == 0)
                elem.setName(getPrefix(targetClass));
        }

        return result;

    }

    /**
     * @param targetClass
     * @param factory
     * @return
     */
    public static URNspec getNewURNspec() {
        MapFactory factory = MapFactory.eINSTANCE;

        URNspec result = null;

        // create the URN spec
        URNspec urnspec = UrnFactory.eINSTANCE.createURNspec();
        // name the component
        urnspec.setName(getPrefix(URNspec.class));

        // seed the global id
        urnspec.setModified("1");

        // add its URN definition
        urnspec.setUrndef(UrncoreFactory.eINSTANCE.createURNdefinition());

        // add its UCMspec
        urnspec.setUcmspec(UcmFactory.eINSTANCE.createUCMspec());

        // create a map
        ucm.map.Map ucm = factory.createMap();
        ucm.setId(getNewID(urnspec));
        ucm.setName(getPrefix(ucm.getClass()));

        // add an empty pathgraph to this map
        ucm.setPathGraph(factory.createPathGraph());

        // add the new mapp to the UCMspec
        urnspec.getUcmspec().getMaps().add(ucm);

        result = urnspec;
        return result;
    }

    /**
     * Returns the next ID that can be used in this document. Assumes it will be used and increments the count in the URNspec.
     * 
     * @param urn
     *            The URNspec containing the value.
     * 
     * @return
     */
    private static String getNewID(URNspec urn) {

        if (urn == null) {
            return "";
        }

        String id = urn.getModified();

        // if we can't convert it, the model is in an invalid state.
        // don't catch the exception
        if (id != null && id.length() > 0)
            id = Long.toString(Long.parseLong(id) + 1);
        else {
            id = "2"; // for backwards compatibility reasons with early jUCMNav files.
            System.out.println("Old file; please discard.");
        }

        urn.setModified(id);

        return id;
    }

    /**
     * When creating names, we often need a generic name. Using this method, we can obtain a prefix using the appropriate naming convention.
     * 
     * @param targetClass
     * @return
     */
    public static String getPrefix(Class targetClass) {
        if (htPrefixes.get(targetClass) != null)
            return (String) htPrefixes.get(targetClass);
        else if (getSimpleName(targetClass).endsWith("Impl"))
            return getSimpleName(targetClass).substring(0, getSimpleName(targetClass).length() - 4);
        else
            return getSimpleName(targetClass);

    }

    /*
     * To avoid depending on Java 1.5
     */
    private static String getSimpleName(Class targetClass) {
        String simpleName = targetClass.getName();
        return simpleName.substring(simpleName.lastIndexOf(".") + 1); // strip the package name
    }

}