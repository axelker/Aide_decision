package representation;
import java.lang.*;
import java.util.*;


public class BooleanVariable extends Variable {
    private static Set<Object> d = new HashSet<>();
    static{
        d.add(true);
        d.add(false);
    }
    public BooleanVariable(String n)   
    {
        super(n,BooleanVariable.d);
    }

}