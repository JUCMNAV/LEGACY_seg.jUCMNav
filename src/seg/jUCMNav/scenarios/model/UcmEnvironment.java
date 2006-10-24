package seg.jUCMNav.scenarios.model;

import java.util.HashMap;
import java.util.Iterator;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;

import seg.jUCMNav.Messages;
import seg.jUCMNav.scenarios.ScenarioUtils;
import seg.jUCMNav.scenarios.parser.SimpleNode;
import ucm.UCMspec;
import ucm.UcmPackage;
import ucm.scenario.ScenarioPackage;
import ucm.scenario.Variable;
import urn.URNspec;

public class UcmEnvironment implements Adapter, Cloneable{

    private HashMap declarations;
    private HashMap enumerations;
    private HashMap valuations;
    private URNspec urn;
    private Notifier target;

    public UcmEnvironment() {
        declarations = new HashMap();
        valuations = new HashMap();
        enumerations = new HashMap();
    }
    
    public UcmEnvironment(URNspec urn) {
        declarations = new HashMap();
        valuations = new HashMap();
        enumerations = new HashMap();
        
        registerUCMspec(urn.getUcmspec());
    	
    }

    public void checkEnumerationDoesNotExists(String var) {
    	var = var.toLowerCase();
        Object type = enumerations.get(var);
        if (type != null)
            throw new IllegalArgumentException(Messages.getString("UcmEnvironment.EnumerationSpace") + var + Messages.getString("UcmEnvironment.IsAlreadyDefined")); //$NON-NLS-1$ //$NON-NLS-2$
    }

    public String[] checkEnumerationExists(String var) {
    	var = var.toLowerCase();
        Object type = enumerations.get(var);
        if (type == null)
            throw new IllegalArgumentException(Messages.getString("UcmEnvironment.EnumerationSpace") + var + Messages.getString("UcmEnvironment.IsNotDefined")); //$NON-NLS-1$ //$NON-NLS-2$
        return (String[]) type;
    }

    public void checkVariableDoesNotExist(SimpleNode root) {
        checkVariableDoesNotExist(root.getText());
    }

    public void checkVariableDoesNotExist(String var) {
    	var = var.toLowerCase();
        Object type = declarations.get(var);
        if (type != null)
            throw new IllegalArgumentException(Messages.getString("UcmEnvironment.VariableSpace") + var + Messages.getString("UcmEnvironment.IsAlreadyDefined")); //$NON-NLS-1$ //$NON-NLS-2$
    }

    public jUCMNavType checkVariableExists(SimpleNode root) {
        return checkVariableExists(root.getText());
    }

    public jUCMNavType checkVariableExists(String var) {
    	var = var.toLowerCase();
        Object type = declarations.get(var);
        if (type == null || !(type instanceof jUCMNavType))
            throw new IllegalArgumentException(Messages.getString("UcmEnvironment.VariableSpace") + var + Messages.getString("UcmEnvironment.IsNotDefined")); //$NON-NLS-1$ //$NON-NLS-2$
        return (jUCMNavType) type;
    }

    public void clearValuations() {
    	valuations.clear();
    }
    public Object clone() throws CloneNotSupportedException {
    	return super.clone();
    }
    public void registerBoolean(String var) {
        registerBoolean(var, false);
    }

    public void registerBoolean(String var, boolean b) {
    	var = var.toLowerCase();
        checkVariableDoesNotExist(var);
        declarations.put(var, jUCMNavType.BOOLEAN);
        valuations.put(var, Boolean.valueOf(b));
    }

    public void registerEnumeration(String enumName, String[] values) {
    	enumName = enumName.toLowerCase();
        checkEnumerationDoesNotExists(enumName);
        if (values.length == 0)
            throw new IllegalArgumentException(Messages.getString("UcmEnvironment.EnumerationMustHaveValues")); //$NON-NLS-1$

        enumerations.put(enumName, values);

        for (int i = 0; i < values.length; i++) {
            String value = values[i];
            jUCMNavType type = (jUCMNavType) declarations.get(value);
            if (type == null) {
                type = new jUCMNavType(jUCMNavType.ENUMERATION + enumName);
                declarations.put(value, type);
            } else {
                type.addEnumerationType(jUCMNavType.ENUMERATION + enumName);

            }
        }
    }

    public String getEnumerationValue(String enumName, int index) {
    	enumName = enumName.toLowerCase();
        checkEnumerationExists(enumName);
        String[] values = (String[]) enumerations.get(enumName);
        return values[index];
    }

    public void registerEnumerationInstance(String enumName, String var) {
    	enumName = enumName.toLowerCase();
    	var = var.toLowerCase();
        registerEnumerationInstance(enumName, var, getEnumerationValue(enumName, 0));
    }

    public void registerEnumerationInstance(String enumName, String var, String value) {
    	enumName = enumName.toLowerCase();
    	var = var.toLowerCase();
    	value = value.toLowerCase();
        checkEnumerationExists(enumName);
        checkVariableDoesNotExist(var);
        checkVariableExists(value);

        declarations.put(var, new jUCMNavType(jUCMNavType.ENUMERATION + enumName));
        valuations.put(var, value);
    }

