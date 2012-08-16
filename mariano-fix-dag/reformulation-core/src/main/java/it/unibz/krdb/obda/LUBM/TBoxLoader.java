package it.unibz.krdb.obda.LUBM;


import it.unibz.krdb.obda.model.OBDAQueryReasoner;
import it.unibz.krdb.obda.model.OBDAModel;
import it.unibz.krdb.obda.owlapi.OBDAOWLReasonerFactory;
import it.unibz.krdb.obda.owlrefplatform.core.DummyOBDAPlatformFactoryImpl;
import it.unibz.krdb.obda.owlrefplatform.core.ontology.Ontology;
import it.unibz.krdb.obda.owlrefplatform.core.translator.OWLAPI2Translator;

import java.io.File;

import org.semanticweb.owl.apibinding.OWLManager;
import org.semanticweb.owl.model.OWLOntology;
import org.semanticweb.owl.model.OWLOntologyManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TBoxLoader {

    private static final Logger log = LoggerFactory.getLogger(TBoxLoader.class);
    static final OWLOntologyManager manager = OWLManager.createOWLOntologyManager();

    private OWLOntology ontology;
    private Ontology dlLiterOntology;
    private String dataDir;
    private String owlfile;

    public TBoxLoader(String dataDir) throws Exception {

        this.dataDir = dataDir;
        this.owlfile = dataDir + "univ-bench.owl";
        ontology = manager.loadOntologyFromPhysicalURI((new File(owlfile)).toURI());
        OWLAPI2Translator translator = new OWLAPI2Translator();
        dlLiterOntology = translator.translate(ontology);
    }

    public Ontology loadOnto() throws Exception {

        return dlLiterOntology;
    }


    public OBDAQueryReasoner loadReasoner(OBDAModel apic, OWLOntologyManager manager) throws Exception {

        OBDAOWLReasonerFactory fac = new DummyOBDAPlatformFactoryImpl();
//        fac.setOBDAController(apic);
        return (OBDAQueryReasoner) fac.createReasoner(manager);


    }


}