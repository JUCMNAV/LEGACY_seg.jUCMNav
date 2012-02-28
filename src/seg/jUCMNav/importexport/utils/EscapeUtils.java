/**
 * Utility methods to handle escape characters in various export/import/report formats.
 */
package seg.jUCMNav.importexport.utils;

/**
 * @author damyot
 *
 */
public class EscapeUtils {

    /**
     * Convert a String to a new String where the HTML special characters have been escaped
     * NOTE: HTML has many more escape characters (see http://tntluoma.com/sidebars/codes/)
     *       However the ones below are often causing compatibility issues whereas the
     *       remaining ones are often tolerated by browsers. Could be extended in the future.
     */
    public static final String escapeHTML(String s) {
        if (s == null)
            return ""; //$NON-NLS-1$
        else
        {
            StringBuffer sb = new StringBuffer();
            int n = s.length();
            for (int i = 0; i < n; i++) {
                char c = s.charAt(i);
                if (c<'?') { // might be a special character to escape, otherwise escapes quickly
                    switch (c) {
                    case '<':
                        sb.append("&lt;"); //$NON-NLS-1$
                        break;
                    case '>':
                        sb.append("&gt;"); //$NON-NLS-1$
                        break;
                    case '&':
                        sb.append("&amp;"); //$NON-NLS-1$
                        break;
                    case '"':
                        sb.append("&quot;"); //$NON-NLS-1$
                        break;
                    case '\'':
                        sb.append("&apos;"); //$NON-NLS-1$
                        break;
                    default:
                        sb.append(c);
                        break;
                    }
                }
                else
                    sb.append(c);
            }
            return sb.toString();
        }
    }

    /**
     * Convert a String to a new String where the XML special characters have been escaped
     */
    public static final String escapeXML(String s) {
        if (s == null)
            return ""; //$NON-NLS-1$
        else
        {
            StringBuffer sb = new StringBuffer();
            int n = s.length();
            for (int i = 0; i < n; i++) {
                char c = s.charAt(i);
                if (c<'?') { // might be a special character to escape, otherwise escapes quickly
                    switch (c) {
                    case '<':
                        sb.append("&lt;"); //$NON-NLS-1$
                        break;
                    case '>':
                        sb.append("&gt;"); //$NON-NLS-1$
                        break;
                    case '&':
                        sb.append("&amp;"); //$NON-NLS-1$
                        break;
                    case '"':
                        sb.append("&quot;"); //$NON-NLS-1$
                        break;
                    case '\'':
                        sb.append("&apos;"); //$NON-NLS-1$
                        break;
                    default:
                        sb.append(c);
                        break;
                    }
                }
                else
                    sb.append(c);
            }
            return sb.toString();
        }
    }
}
