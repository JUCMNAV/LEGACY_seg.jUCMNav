package seg.jUCMNav.importexport;

import grl.Evaluation;
import grl.EvaluationStrategy;
import grl.IntentionalElement;
import grl.StrategiesGroup;
import grl.kpimodel.KPINewEvalValue;

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

import seg.jUCMNav.Messages;
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
            HashMap strategyDefinition = new HashMap();
            boolean inStrategyDefinitions = false;
            
			while( (strLine = br.readLine()) != null) {
				
				if( strLine.contentEquals("")) //$NON-NLS-1$
					continue; // skip blank lines
				
				if( strLine.replace(",", "").contentEquals("")) //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
					continue; // skip 'blank' lines composed solely of commas output by some spreadsheets in CSV mode

				String [] columns = strLine.split(","); //$NON-NLS-1$

  
                if( unquote(columns[0]).contentEquals(Messages.getString("ImportGRLStrategies.StrategyName")) && columns[1].contentEquals(Messages.getString("ImportGRLStrategies.SpceAuthor")) ) { //$NON-NLS-1$ //$NON-NLS-2$
                    inStrategyDefinitions = true;
                }
                
                                
                if( unquote(columns[0]).contentEquals(Messages.getString("ImportGRLStrategies.StrategyName")) && !columns[1].contentEquals(Messages.getString("ImportGRLStrategies.SpceAuthor")) ) { //$NON-NLS-1$ //$NON-NLS-2$
                    inStrategyDefinitions = false;
					this.processSectionHeader( strLine, br, strategyDefinition );
				} else if (inStrategyDefinitions)
                {
                    strategyDefinition.put(unquote(columns[0]), strLine);
                }
				    
								
//				System.out.println ( i++ + ": " + strLine);
			}
			fis.close();
		} catch (Exception e) {//Catch exception if any
			System.err.println("Error: " + e.getMessage()); //$NON-NLS-1$
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
		if( s.startsWith("\"") && s.endsWith("\"")) { //$NON-NLS-1$ //$NON-NLS-2$
			return s.substring( 1, s.length()-1 ); // remove brackets
		} else {
			return s;
		}
	}
	
	private void processSectionHeader( String headerLine, BufferedReader br, HashMap strategyDefinition ) throws IOException {

		String [] columns = headerLine.split(","); //$NON-NLS-1$
		elementIndexes.clear();
		String name = null;
		
//		System.out.println ( "Processing header line: " + headerLine );
		
		for( int i = 1; i < columns.length; i++ ) {
			if( !(columns[i].endsWith(" (A)")  || columns[i].contentEquals("")) ) { // ignore Actor names as their evaluations can't be set //$NON-NLS-1$ //$NON-NLS-2$
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

		this.processStrategyElementValues(br, strategyDefinition); // process Evaluation values for each strategy
	}
	
	private void processStrategyElementValues(BufferedReader br, HashMap strategyDefinition) throws IOException {

		String strLine;
		EvaluationStrategy strategy = null;
		
		while( (strLine = br.readLine()) != null) {

			if( strLine.contentEquals("")) //$NON-NLS-1$
				return; // blank lines signify end of current section
			
			// need to handle lines of just commas output by some spreadsheets
			if( strLine.replace(",", "").contentEquals("")) //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				return; // skip 'blank' lines composed solely of commas output by some spreadsheets in CSV mode

			String [] columns = strLine.split(","); //$NON-NLS-1$

			String strategyName = unquote(columns[0]); // remove brackets if existing

			if( createdStrategies.containsKey( strategyName ) ) { // check if strategy was already created in a previous CSV section
				strategy = createdStrategies.get( strategyName );
			} else { // create a new strategy
			    
			    String [] strategyColumns = null;
			    String strategyLine = (String)strategyDefinition.get(strategyName);
			    if (strategyLine!=null) strategyColumns = strategyLine.split(",");
			    
				StrategiesGroup group = this.getStrategiesGroup();
				strategy = (EvaluationStrategy) ModelCreationFactory.getNewObject( urnSpec, EvaluationStrategy.class );
				strategy.setName( strategyName );
				
				if (strategyColumns!=null) {
    				strategy.setAuthor ( unquote(strategyColumns[1]) );
    				strategy.setDescription( unquote(strategyColumns[2]) );
				}
				strategy.setGroup( group );
				group.getStrategies().add( strategy );
				urnSpec.getGrlspec().getStrategies().add( strategy );
				createdStrategies.put( strategyName, strategy );
//				System.out.println( "Strategy created: " + strategy.getName() );
				
				// bug 854 - point to included strategy
				if (strategyColumns!=null) {
				    
				    String included = strategyColumns[3]; // it split it into multiple extra columns. 
				    for (int i = 4; i < strategyColumns.length; i++) {
                        String s = strategyColumns[i];
                        included += "," + s;
                        
                    }
    				included = unquote(included);
    				String[] includedNames = included.split(",");
    				
    				for (int i = 0; i < includedNames.length; i++) {
                        String name = includedNames[i]; // we have a list of included strategy names. 
                        
                        // check in the list of existing strategies for it. 
                        for (Iterator iterator = urnSpec.getGrlspec().getStrategies().iterator(); iterator.hasNext();) {
                            EvaluationStrategy strat = (EvaluationStrategy) iterator.next();
                            if (strat.getName().replace(',', ';').trim().equalsIgnoreCase(name.trim())) // need to escape the , character due to export encoding
                            {
                                strategy.getIncludedStrategies().add(strat);
                                break; // only include the first one we find - which, given the list we are looking at, should give priority to strategies already in the model. 
                            }
                        }
                    }
				}
			}			
			
			String valueString;
			int value = 0;
			
			for( int i = 1; i < columns.length; i++ ) {
				if( columns[i].contentEquals("") ) //$NON-NLS-1$
					break; // no more meaningful data on line
				if( elementIndexes.containsKey( i )) {
				    boolean importingKPIEvaluation = false;
					if( columns[i].endsWith("#")) { // computed evaluation, ignore //$NON-NLS-1$
						continue;
					} else if ( columns [i].endsWith("!")) {
					    importingKPIEvaluation = true;
					    valueString = columns[i].substring(0, columns[i].length()-1);
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
						
						if (importingKPIEvaluation)
						{
						    if (strategy.getIncludedStrategies().size() == 0)
						        newEvaluation.getKpiEvalValueSet().setEvaluationValue(value); // straight modif of the kpi value set. 
						    else 
						    {
						        newEvaluation.setKpiEvalValueSet(null); // we'll use the KPI Evaluation Set from the included strategy and override its value.  
			                    KPINewEvalValue n = (KPINewEvalValue) ModelCreationFactory.getNewObject(urnSpec, KPINewEvalValue.class);
			                    n.setEvaluationValue(value);
			                    newEvaluation.setKpiNewEvalValue(n);
						    }
						}
						
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
			String name = Messages.getString("ImportGRLStrategies.ImportDash") + basename; //$NON-NLS-1$
			
			if( !this.isGroupNameUnique(name)) {
				int i = 1;
				while( !this.isGroupNameUnique( name + "_" + i )) //$NON-NLS-1$
					i++;
				name = name + "-" + i; //$NON-NLS-1$
			}
			
			strategiesGroup = (StrategiesGroup) ModelCreationFactory.getNewObject(urnSpec, StrategiesGroup.class);
			strategiesGroup.setName( name );
			strategiesGroup.setDescription( Messages.getString("ImportGRLStrategies.EvaluationStrategiesImportedFromFIle") + ImportWizard.getFilename() ); //$NON-NLS-1$
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
			sb.append(Messages.getString("ImportGRLStrategies.FollowingIEInFile") + ImportWizard.getFilename() + Messages.getString("ImportGRLStrategies.DontExistInCurrentModel")); //$NON-NLS-1$ //$NON-NLS-2$

			for( String missing : nonexistentIElements ) {
				sb.append( "\t\"" + missing + "\"\n" ); //$NON-NLS-1$ //$NON-NLS-2$
			}
			sb.append( "\n" ); //$NON-NLS-1$
		}
		
		if( !unconvertibleValues.isEmpty() ) {
			sb.append(Messages.getString("ImportGRLStrategies.FollowingValues") ); //$NON-NLS-1$
			if( nonexistentIElements.isEmpty() ) {
				sb.append(Messages.getString("ImportGRLStrategies.InTheFileQuote") + ImportWizard.getFilename() + Messages.getString("ImportGRLStrategies.InTheFileEndQuote")); // output filename only if needed //$NON-NLS-1$ //$NON-NLS-2$
			}			
			sb.append(Messages.getString("ImportGRLStrategies.CouldNotBeConvertedToIntegers") ); //$NON-NLS-1$
			
			for( String formatError : unconvertibleValues ) {
				sb.append( "\t(" + formatError + ")" ); //$NON-NLS-1$ //$NON-NLS-2$
			}
		}
		
		if(!nonexistentIElements.isEmpty() ) {
			title = Messages.getString("ImportGRLStrategies.IENotFound"); //$NON-NLS-1$
		} else {
			title = Messages.getString("ImportGRLStrategies.InputValuesNotUnderstood"); //$NON-NLS-1$
		}
		
		final String dialogTitle = title;
		final String message = sb.toString();
		final String [] labels = { Messages.getString("ImportGRLStrategies.OK") }; //$NON-NLS-1$
		
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
