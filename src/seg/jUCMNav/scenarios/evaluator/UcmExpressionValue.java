package seg.jUCMNav.scenarios.evaluator;

import seg.jUCMNav.scenarios.model.jUCMNavType;

/***
 * In the first versions of jUCMNav (up to 4.4), a string could resolve to only one of the following.
 * a) a variable which was an int. 
 * b) a variable which was a boolean
 * c) a variable which was a string (enumeration value)
 * d) an enumeration value directly
 *  
 * 
 * We now support having variable names which also match an enumeration value. Therefore, we need a more complex data type to represent this. 
 * 
 *
 */
public class UcmExpressionValue {

    protected Object baseValue;
    protected String secondaryEnumerationValue;

    public Object getBaseValue() {
        return baseValue;
    }

    public void setBaseValue(Object baseValue) {
        this.baseValue = baseValue;
    }

    public String getSecondaryEnumerationValue() {
        return secondaryEnumerationValue;
    }

    public void setSecondaryEnumerationValue(String secondaryEnumerationValue) {
        this.secondaryEnumerationValue = secondaryEnumerationValue;
    }

    public UcmExpressionValue(boolean b) {
        this.baseValue = new Boolean(b);
        this.secondaryEnumerationValue = null;
    }

    public UcmExpressionValue(int i) {
        this.baseValue = new Integer(i);
        this.secondaryEnumerationValue = null;
    }

    public UcmExpressionValue(String s) {
        this.baseValue = null;
        this.secondaryEnumerationValue = s;
    }
    
    public UcmExpressionValue(jUCMNavType type) {
        this.baseValue = type; //most probably jUCMNavType.VOID
        this.secondaryEnumerationValue = null;
    }

    // purposefully will make it crash if incorrect data type - as per original implementation.
    public boolean booleanValue() {
        return ((Boolean) getBaseValue()).booleanValue();
    }

    public int intValue() {
        return ((Integer) getBaseValue()).intValue();
    }

    public boolean equals(Object obj) {

        if (obj == null)
            return false;
        else if (obj instanceof UcmExpressionValue) {
            UcmExpressionValue val = (UcmExpressionValue) obj;
            boolean match = false;
            // if this one has a base value, compare it.
            if (getBaseValue() != null)
                match = equals(val.getBaseValue());

            if (match)
                return true; // both share same base value (int/bool)

            // otherwise compare the enumeration value.
            if (getSecondaryEnumerationValue() != null)
                match = equals(val.getSecondaryEnumerationValue());

            return match;

        } else if (obj instanceof Integer && getBaseValue() instanceof Integer) {
            return obj.equals(intValue());
        } else if (obj instanceof Boolean && getBaseValue() instanceof Boolean) {
            return obj.equals(booleanValue());
        } else if (obj instanceof String && getSecondaryEnumerationValue() != null) {
            return ((String)obj).equalsIgnoreCase(getSecondaryEnumerationValue());
        } else if (obj instanceof jUCMNavType && getBaseValue() instanceof jUCMNavType)
        {
            return ((jUCMNavType)obj).equals(getBaseValue());
        }
        return false;
    }

    public String toString()
    {
        String result = "";  //$NON-NLS-1$
        if (getBaseValue()!=null)
            result += getBaseValue().toString();
        
        if (getSecondaryEnumerationValue() != null)
        {
            if (result.length() == 0)
            {
                result = getSecondaryEnumerationValue();
            }
            else
                result += " [" + getSecondaryEnumerationValue() + "]"; //$NON-NLS-1$ //$NON-NLS-2$
        }
        
        return result;
            
    }
    
    
    public static UcmExpressionValue loadFromObject(Object result)
    {
        if (result instanceof Integer)
            return new UcmExpressionValue((Integer)result);
        else if (result instanceof Boolean)
            return new UcmExpressionValue((Boolean)result);
        else if (result instanceof jUCMNavType)
            return new UcmExpressionValue((jUCMNavType)result);
        else if (result instanceof String)
            return new UcmExpressionValue((String)result);
        else if (result instanceof UcmExpressionValue)
            return (UcmExpressionValue)result;
        else 
            return new UcmExpressionValue(""); //$NON-NLS-1$
    }
    
}
