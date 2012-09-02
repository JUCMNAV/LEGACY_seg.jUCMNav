package regulations.src;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.Vector;

import org.xml.sax.helpers.DefaultHandler;

import seg.jUCMNav.extensionpoints.IURNImport;
import seg.jUCMNav.importexport.ImportGRLCatalog;
import seg.jUCMNav.model.ModelCreationFactory;
import urn.URNspec;

/**
 * @author Rouzbahan
 *
 */
public class CSVLegislationImport extends DefaultHandler implements IURNImport
{
  
    private URNspec urn;
    //private GRLGraph graph;
    //private HashMap map;
    //private ArrayList idList;
    ImportGRLCatalog IGR;

    //private Vector autolayoutDiagrams;

    @Override
    public URNspec importURN(FileInputStream fis, Vector autolayoutDiagrams) throws InvocationTargetException
    {
        // Of no use
        return null;
    }

    @Override
    public URNspec importURN(String filename, Vector autolayoutDiagrams) throws InvocationTargetException
    {
        String nameOfFile, saveURI;
        int lastBackSlash = 0;
        File file = new File(filename);
        FileInputStream grlFileStream = null;
        
        //if ( file.isFile() )
            //System.out.println("\nIn Plugin-> file is correct\n");
        
        // For debugging
        //System.out.println("\nIn Plugin-> filename : " + filename + "\n");
        
        // Capturing the name of file from its URI
        for ( int i = filename.length() - 1; i >= 0; i-- )
        {
            if ( filename.charAt( i ) == '\\' )        
            {
                lastBackSlash = i;
                break;
            }
        }
        
        // Name of file is saved in nameOfFile
        nameOfFile = (String) filename.subSequence(++lastBackSlash, filename.length());
        
        // careating saveURI
        saveURI = filename.substring(0, lastBackSlash);
        saveURI.replace("\\", "//");
        
        // For debugging
        //System.out.println("\nIn Plugin-> nameOfFile : " + nameOfFile + "\n");
        //System.out.println("\nIn Plugin-> saveURI : " + saveURI + "\n");
      
        CSVFile legislationFileSimple = new CSVFile(filename);
        XMLFileSimple xmlFileSimple = new XMLFileSimple();
        
        // For debugging
        //System.out.println("\nIn Plugin-> csv file is found with no error!!!\n");
        //System.out.println("The name of the csv file is : \"" + legislationFileSimple.getFileName() + "\"\n");
      
        // Reading and cleaning up file
        legislationFileSimple.ReadFile();
        legislationFileSimple.deleteBlanks();
        
        // For debugging
        //System.out.println("\nIn Plugin-> csv file can be read with no error!!!\n");
      
        // Creating lists of information provided by csv file
        xmlFileSimple.setLegislationID( legislationFileSimple.getStringValues() );
        xmlFileSimple.setLegislationSection( legislationFileSimple.getStringValues() );
        xmlFileSimple.setIntentionalElement( legislationFileSimple.getStringValues() );
        xmlFileSimple.setAltName( legislationFileSimple.getStringValues() );
        xmlFileSimple.setHyperlink( legislationFileSimple.getStringValues() );
        xmlFileSimple.setAltDescription( legislationFileSimple.getStringValues() );
        xmlFileSimple.setImportance( legislationFileSimple.getStringValues() );
        xmlFileSimple.setDecomposition( legislationFileSimple.getStringValues() );
      
        // Creating xml(or grl) file
        xmlFileSimple.makeElementDefinitionList();
        xmlFileSimple.makeRelationList();
        xmlFileSimple.calculateContributionValueList();
        xmlFileSimple.makeLinkDefinitionList();
        xmlFileSimple.createXMLFile(saveURI);
      
        IGR = new ImportGRLCatalog();
        urn = ModelCreationFactory.getNewURNspec(false, false);
                
        //System.out.println("File saved in Plugin!!!\n");
      
        try 
        {
            grlFileStream = new FileInputStream(saveURI + "file.grl") ;
        } 
        catch (FileNotFoundException e) 
        {
            e.printStackTrace();
        }       
        
        //System.out.println("Before calling importURN!!!\n");
        
        return IGR.importURN(grlFileStream, urn, autolayoutDiagrams);        
    }

    @Override
    public URNspec importURN(FileInputStream fis, URNspec urn, Vector autolayoutDiagrams) throws InvocationTargetException
    {
        // Of no use
        return null;
    }

    @Override
    public URNspec importURN(String filename, URNspec urn, Vector autolayoutDiagrams) throws InvocationTargetException
    {
        // Of no use
        return null;
    }    
}
