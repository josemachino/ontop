package org.semanaticweb.ontop.obda.quest.dag;

/*
 * #%L
 * ontop-quest-owlapi3
 * %%
 * Copyright (C) 2009 - 2013 Free University of Bozen-Bolzano
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */


import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import junit.framework.TestCase;

import org.jgrapht.graph.DefaultEdge;
import org.semanaticweb.ontop.ontology.Description;
import org.semanaticweb.ontop.owlrefplatform.core.dagjgrapht.DAGBuilderImpl;
import org.semanaticweb.ontop.owlrefplatform.core.dagjgrapht.DAGImpl;
import org.semanaticweb.ontop.owlrefplatform.core.dagjgrapht.GraphImpl;
import org.semanaticweb.ontop.owlrefplatform.core.dagjgrapht.TBoxReasonerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class S_TestTransitiveReduction extends TestCase {

	ArrayList<String> input= new ArrayList<String>();
	ArrayList<String> output= new ArrayList<String>();

	Logger log = LoggerFactory.getLogger(S_HierarchyTestNewDAG.class);

	public S_TestTransitiveReduction (String name){
		super(name);
	}

	public void setUp(){
		
		input.add("src/test/resources/test/dag/test-equivalence-roles-inverse.owl");
		input.add("src/test/resources/test/dag/test-role-hierarchy.owl");
		input.add("src/test/resources/test/stockexchange-unittest.owl");
		input.add("src/test/resources/test/dag/role-equivalence.owl");
		
		/** C -> B  -> A  C->A*/
		input.add("src/test/resources/test/newDag/transitive.owl");
		/** C -> B  -> A  C->D ->A C->A */
		input.add("src/test/resources/test/newDag/transitive2.owl");

		/** C = B -> ER -> A*/
		input.add("src/test/resources/test/newDag/equivalents1.owl");
		/** B -> A -> ER=C */
		input.add("src/test/resources/test/newDag/equivalents2.owl");
		/** C->B = ER -> A*/
		input.add("src/test/resources/test/newDag/equivalents3.owl");
		/** ER-> A=B=C */
		input.add("src/test/resources/test/newDag/equivalents4.owl");
		/** C=ER=A->B */
		input.add("src/test/resources/test/newDag/equivalents5.owl");
		/** D-> ER=C=B -> A*/
		input.add("src/test/resources/test/newDag/equivalents6.owl");
		/** P-> ER=B -> A  C=L ->ES-> ER */
		input.add("src/test/resources/test/newDag/equivalents7.owl");
		/** B->A=ET->ER C->ES=D->A*/
		input.add("src/test/resources/test/newDag/equivalents8.owl");

		/** C = B -> ER- -> A*/
		input.add("src/test/resources/test/newDag/inverseEquivalents1.owl");
		/** B -> A -> ER- = C */
		input.add("src/test/resources/test/newDag/inverseEquivalents2.owl");
		/** C->B = ER- -> A*/
		input.add("src/test/resources/test/newDag/inverseEquivalents3.owl");
		/** ER- -> A=B=C */
		input.add("src/test/resources/test/newDag/inverseEquivalents4.owl");
		/** C=ER- =A->B */
		input.add("src/test/resources/test/newDag/inverseEquivalents5.owl");
		/** D-> ER- =C=B -> A*/
		input.add("src/test/resources/test/newDag/inverseEquivalents6.owl");
		/** P-> ER- =B -> A  C=L ->ES- -> ER- */
		input.add("src/test/resources/test/newDag/inverseEquivalents7.owl");
		/** B->A=ET- ->ER- C->ES- = D->A*/
		input.add("src/test/resources/test/newDag/inverseEquivalents8.owl");



	}

	public void testSimplification() throws Exception{
		//for each file in the input
		for (int i=0; i<input.size(); i++){
			String fileInput=input.get(i);

			GraphImpl graph1= S_InputOWL.createGraph(fileInput);

			DAGBuilderImpl change2 = new DAGBuilderImpl(graph1);
			
			DAGImpl dag2=(DAGImpl) change2.getDAG();
//			DAGImpl dag2= S_InputOWL.createDAG(fileInput);


			log.debug("Input number {}", i+1 );
			log.info("First graph {}", graph1);
			log.info("Second dag {}", dag2);
			
			

			assertTrue(testRedundantEdges(graph1,dag2));


		}
	}


	private boolean testRedundantEdges(GraphImpl g1, DAGImpl d2){
		//number of edges in the graph
		int  numberEdgesD1= g1.edgeSet().size();
		//number of edges in the dag
		int numberEdgesD2 = d2.edgeSet().size();

		//number of edges between the equivalent nodes
		int numberEquivalents=0;

		//number of redundant edges 
		int numberRedundants=0;

		TBoxReasonerImpl reasonerd2= new TBoxReasonerImpl(d2);

		Set<Set<Description>> nodesd2= reasonerd2.getNodes(false);
		Iterator<Set<Description>> it1 =nodesd2.iterator();
		while (it1.hasNext()) {
			Set<Description> equivalents=it1.next();

			//two nodes have two edges, three nodes have three edges...
			if(equivalents.size()>=2){
				numberEquivalents += equivalents.size();
			}
		}

		TBoxReasonerImpl reasonerd1= new TBoxReasonerImpl(g1);

		Set<Set<Description>> nodesg1= reasonerd1.getNodes(false);
		Iterator<Set<Description>> it2 =nodesg1.iterator();
		

		while (it2.hasNext()) {

			
			Set<Description> equivalents=it2.next();
			log.info("equivalents {} ", equivalents);
			
			
			
			//check if there are redundant edges
			for (Description vertex: equivalents){
				if(g1.incomingEdgesOf(vertex).size()!= g1.inDegreeOf(vertex)) //check that there anren't two edges pointing twice to the same nodes
					numberRedundants +=g1.inDegreeOf(vertex)- g1.incomingEdgesOf(vertex).size();
			
				
				//descendants of the vertex
				Set<Set<Description>> descendants=reasonerd2.getDescendants(vertex, false);
				Set<Set<Description>> children=reasonerd2.getDirectChildren(vertex, false);

				log.info("descendants{} ", descendants);
				log.info("children {} ", children);

				for(DefaultEdge edge: g1.incomingEdgesOf(vertex)){
					Description source=g1.getEdgeSource(edge);
					for(Set<Description> descendant:descendants){

					if (!children.contains(descendant) & ! equivalents.contains(descendant.iterator().next()) &descendant.contains(source))
						numberRedundants +=1;	
					}
					

				}
				
				
			}

		}

		log.info("edges graph {}", numberEdgesD1);
		log.info("edges dag {}", numberEdgesD2);
		log.info("equivalents {} ", numberEquivalents);
		log.info("redundants {} ", numberRedundants);

		return numberEdgesD1>= (numberRedundants+ numberEquivalents+ numberEdgesD2);

	}
}

