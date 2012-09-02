package regulations.src;

/**
 * @author Rouzbahan
 *
 */
public class RelationNode
{
    private String name;
    private String father;
    private String grandfather;
    
    public RelationNode()
    {
        super();
    }
    
    public RelationNode( String name, String father )
    {
        setName( name );
        setFather( father );
    }

    public void setName( String s )
    {
        name = s;
    }

    public String getName()
    {
        return name;
    }
    
    public void setFather( String s )
    {
        father = s;
    }

    public String getFather()
    {
        return father;
    }
    
    public void setGrandFather( String s )
    {
        grandfather = s;
    }

    public String getGrandFather()
    {
        return grandfather;
    }
    
    @Override
    public String toString() 
    {
        String str = null;
        
        str = str + "name is : " + this.name + "\n";
        str = str + "father is : " + this.father + "\n";
        str = str + "grandfather is : " + this.grandfather + "\n";
        str += "---------------------------------------------------------------" +
               "---------------------------------------------------------------\n";
        
        return str; 
    }
}