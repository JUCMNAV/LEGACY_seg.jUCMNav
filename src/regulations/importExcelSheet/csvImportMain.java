package regulations.importExcelSheet;

/**
 * 
 */

/**
 * @author Rouzbahan
 *
 */
public class csvImportMain
{
    private static final String nameSimpleTestReturn = "CSVSimpleII.csv";
    /**
     * @param args
     */
    public static void main( String[] args )
    {
      //CSVFile legislationFile = new CSVFile( name );
      CSVFile legislationFileSimple = new CSVFile( nameSimpleTestReturn );
      //XMLFile xmlFile = new XMLFile();
      XMLFileSimple xmlFileSimple = new XMLFileSimple();
      
      //legislationFile.ReadFile();
      //legislationFile.deleteBlanks();
      
      legislationFileSimple.ReadFile();
      legislationFileSimple.deleteBlanks();
      
      //legislationFile.WriteFile();
      
      //xmlFile.setLegislationID( legislationFile.getStringValues() );
      //xmlFile.setLegislationSection( legislationFile.getStringValues() );
      //xmlFile.setIntentionalElement( legislationFile.getStringValues() );
      //xmlFile.setAltName( legislationFile.getStringValues() );
      //xmlFile.setHyperlink( legislationFile.getStringValues() );
      //xmlFile.setAltDescription( legislationFile.getStringValues() );
      //xmlFile.setImportance( legislationFile.getStringValues() );
      //xmlFile.setDecomposition( legislationFile.getStringValues() );
      
      xmlFileSimple.setLegislationID( legislationFileSimple.getStringValues() );
      xmlFileSimple.setLegislationSection( legislationFileSimple.getStringValues() );
      xmlFileSimple.setIntentionalElement( legislationFileSimple.getStringValues() );
      xmlFileSimple.setAltName( legislationFileSimple.getStringValues() );
      xmlFileSimple.setHyperlink( legislationFileSimple.getStringValues() );
      xmlFileSimple.setAltDescription( legislationFileSimple.getStringValues() );
      xmlFileSimple.setImportance( legislationFileSimple.getStringValues() );
      xmlFileSimple.setDecomposition( legislationFileSimple.getStringValues() );
          
      xmlFileSimple.makeRelationList();
      
      System.out.println( "\n---RelationList Print---\n");
      
      /*for ( int i = 0; i < xmlFileSimple.getRelationList().size(); i++ )
      {
          System.out.println( "The intentional element number " + i + "\n" 
                             + xmlFileSimple.getRelationList().get( i ) );
      }*/
      
      xmlFileSimple.calculateContributionValueList();
      
      System.out.println( "\n---ContributionValueList Print---\n");
      
      /*for ( int i = 0; i < xmlFileSimple.getContributionValueList().size(); i++ )
      {
          System.out.println( "The intentional element number " + i + " is : " +
                             + xmlFileSimple.getContributionValueList().get( i ) );
      }*/
      
      xmlFileSimple.makeElementDefinitionList();
      
      System.out.println( "\n---Element-Definition Print---\n");
      
      /*for ( int i = 0; i < xmlFileSimple.getElementDefinitionList().size(); i++ )
      {
          System.out.println( "\n---here---\n");
          System.out.println( "The intentional element number " + i + "\n" 
                             + xmlFileSimple.getElementDefinitionList().get( i ) );
      }*/
      
      xmlFileSimple.makeLinkDefinitionList();
            
      System.out.println( "\n---Decomposition-Link-Definition Print---\n");
      
      /*for ( int i = 0; i < xmlFileSimple.getDecompositionLinkDefinitionList().size(); i++ )
      {
          System.out.println( "The intentional element number " + i + "\n" 
                             + xmlFileSimple.getDecompositionLinkDefinitionList().get( i ) );
      }*/
      
      System.out.println( "\n---Contribution-Link-Definition Print---\n");
      
      /*for ( int i = 0; i < xmlFileSimple.getContributionLinkDefinitionList().size(); i++ )
      {
          System.out.println( "The intentional element number " + i + "\n" 
                             + xmlFileSimple.getContributionLinkDefinitionList().get( i ) );
      }*/      
      
      System.out.println( "\nContributionValueList size is : " + xmlFileSimple.getContributionValueList().size() + "\n" );
      System.out.println( "\nRelationList size is : " + xmlFileSimple.getRelationList().size() + "\n" );
      System.out.println( "ElementDefinitionList is : " + xmlFileSimple.getElementDefinitionList().size() + "\n" );
      
      xmlFileSimple.createXMLFile();      
    }
}
