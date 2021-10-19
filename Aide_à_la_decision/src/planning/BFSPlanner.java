package planning;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


import java.util.*;
import representation.*;


public class BFSPlanner implements Planner {

    private Map<Variable, Object>etatInitial;
    private Set<Action>action;
    private Goal but;

    public BFSPlanner(Map<Variable, Object>etatInitial,Set<Action>action,Goal but)
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

    @Override
    public List<Action>plan()
    {
        Map<Map<Variable,Object>,Map<Variable,Object>>father=new HashMap<>();
        father.put(this.etatInitial,null);
        Map<Map<Variable,Object>,Action>plan=new HashMap<>();
        return BFS(father,plan);
    }

    public List<Action>BFS(Map<Map<Variable,Object>,Map<Variable,Object>>father,Map<Map<Variable,Object>,Action>plan)
    {
        List<Map<Variable, Object>> closedliste=new ArrayList<Map<Variable, Object>>();
        closedliste.add(this.etatInitial);
        LinkedList<Map<Variable, Object>> openListe=new LinkedList<Map<Variable,Object>>();
        openListe.add(this.etatInitial);

        if(this.but.isSatisfiedBy(this.etatInitial))
        {
            return new ArrayList<>();
        }

        while(!openListe.isEmpty())
        {
            Map<Variable, Object>instaciation = new HashMap<>();
            instaciation=openListe.poll();
            closedliste.add(instaciation);
            for(Action a : getActions())
            {
                Map<Variable, Object>next=a.successor(instaciation);
                if(!closedliste.contains(next) && !openListe.contains(next))
                {
                    father.put(next,instaciation);
                    plan.put(next,a);
                    if(this.but.isSatisfiedBy(next))
                    {
                        return getbfsplan(father, plan, next);
                    }
                    else {
                        openListe.add(next);
                    }
                }
            }
        }
        return null;



    }

    public List<Action> getbfsplan(Map<Map<Variable,Object>,Map<Variable,Object>>father,Map<Map<Variable,Object>,Action>plan,Map<Variable,Object>goal)
    {
        List<Action>trieListe=new ArrayList<Action>();
        while(goal!=null)
        {
            //Action non null
            if(plan.get(goal)!=null){
                 //AJouter l'action qui nous a mener à goal
            trieListe.add(plan.get(goal));
            }
           
            goal=father.get(goal);  // Sortir le père du goal
        }
        Collections.reverse(trieListe); // Inverser le plan grace à la methode reverse
        return  trieListe;
    }

   
}