package seg.jUCMNav.actions.metrics;

import java.util.Vector;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;

import seg.jUCMNav.Messages;
import seg.jUCMNav.actions.staticSemantic.VerifyStaticSemanticDelegate;
import seg.jUCMNav.metrics.MetricsCalculator;

public class CalculateMetricsActionDelegate extends VerifyStaticSemanticDelegate {

    public void run(IAction action) {
        if (getEditor() != null) {
            Vector result = new Vector();
            MetricsCalculator.getInstance().calculate(getEditor().getModel(), result);
            refreshProblemView(result);

            String header = Messages.getString("CalculateMetricsActionDelegate.MetricCalculation"); //$NON-NLS-1$
            boolean hasError = false;

            String message = Messages.getString("CalculateMetricsActionDelegate.NoMetrics"); //$NON-NLS-1$
            if (result.size() > 0) // first is the info message.
                message = result.size() + Messages.getString("CalculateMetricsActionDelegate.MetricsWereCalculated"); //$NON-NLS-1$

            message += Messages.getString("CalculateMetricsActionDelegate.SeeProblemsView"); //$NON-NLS-1$
            MessageDialog.openInformation(getEditor().getSite().getShell(), header, message);
        }
    }
}
