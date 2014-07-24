package seg.jUCMNav.model.commands.parameters;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.commands.IParameterValues;

public class AlignTypeCommandParameter implements IParameterValues {

	@Override
	public Map getParameterValues() {
		
		Map<String, String> parameters = new HashMap<String, String>();
		
		parameters.put("AlignTop", "seg.jUCMNav.AlignTop");
		parameters.put("AlignMiddle", "seg.jUCMNav.AlignMiddle");
		parameters.put("AlignBottom", "seg.jUCMNav.AlignBottom");
		parameters.put("AlignLeft", "seg.jUCMNav.AlignLeft");
		parameters.put("AlignCenter", "seg.jUCMNav.AlignCenter");
		parameters.put("AlignRight", "seg.jUCMNav.AlignRight");
		
		return parameters;
	}

}
