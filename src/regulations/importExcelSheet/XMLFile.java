package regulations.importExcelSheet;

/**
 * 
 */
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
 
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
/**
 * @author Rouzbahan
 *
 */
public class XMLFile
{
    private ArrayList <String> LegislationID;
    private ArrayList <String> XMLFileLegislationID;
    private ArrayList <String> LegislationSection;
    private ArrayList <String> XMLFileLegislationSection;
    private ArrayList <String> IntentionalElement;
    private ArrayList <String> XMLFileIntentionalElement;
    private ArrayList <String> AltName;
    private ArrayList <String> XMLFileAltName;
    private ArrayList <String> hyperlink;
    private ArrayList <String> XMLFilehyperlink;
    private ArrayList <String> AltDescription;
    private ArrayList <String> XMLFileAltDescription;
    private ArrayList <String> Importance;
    private ArrayList <String> Decomposition;
    private ArrayList <ArrayList <String>> relationTree;
    private ArrayList <ElementDefinition> ElementDefinitionList;
    private ArrayList <ElementDefinition> XMLFileElementDefinitionList;
    private ArrayList <LinkDomposition> LinkDecompositionList;
    private ArrayList <LinkContribution> LinkContributionList;
    private ArrayList <RelationNode> RelationList;
    
    private int numberofIntentionalElement;
    private final static int metadataArraySize = 5;
    
    public void setLegislationID( List <String[]> list )
    {
        String [] row;
        LegislationID = new ArrayList<String>();
      
        for ( int i = 0; i < list.size(); i++ )
        {
            row = list.get( i );
            LegislationID.add( row[ 0 ] );
        }
        
        LegislationID.remove( 0 );
        System.out.println( "Here!!!" );
        System.out.println( "\nLegislationID size is : " + LegislationID.size() );
    }
    
    public ArrayList <String> getLegislationID()
    {
        return LegislationID;
    }
    
    public void setLegislationSection( List <String[]> list )
    {
        String [] row;
        LegislationSection = new ArrayList<String>();
        
        for ( int i = 0; i < list.size(); i++ )
        {
            row = list.get( i );
            LegislationSection.add( row[ 1 ] );
        }
        
        LegislationSection.remove( 0 );
        
        System.out.println( "\nLegislationSection size is : " + LegislationSection.size() );
    }
    
    public ArrayList <String> getLegislationSection()
    {
        return LegislationSection;
    }
    
    public void setIntentionalElement( List <String[]> list )
    {
        String [] row;
        IntentionalElement = new ArrayList<String>();
        
        for ( int i = 0; i < list.size(); i++ )
        {
            row = list.get( i );
            IntentionalElement.add( row[ 2 ] );
        }
        
        IntentionalElement.remove( 0 );
        numberofIntentionalElement = IntentionalElement.size();
        
        System.out.println( "\nIntentionalElement size is : " + IntentionalElement.size() );
    }
    
    public ArrayList <String> getIntentionalElement()
    {
        return IntentionalElement;
    }
    
    public void setAltName( List <String[]> list )
    {
        String [] row;
        AltName = new ArrayList<String>();
      
        for ( int i = 0; i < list.size(); i++ )
        {
            row = list.get( i );
            AltName.add( row[ 3 ] );
        }
        
        AltName.remove( 0 );
        
        System.out.println( "\nAltName size is : " + AltName.size() );
    }
    
    public ArrayList <String> getAltName()
    {
        return AltName;
    }
    
    public void setHyperlink( List <String[]> list )
    {
        String [] row;
        hyperlink = new ArrayList<String>();
      
        for ( int i = 0; i < list.size(); i++ )
        {
            row = list.get( i );
            hyperlink.add( row[ 4 ] );
        }
        
        hyperlink.remove( 0 );
      
        System.out.println( "\nhyperlink size is : " + hyperlink.size() );
    }
    
    public ArrayList <String> getHyperlink()
    {
        return hyperlink;
    }
    
