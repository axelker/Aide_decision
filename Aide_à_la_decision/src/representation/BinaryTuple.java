package representation;
import java.util.*;
import java.lang.*;

public class BinaryTuple {
    private Object v1;
    private Object v2;

    public BinaryTuple(Object v1,Object v2)
    {
        this.v1=v1;
        this.v2=v2;
    }

    public Object getv1()
    {
        return this.v1;
    }
    public Object getv2()
    {
        return this.v2;
    }
    @Override
    public boolean equals(Object c)
    {
        if(this==c)
        {
            return true;
        }

        else if(c instanceof BinaryTuple)
            {
                // Création de tmp incluant c pour convertir en type Variable pour la comparaison des nom
                BinaryTuple tmp = (BinaryTuple)c;
                if(tmp.v1.equals(this.v1) && tmp.v2.equals(this.v2))
                {
                    return true;
                }
                return false;
            }
        
        return false;
    }

    @Override
    public int hashCode()
    {
        // Utilisation de Objects.hash pour inclure deux objet en paramètre de cette fonction et hasher les deux valeurs 
        return Objects.hash(this.v1,this.v2);
    }


}