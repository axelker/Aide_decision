package examples;
import java.util.*;
import java.lang.*;
import representation.*;
import solvers.*;
import planning.*;
import datamining.*;


public class HouseDemo {


 public static void main(String[] args) {

    /******** Dimension de la maison ********/
    /**************************************/
    final int largeur=3;
    final int longueur=3;

    //*******************Création des Pièces d'eau ********************/
    /******************************************************************/
    Set<String>NbPieceEau=new HashSet<String>();
    NbPieceEau.add("Salle de bain");
    NbPieceEau.add("Salle de bain 2");
    NbPieceEau.add("Toilette");
    NbPieceEau.add("Cuisine");
    //Affichage//
    System.out.println("\n\n--------- Pièces d'eau --------- ");
    for(String eau : NbPieceEau )
    {
      System.out.println(eau+" , ");
    }
    System.out.println(" ------------------\n");

    

    //*****************Création autre pièce****************************/
    /******************************************************************/
    Set<String>NbAutrePiece = new HashSet<String>();
    NbAutrePiece.add("Chambre");
    NbAutrePiece.add("Chambre 2");
    NbAutrePiece.add("Chambre 3");
    NbAutrePiece.add("Salon");
    NbAutrePiece.add("Mezzanine");
    System.out.println("------------Autres pièces --------- ");
    for(String autre : NbAutrePiece )
    {
      System.out.println(autre+" , ");
    }
    System.out.println(" ---------------------- \n");


    //*****************Création d'une maison largeurxlongueur(3x3)***************//
    /******************************************************************/
    HouseExample house= new HouseExample(largeur,longueur,NbPieceEau,NbAutrePiece);

    //Récupération du domaine des variables étant toute les valeurs de pièce possible
    Set<Object>domaine=house.getDomain();

    Variable[][] tabVar = new Variable[largeur][longueur];

    //*******************Création variables associé à leur domaine ***********************//
    /******************************************************************/
    for(int i=1;i<=house.getLargeur();i++)
    {
      for(int j=1;j<=house.getLongueur();j++)
      {
        //Création et ajout des variables pièce avec leur domaine dans la maison ainsi que dans le tableau de variable
        house.addVariable(new Variable("Pièce "+i+","+j,domaine));
        tabVar[i-1][j-1]=new Variable("Pièce "+i+","+j,domaine);
      }
    }

    //Affichage variable pièces
    System.out.println("Affichage des variables pièces : \n");
    for(int i=0;i<largeur;i++)
    {
      for(int j=0;j<longueur;j++)
      {
        System.out.println("Variable "+ tabVar[i][j].getName());
      }
    }
    


    //Récupérer les variables pièce pour pouvoir appliqué des contraintes sur les pièces
    List<Variable>ListeVariablesPiece=new ArrayList<Variable>(house.getVariables());

    //*****Création des contraintes portant sur les positions i,j des pièces********************//
    /******************************************************************/
    for(int i=0;i<ListeVariablesPiece.size();i++)
    {
      //Ajout de la contrainte sur l'occupation de tous les emplacement i,j par une pièce        
      Variable var = ListeVariablesPiece.get(i);
      house.addContrainte(new DifferenceConstraint(var,null));
      for(int j=i+1;j<ListeVariablesPiece.size();j++)
      {
        //Ajout des contrainte portante sur la différence de chaque pièce entre elles occupant une postion au sein d'une maison
        Variable varNext = ListeVariablesPiece.get(j);
        house.addContrainte(new DifferenceConstraint(var,varNext));
      }
    }
    
    
    //*********************** Contrainte pièce d'eau cote à cote *******/
    /******************************************************************/
    int k=0;
      for(int i=0;i<largeur;i++)
      {
        if(k==longueur)
        {
          k=0;
        }
        Variable var = tabVar[i][k];
        for(int j=i+1;j<longueur-1;j++)
        {
          Variable varNext = tabVar[i][j];
           //Création d'une contrainte binaryextension sur la var et varNext
           BinaryExtensionConstraint contrainteBinaireExtension=new BinaryExtensionConstraint(var,varNext);

          for(Object value : var.getDomain())
          {
            for(Object valueNext : varNext.getDomain())
            {
              //Test que les pièces ne soit pas voisines
              if(!house.adjacent(i,j,tabVar,var))
              {
                //Si les deux pièces ne sont pas es pièces d'eau
                if(!NbPieceEau.contains(value) || !NbPieceEau.contains(valueNext))
                {
                  contrainteBinaireExtension.addTuple(value, valueNext);
                }
              }
              else {
                if(NbPieceEau.contains(value) && NbPieceEau.contains(valueNext))
                {
                  contrainteBinaireExtension.addTuple(value, valueNext);
                }
              }
            }
          }
          house.addContrainte(contrainteBinaireExtension);
        }
        k++;
      }
     

        
       
    
    //*******Création de variables booléennes et ajout au set variable de la maison********************//
    /******************************************************************/
  
    BooleanVariable dalleCoulle=new BooleanVariable("Dalle coulée");
    BooleanVariable solHumide=new BooleanVariable("Sol humide");
    BooleanVariable mursEleve=new BooleanVariable("Murs élevés");
    BooleanVariable toitureTermine=new BooleanVariable("Toiture terminée");
    house.addVariable(dalleCoulle);
    house.addVariable(solHumide);
    house.addVariable(mursEleve);
    house.addVariable(toitureTermine);

    //********************Contrainte d'implication sur les booleanVariable *****************/
      Constraint non_dalle_solMouille = new Implication(dalleCoulle, false, solHumide, true);
      Constraint sol_sec_dallecoulle = new Implication(solHumide, false, dalleCoulle, true);
      Constraint solMouille_nonMur = new Implication(solHumide, true, mursEleve, false);
      Constraint NonMur_NonToit = new Implication(mursEleve, false, toitureTermine, false);
      Constraint Mur_dalle = new Implication(mursEleve,true, dalleCoulle, true);
      Constraint mur_solSec = new Implication(mursEleve, true, solHumide, false);
      Constraint toi_dalle =new Implication(toitureTermine, true, dalleCoulle, true);
      Constraint toi_sol =new Implication(toitureTermine, true, solHumide, false);
      Constraint toi_mur = new Implication(toitureTermine, true, mursEleve, true);

      house.addContrainte(non_dalle_solMouille);
      house.addContrainte(sol_sec_dallecoulle);
      house.addContrainte(solMouille_nonMur);
      house.addContrainte(NonMur_NonToit);
      house.addContrainte(Mur_dalle);
      house.addContrainte(mur_solSec);
      house.addContrainte(toi_dalle);
      house.addContrainte(toi_sol);
      house.addContrainte(toi_mur);
      System.out.println("\n------------------Solution --------------------------- \n");
      Map<Variable,Object>Solution = new MACSolver(house.getVariables(),house.getConstraints()).solve();
      if(Solution!=null)
      {
        for(Variable var : Solution.keySet())
        {
          System.out.println("Nom variable : " +var.getName() +" valeur : "+Solution.get(var));
        }
      }

      else {
        System.out.println("solution null");
      }
      




    }
}