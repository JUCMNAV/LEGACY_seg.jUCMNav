package seg.jUCMNav.strategies;

import grl.Actor;
import grl.ActorRef;
import grl.Contribution;
import grl.Decomposition;
import grl.DecompositionType;
import grl.Dependency;
import grl.ElementLink;
import grl.Evaluation;
import grl.ImportanceType;
import grl.IntentionalElement;
import grl.IntentionalElementRef;
import grl.QualitativeLabel;

import java.util.Iterator;

import seg.jUCMNav.extensionpoints.IGRLStrategyAlgorithm;
import seg.jUCMNav.model.util.DependencyQualitativeLabelComparitor;
import seg.jUCMNav.model.util.MetadataHelper;
import urncore.IURNNode;

/**
 * This class implements the qualitative GRL evaluation algorithm.
 * 
 * @author sghanava, gunterm
 * 
 */
public class QualitativeGRLStrategyAlgorithm extends PropagationGRLStrategyAlgorithm implements IGRLStrategyAlgorithm {

    private static int D = QualitativeLabel.DENIED;
    private static int WD = QualitativeLabel.WEAKLY_DENIED;
    private static int WS = QualitativeLabel.WEAKLY_SATISFIED;
    private static int S = QualitativeLabel.SATISFIED;
    private static int C = QualitativeLabel.CONFLICT;
    private static int U = QualitativeLabel.UNKNOWN;
    private static int N = QualitativeLabel.NONE;

    private static int[][] contribTable1 = {
            // M, H+, s+, u, s-, H-, B
            { D, WD, WD, N, WS, WS, S }, // D
            { WD, WD, WD, N, WS, WS, WS }, // WD
            { WS, WS, WS, N, WD, WD, WD }, // WS
            { S, WS, WS, N, WD, WD, D }, // S
            { U, U, U, U, U, U, U }, // C
            { U, U, U, U, U, U, U }, // U
            { N, N, N, N, N, N, N }, // N
    };

    int[][] contribTable2 = { { D, D, WD, C, C, U, D }, // D
            { D, WD, N, WS, C, U, WD }, // WD
            { WD, N, WS, S, C, U, WS }, // WS
            { C, WS, S, S, C, U, S }, // S
            { C, C, C, C, C, C, C }, // C
            { U, U, U, U, C, U, U }, // U
            { D, WD, WS, S, C, U, N }, // N
    };

    int[] contribMap = { IGRLStrategyAlgorithm.DENIED, IGRLStrategyAlgorithm.WDENIED, IGRLStrategyAlgorithm.WSATISFICED, IGRLStrategyAlgorithm.SATISFICED,
            IGRLStrategyAlgorithm.CONFLICT, IGRLStrategyAlgorithm.UNDECIDED, IGRLStrategyAlgorithm.NONE, };

