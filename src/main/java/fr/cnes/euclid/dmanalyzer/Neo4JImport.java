package fr.cnes.euclid.dmanalyzer;

import org.neo4j.driver.v1.Session;

public class Neo4JImport {

    public static void main(String[] args) {

        Neo4JDriver driver = new Neo4JDriver();

        DmAnalysis dmAnalysisDpd = new DmAnalysis("dpd.json");
        DmAnalysis dmAnalysisDictionary = new DmAnalysis("dictionary.json");

        try (Session session = driver.getDriver().session()) {
            session.writeTransaction(new DmAnalysisWork(dmAnalysisDpd));
            session.writeTransaction(new DmAnalysisWork(dmAnalysisDictionary));
        }

        driver.close();

    }

}
