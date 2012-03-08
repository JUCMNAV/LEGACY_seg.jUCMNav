package seg.jUCMNav.importexport;

import grl.Actor;
import grl.Evaluation;
import grl.EvaluationStrategy;
import grl.IntentionalElement;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;

import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;

import seg.jUCMNav.editors.UCMNavMultiPageEditor;
import seg.jUCMNav.extensionpoints.IURNExport;
import seg.jUCMNav.strategies.EvaluationStrategyManager;
import urn.URNspec;

/**
 * Export the GRL strategies in the csv (comma-separated values)
 * 
 * @author jfroy
 * 
 */
public class ExportCSV implements IURNExport {

    public static final String COMMA = ","; //$NON-NLS-1$
    public static final String QUOTE = "\""; //$NON-NLS-1$
    public static final String END_LINE = "\r\n"; //$NON-NLS-1$ // need Windows or RFC 4180 CRLF format for new lines

    private FileOutputStream fos = null;

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

            writeHeader(urn);
            writeStrategies(urn);
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
    public void write(String s) throws IOException {
        if (s != null && s.length() > 0) {
            fos.write(s.getBytes());
        }
    }

    /**
     * 
     * @param urn
     *            URNspec
     * @throws IOException
     */
    private void writeHeader(URNspec urn) throws IOException {
        write("\"Strategy Name\", Description, Author");//$NON-NLS-1$    
        // Write the actors names in the header
        for (Iterator iter = urn.getGrlspec().getActors().iterator(); iter.hasNext();) {
            Actor actor = (Actor) iter.next();
            write( COMMA + QUOTE + actor.getName() + " (A)" + QUOTE ); //$NON-NLS-1$
        }

        // Write the intentional element names in the header
        for (Iterator iter = urn.getGrlspec().getIntElements().iterator(); iter.hasNext();) {
            IntentionalElement element = (IntentionalElement) iter.next();
            write( COMMA + QUOTE + element.getName() + QUOTE );
        }
        write(END_LINE);
    }

    /**
     * Writes the strategies information.
     * 
     * @param urn
     *            urnspec
     * @throws IOException
     */
    private void writeStrategies(URNspec urn) throws IOException {
    	for (Iterator iter = urn.getGrlspec().getStrategies().iterator(); iter.hasNext();) {
    		EvaluationStrategy strategy = (EvaluationStrategy) iter.next();

    		// Name
    		write( QUOTE + strategy.getName() + QUOTE );

    		// Description
    		write(COMMA);
    		String desc = strategy.getDescription();
    		if (desc == null) {
    			desc = new String(""); //$NON-NLS-1$
    		}
    		write( QUOTE + desc.replace(',', ';') + QUOTE ); // Replace commas with semicolons

    		// Author
    		write(COMMA);
    		write( QUOTE + strategy.getAuthor() + QUOTE );


//    		if( Display.getCurrent() == null ) {
//    			System.err.println( "Thread is non-UI." );
//    		}

    		// a syncExec block is needed to avoid Eclipse threading errors as calculating Evaluations attempts to update the graphical display
    		// which can't be done from the non-UI wizard thread
    		final EvaluationStrategy currentStrategy = strategy;

    		Display.getDefault().syncExec(new Runnable() {
    			public void run() {
    				try {
    					writeEvaluations(currentStrategy);
    				} catch (IOException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
    			}
    		});

    		write(END_LINE);
    	}
    }

    /**
     * Writes the information about evaluations for a grl strategy.
     * 
     * @param strategy
     *            EvaluationStrategy
     * @throws IOException
     */
    private void writeEvaluations(EvaluationStrategy strategy) throws IOException {
    	
    	EvaluationStrategyManager esm = EvaluationStrategyManager.getInstance(false);
    	
    	if( esm == null) {
    		System.err.println( "EvaluationStrategyManager can't be created." );
    	}
    	
        esm.setStrategy(strategy);
        esm.calculateEvaluation();

        // Write evaluation for actors
        for (Iterator iter = strategy.getGrlspec().getActors().iterator(); iter.hasNext();) {
            Actor actor = (Actor) iter.next();

            int evaluation = esm.getActorEvaluation(actor);

            write(COMMA + evaluation);
        }

        // Write evaluation for intentional elements
        for (Iterator iter = strategy.getGrlspec().getIntElements().iterator(); iter.hasNext();) {
            IntentionalElement element = (IntentionalElement) iter.next();

            Evaluation evaluation = esm.getEvaluationObject(element);

            if (evaluation.getStrategies() != null) {
                write(COMMA + evaluation.getEvaluation() + "*"); //$NON-NLS-1$
            } else {
                write(COMMA + evaluation.getEvaluation()); //$NON-NLS-1$
            }
        }
    }
}
