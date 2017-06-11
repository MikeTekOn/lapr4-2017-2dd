package lapr4.green.s2.core.n1150738.contacts.application;

import lapr4.green.s2.core.n1150738.contacts.domain.Profession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * A class to test ProfessionImporterService
 *
 * @author Henrique Oliveira [1150738@isep.ipp.pt]
 */
public class ProfessionImporterServiceTest {

    @Test
    public void ensureProfessionsAreCorrectlyImported() throws IOException, SAXException, ParserConfigurationException {
        ProfessionImporterService svc = new ProfessionImporterService();
        List<Profession> result = svc.professions("lapr4/green/s2/core/n1150738/contacts/test_professions.xml");
        List<Profession> expected = new LinkedList<>();
        expected.add(new Profession("Accountant"));
        expected.add(new Profession("Auditor"));
        expected.add(new Profession("Carpenter"));
        expected.add(new Profession("Stonemason"));
        expected.add(new Profession("Student"));
        expected.add(new Profession("Engineer"));
        expected.add(new Profession("Scientist"));
        assertTrue("Both Lists should be equal", expected.containsAll(result) && expected.size() == result.size());
    }

}