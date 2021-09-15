package representation;
import java.util.*;

import javax.print.attribute.standard.MediaSize.Other;

import java.lang.*;


public class BinaryExtensionConstraint implements Constraint{
    private Variable v1;
    private Variable v2;
    private Set<BinaryTuple>tuple;

    public BinaryExtensionConstraint(Variable v1,Variable v2)
    {
        this.v1=v1;
        this.v2=v2;
        this.tuple=new HashSet<>();
    }
    public Variable getV1()
    {
        return this.v1;

    }
    public Variable getV2()
    {
        return this.v2;

    }
    public void addTuple(Object value1,Object value2)
    {
        //CRÉATION ET Ajout du binarytuple contenant les deux valeur
        BinaryTuple b = new BinaryTuple(value1, value2);
        this.tuple.add(b);
    }
    @Override
    public Set<Variable>getScope()
    {
        Set<Variable> d = new HashSet<>();
        d.add(v1);
        d.add(v2);
        return d;
    }

    @Override
   
    public boolean isSatisfiedBy(Map<Variable,Object>Nom_map)
    {
        //Test si les variables sont dans la map
        if(Nom_map.containsKey(v1) && Nom_map.containsKey(v2))
        {
            //Parcours du tuple pour tester si les valeurs correspondent au moin à un couple de valeur
            for(BinaryTuple tmp : this.tuple)
            {
                if(Nom_map.get(v1).equals(tmp.getv1()) && Nom_map.get(v2).equals(tmp.getv2()))
                {
                    return true;
                }
            }
            return false;
        }
        throw new IllegalArgumentException("Les variables ne sont pas contenu dans la map");
    }
}