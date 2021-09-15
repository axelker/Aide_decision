package solvers;
import representation.*;
import java.lang.*;
import java.util.*;
import java.awt.im.spi.*;

public class BacktrackSolver extends AbstractSolver {


    public BacktrackSolver(Set<Variable>variable,Set<Constraint>contraint)
    {
       super(variable,contraint);

    }

    //Renvoi une solution sous forme de map ou Null 
    @Override
    public Map<Variable,Object>solve()
    {
        // une hashmap vide qui permettras de stocker la solution 
        Map<Variable,Object> N = new HashMap<>();
        //linkedliste ordonné qui copie la liste de variable
        LinkedList<Variable>v = new LinkedList<>(super.variable);
        
       return backtrack(N,v);
    }

    public Map<Variable,Object>backtrack(Map<Variable,Object>I,LinkedList<Variable>v)
    {
        //SI la liste de variables est vide retourner la solution
        if(v.size()==0)
        {
            return I;
        }
        // Sortir une variable et son domaine 
        Variable vi=v.pop();
        Set<Object> domaine = vi.getDomain();
        //Parcourir le domaine de la variable 
        for(Object d : domaine)
        {
            // Nouvelle hashmap contenant la solution vide ou en cours de remplissage
            Map<Variable,Object> N = new HashMap<>(I);
            // Ajout des de la variable et son domaine
            N.put(vi,d);
            // Test si la hashmap
            if(this.isConsistent(N))
            {
                Map<Variable,Object> R = new HashMap<>(backtrack(N,v));
                if(!R.equals(null))
                {
                    return R;
                }
            }

        }
        v.push(vi);
        return null;

    }
}