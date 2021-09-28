package solvers;
import representation.*;
import java.lang.*;
import java.util.*;
import java.awt.im.spi.*;



public class MACSolver extends AbstractSolver{

    public MACSolver(Set<Variable>variable,Set<Constraint>contraint)
    {
       super(variable,contraint);

    }
    @Override
    public Map<Variable,Object>solve()
    {
         // une hashmap vide qui permettras de stocker la solution 
         Map<Variable,Object> N = new HashMap<>();
         //linkedliste ordonné qui copie la liste de variable
         LinkedList<Variable>v = new LinkedList<>(this.variable);
         return N;
        //return MAC(N,v);
    }

    /*public Map<Variable,Object> MAC(Map<Variable,Set<Object>>I,LinkedList<Variable>v)
    {
        if(v.isEmpty())
        {
            return I;
        }
        else {
            ArcConsistency arc = new ArcConsistency(this.contraint);
            if(!arc.ac1(I))
            {
                return null;
            }

        }
        return null;
    }*/

}