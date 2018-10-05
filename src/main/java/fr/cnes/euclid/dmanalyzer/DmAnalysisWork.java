package fr.cnes.euclid.dmanalyzer;

import org.neo4j.driver.v1.Transaction;
import org.neo4j.driver.v1.TransactionWork;

public class DmAnalysisWork implements TransactionWork<Integer> {

    private DmAnalysis dmAnalysis;

    public DmAnalysisWork(DmAnalysis dmAnalysis) {
        this.dmAnalysis = dmAnalysis;
    }

    @Override
    public Integer execute(Transaction transaction) {

        dmAnalysis.forEach((linkset) -> {
            linkset.getLinkTargets().forEach((target, weight) -> {
                transaction.run(String.format("MERGE (s:Namespace {name : \"%s\"})", linkset.linkSource));
                transaction.run(String.format("MERGE (t:Namespace {name : \"%s\"})", target));
                transaction.run(String.format("MATCH (s:Namespace {name : \"%s\"}), (t:Namespace {name : \"%s\"}) CREATE (s)-[:DEPENDS_ON {weight:%d}]->(t)", linkset.linkSource, target, weight));
            });
        });

        return 1;
    }
}
