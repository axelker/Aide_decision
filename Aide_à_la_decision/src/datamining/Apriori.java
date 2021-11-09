package datamining;
import java.util.*;
import representation.*;


public class Apriori extends AbstractItemsetMiner{

    public Apriori(BooleanDatabase database)
    {
        super(database);
    }


    //Frequence pour les item singleton
    public Set<Itemset> frequentSingletons(float frequence){
        //Liste retour items correspondant à la fréquence
        Set<Itemset>listeItemsSingleton=new HashSet<Itemset>();
        Set<BooleanVariable>listeitem=this.database.getItems();

        for(BooleanVariable item : listeitem)
        {
            //Item singleton
            Set<BooleanVariable>items=new HashSet<BooleanVariable>();
            items.add(item);

            //Calcul de la fréquence et si la fréquence st plus grande ou egale alors on l'ajoute
            float f=super.frequency(items);
            if(f>=frequence)
            {
                listeItemsSingleton.add(new Itemset(items,f)); 
            }
            
        }
        return listeItemsSingleton;

        
    }

    /*public static SortedSet<BooleanVariable> combine(SortedSet<BooleanVariable>itemFirst,SortedSet<BooleanVariable>itemTwo){

        Object[] tabfirst =itemFirst.toArray();
        Object[] tabftwo =itemTwo.toArray();

        if(itemFirst.size()==itemTwo.size())
        {
            int size=itemFirst.size();
            if(tabfirst[size-2]==tabftwo[size-2])
            {

            }
        }
        return null;

    }*/



    @Override
    public Set<Itemset> extract(float frequence){
        return null;
    }
}