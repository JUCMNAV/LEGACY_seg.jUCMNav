package seg.jUCMNav.scenarios.model;

import java.util.ArrayList;
import java.util.Iterator;

import seg.jUCMNav.Messages;

public class jUCMNavType {

    public final static jUCMNavType VOID = new jUCMNavType("#VOID"); //$NON-NLS-1$
    public final static jUCMNavType BOOLEAN = new jUCMNavType("#BOOL"); //$NON-NLS-1$
    public final static jUCMNavType INTEGER = new jUCMNavType("#INT"); //$NON-NLS-1$
    public final static String ENUMERATION = "#ENUM#"; //$NON-NLS-1$

    private Object type;

    public jUCMNavType(String s) {
        if (s.equals("#VOID") || s.equals("#BOOL") || s.equals("#INT")) { //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            type = s;
        } else if (s.startsWith(ENUMERATION)) {
            type = new ArrayList();
            addEnumerationType(s);
        } else
            throw new IllegalArgumentException(Messages.getString("jUCMNavType.InvalidTypeSpecified")); //$NON-NLS-1$

    }

    public jUCMNavType(ArrayList al) {
        for (Iterator iter = al.iterator(); iter.hasNext();) {
            if (!iter.next().toString().startsWith(ENUMERATION)) {
                throw new IllegalArgumentException(Messages.getString("jUCMNavType.InvalidTypeSpecified")); //$NON-NLS-1$
            }
        }
        type = al;
    }

    public void addEnumerationType(String s) {
        getEnumerationList().add(s);
    }

    private ArrayList getEnumerationList() {
        return ((ArrayList) type);
    }

    public boolean equals(Object obj) {
        if (obj instanceof jUCMNavType) {
            Object otherType = ((jUCMNavType) obj).type;
            if (type instanceof ArrayList || otherType instanceof ArrayList) {
                ArrayList type1, type2;
                if (type instanceof ArrayList && otherType  instanceof String) {
                    type1 = new ArrayList(getEnumerationList());
                    type2 = new ArrayList();
                    type2.add(otherType);
                } else if (otherType instanceof ArrayList && type instanceof String) {
                    type1 = new ArrayList();
                    type1.add(type);
                    type2 = (ArrayList) otherType;
                } else
                {
                    type1 = new ArrayList(getEnumerationList());
                    type2 = (ArrayList)otherType;
                }
                type1.retainAll(type2);

                // do they have anything in common when unified?
                return type1.size() > 0;
            } else
                return super.equals(obj);

        } else
            return false;
    }
    
    public int hashCode() {
    	if (type!=null)
    		return type.hashCode();
    	else
    		return 0;
    }
    public String toString() {
        if (this == VOID)
            return "void"; //$NON-NLS-1$
        else if (this == BOOLEAN)
            return "Boolean"; //$NON-NLS-1$
        else if (this == INTEGER)
            return "integer"; //$NON-NLS-1$
        else  // TODO: enumeration
            return type.toString();
    }
}
