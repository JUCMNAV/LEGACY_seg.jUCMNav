/**
 * 
 */

/**
 * @author Rouzbahan
 *
 */
public class DecompoitionAttribute
{
    private String name;
    private String description;
    private String srcid;
    private String destid;
    
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
    
    public void setSrcid( String s )
    {
        srcid = s;
    }
    
    public String getSrcid()
    {
        return srcid;
    }
    
    public void setDestid( String s )
    {
        destid = s;
    }
    
    public String getDestid()
    {
        return destid;
    }
    
    @Override
    public String toString()
    {
        String str = "";
        
        str = str + "name is : " + this.name + "\n";
        str = str + "description is : " + this.description + "\n";
        str = str + "srcid is : " + this.srcid + "\n";
        str = str + "destid is : " + this.destid + "\n";
        str += "---------------------------------------------------------------" +
               "---------------------------------------------------------------\n";
        
        return str;
    }
}
