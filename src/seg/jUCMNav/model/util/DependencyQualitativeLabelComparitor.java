package seg.jUCMNav.model.util;

import grl.QualitativeLabel;

import java.io.Serializable;
import java.util.Comparator;

/**
 * Comparator that sorts qualitative labels, providing a logical ordering for discrete values.
 * 
 * @author sghanava
 * 
 */
public class DependencyQualitativeLabelComparitor implements Comparator, Serializable {

    private static final long serialVersionUID = 1L;

    private static int[][] compareMap = {
    // D WD WS S C U N
            { 0, 1, 1, 1, 1, 1, 1 }, // D
            { -1, 0, 1, 1, -1, -1, 1 }, // WD
            { -1, -1, 0, 1, -1, -1, -1 }, // WS
            { -1, -1, -1, 0, -1, -1, -1 }, // S
            { -1, 1, 1, 1, 0, 1, 1 }, // C
            { -1, 1, 1, 1, -1, 0, 1 }, // U
            { -1, -1, 1, 1, -1, -1, 0 }, // N
    };

    public int compare(Object arg0, Object arg1) {

        QualitativeLabel a, b;

        if (arg0 instanceof QualitativeLabel)
            a = (QualitativeLabel) arg0;
        else
            return 0;
        if (arg1 instanceof QualitativeLabel)
            b = (QualitativeLabel) arg1;
        else
            return 0;

        int aValue = a.getValue();
        int bValue = b.getValue();

        int cm = compareMap[aValue][bValue];
        return cm;
    }

}
