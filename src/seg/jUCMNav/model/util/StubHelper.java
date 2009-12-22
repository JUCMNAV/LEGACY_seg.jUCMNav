package seg.jUCMNav.model.util;

import ucm.map.AspectKind;
import ucm.map.PointcutKind;
import ucm.map.Stub;

public class StubHelper {
    public static int getStubKind(Stub s) {
        int aspectKind = getAspectKind(s);
        
        if(s.isDynamic()) {
            if(s.isSynchronization() && s.isBlocking())
                return StubKind.SYNCHRONIZING_BLOCKING; // Blocking
            else if(s.isSynchronization())
                return StubKind.SYNCHRONIZING; // Synchronized
            else if(aspectKind != -1)
                return aspectKind;
            else if(s.getAopointcut() == PointcutKind.NONE_LITERAL)
                return StubKind.DYNAMIC; // Dynamic
            else if(s.getAopointcut() == PointcutKind.REGULAR_LITERAL)
                return StubKind.POINTCUT; // Pointcut
            else if(s.getAopointcut() == PointcutKind.REPLACEMENT_LITERAL)
                return StubKind.POINTCUT_REPLACEMENT; // Pointcut Replacement
        }
        
        if(aspectKind != -1)
            return aspectKind;
        
        return StubKind.STATIC;
    }

    private static int getAspectKind(Stub s) {
        if(s.getAspect() == AspectKind.NONE_LITERAL)
            return -1;
        else if(s.getAspect() == AspectKind.REGULAR_LITERAL)
            return StubKind.ASPECT_MARKER; // Aspect Marker
        else if(s.getAspect() == AspectKind.ENTRANCE_LITERAL)
            return StubKind.ASPECT_ENTRANCE; // Entrance Aspect Marker
        else if(s.getAspect() == AspectKind.EXIT_LITERAL)
            return StubKind.ASPECT_EXIT; // Exit Aspect Marker
        else if(s.getAspect() == AspectKind.CONDITIONAL_LITERAL)
            return StubKind.ASPECT_CONDITIONNAL; // Conditionnal Aspect Marker
        
        return -1;
    }
    
    public static boolean isDynamic(int stubKind) {
        return stubKind >= 1 && stubKind <= 5;
    }
}
