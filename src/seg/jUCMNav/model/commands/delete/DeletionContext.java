package seg.jUCMNav.model.commands.delete;

public class DeletionContext {

    private static boolean performingCutAction = false;
    private static boolean performingPasteAction = false;

    public static boolean isPerformingCutAction() {
        return performingCutAction;
    }

    public static void setPerformingCutAction(boolean performingCutAction) {
        DeletionContext.performingCutAction = performingCutAction;
    }

    public static boolean isPerformingPasteAction() {
        return performingPasteAction;
    }

    public static void setPerformingPasteAction(boolean performingPasteAction) {
        DeletionContext.performingPasteAction = performingPasteAction;
    }
     
}
