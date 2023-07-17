package owlapi.tutorial;

import org.coode.owlapi.manchesterowlsyntax.ManchesterOWLSyntaxOntologyFormat;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.formats.FunctionalSyntaxDocumentFormat;
import org.semanticweb.owlapi.formats.RDFDocumentFormat;
import org.semanticweb.owlapi.formats.RDFaDocumentFormat;
import org.semanticweb.owlapi.model.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class OWLAPIFirst {
    public static void main(String[] args) throws OWLOntologyCreationException {
        IRI IOR = IRI.create("http://owl.api.tutorial");
        OWLOntologyManager man = OWLManager.createOWLOntologyManager();
        OWLOntology o = man.createOntology(IOR);
        OWLDataFactory df = o.getOWLOntologyManager().getOWLDataFactory();

        // Classes
        OWLClass person = df.getOWLClass(IOR + "#Person");
        OWLClass student = df.getOWLClass(IOR + "#Student");
        OWLClass uni = df.getOWLClass(IOR + "#University");
        OWLClass course = df.getOWLClass(IOR + "#Course");

        // Object properties
        OWLObjectProperty enr = df.getOWLObjectProperty(IOR + "#isEnrolledIn");
        OWLObjectProperty attends = df.getOWLObjectProperty(IOR + "#attends");

        // ObjectSomeValuesFrom
        OWLObjectSomeValuesFrom pe = df.getOWLObjectSomeValuesFrom(enr, uni);
        OWLObjectSomeValuesFrom ac = df.getOWLObjectSomeValuesFrom(attends, course);

        //Intersection
        OWLObjectIntersectionOf peac = df.getOWLObjectIntersectionOf();


        // Individuals
        OWLNamedIndividual minju = df.getOWLNamedIndividual(IOR + "#Minju");
        OWLNamedIndividual mu = df.getOWLNamedIndividual(IOR + "ManchesterUniversity");

        // Assertions


       /* OWLDeclarationAxiom da = df.getOWLDeclarationAxiom(person);
        OWLSubClassOfAxiom w_sub_p = df.getOWLSubClassOfAxiom(woman, person);
        //o.add(da);
        o.add(w_sub_p);
        System.out.println(o); */
    }
}
