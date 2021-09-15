package examples;
import java.util.*;
import java.lang.*;


public class HouseExample {
    private int largeur;
    private int longueur;
    private Set<String>NbPieceEau;
    private Set<String>NbAutrePiece;

    public HouseExample(int largeur,int longueur,Set<String>NbPieceEau,Set<String>NbAutrePiece)
    {
        this.largeur=largeur;
        this.longueur=longueur;
        this.NbPieceEau=NbPieceEau;
        this.NbAutrePiece=NbAutrePiece;

    }


    public int getLargeur()
    {
        return this.largeur;
    }
    public int getLongueur()
    {
        return this.longueur;
    }
    public Set<String>getPieceEau()
    {
        return this.NbPieceEau;
    }
    public Set<String>getAutrePiece()
    {
        return this.NbAutrePiece;
    }

}