package regulations.src;

/**
 * @author Rouzbahan
 *
 */
public class MetadataAttribute
{
    private String elem;
    private String name;
    private String value;    

    public void setElem( String s )
    {
        elem = s;
    }

    public String getElem()
    {
        return elem;
    }

    public void setName( String s )
    {
        name = s;
    }

    public String getName()
    {
        return name;
    }

    public void setValue( String s )
    {
        value = s;
    }

    public String getvalue()
    {
        return value;
    }
    
    @Override
    public String toString()
    {
        String str = ""; 
        
        str = str + "\nThe metadata elem id is : " + elem;
        str = str + "\nThe metadata name is : " + name;
        str = str + "\nThe matadata value is : " + value;
        
        return str;
    }
}