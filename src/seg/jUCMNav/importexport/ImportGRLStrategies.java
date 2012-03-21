package seg.jUCMNav.importexport;

import grl.Evaluation;
import grl.EvaluationStrategy;
import grl.IntentionalElement;
import grl.StrategiesGroup;

import java.io.BufferedReader;
import java.io.File;
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
import seg.jUCMNav.model.ModelCreationFactory;
import seg.jUCMNav.model.util.URNNamingHelper;
import seg.jUCMNav.strategies.EvaluationStrategyManager;
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
	private HashMap<String, EvaluationStrategy> createdStrategies = new HashMap<String, EvaluationStrategy>(); // list of newly created strategies
	private Vector<String> nonexistentIElements = new Vector<String>();
	private Vector<String> unconvertibleValues = new Vector<String>();
	private StrategiesGroup strategiesGroup = null;
	
	public URNspec importURN(FileInputStream fis, URNspec urn,
			Vector autolayoutDiagrams) throws InvocationTargetException {

		urnSpec = (URNspec) EcoreUtil.copy(urn);
        URNNamingHelper.sanitizeURNspec(this.urnSpec); // Sanitize urnspec to resolve naming conflict

        esm = EvaluationStrategyManager.getInstance(false);
    	strategiesGroup = null;
    	createdStrategies.clear();
    	
//		int i = 1;
		
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(fis));
			String strLine;

			while( (strLine = br.readLine()) != null) {
				
				if( strLine.contentEquals(""))
					continue; // skip blank lines
				
				if( strLine.replace(",", "").contentEquals(""))
					continue; // skip 'blank' lines composed solely of commas output by some spreadsheets in CSV mode

				String [] columns = strLine.split(",");
				
				if( unquote(columns[0]).contentEquals("Strategy Name") && !columns[1].contentEquals(" Author") ) {
					this.processSectionHeader( strLine, br );
				}
								
//				System.out.println ( i++ + ": " + strLine);
			}
			fis.close();
		} catch (Exception e) {//Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}

		if( !nonexistentIElements.isEmpty() || !unconvertibleValues.isEmpty() ) {
			this.displayMessage(); // display warning message only if elements were not found or values not convertible, or both
		} 
		
//		for( EvaluationStrategy strategy : createdStrategies.values() ) {
//			System.out.println ( "created strategy: " + strategy.getName() + " # evaluations = " + strategy.getEvaluations().size() );
//			for( Iterator iter = strategy.getEvaluations().iterator(); iter.hasNext(); ) {
//				Evaluation eval = (Evaluation) iter.next();
//				System.out.println ( "\tElement: \"" + eval.getIntElement().getName() + "\" value = " + eval.getEvaluation() 
//						+ " qualEval: " + eval.getQualitativeEvaluation() );
//			}
//		}
		
        return this.urnSpec;
	}

	private String unquote( String s ) {
		if( s.startsWith("\"") && s.endsWith("\"")) {
			return s.substring( 1, s.length()-1 ); // remove brackets
		} else {
			return s;
		}
	}
	
	private void processSectionHeader( String headerLine, BufferedReader br ) throws IOException {

		String [] columns = headerLine.split(",");
		elementIndexes.clear();
		String name = null;
		
//		System.out.println ( "Processing header line: " + headerLine );
		
		for( int i = 1; i < columns.length; i++ ) {
			if( !(columns[i].endsWith(" (A)")  || columns[i].contentEquals("")) ) { // ignore Actor names as their evaluations can't be set
				boolean elementFound = false;
				for( Iterator iter = urnSpec.getGrlspec().getIntElements().iterator(); iter.hasNext(); ) {
					IntentionalElement element = (IntentionalElement) iter.next();
					name = unquote(columns[i]);
					if( name.contentEquals( element.getName() )) {
						elementIndexes.put( new Integer(i), element);
						elementFound = true;
//						System.out.println ( "element: \"" + element.getName() + "\" found at column " + i );
						break;
					}
				}
				if( !elementFound ) {
					nonexistentIElements.add(name);
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
			if( strLine.replace(",", "").contentEquals(""))
				return; // skip 'blank' lines composed solely of commas output by some spreadsheets in CSV mode

			String [] columns = strLine.split(",");

			String strategyName = unquote(columns[0]); // remove brackets if existing

			if( createdStrategies.containsKey( strategyName ) ) { // check if strategy was already created in a previous CSV section
				strategy = createdStrategies.get( strategyName );
			} else { // create a new strategy
				StrategiesGroup group = this.getStrategiesGroup();
				strategy = (EvaluationStrategy) ModelCreationFactory.getNewObject( urnSpec, EvaluationStrategy.class );
				strategy.setName( strategyName );
				strategy.setGroup( group );
				group.getStrategies().add( strategy );
				urnSpec.getGrlspec().getStrategies().add( strategy );
				createdStrategies.put( strategyName, strategy );
//				System.out.println( "Strategy created: " + strategy.getName() );
			}			
			
			String valueString;
			int value = 0;
			
			for( int i = 1; i < columns.length; i++ ) {
				if( columns[i].contentEquals("") )
					break; // no more meaningful data on line
				if( elementIndexes.containsKey( i )) {
					if( columns[i].endsWith("#")) { // computed evaluation, ignore
						continue; 						
					} else {
						valueString = columns[i];
					}
				
					boolean formatError = false;
					try {
						value = Integer.parseInt( valueString );
					} catch (NumberFormatException e) {
						unconvertibleValues.add( valueString );
						formatError = true;
					}

					if( !formatError ) {
						IntentionalElement element = elementIndexes.get(i);
						Evaluation newEvaluation = (Evaluation)  ModelCreationFactory.getNewObject( urnSpec, Evaluation.class );
						newEvaluation.setEvaluation(value);
						newEvaluation.setStrategies(strategy);
						newEvaluation.setIntElement(element);
						strategy.getEvaluations().add(newEvaluation);
						esm.syncIntentionalElementQualitativeEvaluation( newEvaluation, value );
					}
				}
			}

			columns = null;
		}


	}
	
	private StrategiesGroup getStrategiesGroup()
	{
		if( strategiesGroup == null ) {
			
			String path = ImportWizard.getFilename();
			String basename = path.substring( path.lastIndexOf( File.separatorChar )+1, path.lastIndexOf('.'));
			String name = "Import-" + basename;
			
			if( !this.isGroupNameUnique(name)) {
				int i = 1;
				while( !this.isGroupNameUnique( name + "-" + i ))
					i++;
				name = name + "-" + i;
			}
			
			strategiesGroup = (StrategiesGroup) ModelCreationFactory.getNewObject(urnSpec, StrategiesGroup.class);
			strategiesGroup.setName( name );
			strategiesGroup.setDescription( "Evaluation Strategies imported from file " + ImportWizard.getFilename() );
			urnSpec.getGrlspec().getGroups().add(strategiesGroup);
//			System.out.println( "Strategy group created: " + strategiesGroup.getName() );

		} 
	
		return strategiesGroup;
	}
	
	private boolean isGroupNameUnique( String name )
	{
		for( Iterator iter = urnSpec.getGrlspec().getGroups().iterator(); iter.hasNext(); ) {
			StrategiesGroup group = (StrategiesGroup) iter.next();
			if( group.getName().contentEquals( name ))
				return false;
		}
		
		return true;
	}
	
	private void displayMessage()
	{
		StringBuilder sb = new StringBuilder();
		String title;
		
		if( !nonexistentIElements.isEmpty() ) {
			sb.append("The following Intentional Elements in the file \"" + ImportWizard.getFilename() + "\" don't exist in the current model.\n\n");

			for( String missing : nonexistentIElements ) {
				sb.append( "\t\"" + missing + "\"\n" );
			}
			sb.append( "\n" );
		}
		
		if( !unconvertibleValues.isEmpty() ) {
			sb.append("The following values " );
			if( nonexistentIElements.isEmpty() ) {
				sb.append("in the file \"" + ImportWizard.getFilename() + "\" "); // output filename only if needed
			}			
			sb.append("could not be converted to integers. They were ignored.\n" );
			
			for( String formatError : unconvertibleValues ) {
				sb.append( "\t(" + formatError + ")" );
			}
		}
		
		if(!nonexistentIElements.isEmpty() ) {
			title = "Intentional Elements Not Found";
		} else {
			title = "Input Values Not Understood";
		}
		
		final String dialogTitle = title;
		final String message = sb.toString();
		final String [] labels = { "OK" };
		
		Display.getDefault().syncExec(new Runnable() {
			public void run() {
				Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
				MessageDialog md = new MessageDialog( shell, dialogTitle, null, message, MessageDialog.INFORMATION, labels, 0 );
				md.create();
				md.open();
			}
		});
	}
	
	@Override
	public URNspec importURN(FileInputStream fis, Vector autolayoutDiagrams)
			throws InvocationTargetException {
		// not used
		return null;
	}

	@Override
	public URNspec importURN(String filename, Vector autolayoutDiagrams)
			throws InvocationTargetException {
		// not used
		return null;
	}


	@Override
	public URNspec importURN(String filename, URNspec urn,
			Vector autolayoutDiagrams) throws InvocationTargetException {
		// not used
		return null;
	}
}
