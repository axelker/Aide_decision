package solvers;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.*;

import representation.*;

public class NbConstraintsVariableHeuristic implements VariableHeuristic {

private Set<Constraint> contrainte;
private boolean prefere;

public NbConstraintsVariableHeuristic(Set<Constraint>contrainte,boolean prefere)
{
    this.contrainte=contrainte;
    this.prefere=prefere;
}

@Override
public Variable best(Set<Variable> variable,Map<Variable, Set<Object>>map){


    Set<Variable> listeVariable = new HashSet<>(); 
    Set<Variable> listeVariablemap = new HashSet<>(); 
    Map<Variable,Integer>NombreOccurance=new HashMap<>();

    listeVariable=map.keySet();
    //Recuperer les variables dans la map
    for(Constraint c : this.contrainte)
    {
        listeVariable=c.getScope();
        //Si les variable son contenu dans la map en parametre 
        if(listeVariablemap.containsAll(listeVariable)){
            for(Variable vari : listeVariable)
            {
                if(NombreOccurance.containsKey(vari))
                {
                    int nombreApparitionVar=NombreOccurance.get(vari);
                    nombreApparitionVar++;
                    NombreOccurance.put(vari, nombreApparitionVar);
                }
                else {
                    NombreOccurance.put(vari,1);
                }
            }
        }
    }
    if(this.prefere)
    {
        return getMax(NombreOccurance);
    }
    return getMin(NombreOccurance);


}

public Variable getMin(Map<Variable,Integer>NombreOccurance){
    int min = 100000;
        Variable v = null;
    
        for(Variable vi : NombreOccurance.keySet())
        {
            if(NombreOccurance.get(vi)<min)
            {
                min=NombreOccurance.get(vi);
                v=vi;
            }
        }
        return v;
    



}
public Variable getMax(Map<Variable,Integer>NombreOccurance){
    int max = 0;
    
        Variable v = null;
    
        for(Variable vi : NombreOccurance.keySet())
        {
            if(NombreOccurance.get(vi)>max)
            {
                max=NombreOccurance.get(vi);
                v=vi;
            }
        }
        return v;
    


}
    
}