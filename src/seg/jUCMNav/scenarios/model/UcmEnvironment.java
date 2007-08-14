package seg.jUCMNav.scenarios.model;

import java.util.HashMap;
import java.util.Iterator;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;

import seg.jUCMNav.Messages;
import seg.jUCMNav.scenarios.ScenarioUtils;
import seg.jUCMNav.scenarios.parser.SimpleNode;
import ucm.UCMspec;
import ucm.UcmPackage;
import ucm.scenario.ScenarioPackage;
import ucm.scenario.Variable;
import urn.URNspec;

/**
 * A UcmEnvironment represents all the variable/enumeration declarations and valuations
 * 
 * @author jkealey
 * 
 */
public class UcmEnvironment implements Adapter, Cloneable {

    private HashMap declarations;
    private HashMap enumerations;
    private HashMap valuations;

    private URNspec urn;

    /**
     * Create a new environment and register it as a listener to this urnspec
     * 
     * @param urn
     *            the urnspec; pass null if you do not want to register any listeners.
     */
    public UcmEnvironment(URNspec urn) {
        declarations = new HashMap();
        valuations = new HashMap();
        enumerations = new HashMap();

        if (urn != null)
            registerUCMspec(urn.getUcmspec());

    }

    /**
     * Throws an {@link IllegalArgumentException} if it already exists.
     * 
     * @param var
     *            the enumeration name.
     */
    public void checkEnumerationDoesNotExists(String var) {
        var = var.toLowerCase();
        Object type = enumerations.get(var);
        if (type != null)
            throw new IllegalArgumentException(
                    Messages.getString("UcmEnvironment.EnumerationSpace") + var + Messages.getString("UcmEnvironment.IsAlreadyDefined")); //$NON-NLS-1$ //$NON-NLS-2$
    }

    /**
     * Throws an {@link IllegalArgumentException} if it doesn't already exists.
     * 
     * @param var
     *            the enumeration name.
     */
    public String[] checkEnumerationExists(String var) {
        var = var.toLowerCase();
        Object type = enumerations.get(var);
        if (type == null)
            throw new IllegalArgumentException(Messages.getString("UcmEnvironment.EnumerationSpace") + var + Messages.getString("UcmEnvironment.IsNotDefined")); //$NON-NLS-1$ //$NON-NLS-2$
        return (String[]) type;
    }

    /**
     * @param var
     *            the enumeration name.
     * @return returns true if it exists, false otherwise.
     */
    public boolean doesEnumerationExist(String var) {
        var = var.toLowerCase();
        Object type = enumerations.get(var);
        return type != null;
    }

    /**
     * Throws an {@link IllegalArgumentException} if it already exists.
     * 
     * @param root 
     *            the variable 
     */
    public void checkVariableDoesNotExist(SimpleNode root) {
        checkVariableDoesNotExist(root.getText());
    }

    /**
     * Throws an {@link IllegalArgumentException} if it already exists.
     * 
     * @param var 
     *            the variable 
     */
    public void checkVariableDoesNotExist(String var) {
        var = var.toLowerCase();
        Object type = declarations.get(var);
        if (type != null)
            throw new IllegalArgumentException(Messages.getString("UcmEnvironment.VariableSpace") + var + Messages.getString("UcmEnvironment.IsAlreadyDefined")); //$NON-NLS-1$ //$NON-NLS-2$
    }

    /**
     * Throws an {@link IllegalArgumentException} if it doesn't already exists.
     * 
     * @param root 
     *            the variable 
     */
    public jUCMNavType checkVariableExists(SimpleNode root) {
        return checkVariableExists(root.getText());
    }

    
    /**
     * Throws an {@link IllegalArgumentException} if it doesn't already exists.
     * 
     * @param var 
     *            the variable 
     */
    public jUCMNavType checkVariableExists(String var) {
        var = var.toLowerCase();
        Object type = declarations.get(var);
        if (type == null || !(type instanceof jUCMNavType)) {
            for (Iterator iter = enumerations.keySet().iterator(); iter.hasNext();) {
                String enumName = (String) iter.next();
                String[] values = (String[]) enumerations.get(enumName);

                for (int i = 0; i < values.length; i++) {
                    String val = values[i];
                    if (val.equalsIgnoreCase(var)) {
                        if (type == null)
                            type = new jUCMNavType(jUCMNavType.ENUMERATION + enumName);
                        else
                            ((jUCMNavType) type).addEnumerationType(jUCMNavType.ENUMERATION + enumName);
                    }

                }
            }
            if (type == null)
                throw new IllegalArgumentException(Messages.getString("UcmEnvironment.VariableSpace") + var + Messages.getString("UcmEnvironment.IsNotDefined")); //$NON-NLS-1$ //$NON-NLS-2$

        }
        return (jUCMNavType) type;
    }

