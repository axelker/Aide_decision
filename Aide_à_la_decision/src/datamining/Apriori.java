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

    //Combine deux items trié pour renvoyer un sous domaine trié de ces deux items (AB,AC=>ABC)
    public static SortedSet<BooleanVariable> combine(SortedSet<BooleanVariable>itemFirst,SortedSet<BooleanVariable>itemTwo){

        //Stockage des item sous forme de tableaux pour les parcourirs
        Object[] tabfirst =itemFirst.toArray();
        Object[] tabftwo =itemTwo.toArray();
        //Taille set items(choix du 1 car inutile de tester les deux)
        int size=itemFirst.size();

        //Si items vide retourner null
        if(size==0)
        {
            return null;
        }
        //Verifier que les items sont de meme taille
        if(itemFirst.size()==itemTwo.size())
        {
           //Parcourir les items jusqu'a k-1 pour tester leur égalité
            for(int i=0;i<size-1;i++)
            {
                if(tabfirst[i]!=tabftwo[i])
                {
                    return null;
                }
            }
            //Testr que le dernier items de chaque set ne sont pas égaux entre eux
            if(tabfirst[size-1]!=tabftwo[size-1])
            {
                //Retourner le set combiner en castant les objet
                SortedSet<BooleanVariable> setRetour = new TreeSet<>(AbstractItemsetMiner.COMPARATOR);
                for(int i=0;i<size;i++)
                {
                    BooleanVariable b1=BooleanVariable.class.cast(tabfirst[i]);
                    setRetour.add(b1);
                }
                setRetour.add(BooleanVariable.class.cast(tabftwo[size-1]));
                return setRetour;

            }
            else {
                return null;
            }
            
        }
        return null;

    }



    @Override
    public Set<Itemset> extract(float frequence){
        return null;
    }
}