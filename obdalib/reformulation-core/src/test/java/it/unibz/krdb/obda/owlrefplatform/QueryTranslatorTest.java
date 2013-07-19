/*
 * @(#)QueryTranslatorTest 3/11/2010
 *
 * Copyright 2010 OBDA-API. All rights reserved.
 * Use is subject to license terms.
 */
package it.unibz.krdb.obda.owlrefplatform;

import it.unibz.krdb.obda.model.Atom;
import it.unibz.krdb.obda.model.CQIE;
import it.unibz.krdb.obda.model.DatalogProgram;
import it.unibz.krdb.obda.owlrefplatform.core.translator.SparqlAlgebraToDatalogTranslator;

import java.util.List;

import junit.framework.TestCase;

import com.hp.hpl.jena.query.QueryException;

/**
 * Tests {@link SPARQLDatalogTranslator}
 *
 * @author Josef Hardi <josef.hardi@gmail.com>
 */
public class QueryTranslatorTest extends TestCase {

	private static String[] QUERIES = {
	// Scenario 0: Basic syntax.
		"PREFIX :		<http://www.owl-ontologies.com/Ontology1207768242.owl#> \n" +
		"PREFIX rdf:	<http://www.w3.org/1999/02/22-rdf-syntax-ns#> \n" +
		"SELECT * \n" +
		"WHERE { \n" +
			"$x rdf:type :Address. \n" +
			"$x :addressID $id. \n" +
			"$x :inStreet $street. \n" +
			"$x :inCity $city. \n" +
			"$x :inCountry $country. \n" +
			"$x :inState $state. \n" +
			"$x :hasNumber $number. } ",
	// Scenario 1: Subject is a node literal.
		"PREFIX :		<http://www.owl-ontologies.com/Ontology1207768242.owl#> \n" +
		"PREFIX rdf:	<http://www.w3.org/1999/02/22-rdf-syntax-ns#> \n" +
		"SELECT * \n" +
		"WHERE { \n" +
			"'HomeAdress' rdf:type :Address. \n" +
			"$x :addressID $id. \n" +
			"$x :inStreet $street. }",
	// Scenario 2: Subject is a node Uri.
		"PREFIX :		<http://www.owl-ontologies.com/Ontology1207768242.owl#> \n" +
		"PREFIX rdf:	<http://www.w3.org/1999/02/22-rdf-syntax-ns#> \n" +
		"SELECT * \n" +
		"WHERE { \n" +
			":HomeAdress rdf:type :Address. \n" +
			"$x :addressID $id_name. \n" +
			"$x :inStreet $street. }",
	// Scenario 3: Subject and Object are node literals.
		"PREFIX :		<http://www.owl-ontologies.com/Ontology1207768242.owl#> \n" +
		"SELECT * \n" +
		"WHERE { \n" +
			"$x :firstName 'John'. \n" +
			"'Person' :lastName $ln. }",
	// Scenario 4
		"PREFIX :		<http://www.owl-ontologies.com/Ontology1207768242.owl#> \n" +
		"SELECT * \n" +
		"WHERE { \n" +
			"$x :firstName :John. \n" +
			":Person :lastName $ln. }",
	// Scenario 5
		"PREFIX :		<http://www.owl-ontologies.com/Ontology1207768242.owl#> \n" +
		"PREFIX rdf:	<http://www.w3.org/1999/02/22-rdf-syntax-ns#> \n" +
		"SELECT $fn \n" +
		"WHERE { \n" +
			"$trader rdf:type $traderType. \n" +
			"$trader :firstName $fn. } ",
	// Scenario 6
		"PREFIX :		<http://www.owl-ontologies.com/Ontology1207768242.owl#> \n" +
		"PREFIX rdf:	<http://www.w3.org/1999/02/22-rdf-syntax-ns#> \n" +
		"SELECT $fn \n" +
		"WHERE { \n" +
			"$trader rdf:type 'StockTrader'. \n" +
			"$trader :firstName $fn. } ",
	// Scenario 7
		"PREFIX :		<http://www.owl-ontologies.com/Ontology1207768242.owl#> \n" +
		"SELECT $fn \n" +
		"WHERE { \n" +
			"$trader $hasName $n. } ",
	// Scenario 8
		"PREFIX :		<http://www.owl-ontologies.com/Ontology1207768242.owl#> \n" +
		"SELECT $fn \n" +
		"WHERE { \n" +
			"$trader 'HasFirstName' $fn. } ",
	// Scenario 9
		"PREFIX :		<http://www.owl-ontologies.com/Ontology1207768242.owl#> \n" +
		"SELECT $fn \n" +
		"WHERE { \n" +
			":Trader :firstName $fn. } ",
	// Scenario 10
		"PREFIX :		<http://www.owl-ontologies.com/Ontology1207768242.owl#> \n" +
		"SELECT $fn \n" +
		"WHERE { \n" +
			"$trader :firstName $fn. } \n" +
		"ORDER BY $fn"
	};



