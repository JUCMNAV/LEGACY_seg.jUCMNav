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
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

import seg.jUCMNav.extensionpoints.IURNImport;
import seg.jUCMNav.model.util.StrategyEvaluationChange;
import seg.jUCMNav.model.util.URNNamingHelper;
import seg.jUCMNav.strategies.EvaluationStrategyManager;
import seg.jUCMNav.views.wizards.importexport.ExportPreferenceHelper;
import seg.jUCMNav.views.wizards.importexport.ImportWizard;
import urn.URNspec;

/**
 * This class imports GRL strategy evaluations from a .csv file
 * 
 * @author amiga
 * 
 */

public class ImportGRLStrategies implements IURNImport  {

	private URNspec urnSpec;
	EvaluationStrategyManager esm;
	private HashMap<Integer, IntentionalElement> elementIndexes = new HashMap<Integer, IntentionalElement>(); // mapping between column indexes and Intentional Elements for current section
	Vector<StrategyEvaluationChange> changedElements = new Vector<StrategyEvaluationChange>();
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

//		int i = 1;
		
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
				
				
//				System.out.println ( i++ + ": " + strLine);
			}
			fis.close();
		} catch (Exception e) {//Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}

		if( changedElements.isEmpty() ) {
			this.displayMessage();
		} else {  // create command to change all evaluations as a group

			System.out.println ( "\n\nChanged Evaluations (" + changedElements.size() + "):" );
			for( StrategyEvaluationChange sec : changedElements ) {
				System.out.println ( "\t\tstrategy \"" + sec.getStrategy().getName() + "\" element: \"" + sec.getIntentionalElement().getName() + "\" new value = " 
						+ sec.getNewEvaluation() + " current value = " + sec.getOldEvaluation() );
			}

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
					String name = columns[i].substring( 1, columns[i].length()-1 ); // remove brackets
					if( name.contentEquals( element.getName() )) {
						elementIndexes.put( new Integer(i), element);
//						System.out.println ( "element: \"" + element.getName() + "\" found at column " + i );
						break;
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
			
			// need to handle lines of just commas output by some spreadsheets
			
			String [] columns = strLine.split(",");
			
			for( Iterator iter = urnSpec.getGrlspec().getStrategies().iterator(); iter.hasNext(); ) {
				EvaluationStrategy currentStrategy = (EvaluationStrategy) iter.next();
				String name = columns[0].substring( 1, columns[0].length()-1 ); // remove brackets
				if( name.contentEquals( currentStrategy.getName() )) {
//					System.out.println ( "Strategy: \"" + currentStrategy.getName() + "\" found." );
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
						changedElements.add( new StrategyEvaluationChange( strategy, element, value, currentValue ) );
					}
				}
			}

			columns = null;
		}


	}
	
	private void displayMessage() {
		
		final String title = "No New Evaluations Exist";
		final String message = "All of the Intentional Element evaluations in the file \"" + ImportWizard.getFilename() + "\" match those in the current model "
		+ ExportPreferenceHelper.getFilenamePrefix() + ". There is nothing to do. No import will be performed.";
		final String [] labels = { "OK" };
		
		Display.getDefault().syncExec(new Runnable() {
			public void run() {
				Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
				MessageDialog md = new MessageDialog( shell, title, null, message, MessageDialog.INFORMATION, labels, 0 );
				md.create();
				md.open();
			}
		});
	}
	
	@Override
	public URNspec importURN(String filename, URNspec urn,
			Vector autolayoutDiagrams) throws InvocationTargetException {
		// TODO Auto-generated method stub
		return null;
	}

}
