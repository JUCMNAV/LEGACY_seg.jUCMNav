package regulations.importExcelSheet;


/**
   * 
   */
import regulations.importExcelSheet.au.com.bytecode.opencsv.CSVReader;
import regulations.importExcelSheet.au.com.bytecode.opencsv.CSVWriter;

import java.util.*;
import java.io.*;

/**
* @author Rouzbahan
*
*/
  
public class CSVFile
{
    private String fileName;
    private List <String[]> stringValues;
    private ArrayList <String[]> stringValuesArrayList;
      
    public CSVFile( String name )
    {
        setFileName( name );
    }
      
    public void setFileName( String name )
    {
        this.fileName = name;
    }
      
    public String getFileName()
    {
        return fileName;
    }
      
    public void setStringValues( List <String[]> name )
    {
        this.stringValues = name;
    }
      
    public List <String[]> getStringValues()
    {
        return stringValues;
    }
      
    // to read a csv file into a list of strings
    public void ReadFile()
    {
        try
        {
            CSVReader reader = new CSVReader( new FileReader( fileName ), ',' );
            stringValues = (List<String[]>) reader.readAll();            
        }
        catch ( IOException e )
        {
            e.printStackTrace();
        }
    }
      
      // to write the list of strings into a csv file
      /*public void WriteFile()
      {
          StringWriter sWriter = new StringWriter();
          CSVWriter writer = new CSVWriter( sWriter );
          writer.writeAll( stringValues );
      
          System.out.println(sWriter.toString());
      }*/
      
    // to remove all the '\n's that are created by conversion of excel file to tab delimited file
    public void deleteBlanks()
    {
        String tempStr; 
        String [] stringArray;
                                         
        for ( int i = 1; i < stringValues.size(); i++ )
        {
            stringArray = stringValues.get( i );
              
            for ( int j = 0; j < stringArray.length; j++ )
            {
                tempStr = stringArray[ j ].replaceAll( "\n", "" );
                stringArray[ j ] = tempStr;                  
            }          
        }
    }
}
