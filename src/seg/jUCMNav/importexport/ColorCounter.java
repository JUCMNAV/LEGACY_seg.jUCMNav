package seg.jUCMNav.importexport;

import org.eclipse.swt.graphics.RGB;

/**
 * Comparator for RGB frequencies in a Image.
 * 
 * Code used in GIF image downsampling. Found https://bugs.eclipse.org/bugs/show_bug.cgi?id=70949
 * 
 */
public class ColorCounter implements Comparable {
    RGB rgb;
    int count;

    public int compareTo(Object o) {
        return ((ColorCounter) o).count - count;
    }

    public boolean equals(Object obj) {
        if (obj instanceof ColorCounter)
            return ((ColorCounter) obj).count == this.count;
        else
            return super.equals(obj);
    }

    public int hashCode() {
        return count;
    }
}