import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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
 * A class to capture all the information from the input csv file that is stored in a list of arrays of strings
 * and generating the grl(xml) and saving it.
 * 
 * @author Rouzbahan
 *
 */
public class XMLFileSimple
{
    private ArrayList <String> LegislationID;  
    private ArrayList <String> LegislationSection;
    private ArrayList <String> RefinedLegislationSection; // a list of legislation with all the elements being labeled with relevant name 
    private ArrayList <String> IntentionalElement; // ENGLISH-DESCRIPTION values
    private ArrayList <String> AltName; // FRENCH_DESCRIPTION values
    private ArrayList <String> hyperlink; //URL values
    private ArrayList <String> AltDescription; // related to grl file
    private ArrayList <String> Importance;
    private ArrayList <String> Decomposition;
    private ArrayList <String> RefinedDecomposition; // to consider all the elements AND, except for OR
    private ArrayList <ElementDefinition> ElementDefinitionList; // for grl file
    private ArrayList <DecompoitionAttribute> DecompositionLinkDefinitionList; // for grl file
    private ArrayList <ContributionAttribute> ContributionLinkDefinitionList; // for grl file
    private ArrayList <RelationNode> RelationList; // to keep relations betweeen goals
    private ArrayList <String> IDList; // list of ids of element definitions of grl file elements
    private ArrayList <String> LinkIDList; // list of ids of link definition of grl file elements
    private ArrayList <Integer> ContributionValueList; 
  
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
        System.out.println( "\nSimple LegislationID size is : " + LegislationID.size() );
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
      
        System.out.println( "\nSimple LegislationSection size is : " + LegislationSection.size() );
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
      
        System.out.println( "\nSimple IntentionalElement size is : " + IntentionalElement.size() );
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
      
        System.out.println( "\nSimple AltName size is : " + AltName.size() );
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
    
        System.out.println( "\nSimple hyperlink size is : " + hyperlink.size() );
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
    
        System.out.println( "\nSimple AltDescription size is : " + AltDescription.size() );
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
    
        System.out.println( "\nSimple Importance size is : " + Importance.size() );
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
        
        RefinedDecomposition = new ArrayList <String>( Decomposition );
        
        for ( int i = 0; i < RefinedDecomposition.size(); i++ )
        {
            if ( RefinedDecomposition.get( i ).equals( "" ) )
            {
                RefinedDecomposition.remove( i );
                RefinedDecomposition.add( i, "And" );
            }
        }
    
