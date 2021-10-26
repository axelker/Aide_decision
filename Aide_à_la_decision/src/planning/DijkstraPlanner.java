package planning;

import java.util.*;
import representation.*;


public class DijkstraPlanner implements Planner{

    private Map<Variable, Object>etatInitial;
    private Set<Action>action;
    private Goal but;

    public DijkstraPlanner(Map<Variable, Object>etatInitial,Set<Action>action,Goal but)
    {
        this.etatInitial=etatInitial;
        this.action=action;
        this.but=but;
    }

    @Override
    public Map<Variable, Object> getInitialState()
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
        //Initialisation 
        Map<Map<Variable,Object>,Map<Variable,Object>>father=new HashMap<>();
        father.put(this.etatInitial,null); // pere de l'état initial est null car le départ n'a aucun père 
        Map<Map<Variable,Object>,Action>plan=new HashMap<>();
        List<Map<Variable,Object>> goals=new ArrayList<>();
        Map<Map<Variable, Object>, Double> distance = new HashMap<>();
        //Retour solution
        return dijkstra(plan,distance,father,goals);
    }

    //implemter
    public List<Action> dijkstra(Map<Map<Variable,Object>Action>plan,Map<Map<Variable,Object>Double>distance,Map<Map<Variable,Object>,Map<Variable,Object>>father)
    {
        //Créer openlist

        /*while(!openlist.isEmpty())
        {
            

        }

    }


    //Retourn le plan d'action trié
    public List<Action> getdijkstraplan(Map<Map<Variable,Object>,Map<Variable,Object>>father,Map<Map<Variable,Object>,Action>plan,List<Map<Variable,Object>>goals,
    Map<Map<Variable,Object>,Double>distance)
    {
        List<Action>trieListe=new ArrayList<Action>();
        //Sortir but avec min distance
        Map<Variable,Object>goal = argmin(distance,goals);
        //Construction chemin
        while(goal!=null)
        {
            //Action non null
            if(plan.get(goal)!=null){
            //AJouter l'action qui nous a mener à goal
            trieListe.add(plan.get(goal));
            }
           
            goal=father.get(goal);  // Recuperer le père du goal
        }
        Collections.reverse(trieListe); // Inverser le plan grace à la methode reverse
        return  trieListe;
    }
    
}