/*
 * Copyright (C) 2009-2013, Free University of Bozen Bolzano
 * This source code is available under the terms of the Affero General Public
 * License v3.
 * 
 * Please see LICENSE.txt for full license terms, including the availability of
 * proprietary exceptions.
 */
package it.unibz.krdb.obda.owlrefplatform.core.dagjgrapht;

import it.unibz.krdb.obda.ontology.Description;
import it.unibz.krdb.obda.ontology.OClass;
import it.unibz.krdb.obda.ontology.Property;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import org.jgrapht.DirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.traverse.AbstractGraphIterator;
import org.jgrapht.traverse.BreadthFirstIterator;

/**
 * Allows to reason over the TBox using Named DAG
 * TEST ONLY CLASS
 */

public class TestTBoxReasonerImplOnNamedDAG implements TBoxReasoner {

	private NamedDAGImpl dag;

	private Set<OClass> namedClasses;
	private Set<Property> property;

	/**
	 * Constructor using a DAG or a named DAG
	 * @param dag DAG to be used for reasoning
	 */
	public TestTBoxReasonerImplOnNamedDAG(NamedDAGImpl dag) {
		this.dag = dag;
		namedClasses = dag.getClasses();
		property = dag.getRoles();
	}
	
	/**
	 * return the direct children starting from the given node of the dag
	 * 
	 * @param desc node that we want to know the direct children
	 * @return we return a set of set of description to distinguish between
	 *         different nodes and equivalent nodes. equivalent nodes will be in
	 *         the same set of description
	 */
	@Override
	public Set<Set<Description>> getDirectChildren(Description desc) {
		
		LinkedHashSet<Set<Description>> result = new LinkedHashSet<Set<Description>>();

		// take the representative node
		Description node = dag.getReplacementFor(desc);
		if (node == null)
			node = desc;

		for (DefaultEdge edge : dag.incomingEdgesOf(node)) {
			
			Description source = dag.getEdgeSource(edge);

			// get the child node and its equivalent nodes
			Set<Description> namedEquivalences = getEquivalences(source);

			if (!namedEquivalences.isEmpty())
				result.add(namedEquivalences);
			else 
				result.addAll(getNamedChildren(source));
		}

		return Collections.unmodifiableSet(result);
	}

	/*
	 *  Private method that searches for the first named children
	 */

	private Set<Set<Description>> getNamedChildren(Description desc) {

		LinkedHashSet<Set<Description>> result = new LinkedHashSet<Set<Description>>();

		// get equivalences of the current node
		Set<Description> equivalenceSet = getEquivalences(desc);
		// I want to consider also the children of the equivalent nodes
		Set<DefaultEdge> edges = dag.incomingEdgesOf(desc);
		for (DefaultEdge edge : edges) {
			Description source = dag.getEdgeSource(edge);

			// I don't want to consider as children the equivalent node of
			// the current node desc
			if (equivalenceSet.contains(source)) 
				continue;

			Set<Description> namedEquivalences = getEquivalences(source);

			if (!namedEquivalences.isEmpty())
				result.add(namedEquivalences);
			else 
				result.addAll(getNamedChildren(source));
		}
			
		return result;
	}

	/**
	 * return the direct parents starting from the given node of the dag
	 * 
	 * @param desc node from which we want to know the direct parents
	 *            
	 * @return we return a set of set of description to distinguish between
	 *         different nodes and equivalent nodes. equivalent nodes will be in
	 *         the same set of description
	 * */
	@Override
	public Set<Set<Description>> getDirectParents(Description desc) {

		LinkedHashSet<Set<Description>> result = new LinkedHashSet<Set<Description>>();
		
		// take the representative node
		Description node = dag.getReplacementFor(desc);
		if (node == null)
			node = desc;

		for (DefaultEdge edge : dag.outgoingEdgesOf(node)) {
			Description target = dag.getEdgeTarget(edge);

			// get the child node and its equivalent nodes
			Set<Description> namedEquivalences = getEquivalences(target);
			if (!namedEquivalences.isEmpty())
				result.add(namedEquivalences);
			else {
				result.addAll(getNamedParents(target));
			}
		}

		return Collections.unmodifiableSet(result);
	}

	/*
	 *  private method that search for the first named parents
	 */
	
	private Set<Set<Description>> getNamedParents(Description desc) {

		LinkedHashSet<Set<Description>> result = new LinkedHashSet<Set<Description>>();

		// get equivalences of the current node
		Set<Description> equivalenceSet = getEquivalences(desc);
		// I want to consider also the parents of the equivalent nodes

		Set<DefaultEdge> edges = dag.outgoingEdgesOf(desc);
		for (DefaultEdge edge : edges) {
			Description target = dag.getEdgeTarget(edge);

			// I don't want to consider as parents the equivalent node of
			// the current node desc
			if (equivalenceSet.contains(target)) 
				continue;

			Set<Description> namedEquivalences = getEquivalences(target);

			if (!namedEquivalences.isEmpty())
				result.add(namedEquivalences);
			else 
				result.addAll(getNamedParents(target));
		}
		return result;
	}