    public void setAltDescription( List <String[]> list )
    {
        AltDescription = new ArrayList<String>();
      
        for ( int i = 0; i < list.size(); i++ )
            AltDescription.add( "" );
               
        AltDescription.remove( 0 );
      
        System.out.println( "\nAltDescription size is : " + AltDescription.size() );
    }
    
    public ArrayList <String> getAltDescription()
    {
        return AltDescription;
    }
    
    public void setImportance( List <String[]> list )
    {
        String [] row;
        Importance = new ArrayList<String>();
      
        for ( int i = 0; i < list.size(); i++ )
        {
            row = list.get( i );
            Importance.add( row[ 5 ] );            
        }
        
        Importance.remove( 0 );
      
        System.out.println( "\nImportance size is : " + Importance.size() );
    }
    
    public ArrayList <String> getImportance()
    {
        return Importance;
    }
    
    public void setDecomposition( List <String[]> list )
    {
        String [] row;
        Decomposition = new ArrayList<String>();
      
        for ( int i = 0; i < list.size(); i++ )
        {
            row = list.get( i );
            Decomposition.add( row[ 6 ] );            
        }
        
        Decomposition.remove( 0 );
      
        System.out.println( "\nDecomposition size is : " + Decomposition.size() );
    }
    
    public ArrayList <String> getDecomposition()
    {
        return Decomposition;
    }
    
    public ArrayList < ArrayList <String> > getRelationTree()
    {
        return relationTree;
    }
    
    public ArrayList <ElementDefinition> getElementDefinitionList()
    {
        return ElementDefinitionList;
    }
    
    public ArrayList <RelationNode> getRelationList()
    {
        return RelationList;
    }
    
    public ArrayList <String> getXMLFileLegislationID()
    {
        return XMLFileLegislationID;
    }
    
    public ArrayList <String> getXMLFileLegislationSection()
    {
        return XMLFileLegislationSection;
    }
    
    public ArrayList <String> getXMLFileIntentionalElement()
    {
        return XMLFileIntentionalElement;
    }
    
    public ArrayList <String> getXMLFileAltName()
    {
        return XMLFileAltName;
    }
    
    public ArrayList <String> getXMLFilehyperlink()
    {
        return XMLFilehyperlink;
    }
    
    public ArrayList <String> getXMLFileAltDescription()
    {
        return XMLFileAltDescription;
    }
    
    public ArrayList <ElementDefinition> getXMLFileElementDefinitionList()
    {
        return XMLFileElementDefinitionList;
    }
    
    // this function starts making the relation tree by duplicating the LegislationSection list and  
    //removing the very first element of the tempList and eventually calling makeFather function 
    void makeRelationTree()
    {
        //to make new copy of list of LegislationSection
        ArrayList <String> tempList = new ArrayList<String>( LegislationSection );
                
        // to remove first element to have better consistency in finding relations in makeFather and findMatch
        // first element of the tempList is A.A. , and does not match other elements of list
        tempList.remove( 0 );
        makeFather( tempList );        
    }
    
    // this function creates an empty relationTree and fills it by calling findMatch function
    private void makeFather( ArrayList <String> stringlist )
    {
        relationTree = new ArrayList < ArrayList <String> >();
        
        // creating arraylists of the relationTree arraylist
        for ( int j = 0; j < stringlist.size(); j++ )
            relationTree.add( j, new ArrayList <String>() );
        
        // popularizing the relationTree 
        for ( int i = 0; i < stringlist.size(); i++ )
        {
            findMatch( stringlist, i );
            //for debugging purpose
            //System.out.println( stringlist.size() );
        }
        
        // trimming the relationTree to eliminate irrelevant information in it
        trimRelationTree();
    }
    
