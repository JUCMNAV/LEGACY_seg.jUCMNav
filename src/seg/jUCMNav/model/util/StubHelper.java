package seg.jUCMNav.model.util;

import ucm.map.AspectKind;
import ucm.map.PointcutKind;
import ucm.map.Stub;

public class StubHelper {
    public static int getStubKind(Stub s) {
        int aspectKind = getAspectKind(s);
        
        if(s.isDynamic()) {
            if(s.isSynchronization() && s.isBlocking())
                return 5; // Blocking
            else if(s.isSynchronization())
                return 4; // Synchronized
            else if(aspectKind != -1)
                return aspectKind;
            else if(s.getAopointcut() == PointcutKind.NONE_LITERAL)
                return 1; // Dynamic
            else if(s.getAopointcut() == PointcutKind.REGULAR_LITERAL)
                return 2; // Pointcut
            else if(s.getAopointcut() == PointcutKind.REPLACEMENT_LITERAL)
                return 3; // Pointcut Replacement
        }
        
        if(aspectKind != -1)
            return aspectKind;
        
        return 0;
    }

    private static int getAspectKind(Stub s) {
        if(s.getAspect() == AspectKind.NONE_LITERAL)
            return -1;
        else if(s.getAspect() == AspectKind.REGULAR_LITERAL)
            return 6; // Aspect Marker
        else if(s.getAspect() == AspectKind.ENTRANCE_LITERAL)
            return 7; // Entrance Aspect Marker
        else if(s.getAspect() == AspectKind.EXIT_LITERAL)
            return 8; // Exit Aspect Marker
        else if(s.getAspect() == AspectKind.CONDITIONAL_LITERAL)
            return 9; // Conditionnal Aspect Marker
        
        return -1;
    }
    
    public static boolean isDynamic(int stubKind) {
        return stubKind >= 1 && stubKind <= 5;
    }
}
