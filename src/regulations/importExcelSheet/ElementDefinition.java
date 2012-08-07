/**
 * 
 */

/**
 * @author Rouzbahan
 *
 */
public class ElementDefinition
{
    private MetadataAttribute [] metadataAttrs;
    private IntentionalElementAttribute intelemAttr;
    
    public ElementDefinition()
    {
        super(); // just for fun;
    }
    
    public ElementDefinition( IntentionalElementAttribute ieAttr, MetadataAttribute [] mdAttr )
    {
        setIntentionalElementAttribute( ieAttr );
        setMetadataAttrs(mdAttr);
    }
    
    public MetadataAttribute [] getMetadataAttrs()
    {
        return metadataAttrs;
    }
    
    public void setMetadataAttrs( MetadataAttribute [] mdAttr )
    {
        metadataAttrs = mdAttr;
    }
    
    public IntentionalElementAttribute getIntentionalElementAttribute()
    {
        return intelemAttr;
    }
    
    public void setIntentionalElementAttribute( IntentionalElementAttribute ieAttr )
    {
        intelemAttr = ieAttr;
    }
    
    @Override
    public String toString()
    {
        String str = "";
        
        str = str + intelemAttr.toString();
        
        for ( int i = 0; i < metadataAttrs.length; i++ )
            str = str + metadataAttrs[ i ].toString();
              
        return str;
    }
}
