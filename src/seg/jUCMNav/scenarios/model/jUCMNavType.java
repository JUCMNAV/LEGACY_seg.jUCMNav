package seg.jUCMNav.scenarios.model;

import java.util.ArrayList;
import java.util.Iterator;

public class jUCMNavType {

    public final static jUCMNavType VOID = new jUCMNavType("#VOID");
    public final static jUCMNavType BOOLEAN = new jUCMNavType("#BOOL");
    public final static jUCMNavType INTEGER = new jUCMNavType("#INT");
    public final static String ENUMERATION = "#ENUM#";

    private Object type;

    public jUCMNavType(String s) {
        if (s.equals("#VOID") || s.equals("#BOOL") || s.equals("#INT")) {
            type = s;
        } else if (s.startsWith(ENUMERATION)) {
            type = new ArrayList();
            addEnumerationType(s);
        } else
            throw new IllegalArgumentException("Invalid type specified");

    }

    public jUCMNavType(ArrayList al) {
        for (Iterator iter = al.iterator(); iter.hasNext();) {
            if (!iter.next().toString().startsWith(ENUMERATION)) {
                throw new IllegalArgumentException("Invalid type specified");
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
    
    public String toString() {
        if (this == VOID)
            return "void";
        else if (this == BOOLEAN)
            return "Boolean";
        else if (this == INTEGER)
            return "integer";
        else  // TODO: enumeration
            return type.toString();
    }
}
