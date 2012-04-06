package seg.jUCMNav.model.util;

import urn.URNspec;

public class StrategyEvaluationRangeHelper {
    public static final String METADATA_RANGE = "_Use0to100EvaluationRange"; //$NON-NLS-1$
    public static boolean getCurrentRange(URNspec urn) {
        String meta = MetadataHelper.getMetaData(urn, METADATA_RANGE);
        return meta != null && "true".equalsIgnoreCase(meta); //$NON-NLS-1$
    }
    public static void setCurrentRange(URNspec urn, boolean use0to100EvaluationRange) {
        MetadataHelper.addMetaData(urn, METADATA_RANGE, Boolean.valueOf(use0to100EvaluationRange).toString());
    }    
}
