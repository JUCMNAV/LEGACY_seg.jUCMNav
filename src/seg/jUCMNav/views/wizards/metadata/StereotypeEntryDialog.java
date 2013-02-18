package seg.jUCMNav.views.wizards.metadata;

import grl.GrlFactory;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import seg.jUCMNav.Messages;
import ucm.map.MapFactory;
import ucm.performance.PerformanceFactory;
import ucm.scenario.ScenarioFactory;
import urn.UrnFactory;
import urncore.UrncoreFactory;

public class StereotypeEntryDialog extends MetadataEntryDialog {

    protected Vector allTexts = new Vector();
    protected HashMap reverse = new HashMap();

    public StereotypeEntryDialog(Shell _parent) {
        super(_parent);

    }

    @Override
    public void setTitle(String title) {
        shell.setText("Edit Stereotype");
    }

    @Override
    protected void createControls() {
        Point size;

        if (values == null)
            values = new String[2];

        String[] stereotypeValues = (values[1] == null ? "" : values[1]).split(",");
        if (stereotypeValues.length != 3) {
            stereotypeValues = new String[] { "", "", "" };
        }

        Text stereodef = addTextbox("Type", "StereotypeDef", false);
        Combo applyOn = addElementsCombo("Applies on", stereotypeValues[2]);
        Text stereo = addTextbox("Stereotype", stereotypeValues[0]);
        Text val = addTextbox("Value", stereotypeValues[1]);
        
        allTexts.add(stereo);
        allTexts.add(val);
        allTexts.add(applyOn);

        ModifyListener listener = new ModifyListener() {
            public void modifyText(ModifyEvent arg0) {
                reconstructValues();
            }
        };

        for (Iterator i = allTexts.iterator(); i.hasNext();) {
            Object o = (Object) i.next();
            if (o instanceof Text) {
                Text text = (Text) o;
                text.addModifyListener(listener);
            }
        }

        Composite buttonComp = new Composite(shell, SWT.NONE);
        GridData gridData = new GridData();
        gridData.horizontalSpan = 2;
        buttonComp.setLayoutData(gridData);
        GridLayout layout = new GridLayout();
        layout.numColumns = 2;
        buttonComp.setLayout(layout);

        Button okButton = new Button(buttonComp, SWT.PUSH);
        okButton.setText(Messages.getString("MetadataEditorPage.button_ok")); //$NON-NLS-1$
        size = okButton.computeSize(SWT.DEFAULT, SWT.DEFAULT);

        gridData = new GridData();
        gridData.widthHint = size.x + 10;
        okButton.setLayoutData(gridData);
        okButton.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                shell.close();
            }
        });

        Button cancelButton = new Button(buttonComp, SWT.PUSH);
        cancelButton.setText(Messages.getString("MetadataEditorPage.button_cancel")); //$NON-NLS-1$
        size = cancelButton.computeSize(SWT.DEFAULT, SWT.DEFAULT);
        gridData = new GridData();
        gridData.widthHint = size.x + 10;
        cancelButton.setLayoutData(gridData);
        cancelButton.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent e) {
                values = null;
                shell.close();
            }
        });

        shell.setDefaultButton(okButton);
    }
    
    protected void reconstructValues()
    {
        String result = "";

        for (Iterator j = allTexts.iterator(); j.hasNext();) {
            Object o = (Object) j.next();
            if (o instanceof Text) {
                Text t = (Text) o;
                result += t.getText().replace(",", "") + ",";
            }
            else if(o instanceof Combo) {
                Combo t = (Combo)o;
                result += reverse.get(t.getText()).toString().replace(",", "") + ",";
            }
        }

        result = result.substring(0, result.length() - 1);

        values[1] = result;
    }

    protected Text addTextbox(String lblText, String value) {
        return addTextbox(lblText, value, true);
    }

    protected Text addTextbox(String lblText, String value, boolean enabled) {
        Label label = new Label(shell, SWT.RIGHT);
        label.setText(lblText + ": "); //$NON-NLS-1$
        Point size = label.computeSize(SWT.DEFAULT, SWT.DEFAULT);
        GridData gridData = new GridData();
        gridData.widthHint = size.x;
        label.setLayoutData(gridData);

        Text text = new Text(shell, SWT.BORDER);
        gridData = new GridData();
        gridData.widthHint = 320;
        gridData.grabExcessHorizontalSpace = true;
        text.setLayoutData(gridData);
        text.setText(value);
        text.setEnabled(enabled);
        
        return text;
    }

    protected Combo addElementsCombo(String lblText, String value) {
        Label label = new Label(shell, SWT.RIGHT);
        label.setText(lblText + ": "); //$NON-NLS-1$
        Point size = label.computeSize(SWT.DEFAULT, SWT.DEFAULT);
        GridData gridData = new GridData();
        gridData.widthHint = size.x;
        label.setLayoutData(gridData);

        Combo combo = new Combo(shell, SWT.BORDER | SWT.DROP_DOWN);
        gridData = new GridData();
        gridData.widthHint = 320;
        gridData.grabExcessHorizontalSpace = true;
        combo.setLayoutData(gridData);

        Vector<EClassifier> list = new Vector<EClassifier>();

        list.addAll(GrlFactory.eINSTANCE.getGrlPackage().getEClassifiers());
        list.addAll(MapFactory.eINSTANCE.getMapPackage().getEClassifiers());
        list.addAll(PerformanceFactory.eINSTANCE.getPerformancePackage().getEClassifiers());
        list.addAll(ScenarioFactory.eINSTANCE.getScenarioPackage().getEClassifiers());
        list.addAll(MapFactory.eINSTANCE.getMapPackage().getEClassifiers());
        list.addAll(UrncoreFactory.eINSTANCE.getUrncorePackage().getEClassifiers());
        list.addAll(UrnFactory.eINSTANCE.getUrnPackage().getEClassifiers());

        int j = 0;
        for (Iterator i = list.iterator(); i.hasNext();) {
            EClassifier eClassifier = (EClassifier) i.next();
            
            String name = eClassifier.getEPackage().getName().toUpperCase() + ": " + eClassifier.getName();
            
            reverse.put(name, eClassifier.getName());

            combo.add(name);
            combo.setData(eClassifier.getName(), new Integer(j));
            j++;
        }

        j = (Integer)combo.getData(value);
        combo.select(j);

        combo.addSelectionListener(new SelectionListener() {
            public void widgetDefaultSelected(SelectionEvent arg0) { }

            @Override
            public void widgetSelected(SelectionEvent arg0) {
                reconstructValues();
            }
        });
        
        return combo;
    }
}
