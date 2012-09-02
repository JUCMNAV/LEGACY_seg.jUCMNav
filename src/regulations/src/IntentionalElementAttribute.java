package regulations.src;

/**
 * @author Rouzbahan
 *
 */
public class IntentionalElementAttribute
{
    private String id;
    private String name;
    private String description;
    private String type;
    private String decompositiontype;
    
    public void setID( String s )
    {
        id = s;
    }
    
    public String getID()
    {
        return id;
    }
    
    public void setName( String s )
    {
        name = s;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setDescription( String s )
    {
        description = s;
    }
    
    public String getDescription()
    {
        return description;
    }
    
    public void setType( String s )
    {
        type = s;
    }
    
    public String getType()
    {
        return type;
    }
    
    public void setDecompositionType( String s )
    {
        decompositiontype = s;
    }
    
    public String getDecompositiontype()
    {
        return decompositiontype;
    }
    
    @Override
    public String toString()
    {
        String str = "";
        
        str = str + "\nThe id of intentional element is : " + id;
        str = str + "\nThe name of intentional element is : " + name;
        str = str + "\nThe description of intentional element is : " + description;
        str = str + "\nThe type of intentional element is : " + type;
        str = str + "\nThe decompositiontype of intentional element is : " + decompositiontype;
        
        return str;
    }
}