        System.out.println( "\nSimple Decomposition size is : " + Decomposition.size() );
        System.out.println( "\nSimple RefinedDecomposition size is : " + RefinedDecomposition.size() );
    }
  
    public ArrayList <String> getDecomposition()
    {
        return Decomposition;
    }
    
    public ArrayList <String> getRefinedDecomposition()
    {
        return RefinedDecomposition;
    }
  
    public ArrayList <ElementDefinition> getElementDefinitionList()
    {
        return ElementDefinitionList;
    }
    
    public ArrayList <DecompoitionAttribute> getDecompositionLinkDefinitionList()
    {
        return DecompositionLinkDefinitionList;
    }
    
    public ArrayList <ContributionAttribute> getContributionLinkDefinitionList()
    {
        return ContributionLinkDefinitionList;
    }
  
    public ArrayList <RelationNode> getRelationList()
    {
        return RelationList;
    }
    
    public ArrayList <Integer> getContributionValueList()
    {
        return ContributionValueList;
    }
    
    public ArrayList <String> getIDList()
    {
        return IDList;
    }
  
    // This function starts making the relation list by considering the LegislationSection, 
    // Importance and Decomposition list
    void makeRelationList()
    {
        //to make new copy of list of LegislationSection
        RefinedLegislationSection = new ArrayList <String>(LegislationSection);
        RelationNode rNode = new RelationNode();
        RelationList = new ArrayList <RelationNode>();
        String father = "";
        boolean fatherFound = false;
        ArrayList <String> arbitraryGoalNameList;  
        
        // creating a list of arbitrary Goals
        arbitraryGoalNameList = new ArrayList<String>();
        
        for ( int i = 0; i < LegislationSection.size(); i++ )
        {
            if ( LegislationSection.get( i ).equals( "" ) )
            {
                arbitraryGoalNameList.add( IntentionalElement.get( i ) );
            }
        }
        
        // Setting first node's father in RelationList
        rNode.setName( LegislationSection.get( 0 ) );
        rNode.setFather( "" );
        RelationList.add( rNode );
        
        // Setting other nodes' father in RelationList
        for ( int i = 1; i < LegislationSection.size(); i++ )
        {
            if ( ! LegislationSection.get( i ).equals( "" ) )
            {
                rNode = new RelationNode();
                
                for ( int j = arbitraryGoalNameList.size() - 1; j >= 0; j-- )
                    if ( LegislationSection.get( i ).contains( arbitraryGoalNameList.get( j ) ) )
                    {
                        father = arbitraryGoalNameList.get( j );
                        fatherFound = true;
                        break;
                    }
                
                if ( fatherFound ==  false )
                    father = LegislationSection.get( 0 );
                
                rNode.setName( LegislationSection.get( i ) );
                rNode.setFather( father );
                RelationList.add( rNode );
                fatherFound = false;
            }
            if ( LegislationSection.get( i ).equals( "" ) )
            {
                RefinedLegislationSection.remove( i );
                RefinedLegislationSection.add( i, IntentionalElement.get( i ) );
                rNode = new RelationNode();
                
                for ( int j = arbitraryGoalNameList.size() - 1; j >= 0; j-- )
                    if ( RefinedLegislationSection.get( i ).contains( arbitraryGoalNameList.get( j ) ) )
                    {
                        if ( RefinedLegislationSection.get( i ).equals( arbitraryGoalNameList.get( j ) ) )
                            continue;
                        else
                        {
                            father = arbitraryGoalNameList.get( j );
                            fatherFound = true;
                            break;
                        }
                    }
              
                if ( fatherFound ==  false )
                    father = LegislationSection.get( 0 );
              
                rNode.setName( RefinedLegislationSection.get( i ) );
                rNode.setFather( father );
                RelationList.add( rNode );
                fatherFound = false;                
            }
        }
    }
    
    // To find out the common string from the beginning of list of strings
    /*public String findCommonSubString( ArrayList <String> list )
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
    }*/

    public void createXMLFile( String saveURI )
    {
        try
        {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
          
            Document doc = docBuilder.newDocument();
            
            Element rootElement = doc.createElement( "grl-catalog" );
            Element elementdefElement = doc.createElement( "element-def" );
            Element linkdefElement = doc.createElement( "link-def" );
            Element actordefElement = doc.createElement( "actor-def" );
            Element actorIElinkElement = doc.createElement( "actor-IE-link-def" );
          
            doc.appendChild( rootElement );
            Attr attr1 = doc.createAttribute( "author" );
            attr1.setValue( "Rouzbahan" );
            rootElement.setAttributeNode( attr1 );
            Attr attr2 = doc.createAttribute( "description" );
            attr2.setValue( "" );
            rootElement.setAttributeNode( attr2 );
            Attr attr3 = doc.createAttribute( "catalog-name" );
            attr3.setValue( "URNspec" );
            rootElement.setAttributeNode( attr3 );
            
            rootElement.appendChild( elementdefElement );
            rootElement.appendChild( linkdefElement );
            rootElement.appendChild( actordefElement );
            rootElement.appendChild( actorIElinkElement );
            
            Element intentionalelementElement;
            Attr idAttr, nameAttr, descriptionAttr, typeAttr, decompositiontypeAttr;
            Element metadataelement1, metadataelement2, metadataelement3, metadataelement4, metadataelement5;
            Attr mdelemAttr1, mdnameAttr1, mdvalueAttr1;
            Attr mdelemAttr2, mdnameAttr2, mdvalueAttr2;
            Attr mdelemAttr3, mdnameAttr3, mdvalueAttr3;
            Attr mdelemAttr4, mdnameAttr4, mdvalueAttr4;
            Attr mdelemAttr5, mdnameAttr5, mdvalueAttr5;
            MetadataAttribute [] mdAttributeArray;
                        
            for ( int i = 0; i < ElementDefinitionList.size(); i++ )
            {
                intentionalelementElement = doc.createElement( "intentional-element" );
                elementdefElement.appendChild( intentionalelementElement );
              
                idAttr = doc.createAttribute( "id" );
                idAttr.setValue( ElementDefinitionList.get( i ).getIntentionalElementAttribute().getID() );
                intentionalelementElement.setAttributeNode( idAttr );
                
                nameAttr = doc.createAttribute( "name" );
                nameAttr.setValue( ElementDefinitionList.get( i ).getIntentionalElementAttribute().getName() );
                intentionalelementElement.setAttributeNode( nameAttr );
                
                descriptionAttr = doc.createAttribute( "description" );
                descriptionAttr.setValue( ElementDefinitionList.get( i ).getIntentionalElementAttribute().getDescription() );
                intentionalelementElement.setAttributeNode( descriptionAttr );
                
                typeAttr = doc.createAttribute( "type" );
                typeAttr.setValue( ElementDefinitionList.get( i ).getIntentionalElementAttribute().getType() );
                intentionalelementElement.setAttributeNode( typeAttr );
                
                decompositiontypeAttr = doc.createAttribute( "decompositiontype" );
                decompositiontypeAttr.setValue( ElementDefinitionList.get( i ).getIntentionalElementAttribute().getDecompositiontype() );
                intentionalelementElement.setAttributeNode( decompositiontypeAttr );             
                
                mdAttributeArray = ElementDefinitionList.get( i ).getMetadataAttrs();
                
                // First metadata element
                metadataelement1 = doc.createElement( "metadata" );
                elementdefElement.appendChild( metadataelement1 );
                                
                mdelemAttr1 = doc.createAttribute( "elem" );
                mdelemAttr1.setValue( mdAttributeArray[ 0 ].getElem() );
                metadataelement1.setAttributeNode( mdelemAttr1 );
                
                mdnameAttr1 = doc.createAttribute( "name" );
                mdnameAttr1.setValue( mdAttributeArray[ 0 ].getName() );
                metadataelement1.setAttributeNode( mdnameAttr1 );
                
                mdvalueAttr1 = doc.createAttribute( "value" );
                mdvalueAttr1.setValue( mdAttributeArray[ 0 ].getvalue() );
                metadataelement1.setAttributeNode( mdvalueAttr1 ); 
                
                // Second metadata element
                metadataelement2 = doc.createElement( "metadata" );
                elementdefElement.appendChild( metadataelement2 );
                
                mdelemAttr2 = doc.createAttribute( "elem" );
                mdelemAttr2.setValue( mdAttributeArray[ 1 ].getElem() );
                metadataelement2.setAttributeNode( mdelemAttr2 );
                
                mdnameAttr2 = doc.createAttribute( "name" );
                mdnameAttr2.setValue( mdAttributeArray[ 1 ].getName() );
                metadataelement2.setAttributeNode( mdnameAttr2 );
                
                mdvalueAttr2 = doc.createAttribute( "value" );
                mdvalueAttr2.setValue( mdAttributeArray[ 1 ].getvalue() );
                metadataelement2.setAttributeNode( mdvalueAttr2 );
                
                // Third metadata element
                metadataelement3 = doc.createElement( "metadata" );
                elementdefElement.appendChild( metadataelement3 );
                
                mdelemAttr3 = doc.createAttribute( "elem" );
                mdelemAttr3.setValue( mdAttributeArray[ 2 ].getElem() );
                metadataelement3.setAttributeNode( mdelemAttr3 );
                
                mdnameAttr3 = doc.createAttribute( "name" );
                mdnameAttr3.setValue( mdAttributeArray[ 2 ].getName() );
                metadataelement3.setAttributeNode( mdnameAttr3 );
                
                mdvalueAttr3 = doc.createAttribute( "value" );
                mdvalueAttr3.setValue( mdAttributeArray[ 2 ].getvalue() );
                metadataelement3.setAttributeNode( mdvalueAttr3 );
                
                // Fourth metadata element
                metadataelement4 = doc.createElement( "metadata" );
                elementdefElement.appendChild( metadataelement4 );
                
                mdelemAttr4 = doc.createAttribute( "elem" );
                mdelemAttr4.setValue( mdAttributeArray[ 3 ].getElem() );
                metadataelement4.setAttributeNode( mdelemAttr4 );
                
                mdnameAttr4 = doc.createAttribute( "name" );
                mdnameAttr4.setValue( mdAttributeArray[ 3 ].getName() );
                metadataelement4.setAttributeNode( mdnameAttr4 );
                
                mdvalueAttr4 = doc.createAttribute( "value" );
                mdvalueAttr4.setValue( mdAttributeArray[ 3 ].getvalue() );
                metadataelement4.setAttributeNode( mdvalueAttr4 );
                
                // Fifth metadata element
                metadataelement5 = doc.createElement( "metadata" );
                elementdefElement.appendChild( metadataelement5 );
                
                mdelemAttr5 = doc.createAttribute( "elem" );
                mdelemAttr5.setValue( mdAttributeArray[ 4 ].getElem() );
                metadataelement5.setAttributeNode( mdelemAttr5 );
                
                mdnameAttr5 = doc.createAttribute( "name" );
                mdnameAttr5.setValue( mdAttributeArray[ 4 ].getName() );
                metadataelement5.setAttributeNode( mdnameAttr5 );
                
                mdvalueAttr5 = doc.createAttribute( "value" );
                mdvalueAttr5.setValue( mdAttributeArray[ 4 ].getvalue() );
                metadataelement5.setAttributeNode( mdvalueAttr5 );
            }
            
            Element decompositionElement;
            Attr namedecompAttr, descriptiondecompAttr, srciddecompAttr, destiddecompAttr;
            
            for ( int i = 0; i < DecompositionLinkDefinitionList.size(); i++ )
            {
                decompositionElement = doc.createElement( "decomposition" );
                linkdefElement.appendChild( decompositionElement );
                
                namedecompAttr = doc.createAttribute( "name" );
                namedecompAttr.setValue( DecompositionLinkDefinitionList.get( i ).getName() );
                decompositionElement.setAttributeNode( namedecompAttr );
                
                descriptiondecompAttr = doc.createAttribute( "description" );
                descriptiondecompAttr.setValue( DecompositionLinkDefinitionList.get( i ).getDescription() );
                decompositionElement.setAttributeNode( descriptiondecompAttr );
                
                srciddecompAttr = doc.createAttribute( "srcid" );
                srciddecompAttr.setValue( DecompositionLinkDefinitionList.get( i ).getSrcid() );
                decompositionElement.setAttributeNode( srciddecompAttr );
                
                destiddecompAttr = doc.createAttribute( "destid" );
                destiddecompAttr.setValue( DecompositionLinkDefinitionList.get( i ).getDestid() );
                decompositionElement.setAttributeNode( destiddecompAttr );
            }
            
            Element contributionElement;
            Attr namecontrAttr, descriptioncontrAttr, srcidcontrAttr, destidcontrAttr, contributiontypeAttr, 
            quantitativeContributionAttr, correlationAttr;
            
            for ( int i = 0; i < ContributionLinkDefinitionList.size(); i++ )
            {
                contributionElement = doc.createElement( "contribution" );
                linkdefElement.appendChild( contributionElement );
                
                namecontrAttr = doc.createAttribute( "name" );
                namecontrAttr.setValue( ContributionLinkDefinitionList.get( i ).getName() );
                contributionElement.setAttributeNode( namecontrAttr );
                
                descriptioncontrAttr = doc.createAttribute( "description" );
                descriptioncontrAttr.setValue( ContributionLinkDefinitionList.get( i ).getDescription() );
                contributionElement.setAttributeNode( descriptioncontrAttr );
                
                srcidcontrAttr = doc.createAttribute( "srcid" );
                srcidcontrAttr.setValue( ContributionLinkDefinitionList.get( i ).getSrcid() );
                contributionElement.setAttributeNode( srcidcontrAttr );
                
                destidcontrAttr = doc.createAttribute( "destid" );
                destidcontrAttr.setValue( ContributionLinkDefinitionList.get( i ).getDestid() );
                contributionElement.setAttributeNode( destidcontrAttr );
                
                contributiontypeAttr = doc.createAttribute( "contributiontype" );
                contributiontypeAttr.setValue( ContributionLinkDefinitionList.get( i ).getContributionType() );
                contributionElement.setAttributeNode( contributiontypeAttr );
                
                quantitativeContributionAttr = doc.createAttribute( "quantitativeContribution" );
                quantitativeContributionAttr.setValue( ContributionLinkDefinitionList.get( i ).getQuantitativeContribution() );
                contributionElement.setAttributeNode( quantitativeContributionAttr );
                
                correlationAttr = doc.createAttribute( "correlation" );
                correlationAttr.setValue( ContributionLinkDefinitionList.get( i ).getCorrelation() );
                contributionElement.setAttributeNode( correlationAttr );
            }
            
            // Write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            
            // Name of the output file will be file.grl
            StreamResult grlResult = new StreamResult(new File(saveURI + "file.grl"));
            StreamResult xmlResult = new StreamResult(new File(saveURI + "file.xml"));
     
            // Output to console for testing
            StreamResult result = new StreamResult(System.out);
            transformer.transform(source, result);
            
            transformer.transform(source, grlResult);
            transformer.transform(source, xmlResult);
            
            System.out.println("File saved!");
        }      
        catch ( ParserConfigurationException e )
        {
            e.printStackTrace();
        }
        catch (TransformerException e) 
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
        IDList = new ArrayList <String>();
      
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
                        
            //To create a random number for the new abstract intentional element that is unique in whole xml file
            while ( true ) 
            {
                ID = 1 + rdNumber.nextInt(1000);                   
                stringID = Integer.toString( ID );
                
                if ( !IDList.contains( stringID ) )
                  break;
            }
            
            IDList.add( stringID );
          
            ieAttr.setID( stringID );
            ieAttr.setName( IntentionalElement.get( i ) );
            ieAttr.setDescription( "" );
            ieAttr.setType( "Goal" );
            ieAttr.setDecompositionType( RefinedDecomposition.get( i ) );           
                     
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
    }
 
    public void makeLinkDefinitionList()
    {
        LinkIDList = new ArrayList<String>();
        
        createDecompositionList();
        createContributionList();
    }
  
    private void createDecompositionList()
    {
        Random rdNumber = new Random();
        int ID, fatherIndex = 0;
        String stringID, fatherName;        
        DecompoitionAttribute dcompAttr;
        DecompositionLinkDefinitionList = new ArrayList<DecompoitionAttribute>();
        
        for ( int i = 0; i < Decomposition.size(); i++ )
        {
            if ( !Decomposition.get( i ).equals( "" ) )
            {
                //To create a random number for the new abstract intentional element that is unique in whole xml file
                while ( true ) 
                {
                    ID = 1 + rdNumber.nextInt(1000);                   
                    stringID = Integer.toString( ID );
                    
                    if ( ! LinkIDList.contains( stringID ) )
                      break;
                }
                
                LinkIDList.add( stringID );
                
                dcompAttr = new DecompoitionAttribute();
                dcompAttr.setName( "Decomposition" + stringID );
                dcompAttr.setDescription( "" );               
                dcompAttr.setSrcid( ElementDefinitionList.get( i ).getIntentionalElementAttribute().getID() );
                
                //Finding father's id to set as the destid
                fatherName = RelationList.get( i ).getFather();
                
                for ( int j = 0; j < RelationList.size(); j++ )
                {
                    if ( RelationList.get( j ).getName().equals( fatherName ) )
                    {
                        fatherIndex = j;
                        break;
                    }                    
                }
                
                dcompAttr.setDestid( ElementDefinitionList.get( fatherIndex ).getIntentionalElementAttribute().getID() );
                
                DecompositionLinkDefinitionList.add( dcompAttr );
            }
        }
    }
  
    private void createContributionList()
    {
        Random rdNumber = new Random();
        int ID, fatherIndex = 0, foundCounter = 0;
        String stringID, fatherName;
        ContributionAttribute contrbAttr;
        ContributionLinkDefinitionList = new ArrayList<ContributionAttribute>();
        
        for ( int i = 0; i < Importance.size(); i++ )
        {
            if ( !Importance.get( i ).equals( "" ) )
            {
                //To create a random number for the new abstract intentional element that is unique in whole xml file
                while ( true ) 
                {
                    ID = 1 + rdNumber.nextInt(1000);                   
                    stringID = Integer.toString( ID );
                  
                    if ( ! LinkIDList.contains( stringID ) )
                        break;
                }
              
                LinkIDList.add( stringID );
              
                contrbAttr = new ContributionAttribute();
                contrbAttr.setName( "Contribution" + stringID );
                contrbAttr.setDescription( "" );               
                contrbAttr.setSrcid( ElementDefinitionList.get( i ).getIntentionalElementAttribute().getID() );
              
                //Finding father's id to set as the destid
                fatherName = RelationList.get( i ).getFather();
              
                for ( int j = 0; j < RelationList.size(); j++ )
                {
                    if ( RelationList.get( j ).getName().equals( fatherName ) )
                    {
                        fatherIndex = j;
                        foundCounter++;
                        break;
                    }                  
                }
              
                contrbAttr.setDestid( ElementDefinitionList.get( fatherIndex ).getIntentionalElementAttribute().getID() );
                
                //need to be revised
                if ( ContributionValueList.get( i ) < 40  )
                    contrbAttr.setContributionType( "Help" );
                else if ( ContributionValueList.get( i ) > 40 )
                    contrbAttr.setContributionType( "SomePositive" );
                //to this point
                
                contrbAttr.setQuantitativeContribution( ContributionValueList.get( i ).toString() );
                
                contrbAttr.setCorrelation( "false" );
              
                ContributionLinkDefinitionList.add( contrbAttr );
            }
        }
        
        System.out.println("\nfoundCounter for contribution is : " + foundCounter + "\n");
    }
    
    public void calculateContributionValueList()
    {
        String father;
        ArrayList <Integer> siblingsList;
        int weightSum = 0, contributionSum = 0, contributionValue;
        boolean zeroFound = false;
        
        ContributionValueList = new ArrayList<Integer>();
        
        //Initializing ContributionValueList to 0 for all the elements
        for ( int i = 0; i < Importance.size(); i++ )
            ContributionValueList.add( 0 );
        
        for ( int i = 0; i < Importance.size(); i++ )
        {
            if ( ! Importance.get( i ).equals( "" ) )
            {
                siblingsList = new ArrayList<Integer>();
                weightSum = Integer.parseInt( Importance.get( i ) );
                father = RelationList.get( i ).getFather();
                
                //Adding the element to siblings list
                siblingsList.add( i );
                
                //Adding siblings of the element to siblings list
                for ( int j = 0; j < RelationList.size(); j++ )
                {
                    if ( ( RelationList.get( j ).getFather().equals( father ) ) && ( i != j ) )
                    {
                        siblingsList.add( j );
                        weightSum = weightSum + Integer.parseInt( Importance.get( j ) );
                    }
                }
                
                contributionValue = ( Integer.parseInt( Importance.get( i ) ) * 100 ) / weightSum;
                
                //To figure out if the element is the last contribution-not-calculated element of all siblings 
                for ( int j = 1; j < siblingsList.size(); j++ ) // first element of the list is index of the element itself, so we skip
                {
                    if ( ContributionValueList.get( siblingsList.get( j ) ) == 0 )
                    {
                        zeroFound = true;
                        break;
                    }
                }
                
                if ( zeroFound )
                {
                    ContributionValueList.remove( i );
                    ContributionValueList.add( i, contributionValue );
                }
                else //To tweak contributionValue in case of having trouble adding up to 100
                {
                    for ( int j = 1; j < siblingsList.size(); j++ )
                        contributionSum = contributionSum +  ContributionValueList.get( siblingsList.get( j ) );
                    
                    ContributionValueList.remove( i );
                    ContributionValueList.add( i, 100 - contributionSum );
                }
            }//End of if
            
            else //The element does not have a contribution, it has a decomposition. Thus 0 must be added to its related index
              // in ContributionValueList
            {
                ContributionValueList.remove( i );
                ContributionValueList.add( i, 0 );
            }
            
            contributionSum = 0;
            contributionValue = 0;
            weightSum = 0;
            zeroFound = false;
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
            str =  str + "Importance : " + Importance.get( i ) + "\n";
            str =  str + "Decomposition : " + Decomposition.get( i ) + "\n";
            str =  str + "ElementDefinitionList : " + ElementDefinitionList.get( i ) + "\n";
            str =  str + "RelationList : " + RelationList.get( i ) + "\n";
            str =  str + "DecompositionLinkDefinitionList : " + DecompositionLinkDefinitionList.get( i ) + "\n";
            str =  str + "ContributionLinkDefinitionList : " + ContributionLinkDefinitionList.get( i ) + "\n";
            str += "---------------------------------------------------------------" +
                 "---------------------------------------------------------------\n";
        } 
      
        return str; 
    }
}
