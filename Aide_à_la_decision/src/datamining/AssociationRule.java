package datamining;
import java.util.*;
import representation.*;



public class AssociationRule {

    private Set<BooleanVariable>premise;
    private Set<BooleanVariable>conclusion;
    private float frequence;
    private float confiance;
    public AssociationRule(Set<BooleanVariable>p,Set<BooleanVariable>c,float f,float conf)
    {
        this.premise=p;
        this.conclusion=c;
        this.frequence=f;
        this.confiance=conf;
    }
}