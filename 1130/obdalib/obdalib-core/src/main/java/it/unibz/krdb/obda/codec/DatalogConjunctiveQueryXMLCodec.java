package it.unibz.krdb.obda.codec;

import it.unibz.krdb.obda.model.CQIE;
import it.unibz.krdb.obda.model.OBDAModel;
import it.unibz.krdb.obda.parser.DatalogProgramParser;
import it.unibz.krdb.obda.parser.DatalogQueryHelper;

import java.util.ArrayList;
import java.util.Collection;

import org.antlr.runtime.RecognitionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Element;

/**
 * The DatalogConjunctiveQueryCodec can be used to encode a conjunctive query
 * into XML or to decode a conjunctive query from XML
 * 
 * @author Manfred Gerstgrasser
 * 
 */

public class DatalogConjunctiveQueryXMLCodec extends ObjectXMLCodec<CQIE> {

	/**
	 * The tag used to represent a conjunctive query in XML
	 */
	private static final String	TAG				= "CQ";

	/**
	 * the current api controller
	 */
	OBDAModel				apic			= null;

	DatalogProgramParser		datalogParser	= new DatalogProgramParser();

	private final Logger		log				= LoggerFactory.getLogger(this.getClass());

	public DatalogConjunctiveQueryXMLCodec(OBDAModel apic) {
		this.apic = apic;
	}

	/**
	 * Decodes the given XML element into an conjunctive query.
	 */
	@Override
	public CQIE decode(Element input) {
		String query = input.getAttribute("string");

		return decode(query);
	}

	/**
	 * Encodes the given conjunctive query int XML.
	 */
	@Override
	public Element encode(CQIE hq) {

		Element mappingheadelement = createElement(TAG);
		TargetQeryToTextCodec codec = new TargetQeryToTextCodec(apic);
		mappingheadelement.setAttribute("string", codec.encode(hq));
		return mappingheadelement;
	}

	/**
	 * Returns all attributes used in conjunctive query element.
	 */

	@Override
	public Collection<String> getAttributes() {
		ArrayList<String> fixedAttributes = new ArrayList<String>();
		fixedAttributes.add("string");
		return fixedAttributes;
	}

	/**
	 * Returns the tag name for conjunctive queries
	 */

	@Override
	public String getElementTag() {
		// TODO Auto-generated method stub
		return TAG;
	}

	/**
	 * Decodes the given String into an conjunctive query.
	 */
	public CQIE decode(String input) {
		return parse(input);
	}

	private CQIE parse(String query) {
		CQIE cq = null;
		query = prepareQuery(query);
		try {
			datalogParser.parse(query);
			cq = datalogParser.getRule(0);
		} catch (RecognitionException e) {
			log.warn(e.getMessage());
		}
		return cq;
	}

	private String prepareQuery(String input) {
		String query = input;
		DatalogQueryHelper queryHelper = new DatalogQueryHelper(apic.getPrefixManager());

		String[] atoms = input.split(DatalogQueryHelper.DATALOG_IMPLY_SYMBOL, 2);
		if (atoms.length == 1) // if no head
			query = queryHelper.getDefaultHead() + " " + DatalogQueryHelper.DATALOG_IMPLY_SYMBOL + " " + query;

		// Append the prefixes
		query = queryHelper.getPrefixes() + query;

		return query;
	}
}