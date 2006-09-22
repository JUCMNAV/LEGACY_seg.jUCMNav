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

public class UcmEnvironment implements Adapter {

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
        Object type = enumerations.get(var);
        if (type != null)
            throw new IllegalArgumentException(Messages.getString("UcmEnvironment.EnumerationSpace") + var + Messages.getString("UcmEnvironment.IsAlreadyDefined")); //$NON-NLS-1$ //$NON-NLS-2$
    }

    public String[] checkEnumerationExists(String var) {
        Object type = enumerations.get(var);
        if (type == null)
            throw new IllegalArgumentException(Messages.getString("UcmEnvironment.EnumerationSpace") + var + Messages.getString("UcmEnvironment.IsNotDefined")); //$NON-NLS-1$ //$NON-NLS-2$
        return (String[]) type;
    }

    public void checkVariableDoesNotExist(SimpleNode root) {
        checkVariableDoesNotExist(root.getText());
    }

    public void checkVariableDoesNotExist(String var) {
        Object type = declarations.get(var);
        if (type != null)
            throw new IllegalArgumentException(Messages.getString("UcmEnvironment.VariableSpace") + var + Messages.getString("UcmEnvironment.IsAlreadyDefined")); //$NON-NLS-1$ //$NON-NLS-2$
    }

    public jUCMNavType checkVariableExists(SimpleNode root) {
        return checkVariableExists(root.getText());
    }

    public jUCMNavType checkVariableExists(String var) {
        Object type = declarations.get(var);
        if (type == null || !(type instanceof jUCMNavType))
            throw new IllegalArgumentException(Messages.getString("UcmEnvironment.VariableSpace") + var + Messages.getString("UcmEnvironment.IsNotDefined")); //$NON-NLS-1$ //$NON-NLS-2$
        return (jUCMNavType) type;
    }

    public void registerBoolean(String var) {
        registerBoolean(var, false);
    }

    public void registerBoolean(String var, boolean b) {
        checkVariableDoesNotExist(var);
        declarations.put(var, jUCMNavType.BOOLEAN);
        valuations.put(var, Boolean.valueOf(b));
    }

    public void registerEnumeration(String enumName, String[] values) {
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
        checkEnumerationExists(enumName);
        String[] values = (String[]) enumerations.get(enumName);
        return values[index];
    }

    public void registerEnumerationInstance(String enumName, String var) {
        registerEnumerationInstance(enumName, var, getEnumerationValue(enumName, 0));
    }

    public void registerEnumerationInstance(String enumName, String var, String value) {
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
        checkVariableDoesNotExist(var);
        declarations.put(var, jUCMNavType.INTEGER);
        valuations.put(var, new Integer(i));
    }

    public Object getValue(String var) {
        Object result = valuations.get(var);
        if (result == null) {
            result = declarations.get(var);
            
            if (result==null) {// || result.toString().indexOf(jUCMNavType.ENUMERATION)>=0) {
                throw new IllegalArgumentException(Messages.getString("UcmEnvironment.VariableSpace") + var +  Messages.getString("UcmEnvironment.HasNoValuation")); //$NON-NLS-1$ //$NON-NLS-2$
            } else
                result = var;
        }
        return result;
    }
    
    public void setValue(String var, Object o) {
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
				if (declarations.containsKey(notification.getOldValue())) {
					Object o = declarations.get(notification.getOldValue());
					declarations.remove(notification.getOldValue());
					declarations.put(notification.getNewValue(), o);
				}
				if (valuations.containsKey(notification.getOldValue())) {
					Object o = valuations.get(notification.getOldValue());
					valuations.remove(notification.getOldValue());
					valuations.put(notification.getNewValue(), o);
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
				if (ScenarioUtils.sTypeBoolean.equals(var.getType())) {
					if (oldValuations.containsKey(var.getName()))
						this.registerBoolean(var.getName(), ((Boolean)oldValuations.get(var.getName())).booleanValue());
					else
						this.registerBoolean(var.getName());
				} else if (ScenarioUtils.sTypeInteger.equals(var.getType())) {
					if (oldValuations.containsKey(var.getName()))
						this.registerInteger(var.getName(), ((Integer)oldValuations.get(var.getName())).intValue());
					else
						this.registerInteger(var.getName());
				} else {
					//	TODO: implement enumerations
					System.out.println("TODO: implement enumerations");
				}
				
			}

		}
		
		
	}
    

}