    // this function makes the relations between goals by matching their names
    private void findMatch( ArrayList <String> stringlist, int index )
    {
        //String tempString1 = "", tempString2 = "";
        String wanted = stringlist.get( index );
        boolean wantedFound = false, selfFound = false;
        int foundCounter = 0;
        //ArrayList < ArrayList <String> > list = new ArrayList < ArrayList <String> >();
      
        for ( int i = stringlist.get( index ).length() - 1; i >= 0; i-- )
        {         
            for ( int j = 1; j < stringlist.size(); j++ )
            {
                if ( stringlist.get( j ).contains( wanted ) )
                {
                    wantedFound = true;
                    foundCounter++;
                                                            
                    if ( relationTree.get( j ).size() != 0 ) 
                        if ( !relationTree.get( j ).contains( wanted ) )
                            relationTree.get( j ).add( wanted );                    
                }
            }
            
            if ( wantedFound )
                relationTree.get( index ).add( wanted );
            
            if ( foundCounter == ( stringlist.size() - 1 ) )
                break;
            else
            {
                wanted = wanted.substring( 0, i );
                foundCounter = 0;
                wantedFound = false;
            }
        }
        
        // a little tweak for relationTree
        relationTree.get( 0 ).add( 0, stringlist.get( 0 ) );
    }
    
    public void trimRelationTree()
    {
        boolean found = false;
        ArrayList <String> templist;
        
        // creating regular expression
        Pattern p = Pattern.compile( "AA(\\s)4(\\.)7([1-9])?([1-9])?(\\s)?(\\([1-9]\\))?(\\([a-z]([a-z])*\\))*" );
        Matcher m;
               
        // for debugging
        System.out.println( "\ntrimming is started!!!!!!!!! \n" );      
        
        // to remove the irrelevant elements form the relationTree using regular expressions      
        for ( int i = 0; i < relationTree.size(); i++ )
        {    
            templist = new ArrayList <String>();
            
            for ( int j = 0; j < relationTree.get( i ).size(); j++ )
            {
                m = p.matcher( relationTree.get( i ).get( j ) );
                
                if ( m.matches() )
                {
                    templist.add( relationTree.get( i ).get( j ) );
                    //continue;
                }
            }
            
            relationTree.set( i, templist );
        }
        
        // to remove all the white space at the end of the elements and
        // to remove all the redundant elements
        for ( int i = 0; i < relationTree.size(); i++ )
        {
            templist = new ArrayList <String>();
            
            for ( int j = 0; j < relationTree.get( i ).size(); j++ )
            {
                if ( relationTree.get( i ).get( j ).charAt( relationTree.get( i ).get( j ).length() - 1 ) == ' ' )
                {
                    relationTree.get( i ).add( j, relationTree.get( i ).get( j ).trim() );
                    relationTree.get( i ).remove( j + 1 );
                }
            }
            
            for ( int j = 0; j < relationTree.get( i ).size(); j++ )
            {
                if ( ! templist.contains( relationTree.get( i ).get( j ) ) )
                    templist.add( relationTree.get( i ).get( j ) );
            }
            
            relationTree.set( i, templist );           
        }
        
        // to remove element from relationTree that are the only child of another element like the relation
        // between AA 4.71 (2)(g)(i)(ii)(iii) and AA 4.71 (2)(g)(i)(ii), which is useless
        for ( int i = 0; i < relationTree.size(); i++ )
        {
            templist = new ArrayList<String>();
          
            for ( int j = 0; j < relationTree.get( i ).size(); j++ )
            {
                for ( int k = 0; k < relationTree.size(); k++ )
                {
                    if ( i == k ) // if the element is checking itself
                    {
                        //if the element finds its name, it is fine to keep it as the first element of the list
                        if ( relationTree.get( k ).get( j ) == LegislationSection.get( k  + 1 ) ) 
                        {
                            found  = true;
                            break;
                        }
                        else  
                          continue;
                    }
                    else
                    {
                        if ( relationTree.get( k ).contains( relationTree.get( i ).get( j ) ) )
                        {
                            found  = true;
                            break;
                        }                       
                    }
                }
                
                if ( found == false )
                    // adding the element to a templist to remove later
                    templist.add( relationTree.get( i ).get( j ) ); 
                                    
                found = false;
            }
            
            relationTree.get( i ).removeAll( templist );
        }
        
        for ( int i = 0; i < relationTree.size(); i++ )
            for ( int j = 0; j < relationTree.size(); j++ )
                if ( relationTree.get( i ).get( 1 ).equals( relationTree.get( j ).get( 0 ) ) )
                    relationTree.get( i ).remove( 1 );        
    }
    
