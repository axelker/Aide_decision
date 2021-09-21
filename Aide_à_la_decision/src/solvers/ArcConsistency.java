package solvers;
import representation.*;
import java.lang.*;
import java.util.*;


import java.awt.im.spi.*;

public class ArcConsistency {

    private Set<Constraint>contraint;

    public ArcConsistency(Set<Constraint>contraint) throws IllegalArgumentException
    {
        // SI plus de deux contraints
        for(Constraint c : contraint)
        {

            if(c.getScope().size()>2)
            {
                throw new IllegalArgumentException("Erreur taille supérieur ou égale à deux");
            }
        }
        
        this.contraint=contraint;
        
    }

    public boolean enforceNodeConsistency(Map<Variable,Set<Object>>map)
    {
        for(Variable x : map.keySet())
        {
            Set<Object> new_domaine = new HashSet<>();
            Set<Object>domaine = map.get(x);
            for(Object d : domaine)
            {
                Map<Variable,Object> m = new HashMap<>();
                m.put(x,d);
                for(Constraint c : this.contraint)
                {
                    if(c.getScope().size()==1 && c.getScope().contains(x))
                    {
                        if(c.isSatisfiedBy(m))
                        {
                            new_domaine.add(d);
                        }
                    }
                }
                map.put(x,new_domaine);
                
            }
                      
            
        }
        //BOucle sur la map 
        for(Variable x : map.keySet())
        {
            if(map.get(x).isEmpty())
            {
                return false;
            }
        }
        
        return true;
    }

}