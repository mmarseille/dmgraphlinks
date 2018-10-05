package fr.cnes.euclid.dmanalyzer;

import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;

import java.io.IOException;
import java.util.Properties;

public class Neo4JDriver implements AutoCloseable {

    private Driver driver;

    public Neo4JDriver() {

        Properties props = new Properties();
        try {
            props.load(Neo4JDriver.class.getClassLoader().getResourceAsStream("neo4j.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        driver = GraphDatabase.driver(
                props.getProperty("neo4j.uri"),
                AuthTokens.basic(
                        props.getProperty("neo4j.login"),
                        props.getProperty("neo4j.password")
                ));
    }

    public Driver getDriver() {
        return driver;
    }

    public void close() {
        driver.close();
    }
}
