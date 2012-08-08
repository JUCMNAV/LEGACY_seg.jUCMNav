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
    private static final String name = "CSVTest.csv";
    private static final String nameSimple = "CSVSimple.csv";
    private static final String nameSimpleTest = "CSVSimple - nametest.csv";
    private static final String nameSimpleTestReturn = "CSVSimple - nametest - return.csv";
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
      
      //System.out.println( "\nSectionIDs!!!\n" + xmlFile.toString() );
      //System.out.println( xmlFile.findCommonSubString( xmlFile.getLegislationSection() ) );
      
      //for ( int i = 0; i < xmlFile.getAltName().size(); i++ )
          //System.out.println( "start --> " + xmlFile.getAltName().get( i ) + " --> end" );
      
      /*xmlFile.makeRelationTree();
      
      for ( int i = 1; i < xmlFile.getLegislationSection().size(); i++ )
      {
          System.out.println( "fathers of " + xmlFile.getLegislationSection().get( i ) + " is --> " );
          
          for ( int j = 0; j < xmlFile.getRelationTree().get( i - 1 ).size(); j++ )
              System.out.println( xmlFile.getRelationTree().get( i - 1 ).get( j ) ); 
                        
          System.out.println( " --> fathers end\n" );
      }
      
      xmlFile.makeElementDefinitionList();      
      
      for ( int i = 0; i < xmlFile.getXMLFileElementDefinitionList().size(); i++ )
      {
          System.out.println( "\n---here---\n");
          System.out.println( "The intentional element number " + i + "\n" 
                             + xmlFile.getXMLFileElementDefinitionList().get( i ) );
      }
      
      xmlFile.makeRelationList();
      
      System.out.println( "\n---RelationList Print---\n");
      
      for ( int i = 0; i < xmlFile.getRelationList().size(); i++ )
      {
          System.out.println( "The intentional element number " + i + "\n" 
                             + xmlFile.getRelationList().get( i ) );
      }*/
      
      xmlFileSimple.makeElementDefinitionList();
      
      System.out.println( "\n---Element-Definition Print---\n");
      
      /*for ( int i = 0; i < xmlFileSimple.getElementDefinitionList().size(); i++ )
      {
          System.out.println( "\n---here---\n");
          System.out.println( "The intentional element number " + i + "\n" 
                             + xmlFileSimple.getElementDefinitionList().get( i ) );
      }*/
      
      xmlFileSimple.makeRelationList();
      
      System.out.println( "\n---RelationList Print---\n");
      
      /*for ( int i = 0; i < xmlFileSimple.getRelationList().size(); i++ )
      {
          System.out.println( "The intentional element number " + i + "\n" 
                             + xmlFileSimple.getRelationList().get( i ) );
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
      
      xmlFileSimple.calculateContributionValueList();
      
      System.out.println( "\n---ContributionValueList Print---\n");
      
      for ( int i = 0; i < xmlFileSimple.getContributionValueList().size(); i++ )
      {
          System.out.println( "The intentional element number " + i + " is : " +
                             + xmlFileSimple.getContributionValueList().get( i ) );
      }
      
      System.out.println( "\nContributionValueList size is : " + xmlFileSimple.getContributionValueList().size() + "\n" );
      System.out.println( "\nRelationList size is : " + xmlFileSimple.getRelationList().size() + "\n" );
      System.out.println( "ElementDefinitionList is : " + xmlFileSimple.getElementDefinitionList().size() + "\n" );
      
      xmlFileSimple.createXMLFile();
      
      /*// for debugging
      System.out.println( "\nSectionIDs!!!\n" + xmlFile.toString() );     
      System.out.println( "\nThe size of the list is = " + legislationFile.getStringValues().size() );
      for ( int i = 0; i < legislationFile.getStringValues().size(); i++ )
        System.out.println( "\tThe size of element[ " + i + " ] is : " + legislationFile.getStringValues().get( i ).length );
      for ( int i = 0; i < legislationFile.getStringValues().size(); i++ )
      {   
          String [] str = legislationFile.getStringValues().get( i );
          System.out.println( "\tThe SectionID of element[ " + i + " ] is : " + str[ 1 ] );
      }
      System.out.println( "\ndone!!!" );
      // end of for debugging*/
    }

}