	/**
	 * Traverse the graph return the descendants starting from the given node of
	 * the dag
	 * 
	 * @param desc node we want to know the descendants
	 *
	 *@return we return a set of set of description to distinguish between
	 *         different nodes and equivalent nodes. equivalent nodes will be in
	 *         the same set of description
	 */
	@Override
	public Set<Set<Description>> getDescendants(Description desc) {

		LinkedHashSet<Set<Description>> result = new LinkedHashSet<Set<Description>>();

		Description node = dag.getReplacementFor(desc);
		if (node == null)
			node = desc;
		
		// reverse the dag
		DirectedGraph<Description, DefaultEdge> reversed = dag.getReversedDag();

		AbstractGraphIterator<Description, DefaultEdge>  iterator = 
					new BreadthFirstIterator<Description, DefaultEdge>(reversed, node);

		// I don't want to consider the current node
		iterator.next();

		Description startNode = desc;
		Set<Description> sourcesStart = getEquivalences(startNode);
		Set<Description> sourcesStartnoNode = new HashSet<Description>();
		for (Description equivalent : sourcesStart) {
			if (equivalent.equals(startNode))
				continue;
			sourcesStartnoNode.add(equivalent);
		}

		if (!sourcesStartnoNode.isEmpty())
			result.add(sourcesStartnoNode);

		// iterate over the subsequent nodes, they are all descendant of desc
		while (iterator.hasNext()) {
			Description child = iterator.next();

			// add the node and its equivalent nodes
			Set<Description> sources = getEquivalences(child);
			if (!sources.isEmpty())
				result.add(sources);
		}

		// add each of them to the result
		return Collections.unmodifiableSet(result);
	}

	/**
	 * Traverse the graph return the ancestors starting from the given node of
	 * the dag
	 * 
	 * @param desc node we want to know the ancestors
	 * @return we return a set of set of description to distinguish between
	 *         different nodes and equivalent nodes. equivalent nodes will be in
	 *         the same set of description
	 */

	@Override
	public Set<Set<Description>> getAncestors(Description desc) {

		LinkedHashSet<Set<Description>> result = new LinkedHashSet<Set<Description>>();

		Description node = dag.getReplacementFor(desc);
		if (node == null)
			node = desc;

		AbstractGraphIterator<Description, DefaultEdge>  iterator = 
				new BreadthFirstIterator<Description, DefaultEdge>(dag.getDag(), node);

		// I don't want to consider the current node
		iterator.next();

		Description startNode = desc;
		Set<Description> sourcesStart = getEquivalences(startNode);
		Set<Description> sourcesStartnoNode = new HashSet<Description>();
		for (Description equivalent : sourcesStart) {
			if (equivalent.equals(startNode))
				continue;
			sourcesStartnoNode.add(equivalent);
		}

		if (!sourcesStartnoNode.isEmpty())
			result.add(sourcesStartnoNode);

		// iterate over the subsequent nodes, they are all ancestor of desc
		while (iterator.hasNext()) {
			Description parent = iterator.next();

			// add the node and its equivalent nodes
			Set<Description> sources = getEquivalences(parent);

			if (!sources.isEmpty())
				result.add(sources);
		}

		// add each of them to the result
		return Collections.unmodifiableSet(result);
	}

	/**
	 * Return the equivalences starting from the given node of the dag
	 * 
	 * @param desc node we want to know the ancestors
	 *            
	 * @return we return a set of description with equivalent nodes 
	 */

	@Override
	public Set<Description> getEquivalences(Description desc) {
		
		Set<Description> equivalences = new LinkedHashSet<Description>();
			for (Description vertex : dag.getEquivalenceClass0(desc)) {
				if (namedClasses.contains(vertex) || property.contains(vertex)) {
						equivalences.add(vertex);
				}
			}
		return Collections.unmodifiableSet(equivalences);
	}
	
	/**
	 * Return all the nodes in the DAG or graph
	 * 
	 * @return we return a set of set of description to distinguish between
	 *         different nodes and equivalent nodes. equivalent nodes will be in
	 *         the same set of description
	 */

	@Override
	public Set<Set<Description>> getNodes() {

		LinkedHashSet<Set<Description>> result = new LinkedHashSet<Set<Description>>();

		for (Description vertex : dag.vertexSet()) 
				result.add(getEquivalences(vertex));

		return result;
	}

}