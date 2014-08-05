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
		parameters.put("DistributeCentersVertically", "seg.jUCMNav.DistributeCentersVertically");
		parameters.put("DistributeCentersHorizontally", "seg.jUCMNav.DistributeCentersHorizontally");
		parameters.put("DistributeCustom","seg.jUCMNav.DistributeCustom" );
		
		return parameters;
	}

}
