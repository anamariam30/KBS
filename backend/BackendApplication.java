package com.example.backend;

import org.apache.jena.query.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class BackendApplication {
    public static void main(String[] args) {
//        ParameterizedSparqlString qs = new ParameterizedSparqlString(""
//                + "prefix rdfs:    <http://www.w3.org/2000/01/rdf-schema#>\n"
//                + "PREFIX dbo:     <http://dbpedia.org/ontology/>"
//                + "\n"
//                + "select distinct ?resource ?abstract where {\n"
//                + "  ?resource rdfs:label 'Ibuprofen'@en.\n"
//                + "  ?resource dbo:abstract ?abstract.\n"
//                + "  FILTER (lang(?abstract) = 'en')}");
        ParameterizedSparqlString qs = new ParameterizedSparqlString(""
                + "PREFIX foaf: <http://xmlns.com/foaf/0.1/>\n"
                + "PREFIX dbo:     <http://dbpedia.org/ontology/>"
                + "\n"
                + "SELECT ?name ?animal ?thumbnail WHERE {\n" +
                "      ?animal a dbo:Arachnid.\n" +
                "      ?animal foaf:name ?name .\n" +
                "      ?animal dbo:thumbnail ?thumbnail\n" +
                "}  ");


        QueryExecution exec = QueryExecutionFactory.sparqlService("http://dbpedia.org/sparql", qs.asQuery());

        ResultSet results = exec.execSelect();

        while (results.hasNext()) {

            System.out.println(results.next().get("abstract").toString());
        }

        ResultSetFormatter.out(results);
    }

}
