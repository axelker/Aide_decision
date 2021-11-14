package main;
import representationtests.*;
import solvertests.*;
import solvers.*;
import representation.*;
import planning.*;
import planningtests.*;
import datamining.*;
import dataminingtests.*;
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

        //Solver
        ok = ok && AbstractSolverTests.testIsConsistent();
        ok = ok && BacktrackSolverTests.testSolve();
        ok = ok && ArcConsistencyTests.testEnforceNodeConsistency();
        ok = ok && ArcConsistencyTests.testRevise();
        ok = ok && ArcConsistencyTests.testAC1();
        ok = ok && MACSolverTests.testSolve();
        ok = ok && HeuristicMACSolverTests.testSolve();
        ok = ok && NbConstraintsVariableHeuristicTests.testBest();
        ok = ok && DomainSizeVariableHeuristicTests.testBest();
        ok = ok && RandomValueHeuristicTests.testOrdering();



        //Planning 
        ok = ok && BasicActionTests.testIsApplicable();
        ok = ok && BasicActionTests.testSuccessor();
        ok = ok && BasicActionTests.testGetCost();
        ok = ok && BasicGoalTests.testIsSatisfiedBy();
        ok = ok && DFSPlannerTests.testPlan();
        ok = ok && BFSPlannerTests.testPlan();
        ok = ok && DijkstraPlannerTests.testPlan();
        ok = ok && AStarPlannerTests.testPlan();

        //Extraction 
        ok = ok && AbstractItemsetMinerTests.testFrequency();
        ok = ok && AprioriTests.testFrequentSingletons();
        ok = ok && AprioriTests.testCombine();
        ok = ok && AprioriTests.testAllSubsetsFrequent();
        ok = ok && AprioriTests.testExtract();
        ok = ok && AbstractAssociationRuleMinerTests.testFrequency();
        ok = ok && AbstractAssociationRuleMinerTests.testConfidence();
        ok = ok && BruteForceAssociationRuleMinerTests.testAllCandidatePremises();
        ok = ok && BruteForceAssociationRuleMinerTests.testExtract();
        ok = ok && DatabaseTests.testItemTable();
        ok = ok && DatabaseTests.testPropositionalize();


        
       





        System.out.println(ok ? "All tests passed" : "At least one test failed");
    }
    
}