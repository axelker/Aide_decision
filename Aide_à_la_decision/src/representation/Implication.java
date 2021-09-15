package representation;
import java.lang.*;
import java.util.*;

public class Implication implements Constraint{

    private BooleanVariable i1;
    private BooleanVariable i2;
    private boolean vali1;
    private boolean vali2;

    public Implication(BooleanVariable i1,boolean vali1,BooleanVariable i2,boolean vali2)

    {
        this.i1=i1;
        this.i2=i2;
        this.vali1=vali1;
        this.vali2=vali2;
        
    }
    public Variable getV1()
    {
        return this.i1;

    }
    public Variable getV2()
    {
        return this.i2;

    }
    @Override
    public Set<Variable>getScope()
    {
        Set<Variable> d = new HashSet<>(); 
        d.add(i1);
        d.add(i2);
        return d;
    } 
    @Override
    public boolean isSatisfiedBy(Map<Variable,Object>Nom_map)
    {
        //Test si les variable sont dans la map
        if(Nom_map.containsKey(i1) && Nom_map.containsKey(i2))
        {
            //Recuper en testant si les boolean attribué au variable sont identique à ceux en parametre
            boolean a= Nom_map.get(i1).equals(vali1);
            boolean b =Nom_map.get(i2).equals(vali2);
            
        //Retourner la formule de l'implication !p V q =  p=>q
          return !a || b;
        }
        
        throw new IllegalArgumentException("Les variables ne sont pas contenu dans la map");

    }


}