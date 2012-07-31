package seg.jUCMNav.importexport;

import grl.Actor;
import grl.Evaluation;
import grl.EvaluationStrategy;
import grl.GRLLinkableElement;
import grl.IntentionalElement;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import org.eclipse.swt.widgets.Display;

import seg.jUCMNav.Messages;
import seg.jUCMNav.extensionpoints.IURNExport;
import seg.jUCMNav.strategies.EvaluationStrategyManager;
import seg.jUCMNav.views.preferences.ReportGeneratorPreferences;
import seg.jUCMNav.views.wizards.importexport.ExportPreferenceHelper;
import urn.URNspec;

/**
 * Export the GRL strategies in the csv (comma-separated values)
 * 
 * @author jfroy, amiga
 * 
 */
public class ExportCSV implements IURNExport {

    public static final String COMMA = ","; //$NON-NLS-1$
    public static final String QUOTE = "\""; //$NON-NLS-1$
    public static final String END_LINE = "\r\n"; //$NON-NLS-1$ // need Windows or RFC 4180 CRLF format for new lines

    private FileOutputStream fos = null;
    private GRLLinkableElement elements [];
    private int totalElements;
    private final int DEFAULT_COLUMN_WIDTH = 7;
    private final int MIN_COLUMN_WIDTH = 5;
    private final int MAX_COLUMN_WIDTH = 12;
    
