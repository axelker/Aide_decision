package planning;
import representation.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.*;
public class DFSPlanner implements Planner {

    private Map<Variable, Object>etatInitial;
    private Set<Action>action;
    private Goal but;

    public DFSPlanner(Map<Variable, Object>etatInitial,Set<Action>action,Goal but)
    {
        this.etatInitial=etatInitial;
        this.action=action;
        this.but=but;
    }

    @Override
    public  Map<Variable, Object> getInitialState()
    {
        return this.etatInitial;
    }

    @Override
    public Set<Action> getActions(){
        return this.action;
    }

    @Override
    public Goal getGoal(){
        return this.but;
    }

    public List<Action> DFS(Map<Variable, Object> initialeState,List<Action> listeaction,Set<Map<Variable, Object>> closedliste)
    {
        if(this.but.isSatisfiedBy(initialeState))
        {
            return listeaction;
        }
        else {
            for(Action a : getActions())
            {
                Map<Variable, Object>next=a.successor(initialeState);
                if(!closedliste.contains(next))
                {
                    listeaction.add(a);
                    closedliste.add(next);
                    List<Action> subplan =DFS(next,listeaction,closedliste);
                    if(subplan!=null)
                    {
                        return subplan;
                    }
                    else {
                        listeaction.remove(a);
                    }
                }
            }
            return null;
        }
    }
    @Override
    public List<Action> plan()
    {
        
        List<Action> listeaction=new ArrayList<Action>();
        Set<Map<Variable, Object>> closedliste=new HashSet<>();
        closedliste.add(this.etatInitial);
        return DFS(this.etatInitial,listeaction,closedliste);
       

    }

}