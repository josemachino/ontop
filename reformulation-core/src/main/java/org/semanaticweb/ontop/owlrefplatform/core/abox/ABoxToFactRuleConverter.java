package org.semanaticweb.ontop.owlrefplatform.core.abox;

/*
 * #%L
 * ontop-reformulation-core
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

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

import org.openrdf.model.vocabulary.XMLSchema;
import org.semanaticweb.ontop.model.CQIE;
import org.semanaticweb.ontop.model.DatalogProgram;
import org.semanaticweb.ontop.model.Function;
import org.semanaticweb.ontop.model.OBDADataFactory;
import org.semanaticweb.ontop.model.ObjectConstant;
import org.semanaticweb.ontop.model.Predicate;
import org.semanaticweb.ontop.model.ValueConstant;
import org.semanaticweb.ontop.model.Predicate.COL_TYPE;
import org.semanaticweb.ontop.model.impl.OBDADataFactoryImpl;
import org.semanaticweb.ontop.model.impl.OBDAVocabulary;
import org.semanaticweb.ontop.ontology.Assertion;
import org.semanaticweb.ontop.ontology.ClassAssertion;
import org.semanaticweb.ontop.ontology.DataPropertyAssertion;
import org.semanaticweb.ontop.ontology.Description;
import org.semanaticweb.ontop.ontology.ObjectPropertyAssertion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ABoxToFactRuleConverter {
	private static Logger log = LoggerFactory.getLogger(ABoxToFactRuleConverter.class); 

	private static OBDADataFactory factory = OBDADataFactoryImpl.getInstance();

	public static void addFacts(Iterator<Assertion> assetions, DatalogProgram p, Map<Predicate, Description> equivalences) {
		int count = 0;
		while (assetions.hasNext()) {
			Assertion next = assetions.next();
			if (addFact(next, p, equivalences)) {
				count++;
			}
		}
		log.debug("Appended {} ABox assertions as fact rules", count);
		
	}

	private static boolean addFact(Assertion assertion, DatalogProgram p, Map<Predicate, Description> equivalences) {
		CQIE fact = getRule(assertion);
		if (fact != null) {
			p.appendRule(fact);
			return true;
		}
		return false;
	}

	private static CQIE getRule(Assertion assertion) {
		Function head = null;
		CQIE rule = null;
		if (assertion instanceof ClassAssertion) {
			ClassAssertion ca = (ClassAssertion) assertion;
			ObjectConstant c = ca.getObject();
			Predicate p = ca.getConcept();
			Predicate urifuction = factory.getUriTemplatePredicate(1);
			head = factory.getFunction(p, factory.getFunction(urifuction, factory.getConstantLiteral(c.getValue())));
			rule = factory.getCQIE(head, new LinkedList<Function>());
			// head = factory.getFunctionalTerm(p, c);
		} else if (assertion instanceof ObjectPropertyAssertion) {
			ObjectPropertyAssertion ca = (ObjectPropertyAssertion) assertion;
			ObjectConstant s = ca.getFirstObject();
			ObjectConstant o = ca.getSecondObject();
			Predicate p = ca.getPredicate();
			Predicate urifuction = factory.getUriTemplatePredicate(1);
			head = factory.getFunction(p, factory.getFunction(urifuction, factory.getConstantLiteral(s.getValue())), factory.getFunction(urifuction, factory.getConstantLiteral(o.getValue())));
			rule = factory.getCQIE(head, new LinkedList<Function>());
		} else if (assertion instanceof DataPropertyAssertion) {
			/* 
			 * We ignore these for the moment until next release.
			 */
			
//			DataPropertyAssertion ca = (DataPropertyAssertion) assertion;
//			ObjectConstant s = ca.getObject();
//			ValueConstant o = ca.getValue();
//			String typeURI = getURIType(o.getType());
//			Predicate p = ca.getPredicate();
//			Predicate urifuction = factory.getUriTemplatePredicate(1);
//			head = factory.getFunction(p, factory.getFunction(urifuction, s), factory.getFunction(factory.getPredicate(typeURI,1), o));
//			rule = factory.getCQIE(head, new LinkedList<Function>());
		} 	
		return rule;
	}
	
	public static String getURIType(COL_TYPE e) {
		String result = "";
		if (e == COL_TYPE.BOOLEAN) {
			result = XMLSchema.BOOLEAN.toString();
		} else if (e == COL_TYPE.DATETIME) {
			result = XMLSchema.DATETIME.toString();
		} else if (e == COL_TYPE.DECIMAL) {
			result = XMLSchema.DECIMAL.toString();
		} else if (e == COL_TYPE.DOUBLE) {
			result = XMLSchema.DOUBLE.toString();
		} else if (e == COL_TYPE.INTEGER) {
			result = XMLSchema.INTEGER.toString();
		} else if (e == COL_TYPE.LITERAL) {
			result = OBDAVocabulary.RDFS_LITERAL_URI;
		} else if (e == COL_TYPE.LITERAL_LANG) {
			result = OBDAVocabulary.RDFS_LITERAL_URI;
		} else if (e == COL_TYPE.STRING) {
			result = XMLSchema.STRING.toString();
		} else {
			result = OBDAVocabulary.RDFS_LITERAL_URI;
		}
		return result;
	}
}
