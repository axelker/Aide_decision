package planning;
import representation.*;
import java.lang.*;
import java.util.*;
import java.awt.im.spi.*;

public class BasicAction implements Action {

    private Map<Variable,Object> precondition;
    private Map<Variable,Object> effet;
    private int cout;

    public BasicAction(Map<Variable,Object> p,Map<Variable,Object>e,int c)
    {
        this.precondition=p;
        this.effet=e;
        this.cout=c;
    }

@Override 
public boolean isApplicable(Map<Variable, Object>m)
{
    
    for(Variable x: this.precondition.keySet())
    {
        if(!m.containsKey(x))
        {
            return false;
        }
        
        if(!m.get(x).equals(this.precondition.get(x))){
            return false;
        }

    }
    return true;
    
    
}

@Override 
public Map<Variable, Object>successor(Map<Variable, Object>m)
{
    Map<Variable, Object> RetourMap = new HashMap<>();
    /*if(isApplicable(m){
        for(Variable x : m.keySet())
        {

        }
    }*/
    return RetourMap;
}
@Override
public int getCost()
{
    return 1;
}



}