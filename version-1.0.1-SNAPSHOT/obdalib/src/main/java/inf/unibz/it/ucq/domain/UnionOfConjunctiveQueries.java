/***
 * Copyright (c) 2008, Mariano Rodriguez-Muro.
 * All rights reserved.
 *
 * The OBDA-API is licensed under the terms of the Lesser General Public
 * License v.3 (see OBDAAPI_LICENSE.txt for details). The components of this
 * work include:
 * 
 * a) The OBDA-API developed by the author and licensed under the LGPL; and, 
 * b) third-party components licensed under terms that may be different from 
 *   those of the LGPL.  Information about such licenses can be found in the 
 *   file named OBDAAPI_3DPARTY-LICENSES.txt.
 */
package inf.unibz.it.ucq.domain;

import java.util.ArrayList;
import java.util.Iterator;

public class UnionOfConjunctiveQueries {
	
	private ArrayList<ConjunctiveQuery>	queries	= null;
	
	private boolean	uniqueResults = false;

	public ArrayList<ConjunctiveQuery> getQueries() {
		if (queries == null) {
			queries = new ArrayList<ConjunctiveQuery>();
		}
		return queries;
	}
	
	public void addQuery(ConjunctiveQuery query) {
		//verify signature
		getQueries().add(query);
	}
	
	public void setDistinct(boolean unique) {
		this.uniqueResults  = unique;
	}
	
	public boolean isDistinct() {
		return this.uniqueResults;
	}
	
}