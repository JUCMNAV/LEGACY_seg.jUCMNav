package seg.jUCMNav.importexport.z151.marshal;

import seg.jUCMNav.importexport.z151.generated.Dependency;

public class DependencyMHandler extends ElementLinkMHandler {
	public Object handle(Object obj, Object target, boolean isFullConstruction) {
		grl.Dependency elem = (grl.Dependency) obj;
		String objId = elem.getId();
		Dependency elemZ = (Dependency) getObject(objId, target, "createDependency"); //$NON-NLS-1$
		if (isFullConstruction) {
			super.handle(elem, elemZ, true);
		}
		return elemZ;
	}
}
