package regulations.importExcelSheet;



/**
 * @author Rouzbahan
 *
 */
public class LinkContribution
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
  
  public void setContributiontype( String s )
  {
      contributiontype = s;
  }
  
  public String getContributiontype()
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
}
