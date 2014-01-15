package org.semanaticweb.ontop.ontology.impl;

/*
 * #%L
 * ontop-obdalib-core
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

import org.semanaticweb.ontop.model.Predicate;
import org.semanaticweb.ontop.ontology.DisjointDataPropertyAxiom;

public class DisjointDataPropertyAxiomImpl extends DisjointPropertyAxiomImpl implements DisjointDataPropertyAxiom{

	private static final long serialVersionUID = 2049346032304523558L;

	DisjointDataPropertyAxiomImpl(Predicate p1, Predicate p2) {
	//	if (p1.isDataProperty() && p2.isDataProperty())
		super(p1, p2);
		
	}

}