    public void registerInteger(String var) {
        registerInteger(var, 0);
    }

    public void registerInteger(String var, int i) {
    	var = var.toLowerCase();
        checkVariableDoesNotExist(var);
        declarations.put(var, jUCMNavType.INTEGER);
        valuations.put(var, new Integer(i));
    }

    public Object getValue(String var) {
    	String lower = var.toLowerCase();
   	
        Object result = valuations.get(lower);
        if (result == null) {
            result = declarations.get(lower);
            
            if (result!=null) {// || result.toString().indexOf(jUCMNavType.ENUMERATION)>=0) {
                throw new IllegalArgumentException(Messages.getString("UcmEnvironment.VariableSpace") + var +  Messages.getString("UcmEnvironment.HasNoValuation")); //$NON-NLS-1$ //$NON-NLS-2$
            } else
                result = lower;
        }
        return result;
    }
    
    public void setValue(String var, Object o) {
    	var = var.toLowerCase();
        valuations.put(var, o);
    }

	public Notifier getTarget() {
		return urn;
	}

	public boolean isAdapterForType(Object arg0) {
		return arg0 instanceof URNspec;
	}

	public void notifyChanged(Notification notification) {
		
//        int type = notification.getEventType();
//        switch (type) {
//        case Notification.SET:
//        }

        
        if (notification.getNotifier() instanceof UCMspec) {
        	int featureId = notification.getFeatureID(UCMspec.class);

			if (featureId == UcmPackage.UC_MSPEC__VARIABLES) {
				registerUCMspec((UCMspec)notification.getNotifier());
			}
        } else if (notification.getNotifier() instanceof Variable) {
			int featureId = notification.getFeatureID(Variable.class);

			if (featureId == ScenarioPackage.VARIABLE__NAME) {
				if (declarations.containsKey(notification.getOldValue().toString().toLowerCase())) {
					Object o = declarations.get(notification.getOldValue().toString().toLowerCase());
					declarations.remove(notification.getOldValue().toString().toLowerCase());
					declarations.put(notification.getNewValue().toString().toLowerCase(), o);
				}
				if (valuations.containsKey(notification.getOldValue().toString().toLowerCase())) {
					Object o = valuations.get(notification.getOldValue().toString().toLowerCase());
					valuations.remove(notification.getOldValue().toString().toLowerCase());
					valuations.put(notification.getNewValue().toString().toLowerCase(), o);
				}				
					
			} else if (featureId == ScenarioPackage.VARIABLE__TYPE) {
				// assuming we can't change type. 
			}
//			refresh();
		}
        
	}

	public void setTarget(Notifier arg0) {
		target = arg0;
		refresh();
	}

	private void registerUCMspec(UCMspec ucmspec) {
		if (this.urn!=null && ucmspec.getUrnspec()!=this.urn) {
			this.urn.getUcmspec().eAdapters().remove(this);
			
			for (Iterator iter = urn.getUcmspec().getVariables().iterator(); iter.hasNext();) {
				Variable var = (Variable) iter.next();
				var.eAdapters().remove(this);
			}

		}
	
		this.urn = ucmspec.getUrnspec();
		target = ucmspec;
		refresh();

		if (this.urn!=null && !urn.getUcmspec().eAdapters().contains(this)) {
			urn.getUcmspec().eAdapters().add(this);
		}
		
		for (Iterator iter = urn.getUcmspec().getVariables().iterator(); iter.hasNext();) {
			Variable var = (Variable) iter.next();
			if (!var.eAdapters().contains(this))
				var.eAdapters().add(this);
		}	
		
		// TODO: remove unused ones. 
	}

	private void refresh() {
		if (this.urn == null) {
			declarations.clear();
			enumerations.clear();
			valuations.clear();
		}
		else
		{
			declarations.clear();
			enumerations.clear();
			HashMap oldValuations = (HashMap) valuations.clone();
			valuations.clear();
			
			for (Iterator iter = this.urn.getUcmspec().getVariables().iterator(); iter.hasNext();) {
				Variable var = (Variable) iter.next();
				String name = var.getName().toLowerCase();
				if (ScenarioUtils.sTypeBoolean.equals(var.getType())) {
					if (oldValuations.containsKey(name))
						this.registerBoolean(name, ((Boolean)oldValuations.get(name)).booleanValue());
					else
						this.registerBoolean(name);
				} else if (ScenarioUtils.sTypeInteger.equals(var.getType())) {
					if (oldValuations.containsKey(name))
						this.registerInteger(name, ((Integer)oldValuations.get(name)).intValue());
					else
						this.registerInteger(name);
				} else {
					//	TODO: implement enumerations
					System.out.println("TODO: implement enumerations"); //$NON-NLS-1$
				}
				
			}

		}
		
		
	}
	
  

}
