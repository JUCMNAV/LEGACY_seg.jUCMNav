package seg.jUCMNav.scenarios.model;

import java.util.HashMap;

import seg.jUCMNav.scenarios.parser.SimpleNode;

public class UcmEnvironment {

    HashMap declarations;
    HashMap enumerations;
    HashMap valuations;

    public UcmEnvironment() {
        declarations = new HashMap();
        valuations = new HashMap();
        enumerations = new HashMap();
    }

    public void checkEnumerationDoesNotExists(String var) {
        Object type = enumerations.get(var);
        if (type != null)
            throw new IllegalArgumentException("Enumeration " + var + " is already defined.");
    }

    public String[] checkEnumerationExists(String var) {
        Object type = enumerations.get(var);
        if (type == null)
            throw new IllegalArgumentException("Enumeration " + var + " is not defined.");
        return (String[]) type;
    }

    public void checkVariableDoesNotExist(SimpleNode root) {
        checkVariableDoesNotExist(root.getText());
    }

    public void checkVariableDoesNotExist(String var) {
        Object type = declarations.get(var);
        if (type != null)
            throw new IllegalArgumentException("Variable " + var + " is already defined.");
    }

    public jUCMNavType checkVariableExists(SimpleNode root) {
        return checkVariableExists(root.getText());
    }

    public jUCMNavType checkVariableExists(String var) {
        Object type = declarations.get(var);
        if (type == null || !(type instanceof jUCMNavType))
            throw new IllegalArgumentException("Variable " + var + " is not defined.");
        return (jUCMNavType) type;
    }

    public void registerBoolean(String var) {
        registerBoolean(var, false);
    }

    public void registerBoolean(String var, boolean b) {
        checkVariableDoesNotExist(var);
        declarations.put(var, jUCMNavType.BOOLEAN);
        valuations.put(var, Boolean.valueOf(b));
    }

    public void registerEnumeration(String enumName, String[] values) {
        checkEnumerationDoesNotExists(enumName);
        if (values.length == 0)
            throw new IllegalArgumentException("Enumeration must have values.");

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

    public String getEnumerationValue(String enumName, int index) {
        checkEnumerationExists(enumName);
        String[] values = (String[]) enumerations.get(enumName);
        return values[index];
    }

    public void registerEnumerationInstance(String enumName, String var) {
        registerEnumerationInstance(enumName, var, getEnumerationValue(enumName, 0));
    }

    public void registerEnumerationInstance(String enumName, String var, String value) {
        checkEnumerationExists(enumName);
        checkVariableDoesNotExist(var);
        checkVariableExists(value);

        declarations.put(var, new jUCMNavType(jUCMNavType.ENUMERATION + enumName));
        valuations.put(var, value);
    }

    public void registerInteger(String var) {
        registerInteger(var, 0);
    }

    public void registerInteger(String var, int i) {
        checkVariableDoesNotExist(var);
        declarations.put(var, jUCMNavType.INTEGER);
        valuations.put(var, new Integer(i));
    }

    public Object getValue(String var) {
        Object result = valuations.get(var);
        if (result == null) {
            result = declarations.get(var);
            
            if (result==null) {// || result.toString().indexOf(jUCMNavType.ENUMERATION)>=0) {
                throw new IllegalArgumentException("Variable " + var +  " has no valuation.");
            } else
                result = var;
        }
        return result;
    }
    
    public void setValue(String var, Object o) {
        valuations.put(var, o);
    }
    

}