    // to find out the common string from the beginning of list of strings
    public String findCommonSubString( ArrayList <String> list )
    {
        boolean notFound = false;
        String commonString = "";
        String firstString;
        
        //to create a new list of LegislationSection strings
        ArrayList <String> tempList = new ArrayList<String>( list );
        
        //first element of list has different structure, so the second element is considered
        //as the start element
        firstString = tempList.get( 1 );
        
        //for debugging propose
        //System.out.println(firstString + " & size : " + firstString.length());
        
        for ( int i = 0; i < firstString.length(); i++ )
        {
            for ( int j = 2; j < tempList.size(); j++ )
            {
                if ( firstString.charAt( i ) != tempList.get( j ).charAt( i ) )
                {
                    notFound = true;
                    break;
                }
            }
            
            if ( notFound == true )
            {
                break;
            }
            else
            {
                commonString += firstString.charAt( i );
            }
        }
        
        return commonString;
    }
    
    public void createXMLFile()
    {
        try
        {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement( "grl-catalog" );
            Element firstElement = doc.createElement( "element-def" );
            Element secondElement = doc.createElement( "link-def" );
            Element thirdElement = doc.createElement( "actor-def" );
            Element fourthElement = doc.createElement( "actor-IE-link-def" );
            
            doc.appendChild( rootElement );
            Attr attr1 = doc.createAttribute( "author" );
            attr1.setValue( "Rouzbahan" );
            Attr attr2 = doc.createAttribute( "description" );
            attr2.setValue( "" );
            Attr attr3 = doc.createAttribute( "catalog-name" );
            attr3.setValue( "URNspec" );            
        }      
        catch ( ParserConfigurationException e )
        {
            e.printStackTrace();
        }
        
    }
    
    public void makeElementDefinitionList()
    {
        ElementDefinition ed;
        IntentionalElementAttribute ieAttr;
        MetadataAttribute maAttr1;
        MetadataAttribute maAttr2;
        MetadataAttribute maAttr3;
        MetadataAttribute maAttr4;
        MetadataAttribute maAttr5;
        MetadataAttribute [] mdarray; 
        Random rdNumber = new Random();
        int ID; 
        String stringID;        
        
        ElementDefinitionList = new ArrayList<ElementDefinition>();
        
        for ( int i = 0; i < numberofIntentionalElement; i++ )
        {
            ed = new ElementDefinition();
            ieAttr = new IntentionalElementAttribute();
            maAttr1 = new MetadataAttribute();
            maAttr2 = new MetadataAttribute();
            maAttr3 = new MetadataAttribute();
            maAttr4 = new MetadataAttribute();
            maAttr5 = new MetadataAttribute();
            mdarray = new MetadataAttribute[ metadataArraySize ];
            ID = 1 + rdNumber.nextInt(1000);  
            stringID = Integer.toString( ID );
            
            ieAttr.setID( stringID );
            ieAttr.setName( IntentionalElement.get( i ) );
            ieAttr.setDescription( "" );
            ieAttr.setType( "Goal" );
            ieAttr.setDecompositionType( Decomposition.get( i ) );           
                       
            maAttr1.setElem( stringID );
            maAttr1.setName( "AltName" );
            maAttr1.setValue( AltName.get(i) );
            mdarray[ 0 ] = maAttr1;
            
            maAttr2.setElem( stringID );
            maAttr2.setName( "LegislationID" );
            maAttr2.setValue( LegislationID.get(i) );
            mdarray[ 1 ] = maAttr2;
            
            maAttr3.setElem( stringID );
            maAttr3.setName( "LegislationSection" );
            maAttr3.setValue( LegislationSection.get(i) );
            mdarray[ 2 ] = maAttr3;
            
            maAttr4.setElem( stringID );
            maAttr4.setName( "hyperlink" );
            maAttr4.setValue( hyperlink.get(i) );
            mdarray[ 3 ] = maAttr4;
            
            maAttr5.setElem( stringID );
            maAttr5.setName( "AltDescription" );
            maAttr5.setValue( AltDescription.get(i) );
            mdarray[ 4 ] = maAttr5;
            
            ed.setIntentionalElementAttribute( ieAttr );
            ed.setMetadataAttrs( mdarray );
            ElementDefinitionList.add( ed );
        }
        
        elementDefinitionEnhancement();
    }
    