	@Override
	protected void setUp() throws Exception {
		super.setUp();
//		translator = new SPARQLDatalogTranslator();
	}

	public void testGetDatalog() {
		DatalogProgram datalog = SparqlAlgebraToDatalogTranslator.translate(QUERIES[0]);
		List<CQIE> rules = datalog.getRules();
		List<Atom> body = rules.get(0).getBody();
		assertTrue("Number of rules", rules.size() == 1);
		assertTrue("Number of body atoms", body.size() == 7);
	}

	public void testRdfTypeAndSubjectIsNodeLiteral() {
		DatalogProgram datalog = SparqlAlgebraToDatalogTranslator.translate(QUERIES[1]);
		List<CQIE> rules = datalog.getRules();
		List<Atom> body = rules.get(0).getBody();
		assertTrue("Number of rules", rules.size() == 1);
		assertTrue("Number of body atoms", body.size() == 3);
	}

	public void testRdfTypeAndSubjectIsNodeUri() {
		DatalogProgram datalog = SparqlAlgebraToDatalogTranslator.translate(QUERIES[2]);
		List<CQIE> rules = datalog.getRules();
		List<Atom> body = rules.get(0).getBody();
		assertTrue("Number of rules", rules.size() == 1);
		assertTrue("Number of body atoms", body.size() == 3);
	}

	public void testSubjectAndObjectAreNodeLiteral() {
		DatalogProgram datalog = SparqlAlgebraToDatalogTranslator.translate(QUERIES[3]);
		List<CQIE> rules = datalog.getRules();
		List<Atom> body = rules.get(0).getBody();
		assertTrue("Number of rules", rules.size() == 1);
		assertTrue("Number of body atoms", body.size() == 2);
	}

	public void testSubjectAndObjectAreNodeUri() {
		DatalogProgram datalog = SparqlAlgebraToDatalogTranslator.translate(QUERIES[4]);
		List<CQIE> rules = datalog.getRules();
		List<Atom> body = rules.get(0).getBody();
		assertTrue("Number of rules", rules.size() == 1);
		assertTrue("Number of body atoms", body.size() == 2);
	}

	public void testRdfTypeAndObjectIsVariable() {
		try {
			DatalogProgram datalog = SparqlAlgebraToDatalogTranslator.translate(QUERIES[5]);
			List<CQIE> rules = datalog.getRules();
			List<Atom> body = rules.get(0).getBody();
			assertTrue("Number of rules", rules.size() == 1);
			assertTrue("Number of body atoms", body.size() == 2);
			fail("parse() should've thrown an exception!");
		}
		catch(QueryException e) {}
	}

	public void testRdfTypeAndObjectIsNodeLiteral() {
		try {
			DatalogProgram datalog = SparqlAlgebraToDatalogTranslator.translate(QUERIES[6]);
			List<CQIE> rules = datalog.getRules();
			List<Atom> body = rules.get(0).getBody();
			assertTrue("Number of rules", rules.size() == 1);
			assertTrue("Number of body atoms", body.size() == 2);
			fail("parse() should've thrown an exception!");
		}
		catch(QueryException e) {}
	}

	public void testPredicateIsVariable() {
		try {
			DatalogProgram datalog = SparqlAlgebraToDatalogTranslator.translate(QUERIES[7]);
			List<CQIE> rules = datalog.getRules();
			List<Atom> body = rules.get(0).getBody();
			assertTrue("Number of rules", rules.size() == 1);
			assertTrue("Number of body atoms", body.size() == 1);
			fail("parse() should've thrown an exception!");
		}
		catch(QueryException e) {}
	}

	public void testPredicateIsNodeLiteral() {
		try {
			DatalogProgram datalog = SparqlAlgebraToDatalogTranslator.translate(QUERIES[8]);
			List<CQIE> rules = datalog.getRules();
			List<Atom> body = rules.get(0).getBody();
			assertTrue("Number of rules", rules.size() == 1);
			assertTrue("Number of body atoms", body.size() == 1);
			fail("parse() should've thrown an exception!");
		}
		catch(QueryException e) {}
	}

	public void testPredicateIsNodeUri() {
		DatalogProgram datalog = SparqlAlgebraToDatalogTranslator.translate(QUERIES[9]);
		List<CQIE> rules = datalog.getRules();
		List<Atom> body = rules.get(0).getBody();
		assertTrue("Number of rules", rules.size() == 1);
		assertTrue("Number of body atoms", body.size() == 1);
	}

	public void testDifferentElementGroup() {
		DatalogProgram datalog = SparqlAlgebraToDatalogTranslator.translate(QUERIES[10]);
		List<CQIE> rules = datalog.getRules();
		List<Atom> body = rules.get(0).getBody();
		assertTrue("Number of rules", rules.size() == 1);
		assertTrue("Number of body atoms", body.size() == 1);
	}
}