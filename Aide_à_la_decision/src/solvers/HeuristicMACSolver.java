import java.util.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import representation.*;


public class HeuristicMACSolver extends AbstractSolver {
    private VariableHeuristic heuristicVariable;
    private ValueHeuristic heuristcValeur;

    public HeuristicMACSolver(Set<Variable>variable,Set<Constraint>contraint,VariableHeuristic varHeuristic,ValueHeuristic valHeuristic)
    {
        super(variable,contraint);
        this.heuristicVariable=varHeuristic;
        this.heuristcValeur=valHeuristic;
    }

    @Override
    public Map<Variable,Object>solve() 
    {

    }

    


}