    int[][] importanceMap = { { D, WD, WS, S, C, U, N }, // high
            { WD, WD, WS, WS, C, U, N }, // med
            { WD, N, N, WS, C, U, N }, // low
            { N, N, N, N, N, N, N }, // none
    };

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.extensionpoints.IGRLStrategiesAlgorithm#getEvaluationType()
     */
    public int getEvaluationType() {
        return IGRLStrategyAlgorithm.EVAL_QUALITATIVE;
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.extensionpoints.IGRLStrategiesAlgorithm#getEvaluation(grl.IntentionalElement)
     */
    public int getEvaluation(IntentionalElement element) {
        Evaluation eval = (Evaluation) evaluations.get(element);
        if ((element.getLinksDest().size() == 0) || (eval.getIntElement() != null)) {
            return eval.getEvaluation();
        }

        int result = -1;
        int tempResult = 0;

        boolean hasDecomposition = false;
        int decomSums[] = new int[7];
        for (int i = 0; i < 7; i++) {
            decomSums[i] = 0;
        }

        QualitativeLabel depMinLabel = null;
        boolean foundSmallerDependency = false;
        DependencyQualitativeLabelComparitor labelComp = new DependencyQualitativeLabelComparitor();
        int numContributions = 0;
        int sums[] = new int[7];
        for (int i = 0; i < 7; i++) {
            sums[i] = 0;
        }

        Iterator it = element.getLinksDest().iterator(); // Return the list of elementlink
        while (it.hasNext()) {
            ElementLink link = (ElementLink) it.next();

            // handle decomposition
            if (link instanceof Decomposition) {
                if (!hasDecomposition)
                    hasDecomposition = true;
                QualitativeLabel decompositionValue = ((Evaluation) evaluations.get(link.getSrc())).getQualitativeEvaluation();
                int qval = decompositionValue.getValue();
                String value = MetadataHelper.getMetaData(link.getSrc(), "ST_Legal"); //$NON-NLS-1$
                if (element.getDecompositionType().getValue() == DecompositionType.AND && "No".equals(value)) //$NON-NLS-1$
                    continue;
                decomSums[qval]++;

            } else if (link instanceof Dependency) {
                if (depMinLabel == null)
                    depMinLabel = ((Evaluation) evaluations.get(element)).getQualitativeEvaluation();
                QualitativeLabel depValue = ((Evaluation) evaluations.get(link.getSrc())).getQualitativeEvaluation();

                if (labelComp.compare(depValue, depMinLabel) > 0) {
                    depMinLabel = depValue;
                    foundSmallerDependency = true;
                }
            } else if (link instanceof Contribution) {
                Contribution contrib = (Contribution) link;
                // int contValue = contrib.getContribution().getValue();
                int contValue = EvaluationStrategyManager.getInstance().getActiveContribution(contrib).getValue();
                QualitativeLabel srcNode = ((Evaluation) evaluations.get(link.getSrc())).getQualitativeEvaluation();
                int qualValue = srcNode.getValue();

                int ci = contribTable1[qualValue][contValue];
                sums[ci]++;
                numContributions++;
            }
        }

        if (hasDecomposition) {
            int dns = decomSums[S];
            int dnws = decomSums[WS];
            int dnn = decomSums[N];
            int dnwd = decomSums[WD];
            int dnd = decomSums[D];
            int dnc = decomSums[C];
            int dnu = decomSums[U];

            if (element.getDecompositionType().getValue() == DecompositionType.AND) {
                if (dnd > 0) {
                    result = D;
                } else if ((dnc > 0) || (dnu > 0)) {
                    result = U;
                } else if (dnwd > 0) {
                    result = WD;
                } else if (dnn > 0) {
                    result = N;
                } else if (dnws > 0) {
                    result = WS;
                } else if (dns > 0) {
                    result = S;
                } else {
                    result = N;
                }
            } else if (element.getDecompositionType().getValue() == DecompositionType.OR || element.getDecompositionType().getValue() == DecompositionType.XOR) {
                if (dns > 0) {
                    result = S;
                } else if ((dnc > 0) || (dnu > 0)) {
                    result = U;
                } else if (dnws > 0) {
                    result = WS;
                } else if (dnn > 0) {
                    result = N;
                } else if (dnwd > 0) {
                    result = WD;
                } else if (dnd > 0) {
                    result = D;
                }
            }
        }

        if (numContributions > 0 && result == -1) {
            result = getQualitativeContribution(sums, numContributions);
        } else if (numContributions > 0 && result != -1) {
            tempResult = getQualitativeContribution(sums, numContributions);
            result = contribTable2[result][tempResult];
        }

        if (depMinLabel != null) {
            QualitativeLabel currLabel = null;

            if (result != -1)
                currLabel = QualitativeLabel.get(result);
            else
                currLabel = QualitativeLabel.NONE_LITERAL;

            if (labelComp.compare(depMinLabel, currLabel) > 0)
                result = depMinLabel.getValue();
        }

        return (result != -1 ? contribMap[result] : 0);
    }

    private int getQualitativeContribution(int[] sums, int numRead) {
        int toRet = -1;
        if (numRead > 1) {
            int ns = sums[S];
            int nws = sums[WS];
            int nn = sums[N];
            int nwd = sums[WD];
            int nd = sums[D];
            int nc = sums[C];
            int nu = sums[U];

            int w1 = getW1(nws, nwd);
            int w2 = getW2(ns, nd);
            int w3 = getW3(nc, nu);

            int ei = (w3 == -1 ? contribTable2[w1][w2] : w3);
            toRet = ei;
        } else {
            for (int i = 0; i < sums.length; i++) {
                if (sums[i] > 0)
                    toRet = i;
            }
        }
        return toRet;
    }

    /**
     * Gets the evaluation value of the first sets of evaluations.
     * 
     */
    private int getW1(int nws, int nwd) {
        /**
         * w1 = ws, if nws > nwd = wd, if nwd > nws = n, otherwise
         */
        if (nws > nwd)
            return WS;
        if (nwd > nws)
            return WD;
        return N;
    }

    /**
     * Get the evaluation value of the second sets of evaluations.
     * 
     */
    private int getW2(int ns, int nd) {
        /**
         * w2 = c, if ns >0 && nd >0 = s, if ns >0 && nd=0 = d, if nd >0 && ns=0 = n, if ns =0 && nd=0
         */
        if (ns > 0 && nd > 0)
            return C;
        if (ns > 0 && nd == 0)
            return S;
        if (nd > 0 && ns == 0)
            return D;
        return N;
    }

    /**
     * Get the evaluation value of W3.
     * 
     */
    private int getW3(int nc, int nu) {
        /**
         * w3 = u, if nc > 0
         */
        if (nc > 0 || nu > 0)
            return U;
        return -1; // never considered
    }

    /*
     * (non-Javadoc)
     * 
     * @see seg.jUCMNav.extensionpoints.IGRLStrategyAlgorithm#getActorEvaluation(grl.Actor)
     */
    public int getActorEvaluation(Actor actor) {
        double satisficed = 0;
        double denied = 0;

        int total = 0;

        int sums[] = new int[7];
        for (int i = 0; i < 7; i++) {
            sums[i] = 0;
        }

        Iterator iter = actor.getContRefs().iterator();
        while (iter.hasNext()) {
            // Parse through the node bind to this actor
            ActorRef ref = (ActorRef) iter.next();
            Iterator iterNode = ref.getNodes().iterator();
            while (iterNode.hasNext()) {
                IURNNode node = (IURNNode) iterNode.next();
                if (node instanceof IntentionalElementRef) {
                    IntentionalElementRef elementRef = (IntentionalElementRef) node;
                    IntentionalElement element = elementRef.getDef();
                    String value = MetadataHelper.getMetaData(element, "ST_Legal"); //$NON-NLS-1$
                    if ("No".equals(value)) { //$NON-NLS-1$
                        continue;
                    }
                    int evaluation = EvaluationStrategyManager.getInstance().getEvaluation(element);

                    ImportanceType importance = element.getImportance();

                    QualitativeLabel ql = EvaluationStrategyManager.getQualitativeEvaluationForQuantitativeValue(element.getGrlspec().getUrnspec(), evaluation);
                    int ci = importanceMap[importance.getValue()][ql.getValue()];
                    sums[ci]++;
                    total++;
                }
            }
        }

        int result = getQualitativeContribution(sums, total);
        return (result != -1 ? contribMap[result] : 0);

    }

    @Override
    public boolean isConstraintSolverAlgorithm() {
        // TODO Auto-generated method stub
        return false;
    }

}
