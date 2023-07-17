package owlapi.tutorial;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.formats.RDFXMLDocumentFormat;
import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.reasoner.NodeSet;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.reasoner.OWLReasonerFactory;
import org.semanticweb.owlapi.reasoner.structural.StructuralReasonerFactory;
import uk.ac.manchester.cs.owlapi.modularity.ModuleType;
import uk.ac.manchester.cs.owlapi.modularity.SyntacticLocalityModuleExtractor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashSet;
import java.util.Set;

public class Module_Test {
    public static void main(String[] args) throws OWLOntologyCreationException, FileNotFoundException, OWLOntologyStorageException {
        IRI dao_iri = IRI.create("http://cor.esipfed.org/ont/~ushanri/DAO");

        OWLDataFactory df = OWLManager.getOWLDataFactory();
        OWLReasonerFactory reasonerFactory = new StructuralReasonerFactory();
        OWLOntologyManager m = OWLManager.createOWLOntologyManager();

        OWLOntology o = m.loadOntologyFromOntologyDocument(dao_iri);
        OWLClass drug = df.getOWLClass(IRI.create(dao_iri + "/Drug"));
        Set<OWLEntity> sig = new HashSet<OWLEntity>();
        sig.add(drug);
        OWLReasoner reasoner = reasonerFactory.createNonBufferingReasoner(o);
        Set<OWLEntity> seedSig = new HashSet<OWLEntity>();
        for (OWLEntity ent : sig) {
            seedSig.add(ent);
            if (ent.isOWLClass()) {
                NodeSet<OWLClass> subClasses = reasoner.getSubClasses(ent.asOWLClass(),
                        false);
                seedSig.addAll(subClasses.getFlattened());
            }
        }

        SyntacticLocalityModuleExtractor sme = new SyntacticLocalityModuleExtractor(m, o,
                ModuleType.TOP);
        @SuppressWarnings("unused")
        Set<OWLAxiom> mod = sme.extract(sig);


        File fileout = new File("C:\\Users\\Anh Duy\\Documents\\School\\Year 3\\Thesis\\SLME_test\\DAO_TOP.rdf");

        OWLOntologyManager m2 = OWLManager.createOWLOntologyManager();
        OWLOntology o2 = m2.createOntology(dao_iri);
        mod.forEach(o2::add);
        System.out.println(sig);
        System.out.println(o2);
        m2.saveOntology(o2, new RDFXMLDocumentFormat(), new FileOutputStream(fileout));

    }
}