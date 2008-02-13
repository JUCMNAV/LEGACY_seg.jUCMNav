package seg.jUCMNav.staticSemantic;

import java.util.ArrayList;
import java.util.List;

public class RuleGroup {
    private String name;
    private List members;

    RuleGroup(String name)
    {
        this.name = name;
        members = new ArrayList();
    }
    
    public void addRule(Rule rule)
    {
        if(members.indexOf(rule)==-1)
            members.add(rule);       
    }
    
    public void removeRule(Rule rule)
    {
        members.remove(rule);
    }
    
    public void addRule(List rules)
    {
        for(int i=0;i<rules.size();++i)
        {
            addRule((Rule) rules.get(i));
        }
    }
    public List getRules()
    {
        return members;
    }
    public void Enable(boolean bEnable)
    {
        for(int i=0;i<members.size();++i)
        {
            Rule r = (Rule) members.get(i);
            r.setEnabled(bEnable);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public boolean contain(Rule r)
    {
        return members.indexOf(r)!=-1;
    }

    public void removeAll() {
        members.clear();
        
    }
}
