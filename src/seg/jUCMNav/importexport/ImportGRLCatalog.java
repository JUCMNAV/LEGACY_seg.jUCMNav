/**
 * 
 */
package seg.jUCMNav.importexport;

import grl.Contribution;
import grl.ContributionType;
import grl.Decomposition;
import grl.DecompositionType;
import grl.Dependency;
import grl.GRLGraph;
import grl.IntentionalElement;
import grl.IntentionalElementRef;
import grl.IntentionalElementType;

import java.io.FileInputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import seg.jUCMNav.extensionpoints.IURNImport;
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.commands.create.AddIntentionalElementRefCommand;
import seg.jUCMNav.model.commands.create.CreateElementLinkCommand;
import seg.jUCMNav.model.commands.create.CreateGrlGraphCommand;
import seg.jUCMNav.model.util.URNNamingHelper;
import urn.URNspec;

/**
 * This class import a GRL catalog from an xml file
 * 
 * @author Jean-François Roy
 *
 */
public class ImportGRLCatalog extends DefaultHandler implements IURNImport {

    private URNspec urn;
    private GRLGraph graph;
    private HashMap map;
    
    /* (non-Javadoc)
     * @see seg.jUCMNav.extensionpoints.IURNImport#importURN(java.io.FileInputStream)
     */
    public URNspec importURN(FileInputStream fis) throws InvocationTargetException {
        URNspec urn = ModelCreationFactory.getNewURNspec();
        
        //Remove ucm diagram (only one diagram is insert by default).
        urn.getUrndef().getSpecDiagrams().remove(0);
        
        importURN(fis, urn);
        return urn;
    }

    /* (non-Javadoc)
     * @see seg.jUCMNav.extensionpoints.IURNImport#importURN(java.lang.String)
     */
    public URNspec importURN(String filename) throws InvocationTargetException {
        //not used
        return null;
    }

    /* (non-Javadoc)
     * @see seg.jUCMNav.extensionpoints.IURNImport#importURN(java.io.FileInputStream, urn.URNspec)
     */
    public void importURN(FileInputStream fis, URNspec urn) throws InvocationTargetException {
        this.urn = urn;
        this.map = new HashMap();
        // Use the default parser
        SAXParserFactory factory = SAXParserFactory.newInstance();
        //TODO Create an XSL file instead of a DTD file to validate the XML
        //factory.setValidating(true);
        try {
            // Parse the input
            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse(fis, this);
            
            //Sanitize urnspec to resolve naming conflict
            URNNamingHelper.sanitizeURNspec(urn);

        } catch (Exception e) {
            throw new InvocationTargetException(e);
        }
    }

    /* (non-Javadoc)
     * @see seg.jUCMNav.extensionpoints.IURNImport#importURN(java.lang.String, urn.URNspec)
     */
    public void importURN(String filename, URNspec urn) throws InvocationTargetException {
        //not used 
    }

    //SAXParser function
    public void startElement(String namespaceURI, String lName, String qName, 
            Attributes attrs) throws SAXException {
        if (qName == "grl-catalog"){//Root element
            //Create a new GRLGraph in the urnspec
            CreateGrlGraphCommand graphCmd = new CreateGrlGraphCommand(urn);
            
            if (graphCmd.canExecute()){
                graph = graphCmd.getDiagram();
                graphCmd.execute();
                graph.setDescription(attrs.getValue("description"));
                graph.setName(attrs.getValue("name"));
            } else{
                throw new SAXException("Could not create a GrlGraph");
            }
        } else if (qName == "intentional-element"){ 
            //Create the intentional element 
            IntentionalElementType type = IntentionalElementType.get(attrs.getValue("type"));
            IntentionalElementRef ref = (IntentionalElementRef)ModelCreationFactory.getNewObject(urn,IntentionalElementRef.class, type.getValue());
            
            AddIntentionalElementRefCommand elementCmd = new AddIntentionalElementRefCommand(graph, ref);
            if (elementCmd.canExecute()){
                elementCmd.execute();
                //Set the definition properties define in the catalog
                ref.getDef().setDecompositionType(DecompositionType.get(attrs.getValue("decompositiontype")));
                ref.getDef().setName(attrs.getValue("name"));
                ref.getDef().setDescription(attrs.getValue("description"));
                
                //Add the new element in the hashmap for reference from links
                map.put(attrs.getValue("id"),ref.getDef());
            }else{
                throw new SAXException("Could not create IntentionalElementRef " + attrs.getValue("name"));
            }
        } else if (qName == "dependency"){ 
            //Create a dependency between the 2 elements
            IntentionalElement dependee = (IntentionalElement)map.get(attrs.getValue("dependeeid"));
            IntentionalElement depender = (IntentionalElement)map.get(attrs.getValue("dependerid"));
            
            if (dependee == null || depender == null){
                throw new SAXException("Invalid intentional element id in dependency");
            }
            Dependency depend = (Dependency)ModelCreationFactory.getNewObject(urn,Dependency.class);
            CreateElementLinkCommand linkCmd = new CreateElementLinkCommand(urn, depender, depend);
            linkCmd.setTarget(dependee);
            if (linkCmd.canExecute()){
                linkCmd.execute();
                //Set the name and description
                depend.setName(attrs.getValue("name"));
                depend.setDescription(attrs.getValue("description"));
            } else {
                throw new SAXException("Could not create Dependency");
            }
        } else if (qName == "decomposition"){ 
            //Create a decomposition between the 2 elements
            IntentionalElement src = (IntentionalElement)map.get(attrs.getValue("srcid"));
            IntentionalElement dest = (IntentionalElement)map.get(attrs.getValue("destid"));
            
            if (src == null || dest == null){
                throw new SAXException("Invalid intentional element id in decomposition");
            }
            Decomposition decomp = (Decomposition)ModelCreationFactory.getNewObject(urn,Decomposition.class);
            CreateElementLinkCommand linkCmd = new CreateElementLinkCommand(urn, src, decomp);
            linkCmd.setTarget(dest);
            if (linkCmd.canExecute()){
                //Set the name and description
                decomp.setName(attrs.getValue("name"));
                decomp.setDescription(attrs.getValue("description"));
                linkCmd.execute();
            } else {
                throw new SAXException("Could not create Decomposition");
            }
        } else if (qName == "contribution"){ 
            //Create a contribution between the 2 elements
            IntentionalElement src = (IntentionalElement)map.get(attrs.getValue("srcid"));
            IntentionalElement dest = (IntentionalElement)map.get(attrs.getValue("destid"));
            
            if (src == null || dest == null){
                throw new SAXException("Invalid intentional element id in contribution");
            }
            Contribution contrib = (Contribution)ModelCreationFactory.getNewObject(urn,Contribution.class);
            CreateElementLinkCommand linkCmd = new CreateElementLinkCommand(urn, src, contrib);
            linkCmd.setTarget(dest);
            if (linkCmd.canExecute()){
                linkCmd.execute();
                //Set the name and description
                contrib.setName(attrs.getValue("name"));
                contrib.setDescription(attrs.getValue("description"));
                //Set the contribution type
                contrib.setContribution(ContributionType.get(attrs.getValue("contributiontype")));
                //Set the correlation
                if (attrs.getValue("correlation") == "true"){
                    contrib.setCorrelation(true);
                }else{
                    contrib.setCorrelation(false);
                }
            } else {
                throw new SAXException("Could not create Decomposition");
            }
        }
    }
}