    private void elementDefinitionEnhancement()
    {
        int index = -1, ID, insertIndex = 0;
        int [] indexArray = new int[ numberofIntentionalElement ];
        String legislationSectionFatherString = "";
        ElementDefinition eDef;
        IntentionalElementAttribute ieAttr;
        MetadataAttribute [] mdArray;
        MetadataAttribute maAttr1;
        MetadataAttribute maAttr2;
        MetadataAttribute maAttr3;
        MetadataAttribute maAttr4;
        MetadataAttribute maAttr5;
        Random rdNumber = new Random();
        
        ArrayList <String> tempID = new ArrayList <String>( LegislationID );
        ArrayList <String> tempSection = new ArrayList <String>( LegislationSection );      
        ArrayList <String> tempIntentionalElement = new ArrayList <String>( IntentionalElement );
        ArrayList <String> tempAltName = new ArrayList <String>( AltName );
        ArrayList <String> temphyperlink = new ArrayList <String>( hyperlink );
        ArrayList <String> tempAltDescription = new ArrayList <String>( AltDescription );
        
        ArrayList <String> tempElemDefID = new ArrayList <String>();
        ArrayList <ElementDefinition> tempElementDefinitionList = new ArrayList<ElementDefinition>( ElementDefinitionList );
        
        // initializing of the indexArray
        for ( int k = 0; k < numberofIntentionalElement - 1; k++ )
            indexArray[ k ] = 0;
        
        // obtaining IDs in ElementDefinitionList into tempElemDefID
        for ( int k = 0; k < ElementDefinitionList.size(); k++ )
            tempElemDefID.add( ElementDefinitionList.get( k ).getIntentionalElementAttribute().getID() );            
               
        for ( int i = 1; i < numberofIntentionalElement; i++ )
        {
            index = i - 1;
     
            for (  int j = 0; j < relationTree.size(); j++ )
            {
                if ( LegislationSection.get( i ).equals( relationTree.get( j ).get( 0 ) ) )
                {
                    legislationSectionFatherString = relationTree.get( j ).get( 1 );
                    break;
                }
            }            
            
            if ( tempIntentionalElement.contains( legislationSectionFatherString ) )
                continue;
            else
            {
                // obtaining the needed shift for insertIndex
                for ( int k = 0; k < i; k++ )
                    insertIndex += indexArray[ k ];
                // System.out.println( "i = " + i + "\t insertIndex = " + insertIndex + " legislationSectionFatherString is " + legislationSectionFatherString );
                indexArray[ i ]++;
                insertIndex += i;
                                
                maAttr1 = new MetadataAttribute();
                maAttr2 = new MetadataAttribute();
                maAttr3 = new MetadataAttribute();
                maAttr4 = new MetadataAttribute();
                maAttr5 = new MetadataAttribute();
                mdArray = new MetadataAttribute[ metadataArraySize ];
                ieAttr = new IntentionalElementAttribute();
                eDef = new ElementDefinition();               
                
                // to create a random number for the new abstract intentional element that is unique in whole xml file
                while ( true ) // could be implemented in another way
                {
                    ID = 1 + rdNumber.nextInt(1000);                   
                                      
                    if ( !tempElemDefID.contains( Integer.toString( ID ) ) )
                        break;
                }
                
                ieAttr.setID( Integer.toString( ID ) );
                ieAttr.setName( legislationSectionFatherString );
                ieAttr.setDescription( "" );
                ieAttr.setType( "Goal" );
                ieAttr.setDecompositionType( "" );           
                           
                maAttr1.setElem( Integer.toString( ID ) );
                maAttr1.setName( "AltName" );
                maAttr1.setValue( legislationSectionFatherString );
                mdArray[ 0 ] = maAttr1;
                
                maAttr2.setElem( Integer.toString( ID ) );
                maAttr2.setName( "LegislationID" );
                maAttr2.setValue( "" );
                mdArray[ 1 ] = maAttr2;
                
                maAttr3.setElem( Integer.toString( ID ) );
                maAttr3.setName( "LegislationSection" );
                maAttr3.setValue( "" );
                mdArray[ 2 ] = maAttr3;
                
                maAttr4.setElem( Integer.toString( ID ) );
                maAttr4.setName( "hyperlink" );
                maAttr4.setValue( "" );
                mdArray[ 3 ] = maAttr4;
                
                maAttr5.setElem( Integer.toString( ID ) );
                maAttr5.setName( "AltDescription" );
                maAttr5.setValue( "" );
                mdArray[ 4 ] = maAttr5;
                
                eDef.setIntentionalElementAttribute( ieAttr );
                eDef.setMetadataAttrs( mdArray );
                
                // updating the ArrayLists
                tempID.add( insertIndex, "" ); // updated LegislationID
                tempSection.add( insertIndex, "" ); // updated LegislationSection
                tempIntentionalElement.add( insertIndex, legislationSectionFatherString ); // updated IntentionalElement
                tempAltName.add( insertIndex, legislationSectionFatherString ); // updated AltName
                temphyperlink.add( insertIndex, "" ); // updated hyperlink
                tempAltDescription.add( insertIndex, "" ); // updated AltDescription
                tempElementDefinitionList.add( insertIndex, eDef); // updated ElementDefinitionList
                
                insertIndex = 0;
            }           
        }       
        
        XMLFileAltName = new ArrayList<String>( tempAltName );
        XMLFileLegislationID = new ArrayList<String>( tempID );
        XMLFileLegislationSection = new ArrayList<String>( tempSection );
        XMLFilehyperlink = new ArrayList<String>( temphyperlink );
        XMLFileAltDescription = new ArrayList<String>( tempAltDescription );
        XMLFileIntentionalElement = new ArrayList<String>(tempIntentionalElement);
        XMLFileElementDefinitionList = new ArrayList<ElementDefinition>( tempElementDefinitionList );
    }  
    
