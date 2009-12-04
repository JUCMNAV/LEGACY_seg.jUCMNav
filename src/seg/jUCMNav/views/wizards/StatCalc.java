package seg.jUCMNav.views.wizards;

/**
 * This class act as a simplistic statistic calculator.
 * 
 * You enter numbers and you can get the sum, mean and standard deviation.
 * 
 * @author oclift
 * 
 */
public class StatCalc {
    private int n = 0;
    private double total = 0.0;
    private double sqrTotal = 0.0;

    public void enter(double num) {
        n++;
        total += num;
        sqrTotal += num * num;
    }

    public int getCount() {
        return n;
    }

    public double getSum() {
        return total;
    }

    public double getMean() {
        return total / n;
    }

    public double getStandardDeviation() {
        double variance = (sqrTotal - ((total * total) / n)) / n;
        return Math.sqrt(variance);
    }
} // end of class StatCalc
