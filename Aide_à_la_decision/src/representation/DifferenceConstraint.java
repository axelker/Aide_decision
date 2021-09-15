package representation;
import java.util.*;
import java.lang.*;
import java.io.*;

public class DifferenceConstraint implements Constraint{

    private Variable v1;
    private Variable v2;

    public DifferenceConstraint(Variable v1,Variable v2)
    {
        this.v1=v1;
        this.v2=v2;
    }

    public Variable getV1()
    {
        return this.v1;

    }
    public Variable getV2()
    {
        return this.v2;

    }

    public Set<Variable>getScope()
    {
        Set<Variable> d = new HashSet<>(); 
        d.add(v1);
        d.add(v2);
        return d;
    }
    public boolean isSatisfiedBy(Map<Variable,Object>Nom_map)
    {
        //Test si les variables sont contenu dans la map
        if(Nom_map.containsKey(v1) && Nom_map.containsKey(v2))
        {
            // Retourner si la valeur corresponds à la différence des deux
            return (!Nom_map.get(v1).equals(Nom_map.get(v2)));
           
            
        }
       throw new IllegalArgumentException("Les variables ne sont pas égales");
    }
}