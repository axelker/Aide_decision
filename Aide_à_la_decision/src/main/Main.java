package main;
import representationtests.*;
import solvertests.*;
import solvers.*;
import representation.*;
import java.util.*;


public class Main{
    

    public static void main(String[] args)
    {
        boolean ok = true;
        ok = ok && VariableTests.testEquals();
        ok = ok && VariableTests.testHashCode();
        ok = ok && BooleanVariableTests.testConstructor();
        ok = ok && BooleanVariableTests.testEquals();
        ok = ok && BooleanVariableTests.testHashCode();
        ok = ok && DifferenceConstraintTests.testGetScope();
        ok = ok && DifferenceConstraintTests.testIsSatisfiedBy();
        ok = ok && ImplicationTests.testGetScope();
        ok = ok && ImplicationTests.testIsSatisfiedBy();
        ok = ok && BinaryExtensionConstraintTests.testGetScope();
        ok = ok && BinaryExtensionConstraintTests.testIsSatisfiedBy();
        ok = ok && AbstractSolverTests.testIsConsistent();
        ok = ok && BacktrackSolverTests.testSolve();

        System.out.println(ok ? "All tests passed" : "At least one test failed");
        
    }
}