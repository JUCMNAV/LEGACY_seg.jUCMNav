package seg.jUCMNav.importexport.csm.one2one;

import java.io.PrintStream;

import seg.jUCMNav.importexport.utils.EscapeUtils;
import ucm.performance.ArrivalProcess;
import ucm.performance.Workload;

/**
 * Print optional workload attributes to CSM file Component-Ref object.
 * 
 */

public class WorkLoadAttributes {
    public void workAttributes(Workload work, PrintStream ps) {
        printArrivalPattern(ps, work);
        printArrivalParam1(ps, work);
        printArrivalParam2(ps, work);
        externalDelay(ps, work);
        value(ps, work);
        coeffSeq(ps, work);
        description(ps, work);
        tracebilityLink(ps, work);
    }

    // *** To be implemented ***
    public static void printArrivalPattern(PrintStream ps, Workload work) {
        if (work.getArrivalPattern() != null) {
            String arrivalPatternType = ""; //$NON-NLS-1$
            // to be in sync with CSM, the model's values are not used.
            if (ArrivalProcess.PERIODIC_LITERAL == work.getArrivalPattern()) {
                // arrivalPatternType =
                // ArrivalProcess.VALUES.get(ArrivalProcess.PERIODIC).toString();
                arrivalPatternType = "periodic"; //$NON-NLS-1$
            } else if (ArrivalProcess.PHASE_TYPE_LITERAL == work.getArrivalPattern()) {
                // arrivalPatternType =
                // ArrivalProcess.VALUES.get(ArrivalProcess.PHASE_TYPE).toString();
                arrivalPatternType = "phaseType"; //$NON-NLS-1$
            }
            if (ArrivalProcess.POISSON_PDF_LITERAL == work.getArrivalPattern()) {
                // arrivalPatternType =
                // ArrivalProcess.VALUES.get(ArrivalProcess.POISSON_PDF).toString();
                arrivalPatternType = "poissonPDF"; //$NON-NLS-1$
            }
            if (ArrivalProcess.UNIFORM_LITERAL == work.getArrivalPattern()) {
                // arrivalPatternType =
                // ArrivalProcess.VALUES.get(ArrivalProcess.UNIFORM).toString();
                arrivalPatternType = "uniform"; //$NON-NLS-1$
            }
            String print_aPattern = "arrivalPattern=\"" + arrivalPatternType + "\" "; //$NON-NLS-1$ //$NON-NLS-2$
            ps.print(print_aPattern);
        }
    }

    public static void printArrivalParam1(PrintStream ps, Workload work) {
        if (work.getArrivalParam1() != null) {
            String print_aParam1 = "arrivalParam1=\"" + work.getArrivalParam1() + "\" "; //$NON-NLS-1$ //$NON-NLS-2$
            ps.print(print_aParam1);
        }
    }

    public static void printArrivalParam2(PrintStream ps, Workload work) {
        if (work.getArrivalParam2() != null) {
            String print_aParam2 = "arrivalParam2=\"" + work.getArrivalParam2() + "\" "; //$NON-NLS-1$ //$NON-NLS-2$
            ps.print(print_aParam2);
        }
    }

    public static void externalDelay(PrintStream ps, Workload work) {
        if (work.getExternalDelay() != null) {
            String print_delay = "externalDelay=\"" + work.getExternalDelay() + "\" "; //$NON-NLS-1$ //$NON-NLS-2$
            ps.print(print_delay);
        }
    }

    public static void value(PrintStream ps, Workload work) {
        if (work.getValue() != null) {
            String print_value = "value=\"" + work.getValue() + "\" "; //$NON-NLS-1$ //$NON-NLS-2$
            ps.print(print_value);
        }

    }

    public static void coeffSeq(PrintStream ps, Workload work) {
        if (work.getCoeffVarSeq() != null) {
            String print_coeffVar = "coeffVarSq=\"" + work.getCoeffVarSeq() + "\" "; //$NON-NLS-1$ //$NON-NLS-2$
            ps.print(print_coeffVar);
        }

    }

    public static void description(PrintStream ps, Workload work) {
        if (work.getDescription() != null) {
            String print_description = "description=\"" + EscapeUtils.escapeXML(work.getDescription()) + "\" "; //$NON-NLS-1$ //$NON-NLS-2$
            ps.print(print_description);
        }
    }

    public static void tracebilityLink(PrintStream ps, Workload work) {
        if (work.getId() != null) {
            String print_tracebility = "traceabilityLink=\"" + work.getId() + "\" "; //$NON-NLS-1$ //$NON-NLS-2$
            ps.print(print_tracebility);
        }
    }
}
