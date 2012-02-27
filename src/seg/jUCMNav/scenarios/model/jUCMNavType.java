package seg.jUCMNav.scenarios.model;

import java.util.ArrayList;
import java.util.Iterator;

import seg.jUCMNav.Messages;

/**
 * The various types supported by jUCMNav.
 * 
 * A variable can be of only one type, but we do allow variables to be of a list of enumeration types. This is useful to allow enumerations to re-use the same
 * enumeration values.
 * 
 * @author jkealey
 * 
 */
public class jUCMNavType {

    private static final String _INT = "#INT"; //$NON-NLS-1$
    private static final String _BOOL = "#BOOL"; //$NON-NLS-1$
    private static final String _VOID = "#VOID";//$NON-NLS-1$
    public final static jUCMNavType VOID = new jUCMNavType(_VOID);
    public final static jUCMNavType BOOLEAN = new jUCMNavType(_BOOL);
    public final static jUCMNavType INTEGER = new jUCMNavType(_INT);
    public final static String ENUMERATION = "#ENUM#"; //$NON-NLS-1$

    private ArrayList enumerationList;
    private String type;

    /**
     * Creates a new type. Should be used internally to create void, boolean or Integers. Can be used externally to create enumerations.
     * 
     * 
     * @param s
     *            the type, must be one of the predefined constants or start with {@link #ENUMERATION} (@value {@value #ENUMERATION}
     */
    public jUCMNavType(String s) {
        if (s.equals(_VOID) || s.equals(_BOOL) || s.equals(_INT)) { //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            type = s;
        } else if (s.startsWith(ENUMERATION)) {
            type = ENUMERATION;
            addEnumerationType(s);
        } else
            throw new IllegalArgumentException(Messages.getString("jUCMNavType.InvalidTypeSpecified")); //$NON-NLS-1$

    }

    /**
     * Create a new type with a pre-defined list of enumerations.
     * 
     * @param al
     *            the list of strings starting with {@link #ENUMERATION}
     */
    public jUCMNavType(ArrayList al) {
        for (Iterator iter = al.iterator(); iter.hasNext();) {
            if (!iter.next().toString().startsWith(ENUMERATION)) {
                throw new IllegalArgumentException(Messages.getString("jUCMNavType.InvalidTypeSpecified")); //$NON-NLS-1$
            }
        }
        enumerationList = al;
        type = ENUMERATION;
    }

    /**
     * Add a new enumeration type.
     * 
     * @param s
     *            a string starting with {@link #ENUMERATION}
     */
    public void addEnumerationType(String s) {
        if (!s.startsWith(ENUMERATION)) {
            throw new IllegalArgumentException(Messages.getString("jUCMNavType.InvalidTypeSpecified")); //$NON-NLS-1$
        }
        getEnumerationList().add(s);
    }

    /**
     * Assumes was initialized as the proper type.
     * 
     * @return the list of enumerations.
     */
    private ArrayList getEnumerationList() {
        if (enumerationList == null)
            enumerationList = new ArrayList();
        return enumerationList;//((ArrayList) type);
    }

    /***
     * In the past, we had three base types. INT, BOOL and ENUM. Now, because of bug 506, we allow 
     * enumeration values to have the same name as a variable. Therefore, the base type is that
     * of the variable, but we also record the potential enumeration. 
     * @return
     */
    public boolean isPotentiallyAnEnumeration()
    {
        return type == ENUMERATION || getEnumerationList().size()>0;
    }
    
    /**
     * Compares two jUCMNavTypes to determine if they are equal or not. In the case of enumerations, returns true if they can be unified.
     */
    public boolean equals(Object obj) {
        if (obj instanceof jUCMNavType) {
            jUCMNavType other = (jUCMNavType)obj;
            String otherType = other.type;
            if (this.isPotentiallyAnEnumeration() || other.isPotentiallyAnEnumeration()) {
                ArrayList type1, type2;
                if (this.isPotentiallyAnEnumeration() && !other.isPotentiallyAnEnumeration()) {
                    type1 = new ArrayList(getEnumerationList());
                    type2 = new ArrayList();
                    type2.add(otherType);
                } else if (other.isPotentiallyAnEnumeration() && !this.isPotentiallyAnEnumeration()) {
                    type1 = new ArrayList();
                    type1.add(type);
                    type2 = other.getEnumerationList();
                } else {
                    type1 = new ArrayList(getEnumerationList());
                    type2 = other.getEnumerationList();
                }
                type1.retainAll(type2);

                // do they have anything in common when unified?
                return type1.size() > 0;
            } else
                return super.equals(obj); 

        } else
            return false;
    }

    /**
     * @return The hash code
     */
    public int hashCode() {
        if (type != null)
            return type.hashCode();
        else
            return 0;
    }

    /**
     * A string representation of the type.
     */
    public String toString() {
        if (this == VOID)
            return "void"; //$NON-NLS-1$
        else if (this == BOOLEAN)
            return "Boolean"; //$NON-NLS-1$
        else if (this == INTEGER)
            return "integer"; //$NON-NLS-1$
        else
            return enumerationList.toString();
            //return type.toString();
    }
}
