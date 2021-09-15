package solvers;
import representation.*;
import java.lang.*;
import java.util.*;
import java.awt.im.spi.*;
public abstract class AbstractSolver implements Solver {

    protected Set<Variable>variable;
    protected Set<Constraint>contraint;
    public AbstractSolver(Set<Variable>variable,Set<Constraint>contraint)
    {
        this.variable=variable;
        this.contraint=contraint;
    }
    
    

    public boolean isConsistent(Map<Variable,Object>map)
    {
        //test si les contraintes contiens les variables de la liste
        for(Constraint c: this.contraint)
        {
            if(map.keySet().containsAll(c.getScope()))
            {
                // Si oui on test si la contrainte n'est satisfaite on stop la fonction
                if(!c.isSatisfiedBy(map))
                {
                    return false;
                }
            }
            // SI non contenu stoper directement
            else {
                return false;
            }
        }
        //VAriable respect les contrainte 
        return true;
    } 

}