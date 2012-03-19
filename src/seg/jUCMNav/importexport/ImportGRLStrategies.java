package seg.jUCMNav.importexport;

import grl.Evaluation;
import grl.EvaluationStrategy;
import grl.IntentionalElement;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.swt.widgets.Display;

import seg.jUCMNav.extensionpoints.IURNImport;
import seg.jUCMNav.model.util.URNNamingHelper;
import seg.jUCMNav.strategies.EvaluationStrategyManager;
import urn.URNspec;

public class ImportGRLStrategies implements IURNImport  {

	private URNspec urnSpec;
	EvaluationStrategyManager esm;
	private HashMap<Integer, IntentionalElement> elementIndexes = new HashMap<Integer, IntentionalElement>(); // mapping between column indexes and Intentional Elements for current section
	static int currentValue = 0;

	@Override
	public URNspec importURN(FileInputStream fis, Vector autolayoutDiagrams)
			throws InvocationTargetException {
		System.out.println("importURN(FileInputStream fis, Vector autolayoutDiagrams) called.");
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public URNspec importURN(String filename, Vector autolayoutDiagrams)
			throws InvocationTargetException {
		// TODO Auto-generated method stub
		return null;
	}

	public URNspec importURN(FileInputStream fis, URNspec urn,
			Vector autolayoutDiagrams) throws InvocationTargetException {

		urnSpec = (URNspec) EcoreUtil.copy(urn);
    	esm = EvaluationStrategyManager.getInstance(false);

		System.out.println("importURN(FileInputStream fis, URNspec urn, Vector autolayoutDiagrams) called.");

		int i = 1;
		
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(fis));
			String strLine;

			while( (strLine = br.readLine()) != null) {
				
				if( strLine.contentEquals(""))
					continue; // skip blank lines
				
				String [] columns = strLine.split(",");
				
				if( columns[0].contentEquals("\"Strategy Name\"") && !columns[1].contentEquals(" Author") ) {
					this.processSectionHeader( strLine, br );
				}
				
				
				System.out.println ( i++ + ": " + strLine);
			}
			fis.close();
		} catch (Exception e) {//Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}

        // Sanitize urnspec to resolve naming conflict
        URNNamingHelper.sanitizeURNspec(this.urnSpec);

        return this.urnSpec;

//		return null;
	}

	private void processSectionHeader( String headerLine, BufferedReader br ) throws IOException {

		String [] columns = headerLine.split(",");
		elementIndexes.clear();
		
		for( int i = 1; i < columns.length; i++ ) {
			if( !columns[i].endsWith(" (A)") ) { // ignore Actor names as their evaluations can't be set
				for( Iterator iter = urnSpec.getGrlspec().getIntElements().iterator(); iter.hasNext(); ) {
					IntentionalElement element = (IntentionalElement) iter.next();
					if( columns[i].contains( element.getName() )) {
						elementIndexes.put( new Integer(i), element);
						System.out.println ( "element: \"" + element.getName() + "\" found at column " + i );
					}
				}
			}
		}

		this.processStrategyElementValues(br); // process Evaluation values for each strategy
	}
	
	private void processStrategyElementValues(BufferedReader br) throws IOException {

		String strLine;
		EvaluationStrategy strategy = null;
		
		while( (strLine = br.readLine()) != null) {

			if( strLine.contentEquals(""))
				return; // blank lines signify end of current section
			
			String [] columns = strLine.split(",");

			for( Iterator iter = urnSpec.getGrlspec().getStrategies().iterator(); iter.hasNext(); ) {
				EvaluationStrategy currentStrategy = (EvaluationStrategy) iter.next();
				if( columns[0].contains( currentStrategy.getName() )) {
					System.out.println ( "Strategy: \"" + currentStrategy.getName() + "\" found." );
					strategy = currentStrategy;
					break;
				}
			}

			if( strategy == null )
				continue;
			
	        esm.setStrategy(strategy);
	        esm.calculateEvaluation();
			
			String valueString;
			int value = 0;
			
			for( int i = 1; i < columns.length; i++ ) {
				if( elementIndexes.containsKey( i )) {
					if( columns[i].endsWith("*")) {
						valueString = columns[i].substring( 0, columns[i].length()-1 ); 						
					} else {
						valueString = columns[i];
					}
				
					try {
						value = Integer.parseInt( valueString );
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			
					final IntentionalElement element = elementIndexes.get(i);
					
					Display.getDefault().syncExec(new Runnable() {
						public void run() {

							Evaluation evaluation = esm.getEvaluationObject(element);
							currentValue = evaluation.getEvaluation();
						}
					});

					if( value != currentValue ) { // evaluation value in .csv file differs from that in current model
						
						System.out.println ( "For strategy \"" + strategy.getName() + "\" element: \"" + element.getName() + "\" new value = " + value + " current value = " + currentValue );

						
					}
				}
			}

		}


	}
	
	
	
	@Override
	public URNspec importURN(String filename, URNspec urn,
			Vector autolayoutDiagrams) throws InvocationTargetException {
		// TODO Auto-generated method stub
		return null;
	}

}