    public void makeLinkDefinitionList()
    {
        createDecompositionList();
        createContributionList();
    }
    
    private void createDecompositionList()
    {
        ArrayList <String> fatherList;
    }
    
    private void createContributionList()
    {
      ArrayList <String> fatherList;
    }
    
    public void makeRelationList()
    {
        RelationNode rNode;
        RelationList = new ArrayList <RelationNode>();
        
        // adding A.A. to the RelationList
        rNode = new RelationNode();
        rNode.setName( LegislationSection.get( 0 ) );
        rNode.setFather( "" );
        rNode.setGrandFather( "" );
        RelationList.add( rNode );
        
        //adding other elements considering relationTree
        for ( int i = 0; i < relationTree.size(); i++ )
        {
            rNode = new RelationNode();
            rNode.setName( relationTree.get( i ).get( 0 ) );
            rNode.setFather( relationTree.get( i ).get( 1 ) );
            
            if ( relationTree.get( i ).size() > 2 )
                rNode.setGrandFather( relationTree.get( i ).get( 2 ) );
            else
                rNode.setGrandFather( "" );
            
            RelationList.add( rNode );
        }
    }
        
    @Override
    public String toString() 
    {
        String str = null;
        
        for ( int i = 0; i < numberofIntentionalElement; i++ )
        {
            str = str + "LegislationID : " + LegislationID.get( i ) + "\n";
            str = str + "LegislationSection : " + LegislationSection.get( i ) + "\n";
            str = str + "Name : " + IntentionalElement.get( i ) + "\n";
            str =  str + "AltName : " + AltName.get( i ) + "\n";
            str =  str + "hyperlink : " + hyperlink.get( i ) + "\n";
            str =  str + "AltDescription : " + AltDescription.get( i ) + "\n";
            str += "---------------------------------------------------------------" +
                   "---------------------------------------------------------------\n";
        }
        
        return str; 
    }
}