    /**
     * Throws an {@link IllegalArgumentException} if it already exists.
     * 
     * @param enumName 
     *            the enumeration
     * @param value the proposed enumeration value
     *             
     */
    public void checkEnumerationValueExists(String enumName, String value) {
        enumName = enumName.toLowerCase();
        value = value.toUpperCase();
        String[] values = (String[]) enumerations.get(enumName);
        boolean found = false;
        for (int i = 0; i < values.length; i++) {
            if (values[i].equalsIgnoreCase(value))
                found = true;
        }

        if (!found)
            throw new IllegalArgumentException(
                    Messages.getString("UcmEnvironment.EnumerationValue") + value + Messages.getString("UcmEnvironment.DoesNotExistInEnumeration")); //$NON-NLS-1$ //$NON-NLS-2$

    }

    /**
     * Clear all valuations. 
     *
     */
    public void clearValuations() {
        valuations.clear();
    }

    /**
     * Clone the environment. Does not register listeners. 
     */
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    /**
     * Register a new boolean with the name var
     * 
     * @param var the boolean's name. 
     */
    public void registerBoolean(String var) {
        registerBoolean(var, false);
    }
    /**
     * Register a new boolean with the name var and default valuation b. 
     * @param var the boolean's name. 
     * @param b the default value
     */
    public void registerBoolean(String var, boolean b) {
        var = var.toLowerCase();
        checkVariableDoesNotExist(var);
        declarations.put(var, jUCMNavType.BOOLEAN);
        valuations.put(var, Boolean.valueOf(b));
    }

    /**
     * Register a new enumeration with a certain name and certain values. 
     * 
     * @param enumName the enumeration name
     * @param values its possible values. 
     */
    public void registerEnumeration(String enumName, String[] values) {
        enumName = enumName.toLowerCase();
        checkEnumerationDoesNotExists(enumName);
        if (values.length == 0)
            throw new IllegalArgumentException(Messages.getString("UcmEnvironment.EnumerationMustHaveValues")); //$NON-NLS-1$

        enumerations.put(enumName, values);

        for (int i = 0; i < values.length; i++) {
            String value = values[i];
            jUCMNavType type = (jUCMNavType) declarations.get(value);
            if (type == null) {
                type = new jUCMNavType(jUCMNavType.ENUMERATION + enumName);
                declarations.put(value, type);
            } else {
                type.addEnumerationType(jUCMNavType.ENUMERATION + enumName);

            }
        }
    }

    /**
     * Retrieve a particular enumeration value
     * 
     * @param enumName the enumeration name. 
     * @param index the index of the enumeration value
     * @return the enumeration value
     */
    public String getEnumerationValue(String enumName, int index) {
        enumName = enumName.toLowerCase();
        checkEnumerationExists(enumName);
        String[] values = (String[]) enumerations.get(enumName);
        return values[index];
    }

    /**
     * Register a variable that is an instance of a particular enumeration. 
     * @param enumName the enumeration type
     * @param var the variable name. 
     */
    public void registerEnumerationInstance(String enumName, String var) {
        enumName = enumName.toLowerCase();
        var = var.toLowerCase();
        registerEnumerationInstance(enumName, var, getEnumerationValue(enumName, 0));
    }

    /**
     * Register a variable that is an instance of a particular enumeration. 
     * @param enumName the enumeration type
     * @param var the variable name.
     * @param value the default value.  
     */
    public void registerEnumerationInstance(String enumName, String var, String value) {
        enumName = enumName.toLowerCase();
        var = var.toLowerCase();
        value = value.toLowerCase();
        checkEnumerationExists(enumName);
        checkVariableDoesNotExist(var);
        checkEnumerationValueExists(enumName, value);

        declarations.put(var, new jUCMNavType(jUCMNavType.ENUMERATION + enumName));
        valuations.put(var, value);
    }

    /**
     * Registers a new integer
     * @param var the variable name. 
     */
    public void registerInteger(String var) {
        registerInteger(var, 0);
    }

    /**
     * Registers a new integer
     * @param var the variable name
     * @param i the default value
     */
    public void registerInteger(String var, int i) {
        var = var.toLowerCase();
        checkVariableDoesNotExist(var);
        declarations.put(var, jUCMNavType.INTEGER);
        valuations.put(var, new Integer(i));
    }

    /**
     * Returns the value of a variable in the environment. 
     * @param var the variable name
     * @return its valuation. 
     */
    public Object getValue(String var) {
        String lower = var.toLowerCase();

        Object result = valuations.get(lower);
        if (result == null) {
            result = declarations.get(lower);

            if (result != null) {// || result.toString().indexOf(jUCMNavType.ENUMERATION)>=0) {
                throw new IllegalArgumentException(
                        Messages.getString("UcmEnvironment.VariableSpace") + var + Messages.getString("UcmEnvironment.HasNoValuation")); //$NON-NLS-1$ //$NON-NLS-2$
            } else
                result = lower;
        }
        return result;
    }

    /**
     * Sets the value of a variable in the environment
     * @param var the variable name
     * @param o the valuation
     */
    public void setValue(String var, Object o) {
        var = var.toLowerCase();
        valuations.put(var, o);
    }

    /*
     * Needed to list to the UCMspec  
     */
    public Notifier getTarget() {
        return urn;
    }
    /*
     * Needed to list to the UCMspec  
     */
    public boolean isAdapterForType(Object arg0) {
        return arg0 instanceof URNspec;
    }