    private String escape(String s)
    {
        if (s!=null)
        {
            s = s.replace("\"", "\"\""); //$NON-NLS-1$ //$NON-NLS-2$
        }
        return s;
    }

    
    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.extensionpoints.IURNExport#export(urn.URNspec, java.io.FileOutputStream)
     */
    public void export(URNspec urn, HashMap mapDiagrams, FileOutputStream fos) throws InvocationTargetException {
        // Not Used
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.extensionpoints.IURNExport#export(urn.URNspec, java.lang.String)
     */
    public void export(URNspec urn, HashMap mapDiagrams, String filename) throws InvocationTargetException {
        try {
            fos = new FileOutputStream(filename);

            writeStrategyInfo(urn);
            writeStrategyEvaluations(urn);
        } catch (Exception e) {
            throw new InvocationTargetException(e);
        } finally {
            // close the stream
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    /**
     * Write the string to the file output stream.
     * 
     * @param s
     *            the string to write
     * @throws IOException
     */
    private void write(String s) throws IOException {
        if (s != null && s.length() > 0) {
            fos.write(s.getBytes());
        }
    }

    private void writeQuoted(String s) throws IOException {
    	write( quote(s) ); 
    }
    
    private String quote(String s) {
    	return QUOTE + s + QUOTE;
    }
    
    private void writeEscapedAndQuoted(String s) throws IOException {
        write( quote(escape(s)) ); 
    }
    
    private void writeStrategyInfo(URNspec urn) throws IOException {
    	String description;
    	
    	write( quote(Messages.getString("ExportCSV.GRLStrategiesFor")) + COMMA + quote( ExportPreferenceHelper.getFilenamePrefix() ) + END_LINE + END_LINE + END_LINE); //$NON-NLS-1$
    	
        write("\"Strategy Name\", Author, Description, \"Included Strategies\"" + END_LINE);//$NON-NLS-1$    

    	for (Iterator iter = urn.getGrlspec().getStrategies().iterator(); iter.hasNext();) {
    		EvaluationStrategy strategy = (EvaluationStrategy) iter.next();

    		// Name
    		writeEscapedAndQuoted( strategy.getName() );

    		// Author
    		write (COMMA);
    		writeEscapedAndQuoted( strategy.getAuthor());

    		// Description
    		if ((description = strategy.getDescription()) == null) {
    			description = new String(""); //$NON-NLS-1$
    		}
    		write( COMMA + quote(escape(description).replace(',', ';'))); // Replace commas with semicolons
    		
    		write (COMMA);
    		Vector includedNames = new Vector();
    		for (Iterator iterator = strategy.getIncludedStrategies().iterator(); iterator.hasNext();) {
                EvaluationStrategy included = (EvaluationStrategy) iterator.next();
                includedNames.add(included.getName().replace(',', ';')); // get rid of our delimiter in the internal names. 
            }
    		writeEscapedAndQuoted(join(includedNames, ", ")); // only one quote for the whole list. 
    		write (END_LINE);
    	}
    	
        write(END_LINE + END_LINE);
    }
    
    static String join(Collection<?> s, String delimiter) {
        StringBuilder builder = new StringBuilder();
        Iterator iter = s.iterator();
        while (iter.hasNext()) {
            builder.append(iter.next());
            if (!iter.hasNext()) {
              break;                  
            }
            builder.append(delimiter);
        }
        return builder.toString();
    }
    
    private void writeStrategyEvaluations(URNspec urn) throws IOException {
    
    	boolean finished = false;
    	
        int columnWidth;        
    	int actorCount = urn.getGrlspec().getActors().size();
    	int elementCount = urn.getGrlspec().getIntElements().size();
    	
    	totalElements = actorCount + elementCount;
    	int index = 0, i = 0;
    	
    	elements = new GRLLinkableElement[totalElements];
    	
        for (Iterator iter = urn.getGrlspec().getActors().iterator(); iter.hasNext();) {
            Actor actor = (Actor) iter.next();
            elements[i++] = actor;
        }    	
    	
        for (Iterator iter = urn.getGrlspec().getIntElements().iterator(); iter.hasNext();) {
            IntentionalElement element = (IntentionalElement) iter.next();
            elements[i++] = element;
        }
        
        try {
			columnWidth = Integer.parseInt( ReportGeneratorPreferences.getNumberCSV_Columns() );
			
			if( columnWidth < MIN_COLUMN_WIDTH ) {
				columnWidth = DEFAULT_COLUMN_WIDTH;				
			}
		} catch (NumberFormatException e) {
			columnWidth = DEFAULT_COLUMN_WIDTH;
		}
        
        while( !finished ) {
        	finished = writeHeaderLine( index, columnWidth );
        	writeStrategies( urn, index, columnWidth );
        	index += columnWidth;
        }
    }
    
    /**
     * 
     * @param urn
     *            URNspec
     * @return 
     * @throws IOException
     */
    private boolean writeHeaderLine( int index, int columnWidth ) throws IOException {
    	
    	boolean finished = false;
    	
        write("\"Strategy Name\"");//$NON-NLS-1$    
        
        int stopPoint;
        
        if( index + columnWidth >= totalElements ) {
        	stopPoint = totalElements;
        	finished = true;
        } else {
        	stopPoint = index + columnWidth;
        }
        
        for( int j = index; j < stopPoint; j++ ) {
        	if( elements[j] instanceof Actor ) { // Write the actors names in the header
              Actor actor = (Actor) elements[j];
              write( COMMA + quote(escape( actor.getName() + " (A)") )); //$NON-NLS-1$        		
        	} else { // Write the Intentional Element names in the header
              IntentionalElement element = (IntentionalElement) elements[j];
              write( COMMA + quote(escape(element.getName())));        		
        	}
        }
        
        write(END_LINE);        
        return finished;
    }

    /**
     * Writes the strategies information.
     * 
     * @param urn
     *            urnspec
     * @throws IOException
     */
    private void writeStrategies( URNspec urn, final int index, final int columnWidth ) throws IOException {
    	
    	for (Iterator iter = urn.getGrlspec().getStrategies().iterator(); iter.hasNext();) {
    		EvaluationStrategy strategy = (EvaluationStrategy) iter.next();

    		// Name
    		writeEscapedAndQuoted( strategy.getName() );

    		// a syncExec block is needed to avoid Eclipse threading errors as calculating Evaluations attempts to update the graphical display
    		// which can't be done from the non-UI wizard thread
    		final EvaluationStrategy currentStrategy = strategy;

    		Display.getDefault().syncExec(new Runnable() {
    			public void run() {
    				try {
    					writeEvaluations(currentStrategy, index, columnWidth);
    				} catch (IOException e) {
    					e.printStackTrace();
    				}
    			}
    		});

    		write(END_LINE);
    	}
    	
		write(END_LINE + END_LINE);    	
    }

    /**
     * Writes the information about evaluations for a grl strategy.
     * 
     * @param strategy
     *            EvaluationStrategy
     * @throws IOException
     */
    private void writeEvaluations(EvaluationStrategy strategy, int index, int columnWidth) throws IOException {
    	
    	EvaluationStrategyManager esm = EvaluationStrategyManager.getInstance(false);
    	
        esm.setStrategy(strategy);
        esm.calculateEvaluation();

        int stopPoint;
        
        if( index + columnWidth >= totalElements ) {
        	stopPoint = totalElements;
        } else {
        	stopPoint = index + columnWidth;
        }
        
        for( int j = index; j < stopPoint; j++ ) {
        	if( elements[j] instanceof Actor ) { // Write evaluation for actors
              Actor actor = (Actor) elements[j];
              int evaluation = esm.getActorEvaluation(actor);
              write(COMMA + evaluation);
        	} else { // Write evaluation for intentional elements
              IntentionalElement element = (IntentionalElement) elements[j];
              Evaluation evaluation = esm.getEvaluationObject(element);

              int val = evaluation.getEvaluation();
              //val = StrategyEvaluationPreferences.getValueToVisualize(val);

              if (evaluation.getStrategies() != null) {
                  write(COMMA + val);
              } else {
                  write(COMMA + val + "#"); //$NON-NLS-1$
              }
        	}
        }
    }
}
