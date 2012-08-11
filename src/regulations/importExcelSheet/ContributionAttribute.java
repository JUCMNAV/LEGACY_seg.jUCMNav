package regulations.importExcelSheet;



/**
 * @author Rouzbahan
 *
 */
public class ContributionAttribute
{
    private String name;
    private String description;
    private String srcid;
    private String destid;
    private String contributiontype;
    private String quantitativeContribution;
    private String correlation;
  
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
    
    public void setContributionType( String s )
    {
        contributiontype = s;
    }
  
    public String getContributionType()
    {
        return contributiontype;
    }
    
    public void setQuantitativeContribution( String s )
    {
        quantitativeContribution = s;
    }
  
    public String getQuantitativeContribution()
    {
        return quantitativeContribution;
    }
    
    public void setCorrelation( String s )
    {
        correlation = s;
    }
  
    public String getCorrelation()
    {
        return correlation;
    }
  
    @Override
    public String toString()
    {
        String str = "";
      
        str = str + "name is : " + this.name + "\n";
        str = str + "description is : " + this.description + "\n";
        str = str + "srcid is : " + this.srcid + "\n";
        str = str + "destid is : " + this.destid + "\n";
        str = str + "contributiontype is : " + this.contributiontype + "\n";
        str = str + "quantitativeContribution is : " + this.quantitativeContribution + "\n";
        str = str + "correlation is : " + this.correlation + "\n";
        str += "---------------------------------------------------------------" +
               "---------------------------------------------------------------\n";
      
        return str;
    }
}
