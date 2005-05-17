package seg.jUCMNav.views.wizards;

/*
 * Taken from: http://www.faqs.org/docs/javap/c5/ex-5-2-answer.html
 * 
 * Introduction to Programming Using Java
 * 
 * Author:  David J. Eck  (eck@hws.edu)
 * 
 * An object of class StatCalc can be used to compute several simple statistics for a set of numbers. Numbers are entered into the dataset using the
 * enter(double) method. Methods are provided to return the following statistics for the set of numbers that have been entered: The number of items, the sum of
 * the items, the average, and the standard deviation.
 */

public class StatCalc {

    private int count; // Number of numbers that have been entered.
    private double sum; // The sum of all the items that have been entered.
    private double squareSum; // The sum of the squares of all the items.

    public void enter(double num) {
        // Add the number to the dataset.
        count++;
        sum += num;
        squareSum += num * num;
    }

    public int getCount() {
        // Return number of items that have been entered.
        return count;
    }

    public double getSum() {
        // Return the sum of all the items that have been entered.
        return sum;
    }

    public double getMean() {
        // Return average of all the items that have been entered.
        // Value is Double.NaN if count == 0.
        return sum / count;
    }

    public double getStandardDeviation() {
        // Return standard deviation of all the items that have been entered.
        // Value will be Double.NaN if count == 0.
        double mean = getMean();
        return Math.sqrt(squareSum / count - mean * mean);
    }

} // end of class StatCalc
