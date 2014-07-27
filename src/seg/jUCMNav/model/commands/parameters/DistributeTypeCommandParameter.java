package seg.jUCMNav.model.commands.parameters;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.commands.IParameterValues;

public class DistributeTypeCommandParameter implements IParameterValues {

	@Override
	public Map getParameterValues() {
		
		Map<String, String> parameters = new HashMap<String, String>();
		
		parameters.put("DistributeVertically", "seg.jUCMNav.DistributeVertically");
		parameters.put("DistributeHorizontally", "seg.jUCMNav.DistributeHorizontally");
		
		return parameters;
	}

}