    /**
     * Notification when the UCMspec changes (new/deleted variable/enumeration)  
     */
    public void notifyChanged(Notification notification) {

        // int type = notification.getEventType();
        // switch (type) {
        // case Notification.SET:
        // }

        if (notification.getNotifier() instanceof UCMspec) {
            int featureId = notification.getFeatureID(UCMspec.class);

            if (featureId == UcmPackage.UC_MSPEC__VARIABLES || featureId == UcmPackage.UC_MSPEC__ENUMERATION_TYPES) {
                registerUCMspec((UCMspec) notification.getNotifier());
            }
        } else if (notification.getNotifier() instanceof Variable) {
            int featureId = notification.getFeatureID(Variable.class);

            if (featureId == ScenarioPackage.VARIABLE__NAME) {
                if (declarations.containsKey(notification.getOldValue().toString().toLowerCase())) {
                    Object o = declarations.get(notification.getOldValue().toString().toLowerCase());
                    declarations.remove(notification.getOldValue().toString().toLowerCase());
                    declarations.put(notification.getNewValue().toString().toLowerCase(), o);
                }
                if (valuations.containsKey(notification.getOldValue().toString().toLowerCase())) {
                    Object o = valuations.get(notification.getOldValue().toString().toLowerCase());
                    valuations.remove(notification.getOldValue().toString().toLowerCase());
                    valuations.put(notification.getNewValue().toString().toLowerCase(), o);
                }

            } else if (featureId == ScenarioPackage.VARIABLE__TYPE) {
                // assuming we can't change type.
            }
            // refresh();
        }

    }
    /*
     * Needed to list to the UCMspec  
     */
    public void setTarget(Notifier arg0) {
        refresh();
    }

    /**
     * Register yourself as a listener to this ucmspec. Unregisters any previous ucmspec 
     * 
     * @param ucmspec the ucmspec. 
     */
    private void registerUCMspec(UCMspec ucmspec) {
        if (this.urn != null && ucmspec.getUrnspec() != this.urn) {
            this.urn.getUcmspec().eAdapters().remove(this);

            for (Iterator iter = urn.getUcmspec().getVariables().iterator(); iter.hasNext();) {
                Variable var = (Variable) iter.next();
                var.eAdapters().remove(this);
            }

        }

        this.urn = ucmspec.getUrnspec();
        refresh();

        if (this.urn != null && !urn.getUcmspec().eAdapters().contains(this)) {
            urn.getUcmspec().eAdapters().add(this);
        }

        if (this.urn != null) {

            for (Iterator iter = urn.getUcmspec().getVariables().iterator(); iter.hasNext();) {
                Variable var = (Variable) iter.next();
                if (!var.eAdapters().contains(this))
                    var.eAdapters().add(this);
            }
        }
        // TODO: remove unused ones.
    }

    /**
     * Gets rid of all stored data and reloads them from the associated URNspec 
     *
     */
    private void refresh() {
        if (this.urn == null) {
            declarations.clear();
            enumerations.clear();
            valuations.clear();
        } else {
            declarations.clear();
            enumerations.clear();
            HashMap oldValuations = (HashMap) valuations.clone();
            valuations.clear();

            for (Iterator iter = this.urn.getUcmspec().getVariables().iterator(); iter.hasNext();) {
                Variable var = (Variable) iter.next();
                String name = var.getName().toLowerCase();
                if (ScenarioUtils.sTypeBoolean.equals(var.getType())) {
                    if (oldValuations.containsKey(name))
                        this.registerBoolean(name, ((Boolean) oldValuations.get(name)).booleanValue());
                    else
                        this.registerBoolean(name);
                } else if (ScenarioUtils.sTypeInteger.equals(var.getType())) {
                    if (oldValuations.containsKey(name))
                        this.registerInteger(name, ((Integer) oldValuations.get(name)).intValue());
                    else
                        this.registerInteger(name);
                } else {
                    if (var.getEnumerationType() != null) {
                        // using ID instead of name because we don't guarantee uniqueness.
                        if (!doesEnumerationExist(var.getEnumerationType().getId()))
                            this.registerEnumeration(var.getEnumerationType().getId(), var.getEnumerationType().getValues().split(",")); //$NON-NLS-1$

                        if (oldValuations.containsKey(name))
                            this.registerEnumerationInstance(var.getEnumerationType().getId(), name, oldValuations.get(name).toString());
                        else
                            this.registerEnumerationInstance(var.getEnumerationType().getId(), name);
                    }

                }

            }
            
            // we could do it here, but we'd have to bother with listening to model changes. putting all related code in ScenarioTraversalAlgorithm
//            if (ScenarioTraversalPreferences.getShouldIntegrateStrategyVariables()) {
//                Vector v2 = URNNamingHelper.getGrlVariableNames(urn);
//                for (Iterator iter = v2.iterator(); iter.hasNext();) {
//                    String element = (String) iter.next();
//                    try {
//                        this.registerInteger(element);
//                    } catch(IllegalArgumentException ex) {
//                        // ignore naming conflicts. the UCM elements have precedence.  
//                    }
//                }
//            }

        }

    }

    public URNspec getUrn() {
        return urn;
    }

}
